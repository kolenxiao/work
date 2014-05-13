package com.xiu.uuc.manager.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.util.BeanUtilEx;
import com.xiu.uuc.common.util.KeyNames;
import com.xiu.uuc.common.util.StringUtils;
import com.xiu.uuc.core.service.ChangePayService;
import com.xiu.uuc.dal.param.TractParam;
import com.xiu.uuc.dal.po.AcctChangePO;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.dal.po.TractPO;
import com.xiu.uuc.dal.po.UserAcctPO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.TractDTO;
import com.xiu.uuc.facade.dto.TractQueryDTO;
import com.xiu.uuc.facade.dto.TradeDTO;
import com.xiu.uuc.facade.util.ErrorCodeConstant;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.Page;
import com.xiu.uuc.facade.util.TypeEnum;
import com.xiu.uuc.manager.ChangePayManager;
import com.xiu.uuc.manager.validate.ChangePayManagerValidate;

public class ChangePayManagerImpl implements ChangePayManager {

	@Override
	public Result incomeAccounts(TradeDTO tradeDTO) throws Exception {
		
		//退款参数合法性交易
		ChangePayManagerValidate.validateIncomeOrOutcomeAccounts(tradeDTO);
		
		//设置账目进出标志为 入账（01）
		tradeDTO.setIoType(TypeEnum.acctItemIOType.ACCTITEM_INTO.getKey());
		
		//设置账目类型为 换货金(04)
		tradeDTO.setAcctTypeCode(TypeEnum.AcctItemType.CHANGE_PAY.getKey());
		
		//设置业务类型  退/换货(va1107)
		tradeDTO.setBusiType(TypeEnum.virtualAccountBusiType.RETURN_CHANGE_GOODS.getKey());
		
		//根据用户ID获取用户账目信息
		AcctItemPO acctItemPO = changePayService.getAcctItemPoByUserIdAndAcctTypeCode(tradeDTO.getUserId(),tradeDTO.getAcctTypeCode());

		//处理账目信息
		Long acctItemId = processAcctItemAtIncomeAccounts(tradeDTO, acctItemPO);

		//插入账目变更流水明细
		Long accountChangeId = insertAcctChangeAtIncomeAccounts(tradeDTO,acctItemPO, acctItemId);
		
		//插入账目变更轨迹
		insertAcctTract(tradeDTO, accountChangeId);

		return new Result(FacadeConstant.SUCCESS, tradeDTO.getRltId());
	}

	@Override
	public Result outcomeAccounts(TradeDTO tradeDTO) throws Exception {
		
		//扣款参数合法性交易
		ChangePayManagerValidate.validateIncomeOrOutcomeAccounts(tradeDTO);
		
		//设置账目进出标志为 出账（02）
		tradeDTO.setIoType(TypeEnum.acctItemIOType.ACCTITEM_OUT.getKey());
		
		//设置账目类型为 04 换货金
		tradeDTO.setAcctTypeCode(TypeEnum.AcctItemType.CHANGE_PAY.getKey());
		
		//设置业务类型  退/换货(va1107)
		tradeDTO.setBusiType(TypeEnum.virtualAccountBusiType.RETURN_CHANGE_GOODS.getKey());
		
		//用户账户信息和账目信息 逻辑验证
		AcctItemPO acctItemPO = checkUserAcctAndAcctItem(tradeDTO);
		
		//更新账目信息
		updateAcctItemAtOutcomeAccounts(tradeDTO, acctItemPO);	
		
		//插入账目流水
		Long accountChangeId = insertAcctChangeAtOutcomeAccounts(tradeDTO,acctItemPO);
		
		//插入账目变更轨迹
		insertAcctTract(tradeDTO, accountChangeId);
		
		return new Result(FacadeConstant.SUCCESS, tradeDTO);
	}

