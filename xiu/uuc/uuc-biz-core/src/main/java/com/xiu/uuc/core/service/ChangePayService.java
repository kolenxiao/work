package com.xiu.uuc.core.service;

import java.util.List;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.dal.param.TractParam;
import com.xiu.uuc.dal.po.AcctChangePO;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.dal.po.TractPO;
import com.xiu.uuc.dal.po.UserAcctPO;

public interface ChangePayService {
	
	/**
	 * @Title: getAcctItemPoByUserIdAndAcctTypeCode 
	 * @Description:  根据用户id和账目类型获取账目对象
	 * @return AcctItemPO    返回类型 
	 * @throws
	 */
	public AcctItemPO getAcctItemPoByUserIdAndAcctTypeCode(Long userId,String acctTypeCode)throws BusinessException;

	/**
	 * @Title: getUserAcctPOByUserId 
	 * @Description:  根据用户id获取账户对象
	 * @return UserAcctPO    返回类型 
	 * @throws
	 */
	public UserAcctPO getUserAcctPOByUserId(Long userId)throws BusinessException;
	
	/**
	 * @Title: insertAcctItemInfo 
	 * @Description:  新增账目信息
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long insertAcctItemPO(AcctItemPO acctItemPO)throws BusinessException;
	
	/**
	 * @Title: updateAcctItem 
	 * @Description:  更新账目信息
	 * @return Integer    返回类型 
	 * @throws
	 */
	public Integer updateAcctItem(AcctItemPO acctItemPO)throws BusinessException;
	
	/**
	 * @Title: insertAcctChange 
	 * @Description:  插入账目流水
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long insertAcctChange(AcctChangePO acctChangePO)throws BusinessException;
	
	/**
	 * @Title: insertAcctTract 
	 * @Description:  插入账目变更轨迹
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long insertAcctTract(TractPO tractPO)throws BusinessException;
	
	/**
	 * @Title: getTractRefundDetailList 
	 * @Description: 查询老订单出入虚拟账户明细 
	 * @return List<TractPO>    返回类型 
	 * @throws
	 */
	public List<TractPO> getTractRefundDetailList(TractParam tractParam) throws BusinessException ;
	
	/**
	 * @Title: getTractRefundDetailListCount 
	 * @Description: 查询老订单出入虚拟账户明细统计查询 
	 * @return Integer    返回类型 
	 * @throws
	 */
    public Integer getTractRefundDetailListCount(TractParam tractParam) throws BusinessException ;
    
    /**
	 * @Title: getTractRefundNotEndDetailList 
	 * @Description: 查询未完结退款列表 
	 * @return List<TractPO>    返回类型 
	 * @throws
	 */
    public List<TractPO> getTractRefundNotEndDetailList(TractParam tractParam) throws BusinessException ;
    
    /**
	 * @Title: getTractRefundNotEndDetailListCount 
	 * @Description: 统计未完结退款列表记录数
	 * @return Integer    返回类型 
	 * @throws
	 */
    public Integer getTractRefundNotEndDetailListCount(TractParam tractParam) throws BusinessException ;
	
}
