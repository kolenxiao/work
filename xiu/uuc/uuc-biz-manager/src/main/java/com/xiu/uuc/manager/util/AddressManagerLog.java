package com.xiu.uuc.manager.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.uuc.common.util.CommonEnum;
import com.xiu.uuc.common.util.CommonValidationUtil;
import com.xiu.uuc.common.util.JsonUtils;
import com.xiu.uuc.core.service.LogService;
import com.xiu.uuc.dal.po.RcvAddressPO;
import com.xiu.uuc.facade.dto.BusiLogDTO;
import com.xiu.uuc.facade.util.TypeEnum;

public class AddressManagerLog {

	@Autowired
	private LogService logService;
	
	private static JsonUtils jsonUtils = new JsonUtils();
	
	public void logAddRcvAddressInfo(RcvAddressPO rcvAddressPO) throws Exception{
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(rcvAddressPO.getCustId().toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.CUST_ID.getKey());
		busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.ADD_RCV_ADDRESS.getKey());
		busiLogDTO.setOperId(rcvAddressPO.getOperId());

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("地址ID", rcvAddressPO.getAddressId());
		map.put("创建渠道", rcvAddressPO.getCreateChannelId());		
		map.put("省Code", rcvAddressPO.getProvinceCode());
		map.put("市Code", rcvAddressPO.getRegionCode());
		map.put("县Code", rcvAddressPO.getCityCode());
		map.put("街道地址", rcvAddressPO.getAddressInfo());
		map.put("默认", rcvAddressPO.getIsMaster());
		if(StringUtils.isNotBlank(rcvAddressPO.getRcverName())){
			map.put("收货人姓名", rcvAddressPO.getRcverName());
		}
		if(StringUtils.isNotBlank(rcvAddressPO.getMobile())){
			map.put("手机号码", rcvAddressPO.getMobile());
		}
		if (StringUtils.isNotBlank(rcvAddressPO.getAreaCode())
				&& StringUtils.isNotBlank(rcvAddressPO.getPhone())) {
			map.put("电话号码", rcvAddressPO.getAreaCode() + "-" + rcvAddressPO.getPhone()
					    + "-" + rcvAddressPO.getDivCode());
		}
		if(StringUtils.isNotBlank(rcvAddressPO.getPostCode())){
			map.put("邮政编码", rcvAddressPO.getPostCode());
		}
		if(StringUtils.isNotBlank(rcvAddressPO.getBookerName())){
			map.put("订购人", rcvAddressPO.getBookerName());
		}
		if(StringUtils.isNotBlank(rcvAddressPO.getBookerPhone())){
			map.put("订购人电话", rcvAddressPO.getBookerPhone());
		}

