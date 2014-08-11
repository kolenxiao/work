/*
 * 文件名称：Condition.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-03-27
 *
 */
package com.coship.sdp.sce.dp.plan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 精细化运营_条件_实体
 * 
 * @author 909194
 */
@Entity
public class Condition extends EntityObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2093911043134862934L;

	/**
     * 条件名称，如：康佳渠道
     */
    private String name;

    /**
     * 条件代码，一般为条件的参数名称，如：channelId
     */
    private String code;
    
    /**
     * 条件值，根据条件名称定义，如：渠道的值为000000
     */
    private String value;
    
    /**
     * 条件状态，-1=已删除；0=禁用；1=有效
     */
    private Integer status = 1;
    
    /**
     * 方案描述，用于辅助识别方案。
     */
    private String description;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改时间
     */
    private Date updateTime;
   
    /**
     * (临时记录字段)用于记录某方案与此条件是否有关联   0=无关联；1=有关联
     */
    private int planFlag;
    
    /**
     * (临时记录字段) 用于查询与此方案有无关联的条件， 0=无关联 1=有关联 -2=查询全部条件
     */
    private int planSearchFlag = -2;
    
    
    /**
     * (临时记录字段) 用于方案条件关系数据id
     */
    private String pcId;
    
    /**
     * (临时记录字段) 用于展示条件关联的方案信息
     */
    private String planInfo;
    
    @Transient
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	
    @Transient
	public int getPlanFlag() {
		return planFlag;
	}

	public void setPlanFlag(int planFlag) {
		this.planFlag = planFlag;
	}
	
	@Transient
	public int getPlanSearchFlag() {
		return planSearchFlag;
	}

	public void setPlanSearchFlag(int planSearchFlag) {
		this.planSearchFlag = planSearchFlag;
	}

	@Column(nullable=false,columnDefinition = " int(11) default 1 ")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Column(nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false, length = 100)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(length = 500)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(length = 256)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(nullable = false)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(nullable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Transient
	public String getPlanInfo() {
		return planInfo;
	}

	public void setPlanInfo(String planInfo) {
		this.planInfo = planInfo;
	}

}
