package com.xiu.uuc.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.xiu.uuc.dal.dao.CommonDAO;
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

public class UserManageDAOImpl extends SqlSessionDaoSupport implements UserManageDAO {

	@Autowired
	private CommonDAO commonDAO;

	@Override
	public Long insertCustInfo(CustInfoPO custInfoPO) throws DataAccessException {
		Long custId = commonDAO.getSequenceByName("SEQ_X_UUC_CUST_INFO_ID");
		custInfoPO.setCustId(custId);
		this.getSqlSession().insert("userManage.uuc_cust_info_insert", custInfoPO);
		return custId;
	}

	@Override
	public void modifyCustInfo(CustInfoPO custInfoPO) throws DataAccessException {
		this.getSqlSession().update("userManage.uuc_cust_info_modify", custInfoPO);
	}

    @Override
    public void modifyCustLeadRegister(CustInfoPO custInfoPO) throws DataAccessException {
        this.getSqlSession().update("userManage.uuc_cust_info_leadregister_modify", custInfoPO);
    }

	@Override
	public CustInfoPO queryCustInfo(UserInfoParam userInfoParam) throws DataAccessException {
		return (CustInfoPO) this.getSqlSession().selectOne("userManage.uuc_cust_info_query", userInfoParam);
	}
	
	@Override
	public void insertCustExtInfo(CustExtInfoPO custExtInfoPO) throws DataAccessException{
		this.getSqlSession().insert("userManage.uuc_cust_ext_info_insert", custExtInfoPO);
	}
	
	@Override
	public void modifyCustExtInfo(CustExtInfoPO custExtInfoPO) throws DataAccessException{
		this.getSqlSession().insert("userManage.uuc_cust_ext_info_modify", custExtInfoPO);
	}

	
	
	
	@Override
	public Long insertUserInfo(UserInfoPO userInfoPO) throws DataAccessException {
		Long userId = commonDAO.getSequenceByName("SEQ_X_UUC_USER_INFO_ID");
		userInfoPO.setUserId(userId);
		this.getSqlSession().insert("userManage.uuc_user_info_insert", userInfoPO);
		return userId;
	}

	@Override
	public void modifyUserInfo(UserInfoPO userInfoPO) throws DataAccessException {
		this.getSqlSession().update("userManage.uuc_user_info_modify", userInfoPO);	
	}
	
	@Override
	public void modifyUserAuthen(UserInfoPO userInfoPO) throws DataAccessException {
		this.getSqlSession().update("userManage.uuc_user_authentic_modify", userInfoPO);	
	}
	
	@Override
	public void modifyEbayUserAgreement(UserInfoPO userInfoPO) throws DataAccessException{
		this.getSqlSession().update("userManage.uuc_user_ebay_agreement_modify", userInfoPO);	
	}

	@Override
	public void modifyUserPassword(UserInfoPO userInfoPO) throws DataAccessException {
		this.getSqlSession().update("userManage.uuc_user_info_modify_password", userInfoPO);	
	}

    @Override
    public void modifyUserLeadRegister(UserInfoPO userInfoPO) throws DataAccessException {
        this.getSqlSession().update("userManage.uuc_user_info_leadregister_modify", userInfoPO);    
    }

