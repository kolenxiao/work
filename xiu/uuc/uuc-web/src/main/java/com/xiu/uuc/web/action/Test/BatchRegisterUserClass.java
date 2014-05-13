package com.xiu.uuc.web.action.Test;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import com.caucho.hessian.client.HessianProxyFactory;
import com.xiu.uuc.facade.UserManageFacade;
import com.xiu.uuc.facade.dto.RegisterUserDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserBaseDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.TypeEnum;

/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 
 * @AUTHOR : andy.meng@xiu.com 
 * @DATE :Oct 29, 2012 11:59:17 AM
 * </p>
 **************************************************************** 
 */
public class BatchRegisterUserClass {
	
	private UserManageFacade getUserManagerFacade(){
		UserManageFacade userManagerFacade = null;
		try {
			//根据具体情况配置url
			//String url = "http://10.0.0.186:8080/uuc-web/remote/userManageFacade";
			String url = "http://172.16.3.25:8010/remote/userManageFacade";  //120.132.144.61 uuc.xiu.com 
			HessianProxyFactory factory = new HessianProxyFactory();
			userManagerFacade = (UserManageFacade) factory.create(UserManageFacade.class, url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return userManagerFacade;
	}
	
	private void testRegisterUser(int i) throws Exception {
		RegisterUserDTO registerUserDTO = new RegisterUserDTO();
		registerUserDTO.setLogonName("t_t"+i+"@xiu.com");
		registerUserDTO.setPassword("123456");
		registerUserDTO.setChannelId(11);
		registerUserDTO.setRegisterType(TypeEnum.RegisterType.EMAIL.getKey());
		registerUserDTO.setCustType(TypeEnum.CustType.XIU.getKey());
		registerUserDTO.setRegisterIp("127.0.0."+getRandom());
		Result result = getUserManagerFacade().registerUser(registerUserDTO);
		if(FacadeConstant.SUCCESS.equals(result.getSuccess())){
			System.err.println("--------------------------------------");
			Long userId = (Long)result.getData();
			testGetUserBasicInfoByUserId(userId);
			testModifyUserState(userId);
			System.err.println("--------------------------------------");
		}else{
			System.out.println(result.getErrorCode()+": "+result.getErrorMessage());
		}
	}
	
	private void testGetUserBasicInfoByUserId(Long userId) throws Exception {		
		Result result = getUserManagerFacade().getUserBasicInfoByUserId(userId);
		UserBaseDTO userBaseDTO = (UserBaseDTO)result.getData();
		System.out.println("testGetUserBasicInfoByUserId:email="+userBaseDTO.getEmail()+",userid="+userId);
	}
	
	private void testModifyUserState(Long userId) {
		UserBaseDTO userBaseDTO = new UserBaseDTO();
		userBaseDTO.setUserId(userId);
		Result result = getUserManagerFacade().activateUserState(userBaseDTO);
		if (FacadeConstant.SUCCESS.equals(result.getSuccess())) {
			System.out.println("testModifyUserState success userid="+userId);
		}else {
			System.out.println(result.getErrorCode()+": "+result.getErrorMessage());
		}
	}
	
	/**
	 * @Description: 返回3位随机数字串
	 * @return String 3位随机数字串
	 */
	public int getRandom() {
        int number = 0;
        while (true) {
            number = (int) (Math.random() * 1000);
            if (number >= 1 && number < 128) {
                break;
            }
        }
        return number;
    }
	
	public static void main(String[] args) throws Exception {
		BatchRegisterUserClass batchRegisterUserClass = new BatchRegisterUserClass();
		//根据具体情况 配置注册用户数量
		int i = 10101;
		for (int j = i; j < i+100; j++) {
		  batchRegisterUserClass.testRegisterUser(j);
		}
		//batchRegisterUserClass.testRegisterUser(30001);
	}

}
