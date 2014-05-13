package com.xiu.uuc.web.action.Test;

import java.net.MalformedURLException;
import com.caucho.hessian.client.HessianProxyFactory;
import com.xiu.uuc.facade.UserManageFacade;
import com.xiu.uuc.facade.dto.EbayUserAgreementDTO;
import com.xiu.uuc.facade.dto.RegisterUserDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserBaseDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.TypeEnum;

public class UserManagerFacadeImplTest {
	
	private static Long userId = 939L;
	private static String logonName = System.currentTimeMillis() + "@kk.com";
	private static Integer channelId = 11;
	
	public static void main(String[] args) throws Exception {
		UserManagerFacadeImplTest userManagerFacadeImplTest = new UserManagerFacadeImplTest();
		userManagerFacadeImplTest.testRegisterUser(); 
		userManagerFacadeImplTest.testModifyEbayUserAgreement();
		userManagerFacadeImplTest.testGetUserBasicInfoByUserId();
	}
	
	private UserManageFacade getUserManagerFacade(){
		UserManageFacade userManagerFacade = null;
		try {
			String url = "http://uuc.xiu.com:8080/remote/userManageFacade";
			HessianProxyFactory factory = new HessianProxyFactory();
			userManagerFacade = (UserManageFacade) factory.create(UserManageFacade.class, url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return userManagerFacade;
	}
	
	private void testRegisterUser() throws Exception {
		RegisterUserDTO registerUserDTO = new RegisterUserDTO();
		registerUserDTO.setLogonName(logonName);
		registerUserDTO.setPassword("123456");
		registerUserDTO.setChannelId(channelId);
		registerUserDTO.setRegisterType(TypeEnum.RegisterType.EMAIL.getKey());
		registerUserDTO.setCustType(TypeEnum.CustType.XIU.getKey());
		registerUserDTO.setRegisterIp("192.168.88.88");
		Result result = getUserManagerFacade().registerUser(registerUserDTO);
		if(FacadeConstant.SUCCESS.equals(result.getSuccess())){
			userId = (Long)result.getData();
		}else{
			System.out.println(result.getErrorCode()+": "+result.getErrorMessage());
		}
	}
	
	private void testModifyEbayUserAgreement() throws Exception {
		EbayUserAgreementDTO ebayUserAgreementDTO = new EbayUserAgreementDTO();
		ebayUserAgreementDTO.setUserId(userId);
		ebayUserAgreementDTO.setEbayUserAgreement(TypeEnum.YesOrNo.YES.getKey());
		getUserManagerFacade().modifyEbayUserAgreement(ebayUserAgreementDTO);
		Result result = getUserManagerFacade().getUserBasicInfoByUserId(userId);
		UserBaseDTO userDTO = (UserBaseDTO)result.getData();
		System.out.println("testModifyEbayUserAgreement:ebayUserAgreement="+userDTO.getEbayUserAgreement());
	}
	
	private void testGetUserBasicInfoByUserId() throws Exception {		
		Result result = getUserManagerFacade().getUserBasicInfoByUserId(userId);
		UserBaseDTO userBaseDTO = (UserBaseDTO)result.getData();
		System.out.println("testGetUserBasicInfoByUserId:ebayUserAgreement="+userBaseDTO.getEbayUserAgreement());
	}

}
