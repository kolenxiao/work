package com.coship.sdp.sce.dp.ap.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.dao.DpAppInfoDao;
import com.coship.sdp.sce.dp.ap.dao.HandAppRelateDao;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.ap.entity.HandAppRelate;
import com.coship.sdp.sce.dp.ap.service.DpAppInfoService;
import com.coship.sdp.sce.dp.ap.service.HandAppRelateService;
import com.coship.sdp.sce.dp.common.AppStatusConstants;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.plan.entity.PlanItemApp;
import com.coship.sdp.sce.dp.plan.service.PlanItemAppService;
import com.coship.sdp.sce.dp.tag.service.TagService;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Page;


/**
 * 人工指定的关联推荐应用信息"实现类
 * 
 * @author 908618
 * 
 */
@Service("handAppRelateService")
@Transactional
public class HandAppRelateServiceImpl implements HandAppRelateService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4136901404872080344L;
	
	@Autowired
	private HandAppRelateDao appHandRelateDao;
	
	@Autowired
	private DpAppInfoService dpAppInfoService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private PlanItemAppService planItemAppService;
	
	@Autowired
	private DpAppInfoDao dpAppInfoDao;

	@Override
	public void save(String planId, String appPkgName, String[] relateAppPkgNames) {
		//首先删除
		this.deleteByAppPkgName(planId, appPkgName);
		
		//新增
		int n = 1;
		for (String relateAppPkgName : relateAppPkgNames) {
			HandAppRelate handAppRelate = new HandAppRelate();
			handAppRelate.setPlanId(planId);
			handAppRelate.setAppPkgName(appPkgName);
			handAppRelate.setRelateAppPkgName(relateAppPkgName);
			handAppRelate.setSortNum(n++);
			appHandRelateDao.save(handAppRelate);
		}
	}

	@Override
	public void deleteByAppPkgName(String planId, String appPkgName) {
		String hql = "delete from HandAppRelate where appPkgName = :appPkgName " ;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appPkgName", appPkgName);
		
		if(StringUtils.isNotBlank(planId)){
			hql += " and planId = :planId" ;
			params.put("planId", planId);
		}
		appHandRelateDao.executeUpdate(hql, params);
	}
	
	@Override
	public void deleteByRelateAppPkgName(String planId, String relateAppPkgName) {
		String selectHQL = "from HandAppRelate where relateAppPkgName = :relateAppPkgName ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("relateAppPkgName", relateAppPkgName);
		
		if(StringUtils.isNotBlank(planId)){
			selectHQL += " and planId = :planId" ;
			params.put("planId", planId);
		}
		
		List<HandAppRelate> list = appHandRelateDao.query(selectHQL, params);
		if (null != list) {
			for (HandAppRelate handAppRelate : list) {
				this.deleteByAppPkgName(handAppRelate.getPlanId(), handAppRelate.getAppPkgName());
			}
		}
	}

	@Override
	public List<HandAppRelate> list(String planId, String appPkgName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("planId", planId);
		params.put("appPkgName", appPkgName);

		StringBuffer sbf = new StringBuffer("from HandAppRelate where planId = :planId ");
		sbf.append(" and appPkgName = :appPkgName");
		sbf.append(" order by sortNum");
		String hql = sbf.toString();
		List<HandAppRelate> list = appHandRelateDao.query(hql, params);
		if (null == list) {
			list = new ArrayList<HandAppRelate>();
		}
		return list;
	}

	@Override
	public List<DpAppInfo> getAppRelateList(String planId, String appPkgName) {
		List<DpAppInfo> resultList = this.getAppRelateAppListByHand(planId, appPkgName);
		if(resultList.size() == 0){
			resultList = this.getAppRelateAppListBySystem(planId, appPkgName);
		}
		return resultList;
	}
	
	@Override
	public List<DpAppInfo> getAppRelateAppListBySystem(String planId, String appPkgName) {
		//需要关联的应用个数
		int countMax = Constants.APP_RELATE_MAXCOUNT;
		//查询方案分类下的应用集合(在线状态)
		Map<String, PlanItemApp> planItemAppMap = planItemAppService.getItemAppOnlineMapByPlanId(planId);

		//查询应用关联的标签集合
    	Map<String, List<String>> appTagMap = tagService.getTagNameListForAll();
    	List<String> currentAppTagNameList = appTagMap.get(appPkgName);
    	
    	Map<String, Integer> sortMap = new LinkedHashMap<String, Integer>();
		if(null != currentAppTagNameList){
			//应用本身有标签，则首先匹配标签
			for (String tagName : currentAppTagNameList) {
				for (String key : appTagMap.keySet()) {
					if (!StringUtils.equals(appPkgName, key)
							&& planItemAppMap.containsKey(key)
							&& appTagMap.get(key).contains(tagName)) {
						Integer count = null == sortMap.get(key) ?  0 : sortMap.get(key);
						sortMap.put(key, count + 1);
					}
				}
			}
		}
		
		//应用本身没有标签或者标签匹配的数量不够时，才匹配应用所属的固定分类
		if(sortMap.size() < countMax){
			DpAppInfo dpAppInfo = dpAppInfoService.findUniqueByPackageName(appPkgName);
			try {
				for (PlanItemApp planItemApp : planItemAppMap.values()) {
					String tempPkgName = planItemApp.getAppPkgName();
					while (!appPkgName.equals(tempPkgName)
							&& !sortMap.containsKey(tempPkgName)
							&& sortMap.size() < countMax
							&& StringUtils.equals(planItemApp.getDpAppInfo().getDpType().getId(), 
									dpAppInfo.getDpType().getId())) {
						sortMap.put(tempPkgName, 1);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//应用连固定分类都不够匹配时，则匹配其它分类
		if(sortMap.size() < countMax){
			for (PlanItemApp planItemApp : planItemAppMap.values()) {
				String tempPkgName = planItemApp.getAppPkgName();
				while (!appPkgName.equals(tempPkgName)
						&& !sortMap.containsKey(tempPkgName)
						&& sortMap.size() < countMax) {
					sortMap.put(tempPkgName, 1);
				}
			}
		}

		// 对treeMap进行排序
		List<Map.Entry<String, Integer>> mappingList = new ArrayList<Map.Entry<String, Integer>>(sortMap.entrySet());
		Collections.sort(mappingList,
				new Comparator<Map.Entry<String, Integer>>() {
					public int compare(Map.Entry<String, Integer> mapping1, Map.Entry<String, Integer> mapping2) {
						return mapping2.getValue().compareTo(mapping1.getValue());
					}
				});
		
		List<String> pkgNameList = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : mappingList) {
			pkgNameList.add(entry.getKey());
		}

		//获取DpAppInfo列表
		List<DpAppInfo> resultList = this.transPkgNameToDpAppInPlan(planId, pkgNameList);
		
		//返回
		return resultList;
	}
	
	@Override
	public List<DpAppInfo> getAppRelateAppListByHand(String planId, String appPkgName) {
		// 查询是否有人工指定关联应用
		List<HandAppRelate> handList = this.list(planId, appPkgName);
		
		List<String> pkgNameList = new ArrayList<String>();
		for (HandAppRelate handAppRelate : handList) {
			pkgNameList.add(handAppRelate.getRelateAppPkgName());
		}
		
		List<DpAppInfo> resultList = this.transPkgNameToDpAppInPlan(planId, pkgNameList);
		return resultList;
	}
	
	public Page<DpAppInfo> getAppListForReplace(Page<DpAppInfo> page, Map<String, Object> specialParams) {
		//构造查询条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("planId", specialParams.get("planId"));
		params.put("appStatus", AppStatusConstants.APP_STATUS_ONLINE);
		params.put("sourceAppPkgName", specialParams.get("sourceAppPkgName"));
		
		StringBuffer sbf = new StringBuffer();
    	sbf.append("from DpAppInfo as da where da.appStatus = :appStatus ");
    	sbf.append(" and da.appFilePackage != :sourceAppPkgName ");
    	sbf.append(" and exists (select ia.appPkgName from PlanItemApp as ia where ia.planId=:planId and ia.appPkgName = da.appFilePackage)");
    	
    	String appName = (String)specialParams.get("appName");
        if(StringUtils.isNotBlank(appName)){
        	sbf.append( " and da.appName like '");
        	sbf.append(SqlUtil.getLikeSql(appName));
        	sbf.append("' escape '/' ");
        }

        String typeId = (String)specialParams.get("typeId");
        if(StringUtils.isNotBlank(typeId)){
        	sbf.append( " and da.dpType.id = :typeId");
        	params.put("typeId", typeId);
        }
    	sbf.append(" order by da.updateTime desc ");

        String hql = sbf.toString();
        if(!"".equals(hql)){
        	page = dpAppInfoDao.queryPage((Page<DpAppInfo>) page, hql, params);
        }
		return page;
	}
	
	
	
	
	/**
	 * 过滤在方案分类下的应用
	 * @param planId
	 * @param pkgNameList
	 * @return
	 */
	private List<DpAppInfo> transPkgNameToDpAppInPlan(String planId, List<String> pkgNameList){
		List<DpAppInfo> resultList = new ArrayList<DpAppInfo>();
		if(null != pkgNameList){
			//查询方案分类下的应用集合
			Map<String, PlanItemApp> planItemAppMap = planItemAppService.getItemAppMapByPlanId(planId);
			for (String pkgName : pkgNameList) {
				PlanItemApp planItemApp = planItemAppMap.get(pkgName);
				if(null != planItemApp && null != planItemApp.getDpAppInfo()){
					resultList.add(planItemApp.getDpAppInfo());
				}
			}
		}
		return resultList;
	}

	
	
}
