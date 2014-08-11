package com.coship.sdp.sce.dp.operation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.operation.dao.UserOperationDao;
import com.coship.sdp.sce.dp.operation.entity.UserOperation;
import com.coship.sdp.sce.dp.operation.service.UserOperationService;
import com.coship.sdp.utils.Page;


/**
 * <客户端用户操作记录服务层接口>.
 * @author  Huangliufei/907632
 * @version  [版本号, Aug 28, 2013]
 * @since  [产品/模块版本]
 */
@Service("userOperationService")
@Transactional
public class UserOperationServiceImpl implements UserOperationService
{

    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * classname.
     */
    private static final String MODULE = UserOperationService.class.getName();

    /**
     * 查询所有的hql语句.
     */
    private static final String QUERY_HQL = "from UserOperation op where 1=1";

    /**
     * dao层接口.
     */
    @Autowired
    private UserOperationDao userOperationDao;

    /**
     * 保存用户操作记录.
     * @param userOperation 资讯实体
     * @throws Exception 异常
     */
    @Override
    public void saveUserOperation(UserOperation userOperation) throws Exception
    {
        this.userOperationDao.save(userOperation);
    }
    
    @Override
    public Page<UserOperation> listUserOperation(Page<UserOperation> page, String hql,
			Object[] values) throws Exception {
        return userOperationDao.queryPage(page, hql, values);
    }

}
