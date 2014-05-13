package com.xiu.uuc.core.service;

import java.util.List;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.dal.param.AcctChangeExtParam;
import com.xiu.uuc.dal.param.AcctChangeParam;
import com.xiu.uuc.dal.param.AcctItemParam;
import com.xiu.uuc.dal.param.IntegeralRuleParam;
import com.xiu.uuc.dal.param.UserAcctParam;
import com.xiu.uuc.dal.param.VirtualAcctItemParam;
import com.xiu.uuc.dal.po.AcctChangeExtPO;
import com.xiu.uuc.dal.po.AcctChangePO;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.dal.po.IntegeralRulePO;
import com.xiu.uuc.dal.po.UserAcctPO;
import com.xiu.uuc.dal.po.VirtualAcctItemPO;

/**
 * @ClassName: AssetsManageService 
 * @Description: 资产管理业务服务层 
 * @author menglei
 * @date Jul 19, 2011 3:00:15 PM 
 */
public interface AssetsManageService {
	
	/**
	 * @Title: updateAcctItem 
	 * @Description: 修改账目信息
	 * @return Integer    返回类型 
	 * @throws
	 */
	Integer updateAcctItem(AcctItemPO acctItemPO) throws BusinessException ;

	/**
	 * @Title: listAcctItem 
	 * @Description: 查询账目信息列表
	 * @return List<AcctItemPO>    返回类型 
	 * @throws
	 */
    List<AcctItemPO> listAcctItem(AcctItemParam acctItemParam) throws BusinessException ;
    
    /**
	 * @Title: findAcctItem 
	 * @Description: 查询账目信息详情
	 * @return AcctItemPO    返回类型 
	 * @throws
	 */
    AcctItemPO findAcctItem(AcctItemParam acctItemParam) throws BusinessException ;
    
    /**
	 * @Title: listAcctItemCount 
	 * @Description: 统计账目信息
	 * @return Integer    返回类型 
	 * @throws
	 */
	Integer listAcctItemCount(AcctItemParam acctItemParam) throws BusinessException ;
    
    /**
     * @Title: insertAcctChange 
     * @Description: 新增账目变更历史流水记录 
     * @return Long    返回类型 
     * @throws
     */
	Long insertAcctChange(AcctChangePO acctChangePO) throws BusinessException;

	/**
	 * @Title: listAcctChange 
	 * @Description: 查询账目变更历史流水记录 
	 * @return List<AcctChangePO>    返回类型 
	 * @throws
	 */
    List<AcctChangePO> listAcctChange(AcctChangeParam acctChangeParam) throws BusinessException ;
    
    /**
	 * @Title: listAcctChangeExt 
	 * @Description: 查询账目变更历史流水记录 
	 * @return List<AcctChangeExtPO>    返回类型 
	 * @throws
	 */
    List<AcctChangeExtPO> listAcctChangeExt(AcctChangeExtParam acctChangeExtParam) throws BusinessException ;
    
    /**
	 * @Title: listAcctChangeCount 
	 * @Description: 查询统计账目变更历史流水记录 
	 * @return Integer    返回类型 
	 * @throws
	 */
	Integer listAcctChangeCount(AcctChangeParam acctChangeParam) throws BusinessException ;
	
	/**
	 * @Title: listAcctChangeCountExt 
	 * @Description: 查询统计账目变更历史流水记录 
	 * @return Integer    返回类型 
	 * @throws
	 */
	Integer listAcctChangeCountExt(AcctChangeExtParam acctChangeExtParam) throws BusinessException ;
	
	
	/**
	 * @Title: updateUserAcct 
	 * @Description: 修改用戶账户信息
	 * @return Integer    返回类型 
	 * @throws
	 */
	Integer updateUserAcct(UserAcctPO userAcctPO) throws BusinessException ;

	/**
	 * @Title: findUserAcct 
	 * @Description: 查询用户账户信息
	 * @return UserAcctPO    返回类型 
	 * @throws
	 */
    UserAcctPO findUserAcct(UserAcctParam userAcctParam) throws BusinessException ;
    
    /**
	 * @Title: findIntegeralRule 
	 * @Description: 查询特定渠道积分规则表达式信息
	 * @return IntegeralRulePO    返回类型 
	 * @throws
	 */
    IntegeralRulePO findIntegeralRule(IntegeralRuleParam integeralRuleParam) throws BusinessException ;
    
    /**
	 * @Title: getVirtualAccountInfo 
	 * @Description: 虚拟帐户信息（账目）包括：可提现总金额,可提现冻结金额,不可提现总金额,不可提现冻结金额,总积分,积分冻结
	 * @return VirtualAcctItemPO    返回类型 
	 * @throws
	 */
    List<VirtualAcctItemPO> getVirtualAccountInfo(VirtualAcctItemParam virtualAcctItemParam) throws BusinessException ;
    
    /**
	 * @Title: getVirtualAccountInfoCount 
	 * @Description: 统计查询虚拟帐户信息（账目）包括：可提现总金额,可提现冻结金额,不可提现总金额,不可提现冻结金额,总积分,积分冻结
	 * @return Integer    返回类型 
	 * @throws
	 */
    Integer getVirtualAccountInfoCount(VirtualAcctItemParam virtualAcctItemParam) throws BusinessException ;
}
