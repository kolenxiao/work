package com.xiu.uuc.dal.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xiu.uuc.dal.param.RcvAddressParam;
import com.xiu.uuc.dal.po.RcvAddressPO;

/**
 * 收货地址管理DAO
 * @ClassName: AddressManageDAO
 * @author xiaoyingping
 * @date 2011-7-19 上午11:00:58
 * 
 */
public interface AddressManageDAO {


	/**
	 * 新增收货地址
	 * @Title: addRcvAddress 
	 * @param  rcvAddressPO 
	 * @return Long    返回类型 
	 * @throws DataAccessException
	 */
	public Long addRcvAddress(RcvAddressPO rcvAddressPO) throws DataAccessException;
		
	/**
	 * 修改收货地址
	 * @Title: modifyRcvAddress 
	 * @param   rcvAddressPO
	 * @return void    返回类型 
	 * @throws DataAccessException
	 */
	public void modifyRcvAddress(RcvAddressPO rcvAddressPO) throws DataAccessException;
	
	/**
	 * 修改收货地址关联的客户ID
	 * @Title: modifyRcvAddressCust 
	 * @param   rcvAddressParam
	 * @return void    返回类型 
	 * @throws DataAccessException
	 */
	public void modifyRcvAddressCust(RcvAddressParam rcvAddressParam) throws DataAccessException;
	
	/**
	 * 删除收货地址
	 * @Title: deleteRcvAddress 
	 * @param  rcvAddressId 地址ID 
	 * @return void    返回类型 
	 * @throws DataAccessException
	 */
	public void deleteRcvAddress(Long rcvAddressId) throws DataAccessException;
	
	/**
	 * 查询收货地址信息
	 * @Title: queryRcvAddress 
	 * @param  rcvAddressParam 
	 * @return rcvAddressPO    返回类型 
	 * @throws DataAccessException
	 */
	public RcvAddressPO queryRcvAddress(RcvAddressParam rcvAddressParam) throws DataAccessException;
	
	/**
	 * 查询收货地址信息列表
	 * @Title: queryRcvAddressList 
	 * @param  rcvAddressParam 
	 * @return List<RcvAddressPO>    返回类型 
	 * @throws DataAccessException
	 */
	public List<RcvAddressPO> queryRcvAddressList(RcvAddressParam rcvAddressParam) throws DataAccessException;
	
	/**
	 * 查询收货地址信息列表
	 * @Title: queryRcvAddressCount 
	 * @param  rcvAddressParam 
	 * @return Long    返回类型 
	 * @throws DataAccessException
	 */
	public Long queryRcvAddressCount(RcvAddressParam rcvAddressParam) throws DataAccessException;


}
