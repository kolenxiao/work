package com.xiu.uuc.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.core.service.AddressManageService;
import com.xiu.uuc.dal.dao.AddressManageDAO;
import com.xiu.uuc.dal.param.RcvAddressParam;
import com.xiu.uuc.dal.po.RcvAddressPO;

public class AddressManageServiceImpl implements AddressManageService {

    @Autowired
	private AddressManageDAO addressManageDAO;

	@Override
	public Long addRcvAddress(RcvAddressPO rcvAddressPO) throws BusinessException {
		this.proceRepeatAddress(rcvAddressPO);
		return addressManageDAO.addRcvAddress(rcvAddressPO);
	}	

	@Override
	public void modifyRcvAddress(RcvAddressPO rcvAddressPO) throws BusinessException {
	    this.proceRepeatAddress(rcvAddressPO);
		addressManageDAO.modifyRcvAddress(rcvAddressPO);
	}

    @Override
    public void modifyRcvAddressMaster(Long custId, Long addressId, String isMaster) {
        RcvAddressPO rcvAddressPO = new RcvAddressPO();
        rcvAddressPO.setCustId(custId);
        rcvAddressPO.setAddressId(addressId);
        rcvAddressPO.setIsMaster(isMaster);
        addressManageDAO.modifyRcvAddress(rcvAddressPO);
    }

	@Override
	public void modifyRcvAddressCust(RcvAddressParam rcvAddressParam) throws BusinessException {
		addressManageDAO.modifyRcvAddressCust(rcvAddressParam);
	}

	@Override
	public void deleteRcvAddress(Long rcvAddressId) throws BusinessException {
		addressManageDAO.deleteRcvAddress(rcvAddressId);
	}

	@Override
	public RcvAddressPO queryRcvAddress(RcvAddressParam rcvAddressParam) throws BusinessException {
		return addressManageDAO.queryRcvAddress(rcvAddressParam);
	}

	@Override
	public RcvAddressPO getRcvAddressInfoById(Long addressId) throws BusinessException {
		RcvAddressParam rcvAddressParam = new RcvAddressParam();
		rcvAddressParam.setAddressId(addressId);
		return this.queryRcvAddress(rcvAddressParam);
	}
	
	@Override
	public List<RcvAddressPO> queryRcvAddressList(RcvAddressParam rcvAddressParam) throws BusinessException{
		return addressManageDAO.queryRcvAddressList(rcvAddressParam);
	}
	
	@Override
	public Long queryRcvAddressCount(RcvAddressParam rcvAddressParam) throws BusinessException{
		return addressManageDAO.queryRcvAddressCount(rcvAddressParam);
	}

	/**
	 * 处理重复地址
	 * @Title: proceRepeatAddress 
	 * @param   
	 * @return void    返回类型 
	 * @throws
	 */
	private void proceRepeatAddress(RcvAddressPO rcvAddressPO){
		RcvAddressParam rcvAddressParam = new RcvAddressParam();
		rcvAddressParam.setCustId(rcvAddressPO.getCustId());
		rcvAddressParam.setProvinceCode(rcvAddressPO.getProvinceCode());
		rcvAddressParam.setRegionCode(rcvAddressPO.getRegionCode());
		rcvAddressParam.setCityCode(rcvAddressPO.getCityCode());
		rcvAddressParam.setAddressInfo(rcvAddressPO.getAddressInfo());
		rcvAddressParam.setRcverName(rcvAddressPO.getRcverName());
		rcvAddressParam.setMobile(rcvAddressPO.getMobile());
		
		List<RcvAddressPO> list = this.queryRcvAddressList(rcvAddressParam);
		if(list.size()>1){
			throw new BusinessException("收货地址重复!");
		}else if(list.size()==1){
			RcvAddressPO po = list.get(0);
			if(!po.getAddressId().equals(rcvAddressPO.getAddressId())){
				throw new BusinessException("收货地址重复!");
			}
		}
	}

}
