/*
 * 文件名称：AppStore.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-13
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.appstore.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 应用客户端
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-13]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppStoreClient extends EntityObject
{
    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 版本号
     */
    private int versionCode;

    /**
     * 应用安装包保存路径
     */
    private String apkFileSavePath;

    /**
     * 系统要求
     */
    private int system;

    /**
     * 客户端的状态
     */
    private String status;

    private String teminalNum;

    /**
     * 客户端创建时间
     */
    private Date createTime;

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public int getVersionCode()
    {
        return versionCode;
    }

    public void setVersionCode(int versionCode)
    {
        this.versionCode = versionCode;
    }

    public int getSystem()
    {
        return system;
    }

    public void setSystem(int system)
    {
        this.system = system;
    }

    public String getApkFileSavePath()
    {
        return apkFileSavePath;
    }

    public void setApkFileSavePath(String apkFileSavePath)
    {
        this.apkFileSavePath = apkFileSavePath;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getTeminalNum() {
		return teminalNum;
	}

	public void setTeminalNum(String teminalNum) {
		this.teminalNum = teminalNum;
	}

	public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
