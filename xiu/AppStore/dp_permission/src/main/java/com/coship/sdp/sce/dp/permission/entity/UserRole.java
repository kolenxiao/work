package com.coship.sdp.sce.dp.permission.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ManyToAny;

import com.coship.sdp.dp.access.entity.EntityObject;

/**
 * 用户角色关系表
 * @author 905950
 *
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserRole extends EntityObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户  
	 */
	private User user;
	
	/**
	 * 角色
	 */
	private Role userRole;
	
	@ManyToOne
	@JoinColumn(name="USER_ROLE_ID")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name="ROLE_USER_ID")
	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	
}
