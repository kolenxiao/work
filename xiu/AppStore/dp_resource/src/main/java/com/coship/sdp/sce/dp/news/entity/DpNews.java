/*
 * 文件名称：DpNews.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Aug 31, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.type.entity.DpType;

/**
 * <资讯实体类>
 * @author Huangliufei/905735
 * @version [版本号, Aug 31, 2011]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DpNews extends EntityObject
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 2068893615607202361L;

    /**
     * 资讯标题
     */
    private String newsTitle;
    /**
     * 资讯类型
     */
    private DpType dpType;

    /**
     * 资讯来源地址
     */
    private String newsSourceUrl;

    /**
     * 资讯来源
     */
    private String newsSource;

    /**
     * 资讯概要
     */
    private String newsSummary;

    /**
     * 资讯正文
     */
    private String newsContent;

    /**
     * 创建时间
     */
    private Date newsCreateTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态
     * 1001 : 初稿
     * 1002   ：上线
     */
    private String newsStstus;

    /**
     * 排序
     */
    private int newsOrderId;

    /**
     * 创建者
     */
    private String  createUser;

    /**
     * 创建开始时间   < 作用描述：该字段是用于页面列表查询时的开始时间字段值,不保存数据库>
     */
    private String beginNewsCtime;

    /**
     * 创建结束时间  < 作用描述：该字段是用于页面列表查询时的开始时间字段值,不保存数据库>
     */
    private String endNewsCtime;

    /**
     * 资讯点击次数
     */
    private int clickTime;

    @Column(length = 128)
    public String getNewsTitle()
    {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle)
    {
        this.newsTitle = newsTitle;
    }

    @ManyToOne
    @JoinColumn(name="C_TYPE_ID")
    public DpType getDpType()
    {
        return dpType;
    }

    public void setDpType(DpType dpType)
    {
        this.dpType = dpType;
    }

    @Column(length = 128)
    public String getNewsSourceUrl()
    {
        return newsSourceUrl;
    }

    public void setNewsSourceUrl(String newsSourceUrl)
    {
        this.newsSourceUrl = newsSourceUrl;
    }
    @Column(length = 128)
    public String getNewsSource()
    {
        return newsSource;
    }

    public void setNewsSource(String newsSource)
    {
        this.newsSource = newsSource;
    }
    @Column(length = 512)
    public String getNewsSummary()
    {
        return newsSummary;
    }

    public void setNewsSummary(String newsSummary)
    {
        this.newsSummary = newsSummary;
    }
    @Column(length = 30000)
    public String getNewsContent()
    {
        return newsContent;
    }

    public void setNewsContent(String newsContent)
    {
        this.newsContent = newsContent;
    }

    public Date getNewsCreateTime()
    {
        Date date = newsCreateTime;
        return date;
    }

    public void setNewsCreateTime(Date newsCreateTime)
    {
        if (null != newsCreateTime) {
            this.newsCreateTime = (Date) newsCreateTime.clone();
        } else {
            this.newsCreateTime = new Date();
        }
    }

    public Date getUpdateTime()
    {
        Date date = updateTime;
        return date;
    }

    public void setUpdateTime(Date updateTime)
    {
        if (null != updateTime) {
            this.updateTime = (Date) updateTime.clone();
        } else {
            this.updateTime = new Date();
        }
    }
    @Column(length = 8)
    public String getNewsStstus()
    {
        return newsStstus;
    }

    public void setNewsStstus(String newsStstus)
    {
        this.newsStstus = newsStstus;
    }

    public int getNewsOrderId()
    {
        return newsOrderId;
    }

    public void setNewsOrderId(int newsOrderId)
    {
        this.newsOrderId = newsOrderId;
    }
    @Column(length=64)
    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    @Transient
    public String getBeginNewsCtime()
    {
        return beginNewsCtime;
    }

    public void setBeginNewsCtime(String beginNewsCtime)
    {
        this.beginNewsCtime = beginNewsCtime;
    }
    @Transient
    public String getEndNewsCtime()
    {
        return endNewsCtime;
    }

    public void setEndNewsCtime(String endNewsCtime)
    {
        this.endNewsCtime = endNewsCtime;
    }

    public int getClickTime()
    {
        return clickTime;
    }

    public void setClickTime(int clickTime)
    {
        this.clickTime = clickTime;
    }



}
