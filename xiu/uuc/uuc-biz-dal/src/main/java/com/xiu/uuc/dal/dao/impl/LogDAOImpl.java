package com.xiu.uuc.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import com.xiu.uuc.dal.dao.LogDAO;
import com.xiu.uuc.facade.dto.BusiLogDTO;
import com.xiu.uuc.facade.dto.BusiLogQueryDTO;
import com.xiu.uuc.facade.dto.BusiTypeDTO;

public class LogDAOImpl extends SqlSessionDaoSupport implements LogDAO {

	@Override
	public void insertBusiLog(BusiLogDTO busiLogDTO) throws DataAccessException {
		this.getSqlSession().insert("log.uuc_busi_log_insert", busiLogDTO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiLogDTO> queryBusiLogList(BusiLogQueryDTO busiLogQueryDTO) throws DataAccessException {
		List<BusiLogDTO> list = this.getSqlSession().selectList("log.uuc_busi_log_list_query", busiLogQueryDTO);
		if(null == list){
			list = new ArrayList<BusiLogDTO>();
		}
		return list;
	}

	@Override
	public Long countBusiLogList(BusiLogQueryDTO busiLogQueryDTO) throws DataAccessException {
		Long count = (Long)this.getSqlSession().selectOne("log.uuc_busi_log_list_count", busiLogQueryDTO);
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusiTypeDTO> queryBusiTypeList(BusiTypeDTO busiTypeDTO) throws DataAccessException {
		List<BusiTypeDTO> list = this.getSqlSession().selectList("log.uuc_busi_type_query", busiTypeDTO);
		if(null == list){
			list = new ArrayList<BusiTypeDTO>();
		}
		return list;
	}
}
