package com.coship.sdp.sce.dp.device.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.type.entity.DpType;

/**
 * @author 906974 终端设备信息
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DeviceInfo extends EntityObject {

	/**
	 *
	 */
	private static final long serialVersionUID = -7883579863231868831L;

	/**
	 * 终端系统版本
	 */
	private int osVersion;

	/**
	 * 终端设备型号
	 */
	private String deviceType;

	/**
	 * 终端设备序列号，对应我的应用表的“机顶盒的id”
	 */
	private String deviceSerialNo;

	/**
	 * 终端设备mac地址
	 */
	private String deviceMac;

	/**
	 * 终端设备ip
	 */
	private String deviceIp;

	/**
	 * 终端设备上安装的客户端的时间
	 */
	private Date appStoreClientInstallDate;

    /**
     * 系统要求类别
     */
    private DpType systemDpType;

	/**
	 * 终端设备上安装的客户端的版本
	 */
	private String appStoreClientVersion;
	
	/**
	 * 渠道Id
	 */
	private String channelId;
	
	/**
	 * 城市编码
	 */
	private String cityCode;
	

	public int getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(int osVersion) {
		this.osVersion = osVersion;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceSerialNo() {
		return deviceSerialNo;
	}

	public void setDeviceSerialNo(String deviceSerialNo) {
		this.deviceSerialNo = deviceSerialNo;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public Date getAppStoreClientInstallDate() {
		return appStoreClientInstallDate;
	}

	public void setAppStoreClientInstallDate(Date appStoreClientInstallDate) {
		this.appStoreClientInstallDate = appStoreClientInstallDate;
	}

	public String getAppStoreClientVersion() {
		return appStoreClientVersion;
	}

	public void setAppStoreClientVersion(String appStoreClientVersion) {
		this.appStoreClientVersion = appStoreClientVersion;
	}

	@Transient
	public DpType getSystemDpType() {
		return systemDpType;
	}

	public void setSystemDpType(DpType systemDpType) {
		this.systemDpType = systemDpType;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}


}
