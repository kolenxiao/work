package com.xiu.uuc.manager.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiu.csp.facade.SysParamFacade;
import com.xiu.csp.facade.util.SysParamUtil;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.jexl.JexlManager;
import com.xiu.uuc.common.util.BeanUtilEx;
import com.xiu.uuc.common.util.KeyNames;
import com.xiu.uuc.common.util.StringUtils;
import com.xiu.uuc.core.service.AssetsManageService;
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
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctChangeExtDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.IntegeralRuleDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserAcctDTO;
import com.xiu.uuc.facade.dto.VirtualAcctItemDTO;
import com.xiu.uuc.facade.dto.VirtualAcctPayDTO;
import com.xiu.uuc.facade.util.ErrorCodeConstant;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.Page;
import com.xiu.uuc.facade.util.TypeEnum;
import com.xiu.uuc.facade.util.TypeEnum.UserAcctState;
import com.xiu.uuc.manager.AssetsManager;
import com.xiu.uuc.manager.LogManager;
import com.xiu.uuc.manager.validate.AssetsManagerValidate;

/**
 * @ClassName: AssetsManageManagerImpl 
 * @Description: 资产管理业务流程控制层实现类 
 * @author menglei
 * @date Jul 19, 2011 3:05:01 PM 
 */
public class AssetsManagerImpl implements AssetsManager {
	
	public Result getAcctChangeDetailList(AcctChangeDTO acctChangeDTO) throws Exception{
		Result result = null;
		//虚拟账户积分变更明细查询 输入参数合法性校验 
		AssetsManagerValidate.validateGetAcctChangeDetailList(acctChangeDTO); 
		if (org.apache.commons.lang.StringUtils.isBlank(String.valueOf(acctChangeDTO.getAccountChangeId())) 
		     && org.apache.commons.lang.StringUtils.isBlank(String.valueOf(acctChangeDTO.getUserId()))
		     && org.apache.commons.lang.StringUtils.isBlank(String.valueOf(acctChangeDTO.getAcctItemId()))
		     && org.apache.commons.lang.StringUtils.isBlank(acctChangeDTO.getIoType())
		     && org.apache.commons.lang.StringUtils.isBlank(acctChangeDTO.getBusiType())
		     && org.apache.commons.lang.StringUtils.isBlank(String.valueOf(acctChangeDTO.getIoAmount()))
		     && org.apache.commons.lang.StringUtils.isBlank(acctChangeDTO.getTimeSlot())
		     && org.apache.commons.lang.StringUtils.isBlank(acctChangeDTO.getAcctTypeCode())
		     && StringUtils.isNullObject(acctChangeDTO.getAcctTypeCodeList()) 
		     && StringUtils.isNullObject(acctChangeDTO.getCreateTime()) 
		     && StringUtils.isNullObject(acctChangeDTO.getExpireTime()) ) {
			return new Result(FacadeConstant.SUCCESS,new ArrayList<AcctChangeDTO>(),new Page());
		}
		AcctChangeParam acctChangeParam = new AcctChangeParam();
		BeanUtilEx.copyProperties(acctChangeParam, acctChangeDTO);
		int currentPage = acctChangeParam.getCurrentPage();
		
		// 获取满足条件账目记录总记录数
		int totalRecord = getAssetsManageService().listAcctChangeCount(acctChangeParam); 
		int pageSize = acctChangeParam.getPageSize();
		
		// 计算出起始行和结束行，page对象
		Page page = Page.getPage(currentPage, totalRecord, pageSize); 
		acctChangeParam.setFirstRow(page.getStartRec());
		acctChangeParam.setLastRow(page.getEndRec());
		List<AcctChangeDTO> acctChangeDTOList = new ArrayList<AcctChangeDTO>();
		SysParamUtil sysParamUtil = SysParamUtil.getInstance(sysParamFacade);
		for(AcctChangePO acctChangePO : getAssetsManageService().listAcctChange(acctChangeParam)){
			acctChangeDTO = new AcctChangeDTO();
			BeanUtilEx.copyProperties(acctChangeDTO, acctChangePO);
			acctChangeDTO.setRltChannelIdDesc(TypeEnum.ChannelType.getList().get(Integer.valueOf(acctChangeDTO.getRltChannelId())));
			if(org.apache.commons.lang.StringUtils.isNotBlank(acctChangeDTO.getBusiType()) && "1".equals(acctChangeDTO.getOperMode())){
			   acctChangeDTO.setBusiTypeDesc(sysParamUtil.getParamDescByCode(acctChangeDTO.getBusiType()));	
			}
			if("2".equals(acctChangeDTO.getOperMode())){
				acctChangeDTO.setBusiTypeDesc(TypeEnum.virtualAccountBusiType.HANDWORK_RDJUST.getValue());	
			}
			acctChangeDTOList.add(acctChangeDTO);
		}
		result = new Result(FacadeConstant.SUCCESS,acctChangeDTOList,page);
	    return result;
	}
	
	public Result getAcctChangeDetailListExt(AcctChangeExtDTO acctChangeExtDTO) throws Exception{
		Result result = null ;
		//虚拟账户积分变更明细查询 输入参数合法性校验
		AssetsManagerValidate.validateGetAcctChangeDetailListExt(acctChangeExtDTO); 
		if (org.apache.commons.lang.StringUtils.isBlank(String.valueOf(acctChangeExtDTO.getAccountChangeId())) 
		     && org.apache.commons.lang.StringUtils.isBlank(String.valueOf(acctChangeExtDTO.getUserId()))
		     && org.apache.commons.lang.StringUtils.isBlank(String.valueOf(acctChangeExtDTO.getAcctItemId()))
		     && org.apache.commons.lang.StringUtils.isBlank(acctChangeExtDTO.getIoType())
		     && org.apache.commons.lang.StringUtils.isBlank(acctChangeExtDTO.getBusiType())
		     && org.apache.commons.lang.StringUtils.isBlank(String.valueOf(acctChangeExtDTO.getIoAmount()))
		     && org.apache.commons.lang.StringUtils.isBlank(acctChangeExtDTO.getTimeSlot())
		     && org.apache.commons.lang.StringUtils.isBlank(acctChangeExtDTO.getAcctTypeCode())
		     && StringUtils.isNullObject(acctChangeExtDTO.getAcctTypeCodeList()) 
		     && StringUtils.isNullObject(acctChangeExtDTO.getCreateTime()) 
		     && StringUtils.isNullObject(acctChangeExtDTO.getExpireTime())) {
			return new Result(FacadeConstant.SUCCESS,new ArrayList<AcctChangeExtDTO>(),new Page());
		}
		AcctChangeExtParam acctChangeExtParam = new AcctChangeExtParam();
		BeanUtilEx.copyProperties(acctChangeExtParam, acctChangeExtDTO);
		int currentPage = acctChangeExtParam.getCurrentPage();
		// 获取满足条件账目记录总记录数
		int totalRecord = getAssetsManageService().listAcctChangeCountExt(acctChangeExtParam); 
		int pageSize = acctChangeExtParam.getPageSize();
		// 计算出起始行和结束行，page对象
		Page page = Page.getPage(currentPage, totalRecord, pageSize); 
		acctChangeExtParam.setFirstRow(page.getStartRec());
		acctChangeExtParam.setLastRow(page.getEndRec());
		List<AcctChangeExtDTO> acctChangeExtDTOList = new ArrayList<AcctChangeExtDTO>();
		SysParamUtil sysParamUtil = SysParamUtil.getInstance(sysParamFacade);
		for(AcctChangeExtPO acctChangeExtPO : getAssetsManageService().listAcctChangeExt(acctChangeExtParam)){
			acctChangeExtDTO = new AcctChangeExtDTO();
			BeanUtilEx.copyProperties(acctChangeExtDTO, acctChangeExtPO);
			acctChangeExtDTO.setRltChannelIdDesc(TypeEnum.ChannelType.getList().get(Integer.valueOf(acctChangeExtDTO.getRltChannelId())));
			if(org.apache.commons.lang.StringUtils.isNotBlank(acctChangeExtDTO.getBusiType()) && "1".equals(acctChangeExtDTO.getOperMode())){
			   acctChangeExtDTO.setBusiTypeDesc(sysParamUtil.getParamDescByCode(acctChangeExtDTO.getBusiType()));	
			}
			if("2".equals(acctChangeExtDTO.getOperMode())){
				acctChangeExtDTO.setBusiTypeDesc(TypeEnum.virtualAccountBusiType.HANDWORK_RDJUST.getValue());	
			}
			acctChangeExtDTOList.add(acctChangeExtDTO);
		}
		result = new Result(FacadeConstant.SUCCESS,acctChangeExtDTOList,page);
	    return result;
	}
	
