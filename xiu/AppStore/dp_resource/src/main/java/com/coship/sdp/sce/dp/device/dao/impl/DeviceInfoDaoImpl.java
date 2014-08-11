package com.coship.sdp.sce.dp.device.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.device.dao.DeviceInfoDao;
import com.coship.sdp.sce.dp.device.entity.DeviceInfo;
@Repository("deviceInfoDao")
public class DeviceInfoDaoImpl  extends GenericDaoImpl<DeviceInfo, String> implements DeviceInfoDao {

	/**
	 *
	 */
	private static final long serialVersionUID = -3808342309752545773L;

}
