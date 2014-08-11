/*
 * 文件名称：AppSubjectType.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.subject.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;

/**
 * 应用信息和专题的第三张关联关系表
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SubjectAppinfoRelation extends EntityObject
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 应用信息的关联关系
     */
    private DpAppInfo appInfo;

    /**
     * 和专题表的关联关系
     */
    private AppSubjectType appSubjectType;

    /**
     * 排列字段
     */
    private Double sort;

    @ManyToOne
    @JoinColumn(name = "C_APP_ID")
    public DpAppInfo getAppInfo()
    {
        return appInfo;
    }

    public void setAppInfo(DpAppInfo appInfo)
    {
        this.appInfo = appInfo;
    }

    @ManyToOne
    @JoinColumn(name = "C_SUBJECT_ID")
    public AppSubjectType getAppSubjectType()
    {
        return appSubjectType;
    }

    public void setAppSubjectType(AppSubjectType appSubjectType)
    {
        this.appSubjectType = appSubjectType;
    }

    public Double getSort()
    {
        return sort;
    }

    public void setSort(Double sort)
    {
        this.sort = sort;
    }



}
