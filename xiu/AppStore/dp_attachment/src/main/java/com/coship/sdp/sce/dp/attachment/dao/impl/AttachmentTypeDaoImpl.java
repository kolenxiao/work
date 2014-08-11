/*
 * 文 件 名：AttachmentTypeDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-8-30
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.attachment.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.coship.sdp.dp.access.dao.impl.PageGenericDaoImpl;
import com.coship.sdp.sce.dp.attachment.dao.AttachmentTypeDao;
import com.coship.sdp.sce.dp.attachment.entity.AttachmentType;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * @author  Sunwengang/903820
 * @version  [版本号, 2011-8-30]
 * @since  [产品/模块版本]
 */
@Repository("attachmentTypeDao")
public class AttachmentTypeDaoImpl extends PageGenericDaoImpl<AttachmentType, Long> implements AttachmentTypeDao<AttachmentType, Long>{

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param attachTypeName
     * @return [参数说明]
     * @return List<AttachmentType> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public List<AttachmentType> findAttachmentTypeByTypeName(String attachTypeName) {
        Object[] values = {attachTypeName};
        return this.query("from AttachmentType as t where t.attachTypeName=?", values);
    }
}
