/*
 * 文件名称：Sign.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：签名实体
 * 创 建 人：wangchenbo/906055
 * 创建时间：2012-9-22
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.sign.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;

/**
 * 应用签名信息实体类
 * @author wangchenbo/906055
 * @version [版本号, 2012-9-22]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppSign extends EntityObject
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;


    /**
     * 已签名应用信息
     */
    private DpAppInfo dpAppInfo;

    /**
     * 已签名应用使用的证书和私钥.
     */
    private AppCertificate appCert;


    /**
     * 创建时间
     */
    private Date createTime;

    @OneToOne
    @JoinColumn(name = "C_APP_ID")
    public DpAppInfo getDpAppInfo()
    {
        return dpAppInfo;
    }

    public void setDpAppInfo(DpAppInfo dpAppInfo)
    {
        this.dpAppInfo = dpAppInfo;
    }
    @OneToOne
    @JoinColumn(name = "C_APP_CERT_ID")
    public AppCertificate getAppCert()
    {
        return appCert;
    }

    public void setAppCert(AppCertificate appCert)
    {
        this.appCert = appCert;
    }

    public Date getCreateTime()

    {
        Date date = createTime;
        return date;
    }

    public void setCreateTime(Date createTime)
    {
        if (null != createTime)
        {
            this.createTime = (Date) createTime.clone();
        }
        else
        {
            this.createTime = new Date();
        }
    }

}
