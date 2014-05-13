package com.xiu.uuc.facade;

import com.xiu.commons.core.IServiceStatusHealthyChecking;
import com.xiu.uuc.facade.dto.AcctChangeExtDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.UserAcctDTO;

/**
 * @ClassName: AssetsManageFacade 
 * @Description: 用户账目变更历史记录相关的facade接口 供第三方系统统一调用（包括：不可提现，可提现金额以及积分管理）  
 * @author menglei
 * @date Jul 21, 2011 6:58:05 PM 
 */
public interface AcctChangeFacade extends IServiceStatusHealthyChecking{
	
	/**
	 * @Title: checkIsFreezeUserAcct 
	 * @Description: 校验当前用户账户是否被冻结 01表示账户正常 02账户已经冻结 
	 * @return Result    返回类型 
	 * @throws
	 */
	Result checkIsFreezeUserAcct(UserAcctDTO userAcctDTO);
	
	/**
	 * 1.虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
	 * 2.积分明细查询（当前用户当前渠道积分账目变更信息，这里指积分）
	 * @Title: getVirtualChangeList 
	 * @Description: 虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getVirtualChangeList(AcctChangeDTO acctChangeDTO);
	
	/**
	 * 1.虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
	 * 2.积分明细查询（当前用户当前渠道积分账目变更信息，这里指积分）
	 * 主要为了解决1.5系统中订单号含有字母的情况
     * 将rltId（订单id或者退换货id）数据类型由Long---》String
     * Bug 7732 - 查询账户变动列表记录接口
	 * @Title: getVirtualChangeList 
	 * @Description: 虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getVirtualChangeListExt(AcctChangeExtDTO acctChangeExtDTO);
	
	/**
	 * @Title: setUserAcctFreeze 
	 * @Description: 设置用户账户冻结
	 * @return Result    返回类型 
	 * @throws
	 */
	Result setUserAcctFreeze(UserAcctDTO userAcctDTO);
	
	/**
	 * @Title: setUserAcctUnFreeze 
	 * @Description: 设置用户账户解冻
	 * @return Result    返回类型 
	 * @throws
	 */
	Result setUserAcctUnFreeze(UserAcctDTO userAcctDTO);
	
	/**
	 * @Title: setUserAcctItemFreezeMoney 
	 * @Description: 修改当前账目冻结数量(主要是可提现，不可提现账目冻结金额缺省为0，积分)
	 *               对于冻结数量 进行减操作时：包括 不是仅仅对冻结数量做清零操作，仅仅对冻结数量做清零操作
	 * @return Result    返回类型 
	 * @throws
	 */
	Result setUserAcctItemFreezeMoney(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO) ;
	
	/**
	 * @Title: getUserOnlineDrawBackList 
	 * @Description: 查询用户虚拟账户的在线退款列表记录(支持分页)
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getUserOnlineDrawBackList(AcctChangeDTO acctChangeDTO);
	
	/**
	 * @Title: getUserAcctChangeList 
	 * @Description: 查询用户虚拟账户的账户变动列表记录(支持分页)
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getUserAcctChangeList(AcctChangeDTO acctChangeDTO);
	
	/**
	 * @Title: checkIsEnoughApplyDrawAmount 
	 * @Description: 当前用户可提现可用金额（包括可提现可用）>=申请提现 ？ true:false 或者
	 *               当前用户不可提现可用金额（包括不可提现可用）>=申请提现 ？ true:false 或者
	 *               当前用户可用金额（包括可提现可用+不可提现可用）>=申请提现 ？ true:false 或者
	 *               当前用户可用积分（包括总积分-冻结积分）>=申请提现 ？ true:false 
	 * @return Result    返回类型 
	 * @throws
	 */
	Result checkIsEnoughApplyDrawAmount(AcctItemDTO acctItemDTO,String operatorFlag);
}