	@Override
	public Result getTractRefundDetailList(TractQueryDTO tractQueryDTO) throws Exception {
		
		if (org.apache.commons.lang.StringUtils.isBlank(String.valueOf(tractQueryDTO.getOldRltId()))
			 && org.apache.commons.lang.StringUtils.isBlank(String.valueOf(tractQueryDTO.getNewRltId())) 
			 && org.apache.commons.lang.StringUtils.isBlank(tractQueryDTO.getRefundId())
			 && org.apache.commons.lang.StringUtils.isBlank(tractQueryDTO.getPayMode())
		     && org.apache.commons.lang.StringUtils.isBlank(tractQueryDTO.getResCode())
		     && org.apache.commons.lang.StringUtils.isBlank(tractQueryDTO.getIoType())
		     && StringUtils.isNullObject(tractQueryDTO.getBegTime())
		     && StringUtils.isNullObject(tractQueryDTO.getEndTime()) ) {
			return new Result(FacadeConstant.SUCCESS,new ArrayList<TractDTO>());
		}
		
		TractParam tractParam = new TractParam();
		BeanUtilEx.copyProperties(tractParam, tractQueryDTO);
		
		// 获取满足条件账目记录总记录数
		int currentPage = tractParam.getCurrentPage();
		int totalRecord = changePayService.getTractRefundDetailListCount(tractParam);
		int pageSize = tractParam.getPageSize();
		
		// 计算出起始行和结束行，page对象
		Page page = Page.getPage(currentPage, totalRecord, pageSize); 
		tractParam.setFirstRow(page.getStartRec());
		tractParam.setLastRow(page.getEndRec());
		
		List<TractDTO> tractDTOList = new ArrayList<TractDTO>();
		for(TractPO tractPO : changePayService.getTractRefundDetailList(tractParam)){
			TractDTO tractDTO = new TractDTO();
			BeanUtilEx.copyProperties(tractDTO, tractPO);
			tractDTOList.add(tractDTO);
		}
		
		return new Result(FacadeConstant.SUCCESS,tractDTOList);
		
	}

	@Override
	public Result getTractRefundNotEndDetailList(TractQueryDTO tractQueryDTO)throws Exception {
		
		if (org.apache.commons.lang.StringUtils.isBlank(String.valueOf(tractQueryDTO.getOldRltId()))
			 && org.apache.commons.lang.StringUtils.isBlank(tractQueryDTO.getRefundId())
		     && StringUtils.isNullObject(tractQueryDTO.getBegTime())
		     && StringUtils.isNullObject(tractQueryDTO.getEndTime()) ) {
			return new Result(FacadeConstant.SUCCESS,new ArrayList<TractDTO>());
		}
		
		TractParam tractParam = new TractParam();
		BeanUtilEx.copyProperties(tractParam, tractQueryDTO);
		
		// 获取满足条件账目记录总记录数
		int currentPage = tractParam.getCurrentPage();
		int totalRecord = changePayService.getTractRefundNotEndDetailListCount(tractParam);
		int pageSize = tractParam.getPageSize();
		
		// 计算出起始行和结束行，page对象
		Page page = Page.getPage(currentPage, totalRecord, pageSize); 
		tractParam.setFirstRow(page.getStartRec());
		tractParam.setLastRow(page.getEndRec());
		
		List<TractDTO> tractDTOList = new ArrayList<TractDTO>();
		for(TractPO tractPO : changePayService.getTractRefundNotEndDetailList(tractParam)){
			TractDTO tractDTO = new TractDTO();
			BeanUtilEx.copyProperties(tractDTO, tractPO);
			tractDTOList.add(tractDTO);
		}
		
		return new Result(FacadeConstant.SUCCESS,tractDTOList);
	}
	
	//退货退款入账
	//处理账目信息
	private Long processAcctItemAtIncomeAccounts(TradeDTO tradeDTO,AcctItemPO acctItemPO) {
		
		AcctItemPO curAcctItemPO = new AcctItemPO();
		Long acctItemId = 0L;
		
		//若账目表中不存在换货支付类型的账目记录的时候，则新增一条账目记录
		if (acctItemPO == null) {
			UserAcctPO userAcctPO = changePayService.getUserAcctPOByUserId(tradeDTO.getUserId());
			//用户对应的用户账户信息不存在
			if (userAcctPO == null) {
				throw new BusinessException(ErrorCodeConstant.USER_ACCT_NOT_FOUND_MESSAGE);
			} else {
				//用户对应的用户账户信息存在，账目信息不存在的时候，插入账目信息
				acctItemId = insertAcctItemAtIncomeAccounts(tradeDTO,curAcctItemPO, userAcctPO);
			}
		} else {
		    //若账目表中存在换货支付类型的账目记录的时候，则更新账目总金额
			updateAcctItemAtIncomeAccounts(tradeDTO, acctItemPO, curAcctItemPO);
		}
		return acctItemId;
	}
	