	@Override
	public UserInfoPO queryUserInfo(UserInfoParam userInfoParam) throws DataAccessException {
		return (UserInfoPO) this.getSqlSession().selectOne("userManage.uuc_user_info_query", userInfoParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfoPO> queryUserInfoList(UserInfoParam userInfoParam) throws DataAccessException {
		List<UserInfoPO> list = (List<UserInfoPO>)this.getSqlSession().selectList("userManage.uuc_user_info_query", userInfoParam);
		if(null == list){
			list = new ArrayList<UserInfoPO>();
		}
		return list;
	}
	
	
	

	@Override
	public Long insertRegisterIp(RegisterIpPO registerIpPO) throws DataAccessException {
		Long ipId = commonDAO.getSequenceByName("SEQ_X_UUC_REGISTER_IP_ID");
		registerIpPO.setIpId(ipId);
		this.getSqlSession().insert("userManage.uuc_register_ip_insert", registerIpPO);
		return ipId;
	}

	@Override
	public Integer queryRegisterIpCount(RegisterIpPO registerIpPO) throws DataAccessException{
		return (Integer)this.getSqlSession().selectOne("userManage.uuc_register_ip_count", registerIpPO);
	}
	
	public void insertSsoScheduleTask(Map<String, Object> paramMap) throws DataAccessException{
		this.getSqlSession().insert("userManage.uuc_sso_schedule_task_insert", paramMap);
	}

	
	
	
	@Override
	public Long insertUserAcctInfo(UserAcctPO userAcctPO) throws DataAccessException {
		Long acctId = commonDAO.getSequenceByName("SEQ_X_UUC_USER_ACCT_ID");
		userAcctPO.setAcctId(acctId);
		this.getSqlSession().insert("userManage.uuc_user_acct_insert", userAcctPO);
		return acctId;
	}

	@Override
	public Long insertAcctItemInfo(AcctItemPO acctItemPO) throws DataAccessException {
		Long acctItemId = commonDAO.getSequenceByName("SEQ_X_UUC_ACCT_ITEM_ID");
		acctItemPO.setAcctItemId(acctItemId);
		this.getSqlSession().insert("userManage.uuc_acct_item_insert", acctItemPO);
		return acctItemId;
	}

	
	
	

	@Override
	public Long queryUserBaseInfoCount(UserInfoParam userInfoParam) throws DataAccessException {
		return (Long)this.getSqlSession().selectOne("userManage.uuc_user_base_list_count", userInfoParam);
	}

	@Override
	public UserBaseInfo queryUserBaseInfo(UserInfoParam userInfoParam) throws DataAccessException {
		return (UserBaseInfo)this.getSqlSession().selectOne("userManage.uuc_user_base_info_query", userInfoParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserBaseInfo> queryUserBaseInfoList(UserInfoParam userInfoParam) throws DataAccessException {
		List<UserBaseInfo> list = (List<UserBaseInfo>)this.getSqlSession().selectList("userManage.uuc_user_base_list_query", userInfoParam);
		if(null == list){
			list = new ArrayList<UserBaseInfo>();
		}
		return list;
	}

	@Override
	public Long queryUserDetailInfoCount(UserInfoParam userInfoParam) throws DataAccessException {
		return (Long)this.getSqlSession().selectOne("userManage.uuc_user_detail_list_count", userInfoParam);
	}

	@Override
	public UserDetailInfo queryUserDetailInfo(UserInfoParam userInfoParam) throws DataAccessException {
		return (UserDetailInfo)this.getSqlSession().selectOne("userManage.uuc_user_detail_info_query", userInfoParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetailInfo> queryUserDetailInfoList(UserInfoParam userInfoParam) throws DataAccessException {
		List<UserDetailInfo> list = (List<UserDetailInfo>)this.getSqlSession().selectList("userManage.uuc_user_detail_list_query", userInfoParam);
		if(null == list){
			list = new ArrayList<UserDetailInfo>();
		}
		return list;
	}

	@Override
	public Long queryUserChannelCount(UserInfoParam userInfoParam) throws DataAccessException {
		return (Long)this.getSqlSession().selectOne("userManage.uuc_user_channel_list_count", userInfoParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetailInfo> queryUserChannelList(UserInfoParam userInfoParam) throws DataAccessException {
		List<UserDetailInfo> list = (List<UserDetailInfo>)this.getSqlSession().selectList("userManage.uuc_user_channel_list_query", userInfoParam);
		if(null == list){
			list = new ArrayList<UserDetailInfo>();
		}
		return list;
	}



}
