package com.coship.sdp.sce.dp.permission.common;

import java.io.Serializable;


public class QueryUserCondition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2025408036299369873L;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 用户角色
	 */
	private Long roleId;
	
	/**
	 * 开始时间
	 */
	private String startDate;
	
	/**
	 * 结束时间
	 */
	private String endDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("userName : " + userName);
		sb.append("\r\n");
		sb.append("roleId : " + roleId);
		sb.append("\r\n");
		sb.append("startDate : " + startDate);
		sb.append("\r\n");
		sb.append("endDate : " + endDate);
		
		return sb.toString();
	}
	
	

}
