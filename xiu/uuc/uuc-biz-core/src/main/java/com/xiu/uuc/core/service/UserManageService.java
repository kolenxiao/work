package com.xiu.uuc.core.service;

import java.util.List;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.dal.param.UserInfoParam;
import com.xiu.uuc.dal.po.CustExtInfoPO;
import com.xiu.uuc.dal.po.CustInfoPO;
import com.xiu.uuc.dal.po.RegisterIpPO;
import com.xiu.uuc.dal.po.UserAcctPO;
import com.xiu.uuc.dal.po.UserBaseInfo;
import com.xiu.uuc.dal.po.UserDetailInfo;
import com.xiu.uuc.dal.po.UserInfoPO;

/**
 * 用户管理Service
 * @ClassName: UserManageService 
 * @author xiaoyingping
 * @date 2011-7-19 上午11:04:39 
 *
 */
public interface UserManageService {

	/**
	 * 插入客户信息
	 * @Title: insertCustInfo 
	 * @param: isNeedActivate 是否需要将邮箱注册用户设置为"非激活"状态
	 * @param: custInfoPO
	 * @return Long    返回类型 
	 * @throws BusinessException
	 */
	public Long insertCustInfo(String isNeedActivate, CustInfoPO custInfoPO) throws BusinessException;

	/**
	 * 修改客户信息
	 * @Title: modifyCustInfo 
	 * @param: custInfoPO
	 * @return void    返回类型 
	 * @throws BusinessException
	 */
	public void modifyCustInfo(CustInfoPO custInfoPO) throws BusinessException;

	/**
	 * 查询客户信息
	 * @Title: queryCustInfo 
	 * @param: userInfoParam
	 * @return CustInfoPO    返回类型 
	 * @throws BusinessException
	 */
	public CustInfoPO queryCustInfo(UserInfoParam userInfoParam) throws BusinessException;
	
	/**
	 * 通过客户ID得到客户信息
	 * @Title: getCustById 
	 * @param  custId 客户ID 
	 * @return CustInfoPO    返回类型 
	 * @throws BusinessException
	 */
	public CustInfoPO getCustById(Long custId) throws BusinessException;
		
	/**
	 * 新增客户扩展信息 
	 * @Title: insertCustExtInfo 
	 * @param  custExtInfoPO 
	 * @return void    返回类型 
	 * @throws BusinessException
	 */
	public void insertCustExtInfo(CustExtInfoPO custExtInfoPO) throws BusinessException;
	
	/**
	 * 修改用户扩展信息
	 * @Title: modifyCustExtInfo 
	 * @param  custExtInfoPO
	 * @return void    返回类型 
	 * @throws BusinessException
	 */
	public void modifyCustExtInfo(CustExtInfoPO custExtInfoPO) throws BusinessException;

	
	
	
	/**
	 * 插入用户信息
	 * @Title: insertUserInfo 
	 * @param: userInfoPO
	 * @return Long    返回类型 
	 * @throws BusinessException
	 */
	public Long insertUserInfo(UserInfoPO userInfoPO) throws BusinessException;

	/**
	 * 修改用户信息
	 * @Title: modifyUserInfo 
	 * @param: userInfoPO
	 * @return void    返回类型 
	 * @throws BusinessException
	 */
	public void modifyUserInfo(UserInfoPO userInfoPO) throws BusinessException;
	
	/**
	 * 
	 * @Title: modifyUserAuthen
	 * @Description: 修改邮箱和手机的认证
	 * @param userInfoPO
	 */
	public void modifyUserAuthen(UserInfoPO userInfoPO);
	
	/**
	 * @Title: modifyEbayUserAgreement
	 * @Description: 修改eBay用户协议是否同意 Y：同意 ,N：不同意,其它也表示不同意
	 * @param userInfoPO
	 */
	public void modifyEbayUserAgreement(UserInfoPO userInfoPO);
	
