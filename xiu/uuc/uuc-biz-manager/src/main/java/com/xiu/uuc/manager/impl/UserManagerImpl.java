package com.xiu.uuc.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.csp.facade.SysParamFacade;
import com.xiu.csp.facade.util.SysParamUtil;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.BeanUtilEx;
import com.xiu.uuc.common.util.CommonEnum;
import com.xiu.uuc.common.util.CommonValidationUtil;
import com.xiu.uuc.common.util.ExceptionEnum;
import com.xiu.uuc.common.util.JsonUtils;
import com.xiu.uuc.common.util.KeyNames;
import com.xiu.uuc.common.util.SecurityMD5;
import com.xiu.uuc.core.service.UserManageService;
import com.xiu.uuc.core.util.CoreUtil;
import com.xiu.uuc.dal.param.UserInfoParam;
import com.xiu.uuc.dal.po.CustExtInfoPO;
import com.xiu.uuc.dal.po.CustInfoPO;
import com.xiu.uuc.dal.po.RegisterIpPO;
import com.xiu.uuc.dal.po.UserAcctPO;
import com.xiu.uuc.dal.po.UserBaseInfo;
import com.xiu.uuc.dal.po.UserDetailInfo;
import com.xiu.uuc.dal.po.UserInfoPO;
import com.xiu.uuc.facade.dto.EbayUserAgreementDTO;
import com.xiu.uuc.facade.dto.LeadRegisterDTO;
import com.xiu.uuc.facade.dto.ModifyUserAuthenDTO;
import com.xiu.uuc.facade.dto.RegisterUserDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserBaseDTO;
import com.xiu.uuc.facade.dto.UserDetailDTO;
import com.xiu.uuc.facade.dto.UserQueryDTO;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.Page;
import com.xiu.uuc.facade.util.TypeEnum;
import com.xiu.uuc.manager.UserManager;
import com.xiu.uuc.manager.util.UserManagerLog;
import com.xiu.uuc.manager.validate.UserManageValidate;

public class UserManagerImpl implements UserManager {

    private static final Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    private UserManageService userManageService;
    @Autowired
    private SysParamFacade sysParamFacade;
    @Autowired
    private UserManagerLog userManagerLog;
    
    private JsonUtils jsonUtils = new JsonUtils();

    @Override
    public Result isLogonNameExist(String logonName, Integer channelId) {
        // 校验数据
        UserManageValidate.validateIsLogonNameExist(logonName, channelId);

        // 调用userManageService
        UserInfoPO bl = userManageService.isLogonNameExist(logonName, channelId);

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        if (null != bl) {
            result.setData(bl.getUserId());
        }
        return result;
    }

    @Override
    public Result isLogonNameExist(String logonName) {
        // 校验数据
    	if (StringUtils.isBlank(logonName)) {
			throw new ParameterException("用户登录名不能为空!");
		}

        // 调用userManageService
        boolean bl = userManageService.isLogonNameExist(logonName);

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        result.setData(bl);
        return result;
    }

