/*
 * 文件名称：DpAuditRecord.java
 * 版    权：Shenzhen Coship Electronics
 * Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.audit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;

/**
 * <功能描述>.
 * @author FuJian/906126
 * @version [版本号, 2011-9-7]
 * @since [产品/模块版本]
 */
@Entity
public class DpAuditRecord extends EntityObject
{

    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 审核时间.
     */
    private Date auditDate;

    /**
     * 审核意见.
     */
    private String auditOption;

    /**
     * 审核结果.
     */
    private String auditResult;

    /**
     * 审核员.
     */
    private String assessor;

    /**
     * 被审核记录的ID信息.
     */
    private String auditRecordId;

    /**
     * 应用信息
     */
    private DpAppInfo dpAppInfo;

    /**
     * 审核类型（1：新闻审核，2：App应用审核； 3：ap账户审核）.
     */
    private String auditFlag;

    @Column
    public Date getAuditDate()
    {
        Date date = auditDate;
        return date;
    }

    public void setAuditDate(Date auditDate)
    {
        if (null != auditDate)
        {
            this.auditDate = (Date) auditDate.clone();
        }
        else
        {
            this.auditDate = new Date();
        }
    }

    @Column(length = 256)
    public String getAuditOption()
    {
        return auditOption;
    }

    public void setAuditOption(String auditOption)
    {
        this.auditOption = auditOption;
    }

    @Column(length = 4)
    public String getAuditResult()
    {
        return auditResult;
    }

    public void setAuditResult(String auditResult)
    {
        this.auditResult = auditResult;
    }

    public String getAuditRecordId()
    {
        return auditRecordId;
    }

    public void setAuditRecordId(String auditRecordId)
    {
        this.auditRecordId = auditRecordId;
    }

    @Column(length = 32)
    public String getAuditFlag()
    {
        return auditFlag;
    }

    public void setAuditFlag(String auditFlag)
    {
        this.auditFlag = auditFlag;
    }

    @Column(length = 32)
    public String getAssessor()
    {
        return assessor;
    }

    public void setAssessor(String assessor)
    {
        this.assessor = assessor;
    }

    @Transient
    public DpAppInfo getDpAppInfo()
    {
        return dpAppInfo;
    }

    public void setDpAppInfo(DpAppInfo dpAppInfo)
    {
        this.dpAppInfo = dpAppInfo;
    }

}
