package com.xiu.uuc.dal.dao.impl;

import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import com.xiu.uuc.common.util.StringUtils;
import com.xiu.uuc.dal.dao.AssetsManageDAO;
import com.xiu.uuc.dal.dao.CommonDAO;
import com.xiu.uuc.dal.param.AcctChangeExtParam;
import com.xiu.uuc.dal.param.AcctChangeParam;
import com.xiu.uuc.dal.param.AcctItemParam;
import com.xiu.uuc.dal.param.IntegeralRuleParam;
import com.xiu.uuc.dal.param.UserAcctParam;
import com.xiu.uuc.dal.param.VirtualAcctItemParam;
import com.xiu.uuc.dal.po.AcctChangeExtPO;
import com.xiu.uuc.dal.po.AcctChangePO;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.dal.po.IntegeralRulePO;
import com.xiu.uuc.dal.po.UserAcctPO;
import com.xiu.uuc.dal.po.VirtualAcctItemPO;

/**
 * @ClassName: AssetsManageDAOImpl 
 * @Description: 资产管理数据访问对象DAO实现类
 * @author menglei
 * @date Jul 19, 2011 2:59:15 PM 
 */
public class AssetsManageDAOImpl extends SqlSessionDaoSupport implements AssetsManageDAO {
	
	public Integer updateAcctItem(AcctItemPO acctItemPO) throws DataAccessException{
		return (Integer)getSqlSession().update("uuc_acct_item_update", acctItemPO);
	}
	
	@SuppressWarnings("unchecked")
	public List<AcctItemPO> listAcctItem(AcctItemParam acctItemParam) throws DataAccessException{
		return (List<AcctItemPO>)getSqlSession().selectList("uuc_acct_item_list", acctItemParam);
	}
	
	public AcctItemPO findAcctItem(AcctItemParam acctItemParam){
		return (AcctItemPO)getSqlSession().selectOne("uuc_acct_item_find", acctItemParam);
	}
	
	public Integer listAcctItemCount(AcctItemParam acctItemParam)throws DataAccessException {
		return (Integer)getSqlSession().selectOne("uuc_acct_item_count", acctItemParam);
	}
	
	public Long insertAcctChange(AcctChangePO acctChangePO) throws DataAccessException{
		Long acctChangeId = getCommonDAO().getSequenceByName("SEQ_X_UUC_ACCT_CHANGE_ID");
		acctChangePO.setAccountChangeId(acctChangeId);
		if(StringUtils.isNullObject(acctChangePO.getIoAmount())){
			acctChangePO.setIoAmount(0L);
		}
		if(StringUtils.isNullObject(acctChangePO.getHistoryIoAmount())){
			acctChangePO.setHistoryIoAmount(0L);
		}
		if(StringUtils.isNullObject(acctChangePO.getLastIoAmount())){
			acctChangePO.setLastIoAmount(0L);
		}
		getSqlSession().insert("uuc_acct_change_insert", acctChangePO);
    	return acctChangeId;
	}
	
	@SuppressWarnings("unchecked")
	public List<AcctChangePO> listAcctChange(AcctChangeParam acctChangeParam) throws DataAccessException{
		return (List<AcctChangePO>)getSqlSession().selectList("uuc_acct_change_list", acctChangeParam);
	}
	
	@SuppressWarnings("unchecked")
	public List<AcctChangeExtPO> listAcctChangeExt(AcctChangeExtParam acctChangeExtParam) throws DataAccessException{
		return (List<AcctChangeExtPO>)getSqlSession().selectList("uuc_acct_change_list_ext", acctChangeExtParam);
	}
	
	public Integer listAcctChangeCount(AcctChangeParam acctChangeParam) throws DataAccessException{
		return (Integer)getSqlSession().selectOne("uuc_acct_change_count", acctChangeParam);
	}
	
	public Integer listAcctChangeCountExt(AcctChangeExtParam acctChangeExtParam) throws DataAccessException{
		return (Integer)getSqlSession().selectOne("uuc_acct_change_count_ext", acctChangeExtParam);
	}
	
	public Integer updateUserAcct(UserAcctPO userAcctPO) throws DataAccessException{
		return (Integer)getSqlSession().update("uuc_user_acct_update", userAcctPO);
	}

	@SuppressWarnings("unchecked")
	public UserAcctPO findUserAcct(UserAcctParam userAcctParam) throws DataAccessException {
		return (UserAcctPO)getSqlSession().selectOne("uuc_user_acct_list", userAcctParam);
	}
	
	public IntegeralRulePO findIntegeralRule(IntegeralRuleParam integeralRuleParam) throws DataAccessException{
		return (IntegeralRulePO)getSqlSession().selectOne("uuc_integeral_rule_list", integeralRuleParam);
	}
	
	@SuppressWarnings("unchecked")
	public List<VirtualAcctItemPO> getVirtualAccountInfo(VirtualAcctItemParam virtualAcctItemParam) throws DataAccessException {
		return (List<VirtualAcctItemPO>)getSqlSession().selectList("uuc_acct_item_virtual_list", virtualAcctItemParam);
	}
	
	public Integer getVirtualAccountInfoCount(VirtualAcctItemParam virtualAcctItemParam)throws DataAccessException {
		return (Integer)getSqlSession().selectOne("uuc_acct_item_virtual_count", virtualAcctItemParam);
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
