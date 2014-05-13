package com.xiu.uuc.facade.dto;

/**
 * 用户账户信息DTO（DTO：对外传输对象）
 * @ClassName: UserAcctDTO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author xiaoyingping
 * @date 2011-7-22 下午03:04:56 
 *
 */
public class UserAcctDTO extends BaseDTO {

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 6504983206238592974L;


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
	
	public UserAcctDTO(){
		
	}
	
	public UserAcctDTO(Long userId){
		this.userId = userId;
	}

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
