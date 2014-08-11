/*
 * 文件名称：DpNewsDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Aug 31, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.news.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.news.dao.DpNewsDao;
import com.coship.sdp.sce.dp.news.entity.DpNews;

/**
 * <资讯dao层实现类>.
 * @author  Huangliufei/905735
 * @version  [版本号, Aug 31, 2011]
 * @since  [产品/模块版本]
 */
@Repository("dpNewsDao")
public class DpNewsDaoImpl extends GenericDaoImpl<DpNews, String>
implements DpNewsDao {

    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;

}
