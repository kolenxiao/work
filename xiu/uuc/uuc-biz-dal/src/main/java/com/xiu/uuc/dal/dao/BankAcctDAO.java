package com.xiu.uuc.dal.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.xiu.uuc.dal.param.BankAcctParam;
import com.xiu.uuc.dal.po.BankAcctPO;

/**
 * @ClassName: BankAcctDAO 
 * @Description: 银行卡账户信息数据访问对象DAO 
 * @author menglei
 * @date Jul 20, 2011 7:30:43 PM 
 */
public interface BankAcctDAO {
	
	/**
     * @Title: insertBankAcct 
     * @Description: 新增银行卡账户信息
     * @return Long    返回类型 
     * @throws
     */
	Long insertBankAcct(BankAcctPO bankAcctPO) throws DataAccessException;
	
	/**
     * @Title: deleteBankAcct 
     * @Description: 删除银行卡账户信息 
     * @return Integer    返回类型 
     * @throws
     */
	Integer deleteBankAcct(BankAcctPO bankAcctPO) throws DataAccessException;
	
	/**
	 * @Title: updateBankAcct 
	 * @Description: 修改银行卡账户信息 
	 * @return Integer    返回类型 
	 * @throws
	 */
	Integer updateBankAcct(BankAcctPO bankAcctPO) throws DataAccessException ;

	/**
	 * @Title: listBankAcct 
	 * @Description: 查询银行卡账户信息列表 
	 * @return List<AcctItemPO>    返回类型 
	 * @throws
	 */
    List<BankAcctPO> listBankAcct(BankAcctParam bankAcctParam) throws DataAccessException ;
    
    /**
	 * @Title: findBankAcct 
	 * @Description: 查询银行卡账户信息详情 
	 * @return BankAcctPO    返回类型 
	 * @throws
	 */
    BankAcctPO findBankAcct(BankAcctParam bankAcctParam) throws DataAccessException ;
}
