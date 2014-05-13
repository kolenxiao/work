package com.xiu.uuc.manager;

import com.xiu.uuc.facade.dto.BankAcctDTO;
import com.xiu.uuc.facade.dto.Result;

/**
 * @ClassName: BankAcctManager 
 * @Description: 银行卡账户管理流程控制层
 * @author menglei
 * @date Jul 20, 2011 8:05:02 PM 
 */
public interface BankAcctManager {
	
	/**
     * @Title: insertBankAcct 
     * @Description: 新增银行卡账户信息
     * @return Result    返回类型 
     * @throws
     */
	Result insertBankAcct(BankAcctDTO bankAcctDTO)throws Exception;
	
	/**
     * @Title: deleteBankAcct 
     * @Description: 删除银行卡账户信息 
     * @return Result    返回类型 
     * @throws
     */
	Result deleteBankAcct(BankAcctDTO bankAcctDTO)throws Exception;
	
	/**
	 * @Title: updateBankAcct 
	 * @Description: 修改银行卡账户信息 
	 * @return Result    返回类型 
	 * @throws
	 */
	Result updateBankAcct(BankAcctDTO bankAcctDTO)throws Exception;

	/**
	 * @Title: listBankAcct 
	 * @Description: 查询银行卡账户信息列表
	 * @return Result    返回类型 
	 * @throws
	 */
	Result listBankAcct(BankAcctDTO bankAcctDTO)throws Exception;
	
	/**
	 * @Title: findBankAcct 
	 * @Description: 查询银行卡账户信息详情
	 * @return Result    返回类型 
	 * @throws
	 */
	Result findBankAcct(BankAcctDTO bankAcctDTO)throws Exception;
}
