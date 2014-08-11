/*
 * 文 件 名：AttachmentFileDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：sunwengang/903820
 * 修改时间：2011-7-25
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.atachmentfile.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.atachmentfile.dao.AttachmentFileDao;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * @author sunwengang/903820
 * @version [版本号, 2011-7-25]
 * @since [产品/模块版本]
 */
@Repository("attachmentFileDao")
public class AttachmentFileDaoImpl extends
        GenericDaoImpl<AttachmentFile, String> implements AttachmentFileDao
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return
     */
    @Override
    public Session creatSqlSession()
    {
        return getSession();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAttachmentListBySql(String sql) {
		SQLQuery queryList = this.getSession().createSQLQuery(sql);
		List<Object> list = queryList.list();
		return list;
	}
}