	public Result getAcctItemDetailList(AcctItemDTO acctItemDTO)throws Exception{
		Result result =null;
		//虚拟账户变更明细查询 输入参数合法性校验
		AssetsManagerValidate.validateGetAcctItemDetailList(acctItemDTO); 
		if (org.apache.commons.lang.StringUtils.isBlank(String.valueOf(acctItemDTO.getUserId()))
			 && org.apache.commons.lang.StringUtils.isBlank(String.valueOf(acctItemDTO.getAcctId())) 
		     && org.apache.commons.lang.StringUtils.isBlank(String.valueOf(acctItemDTO.getAcctItemId()))
		     && org.apache.commons.lang.StringUtils.isBlank(acctItemDTO.getAcctTypeCode())
		     && StringUtils.isNullObject(acctItemDTO.getAcctItemIdList())
			 && StringUtils.isNullObject(acctItemDTO.getAcctTypeCodeList()) ) {
			return new Result(FacadeConstant.SUCCESS,new ArrayList<AcctItemDTO>(),new Page());
		}
		AcctItemParam acctItemParam = new AcctItemParam();
		BeanUtilEx.copyProperties(acctItemParam, acctItemDTO);
		int currentPage = acctItemParam.getCurrentPage();
		// 获取满足条件账目记录总记录数
		int totalRecord = getAssetsManageService().listAcctItemCount(acctItemParam);
		int pageSize = acctItemParam.getPageSize();
		// 计算出起始行和结束行，page对象
		Page page = Page.getPage(currentPage, totalRecord, pageSize); 
		acctItemParam.setFirstRow(page.getStartRec());
		acctItemParam.setLastRow(page.getEndRec());
		List<AcctItemDTO> acctItemDTOList = new ArrayList<AcctItemDTO>();
		for(AcctItemPO acctItemPO : getAssetsManageService().listAcctItem(acctItemParam)){
			acctItemDTO = new AcctItemDTO();
			BeanUtilEx.copyProperties(acctItemDTO, acctItemPO);
			acctItemDTOList.add(acctItemDTO);
		}
		result = new Result(FacadeConstant.SUCCESS,acctItemDTOList,page);
		return result;
	}
	
	public Result setUserAcctFreezeOrUnFreeze(UserAcctDTO userAcctDTO,String operatorFlag)throws Exception{
		Result result = null;
		//检查设置账户冻结的输入参数合法性
		AssetsManagerValidate.validateSetUserAcctFreezeOrUnFreeze(userAcctDTO); 
		UserAcctPO userAcctPO = new UserAcctPO();
		BeanUtilEx.copyProperties(userAcctPO, userAcctDTO);
		
		//检查当前账户是否冻结
		String IsFreezeFlag = (String)checkIsFreezeByUserAcct(userAcctDTO).getData(); 
		
		//进行用户账户冻结操作
		if (KeyNames.ACCT_STATE_FROZEN.equals(operatorFlag)) {
           if(UserAcctState.FORBID.getKey().equals(IsFreezeFlag)){
        	 //当前用户账户已经冻结,提示用户账户已经冻结
        	   throw new BusinessException(ErrorCodeConstant.ACCT_STATE_FROZEN_MESSAGE);
           }else{//当前用户账户没冻结，进行冻结操作
        	   userAcctPO.setAcctId(userAcctDTO.getAcctId()); 
        	   userAcctPO.setUserId(userAcctDTO.getUserId()); 
        	   userAcctPO.setAcctState(KeyNames.ACCT_STATE_FROZEN);
        	   getAssetsManageService().updateUserAcct(userAcctPO);
        	   result = new Result(FacadeConstant.SUCCESS, userAcctDTO.getAcctId());
           }
		} else if (KeyNames.ACCT_STATE_NATURAL.equals(operatorFlag)) {//进行用户账户解冻操作
			if(UserAcctState.NATURAL.getKey().equals(IsFreezeFlag)){
				//当前用户账户已经解冻,提示用户账户已经解冻
			   throw new BusinessException(ErrorCodeConstant.ACCT_STATE_NATURAL_MESSAGE);
            }else{//当前用户账户没解冻，进行解冻操作
        	   userAcctPO.setAcctId(userAcctDTO.getAcctId()); 
        	   userAcctPO.setUserId(userAcctDTO.getUserId()); 
        	   userAcctPO.setAcctState(KeyNames.ACCT_ITEM_STATE_NATURAL);
        	   getAssetsManageService().updateUserAcct(userAcctPO);
        	   result = new Result(FacadeConstant.SUCCESS, userAcctDTO.getAcctId());
            }
		}
		return result;
	}
	
	public Result checkIsFreezeByUserAcct(UserAcctDTO userAcctDTO)throws Exception {
		Result result = null;
		// 检查账目冻结输入参数合法性校验
		AssetsManagerValidate.validateCheckIsFreezeByUserAcct(userAcctDTO); 
		UserAcctParam userAcctParam = new UserAcctParam();
		BeanUtilEx.copyProperties(userAcctParam, userAcctDTO);
		UserAcctPO findUserAcctPO = (UserAcctPO) getAssetsManageService().findUserAcct(userAcctParam);
		
		// 检查当前用户对应的账户信息是否存在,输入参数合法性校验
		AssetsManagerValidate.validateFindUserAcctPO(findUserAcctPO); 
		if (KeyNames.ACCT_STATE_NATURAL.equals(findUserAcctPO.getAcctState())) {
			// 表示账户正常 01
			result = new Result(FacadeConstant.SUCCESS, UserAcctState.NATURAL.getKey()); 
		} else if (KeyNames.ACCT_STATE_FROZEN.equals(findUserAcctPO.getAcctState())) {
			 // 表示账户已经冻结 02
			result = new Result(FacadeConstant.SUCCESS, UserAcctState.FORBID.getKey());
		}
		return result;
	}
	
