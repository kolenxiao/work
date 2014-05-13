package com.xiu.uuc.dal.po;

import java.util.Date;

/**
 * 操作业务日志表PO
 * @ClassName: BusiLogPO 
 * @author xiaoyingping
 * @date 2011-8-4 下午07:57:09 
 *
 */
public class BusiLogPO{

	/**
	 * 业务日志ID
	 */
	private Long logId ;

	/**
	 * 操作者id
	 */
	private String operatorId ;

	/**
	 * 操作者名称
	 */
	private String operatorName;

	/**
	 * 操作者ip地址
	 */
	private String operatorIp;

	/**
	 * 对象名称
	 */
	private String operationName ;

	/**
	 * 内容描述
	 */
	private String content;

	/**
	 * 日志类型
	 */
	private String type;

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

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorIp() {
		return operatorIp;
	}

	public void setOperatorIp(String operatorIp) {
		this.operatorIp = operatorIp;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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



}
