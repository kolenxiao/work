package com.xiu.uuc.dal.po;

/**
 * @ClassName: AcctChangePO 
 * @Description: 账目变更历史流水表PO
 *               主要为了解决1.5系统中订单号含有字母的情况
 *               将rltId（订单id或者退换货id）数据类型由Long---》String
 *               Bug 7732 - 查询账户变动列表记录接口
 * @author menglei
 * @date Jul 19, 2011 2:34:36 PM 
 */
public class AcctChangeExtPO extends BasePO{
    
	/**
	 * 账目变更序列号
	 */
	private Long accountChangeId ;    
	
	/**
	 * 账目ID
	 */
	private Long acctItemId ;         
	
	/**
	 * 导致帐目变化的业务流水ID
	 */
	private String rltId ;     
	
	/**
	 * 导致帐目变化的业务流水code
	 */
	private String rltCode ;  
	
	/**
	 * 订单流水
	 */
	private String rltSeq ;              
	
	/**
	 * 账目进出类型 01.进账 02出帐
	 */
	private String ioType = ""; 
	
	/**
	 * 业务类型  01.可体现 02.不可体现 03积分
	 */
	private String busiType = ""; 
	
	/**
	 * 出入数量
	 */
	private Long ioAmount ; 
	
	/**
	 * 出入数量 变更操作前的历史数量
	 */
	private Long historyIoAmount ;
	
	/**
	 * 出入数量 变更操作后的历史数量
	 */
	private Long lastIoAmount ;
	
	/**
	 * 业务来源渠道标识
	 */
	private String rltChannelId = "";
	
	/**
	 * 账目类型 01 可提现 02不可提现 03积分
	 */
	private String acctTypeCode = "" ;
	
	/**
	 * 备注信息
	 */
	private String remark = "" ;
	
	/**
	 * 导致帐目变化的业务方式 1：自动方式 2：手动方式 缺省为1
	 */
	private String operMode = "1";
	
	public Long getAccountChangeId() {
		return accountChangeId;
	}
	public void setAccountChangeId(Long accountChangeId) {
		this.accountChangeId = accountChangeId;
	}
	public Long getAcctItemId() {
		return acctItemId;
	}
	public void setAcctItemId(Long acctItemId) {
		this.acctItemId = acctItemId;
	}
	public String getIoType() {
		return ioType;
	}
	public void setIoType(String ioType) {
		this.ioType = ioType;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	public Long getIoAmount() {
		return ioAmount;
	}
	public void setIoAmount(Long ioAmount) {
		this.ioAmount = ioAmount;
	}
	public String getRltChannelId() {
		return rltChannelId;
	}
	public void setRltChannelId(String rltChannelId) {
		this.rltChannelId = rltChannelId;
	}
	public String getAcctTypeCode() {
		return acctTypeCode;
	}
	public void setAcctTypeCode(String acctTypeCode) {
		this.acctTypeCode = acctTypeCode;
	}
	public Long getHistoryIoAmount() {
		return historyIoAmount;
	}
	public void setHistoryIoAmount(Long historyIoAmount) {
		this.historyIoAmount = historyIoAmount;
	}
	public Long getLastIoAmount() {
		return lastIoAmount;
	}
	public void setLastIoAmount(Long lastIoAmount) {
		this.lastIoAmount = lastIoAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRltId() {
		return rltId;
	}
	public void setRltId(String rltId) {
		this.rltId = rltId;
	}
	public String getRltCode() {
		return rltCode;
	}
	public void setRltCode(String rltCode) {
		this.rltCode = rltCode;
	}
	public String getRltSeq() {
		return rltSeq;
	}
	public void setRltSeq(String rltSeq) {
		this.rltSeq = rltSeq;
	}
	public String getOperMode() {
		return operMode;
	}
	public void setOperMode(String operMode) {
		this.operMode = operMode;
	}
}
