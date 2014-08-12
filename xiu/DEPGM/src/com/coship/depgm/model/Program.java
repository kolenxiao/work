package com.coship.depgm.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.annotations.Index;

@Entity
@Table(name = "depg_program")
public class Program implements Serializable {
	private static final long serialVersionUID = -6908312611849046192L;

	@Id
	private String id;
	//名称
	private String name;
	//描述
	private String description;
	//状态
	private int status;
	//开始时间
	private Date beginTime;
	//结束时间
	private Date endTime;
	//节目单日期
	@Index(name="eventDate")
	private Date eventDate;
	//煤资ID
	private String assetId;
	//总集数
	private int chapter;
	//频道ID	
	@Index(name="channelId")
	private String channelId;
	//节目ID
	@Index(name="contentId")
	private String contentId;
	//类型ID
	@Index(name="typeId")
	private String typeId;
	//是否已经导出MSCP
	@Index(name="exported")
	private boolean exported;
	//是否已经计算回看节目
	@Index(name="btvCaculated")
	private boolean btvCaculated;
	//节目名称
	@Transient
	private String contentName;
	//节目名称
	@Transient
	private String typeName;
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
		this.eventDate = DateUtils.truncate(beginTime, Calendar.DATE);
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public boolean isExported() {
		return exported;
	}

	public void setExported(boolean exported) {
		this.exported = exported;
	}

	public boolean isBtvCaculated() {
		return btvCaculated;
	}

	public void setBtvCaculated(boolean btvCaculated) {
		this.btvCaculated = btvCaculated;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}