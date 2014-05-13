package com.xiu.uuc.dal.po;

import java.util.Date;



/**
 * 用户注册IP记录表PO
 * @ClassName: RegisterIpPO 
 * @author xiaoyingping
 * @date 2011-8-19 上午10:24:34 
 *
 */
public class RegisterIpPO{

	/**
	 * ID
	 */
	private Long ipId ;

	/**
	 * 客户ID
	 */
	private Long custId ;

	/**
	 * 注册IP地址
	 */
	private String registerIp;	
	
	/**
     * 创建时间
     */
	private Date createTime ;
	
	/**
	 * 限制注册IP数量的间隔时间(距离系统当前时间,单位:秒)
	 */
	private int interval;

	public Long getIpId() {
		return ipId;
	}

	public void setIpId(Long ipId) {
		this.ipId = ipId;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

}
