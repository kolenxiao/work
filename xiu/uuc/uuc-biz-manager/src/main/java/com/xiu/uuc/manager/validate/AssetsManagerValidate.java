package com.xiu.uuc.manager.validate;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.CommonValidationUtil;
import com.xiu.uuc.common.util.KeyNames;
import com.xiu.uuc.common.util.StringUtils;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.dal.po.IntegeralRulePO;
import com.xiu.uuc.dal.po.UserAcctPO;
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctChangeExtDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.IntegeralRuleDTO;
import com.xiu.uuc.facade.dto.UserAcctDTO;
import com.xiu.uuc.facade.dto.VirtualAcctItemDTO;
import com.xiu.uuc.facade.util.ErrorCodeConstant;
import com.xiu.uuc.facade.util.TypeEnum;

/**
 * @ClassName: AssetsManagerValidate 
 * @Description: 主要作用是对manager层输入参数DTO，进行合法性校验 
 * @author menglei
 * @date Aug 18, 2011 10:59:03 AM 
 */
public class AssetsManagerValidate {
	
	/**
	 * @Title: validateGetAcctChangeDetailList 
	 * @Description: 虚拟账户积分变更明细查询 输入参数合法性校验 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateGetAcctChangeDetailList(AcctChangeDTO acctChangeDTO){
		if (StringUtils.isNullObject(acctChangeDTO)) {
			throw new ParameterException("输入参数对象acctChangeDTO不允许为空");
		}
	}
	
	/**
	 * @Title: validateGetAcctChangeDetailList 
	 * @Description: 虚拟账户积分变更明细查询 输入参数合法性校验
	 *               主要为了解决1.5系统中订单号含有字母的情况
     *               将rltId（订单id或者退换货id）数据类型由Long---》String
     *               Bug 7732 - 查询账户变动列表记录接口 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateGetAcctChangeDetailListExt(AcctChangeExtDTO acctChangeExtDTO){
		if (StringUtils.isNullObject(acctChangeExtDTO)) {
			throw new ParameterException("输入参数对象acctChangeExtDTO不允许为空");
		}
	}
	
	/**
	 * @Title: validateGetAcctItemDetailList 
	 * @Description: 虚拟账户变更明细查询 输入参数合法性校验 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateGetAcctItemDetailList(AcctItemDTO acctItemDTO){
		if (StringUtils.isNullObject(acctItemDTO)) {
			throw new ParameterException("输入参数对象acctItemDTO不允许为空");
		}
	}
	
	/**
	 * @Title: validateSetUserAcctFreezeOrUnFreeze 
	 * @Description: 设置当前用户账户冻结或者解冻 输入参数合法性校验 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateSetUserAcctFreezeOrUnFreeze(UserAcctDTO userAcctDTO){
		if (StringUtils.isNullObject(userAcctDTO)) {
			throw new ParameterException("输入参数对象userAcctDTO不允许为空");
		}
		if(StringUtils.isNullObject(userAcctDTO.getUserId())) {
			throw new ParameterException("输入参数对象userAcctDTO中的属性userId不允许为空");
		}
	}
	
	/**
	 * @Title: validateCheckIsFreezeByUserAcct 
	 * @Description: 检查当前用户账户是否被冻结 输入参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateCheckIsFreezeByUserAcct(UserAcctDTO userAcctDTO){
		if (StringUtils.isNullObject(userAcctDTO)) {
			throw new ParameterException("输入参数对象userAcctDTO不允许为空");
		}
		if(StringUtils.isNullObject(userAcctDTO.getUserId())) {
			throw new ParameterException("输入参数对象userAcctDTO中的属性userId不允许为空");
		}
	}
	
	/**
	 * @Title: validateFindUserAcctPO 
	 * @Description: 检查当前用户对应的账户信息是否存在 输入参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateFindUserAcctPO(UserAcctPO findUserAcctPO){
		if (StringUtils.isNullObject(findUserAcctPO)) {
			throw new BusinessException("当前用户id所对应的用户账户信息不存在");
		}
		if(StringUtils.isNullObject(findUserAcctPO.getAcctState())) {
			throw new BusinessException("当前用户id所对应的用户账户信息的用户状态不允许为空");
		}
		if(!KeyNames.ACCT_STATE_NATURAL.equals(findUserAcctPO.getAcctState()) 
		   && !KeyNames.ACCT_STATE_FROZEN.equals(findUserAcctPO.getAcctState())){
			throw new BusinessException("当前用户id所对应的用户账户信息的用户状态只能为：01:正常,02:已冻结");
		}
	}
	
	/**
	 * @Title: validateSetUserAcctItemFreezeMoney 
	 * @Description: 检查账目冻结输入参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateSetUserAcctItemFreezeMoney(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO){
		if(StringUtils.isNullObject(acctItemDTO) || StringUtils.isNullObject(acctChangeDTO)){
			throw new ParameterException("acctItemDTO或者acctChangeDTO对象不允许为空");
		}
		if(StringUtils.isNullObject(acctItemDTO.getUserId())){
			throw new ParameterException("acctItemDTO中的userId不允许为空");
		}
		if(StringUtils.isNullObject(acctItemDTO.getAcctTypeCode())){
			throw new ParameterException("acctItemDTO中的acctTypeCode不允许为空");
		}
		if(StringUtils.isNullObject(acctItemDTO.getFreezeAmount())){
			throw new ParameterException("acctItemDTO中的freezeAmount不允许为空");
		}
		if(!CommonValidationUtil.isInteger(acctItemDTO.getFreezeAmount().toString())){
			throw new ParameterException("acctItemDTO中的freezeAmount必须为大于零的整数");
		}
		if(StringUtils.isNullObject(acctItemDTO.getOperType())){
			throw new ParameterException("acctItemDTO中的OperType不允许为空");
		}
		if(StringUtils.isNullObject(acctChangeDTO.getRltId())){
			throw new ParameterException("acctChangeDTO中的rltId不允许为空");
		}
		if(StringUtils.isNullObject(acctChangeDTO.getBusiType())){
			throw new ParameterException("acctChangeDTO中的busiType不允许为空");
		}
		if(StringUtils.isNullObject(acctChangeDTO.getRltChannelId())){
			throw new ParameterException("acctChangeDTO中的rltChannelId不允许为空");
		}
	}
	
	/**
	 * @Title: validateFindAcctItem 
	 * @Description: 检查获取账目详情信息校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateFindAcctItem(AcctItemPO findAcctItem){
		if(StringUtils.isNullObject(findAcctItem)){
			throw new BusinessException("获取账目详情信息失败");
		}
		if(KeyNames.ACCT_ITEM_STATE_FROZEN.equals(findAcctItem.getAcctItemState())){
			throw new BusinessException(ErrorCodeConstant.ACCT_ITEM_STATE_FROZEN_MESSAGE);
		}
	}
	
	/**
	 * @Title: validateAcctItemFreezeMoneyAdd 
	 * @Description: 检查账目冻结金额有效性
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateAcctItemFreezeMoneyAdd(AcctItemDTO acctItemDTO,AcctItemPO findAcctItem){
		if (acctItemDTO.getFreezeAmount() < 0) {
			throw new BusinessException("冻结金额或者积分数量不能小于零");
		}
		if (findAcctItem.getTotalAmount() - findAcctItem.getFreezeAmount() < acctItemDTO.getFreezeAmount()) {
			throw new BusinessException("冻结金额或者积分数量不能大于当前账目可用金额或者可用积分总数量");
		}
	}
	
	/**
	 * @Title: validateAcctItemFreezeMoneyDecNotOnlyClearZero 
	 * @Description: 检查账目冻结金额有效性 申请提现成功
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateAcctItemFreezeMoneyDecNotOnlyClearZero(AcctItemDTO acctItemDTO,AcctItemPO findAcctItem){
		if (acctItemDTO.getFreezeAmount() < 0) {
			throw new BusinessException("申请提现金额数量不能小于零");
		}
		if (findAcctItem.getTotalAmount() < acctItemDTO.getFreezeAmount()) {
			throw new BusinessException("申请提现冻结金额数量不能大于当前账目金额总数量");
		}
		if (findAcctItem.getFreezeAmount() < acctItemDTO.getFreezeAmount()) {
			throw new BusinessException("申请提现冻结金额数量不能大于当前账目冻结金额总数量");
		}
	}
	
	/**
	 * @Title: validateAcctItemFreezeMoneyDecOnlyClearZero 
	 * @Description: 检查账目冻结金额有效性 取消申请提现
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateAcctItemFreezeMoneyDecOnlyClearZero(AcctItemDTO acctItemDTO,AcctItemPO findAcctItem){
		if (acctItemDTO.getFreezeAmount() < 0) {
			throw new BusinessException("取消申请提现金额数量不能小于零");
		}
		if (findAcctItem.getFreezeAmount() < acctItemDTO.getFreezeAmount()) {
			throw new BusinessException("取消申请提现冻结金额数量不能大于当前账目冻结金额总数量");
		}
	}
	
	/**
	 * @Title: validateGetVirtualAccountInfo 
	 * @Description: 虚拟账户积分统计查询 输入参数合法性校验 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateGetVirtualAccountInfo(VirtualAcctItemDTO virtualAcctItemDTO){
		if (StringUtils.isNullObject(virtualAcctItemDTO)) {
			throw new ParameterException("输入参数对象virtualAcctItemDTO不允许为空");
		}
	}
	
	/**
	 * @Title: validateModifyVirtualAccountMoneyOrIntegral 
	 * @Description: 虚拟账户加钱或者加积分 输入参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateModifyVirtualAccountAddMoneyOrIntegral(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO,IntegeralRuleDTO integeralRuleDTO){
		if(StringUtils.isNullObject(acctItemDTO) || StringUtils.isNullObject(acctChangeDTO)){
			throw new ParameterException("acctItemDTO或者acctChangeDTO对象不允许为空");
		}
		if(StringUtils.isNullObject(acctItemDTO.getUserId())){
			throw new ParameterException("acctItemDTO中的userId不允许为空");
		}
		if(StringUtils.isNullObject(acctItemDTO.getAcctTypeCode())){
			throw new ParameterException("acctItemDTO中的acctTypeCode不允许为空");
		}
		if((TypeEnum.AcctItemType.INTEGRAL.getKey()).equals(acctItemDTO.getAcctTypeCode())){//积分充值
			validateGetIntegeralByRule(integeralRuleDTO);
		}else if((TypeEnum.AcctItemType.WITHDRAWAL_YES.getKey()).equals(acctItemDTO.getAcctTypeCode()) 
				|| (TypeEnum.AcctItemType.WITHDRAWAL_NO.getKey()).equals(acctItemDTO.getAcctTypeCode())){//可提现或者不可提现金额充值
			if(StringUtils.isNullObject(acctItemDTO.getTotalAmount())){
				throw new ParameterException("acctItemDTO中的totalAmount不允许为空");
			}
			if(!CommonValidationUtil.isInteger(acctItemDTO.getTotalAmount().toString())){
				throw new ParameterException("acctItemDTO中的totalAmount必须为大于零的整数");
			}
		}
		if(StringUtils.isNullObject(acctChangeDTO.getBusiType())){
			throw new ParameterException("acctChangeDTO中的busiType不允许为空");
		}
		if(StringUtils.isNullObject(acctChangeDTO.getRltChannelId())){
			throw new ParameterException("acctChangeDTO中的rltChannelId不允许为空");
		}
	}
	
	/**
	 * @Title: validateModifyVirtualAccountDecMoneyOrIntegral 
	 * @Description: 虚拟账户减钱或者减积分 输入参数合法性校验 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateModifyVirtualAccountDecMoneyOrIntegral(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO,IntegeralRuleDTO integeralRuleDTO,String operatorFlag){
		if(StringUtils.isNullObject(acctItemDTO) || StringUtils.isNullObject(acctChangeDTO)){
			throw new ParameterException("acctItemDTO或者acctChangeDTO对象不允许为空");
		}
		if(StringUtils.isNullObject(acctItemDTO.getUserId())){
			throw new ParameterException("acctItemDTO中的userId不允许为空");
		}
		if(KeyNames.ACCT_ITEM_OPERATER_MONEY.equals(operatorFlag)){
			if(StringUtils.isNullObject(acctItemDTO.getTotalAmount())){
				throw new ParameterException("acctItemDTO中的totalAmount不允许为空");
			}
			if(!CommonValidationUtil.isInteger(acctItemDTO.getTotalAmount().toString())){
				throw new ParameterException("acctItemDTO中的totalAmount必须为大于零的整数");
			}
		}
		if(KeyNames.ACCT_ITEM_OPERATER_INTEGRAL.equals(operatorFlag)){
			if(StringUtils.isNullObject(acctItemDTO.getTotalAmount()) && StringUtils.isNullObject(integeralRuleDTO)){
				throw new ParameterException("acctItemDTO中的totalAmount和integeralRuleDTO对象不允许同时为空");
			}
			if(!StringUtils.isNullObject(acctItemDTO.getTotalAmount()) &&
			   !CommonValidationUtil.isInteger(acctItemDTO.getTotalAmount().toString())){
				throw new ParameterException("acctItemDTO中的totalAmount不为空时，必须为大于零的整数");
			}
			if(!StringUtils.isNullObject(integeralRuleDTO)){
				validateGetIntegeralByRule(integeralRuleDTO);
			}
		}
		if(StringUtils.isNullObject(acctChangeDTO.getBusiType())){
			throw new ParameterException("acctChangeDTO中的busiType不允许为空");
		}
		if(StringUtils.isNullObject(acctChangeDTO.getRltChannelId())){
			throw new ParameterException("acctChangeDTO中的rltChannelId不允许为空");
		}
	}
	
	/**
	 * @Title: validateGetIntegeralByRule 
	 * @Description: //检查获取积分参数合法性校验 输入参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateGetIntegeralByRule(IntegeralRuleDTO integeralRuleDTO){
		if(StringUtils.isNullObject(integeralRuleDTO)){
			throw new ParameterException("integeralRuleDTO对象不允许为空");
		}
		if(StringUtils.isNullObject(integeralRuleDTO.getCreatePointCode())){
			throw new ParameterException("integeralRuleDTO中的createPointCode不允许为空");
		}
		if(!StringUtils.isNullObject(integeralRuleDTO.getMap())){
			if(!StringUtils.isNullObject(integeralRuleDTO.getMap().get("p1"))
			   && !CommonValidationUtil.isNumber(integeralRuleDTO.getMap().get("p1").toString())){
				throw new ParameterException("integeralRuleDTO中的map中的p1不为空的时候，只能为整数或者浮点数");
			}
			if(!StringUtils.isNullObject(integeralRuleDTO.getMap().get("p2"))
			   && !CommonValidationUtil.isNumber(integeralRuleDTO.getMap().get("p2").toString())){
				throw new ParameterException("integeralRuleDTO中的map中的p2不为空的时候，只能为整数或者浮点数");
			}
		}
	}
	
	/**
	 * @Title: validateGetIntegeralExpress 
	 * @Description: 检查积分表达式合法性 
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateGetIntegeralExpress(IntegeralRulePO findIntegeralRulePO){
		if(StringUtils.isNullObject(findIntegeralRulePO)){
		    throw new BusinessException("查找积分表达式失败");
	    }
		if (TypeEnum.createPointStateType.CREATE_PONIT_STATE_FORBIDDEN.equals(findIntegeralRulePO.getCreatePointState())) {
			// 检查当前使用积分规则是否被启用
			throw new BusinessException("积分点码对应的积分规则没有被启用");
		}
		if (StringUtils.isNullObject(findIntegeralRulePO.getCaculateExpression())) {
			// 检查当前积分规则是否初始化
			throw new BusinessException("积分点码对应的积分表达式不存");
		}
	}
	
	/**
	 * @Title: decVirtualAccountMoneyByItemTypeCode 
	 * @Description: 检查手工调节输入参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void decVirtualAccountMoneyByItemTypeCode(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO){
		if(StringUtils.isNullObject(acctItemDTO) || StringUtils.isNullObject(acctChangeDTO)){
			throw new ParameterException("acctItemDTO或者acctChangeDTO对象不允许为空");
		}
		if(StringUtils.isNullObject(acctItemDTO.getUserId())){
			throw new ParameterException("acctItemDTO中的userId不允许为空");
		}
		if(StringUtils.isNullObject(acctItemDTO.getAcctTypeCode())){
			throw new ParameterException("acctItemDTO中的acctTypeCode不允许为空");
		}
		if(StringUtils.isNullObject(acctItemDTO.getTotalAmount())){
			throw new ParameterException("acctItemDTO中的totalAmount不允许为空");
		}
		if(!CommonValidationUtil.isInteger(acctItemDTO.getTotalAmount().toString())){
			throw new ParameterException("acctItemDTO中的totalAmount必须为大于零的整数");
		}
		/*if(StringUtils.isNullObject(acctChangeDTO.getOperId())){
			throw new ParameterException("acctChangeDTO中的OperId不允许为空");
		}*/
		if(StringUtils.isNullObject(acctChangeDTO.getRltId())){
			throw new ParameterException("acctChangeDTO中的rltId不允许为空");
		}
		if(StringUtils.isNullObject(acctChangeDTO.getBusiType())){
			throw new ParameterException("acctChangeDTO中的busiType不允许为空");
		}
		if(StringUtils.isNullObject(acctChangeDTO.getRltChannelId())){
			throw new ParameterException("acctChangeDTO中的rltChannelId不允许为空");
		}
	}

}
