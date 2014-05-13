package com.xiu.uuc.facade.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiu.commons.core.BaseExternalService;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.JsonUtils;
import com.xiu.uuc.facade.ChangePayFacade;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.TractDTO;
import com.xiu.uuc.facade.dto.TractQueryDTO;
import com.xiu.uuc.facade.dto.TradeDTO;
import com.xiu.uuc.manager.ChangePayManager;
import com.xiu.uuc.manager.util.ExceptionProcessor;

/**
 * @ClassName: ChangePayFacadeImpl 
 * @Description:换货支付facade实现类 供第三方系统统一调用  
 * @author menglei
 * @date Jul 21, 2011 6:47:59 PM 
 */
public class ChangePayFacadeImpl extends BaseExternalService implements ChangePayFacade {
	
	@Override
	public Result incomeAccounts(TradeDTO tradeDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("incomeAccounts entry: tradeDTO={}",
				   new Object[] {jsonUtils.toJson(tradeDTO)});
		try {
			result = (Result) changePayManager.incomeAccounts(tradeDTO);
			Long rltId = (Long)result.getData();
			logger.info("incomeAccounts success: rltId={}, userId={}, costTime={}ms",
					   new Object[] { rltId,tradeDTO.getUserId(),System.currentTimeMillis() - stime });
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
	public Result outcomeAccounts(TradeDTO tradeDTO) {
	    long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("outcomeAccounts entry: tradeDTO={}",
					new Object[] {jsonUtils.toJson(tradeDTO)});	 
		try {
			result = (Result) changePayManager.outcomeAccounts(tradeDTO);
			tradeDTO = (TradeDTO)result.getData();
			logger.info("outcomeAccounts success: tradeDTO={},costTime={}ms",
					   new Object[] { jsonUtils.toJson(tradeDTO),System.currentTimeMillis() - stime });
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
	public Result getTractRefundDetailList(TractQueryDTO tractQueryDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("getTractRefundDetailList entry: tractQueryDTO={}",
				   new Object[] {jsonUtils.toJson(tractQueryDTO)});
		try {
			result = (Result) changePayManager.getTractRefundDetailList(tractQueryDTO);
			List<TractDTO> resultList = (List<TractDTO>) result.getData();
			logger.info("getTractRefundDetailList success: count={}, costTime={}ms",
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
	public Result getTractRefundNotEndDetailList(TractQueryDTO tractQueryDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("getTractRefundNotEndDetailList entry: tractQueryDTO={}",
				   new Object[] {jsonUtils.toJson(tractQueryDTO)});
		try {
			result = (Result) changePayManager.getTractRefundNotEndDetailList(tractQueryDTO);
			List<TractDTO> resultList = (List<TractDTO>) result.getData();
			logger.info("getTractRefundNotEndDetailList success: count={},costTime={}ms",
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
	
	@Autowired
	public void setChangePayManager(ChangePayManager changePayManager) {
		this.changePayManager = changePayManager;
	}
	
	private ChangePayManager changePayManager;
	private static final Logger logger = LoggerFactory.getLogger(ChangePayFacadeImpl.class);
	private JsonUtils jsonUtils = new JsonUtils();

}
