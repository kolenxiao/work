package com.coship.depgm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * MSCP 上架关系（栏目-媒资）
 * 
 * @author 907900
 * 
 */
@Entity
@Table(name = "t_column_resource")
public class ColumnResource implements Serializable {
	private static final long serialVersionUID = -6902977802828550264L;

	@Id
	private String id;

	@Index(name="IDX_COLUMN_ID")
	private Long columnId;
	@Index(name="IDX_RESOURCE_ID")
	private String resourceId;
	private Long rank;
	private Integer resourceType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getColumnId() {
		return columnId;
	}

	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

}