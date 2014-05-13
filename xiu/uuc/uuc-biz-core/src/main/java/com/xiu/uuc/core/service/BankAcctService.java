package com.xiu.uuc.core.service;

import java.util.List;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.dal.param.BankAcctParam;
import com.xiu.uuc.dal.po.BankAcctPO;

/**
 * @ClassName: BankAcctService 
 * @Description: 银行卡账户管理业务服务层  
 * @author menglei
 * @date Jul 20, 2011 7:50:52 PM 
 */
public interface BankAcctService {
	
	/**
     * @Title: insertBankAcct 
     * @Description: 新增银行卡账户信息
     * @return Long    返回类型 
     * @throws
     */
	Long insertBankAcct(BankAcctPO bankAcctPO) throws BusinessException;
	
	/**
     * @Title: deleteBankAcct 
     * @Description: 删除银行卡账户信息 
     * @return Integer    返回类型 
     * @throws
     */
	Integer deleteBankAcct(BankAcctPO bankAcctPO) throws BusinessException;
	
	/**
	 * @Title: updateBankAcct 
	 * @Description: 修改银行卡账户信息 
	 * @return Integer    返回类型 
	 * @throws
	 */
	Integer updateBankAcct(BankAcctPO bankAcctPO) throws BusinessException ;

	/**
	 * @Title: listBankAcct 
	 * @Description: 查询银行卡账户信息列表
	 * @return List<AcctItemPO>    返回类型 
	 * @throws
	 */
    List<BankAcctPO> listBankAcct(BankAcctParam bankAcctParam) throws BusinessException ;
    
    /**
	 * @Title: findBankAcct 
	 * @Description: 查询银行卡账户信息详情 
	 * @return BankAcctPO    返回类型 
	 * @throws
	 */
    BankAcctPO findBankAcct(BankAcctParam bankAcctParam) throws BusinessException ;
}
