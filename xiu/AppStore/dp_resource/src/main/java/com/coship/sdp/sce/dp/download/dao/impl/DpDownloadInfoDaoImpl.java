/*
 * 文 件 名：DpDownloadInfoDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-9-5
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.download.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.download.dao.DpDownloadInfoDao;
import com.coship.sdp.sce.dp.download.entity.DpDownloadInfo;

/**
 * <一句话功能简述>.
 * <功能详细描述>
 * @author  Sunwengang/903820
 * @version  [版本号, 2011-9-5]
 * @since  [产品/模块版本]
 */
@Repository("dpDownloadInfoDao")
public class DpDownloadInfoDaoImpl extends GenericDaoImpl<DpDownloadInfo,
        String>implements DpDownloadInfoDao {

    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;

}