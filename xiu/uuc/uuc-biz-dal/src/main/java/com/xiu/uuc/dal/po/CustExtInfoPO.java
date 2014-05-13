package com.xiu.uuc.dal.po;


/**
 * 客户扩展信息表PO
 * @ClassName: CustExtInfoPO 
 * @author xiaoyingping
 * @date 2011-7-29 下午08:46:50 
 *
 */
public class CustExtInfoPO extends BasePO {

	/**
	 * 客户ID
	 */
	private Long custId ;
	
	/**
	 * 属性类型
	 */
	private String attrType;

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

	/**
	 * 消息订阅
	 */
	private String subscibeInfo ;


	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
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
	public String getWorkTypeStyle() {
		return workTypeStyle;
	}
	public void setWorkTypeStyle(String workTypeStyle) {
		this.workTypeStyle = workTypeStyle;
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
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
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
	
}
