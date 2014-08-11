/*
 * 文件名称：UserDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：chenjiawei/903903
 * 创建时间：2011-7-5
 * 
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.permission.dao;

import java.io.Serializable;

import com.coship.sdp.dp.access.dao.IPageGenericDao;
import com.coship.sdp.sce.dp.permission.entity.User;

/**
 * <功能描述>
 * @author  chenjiawei/903903
 * @version  [版本号, 2011-7-5]
 * @since  [产品/模块版本]
 */
public interface UserDao<T, PK extends Serializable> extends IPageGenericDao<T, Long> {

}
