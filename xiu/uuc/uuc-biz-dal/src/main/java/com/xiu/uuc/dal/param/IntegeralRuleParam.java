package com.xiu.uuc.dal.param;

import java.util.Map;

/**
 * @ClassName: IntegeralRuleParam 
 * @Description: 积分规则创建点表Param 
 * @author IBM
 * @date Aug 1, 2011 3:48:59 PM 
 */
public class IntegeralRuleParam extends BaseParam{
	
	/**
	 * 积分规则创建点序列号 PK
	 */
	private Long createPointId; 
	
	/**
	 * 积分规则创建点编号
	 */
	private String createPointCode = "";    
	
	/**
	 * 积分规则创建点名称
	 */
	private String createPointName = "";         
	
	/**
	 * 积分规则创建点描述信息
	 */
	private String createPointDesc = "";            
	
	/**
	 * 积分规则创建点状态 01启用 02禁用
	 */
	private String createPointState = ""; 
	
	/**
	 * 当前操作员序列号
	 */
	private String operId = "";     
	
	/**
	 * 积分生成的表达式 p1*100 + p2*300 
	 * 表达式的解析使用 apache 的 JEXL
	 * p1,p2 的值 从hashmap 中取出
	 */
	private String caculateExpression = ""; 
	
	/**
	 * 积分规则应用的渠道编号
	 */
	private Integer channelId ;
	
	/**
	 * 积分来源和去向 01获取积分 02扣除积分
	 */
	private String operType = ""; 
	
	/**
	 * 用于存放计算表达式caculateExpression 的参数
	 */
	@SuppressWarnings("unchecked")
	private Map map;
	
	public IntegeralRuleParam(){
		
	}
	
	public IntegeralRuleParam(String createPointCode){
		this.createPointCode = createPointCode;
	}
	
	@SuppressWarnings("unchecked")
	public IntegeralRuleParam(String createPointCode,Map map){
		this.createPointCode = createPointCode;
		this.map = map;
	}

	public Long getCreatePointId() {
		return createPointId;
	}

	public void setCreatePointId(Long createPointId) {
		this.createPointId = createPointId;
	}

	public String getCreatePointName() {
		return createPointName;
	}

	public void setCreatePointName(String createPointName) {
		this.createPointName = createPointName;
	}

	public String getCreatePointDesc() {
		return createPointDesc;
	}

	public void setCreatePointDesc(String createPointDesc) {
		this.createPointDesc = createPointDesc;
	}

	public String getCreatePointState() {
		return createPointState;
	}

	public void setCreatePointState(String createPointState) {
		this.createPointState = createPointState;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getCaculateExpression() {
		return caculateExpression;
	}

	public void setCaculateExpression(String caculateExpression) {
		this.caculateExpression = caculateExpression;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getCreatePointCode() {
		return createPointCode;
	}

	public void setCreatePointCode(String createPointCode) {
		this.createPointCode = createPointCode;
	}

	@SuppressWarnings("unchecked")
	public Map getMap() {
		return map;
	}

	@SuppressWarnings("unchecked")
	public void setMap(Map map) {
		this.map = map;
	}
}
