package com.xiu.uuc.facade.dto;

import java.util.Date;

/**
 * 业务操作日志查询对象
 * @ClassName: RmBaseQueryDTO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author kolenxiao
 * @date 2011-12-29 下午04:48:40 
 *
 */
public class BusiLogQueryDTO extends BaseQueryDTO {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */
	
	private static final long serialVersionUID = -8350124782821731728L;

	/**
	 * 业务日志ID
	 */
	private String logId ;

	/**
	 * 业务操作代码
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
	 * 开始操作时间
	 */
	private Date createTimeMin;

	/**
	 * 结束操作时间
	 */
	private Date createTimeMax;

	/**
	 * 操作者id
	 */
	private String operId ;

	/**
	 * 操作者ip地址
	 */
	private String operIp;
	
	

	/**
	 * 业务类型描述
	 */
	private String busiTypeDesc ;
	

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

	public String getBusiTypeDesc() {
		return busiTypeDesc;
	}

	public void setBusiTypeDesc(String busiTypeDesc) {
		this.busiTypeDesc = busiTypeDesc;
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

	public Date getCreateTimeMin() {
		return createTimeMin;
	}

	public void setCreateTimeMin(Date createTimeMin) {
		this.createTimeMin = createTimeMin;
	}

	public Date getCreateTimeMax() {
		return createTimeMax;
	}

	public void setCreateTimeMax(Date createTimeMax) {
		this.createTimeMax = createTimeMax;
	}

	public String getBusiTypeCode() {
		return busiTypeCode;
	}

	public void setBusiTypeCode(String busiTypeCode) {
		this.busiTypeCode = busiTypeCode;
	}
	
}
