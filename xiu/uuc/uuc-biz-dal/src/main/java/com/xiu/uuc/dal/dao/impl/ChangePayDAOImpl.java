package com.xiu.uuc.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import com.xiu.uuc.dal.dao.ChangePayDAO;
import com.xiu.uuc.dal.dao.CommonDAO;
import com.xiu.uuc.dal.param.TractParam;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.dal.po.TractPO;

public class ChangePayDAOImpl extends SqlSessionDaoSupport implements ChangePayDAO {
	
	@Override
	public Long insertAcctItemPO(AcctItemPO acctItemPO) throws DataAccessException {
		Long acctItemId = commonDAO.getSequenceByName("SEQ_X_UUC_ACCT_ITEM_ID");
		acctItemPO.setAcctItemId(acctItemId);
		this.getSqlSession().insert("uuc_acct_item_tract_insert", acctItemPO);
		return acctItemId;
	}

	@Override
	public Long insertTractPO(TractPO tractPO) throws DataAccessException {
		Long acctTractId = commonDAO.getSequenceByName("SEQ_X_UUC_ACCT_TRACT_ID");
		tractPO.setAcctTractId(acctTractId);
		getSqlSession().insert("uuc_acct_tract_insert", tractPO);
    	return acctTractId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TractPO> getTractRefundDetailList(TractParam tractParam)throws DataAccessException {
		List<TractPO> tractPOList = (List<TractPO>) getSqlSession().selectList("uuc_acct_tract_refund_detail_list", tractParam);
		return (tractPOList != null) ? tractPOList : new ArrayList<TractPO>();
	}
	
	@Override
	public Integer getTractRefundDetailListCount(TractParam tractParam)throws DataAccessException {
		return (Integer)getSqlSession().selectOne("uuc_acct_tract_refund_detail_list_count", tractParam);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TractPO> getTractRefundNotEndDetailList(TractParam tractParam) throws DataAccessException {
		List<TractPO> tractPOList = (List<TractPO>) getSqlSession().selectList("uuc_acct_tract_refund_not_end_detail_list", tractParam);
		return (tractPOList != null) ? tractPOList : new ArrayList<TractPO>();
	}
	
	@Override
	public Integer getTractRefundNotEndDetailListCount(TractParam tractParam)throws DataAccessException {
		return (Integer)getSqlSession().selectOne("uuc_acct_tract_refund_not_end_detail_count", tractParam);
	}
	
	@Autowired
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	private CommonDAO commonDAO;

}
