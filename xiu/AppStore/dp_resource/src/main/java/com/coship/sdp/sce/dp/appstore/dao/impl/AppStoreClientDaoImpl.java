/*
 * 文件名称：AppStoreClientDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-13
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.appstore.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.appstore.dao.AppStoreClientDao;
import com.coship.sdp.sce.dp.appstore.entity.AppStoreClient;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-13]
 * @since [产品/模块版本]
 */
@Repository("appStoreClientDao")
public class AppStoreClientDaoImpl extends
        GenericDaoImpl<AppStoreClient, String> implements AppStoreClientDao
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

}
