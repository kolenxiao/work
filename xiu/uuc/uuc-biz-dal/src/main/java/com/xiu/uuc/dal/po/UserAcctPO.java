package com.xiu.uuc.dal.po;

/**
 * 用户账户表PO
 * @ClassName: UserAcctPO 
 * @Description: 用户账户表PO
 * @author xiaoyingping
 * @date 2011-7-19 下午08:46:50 
 */
public class UserAcctPO extends BasePO{

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
	private String acctName ;
	
	/**
	 * 账户状态
	 * 01:正常   02:已冻结
	 */
	private String acctState ; 
	
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

	public Long getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(Long dataVersion) {
		this.dataVersion = dataVersion;
	}
}
