package com.xiu.uuc.web.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.facade.UserManageFacade;
import com.xiu.uuc.facade.dto.LeadRegisterDTO;
import com.xiu.uuc.facade.dto.RegisterUserDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.web.action.base.BaseAction;

public class RegisterUserAction extends BaseAction implements ModelDriven<RegisterUserDTO>{

	private static final long serialVersionUID = 8874503944126563034L;

	private UserManageFacade userManageFacade;
	
	private RegisterUserDTO registerUserDTO = new RegisterUserDTO();

	
	/**
	 * 用户注册
	 * @Title: registerUser 
	 * @param   
	 * @return String    返回类型 
	 * @throws
	 */
	public String registerUser() throws Exception {
		Result result = userManageFacade.registerUser(registerUserDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("data","用户ID:"+result.getData());
			return SUCCESS;
		}else{
			setErrorReason(result.getErrorMessage());
			return ERROR;
		}
	}
	
   /**
    * 引导联盟用户注册	
   * @Description: 
   * @return
   * @throws Exception
    */
   public String leadPartnerRegister() throws Exception {
       LeadRegisterDTO leadRegisterDTO = new LeadRegisterDTO();
       leadRegisterDTO.setUserId(Long.valueOf(request.getParameter("userId")));
       leadRegisterDTO.setEmail(request.getParameter("email"));
       leadRegisterDTO.setMobile(request.getParameter("mobile"));
       leadRegisterDTO.setPassword(request.getParameter("password"));
       leadRegisterDTO.setChannelId(Integer.valueOf(request.getParameter("channelId")));
       
        Result result = userManageFacade.leadPartnerRegister(leadRegisterDTO);
        if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
            return SUCCESS;
        }else{
            setErrorReason(result.getErrorMessage());
            return ERROR;
        }
    }
	

	
	@Autowired
	public void setUserManageFacade(UserManageFacade userManageFacade) {
		this.userManageFacade = userManageFacade;
	}

	@Override
	public RegisterUserDTO getModel() {
		return registerUserDTO;
	}

}
