package com.xiu.uuc.web.action;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.facade.AcctItemFacade;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.VirtualAcctItemDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.web.action.base.BaseAction;

public class VirtualAccountAction extends BaseAction implements ModelDriven<VirtualAcctItemDTO>{

	@SuppressWarnings("unchecked")
	public String getVirtualAccountInfo() throws Exception {
		Result result = acctItemFacade.getVirtualAccountInfo(virtualAcctItemDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			List<VirtualAcctItemDTO> virtualAcctItemDTOList = (List<VirtualAcctItemDTO>)result.getData();
			setResult("virtualAccount",virtualAcctItemDTOList.get(0));
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
	public VirtualAcctItemDTO getModel() {
		return virtualAcctItemDTO;
	}
	
	private static final long serialVersionUID = 8874503944126563034L;

	private AcctItemFacade acctItemFacade;
	
	private VirtualAcctItemDTO virtualAcctItemDTO = new VirtualAcctItemDTO();
}
