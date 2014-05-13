package com.xiu.uuc.web.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiu.uuc.common.util.StringUtils;
import com.xiu.uuc.core.service.AbnormalLogService;
import com.xiu.uuc.facade.AcctChangeFacade;
import com.xiu.uuc.facade.AcctItemFacade;
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.IntegeralRuleDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserAcctDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.web.action.base.BaseAction;

public class AddVirtualAccountAction extends BaseAction {
	
	public String addVirtualAccountMoney() throws Exception {
		if(!StringUtils.isNullObject(acctItemDTO) && !StringUtils.isNullObject(acctChangeDTO)){
			Result result = acctItemFacade.addVirtualAccountMoney(acctItemDTO, acctChangeDTO);
			if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
				//auditAccountPayLimitProcess();
				setResult("count",result.getData());
				return SUCCESS;
			}else{
				addActionError(result.getErrorMessage());
				return ERROR;
			}
		}
		return ERROR;
	}
	
	public String decVirtualAccountMoney() throws Exception {
		if(!StringUtils.isNullObject(acctItemDTO) && !StringUtils.isNullObject(acctChangeDTO)){
			Result result = acctItemFacade.decVirtualAccountMoney(acctItemDTO, acctChangeDTO);
			if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
				setResult("count",result.getData());
				return SUCCESS;
			}else{
				addActionError(result.getErrorMessage());
				return ERROR;
			}
		}
		return ERROR;
	}
	
	public String decVirtualAccountMoneyByItemTypeCode() throws Exception {
		if(!StringUtils.isNullObject(acctItemDTO) && !StringUtils.isNullObject(acctChangeDTO)){
			Result result = acctItemFacade.decVirtualAccountMoneyByItemTypeCode(acctItemDTO, acctChangeDTO);
			if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
				setResult("count",result.getData());
				return SUCCESS;
			}else{
				addActionError(result.getErrorMessage());
				return ERROR;
			}
		}
		return ERROR;
	}
	
	public String addVirtualAccountIntegral() throws Exception {
		if(!StringUtils.isNullObject(acctItemDTO) && !StringUtils.isNullObject(acctChangeDTO)){
			integeralRuleDTO = getIntegeralRuleDTO();
			Result result = acctItemFacade.addVirtualAccountIntegral(acctItemDTO, acctChangeDTO, integeralRuleDTO);
			if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
				setResult("count",result.getData());
				return SUCCESS;
			}else{
				addActionError(result.getErrorMessage());
				return ERROR;
			}
		}
		return ERROR;
	}
	
	public String decVirtualAccountIntegral() throws Exception {
		if(!StringUtils.isNullObject(acctItemDTO) && !StringUtils.isNullObject(acctChangeDTO)){
			integeralRuleDTO = getIntegeralRuleDTO();
			Result result = acctItemFacade.decVirtualAccountIntegral(acctItemDTO, acctChangeDTO, integeralRuleDTO);
			if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
				setResult("count",result.getData());
				return SUCCESS;
			}else{
				addActionError(result.getErrorMessage());
				return ERROR;
			}
		}
		return ERROR;
	}
	
	public String setUserAcctFreeze() throws Exception {
		if(!StringUtils.isNullObject(userAcctDTO)){
			Result result = acctChangeFacade.setUserAcctFreeze(userAcctDTO);
			if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
				setResult("count",result.getData());
				return SUCCESS;
			}else{
				addActionError(result.getErrorMessage());
				return ERROR;
			}
		}
		return ERROR;
	}
	
	public String setUserAcctUnFreeze() throws Exception {
		if(!StringUtils.isNullObject(userAcctDTO)){
			Result result = acctChangeFacade.setUserAcctUnFreeze(userAcctDTO);
			if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
				setResult("count",result.getData());
				return SUCCESS;
			}else{
				addActionError(result.getErrorMessage());
				return ERROR;
			}
		}
		return ERROR;
	}
	
	public String setUserAcctItemFreezeMoney() throws Exception {
		if(!StringUtils.isNullObject(acctItemDTO) && !StringUtils.isNullObject(acctChangeDTO)){
			Result result = acctChangeFacade.setUserAcctItemFreezeMoney(acctItemDTO, acctChangeDTO);
			if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
				setResult("count",result.getData());
				return SUCCESS;
			}else{
				addActionError(result.getErrorMessage());
				return ERROR;
			}
		}
		return ERROR;
	}
	
	
	@SuppressWarnings("unused")
	private void auditAccountPayLimitProcess() throws IllegalAccessException,InvocationTargetException {
		abnormalLogService.auditAccountPayLimit(acctItemDTO);
	}
	
	public AcctItemFacade getAcctItemFacade() {
		return acctItemFacade;
	}
	public void setAcctItemFacade(AcctItemFacade acctItemFacade) {
		this.acctItemFacade = acctItemFacade;
	}
	public AcctItemDTO getAcctItemDTO() {
		return acctItemDTO;
	}
	public void setAcctItemDTO(AcctItemDTO acctItemDTO) {
		this.acctItemDTO = acctItemDTO;
	}
	public AcctChangeDTO getAcctChangeDTO() {
		return acctChangeDTO;
	}
	public void setAcctChangeDTO(AcctChangeDTO acctChangeDTO) {
		this.acctChangeDTO = acctChangeDTO;
	}
	
	public UserAcctDTO getUserAcctDTO() {
		return userAcctDTO;
	}

	public void setUserAcctDTO(UserAcctDTO userAcctDTO) {
		this.userAcctDTO = userAcctDTO;
	}
	
	public AcctChangeFacade getAcctChangeFacade() {
		return acctChangeFacade;
	}

	public void setAcctChangeFacade(AcctChangeFacade acctChangeFacade) {
		this.acctChangeFacade = acctChangeFacade;
	}
	
	@SuppressWarnings("unchecked")
	public IntegeralRuleDTO getIntegeralRuleDTO() {
		if (StringUtils.isNullObject(integeralRuleDTO)) {
			Map map = new HashMap();
			map.put("p1", 1);
			map.put("p2", 1);
			integeralRuleDTO = new IntegeralRuleDTO("004", map);
		}
		return integeralRuleDTO;
	}
	
	@Autowired
	public void setAbnormalLogService(AbnormalLogService abnormalLogService) {
		this.abnormalLogService = abnormalLogService;
	}
	
	private static final long serialVersionUID = 8874503944126563034L;
	private AcctItemFacade acctItemFacade;
	private AcctChangeFacade acctChangeFacade;
	private AcctItemDTO acctItemDTO; 
	private AcctChangeDTO acctChangeDTO;
	private IntegeralRuleDTO integeralRuleDTO;
	private UserAcctDTO userAcctDTO;
	private AbnormalLogService abnormalLogService;
}
