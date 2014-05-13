package com.xiu.uuc.facade.dto;

/**
 * @ClassName: VirtualAcctItem 
 * @Description: 根据用户ID，获取虚拟账户统计信息 包括：可提现总金额,可提现冻结金额,不可提现总金额,不可提现冻结金额,总积分,积分冻结 供第三方系统调用  
 * @author menglei
 * @date Aug 6, 2011 10:46:59 AM 
 */
public class VirtualAcctItemDTO extends BaseQueryDTO {

	/**
	 * @Fields serialVersionUID :  缺省序列化号 
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 客户ID
	 */
	private Long custId ;
	
	/**
	 * 渠道标识
	 */
	private Integer channelId ;
	
	/**
	 * 用户等级
	 */
	private String userLevel ;
	
	/**
	 * 可提现总金额(包括可体现冻结金额) 单位：分
	 */
	private Long drawTotalMoney = 0L;
	
	/**
	 * 可提现冻结金额 单位：分
	 */
	private Long drawFreezeMoney = 0L;
	
	/**
	 * 不可提现总金额(包括不可体现冻结金额) 单位：分
	 */
	private Long noDrawTotalMoney = 0L;
	
	/**
	 * 不可提现冻结金额(一般缺省为0) 单位：分
	 */
	private Long noDrawFreezeMoney = 0L;
	
	/**
	 * 总积分
	 */
	private Long totalIntegral = 0L;
	
	/**
	 * 冻结积分
	 */
	private Long freezeIntegral = 0L;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public Long getDrawTotalMoney() {
		return drawTotalMoney;
	}

	public void setDrawTotalMoney(Long drawTotalMoney) {
		this.drawTotalMoney = drawTotalMoney;
	}

	public Long getDrawFreezeMoney() {
		return drawFreezeMoney;
	}

	public void setDrawFreezeMoney(Long drawFreezeMoney) {
		this.drawFreezeMoney = drawFreezeMoney;
	}

	public Long getNoDrawTotalMoney() {
		return noDrawTotalMoney;
	}

	public void setNoDrawTotalMoney(Long noDrawTotalMoney) {
		this.noDrawTotalMoney = noDrawTotalMoney;
	}

	public Long getNoDrawFreezeMoney() {
		return noDrawFreezeMoney;
	}

	public void setNoDrawFreezeMoney(Long noDrawFreezeMoney) {
		this.noDrawFreezeMoney = noDrawFreezeMoney;
	}

	public Long getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(Long totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public Long getFreezeIntegral() {
		return freezeIntegral;
	}

	public void setFreezeIntegral(Long freezeIntegral) {
		this.freezeIntegral = freezeIntegral;
	}

	/**
	 * 可提现可用金额（可提现总金额-可提现冻结金额） 单位：分
	 */
	public Long getUnableDrawMoney() {
		return drawTotalMoney-drawFreezeMoney;
	}

	/**
	 * 不可提现可用金额（不可提现总金额-不可提现冻结金额） 单位：分
	 */
	public Long getUnableNoDrawMoney() {
		return noDrawTotalMoney-noDrawFreezeMoney;
	}
	
	/**
	 * 可用总金额(可提现可用金额+不可提现可用金额) 单位：分
	 */
	public Long getUnableTotalMoney() {
		return (drawTotalMoney-drawFreezeMoney)+(noDrawTotalMoney-noDrawFreezeMoney);
	}

	/**
	 * 可用积分（总积分-冻结积分）
	 */
	public Long getUnableIntegral() {
		return totalIntegral-freezeIntegral;
	}
}
