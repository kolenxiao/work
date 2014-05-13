package com.xiu.uuc.web.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.facade.UserManageFacade;
import com.xiu.uuc.facade.dto.ModifyUserAuthenDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserBaseDTO;
import com.xiu.uuc.facade.dto.UserDetailDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.web.action.base.BaseAction;
import com.xiu.uuc.web.util.WebUtil;

public class UserManageAction extends BaseAction implements ModelDriven<UserDetailDTO>{

	private static final long serialVersionUID = 8874503944126563034L;

	@Autowired
	private UserManageFacade userManageFacade;
	private UserDetailDTO userDetailDTO = new UserDetailDTO();
	
	
	/**
	 * 激活用户状态
	 */
	public String activateUser() throws Exception {
		Long userId = userDetailDTO.getUserId();
		UserBaseDTO userBaseDTO = new UserBaseDTO();
		userBaseDTO.setUserId(userId);
		Result result = userManageFacade.activateUserState(userBaseDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	
	/**
	 * 禁用用户
	 */
	public String forbidUser() throws Exception {
		Long userId = userDetailDTO.getUserId();
		UserBaseDTO userBaseDTO = new UserBaseDTO();
		userBaseDTO.setUserId(userId);
		Result result = userManageFacade.forbidUser(userBaseDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	
	/**
	 * 起用用户
	 */
	public String unForbidUser() throws Exception {
		Long userId = userDetailDTO.getUserId();
		UserBaseDTO userBaseDTO = new UserBaseDTO();
		userBaseDTO.setUserId(userId);
		Result result = userManageFacade.unForbidUser(userBaseDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	
	/**
	 * 删除用户
	 */
	public String deleteUser() throws Exception {
		Long userId = userDetailDTO.getUserId();
		UserBaseDTO userBaseDTO = new UserBaseDTO();
		userBaseDTO.setUserId(userId);
		Result result = userManageFacade.deleteUser(userBaseDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}

	/**
	 * 修改用户密码
	 * @Title: modifyUserPassword 
	 * @param   
	 * @return String    返回类型 
	 * @throws
	 */
	public String modifyUserPassword() throws Exception {	
		Long userId = Long.valueOf(request.getParameter("userId"));
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		
		Result result = userManageFacade.modifyUserPassword(userId, oldPassword, newPassword);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	
	/**
	 * 重置用户密码
	 * @Title: resetUserPassword 
	 * @param   
	 * @return String    返回类型 
	 * @throws
	 */
	public String resetUserPassword() throws Exception {
		Long userId = Long.valueOf(request.getParameter("userId"));
		String newPassword = request.getParameter("newPassword");
		String operId = request.getParameter("operId");
		String remark = request.getParameter("remark");
		
		Result result = userManageFacade.resetUserPassword(userId, newPassword, operId, remark);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	
	/**
	 * 修改用户详细信息
	 */
	public String modifyUserDetailInfo() throws Exception {
		Result result = userManageFacade.modifyUserDetailInfo(userDetailDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("user",result.getData());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}	

	public String test() throws Exception {
		UserDetailDTO userBaseDTO = new UserDetailDTO();
		userBaseDTO.setUserId(233l);
		userBaseDTO.setSubscibeInfo("1");
		Result result = userManageFacade.modifyUserDetailInfo(userBaseDTO);
		
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			Object obj = result.getData();
			if(null != obj){
				System.out.println("成功:"+obj.toString());
			}
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	

	/**
	 * 
	 * @Title: modifyUserAuthen
	 * @Description: 修改用户名认证
	 * @return
	 * @throws Exception
	 */
	public String modifyUserAuthen() throws Exception {	
		Long userId = Long.valueOf(request.getParameter("userId"));
		String authenType = request.getParameter("authenType");
		String authenValue = request.getParameter("authenValue");
		
		ModifyUserAuthenDTO modifyUserAuthenDTO = new ModifyUserAuthenDTO();
		modifyUserAuthenDTO.setUserId(userId);
		modifyUserAuthenDTO.setAuthenType(authenType);
		modifyUserAuthenDTO.setAuthenValue(authenValue);
		modifyUserAuthenDTO.setIpAddr(WebUtil.getIpAddr(request));
		
		Result result = null;
		result = userManageFacade.modifyUserAuthen(modifyUserAuthenDTO);
		
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}

	@Override
	public UserDetailDTO getModel() {
		return userDetailDTO;
	}

}
