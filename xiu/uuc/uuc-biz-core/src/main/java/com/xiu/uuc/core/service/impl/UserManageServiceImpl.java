package com.xiu.uuc.core.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.util.ExceptionEnum;
import com.xiu.uuc.common.util.JsonUtils;
import com.xiu.uuc.common.util.KeyNames;
import com.xiu.uuc.common.util.SecurityMD5;
import com.xiu.uuc.core.service.UserManageService;
import com.xiu.uuc.dal.dao.UserManageDAO;
import com.xiu.uuc.dal.param.UserInfoParam;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.dal.po.CustExtInfoPO;
import com.xiu.uuc.dal.po.CustInfoPO;
import com.xiu.uuc.dal.po.RegisterIpPO;
import com.xiu.uuc.dal.po.UserAcctPO;
import com.xiu.uuc.dal.po.UserBaseInfo;
import com.xiu.uuc.dal.po.UserDetailInfo;
import com.xiu.uuc.dal.po.UserInfoPO;
import com.xiu.uuc.facade.util.TypeEnum;

public class UserManageServiceImpl implements UserManageService {

    private static final Logger logger = LoggerFactory.getLogger(UserManageServiceImpl.class);
    private UserManageDAO userManageDAO;

    @Override
    public Long insertCustInfo(String isNeedActivate, CustInfoPO custInfoPO) throws BusinessException {
        // Email注册
        if (TypeEnum.RegisterType.EMAIL.getKey().equals(custInfoPO.getRegisterType())) {
            if (StringUtils.equals(TypeEnum.YesOrNo.NO.getKey(), isNeedActivate)) {
                custInfoPO.setCustState(TypeEnum.CustState.NATURAL.getKey());// 客户状态为'正常'
            } else {
                custInfoPO.setCustState(TypeEnum.CustState.NOT_ACTIVATION.getKey());// 客户状态默认为'未激活'
            }
            // 手机号注册、昵称注册
        } else {
            custInfoPO.setCustState(TypeEnum.CustState.NATURAL.getKey());// 客户状态为'正常'
        }
        if (StringUtils.isEmpty(custInfoPO.getCustLevel())) {
            custInfoPO.setCustLevel(KeyNames.DEFAULT_LEVEL);// 默认客户等级
        }
        return userManageDAO.insertCustInfo(custInfoPO);
    }

    @Override
    public void modifyCustInfo(CustInfoPO custInfoPO) throws BusinessException {
        userManageDAO.modifyCustInfo(custInfoPO);
    }

    @Override
    public CustInfoPO queryCustInfo(UserInfoParam userInfoParam) throws BusinessException {
        return userManageDAO.queryCustInfo(userInfoParam);
    }

    @Override
    public CustInfoPO getCustById(Long custId) throws BusinessException {
        UserInfoParam param = new UserInfoParam();
        param.setCustId(custId);
        CustInfoPO po = userManageDAO.queryCustInfo(param);
        return po;
    }

    @Override
    public void insertCustExtInfo(CustExtInfoPO custExtInfoPO) throws BusinessException {
        custExtInfoPO.setAttrType(KeyNames.DEFAULT_CUST_ATTR_TYPE);
        custExtInfoPO.setSubscibeInfo(KeyNames.DEFAULT_SUBSCIBE_INFO);
        if (StringUtils.isNotEmpty(custExtInfoPO.getWorkType())
                && StringUtils.isEmpty(custExtInfoPO.getWorkTypeStyle())) {
            custExtInfoPO.setWorkTypeStyle(KeyNames.WORK_TYPE_STYLE_SINGLE);
        }
        userManageDAO.insertCustExtInfo(custExtInfoPO);
    }

    @Override
    public Long insertUserInfo(UserInfoPO userInfoPO) throws BusinessException {
        userInfoPO.setUserState(TypeEnum.UserState.NATURAL.getKey());// 默认用户状态
        if (StringUtils.isEmpty(userInfoPO.getUserLevel())) {
            userInfoPO.setUserLevel(KeyNames.DEFAULT_LEVEL);
        }
        if (StringUtils.isEmpty(userInfoPO.getEmailAuthenStatus())) {
            userInfoPO.setEmailAuthenStatus(TypeEnum.AuthenStatus.NOT.getKey());
        }
        if (StringUtils.isEmpty(userInfoPO.getMobileAuthenStatus())) {
            userInfoPO.setMobileAuthenStatus(TypeEnum.AuthenStatus.NOT.getKey());
        }
        return userManageDAO.insertUserInfo(userInfoPO);
    }

