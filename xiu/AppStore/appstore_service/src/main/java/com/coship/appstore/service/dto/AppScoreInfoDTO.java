/*
 * 文件名称：AppCommentInfoDTO.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：王晨波/906055
 * 创建时间：2012-9-8
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.appstore.service.dto;


/**
 * 应用的评论信息类
 * @author wangchenbo/906055
 * @version [版本号, 2012-9-8]
 * @since [产品/模块版本]
 */

public class AppScoreInfoDTO{


    /**
     * 应用id
     */
    private String appId ;

    /**
     * 评论的用户名
     */
    private String commentUserName;

    /**
     * 评论的时间
     */
    private String createDate;

    /**
     * 评分值
     */
    private int score;



    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getCommentUserName()
    {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName)
    {
        this.commentUserName = commentUserName;
    }



    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }


}
