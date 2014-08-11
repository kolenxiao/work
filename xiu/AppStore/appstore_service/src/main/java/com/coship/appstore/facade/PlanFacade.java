/**
 * 
 */
package com.coship.appstore.facade;

import java.util.List;
import java.util.Map;

import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.utils.Page;

/**
 * @author xiezhengrong/907948
 *
 * @since 2014年4月10日
 */
public interface PlanFacade {
	
	/**
	 * 获取方案
	 * @param paramMap
	 * @return
	 */
	Plan getPlan(Map<String, String[]> paramMap);

    /**
     * 根据方案获得分类列表.（可以包含自定义分类）
     * 
     * @param paramMap request参数.
     * @return
     */
    List<DpType> getPlanedDpTypeList(Map<String, String[]> paramMap);
    List<DpType> getPlanedSelfTypeList(Map<String, String[]> paramMap);

    /**
     * 根据方案获得专题列表
     * 
     * @return
     */
    List<AppSubjectType> getPlanedAppSubjectTypeList(Map<String, String[]> paramMap);
    List<AppSubjectType> getPlanedAppSubjectTypeList(String planId);

    /**
     * 根据方案+类型id获得分类下的应用列表
     * 
     * @param parameterMap
     * @param page
     * @return
     */
    List<AppInfoDetail> getPlanedAppDetailListByType(Map<String, String[]> parameterMap, Page<AppInfoDetail> page);
    List<AppInfoDetail> getPlanedAppDetailListByType(String planId, String typeId, String osVersion);
    
    /**
     * 获取方案下的应用列表.
     * @param paramMap
     * @return
     */
    List<AppInfoDetail> getPlanedAppInfoList(Map<String, String[]> paramMap);
    
    /**
     * 获取方案下的精选页版块json串(已包含版块下的元素列表)
     * @param paramMap
     * @return
     */
    String getPlanedAppRecommendPanelJson(Map<String, String[]> paramMap);

}