    @Override
    public void modifyUserInfo(UserInfoPO userInfoPO) throws BusinessException {
        // 查询用户信息
        UserInfoPO user = this.getUserById(userInfoPO.getUserId());

        boolean isSchedule = false;
        // 判断呢称、Email、手机号友的唯一性
        if (StringUtils.isNotBlank(userInfoPO.getPetName())
                && !StringUtils.equals(user.getPetName(), userInfoPO.getPetName())) {
            UserInfoPO po = this.isLogonNameExist(userInfoPO.getPetName(), null);
            if (null != po && !po.getUserId().equals(userInfoPO.getUserId())) {
                throw new BusinessException("呢称已经被使用!");
            }
            isSchedule = true;
        }
        if (StringUtils.isNotBlank(userInfoPO.getEmail())
                && !StringUtils.equals(user.getEmail(), userInfoPO.getEmail())) {
            UserInfoPO po = this.isLogonNameExist(userInfoPO.getEmail(), null);
            if (null != po && !po.getUserId().equals(userInfoPO.getUserId())) {
                throw new BusinessException("Eamil已经被使用!");
            }
            isSchedule = true;
        }
        if (StringUtils.isNotBlank(userInfoPO.getMobile())
                && !StringUtils.equals(user.getMobile(), userInfoPO.getMobile())) {
            UserInfoPO po = this.isLogonNameExist(userInfoPO.getMobile(), null);
            if (null != po && !po.getUserId().equals(userInfoPO.getUserId())) {
                throw new BusinessException("手机号已经被使用!");
            }
            isSchedule = true;
        }
        userManageDAO.modifyUserInfo(userInfoPO);

        // 通知SSO
        if (true == isSchedule) {
            this.insertSsoScheduleTask(userInfoPO.getUserId(), null);
        }
    }
    
    @Override
    public void modifyUserAuthen(UserInfoPO userInfoPO){
        userManageDAO.modifyUserAuthen(userInfoPO);

        // 通知SSO
        this.insertSsoScheduleTask(userInfoPO.getUserId(), null);
    }
    
    @Override
    public void modifyEbayUserAgreement(UserInfoPO userInfoPO){
        userManageDAO.modifyEbayUserAgreement(userInfoPO);
    }

    @Override
    public void modifyUserPassword(UserInfoPO userInfoPO, String oldPassword, String newPassword, String operId,
            String remark) throws BusinessException {
        if (StringUtils.isNotEmpty(oldPassword) && !oldPassword.equals(userInfoPO.getPassword())) {
            throw new BusinessException("输入的当前登录密码不正确!");
        }
        userInfoPO.setPassword(newPassword);
        userInfoPO.setOperId(operId);
        userManageDAO.modifyUserPassword(userInfoPO);

        // 通知SSO
        this.insertSsoScheduleTask(userInfoPO.getUserId(), null);
    }

    @Override
    public void modifyUserState(Long userId, String userState) throws BusinessException {
        // 修改用户状态
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setUserId(userId);
        userInfoPO.setUserState(userState);
        userManageDAO.modifyUserInfo(userInfoPO);

        // 通知SSO
        String operFlag = "";
        if (userState.equals(TypeEnum.UserState.NATURAL.getKey())) {
            operFlag = "unforbitUser";
        }
        this.insertSsoScheduleTask(userInfoPO.getUserId(), operFlag);
    }

    @Override
    public UserInfoPO queryUserInfo(UserInfoParam userInfoParam) throws BusinessException {
        return userManageDAO.queryUserInfo(userInfoParam);
    }

    @Override
    public List<UserInfoPO> queryUserInfoList(UserInfoParam userInfoParam) throws BusinessException {
        return userManageDAO.queryUserInfoList(userInfoParam);
    }

