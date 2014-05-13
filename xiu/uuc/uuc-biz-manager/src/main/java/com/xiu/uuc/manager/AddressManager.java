package com.xiu.uuc.manager;

import com.xiu.uuc.facade.dto.RcvAddressDTO;
import com.xiu.uuc.facade.dto.Result;

/**
 * 收货地址管理Manager
 * @ClassName: AddressManager 
 * @author xiaoyingping
 * @date 2011-7-19 上午11:26:01 
 *
 */
public interface AddressManager {
		
	/**
	 * 增加一条收货信息
	 * @Title: addRcvAddressInfo 
	 * @param  rcvAddressDTO 收货地址信息DTO 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result addRcvAddressInfo(RcvAddressDTO rcvAddressDTO) throws Exception;
	
	/**
	 * 修改收货地址信息
	 * @Title: modifyRcvAddressInfo 
	 * @param  rcvAddressDTO 收货地址信息DTO（addressId不能为空） 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result modifyRcvAddressInfo(RcvAddressDTO rcvAddressDTO) throws Exception;
	
	/**
	 * 将收货地址设为默认地址
	 * @Title: modifyRcvAddressMaster 
	 * @param  addressId 地址信息ID
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result modifyRcvAddressMaster(Long addressId) throws Exception;
	
	/**
	 * 删除一条收货地址信息
	 * @Title: deleteRcvAddressInfo 
	 * @param  addressId 地址信息ID 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result deleteRcvAddressInfo(Long addressId) throws Exception;
	
	/**
	 * 通过地址ID得到收货地址信息
	 * @Title: getRcvAddressInfoById 
	 * @param  addressId 地址信息ID 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getRcvAddressInfoById(Long addressId) throws Exception;

	/**
	 * 通过用户ID查询收货地址信息列表
	 * @Title: getRcvAddressListByUserId 
	 * @param  userId 用户ID 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getRcvAddressListByUserId(Long userId) throws Exception;
	
	/**
	 * 通过用户ID查询收货地址信息总条数
	 * @Title: getRcvAddressCountByUserId 
	 * @param  userId 用户ID 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getRcvAddressCountByUserId(Long userId);


}
