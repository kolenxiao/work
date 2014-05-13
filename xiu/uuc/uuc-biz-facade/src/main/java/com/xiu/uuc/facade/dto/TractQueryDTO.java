package com.xiu.uuc.facade.dto;

import java.util.Date;

public class TractQueryDTO extends BaseQueryDTO {
	    
	private static final long serialVersionUID = 1L;
	
	/**
	 * 导致账目变更的业务ID，这里指老订单ID ，产生金额来源订单
	 */
	private Long oldRltId ;
	
	/**
	 * 导致账目变更的业务ID，这里指新订单ID ，消费金额去向订单
	 */
	private Long newRltId ;
	
	/**
	 * 进出账标志 01进账 02出账
	 */
	private String ioType ;
	
	/**
	 * 进出金额 单位：分
	 */
	private Long ioAmount ;
	
	/**
	 * 退款编号
	 */
	private String refundId ;  
	
	/**
	 * 支付方式编号
	 */
	private String payMode ;
	
	/**
     * 起止时间(相对于创建时间)
     */
	private Date begTime ;
	
	/**
     * 结束时间(相对于创建时间)
     */
	private Date endTime ;
	
	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 支付流水号
	 */
	private String rltSeq ;
	
	/**
	 * 礼品卡号
	 */
	private String resCode ;

	public Long getOldRltId() {
		return oldRltId;
	}

	public void setOldRltId(Long oldRltId) {
		this.oldRltId = oldRltId;
	}

	public Long getNewRltId() {
		return newRltId;
	}

	public void setNewRltId(Long newRltId) {
		this.newRltId = newRltId;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public Date getBegTime() {
		return begTime;
	}

	public void setBegTime(Date begTime) {
		this.begTime = begTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRltSeq() {
		return rltSeq;
	}

	public void setRltSeq(String rltSeq) {
		this.rltSeq = rltSeq;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getIoType() {
		return ioType;
	}

	public void setIoType(String ioType) {
		this.ioType = ioType;
	}

	public Long getIoAmount() {
		return ioAmount;
	}

	public void setIoAmount(Long ioAmount) {
		this.ioAmount = ioAmount;
	}

}
