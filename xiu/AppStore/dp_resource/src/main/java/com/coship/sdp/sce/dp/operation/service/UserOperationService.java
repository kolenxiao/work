package com.coship.sdp.sce.dp.operation.service;

import java.io.Serializable;

import com.coship.sdp.sce.dp.operation.entity.UserOperation;
import com.coship.sdp.utils.Page;

/**
 * <客户端用户操作记录服务层接口>.
 * 
 * @author Huangliufei/907632
 * @version [版本号, Aug 28, 2013]
 * @since [产品/模块版本]
 */
public interface UserOperationService extends Serializable {
	public void saveUserOperation(UserOperation userOperation) throws Exception;

	  /**
     * 分页查询,获取用户操作信息列表
     * @param page 分页对象
     * @param hql 查询字符串
     * @param values hql中的参数
     * @return 用户操作信息分页列表
     * @throws Exception [参数说明]
     * @return Page<UserOperation> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
	public Page<UserOperation> listUserOperation(Page<UserOperation> page, String hql,
			Object[] values) throws Exception;
}
