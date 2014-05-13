package com.xiu.uuc.facade.dto;


/**
 * 收货地址信息DTO（DTO：对外传输对象）
 * @ClassName: RcvAddressDTO 
 * @author xiaoyingping
 * @date 2011-7-22 下午02:57:51 
 *
 */
public class RcvAddressDTO extends BaseDTO {

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 5035065278480594750L;

	/**
	 * 地址ID
	 */
	private Long addressId ;

	/**
	 * 客户ID
	 */
	private Long custId ;

	/**
	 * 用户ID
	 */
	private Long userId ;

	/**
	 * 创建客户的渠道标识
	 */
	private Integer createChannelId ;

	/**
	 * 所在省CODE
	 */
	private String provinceCode ;
	private String provinceCodeDesc ;

	/**
	 * 所在市CODE
	 */
	private String regionCode;
	private String regionCodeDesc;

	/**
	 * 所在县CODE
	 */
	private String cityCode;
	private String cityCodeDesc;
	private String cityCodeRemark;

	/**
	 * 街道地址
	 */
	private String addressInfo;

	/**
	 * 是否默认地址
	 */
	private String isMaster;

	/**
	 * 收货人姓名
	 */
	private String rcverName;

	/**
	 * 区号
	 */
	private String areaCode;

	/**
	 * 电话号码
	 */
	private String phone;

	/**
	 * 分机号
	 */
	private String divCode;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 邮政编码
	 */
	private String postCode;

	/**
	 * 订购人姓名
	 */
	private String bookerName;

	/**
	 * 订购人联系电话
	 */
	private String bookerPhone;


	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Integer getCreateChannelId() {
		return createChannelId;
	}

	public void setCreateChannelId(Integer createChannelId) {
		this.createChannelId = createChannelId;
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

	public String getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}

	public String getRcverName() {
		return rcverName;
	}

	public void setRcverName(String rcverName) {
		this.rcverName = rcverName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getBookerName() {
		return bookerName;
	}

	public void setBookerName(String bookerName) {
		this.bookerName = bookerName;
	}

	public String getBookerPhone() {
		return bookerPhone;
	}

	public void setBookerPhone(String bookerPhone) {
		this.bookerPhone = bookerPhone;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
	

}
