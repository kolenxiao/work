package com.coship.sdp.sce.dp.action.device;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.coship.sdp.sce.dp.ap.service.DpAppInfoService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.device.entity.DeviceInfo;
import com.coship.sdp.sce.dp.device.service.DeviceInfoService;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.utils.Page;

/**
 * @author 906974
 *
 */
public class DeviceInfoAction extends BaseAction {

    /**
	 *
	 */
	private static final long serialVersionUID = 6570378224189744582L;

	private DeviceInfo queryDeviceInfo;

	/**
	 * 应用包名
	 */
	private String packageName;

	/**
	 * 应用名称
	 */
	private String appName;

	/**
	 * 分类名称
	 */
	private String typeName;

	/**
	 * 开发者
	 */
	private String staffName;

	/**
	 * 标志是否需要转码的标记：isNeedEnCode：0表需转码
	 */
	private String isNeedEnCode;

	/**
     * 分页.
     */
    private Page<DeviceInfo> page;

    @Autowired
    private DeviceInfoService deviceInfoService;

    /**
     * 应用类型接口.
     */
    @Autowired
    private DpTypeService dpTypeService;

    /**
     * 应用服务接口
     */
    @Autowired
    private DpAppInfoService dpAppInfoService;

	/**
	 * 获取客户端分页数据
	 *
	 * @return 返回列表页面
	 */
	public String doList() {
		page = new Page<DeviceInfo>();
		page.setPageSize(limit);
		page.setCurrentPage(start);
		try {
			String hql = "from DeviceInfo di order by di.appStoreClientInstallDate desc";
			page = deviceInfoService.listDeviceInfoList(page, hql, null);

			for (DeviceInfo devInfo : page.getResultList())
			{
				devInfo.setSystemDpType(dpTypeService
						.findByTypeCode(String.valueOf(devInfo.getOsVersion())));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "doList";
	}

	public String viewDeviceDetail()
	{
		page = new Page<DeviceInfo>();
		page.setPageSize(limit);
		page.setCurrentPage(start);

		try
		{
			Map<String, String> map = dpAppInfoService.getStaffAndTypeByPacName(packageName);
			appName = map.get("appName");
			typeName = map.get("typeName");

			String hql = "select dv from DeviceInfo dv,MyApp mApp where dv.deviceSerialNo=mApp.uid "
				+ " and mApp.appPackageName=?";
			page = deviceInfoService.listDeviceInfoList(page, hql, new Object[]{packageName});

			for (DeviceInfo devInfo : page.getResultList())
			{
				devInfo.setSystemDpType(dpTypeService
						.findByTypeCode(String.valueOf(devInfo.getOsVersion())));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}


		return "showDeviceList";
	}

	/**
	 * @return 返回列表页面
	 */
	public String doSearchList() {
		page = new Page<DeviceInfo>();
		page.setPageSize(limit);
		page.setCurrentPage(start);
		page = deviceInfoService.searchDeviceInfo(page, queryDeviceInfo);
		return "doList";
	}

	public DeviceInfo getQueryDeviceInfo() {
		return queryDeviceInfo;
	}

	public void setQueryDeviceInfo(DeviceInfo queryDeviceInfo) {
		this.queryDeviceInfo = queryDeviceInfo;
	}

	public Page<DeviceInfo> getPage() {
		return page;
	}

	public void setPage(Page<DeviceInfo> page) {
		this.page = page;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getIsNeedEnCode() {
		return isNeedEnCode;
	}

	public void setIsNeedEnCode(String isNeedEnCode) {
		this.isNeedEnCode = isNeedEnCode;
	}



}
