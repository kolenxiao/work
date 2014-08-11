package com.coship.appstore.service.dto;

import java.io.Serializable;

/**
 * 精选页板块中的元素DTO对象.
 * 
 * @author 907948
 * 
 */
public class AppRecommendPanelItemDTO implements Serializable {
	private static final long serialVersionUID = -7979200825360543714L;

	/**
	 * 精选页板块元素的类型，包含：1=应用；2=专题；3=文本
	 */
	private Integer type;

	/**
	 * 元素类型标识值，比如应用包名、专题的id、文本类型名称。
	 */
	private String typeValue;

	/**
	 * 元素名称，用于最后的版块的小元素.目前包含：
	 */
	private String itemName;

	/**
	 * 元素海报地址，如：专题海报地址。
	 */
	private String itemPoster;

	private Object itemInfo;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeId) {
		this.typeValue = typeId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPoster() {
		return itemPoster;
	}

	public void setItemPoster(String itemPoster) {
		this.itemPoster = itemPoster;
	}

	public Object getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(Object itemInfo) {
		this.itemInfo = itemInfo;
	}
}
