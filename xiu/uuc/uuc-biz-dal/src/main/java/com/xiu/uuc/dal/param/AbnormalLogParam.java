package com.xiu.uuc.dal.param;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: AbnormalLogParam 
 * @Description: 虚拟账户异动日志表Param
 * @author menglei
 * @date Dec 14, 2011 9:53:28 AM 
 */
public class AbnormalLogParam extends BaseParam {
	
	public AbnormalLogParam(){
		
	}
	
	public AbnormalLogParam(final Date validTime, final Date expireTime){
		this.validTime = validTime;
		this.expireTime = expireTime;
	}
	
	public AbnormalLogParam(final Long acctItemId,final String acctTypeCode,final Integer payCountValidDateTime){
		this.acctItemId = acctItemId;
		this.acctTypeCode = acctTypeCode;
		this.payCountValidDateTime = payCountValidDateTime;
	}
	
	public AbnormalLogParam(final Long acctItemId,final String acctTypeCode ,final Date validTime, final Date expireTime){
		this(validTime,expireTime);
		this.acctItemId = acctItemId;
		this.acctTypeCode = acctTypeCode;
	}
	
	/**
	 * 虚拟账户异动日志ID
	 */
	private Long abnormalId ;
	
	/**
	 * 虚拟账户异动日志ID
	 */
	private List<Long> abnormalIdList ;
	
	/**
	 * 异动类型 00：账目不平 01：超过支付额的限制 02：操作支付次数限制
	 */
	private String abnormalType ;
	
	
	/**
	 * 账目类型 00：账目不平 01：超过支付额的限制 02：操作支付次数限制 列表条件 支持一次性查询出多条账目异动信息
	 */
	private List<String> abnormalTypeList;

	/**
	 * 异动类型描述 00：账目不平 01：超过支付额的限制 02：操作支付次数限制
	 */
	private String abnormalDesc;

	/**
	 * 账目序列号
	 */
	private Long acctItemId ;
	
	/**
	 * 用户账户序列号
	 */
	private Long acctId;
	
	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 当前用户，当前账目类型 流水总账
	 */
	private String acctTypeCode ;
	
	/**
     * 生效时间(相对于创建时间)
     */
	private Date validTime ;
	
	/**
     * 失效时间(相对于创建时间)
     */
	private Date expireTime ;
	
	/**
     * 是否已经发送了告警邮件信息 0:未发送 1：已发送 缺省为0
     */
	private String sendEmailFlag = "0";
	
	/**
     * 时间间隔单位：分钟(相对于创建时间)
     * 用户虚拟账户异动审计 账目流水支付笔数统计时间段
     */
	private Integer payCountValidDateTime;
	
	public Long getAbnormalId() {
		return abnormalId;
	}

	public void setAbnormalId(Long abnormalId) {
		this.abnormalId = abnormalId;
	}

	public String getAbnormalType() {
		return abnormalType;
	}

	public void setAbnormalType(String abnormalType) {
		this.abnormalType = abnormalType;
	}

	public List<String> getAbnormalTypeList() {
		return abnormalTypeList;
	}

	public void setAbnormalTypeList(List<String> abnormalTypeList) {
		this.abnormalTypeList = abnormalTypeList;
	}

	public String getAbnormalDesc() {
		return abnormalDesc;
	}

	public void setAbnormalDesc(String abnormalDesc) {
		this.abnormalDesc = abnormalDesc;
	}

	public Long getAcctItemId() {
		return acctItemId;
	}

	public void setAcctItemId(Long acctItemId) {
		this.acctItemId = acctItemId;
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

	public String getAcctTypeCode() {
		return acctTypeCode;
	}

	public void setAcctTypeCode(String acctTypeCode) {
		this.acctTypeCode = acctTypeCode;
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

	public String getSendEmailFlag() {
		return sendEmailFlag;
	}

	public void setSendEmailFlag(String sendEmailFlag) {
		this.sendEmailFlag = sendEmailFlag;
	}

	public Integer getPayCountValidDateTime() {
		return payCountValidDateTime;
	}

	public void setPayCountValidDateTime(Integer payCountValidDateTime) {
		this.payCountValidDateTime = payCountValidDateTime;
	}

	public List<Long> getAbnormalIdList() {
		return abnormalIdList;
	}

	public void setAbnormalIdList(List<Long> abnormalIdList) {
		this.abnormalIdList = abnormalIdList;
	}
}
