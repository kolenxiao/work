package com.xiu.uuc.core.util;

import java.util.HashSet;
import java.util.Set;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.util.CommonEnum;
import com.xiu.uuc.facade.util.TypeEnum;

public class CoreUtil {

	/**
	 * 客户，用户状态的业务操作处理
	 * @Title: processBusiStatus 
	 * @param   
	 * @return void    返回类型 
	 * @throws
	 */
	public static void processBusiStatus(String custState, String userState, String busiOperType){		
		String custStateDesc = "";
		String userStateDesc = "";
		String busiOperTypeDesc = "";
		Set<String> grantSet = null;//可以执行
		Set<String> notGrantSet = null;//不能执行

		if(TypeEnum.CustState.getList().keySet().contains(custState)){
			custStateDesc = TypeEnum.CustState.getList().get(custState);
		}
		if(TypeEnum.UserState.getList().keySet().contains(userState)){
			userStateDesc = TypeEnum.UserState.getList().get(userState);
		}
		if(CommonEnum.BusiOperType.getList().keySet().contains(busiOperType)){
			busiOperTypeDesc = CommonEnum.BusiOperType.getList().get(busiOperType);
		}
		
		//客户状态
		if(TypeEnum.CustState.NOT_ACTIVATION.getKey().equals(custState)){//未激活
			grantSet = new HashSet<String>();
			grantSet.add(CommonEnum.BusiOperType.ACTIVATE_USER.getKey());
			grantSet.add(CommonEnum.BusiOperType.DELETE_USER.getKey());
		}else if(TypeEnum.CustState.NATURAL.getKey().equals(custState)){//正常
			notGrantSet = new HashSet<String>();
			notGrantSet.add(CommonEnum.BusiOperType.ACTIVATE_USER.getKey());
		}
		
		if(null != grantSet && !grantSet.contains(busiOperType)){
			throw new BusinessException("客户状态为:'"+custStateDesc+"',不能进行'"+busiOperTypeDesc+"'操作!");
		}
		if(null != notGrantSet && notGrantSet.contains(busiOperType)){
			throw new BusinessException("客户状态为:'"+custStateDesc+"',不能进行'"+busiOperTypeDesc+"'操作!");
		}
		
		//用户状态
		grantSet = null;
		notGrantSet = null;
		if(TypeEnum.UserState.NATURAL.getKey().equals(userState)){//正常
			notGrantSet = new HashSet<String>();
			notGrantSet.add(CommonEnum.BusiOperType.UNFORBIT_USER.getKey());
		}else if(TypeEnum.UserState.FORBID.getKey().equals(userState)){//禁用
			notGrantSet = new HashSet<String>();
			notGrantSet.add(CommonEnum.BusiOperType.FORBIT_USER.getKey());
		}else if(TypeEnum.UserState.DELETED.getKey().equals(userState)){//删除
			grantSet = new HashSet<String>();
		}
		
		if(null != grantSet && !grantSet.contains(busiOperType)){
			throw new BusinessException("用户状态为:'"+userStateDesc+"',不能进行'"+busiOperTypeDesc+"'操作!");
		}
		if(null != notGrantSet && notGrantSet.contains(busiOperType)){
			throw new BusinessException("用户状态为:'"+userStateDesc+"',不能进行'"+busiOperTypeDesc+"'操作!");
		}
	}


}