	/**
	 * 修改用户密码
	 * @Title: modifyUserPassword 
	 * @param  userInfoPO 用户信息对象
	 * @param  oldPassword 旧密码
	 * @param  newPassword 新密码
	 * @param  operId 操作员ID 
	 * @param  remark 重置原因
	 * @return void    返回类型 
	 * @throws BusinessException
	 * @throws Exception 
	 */
	public void modifyUserPassword(UserInfoPO userInfoPO, String oldPassword, String newPassword, String operId, String remark) throws BusinessException;
		   
    /**
     * 
    * @Title: leadPartnerRegister
    * @Description: 引导联盟注册
    * @param userInfoPO
    * @param custInfoPO
    * @throws BusinessException
     */
    public void leadPartnerRegister(UserInfoPO userInfoPO, CustInfoPO custInfoPO) ;
	
	/**
	 * 修改用户状态
	 * @param userId
	 * @param userState
	 * @throws BusinessException
	 */
	public void modifyUserState(Long userId, String userState) throws BusinessException;

	/**
	 * 查询用户信息
	 * @Title: queryUserInfo 
	 * @param: userInfoParam
	 * @return UserInfoPO    返回类型 
	 * @throws BusinessException
	 */
	public UserInfoPO queryUserInfo(UserInfoParam userInfoParam) throws BusinessException;
	
	/**
	 * 查询用户信息列表
	 * @Title: queryUserInfoList
	 * @param: userInfoParam
	 * @return List<UserInfoPO> 返回类型
	 * @throws BusinessException
	 */
	public List<UserInfoPO> queryUserInfoList(UserInfoParam userInfoParam) throws BusinessException;	
	
	/**
	 * 查询登录用户名是否已经存在
	 * @Title: isLogonNameExist 
	 * @param: logonName 登录用户名  
	 * @param  channelId 渠道标识
	 * @return UserInfoPO    返回类型 
	 * @throws BusinessException
	 */
	public UserInfoPO isLogonNameExist(String logonName, Integer channelId) throws BusinessException;
	
	/**
	 * 
	 * @Title: isLogonNameExist
	 * @Description: 查询登录用户名是否已经存在
	 * @param logonName 登录用户名
	 * @return boolean
	 */
	public boolean isLogonNameExist(String logonName);
	
	/**
	 * 通过用户ID得到用户信息
	 * @Title: getUserById 
	 * @param  userId 用户ID 
	 * @return UserInfoPO    返回类型 
	 * @throws BusinessException
	 */
	public UserInfoPO getUserById(Long userId) throws BusinessException;
	
	/**
	 * 通过用户登录名，登录密码和渠道标识得到用户信息
	 * @Title: getUserByNameAndPassword 
	 * @param: logonName 登录用户名  
	 * @param  password  登录密码
	 * @param  channelId 渠道标识  
	 * @return UserInfoPO    返回类型 
	 * @throws BusinessException
	 */
	public UserInfoPO getUserByNameAndPassword(String logonName, String password, Integer channelId) throws BusinessException;
	
	/**
	 * 通过客户ID和渠道标识得到用户信息
	 * @Title: getUserByCustIdAndChannelId 
	 * @param  custId 客户ID
	 * @param  channelId 渠道标识 
	 * @return UserInfoPO    返回类型 
	 * @throws BusinessException
	 */
	public UserInfoPO getUserByCustIdAndChannelId(Long custId, Integer channelId) throws BusinessException;
	
	
	/**
	 * 通过客户ID得到用户信息列表
	 * @Title: getUserListByCustId 
	 * @param  custId 客户ID
	 * @return List<UserInfoPO>    返回类型 
	 * @throws BusinessException
	 */
	public List<UserInfoPO> getUserListByCustId(Long custId) throws BusinessException;
	
