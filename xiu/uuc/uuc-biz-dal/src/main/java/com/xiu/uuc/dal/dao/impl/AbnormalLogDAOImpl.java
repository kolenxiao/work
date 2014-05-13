package com.xiu.uuc.dal.dao.impl;

import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import com.xiu.uuc.dal.dao.AbnormalLogDAO;
import com.xiu.uuc.dal.dao.AssetsManageDAO;
import com.xiu.uuc.dal.dao.CommonDAO;
import com.xiu.uuc.dal.param.AbnormalLogParam;
import com.xiu.uuc.dal.param.AcctChangeParam;
import com.xiu.uuc.dal.param.AcctItemParam;
import com.xiu.uuc.dal.po.AbnormalLogPO;
import com.xiu.uuc.dal.po.AcctChangePO;
import com.xiu.uuc.dal.po.AcctItemPO;

/**
 * @ClassName: AbnormalLogDAOImpl 
 * @Description: 虚拟账户异动日志DAO实现类 
 * @author menglei
 * @date Dec 14, 2011 10:11:20 AM 
 */
public class AbnormalLogDAOImpl extends SqlSessionDaoSupport implements AbnormalLogDAO {

	@Override
	public Long insertAbnormalLog(AbnormalLogPO abnormalLogPO) throws DataAccessException {
		Long abnormalId = commonDAO.getSequenceByName("SEQ_X_UUC_ABNORAML_LOG_ID");
		abnormalLogPO.setAbnormalId(abnormalId);
		getSqlSession().insert("uuc_abnormal_log_insert", abnormalLogPO);
    	return abnormalId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AbnormalLogPO> listAbnormalLog(AbnormalLogParam abnormalLogParam)throws DataAccessException {
		return (List<AbnormalLogPO>)getSqlSession().selectList("uuc_abnormal_log_list", abnormalLogParam);
	}
	
	@Override
	public Integer updateAbnormalLogPO(AbnormalLogPO abnormalLogPO) throws DataAccessException {
		return (Integer)getSqlSession().update("uuc_abnormal_log_update", abnormalLogPO);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AcctItemPO> getlistAcctItemAudit(AcctItemParam acctItemParam) throws DataAccessException{
		return (List<AcctItemPO>)getSqlSession().selectList("uuc_acct_item_list_audit", acctItemParam);
	}
	
	@Override
	public AbnormalLogPO getChangeSumAudit(AbnormalLogParam abnormalLogParam) throws DataAccessException{
	    return (AbnormalLogPO)getSqlSession().selectOne("uuc_acct_change_sum_audit", abnormalLogParam); 
	}
	
	@Override
	public AcctChangePO getLastAcctChangePO(AcctChangeParam acctChangeParam) throws DataAccessException{
		List<AcctChangePO> acctChangePOList = assetsManageDAO.listAcctChange(acctChangeParam);
		return (acctChangePOList != null && acctChangePOList.size() > 0) ? acctChangePOList.get(0): null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AbnormalLogPO> getNotBalanceAlarmInfoList(AbnormalLogParam abnormalLogParam) throws DataAccessException{
		return (List<AbnormalLogPO>)getSqlSession().selectList("uuc_abnormal_log_list_not_balance", abnormalLogParam);
	}
	
	@Autowired
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	@Autowired
	public void setAssetsManageDAO(AssetsManageDAO assetsManageDAO) {
		this.assetsManageDAO = assetsManageDAO;
	}
	
	private CommonDAO commonDAO;
	private AssetsManageDAO assetsManageDAO;
}
