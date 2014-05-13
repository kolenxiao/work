package com.xiu.uuc.dal.po;

import java.util.Date;

/**
 * 接口日志表PO
 * @ClassName: InterfaceLogPO 
 * @author xiaoyingping
 * @date 2011-8-16 下午04:14:56 
 */
public class InterfaceLogPO {

	/**
	 * 业务日志ID
	 */
	private Long logId ;

	/**
	 * 操作者id
	 */
	private String operatorId ;

	/**
	 * 操作者ip地址
	 */
	private String operatorIp;

	/**
	 * 接口方法
	 */
	private String method ;

	/**
	 * 入参
	 */
	private String parameters;

	/**
	 * 返回结果
	 */
	private String results;

	/**
	 * 主要参数
	 */
	private String mainParam;

	/**
	 * 日志备注
	 */
	private String remark;

	/**
	 * 操作时间
	 */
	private Date createTime;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorIp() {
		return operatorIp;
	}

	public void setOperatorIp(String operatorIp) {
		this.operatorIp = operatorIp;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMainParam() {
		return mainParam;
	}

	public void setMainParam(String mainParam) {
		this.mainParam = mainParam;
	}


}
