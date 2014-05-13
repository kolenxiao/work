package com.xiu.uuc.manager;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.xiu.uuc.common.util.SecurityMD5;
import com.xiu.uuc.facade.UserManageFacade;
import com.xiu.uuc.facade.dto.RegisterUserDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserBaseDTO;
import com.xiu.uuc.facade.dto.UserDetailDTO;
import com.xiu.uuc.facade.dto.UserQueryDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.TypeEnum;
import com.xiu.uuc.manager.util.BaseTest;

public class UserManagerImplTest extends BaseTest {
	
	private static Long userId = null;
	private static String logonName = System.currentTimeMillis() + "@kk.com";
	private static Integer channelId = 11;
	
	@Autowired
	private UserManageFacade userManager;
	
	@Test
	@Rollback(false)
	public void testRegisterUser() throws Exception {
		RegisterUserDTO registerUserDTO = new RegisterUserDTO();
		registerUserDTO.setLogonName(logonName);
		registerUserDTO.setPassword("123456");
		registerUserDTO.setChannelId(channelId);
		registerUserDTO.setRegisterType(TypeEnum.RegisterType.EMAIL.getKey());
		registerUserDTO.setCustType(TypeEnum.CustType.XIU.getKey());
		registerUserDTO.setRegisterIp("192.168.88.88");
		
		Result result = userManager.registerUser(registerUserDTO);
		if(FacadeConstant.SUCCESS.equals(result.getSuccess())){
			userId = (Long)result.getData();
		}else{
			System.out.println(result.getErrorCode()+": "+result.getErrorMessage());
		}
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	public void testIsLogonNameExist() {
		Result result = userManager.isLogonNameExist(logonName, channelId);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}


	@Test
	public void testModifyUserState() {
		UserBaseDTO userBaseDTO = new UserBaseDTO();
		userBaseDTO.setUserId(userId);
		
		Result result = userManager.activateUserState(userBaseDTO);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	public void testModifyUserBaseInfo() throws Exception {
		String custName = "张三";
		
		UserBaseDTO userBaseDTO = new UserBaseDTO();
		userBaseDTO.setUserId(userId);
		userBaseDTO.setCustName(custName);
		
		userManager.modifyUserBaseInfo(userBaseDTO);
		Result result = userManager.getUserBasicInfoByUserId(userId);
		UserBaseDTO userDTO = (UserBaseDTO)result.getData();
		System.out.println("testModifyUserBaseInfo:custName="+userDTO.getCustName());
		assertEquals(custName, userDTO.getCustName());
	}
			
	@Test
	public void testModifyUserDetailInfo() throws Exception {
		String provinceCode = "Y001";
		
		UserDetailDTO userDetailDTO = new UserDetailDTO();
		userDetailDTO.setUserId(userId);
		userDetailDTO.setProvinceCode(provinceCode);
		
		userManager.modifyUserDetailInfo(userDetailDTO);
		Result result = userManager.getUserDetailInfoByUserId(userId);
		UserDetailDTO userDTO = (UserDetailDTO)result.getData();
		System.out.println("testModifyUserBaseInfo:provinceCode="+userDTO.getProvinceCode());
		assertEquals(provinceCode, userDTO.getProvinceCode());
	}

	@Test
	@Ignore
	public void testModifyUserPassword() throws Exception {
		String oldPassword = "123456";
		String newPassword = "111111";
		
		userManager.modifyUserPassword(userId, oldPassword, newPassword);
		Result result = userManager.getPasswordByLogonNameAndChannelId(logonName, channelId);
		String password = (String)result.getData();
		System.out.println("testModifyUserPassword:"+password);
		assertEquals(SecurityMD5.MD5Encode(newPassword), password);
	}

	@Test
	@Ignore
	public void testResetUserPassword() throws Exception {
		String newPassword = "111111";
		
		userManager.resetUserPassword(userId, newPassword, "555555", "");
		Result result = userManager.getPasswordByLogonNameAndChannelId(logonName, channelId);
		String password = (String)result.getData();
		System.out.println("testResetUserPassword:"+password);
		assertEquals(SecurityMD5.MD5Encode(newPassword), password);
	}

	@Test
	public void testGetUserBasicInfoByUserId() throws Exception {		
		Result result = userManager.getUserBasicInfoByUserId(userId);
		UserBaseDTO userBaseDTO = (UserBaseDTO)result.getData();
		System.out.println("testGetUserBasicInfoByUserId:"+userBaseDTO.getEmail());
		assertEquals(logonName, userBaseDTO.getEmail());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUserBaseInfoList() throws Exception {
		UserQueryDTO userQueryDTO = new UserQueryDTO();
		userQueryDTO.setEmail(logonName);
		Result result = userManager.getUserBaseInfoList(userQueryDTO);
		List<UserBaseDTO> list = (List<UserBaseDTO>)result.getData();
		UserBaseDTO userBaseDTO = list.get(0);
		System.out.println("testGetUserBaseInfoList:"+userBaseDTO.getUserId());
		assertEquals(userId, userBaseDTO.getUserId());
	}

	@Test
	public void testGetUserDetailInfoByUserId() throws Exception {
		Result result = userManager.getUserDetailInfoByUserId(userId);
		UserDetailDTO userDetailDTO = (UserDetailDTO)result.getData();
		System.out.println("testGetUserDetailInfoByUserId:"+userDetailDTO.getEmail());
		assertEquals(logonName, userDetailDTO.getEmail());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUserDetailInfoList() throws Exception {
		UserQueryDTO userQueryDTO = new UserQueryDTO();
		userQueryDTO.setEmail(logonName);
		Result result = userManager.getUserDetailInfoList(userQueryDTO);
		List<UserDetailDTO> list = (List<UserDetailDTO>)result.getData();
		UserDetailDTO userDetailDTO = list.get(0);
		System.out.println("testGetUserDetailInfoList:"+userDetailDTO.getUserId());
		assertEquals(userId, userDetailDTO.getUserId());
	}

	@Test
	@Ignore
	public void testGetPasswordByLogonNameAndChannelId() {
		Result result = userManager.getPasswordByLogonNameAndChannelId(logonName, channelId);
		String password = (String)result.getData();
		System.out.println("testGetPasswordByLogonNameAndChannelId:"+password);
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	@Ignore
	public void testIsRightPassword() {
		Result result = userManager.isRightPassword(319l, "123456");
		Boolean bl = (Boolean)result.getData();
		System.out.println("testIsRightPassword:"+bl.booleanValue());
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}

	@Test
	@Ignore
	public void testGetUserInTargetChannel() throws Exception {
		Result result = userManager.getUserInTargetChannel(userId, channelId);
		UserBaseDTO dto = (UserBaseDTO)result.getData();
		System.out.println("testGetUserInTargetChannel:"+dto.getUserId());
		
		Result result2 = userManager.getUserInTargetChannel(userId, TypeEnum.ChannelType.XIU_GROUP.getKey());
		UserBaseDTO dto2 = (UserBaseDTO)result2.getData();
		System.out.println("testGetUserInTargetChannel:"+dto2.getUserId());
		
		assertEquals(FacadeConstant.SUCCESS, result.getSuccess());
	}	

}
