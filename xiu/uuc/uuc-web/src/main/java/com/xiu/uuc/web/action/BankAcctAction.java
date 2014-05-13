package com.xiu.uuc.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.facade.BankAcctFacade;
import com.xiu.uuc.facade.dto.BankAcctDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.web.action.base.BaseAction;

public class BankAcctAction extends BaseAction implements ModelDriven<BankAcctDTO> {

	public String addBankAcctInfo() throws Exception {	
		Result result = bankAcctFacade.addBankAcctInfo(bankAcctDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			setErrorReason(result.getErrorMessage());
			return ERROR;
		}
	}
	
	public String deleteBankAcctInfo() throws Exception {
		Result result = bankAcctFacade.deleteBankAcctInfo(bankAcctDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			setErrorReason(result.getErrorMessage());
			return ERROR;
		}
	}

	public String updateBankAcctInfo() throws Exception {
		Result result = bankAcctFacade.updateBankAcctInfo(bankAcctDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			setErrorReason(result.getErrorMessage());
			return ERROR;
		}
	}

	public String findBankAcctDetailInfo() throws Exception {
		Result result = bankAcctFacade.findBankAcctDetailInfo(bankAcctDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("bankAcct",result.getData());
			return SUCCESS;
		}else{
			setErrorReason(result.getErrorMessage());
			return ERROR;
		}
	}

	public String getBankAcctListInfo() throws Exception {
		Result result = bankAcctFacade.getBankAcctListInfo(bankAcctDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("bankAcctList",result.getData());
			return SUCCESS;
		}else{
			setErrorReason(result.getErrorMessage());
			return ERROR;
		}
	}

	@Autowired
	public void setBankAcctFacade(BankAcctFacade bankAcctFacade) {
		this.bankAcctFacade = bankAcctFacade;
	}

	@Override
	public BankAcctDTO getModel() {
		return bankAcctDTO;
	}
	
	private static final long serialVersionUID = 8874503944126563034L;

	private BankAcctFacade bankAcctFacade;
	
	private BankAcctDTO bankAcctDTO = new BankAcctDTO();
}
