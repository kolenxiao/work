package com.coship.sdp.sce.dp.device.service;

import com.coship.sdp.sce.dp.device.entity.DeviceInfo;
import com.coship.sdp.utils.Page;

public interface DeviceInfoService {

	/**
	 * @param deviceInfo
	 */
	public void saveOrUpdate(DeviceInfo deviceInfo);
	
	public DeviceInfo getDeviceInfoByMac(String mac);

	/**
	 * @param serialNo
	 * @return
	 */
	public DeviceInfo getDeviceInfoBySerialNo(String serialNo);

	public Page<DeviceInfo> listDeviceInfoList(Page<DeviceInfo> page, String hql,
			Object[] values);

	public Page<DeviceInfo> searchDeviceInfo(Page<DeviceInfo> page,
			DeviceInfo queryDeviceInfo);

}
