package com.xiu.uuc.facade;

import com.xiu.commons.core.IServiceStatusHealthyChecking;
import com.xiu.uuc.facade.dto.IntegeralRuleDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.VirtualAcctItemDTO;

/**
 * @ClassName: AssetsManageFacade 
 * @Description: 用户账目记录相关的facade接口 供第三方系统统一调用（包括：不可提现，可提现金额以及积分管理）  
 * @author menglei
 * @date Jul 21, 2011 7:58:05 PM 
 */
public interface AcctItemFacade extends IServiceStatusHealthyChecking {
	
	/**
	 * 1.虚拟账户信息查询
	 * 2.积分信息查询
	 * @Title: getVirtualAccountInfo 
	 * @Description: 虚拟帐户信息（账目）包括：可提现总金额,可提现冻结金额,不可提现总金额,不可提现冻结金额,总积分,积分冻结
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getVirtualAccountInfo(VirtualAcctItemDTO virtualAcctItemDTO);
	
	/**
	 * 1.虚拟账户查询（当前用户当前渠道虚拟账户账目信息，这里指金额）
	 * 2.积分查询（当前用户当前渠道积分账目信息，这里指积分）
	 * @Title: getVirtualItemList 
	 * @Description: 虚拟账户查询（当前用户当前渠道虚拟账户账目信息）
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getVirtualItemList(AcctItemDTO acctItemDTO);
	
	/**
	 * @Title: addVirtualAccountMoney 
	 * @Description: 虚拟账户金额充值 使用场景：虚拟账户金额充值
	 *               根据账目类型 可分为可提现01，不提现02，进行金额充值
	 * @return Result    返回类型 
	 * @throws
	 */
	Result addVirtualAccountMoney(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO);
	
	/**
	 * @Title: decVirtualAccountMoney 
	 * @Description: 虚拟账户金额支付 使用场景：虚拟账户金额支付
	 *               虚拟账户金额支付 不区分账目类型，参数为需要支付的总金额（可能包括可提现部分，不可提现部分，以及两者都有三种情况） 
	 * @return Result    返回类型 
	 * @throws
	 */
	Result decVirtualAccountMoney(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO);
	
	/**
	 * @Title: decVirtualAccountMoneyByItemTypeCode 
	 * @Description: 根据账目类型 进行虚拟账户支付
	 *               账目类型:可提现,不可提现,积分（均指支付一定数量）
	 *               说明：这里为了财务账务平衡 暂时没有对金额 或者积分进行是否足够支付判断处理
	 * @return Result    返回类型 
	 * @throws
	 */
	Result decVirtualAccountMoneyByItemTypeCode(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO);
	
	/**
	 * @Title: addVirtualAccountIntegral 
	 * @Description: 虚拟账户积分充值 使用场景：虚拟账户积分充值
	 *               根据账目类型 积分03，进行积分充值
	 * @return Result    返回类型 
	 * @throws
	 */
	Result addVirtualAccountIntegral(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO,IntegeralRuleDTO integeralRuleDTO);
	
	/**
	 * @Title: decVirtualAccountIntegral 
	 * @Description: 虚拟账户积分支付 使用场景：虚拟账户积分支付
	 *               根据账目类型 积分03，进行积分支付
	 * @return Result    返回类型 
	 * @throws
	 */
	Result decVirtualAccountIntegral(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO,IntegeralRuleDTO integeralRuleDTO);
	
	/**
	 * @Title: getIntegeralByRule 
	 * @Description: 根据积分规则获取积分数量
	 * @return Result   
	 * @throws
	 */
	Result getIntegeralByRule(IntegeralRuleDTO integeralRuleDTO);
}
