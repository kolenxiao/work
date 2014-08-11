package com.coship.appstore.plan.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coship.appstore.plan.filter.ConditionFilter;
import com.coship.appstore.plan.filter.ConditionFilterData;
import com.coship.appstore.service.common.Global;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.plan.service.PlanService;

/**
 * 方案处理器, 通过条件过滤器链获得最匹配方案.
 * 
 * @author xiezhengrong/907948
 * 
 * @since 2014年4月8日
 */
@Component
public class PlanProcessor {
    private static final Logger logger = LoggerFactory.getLogger(PlanProcessor.class);

    @Autowired
    private PlanService planService;

    /**
     * 过滤器列表.
     */
    private List<ConditionFilter> conditionFilters = new ArrayList<ConditionFilter>();

    /**
     * 过滤器参数名列表.
     */
    private List<String> conditionCodeList = new ArrayList<String>();

    @Autowired
    public void setConditionFilters(List<ConditionFilter> conditionFilters) {
        this.conditionFilters = conditionFilters;

        for (ConditionFilter filter : this.conditionFilters) {
            String conditionCode = filter.getConditionCode();
            if (StringUtils.isNotEmpty(conditionCode)) {
                conditionCodeList.add(conditionCode);
            }
        }
    }

    public Plan process(Map<String, String[]> paramMap) {

        List<Plan> planList = new ArrayList<Plan>();
        try {
            ConditionFilterData data = new ConditionFilterData();
            for (ConditionFilter conditionFilter : conditionFilters) {
                planList = conditionFilter.doFilter(paramMap, planList, data);

                // 如果经过过滤器过滤了，但是无结果，表明没有符合条件的方案，需使用默认方案.
                if (data.getFilterCount() > 0 && CollectionUtils.isEmpty(planList)) {
                    break;
                }
            }

            // 没有匹配的方案，使用默认方案.
            if (planList == null || planList.size() == 0) {
                return defaultPlan(paramMap);
            }

            // 匹配多个方案，使用优先级最高的方案.优先级相同时，时间最新的方案。
            return calculateHighestPlan(planList);
        } catch (Exception e) {
            logger.error("获取方案时发生异常。异常信息如下： ", e);
            return defaultPlan(paramMap);
        }
    }

    /**
     * 计算优先级最高的方案.
     * 
     * @param planList
     */
    private Plan calculateHighestPlan(List<Plan> planList) {
        Map<String, Plan> fullPlanMap = Global.planMap;

        int size = planList.size();
        // 第一个.
        Plan priorityHighestPlan = fullPlanMap.get(planList.get(0).getId());

        if (size == 1) {
            return priorityHighestPlan;
        }

        // 从第二个开始.
        for (int i = 1; i < size; i++) {
            Plan plan = planList.get(i);

            String planId = plan.getId();
            Plan fullPlan = fullPlanMap.get(planId);

            int priority = fullPlan.getSortNum();
            int oldPriority = priorityHighestPlan.getSortNum();

            // 值最小,优先级最高.
            if (priority < oldPriority) {
                priorityHighestPlan = fullPlan;
                continue;
            }

            if (priority == oldPriority) {
                // 检查更新时间，方案更新时间最晚的优先级最高.
                Date date = fullPlan.getUpdateTime();
                Date priorityHighestDate = priorityHighestPlan.getUpdateTime();

                if (date.after(priorityHighestDate)) {
                    priorityHighestPlan = fullPlan;
                }
            }
        }

        return priorityHighestPlan;
    }

    /**
     * 使用默认方案. 目前直接返回全局的默认方案, 暂不根据渠道号、区域码等条件来获得默认方案.。
     * 
     * @param paramMap
     */
    private Plan defaultPlan(Map<String, String[]> paramMap) {
        return Global.defaultPlan;
    }
}
