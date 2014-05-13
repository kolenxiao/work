package com.xiu.uuc.dal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.xiu.uuc.dal.param.UserInfoParam;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.dal.po.CustExtInfoPO;
import com.xiu.uuc.dal.po.CustInfoPO;
import com.xiu.uuc.dal.po.RegisterIpPO;
import com.xiu.uuc.dal.po.UserAcctPO;
import com.xiu.uuc.dal.po.UserBaseInfo;
import com.xiu.uuc.dal.po.UserDetailInfo;
import com.xiu.uuc.dal.po.UserInfoPO;

/**
 * 用户管理DAO
 * @ClassName: UserManageDAO
 * @author xiaoyingping
 * @date 2011-7-19 上午11:00:58
 * 
 */
public interface UserManageDAO {

	/**
	 * 插入客户信息
	 * @Title: insertCustInfo
	 * @param: custInfoPO
	 * @return Long 返回类型
	 * @throws DataAccessException
	 */
	public Long insertCustInfo(CustInfoPO custInfoPO) throws DataAccessException;

	/**
	 * 修改客户信息
	 * @Title: modifyCustInfo
	 * @param: custInfoPO
	 * @return void    返回类型 
	 * @throws DataAccessException
	 */
	public void modifyCustInfo(CustInfoPO custInfoPO) throws DataAccessException;
	   
    /**
     * 引导联盟注册时修改客户表
     * @Title: modifyCustLeadRegister 
     * @param  custInfoPO 
     * @return void    返回类型 
     * @throws DataAccessException
     */
    public void modifyCustLeadRegister(CustInfoPO custInfoPO) throws DataAccessException;

	/**
	 * 查询客户信息
	 * @Title: queryCustInfo
	 * @param: userInfoParam
	 * @return CustInfoPO 返回类型
	 * @throws DataAccessException
	 */
	public CustInfoPO queryCustInfo(UserInfoParam userInfoParam) throws DataAccessException;
	
	/**
	 * 新增客户扩展信息 
	 * @Title: insertCustExtInfo 
	 * @param  custExtInfoPO 
	 * @return void    返回类型 
	 * @throws DataAccessException
	 */
	public void insertCustExtInfo(CustExtInfoPO custExtInfoPO) throws DataAccessException;
	
	/**
	 * 修改客户扩展信息 
	 * @Title: modifyCustExtInfo 
	 * @param  custExtInfoPO 
	 * @return void    返回类型 
	 * @throws DataAccessException
	 */
	public void modifyCustExtInfo(CustExtInfoPO custExtInfoPO) throws DataAccessException;

	
	
	
	/**
	 * 插入用户信息
	 * @Title: insertUserInfo
	 * @param: userInfoPO
	 * @return Long 返回类型
	 * @throws DataAccessException
	 */
	public Long insertUserInfo(UserInfoPO userInfoPO) throws DataAccessException;

	/**
	 * 修改用户信息
	 * @Title: modifyUserInfo 
	 * @param: userInfoPO
	 * @return void    返回类型 
	 * @throws DataAccessException
	 */
	public void modifyUserInfo(UserInfoPO userInfoPO) throws DataAccessException;
	
	/**
	 * 
	 * @Title: modifyUserAuthen
	 * @Description: 修改邮箱和手机的认证状态
	 * @param userInfoPO
	 * @throws DataAccessException
	 */
	public void modifyUserAuthen(UserInfoPO userInfoPO) throws DataAccessException;
	
	/**
	 * 
	 * @Title: modifyEbayUserAgreement
	 * @Description: 修改eBay用户协议是否同意标志
	 * @param userInfoPO
	 * @throws DataAccessException
	 */
	public void modifyEbayUserAgreement(UserInfoPO userInfoPO) throws DataAccessException;
	
	/**
	 * 修改用户密码
	 * @Title: modifyUserPassword 
	 * @param  userInfoPO 
	 * @return void    返回类型 
	 * @throws DataAccessException
	 */
	public void modifyUserPassword(UserInfoPO userInfoPO) throws DataAccessException;
	
	/**
     * 引导联盟注册时修改用户表
     * @Title: modifyUserLeadRegister 
     * @param  userInfoPO 
     * @return void    返回类型 
     * @throws DataAccessException
     */
	public void modifyUserLeadRegister(UserInfoPO userInfoPO) throws DataAccessException;

	/**
	 * 查询用户信息
	 * @Title: queryUserInfo
	 * @param: userInfoParam
	 * @return UserInfoPO 返回类型
	 * @throws DataAccessException
	 */
	public UserInfoPO queryUserInfo(UserInfoParam userInfoParam) throws DataAccessException;
	
