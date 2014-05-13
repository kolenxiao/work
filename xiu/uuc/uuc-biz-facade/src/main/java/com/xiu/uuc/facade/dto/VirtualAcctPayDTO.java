package com.xiu.uuc.facade.dto;

import java.io.Serializable;

public class VirtualAcctPayDTO implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */
	
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 账目类型 01 可提现 02不可提现 03积分
	 */
	private String acctTypeCode = "" ;
	
	/**
	 * 可提现支付金额 单位：分
	 */
	private Long drawPayMoney = 0L;
	
	/**
	 * 不可提现支付金额 单位：分
	 */
	private Long unDrawPayMoney = 0L;
	
	/**
	 * 积分支付 单位：分
	 */
	private Long payIntegral = 0L;
	
	public VirtualAcctPayDTO(){
		
	}
	
	public VirtualAcctPayDTO(Long userId,Long payIntegral){
		this.userId = userId;
		this.payIntegral = payIntegral;
	}
	
	public VirtualAcctPayDTO(Long userId,String acctTypeCode,Long unDrawPayMoney,Long drawPayMoney,Long payIntegral){
		this.userId = userId;
		this.acctTypeCode = acctTypeCode;
		this.unDrawPayMoney = unDrawPayMoney;
		this.drawPayMoney = drawPayMoney;
		this.payIntegral = payIntegral;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDrawPayMoney() {
		return drawPayMoney;
	}

	public void setDrawPayMoney(Long drawPayMoney) {
		this.drawPayMoney = drawPayMoney;
	}

	public Long getUnDrawPayMoney() {
		return unDrawPayMoney;
	}

	public void setUnDrawPayMoney(Long unDrawPayMoney) {
		this.unDrawPayMoney = unDrawPayMoney;
	}

	/**
	 * 当此虚拟账户总共支付金额 单位：分
	 */
	public Long getPayTotalMoney() {
		return unDrawPayMoney + drawPayMoney ;
	}

	public Long getPayIntegral() {
		return payIntegral;
	}

	public void setPayIntegral(Long payIntegral) {
		this.payIntegral = payIntegral;
	}

	public String getAcctTypeCode() {
		return acctTypeCode;
	}

	public void setAcctTypeCode(String acctTypeCode) {
		this.acctTypeCode = acctTypeCode;
	}
}
