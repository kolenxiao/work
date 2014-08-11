package com.coship.sdp.sce.dp.device.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.device.dao.DeviceInfoDao;
import com.coship.sdp.sce.dp.device.entity.DeviceInfo;
import com.coship.sdp.sce.dp.device.service.DeviceInfoService;
import com.coship.sdp.utils.Page;

/**
 * @author 906974
 *
 */
@Service("deviceInfoService")
@Transactional
public class DeviceInfoServiceImpl implements DeviceInfoService {

	@Autowired
	private DeviceInfoDao deviceInfoDao;

	@Override
	public void saveOrUpdate(DeviceInfo deviceInfo) {
		deviceInfoDao.saveOrUpdate(deviceInfo);
	}

	public DeviceInfo getDeviceInfoBySerialNo(String serialNo) {
		DeviceInfo deviceInfoTemp = null;

		String hql = "from DeviceInfo where deviceSerialNo = ?";
		List<DeviceInfo> list = deviceInfoDao.query(hql,
				new Object[] { serialNo });
		if (list != null && list.size() > 0) {
			deviceInfoTemp = list.get(0);
		}
		return deviceInfoTemp;
	}

	public DeviceInfo getDeviceInfoByMac(String mac) {
		DeviceInfo deviceInfoTemp = null;
		String hql = "from DeviceInfo where deviceMac = ?";
		List<DeviceInfo> list = deviceInfoDao.query(hql,
				new Object[] { mac });
		if (list != null && list.size() > 0) {
			deviceInfoTemp = list.get(0);
		}
		return deviceInfoTemp;
	}
	@Override
	public Page<DeviceInfo> listDeviceInfoList(Page<DeviceInfo> page,
			String hql, Object[] values) {
		page = deviceInfoDao.queryPage(page, hql, values);
		return page;
	}

	@Override
	public Page<DeviceInfo> searchDeviceInfo(Page<DeviceInfo> page,
			DeviceInfo queryDeviceInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from DeviceInfo da where 1 = 1 ";
		page = deviceInfoDao.queryPage(page, hql, map);
		return page;
	}

}
