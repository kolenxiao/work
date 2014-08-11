/*
 * 文件名称：AppStatisticsDto.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-20
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.entity;

import java.util.Date;

/**
 * 应用统计分析数据封装对象
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-20]
 * @since [产品/模块版本]
 */
public class AppStatisticsInfo
{

    /**
     * 应用id
     */
    private String appId;

    /**
     * 升降序标识变量
     */
    private String orderType;

    /**
     * 排序的属性
     */
    private String orderProperty;

    /**
     * 查询上架开始时间
     */
    private String startTime;

    /**
     * 查询上架结束时间
     */
    private String endTime;

    /**
     * 更新时间
     */
    public Date updateTime;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用分类
     */
    private String appType;

    /**
     * 应用分类
     */
    private String appTypeId;

    /**
     * 开发者名称
     */
    private String dpStaffName;

    /**
     * 评论次数
     */
    private int commentCount;

    /**
     * 评论平均分值
     */
    private double commentAvgScore;

    /**
     * 状态：上架或者下架状态
     */
    private String appStatus;

    /**
     * 应用的包名
     */
    private String packageName;

    /**
     * 应用下载量
     */
    private int downloadCount;

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public String getOrderProperty()
    {
        return orderProperty;
    }

    public void setOrderProperty(String orderProperty)
    {
        this.orderProperty = orderProperty;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getDpStaffName()
    {
        return dpStaffName;
    }

    public void setDpStaffName(String dpStaffName)
    {
        this.dpStaffName = dpStaffName;
    }

    public String getAppType()
    {
        return appType;
    }

    public void setAppType(String appType)
    {
        this.appType = appType;
    }

    public int getCommentCount()
    {
        return commentCount;
    }

    public void setCommentCount(int commentCount)
    {
        this.commentCount = commentCount;
    }

    public double getCommentAvgScore()
    {
        return commentAvgScore;
    }

    public void setCommentAvgScore(double commentAvgScore)
    {
        this.commentAvgScore = commentAvgScore;
    }

    public int getDownloadCount()
    {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount)
    {
        this.downloadCount = downloadCount;
    }

    public String getAppStatus()
    {
        return appStatus;
    }

    public void setAppStatus(String appStatus)
    {
        this.appStatus = appStatus;
    }

	public String getAppTypeId() {
		return appTypeId;
	}

	public void setAppTypeId(String appTypeId) {
		this.appTypeId = appTypeId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}



}
