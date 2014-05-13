package com.xiu.uuc.dal.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xiu.uuc.facade.dto.BusiLogDTO;
import com.xiu.uuc.facade.dto.BusiLogQueryDTO;
import com.xiu.uuc.facade.dto.BusiTypeDTO;

/**
 * 业务日志DAO
 * @ClassName: LogDAO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author kolenxiao
 * @date 2011-12-30 上午09:20:16 
 *
 */
public interface LogDAO {
	
	/**
	 * 插入业务操作日志
	 * @Title: insertBusiLog
	 * @param: busiLogDTO
	 * @return void
	 * @throws DataAccessException
	 */
	public void insertBusiLog(BusiLogDTO busiLogDTO) throws DataAccessException;
	
	/**
	 * 查询业务操作日志列表
	 * @Title: queryBusiLogList 
	 * @param   
	 * @return List<BusiLogDTO>    返回类型 
	 * @throws
	 */
	public List<BusiLogDTO> queryBusiLogList(BusiLogQueryDTO busiLogQueryDTO) throws DataAccessException;
	
	public Long countBusiLogList(BusiLogQueryDTO busiLogQueryDTO) throws DataAccessException;
		
	
	/**
	 * 查询业务操作类型列表
	 * @Title: queryBusiTypeList 
	 * @param   
	 * @return List<BusiTypeDTO>    返回类型 
	 * @throws
	 */
	public List<BusiTypeDTO> queryBusiTypeList(BusiTypeDTO busiTypeDTO) throws DataAccessException;

}
