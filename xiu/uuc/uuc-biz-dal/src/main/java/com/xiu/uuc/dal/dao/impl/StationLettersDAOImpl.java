package com.xiu.uuc.dal.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import com.xiu.uuc.dal.dao.StationLettersDAO;
import com.xiu.uuc.dal.param.StationLettersParameter;
import com.xiu.uuc.dal.po.StationLettersPO;

public class StationLettersDAOImpl extends SqlSessionDaoSupport implements StationLettersDAO {

	@Override
	public StationLettersPO sendLetters(StationLettersPO stationLettersPO) throws DataAccessException {
		
		return (StationLettersPO)this.getSqlSession().selectOne("uuc_stationLetters_insert",stationLettersPO);
	}

	@Override
	public void deleteLetters(String lettersId) throws DataAccessException {
		this.getSqlSession().delete("uuc_stationLetters_delete",lettersId);
	}

	@Override
	public StationLettersPO findLetters(StationLettersParameter stationLettersParameter) 
		throws DataAccessException {
		
		return (StationLettersPO)this.getSqlSession().selectOne("one_stationLetters", stationLettersParameter);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StationLettersPO> lettersList(StationLettersParameter stationLettersParameter) 
		throws DataAccessException {
		
		return this.getSqlSession().selectList("uuc_stationLetters_List", stationLettersParameter);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StationLettersPO> noRead(StationLettersParameter stationLettersParameter) 
		throws DataAccessException {
		
		return this.getSqlSession().selectList("uuc_stationLetters_unreaList", stationLettersParameter);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StationLettersPO> read(StationLettersParameter stationLettersParameter) 
		throws DataAccessException {

		return this.getSqlSession().selectList("uuc_stationLetters_readList", stationLettersParameter);
	}
	
	@Override
	public int noReadSize(String customerId) throws DataAccessException {

		return (Integer)this.getSqlSession().selectOne("uuc_stationLetters_noReadSize", customerId);
	}
	
	@Override
	public int readSize(String customerId) throws DataAccessException {

		return (Integer)this.getSqlSession().selectOne("uuc_stationLetters_readSize", customerId);
	}
	
	@Override
	public int allSize(String customerId) throws DataAccessException {

		return (Integer)this.getSqlSession().selectOne("uuc_stationLetters_allSize", customerId);
	}
	
	@Override
	public void update(StationLettersPO stationLettersPO) throws DataAccessException {
		this.getSqlSession().update("uuc_update_letters", stationLettersPO);
	}
}
