package com.xiu.uuc.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.facade.AcctChangeFacade;
import com.xiu.uuc.facade.dto.AcctChangeDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.web.action.base.BaseAction;

public class AcctChangeAction  extends BaseAction implements ModelDriven<AcctChangeDTO>{

	public String getVirtualChangeList() throws Exception {
		Result result = acctChangeFacade.getVirtualChangeList(acctChangeDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("virtualChangeList",result.getData());
			return SUCCESS;
		}else{
			setErrorReason(result.getErrorMessage());
			return ERROR;
		}
	}

	@Autowired
	public void setAcctChangeFacade(AcctChangeFacade acctChangeFacade) {
		this.acctChangeFacade = acctChangeFacade;
	}

	@Override
	public AcctChangeDTO getModel() {
		return acctChangeDTO;
	}
	
	private static final long serialVersionUID = 8874503944126563034L;

	private AcctChangeFacade acctChangeFacade;
	
	private AcctChangeDTO acctChangeDTO = new AcctChangeDTO();
}
