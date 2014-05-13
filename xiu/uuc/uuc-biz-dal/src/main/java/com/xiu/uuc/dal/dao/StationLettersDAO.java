package com.xiu.uuc.dal.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xiu.uuc.dal.param.StationLettersParameter;
import com.xiu.uuc.dal.po.StationLettersPO;

public interface StationLettersDAO {
	
	/**
	 * 功能：发送信件
	 * @param stationLettersPO 实体对象
	 * @return StationLettersPO对象
	 */
	public StationLettersPO sendLetters(StationLettersPO stationLettersPO) throws DataAccessException;
	
	/**
	 * 功能：删除信件
	 * @param lettersId 信件ID
	 */
	public void deleteLetters(String lettersId) throws DataAccessException;
	
	/**
	 * 功能：查询单封信件
	 * @param StationLettersParameter参数对象
	 * @return StationLettersPO对象
	 */
	public StationLettersPO findLetters(StationLettersParameter stationLettersParameter) throws DataAccessException;
	
	/**
	 * 功能：获取所有信件
	 * @param StationLettersParameter参数对象
	 * @return StationLettersPO对象列表
	 */
	public List<StationLettersPO> lettersList(StationLettersParameter stationLettersParameter) throws DataAccessException;
	
	/**
	 * 功能：获取未读信件列表
	 * @param StationLettersParameter参数对象
	 * @return StationLettersPO对象列表
	 */
	public List<StationLettersPO> noRead(StationLettersParameter stationLettersParameter) throws DataAccessException;
	
	/**
	 * 功能：获取已经阅读过的信件列表
	 * @param StationLettersParameter参数对象
	 * @return StationLettersPO对象列表
	 */
	public List<StationLettersPO> read(StationLettersParameter stationLettersParameter) throws DataAccessException;
	
	/**
	 * 查询没有阅读信件的条数
	 * @Title: noReadSize 
	 * @return Result    返回类型 
	 */
	public int noReadSize(String customerId) throws DataAccessException;
	
	/**
	 * 已经阅读的信件列表
	 * 
	 * @Title: read 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return int    返回类型 
	 * @throws
	 */
	public int readSize(String customerId) throws DataAccessException;
	
	/**
	 * 已经阅读的信件列表
	 * 
	 * @Title: read 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return int    返回类型 
	 * @throws
	 */
	public int allSize(String customerId) throws DataAccessException;
	
	/**
	 * 功能：发送信件
	 * @param stationLettersPO 实体对象
	 * @return StationLettersPO对象
	 */
	public void update(StationLettersPO stationLettersPO) throws DataAccessException;
}
