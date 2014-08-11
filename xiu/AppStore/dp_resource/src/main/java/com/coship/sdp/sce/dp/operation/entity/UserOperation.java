package com.coship.sdp.sce.dp.operation.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

/**
 * <客户端用户操作记录>
 * 
 * @author Huangliufei/907632
 * @version [版本号, Aug 28, 2013]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserOperation extends EntityObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5721938942043135798L;
	
	//操作类型
	private int optType;
	
	//操作时间
	private Date optTime;
	
	//操作内容
	private String optContent;
	
	//操作内容ID
	private String optContentId;
	
	//设备mac地址
	private String deviceMac;
	
	//渠道id
	private String channelId;
	
	//区域码
    private String cityCode;
	
	//扩展参数
	private String param1;
	
	//扩展参数
	private String param2;
	
	//扩展参数
	private String param3;
	
	//用户编码
	private String userCode;

	public int getOptType()
	{
		return optType;
	}

	public void setOptType(int optType)
	{
		this.optType = optType;
	}

	public Date getOptTime()
	{
		return optTime;
	}

	public void setOptTime(Date optTime)
	{
		this.optTime = optTime;
	}

	public String getOptContent()
	{
		return optContent;
	}

	public void setOptContent(String optContent)
	{
		this.optContent = optContent;
	}

	public String getOptContentId()
	{
		return optContentId;
	}

	public void setOptContentId(String optContentId)
	{
		this.optContentId = optContentId;
	}

	public String getDeviceMac()
	{
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac)
	{
		this.deviceMac = deviceMac;
	}

	public String getParam1()
	{
		return param1;
	}

	public void setParam1(String param1)
	{
		this.param1 = param1;
	}

	public String getParam2()
	{
		return param2;
	}

	public void setParam2(String param2)
	{
		this.param2 = param2;
	}

	public String getParam3()
	{
		return param3;
	}

	public void setParam3(String param3)
	{
		this.param3 = param3;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	

}
