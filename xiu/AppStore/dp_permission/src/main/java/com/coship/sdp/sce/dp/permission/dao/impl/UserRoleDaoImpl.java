package com.coship.sdp.sce.dp.permission.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.dp.access.dao.impl.PageGenericDaoImpl;
import com.coship.sdp.sce.dp.permission.dao.UserRoleDao;
import com.coship.sdp.sce.dp.permission.entity.UserRole;

/**
 * <功能描述>
 * @author  maqi/906092
 * @version  [版本号, 2011-7-5]
 * @since  [产品/模块版本]
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends PageGenericDaoImpl<UserRole, Long> implements UserRoleDao<UserRole, Long> {

}
