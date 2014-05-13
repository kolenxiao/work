package com.xiu.uuc.facade.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiu.commons.core.BaseExternalService;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.JsonUtils;
import com.xiu.uuc.common.util.KeyNames;
import com.xiu.uuc.core.service.AbnormalLogService;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.AcctItemFacade;
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.IntegeralRuleDTO;
import com.xiu.uuc.facade.dto.VirtualAcctItemDTO;
import com.xiu.uuc.facade.dto.VirtualAcctPayDTO;
import com.xiu.uuc.manager.AssetsManager;
import com.xiu.uuc.manager.util.ExceptionProcessor;

/**
 * @ClassName: AcctItemFacadeImpl 
 * @Description:用户账目记录相关的facade实现类 供第三方系统统一调用（包括：不可提现，可提现金额以及积分管理）  
 * @author menglei
 * @date Jul 21, 2011 8:40:04 PM 
 */
public class AcctItemFacadeImpl extends BaseExternalService implements AcctItemFacade {

	@SuppressWarnings("unchecked")
	@Override
	public Result getVirtualAccountInfo(VirtualAcctItemDTO virtualAcctItemDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("getVirtualAccountInfo entry: virtualAcctItemDTO={}",jsonUtils.toJson(virtualAcctItemDTO));
		try {
			result = (Result) getAssetsManager().getVirtualAccountInfo(virtualAcctItemDTO);
			List<VirtualAcctItemDTO> resultList = (List<VirtualAcctItemDTO>) result.getData();
			logger.info("getVirtualAccountInfo success: count={},costTime={}ms",
						new Object[] {resultList.size(),System.currentTimeMillis() - stime });
		} catch (ParameterException e) {
			result = ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			result = ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			result = ExceptionProcessor.getExceptionResult(e);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Result getVirtualItemList(AcctItemDTO acctItemDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("getVirtualItemList entry: acctItemDTO={}",jsonUtils.toJson(acctItemDTO));
		try {
			result = (Result) getAssetsManager().getAcctItemDetailList(acctItemDTO);
			List<AcctItemDTO> resultList = (List<AcctItemDTO>) result.getData();
			logger.info("getVirtualItemList success: count={}, costTime={}ms",
					new Object[] {resultList.size(),System.currentTimeMillis() - stime });
		} catch (ParameterException e) {
			result = ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			result = ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			result = ExceptionProcessor.getExceptionResult(e);
		}
		return result;
	}

	@Override
	public Result addVirtualAccountMoney(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO) { 
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("addVirtualAccountMoney entry: acctItemDTO={},acctChangeDTO={}",
				   new Object[] { jsonUtils.toJson(acctItemDTO),jsonUtils.toJson(acctChangeDTO)});
		try {
			result = (Result) getAssetsManager().modifyVirtualAccountMoneyOrIntegral(acctItemDTO,
							acctChangeDTO, null, KeyNames.ACCT_ITEM_IO_TYPE_IN,
							KeyNames.ACCT_ITEM_OPERATER_MONEY);
			VirtualAcctPayDTO virtualAcctPayDTO = (VirtualAcctPayDTO) result.getData();
			logger.info("addVirtualAccountMoney success: virtualAcctPayDTO={},costTime={}ms",
							new Object[] { jsonUtils.toJson(virtualAcctPayDTO),System.currentTimeMillis() - stime });
			//虚拟账户异动审计--账目金额和次数实时审计(暂且屏蔽)
			//getAbnormalLogService().auditAccountPayLimit(acctItemDTO);
		} catch (ParameterException e) {
			result = ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			result = ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			result = ExceptionProcessor.getExceptionResult(e);
		}
		return result;
	}

	@Override
	public Result decVirtualAccountMoney(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("decVirtualAccountMoney entry: acctItemDTO={},acctChangeDTO={}",
				   new Object[] { jsonUtils.toJson(acctItemDTO),jsonUtils.toJson(acctChangeDTO)});
		try {
			result = (Result) getAssetsManager().modifyVirtualAccountMoneyOrIntegral(acctItemDTO,
							acctChangeDTO, null,KeyNames.ACCT_ITEM_IO_TYPE_OUT,
							KeyNames.ACCT_ITEM_OPERATER_MONEY);
			VirtualAcctPayDTO virtualAcctPayDTO = (VirtualAcctPayDTO) result.getData();
			logger.info("decVirtualAccountMoney success: virtualAcctPayDTO={}, costTime={}ms",
							new Object[] { jsonUtils.toJson(virtualAcctPayDTO),System.currentTimeMillis() - stime });
			//虚拟账户异动审计--账目金额和次数实时审计(暂且屏蔽)
			//getAbnormalLogService().auditAccountPayLimit(acctItemDTO);
		} catch (ParameterException e) {
			result = ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			result = ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			result = ExceptionProcessor.getExceptionResult(e);
		}
		return result;
	}
	
	@Override
	public Result decVirtualAccountMoneyByItemTypeCode(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("decVirtualAccountMoneyByItemTypeCode entry: acctItemDTO={},acctChangeDTO={}",
				   new Object[] { jsonUtils.toJson(acctItemDTO),jsonUtils.toJson(acctChangeDTO)});
		try {
			result = (Result) getAssetsManager().decVirtualAccountMoneyByItemTypeCode(acctItemDTO,acctChangeDTO);
			VirtualAcctPayDTO virtualAcctPayDTO = (VirtualAcctPayDTO) result.getData();
			logger.info("decVirtualAccountMoneyByItemTypeCode success: virtualAcctPayDTO={},costTime={}ms",
							new Object[] { jsonUtils.toJson(virtualAcctPayDTO),System.currentTimeMillis() - stime });
			//虚拟账户异动审计--账目金额和次数实时审计
			//getAbnormalLogService().auditAccountPayLimit(acctItemDTO);
		} catch (ParameterException e) {
			result = ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			result = ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			result = ExceptionProcessor.getExceptionResult(e);
		}
		return result;
	}
	
	@Override
	public Result addVirtualAccountIntegral(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO,IntegeralRuleDTO integeralRuleDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		acctItemDTO.setAcctTypeCode(KeyNames.ACCT_ITEM_INTEGRAL);
		logger.info("addVirtualAccountIntegral entry: acctItemDTO={},acctChangeDTO={},integeralRuleDTO={}",
			new Object[] {jsonUtils.toJson(acctItemDTO),jsonUtils.toJson(acctChangeDTO),jsonUtils.toJson(integeralRuleDTO) });
		try {
			result = (Result) getAssetsManager().modifyVirtualAccountMoneyOrIntegral(acctItemDTO,
							acctChangeDTO, integeralRuleDTO,KeyNames.ACCT_ITEM_IO_TYPE_IN,KeyNames.ACCT_ITEM_OPERATER_INTEGRAL);
			VirtualAcctPayDTO virtualAcctPayDTO = (VirtualAcctPayDTO)result.getData();
			logger.info("addVirtualAccountIntegral success: virtualAcctPayDTO={},costTime={}ms",
					   new Object[] { jsonUtils.toJson(virtualAcctPayDTO),System.currentTimeMillis() - stime });
		} catch (ParameterException e) {
			result = ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			result = ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			result = ExceptionProcessor.getExceptionResult(e);
		}
		return result;
	}
	
	@Override
	public Result decVirtualAccountIntegral(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO,IntegeralRuleDTO integeralRuleDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		acctItemDTO.setAcctTypeCode(KeyNames.ACCT_ITEM_INTEGRAL);
		logger.info("decVirtualAccountIntegral entry: acctItemDTO={},acctChangeDTO={},integeralRuleDTO={}",
				new Object[] { jsonUtils.toJson(acctItemDTO),jsonUtils.toJson(acctChangeDTO),jsonUtils.toJson(integeralRuleDTO) });
		try {
			result = (Result) getAssetsManager().modifyVirtualAccountMoneyOrIntegral(acctItemDTO,acctChangeDTO, 
					         integeralRuleDTO,KeyNames.ACCT_ITEM_IO_TYPE_OUT,KeyNames.ACCT_ITEM_OPERATER_INTEGRAL);
			VirtualAcctPayDTO virtualAcctPayDTO = (VirtualAcctPayDTO)result.getData();
			logger.info("decVirtualAccountIntegral success: virtualAcctPayDTO={},costTime={}ms",
					   new Object[] { jsonUtils.toJson(virtualAcctPayDTO),System.currentTimeMillis() - stime });
		} catch (ParameterException e) {
			result = ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			result = ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			result = ExceptionProcessor.getExceptionResult(e);
		}
		return result;
	}
	
	@Override
	public Result getIntegeralByRule(IntegeralRuleDTO integeralRuleDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("getIntegeralByRule entry: integeralRuleDTO={}",
				   new Object[] {jsonUtils.toJson(integeralRuleDTO)});
		try {
			result = (Result) getAssetsManager().getIntegeralByRule(integeralRuleDTO);
			integeralRuleDTO = (IntegeralRuleDTO)result.getData();
			logger.info("getIntegeralByRule success: integeralRuleDTO={},costTime={}ms",
					   new Object[] { jsonUtils.toJson(integeralRuleDTO),System.currentTimeMillis() - stime });
		} catch (ParameterException e) {
			result = ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			result = ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			result = ExceptionProcessor.getExceptionResult(e);
		}
		return result;
	}

	public AssetsManager getAssetsManager() {
		return assetsManager;
	}
	
	public AbnormalLogService getAbnormalLogService() {
		return abnormalLogService;
	}

	@Autowired
	public void setAssetsManager(AssetsManager assetsManager) {
		this.assetsManager = assetsManager;
	}
	
	@Autowired
	public void setAbnormalLogService(AbnormalLogService abnormalLogService) {
		this.abnormalLogService = abnormalLogService;
	}
	
	private AssetsManager assetsManager;
	private AbnormalLogService abnormalLogService;
	private static final Logger logger = LoggerFactory.getLogger(AcctItemFacadeImpl.class);
	private JsonUtils jsonUtils = new JsonUtils();
}
