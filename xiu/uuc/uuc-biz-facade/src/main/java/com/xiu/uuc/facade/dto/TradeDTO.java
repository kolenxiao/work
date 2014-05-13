package com.xiu.uuc.facade.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TradeDTO 
 * @Description: 交易参数DTO对象 供第三方系统调用
 *               1. 退款进账
 *               2. 扣款出账
 * @author menglei
 * @date Jul 22, 2011 9:53:19 AM 
 */

public class TradeDTO implements Serializable {

	/**
	 * @Fields serialVersionUID : 缺省序列化号 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 交易订单ID(退款的时候传老订单ID，支付的时候传新订单ID)
	 */
	private Long rltId ;     
	
	/**
	 * 交易订单CODE(退款的时候传老订单CODE，支付的时候传新订单CODE)
	 */
	private String rltCode ;  
	
	/**
	 * 支付流水号
	 */
	private String rltSeq ;  
	
	/**
	 * 交易总金额 单位：分
	 */
	private Long totalAmount ; 
	
	/**
	 * 业务来源渠道标识 11.官网 12.秀团 13.团货
	 */
	private String rltChannelId ;
	
	/**
	 * 业务类型 
	 */
	private String busiType; 
	
	/**
	 * 账目类型 01 可提现 02不可提现 03积分 04换货支付
	 */
	private String acctTypeCode ;
	
	/**
	 * 操作人员ID
	 */
	private String operId;
	
	/**
	 * 导致帐目变化的业务方式 1：自动方式 2：手动方式 缺省为1
	 */
	private String operMode ;
	
	/**
	 * 进出账标志 01进账 02出账
	 */
	private String ioType ;
	
	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 交易轨迹列表
	 */
	private List<TractDTO> tractDTOList = new ArrayList<TractDTO>();

	public Long getRltId() {
		return rltId;
	}

	public void setRltId(Long rltId) {
		this.rltId = rltId;
	}

	public String getRltCode() {
		return rltCode;
	}

	public void setRltCode(String rltCode) {
		this.rltCode = rltCode;
	}

	public String getRltSeq() {
		return rltSeq;
	}

	public void setRltSeq(String rltSeq) {
		this.rltSeq = rltSeq;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRltChannelId() {
		return rltChannelId;
	}

	public void setRltChannelId(String rltChannelId) {
		this.rltChannelId = rltChannelId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<TractDTO> getTractDTOList() {
		return tractDTOList;
	}

	public void setTractDTOList(List<TractDTO> tractDTOList) {
		this.tractDTOList = tractDTOList;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getAcctTypeCode() {
		return acctTypeCode;
	}

	public void setAcctTypeCode(String acctTypeCode) {
		this.acctTypeCode = acctTypeCode;
	}
	
	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}
	
	public String getOperMode() {
		return operMode;
	}

	public void setOperMode(String operMode) {
		this.operMode = operMode;
	}
	
	public String getIoType() {
		return ioType;
	}

	public void setIoType(String ioType) {
		this.ioType = ioType;
	}
	
	public String toString() {
		String s = "";
		try {
			s = getPropertyString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	public String getPropertyString(Object entityName) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("------ " + " begin ------\n");
		if(entityName!=null){
			Class c = entityName.getClass();
			Field field[] = c.getDeclaredFields();
			for (Field f : field) {
				Object obj = invokeMethod(entityName, f.getName(), null);
				if(null != obj){
					sb.append(f.getName());
					sb.append(" : ");
					sb.append(obj);
					sb.append("\n");
				}
			}
		}
		sb.append("------ " + " end ------\n");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public Object invokeMethod(Object owner, String methodName, Object[] args)throws Exception {
		Class ownerClass = owner.getClass();
		Method method = null;
		if(methodName!=null){
			methodName = methodName.substring(0, 1).toUpperCase()+ methodName.substring(1);
		}
		try {
			method = ownerClass.getMethod("get" + methodName);
		} catch (SecurityException e) {
			 e.printStackTrace();
		} catch (NoSuchMethodException e) {
			return null;
		}
		return method.invoke(owner);
	}

}
