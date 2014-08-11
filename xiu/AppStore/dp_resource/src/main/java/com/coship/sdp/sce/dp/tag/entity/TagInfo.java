/**
 * 标签信息
 */
package com.coship.sdp.sce.dp.tag.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TagInfo extends EntityObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 标签名称
	 */
	private String name;
	
	/**
	 * 标签状态
	 * 1:有效
	 * -1:无效
	 */
	private Integer status;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		if (createTime == null) {
			this.createTime = new Date();
		} else {
			this.createTime = (Date) createTime.clone();
		}
	}
	
	

}
