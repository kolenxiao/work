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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 应用主题类型
 * 
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppSubjectType extends EntityObject
{

    /**
     * 序列号
     */
    private static final long serialVersionUID = -3596062116126064251L;

    /**
     * 主题名称
     */
    private String subjectName;

    /**
     * 产品编码.用于
     */
    private String productCode;

    /**
     * 专题描述
     */
    private String subjectDesc;

    /**
     * 专题图片,现作为专题的背景图片.
     */
    private String subjectImg;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 创建开始时间
     */
    private String beginSujectCTime;

    /**
     * 专题下的应用数
     */
    private int appTotal;

    /**
     * 创建结束时间
     */
    private String endSubjectCTime;

    /**
     * 显示标记 1:显示；2：隐藏
     */
    private int visibleFlag;

    public String getSubjectName()
    {
        return subjectName;
    }

    public void setSubjectName(String subjectName)
    {
        this.subjectName = subjectName;
    }

    @Column
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Column(length = 500)
    public String getSubjectDesc()
    {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc)
    {
        this.subjectDesc = subjectDesc;
    }

    public String getSubjectImg()
    {
        return subjectImg;
    }


    public void setSubjectImg(String subjectImg)
    {
        this.subjectImg = subjectImg;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    @Transient
    public String getBeginSujectCTime()
    {
        return beginSujectCTime;
    }

    public void setBeginSujectCTime(String beginSujectCTime)
    {
        this.beginSujectCTime = beginSujectCTime;
    }

    @Transient
    public String getEndSubjectCTime()
    {
        return endSubjectCTime;
    }

    public void setEndSubjectCTime(String endSubjectCTime)
    {
        this.endSubjectCTime = endSubjectCTime;
    }

    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public Date getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    @Transient
    public int getAppTotal()
    {
        return appTotal;
    }

    public void setAppTotal(int appTotal)
    {
        this.appTotal = appTotal;
    }


    @Column
	public int getVisibleFlag() {
		return visibleFlag;
	}

	public void setVisibleFlag(int visibleFlag) {
		this.visibleFlag = visibleFlag;
	}




}
