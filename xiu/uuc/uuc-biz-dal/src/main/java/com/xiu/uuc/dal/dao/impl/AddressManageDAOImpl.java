package com.xiu.uuc.dal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.xiu.uuc.dal.dao.AddressManageDAO;
import com.xiu.uuc.dal.dao.CommonDAO;
import com.xiu.uuc.dal.param.RcvAddressParam;
import com.xiu.uuc.dal.po.RcvAddressPO;

public class AddressManageDAOImpl extends SqlSessionDaoSupport implements AddressManageDAO {

	private CommonDAO commonDAO;
	

	@Override
	public Long addRcvAddress(RcvAddressPO rcvAddressPO) throws DataAccessException {
		Long addressId = commonDAO.getSequenceByName("SEQ_X_UUC_RCV_ADDRESS_ID");
		rcvAddressPO.setAddressId(addressId);
		this.getSqlSession().insert("addressManage.uuc_rcv_address_insert", rcvAddressPO);
		return addressId;
	}

	@Override
	public void modifyRcvAddress(RcvAddressPO rcvAddressPO) throws DataAccessException {
		this.getSqlSession().update("addressManage.uuc_rcv_address_modify", rcvAddressPO);
	}

	@Override
	public void modifyRcvAddressCust(RcvAddressParam rcvAddressParam) throws DataAccessException {
		this.getSqlSession().update("addressManage.uuc_rcv_address_modify_cust", rcvAddressParam);
	}

	@Override
	public void deleteRcvAddress(Long rcvAddressId) throws DataAccessException {
		this.getSqlSession().delete("addressManage.uuc_rcv_address_delete", rcvAddressId);
		
	}

	@Override
	public RcvAddressPO queryRcvAddress(RcvAddressParam rcvAddressParam) throws DataAccessException {
		return (RcvAddressPO)this.getSqlSession().selectOne("addressManage.uuc_rcv_address_query", rcvAddressParam);
		
	}
		
	@Override
	@SuppressWarnings("unchecked")
	public List<RcvAddressPO> queryRcvAddressList(RcvAddressParam rcvAddressParam) throws DataAccessException {
		List<RcvAddressPO> list = (List<RcvAddressPO>)this.getSqlSession().selectList("addressManage.uuc_rcv_address_query", rcvAddressParam);
		if(null == list){
			list = new ArrayList<RcvAddressPO>();
		}
		return list;
	}

	@Override
	public Long queryRcvAddressCount(RcvAddressParam rcvAddressParam) throws DataAccessException {
		return (Long)this.getSqlSession().selectOne("addressManage.uuc_rcv_address_count", rcvAddressParam);
		
	}
	
	
	@Autowired
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}




}
