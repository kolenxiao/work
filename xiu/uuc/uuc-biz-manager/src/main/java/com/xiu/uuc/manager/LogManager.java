package com.xiu.uuc.manager;

import java.util.List;

import com.xiu.uuc.dal.po.AcctChangePO;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.facade.dto.BusiLogQueryDTO;
import com.xiu.uuc.facade.dto.BusiTypeDTO;
import com.xiu.uuc.facade.dto.Result;

public interface LogManager {
	
	
	/**
	 * 查询业务操作日志列表
	 * @Title: queryBusiLogList 
	 * @param   
	 * @return RmResult    返回类型 
	 * @throws
	 */
	public Result queryBusiLogList(BusiLogQueryDTO busiLogQueryDTO);
		
	
	/**
	 * 查询业务操作类型列表
	 * @Title: queryBusiTypeList 
	 * @param   
	 * @return List<BusiTypeDTO>    返回类型 
	 * @throws
	 */
	public List<BusiTypeDTO> queryBusiTypeList(BusiTypeDTO busiTypeDTO);
	
	
	/**
	 * @throws Exception 
	 * 虚拟账户新增写表业务日志
	 * @Title: assetsManagerWriteLog 
	 * @param   
	 * @return void    返回类型 
	 * @throws
	 */
	public void assetsManagerWriteLog(AcctItemPO acctItemPO, AcctChangePO acctChangePO) throws Exception;

}
