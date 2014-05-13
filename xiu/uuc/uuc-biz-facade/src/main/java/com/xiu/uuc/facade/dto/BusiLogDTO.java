package com.xiu.uuc.facade.dto;

import java.util.Date;

/**
 * 操作业务日志表PO
 * @ClassName: BusiLogPO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author xiu
 * @date 2011-10-29 下午08:04:32 
 *
 */
public class BusiLogDTO {

	/**
	 * 业务日志ID
	 */
	private String logId ;

	/**
	 * 业务类型
	 */
	private String busiTypeCode ;

	/**
	 * 操作前的内容
	 */
	private String beforeContent;

	/**
	 * 操作后的内容
	 */
	private String afterContent;

	/**
	 * 关键值
	 */
	private String keyWord;

	/**
	 * 关键值类型
	 */
	private String keyWordType;

	/**
	 * 日志备注
	 */
	private String remark;

	/**
	 * 操作时间
	 */
	private Date createTime;

	/**
	 * 操作者id
	 */
	private String operId ;

	/**
	 * 操作者ip地址
	 */
	private String operIp;
	
	/**
	 * 防删改字符串
	 */
	private String xstr;

	private String busiTypeDesc ;
	private String keyWordTypeDesc;
	

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getOperIp() {
		return operIp;
	}

	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getKeyWordType() {
		return keyWordType;
	}

	public void setKeyWordType(String keyWordType) {
		this.keyWordType = keyWordType;
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

	public String getBeforeContent() {
		return beforeContent;
	}

	public void setBeforeContent(String beforeContent) {
		this.beforeContent = beforeContent;
	}

	public String getAfterContent() {
		return afterContent;
	}

	public void setAfterContent(String afterContent) {
		this.afterContent = afterContent;
	}

	public String getBusiTypeDesc() {
		return busiTypeDesc;
	}

	public void setBusiTypeDesc(String busiTypeDesc) {
		this.busiTypeDesc = busiTypeDesc;
	}

	public String getKeyWordTypeDesc() {
		return keyWordTypeDesc;
	}

	public void setKeyWordTypeDesc(String keyWordTypeDesc) {
		this.keyWordTypeDesc = keyWordTypeDesc;
	}

	public String getBusiTypeCode() {
		return busiTypeCode;
	}

	public void setBusiTypeCode(String busiTypeCode) {
		this.busiTypeCode = busiTypeCode;
	}

	public String getXstr() {
		return xstr;
	}

	public void setXstr(String xstr) {
		this.xstr = xstr;
	}
	
}
