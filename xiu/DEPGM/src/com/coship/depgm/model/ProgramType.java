package com.coship.depgm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "depg_program_type")
public class ProgramType implements Serializable {
	private static final long serialVersionUID = -7626230367713929830L;

	@Id
	private String id;
	//名称
	private String name;
	//是否可见
	private Boolean visible;
	//顺序
	private Integer rank;

	//搜视网分类ID
	private String tvSouTypeId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getTvSouTypeId() {
		return tvSouTypeId;
	}

	public void setTvSouTypeId(String tvSouTypeId) {
		this.tvSouTypeId = tvSouTypeId;
	}

}
