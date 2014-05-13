package com.xiu.uuc.manager.validate;

import org.apache.commons.lang.StringUtils;

import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.CommonValidationUtil;
import com.xiu.uuc.facade.dto.RcvAddressDTO;
import com.xiu.uuc.facade.util.TypeEnum;

/**
 * AddressManageImpl的验证类
 * @ClassName: AddressManageValidate 
 * @author xiaoyingping
 * @date 2011-8-18 下午12:01:53 
 *
 */
public class AddressManageValidate {
	
	public static void validateAddRcvAddressInfo(RcvAddressDTO rcvAddressDTO){
		if(null == rcvAddressDTO){
			throw new ParameterException("参数不能为空!");
		}
		if(CommonValidationUtil.isEmpty(rcvAddressDTO.getUserId())){
			throw new ParameterException("用户ID不能为空!");
		}
		if(!TypeEnum.ChannelType.getList().containsKey(rcvAddressDTO.getCreateChannelId())){
			throw new ParameterException("渠道标识不正确!");
		}
		if(StringUtils.isBlank(rcvAddressDTO.getProvinceCode())){
			throw new ParameterException("所在省Code不能为空!");
		}else if(!CommonValidationUtil.checkExactingLength(rcvAddressDTO.getProvinceCode(), 1, 100)){
			throw new ParameterException("所在省Code长度不能大于100!");
		}
		if(StringUtils.isBlank(rcvAddressDTO.getRegionCode())){
			throw new ParameterException("所在市Code不能为空!");
		}else if(!CommonValidationUtil.checkExactingLength(rcvAddressDTO.getRegionCode(), 1, 100)){
			throw new ParameterException("所在市Code长度不能大于100!");
		}
		if(StringUtils.isBlank(rcvAddressDTO.getCityCode())){
			throw new ParameterException("所在县Code不能为空!");
		}else if(!CommonValidationUtil.checkExactingLength(rcvAddressDTO.getCityCode(), 1, 100)){
			throw new ParameterException("所在县Code长度不能大于100!");
		}
		if(StringUtils.isBlank(rcvAddressDTO.getAddressInfo())){
			throw new ParameterException("街道地址不能为空!");
		}else if(!CommonValidationUtil.checkExactingLength(rcvAddressDTO.getAddressInfo(), 1, 1024)){
			throw new ParameterException("街道地址长度不能大于1024!");
		}
		if(StringUtils.isBlank(rcvAddressDTO.getRcverName())){
			throw new ParameterException("收货人姓名不能为空!");
		}else if(!CommonValidationUtil.checkExactingLength(rcvAddressDTO.getRcverName(), 1, 100)){
			throw new ParameterException("收货人姓名长度不能大于100!");
		}	
	}
	
	public static void validateModifyRcvAddressInfo(RcvAddressDTO rcvAddressDTO){
		if(null == rcvAddressDTO){
			throw new ParameterException("参数不能为空!");
		}
		if(CommonValidationUtil.isEmpty(rcvAddressDTO.getAddressId())){
			throw new ParameterException("收货地址ID不能为空!");
		}
		if(StringUtils.isBlank(rcvAddressDTO.getProvinceCode())){
			throw new ParameterException("所在省Code不能为空!");
		}else if(!CommonValidationUtil.checkExactingLength(rcvAddressDTO.getProvinceCode(), 1, 100)){
			throw new ParameterException("所在省Code长度不能大于100!");
		}
		if(StringUtils.isBlank(rcvAddressDTO.getRegionCode())){
			throw new ParameterException("所在市Code不能为空!");
		}else if(!CommonValidationUtil.checkExactingLength(rcvAddressDTO.getRegionCode(), 1, 100)){
			throw new ParameterException("所在市Code长度不能大于100!");
		}
		if(StringUtils.isBlank(rcvAddressDTO.getCityCode())){
			throw new ParameterException("所在县Code不能为空!");
		}else if(!CommonValidationUtil.checkExactingLength(rcvAddressDTO.getCityCode(), 1, 100)){
			throw new ParameterException("所在县Code长度不能大于100!");
		}
		if(StringUtils.isBlank(rcvAddressDTO.getAddressInfo())){
			throw new ParameterException("街道地址不能为空!");
		}else if(!CommonValidationUtil.checkExactingLength(rcvAddressDTO.getAddressInfo(), 1, 1024)){
			throw new ParameterException("街道地址长度不能大于1024!");
		}
		if(StringUtils.isBlank(rcvAddressDTO.getRcverName())){
			throw new ParameterException("收货人姓名不能为空!");
		}else if(!CommonValidationUtil.checkExactingLength(rcvAddressDTO.getRcverName(), 1, 100)){
			throw new ParameterException("收货人姓名长度不能大于100!");
		}		
	}
	
	public static void validateModifyRcvAddressMaster(Long addressId){
		if(CommonValidationUtil.isEmpty(addressId)){
			throw new ParameterException("收货地址ID不能为空!");
		}
	}
	
	public static void validateDeleteRcvAddressInfo(Long addressId){
		if(CommonValidationUtil.isEmpty(addressId)){
			throw new ParameterException("收货地址ID不能为空!");
		}
	}
	
	public static void validateGetRcvAddressInfoById(Long addressId){
		if(CommonValidationUtil.isEmpty(addressId)){
			throw new ParameterException("收货地址ID不能为空!");
		}
	}
	
	public static void validateGetRcvAddressListByUserId(Long userId){
		if(CommonValidationUtil.isEmpty(userId)){
			throw new ParameterException("用户ID不能为空!");
		}
	}
	
	public static void validateGetRcvAddressCountByUserId(Long userId){
		if(CommonValidationUtil.isEmpty(userId)){
			throw new ParameterException("用户ID不能为空!");
		}
	}
	

}
