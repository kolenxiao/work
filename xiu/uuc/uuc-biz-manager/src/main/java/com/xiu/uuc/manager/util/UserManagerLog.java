package com.xiu.uuc.manager.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.uuc.common.util.CommonEnum;
import com.xiu.uuc.common.util.CommonValidationUtil;
import com.xiu.uuc.common.util.JsonUtils;
import com.xiu.uuc.core.service.LogService;
import com.xiu.uuc.dal.po.UserBaseInfo;
import com.xiu.uuc.dal.po.UserDetailInfo;
import com.xiu.uuc.dal.po.UserInfoPO;
import com.xiu.uuc.facade.dto.BusiLogDTO;
import com.xiu.uuc.facade.dto.EbayUserAgreementDTO;
import com.xiu.uuc.facade.dto.LeadRegisterDTO;
import com.xiu.uuc.facade.dto.ModifyUserAuthenDTO;
import com.xiu.uuc.facade.dto.RegisterUserDTO;
import com.xiu.uuc.facade.dto.UserBaseDTO;
import com.xiu.uuc.facade.dto.UserDetailDTO;
import com.xiu.uuc.facade.util.TypeEnum;

public class UserManagerLog {

	@Autowired
	private LogService logService;
	
	private static JsonUtils jsonUtils = new JsonUtils();
	
	public void logRegisterUser(RegisterUserDTO registerUserDTO, Long userId) throws Exception{
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(userId.toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.USER_ID.getKey());
		busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.RegisterUser.getKey());
		busiLogDTO.setOperId(registerUserDTO.getOperId());
		busiLogDTO.setOperIp(registerUserDTO.getRegisterIp());	

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("用户名", registerUserDTO.getLogonName());
		map.put("注册渠道", TypeEnum.ChannelType.getList().get(registerUserDTO.getChannelId()));	
		map.put("注册类型", TypeEnum.RegisterType.getList().get(registerUserDTO.getRegisterType()));
		map.put("客户类型", TypeEnum.CustType.getList().get(registerUserDTO.getCustType()));
		if(TypeEnum.CustType.PARTNER.getKey().equals(registerUserDTO.getCustType())){
			map.put("联盟ID", registerUserDTO.getPartnerId());
			map.put("联盟渠道", TypeEnum.PartnerChannelType.getList().get(registerUserDTO.getPartnerChannelId()));
		}
		map.put("是否需要激活", TypeEnum.YesOrNo.getList().get(registerUserDTO.getIsNeedActivate()));

