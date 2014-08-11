package com.coship.appstore.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coship.appstore.service.common.Global;
import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.PlanConstants;
import com.coship.sdp.sce.dp.plan.entity.Item;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.plan.entity.PlanCondition;
import com.coship.sdp.sce.dp.plan.entity.PlanItem;
import com.coship.sdp.sce.dp.plan.entity.PlanItemApp;
import com.coship.sdp.sce.dp.plan.service.ItemService;
import com.coship.sdp.sce.dp.plan.service.PlanConditionService;
import com.coship.sdp.sce.dp.plan.service.PlanItemAppService;
import com.coship.sdp.sce.dp.plan.service.PlanItemService;
import com.coship.sdp.sce.dp.plan.service.PlanService;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanelItem;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendPanelItemService;
import com.coship.sdp.sce.dp.subject.service.SubjectAppinfoRelationService;

/**
 * 应用商店前端缓存服务， 从数据库中加载数据到{@link Global}缓存.
 * 
 * @author xiezhengrong/907948
 * 
 * @since 2014年4月14日
 */
@Service("appStoreCacheService")
public class AppStoreCacheService {
    public static final String cacheKeyTemplate = "%s_%s";
    public static final String conditionPlanIdCacheKeyTemplate = "%s_%s_%d";

    @Autowired
    private PlanService planService;

    @Autowired
    private PlanConditionService planConditionService;

    @Autowired
    private PlanItemService planItemService;

    @Autowired
    private PlanItemAppService planItemAppService;
    
    @Autowired
    private ItemService itemService;

    @Autowired
    private SubjectAppinfoRelationService subjectAppinfoRelationService;

    @Autowired
    private AppRecommendPanelItemService appRecommendPanelItemService;

    /**
     * 初始化所有方案.<br>
     * 初始化默认方案.
     */
    public void initPlanMap() {
        List<Plan> planList = planService.getAllEnabledPlanList();

        Plan defaultPlan = new Plan();
        Map<String, Plan> planMap = new HashMap<String, Plan>();
        for (Plan plan : planList) {
            planMap.put(plan.getId(), plan);

            if (plan.getDefaulted()) {
                defaultPlan = plan;
            }
        }

        Global.planMap = planMap;
        Global.defaultPlan = defaultPlan;
    }

    /**
     * 初始化后条件方案的map.
     */
    public void initConditionPlanIdMap() {
        List<PlanCondition> enabledPlanConditionList = planConditionService.getAllEnabledPlanConditionList();

        Map<String, List<Plan>> planConditionPlanIdMap = new HashMap<String, List<Plan>>();
        for (PlanCondition planCondition : enabledPlanConditionList) {
            String code = planCondition.getConditionCode();
            String value = planCondition.getConditionValue();
            String planId = planCondition.getPlanId();
            Integer status = planCondition.getConditionStatus();
            status = ((status == null || status <= 0) ? 0 : status);
            status = (status == 0 ? PlanConstants.CONDITION_STATUS_DISABLED : PlanConstants.CONDITION_STATUS_ENABLED); // 忽略条件的其他状态.
                                                                                                                       // 0表示条件禁用；1表示条件启用。

            String key = String.format(conditionPlanIdCacheKeyTemplate, code, value, status);

            List<Plan> planList = planConditionPlanIdMap.get(key);
            if (planList == null) {
                planList = new ArrayList<Plan>();
                planConditionPlanIdMap.put(key, planList);
            }
            planList.add(new Plan(planId));
        }

        Global.conditionPlanIdMap = planConditionPlanIdMap;
    }

    /**
     * 缓存方案包含的分类
     */
    public void initPlanDpTypeIdMap() {
        List<PlanItem> allPlanDpTypeItemList = planItemService.getAllPlanItemList(PlanConstants.ITEM_TYPE_DPTYPE);

        Map<String, List<String>> planDpTypeIdMap = new HashMap<String, List<String>>();
        for (PlanItem planItem : allPlanDpTypeItemList) {
            String planId = planItem.getPlanId();

            List<String> dpTypeIdList = planDpTypeIdMap.get(planId);
            if (dpTypeIdList == null) {
                dpTypeIdList = new ArrayList<String>();
                planDpTypeIdMap.put(planId, dpTypeIdList);
            }
            dpTypeIdList.add(planItem.getItemId());
        }

        Global.planDpTypeIdMap = planDpTypeIdMap;
    }
    
    public void initPlanSelfTypeMap() {
        List<PlanItem> allPlanSelfTypeItemList = planItemService.getAllPlanItemList(PlanConstants.ITEM_TYPE_SELFTYPE);

        Map<String, List<Item>> planSelfTypeMap = new HashMap<String, List<Item>>();
        for (PlanItem planItem : allPlanSelfTypeItemList) {
            String planId = planItem.getPlanId();

            List<Item> selfTypeList = planSelfTypeMap.get(planId);
            if (selfTypeList == null) {
            	selfTypeList = new ArrayList<Item>();
            	planSelfTypeMap.put(planId, selfTypeList);
            }
            selfTypeList.add(itemService.findItem(planItem.getItemId()));
        }

        Global.planSelfTypeMap = planSelfTypeMap;
    }

