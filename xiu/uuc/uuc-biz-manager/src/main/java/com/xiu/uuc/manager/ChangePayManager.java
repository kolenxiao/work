package com.xiu.uuc.manager;

import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.TractQueryDTO;
import com.xiu.uuc.facade.dto.TradeDTO;

/** 
 * @ClassName: ChangePayManager 
 * @Description: 换货支付管理流程控制层
 * @author menglei
 * @DATE   :Mar 26, 2012 9:30:14 AM             
 */
public interface ChangePayManager {
	
	/**
	 * @Title: incomeAccounts 
	 * @Description:  退货退款入帐接口
	 *                当换货的时候，若当前用户04账目不存在则新增一条04账目，若存在则更新04账目总余额，两种情况下都需要写入账流水  
	 * @return Result    返回类型 
	 * @throws
	 */
	Result incomeAccounts(TradeDTO tradeDTO) throws Exception;

    /**
	 * @Title: outcomeAccounts 
	 * @Description:  换货支付出帐接口
	 *                当支付的时候，若当前用户04账目总余额够当次支付的时候，进行04账目出账操作，同时写出账流水 
	 * @return Result    返回类型 
	 * @throws
	 */
	Result outcomeAccounts(TradeDTO tradeDTO) throws Exception;
	
    /**
	 * @Title: getChangeRefundDetailList 
	 * @Description:  获取老订单 各种支付方式的轨迹余额
	 *                例如： 订单ID为A, 虚拟账户：20元  招商银行：30元
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getTractRefundDetailList(TractQueryDTO tractQueryDTO) throws Exception ;

    /**
	 * @Title: getChangeRefundNotEndDetailList 
	 * @Description:  查询未完结退款列表接口(这里有时间段和余额大于0)
	 *                例如： 订单ID为A, 虚拟账户：20元  招商银行：30元
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getTractRefundNotEndDetailList(TractQueryDTO tractQueryDTO) throws Exception ;

}
