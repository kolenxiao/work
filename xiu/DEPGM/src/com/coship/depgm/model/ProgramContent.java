package com.coship.depgm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "depg_program_content")
public class ProgramContent implements Serializable {
	private static final long serialVersionUID = 3436960463233977490L;

	@Id
	private String id;
	//搜视网节目ID
	private String tvSouId;
	//节目名称
	private String name;
	//海报地址
	private String poster;
	//横版海报
	private String horiPoster;
	//描述信息
	@Column(length = 4000)
	private String description;
	//是否回看
	private boolean btv;
	//节目类型id
	@Index(name = "typeId")
	private String typeId;
	//搜视网节目类型
	private String tvSouTypes;
	//内容类型
	private String assetTypes;
	//节目导演
	private String director;
	//节目主演
	private String leadingActor;
	//节目总集数
	private int chapter;
	
	//节目类型名称
	@Transient
	private String typeName;
	
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

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public void clearPoster() {
		this.poster = null;
		this.horiPoster = null;
	}
	
	public boolean hasAllPoster() {
		return this.poster != null && this.horiPoster != null;
	}
	
	public String toPoster() {
		return "poster=" + this.poster + ";horiPoster=" + this.horiPoster;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isBtv() {
		return btv;
	}

	public void setBtv(boolean btv) {
		this.btv = btv;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getHoriPoster() {
		return horiPoster;
	}

	public void setHoriPoster(String horiPoster) {
		this.horiPoster = horiPoster;
	}

	public String getAssetTypes() {
		return assetTypes;
	}

	public void setAssetTypes(String assetTypes) {
		this.assetTypes = assetTypes;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getLeadingActor() {
		return leadingActor;
	}

	public void setLeadingActor(String leadingActor) {
		this.leadingActor = leadingActor;
	}

	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public void caculateTypeId(List<ProgramType> types) {
		for (ProgramType type : types) {
			if(tvSouTypes.indexOf(", " + type.getTvSouTypeId() + ",") > -1){					
				setTypeId(type.getId());
				break;
			}
		}
	}
	
	@Override
	public String toString() {
		return "tvSouId=" + tvSouId + ";name=" + name;
	}

	public String getTvSouId() {
		return tvSouId;
	}

	public void setTvSouId(String tvSouId) {
		this.tvSouId = tvSouId;
	}

	public String getTvSouTypes() {
		return tvSouTypes;
	}

	public void setTvSouTypes(String tvSouTypes) {
		this.tvSouTypes = tvSouTypes;
	}
}
