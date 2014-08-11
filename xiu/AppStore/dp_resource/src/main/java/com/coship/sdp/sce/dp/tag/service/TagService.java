package com.coship.sdp.sce.dp.tag.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.coship.sdp.sce.dp.tag.entity.AppTagRelation;
import com.coship.sdp.sce.dp.tag.entity.TagInfo;

/**
 * 应用标签service
 * @author 908618
 *
 */
public interface TagService extends Serializable {

	/**
	 * 保存应用标签
	 * @param dpAppInfo
	 * @param appTags
	 */
	public void saveAppTag(String appFilePackage, String[] appTags);
	
	/**
	 * 通过标签名查询标签信息
	 * @param tagName
	 * @return
	 */
	public TagInfo getTagInfoByName(String tagName);
	
	/**
	 * 通过应用包名查询标签信息列表
	 * @param appPackageName
	 * @return
	 */
	public List<TagInfo> getTagInfoListByAppPackageName(String appPackageName);
	
	/**
	 * 通过应用包名查询标签名字符串，以逗号隔开
	 * @param appPackageName
	 * @return
	 */
	public String getTagsNameByAppPackageName(String appPackageName);
	
	/**
	 * 查询应用的标签名List
	 * @return
	 */
	public Map<String, List<String>> getTagNameListForAll();
	
	/**
	 * 通过应用包名获取对应的应用标签关联列表
	 * @param appPackageName
	 * @return
	 */
	public List<AppTagRelation> getAppTagRelationListByAppPackageName(String appPackageName);
	
	/**
	 * 通过标签名获取对应的应用标签关联列表
	 * @param tagName
	 * @return
	 */
	public List<AppTagRelation> getAppTagRelationListByTagName(String tagName);

}
