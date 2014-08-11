/*
 * 文 件 名：AppCertificateDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：证书操作Dao层
 * 修 改 人：wangchenbo/906055
 * 修改时间：2012-9-21
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.certificate.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.certificate.dao.AppCertificateDao;
import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;

/**
 * 证书操作Dao层.
 * 
 * @author  wangchenbo/906055
 * @version  [版本号, 2012-9-21]
 * @since  [产品/模块版本]
 */
@Repository("AppCertificateDao")
public class AppCertificateDaoImpl extends
        GenericDaoImpl<AppCertificate, String> implements AppCertificateDao
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

}
