package com.coship.sdp.sce.dp.appstore.service;

import java.io.Serializable;
import java.util.Map;

import com.coship.sdp.sce.dp.appstore.entity.UserApp;

/**
 * "用户拥有的应用" 服务层接口
 * 
 * @author 908618
 * 
 */
public interface UserAppService extends Serializable {

	/**
	 * 保存
	 * 
	 * @param userCode
	 * @param appPackageName
	 */
	public void save(String userCode, String appPackageName);

	/**
	 * 删除
	 * 
	 * @param userCode
	 * @param appPackageName
	 */
	public void delete(String userCode, String appPackageName);
	
	/**
	 * 查询
	 * @param userCode
	 * @param appPackageName
	 * @return
	 */
	public UserApp search(String userCode, String appPackageName);

	/**
	 * 查询列表
	 * @param userCode
	 * @return
	 */
	public Map<String, UserApp> search(String userCode);

}
