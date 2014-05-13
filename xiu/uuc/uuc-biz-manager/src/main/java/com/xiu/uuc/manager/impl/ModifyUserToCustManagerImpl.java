package com.xiu.uuc.manager.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.util.SecurityMD5;
import com.xiu.uuc.core.service.AddressManageService;
import com.xiu.uuc.core.service.UserManageService;
import com.xiu.uuc.dal.param.RcvAddressParam;
import com.xiu.uuc.dal.po.CustInfoPO;
import com.xiu.uuc.dal.po.UserInfoPO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.ResultKeys;
import com.xiu.uuc.facade.util.TypeEnum;
import com.xiu.uuc.manager.ModifyUserToCustManager;

public class ModifyUserToCustManagerImpl implements ModifyUserToCustManager {

	private UserManageService userManageService;
	private AddressManageService addressManageService;


	public Result modifyUserToCust(Long userId, String bindLogonName, String bindPassword, Integer bindChannelId){
		Result result = null;
		try {			
			//校验数据
			result = this.validateBindUserToCust(userId, bindLogonName, bindPassword, bindChannelId);
			if(result != null){
				return result;
			}
			
			//检查被绑定的用户是否匹配正确(用户名，密码，渠道)
			bindPassword = new SecurityMD5().MD5Encode(bindPassword);//加密
			UserInfoPO userInfoPO = userManageService.getUserByNameAndPassword(bindLogonName, bindPassword, bindChannelId);
			if (userInfoPO == null) {// 如果输入的用户信息不匹配
				throw new BusinessException("要绑定的用户登录名或登录密码错误!");
			}
			Long bindCustId = userInfoPO.getCustId();
			Long bindUserId = userInfoPO.getUserId();
			
			//检查本用户是否存在
			userInfoPO = userManageService.getUserById(userId);
			if (userInfoPO == null) {
				throw new BusinessException("传入的用户ID不存在");
			}
			
			//检查客户在渠道下是否已经存在关联用户
			Long custId = userInfoPO.getCustId();
			userInfoPO = userManageService.getUserByCustIdAndChannelId(custId, bindChannelId);
			if(userInfoPO != null){
				throw new BusinessException("绑定失败，在此渠道下已经存在关联用户!");
			}
			
			//得到被绑定客户的用户列表
			List<UserInfoPO> userList = userManageService.getUserListByCustId(bindCustId);
			if(null != userList){
				for (UserInfoPO tempUserInfoPO : userList) {					
					//修改用户关联的客户ID
					tempUserInfoPO.setCustId(custId);
					//userManageService.modifyUserRelateCustId(bindUserId, custId);
				}
			}
			
			//修改原客户的状态，设为已删除
			CustInfoPO custInfoPO = new CustInfoPO();
			custInfoPO.setCustId(bindCustId);
			custInfoPO.setCustState(TypeEnum.CustState.DELETED.getKey());
			userManageService.modifyCustInfo(custInfoPO);
			
			//修改收货地址关联的客户ID
			RcvAddressParam rcvAddressParam = new RcvAddressParam();
			rcvAddressParam.setBindCustId(bindCustId);
			rcvAddressParam.setCustId(custId);
			addressManageService.modifyRcvAddressCust(rcvAddressParam);

			//返回结果
			result = new Result();
			result.setSuccess(FacadeConstant.SUCCESS);
			return result;
		} catch (BusinessException e) {
			result = new Result();
			result.setSuccess(FacadeConstant.FALSE);
			result.setErrorCode(ResultKeys.ERROE_CODE_BUSINESS);
			result.setErrorMessage(e.getMessage());
			return result;
		} catch (Exception e) {
			result = new Result();
			result.setSuccess(FacadeConstant.FALSE);
			result.setErrorCode(ResultKeys.ERROE_CODE_SYSTEM);
			result.setErrorMessage(e.getStackTrace().toString());
			return result;
		}

	}

	
	//校验绑定用户到客户的数据
	private Result validateBindUserToCust(Long userId, String bindLogonName, String bindPassword, Integer bindChannelId){
		String errorMesg = "";
		if(userId == null){
			errorMesg = "用户ID不能为空!";
		}else if(StringUtils.isEmpty(bindLogonName)){
			errorMesg = "要绑定的用户登录名不能为空!";
		}else if(StringUtils.isEmpty(bindPassword)){
			errorMesg = "要绑定的用户登录密码不能为空!";
		}else if(bindChannelId == null){
			errorMesg = "要绑定的用户所属渠道标识不能为空!";
		}
		
		if(StringUtils.isNotEmpty(errorMesg)){
			Result result = new Result();
			result.setSuccess(FacadeConstant.FALSE);
			result.setErrorCode(ResultKeys.ERROE_CODE_PARAMETER);
			result.setErrorMessage(errorMesg);
			return result;
		}else{
			return null;
		}
	}

	
	
	
	
	

	@Autowired
	public void setUserManageService(UserManageService userManageService) {
		this.userManageService = userManageService;
	}

	@Autowired
	public void setAddressManageService(AddressManageService addressManageService) {
		this.addressManageService = addressManageService;
	}



}
