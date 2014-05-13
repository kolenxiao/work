package com.xiu.uuc.dal.po;

import java.util.Date;
import java.util.List;
import com.xiu.uuc.facade.util.TypeEnum;

/**
 * 虚拟账户异动日志表PO
 * @ClassName: AbnormalLogPO 
 * @Description: 虚拟账户异动日志表PO
 * @author menglei
 * @date 2011-12-13 下午14:46:50 
 */

public class AbnormalLogPO extends BasePO{
	
	public AbnormalLogPO(){
		
	}
	
    public AbnormalLogPO(final String abnormalType, final String abnormalDesc,
			final Long acctId, final Long acctItemId, final Long userId,
			final String acctTypeCode, final Long sumIoAmount,
			final Long sumChangeCount,final Long lastIoAmount) {
		this.abnormalType = abnormalType;
		this.abnormalDesc = abnormalDesc;
		this.acctId = acctId;
		this.acctItemId = acctItemId;
		this.userId = userId;
		this.acctTypeCode = acctTypeCode;
		this.sumIoAmount = sumIoAmount;
		this.sumChangeCount = sumChangeCount;
		this.lastIoAmount = lastIoAmount;
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
	 * 当前用户，当前账目类型 描述信息
	 */
	private String acctTypeCodeDesc ;
	
	/**
	 * 当前用户，当前账目类型 流水总账
	 */
	private Long sumIoAmount ;
	
	/**
	 * 当前用户，当前账目类型 出入数量 变更操作后的最新数量（在当前时间段内）
	 */
	private Long lastIoAmount ;
	
	/**
	 * 当前用户，当前账目类型 流水总次数统计
	 */
	private Long sumChangeCount ;
	
	/**
     * 是否已经发送了告警邮件信息 0:未发送 1：已发送 缺省为0
     */
	private String sendEmailFlag = "0";
	
	/**
     * 审计时间段 开始时间
     */
	private Date begTime;
	
	/**
     * 审计时间段 截止时间
     */
	private Date endTime;
	
	/**
     * 用户昵称
     */
	private String petName;
	
	/**
     * 手机号码
     */
	private String mobile;
	
	/**
     * 邮箱
     */
	private String email;
	
	/**
     * 渠道
     */
	private String channelId;
	
	/**
     * 渠道描述
     */
	private String channelIdDesc;
	
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

	public Long getSumIoAmount() {
		return sumIoAmount;
	}

	public void setSumIoAmount(Long sumIoAmount) {
		this.sumIoAmount = sumIoAmount;
	}

	public Long getSumChangeCount() {
		return sumChangeCount;
	}

	public void setSumChangeCount(Long sumChangeCount) {
		this.sumChangeCount = sumChangeCount;
	}

	public Long getLastIoAmount() {
		return lastIoAmount;
	}

	public void setLastIoAmount(Long lastIoAmount) {
		this.lastIoAmount = lastIoAmount;
	}

	public String getSendEmailFlag() {
		return sendEmailFlag;
	}

	public void setSendEmailFlag(String sendEmailFlag) {
		this.sendEmailFlag = sendEmailFlag;
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

	public String getAcctTypeCodeDesc() {
		acctTypeCodeDesc = TypeEnum.AcctItemType.getList().get(acctTypeCode);
		return acctTypeCodeDesc;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelIdDesc() {
		channelIdDesc = TypeEnum.ChannelType.getList().get(Integer.getInteger(channelId));
		return channelIdDesc;
	}

	public List<Long> getAbnormalIdList() {
		return abnormalIdList;
	}

	public void setAbnormalIdList(List<Long> abnormalIdList) {
		this.abnormalIdList = abnormalIdList;
	}

	public Integer getPayCountValidDateTime() {
		return payCountValidDateTime;
	}

	public void setPayCountValidDateTime(Integer payCountValidDateTime) {
		this.payCountValidDateTime = payCountValidDateTime;
	}
}
