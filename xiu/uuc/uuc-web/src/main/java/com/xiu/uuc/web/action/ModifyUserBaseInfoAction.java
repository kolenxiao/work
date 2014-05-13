package com.xiu.uuc.web.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.facade.UserManageFacade;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserBaseDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.web.action.base.BaseAction;

public class ModifyUserBaseInfoAction extends BaseAction implements ModelDriven<UserBaseDTO>{

	private static final long serialVersionUID = 8874503944126563034L;

	private UserManageFacade userManageFacade;
	private UserBaseDTO userBaseDTO = new UserBaseDTO();
	

	
	/**
	 * 修改用户基本信息
	 */
	public String modifyUserBaseInfo() throws Exception {
		Result result = userManageFacade.modifyUserBaseInfo(userBaseDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("user",result.getData());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}	


	@Autowired
	public void setUserManageFacade(UserManageFacade userManageFacade) {
		this.userManageFacade = userManageFacade;
	}

	@Override
	public UserBaseDTO getModel() {
		return this.userBaseDTO;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
















	

}
