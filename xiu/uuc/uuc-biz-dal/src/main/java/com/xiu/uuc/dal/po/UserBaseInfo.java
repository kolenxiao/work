package com.xiu.uuc.dal.po;

import java.util.Date;

/**
 * 用户基本信息
 * @ClassName: UserBaseInfo 
 * @author xiaoyingping
 * @date 2011-7-28 下午08:46:50 
 *
 */
public class UserBaseInfo extends BasePO {

	/**
	 * 用户ID
	 */
	private Long userId ;

	/**
	 * 客户ID
	 */
	private Long custId ;

	/**
	 * 用户登录名
	 */
	private String logonName;

	/**
	 * 用户登录密码
	 */
	private String password;

	/**
	 * 渠道标识
	 */
	private Integer channelId ;

	/**
	 * 用户等级
	 */
	private String userLevel;

	/**
	 * 是否主用户
	 */
	private String isMaster;

	/**
	 * 用户状态
	 */
	private String userState;
	
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

	/**
	 * 注册时间
	 */
	private Date registerTime ;

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
	 * 用户昵称
	 */
	private String petName;

	/**
	 * 注册类型 01.Email 02.手机  03.呢称
	 */
	private String registerType;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 电子邮箱
	 */
	private String email;
	
	/**
	 * 手机认证
	 * 0:未认证  1:认证通过
	 */
	private String mobileAuthenStatus;
	
	/**
	 * 邮箱认证
	 * 0:未认证  1:认证通过
	 */
	private String emailAuthenStatus;
	
	/**
	 * eBay用户协议是否同意  
	 * Y：同意 ，N：不同意,其它也表示不同意
	 */
	private String ebayUserAgreement;

	/**
	 * 生日
	 */
	private Date birthday ;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getLogonName() {
		return logonName;
	}

	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
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

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
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

	public Integer getLastLogonChannelId() {
		return lastLogonChannelId;
	}

	public void setLastLogonChannelId(Integer lastLogonChannelId) {
		this.lastLogonChannelId = lastLogonChannelId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getMobileAuthenStatus() {
		return mobileAuthenStatus;
	}

	public void setMobileAuthenStatus(String mobileAuthenStatus) {
		this.mobileAuthenStatus = mobileAuthenStatus;
	}

	public String getEmailAuthenStatus() {
		return emailAuthenStatus;
	}

	public void setEmailAuthenStatus(String emailAuthenStatus) {
		this.emailAuthenStatus = emailAuthenStatus;
	}

	public String getEbayUserAgreement() {
		return ebayUserAgreement;
	}

	public void setEbayUserAgreement(String ebayUserAgreement) {
		this.ebayUserAgreement = ebayUserAgreement;
	}
	
	
}