		busiLogDTO.setAfterContent(jsonUtils.toJson(map));
		logService.insertBusiLog(busiLogDTO);
	}
	
	public void logModifyCustState(UserBaseInfo userBaseInfo, String busiOperType, String oldState, String newState) throws Exception{
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(userBaseInfo.getUserId().toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.USER_ID.getKey());
		busiLogDTO.setBusiTypeCode(busiOperType);
		busiLogDTO.setOperId(userBaseInfo.getOperId());
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("客户状态", TypeEnum.CustState.getList().get(oldState));
		busiLogDTO.setBeforeContent(jsonUtils.toJson(map));

		map = new LinkedHashMap<String, Object>();
		map.put("客户状态", TypeEnum.CustState.getList().get(newState));
		busiLogDTO.setAfterContent(jsonUtils.toJson(map));
		
		logService.insertBusiLog(busiLogDTO);
	}
	
	public void logModifyUserState(UserBaseInfo userBaseInfo, String busiOperType, String oldState, String newState) throws Exception{
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(userBaseInfo.getUserId().toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.USER_ID.getKey());
		busiLogDTO.setBusiTypeCode(busiOperType);
		busiLogDTO.setOperId(userBaseInfo.getOperId());
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("用户状态", TypeEnum.UserState.getList().get(oldState));
		busiLogDTO.setBeforeContent(jsonUtils.toJson(map));

		map = new LinkedHashMap<String, Object>();
		map.put("用户状态", TypeEnum.UserState.getList().get(newState));

		busiLogDTO.setAfterContent(jsonUtils.toJson(map));
		logService.insertBusiLog(busiLogDTO);
	}
		
	
	public void logModifyUserBaseInfo(UserBaseInfo oldUser, UserBaseDTO newUser) throws Exception{
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(newUser.getUserId().toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.USER_ID.getKey());
		busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.ModifyUserInfo.getKey());
		busiLogDTO.setOperId(newUser.getOperId());
		
		Map<String, Object> oldMap = new LinkedHashMap<String, Object>();	
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		
		if(null != newUser.getMobile() && !CommonValidationUtil.equals(oldUser.getMobile(), newUser.getMobile())){
			oldMap.put("手机号", oldUser.getMobile());
			newMap.put("手机号", newUser.getMobile());
		}
		if(null != newUser.getPetName() && !CommonValidationUtil.equals(oldUser.getPetName(), newUser.getPetName())){
			oldMap.put("呢称", oldUser.getPetName());
			newMap.put("呢称", newUser.getPetName());
		}
		if(null != newUser.getEmail() && !CommonValidationUtil.equals(oldUser.getEmail(), newUser.getEmail())){
			oldMap.put("邮箱", oldUser.getEmail());
			newMap.put("邮箱", newUser.getEmail());
		}
		if(null != newUser.getUserLevel() && !CommonValidationUtil.equals(oldUser.getUserLevel(), newUser.getUserLevel())){
			oldMap.put("用户等级", oldUser.getUserLevel());
			newMap.put("用户等级", newUser.getUserLevel());
		}
		if(null != newUser.getCustLevel() && !CommonValidationUtil.equals(oldUser.getCustLevel(), newUser.getCustLevel())){
			oldMap.put("客户等级", oldUser.getCustLevel());
			newMap.put("客户等级", newUser.getCustLevel());
		}
		
		boolean bl = false;
		if(!oldMap.isEmpty()){
			bl = true;
			busiLogDTO.setBeforeContent(jsonUtils.toJson(oldMap));
		}
		if(!newMap.isEmpty()){
			bl = true;
			busiLogDTO.setAfterContent(jsonUtils.toJson(newMap));
		}
		if(bl){
			logService.insertBusiLog(busiLogDTO);
		}		
	}
	
	
	public void logModifyUserDetailInfo(UserDetailInfo oldUser, UserDetailDTO newUser) throws Exception{
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(newUser.getUserId().toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.USER_ID.getKey());
		busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.ModifyUserInfo.getKey());
		busiLogDTO.setOperId(newUser.getOperId());
		
		Map<String, Object> oldMap = new LinkedHashMap<String, Object>();	
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		
		if(null != newUser.getMobile() && !CommonValidationUtil.equals(oldUser.getMobile(), newUser.getMobile())){
			oldMap.put("手机号", oldUser.getMobile());
			newMap.put("手机号", newUser.getMobile());
		}
		if(null != newUser.getPetName() && !CommonValidationUtil.equals(oldUser.getPetName(), newUser.getPetName())){
			oldMap.put("呢称", oldUser.getPetName());
			newMap.put("呢称", newUser.getPetName());
		}
		if(null != newUser.getEmail() && !CommonValidationUtil.equals(oldUser.getEmail(), newUser.getEmail())){
			oldMap.put("邮箱", oldUser.getEmail());
			newMap.put("邮箱", newUser.getEmail());
		}
		if(null != newUser.getUserLevel() && !CommonValidationUtil.equals(oldUser.getUserLevel(), newUser.getUserLevel())){
			oldMap.put("用户等级", oldUser.getUserLevel());
			newMap.put("用户等级", newUser.getUserLevel());
		}
		if(null != newUser.getCustLevel() && !CommonValidationUtil.equals(oldUser.getCustLevel(), newUser.getCustLevel())){
			oldMap.put("客户等级", oldUser.getCustLevel());
			newMap.put("客户等级", newUser.getCustLevel());
		}
						
		boolean bl = false;
		if(!oldMap.isEmpty()){
			bl = true;
			busiLogDTO.setBeforeContent(jsonUtils.toJson(oldMap));
		}
		if(!newMap.isEmpty()){
			bl = true;
			busiLogDTO.setAfterContent(jsonUtils.toJson(newMap));
		}
		if(bl){
			logService.insertBusiLog(busiLogDTO);
		}	
	}
	
	
	public void logModifyUserPassword(Long userId, String oldPassword, String newPassword) throws Exception{
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(userId.toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.USER_ID.getKey());
		busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.ModifyUserPassword.getKey());
		
		Map<String, Object> oldMap = new LinkedHashMap<String, Object>();	
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		oldMap.put("密码", oldPassword);
		newMap.put("密码", newPassword);
		busiLogDTO.setBeforeContent(jsonUtils.toJson(oldMap));
		busiLogDTO.setAfterContent(jsonUtils.toJson(newMap));

		logService.insertBusiLog(busiLogDTO);
	}
	
	
	public void logResetUserPassword(Long userId, String oldPassword, String newPassword, String operId, String remark) throws Exception{
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(userId.toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.USER_ID.getKey());
		busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.ResetUserPassword.getKey());
		busiLogDTO.setOperId(operId);
		busiLogDTO.setRemark(remark);
		
		Map<String, Object> oldMap = new LinkedHashMap<String, Object>();	
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		//oldMap.put("密码", "********"+oldPassword.substring(oldPassword.length()-4, oldPassword.length()));
		//newMap.put("密码", "********"+newPassword.substring(newPassword.length()-4, newPassword.length()));
		oldMap.put("密码", oldPassword);
        newMap.put("密码", newPassword);
		busiLogDTO.setBeforeContent(jsonUtils.toJson(oldMap));
		busiLogDTO.setAfterContent(jsonUtils.toJson(newMap));

		logService.insertBusiLog(busiLogDTO);
	}
   
    
    public void logLeadPartnerRegister(LeadRegisterDTO leadRegisterDTO) {
        BusiLogDTO busiLogDTO = new BusiLogDTO();
        busiLogDTO.setKeyWord(leadRegisterDTO.getUserId().toString());
        busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.USER_ID.getKey());
        busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.LeadPartnerRegister.getKey());
        
        Map<String, Object> oldMap = new LinkedHashMap<String, Object>();   
        Map<String, Object> newMap = new LinkedHashMap<String, Object>();

        oldMap.put("客户类型", TypeEnum.CustType.PARTNER.getValue());

        if(StringUtils.isNotBlank(leadRegisterDTO.getEmail())){
            newMap.put("邮箱", leadRegisterDTO.getEmail());
        }else if(StringUtils.isNotBlank(leadRegisterDTO.getMobile())){
            newMap.put("手机号", leadRegisterDTO.getMobile());
        }        
        //String password = leadRegisterDTO.getPassword();
        //newMap.put("密码", "********"+password.substring(password.length()-4, password.length()));
        //newMap.put("密码", leadRegisterDTO.getPassword());
        newMap.put("客户类型", TypeEnum.CustType.XIU.getValue());

        busiLogDTO.setBeforeContent(jsonUtils.toJson(oldMap));
        busiLogDTO.setAfterContent(jsonUtils.toJson(newMap));
        logService.insertBusiLog(busiLogDTO);
    }
    	
	public void logModifyEmailAuthen(ModifyUserAuthenDTO modifyUserAuthenDTO, UserInfoPO oldUser, UserInfoPO newUser) {
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(modifyUserAuthenDTO.getUserId().toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.USER_ID.getKey());
		busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.ModifyEmailAuthen.getKey());
		busiLogDTO.setOperIp(modifyUserAuthenDTO.getIpAddr());
		
		Map<String, Object> oldMap = new LinkedHashMap<String, Object>();	
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		if(!StringUtils.equals(oldUser.getEmail(), newUser.getEmail())){
			oldMap.put("邮箱", oldUser.getEmail());
		}
        oldMap.put("状态", oldUser.getEmailAuthenStatus());
        newMap.put("邮箱", newUser.getEmail());
        newMap.put("状态", newUser.getEmailAuthenStatus());
		
		if(!oldMap.isEmpty()){
			busiLogDTO.setBeforeContent(jsonUtils.toJson(oldMap));
		}
		busiLogDTO.setAfterContent(jsonUtils.toJson(newMap));
		logService.insertBusiLog(busiLogDTO);
	}
	
	public void logModifyMobileAuthen(ModifyUserAuthenDTO modifyUserAuthenDTO, UserInfoPO oldUser, UserInfoPO newUser) {
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(modifyUserAuthenDTO.getUserId().toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.USER_ID.getKey());
		busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.ModifyMobileAuthen.getKey());
		busiLogDTO.setOperIp(modifyUserAuthenDTO.getIpAddr());
		
		Map<String, Object> oldMap = new LinkedHashMap<String, Object>();	
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		if(!StringUtils.equals(oldUser.getMobile(), newUser.getMobile())){
			oldMap.put("手机", oldUser.getMobile());			
		}
		oldMap.put("状态", oldUser.getMobileAuthenStatus());
		newMap.put("手机", newUser.getMobile());
		newMap.put("状态", newUser.getMobileAuthenStatus());
		
		if(!oldMap.isEmpty()){
			busiLogDTO.setBeforeContent(jsonUtils.toJson(oldMap));
		}
		busiLogDTO.setAfterContent(jsonUtils.toJson(newMap));
		logService.insertBusiLog(busiLogDTO);
	}
	
	public void logModifyEbayUserAgreement(EbayUserAgreementDTO ebayUserAgreementDTO, UserInfoPO oldUser, UserInfoPO newUser) {
		
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(ebayUserAgreementDTO.getUserId().toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.USER_ID.getKey());
		busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.ModifyEbayUserAgreement.getKey());
		busiLogDTO.setOperIp(ebayUserAgreementDTO.getIpAddr());
		
		Map<String, Object> oldMap = new LinkedHashMap<String, Object>();	
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		
		oldMap.put("eBay用户协议是否同意", StringUtils.isNotEmpty(oldUser.getEbayUserAgreement()) 
				   ? oldUser.getEbayUserAgreement(): TypeEnum.YesOrNo.NO.getKey());
		newMap.put("eBay用户协议是否同意", newUser.getEbayUserAgreement());
		
		busiLogDTO.setBeforeContent(jsonUtils.toJson(oldMap));
		busiLogDTO.setAfterContent(jsonUtils.toJson(newMap));
		logService.insertBusiLog(busiLogDTO);
	}
	
    


}
