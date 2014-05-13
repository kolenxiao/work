package com.xiu.uuc.facade.dto;

import java.io.Serializable;
import java.util.Date;

public class StationLettersDTO implements Serializable {
	
	/**
	 * serialVersionUID序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性
	 */
	private static final long serialVersionUID = 3337231675722148251L;

	/**
	 * 信件ID
	 */
	private String lettersId;
	
	/**
	 * 收件人
	 */
	private String addressee;
	
	/**
	 * 收件人所在渠道
	 */
	private String receivChannel;
	
	/**
	 * 发件人id
	 */
	private String sender;
	
	/**
	 * 发件人所在渠道	
	 */
	private String sendChannel;
	
	/**
	 * 信件标题
	 */
	private String title;
	
	/**
	 * 信件内容
	 */
	private String content;
	
	/**
	 * 是否阅读
	 */
	private Integer read;
	
	/**
	 * 信件发送时间
	 */
	private Date sendTime;
	
	/**
	 * 信件阅读时间
	 */
	private Date receivTime;
	
	/**
	 * 操作
	 */
	private String operation;
	
	/**
	 * 接收信件的用户ID，以前是保存客户id的后来改成用户id
	 */
	private String customerId;
	
	/**
	 * 发件人名字
	 */
	private String customerName;
	
	/**
	 * 客户电话
	 */
	private String telphone;
	
	/**
	 * 客户邮箱
	 */
	private String email;

	public String getLettersId() {
		return lettersId;
	}
	
	public void setLettersId(String lettersId) {
		this.lettersId = lettersId;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getAddressee() {
		return addressee;
	}
	
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	
	public String getReceivChannel() {
		return receivChannel;
	}
	
	public void setReceivChannel(String receivChannel) {
		this.receivChannel = receivChannel;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getSendChannel() {
		return sendChannel;
	}
	
	public void setSendChannel(String sendChannel) {
		this.sendChannel = sendChannel;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getRead() {
		return read;
	}
	
	public void setRead(Integer read) {
		this.read = read;
	}
	public Date getSendTime() {
		return sendTime;
	}
	
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	public Date getReceivTime() {
		return receivTime;
	}
	
	public void setReceivTime(Date receivTime) {
		this.receivTime = receivTime;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