	public Result setUserAcctItemFreezeMoney(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO) throws Exception {
		Result result = new Result();
		
		 // 检查账目冻结输入参数合法性校验
		AssetsManagerValidate.validateSetUserAcctItemFreezeMoney(acctItemDTO,acctChangeDTO);
		
		// 检查当前用户账户是否被冻结(01表示正常,02表示冻结)
		IsFreezeUserAcct(new UserAcctDTO(acctItemDTO.getUserId()));
			
		// 获取账目详情
		AcctItemParam acctItemParam = new AcctItemParam();
		BeanUtilEx.copyProperties(acctItemParam, acctItemDTO);
		AcctItemPO findAcctItem = getAssetsManageService().findAcctItem(acctItemParam); 
		
		// 检查获取账目详情信息校验
		AssetsManagerValidate.validateFindAcctItem(findAcctItem); 
		
		AcctItemPO acctItemPO = new AcctItemPO();
		AcctChangePO acctChangePO = new AcctChangePO();
		BeanUtilEx.copyProperties(acctChangePO, acctChangeDTO);
		
		// 进行账目金额冻结 加操作
		if (FacadeConstant.ACCT_ITEM_FREEZE_MONEY_ADD.equals(acctItemDTO.getOperType())) {
			
			// 检查账目冻结数量有效性
			AssetsManagerValidate.validateAcctItemFreezeMoneyAdd(acctItemDTO,findAcctItem); 
			
			// 增加账目冻结金额后可提现总金额
			acctItemPO.setTotalAmount(findAcctItem.getTotalAmount()); 
			
			// 设置账目冻结后的冻结总数量
			acctItemPO.setFreezeAmount(acctItemDTO.getFreezeAmount()+ findAcctItem.getFreezeAmount()); 
			
			// 设置当次账目流水出入后的最新总数量
			acctChangePO.setLastIoAmount(findAcctItem.getTotalAmount()); 
			
			 // 设置账目流水出入标志为冻结
			acctChangePO.setIoType(KeyNames.ACCT_ITEM_IO_TYPE_FREEZE);
				
		} else if (FacadeConstant.ACCT_ITEM_FREEZE_MONEY_DEC.equals(acctItemDTO.getOperType())) {// 进行账目数量冻结 减操作
			
			// 非仅仅进行冻结数量清零操作
			if (FacadeConstant.ACCT_ITEM_FREEZE_MONEY_DEC_NOT_ONLY_CLEAR_ZERO.equals(acctItemDTO.getOnlyClearZero())) {
				
				// 检查账目冻结数量有效性
				AssetsManagerValidate.validateAcctItemFreezeMoneyDecNotOnlyClearZero(acctItemDTO, findAcctItem); 
				
				// 申请提现成功后 可提现总金额
				acctItemPO.setTotalAmount(findAcctItem.getTotalAmount()- acctItemDTO.getFreezeAmount());
				
				// 申请提现成功后 可提现总冻结金额
				acctItemPO.setFreezeAmount(findAcctItem.getFreezeAmount()- acctItemDTO.getFreezeAmount());
				
				// 设置当次账目流水出入后的最新总数量
				acctChangePO.setLastIoAmount(findAcctItem.getTotalAmount()- acctItemDTO.getFreezeAmount()); 
				
				// 设置账目流水出入标志为出账
				acctChangePO.setIoType(KeyNames.ACCT_ITEM_IO_TYPE_OUT); 
															
			} else if (FacadeConstant.ACCT_ITEM_FREEZE_MONEY_DEC_ONLY_CLEAR_ZERO.equals(acctItemDTO.getOnlyClearZero())) {
				// 仅仅进行冻结数量清零操作
				
				// 检查账目冻结数量有效性
				AssetsManagerValidate.validateAcctItemFreezeMoneyDecOnlyClearZero(acctItemDTO, findAcctItem); 
							
				// 申请提现取消后,可提现总金额
				acctItemPO.setTotalAmount(findAcctItem.getTotalAmount()); 
				
				// 申请提现取消后 可提现总冻结金额
				acctItemPO.setFreezeAmount(findAcctItem.getFreezeAmount()- acctItemDTO.getFreezeAmount()); 
				
				// 设置当次账目流水出入后的最新总数量
				acctChangePO.setLastIoAmount(findAcctItem.getTotalAmount()); 
				
				// 设置账目流水出入标志为解冻
				acctChangePO.setIoType(KeyNames.ACCT_ITEM_IO_TYPE_UNFREEZE); 

			}
			
			// 设置当次账目流水出入前的历史冻结总数量
			acctChangePO.setHistoryIoAmount(findAcctItem.getTotalAmount()); 
		}
		
		
		acctItemPO.setAcctItemId(findAcctItem.getAcctItemId()); 
		acctItemPO.setDataVersion(findAcctItem.getDataVersion()); 
		
		 // 当更新账目操作员ID非空时，更新操作员字段
		if (!StringUtils.isNullObject(acctItemDTO.getOperId())) {
			acctItemPO.setOperId(acctItemDTO.getOperId());
		}
		
		// 设置当次账目流水关联的账目id
		acctChangePO.setAcctItemId(acctItemPO.getAcctItemId()); 
		
		// 设置当前账目流水的账目类型
		acctChangePO.setAcctTypeCode(findAcctItem.getAcctTypeCode()); 
		
		// 设置当次账目流水出入数量
		acctChangePO.setIoAmount(acctItemDTO.getFreezeAmount()); 
		
		// 当更新账目变更表操作员ID非空时，更新操作员字段
		if (StringUtils.isNullObject(acctChangePO.getOperId())) { 
			acctChangePO.setOperId(acctItemPO.getOperId());
		}
		acctItemChangeProcess(acctItemPO, acctChangePO);
		// logManager.assetsManagerWriteLog(acctItemPO,acctChangePO); //新增业务操作写表
		result = new Result(FacadeConstant.SUCCESS, 1);
		return result;
	}
	
	public Result getVirtualAccountInfo(VirtualAcctItemDTO virtualAcctItemDTO)throws Exception {
		Result result = null ;
		
		// 虚拟账户积分统计查询,输入参数合法性校验
		AssetsManagerValidate.validateGetVirtualAccountInfo(virtualAcctItemDTO);
		
		VirtualAcctItemParam virtualAcctItemParam = new VirtualAcctItemParam();
		BeanUtilEx.copyProperties(virtualAcctItemParam, virtualAcctItemDTO);
		int currentPage = virtualAcctItemParam.getCurrentPage();
		
		 // 获取统计查询总记录数
		int totalRecord = getAssetsManageService().getVirtualAccountInfoCount(virtualAcctItemParam);
		int pageSize = virtualAcctItemParam.getPageSize();
		
		 // 计算出起始行和结束行，page对象
		Page page = Page.getPage(currentPage, totalRecord, pageSize);
		virtualAcctItemParam.setFirstRow(page.getStartRec());
		virtualAcctItemParam.setLastRow(page.getEndRec());
		if (virtualAcctItemParam.getLastRow()- virtualAcctItemParam.getFirstRow() > 50) {
				return new Result(FacadeConstant.SUCCESS,new ArrayList<VirtualAcctItemDTO>(),new Page());
		}
		
		List<VirtualAcctItemPO> virtualAcctItemPOList = getAssetsManageService().getVirtualAccountInfo(virtualAcctItemParam);
		List<VirtualAcctItemDTO> virtualAcctItemDTOList = new ArrayList<VirtualAcctItemDTO>();
		for (VirtualAcctItemPO virtualAcctItemPO : virtualAcctItemPOList) {
			virtualAcctItemDTO = new VirtualAcctItemDTO();
			BeanUtilEx.copyProperties(virtualAcctItemDTO, virtualAcctItemPO);
			virtualAcctItemDTOList.add(virtualAcctItemDTO);
		}
		result = new Result(FacadeConstant.SUCCESS, virtualAcctItemDTOList,page);
		return result;
	}
	
