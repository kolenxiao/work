package com.coship.depgm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "depg_channel")
public class Channel implements Serializable {

	private static final long serialVersionUID = 7723049187713814002L;

	@Id
	private String id;
	
	private String mscpId;
	// 名称
	private String name;

	// 搜视网频道ID
	private String soutvId;

	//视频清晰度类型 HD高清 SD标清
	private String videoType;

	// 是否支持回看
	private Boolean btv;

	// DVD频道三要素
	private Integer networkID;// 网络
	private Integer tsID;// 频点
	private Integer serviceID;// 频道

	 // 状态 0无效 1有效
	private Integer status;
	//顺序
	private Integer rank;
	//当天是否有节目
	@Transient
	private String avaliableToday;
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

	public String getSoutvId() {
		return soutvId;
	}

	public void setSoutvId(String soutvId) {
		this.soutvId = soutvId;
	}

	public Integer getNetworkID() {
		return networkID;
	}

	public void setNetworkID(Integer networkID) {
		this.networkID = networkID;
	}

	public Integer getTsID() {
		return tsID;
	}

	public void setTsID(Integer tsID) {
		this.tsID = tsID;
	}

	public Integer getServiceID() {
		return serviceID;
	}

	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public Boolean getBtv() {
		return btv;
	}

	public void setBtv(Boolean btv) {
		this.btv = btv;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getAvaliableToday() {
		return avaliableToday;
	}

	public void setAvaliableToday(String avaliableToday) {
		this.avaliableToday = avaliableToday;
	}

	public String getMscpId() {
		return mscpId;
	}

	public void setMscpId(String mscpId) {
		this.mscpId = mscpId;
	}
	
}