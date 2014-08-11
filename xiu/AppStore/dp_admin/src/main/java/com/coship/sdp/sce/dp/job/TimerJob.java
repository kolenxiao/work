package com.coship.sdp.sce.dp.job;

import com.coship.sdp.sce.dp.action.ap.DpAppInfoAction;
import com.coship.sdp.utils.Debug;

/**
 * 执行定时任务类
 * @author 907632
 * @version V100R001B050
 */
public class TimerJob
{   
	/**
	 * 模块的名称.
	 */
	private static final String MODULE_NAME = DpAppInfoAction.class.getName();
	
	//数据同步接口，同步数据到平台搜索引擎
	private SynData synData;
    
	/**
	 * 同步更新或修改数据到搜索平台
	 */
	public synchronized void asynData()
	{
		long start = System.currentTimeMillis();
		Debug.logVerbose("prepare sync data to platform", MODULE_NAME);
		try
		{
			synData.syncDataToPlatform();
		} catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
		}
		long end = System.currentTimeMillis();
		Debug.logVerbose(String.format("sync data to platform(time=%s)",end-start), MODULE_NAME);
	}
    
	
	public SynData getSynData()
	{
		return synData;
	}

	public void setSynData(SynData synData)
	{
		this.synData = synData;
	}

}
