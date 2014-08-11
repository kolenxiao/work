/*
 * 文件名称：AppRecommend.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.recommend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.type.entity.DpType;

/**
 * 应用推荐类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppRecommend extends EntityObject
{

    /**
     * 序列号
     */
    private static final long serialVersionUID = -8275337870101069788L;

    /**
     * 应用信息
     */
    private DpAppInfo dpAppInfo;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建开始时间
     */
    private Date appRecommendCTime;

    /**
     * 推荐分类
     */
    private DpType dpType;

    /**
     * 排序
     */
    private Double sort;

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

    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public Date getAppRecommendCTime()
    {
        return appRecommendCTime;
    }

    public void setAppRecommendCTime(Date appRecommendCTime)
    {
        this.appRecommendCTime = appRecommendCTime;
    }

    public Double getSort()
    {
        return sort;
    }

    public void setSort(Double sort)
    {
        this.sort = sort;
    }

    @ManyToOne
    @JoinColumn(name = "C_TYPE_ID")
	public DpType getDpType() {
		return dpType;
	}

	public void setDpType(DpType dpType) {
		this.dpType = dpType;
	}




}
