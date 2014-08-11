/*
 * 文件名称：AppCertificate.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：wangchenbo/906055
 * 创建时间：2012-9-21
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.certificate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

/**
 *
 * 证书实体.
 * @author wangchenbo/906055
 * @version [版本号, 2012-9-21]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppCertificate extends EntityObject
{
    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 证书原始名称.
     */
    private String certificateSrcName;

    /**
     * 证书保存名称.
     */
    private String certificateSaveName;

    /**
     * 私钥原始名称.
     */
    private String privateKeySrcName;

    /**
     * 私钥保存名称.
     */
    private String privateKeySaveName;

    /**
     * 吊销证书列表原始名称.
     */
    private String revokeSrcName;

    /**
     * 吊销证书列表保存名称.
     */
    private String revokeSaveName;

    /**
     * 证书类型:1001 签名证书和私钥，1003 吊销证书列表
     */
    private String certificateType;

    /**
     * 证书描述.
     */
    private String certificateDesc;

    /**
     * 证书吊销标记.
     */
    private String revokeFlag;

    /**
     * 序列号.
     */
    private String serialNumber;

    /**
     * 有效起始日期 .
     */
    private Date notBefore;

    /**
     * 有效终止日期.
     */
    private Date notAfter;

    /**
     * CRL本次更新时间函数
     */
    private Date thisUpdate;

    /**
     * CRL下次更新时间
     */
    private Date nextUpdate;

    /**
     * 创建日期.
     */
    private Date createdDate;

    /**
     * 修改日期.
     */
    private Date updatedDate;

    /**
     * 创建者.
     */
    private String createdUser;

    /**
     * 是否是默认设置
     */
    private int isDefault;

    @Column(length = 128)
    public String getCertificateSrcName()
    {
        return certificateSrcName;
    }

    public void setCertificateSrcName(String certificateSrcName)
    {
        this.certificateSrcName = certificateSrcName;
    }

    @Column(length = 128)
    public String getCertificateSaveName()
    {
        return certificateSaveName;
    }

    public void setCertificateSaveName(String certificateSaveName)
    {
        this.certificateSaveName = certificateSaveName;
    }

    @Column(length = 128)
    public String getPrivateKeySrcName()
    {
        return privateKeySrcName;
    }

    public void setPrivateKeySrcName(String privateKeySrcName)
    {
        this.privateKeySrcName = privateKeySrcName;
    }

    @Column(length = 128)
    public String getPrivateKeySaveName()
    {
        return privateKeySaveName;
    }

    public void setPrivateKeySaveName(String privateKeySaveName)
    {
        this.privateKeySaveName = privateKeySaveName;
    }

    @Column(length = 128)
    public String getRevokeSrcName()
    {
        return revokeSrcName;
    }

    public void setRevokeSrcName(String revokeSrcName)
    {
        this.revokeSrcName = revokeSrcName;
    }

    @Column(length = 128)
    public String getRevokeSaveName()
    {
        return revokeSaveName;
    }

    public void setRevokeSaveName(String revokeSaveName)
    {
        this.revokeSaveName = revokeSaveName;
    }

    @Column(length = 4)
    public String getCertificateType()
    {
        return certificateType;
    }

    public void setCertificateType(String certificateType)
    {
        this.certificateType = certificateType;
    }

    @Column(length = 512)
    public String getCertificateDesc()
    {
        return certificateDesc;
    }

    public void setCertificateDesc(String certificateDesc)
    {
        this.certificateDesc = certificateDesc;
    }

    @Column(length = 4)
    public String getRevokeFlag()
    {
        return revokeFlag;
    }

    public void setRevokeFlag(String revokeFlag)
    {
        this.revokeFlag = revokeFlag;
    }

    public Date getCreatedDate()
    {
        Date date = createdDate;
        return date;

    }

    public void setCreatedDate(Date createdDate)
    {

        if (null != createdDate)
        {
            this.createdDate = (Date) createdDate.clone();
        }
        else
        {
            this.createdDate = new Date();
        }
    }

    public Date getUpdatedDate()
    {
        Date date = updatedDate;
        return date;
    }

    public void setUpdatedDate(Date updatedDate)
    {

        if (null != updatedDate)
        {
            this.updatedDate = (Date) updatedDate.clone();
        }
        else
        {
            this.updatedDate = new Date();
        }
    }

    @Column(length = 64)
    public String getCreatedUser()
    {
        return createdUser;
    }

    public void setCreatedUser(String createdUser)
    {
        this.createdUser = createdUser;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public Date getNotBefore()
    {
        Date date = notBefore;
        return date;

    }

    public void setNotBefore(Date notBefore)
    {

        if (null != notBefore)
        {
            this.notBefore = (Date) notBefore.clone();
        }

    }

    public Date getNotAfter()
    {
        Date date = notAfter;
        return date;
    }

    public void setNotAfter(Date notAfter)
    {
        if (null != notAfter)
        {
            this.notAfter = (Date) notAfter.clone();
        }

    }

    public Date getThisUpdate()
    {
        Date date = thisUpdate;
        return date;
    }

    public void setThisUpdate(Date thisUpdate)
    {
        if (null != thisUpdate)
        {
            this.thisUpdate = (Date) thisUpdate.clone();
        }
    }

    public Date getNextUpdate()
    {
        Date date = nextUpdate;
        return date;
    }

    public void setNextUpdate(Date nextUpdate)
    {
        if (null != nextUpdate)
        {
            this.nextUpdate = (Date) nextUpdate.clone();
        }
    }

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

}
