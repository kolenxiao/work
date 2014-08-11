package com.coship.sdp.sce.dp.ap.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.coship.sdp.sce.dp.ap.entity.AppThumbnail;

public interface AppThumbnailService extends Serializable {
	
	/**
	 * 新增
	 * @param appThumbnail
	 */
	public void save(AppThumbnail appThumbnail);
	
	/**
	 * 通过源图类型获取缩略图Map
	 * @param srcType
	 * @return
	 */
	public Map<String, List<AppThumbnail>> searchByType(String srcType);
	
	/**
	 * 通过源图类型获取缩略图List
	 * @param srcType
	 * @return
	 */
	public List<AppThumbnail> getListByType(String srcType);
}
