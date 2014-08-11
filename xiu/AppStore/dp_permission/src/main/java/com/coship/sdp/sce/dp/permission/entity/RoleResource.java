package com.coship.sdp.sce.dp.permission.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.coship.sdp.dp.access.entity.EntityObject;

/**
 * 角色资源对应关系表
 * @author 905950
 *
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleResource extends EntityObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色
	 */
	private Role resRole;
	
	/**
	 * 资源
	 */
	private Resource resource;

	@ManyToOne
	@JoinColumn(name="ROLE_RES_ID")
	public Role getResRole() {
		return resRole;
	}

	public void setResRole(Role resRole) {
		this.resRole = resRole;
	}

	@ManyToOne
	@JoinColumn(name="RES_ROLE_ID")
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
