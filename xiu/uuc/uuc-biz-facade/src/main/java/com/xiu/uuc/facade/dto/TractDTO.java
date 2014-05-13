package com.xiu.uuc.facade.dto;

import java.io.Serializable;

/**
 * @ClassName: TractParamDTO 
 * @Description: 轨迹参数DTO对象 供第三方系统调用 
 * @author menglei
 * @date Jul 22, 2011 9:53:19 AM 
 */

public class TractDTO implements Serializable {

	/**
	 * @Fields serialVersionUID : 缺省序列化号 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 支付方式编号
	 */
	private String payMode ;
	
	/**
	 * 退款编号
	 */
	private String refundId ;  
	
	/**
	 * 进出金额 单位：分
	 */
	private Long ioAmount ;
	
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
	 * 备注
	 */
	private String remark ;
	
	/**
	 * 余额（当前支付方式可用总余额） 单位：分
	 * 各种支付方式的轨迹余额
	 */
	private Long sumIoAmount = 0L;
	
	/**
	 * 支付流水号
	 */
	private String rltSeq ;
	
	/**
	 * 礼品卡号
	 */
	private String resCode ;

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public Long getIoAmount() {
		return ioAmount;
	}

	public void setIoAmount(Long ioAmount) {
		this.ioAmount = ioAmount;
	}

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

	public String getIoType() {
		return ioType;
	}

	public void setIoType(String ioType) {
		this.ioType = ioType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSumIoAmount() {
		return sumIoAmount;
	}

	public void setSumIoAmount(Long sumIoAmount) {
		this.sumIoAmount = sumIoAmount;
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
	
}
