package com.coship.depgm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * MSCP 媒资包-媒资关系
 * 
 * @author 907900
 * 
 */
@Entity
@Table(name = "t_resource_package_map")
public class AssetPackageMap implements Serializable {
	private static final long serialVersionUID = -6739721903708645993L;

	@Id
	private String id;

	@Index(name="IDX_RESOURCEPKG_ID")
	private String resourcePkgId;
	@Index(name="IDX_RESOURCE_ID")
	private String resourceId;
	private Integer orderNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourcePkgId() {
		return resourcePkgId;
	}

	public void setResourcePkgId(String resourcePkgId) {
		this.resourcePkgId = resourcePkgId;
		this.id = resourcePkgId + "_" + resourceId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
		this.id = resourcePkgId + "_" + resourceId;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

}