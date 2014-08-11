/**
 * 应用标签关联信息
 */
package com.coship.sdp.sce.dp.tag.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppTagRelation extends EntityObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 应用包名
	 */
	private String appPackageName;
	
	/**
	 * 标签信息
	 */
	private TagInfo tagInfo;
	
	/**
	 * 标签顺序
	 */
	private Integer sortNum;

	@ManyToOne
    @JoinColumn(name = "C_TAG_ID")
	public TagInfo getTagInfo() {
		return tagInfo;
	}

	public void setTagInfo(TagInfo tagInfo) {
		this.tagInfo = tagInfo;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getAppPackageName() {
		return appPackageName;
	}

	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
	}
	
	

}
