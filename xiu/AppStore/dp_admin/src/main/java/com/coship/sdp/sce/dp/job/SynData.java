package com.coship.sdp.sce.dp.job;

/**
 * 数据同步接口，同步数据到平台搜索引擎
 * @author 907632
 * @version V100R001B050
 */
public interface SynData
{
	
	void syncDataToPlatform() throws Exception;
	
	/**
	 * 同步新增或更新数据<br/>
	 * URL:http://ip:port/search/services/receiveMsg
	 * RequestMethod:POST
	 * Encoding:UTF-8
	 * Type:HTTP+XML
	 * Response: 0 成功 1 失败
	 * @throws Exception
	 */
	void syncUpdateData() throws Exception;
    
	
	/**
	 * 同步删除数据<br/>
	 * URL:http://ip:port/search/services/receiveMsg
	 * RequestMethod:POST
	 * Encoding:UTF-8
	 * Type:HTTP+XML
	 * Response: 0 成功 1 失败
	 * @throws Exception
	 */
	void syncDeleteData() throws Exception;
}
