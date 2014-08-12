package com.coship.depgm.model;

public class AssetRecommend {
	private String resourceID;
	private String refResourceID;
	private Integer type;
	private Integer rank;

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	public String getRefResourceID() {
		return refResourceID;
	}

	public void setRefResourceID(String refResourceID) {
		this.refResourceID = refResourceID;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
}
