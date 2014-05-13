package com.xiu.uuc.facade.dto;


/**
 * 操作业务类型表DTO
 * @ClassName: BusiTypeDTO 
 * @author xiu
 * @date 2012-2-22 上午08:04:32 
 *
 */
public class BusiTypeDTO {

	/**
	 * 业务类型代码
	 */
	private String code ;

	/**
	 * 业务类型描述
	 */
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
