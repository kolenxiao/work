/**
 * 
 */
package com.coship.sdp.sce.dp.recommend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 精选页板块实体.
 * 
 * @author 907948
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppRecommendPanel extends EntityObject {
	private static final long serialVersionUID = -3196252968286319141L;

	/**
	 * 精选页板块名称
	 */
	private String panelName = "";

	/**
	 * 板块布局类型，1=3个元素1大2小；2=6个元素平铺
	 */
	private Integer layoutType;

	/**
	 * 精选页排序字段，最小的在最后。
	 */
	private Integer sortNum = 0;

	/**
	 * 精选页描述(扩展用)
	 */
	private String panelDesc = "";

	/**
	 * 状态字段，默认值1， 1=生效；-1=已删除。
	 */
	private Integer status = 1;

	@Column(nullable = false, columnDefinition = " varchar(255) default '' ")
	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	@Column(nullable = false, columnDefinition = " int(11) default 1 ")
	public Integer getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(Integer layoutType) {
		this.layoutType = layoutType;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getPanelDesc() {
		return panelDesc;
	}

	public void setPanelDesc(String panelDesc) {
		this.panelDesc = panelDesc;
	}

	@Column(nullable = false, columnDefinition = " int(11) default 1 ")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
