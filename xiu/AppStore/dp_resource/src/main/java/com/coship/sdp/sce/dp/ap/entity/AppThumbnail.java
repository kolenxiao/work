package com.coship.sdp.sce.dp.ap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 缩略图信息
 * 
 * @author 908618
 * 
 */
@Entity
public class AppThumbnail extends EntityObject {

	private static final long serialVersionUID = 7170515828866251529L;

	/**
	 * 源图类型(logo, img, poster等)
	 */
	private String srcType;

	/**
	 * 源图片
	 */
	private String srcImg;
	
	/**
	 * 缩略后的图片
	 */
	private String thumbImg;

	/**
	 * 缩略尺寸
	 */
	private String dimension;
	
	/**
	 * 状态0:失败; 1：成功;
	 */
	private Integer status;
	
	/**
	 * 失败次数
	 */
	private Integer failCount;

	public String getSrcType() {
		return srcType;
	}

	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}

	public String getSrcImg() {
		return srcImg;
	}

	public void setSrcImg(String srcImg) {
		this.srcImg = srcImg;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
