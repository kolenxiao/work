package com.xiu.uuc.dal.param;

/**
 * @ClassName: UserAcctParam 
 * @Description: 用戶账户信息表Param对象 用于对用户账户信息进行查询操作 
 * @author menglei
 * @date Jul 24, 2011 10:43:21 AM 
 */
public class UserAcctParam extends BaseParam {
	
	/**
	 * 账户ID
	 */
	private Long acctId ;
	
	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 账户名称
	 */
	private String acctName = "";
	
	/**
	 * 账户状态
	 * 01:正常   02:已冻结
	 */
	private String acctState = ""; 
	
	/**
	 * 操作人员ID
	 */
	private String operId = ""; 
	
	/**
	 * 数据版本号
	 */
	private Long dataVersion ;
	
	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getAcctState() {
		return acctState;
	}

	public void setAcctState(String acctState) {
		this.acctState = acctState;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Long getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(Long dataVersion) {
		this.dataVersion = dataVersion;
	}
}
