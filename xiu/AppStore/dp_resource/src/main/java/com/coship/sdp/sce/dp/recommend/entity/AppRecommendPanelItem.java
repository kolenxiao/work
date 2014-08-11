package com.coship.sdp.sce.dp.recommend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 精选页板块中的元素实体.
 * 
 * @author 907948
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppRecommendPanelItem extends EntityObject {
	private static final long serialVersionUID = -9127354348033642140L;

	/**
	 * 所属精选页板块.
	 */
	private AppRecommendPanel appRecommendPanel;

	/**
	 * 精选页板块元素的类型，包含：1=应用；2=专题；3=文本
	 */
	private Integer type;

	/**
	 * 元素类型标识值，比如应用包名、专题的id、终端从应用商店启动元素的调用字符串。
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

	/**
	 * 排序字段，最小的在最后。
	 */
	private Integer sortNum = 0;

	/**
	 * 描述版块元素信息字段，扩展用。
	 */
	private String itemDesc;

	/**
	 * 状态字段，默认值1， 1=生效；-1=已删除。
	 */
	private Integer status = 1;

	@ManyToOne
	@JoinColumn(name = "C_PANEL_ID")
	public AppRecommendPanel getAppRecommendPanel() {
		return appRecommendPanel;
	}

	public void setAppRecommendPanel(AppRecommendPanel appRecommendPanel) {
		this.appRecommendPanel = appRecommendPanel;
	}

	@Column(nullable = false, columnDefinition = " int(11) default 1 ")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(nullable = false, columnDefinition = " varchar(255) default '' ")
	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
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

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	@Column(nullable = false, columnDefinition = " int(11) default 1 ")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
