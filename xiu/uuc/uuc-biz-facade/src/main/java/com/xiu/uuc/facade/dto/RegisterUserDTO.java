package com.xiu.uuc.facade.dto;

/**
 * 
 * @ClassName: RegisterUserDTO
 * @Description: 用户注册传输对象
 * @author xiaoyingping
 * @date 2011-7-19 上午11:24:37
 * 
 */
public class RegisterUserDTO extends BaseDTO {

	private static final long serialVersionUID = 5843912494072804242L;

	/**
	 * 用户登录名
	 */
	private String logonName ;
	
	/**
	 * 用户登录密码
	 */
	private String password ;
	
	/**
	 * 创建渠道标识
	 * 11：官网  12：秀团  13：团货
	 */
	private Integer channelId;
	
	/**
	 * 注册类型
	 * 01:邮箱  02:手机  03:呢称
	 */
	private String registerType ;
	
	/**
	 * 客户类型
	 * 01:秀客户  02:联盟用户
	 */
	private String custType ;
	
	/**
	 * 联盟客户在联盟渠道中的ID
	 */
	private String partnerId ;
	
	/**
	 * 联盟客户来源渠道标识
	 */
	private Integer partnerChannelId = null; 
	
	/**
	 * 是否需要将邮箱注册用户设置为"非激活"状态
	 * Y:需要激活   N:不需要激活
	 */
	private String isNeedActivate ;
	
	/**
	 * 注册IP地址
	 */
	private String registerIp ;
	
	

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

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
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

	public String getIsNeedActivate() {
		return isNeedActivate;
	}

	public void setIsNeedActivate(String isNeedActivate) {
		this.isNeedActivate = isNeedActivate;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

}
