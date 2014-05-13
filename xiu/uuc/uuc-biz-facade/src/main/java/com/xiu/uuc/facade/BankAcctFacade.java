package com.xiu.uuc.facade;

import com.xiu.commons.core.IServiceStatusHealthyChecking;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.BankAcctDTO;

/**
 * @ClassName: BankAcctFacade 
 * @Description: 用户提现银行卡账号管理的facade接口 供第三方系统统一调用 
 * @author menglei
 * @date Jul 21, 2011 5:49:50 PM 
 */
public interface BankAcctFacade extends IServiceStatusHealthyChecking {
	
	/**
	 * @Title: addBankAcctInfo 
	 * @Description: 新增用户提现银行账号信息 
	 * @return Result    返回类型 
	 * @throws
	 */
	Result addBankAcctInfo(BankAcctDTO bankAcctDTO);
	
	/**
	 * @Title: deleteBankAcctInfo 
	 * @Description: 删除用户提现银行帐号信息(注意这里是逻辑删除，不是正真意义上的物理删除)
	 * @return Result    返回类型 
	 * @throws
	 */
	Result deleteBankAcctInfo(BankAcctDTO bankAcctDTO);
	
	/**
	 * @Title: updateBankAcctInfo 
	 * @Description: 编辑用户提现银行账号帐号信息
	 * @return Result    返回类型 
	 * @throws
	 */
	Result updateBankAcctInfo(BankAcctDTO bankAcctDTO);
	
	/**
	 * @Title: getBankAcctListInfo 
	 * @Description: 查询用户已有的提现银行账号列表 
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getBankAcctListInfo(BankAcctDTO bankAcctDTO);
	
	/**
	 * @Title: findBankAcctDetailInfo 
	 * @Description: 查询特定提现银行账号详情 
	 * @return Result    返回类型 
	 * @throws
	 */
	Result findBankAcctDetailInfo(BankAcctDTO bankAcctDTO);
}