	public Result modifyVirtualAccountMoneyOrIntegral(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO, 
		IntegeralRuleDTO integeralRuleDTO,String acctItemIoTypeFlag, String operatorFlag) throws Exception {
		
		Result result = null;
		
		// 进行入账操作
		if (KeyNames.ACCT_ITEM_IO_TYPE_IN.equals(acctItemIoTypeFlag)) {
			
			// 输入参数合法性校验
			AssetsManagerValidate.validateModifyVirtualAccountAddMoneyOrIntegral(acctItemDTO, acctChangeDTO, integeralRuleDTO); 
			
			// 虚拟账户加钱或者加积分（包括金额充值）或者积分信息（包括积分充值）
			result = (Result) modifyVirtualAccountAddMoneyOrIntegral(acctItemDTO, acctChangeDTO, integeralRuleDTO, operatorFlag);
		
		// 进行出账操作
		} else if (KeyNames.ACCT_ITEM_IO_TYPE_OUT.equals(acctItemIoTypeFlag)) {
			
			//输入参数合法性校验
			AssetsManagerValidate.validateModifyVirtualAccountDecMoneyOrIntegral(acctItemDTO, acctChangeDTO, integeralRuleDTO,operatorFlag); 
			
			// 虚拟账户减钱或者减积分（包括金额支付,积分支付）
			result = (Result) modifyVirtualAccountDecMoneyOrIntegral(acctItemDTO, acctChangeDTO, integeralRuleDTO, operatorFlag);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Result getIntegeralByRule(IntegeralRuleDTO integeralRuleDTO)throws Exception {
		Result result = null ;
		
		// 检查获取积分参数合法性校验
		AssetsManagerValidate.validateGetIntegeralByRule(integeralRuleDTO);
		IntegeralRuleParam integeralRuleParam = new IntegeralRuleParam();
		BeanUtilEx.copyProperties(integeralRuleParam, integeralRuleDTO);
		IntegeralRulePO findIntegeralRulePO = getAssetsManageService().findIntegeralRule(integeralRuleParam);
		
		// 检查积分表达式合法性
		AssetsManagerValidate.validateGetIntegeralExpress(findIntegeralRulePO);
		Long integeral = JexlManager.getExpressValue(integeralRuleDTO.getMap(),findIntegeralRulePO.getCaculateExpression());
		BeanUtilEx.copyProperties(integeralRuleDTO, findIntegeralRulePO);
		integeralRuleDTO.setIntegeral(integeral);
		result = new Result(FacadeConstant.SUCCESS, integeralRuleDTO);
		return result;
	}
	
	/**
	 * @Title: decVirtualAccountMoneyByItemTypeCode 
	 * @Description: 根据账目类型 进行虚拟账户支付
	 *               账目类型:可提现,不可提现,积分（均指支付一定数量）
	 *               (说明：这里为了财务账务平衡 暂时没有对金额 或者积分进行是否足够支付判断处理)这个判断现在还是加上
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result decVirtualAccountMoneyByItemTypeCode(AcctItemDTO acctItemDTO,
			AcctChangeDTO acctChangeDTO) throws Exception {

		Long drawPayMoney = 0L; // 当次支付活动中，该用户的可提现部分支付数量 单位：分
		Long unDrawPayMoney = 0L; // 当次支付活动中，该用户的不可提现部分支付数量 单位：分
		Long payIntegral = 0L; // 当次支付活动中，该用户的积分部分支付数量 单位：分

		// 校验输入参数合法性
		AssetsManagerValidate.decVirtualAccountMoneyByItemTypeCode(acctItemDTO,acctChangeDTO);

		// 检查当前用户账户是否被冻结 true表示冻结 false表示正常
		Long userId = acctItemDTO.getUserId();
		String acctTypeCode = acctItemDTO.getAcctTypeCode();
		IsFreezeUserAcct(new UserAcctDTO(userId));

		// 缺省当前手工调节减可提现金额不足
		if (!checkIsEnoughApplyDrawAmount(acctItemDTO, acctTypeCode)) {
			throw new BusinessException(ErrorCodeConstant.ACCT_UNBALB_MONEY_NO_ENOUGH_MESSAGE);
		}

		// 获取当前用户的金额账目详情
		AcctChangePO acctChangePO = new AcctChangePO();
		BeanUtilEx.copyProperties(acctChangePO, acctChangeDTO);
		AcctItemParam acctItemParam = new AcctItemParam();
		acctItemParam.setUserId(acctItemDTO.getUserId());
		acctItemParam.setAcctTypeCode(acctItemDTO.getAcctTypeCode());
		AcctItemPO findAcctItem = (AcctItemPO) getAssetsManageService().findAcctItem(acctItemParam);
		if (StringUtils.isNullObject(findAcctItem)) {
			throw new BusinessException(ErrorCodeConstant.USER_ACCT_ITEM_NOT_FOUND_MESSAGE);
		}

		// 金额账目够支付,且支付金额>0
		// 从账目中进行扣可提现或者不可提现金额,进行入库操作
		// 当前账目扣款的出账金额
		Long IoAcctMoney = acctItemDTO.getTotalAmount();
		
		// 设置账目支付后的金额（可提现或者不可提现,其中包括冻结金额）
		findAcctItem.setTotalAmount(findAcctItem.getTotalAmount() - IoAcctMoney);
		
		// 当更新账目操作员ID非空时，更新操作员字段
		if (!StringUtils.isNullObject(acctItemDTO.getOperId())) { 
			findAcctItem.setOperId(acctItemDTO.getOperId());
		}
		acctChangePO.setIoAmount(IoAcctMoney);
		
		// 设置帐户变更信息的出账标志
		acctChangePO.setIoType(KeyNames.ACCT_ITEM_IO_TYPE_OUT); 
	
		// 设置帐户变更信息账目ID
		acctChangePO.setAcctItemId(findAcctItem.getAcctItemId()); 
		acctChangePO.setAcctTypeCode(findAcctItem.getAcctTypeCode());
		
		// 设置当次账目流水出入前的历史总金额
		acctChangePO.setHistoryIoAmount(findAcctItem.getTotalAmount()+ IoAcctMoney); 
		
		 // 设置当次账目流水出入后的最新总金额
		acctChangePO.setLastIoAmount(findAcctItem.getTotalAmount());
		
		// 当更新账目变更表操作员ID非空时，更新操作员字段
		if (StringUtils.isNullObject(acctChangePO.getOperId())) { 
			acctChangePO.setOperId(findAcctItem.getOperId());
		}
		acctItemChangeProcess(findAcctItem, acctChangePO);
		// logManager.assetsManagerWriteLog(findAcctItem,acctChangePO);
		if (KeyNames.ACCT_ITEM_MONEY_TYPE_DRAW.equals(acctItemDTO.getAcctTypeCode())) {
			drawPayMoney = IoAcctMoney;
		} else if (KeyNames.ACCT_ITEM_MONEY_TYPE_UNDRAW.equals(acctItemDTO.getAcctTypeCode())) {
			unDrawPayMoney = IoAcctMoney;
		} else if (KeyNames.ACCT_ITEM_TYPE_INTEGRAL.equals(acctItemDTO.getAcctTypeCode())) {
			payIntegral = IoAcctMoney;
		}
		VirtualAcctPayDTO virtualAcctPayDTO = new VirtualAcctPayDTO(userId,
				acctTypeCode, unDrawPayMoney, drawPayMoney, payIntegral);
		return new Result(FacadeConstant.SUCCESS, virtualAcctPayDTO);
	}
	
	/**
	 * @Title: checkIsEnoughApplyDrawAmount 
	 * @Description: 当前用户可提现可用金额（包括可提现可用）>=申请提现 ？ true:false 或者
	 *               当前用户不可提现可用金额（包括不可提现可用）>=申请提现 ？ true:false 或者
	 *               当前用户可用金额（包括可提现可用+不可提现可用）>=申请提现 ？ true:false 或者
	 *               当前用户可用积分（包括总积分-冻结积分）>=申请提现 ？ true:false 
	 * @return Boolean    返回类型 
	 * @throws
	 */
	public Boolean checkIsEnoughApplyDrawAmount(AcctItemDTO acctItemDTO,
			String operatorFlag) throws Exception {
		Boolean result = Boolean.FALSE;
		VirtualAcctItemParam virtualAcctItemParam = new VirtualAcctItemParam();
		virtualAcctItemParam.setUserId(acctItemDTO.getUserId());
		List<VirtualAcctItemPO> virtualAcctItemPOList = (List<VirtualAcctItemPO>) getAssetsManageService()
				.getVirtualAccountInfo(virtualAcctItemParam);
		if (virtualAcctItemPOList != null && virtualAcctItemPOList.size() > 0) {
			VirtualAcctItemPO virtualAcctItemPO = virtualAcctItemPOList.get(0);
			
			// 可提现可用金额是否够用
			if (KeyNames.ACCT_ITEM_MONEY_TYPE_DRAW.equals(operatorFlag)) {
				result = (virtualAcctItemPO.getUnableDrawMoney() >= acctItemDTO
						.getTotalAmount()) ? Boolean.TRUE : Boolean.FALSE;
			}
			
			// 不可提现可用金额是否够用
			if (KeyNames.ACCT_ITEM_MONEY_TYPE_UNDRAW.equals(operatorFlag)) {
				result = (virtualAcctItemPO.getUnableNoDrawMoney() >= acctItemDTO
						.getTotalAmount()) ? Boolean.TRUE : Boolean.FALSE;
			}
			
			// 可用金额是否够用
			if (KeyNames.ACCT_ITEM_MONEY_TYPE_UNSABLE.equals(operatorFlag)) {
				result = (virtualAcctItemPO.getUnableTotalMoney() >= acctItemDTO
						.getTotalAmount()) ? Boolean.TRUE : Boolean.FALSE;
			} else if (KeyNames.ACCT_ITEM_TYPE_INTEGRAL.equals(operatorFlag)) {
				// 可用积分是否够用
				result = (virtualAcctItemPO.getUnableIntegral() >= acctItemDTO
						.getTotalAmount()) ? Boolean.TRUE : Boolean.FALSE;
			}
		} else {
			throw new BusinessException(ErrorCodeConstant.VIRTUAL_ACCOUNT_INFO_FAIL_MESSAGE);
		}
		return result;
	}
	
	/**
	 * @Title: modifyVirtualAccountAddMoneyOrIntegral(这里不需要对用户账号状态进行判断)
	 * @Description: 增加虚拟账户金额信息（1.根据充值数量对金额充值）或者积分信息
	 *               （1.根据积分数量进行积分充值 2.根据积分规则对积分充值）
	 * @return Result 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private Result modifyVirtualAccountAddMoneyOrIntegral(AcctItemDTO acctItemDTO, AcctChangeDTO acctChangeDTO,
			IntegeralRuleDTO integeralRuleDTO, String operatorFlag)throws Exception {
		
		Result result = null;
		
		// 当次支付活动中，该用户的可提现部分支付数量 单位：分
		Long drawPayMoney = 0L; 
		
		// 当次支付活动中，该用户的不可提现部分支付数量 单位：分
		Long unDrawPayMoney = 0L; 
		
		// 当次支付活动中，该用户的积分部分支付数量 单位：分
		Long payIntegral = 0L; 
		Long userId = acctItemDTO.getUserId();
		String acctTypeCode = !StringUtils.isNullString(acctItemDTO.getAcctTypeCode()) ? acctItemDTO.getAcctTypeCode() : "";
		
		AcctChangePO acctChangePO = new AcctChangePO();
		BeanUtilEx.copyProperties(acctChangePO, acctChangeDTO);
		AcctItemParam acctItemParam = new AcctItemParam();
		acctItemParam.setUserId(acctItemDTO.getUserId());
		acctItemParam.setAcctTypeCode(acctItemDTO.getAcctTypeCode());
		AcctItemPO findAcctItemPO = (AcctItemPO) getAssetsManageService().findAcctItem(acctItemParam);
		if (!StringUtils.isNullObject(findAcctItemPO)) {
			
			// 进行账目金额加操作
			if (KeyNames.ACCT_ITEM_OPERATER_MONEY.equals(operatorFlag)) {
				if (KeyNames.ACCT_ITEM_MONEY_TYPE_DRAW.equals(acctTypeCode)) {
					// 当次充值的可提现金额
					drawPayMoney = acctItemDTO.getTotalAmount(); 
				} else if (KeyNames.ACCT_ITEM_MONEY_TYPE_UNDRAW.equals(acctTypeCode)) {
					// 当次充值的不可提现金额
					unDrawPayMoney = acctItemDTO.getTotalAmount();
				}
				acctChangePO.setIoAmount(acctItemDTO.getTotalAmount());
				
				// 设置当次账目流水出入前的历史总金额
				acctChangePO.setHistoryIoAmount(findAcctItemPO.getTotalAmount()); 
				
				// 设置当次账目流水出入后的最新总金额
				acctChangePO.setLastIoAmount(acctItemDTO.getTotalAmount()+ findAcctItemPO.getTotalAmount()); 
				
				// 设置充值后总金额（包括冻结金额）
				findAcctItemPO.setTotalAmount(acctItemDTO.getTotalAmount()+ findAcctItemPO.getTotalAmount()); 
			
			// 进行账目积分加操作
			} else if (KeyNames.ACCT_ITEM_OPERATER_INTEGRAL.equals(operatorFlag)) {
				if (!StringUtils.isNullObject(acctItemDTO.getTotalAmount())) { 
					
					// 根据积分数量进行积分充值
					payIntegral = acctItemDTO.getTotalAmount();
					
				} else if (!StringUtils.isNullObject(integeralRuleDTO)) {
					
					// 根据积分规则对积分充值
					IntegeralRuleDTO findIntegeralRuleDTO = (IntegeralRuleDTO) getIntegeralByRule(integeralRuleDTO).getData();
					
					// 查询创建点表达式，计算应该加的积分数量
					payIntegral = findIntegeralRuleDTO.getIntegeral(); 
				}
				acctChangePO.setIoAmount(payIntegral);
				
				// 设置当次账目流水出入前的历史总积分
				acctChangePO.setHistoryIoAmount(findAcctItemPO.getTotalAmount()); 
				
				// 设置当次账目流水出入后的最新总积分
				acctChangePO.setLastIoAmount(payIntegral+ findAcctItemPO.getTotalAmount()); 
				
				// 设置充值后总积分（包括冻结积分）
				findAcctItemPO.setTotalAmount(payIntegral+ findAcctItemPO.getTotalAmount()); 
			}
			
			// 当更新账目操作员ID非空时，更新操作员字段
			findAcctItemPO.setOperId(StringUtils.isNotEmpty(acctItemDTO.getOperId())?acctItemDTO.getOperId():findAcctItemPO.getOperId());
			// 设置账目变更信息的账目进出类型
			acctChangePO.setIoType(KeyNames.ACCT_ITEM_IO_TYPE_IN);
			acctChangePO.setAcctItemId(findAcctItemPO.getAcctItemId());
			acctChangePO.setAcctTypeCode(findAcctItemPO.getAcctTypeCode());
			acctChangePO.setOperId(findAcctItemPO.getOperId());
			acctItemChangeProcess(findAcctItemPO, acctChangePO);
			// logManager.assetsManagerWriteLog(findAcctItemPO,acctChangePO);
			//新增业务操作写表
			VirtualAcctPayDTO virtualAcctPayDTO = new VirtualAcctPayDTO(userId,acctTypeCode, unDrawPayMoney, drawPayMoney, payIntegral);
			result = new Result(FacadeConstant.SUCCESS, virtualAcctPayDTO);
		} else {
			throw new BusinessException(ErrorCodeConstant.USER_ACCT_NOT_FOUND_MESSAGE);
		}
		return result;
	}
	
	/**
	 * @Title: modifyVirtualAccountDecMoneyOrIntegral
	 *         (这里需要对用户账号状态进行判断,只有当前用户账户状态为：非冻结状态时，才能成功调用此接口) 
	 * @Description: 减少虚拟账户金额信息（包括金额支付,积分支付）
	 * @return Result    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private Result modifyVirtualAccountDecMoneyOrIntegral(
			AcctItemDTO acctItemDTO, AcctChangeDTO acctChangeDTO,
			IntegeralRuleDTO integeralRuleDTO, String operatorFlag)
			throws Exception {
		Result result = null;
		VirtualAcctPayDTO virtualAcctPayDTO = null;
		Long userId = acctItemDTO.getUserId();
		
		// 检查当前用户账户是否被冻结 02表示冻结,01表示正常
		IsFreezeUserAcct(new UserAcctDTO(userId));
		
		AcctChangePO acctChangePO = new AcctChangePO();
		BeanUtilEx.copyProperties(acctChangePO, acctChangeDTO);
		if (KeyNames.ACCT_ITEM_OPERATER_MONEY.equals(operatorFlag)) {
			// 根据账目类型进行支付
			if (!StringUtils.isNullString(acctItemDTO.getAcctTypeCode())) {
				virtualAcctPayDTO = acctItemMoneyDecByAcctTypeCode(acctItemDTO,acctChangeDTO);
			} else {// 根据支付总金额进行支付
				virtualAcctPayDTO = acctItemMoneyDec(acctItemDTO, acctChangePO); // 进行账目金额减操作
			}
		} else if (KeyNames.ACCT_ITEM_OPERATER_INTEGRAL.equals(operatorFlag)) {
			// 进行账目积分减操作
			virtualAcctPayDTO = acctItemIntegerDec(acctItemDTO, acctChangePO,integeralRuleDTO); 
		}
		result = new Result(FacadeConstant.SUCCESS, virtualAcctPayDTO);
		return result;
	}

	/**
	 * @Title: acctItemMoneyDecByAcctTypeCode 
	 * @Description: 根据账目类型 进行金额支付 
	 * 说明：
	 * (1)这里主要是用于秀团，团货 用于 根据账目类型 进行支付
	 * (2)这里对用户是否有足够的支付金额进行了判断
	 * @return VirtualAcctPayDTO    返回类型 
	 * @throws
	 */
	private VirtualAcctPayDTO acctItemMoneyDecByAcctTypeCode(
			AcctItemDTO acctItemDTO, AcctChangeDTO acctChangeDTO)
			throws Exception {
		Boolean IsEnoughMount = Boolean.FALSE; // 缺省当前支付金额不足
		VirtualAcctPayDTO virtualAcctPayDTO = null;
		if (KeyNames.ACCT_ITEM_MONEY_TYPE_DRAW.equals(acctItemDTO.getAcctTypeCode())) {
			IsEnoughMount = (Boolean) checkIsEnoughApplyDrawAmount(acctItemDTO,KeyNames.ACCT_ITEM_MONEY_TYPE_DRAW);
		} else if (KeyNames.ACCT_ITEM_MONEY_TYPE_UNDRAW.equals(acctItemDTO.getAcctTypeCode())) {
			IsEnoughMount = (Boolean) checkIsEnoughApplyDrawAmount(acctItemDTO,KeyNames.ACCT_ITEM_MONEY_TYPE_UNDRAW);
		}
		if (IsEnoughMount) {// 根据账目类型进行支付时 金额足够当次支付
			virtualAcctPayDTO = (VirtualAcctPayDTO) decVirtualAccountMoneyByItemTypeCode(acctItemDTO, acctChangeDTO).getData();
		} else {// 根据账目类型进行支付时 金额不够当次支付
			if (KeyNames.ACCT_ITEM_MONEY_TYPE_DRAW.equals(acctItemDTO.getAcctTypeCode())
				|| KeyNames.ACCT_ITEM_MONEY_TYPE_UNDRAW.equals(acctItemDTO.getAcctTypeCode())) {
				throw new BusinessException(ErrorCodeConstant.ACCT_UNBALB_MONEY_NO_ENOUGH_MESSAGE);
			}
		}
		return virtualAcctPayDTO;
	}
	
	/**
	 * @Title: acctItemMoneyDec 
	 * @Description: 账目金额减操作 金额支付(支付一定数量金额acctItemDTO.getTotalAmount())
	 *               扣除规则为：先从不可提现账目中扣除，不可提现金额不足，再从可提现中扣除
	 * @return VirtualAcctPayDTO    返回类型 
	 * @throws
	 */
	private VirtualAcctPayDTO acctItemMoneyDec(AcctItemDTO acctItemDTO,AcctChangePO acctChangePO) throws Exception {
		
		// 初始化金额支付返回对象
		VirtualAcctPayDTO virtualAcctPayDTO;
		
		// 当次支付活动中，该用户的可提现部分支付数量 单位：分
		Long drawPayMoney = 0L; 
		
		// 当次支付活动中，该用户的不可提现部分支付数量 单位：分
		Long unDrawPayMoney = 0L; 
		
		// 当前用户可用金额（包括可提现可用+不可提现可用）>=申请提现 ？ true:false
		Boolean IsEnoughMount = (Boolean) checkIsEnoughApplyDrawAmount(acctItemDTO, KeyNames.ACCT_ITEM_MONEY_TYPE_UNSABLE);
		
		// 当前用户虚拟账户可用总金额不够当次支付
		if (!IsEnoughMount) {
			throw new BusinessException(ErrorCodeConstant.ACCT_UNBALB_MONEY_NO_ENOUGH_MESSAGE);
		} else {// 当前用户虚拟账户可用总金额足够当次支付,
			AcctItemParam acctItemParam = new AcctItemParam();
			acctItemParam.setUserId(acctItemDTO.getUserId());
			acctItemParam.setAcctTypeCode(KeyNames.ACCT_ITEM_WITHDRAWAL_NO);
			// 获取当前用户的不可提现账目详情
			AcctItemPO findAcctItemNo = (AcctItemPO) getAssetsManageService().findAcctItem(acctItemParam);
			if (StringUtils.isNullObject(findAcctItemNo)) {
				throw new BusinessException(ErrorCodeConstant.USER_ACCT_ITEM_NOT_FOUND_MESSAGE);
			}
			Long IoAcctMoney = acctItemDTO.getTotalAmount(); // 当前账目扣款的出账金额
			if (findAcctItemNo.getTotalAmount()- findAcctItemNo.getFreezeAmount() - IoAcctMoney >= 0) {// 不可提现金额够当次支付
				if (IoAcctMoney > 0) {// 不可提现金额够当次支付,且当次支付金额>0,进行不可提现支付操作
					
					// 设置不可提现账目支付后的总金额数(包括冻结金额)
					findAcctItemNo.setTotalAmount(findAcctItemNo.getTotalAmount()- IoAcctMoney); 
					findAcctItemNo.setOperId(StringUtils.isNotEmpty(acctItemDTO.getOperId())?acctItemDTO.getOperId():findAcctItemNo.getOperId());
					acctChangePO.setAcctItemId(findAcctItemNo.getAcctItemId());
					acctChangePO.setIoAmount(IoAcctMoney);
					
					// 设置当次账目流水出入前的历史总金额
					acctChangePO.setHistoryIoAmount(findAcctItemNo.getTotalAmount()+ IoAcctMoney); 
					
					// 设置当次账目流水出入后的最新总金额
					acctChangePO.setLastIoAmount(findAcctItemNo.getTotalAmount()); 
					
					// 设置帐户变更信息的出账标志用户记录流水
					acctChangePO.setIoType(KeyNames.ACCT_ITEM_IO_TYPE_OUT);
					
					acctChangePO.setAcctTypeCode(findAcctItemNo.getAcctTypeCode());
					if (StringUtils.isNullObject(acctChangePO.getOperId())) {
						
						// 当更新账目变更表操作员ID非空时，更新操作员字段
						acctChangePO.setOperId(findAcctItemNo.getOperId());
					}
					acctChangePO.setOperId(findAcctItemNo.getOperId());
					acctItemChangeProcess(findAcctItemNo, acctChangePO);
					// logManager.assetsManagerWriteLog(findAcctItemNo,acctChangePO);
					unDrawPayMoney = IoAcctMoney;
				}
			} else if (findAcctItemNo.getTotalAmount()- findAcctItemNo.getFreezeAmount() - IoAcctMoney < 0) {// 不可提现金额不够当次支付
				if ((findAcctItemNo.getTotalAmount()- findAcctItemNo.getFreezeAmount() > 0)&& IoAcctMoney > 0) {// 先从不可提现账目中进行扣款
					if (!StringUtils.isNullObject(acctItemDTO.getOperId())) { 
						// 当更新账目操作员ID非空时，更新操作员字段
						findAcctItemNo.setOperId(acctItemDTO.getOperId());
					}
					acctChangePO.setAcctItemId(findAcctItemNo.getAcctItemId());
					acctChangePO.setIoAmount(findAcctItemNo.getTotalAmount()- findAcctItemNo.getFreezeAmount());
					
					// 设置当次账目流水出入前的历史总金额
					acctChangePO.setHistoryIoAmount(findAcctItemNo.getTotalAmount()); 
					
					// 设置当次账目流水出入后的最新总金额
					acctChangePO.setLastIoAmount(findAcctItemNo.getFreezeAmount());
					
					 // 设置不可提现帐户变更信息的出账标志用户记录流水
					acctChangePO.setIoType(KeyNames.ACCT_ITEM_IO_TYPE_OUT);
					acctChangePO.setAcctTypeCode(findAcctItemNo.getAcctTypeCode());
					
					 // 当更新账目变更表操作员ID非空时，更新操作员字段
					if (StringUtils.isNullObject(acctChangePO.getOperId())) {
						acctChangePO.setOperId(findAcctItemNo.getOperId());
					}
					
					// 设置不可提现扣款后总金额（等于不可提现冻结金额）
					findAcctItemNo.setTotalAmount(findAcctItemNo.getFreezeAmount()); 
					acctItemChangeProcess(findAcctItemNo, acctChangePO);
					// logManager.assetsManagerWriteLog(findAcctItemNo,acctChangePO);
					unDrawPayMoney = acctChangePO.getIoAmount();
				}
				
				// 从可提现账目中需要支付的金额数
				IoAcctMoney -= (unDrawPayMoney); 
				
				// 重设查询当前账目可提现查询条件
				acctItemParam.setAcctTypeCode(KeyNames.ACCT_ITEM_WITHDRAWAL_YES); 
				AcctItemPO findAcctItemYes = getAssetsManageService().findAcctItem(acctItemParam);
				if (StringUtils.isNullObject(findAcctItemYes)) {
					throw new BusinessException(ErrorCodeConstant.USER_ACCT_ITEM_NOT_FOUND_MESSAGE);
				}
				
				// 再从可提现账目中进行扣款
				if ((findAcctItemYes.getTotalAmount()- findAcctItemYes.getFreezeAmount() - IoAcctMoney >= 0)&& IoAcctMoney > 0) {
					
					// 设置支付后的可提现总金额（包括冻结金额）
					findAcctItemYes.setTotalAmount(findAcctItemYes.getTotalAmount()- IoAcctMoney); 
					
					// 当更新账目操作员ID非空时，更新操作员字段
					if (!StringUtils.isNullObject(acctItemDTO.getOperId())) { 
						findAcctItemYes.setOperId(acctItemDTO.getOperId());
					}
					acctChangePO.setAcctItemId(findAcctItemYes.getAcctItemId());
					acctChangePO.setIoAmount(IoAcctMoney);
					
					// 设置当次账目流水出入前的历史总金额
					acctChangePO.setHistoryIoAmount(findAcctItemYes.getTotalAmount()+ IoAcctMoney); 
					
					// 设置当次账目流水出入后的最新总金额
					acctChangePO.setLastIoAmount(findAcctItemYes.getTotalAmount()); 
					
					// 设置可提现账目变更信息的账目进出类型用于记录流水
					acctChangePO.setIoType(KeyNames.ACCT_ITEM_IO_TYPE_OUT); 
					acctChangePO.setAcctTypeCode(findAcctItemYes.getAcctTypeCode());
					
					// 当更新账目操作员ID非空时，更新操作员字段
					if (StringUtils.isNullObject(acctChangePO.getOperId())) { 
						acctChangePO.setOperId(findAcctItemYes.getOperId());
					}
					acctItemChangeProcess(findAcctItemYes, acctChangePO);
					// logManager.assetsManagerWriteLog(findAcctItemYes,acctChangePO);
					drawPayMoney = IoAcctMoney;
				}
			}
		}
		virtualAcctPayDTO = new VirtualAcctPayDTO(acctItemDTO.getUserId(),acctItemDTO.getAcctTypeCode(), unDrawPayMoney, drawPayMoney, 0L);
		return virtualAcctPayDTO;
	}
    
	/**
	 * @Title: acctItemIntegerDec 
	 * @Description: 账目积分减操作 积分支付
	 *               1.根据积分数量进行积分支付 2.根据积分规则进行积分支付
	 * @param acctItemDTO,acctChangePO,integeralRuleDTO
	 * @return Long    返回类型 
	 * @throws
	 */
	private VirtualAcctPayDTO acctItemIntegerDec(AcctItemDTO acctItemDTO,
			AcctChangePO acctChangePO, IntegeralRuleDTO integeralRuleDTO)
			throws Exception {
		
		// 初始化积分支付返回对象
		VirtualAcctPayDTO virtualAcctPayDTO; 
		
		// 用来存储当次支付积分 初始化为0L
		Long payIntegral = 0L; 
		
		// 根据积分规则对积分支付
		IntegeralRuleDTO findIntegeralRuleDTO = null;
		
		if (StringUtils.isNullObject(acctItemDTO.getTotalAmount())) {
			findIntegeralRuleDTO = (IntegeralRuleDTO) getIntegeralByRule(integeralRuleDTO).getData();
			acctItemDTO.setTotalAmount(findIntegeralRuleDTO.getIntegeral());
		}
		
		// 当前用户可用积分（包括总积分-冻结积分）>=当此支付积分 ？ true:false
		Boolean IsEnoughMount = (Boolean) checkIsEnoughApplyDrawAmount(acctItemDTO, KeyNames.ACCT_ITEM_TYPE_INTEGRAL);
		
		if (!IsEnoughMount) {// 当前用户虚拟账户可用总积分不够当次支付
			throw new BusinessException(ErrorCodeConstant.ACCT_UNABLE_INTEGRAL_NO_ENOUGH_MESSAGE);
		} else {// 当前用户虚拟账户可用总积分够当次支付
			AcctItemParam acctItemParam = new AcctItemParam();
			acctItemParam.setUserId(acctItemDTO.getUserId());
			acctItemParam.setAcctTypeCode(KeyNames.ACCT_ITEM_INTEGRAL);
			
			// 获取当前用户的积分账目详情
			AcctItemPO findAcctItemIntegral = (AcctItemPO) getAssetsManageService().findAcctItem(acctItemParam);
			if (StringUtils.isNullObject(findAcctItemIntegral)) {
				throw new BusinessException(ErrorCodeConstant.USER_ACCT_ITEM_NOT_FOUND_MESSAGE);
			}
			if (!StringUtils.isNullObject(acctItemDTO.getTotalAmount())) { // 根据积分数量进行积分支付
				payIntegral = acctItemDTO.getTotalAmount();
			} else {// 根据积分规则对积分支付
				// 查询创建点表达式，计算应支付的积分数量
				payIntegral = findIntegeralRuleDTO.getIntegeral(); 
			}
			if (payIntegral > 0) {// 积分账目 当次支付积分>0,进行入库操作
				// 从积分账目中进行扣积分
				findAcctItemIntegral.setTotalAmount(findAcctItemIntegral.getTotalAmount()- payIntegral);
				if (!StringUtils.isNullObject(acctItemDTO.getOperId())) { 
					// 当更新账目操作员ID非空时，更新操作员字段
					findAcctItemIntegral.setOperId(acctItemDTO.getOperId());
				}
				acctChangePO.setAcctItemId(findAcctItemIntegral.getAcctItemId());
				acctChangePO.setIoAmount(payIntegral);
				
				// 设置当次账目流水出入前的历史总积分
				acctChangePO.setHistoryIoAmount(findAcctItemIntegral.getTotalAmount()+ payIntegral);
				
				// 设置当次账目流水出入后的最新总积分
				acctChangePO.setLastIoAmount(findAcctItemIntegral.getTotalAmount()); 
				
				// 设置帐户变更信息的出账标志
				acctChangePO.setIoType(KeyNames.ACCT_ITEM_IO_TYPE_OUT); 
				acctChangePO.setAcctTypeCode(KeyNames.ACCT_ITEM_INTEGRAL);
				if (StringUtils.isNullObject(acctChangePO.getOperId())) { 
					// 当更新账目变更表操作员ID非空时，更新操作员字段
					acctChangePO.setOperId(findAcctItemIntegral.getOperId());
				}
				acctItemChangeProcess(findAcctItemIntegral, acctChangePO);
				// logManager.assetsManagerWriteLog(findAcctItemIntegral,acctChangePO);
			} else { // 积分账目 当次支付积分<=0,不进行入库操作
				throw new BusinessException(ErrorCodeConstant.INTEGRAL_PAY_AMOUNT_ZERO_ERROR_MESSAGE);
			}
		}
		virtualAcctPayDTO = new VirtualAcctPayDTO(acctItemDTO.getUserId(),acctItemDTO.getAcctTypeCode(), 0L, 0L, payIntegral);
		return virtualAcctPayDTO;
	}

	/**
	 * @Title: acctItemChangeProcess
	 * @Description: 更新账目 插入账目流水
	 * @return void 返回类型
	 * @throws
	 */
	private void acctItemChangeProcess(AcctItemPO acctItemPO,AcctChangePO acctChangePO) throws BusinessException {
		
		// 更新账目信息表
		Integer count = getAssetsManageService().updateAcctItem(acctItemPO);
		
		// 新增账目变更历史信息表
		Long accountChangeId = getAssetsManageService().insertAcctChange(acctChangePO); 
		
		if (count == null || count <= 0 || accountChangeId == null || accountChangeId < 0) {
			throw new BusinessException("更新账目操作失败");
		}
		
	}
	
	/**
	 * @Title: IsFreezeUserAcct 
	 * @Description: 检查当前用户账户是否被冻结 02表示冻结 01表示正常
	 * @return void    返回类型 
	 * @throws
	 */
	private void IsFreezeUserAcct(UserAcctDTO userAcctDTO) throws Exception {
		Result result = checkIsFreezeByUserAcct(userAcctDTO);
		
		// 用户账户状态,缺省为 01:正常
		String IsFreezeUserAcct = UserAcctState.NATURAL.getKey(); 

		// 检查用户账户状态成功
		if (FacadeConstant.SUCCESS.equals(result.getSuccess())) {
			IsFreezeUserAcct = (String) checkIsFreezeByUserAcct(userAcctDTO).getData();
			if (UserAcctState.FORBID.getKey().equals(IsFreezeUserAcct)) {
				// 当前用户账户被冻结
				throw new BusinessException(ErrorCodeConstant.ACCT_STATE_FROZEN_MESSAGE);
			}
		}
	}

	public AssetsManageService getAssetsManageService() {
		return assetsManageService;
	}

	@Autowired
	public void setAssetsManageService(AssetsManageService assetsManageService) {
		this.assetsManageService = assetsManageService;
	}
	
	@Autowired
	public void setSysParamFacade(SysParamFacade sysParamFacade) {
		this.sysParamFacade = sysParamFacade;
	}
	
	
	@Autowired
	public void setLogManager(LogManager logManager) {
		this.logManager = logManager;
	}
	
	/**
	 * 资产管理业务服务层对象
	 */
	private AssetsManageService assetsManageService;
	
	/**
	 * 公共系统参数接口对象
	 */
	private SysParamFacade sysParamFacade;
	
	@SuppressWarnings("unused")
	private LogManager logManager;
}
