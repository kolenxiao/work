package com.xiu.uuc.dal.po;

import java.util.Date;

public class BasePO {
	
	/**
     * 创建时间
     */
	private Date createTime ;
	
	/**
	 * 修改时间
	 */
    private Date updateTime;
    
	/**
	 * 操作人员ID
	 */
	private String operId;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}
}
