package com.xiu.uuc.core.service;

import java.util.List;

import com.xiu.uuc.facade.dto.BusiLogDTO;
import com.xiu.uuc.facade.dto.BusiLogQueryDTO;
import com.xiu.uuc.facade.dto.BusiTypeDTO;


public interface LogService {
	

	/**
	 * 插入业务操作日志
	 * @Title: insertBusiLog
	 * @param: busiLogDTO
	 * @return void
	 * @throws Exception 
	 */
	public void insertBusiLog(BusiLogDTO busiLogDTO);
	
	/**
	 * 查询业务操作日志列表
	 * @Title: queryBusiLogList 
	 * @param   
	 * @return List<BusiLogDTO>    返回类型 
	 * @throws
	 */
	public List<BusiLogDTO> queryBusiLogList(BusiLogQueryDTO busiLogQueryDTO);
	
	public Long countBusiLogList(BusiLogQueryDTO busiLogQueryDTO);
		
	
	/**
	 * 查询业务操作类型列表
	 * @Title: queryBusiTypeList 
	 * @param   
	 * @return List<BusiTypeDTO>    返回类型 
	 * @throws
	 */
	public List<BusiTypeDTO> queryBusiTypeList(BusiTypeDTO busiTypeDTO);

}
