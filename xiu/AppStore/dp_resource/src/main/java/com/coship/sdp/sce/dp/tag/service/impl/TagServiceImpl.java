package com.coship.sdp.sce.dp.tag.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.tag.dao.AppTagRelationDao;
import com.coship.sdp.sce.dp.tag.dao.TagDao;
import com.coship.sdp.sce.dp.tag.entity.AppTagRelation;
import com.coship.sdp.sce.dp.tag.entity.TagInfo;
import com.coship.sdp.sce.dp.tag.service.TagService;

/**
 * 应用标签service
 * @author 908618
 *
 */
@Service("tagService")
@Transactional
public class TagServiceImpl implements TagService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TagDao tagDao;
	
	@Autowired
	private AppTagRelationDao appTagRelationDao;

	@Override
	public void saveAppTag(String appFilePackage, String[] appTags) {
		if (StringUtils.isNotBlank(appFilePackage)) {
			// 删除应用标签关联信息
			this.deleteAppTagRelationByAppPackageName(appFilePackage);
			for (int i = 0; i < appTags.length; i++) {
				String tagName = appTags[i];
				TagInfo tagInfo = this.getTagInfoByName(tagName);
				if (null == tagInfo) {
					// 新增标签信息
					tagInfo = new TagInfo();
					tagInfo.setName(tagName);
					tagInfo.setStatus(1);
					tagInfo.setCreateTime(new Date());
					tagDao.save(tagInfo);
				}

				// 新增应用标签关联信息
				AppTagRelation appTagRelation = new AppTagRelation();
				appTagRelation.setAppPackageName(appFilePackage);
				appTagRelation.setTagInfo(tagInfo);
				appTagRelation.setSortNum(i + 1);
				appTagRelationDao.saveOrUpdate(appTagRelation);
			}
		}
	}
	
	@Override
	public TagInfo getTagInfoByName(String tagName) {
		String hql = "from TagInfo ti where ti.name = ?";
		TagInfo tagInfo = tagDao.findUnique(hql, new Object[] { tagName });
		return tagInfo;
	}
	
	@Override
	public List<TagInfo> getTagInfoListByAppPackageName(String appPackageName) {
		List<TagInfo> resultList = new ArrayList<TagInfo>();
		if (StringUtils.isNotBlank(appPackageName)) {
			List<AppTagRelation> list = this.getAppTagRelationListByAppPackageName(appPackageName);
			for (AppTagRelation appTagRelation : list) {
				resultList.add(appTagRelation.getTagInfo());
			}
		}
		return resultList;
	}

	@Override
	public List<AppTagRelation> getAppTagRelationListByAppPackageName(String appPackageName) {
		if(StringUtils.isBlank(appPackageName)){
			return new ArrayList<AppTagRelation>();
		}
		
		String hql = "from AppTagRelation atr where atr.appPackageName = ? order by atr.sortNum";
		List<AppTagRelation> list = appTagRelationDao.query(hql, new Object[] { appPackageName });
		if(null == list){
			list = new ArrayList<AppTagRelation>();
		}
		return list;
	}

	@Override
	public List<AppTagRelation> getAppTagRelationListByTagName(String tagName) {
		if (StringUtils.isBlank(tagName)) {
			return new ArrayList<AppTagRelation>();
		}

		String hql = "from AppTagRelation atr where tagInfo.name = ? order by atr.sortNum";
		List<AppTagRelation> list = appTagRelationDao.query(hql, new Object[] { tagName });
		if (null == list) {
			list = new ArrayList<AppTagRelation>();
		}
		return list;
	}

	@Override
	public String getTagsNameByAppPackageName(String appPackageName) {
		String appTags = "";
		if (StringUtils.isNotBlank(appPackageName)) {
			List<TagInfo> tagInfoList = this.getTagInfoListByAppPackageName(appPackageName);
			for (TagInfo tagInfo : tagInfoList) {
				appTags += tagInfo.getName() + ",";
			}
			if (StringUtils.isNotBlank(appTags)) {
				appTags = StringUtils.substringBeforeLast(appTags, ",");
			}
		}
		return appTags;
	}
	
	@Override
	public Map<String, List<String>> getTagNameListForAll() {
		String hql = "from AppTagRelation atr order by atr.appPackageName, atr.sortNum";
		List<AppTagRelation> appTagList = appTagRelationDao.query(hql);

		Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
		if(null != appTagList){
			List<String> tagNameList = null;
			for (AppTagRelation appTagRelation : appTagList) {
				tagNameList = resultMap.get(appTagRelation.getAppPackageName());
				if (null == tagNameList) {
					tagNameList = new ArrayList<String>();
					resultMap.put(appTagRelation.getAppPackageName(), tagNameList);
				}
				tagNameList.add(appTagRelation.getTagInfo().getName());
			}
		}
		return resultMap;
	}

	
	
	
	
	private void deleteAppTagRelationByAppPackageName(String appPackageName) {
		String sql = "delete from T_APP_TAG_RELATION where C_APP_PACKAGE_NAME = '" + appPackageName + "'";
		appTagRelationDao.deleteAppTagRelation(sql);
	}

}