    @Override
    public Result registerUser(RegisterUserDTO registerUserDTO) throws Exception {
    	
        try {
        	
            // 对明文密码进行加密
            String password = SecurityMD5.MD5Encode(registerUserDTO.getPassword(), registerUserDTO.getChannelId());
            registerUserDTO.setPassword(password);
            logger.info("registerUser entry: {}", registerUserDTO);
            
            // 校验参数
            UserManageValidate.validateRegisterUser(registerUserDTO);

            // 将用户名转化成小写
            registerUserDTO.setLogonName(registerUserDTO.getLogonName().toLowerCase());

            // 检查渠道下用户名是否已被注册
            UserInfoPO bl = userManageService.isLogonNameExist(registerUserDTO.getLogonName(),registerUserDTO.getChannelId());
            if (null != bl) {
                throw new BusinessException(ExceptionEnum.Busi.LOGON_NAME_EXIST.getKey(), "");
            }

            // 检查联盟用户是否已经存在
            if (TypeEnum.CustType.PARTNER.getKey().equals(registerUserDTO.getCustType())) {
                registerUserDTO.setPartnerId(registerUserDTO.getPartnerId());
                bl = userManageService.isLogonNameExist(registerUserDTO.getPartnerId(),
                        registerUserDTO.getPartnerChannelId());
                if (null != bl) {
                    throw new BusinessException(ExceptionEnum.Busi.PARTNER_USER_EXIST.getKey(), "");
                }
            }

            // 检查注册IP是否受限
            RegisterIpPO registerIpParam = new RegisterIpPO();
            registerIpParam.setRegisterIp(registerUserDTO.getRegisterIp());
            registerIpParam.setInterval(KeyNames.MAX_REGISTER_IP_INTERVAL);
            Integer ipCount = userManageService.queryRegisterIpCount(registerIpParam);
            if (ipCount.intValue() >= KeyNames.MAX_REGISTER_IP_COUNT) {
                throw new BusinessException(ExceptionEnum.Busi.REGISTER_USER_IP_MAX.getKey(), "");
            }

            // 创建客户信息
            CustInfoPO custInfoPO = new CustInfoPO();
            BeanUtilEx.copyProperties(custInfoPO, registerUserDTO);
            custInfoPO.setLastLogonIp(registerUserDTO.getRegisterIp());
            custInfoPO.setLastLogonChannelId(registerUserDTO.getChannelId());
            Long custId = userManageService.insertCustInfo(registerUserDTO.getIsNeedActivate(), custInfoPO);

            // 创建用户信息
            UserInfoPO userInfoPO = new UserInfoPO();
            BeanUtilEx.copyProperties(userInfoPO, registerUserDTO);
            userInfoPO.setCustId(custId);
            userInfoPO.setIsMaster(TypeEnum.YesOrNo.YES.getKey());// 主用户
            if (TypeEnum.RegisterType.EMAIL.getKey().equals(custInfoPO.getRegisterType())) {
                userInfoPO.setEmail(registerUserDTO.getLogonName());
            } else if (TypeEnum.RegisterType.MOBILE.getKey().equals(custInfoPO.getRegisterType())) {
                userInfoPO.setMobile(registerUserDTO.getLogonName());
                userInfoPO.setMobileAuthenStatus(TypeEnum.AuthenStatus.PASS.getKey());//手机号已通过认证
            } else if (TypeEnum.RegisterType.PETNAME.getKey().equals(custInfoPO.getRegisterType())) {
                userInfoPO.setPetName(registerUserDTO.getLogonName());
            }
            Long userId = userManageService.insertUserInfo(userInfoPO);

            // 创建用户账户信息
            UserAcctPO userAcctPO = new UserAcctPO();
            userAcctPO.setUserId(userId);
            Long acctId = userManageService.insertUserAcctInfo(userAcctPO);

            // 初始化账目信息
            userManageService.initializeAcctItemInfo(acctId);

            // 创建客户扩展信息
            CustExtInfoPO custExtInfoPO = new CustExtInfoPO();
            custExtInfoPO.setCustId(custId);
            custExtInfoPO.setSourceType(TypeEnum.UserSource.REGISTER.getKey());
            userManageService.insertCustExtInfo(custExtInfoPO);

            // 插入注册IP信息
            RegisterIpPO registerIpPO = new RegisterIpPO();
            registerIpPO.setRegisterIp(registerUserDTO.getRegisterIp());
            registerIpPO.setCustId(custId);
            userManageService.insertRegisterIp(registerIpPO);

            // 插入业务日志
            userManagerLog.logRegisterUser(registerUserDTO, userId);

            // 返回结果
            Result result = new Result();
            result.setSuccess(FacadeConstant.SUCCESS);
            result.setData(userId);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result modifyUserState(UserBaseDTO userBaseDTO, String busiOperType) throws Exception {
        // 校验数据
        UserManageValidate.validateModifyUserState(userBaseDTO);

        // 检查用户是否存在
        UserInfoParam param = new UserInfoParam();
        if (StringUtils.isNotBlank(String.valueOf(userBaseDTO.getUserId()))) {
            param.setUserId(userBaseDTO.getUserId());
        } else {
            param.setLogonName(userBaseDTO.getLogonName());
            param.setChannelId(userBaseDTO.getChannelId());
        }

        UserBaseInfo userInfo = userManageService.queryUserBaseInfo(param);
        if (null == userInfo) {
            throw new BusinessException(ExceptionEnum.Busi.USER_NOT_EXIST.getKey(), "");
        }

        // 修改用户状态并记录业务日志
        this.modifyUserState(userInfo, busiOperType);
        
        
        

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        return result;
    }

    @Override
    public Result modifyUserBaseInfo(UserBaseDTO userBaseDTO) throws Exception {
        Result result = null;
        try {
            // 校验数据
            UserManageValidate.validateModifyUserBaseInfo(userBaseDTO);

            // 检查用户
            UserInfoParam userInfoParam = new UserInfoParam();
            userInfoParam.setUserId(userBaseDTO.getUserId());
            UserBaseInfo userBaseInfo = userManageService.queryUserBaseInfo(userInfoParam);
            if (null == userBaseInfo) {
                throw new BusinessException(ExceptionEnum.Busi.USER_NOT_EXIST.getKey(), "");
            }

            // 将email和petName转化成小写
            if (StringUtils.isNotBlank(userBaseDTO.getEmail())) {
                userBaseDTO.setEmail(userBaseDTO.getEmail().toLowerCase());
            }
            if (StringUtils.isNotBlank(userBaseDTO.getPetName())) {
                userBaseDTO.setPetName(userBaseDTO.getPetName().toLowerCase());
            }

            // 修改用户表
            UserInfoPO userInfoPO = new UserInfoPO();
            BeanUtilEx.copyProperties(userInfoPO, userBaseDTO);
            userInfoPO.setUserId(userBaseInfo.getUserId());
            userManageService.modifyUserInfo(userInfoPO);

            // 修改客户表
            CustInfoPO custInfoPO = new CustInfoPO();
            BeanUtilEx.copyProperties(custInfoPO, userBaseDTO);
            custInfoPO.setCustId(userBaseInfo.getCustId());
            userManageService.modifyCustInfo(custInfoPO);

            // 插入业务日志
            userManagerLog.logModifyUserBaseInfo(userBaseInfo, userBaseDTO);

            // 返回结果
            result = new Result();
            result.setSuccess(FacadeConstant.SUCCESS);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result modifyUserDetailInfo(UserDetailDTO userDetailDTO) throws Exception {
        try {
            // 校验数据
            UserManageValidate.validateModifyUserDetailInfo(userDetailDTO);

            // 检查用户
            UserInfoParam userInfoParam = new UserInfoParam();
            userInfoParam.setUserId(userDetailDTO.getUserId());
            UserDetailInfo userDetailInfo = userManageService.queryUserDetailInfo(userInfoParam);
            if (null == userDetailInfo) {
                throw new BusinessException(ExceptionEnum.Busi.USER_NOT_EXIST.getKey(), "");
            }

            // 将email和petName转化成小写
            if (StringUtils.isNotBlank(userDetailDTO.getEmail())) {
                userDetailDTO.setEmail(userDetailDTO.getEmail().toLowerCase());
            }
            if (StringUtils.isNotBlank(userDetailDTO.getPetName())) {
                userDetailDTO.setPetName(userDetailDTO.getPetName().toLowerCase());
            }

            // 修改用户信息表
            UserInfoPO userInfoPO = new UserInfoPO();
            BeanUtilEx.copyProperties(userInfoPO, userDetailDTO);
            if (StringUtils.isNotEmpty(userInfoPO.getPetName()) || StringUtils.isNotEmpty(userInfoPO.getMobile())
                    || StringUtils.isNotEmpty(userInfoPO.getEmail())) {
                userManageService.modifyUserInfo(userInfoPO);
            }

            // 修改客户信息表
            CustInfoPO custInfoPO = new CustInfoPO();
            BeanUtilEx.copyProperties(custInfoPO, userDetailDTO);
            if (StringUtils.isNotEmpty(custInfoPO.getCustName()) || null != custInfoPO.getBirthday()) {
                custInfoPO.setCustId(userDetailInfo.getCustId());
                userManageService.modifyCustInfo(custInfoPO);
            }

            // 修改客户扩展信息表
            CustExtInfoPO custExtInfoPO = new CustExtInfoPO();
            BeanUtilEx.copyProperties(custExtInfoPO, userDetailDTO);
            custExtInfoPO.setCustId(userDetailInfo.getCustId());
            userManageService.modifyCustExtInfo(custExtInfoPO);

            // 插入业务日志
            userManagerLog.logModifyUserDetailInfo(userDetailInfo, userDetailDTO);

            // 返回结果
            Result result = new Result();
            result.setSuccess(FacadeConstant.SUCCESS);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result modifyUserPassword(Long userId, String oldPassword, String newPassword) throws Exception {
        // 校验数据
        UserManageValidate.validateModifyUserPassword(userId, oldPassword, newPassword);

        // 校验用户是否存在
        UserInfoPO userInfoPO = userManageService.getUserById(userId);

        // 对密码进行加密码
        oldPassword = SecurityMD5.MD5Encode(oldPassword, userInfoPO.getChannelId());
        newPassword = SecurityMD5.MD5Encode(newPassword, userInfoPO.getChannelId());

        // 修改密码
        userManageService.modifyUserPassword(userInfoPO, oldPassword, newPassword, "", "");

        // 插入业务日志
        userManagerLog.logModifyUserPassword(userId, oldPassword, newPassword);

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        return result;
    }

    @Override
    public Result resetUserPassword(Long userId, String newPassword, String operId, String remark) throws Exception {
        // 校验数据
        UserManageValidate.validateResetUserPassword(userId, newPassword, operId);

        // 校验用户是否存在
        UserInfoPO userInfoPO = userManageService.getUserById(userId);
        
        //判断用户状态 是否 03:已禁用
        if (TypeEnum.UserState.FORBID.getKey().equals(userInfoPO.getUserState())) {
        	throw new BusinessException(ExceptionEnum.Busi.FORBID_USER_STATE.getKey(), "");
		}

        // 修改密码
        newPassword = SecurityMD5.MD5Encode(newPassword, userInfoPO.getChannelId());
        String oldPassword = userInfoPO.getPassword();
        userManageService.modifyUserPassword(userInfoPO, "", newPassword, operId, remark);

        // 插入业务日志        
        userManagerLog.logResetUserPassword(userId, oldPassword, newPassword, operId, remark);

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        return result;
    }
    
    @Override
    public Result modifyUserAuthen(ModifyUserAuthenDTO modifyUserAuthenDTO){
        // 校验数据
        UserManageValidate.validateModifyUserAuthen(modifyUserAuthenDTO);
        
        // 判断是邮箱还是手机
        modifyUserAuthenDTO.setAuthenValue(modifyUserAuthenDTO.getAuthenValue().toLowerCase());
        if (modifyUserAuthenDTO.getAuthenType().equals(TypeEnum.AuthenType.EMAIL.getKey())) {
            this.modifyEmailAuthen(modifyUserAuthenDTO);
        } else if (modifyUserAuthenDTO.getAuthenType().equals(TypeEnum.AuthenType.MOBILE.getKey())) {
            this.modifyMobileAuthen(modifyUserAuthenDTO);
        }

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        return result;
    }
    
    @Override
    public Result modifyEbayUserAgreement(EbayUserAgreementDTO ebayUserAgreementDTO){
    	
    	 // 校验数据
        UserManageValidate.validateModifyEbayUserAgreement(ebayUserAgreementDTO);
        
        // 得到用户信息
        UserInfoPO userInfoPO = userManageService.getUserById(ebayUserAgreementDTO.getUserId());

        // 判断eBay用户协议是否已经同意
        if(TypeEnum.YesOrNo.YES.getKey().equals(userInfoPO.getEbayUserAgreement())) {
            throw new BusinessException("eBay用户协议已经同意");
        }

        // 修改eBay用户协议是否已经同意标志位
        UserInfoPO tempUserInfoPO = new UserInfoPO();
        tempUserInfoPO.setUserId(ebayUserAgreementDTO.getUserId());
        tempUserInfoPO.setEbayUserAgreement(ebayUserAgreementDTO.getEbayUserAgreement());
        userManageService.modifyEbayUserAgreement(tempUserInfoPO);

        // 插入业务日志
        userManagerLog.logModifyEbayUserAgreement(ebayUserAgreementDTO, userInfoPO, tempUserInfoPO);

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        return result;
    }
    
    @Override
    public Result isAuthentic(Long userId, String authenType){
        // 校验数据
        UserManageValidate.validateIsAuthentic(userId, authenType);

        // 得到用户信息
        UserInfoPO userInfoPO = userManageService.getUserById(userId);
        
        //判断邮箱、手机是否已被认证
        String authenStatus = "";
		if (authenType.equals(TypeEnum.AuthenType.EMAIL.getKey())) {
			authenStatus = userInfoPO.getEmailAuthenStatus();
		} else if (authenType.equals(TypeEnum.AuthenType.MOBILE.getKey())) {
			authenStatus = userInfoPO.getMobileAuthenStatus();
		}		
		boolean bl = StringUtils.equals(authenStatus, TypeEnum.AuthenStatus.PASS.getKey());

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        result.setData(bl);
        return result;
    }

    @Override
    public Result getUserBasicInfoByUserId(Long userId) throws Exception {
        // 数据交验
        if (CommonValidationUtil.isEmpty(userId)) {
            throw new ParameterException("用户ID不能为空!");
        }

        Result result = null;
        try {
            // 查询用户信息
            UserInfoParam userInfoParam = new UserInfoParam();
            userInfoParam.setUserId(userId);
            UserBaseInfo userBaseInfo = userManageService.queryUserBaseInfo(userInfoParam);
            UserBaseDTO userBaseDTO = new UserBaseDTO();
            if (null == userBaseInfo) {
                throw new BusinessException(ExceptionEnum.Busi.USER_NOT_EXIST.getKey(), "");
            }
            BeanUtilEx.copyProperties(userBaseDTO, userBaseInfo);

            // 返回结果
            result = new Result();
            result.setSuccess(FacadeConstant.SUCCESS);
            result.setData(userBaseDTO);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result getUserBaseInfoList(UserQueryDTO userQueryDTO) throws Exception {
        try {
			if (StringUtils.isBlank(userQueryDTO.getEmail())
					&& StringUtils.isBlank(userQueryDTO.getMobile())
					&& StringUtils.isBlank(userQueryDTO.getPetName())
					&& StringUtils.isBlank(userQueryDTO.getLogonName())) {
        		logger.info("getUserBaseInfoList:userQueryDTO is null");
        		Result result = new Result();
                result.setSuccess(FacadeConstant.SUCCESS);
                result.setData(new ArrayList<UserBaseDTO>());
                result.setPage(new Page());
                return result;
			}
            UserInfoParam userInfoParam = new UserInfoParam();
            BeanUtilEx.copyProperties(userInfoParam, userQueryDTO);

            int currentPage = userInfoParam.getCurrentPage();
            Long totalRecord = userManageService.queryUserBaseInfoCount(userInfoParam);// 获取满足条件总记录数
            int pageSize = userInfoParam.getPageSize();
            Page page = Page.getPage(currentPage, totalRecord.intValue(), pageSize); // 计算出起始行和结束行，page对象
            userInfoParam.setFirstRow(page.getStartRec());
            userInfoParam.setLastRow(page.getEndRec());

            List<UserBaseInfo> list = userManageService.queryUserBaseInfoList(userInfoParam);

            List<UserBaseDTO> resultList = new ArrayList<UserBaseDTO>();
            UserBaseDTO dto = null;
            for (UserBaseInfo userBaseInfo : list) {
                dto = new UserBaseDTO();
                BeanUtilEx.copyProperties(dto, userBaseInfo);
                resultList.add(dto);
            }

            // 返回结果
            Result result = new Result();
            result.setSuccess(FacadeConstant.SUCCESS);
            result.setData(resultList);
            result.setPage(page);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result getUserDetailInfoByUserId(Long userId) throws Exception {
        // 数据交验
        if (CommonValidationUtil.isEmpty(userId)) {
            throw new ParameterException("用户ID不能为空!");
        }

        try {
            // 查询用户信息
            UserInfoParam userInfoParam = new UserInfoParam();
            userInfoParam.setUserId(userId);
            UserDetailInfo userDetailInfo = userManageService.queryUserDetailInfo(userInfoParam);
            if (null == userDetailInfo) {
                throw new BusinessException(ExceptionEnum.Busi.USER_NOT_EXIST.getKey(), "");
            }

            // 判断是否主用户，不是主用户则查询出主用户信息，并且将email等置换
            if (null != userDetailInfo && TypeEnum.YesOrNo.NO.getKey().equals(userDetailInfo.getIsMaster())) {
                userInfoParam = new UserInfoParam();
                userInfoParam.setCustId(userDetailInfo.getCustId());
                userInfoParam.setIsMaster(TypeEnum.YesOrNo.YES.getKey());
                UserDetailInfo masterUser = userManageService.queryUserDetailInfo(userInfoParam);
                if (StringUtils.isBlank(userDetailInfo.getEmail()) && StringUtils.isNotBlank(masterUser.getEmail())) {
                    userDetailInfo.setEmail(masterUser.getEmail());
                }
                if (StringUtils.isBlank(userDetailInfo.getMobile()) && StringUtils.isNotBlank(masterUser.getMobile())) {
                    userDetailInfo.setMobile(masterUser.getMobile());
                }
                if (StringUtils.isBlank(userDetailInfo.getPetName()) && StringUtils.isNotBlank(masterUser.getPetName())) {
                    userDetailInfo.setPetName(masterUser.getPetName());
                }
            }

            UserDetailDTO userDetailDTO = new UserDetailDTO();
            BeanUtilEx.copyProperties(userDetailDTO, userDetailInfo);
            this.getUserDetailDescFromSysParam(userDetailDTO);

            // 返回结果
            Result result = new Result();
            result.setSuccess(FacadeConstant.SUCCESS);
            result.setData(userDetailDTO);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
    

    @Override
    public Result getUserDetailInfoByLogonName(String logonName, Integer channelId) {
        // 校验数据
        UserManageValidate.validateGetUserDetailInfoByLogonName(logonName, channelId);

        // 查询用户
        UserInfoParam param = new UserInfoParam();
        if (TypeEnum.PartnerChannelType.getList().containsKey(channelId)) {// 联盟用户
            param.setPartnerId(logonName);
            param.setPartnerChannelId(channelId);
            CustInfoPO cstInfoPO = userManageService.queryCustInfo(param);
            if (null == cstInfoPO) {
                return null;
            } else {
                param.setCustId(cstInfoPO.getCustId());
                param.setChannelId(cstInfoPO.getChannelId());
            }
        } else {// 秀用户
            param.setLogonName(logonName);
        }
        List<UserDetailInfo> userList = userManageService.queryUserDetailInfoList(param);
        
        UserDetailInfo userDetailInfo = null;
        if(userList.size() == 1) {
        	userDetailInfo = userList.get(0);
        } else if(userList.size() > 1) {
        	for (UserDetailInfo user : userList) {
				if(user.getChannelId().equals(channelId)){
					userDetailInfo = user;
				}
			}
        }

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        result.setData(userDetailInfo);
        return result;
    }

    @Override
    public Result getUserDetailInfoList(UserQueryDTO userQueryDTO) throws Exception {
        try {
            UserInfoParam userInfoParam = new UserInfoParam();
            BeanUtilEx.copyProperties(userInfoParam, userQueryDTO);

            int currentPage = userInfoParam.getCurrentPage();
            Long totalRecord = userManageService.queryUserDetailInfoCount(userInfoParam);// 获取满足条件总记录数
            int pageSize = userInfoParam.getPageSize();
            Page page = Page.getPage(currentPage, totalRecord.intValue(), pageSize); // 计算出起始行和结束行，page对象
            userInfoParam.setFirstRow(page.getStartRec());
            userInfoParam.setLastRow(page.getEndRec());

            List<UserDetailInfo> list = userManageService.queryUserDetailInfoList(userInfoParam);

            List<UserDetailDTO> resultList = new ArrayList<UserDetailDTO>();
            UserDetailDTO dto = null;
            for (UserDetailInfo userDetailInfo : list) {
                dto = new UserDetailDTO();
                BeanUtilEx.copyProperties(dto, userDetailInfo);
                this.getUserDetailDescFromSysParam(dto);
                resultList.add(dto);
            }
            // 返回结果
            Result result = new Result();
            result.setSuccess(FacadeConstant.SUCCESS);
            result.setData(resultList);
            result.setPage(page);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result getPasswordByLogonNameAndChannelId(String logonName, Integer channelId) {
        // 校验数据
        UserManageValidate.validateGetPasswordByLogonNameAndChannelId(logonName, channelId);

        // 查询用户信息
        UserInfoParam userInfoParam = new UserInfoParam();
        userInfoParam.setLogonName(logonName.toLowerCase());
        userInfoParam.setChannelId(channelId);
        UserInfoPO userInfoPO = userManageService.queryUserInfo(userInfoParam);
        if (null == userInfoPO) {
            throw new BusinessException(ExceptionEnum.Busi.USER_NOT_EXIST.getKey(), "");
        }

        // 得到密码
        String password = userManageService.getUserPasswordById(userInfoPO.getUserId());
        password = "********"+password.substring(password.length() - 4, password.length());

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        result.setData(password);
        return result;
    }

    @Override
    public Result isRightPassword(Long userId, String password) throws Exception {
        // 校验数据
        UserManageValidate.validateIsRightPassword(userId, password);

        // 检查用户是否存在
        UserInfoPO user = userManageService.getUserById(userId);

        // 得用该客户的所有用户
        List<UserInfoPO> list = userManageService.getUserListByCustId(user.getCustId());
        boolean bl = false;
        for (UserInfoPO po : list) {
            if (StringUtils.isNotBlank(po.getPassword())) {
                // 对密码进行MD5加密
                String passwordMd5 = SecurityMD5.MD5Encode(password, po.getChannelId());
                // 判断密码是否正确
                if (passwordMd5.equals(po.getPassword())) {
                    bl = true;
                }
            }
        }

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        result.setData(bl);
        return result;
    }

    @Override
    public Result getUserInTargetChannel(Long userId, Integer targetChannelId) throws Exception {
        try {
            // 校验数据
            UserManageValidate.validateGetUserInTargetChannel(userId, targetChannelId);

            // 通过用户ID得到客户ID
            UserInfoPO userInfoPO = userManageService.getUserById(userId);
            Long custId = userInfoPO.getCustId();

            // 检查渠道下客户是否已有关联用户存在
            UserInfoPO targetUser = userManageService.getUserByCustIdAndChannelId(custId, targetChannelId);
            Long targetUserId = null;// 指定渠道的用户ID
            if (targetUser != null) {// 如果渠道下已存在用户，返回用户ID
                targetUserId = targetUser.getUserId();
            } else {// 如果渠道下不存在用户，则系统在该渠道下代创建一个用户
                // 生成用户信息
                UserInfoPO newUserInfoPO = new UserInfoPO();
                newUserInfoPO.setCustId(custId);
                newUserInfoPO.setChannelId(targetChannelId);
                newUserInfoPO.setIsMaster(TypeEnum.YesOrNo.NO.getKey());// 0:非主用户
                targetUserId = userManageService.insertUserInfo(newUserInfoPO);
                // 生成用户账户信息
                Long acctId = this.createUserAcct(targetUserId);
                // 初始化账目信息
                userManageService.initializeAcctItemInfo(acctId);
            }
            // 返回刚刚生成的用户基本信息
            return this.getUserBasicInfoByUserId(targetUserId);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result getUserChannelList(UserQueryDTO userQueryDTO) throws Exception {
        try {
            UserInfoParam userInfoParam = new UserInfoParam();
            BeanUtilEx.copyProperties(userInfoParam, userQueryDTO);

            int currentPage = userInfoParam.getCurrentPage();
            Long totalRecord = userManageService.queryUserChannelCount(userInfoParam);// 获取满足条件总记录数
            int pageSize = userInfoParam.getPageSize();
            Page page = Page.getPage(currentPage, totalRecord.intValue(), pageSize); // 计算出起始行和结束行，page对象
            userInfoParam.setFirstRow(page.getStartRec());
            userInfoParam.setLastRow(page.getEndRec());

            List<UserDetailInfo> list = userManageService.queryUserChannelList(userInfoParam);

            List<UserDetailDTO> resultList = new ArrayList<UserDetailDTO>();
            UserDetailDTO dto = null;
            for (UserDetailInfo userDetailInfo : list) {
                dto = new UserDetailDTO();
                BeanUtilEx.copyProperties(dto, userDetailInfo);
                this.getUserDetailDescFromSysParam(dto);
                resultList.add(dto);
            }
            // 返回结果
            Result result = new Result();
            result.setSuccess(FacadeConstant.SUCCESS);
            result.setData(resultList);
            result.setPage(page);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result leadPartnerRegister(LeadRegisterDTO leadRegisterDTO) {
        // 校验数据
        UserManageValidate.validateLeadPartnerRegister(leadRegisterDTO);

        // 对密码进行加密
        String password = SecurityMD5.MD5Encode(leadRegisterDTO.getPassword(), leadRegisterDTO.getChannelId());
        leadRegisterDTO.setPassword(password);
        logger.info("leadPartnerRegister entry: {}", jsonUtils.toJson(leadRegisterDTO));

        // 查询联盟用户是否存在
        UserInfoParam userInfoParam = new UserInfoParam();
        userInfoParam.setUserId(leadRegisterDTO.getUserId());
        UserBaseInfo userBaseInfo = userManageService.queryUserBaseInfo(userInfoParam);
        if (userBaseInfo == null) {
            throw new BusinessException(ExceptionEnum.Busi.USER_NOT_EXIST.getKey(), "");
        }
        if (userBaseInfo.getCustType().equals(TypeEnum.CustType.XIU.getKey())) {
            throw new BusinessException("您已经是走秀用户！");
        }
        
        //填充用户信息
        UserInfoPO newUserInfoPO = new UserInfoPO();
        String registerType = "";
        String email = leadRegisterDTO.getEmail();
        String mobile = leadRegisterDTO.getMobile();
        if (StringUtils.isNotBlank(email)) {
            email = email.toLowerCase();
            if (userManageService.isLogonNameExist(email) && !email.equalsIgnoreCase(userBaseInfo.getEmail())) {
                throw new BusinessException("邮箱已经被使用!");
            }
            newUserInfoPO.setEmail(email);
            registerType = TypeEnum.RegisterType.EMAIL.getKey();
        } else if (StringUtils.isNotBlank(mobile)) {
            mobile = mobile.toLowerCase();
            if (userManageService.isLogonNameExist(mobile) && !mobile.equalsIgnoreCase(userBaseInfo.getMobile())) {
                throw new BusinessException("手机号已经被使用!");
            }
            newUserInfoPO.setMobile(mobile);
            newUserInfoPO.setMobileAuthenStatus(TypeEnum.AuthenStatus.PASS.getKey());
            registerType = TypeEnum.RegisterType.MOBILE.getKey();
        }    
        newUserInfoPO.setUserId(leadRegisterDTO.getUserId());
        newUserInfoPO.setPassword(leadRegisterDTO.getPassword());
        newUserInfoPO.setPetName("");

        //填充客户信息
        CustInfoPO newCustInfoPO = new CustInfoPO();
        newCustInfoPO.setCustId(userBaseInfo.getCustId());
        newCustInfoPO.setCustType(TypeEnum.CustType.XIU.getKey());
        newCustInfoPO.setRegisterType(registerType);

        // 调用Service的方法
        userManageService.leadPartnerRegister(newUserInfoPO, newCustInfoPO);

        // 插入业务日志
        userManagerLog.logLeadPartnerRegister(leadRegisterDTO);

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        return result;
    }

    /** =====================================================私有方法=============================================== */

    // 生成用户账户信息
    private Long createUserAcct(Long userId) {
        UserAcctPO userAcctPO = new UserAcctPO();
        userAcctPO.setUserId(userId);
        Long acctId = userManageService.insertUserAcctInfo(userAcctPO);
        return acctId;
    }

    // 将Code翻译
    private void getUserDetailDescFromSysParam(UserDetailDTO userDetailDTO) throws Exception {
        SysParamUtil sysParamUtil = SysParamUtil.getInstance(sysParamFacade);
        if (StringUtils.isNotBlank(userDetailDTO.getProvinceCode())) {
            userDetailDTO.setProvinceCodeDesc(sysParamUtil.getParamDescByCode(userDetailDTO.getProvinceCode()));
        }
        if (StringUtils.isNotBlank(userDetailDTO.getRegionCode())) {
            userDetailDTO.setRegionCodeDesc(sysParamUtil.getParamDescByCode(userDetailDTO.getRegionCode()));
        }
        if (StringUtils.isNotBlank(userDetailDTO.getCityCode())) {
            userDetailDTO.setCityCodeDesc(sysParamUtil.getParamDescByCode(userDetailDTO.getCityCode()));
            userDetailDTO.setCityCodeRemark(sysParamUtil.getParamRemarkByCode(userDetailDTO.getCityCode()));
        }
        if (StringUtils.isNotBlank(userDetailDTO.getWorkType())) {
            String desc = "";
            if (KeyNames.WORK_TYPE_STYLE_TEXT.equals(userDetailDTO.getWorkTypeStyle())) {
                desc = userDetailDTO.getWorkType();
            } else {
                desc = sysParamUtil.getParamDescByCode(userDetailDTO.getWorkType());
            }
            userDetailDTO.setWorkTypeDesc(desc);
        }
        if (StringUtils.isNotBlank(userDetailDTO.getWorkType())) {
            String desc = sysParamUtil.getParamDescByCode(userDetailDTO.getWorkType());
            if (StringUtils.isBlank(desc)) {
                desc = userDetailDTO.getWorkType();
            }
            userDetailDTO.setWorkTypeDesc(desc);
        }
        if (StringUtils.isNotBlank(userDetailDTO.getSex())) {
            userDetailDTO.setSexDesc(sysParamUtil.getParamDescByCode(userDetailDTO.getSex()));
        }
        if (StringUtils.isNotBlank(userDetailDTO.getDegree())) {
            userDetailDTO.setDegreeDesc(sysParamUtil.getParamDescByCode(userDetailDTO.getDegree()));
        }
        if (StringUtils.isNotBlank(userDetailDTO.getMonthIncome())) {
            userDetailDTO.setMonthIncomeDesc(sysParamUtil.getParamDescByCode(userDetailDTO.getMonthIncome()));
        }
        if (StringUtils.isNotBlank(userDetailDTO.getMarry())) {
            userDetailDTO.setMarryDesc(sysParamUtil.getParamDescByCode(userDetailDTO.getMarry()));
        }
        if (StringUtils.isNotBlank(userDetailDTO.getSourceType())) {
            userDetailDTO.setSourceTypeDesc(sysParamUtil.getParamDescByCode(userDetailDTO.getSourceType()));
        }
    }

    /**********************************
     * 私有方法
     * 
     * @throws Exception
     **************************************************/
    private void modifyUserState(UserBaseInfo userInfo, String busiOperType) throws Exception {
        // 判断是否能够操作业务
        CoreUtil.processBusiStatus(userInfo.getCustState(), userInfo.getUserState(), busiOperType);

        // 修改用户状态
        if (busiOperType.equals(CommonEnum.BusiOperType.ACTIVATE_USER.getKey())) {
            CustInfoPO custInfoPO = new CustInfoPO();
            custInfoPO.setCustId(userInfo.getCustId());
            custInfoPO.setCustState(TypeEnum.CustState.NATURAL.getKey());
            userManageService.modifyCustInfo(custInfoPO);

            // 插入业务日志
            userManagerLog.logModifyCustState(userInfo, busiOperType, userInfo.getCustState(),
                    TypeEnum.CustState.NATURAL.getKey());
        } else {
            String newState = "";
            if (busiOperType.equals(CommonEnum.BusiOperType.FORBIT_USER.getKey())) {
                newState = TypeEnum.UserState.FORBID.getKey();
            } else if (busiOperType.equals(CommonEnum.BusiOperType.UNFORBIT_USER.getKey())) {
                newState = TypeEnum.UserState.NATURAL.getKey();
            } else if (busiOperType.equals(CommonEnum.BusiOperType.DELETE_USER.getKey())) {
                newState = TypeEnum.UserState.DELETED.getKey();
            }
            userManageService.modifyUserState(userInfo.getUserId(), newState);

            // 插入业务日志
            userManagerLog.logModifyUserState(userInfo, busiOperType, userInfo.getUserState(), newState);
        }

    }
    
    private void modifyEmailAuthen(ModifyUserAuthenDTO modifyUserAuthenDTO) {
        // 得到用户信息
        UserInfoPO userInfoPO = userManageService.getUserById(modifyUserAuthenDTO.getUserId());

        // 判断邮箱是否已被占用
        if (!modifyUserAuthenDTO.getAuthenValue().equals(userInfoPO.getEmail())) {
            boolean bl = userManageService.isLogonNameExist(modifyUserAuthenDTO.getAuthenValue());
            if (bl) {
                throw new BusinessException("邮箱已经被使用!");
            }
        } else if (StringUtils.equals(userInfoPO.getEmailAuthenStatus(), TypeEnum.AuthenStatus.PASS.getKey())) {
            throw new BusinessException("邮箱已经认证过!");
        }

        // 修改邮箱和认证状态
        UserInfoPO tempUserInfoPO = new UserInfoPO();
        tempUserInfoPO.setUserId(modifyUserAuthenDTO.getUserId());
        tempUserInfoPO.setEmail(modifyUserAuthenDTO.getAuthenValue());
        tempUserInfoPO.setEmailAuthenStatus(TypeEnum.AuthenStatus.PASS.getKey());
        userManageService.modifyUserAuthen(tempUserInfoPO);

        // 插入业务日志
        userManagerLog.logModifyEmailAuthen(modifyUserAuthenDTO, userInfoPO, tempUserInfoPO);

    }
    
    private void modifyMobileAuthen(ModifyUserAuthenDTO modifyUserAuthenDTO) {
        // 得到用户信息
        UserInfoPO userInfoPO = userManageService.getUserById(modifyUserAuthenDTO.getUserId());

        // 判断手机是否已被占用
        if (!modifyUserAuthenDTO.getAuthenValue().equals(userInfoPO.getMobile())) {
            boolean bl = userManageService.isLogonNameExist(modifyUserAuthenDTO.getAuthenValue());
            if (bl) {
                throw new BusinessException("手机号已经被使用!");
            }
        } else if (StringUtils.equals(userInfoPO.getMobileAuthenStatus(), TypeEnum.AuthenStatus.PASS.getKey())) {
            throw new BusinessException("手机号已经认证过!");
        }

        // 修改邮箱和认证状态
        UserInfoPO tempUserInfoPO = new UserInfoPO();
        tempUserInfoPO.setUserId(modifyUserAuthenDTO.getUserId());
        tempUserInfoPO.setMobile(modifyUserAuthenDTO.getAuthenValue());
        tempUserInfoPO.setMobileAuthenStatus(TypeEnum.AuthenStatus.PASS.getKey());
        userManageService.modifyUserAuthen(tempUserInfoPO);

        // 插入业务日志
        userManagerLog.logModifyMobileAuthen(modifyUserAuthenDTO, userInfoPO, tempUserInfoPO);

    }

}