    /**
     * 缓存方案下的分类的应用
     */
    public void initPlanDpTypeAppPackageNameMap() {
        //List<PlanItemApp> allPlanDpTypeAppList = planItemAppService.getAllPlanItemAppList(PlanConstants.ITEM_TYPE_DPTYPE);
    	List<PlanItemApp> allPlanDpTypeAppList = planItemAppService.getAllPlanItemAppList(PlanConstants.ITEM_TYPE_SELFTYPE);

        Map<String, List<String>> planDpTypeAppPackageNameMap = new HashMap<String, List<String>>();
        Map<String, Set<String>> planIdKeyDpTypeAppPackageNameMap = new HashMap<String, Set<String>>();
        for (PlanItemApp planItemApp : allPlanDpTypeAppList) {
            String planId = planItemApp.getPlanId();
            String itemId = planItemApp.getItemId();

            String key = String.format(cacheKeyTemplate, planId, itemId);
            List<String> appPkgNameList = planDpTypeAppPackageNameMap.get(key);
            if (appPkgNameList == null) {
                appPkgNameList = new ArrayList<String>();
                planDpTypeAppPackageNameMap.put(key, appPkgNameList);
            }
            appPkgNameList.add(planItemApp.getAppPkgName());
            
            Set<String> theAppPkgNameList = planIdKeyDpTypeAppPackageNameMap.get(planId);
            if(theAppPkgNameList == null) {
            	theAppPkgNameList = new HashSet<String>();
            	planIdKeyDpTypeAppPackageNameMap.put(planId, theAppPkgNameList);
            }
            theAppPkgNameList.add(planItemApp.getAppPkgName());
        }

        Global.planDpTypeAppPackageNameMap = planDpTypeAppPackageNameMap;
        
        //专门用于首页计算方案分类下的应用.
        Global.planIdKeyDpTypeAppPackageNameMap = planIdKeyDpTypeAppPackageNameMap; 
    }

    /**
     * 缓存方案下的所有应用列表.
     */
    public void initPlanAppPackageNameMap() {
        Map<String, Set<String>> planAppPackageNameSetMap = new HashMap<String, Set<String>>();
        // 1. 方案首页(客户端2.0版本精选页)应用
        // 方案板块map.
        List<PlanItem> planItemList = planItemService.getAllPlanItemList(PlanConstants.ITEM_TYPE_PANEL);

        // 方案首页板块id列表.
        Map<String, List<String>> planPanelSubjectIdMap = putPlanPanelAppPkgNameToMap(planAppPackageNameSetMap,
                planItemList);

        // 2. 方案专题应用
        // 方案专题map.
        putPlanSubjectAppPkgNameToMap(planAppPackageNameSetMap, planPanelSubjectIdMap);

        // 3. 方案分类应用
        putPlanAppTypeAppPkgNameToMap(planAppPackageNameSetMap);
        
        Global.planAppPackageNameMap = planAppPackageNameSetMap;
    }

    private void putPlanAppTypeAppPkgNameToMap(Map<String, Set<String>> planAppPackageNameSetMap) {
        Map<String, Set<String>> planDpTypeAppPackageNameMap = Global.planIdKeyDpTypeAppPackageNameMap;
        for(String planId : planDpTypeAppPackageNameMap.keySet()) {
            Set<String> dpTypeAppList = planDpTypeAppPackageNameMap.get(planId);

            addAllToPlanAppPackageNameSet(planId, planAppPackageNameSetMap, dpTypeAppList);
        }
    }

    private void putPlanSubjectAppPkgNameToMap(Map<String, Set<String>> planAppPackageNameSetMap,
            Map<String, List<String>> planPanelSubjectIdMap) {
        Map<String, List<String>> planSubjectTypeMap = Global.planSubjectTypeMap;
        for (String planId : planSubjectTypeMap.keySet()) {
            List<String> subjectTypeIdList = planSubjectTypeMap.get(planId);
            List<String> planPanelSubjectIdList = planPanelSubjectIdMap.get(planId);

            List<String> mergedSubjectTypeIdList = new ArrayList<String>();
            if (planPanelSubjectIdList != null) {
                mergedSubjectTypeIdList.addAll(planPanelSubjectIdList);
            }
            if (subjectTypeIdList != null) {
                mergedSubjectTypeIdList.addAll(subjectTypeIdList);
            }

            // 方案的所有专题下的所有应用包名列表.
            List<String> subjectAppPkgNameList = subjectAppinfoRelationService
                    .getSubjectAppPkgNameList(mergedSubjectTypeIdList);

            addAllToPlanAppPackageNameSet(planId, planAppPackageNameSetMap, subjectAppPkgNameList);
        }
    }

