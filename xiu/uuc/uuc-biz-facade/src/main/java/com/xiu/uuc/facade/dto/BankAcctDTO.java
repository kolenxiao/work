package com.xiu.uuc.facade.dto;

/**
 * @ClassName: BankAcctDTO 
 * @Description: 客户银行卡帐号表DTO对象 供第三方系统调用  
 * @author menglei
 * @date Jul 22, 2011 10:08:01 AM 
 */
public class BankAcctDTO extends BaseDTO {

	/**
	 * @Fields serialVersionUID :缺省的序列
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 客户银行卡账号表ID
	 */
	private Long bankAcctId ;    
	
	/**
	 * 客户ID
	 */
	private Long custId ;         
	
	/**
	 * 客户银行卡号
	 */
	private String bankAcctNo ;            
	
	/**
	 * 开户人姓名
	 */
	private String bankAcctName = ""; 
	
	/**
	 * 开户银行名称
	 */
	private String bankName = ""; 
	
	/**
	 * 开户银行分行名称
	 */
	private String bankNameBranch = ""; 
	
	/**
	 * 创建渠道 10751.官网 2.秀团 3.团货
	 */
	private Integer createChannelId ; 
	
	/**
	 * 创建渠道文字描述信息
	 */
	private String createChannelIdDesc = "";
	
	/**
	 * 省代码
	 */
	private String provinceCode = ""; 
	
	/**
	 * 市代码
	 */
	private String cityCode = "";
	
	/**
	 * 是否主卡：1：主卡 2：非主卡
	 */
	private String isMasterCard = "";
	
	/**
	 * 区号
	 */
	private String areaCode ="";     
	
	/**
	 * 电话号码
	 */
	private String phone = ""; 
	
	/**
	 * 手机号码
	 */
	private String mobile = "";
	
	/**
	 * 用户ID
	 */
	private Long userId ;

	public Long getBankAcctId() {
		return bankAcctId;
	}

	public void setBankAcctId(Long bankAcctId) {
		this.bankAcctId = bankAcctId;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getBankAcctNo() {
		return bankAcctNo;
	}

	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}

	public String getBankAcctName() {
		return bankAcctName;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getIsMasterCard() {
		return isMasterCard;
	}

	public void setIsMasterCard(String isMasterCard) {
		this.isMasterCard = isMasterCard;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取创建渠道文字描述信息
	 */
	public String getCreateChannelIdDesc() {
		if("10751".equals(createChannelId)){
			createChannelIdDesc = "秀官网";
		}else if("2".equals(createChannelId)){
			createChannelIdDesc = "秀团";
		}else if("3".equals(createChannelId)){
			createChannelIdDesc = "团货";
		}
		return createChannelIdDesc;
	}

	public String getBankNameBranch() {
		return bankNameBranch;
	}

	public void setBankNameBranch(String bankNameBranch) {
		this.bankNameBranch = bankNameBranch;
	}
}
