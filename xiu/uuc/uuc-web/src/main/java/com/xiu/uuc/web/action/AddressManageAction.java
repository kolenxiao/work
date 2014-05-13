package com.xiu.uuc.web.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.facade.AddressManageFacade;
import com.xiu.uuc.facade.dto.RcvAddressDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.web.action.base.BaseAction;

public class AddressManageAction extends BaseAction implements ModelDriven<RcvAddressDTO> {

	private static final long serialVersionUID = 8874503944126563034L;

	private AddressManageFacade addressManageFacade;
	
	private RcvAddressDTO rcvAddressDTO = new RcvAddressDTO();

	public String addRcvAddressInfo() throws Exception {	
		Result result = addressManageFacade.addRcvAddressInfo(rcvAddressDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			RcvAddressDTO dto = (RcvAddressDTO)result.getData();
			setResult("data", "地址ID："+dto.getAddressId());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}

	public String modifyRcvAddressInfo() throws Exception {
		Result result = addressManageFacade.modifyRcvAddressInfo(rcvAddressDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}

	public String modifyRcvAddressMaster() throws Exception {
		Result result = addressManageFacade.modifyRcvAddressMaster(rcvAddressDTO.getAddressId());
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}

	public String deleteRcvAddressInfo() throws Exception {
		Result result = addressManageFacade.deleteRcvAddressInfo(rcvAddressDTO.getAddressId());
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}

	public String getRcvAddressList() throws Exception {
		Result result = addressManageFacade.getRcvAddressListByUserId(rcvAddressDTO.getUserId());
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("rcvAddressList",result.getData());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}

	public String getRcvAddressInfo() throws Exception {
		Result result = addressManageFacade.getRcvAddressInfoById(rcvAddressDTO.getAddressId());
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("rcvAddress",result.getData());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}

	public String getRcvAddressCount() throws Exception {
		Result result = addressManageFacade.getRcvAddressCountByUserId(rcvAddressDTO.getUserId());
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("count",result.getData());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	

	@Autowired
	public void setAddressManageFacade(AddressManageFacade addressManageFacade) {
		this.addressManageFacade = addressManageFacade;
	}

	@Override
	public RcvAddressDTO getModel() {
		return rcvAddressDTO;
	}

	
	
	
	
	
	
	
	
	
	
















	

}
