package com.xiu.uuc.facade.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiu.commons.core.BaseExternalService;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.JsonUtils;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.BankAcctFacade;
import com.xiu.uuc.facade.dto.BankAcctDTO;
import com.xiu.uuc.manager.BankAcctManager;
import com.xiu.uuc.manager.util.ExceptionProcessor;

/**
 * @ClassName: BankAcctFacadeImpl 
 * @Description: 用户提现银行卡账号管理的facade实现类 供第三方系统统一调用  
 * @author menglei
 * @date Jul 21, 2011 6:47:59 PM 
 */
public class BankAcctFacadeImpl extends BaseExternalService implements BankAcctFacade {

	@Override
	public Result addBankAcctInfo(BankAcctDTO bankAcctDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("addBankAcctInfo entry: bankAcctDTO={}",
				   new Object[] {jsonUtils.toJson(bankAcctDTO)});
		try {
			result = (Result) getBankAcctManager().insertBankAcct(bankAcctDTO);
			Long bankAcctId = (Long)result.getData();
			logger.info("addBankAcctInfo success: bankAcctId={}, costTime={}ms",
					   new Object[] { bankAcctId,System.currentTimeMillis() - stime });
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
	public Result deleteBankAcctInfo(BankAcctDTO bankAcctDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("deleteBankAcctInfo entry: bankAcctDTO={}",
				   new Object[] {jsonUtils.toJson(bankAcctDTO)});
		try {
			result = (Result) getBankAcctManager().deleteBankAcct(bankAcctDTO);
			logger.info("deleteBankAcctInfo success: bankAcctId={}, costTime={}ms",
					   new Object[] { bankAcctDTO.getBankAcctId(),System.currentTimeMillis() - stime });
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
	public Result updateBankAcctInfo(BankAcctDTO bankAcctDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("updateBankAcctInfo entry: bankAcctDTO={}",
				   new Object[] {jsonUtils.toJson(bankAcctDTO)});
		try {
			result = (Result) getBankAcctManager().updateBankAcct(bankAcctDTO);
			logger.info("updateBankAcctInfo success: bankAcctId={}, costTime={}ms",
					   new Object[] { bankAcctDTO.getBankAcctId(),System.currentTimeMillis() - stime });
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
	public Result getBankAcctListInfo(BankAcctDTO bankAcctDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("getBankAcctListInfo entry: bankAcctDTO={}",
				   new Object[] {jsonUtils.toJson(bankAcctDTO)});
		try {
			result = (Result) getBankAcctManager().listBankAcct(bankAcctDTO);
			List<BankAcctDTO> resultList = (List<BankAcctDTO>) result.getData();
			logger.info("getBankAcctListInfo success: count={},costTime={}ms",
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
	public Result findBankAcctDetailInfo(BankAcctDTO bankAcctDTO){
		long stime = System.currentTimeMillis();
		Result result = null;
		logger.info("findBankAcctDetailInfo entry: bankAcctDTO={}",
				   new Object[] {jsonUtils.toJson(bankAcctDTO)});
		try {
			result = (Result) getBankAcctManager().findBankAcct(bankAcctDTO);
			bankAcctDTO = (BankAcctDTO)result.getData();
			logger.info("findBankAcctDetailInfo success: bankAcctDTO={}, costTime={}ms",
					   new Object[] { jsonUtils.toJson(bankAcctDTO),System.currentTimeMillis() - stime });
		} catch (ParameterException e) {
			result = ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			result = ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			result = ExceptionProcessor.getExceptionResult(e);
		}
		return result;
	}

	public BankAcctManager getBankAcctManager() {
		return bankAcctManager;
	}

	@Autowired
	public void setBankAcctManager(BankAcctManager bankAcctManager) {
		this.bankAcctManager = bankAcctManager;
	}
	
	private BankAcctManager bankAcctManager;
	private static final Logger logger = LoggerFactory.getLogger(BankAcctFacadeImpl.class);
	private JsonUtils jsonUtils = new JsonUtils();
}