    @Override
    public UserInfoPO isLogonNameExist(String logonName, Integer channelId) throws BusinessException {
        // 查询用户
        UserInfoParam param = new UserInfoParam();
        if (TypeEnum.PartnerChannelType.getList().containsKey(channelId)) {// 联盟用户
            param.setPartnerId(logonName);
            param.setPartnerChannelId(channelId);
            CustInfoPO cstInfoPO = this.queryCustInfo(param);
            if (null == cstInfoPO) {
                return null;
            } else {
                param.setCustId(cstInfoPO.getCustId());
                param.setChannelId(cstInfoPO.getChannelId());
            }
        } else {// 秀用户
            param.setLogonName(logonName);
        }
        List<UserInfoPO> list = this.queryUserInfoList(param);
        if (list.size() == 0) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            logger.error("用户名'" + logonName + "' 已存在" + list.size() + "条数据，请检查!");
            return list.get(0);
        }
    }

    @Override
    public boolean isLogonNameExist(String logonName) throws BusinessException {
        // 查询用户
        UserInfoParam param = new UserInfoParam();
        param.setLogonName(logonName);
        Long count = this.queryUserBaseInfoCount(param);
        if(count>0){
        	return true;
        }
        return false;
    }

    @Override
    public UserInfoPO getUserById(Long userId) throws BusinessException {
        UserInfoParam param = new UserInfoParam();
        param.setUserId(userId);
        UserInfoPO userInfoPO = this.queryUserInfo(param);
        if (null == userInfoPO) {
            throw new BusinessException(ExceptionEnum.Busi.USER_NOT_EXIST.getKey(), "");
        }
        return userInfoPO;
    }

    @Override
    public UserInfoPO getUserByNameAndPassword(String logonName, String password, Integer channelId)
            throws BusinessException {
        UserInfoParam param = new UserInfoParam();
        param.setLogonName(logonName);
        param.setPassword(password);
        param.setChannelId(channelId);
        UserInfoPO userInfoPO = this.queryUserInfo(param);
        return userInfoPO;
    }

    @Override
    public UserInfoPO getUserByCustIdAndChannelId(Long custId, Integer channelId) throws BusinessException {
        UserInfoParam param = new UserInfoParam();
        param.setCustId(custId);
        param.setChannelId(channelId);
        UserInfoPO userInfoPO = this.queryUserInfo(param);
        return userInfoPO;
    }

    @Override
    public List<UserInfoPO> getUserListByCustId(Long custId) throws BusinessException {
        UserInfoParam userInfoParam = new UserInfoParam();
        userInfoParam.setCustId(custId);
        List<UserInfoPO> list = this.queryUserInfoList(userInfoParam);
        return list;
    }

    @Override
    public String getUserPasswordById(Long userId) throws BusinessException {
        UserInfoPO userInfoPO = this.getUserById(userId);
        return userInfoPO.getPassword();
    }

    @Override
    public Long insertRegisterIp(RegisterIpPO registerIpPO) throws BusinessException {
        try {
            return userManageDAO.insertRegisterIp(registerIpPO);
        } catch (DataAccessException e) {
            throw new BusinessException(this.getClass().getName() + ".insertRegisterIp:" + e);
        }
    }

    @Override
    public Integer queryRegisterIpCount(RegisterIpPO registerIpPO) throws BusinessException {
        return userManageDAO.queryRegisterIpCount(registerIpPO);
    }

    @Override
    public Long insertUserAcctInfo(UserAcctPO userAcctPO) throws BusinessException {
        try {
            userAcctPO.setAcctState(TypeEnum.UserAcctState.NATURAL.getKey());
            return userManageDAO.insertUserAcctInfo(userAcctPO);
        } catch (DataAccessException e) {
            throw new BusinessException(this.getClass().getName() + ".insertUserAcctInfo:" + e);
        }
    }

    @Override
    public void initializeAcctItemInfo(Long acctId) throws BusinessException {
        this.insertAcctItemInfo(acctId, TypeEnum.AcctItemType.WITHDRAWAL_YES.getKey());// 可提现账户
        this.insertAcctItemInfo(acctId, TypeEnum.AcctItemType.WITHDRAWAL_NO.getKey());// 不可提现账户
        this.insertAcctItemInfo(acctId, TypeEnum.AcctItemType.INTEGRAL.getKey());// 积分账户
    }

    @Override
    public UserBaseInfo queryUserBaseInfo(UserInfoParam userInfoParam) throws BusinessException {
        return userManageDAO.queryUserBaseInfo(userInfoParam);
    }

    @Override
    public Long queryUserBaseInfoCount(UserInfoParam userInfoParam) throws BusinessException {
        return userManageDAO.queryUserBaseInfoCount(userInfoParam);
    }

    @Override
    public List<UserBaseInfo> queryUserBaseInfoList(UserInfoParam userInfoParam) throws BusinessException {
        return userManageDAO.queryUserBaseInfoList(userInfoParam);
    }

    @Override
    public Long queryUserDetailInfoCount(UserInfoParam userInfoParam) throws BusinessException {
        return userManageDAO.queryUserDetailInfoCount(userInfoParam);
    }

    @Override
    public UserDetailInfo queryUserDetailInfo(UserInfoParam userInfoParam) throws BusinessException {
        return userManageDAO.queryUserDetailInfo(userInfoParam);
    }

    @Override
    public List<UserDetailInfo> queryUserDetailInfoList(UserInfoParam userInfoParam) throws BusinessException {
        return userManageDAO.queryUserDetailInfoList(userInfoParam);
    }

    @Override
    public void modifyCustExtInfo(CustExtInfoPO custExtInfoPO) throws BusinessException {
        try {
            userManageDAO.modifyCustExtInfo(custExtInfoPO);
        } catch (DataAccessException e) {
            throw new BusinessException(this.getClass().getName() + ".custExtInfoPO:" + e);
        }
    }

    @Override
    public Long queryUserChannelCount(UserInfoParam userInfoParam) throws BusinessException {
        return userManageDAO.queryUserChannelCount(userInfoParam);
    }

    @Override
    public List<UserDetailInfo> queryUserChannelList(UserInfoParam userInfoParam) throws BusinessException {
        return userManageDAO.queryUserChannelList(userInfoParam);
    }

    @Override
    public void leadPartnerRegister(UserInfoPO userInfoPO, CustInfoPO custInfoPO) {
        //修改用户表
        userManageDAO.modifyUserLeadRegister(userInfoPO);

        //修改客户表
        userManageDAO.modifyCustLeadRegister(custInfoPO);
    }
    
    
    
    
    

    private void insertAcctItemInfo(Long acctId, String acctTypeCode) throws BusinessException {
        try {
            AcctItemPO acctItemPO = new AcctItemPO();
            acctItemPO.setAcctId(acctId);
            acctItemPO.setAcctTypeCode(acctTypeCode);
            acctItemPO.setAcctItemState(TypeEnum.AcctItemState.NATURAL.getKey());
            userManageDAO.insertAcctItemInfo(acctItemPO);
        } catch (DataAccessException e) {
            throw new BusinessException(this.getClass().getName() + ".insertAcctItemInfo:" + e);
        }
    }

    @SuppressWarnings("unchecked")
    private void insertSsoScheduleTask(Long userId, String operFlag) {
        String url = "";
        try {
            Long timestamp = System.currentTimeMillis();
            String signature = SecurityMD5.MD5Encode(String.valueOf(userId) + operFlag + String.valueOf(timestamp)
                    + "#salkd687dsj9sk!");
            url = KeyNames.SSO_SERVER_URL;
            url += "?userId=" + String.valueOf(userId);
            url += "&operFlag=" + operFlag;
            url += "&timestamp=" + timestamp;
            url += "&signature=" + signature;

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream is = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                Map<String, String> map = new HashMap<String, String>();
                String line = "";
                while ((line = br.readLine()) != null) {
                    map = new JsonUtils().fromJson(line, Map.class);
                }
                String code = map.get("code");
                if (!StringUtils.equals(code, "1")) {// 1:成功
                    logger.error("调用SSO异常:" + url);
                    throw new BusinessException("网络异常，请稍后再试!");
                }
            }
        } catch (Exception e) {
            logger.error("调用SSO异常:" + url, e);
            throw new BusinessException("网络异常，请稍后再试!", e);
        }

    }

    @Autowired
    public void setUserManageDAO(UserManageDAO userManageDAO) {
        this.userManageDAO = userManageDAO;
    }


}
