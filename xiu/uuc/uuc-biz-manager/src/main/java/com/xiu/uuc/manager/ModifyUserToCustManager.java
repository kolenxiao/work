package com.xiu.uuc.manager;

import com.xiu.uuc.facade.dto.Result;

/**
 * 绑定用户到客户Manager
 * @ClassName: ModifyUserToCustManager 
 * @author xiaoyingping
 * @date 2011-7-19 上午11:26:01 
 *
 */
public interface ModifyUserToCustManager {
	
	/**
	 * 绑定用户到客户
	 * @Title: modifyUserToCust 
	 * @Description: 将已存在的用户关联到另一个客户ID上
	 * @param userId:用户ID 
	 *        bindLogonName：要绑定的用户登录名
	 *        bindPassword：  要绑定的用户登录密码
	 *        bindChannelId：要绑定的用户所属渠道标识
	 * @return Result    返回类型 
	 * @throws 
	 */
	public Result modifyUserToCust(Long userId, String bindLogonName, String bindPassword, Integer bindChannelId);

}
