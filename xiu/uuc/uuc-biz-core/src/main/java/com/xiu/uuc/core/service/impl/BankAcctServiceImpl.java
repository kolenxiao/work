package com.xiu.uuc.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.core.service.BankAcctService;
import com.xiu.uuc.dal.dao.BankAcctDAO;
import com.xiu.uuc.dal.param.BankAcctParam;
import com.xiu.uuc.dal.po.BankAcctPO;

/**
 * @ClassName: BankAcctServiceImpl 
 * @Description: 银行卡账户管理业务服务层实现类
 * @author menglei
 * @date Jul 20, 2011 7:56:06 PM 
 */
public class BankAcctServiceImpl implements BankAcctService {

	@Override
	public Long insertBankAcct(BankAcctPO bankAcctPO) throws BusinessException {
		return getBankAcctDAO().insertBankAcct(bankAcctPO);
	}
	
	@Override
	public Integer deleteBankAcct(BankAcctPO bankAcctPO)throws BusinessException {
		return getBankAcctDAO().deleteBankAcct(bankAcctPO);
	}

	@Override
	public Integer updateBankAcct(BankAcctPO bankAcctPO)throws BusinessException {
		return getBankAcctDAO().updateBankAcct(bankAcctPO);
	}
	
	@Override
	public List<BankAcctPO> listBankAcct(BankAcctParam bankAcctParam)throws BusinessException {
		return getBankAcctDAO().listBankAcct(bankAcctParam);
	}
	
	@Override
	public BankAcctPO findBankAcct(BankAcctParam bankAcctParam)throws BusinessException {
		return getBankAcctDAO().findBankAcct(bankAcctParam);
	}
	
	public BankAcctDAO getBankAcctDAO() {
		return bankAcctDAO;
	}

	@Autowired
	public void setBankAcctDAO(BankAcctDAO bankAcctDAO) {
		this.bankAcctDAO = bankAcctDAO;
	}
	
	/**
	 * 银行卡账户信息数据访问对象DAO 
	 */
	private BankAcctDAO bankAcctDAO;
}
