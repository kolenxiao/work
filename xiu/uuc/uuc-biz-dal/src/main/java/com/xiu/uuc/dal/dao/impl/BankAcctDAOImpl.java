package com.xiu.uuc.dal.dao.impl;

import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import com.xiu.uuc.dal.dao.BankAcctDAO;
import com.xiu.uuc.dal.dao.CommonDAO;
import com.xiu.uuc.dal.param.BankAcctParam;
import com.xiu.uuc.dal.po.BankAcctPO;

/**
 * @ClassName: BankAcctDAOImpl 
 * @Description: 银行卡账户信息数据访问对象DAO实现类 
 * @author menglei
 * @date Jul 20, 2011 7:33:18 PM 
 */
public class BankAcctDAOImpl extends SqlSessionDaoSupport implements BankAcctDAO {
	
	@Override
	public Long insertBankAcct(BankAcctPO bankAcctPO)throws DataAccessException {
		Long bankAcctId = getCommonDAO().getSequenceByName("SEQ_X_UUC_BANK_ACCT_ID");
		bankAcctPO.setBankAcctId(bankAcctId);
		getSqlSession().insert("uuc_bank_acct_insert", bankAcctPO);
    	return bankAcctId;
	}

	@Override
	public Integer deleteBankAcct(BankAcctPO bankAcctPO)throws DataAccessException {
		return (Integer)getSqlSession().delete("uuc_bank_acct_delete", bankAcctPO);
	}

	@Override
	public Integer updateBankAcct(BankAcctPO bankAcctPO)throws DataAccessException {
		return (Integer)getSqlSession().update("uuc_bank_acct_update", bankAcctPO);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BankAcctPO> listBankAcct(BankAcctParam bankAcctParam)throws DataAccessException {
		return (List<BankAcctPO>)getSqlSession().selectList("uuc_bank_acct_list", bankAcctParam);	
	}
	
	@Override
	public BankAcctPO findBankAcct(BankAcctParam bankAcctParam)throws DataAccessException {
		return (BankAcctPO)getSqlSession().selectOne("uuc_bank_acct_list", bankAcctParam);
	}
	
	public CommonDAO getCommonDAO() {
		return commonDAO;
	}
	
	@Autowired
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	private CommonDAO commonDAO;
}
