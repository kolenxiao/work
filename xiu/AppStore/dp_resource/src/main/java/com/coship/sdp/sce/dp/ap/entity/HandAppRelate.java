package com.coship.sdp.sce.dp.ap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 人工指定的关联推荐应用信息(猜你喜欢)
 * 
 * @author 908618
 * 
 */
@Entity
public class HandAppRelate extends EntityObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8834292453862871229L;

	/**
	 * 方案id
	 */
	private String planId;

	/**
	 * 应用包名
	 */
	private String appPkgName;

	/**
	 * 关联的应用包名
	 */
	private String relateAppPkgName;

	/**
	 * 关联应用的排序
	 */
	private Integer sortNum;

	@Column(nullable = false)
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	@Column(nullable = false)
	public String getAppPkgName() {
		return appPkgName;
	}

	public void setAppPkgName(String appPkgName) {
		this.appPkgName = appPkgName;
	}

	@Column(nullable = false)
	public String getRelateAppPkgName() {
		return relateAppPkgName;
	}

	public void setRelateAppPkgName(String relateAppPkgName) {
		this.relateAppPkgName = relateAppPkgName;
	}

	@Column(nullable = false)
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

}
