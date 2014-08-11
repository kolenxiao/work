/**
 * 
 */
package com.coship.sdp.sce.dp.plan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 精细化运营-类项实体
 * 
 * @author 908618
 * 
 */
@Entity
public class Item extends EntityObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 471847104315423518L;

	/**
	 * 类项的名称
	 */
	private String name;

	/**
	 * 类项的类型 1:首页; 2:专题; 3:分类 4:自定义类项
	 */
	private Integer itemType = 4;

	/**
	 * 类型的状态 -1=已删除；0=禁用；1=有效
	 */
	private Integer status = 1;
	
	/**
	 * 父类型编码
	 */
	private String parentTypeCode;

    /**
     * 获取焦点图标
     */
    private String typeImg1;

    /**
     * 失去焦点图标
     */
    private String typeImg2;

	/**
	 * 描述信息
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
     * (临时记录字段)用于记录此类项下是否有app   0=无app；1=有app
     */
    private int appFlag;

	@Column(nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false,columnDefinition = " int(11) default 4 ")
	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	@Column(nullable = false,columnDefinition = " int(11) default 1 ")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTypeImg1() {
		return typeImg1;
	}

	public void setTypeImg1(String typeImg1) {
		this.typeImg1 = typeImg1;
	}

	public String getTypeImg2() {
		return typeImg2;
	}

	public void setTypeImg2(String typeImg2) {
		this.typeImg2 = typeImg2;
	}

	@Column(length = 256)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(nullable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(nullable = false)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getParentTypeCode() {
		return parentTypeCode;
	}

	public void setParentTypeCode(String parentTypeCode) {
		this.parentTypeCode = parentTypeCode;
	}

	@Transient
	public int getAppFlag() {
		return appFlag;
	}

	public void setAppFlag(int appFlag) {
		this.appFlag = appFlag;
	}

}
