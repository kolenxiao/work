package com.xiu.uuc.manager.validate;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.StringUtils;
import com.xiu.uuc.dal.po.UserInfoPO;
import com.xiu.uuc.facade.dto.BankAcctDTO;

/**
 * @ClassName: BankAcctManagerValidate 
 * @Description:  主要作用是对manager层输入参数DTO，进行合法性校验  
 * @author menglei
 * @date Aug 18, 2011 2:10:50 PM 
 */
public class BankAcctManagerValidate {
	
	/**
	 * @Title: validateInsertBankAcct 
	 * @Description: 新增银行卡账户信息 输入参数合法性校验 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateInsertBankAcct(BankAcctDTO bankAcctDTO){
		if (StringUtils.isNullObject(bankAcctDTO)) {
			throw new ParameterException("输入参数对象bankAcctDTO不允许为空");
		}
		if(StringUtils.isNullObject(bankAcctDTO.getUserId())) {
			throw new ParameterException("输入参数对象bankAcctDTO中的属性userId不允许为空");
		}
		if(StringUtils.isNullObject(bankAcctDTO.getBankAcctNo())) {
			throw new ParameterException("输入参数对象bankAcctDTO中的属性bankAcctNo不允许为空");
		}
		if(StringUtils.isNullObject(bankAcctDTO.getBankAcctName())) {
			throw new ParameterException("输入参数对象bankAcctDTO中的属性bankAcctName不允许为空");
		}
		if(StringUtils.isNullObject(bankAcctDTO.getCreateChannelId())) {
			throw new ParameterException("输入参数对象bankAcctDTO中的属性createChannelId不允许为空");
		}
	}
	
	/** 
	 * @Title: validateGetUserById 
	 * @Description: 通过用户id获取用户信息 校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateGetUserById(UserInfoPO userInfoPO){
		if (StringUtils.isNullObject(userInfoPO)) {
			throw new BusinessException("当前userId对应的用户信息不存在");
		}
		if(StringUtils.isNullObject(userInfoPO.getCustId())){
			throw new BusinessException("当前userId对应的用户信息中的custId不允许为空");
		}
	}
	
	/**
	 * @Title: validateDeleteBankAcct 
	 * @Description: 删除银行卡账户信息 输入参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateDeleteBankAcct(BankAcctDTO bankAcctDTO){
		if (StringUtils.isNullObject(bankAcctDTO)) {
			throw new ParameterException("输入参数对象bankAcctDTO不允许为空");
		}
		if(StringUtils.isNullObject(bankAcctDTO.getBankAcctId())) {
			throw new ParameterException("输入参数对象bankAcctDTO中的属性bankAcctId不允许为空");
		}
	}
	
	/**
	 * @Title: validateUpdateBankAcct 
	 * @Description: 修改银行卡账户信息 输入参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateUpdateBankAcct(BankAcctDTO bankAcctDTO){
		if (StringUtils.isNullObject(bankAcctDTO)) {
			throw new ParameterException("输入参数对象bankAcctDTO不允许为空");
		}
		if(StringUtils.isNullObject(bankAcctDTO.getBankAcctId())
			&& StringUtils.isNullObject(bankAcctDTO.getCustId())
		    && StringUtils.isNullObject(bankAcctDTO.getUserId())) {
			throw new ParameterException("输入参数对象bankAcctDTO中的属性bankAcctId,custId,userId不允许同时为空");
		}
	}
	
	/**
	 * @Title: validateListBankAcct 
	 * @Description: 查询银行卡账户信息列表 输入参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateListBankAcct(BankAcctDTO bankAcctDTO){
		if (StringUtils.isNullObject(bankAcctDTO)) {
			throw new ParameterException("输入参数对象bankAcctDTO不允许为空");
		}
	}
	
	/**
	 * @Title: validateFindBankAcct 
	 * @Description: 查询特定提现银行账号详情 输入参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateFindBankAcct(BankAcctDTO bankAcctDTO){
		if (StringUtils.isNullObject(bankAcctDTO)) {
			throw new ParameterException("输入参数对象bankAcctDTO不允许为空");
		}
		if(StringUtils.isNullObject(bankAcctDTO.getBankAcctId())) {
			throw new ParameterException("输入参数对象bankAcctDTO中的属性bankAcctId不允许为空");
		}
	}
}
