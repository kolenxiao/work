package com.xiu.uuc.dal.param;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: AcctItemParam 
 * @Description: 账目信息表Param对象 用于对账目信息进行查询操作 
 * @author menglei
 * @date Jul 22, 2011 4:53:28 PM 
 */
public class AcctItemParam extends BaseParam {
	
	public AcctItemParam(){
		
	}
	
	public AcctItemParam(final Integer payCountValidDateTime){
		this.payCountValidDateTime = payCountValidDateTime;
	}
	
	public AcctItemParam(final Date validTime, final Date expireTime){
		this.validTime = validTime;
		this.expireTime = expireTime;
	}
	
	public AcctItemParam(final Long userId,final String acctTypeCode){
		this.userId = userId;
		this.acctTypeCode = acctTypeCode;
	}
	
	/**
	 * 账目ID
	 */
	private Long acctItemId ;
	
	/**
	 * 账目ID 列表条件 支持一次性查询出多条账目信息
	 */
	private List<Long> acctItemIdList;
	
	/**
	 * 帐户ID
	 */
	private Long acctId ;

	/**
	 * 账目类型 01 可提现 02不可提现 03积分
	 */
	private String acctTypeCode = "" ;
	
	/**
	 * 账目类型 01 可提现 02不可提现 03积分 列表条件 支持一次性查询出多条账目信息
	 */
	private List<String> acctTypeCodeList;

	/**
	 * 当前总账金额 单位：分
	 */
	private Long totalAmount;
	
	/**
	 * 当前冻结的金额 单位：分
	 */
	private Long freezeAmount;
	
	/**
	 * 账目状态 01.账目正常 02.账目已禁用
	 */
	private String acctItemState = "" ;
	
	/**
	 * 操作人员ID
	 */
	private String operId = "";
	
	/**
	 * 数据版本号 作用:在更新账目信息时（扣款或者充值），
	 * 连同扣款或者充值金额一起传递给后台，防止数据同步操作出差，作为乐观锁使用
	 * 后台将提供相关接口获取数据版本
	 */
	private Long dataVersion ;
	
	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 当前申请提现金额 单位：分
	 */
	private Long applyDrawAmount ;
	
	/**
     * 生效时间(相对于创建时间)
     */
	private Date validTime ;
	
	/**
     * 失效时间(相对于创建时间)
     */
	private Date expireTime ;
	
	/**
     * 时间间隔单位：分钟(相对于创建时间)
     * 用户虚拟账户异动审计 账目流水支付笔数统计时间段
     */
	private Integer payCountValidDateTime;

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

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public List<Long> getAcctItemIdList() {
		return acctItemIdList;
	}

	public void setAcctItemIdList(List<Long> acctItemIdList) {
		this.acctItemIdList = acctItemIdList;
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

	public Long getApplyDrawAmount() {
		return applyDrawAmount;
	}

	public void setApplyDrawAmount(Long applyDrawAmount) {
		this.applyDrawAmount = applyDrawAmount;
	}

	public List<String> getAcctTypeCodeList() {
		return acctTypeCodeList;
	}

	public void setAcctTypeCodeList(List<String> acctTypeCodeList) {
		this.acctTypeCodeList = acctTypeCodeList;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getPayCountValidDateTime() {
		return payCountValidDateTime;
	}

	public void setPayCountValidDateTime(Integer payCountValidDateTime) {
		this.payCountValidDateTime = payCountValidDateTime;
	}
}
