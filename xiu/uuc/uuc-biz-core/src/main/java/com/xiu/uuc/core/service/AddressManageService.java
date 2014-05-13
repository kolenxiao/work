package com.xiu.uuc.core.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.dal.param.RcvAddressParam;
import com.xiu.uuc.dal.po.RcvAddressPO;

/**
 * 收货地址管理Service
 * @ClassName: AddressManageService 
 * @author xiaoyingping
 * @date 2011-7-19 上午11:04:39 
 *
 */
public interface AddressManageService {

	/**
	 * 新增收货地址
	 * @Title: addRcvAddress 
	 * @param  rcvAddressPO 
	 * @return Long    返回类型 
	 * @throws BusinessException
	 */
	public Long addRcvAddress(RcvAddressPO rcvAddressPO) throws BusinessException;
		
	/**
	 * 修改收货地址
	 * @Title: modifyRcvAddress 
	 * @param   rcvAddressPO
	 * @return void    返回类型 
	 * @throws BusinessException
	 */
	public void modifyRcvAddress(RcvAddressPO rcvAddressPO) throws BusinessException;
	
	/**
	 * 修改收货地址的是否默认属性
	* @Description: 
	* @param custId
	* @param addressId
	* @param isMaster
	 */
	public void modifyRcvAddressMaster(Long custId, Long addressId, String isMaster);
	
	/**
	 * 修改收货地址关联的客户ID
	 * @Title: modifyRcvAddressCust 
	 * @param   rcvAddressParam
	 * @return void    返回类型 
	 * @throws BusinessException
	 */
	public void modifyRcvAddressCust(RcvAddressParam rcvAddressParam) throws BusinessException;
	
	/**
	 * 删除收货地址
	 * @Title: deleteRcvAddress 
	 * @param  rcvAddressId 地址ID 
	 * @return void    返回类型 
	 * @throws BusinessException
	 */
	public void deleteRcvAddress(Long rcvAddressId) throws BusinessException;
	
	/**
	 * 查询收货地址信息对象
	 * @Title: queryRcvAddress 
	 * @param  rcvAddressParam 
	 * @return rcvAddressPO    返回类型 
	 * @throws BusinessException
	 */
	public RcvAddressPO queryRcvAddress(RcvAddressParam rcvAddressParam) throws BusinessException;
	
	/**
	 * 通过地址ID得到收货地址信息
	 * @Title: getRcvAddressInfoById 
	 * @param  addressId 收货地址ID 
	 * @return rcvAddressPO    返回类型 
	 * @throws BusinessException
	 */
	public RcvAddressPO getRcvAddressInfoById(Long addressId) throws BusinessException;
	
	/**
	 * 查询收货地址信息列表
	 * @Title: queryRcvAddressList 
	 * @param  rcvAddressParam 
	 * @return List<RcvAddressPO>    返回类型 
	 * @throws BusinessException
	 */
	public List<RcvAddressPO> queryRcvAddressList(RcvAddressParam rcvAddressParam) throws BusinessException;
	
	/**
	 * 查询收货地址信息列表
	 * @Title: queryRcvAddressCount 
	 * @param  rcvAddressParam 
	 * @return Long    返回类型 
	 * @throws DataAccessException
	 */
	public Long queryRcvAddressCount(RcvAddressParam rcvAddressParam) throws BusinessException;

}
