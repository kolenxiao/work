package com.xiu.uuc.manager.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.uuc.common.util.CommonEnum;
import com.xiu.uuc.common.util.JsonUtils;
import com.xiu.uuc.common.util.KeyNames;
import com.xiu.uuc.core.service.LogService;
import com.xiu.uuc.dal.po.AcctChangePO;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.facade.dto.BusiLogDTO;
import com.xiu.uuc.facade.dto.BusiLogQueryDTO;
import com.xiu.uuc.facade.dto.BusiTypeDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.Page;
import com.xiu.uuc.facade.util.TypeEnum;
import com.xiu.uuc.manager.LogManager;

public class LogManagerImpl implements LogManager {

	@Override
	public Result queryBusiLogList(BusiLogQueryDTO busiLogQueryDTO) {		
	    Long totalRecord = logService.countBusiLogList(busiLogQueryDTO);//获取满足条件总记录数
	    int currentPage = busiLogQueryDTO.getCurrentPage();
	    int pageSize = busiLogQueryDTO.getPageSize();
	    Page page = Page.getPage(currentPage, totalRecord.intValue(), pageSize); //计算出起始行和结束行，page对象
	    busiLogQueryDTO.setFirstRow(page.getStartRec());
	    busiLogQueryDTO.setLastRow(page.getEndRec());
	    
	    //查询
		List<BusiLogDTO> dtoList = logService.queryBusiLogList(busiLogQueryDTO);
		for (BusiLogDTO busiLogDTO : dtoList) {
			busiLogDTO.setBusiTypeDesc(CommonEnum.BusiOperType.getList().get(busiLogDTO.getBusiTypeCode()));
			busiLogDTO.setKeyWordTypeDesc(TypeEnum.KeyWordType.getList().get(busiLogDTO.getKeyWordType()));
		}

		//返回结果
		Result result = new Result(FacadeConstant.SUCCESS,dtoList,page);
		return result;
	}
	
	
	@Override
	public List<BusiTypeDTO> queryBusiTypeList(BusiTypeDTO busiTypeDTO) {
		return logService.queryBusiTypeList(busiTypeDTO);
	}
	
	@Override
	public void assetsManagerWriteLog(AcctItemPO acctItemPO, AcctChangePO acctChangePO) throws Exception{
		
		if(checkAcctItemPOAndacctChangePO(acctItemPO,acctChangePO)){
            
			Map<String, Object> beforeContentMap = new LinkedHashMap<String, Object>();
			Map<String, Object> afterContentMap = new LinkedHashMap<String, Object>();
			
			BusiLogDTO busiLogDTO = new BusiLogDTO();
			busiLogDTO.setBusiTypeCode(acctChangePO.getAcctTypeCode());
			busiLogDTO.setCreateTime(new Date());
			busiLogDTO.setOperId(acctChangePO.getOperId());
			if (acctItemPO != null && acctItemPO.getUserId() != null) {
				busiLogDTO.setKeyWord(acctItemPO.getUserId().toString());
				busiLogDTO.setKeyWordType(KeyNames.LogKeyWordType.LOG_KEY_WORD_TYPE_USERID.getKey());
				busiLogDTO.setOperId(acctItemPO.getUserId().toString());
			}else if(acctItemPO != null && acctItemPO.getAcctId() != null){
				busiLogDTO.setKeyWord(acctItemPO.getAcctId().toString());
				busiLogDTO.setKeyWordType(KeyNames.LogKeyWordType.LOG_KEY_WORD_TYPE_ACCTID.getKey());
			}else if(acctItemPO != null && acctItemPO.getAcctItemId() != null){
				busiLogDTO.setKeyWord(acctItemPO.getAcctItemId().toString());
				busiLogDTO.setKeyWordType(KeyNames.LogKeyWordType.LOG_KEY_WORD_TYPE_ACCTITEMID.getKey());
			}
			
			beforeContentMap.put("账户ID", acctItemPO.getAcctId());	
			beforeContentMap.put("账目ID", acctItemPO.getAcctItemId());	
			beforeContentMap.put("账目类型", acctItemPO.getAcctTypeCode());
			beforeContentMap.put("应进出标志", acctChangePO.getIoType());
			//beforeContentMap.put("应出入数量", acctChangePO.getIoAmount());
			buildBeforeContentMap(acctItemPO, acctChangePO, beforeContentMap);
			beforeContentMap.put("应变更前数量", acctChangePO.getHistoryIoAmount());
			beforeContentMap.put("应变更后数量", acctChangePO.getLastIoAmount());	
			busiLogDTO.setBeforeContent(jsonUtils.toJson(beforeContentMap));
			
			afterContentMap.put("账户ID", acctItemPO.getAcctId());	
			afterContentMap.put("账目ID", acctItemPO.getAcctItemId());
			afterContentMap.put("账目类型", acctChangePO.getAcctTypeCode());
			afterContentMap.put("实进出标志", acctChangePO.getIoType());
			//afterContentMap.put("实出入数量", acctChangePO.getIoAmount());
			buildAfterContentMap(acctItemPO, acctChangePO, afterContentMap);
			afterContentMap.put("实变更前数量", acctChangePO.getHistoryIoAmount());
			afterContentMap.put("实变更后数量", acctChangePO.getLastIoAmount());	
			afterContentMap.put("业务流水ID", acctChangePO.getRltId());	
			afterContentMap.put("业务流水CODE", acctChangePO.getRltCode());	
			afterContentMap.put("业务流水SEQ", acctChangePO.getRltSeq());	
			afterContentMap.put("渠道ID", acctChangePO.getRltChannelId());
			afterContentMap.put("操作员ID", acctChangePO.getOperId());	
			busiLogDTO.setAfterContent(jsonUtils.toJson(afterContentMap));
			
			logService.insertBusiLog(busiLogDTO);
			
		}

	}

