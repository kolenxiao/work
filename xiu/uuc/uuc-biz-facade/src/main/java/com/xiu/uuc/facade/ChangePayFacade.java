package com.xiu.uuc.facade;

import com.xiu.commons.core.IServiceStatusHealthyChecking;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.TractQueryDTO;
import com.xiu.uuc.facade.dto.TradeDTO;

/**
 * 
 * @ClassName: ChangePayFacade 
 * @Description: 主要用来协助订单中心-换货支付优化，而提供相关接口
 *               当换货的时候，若当前用户04账目不存在则新增一条04账目，若存在则更新04账目，两种情况下都需要写入账流水  
 *               当支付的时候，若当前用户04账目总余额够当次支付的时候，进行04账目出账操作，同时写出账流水
 * @author menglei
 * @date Feb 23, 2012 9:36:55 AM 
 */
public interface ChangePayFacade extends IServiceStatusHealthyChecking {

	/**
	 * @Title: incomeAccounts 
	 * @Description:  退货退款入帐接口
	 *                当换货的时候，若当前用户04账目不存在则新增一条04账目，若存在则更新04账目总余额，两种情况下都需要写入账流水  
	 * @return Result    返回类型 
	 * @throws
	 */
	Result incomeAccounts(TradeDTO tradeDTO);
	
	/**
	 * @Title: outcomeAccounts 
	 * @Description:  换货支付出帐接口
	 *                当支付的时候，若当前用户04账目总余额够当次支付的时候，进行04账目出账操作，同时写出账流水 
	 * @return Result    返回类型 
	 * @throws
	 */
	Result outcomeAccounts(TradeDTO tradeDTO);
	
	/**
	 * @Title: getChangeRefundDetailList 
	 * @Description:  查询老订单入虚拟帐户明细接口
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getTractRefundDetailList(TractQueryDTO tractQueryDTO);
	
	/**
	 * @Title: getChangeRefundNotEndDetailList 
	 * @Description:  查询未完结退款列表接口
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getTractRefundNotEndDetailList(TractQueryDTO tractQueryDTO);
	
}
