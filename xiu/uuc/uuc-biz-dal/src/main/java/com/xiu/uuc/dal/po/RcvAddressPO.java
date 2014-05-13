package com.xiu.uuc.dal.po;


/**
 * 收货地址信息表PO
 * @ClassName: RcvAddressPO 
 * @Description: 收货地址信息表PO
 * @author xiaoyingping
 * @date 2011-7-19 下午08:46:50 
 *
 */
public class RcvAddressPO extends BasePO {


	/**
	 * 地址ID
	 */
	private Long addressId ;

	/**
	 * 客户ID
	 */
	private Long custId ;

	/**
	 * 创建客户的渠道标识
	 */
	private Integer createChannelId ;

	/**
	 * 所在省CODE
	 */
	private String provinceCode;

	/**
	 * 所在市CODE
	 */
	private String regionCode;

	/**
	 * 所在县CODE
	 */
	private String cityCode;

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

}
