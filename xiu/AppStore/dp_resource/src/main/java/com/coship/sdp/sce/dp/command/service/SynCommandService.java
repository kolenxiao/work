package com.coship.sdp.sce.dp.command.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;

public interface SynCommandService extends Serializable
{
	/**
	 * 更新下发状态
	 * <h1>1 查询可用信息，并添加到下信息表中</h1>
	 * <h2>2 检查实际已经上架应用修改状态为待上传</h2>
	 * <h3>3 检查实际已经下架应用，修改状态为待删除</h3>
	 * @see SynCommandService#addCommand()
	 * @see SynCommandService#queryIssuedCommand()
	 * @see SynCommandService#updateBlockIssued()
	 * @see SynCommandService#updateBlockDelete()
	 * @throws Exception
	 */
	void updateStauts(int synCount,String flag) throws Exception;
     
	
	/**
	 * 检查已删除或待删除信息中是否有重新上架应用，将状态改为待发送
	 * @throws Exception
	 */
	void updateToBeIssued() throws Exception;
    
	/**
	 * 检查已发送/待发送信息中是否有已下架应用，将状态改为待删除
	 * @throws Exception
	 */
	void updateToBeDelete() throws Exception;
    
	/**
	 * 查询可同步应用信息ID
	 * @return
	 * @throws Exception
	 */
	List<String> queryIssuedCommand() throws Exception;
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	List<String> queryUnIssuedCommand(String appType) throws Exception;
	
	/**
	 * 新增可发送信息到同步表中
	 * @throws Exception
	 */
	void addCommand(final List<String> appList)throws Exception;
	
	/**
	 * 查询下发数据
	 * @return
	 * @throws Exception
	 */
	List<AppInfoDetail> getSynAppInfos() throws Exception;
	
	/**
	 * 标记哪些记录在本次任务中进行同步
	 * @throws Exception
	 */
    void signWhichSendNow(int synCount,String flag) throws Exception;
    
    /**
     * 修改下发后的状态是成功或失败
     * @param status
     * @param flag
     * @throws Exception
     */
	void updateStatusAfterSend(final int status, final String flag,final boolean isFail);
	
	/**
	 * 修改应用的同步状态，让应该可以同步到推荐引擎的修改接口
	 * @param appId
	 * @param appStatus
	 */
	public void updateStatusToModify(final String appId, final String appStatus);
	
}
