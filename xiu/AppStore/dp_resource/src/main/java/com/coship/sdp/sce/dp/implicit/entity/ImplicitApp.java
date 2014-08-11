/*
 * 文件名称：ImplicitApp.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：zhengxinlian/906976
 * 创建时间：2013-02-25
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.implicit.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 隐式应用实体对象
 * @author zhengxinlian/906976
 * @version [版本号, 2013-02-25]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ImplicitApp extends EntityObject
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
     * 文件名称
     */
    private String fileName;

    /**
     * 应用安装包保存路径
     */
    private String apkFileSavePath;

    /**
     * 系统要求
     */
    private String system;

    /**
     * 系统要求VO对象
     */
    private String systemVo;

    /**
     * 隐式应用的状态
     * 0：启用；1：停用
     */
    private String status;

    /**
     * 隐式应用创建时间
     */
    private Date createTime;

    /**
     * 隐式应用部署时间
     */
    private Date deployTime;

    /**
     * 包名.
     */
    private String appFilePackage;

    /**
     * 终端型号
     * 0：mstar和hisi；1：mstar；2：hisi
     */
    private String teminalNum;

    /**
     * 隐式类型
     * 1：安装
     * 2：升级
     * 3：卸载
     */
    private String implicitType;

    /**
     * 应用描述
     */
    private String description;

    /**
     *应用的签名证书
     */
    private String appCertId;

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

    public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
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

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getDeployTime() {
		return deployTime;
	}

	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}

	public String getAppFilePackage() {
		return appFilePackage;
	}

	public void setAppFilePackage(String appFilePackage) {
		this.appFilePackage = appFilePackage;
	}

	public String getTeminalNum() {
		return teminalNum;
	}

	public void setTeminalNum(String teminalNum) {
		this.teminalNum = teminalNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImplicitType() {
		return implicitType;
	}

	public void setImplicitType(String implicitType) {
		this.implicitType = implicitType;
	}

	@Transient
	public String getSystemVo() {
		return systemVo;
	}

	public void setSystemVo(String systemVo) {
		this.systemVo = systemVo;
	}

	public String getAppCertId() {
		return appCertId;
	}

	public void setAppCertId(String appCertId) {
		this.appCertId = appCertId;
	}

}
