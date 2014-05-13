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
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.AcctChangeFacade;
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctChangeExtDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.UserAcctDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.manager.AssetsManager;
import com.xiu.uuc.manager.util.ExceptionProcessor;

/**
 * @ClassName: AcctChangeFacadeImpl 
 * @Description: 用户账目变更历史记录相关的facade实现类 供第三方系统统一调用（包括：不可提现，可提现金额以及积分管理） 
 * @author menglei
 * @date Jul 21, 2011 8:38:48 PM 
 */
public class AcctChangeFacadeImpl extends BaseExternalService implements AcctChangeFacade {
	
	@Override
	public Result checkIsFreezeUserAcct(UserAcctDTO userAcctDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("checkIsFreezeUserAcct entry: userId={}",userAcctDTO!=null ? userAcctDTO.getUserId() : "");
		try {
			result = (Result) getAssetsManager().checkIsFreezeByUserAcct(userAcctDTO);
			String userAcctStatue = (String)result.getData();
			logger.info("checkIsFreezeUserAcct success: userAcctStatue={}, userId={}, costTime={}ms",
							new Object[] {userAcctStatue,userAcctDTO.getUserId(),
							System.currentTimeMillis() - stime });
			return result;
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
	public Result getVirtualChangeList(AcctChangeDTO acctChangeDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("getVirtualChangeList entry: acctChangeDTO={}",jsonUtils.toJson(acctChangeDTO));
		try {
			result = (Result) getAssetsManager().getAcctChangeDetailList(acctChangeDTO);
			List<AcctChangeDTO> resultList = (List<AcctChangeDTO>) result.getData();
			logger.info("getVirtualChangeList success: count={}, costTime={}ms",
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
	public Result getVirtualChangeListExt(AcctChangeExtDTO acctChangeExtDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("getVirtualChangeListExt entry: acctChangeExtDTO={}",jsonUtils.toJson(acctChangeExtDTO));
		try {
			result = (Result) getAssetsManager().getAcctChangeDetailListExt(acctChangeExtDTO);
			List<AcctChangeExtDTO> resultList = (List<AcctChangeExtDTO>) result.getData();
			logger.info("getVirtualChangeListExt success: count={},costTime={}ms",
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
	public Result setUserAcctFreeze(UserAcctDTO userAcctDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("setUserAcctFreeze entry: userAcctDTO={}",jsonUtils.toJson(userAcctDTO));
		try {
			result = (Result) getAssetsManager().setUserAcctFreezeOrUnFreeze(userAcctDTO, KeyNames.ACCT_STATE_FROZEN);
			Long acctId = (Long)result.getData();
			logger.info("setUserAcctFreeze success: acctId={}, userId={}, costTime={}ms",
					   new Object[] { acctId,userAcctDTO.getUserId(),System.currentTimeMillis() - stime });
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
	public Result setUserAcctUnFreeze(UserAcctDTO userAcctDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("setUserAcctUnFreeze entry: userAcctDTO={}",jsonUtils.toJson(userAcctDTO));
		try {
			result = (Result) getAssetsManager().setUserAcctFreezeOrUnFreeze(userAcctDTO, KeyNames.ACCT_STATE_NATURAL);
			Long acctId = (Long)result.getData();
			logger.info("setUserAcctUnFreeze success: acctId={}, userId={}, costTime={}ms",
					   new Object[] { acctId,userAcctDTO.getUserId(),System.currentTimeMillis() - stime });
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
	public Result setUserAcctItemFreezeMoney(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("setUserAcctItemFreezeMoney entry: acctItemDTO={},acctChangeDTO={}",
				   new Object[] { jsonUtils.toJson(acctItemDTO),jsonUtils.toJson(acctChangeDTO)});
		try {
			result = (Result) getAssetsManager().setUserAcctItemFreezeMoney(acctItemDTO, acctChangeDTO);
			logger.info("setUserAcctItemFreezeMoney success: userId={}, costTime={}ms",
					   new Object[] {acctItemDTO.getUserId(),System.currentTimeMillis() - stime });
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
	public Result getUserOnlineDrawBackList(AcctChangeDTO acctChangeDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("getUserOnlineDrawBackList entry: acctChangeDTO={}",
				   new Object[] { jsonUtils.toJson(acctChangeDTO)});
		try {
			result = (Result) getAssetsManager().getAcctChangeDetailList(acctChangeDTO);
			List<AcctChangeDTO> resultList = (List<AcctChangeDTO>) result.getData();
			logger.info("getUserOnlineDrawBackList success: count={},costTime={}ms",
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
	public Result getUserAcctChangeList(AcctChangeDTO acctChangeDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("getUserAcctChangeList entry: acctChangeDTO={}",
				   new Object[] { jsonUtils.toJson(acctChangeDTO)});
		try {
			result = (Result) getAssetsManager().getAcctChangeDetailList(acctChangeDTO);
			List<AcctChangeDTO> resultList = (List<AcctChangeDTO>) result.getData();
			logger.info("getUserAcctChangeList success: count={},costTime={}ms",
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
	public Result checkIsEnoughApplyDrawAmount(AcctItemDTO acctItemDTO,String operatorFlag){
		Boolean  IsEnoughApplyDrawAmount = Boolean.FALSE;
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("checkIsEnoughApplyDrawAmount entry: acctItemDTO={},operatorFlag={}",
				   new Object[] { jsonUtils.toJson(acctItemDTO),operatorFlag});
		try {
			IsEnoughApplyDrawAmount = (Boolean) getAssetsManager().checkIsEnoughApplyDrawAmount(acctItemDTO,operatorFlag);
			result = new Result(FacadeConstant.SUCCESS, IsEnoughApplyDrawAmount);
			logger.info("checkIsEnoughApplyDrawAmount success: IsEnoughApplyDrawAmount={}, userId={}, costTime={}ms",
					   new Object[] { IsEnoughApplyDrawAmount,acctItemDTO.getUserId(),System.currentTimeMillis() - stime });
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

	@Autowired
	public void setAssetsManager(AssetsManager assetsManager) {
		this.assetsManager = assetsManager;
	}
	
	private AssetsManager assetsManager;
	private static final Logger logger = LoggerFactory.getLogger(AcctChangeFacadeImpl.class);
	private JsonUtils jsonUtils = new JsonUtils();
}

