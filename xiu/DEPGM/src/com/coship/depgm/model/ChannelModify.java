package com.coship.depgm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "depg_channel_modify")
public class ChannelModify {
	@Id
	private String id;
	
	@Index(name = "tvSouChannelId")
	private String tvSouChannelId;
	
	@Index(name = "date")
	private String date;
	
	private String md5;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTvSouChannelId() {
		return tvSouChannelId;
	}

	public void setTvSouChannelId(String tvSouChannelId) {
		this.tvSouChannelId = tvSouChannelId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
}