package com.coship.appstore.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取精选页列表接口的DTO对象.
 * 
 * @author 907948
 *
 */
public class AppRecommendPanelDTO implements Serializable {
	private static final long serialVersionUID = -8199581295083411766L;

	/**
	 * 精选页板块名称
	 */
	private String panelName;

	/**
	 * 布局类型.
	 */
	private Integer layoutType;

	private List<AppRecommendPanelItemDTO> itemList = new ArrayList<AppRecommendPanelItemDTO>();

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public Integer getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(Integer layoutType) {
		this.layoutType = layoutType;
	}

	public List<AppRecommendPanelItemDTO> getItemList() {
		return itemList;
	}

	public void setItemList(List<AppRecommendPanelItemDTO> itemList) {
		this.itemList = itemList;
	}
}
