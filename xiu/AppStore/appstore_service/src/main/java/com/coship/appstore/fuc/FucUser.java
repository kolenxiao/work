package com.coship.appstore.fuc;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于FUC
 * @author 908618
 *
 */
public class FucUser implements Serializable {
	private static final long serialVersionUID = 4088614661069432128L;

	private String id;

	private String name;

	private String password;

	private String modifyPassword;

	private String salt;

	private boolean nameCanSet;

	private String phone;

	private Date registerDate;

	private String terminalType;

	private String terminalId;

	private String bindTerminalId;

	private String registerType;

	private String appType;

	private String email;

	private String modifyEmail;

	private String nickname;

	private String sign;

	private String sex;

	private Date birthday;

	private String headPicUrl;

	private String userType;

	private boolean active;

	private Date modifyTime;

	private String terminalPassword;

	private String ticket;

	private long ticketTime;

	private boolean forbid;

	private String terminalSystem;

	private String terminalMode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getModifyPassword() {
		return modifyPassword;
	}

	public void setModifyPassword(String modifyPassword) {
		this.modifyPassword = modifyPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isNameCanSet() {
		return nameCanSet;
	}

	public void setNameCanSet(boolean nameCanSet) {
		this.nameCanSet = nameCanSet;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getBindTerminalId() {
		return bindTerminalId;
	}

	public void setBindTerminalId(String bindTerminalId) {
		this.bindTerminalId = bindTerminalId;
	}

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getModifyEmail() {
		return modifyEmail;
	}

	public void setModifyEmail(String modifyEmail) {
		this.modifyEmail = modifyEmail;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHeadPicUrl() {
		return headPicUrl;
	}

	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getTerminalPassword() {
		return terminalPassword;
	}

	public void setTerminalPassword(String terminalPassword) {
		this.terminalPassword = terminalPassword;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public long getTicketTime() {
		return ticketTime;
	}

	public void setTicketTime(long ticketTime) {
		this.ticketTime = ticketTime;
	}

	public boolean isForbid() {
		return forbid;
	}

	public void setForbid(boolean forbid) {
		this.forbid = forbid;
	}

	public String getTerminalSystem() {
		return terminalSystem;
	}

	public void setTerminalSystem(String terminalSystem) {
		this.terminalSystem = terminalSystem;
	}

	public String getTerminalMode() {
		return terminalMode;
	}

	public void setTerminalMode(String terminalMode) {
		this.terminalMode = terminalMode;
	}

}