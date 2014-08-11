/**
 * 
 */
package com.coship.appstore.plan.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coship.appstore.cache.AppStoreCacheService;
import com.coship.appstore.service.common.Global;
import com.coship.sdp.sce.dp.common.PlanConstants;
import com.coship.sdp.sce.dp.plan.entity.Plan;

/**
 * 抽象条件过滤器，提供基本封装.
 * 
 * @author xiezhengrong/907948
 * 
 * @since 2014年4月9日
 */
public abstract class AbstractConditionFilter implements ConditionFilter {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String conditionCode;

    public String getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }

    @Override
    public List<Plan> doFilter(Map<String, String[]> paramMap, List<Plan> beforeFilterPlanList,
            ConditionFilterData conditionFilterData) {
        // 过滤器未设置条件.
        if (conditionCode == null || "".equals(conditionCode)) {
            return beforeFilterPlanList;
        }

        String[] conditionValueObj = paramMap.get(conditionCode);
        // 没有传参数。
        if (conditionValueObj == null || conditionValueObj.length == 0) {
            return beforeFilterPlanList;
        }

        String conditionValue = conditionValueObj[0];
        if("".equals(conditionValue) || "null".equalsIgnoreCase(conditionValue)) {
        	return beforeFilterPlanList;
        }
        
        // 条件已被禁用，不参与计算.
        boolean disabled = checkConditionStatus(conditionValue);
        if (disabled) {
            return beforeFilterPlanList;
        }

        // 过滤器需要做处理，计数器+1
        conditionFilterData.incrementFilterCount();

        if (beforeFilterPlanList == null || beforeFilterPlanList.size() == 0) {
            return getPlanListByCondition(conditionValue);
        }

        return filter(beforeFilterPlanList, conditionValue);
    }

    /**
     * 检查条件是否已被禁用.
     * 
     * @param 条件值.
     * @return
     */
    private boolean checkConditionStatus(String conditionValue) {
        String key = String.format(AppStoreCacheService.conditionPlanIdCacheKeyTemplate, this.getConditionCode(),
                conditionValue, PlanConstants.CONDITION_STATUS_DISABLED);

        List<Plan> result = Global.conditionPlanIdMap.get(key);
        if (result != null) {
            return true;
        }
        return false;
    }

    /**
     * 过滤结果. 默认使用相等过滤，如果不用相等过滤（比如ip）时，请在子类覆盖本方法.
     * 
     * @param beforeFilterPlanList 经过本过滤器过滤之前的结果列表.
     * @param conditionValue
     * @return 返回满足条件的方案列表.
     */
    protected List<Plan> filter(List<Plan> beforeFilterPlanList, String conditionValue) {
        // 本过滤器返回的方案列表。
        List<Plan> conditionCodePlanList = getPlanListByCondition(conditionValue);

        if (CollectionUtils.isEmpty(beforeFilterPlanList)) {
            return conditionCodePlanList;
        }

        return equalsFilter(beforeFilterPlanList, conditionCodePlanList);
    }

    /**
     * 相等比较过滤.
     * 
     * @param beforeFilterPlanList
     * @param conditionCodePlanList
     * @return
     */
    private List<Plan> equalsFilter(List<Plan> beforeFilterPlanList, List<Plan> conditionCodePlanList) {
        // 开始过滤.
        List<Plan> result = new ArrayList<Plan>();
        for (Plan filterPlan : beforeFilterPlanList) {
            String filterPlanId = filterPlan.getId();
            for (Plan conditionPlan : conditionCodePlanList) {
                if (filterPlanId.equals(conditionPlan.getId())) {
                    result.add(filterPlan);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 根据条件在缓存中查找匹配条件的方案.
     * 
     * @param conditionValue
     * @return
     */
    protected List<Plan> getPlanListByCondition(String conditionValue) {
        String key = String.format(AppStoreCacheService.conditionPlanIdCacheKeyTemplate, this.getConditionCode(),
                conditionValue, PlanConstants.CONDITION_STATUS_ENABLED);

        return Global.conditionPlanIdMap.get(key);
    }
}
