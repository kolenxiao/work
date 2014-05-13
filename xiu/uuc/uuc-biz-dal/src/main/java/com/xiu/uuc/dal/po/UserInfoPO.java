package com.xiu.uuc.dal.po;

/**
 * 用户信息表PO
 * @ClassName: UserInfoPO 
 * @Description: 用户信息表PO
 * @author xiaoyingping
 * @date 2011-7-19 下午08:46:50 
 *
 */
public class UserInfoPO extends BasePO {

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
	 * 用户昵称
	 */
	private String petName;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 是否主用户
	 * Y:主用户   N:非主用户
	 */
	private String isMaster;

	/**
	 * 用户状态
	 * 01:正常   02:已冻结   03:已禁用   04:已删除
	 */
	private String userState;
	
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

	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getEmailAuthenStatus() {
		return emailAuthenStatus;
	}

	public void setEmailAuthenStatus(String emailAuthenStatus) {
		this.emailAuthenStatus = emailAuthenStatus;
	}

	public String getMobileAuthenStatus() {
		return mobileAuthenStatus;
	}

	public void setMobileAuthenStatus(String mobileAuthenStatus) {
		this.mobileAuthenStatus = mobileAuthenStatus;
	}

	public String getEbayUserAgreement() {
		return ebayUserAgreement;
	}

	public void setEbayUserAgreement(String ebayUserAgreement) {
		this.ebayUserAgreement = ebayUserAgreement;
	}
	

}
