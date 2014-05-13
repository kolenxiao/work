package com.xiu.uuc.facade.dto;

import java.util.Date;

/**
 * 用户详细信息DTO
 * @ClassName: UserDetailDTO
 * @author xiaoyingping
 * @date 2011-7-29 下午08:46:50 
 *
 */
public class UserDetailDTO extends BaseDTO {

	/**
	 * @Fields serialVersionUID :  
	 */
	
	private static final long serialVersionUID = 8133952789070619816L;

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
	private String logonName ;

	/**
	 * 用户登录密码
	 */
	private String password ;

	/**
	 * 渠道标识
	 */
	private Integer channelId ;

	/**
	 * 用户等级
	 */
	private String userLevel;
	
	/**
	 * 客户等级
	 */
	private String custLevel; 
	
	/**
	 * 用户状态
	 */
	private String userState;
	
	/**
	 * 客户状态
	 */
	private String custState; 

	/**
	 * 客户类型
	 */
	private String custType ;

	/**
	 * 是否主用户
	 */
	private String isMaster;

	/**
	 * 联盟客户在联盟渠道中的标识
	 */
	private String partnerId ;

	/**
	 * 联盟客户来源渠道标识
	 */
	private Integer partnerChannelId;
	
	/**
	 * 客户姓名
	 */
	private String custName;
	
	/**
	 * 客户昵称
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
	 * 注册类型 01.Email 02.手机  03.呢称
	 */
	private String registerType;

	/**
	 * 注册时间
	 */
	private Date registerTime ;
	
	/**
	 * 属性类型
	 */
	private String attrType;

	/**
	 * 所在省CODE
	 */
	private String provinceCode;
	private String provinceCodeDesc;

	/**
	 * 所在市CODE
	 */
	private String regionCode ;
	private String regionCodeDesc ;

	/**
	 * 所在县CODE
	 */
	private String cityCode ;
	private String cityCodeDesc ;
	private String cityCodeRemark;

	/**
	 * 街道地址
	 */
	private String addressInfo ;
	
	/**
	 * 身份值类型
	 */
	private String workTypeStyle;
	
	/**
	 * 身份值
	 */
	private String workType;
	private String workTypeDesc;
	
	/**
	 * 性别
	 */
	private String sex;
	private String sexDesc;
	
	/**
	 * 学历
	 */
	private String degree;
	private String degreeDesc;
	
	/**
	 * 月收入
	 */
	private String monthIncome;
	private String monthIncomeDesc;
	
	/**
	 * 婚姻状态
	 */
	private String marry;
	private String marryDesc;
	
	/**
	 * 邀请人ID
	 */
	private String recommendId;
	
	/**
	 * 身份证号码
	 */
	private String idCard;
	
	/**
	 * 兴趣爱好
	 */
	private String interest;

	/**
	 * 统计ID
	 */
	private String fromId ;
	
	/**
	 * 来源类型
	 */
	private String sourceType;
	private String sourceTypeDesc;

	/**
	 * 消息订阅
	 */
	private String subscibeInfo ;

	/**
	 * 积分
	 */
	private Long integral ;

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
        if(null != getPetName() && !"".equals(getPetName())){
            logonName = getPetName();
        }else if(null != getEmail() && !"".equals(getEmail())){
            logonName = getEmail();
        }else if(null != getMobile() && !"".equals(getMobile())){
            logonName = getMobile();
        }
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

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}

	public String getMarry() {
		return marry;
	}

	public void setMarry(String marry) {
		this.marry = marry;
	}

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getWorkTypeStyle() {
		return workTypeStyle;
	}

	public void setWorkTypeStyle(String workTypeStyle) {
		this.workTypeStyle = workTypeStyle;
	}

	public String getSubscibeInfo() {
		return subscibeInfo;
	}

	public void setSubscibeInfo(String subscibeInfo) {
		this.subscibeInfo = subscibeInfo;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = custState;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}	

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public String getProvinceCodeDesc() {
		return provinceCodeDesc;
	}

	public void setProvinceCodeDesc(String provinceCodeDesc) {
		this.provinceCodeDesc = provinceCodeDesc;
	}

	public String getRegionCodeDesc() {
		return regionCodeDesc;
	}

	public void setRegionCodeDesc(String regionCodeDesc) {
		this.regionCodeDesc = regionCodeDesc;
	}

	public String getCityCodeDesc() {
		return cityCodeDesc;
	}

	public void setCityCodeDesc(String cityCodeDesc) {
		this.cityCodeDesc = cityCodeDesc;
	}

	public String getCityCodeRemark() {
		return cityCodeRemark;
	}

	public void setCityCodeRemark(String cityCodeRemark) {
		this.cityCodeRemark = cityCodeRemark;
	}

	public String getWorkTypeDesc() {
		return workTypeDesc;
	}

	public void setWorkTypeDesc(String workTypeDesc) {
		this.workTypeDesc = workTypeDesc;
	}

	public String getSexDesc() {
		return sexDesc;
	}

	public void setSexDesc(String sexDesc) {
		this.sexDesc = sexDesc;
	}

	public String getDegreeDesc() {
		return degreeDesc;
	}

	public void setDegreeDesc(String degreeDesc) {
		this.degreeDesc = degreeDesc;
	}

	public String getMonthIncomeDesc() {
		return monthIncomeDesc;
	}

	public void setMonthIncomeDesc(String monthIncomeDesc) {
		this.monthIncomeDesc = monthIncomeDesc;
	}

	public String getMarryDesc() {
		return marryDesc;
	}

	public void setMarryDesc(String marryDesc) {
		this.marryDesc = marryDesc;
	}

	public String getSourceTypeDesc() {
		return sourceTypeDesc;
	}

	public void setSourceTypeDesc(String sourceTypeDesc) {
		this.sourceTypeDesc = sourceTypeDesc;
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

	public String getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
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
