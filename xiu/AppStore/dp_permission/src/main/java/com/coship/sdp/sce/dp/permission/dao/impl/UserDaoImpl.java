/*
 * 文件名称：UserDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：chenjiawei/903903
 * 创建时间：2011-7-5
 * 
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.permission.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.dp.access.dao.impl.PageGenericDaoImpl;
import com.coship.sdp.sce.dp.permission.dao.UserDao;
import com.coship.sdp.sce.dp.permission.entity.User;

/**
 * <功能描述>
 * @author  chenjiawei/903903
 * @version  [版本号, 2011-7-5]
 * @since  [产品/模块版本]
 */
@Repository("userDao")
public class UserDaoImpl extends PageGenericDaoImpl<User, Long> implements UserDao<User, Long> {

}
