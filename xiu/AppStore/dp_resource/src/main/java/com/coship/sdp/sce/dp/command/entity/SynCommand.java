package com.coship.sdp.sce.dp.command.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

/**
 * <客户端用户操作记录>
 * 
 * @author Huangliufei/907632
 * @version [版本号, September 5, 2013]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SynCommand extends EntityObject
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3745490433467491755L;

	// 应用APP主键
	private String appId;

	// 初始入库时间
	private Date initTime;

	// 上次下发时间
	private Date lastTime;

	// 失败次数
	private int failCount;

	// 同步状态:1 待同步 2 已同步 3 待删除 4 已删除
	private int status;

	// 扩展参数
	private String param;

	public String getAppId()
	{
		return appId;
	}

	public void setAppId(String appId)
	{
		this.appId = appId;
	}

	public Date getInitTime()
	{
		return initTime;
	}

	public void setInitTime(Date initTime)
	{
		this.initTime = initTime;
	}

	public Date getLastTime()
	{
		return lastTime;
	}

	public void setLastTime(Date lastTime)
	{
		this.lastTime = lastTime;
	}

	public int getFailCount()
	{
		return failCount;
	}

	public void setFailCount(int failCount)
	{
		this.failCount = failCount;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getParam()
	{
		return param;
	}

	public void setParam(String param)
	{
		this.param = param;
	}

}
