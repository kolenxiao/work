package com.coship.sdp.sce.dp.action.system;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.InitAppInfoInfo;
import com.coship.sdp.sce.dp.job.HttpPostSynData;
import com.coship.sdp.sce.dp.utils.HttpUtil;
import com.coship.sdp.utils.Debug;

@Controller
public class SystemAdminAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MODULE_NAME = SystemAdminAction.class.getName();
	
    @Autowired
    private HttpPostSynData httpPostSynData;
    private ExecutorService executorService = Executors.newFixedThreadPool(3);
	
	
	public String cacheHome(){
		return "cacheHome";
	}
	
	
	/**
	 * 手动刷新系统缓存
	 * @return
	 */
	public String refreshCache() {
		Debug.logInfo("refreshCache star", MODULE_NAME);
		long beginTime = System.currentTimeMillis();
		try {
 			String[] ipArr = HttpUtil.getServerIps();
 			if(null == ipArr){
 				throw new RuntimeException("配置文件中缺少appstore.cache.ip的值");
 			}

            List<Future<?>> futureList = new ArrayList<Future<?>>();
			for (final String ip : ipArr) {
                Future<?> future = executorService.submit(new Runnable() {
		            @Override  
		            public void run() {  
		            	InitAppInfoInfo.executeInit(ip, Constants.APPSTORE_DPPORTAL_PORT);
		            }  
		        }); 
                futureList.add(future);
			}
			
            for (Future<?> future : futureList) {
                future.get();
            }

			this.setResult("success", true);
			
			long endTime = System.currentTimeMillis();
			Debug.logInfo("refreshCache success, cost time:" + (endTime - beginTime) + " ms", MODULE_NAME);
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			this.setResult("success", false);
			this.setResult("msg", e.getMessage());
		}
		this.write(JSONObject.fromObject(this.getResult()).toString());
		return null;
	}
	
	/**
	 * 手动同步数据到搜索引擎
	 * @return
	 */
	public String syncDataToSearch(){
		Debug.logInfo("syncDataToSearch start");
		long beginTime = System.currentTimeMillis();
		try {
			httpPostSynData.syncDataToPlatform();
			this.setResult("success", true);
			
			long endTime = System.currentTimeMillis();
			Debug.logInfo("syncDataToSearch success, cost time:" + (endTime - beginTime) + " ms", MODULE_NAME);
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			this.setResult("success", false);
			this.setResult("msg", e.getMessage());
		}
		this.write(JSONObject.fromObject(this.getResult()).toString());
		return null;
	}

	
	

}
