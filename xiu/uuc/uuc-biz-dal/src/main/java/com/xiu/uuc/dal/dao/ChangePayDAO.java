package com.xiu.uuc.dal.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.xiu.uuc.dal.param.TractParam;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.dal.po.TractPO;

public interface ChangePayDAO {
	
	/**
     * @Title: insertAcctItemPO 
     * @Description: 新增账目信息
     * @return Long    返回类型 
     * @throws
     */
	public Long insertAcctItemPO(AcctItemPO acctItemPO) throws DataAccessException;
	
	/**
     * @Title: insertTractPO 
     * @Description: 新增账目轨迹信息
     * @return Long    返回类型 
     * @throws
     */
	public Long insertTractPO(TractPO tractPO) throws DataAccessException;
	
	/**
	 * @Title: getTractRefundDetailList 
	 * @Description: 查询老订单出入虚拟账户明细 
	 * @return List<TractPO>    返回类型 
	 * @throws
	 */
	public List<TractPO> getTractRefundDetailList(TractParam tractParam) throws DataAccessException ;
	
	/**
	 * @Title: getTractRefundDetailList 
	 * @Description: 查询老订单出入虚拟账户明细统计查询 
	 * @return Integer    返回类型 
	 * @throws
	 */
	public Integer getTractRefundDetailListCount(TractParam tractParam) throws DataAccessException ;
    
    /**
	 * @Title: getTractRefundNotEndDetailList 
	 * @Description: 查询未完结退款列表接口
	 * @return List<TractPO>    返回类型 
	 * @throws
	 */
    public List<TractPO> getTractRefundNotEndDetailList(TractParam tractParam) throws DataAccessException ;
    
    /**
	 * @Title: getTractRefundNotEndDetailListCount 
	 * @Description: 统计未完结退款列表记录数
	 * @return Integer    返回类型 
	 * @throws
	 */
    public Integer getTractRefundNotEndDetailListCount(TractParam tractParam) throws DataAccessException ;
  
}
