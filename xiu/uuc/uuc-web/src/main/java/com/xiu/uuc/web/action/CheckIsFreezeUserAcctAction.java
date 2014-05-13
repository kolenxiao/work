package com.xiu.uuc.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.common.util.StringUtils;
import com.xiu.uuc.facade.AcctChangeFacade;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserAcctDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.web.action.base.BaseAction;

public class CheckIsFreezeUserAcctAction extends BaseAction implements ModelDriven<UserAcctDTO>{
	
	public String checkIsFreezeUserAcct() throws Exception {
		if(!StringUtils.isNullObject(userAcctDTO)){
			Result result = acctChangeFacade.checkIsFreezeUserAcct(userAcctDTO);
			if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
				setResult("IsFreezeUserAcct",result.getData());
				return SUCCESS;
			}else{
				addActionError(result.getErrorMessage());
				return ERROR;
			}
		}
		return ERROR;
	}
	
	@Autowired
	public void setAcctChangeFacade(AcctChangeFacade acctChangeFacade) {
		this.acctChangeFacade = acctChangeFacade;
	}

	@Override
	public UserAcctDTO getModel() {
		return userAcctDTO;
	}
	
	private static final long serialVersionUID = 8874503944126563034L;
	private AcctChangeFacade acctChangeFacade;
	private UserAcctDTO userAcctDTO = new UserAcctDTO();
}
