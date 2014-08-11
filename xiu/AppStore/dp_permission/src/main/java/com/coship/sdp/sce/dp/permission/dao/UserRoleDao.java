package com.coship.sdp.sce.dp.permission.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.coship.sdp.dp.access.dao.IPageGenericDao;

import com.coship.sdp.sce.dp.permission.entity.UserRole;

/**
 * <功能描述>
 * @author  maqi/906092
 * @version  [版本号, 2011-7-5]
 * @since  [产品/模块版本]
 */

public interface UserRoleDao <T, PK extends Serializable> extends IPageGenericDao<UserRole, Long> {

}
