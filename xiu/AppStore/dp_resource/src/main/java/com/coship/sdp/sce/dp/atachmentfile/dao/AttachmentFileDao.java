/*
 * 文 件 名：AttachmentFileDao.java
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：sunwengang/903820
 * 修改时间：2011-7-25
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.atachmentfile.dao;

import java.util.List;

import org.hibernate.Session;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;

/**
 * <一句话功能简述>. <功能详细描述>
 * @author sunwengang/903820
 * @version [版本号, 2011-7-25]
 * @since [产品/模块版本]
 */
public interface AttachmentFileDao extends IGenericDao<AttachmentFile, String>
{

    /**
     * <功能描述>
     * @return [参数说明]
     * @return Session [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    Session creatSqlSession();
    
    public List<Object> findAttachmentListBySql(String sql);

}