	private void buildBeforeContentMap(AcctItemPO acctItemPO, AcctChangePO acctChangePO, Map<String, Object> beforeContentMap) {
		if(KeyNames.ACCT_ITEM_WITHDRAWAL_YES.equals(acctItemPO.getAcctTypeCode())
		 || KeyNames.ACCT_ITEM_WITHDRAWAL_NO.equals(acctItemPO.getAcctTypeCode())) {
			if(KeyNames.ACCT_ITEM_IO_TYPE_IN.equals(acctChangePO.getIoType())){
				beforeContentMap.put("应该入账金额", acctChangePO.getIoAmount());	
			}
			if(KeyNames.ACCT_ITEM_IO_TYPE_OUT.equals(acctChangePO.getIoType())){
				beforeContentMap.put("应出账金额", acctChangePO.getIoAmount());	
			}
			if(KeyNames.ACCT_ITEM_IO_TYPE_FREEZE.equals(acctChangePO.getIoType())){
				beforeContentMap.put("应该冻结金额", acctChangePO.getIoAmount());	
			}
			if(KeyNames.ACCT_ITEM_IO_TYPE_UNFREEZE.equals(acctChangePO.getIoType())){
				beforeContentMap.put("应该解冻金额", acctChangePO.getIoAmount());	
			}
		}
		if(KeyNames.ACCT_ITEM_INTEGRAL.equals(acctItemPO.getAcctTypeCode())){
			if(KeyNames.ACCT_ITEM_IO_TYPE_IN.equals(acctChangePO.getIoType())){
				beforeContentMap.put("应该入账积分", acctChangePO.getIoAmount());	
			}
			if(KeyNames.ACCT_ITEM_IO_TYPE_OUT.equals(acctChangePO.getIoType())){
				beforeContentMap.put("应该出账积分", acctChangePO.getIoAmount());	
			}
		}
	}
	
	private void buildAfterContentMap(AcctItemPO acctItemPO, AcctChangePO acctChangePO, Map<String, Object> afterContentMap) {
		if(KeyNames.ACCT_ITEM_WITHDRAWAL_YES.equals(acctItemPO.getAcctTypeCode())
		 || KeyNames.ACCT_ITEM_WITHDRAWAL_NO.equals(acctItemPO.getAcctTypeCode())) {
			if(KeyNames.ACCT_ITEM_IO_TYPE_IN.equals(acctChangePO.getIoType())){
				afterContentMap.put("实际入账金额", acctChangePO.getIoAmount());	
			}
			if(KeyNames.ACCT_ITEM_IO_TYPE_OUT.equals(acctChangePO.getIoType())){
				afterContentMap.put("实际出账金额",  acctChangePO.getIoAmount());	
			}
			if(KeyNames.ACCT_ITEM_IO_TYPE_FREEZE.equals(acctChangePO.getIoType())){
				afterContentMap.put("实际冻结金额",  acctChangePO.getIoAmount());	
			}
			if(KeyNames.ACCT_ITEM_IO_TYPE_UNFREEZE.equals(acctChangePO.getIoType())){
				afterContentMap.put("实际解冻金额",  acctChangePO.getIoAmount());	
			}
		}
		if(KeyNames.ACCT_ITEM_INTEGRAL.equals(acctItemPO.getAcctTypeCode())){
			if(KeyNames.ACCT_ITEM_IO_TYPE_IN.equals(acctChangePO.getIoType())){
				afterContentMap.put("实际入账积分",  acctChangePO.getIoAmount());	
			}
			if(KeyNames.ACCT_ITEM_IO_TYPE_OUT.equals(acctChangePO.getIoType())){
				afterContentMap.put("实际出账积分",  acctChangePO.getIoAmount());	
			}
		}
	}
	
	private boolean checkAcctItemPOAndacctChangePO(AcctItemPO acctItemPO,AcctChangePO acctChangePO) {
		Boolean resultFlag = true; // 表示检查通过
		if (acctItemPO != null && acctChangePO != null) {
			if (acctItemPO.getTotalAmount() == null && acctItemPO.getAcctTypeCode() == null) {
				resultFlag = false;
			}
			if (acctChangePO.getAcctTypeCode() == null && acctChangePO.getBusiType() == null 
			    && acctChangePO.getIoAmount() == null && acctChangePO.getIoType() == null) {
				resultFlag = false;
			}
		}
		return resultFlag;
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	private LogService logService;
	private JsonUtils jsonUtils = new JsonUtils();

}
