package com.xiu.uuc.dal.param;

import java.util.Date;
import java.util.List;


/**
 * @ClassName: AcctChangeParam 
 * @Description: 账目变更历史表Param对象 用于对账目变更历史信息进行查询操作 
 * @author menglei
 * @date Jul 22, 2011 9:53:19 AM 
 */
public class AcctChangeParam extends BaseParam {
	
	public AcctChangeParam(){
		
	}
	
	public AcctChangeParam(final Long acctItemId,final String acctTypeCode ,final Date validTime, final Date expireTime){
		this.acctItemId = acctItemId;
		this.acctTypeCode = acctTypeCode;
		this.validTime = validTime;
		this.expireTime = expireTime;
	}
	
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
	private Long rltId ;     
	
	/**
	 * 导致帐目变化的业务流水code
	 */
	private String rltCode ;  
	
	/**
	 * 订单流水
	 */
	private String rltSeq ;         
	
	/**
	 * 账目进出类型 01.进账 02出帐 03冻结
	 */
	private String ioType = ""; 
	
	/**
	 * 业务类型 01.订单充值 02.线上充值 03线下充值 04邀请返利 05退货退款 
	 *        06抽奖 07订单返还 08申请提现 09注册赠送 10手工调节  
	 *        11赠送推荐好友 12首次下单赠送  13 积分换购商品 
	 *        14兑换优惠卡/券 15抽奖 16退换货 17被过期
	 */
	private String busiType = ""; 
	
	/**
	 * 出入数量
	 */
	private Long ioAmount ; 
	
	/**
	 * 出入数量 变更操作前的历史数量
	 */
	private Long historyIoAmount;
	
	/**
	 * 出入数量 变更操作后的历史数量
	 */
	private Long lastIoAmount;
	
	/**
	 * 出入数量 比较类型 1:'大于',2:'小于',3:'等于',4:'大于等于',5:'小于等于'
	 */
	private int ioAmountOperType;
	
	/**
	 * 业务来源渠道标识 1.官网 2.秀团 3.团货
	 */
	private String rltChannelId = "";
	
	/**
	 * 操作员ID
	 */
	private String operId = "";
	
	/**
	 * 账户ID
	 */
	private Long acctId ;
	
	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 账目类型 01 可提现 02不可提现 03积分
	 */
	private String acctTypeCode = "" ;
	
	/**
	 * 账目类型 01 可提现 02不可提现 03积分 列表条件 支持一次性查询出多条账目信息
	 */
	private List<String> acctTypeCodeList;
	
	/**
	 * 相对于创建时间 前几个月的记录
	 * '1':'近三个月（相对于创建时间）','2':'三个月前（相对于创建时间）'
	 */
	private String timeSlot="";
	
	/**
     * 生效时间(相对于创建时间)
     */
	private Date validTime ;
	
	/**
     * 失效时间(相对于创建时间)
     */
	private Date expireTime ;
	
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

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getIoAmountOperType() {
		return ioAmountOperType;
	}

	public void setIoAmountOperType(int ioAmountOperType) {
		this.ioAmountOperType = ioAmountOperType;
	}

	public String getAcctTypeCode() {
		return acctTypeCode;
	}

	public void setAcctTypeCode(String acctTypeCode) {
		this.acctTypeCode = acctTypeCode;
	}

	public List<String> getAcctTypeCodeList() {
		return acctTypeCodeList;
	}

	public void setAcctTypeCodeList(List<String> acctTypeCodeList) {
		this.acctTypeCodeList = acctTypeCodeList;
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

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getRltId() {
		return rltId;
	}

	public void setRltId(Long rltId) {
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
