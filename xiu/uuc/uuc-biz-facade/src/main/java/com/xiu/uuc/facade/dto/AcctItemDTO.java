package com.xiu.uuc.facade.dto;

import java.util.Date;
import java.util.List;
import com.xiu.uuc.facade.util.TypeEnum;

/**
 * @ClassName: AcctItemDTO 
 * @Description: 账目表DTO对象 供第三方系统调用  
 * @author menglei
 * @date Jul 22, 2011 9:58:27 AM 
 */
public class AcctItemDTO extends BaseQueryDTO {
	
    public AcctItemDTO(){
		
	}
	
	public AcctItemDTO(Long userId,Long totalAmount){
		this.userId = userId;
		this.totalAmount = totalAmount;
	}

	/**
	 * @Fields serialVersionUID : 缺省序列化号
	 */
	
	private static final long serialVersionUID = 1L;
	
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
	 * 账目类型 文字描述信息
	 */
	private String acctTypeCodeDesc = "" ;
	
	/**
	 * 账目类型 01 可提现 02不可提现 03积分 列表条件 支持一次性查询出多条账目信息
	 */
	private List<String> acctTypeCodeList;

	/**
	 * 当前总账金额 单位：分
	 */
	private Long totalAmount ;
	
	/**
	 * 当前冻结的金额 单位：分
	 */
	private Long freezeAmount;
	
	/**
	 * 账目状态 01.账目正常 02.账目已禁用
	 */
	private String acctItemState = "" ;
	
	/**
	 * 数据版本号 作用:在更新账目信息时（扣款或者充值），
	 * 连同扣款或者充值金额一起传递给后台，防止数据同步操作出差，作为乐观锁使用
	 * 后台将提供相关接口获取数据版本
	 */
	private Long dataVersion ;
	
	/**
	 * 当前申请提现金额 单位：分
	 */
	private Long applyDrawAmount = 0L;
	
	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 对当前账目进行操作的类型 比如：对当前账目金额进行冻结 add操作，dec操作
	 */
	private String operType=""; 
	
	/**
	 * 对当前账目进行冻结操作的时候 是否仅仅对账目冻结数量进行清零操作 
	 * 比如：申请提现前     可提现总金额（包括冻结部分）为1000，可提现冻结为0 ，申请提现 300
	 * 0：表示不是仅仅对冻结数量做清零操作  可提现总金额（包括冻结部分）为700， 可提现冻结为0
	 * 1：表示仅仅对冻结数量做清零操作  可提现总金额（包括冻结部分）为1000，可提现冻结为0
	 */
	private String OnlyClearZero="0"; 
	
	/**
     * 生效时间(相对于创建时间)
     */
	private Date validTime ;
	
	/**
     * 失效时间(相对于创建时间)
     */
	private Date expireTime ;

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

	public Long getApplyDrawAmount() {
		return applyDrawAmount;
	}

	public void setApplyDrawAmount(Long applyDrawAmount) {
		this.applyDrawAmount = applyDrawAmount;
	}

	public List<Long> getAcctItemIdList() {
		return acctItemIdList;
	}

	public void setAcctItemIdList(List<Long> acctItemIdList) {
		this.acctItemIdList = acctItemIdList;
	}

	public List<String> getAcctTypeCodeList() {
		return acctTypeCodeList;
	}

	public void setAcctTypeCodeList(List<String> acctTypeCodeList) {
		this.acctTypeCodeList = acctTypeCodeList;
	}
	
	/**
	 * 获取账目类型文字描述信息
	 */
	public String getAcctTypeCodeDesc() {
		acctTypeCodeDesc = TypeEnum.AcctItemType.getList().get(acctTypeCode);
		return acctTypeCodeDesc;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getOnlyClearZero() {
		return OnlyClearZero;
	}

	public void setOnlyClearZero(String onlyClearZero) {
		OnlyClearZero = onlyClearZero;
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
}
