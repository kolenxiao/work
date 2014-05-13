package com.xiu.uuc.dal.po;

/**
 * 账目信息表PO
 * @ClassName: AcctItemPO 
 * @Description: 账目信息表PO
 * @author xiaoyingping
 * @date 2011-7-19 下午08:46:50 
 *
 */
public class AcctItemPO extends BasePO{

	/**
	 * 账目ID
	 */
	private Long acctItemId ;
	
	/**
	 * 帐户ID
	 */
	private Long acctId ;

	/**
	 * 账户类型编号
	 */
	private String acctTypeCode = "" ;

	/**
	 * 当前总账金额
	 */
	private Long totalAmount ;
	
	/**
	 * 当前冻结的金额
	 */
	private Long freezeAmount ;
	
	/**
	 * 账目状态 01.账目正常 02.账目已禁用
	 */
	private String acctItemState = "" ;
	
	/**
	 * 数据版本号
	 */
	private Long dataVersion ;
	
	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
     * 用户昵称
     */
	private String petName;
	
	/**
     * 手机号码
     */
	private String mobile;
	
	/**
     * 邮箱
     */
	private String email;
	
	/**
     * 渠道
     */
	private String channelId;
	
	
	public Long getAcctItemId() {
		return acctItemId;
	}
	public void setAcctItemId(Long acctItemId) {
		this.acctItemId = acctItemId;
	}
	public Long getAcctId() {
		return acctId;
	}
	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}
	public String getAcctTypeCode() {
		return acctTypeCode;
	}
	public void setAcctTypeCode(String acctTypeCode) {
		this.acctTypeCode = acctTypeCode;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getFreezeAmount() {
		return freezeAmount;
	}
	public void setFreezeAmount(Long freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	public String getAcctItemState() {
		return acctItemState;
	}
	public void setAcctItemState(String acctItemState) {
		this.acctItemState = acctItemState;
	}
	public Long getDataVersion() {
		return dataVersion;
	}
	public void setDataVersion(Long dataVersion) {
		this.dataVersion = dataVersion;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
}
