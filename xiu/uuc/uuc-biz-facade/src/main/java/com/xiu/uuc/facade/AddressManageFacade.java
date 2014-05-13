package com.xiu.uuc.facade;

import com.xiu.commons.core.IServiceStatusHealthyChecking;
import com.xiu.uuc.facade.dto.RcvAddressDTO;
import com.xiu.uuc.facade.dto.Result;

/**
 * 收货地址管理Facade
 * @ClassName: AddressManageFacade 
 * @Description: 用户中心系统对外接口类
 * @author xiaoyingping
 * @date 2011-7-21 下午04:30:48 
 *
 */
public interface AddressManageFacade extends IServiceStatusHealthyChecking{
	
	
	/**
	 * 增加一条收货地址信息
	 * @Title: addRcvAddressInfo 
	 * @param  rcvAddressDTO 收货地址信息DTO 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result addRcvAddressInfo(RcvAddressDTO rcvAddressDTO);
	
	/**
	 * 修改收货地址信息
	 * @Title: modifyRcvAddressInfo 
	 * @param  rcvAddressDTO 收货地址信息DTO 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result modifyRcvAddressInfo(RcvAddressDTO rcvAddressDTO);
	
	/**
	 * 设置收货地址为默认地址
	 * @Title: modifyRcvAddressMaster 
	 * @param  addressId 地址信息ID
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result modifyRcvAddressMaster(Long addressId);
	
	/**
	 * 删除一条收货地址信息
	 * @Title: deleteRcvAddressInfo 
	 * @param  addressId 地址信息ID 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result deleteRcvAddressInfo(Long addressId);

	/**
	 * 通过收货地址ID查询收货地址信息对象
	 * @Title: getRcvAddressInfoById 
	 * @param  addressId 地址信息ID 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getRcvAddressInfoById(Long addressId);
	
	/**
	 * 通过用户ID查询收货地址信息列表
	 * @Title: getRcvAddressListByUserId 
	 * @param  userId 用户ID 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getRcvAddressListByUserId(Long userId);
	
	/**
	 * 通过用户ID查询收货地址信息总条数
	 * @Title: getRcvAddressCountByUserId 
	 * @param  userId 用户ID 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result getRcvAddressCountByUserId(Long userId);



}