	/**
	 * 得到用户密码
	 * @Title: getUserPassword 
	 * @param  userId 用户ID 
	 * @return String    返回类型 
	 * @throws BusinessException
	 */
	public String getUserPasswordById(Long userId) throws BusinessException;
	
	
	
	
	/**
	 * 插入注册IP信息
	 * @Title: insertRegisterIp
	 * @param: registerIpPO
	 * @return Long 返回类型
	 * @throws BusinessException
	 */
	public Long insertRegisterIp(RegisterIpPO registerIpPO) throws BusinessException;
	
	/**
	 * 查询注册IP数目
	 * @Title: queryRegisterIpCount 
	 * @param  registerIpPO 
	 * @return Integer    返回类型 
	 * @throws BusinessException
	 */
	public Integer queryRegisterIpCount(RegisterIpPO registerIpPO) throws BusinessException;

	
	
	
	/**
	 * 插入用户账户信息
	 * @Title: insertUserAcctInfo 
	 * @param: userAcctPO
	 * @return Long    返回类型 
	 * @throws BusinessException
	 */
	public Long insertUserAcctInfo(UserAcctPO userAcctPO) throws BusinessException;

	/**
	 * 初始化账目信息
	 * @Title: initializeAcctItemInfo 
	 * @param  acctId 帐户ID 
	 * @return void    返回类型 
	 * @throws BusinessException
	 */
	public void initializeAcctItemInfo(Long acctId) throws BusinessException;
	
	
	
	/**
	 * 查询用户基本信息数目
	 * @Title: queryUserBaseInfoCount 
	 * @param  userInfoParam
	 * @return Long 返回类型 
	 * @throws BusinessException
	 */
	public Long queryUserBaseInfoCount(UserInfoParam userInfoParam) throws BusinessException;
	
	/**
	 * 查询用户基本信息
	 * @Title: queryUserBaseInfo 
	 * @param  userInfoParam
	 * @return UserBaseInfoPO 返回类型 
	 * @throws BusinessException
	 */
	public UserBaseInfo queryUserBaseInfo(UserInfoParam userInfoParam) throws BusinessException;
	
	/**
	 * 查询用户基本信息List
	 * @Title: queryUserBaseInfoList 
	 * @param  userInfoParam
	 * @return List<UserBaseInfo> 返回类型 
	 * @throws BusinessException
	 */
	public List<UserBaseInfo> queryUserBaseInfoList(UserInfoParam userInfoParam) throws BusinessException;	
	
	/**
	 * 查询用户详细信息数目
	 * @Title: queryUserDetailInfoCount 
	 * @param  userInfoParam 
	 * @return Long    返回类型 
	 * @throws BusinessException
	 */
	public Long queryUserDetailInfoCount(UserInfoParam userInfoParam) throws BusinessException;
	
	/**
	 * 查询用户详细信息
	 * @Title: queryUserDetailInfo 
	 * @param  userInfoParam 
	 * @return UserDetailInfo    返回类型 
	 * @throws BusinessException
	 */
	public UserDetailInfo queryUserDetailInfo(UserInfoParam userInfoParam) throws BusinessException;
	
	/**
	 * 查询用户详细信息List
	 * @Title: queryUserDetailInfoList 
	 * @param  userInfoParam 
	 * @return List<UserDetailInfo>    返回类型 
	 * @throws BusinessException
	 */
	public List<UserDetailInfo> queryUserDetailInfoList(UserInfoParam userInfoParam) throws BusinessException;

	/**
	 * 用户的每个渠道列表数目
	 * @Title: queryUserChannelCount 
	 * @param   
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long queryUserChannelCount(UserInfoParam userInfoParam) throws BusinessException;

	/**
	 * 查询用户的每个渠道列表
	 * @Title: queryUserChannelList 
	 * @param   
	 * @return List<UserDetailInfo>    返回类型 
	 * @throws
	 */
	public List<UserDetailInfo> queryUserChannelList(UserInfoParam userInfoParam) throws BusinessException;

}
