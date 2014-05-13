package com.xiu.uuc.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.facade.AcctItemFacade;
import com.xiu.uuc.facade.dto.AcctItemDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.web.action.base.BaseAction;

public class AcctItemAction  extends BaseAction implements ModelDriven<AcctItemDTO>{

	public String getVirtualItemList() throws Exception {
		Result result = acctItemFacade.getVirtualItemList(acctItemDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("virtualItemList",result.getData());
			return SUCCESS;
		}else{
			setErrorReason(result.getErrorMessage());
			return ERROR;
		}
	}

	@Autowired
	public void setAcctItemFacade(AcctItemFacade acctItemFacade) {
		this.acctItemFacade = acctItemFacade;
	}

	@Override
	public AcctItemDTO getModel() {
		return acctItemDTO;
	}
	
	private static final long serialVersionUID = 8874503944126563034L;

	private AcctItemFacade acctItemFacade;
	
	private AcctItemDTO acctItemDTO = new AcctItemDTO();
}
