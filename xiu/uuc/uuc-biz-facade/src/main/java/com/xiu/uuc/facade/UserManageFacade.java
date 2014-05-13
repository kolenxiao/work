package com.xiu.uuc.facade;

import com.xiu.commons.core.IServiceStatusHealthyChecking;
import com.xiu.uuc.facade.dto.EbayUserAgreementDTO;
import com.xiu.uuc.facade.dto.LeadRegisterDTO;
import com.xiu.uuc.facade.dto.ModifyUserAuthenDTO;
import com.xiu.uuc.facade.dto.RegisterUserDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserBaseDTO;
import com.xiu.uuc.facade.dto.UserDetailDTO;
import com.xiu.uuc.facade.dto.UserQueryDTO;

/**
 * 用户管理Facade
 * @ClassName: UserManageFacade 
 * @Description: 用户中心系统对外接口类
 * @author xiaoyingping
 * @date 2011-7-21 下午04:30:48 
 *
 */
public interface UserManageFacade extends IServiceStatusHealthyChecking {
	
	/**
	 * 检查用户登录名是否已被注册
	 * @Title: isLogonNameExist 
	 * @param  logonName 用户登录名
	 * @param  channelId 用户所属渠道标识 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result isLogonNameExist(String logonName, Integer channelId);
	
	/**
	 * 检查用户登录名是否已被注册
	 * @Title: isLogonNameExist 
	 * @param  logonName 用户登录名
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result isLogonNameExist(String logonName);
	
	/**
	 * 用户注册
	 * @Title: registerUser 
	 * @param  registerUserDTO 用户注册信息DTO
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result registerUser(RegisterUserDTO registerUserDTO);
	   
    /**
     *@Title: leadPartnerRegister 
    * @Description: 引导联盟用户注册
    * @param leadRegisterDTO
    * @return
     */
    public Result leadPartnerRegister(LeadRegisterDTO leadRegisterDTO);
	
	/**
	 * 激活用户
	 * @Title: activateUserState 
	 * @param  userBaseDTO
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result activateUserState(UserBaseDTO userBaseDTO);
	
	/**
	 * 禁用用户
	 * @Title: forbidUser 
	 * @param  userBaseDTO 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result forbidUser(UserBaseDTO userBaseDTO);
	
	/**
	 * 解禁用户
	 * @Title: unForbidUser 
	 * @param  userBaseDTO 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result unForbidUser(UserBaseDTO userBaseDTO);
	
	/**
	 * 删除用户
	 * @Title: deleteUser 
	 * @param  userBaseDTO 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result deleteUser(UserBaseDTO userBaseDTO);
	
	/**
	 * 修改用户基本信息
	 * @Title: modifyUserBaseInfo 
	 * @param  userBaseDTO
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result modifyUserBaseInfo(UserBaseDTO userBaseDTO);
	
	/**
	 * 修改用户详细信息
	 * @Title: modifyUserDetailInfo 
	 * @param  userDetailDTO
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result modifyUserDetailInfo(UserDetailDTO userDetailDTO);
	
	/**
	 * 修改用户密码
	 * @Title: modifyUserPassword 
	 * @param  userId 用户ID
	 * @param  oldPassword 当前使用密码 
	 * @param  newPassword 新密码
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result modifyUserPassword(Long userId, String oldPassword, String newPassword);
	
	/**
	 * 重置用户密码
	 * @Title: resetUserPassword 
	 * @param  userId 用户ID
	 * @param  newPassword 新密码
	 * @param  operId 操作员ID 
	 * @param  remark 重置原因
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result resetUserPassword(Long userId, String newPassword, String operId, String remark);
	
	/**
	 * 
	* @Title: modifyUserAuthen
	* @Description: 修改用户认证(邮箱、手机)
	* @param modifyUserAuthenDTO
	* @return Result
	 */
	public Result modifyUserAuthen(ModifyUserAuthenDTO modifyUserAuthenDTO);
	
	/**
	 * 
	 * @Title: isAuthentic
	 * @Description: 是否通过验证(邮箱、手机)
	 * @param userId 用户ID
	 * @param authenType 要验证的类型 (0:邮箱, 1:手机)
	 * @return Result
	 */
	public Result isAuthentic(Long userId, String authenType);
	
	/**
	 * 通过用户ID得到用户基本信息
	 * @Title: getUserBasicInfoByUserId 
	 * @param  userId 用户ID 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getUserBasicInfoByUserId(Long userId);
	
	/**
	 * 得到用户基本信息列表
	 * @Title: getUserBaseInfoList 
	 * @param  userQueryDTO 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getUserBaseInfoList(UserQueryDTO userQueryDTO);
	
	/**
	 * 通过用户ID得到用户详细信息
	 * @Title: getUserDetailInfoByUserId 
	 * @param  userId 用户ID 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getUserDetailInfoByUserId(Long userId);
	
	/**
	 * 通过用户名得到用户详细信息(如果用户存在多个，则匹配channelId)
	 * @param logonName
	 * @param channelId
	 * @return
	 */
	public Result getUserDetailInfoByLogonName(String logonName, Integer channelId);
	
	/**
	 * 得到用户详细信息列表
	 * @Title: getUserDetailInfoList 
	 * @param  userQueryDTO 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getUserDetailInfoList(UserQueryDTO userQueryDTO);
	
	/**
	 * 通过登录名和渠道标识得到登录密码
	 * @Title: getPasswordByLogonNameAndChannelId 
	 * @param  logonName 用户登录名
	 * @param  channelId 渠道标识  
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getPasswordByLogonNameAndChannelId(String logonName, Integer channelId);
	
	/**
	 * 判断登录密码是否正确
	 * @Title: isRightPassword 
	 * @param  userId 用户ID
	 * @param  password 用户登录密码
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result isRightPassword(Long userId, String password);
	
	/**
	 * 得到在目标渠道下的用户基本信息
	 * @Title: getUserInTargetChannel 
	 * @param  userId 用户ID
	 * @param  targetChannelId 目标渠道标识
	 * @description 如果在指定的渠道下不存在用户，则系统代创建一个用户
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getUserInTargetChannel(Long userId, Integer targetChannelId);
	
	/**
	 * 得到用户的每个渠道列表
	 * @Title: getUserChannelList 
	 * @param  userQueryDTO 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getUserChannelList(UserQueryDTO userQueryDTO);
	
	/**
	 * 
	* @Title: modifyEbayUserAgreement
	* @Description: 修改eBay用户协议是否同意标志位(Y：同意 ，N：不同意,其它也表示不同意)
	* @param ebayUserAgreementDTO
	* @return Result
	 */
	public Result modifyEbayUserAgreement(EbayUserAgreementDTO ebayUserAgreementDTO);
	
}
