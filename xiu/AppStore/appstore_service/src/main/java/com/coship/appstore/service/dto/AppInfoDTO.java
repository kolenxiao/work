/*
 * 文件名称：AppInfoDTO.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Sep 29, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.appstore.service.dto;

import java.util.List;
import java.util.Map;

/**
 * <AppInfo对象> 该对象目的是保存dp中上架的ap应用信息，封装成json格式对象发送给AppStore.
 * @author Huangliufei/905735
 * @version [版本号, Sep 29, 2011]
 * @since [产品/模块版本]
 */
public class AppInfoDTO
{
    /**
     * 应用数据库记录id.
     */
    private String appId;

    /**
     * 应用的签名证书id
     */
    private String certificateId;

    /**
     * 应用名称.
     */
    private String name;

    /**
     * 应用名称拼音.
     */
    private String appNamePinyin;

    /**
     * 应用包名称.
     */
    private String appFilePackage;

    /**
     * apk下载URL.
     */
    private String apkFileUrl;

    /**
     * 应用类别：比如游戏，健康.
     */
    private String appType;

    /**
     * 简介.
     */
    private String description;

    /**
     * 版本名称.
     */
    private String version;

    /**
     * 版本编号
     */
    private String versionCode;

    /**
     * 应用发布时间.
     */
    private String publishTime;

    /**
     * 应用价格.
     */
    private double price;

    /**
     * 应用大小.
     */
    private long size;

    /**
     * 应用语言.
     */
    private String language;

    /**
     * 系统要求.
     */
    private String system;

    /**
     * 开发商.
     */
    private String developer;

    /**
     * 应用海报
     */
    private List<String> appPosters;

    /**
     * 应用logos
     */
    private List<String> appLogos;

    /**
     * 应用截图
     */
    private List<String> appImages;

    /**
     * 应用gameLogos
     */
    private List<String> appGameLogos;

    /**
     * 平均评分
     */
    private double averageScore;

    /**
     * 平均星值
     */
    private double averageStar;

    /**
     * 下载次数
     */
    private long downloadCount;

    /**
     * 评论次数
     */
    private long commentCount;

    /**
     * 五颗星分别的评论数
     */
    private String perStarCountStr;
    
    /**
     * 应用专题海报
     */
    private String subjectPoster;
    
    /**
     * 操作类型
     */
    private Integer opMode;
    
    /**
     * 父类型编码
     */
    private String parentTypeCode;
    
    /**
     * 缩略图
     */
    private Map<String, List<String>> thumbnail;
    
    /**
     * 是否有权限
     */
    private Integer hasPrivilege;

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getCertificateId()
    {
        return certificateId;
    }

    public void setCertificateId(String certificateId)
    {
        this.certificateId = certificateId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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

    public String getPublishTime()
    {
        return publishTime;
    }

    public void setPublishTime(String publishTime)
    {
        this.publishTime = publishTime;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public long getSize()
    {
        return size;
    }

    public void setSize(long size)
    {
        this.size = size;
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

    public String getAppType()
    {
        return appType;
    }

    public void setAppType(String appType)
    {
        this.appType = appType;
    }

    public String getAppNamePinyin()
    {
        return appNamePinyin;
    }

    public void setAppNamePinyin(String appNamePinyin)
    {
        this.appNamePinyin = appNamePinyin;
    }

    public String getAppFilePackage()
    {
        return appFilePackage;
    }

    public void setAppFilePackage(String appFilePackage)
    {
        this.appFilePackage = appFilePackage;
    }

    public double getAverageScore()
    {
        return averageScore;
    }

    public void setAverageScore(double averageScore)
    {
        this.averageScore = averageScore;
    }

    public long getDownloadCount()
    {
        return downloadCount;
    }

    public void setDownloadCount(long downloadCount)
    {
        this.downloadCount = downloadCount;
    }

    public long getCommentCount()
    {
        return commentCount;
    }

    public void setCommentCount(long commentCount)
    {
        this.commentCount = commentCount;
    }

    public String getApkFileUrl()
    {
        return apkFileUrl;
    }

    public void setApkFileUrl(String apkFileUrl)
    {
        this.apkFileUrl = apkFileUrl;
    }

    public List<String> getAppPosters()
    {
        return appPosters;
    }

    public void setAppPosters(List<String> appPosters)
    {
        this.appPosters = appPosters;
    }

    public List<String> getAppLogos()
    {
        return appLogos;
    }

    public void setAppLogos(List<String> appLogos)
    {
        this.appLogos = appLogos;
    }

    public List<String> getAppImages()
    {
        return appImages;
    }

    public void setAppImages(List<String> appImages)
    {
        this.appImages = appImages;
    }

    public String getPerStarCountStr()
    {
        return perStarCountStr;
    }

    public void setPerStarCountStr(String perStarCountStr)
    {
        this.perStarCountStr = perStarCountStr;
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

	public double getAverageStar() {
		return averageStar;
	}

	public void setAverageStar(double averageStar) {
		this.averageStar = averageStar;
	}

	public String getParentTypeCode() {
		return parentTypeCode;
	}

	public void setParentTypeCode(String parentTypeCode) {
		this.parentTypeCode = parentTypeCode;
	}

	public Map<String, List<String>> getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Map<String, List<String>> thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getHasPrivilege() {
		return hasPrivilege;
	}

	public void setHasPrivilege(Integer hasPrivilege) {
		this.hasPrivilege = hasPrivilege;
	}

	public List<String> getAppGameLogos() {
		return appGameLogos;
	}

	public void setAppGameLogos(List<String> appGameLogos) {
		this.appGameLogos = appGameLogos;
	}
	
}
