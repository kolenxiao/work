package com.coship.sdp.sce.dp.permission.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.dp.access.entity.EntityObject;

/**
 * 资源表
 * @author 905950
 *
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Resource extends EntityObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7145612663968632032L;
	
	/**
	 * 资源英名名（唯一）
	 */
	private String enName;
	
	/**
	 * 资源名称
	 */
	private String name;
	
	/**
	 * 资源链接
	 */
	private String url;
	
	/**
	 * 资源级别
	 */
	private int level;
	
	/**
	 * 菜单在页面的排序
	 */
	private int orderField;
	
	/**
	 * 菜单或按钮
	 * 菜单   0
	 * 按钮   1
	 */
	private int menuButton;
	/**
	 * 资源描述
	 */
	private String description;
	
	private Date createdDate;
	
	private String createdUser;
	
	private Date updatedDate;
	
	/**
	 * 资源所属子资源
	 */
	private Set<Resource> res;
	
	/**
	 * 资源的父资源
	 */
	private Resource parentRes;

	@Column(length = 50, unique=true,nullable=false)
	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	@Column(length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(length = 2)
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Column(length = 2)
	public int getOrderField() {
		return orderField;
	}

	public void setOrderField(int orderField) {
		this.orderField = orderField;
	}

	@Column(length = 1)
	public int getMenuButton() {
		return menuButton;
	}

	public void setMenuButton(int menuButton) {
		this.menuButton = menuButton;
	}

	@Column(length = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @OneToMany(targetEntity = Resource.class, mappedBy="parentRes")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<Resource> getRes() {
		return res;
	}

	public void setRes(Set<Resource> res) {
		this.res = res;
	}

	@ManyToOne
    @JoinColumn(name = "parent_id") 
	public Resource getParentRes() {
		return parentRes;
	}

	public void setParentRes(Resource parentRes) {
		this.parentRes = parentRes;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(length=64)
	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
