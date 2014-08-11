/*
 * 文件名称：DpAuditRecordDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-9-5
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.audit.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.audit.dao.DpAuditRecordDao;
import com.coship.sdp.sce.dp.audit.entity.DpAuditRecord;

/**
 * <功能描述>
 * @author  FuJian/906126
 * @version  [版本号, 2011-9-5]
 * @since  [产品/模块版本]
 */
@Repository("dpAuditRecordDao")
public class DpAuditRecordDaoImpl extends GenericDaoImpl<DpAuditRecord, String>  implements DpAuditRecordDao
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

}
