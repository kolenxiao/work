/**
 * 
 */
package com.coship.sdp.sce.dp.plan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.type.entity.DpType;

/**
 * 精细化运营-方案与类项的关联关系实体
 * 
 * @author 908618
 * 
 */
@Entity
public class PlanItem extends EntityObject {

	private static final long serialVersionUID = -617284252721838712L;

	/**
	 * 类项的类型 1:首页; 2:专题; 3:固定分类; 4:自定义分类
	 */
	private Integer itemType;

	/**
	 * 描述信息
	 */
	private String description = "";

	/**
	 * 排序
	 */
	private Integer sortNum;

	/**
	 * 方案实体
	 */
	private String planId;
	
	/**
	 * 类项Id
	 */
	private String itemId;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改时间
     */
    private Date updateTime;
	
	//首页版块
	private AppRecommendPanel appRecommendPanel;
	//专题
	private AppSubjectType appSubjectType;
	//应用
	private DpType dpType;
	//类项
	private Item item;
	//关联的应用数量
	private Integer appNum;

	
	@Column(nullable = false)
	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	@Column(nullable = false)
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(nullable = false)
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	@Column(nullable = false)
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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

	@Transient
	public AppRecommendPanel getAppRecommendPanel() {
		return appRecommendPanel;
	}

	public void setAppRecommendPanel(AppRecommendPanel appRecommendPanel) {
		this.appRecommendPanel = appRecommendPanel;
	}

	@Transient
	public AppSubjectType getAppSubjectType() {
		return appSubjectType;
	}

	public void setAppSubjectType(AppSubjectType appSubjectType) {
		this.appSubjectType = appSubjectType;
	}

	@Transient
	public DpType getDpType() {
		return dpType;
	}

	public void setDpType(DpType dpType) {
		this.dpType = dpType;
	}

	@Transient
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Transient
	public Integer getAppNum() {
		return appNum;
	}

	public void setAppNum(Integer appNum) {
		this.appNum = appNum;
	}


}