    /**
     * 处理方案首页关联的应用.
     * 
     * @param planAppPackageNameSetMap
     * @param planItemList 返回方案首页关联的专题列表.
     * @return
     */
    private Map<String, List<String>> putPlanPanelAppPkgNameToMap(Map<String, Set<String>> planAppPackageNameSetMap,
            List<PlanItem> planItemList) {
        Map<String, List<String>> planPanelIdMap = new HashMap<String, List<String>>();
        for (PlanItem planItem : planItemList) {
            String planId = planItem.getPlanId();

            List<String> planIdList = planPanelIdMap.get(planId);
            if (planIdList == null) {
                planIdList = new ArrayList<String>();
                planPanelIdMap.put(planId, planIdList);
            }
            planIdList.add(planItem.getItemId());
            
        }

        Map<String, List<AppRecommendPanelItem>> panelItemMapCache = new HashMap<String, List<AppRecommendPanelItem>>();
        Map<String, Set<AppRecommendPanelItem>> planPanelItemMapCache = new HashMap<String, Set<AppRecommendPanelItem>>();

        for (String planId : planPanelIdMap.keySet()) {
            List<String> panelIdList = planPanelIdMap.get(planId);
            Set<AppRecommendPanelItem> planPanelItemSet = new HashSet<AppRecommendPanelItem>();

            for (String panelId : panelIdList) {
                List<AppRecommendPanelItem> panelItemList = panelItemMapCache.get(panelId);
                if (panelItemList == null) {
                    panelItemList = appRecommendPanelItemService.getAppRecommendPanelItemList(panelId);
                    panelItemMapCache.put(panelId, panelItemList);
                }
                planPanelItemSet.addAll(panelItemList);

            }
            planPanelItemMapCache.put(planId, planPanelItemSet);
        }

        Map<String, List<String>> planPanelSubjectIdMap = new HashMap<String, List<String>>();
        for (String planId : planPanelItemMapCache.keySet()) {
            Set<AppRecommendPanelItem> planPanelItemSet = planPanelItemMapCache.get(planId);
            Set<String> appPkgNameSet = new HashSet<String>();
            List<String> subjectIdList = new ArrayList<String>();

            for (AppRecommendPanelItem item : planPanelItemSet) {
                Integer type = item.getType();
                if (type == null) {
                    continue;
                }
                if (type == Constants.APP_RECOMMEND_PANEL_ITEM_APP_TYPE) {
                    String appPakName = item.getTypeValue();
                    appPkgNameSet.add(appPakName);
                    continue;
                }
                if (type == Constants.APP_RECOMMEND_PANEL_ITEM_APP_SUBJECT_TYPE) {
                    String subjectId = item.getTypeValue();

                    subjectIdList.add(subjectId);
                }
            }
            planPanelSubjectIdMap.put(planId, subjectIdList);

            addAllToPlanAppPackageNameSet(planId, planAppPackageNameSetMap, appPkgNameSet);
        }
        return planPanelSubjectIdMap;
    }

    /**
     * 将方案应用放入方案应用map.
     * 
     * @param planAppPackageNameSetMap
     * @param planId
     * @param appPkgNameList
     */
    private void addAllToPlanAppPackageNameSet(String planId, Map<String, Set<String>> planAppPackageNameSetMap,
            Collection<String> appPkgNameList) {
        if (appPkgNameList != null) {
            Set<String> appPkgNameSet = planAppPackageNameSetMap.get(planId);
            if (appPkgNameSet == null) {
                appPkgNameSet = new HashSet<String>();
                planAppPackageNameSetMap.put(planId, appPkgNameSet);
            }
            appPkgNameSet.addAll(appPkgNameList);
        }
    }

    /**
     * 缓存方案专题列表.
     */
    public void initPlanSubjectTypeMap() {
        List<PlanItem> planSubjectTypeList = planItemService.getAllPlanItemList(PlanConstants.ITEM_TYPE_SUBJECT);

        Map<String, List<String>> planSubjectTypeMap = new HashMap<String, List<String>>();
        for (PlanItem planItem : planSubjectTypeList) {
            String planId = planItem.getPlanId();
            String subjectId = planItem.getItemId();

            List<String> subjectTypeIdList = planSubjectTypeMap.get(planId);
            if (subjectTypeIdList == null) {
                subjectTypeIdList = new ArrayList<String>();
                planSubjectTypeMap.put(planId, subjectTypeIdList);
            }
            subjectTypeIdList.add(subjectId);
        }

        Global.planSubjectTypeMap = planSubjectTypeMap;
    }

    public void initAppInfoDetailMap(List<AppInfoDetail> appInfoDetailList) {
        Map<String, AppInfoDetail> appInfoDetailMap = new HashMap<String, AppInfoDetail>();
        for (AppInfoDetail appInfoDetail : appInfoDetailList) {
            String pkgName = appInfoDetail.getAppFilePackage();

            appInfoDetailMap.put(pkgName, appInfoDetail);
        }

        Global.appInfoDetailMap = appInfoDetailMap;
    }
    
    
}
