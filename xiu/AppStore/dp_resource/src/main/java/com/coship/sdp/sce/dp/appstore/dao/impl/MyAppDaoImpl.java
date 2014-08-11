/*
 * 文件名称：MyAppDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Oct 11, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.appstore.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.appstore.dao.MyAppDao;
import com.coship.sdp.sce.dp.appstore.entity.MyApp;

/**
 * <功能描述>
 * @author Huangliufei/905735
 * @version [版本号, Oct 11, 2011]
 * @since [产品/模块版本]
 */
@Repository("myAppDao")
public class MyAppDaoImpl extends GenericDaoImpl<MyApp, String> implements
        MyAppDao
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    public Session creatSqlSession()
    {
        return getSession();
    }
}
