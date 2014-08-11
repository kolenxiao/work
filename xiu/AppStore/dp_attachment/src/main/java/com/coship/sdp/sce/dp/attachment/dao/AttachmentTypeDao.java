/*
 * 文 件 名：AttachmentTypeDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-8-30
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.attachment.dao;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.dp.access.dao.IPageGenericDao;
import com.coship.sdp.sce.dp.attachment.entity.AttachmentType;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * @author  Sunwengang/903820
 * @version  [版本号, 2011-8-30]
 * @since  [产品/模块版本]
 */
public interface AttachmentTypeDao<T, PK extends Serializable> extends IPageGenericDao<T, PK> {
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param attachTypeName
     * @return [参数说明]
     * @return List<AttachmentType> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<AttachmentType> findAttachmentTypeByTypeName(String attachTypeName);
}
