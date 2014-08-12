package com.coship.core.dal.sync;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.google.gson.Gson;

/**
 * 集群状态下，缓存数据发生变化时要在集群节点中同步
 * 
 * @author 906324
 * 
 */
public class CacheClusterSync {
	public static final int SYNC_PORT = 47890;
	
	private static final Logger logger = Logger.getLogger(CacheClusterSync.class);
	
	private static List<ClusterNode> nodes = new ArrayList<ClusterNode>();

	public static void addNodeIp(String nodeIp) {
		nodes.add(new ClusterNode(nodeIp));
	}

	public static List<ClusterNode> getNodes() {
		return nodes;
	}

	public static void syncAddEntity(Object entity) throws Exception {
		sync(CacheSyncObject.OPERATE_INSERT, null, entity, entity.getClass());
	}

	public static void syncModifyEntity(Object entity) throws Exception {
		sync(CacheSyncObject.OPERATE_UPDATE, null, entity, entity.getClass());
	}

	public static void syncDeleteEntity(Object entityId, Class<?> entityClass) throws Exception {
		sync(CacheSyncObject.OPERATE_DELETE, entityId, null, entityClass);
	}

	public static void syncReloadEntity(Class<?> entityClass) throws Exception {
		sync(CacheSyncObject.OPERATE_RELOAD, null, entityClass.newInstance(), entityClass);
	}
	
	public static void sync(String operate,Object id, Object entity,Class<?> entityClass) throws Exception {
		CacheSyncObject sync = new CacheSyncObject(operate);
		sync.setSyncDataId(id);
		sync.setSyncClassName(entityClass.getName());
		if (entity != null) {
			sync.setDataText(new Gson().toJson(entity));
		}
		syncCacheData(sync);
	}
	
	public static void sync(Object syncObject) {
		syncCacheData(syncObject);
	}
	
	private static ExecutorService pool = Executors.newFixedThreadPool(256);

	private static void syncCacheData(final Object sync) {
		pool.submit(new Runnable() {
			public void run() {
				for (ClusterNode node : nodes) {
					sendToNode(sync, node);
				}
			}
		});
	}
	
	private static void sendToNode(Object sync,ClusterNode node){
		IoSession session = null;
		NioSocketConnector connector = null;
		try {
			connector = node.connect();
			ConnectFuture connectFuture = connector.connect(new InetSocketAddress(node.getIp(), SYNC_PORT));
			if (connectFuture.awaitUninterruptibly(5000)) {
				if (connectFuture.isConnected()) {
					session = connectFuture.getSession();
				}
			}
			if (session == null) {
				throw new Exception("session创建失败:" + node);
			}
			WriteFuture WriteFuture = session.write(sync);
			WriteFuture.awaitUninterruptibly();
			if (WriteFuture.getException() != null) {
				throw new Exception("发送存在异常: " + node, WriteFuture.getException());
			}
			if (!WriteFuture.isWritten()) {
				throw new Exception("同步缓存对象失败:" + node + ":" + sync);
			}
		} catch (Exception e) {
			logger.error("缓存数据异常:"+node, e);
			addFailSync(sync, node);
		} finally{
			if (null != session) {
				session.close(true);
			}
			connector.dispose();
		}
	}
	private static BlockingQueue<FailSync> failSyncs = new LinkedBlockingQueue<FailSync>();
	
	private static ScheduledExecutorService failPool = Executors.newScheduledThreadPool(128);
	
	static{
		failPool.scheduleAtFixedRate(new Runnable(){
			public void run() {
				try {
					FailSync fail = failSyncs.take();
					logger.info("开始重新发送：" + fail.getNode() + ":" + fail.getSync());
					sendToNode(fail.getSync(), fail.getNode());
				} catch (InterruptedException e) {
					logger.error("取失败队列异常:", e);
				}
			}			
		}, 10, 10, TimeUnit.SECONDS);
	}
	
	private static void addFailSync(Object sync,ClusterNode node){
		FailSync failSync = new FailSync();
		failSync.setSync(sync);
		failSync.setNode(node);
		try {
			failSyncs.put(failSync);
			logger.error("加入失败队列:"+node+":"+sync);
		} catch (InterruptedException e) {
			logger.error("加入失败队列异常", e);
		}
	}
	
	private static class FailSync {
		private Object sync;
		private ClusterNode node;

		public Object getSync() {
			return sync;
		}

		public void setSync(Object sync) {
			this.sync = sync;
		}

		public ClusterNode getNode() {
			return node;
		}

		public void setNode(ClusterNode node) {
			this.node = node;
		}
	}
}