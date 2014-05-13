package com.xiu.uuc.web.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xiu.uuc.facade.UserManageFacade;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserBaseDTO;
import com.xiu.uuc.facade.dto.UserQueryDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.web.action.base.BaseAction;

public class UserQueryAction extends BaseAction implements ModelDriven<UserQueryDTO>{

	private static final long serialVersionUID = 8874503944126563034L;

	@Autowired
	private UserManageFacade userManageFacade;
	
	private UserQueryDTO userQueryDTO = new UserQueryDTO();
	

	
	/**
	 * 检查用户是否注册
	 */
	public String isLogonNameExist() throws Exception {
		String logonName = userQueryDTO.getLogonName(); 
		Integer channelId = userQueryDTO.getChannelId();

		Result result = userManageFacade.isLogonNameExist(logonName, channelId);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			if(null == result.getData()){
				setResult("isExist","不");
			}else{
				setResult("isExist","是");
			}
			setResult("userId",result.getData());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}	
	
	/**
	 * 通过登录名和渠道标识得到登录密码
	 */
	public String getPasswordByLogonNameAndChannelId() throws Exception {
		String logonName = userQueryDTO.getLogonName(); 
		Integer channelId = userQueryDTO.getChannelId();

		Result result = userManageFacade.getPasswordByLogonNameAndChannelId(logonName, channelId);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			String password = (String)result.getData();
			if(StringUtils.isEmpty(password)){
				setResult("password","密码不存在");
			}else{
				setResult("password",result.getData());
			}
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	
	/**
	 * 通过用户ID得到用户基本信息
	 */
	public String getUserBasicInfoByUserId() throws Exception {
		long userId  = userQueryDTO.getUserId();
		
		Result result = userManageFacade.getUserBasicInfoByUserId(userId);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("user",result.getData());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}	
	
	/**
	 * 得到用户基本信息列表
	 */
	public String getUserBaseInfoList() throws Exception {
		Result result = userManageFacade.getUserBaseInfoList(userQueryDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("user",result.getData());
			setResult("page",result.getPage());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	
	/**
	 * 通过用户ID得到用户详细信息
	 */
	public String getUserDetailInfoByUserId() throws Exception {
		long userId  = userQueryDTO.getUserId(); 
		
		Result result = userManageFacade.getUserDetailInfoByUserId(userId);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("user",result.getData());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	
	/**
	 * 得到用户详细信息列表
	 */
	public String getUserDetailInfoList() throws Exception {
		Result result = userManageFacade.getUserDetailInfoList(userQueryDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("user",result.getData());
			setResult("page",result.getPage());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}

	
	/**
	 * 得到在目标渠道下的用户信息
	 */
	public String getUserInTargetChannel() throws Exception {
		Long userId = userQueryDTO.getUserId();
		Integer targetChannelId = userQueryDTO.getChannelId();
		
		Result result = userManageFacade.getUserInTargetChannel(userId, targetChannelId);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			UserBaseDTO dto = (UserBaseDTO)result.getData();
			setResult("userId", dto.getUserId());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	
	/**
	 * 得到用户渠道列表
	 */
	public String getUserChannelList() throws Exception {
		Result result = userManageFacade.getUserChannelList(userQueryDTO);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			setResult("user",result.getData());
			setResult("page",result.getPage());
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	
	/**
	 * (邮箱/手机)是否通过验证
	 */
	public String isAuthentic() throws Exception {
		String userId = request.getParameter("userId");
		String authenType = request.getParameter("authenType");

		Result result = userManageFacade.isAuthentic(Long.valueOf(userId), authenType);
		if(result.getSuccess().equals(FacadeConstant.SUCCESS)){
			boolean bl = (Boolean)result.getData();
			if(false == bl){
				setResult("isAuthentic","没有验证过");
			}else{
				setResult("isAuthentic","已通过验证");
			}
			setResult("userId",userId);
			setResult("authenType",authenType);
			return SUCCESS;
		}else{
			addActionError(result.getErrorMessage());
			return ERROR;
		}
	}
	
	
	
	

	@Override
	public UserQueryDTO getModel() {
		return userQueryDTO;
	}

}
