package com.xiu.uuc.dal.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import com.xiu.uuc.dal.dao.CommonDAO;
import com.xiu.uuc.dal.po.BusiLogPO;
import com.xiu.uuc.dal.po.InterfaceLogPO;

public class CommonDAOImpl extends SqlSessionDaoSupport implements CommonDAO {

	@Override
	public Long getSequenceByName(String sequenceName) throws DataAccessException {
		Long seq = (Long) this.getSqlSession().selectOne("common.uuc_get_sequence_by_name", sequenceName + ".Nextval");
		return seq;
	}

	@Override
	public Long insertBusiLog(BusiLogPO busiLogPO) throws DataAccessException {
		Long logId = this.getSequenceByName("SEQ_X_UUC_BUSI_LOG_ID");
		busiLogPO.setLogId(logId);
		this.getSqlSession().insert("common.uuc_busi_log_insert", busiLogPO);
		return logId;
	}

	@Override
	public Long insertInterfaceLog(InterfaceLogPO interfaceLogPO) throws DataAccessException {
		Long logId = this.getSequenceByName("SEQ_X_UUC_INTERFACE_LOG_ID");
		interfaceLogPO.setLogId(logId);
		this.getSqlSession().insert("uuc_interface_log_insert", interfaceLogPO);
		return logId;
	}

}