	/**
	 * 查询用户信息列表
	 * @Title: queryUserInfoList
	 * @param: userInfoParam
	 * @return List<UserInfoPO> 返回类型
	 * @throws DataAccessException
	 */
	public List<UserInfoPO> queryUserInfoList(UserInfoParam userInfoParam) throws DataAccessException;
	
	
	
	
	/**
	 * 插入注册IP信息
	 * @Title: insertRegisterIp
	 * @param: registerIpPO
	 * @return Long 返回类型
	 * @throws DataAccessException
	 */
	public Long insertRegisterIp(RegisterIpPO registerIpPO) throws DataAccessException;
	
	/**
	 * 查询注册IP数目
	 * @Title: queryRegisterIpCount 
	 * @param  registerIpPO 
	 * @return Integer    返回类型 
	 * @throws DataAccessException
	 */
	public Integer queryRegisterIpCount(RegisterIpPO registerIpPO) throws DataAccessException;
	
	
	/**
	 * 通知SSO
	 * @Title: insertSsoScheduleTask 
	 * @param  paramMap 
	 * @return Integer    返回类型 
	 * @throws
	 */
	public void insertSsoScheduleTask(Map<String, Object> paramMap) throws DataAccessException;


	
	
	
	/**
	 * 插入用户账户信息
	 * @Title: insertUserAcctInfo
	 * @param: userAcctPO
	 * @return Long 返回类型
	 * @throws DataAccessException
	 */
	public Long insertUserAcctInfo(UserAcctPO userAcctPO) throws DataAccessException;
	
	/**
	 * 插入账目信息
	 * @Title: insertAcctItemInfo
	 * @param: acctItemPO
	 * @return Long 返回类型
	 * @throws DataAccessException
	 */
	public Long insertAcctItemInfo(AcctItemPO acctItemPO) throws DataAccessException;

	
	
	

	
	/**
	 * 查询用户基本信息数目
	 * @Title: queryUserBaseInfoCount 
	 * @param  userInfoParam
	 * @return Long 返回类型 
	 * @throws DataAccessException
	 */
	public Long queryUserBaseInfoCount(UserInfoParam userInfoParam) throws DataAccessException;
	
	/**
	 * 查询用户基本信息
	 * @Title: queryUserBaseInfoList 
	 * @param  userInfoParam
	 * @return UserBaseInfo 返回类型 
	 * @throws DataAccessException
	 */
	public UserBaseInfo queryUserBaseInfo(UserInfoParam userInfoParam) throws DataAccessException;
	
	/**
	 * 查询用户基本信息List
	 * @Title: queryUserBaseInfoList 
	 * @param  userInfoParam
	 * @return List<UserBaseInfo> 返回类型 
	 * @throws DataAccessException
	 */
	public List<UserBaseInfo> queryUserBaseInfoList(UserInfoParam userInfoParam) throws DataAccessException;

	/**
	 * 查询用户详细信息数目
	 * @Title: queryUserDetailInfoCount 
	 * @param  userInfoParam 
	 * @return Long    返回类型 
	 * @throws DataAccessException
	 */
	public Long queryUserDetailInfoCount(UserInfoParam userInfoParam) throws DataAccessException;

	/**
	 * 查询用户详细信息
	 * @Title: queryUserDetailInfo 
	 * @param  userInfoParam 
	 * @return List<UserDetailInfo>    返回类型 
	 * @throws DataAccessException
	 */
	public UserDetailInfo queryUserDetailInfo(UserInfoParam userInfoParam) throws DataAccessException;

	/**
	 * 查询用户详细信息列表
	 * @Title: queryUserDetailInfoList 
	 * @param  userInfoParam 
	 * @return List<UserDetailInfo>    返回类型 
	 * @throws DataAccessException
	 */
	public List<UserDetailInfo> queryUserDetailInfoList(UserInfoParam userInfoParam) throws DataAccessException;

	/**
	 * 用户的每个渠道列表数目
	 * @Title: queryUserChannelCount 
	 * @param   
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long queryUserChannelCount(UserInfoParam userInfoParam) throws DataAccessException;

	/**
	 * 查询用户的每个渠道列表
	 * @Title: queryUserChannelList 
	 * @param   
	 * @return List<UserDetailInfo>    返回类型 
	 * @throws
	 */
	public List<UserDetailInfo> queryUserChannelList(UserInfoParam userInfoParam) throws DataAccessException;

}
