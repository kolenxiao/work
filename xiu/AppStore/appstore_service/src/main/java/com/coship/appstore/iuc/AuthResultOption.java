package com.coship.appstore.iuc;

import java.io.Serializable;

/**
 * IUC鉴权结果.
 * 
 * @author 908618
 *
 */
public class AuthResultOption implements Serializable {
	private static final long serialVersionUID = -505341768769532228L;
	
	/**
	 * 资源编码,可能为产品编码或者频道编码（id）之类的值.
	 */
	private String code;

	/**
	 * 类型, 1=产品;2=频道;3=...
	 */
	private int type;
	
	private Boolean success;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}