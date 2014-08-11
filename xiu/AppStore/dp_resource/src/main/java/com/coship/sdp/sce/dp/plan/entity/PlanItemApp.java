/**
 * 
 */
package com.coship.sdp.sce.dp.plan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;

/**
 * 精细化运营-方案类项与应用的关联关系实体
 * 
 * @author 908618
 * 
 */
@Entity
public class PlanItemApp extends EntityObject {

	private static final long serialVersionUID = -5573907021887285266L;

	/**
	 * 类项的类型
	 */
	private Integer itemType;

	/**
	 * 应用的包名
	 */
	private String appPkgName;

	/**
	 * 排序
	 */
	private Integer sortNum;

	/**
	 * 方案id
	 */
	private String planId;

	/**
	 * 分类id
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
	
	//应用
	private DpAppInfo dpAppInfo;

	@Column(nullable = false)
	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	@Column(nullable = false)
	public String getAppPkgName() {
		return appPkgName;
	}

	public void setAppPkgName(String appPkgName) {
		this.appPkgName = appPkgName;
	}

	@Column(nullable = false)
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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
	public DpAppInfo getDpAppInfo() {
		return dpAppInfo;
	}

	public void setDpAppInfo(DpAppInfo dpAppInfo) {
		this.dpAppInfo = dpAppInfo;
	}

}
