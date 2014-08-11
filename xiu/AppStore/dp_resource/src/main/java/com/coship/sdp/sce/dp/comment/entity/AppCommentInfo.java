/*
 * 文件名称：AppCommentInfo.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.comment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;

/**
 * 应用的评论信息类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppCommentInfo extends EntityObject
{

    /**
     * 序列号
     */
    private static final long serialVersionUID = -5355801759965211554L;

    /**
     * 应用的基本信息
     */
    private DpAppInfo dpAppInfo;

    /**
     * 应用的包名
     */
    private String appPackageName;

    /**
     * 评论的用户昵称
     */
    private String commentUserName;

    /**
     * 评论用户设备id
     */
    private String commentUserId;

    /**
     * 评论的时间
     */
    private Date createDate;

    /**
     * 评论的内容
     */
    private String commentContent;

    /**
     * 评分值
     */
    private int score;

    @ManyToOne
    @JoinColumn(name = "C_APP_ID")
    public DpAppInfo getDpAppInfo()
    {
        return dpAppInfo;
    }

    public void setDpAppInfo(DpAppInfo dpAppInfo)
    {
        this.dpAppInfo = dpAppInfo;
    }

    public String getAppPackageName()
    {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName)
    {
        this.appPackageName = appPackageName;
    }

    public String getCommentUserName()
    {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName)
    {
        this.commentUserName = commentUserName;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    @Column(length = 500)
    public String getCommentContent()
    {
        return commentContent;
    }

    public void setCommentContent(String commentContent)
    {
        this.commentContent = commentContent;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public String getCommentUserId()
    {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId)
    {
        this.commentUserId = commentUserId;
    }

}