	//退货退款入账
	//插入账目变更流水明细
	private Long insertAcctChangeAtIncomeAccounts(TradeDTO tradeDTO,AcctItemPO acctItemPO, Long acctItemId)throws IllegalAccessException, InvocationTargetException {
		Long accountChangeId = 0L;
		AcctChangePO acctChangePO = new AcctChangePO();
		BeanUtilEx.copyProperties(acctChangePO, tradeDTO);
		Long newAcctItemId = (acctItemId > 0) ? acctItemId : acctItemPO.getAcctItemId();
		acctChangePO.setAcctItemId(newAcctItemId);
		acctChangePO.setHistoryIoAmount(0L);
		acctChangePO.setIoAmount(tradeDTO.getTotalAmount());
		acctChangePO.setLastIoAmount(tradeDTO.getTotalAmount());
		acctChangePO.setOperMode(tradeDTO.getOperMode());
		acctChangePO.setIoType(tradeDTO.getIoType());
		accountChangeId = changePayService.insertAcctChange(acctChangePO);
		return accountChangeId;
	}
	
	//退货退款入账
	//若账目表中存在换货支付类型的账目记录的时候，则更新账目总金额
	private void updateAcctItemAtIncomeAccounts(TradeDTO tradeDTO,AcctItemPO acctItemPO, AcctItemPO curAcctItemPO) {
		Long oldTotalMoney = acctItemPO.getTotalAmount();
		Long incomeTotalMoney = tradeDTO.getTotalAmount();
		curAcctItemPO.setAcctItemId(acctItemPO.getAcctItemId());
		curAcctItemPO.setTotalAmount(oldTotalMoney + incomeTotalMoney);
		curAcctItemPO.setOperId(acctItemPO.getOperId());
		curAcctItemPO.setDataVersion(acctItemPO.getDataVersion());
		changePayService.updateAcctItem(curAcctItemPO);
	}
	
	//退货退款入账
	//用户对应的用户账户信息存在，账目信息不存在的时候，插入账目信息
	private Long insertAcctItemAtIncomeAccounts(TradeDTO tradeDTO,AcctItemPO curAcctItemPO, UserAcctPO userAcctPO) {
		Long acctItemId;
		curAcctItemPO.setAcctId(userAcctPO.getAcctId());
		curAcctItemPO.setAcctTypeCode(tradeDTO.getAcctTypeCode());
		curAcctItemPO.setTotalAmount(tradeDTO.getTotalAmount());
		curAcctItemPO.setFreezeAmount(0L);
		curAcctItemPO.setDataVersion(0L);
		curAcctItemPO.setAcctItemState(KeyNames.ACCT_ITEM_STATE_NATURAL);
		curAcctItemPO.setOperId(tradeDTO.getOperId());
		acctItemId = changePayService.insertAcctItemPO(curAcctItemPO);
		return acctItemId;
	}
	
