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
package com.coship.sdp.sce.dp.comment.entity;

/**
 * 应用的评论信息类
 * @author wangchenbo/906055
 * @version [版本号, 2012-9-8]
 * @since [产品/模块版本]
 */

public class AppScoreSummary
{

    /**
     * 应用id
     */
    private String appId;

    /**
     * 平均评分值
     */
    private double avgScore;

    /**
     * 人工增加后的平均评分值
     */
    private double afterHandAvgScore;
    
    /**
     * 平均星值
     */
    private double avgStar;
    
    /**
     * 真实的评分人数
     */
    private long scoreCount;
    
    /**
     * 人工增加后的评分人数
     */
    private long afterHandScoreCount;

    /**
     * 1星评分值人数
     */
    private long star1;

    /**
     * 2星评分值人数
     */
    private long star2;

    /**
     * 3星评分值人数
     */
    private long star3;

    /**
     * 4星评分值人数
     */
    private long star4;

    /**
     * 5星评分值人数
     */
    private long star5;

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }



    public long getStar1()
    {
        return star1;
    }

    public void setStar1(long star1)
    {
        this.star1 = star1;
    }

    public long getStar2()
    {
        return star2;
    }

    public void setStar2(long star2)
    {
        this.star2 = star2;
    }

    public long getStar3()
    {
        return star3;
    }

    public void setStar3(long star3)
    {
        this.star3 = star3;
    }

    public long getStar4()
    {
        return star4;
    }

    public void setStar4(long star4)
    {
        this.star4 = star4;
    }

    public long getStar5()
    {
        return star5;
    }

    public void setStar5(long star5)
    {
        this.star5 = star5;
    }

    public double getAvgScore()
    {
        return avgScore;
    }

    public void setAvgScore(double avgScore)
    {
        this.avgScore = avgScore;
    }

	public double getAvgStar() {
		return avgStar;
	}

	public void setAvgStar(double avgStar) {
		this.avgStar = avgStar;
	}

	public double getAfterHandAvgScore() {
		return afterHandAvgScore;
	}

	public void setAfterHandAvgScore(double afterHandAvgScore) {
		this.afterHandAvgScore = afterHandAvgScore;
	}

	public long getAfterHandScoreCount() {
		return afterHandScoreCount;
	}

	public void setAfterHandScoreCount(long afterHandScoreCount) {
		this.afterHandScoreCount = afterHandScoreCount;
	}

	public long getScoreCount() {
		return scoreCount;
	}

	public void setScoreCount(long scoreCount) {
		this.scoreCount = scoreCount;
	}



}
