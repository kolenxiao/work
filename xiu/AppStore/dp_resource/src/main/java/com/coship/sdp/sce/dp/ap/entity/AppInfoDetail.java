/*
 * 文件名称：AppInfoDetail.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-27
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.entity;

import java.util.Date;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-27]
 * @since [产品/模块版本]
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppInfoDetail extends EntityObject
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
     * 应用名称拼音.
     */
    private String appNamePinyin;

    /**
     * 应用简介
     */
    private String appDesc;

    /**
     * 应用包名称.
     */
    private String appFilePackage;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 入库时间
     */
    private Date appTime;

    /**
     * 应用价格
     */
    private double price;

    /**
     * 版本名称
     */
    private String version;

    /**
     * 版本编码
     */
    private String versionCode;

    /**
     * 应用语言
     */
    private String language;

    /**
     * 系统要求
     */
    private String system;

    /**
     * 开发商
     */
    private String developer;

    /**
     * 应用的状态
     */
    private String appStatus;

    /**
     * 分类id
     */
    private String typeId;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 签名证书id
     */
    private String appCertId;

    /**
     * 平均评分
     */
    private Double avgScore;

    /**
     * 评论数
     */
    private long commentCount;

    /**
     * 下载次数
     */
    private long downloadCount;

    /**
     * 专题v
     */
    private String subjectId;
    
    /**
     * 应用专题海报
     */
    private String subjectPoster;
    
    /**
     * 操作类型
     */
    private Integer opMode;
    
    /**
     * 应用Logo图片地址
     */
    private String logoUrl;
    
    /**
     * 人工增加的下载次数
     */
    private int handDownCount;
    /**
     * 人工增加的平均评分
     */
    private double handAvgScore;
    /**
     * 人工增加的评分人数
     */
    private int handScoreCount;
    
    /**
     * 方案id
     */
    private String plan;
    
    

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getAppNamePinyin()
    {
        return appNamePinyin;
    }

    public void setAppNamePinyin(String appNamePinyin)
    {
        this.appNamePinyin = appNamePinyin;
    }

    public String getAppDesc()
    {
        return appDesc;
    }

    public void setAppDesc(String appDesc)
    {
        this.appDesc = appDesc;
    }

    public String getAppFilePackage()
    {
        return appFilePackage;
    }

    public void setAppFilePackage(String appFilePackage)
    {
        this.appFilePackage = appFilePackage;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Date getAppTime()
    {
        return appTime;
    }

    public void setAppTime(Date appTime)
    {
        this.appTime = appTime;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getVersionCode()
    {
        return versionCode;
    }

    public void setVersionCode(String versionCode)
    {
        this.versionCode = versionCode;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getSystem()
    {
        return system;
    }

    public void setSystem(String system)
    {
        this.system = system;
    }

    public String getDeveloper()
    {
        return developer;
    }

    public void setDeveloper(String developer)
    {
        this.developer = developer;
    }

    public String getAppStatus()
    {
        return appStatus;
    }

    public void setAppStatus(String appStatus)
    {
        this.appStatus = appStatus;
    }

    public String getTypeId()
    {
        return typeId;
    }

    public void setTypeId(String typeId)
    {
        this.typeId = typeId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getAppCertId()
    {
        return appCertId;
    }

    public void setAppCertId(String appCertId)
    {
        this.appCertId = appCertId;
    }

    public Double getAvgScore()
    {
        return avgScore;
    }

    public void setAvgScore(Double avgScore)
    {
        this.avgScore = avgScore;
    }

    public long getCommentCount()
    {
        return commentCount;
    }

    public void setCommentCount(long commentCount)
    {
        this.commentCount = commentCount;
    }

    public long getDownloadCount()
    {
        return downloadCount;
    }

    public void setDownloadCount(long downloadCount)
    {
        this.downloadCount = downloadCount;
    }

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

    public String getSubjectPoster()
    {
        return subjectPoster;
    }

    public void setSubjectPoster(String subjectPoster)
    {
        this.subjectPoster = subjectPoster;
    }

	public Integer getOpMode() {
		return opMode;
	}

	public void setOpMode(Integer opMode) {
		this.opMode = opMode;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public int getHandDownCount() {
		return handDownCount;
	}

	public void setHandDownCount(int handDownCount) {
		this.handDownCount = handDownCount;
	}

	public double getHandAvgScore() {
		return handAvgScore;
	}

	public void setHandAvgScore(double handAvgScore) {
		this.handAvgScore = handAvgScore;
	}

	public int getHandScoreCount() {
		return handScoreCount;
	}

	public void setHandScoreCount(int handScoreCount) {
		this.handScoreCount = handScoreCount;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}


	

}
