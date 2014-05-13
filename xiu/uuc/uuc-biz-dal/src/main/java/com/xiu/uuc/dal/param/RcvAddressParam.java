package com.xiu.uuc.dal.param;

import com.xiu.uuc.dal.po.RcvAddressPO;


public class RcvAddressParam extends RcvAddressPO{

	/**
	 * 需要绑定的客户ID
	 */
	private Long bindCustId ;
	
	/**
	 * 用户ID
	 */
	private Long userId ;	

	public Long getBindCustId() {
		return bindCustId;
	}

	public void setBindCustId(Long bindCustId) {
		this.bindCustId = bindCustId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
