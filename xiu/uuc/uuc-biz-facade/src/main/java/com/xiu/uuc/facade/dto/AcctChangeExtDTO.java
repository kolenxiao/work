package com.xiu.uuc.facade.dto;

import java.util.Date;
import java.util.List;
import com.xiu.uuc.facade.util.TypeEnum;

/**
 * @ClassName: AcctChangeExtDTO 
 * @Description: 账目变更历史表DTO对象 供第三方系统调用 
 *               主要为了解决1.5系统中订单号含有字母的情况
 *               将rltId（订单id或者退换货id）数据类型由Long---》String
 *               Bug 7732 - 查询账户变动列表记录接口
 * @author menglei
 * @date Jul 22, 2011 9:53:19 AM 
 */
public class AcctChangeExtDTO extends BaseQueryDTO {

	/**
	 * @Fields serialVersionUID : 缺省序列化号 
	 */
	
	private static final long serialVersionUID = 1L;
	
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
	 * 账目进出类型 01.进账 02出帐 03冻结 04解冻
	 */
	private String ioType = ""; 
	
	/**
	 * 账目进出类型文字描述信息
	 */
	private String ioTypeDesc = ""; 
	
	/**
	 * 业务类型 
	 */
	private String busiType = ""; 
	
	/**
	 * 业务类型 文字描述信息
	 */
	private String busiTypeDesc = ""; 
	
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
	private int ioAmountOperType=0;
	
	/**
	 * 业务来源渠道标识 10751.官网 2.秀团 3.团货
	 */
	private String rltChannelId = "";
	
	/**
	 * 业务来源渠道标识文字描述信息
	 */
	private String rltChannelIdDesc = "";
	
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
	 * 账目类型 文字描述信息
	 */
	private String acctTypeCodeDesc = "" ;
	
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

	public String getRltId() {
		return rltId;
	}

	public void setRltId(String rltId) {
		this.rltId = rltId;
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

	/**
	 * 获取账目进出类型文字描述信息
	 */
	public String getIoTypeDesc() {
		ioTypeDesc = TypeEnum.acctItemIOType.getList().get(ioType);
		return ioTypeDesc;
	}

	/**
	 * 获取业务类型文字描述信息
	 */
	public String getBusiTypeDesc() {
		if(busiTypeDesc==null || "".equals(busiTypeDesc)){
			busiTypeDesc = TypeEnum.virtualAccountBusiType.getList().get(busiType);	
		}
		return busiTypeDesc;
	}
	
	public void setBusiTypeDesc(String busiTypeDesc) {
		this.busiTypeDesc = busiTypeDesc;
	}

	/**
	 * 获取业务来源渠道标识文字描述信息
	 */
	public String getRltChannelIdDesc() {
		return rltChannelIdDesc;
	}
	
	public void setRltChannelIdDesc(String rltChannelIdDesc) {
		this.rltChannelIdDesc = rltChannelIdDesc;
	}

	/**
	 * 获取账目类型文字描述信息
	 */
	public String getAcctTypeCodeDesc() {
		acctTypeCodeDesc = TypeEnum.AcctItemType.getList().get(acctTypeCode);
		return acctTypeCodeDesc;
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
