package com.xiu.uuc.dal.dao;

import org.springframework.dao.DataAccessException;

import com.xiu.uuc.dal.po.BusiLogPO;
import com.xiu.uuc.dal.po.InterfaceLogPO;

/**
 * 公用方法DAO
 * @ClassName: CommonDAO 
 * @author xiaoyingping
 * @date 2011-7-19 上午11:19:55 
 *
 */
public interface CommonDAO {

	/**
	 * 通过sequenceName生成一个sequence值
	 * @Title: getSequenceByName
	 * @param: sequenceName 序列名
	 * @return Long    返回类型 
	 * @throws DataAccessException
	 */
	public Long getSequenceByName(String sequenceName) throws DataAccessException;
	

	/**
	 * 插入业务操作日志
	 * @Title: insertBusiLog
	 * @param: busiLogPO
	 * @return Long 返回类型
	 * @throws DataAccessException
	 */
	public Long insertBusiLog(BusiLogPO busiLogPO) throws DataAccessException;
	

	/**
	 * 插入接口日志
	 * @Title: insertInterfaceLog
	 * @param: interfaceLogPO
	 * @return Long 返回类型
	 * @throws DataAccessException
	 */
	public Long insertInterfaceLog(InterfaceLogPO interfaceLogPO) throws DataAccessException;

}