	//换货支付出账
	//用户账户信息和账目信息 逻辑验证
	private AcctItemPO checkUserAcctAndAcctItem(TradeDTO tradeDTO) {

		Long userId = tradeDTO.getUserId();
		String acctTypeCode = tradeDTO.getAcctTypeCode();
		UserAcctPO userAcctPO = changePayService.getUserAcctPOByUserId(userId);

		// 用户账户不存在
		if (userAcctPO == null) {
			throw new BusinessException(ErrorCodeConstant.USER_ACCT_NOT_FOUND_MESSAGE);
		}

		// 用户账户被冻结
		if (KeyNames.ACCT_STATE_FROZEN.equals(userAcctPO.getAcctState())) {
			throw new BusinessException(ErrorCodeConstant.ACCT_STATE_FROZEN_MESSAGE);
		}

		// 用户对应的 换货支付类型 账目信息是不存在
		AcctItemPO acctItemPO = changePayService.getAcctItemPoByUserIdAndAcctTypeCode(userId, acctTypeCode);
		if (acctItemPO == null) {
			throw new BusinessException(ErrorCodeConstant.USER_ACCT_ITEM_NOT_FOUND_MESSAGE);
		}

		// 用户对应的 换货支付类型 账目信息是存在 且换货金总额大于0
		Long totalAmount = acctItemPO.getTotalAmount() != null ? acctItemPO.getTotalAmount() : 0L;
		Long freezeAmount = acctItemPO.getFreezeAmount() != null ? acctItemPO.getFreezeAmount() : 0L;
		Long tradeTotalAmount = tradeDTO != null ? tradeDTO.getTotalAmount(): 0L;

		if ((totalAmount - freezeAmount) - tradeTotalAmount >= 0) {

			// 查询换货支付账户中当前订单对应的各支付方式所剩余额 单位：分
			// 当前订单对应的各支付方式待扣额度
			List<TractDTO> oldTractPOList = tradeDTO.getTractDTOList();
			Long oldRltId = oldTractPOList != null && oldTractPOList.size() > 0 ? oldTractPOList.get(0).getOldRltId(): 0L;
			TractParam tractParam = new TractParam();
			tractParam.setOldRltId(oldRltId); // 获取老订单Id
			List<TractPO> newTractPOList = changePayService.getTractRefundNotEndDetailList(tractParam);

			if (newTractPOList != null && oldTractPOList != null) {

				// 将从数据库中获取的最新换货支付账户中当前订单对应的各支付方式所剩余额，放入map中
				Map<String, TractPO> newTractPOListMap = new HashMap<String, TractPO>();
				if (newTractPOList != null && newTractPOList.size() > 0) {
					for (TractPO tractPO : newTractPOList) {
						StringBuffer newKeySb = new StringBuffer();
						Long newOldRltId = tractPO.getOldRltId() != null ? tractPO.getOldRltId(): 0L;
						String newRefundId = tractPO.getRefundId() != null
								&& !"".equals(tractPO.getRefundId()) ? tractPO.getRefundId() : "#";
						String newPayMode = tractPO.getPayMode() != null
								&& !"".equals(tractPO.getPayMode()) ? tractPO.getPayMode() : "*";
						String newRltSeq = tractPO.getRltSeq() != null
								&& !"".equals(tractPO.getRltSeq()) ? tractPO.getRltSeq() : "$";
						String newResCode = tractPO.getResCode() != null
								&& !"".equals(tractPO.getResCode()) ? tractPO.getResCode() : "@";
						newKeySb.append(newOldRltId).append(newRefundId)
								.append(newPayMode).append(newRltSeq).append(newResCode);
						newTractPOListMap.put(newKeySb.toString(), tractPO);
						logger.info("checkUserAcctAndAcctItem entry: newKeySbFromDB={}",new Object[] { newKeySb.toString() });
					}
				}

				// 判断当次支付金额是否足够支付,有一个不够支付 本次支付失败
				if (oldTractPOList != null && oldTractPOList.size() > 0) {
					for (TractDTO oldTractDTO : oldTractPOList) {
						StringBuffer oldKeySb = new StringBuffer();
						Long oldOldRltId = oldTractDTO.getOldRltId() != null ? oldTractDTO.getOldRltId(): 0L;
						String oldRefundId = oldTractDTO.getRefundId() != null
								&& !"".equals(oldTractDTO.getRefundId()) ? oldTractDTO.getRefundId(): "#";
						String oldPayMode = oldTractDTO.getPayMode() != null
								&& !"".equals(oldTractDTO.getPayMode()) ? oldTractDTO.getPayMode(): "*";
						String oldRltSeq = oldTractDTO.getRltSeq() != null
								&& !"".equals(oldTractDTO.getRltSeq()) ? oldTractDTO.getRltSeq(): "$";
						String oldResCode = oldTractDTO.getResCode() != null
								&& !"".equals(oldTractDTO.getResCode()) ? oldTractDTO.getResCode(): "@";
						oldKeySb.append(oldOldRltId).append(oldRefundId)
								.append(oldPayMode).append(oldRltSeq).append(oldResCode);
						logger.info("checkUserAcctAndAcctItem entry: oldKeySbFromParm={}",new Object[] { oldKeySb.toString() });
						TractPO newTractPO = newTractPOListMap.get(oldKeySb.toString());
						if (newTractPO == null) {
							throw new BusinessException(ErrorCodeConstant.ACCT_UNBALB_MONEY_NO_ENOUGH_MESSAGE);
						} else {
							// 当前订单对应的各支付方式待扣额度 > 查询换货支付账户中当前订单对应的各支付方式所剩余额 单位：分
							Long ioAmount = oldTractDTO.getIoAmount() != null ? oldTractDTO.getIoAmount(): 0L;
							Long sumIoAmount = newTractPO.getSumIoAmount() != null ? newTractPO.getSumIoAmount(): 0L;
							if (ioAmount > sumIoAmount) {
								logger.error("checkUserAcctAndAcctItem entry : no enough acctItem total money: ioAmount={},sumIoAmount={}",
												new Object[] { ioAmount,sumIoAmount });
								throw new BusinessException(ErrorCodeConstant.ACCT_UNBALB_MONEY_NO_ENOUGH_MESSAGE);
							}
						}
					}
				}

			}

		} else {
			logger.error("checkUserAcctAndAcctItem entry :no enough acctItem total money");
			throw new BusinessException(ErrorCodeConstant.ACCT_UNBALB_MONEY_NO_ENOUGH_MESSAGE);
		}
		return acctItemPO;
	}
	
