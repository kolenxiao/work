package com.coship.core.dal.sync;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * 集群内节点
 * 
 * @author 906324
 *
 */
public class ClusterNode {
	private String ip;

	public ClusterNode(String ip){
		this.ip = ip;
	}

	public NioSocketConnector connect(){
		NioSocketConnector connector = new NioSocketConnector();
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("myChin", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		chain.addLast("exceutor", new ExecutorFilter());  
		connector.setHandler(new IoHandlerAdapter(){});
		
		return connector;
	}

	public String getIp() {
		return ip;
	}

	@Override
	public String toString() {
		return ip;
	}
}