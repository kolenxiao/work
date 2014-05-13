package com.xiu.uuc.facade.dto;

import java.util.Date;


/**
 * 用户查询条件DTO
 * @ClassName: UserQueryDTO
 * @author xiaoyingping
 * @date 2011-7-29 下午08:46:50 
 *
 */
public class UserQueryDTO extends BaseQueryDTO {


	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = -5494437276802559674L;

	
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
	 * 生日
	 */
	private Date birthday ;

	/**
	 * 所在省CODE
	 */
	private String provinceCode;

	/**
	 * 所在市CODE
	 */
	private String regionCode ;

	/**
	 * 所在县CODE
	 */
	private String cityCode ;

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
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 学历
	 */
	private String degree;
	
	/**
	 * 月收入
	 */
	private String monthIncome;
	
	/**
	 * 婚姻状态
	 */
	private String marry;
	
	/**
	 * 邀请人ID
	 */
	private String recommendId;
	
	/**
	 * 身份证号码
	 */
	private String idCard;
	
	/**
	 * 来源类型
	 */
	private String sourceType;

	/**
	 * 消息订阅
	 */
	private String subscibeInfo ;

	/**
	 * 联盟客户在联盟渠道中的标识
	 */
	private String partnerId ;

	/**
	 * 联盟客户来源渠道标识
	 */
	private Integer partnerChannelId;
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	/**
	 * 最小积分
	 */
	private String minIntegral;
	
	/**
	 * 最大积分
	 */
	private String maxIntegral;

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

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = custState;
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

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSubscibeInfo() {
		return subscibeInfo;
	}

	public void setSubscibeInfo(String subscibeInfo) {
		this.subscibeInfo = subscibeInfo;
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

	public String getMinIntegral() {
		return minIntegral;
	}

	public void setMinIntegral(String minIntegral) {
		this.minIntegral = minIntegral;
	}

	public String getMaxIntegral() {
		return maxIntegral;
	}

	public void setMaxIntegral(String maxIntegral) {
		this.maxIntegral = maxIntegral;
	}

	public String getWorkTypeStyle() {
		return workTypeStyle;
	}

	public void setWorkTypeStyle(String workTypeStyle) {
		this.workTypeStyle = workTypeStyle;
	}

}
