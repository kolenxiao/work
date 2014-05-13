package com.xiu.uuc.manager;

import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctChangeExtDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.IntegeralRuleDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserAcctDTO;
import com.xiu.uuc.facade.dto.VirtualAcctItemDTO;

/**
 * @ClassName: AssetsManageManager 
 * @Description: 资产管理业务流程控制层 
 * @author menglei
 * @date Jul 19, 2011 3:04:08 PM 
 */
public interface AssetsManager {
	
	/**
	 * 1.虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
	 * 2.积分明细查询（当前用户当前渠道积分账目变更信息，这里指积分）
	 * @Title: getAcctChangeDetailList 
	 * @Description: 虚拟账户积分变更明细查询
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getAcctChangeDetailList(AcctChangeDTO acctChangeDTO)throws Exception;
	
	/**
	 * 1.虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
	 * 2.积分明细查询（当前用户当前渠道积分账目变更信息，这里指积分）
	 * 主要为了解决1.5系统中订单号含有字母的情况
     * 将rltId（订单id或者退换货id）数据类型由Long---》String
     * Bug 7732 - 查询账户变动列表记录接口
	 * @Title: getAcctChangeDetailList 
	 * @Description: 虚拟账户积分变更明细查询
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getAcctChangeDetailListExt(AcctChangeExtDTO acctChangeExtDTO)throws Exception;
	
	/**
	 * 1.虚拟账户查询（当前用户当前渠道虚拟账户账目信息，这里指金额）
	 * 2.积分查询（当前用户当前渠道积分账目信息，这里指积分）
	 * @Title: getAcctItemDetailList 
	 * @Description: 虚拟账户积分账目信息查询
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getAcctItemDetailList(AcctItemDTO acctItemDTO)throws Exception;
	
	/**
	 * @Title: setUserAcctFreezeOrUnFreeze 
	 * @Description: 设置当前用户账户冻结或者解冻(账户冻结或者解冻，直接修改冻结标志位)
	 * @return Result   
	 * @throws
	 */
	Result setUserAcctFreezeOrUnFreeze(UserAcctDTO userAcctDTO,String operatorFlag)throws Exception;
	
	/**
	 * @Title: checkIsFreezeByUserAcct 
	 * @Description: 检查当前用户账户是否被冻结 true表示冻结 false表示正常
	 * @return Result   
	 * @throws
	 */
	Result checkIsFreezeByUserAcct(UserAcctDTO userAcctDTO)throws Exception;
	
	/**
	 * @Title: setUserAcctItemFreezeMoney 
	 * @Description: 修改当前账目冻结数量(主要是可提现，不可提现账目冻结金额缺省为0，积分)
	 *               对于冻结数量 进行减操作时：包括 不是仅仅对冻结数量做清零操作，仅仅对冻结数量做清零操作
	 * @return Result    返回类型 
	 * @throws
	 */
	Result setUserAcctItemFreezeMoney(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO) throws Exception;
	
	/**
	 * 1.虚拟账户信息查询
	 * 2.积分信息查询
	 * @Title: getVirtualAccountInfo 
	 * @Description: 虚拟帐户信息（账目）包括：可提现总金额,可提现冻结金额,不可提现总金额,不可提现冻结金额,总积分,积分冻结
	 * @return Result    返回类型 
	 * @throws
	 */
	Result getVirtualAccountInfo(VirtualAcctItemDTO virtualAcctItemDTO)throws Exception;
	
	/**
	 * @Title: modifyVirtualAccountMoneyOrIntegral 
	 * @Description: 增加虚拟账户金额信息（1.根据充值数量对金额充值）或者积分信息（1.根据积分数量进行积分充值 2.根据积分规则对积分充值）
	 * @return Result   
	 * @throws
	 */
	Result modifyVirtualAccountMoneyOrIntegral(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO, 
			IntegeralRuleDTO integeralRuleDTO,String acctItemIoTypeFlag,String operatorFlag)throws Exception;
	
	/**
	 * @Title: decVirtualAccountMoneyByItemTypeCode 
	 * @Description: 根据账目类型 进行虚拟账户支付
	 *               账目类型:可提现,不可提现,积分（均指支付一定数量）
	 * @return Result    返回类型 
	 * @throws
	 */
	Result decVirtualAccountMoneyByItemTypeCode(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO)throws Exception;

	
	/**
	 * @Title: getIntegeralByRule 
	 * @Description: 根据积分规则获取积分数量
	 * @return Result   
	 * @throws
	 */
	Result getIntegeralByRule(IntegeralRuleDTO integeralRuleDTO)throws Exception;
	
	/**
	 * @Title: checkIsEnoughApplyDrawAmount 
	 * @Description: 当前用户可提现可用金额（包括可提现可用）>=申请提现 ？ true:false 或者
	 *               当前用户不可提现可用金额（包括不可提现可用）>=申请提现 ？ true:false 或者
	 *               当前用户可用金额（包括可提现可用+不可提现可用）>=申请提现 ？ true:false 或者
	 *               当前用户可用积分（包括总积分-冻结积分）>=申请提现 ？ true:false 
	 * @return Boolean    返回类型 
	 * @throws
	 */
	Boolean checkIsEnoughApplyDrawAmount(AcctItemDTO acctItemDTO,String operatorFlag)throws Exception;
}