		busiLogDTO.setAfterContent(jsonUtils.toJson(map));
		logService.insertBusiLog(busiLogDTO);
	}
	
	public void logModifyRcvAddressInfo(RcvAddressPO oldRcvAddress, RcvAddressPO newRcvAddress) throws Exception{
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(newRcvAddress.getCustId().toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.CUST_ID.getKey());
		busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.MODIFY_RCV_ADDRESS.getKey());
		busiLogDTO.setOperId(newRcvAddress.getOperId());

		Map<String, Object> oldMap = new LinkedHashMap<String, Object>();
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();		
		newMap.put("地址ID", newRcvAddress.getAddressId());
		
		if(null != newRcvAddress.getProvinceCode() && !CommonValidationUtil.equals(oldRcvAddress.getProvinceCode(), newRcvAddress.getProvinceCode())){
			oldMap.put("省Code", oldRcvAddress.getProvinceCode());
			newMap.put("省Code", newRcvAddress.getProvinceCode());
		}
		if(null != newRcvAddress.getRegionCode() && !CommonValidationUtil.equals(oldRcvAddress.getRegionCode(), newRcvAddress.getRegionCode())){
			oldMap.put("市Code", oldRcvAddress.getRegionCode());
			newMap.put("市Code", newRcvAddress.getRegionCode());
		}
		if(null != newRcvAddress.getCityCode() && !CommonValidationUtil.equals(oldRcvAddress.getCityCode(), newRcvAddress.getCityCode())){
			oldMap.put("县Code", oldRcvAddress.getCityCode());
			newMap.put("县Code", newRcvAddress.getCityCode());
		}
		if(null != newRcvAddress.getAddressInfo() && !CommonValidationUtil.equals(oldRcvAddress.getAddressInfo(), newRcvAddress.getAddressInfo())){
			oldMap.put("街道地址", oldRcvAddress.getAddressInfo());
			newMap.put("街道地址", newRcvAddress.getAddressInfo());
		}
		if(null != newRcvAddress.getIsMaster() && !CommonValidationUtil.equals(oldRcvAddress.getIsMaster(), newRcvAddress.getIsMaster())){
			oldMap.put("默认", oldRcvAddress.getIsMaster());
			newMap.put("默认", newRcvAddress.getIsMaster());
		}
		if(null != newRcvAddress.getRcverName() && !CommonValidationUtil.equals(oldRcvAddress.getRcverName(), newRcvAddress.getRcverName())){
			oldMap.put("收货人姓名", oldRcvAddress.getRcverName());
			newMap.put("收货人姓名", newRcvAddress.getRcverName());
		}
		if(null != newRcvAddress.getMobile() && !CommonValidationUtil.equals(oldRcvAddress.getMobile(), newRcvAddress.getMobile())){
			oldMap.put("手机号码", oldRcvAddress.getMobile());
			newMap.put("手机号码", newRcvAddress.getMobile());
		}
		if(null != newRcvAddress.getPhone() && !CommonValidationUtil.equals(oldRcvAddress.getPhone(), newRcvAddress.getPhone())){
			oldMap.put("电话号码", oldRcvAddress.getAreaCode() + "-" + oldRcvAddress.getPhone()
				    + "-" + oldRcvAddress.getDivCode());
			newMap.put("电话号码", newRcvAddress.getAreaCode() + "-" + newRcvAddress.getPhone()
				    + "-" + newRcvAddress.getDivCode());
		}
		if(null != newRcvAddress.getPostCode() && !CommonValidationUtil.equals(oldRcvAddress.getPostCode(), newRcvAddress.getPostCode())){
			oldMap.put("邮政编码", oldRcvAddress.getPostCode());
			newMap.put("邮政编码", newRcvAddress.getPostCode());
		}
		if(null != newRcvAddress.getBookerName() && !CommonValidationUtil.equals(oldRcvAddress.getBookerName(), newRcvAddress.getBookerName())){
			oldMap.put("订购人", oldRcvAddress.getBookerName());
			newMap.put("订购人", newRcvAddress.getBookerName());
		}
		if(null != newRcvAddress.getBookerPhone() && !CommonValidationUtil.equals(oldRcvAddress.getBookerPhone(), newRcvAddress.getBookerPhone())){
			oldMap.put("订购人电话", oldRcvAddress.getBookerPhone());
			newMap.put("订购人电话", newRcvAddress.getBookerPhone());
		}

		if(!oldMap.isEmpty()){
			busiLogDTO.setBeforeContent(jsonUtils.toJson(oldMap));
		}
		if(!newMap.isEmpty()){
			busiLogDTO.setAfterContent(jsonUtils.toJson(newMap));
		}		
		logService.insertBusiLog(busiLogDTO);
	}
	
	public void logDeleteRcvAddressInfo(RcvAddressPO rcvAddressPO) throws Exception{
		BusiLogDTO busiLogDTO = new BusiLogDTO();
		busiLogDTO.setKeyWord(rcvAddressPO.getCustId().toString());
		busiLogDTO.setKeyWordType(TypeEnum.KeyWordType.CUST_ID.getKey());
		busiLogDTO.setBusiTypeCode(CommonEnum.BusiOperType.DELETE_RCV_ADDRESS.getKey());

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("地址ID", rcvAddressPO.getAddressId());
		map.put("创建渠道", rcvAddressPO.getCreateChannelId());		
		map.put("省Code", rcvAddressPO.getProvinceCode());
		map.put("市Code", rcvAddressPO.getRegionCode());
		map.put("县Code", rcvAddressPO.getCityCode());
		map.put("街道地址", rcvAddressPO.getAddressInfo());
		map.put("默认", rcvAddressPO.getIsMaster());

		busiLogDTO.setBeforeContent(jsonUtils.toJson(map));
		logService.insertBusiLog(busiLogDTO);
	}
	
}
