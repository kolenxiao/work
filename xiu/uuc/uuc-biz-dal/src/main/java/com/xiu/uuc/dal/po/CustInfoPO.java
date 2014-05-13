package com.xiu.uuc.dal.po;

import java.util.Date;

/**
 * 客户信息表PO
 * @ClassName: CustInfoPO 
 * @Description: 客户信息表PO
 * @author xiaoyingping
 * @date 2011-7-19 下午08:46:50 
 *
 */
public class CustInfoPO extends BasePO {

	/**
	 * 客户ID
	 */
	private Long custId;

	/**
	 * 注册渠道标识
	 */
	private Integer channelId;

	/**
	 * 客户状态
	 */
	private String custState;

	/**
	 * 客户类型
	 */
	private String custType;

	/**
	 * 联盟客户在联盟渠道中的标识
	 */
	private String partnerId;

	/**
	 * 联盟客户来源渠道标识
	 */
	private Integer partnerChannelId;

	/**
	 * 客户等级
	 */
	private String custLevel;

	/**
	 * 客户姓名
	 */
	private String custName;

	/**
	 * 注册类型 01.Email 02.手机  03.呢称
	 */
	private String registerType;

	/**
	 * 生日
	 */
	private Date birthday ;
	
	/**
	 * 最后登录IP
	 */
	private String lastLogonIp;
	
	/**
	 * 最后登录时间
	 */
	private Date lastLogonTime;
	
	/**
	 * 最后登录渠道标识
	 */
	private Integer lastLogonChannelId;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = custState;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getPartnerChannelId() {
		return partnerChannelId;
	}

	public void setPartnerChannelId(Integer partnerChannelId) {
		this.partnerChannelId = partnerChannelId;
	}

	public String getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLastLogonIp() {
		return lastLogonIp;
	}

	public void setLastLogonIp(String lastLogonIp) {
		this.lastLogonIp = lastLogonIp;
	}

	public Date getLastLogonTime() {
		return lastLogonTime;
	}

	public void setLastLogonTime(Date lastLogonTime) {
		this.lastLogonTime = lastLogonTime;
	}

	public Integer getLastLogonChannelId() {
		return lastLogonChannelId;
	}

	public void setLastLogonChannelId(Integer lastLogonChannelId) {
		this.lastLogonChannelId = lastLogonChannelId;
	}



}
