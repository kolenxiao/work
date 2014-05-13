package com.xiu.uuc.manager.validate;

import java.util.List;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.CommonValidationUtil;
import com.xiu.uuc.common.util.StringUtils;
import com.xiu.uuc.facade.dto.TractDTO;
import com.xiu.uuc.facade.dto.TradeDTO;

public class ChangePayManagerValidate {
	
	/**
	 * @Title: validateIncomeOrOutcomeAccounts 
	 * @Description: 
	 *               1.检查退货退款入帐接口输入参数合法性校验
	 *               2.检查换货扣款出账接口输入参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	public static void validateIncomeOrOutcomeAccounts(TradeDTO tradeDTO) {
		validateTradeDTO(tradeDTO);
		validateTractDTOList(tradeDTO);
	}

	/**
	 * @Title: validateTradeDTO 
	 * @Description: 检查交易参数合法性校验
	 * @return void    返回类型 
	 * @throws
	 */
	private static void validateTradeDTO(TradeDTO tradeDTO) {
		if(StringUtils.isNullObject(tradeDTO)){
			throw new ParameterException("tradeDTO对象不允许为空");
		}
		if(StringUtils.isNullObject(tradeDTO.getRltId())){
			throw new ParameterException("tradeDTO.rltId不允许为空");
		}
		if(StringUtils.isNullObject(tradeDTO.getRltSeq())){
			throw new ParameterException("tradeDTO.rltSeq不允许为空");
		}
		if(StringUtils.isNullObject(tradeDTO.getTotalAmount())){
			throw new ParameterException("tradeDTO.totalAmount不允许为空");
		}
		if(!CommonValidationUtil.isInteger(tradeDTO.getTotalAmount().toString())){
			throw new ParameterException("tradeDTO.totalAmount必须为大于零的整数");
		}
		if(StringUtils.isNullObject(tradeDTO.getRltChannelId())){
			throw new ParameterException("tradeDTO.rltChannelId不允许为空");
		}
		if(StringUtils.isNullObject(tradeDTO.getOperId())){
			throw new ParameterException("tradeDTO.operId不允许为空");
		}
		if(StringUtils.isNullObject(tradeDTO.getOperMode())){
			throw new ParameterException("tradeDTO.operMode不允许为空");
		}
		if(StringUtils.isNullObject(tradeDTO.getUserId())){
			throw new ParameterException("tradeDTO.userId不允许为空");
		}
	}

	/**
	 * @Title: validateTractDTOList 
	 * @Description: 检查轨迹列表参数合法性
	 * @return void    返回类型 
	 * @throws
	 */
	private static void validateTractDTOList(TradeDTO tradeDTO) {
		if (!StringUtils.isNullObject(tradeDTO)) {
			List<TractDTO> tractDTOList = tradeDTO.getTractDTOList();
			if (StringUtils.isNullObject(tractDTOList)) {
				throw new ParameterException("tradeDTO.tractDTOList不允许为空");
			}
			if (tractDTOList.size() <= 0) {
				throw new ParameterException("tradeDTO.tractDTOList不允许为空");
			}
			for (int i = 0; i < tractDTOList.size(); i++) {
				TractDTO tractDTO = tractDTOList.get(i);
				if (StringUtils.isNullObject(tractDTO.getPayMode())) {
					throw new ParameterException("tradeDTO.tractDTOList.tractDTO["+i+"].payMode不允许为空");
				}
				if (StringUtils.isNullObject(tractDTO.getRefundId())) {
					throw new ParameterException("tradeDTO.tractDTOList.tractDTO["+i+"].refundId不允许为空");
				}
				if (StringUtils.isNullObject(tractDTO.getIoAmount())) {
					throw new ParameterException("tradeDTO.tractDTOList.tractDTO["+i+"].ioAmount不允许为空");
				}
				if (StringUtils.isNullObject(tractDTO.getOldRltId())) {
					throw new ParameterException("tradeDTO.tractDTOList.tractDTO["+i+"].oldRltId不允许为空");
				}
				/*if (StringUtils.isNullObject(tractDTO.getRltSeq())) {
					throw new ParameterException("tradeDTO.tractDTOList.tractDTO["+i+"].rltSeq不允许为空");
				}
				if (StringUtils.isNullObject(tractDTO.getNewRltId())) {
				throw new ParameterException("tradeDTO.tractDTOList.tractDTO["+i+"].newRltId不允许为空");
			    }*/
			}
		}
	}

}