	//换货支付出账
	//更新账目信息
	private void updateAcctItemAtOutcomeAccounts(TradeDTO tradeDTO,AcctItemPO acctItemPO) {
		AcctItemPO curAcctItemPO = new AcctItemPO();
		curAcctItemPO.setAcctItemId(acctItemPO.getAcctItemId());
		curAcctItemPO.setTotalAmount(acctItemPO.getTotalAmount() - tradeDTO.getTotalAmount());
		curAcctItemPO.setOperId(acctItemPO.getOperId());
		curAcctItemPO.setDataVersion(acctItemPO.getDataVersion());
		changePayService.updateAcctItem(curAcctItemPO);
	}
	
	//换货支付出账
	//插入账目流水
	private Long insertAcctChangeAtOutcomeAccounts(TradeDTO tradeDTO,AcctItemPO acctItemPO) throws IllegalAccessException,InvocationTargetException {
		Long accountChangeId = 0L;
		AcctChangePO acctChangePO = new AcctChangePO();
		BeanUtilEx.copyProperties(acctChangePO, tradeDTO);
		Long newAcctItemId = acctItemPO.getAcctItemId();
		acctChangePO.setAcctItemId(newAcctItemId);
		acctChangePO.setHistoryIoAmount(0L);
		acctChangePO.setIoAmount(tradeDTO.getTotalAmount());
		acctChangePO.setLastIoAmount(tradeDTO.getTotalAmount());
		acctChangePO.setOperMode(tradeDTO.getOperMode());
		acctChangePO.setIoType(tradeDTO.getIoType());
		accountChangeId = changePayService.insertAcctChange(acctChangePO);
		return accountChangeId;
	}
	
	//插入账目变更轨迹
	private void insertAcctTract(TradeDTO tradeDTO, Long accountChangeId)throws IllegalAccessException, InvocationTargetException {
		for (TractDTO tractDTO : tradeDTO.getTractDTOList()) {
			TractPO tractPO = new TractPO();
			BeanUtilEx.copyProperties(tractPO, tractDTO);
			tractPO.setAccountChangeId(accountChangeId);
			tractPO.setIoType(tradeDTO.getIoType());
			changePayService.insertAcctTract(tractPO);
		}
	}
	
	@Autowired
	public void setChangePayService(ChangePayService changePayService) {
		this.changePayService = changePayService;
	}

	private ChangePayService changePayService;
	//private JsonUtils jsonUtils = new JsonUtils();
	private static final Logger logger = LoggerFactory.getLogger(ChangePayManagerImpl.class);

}
