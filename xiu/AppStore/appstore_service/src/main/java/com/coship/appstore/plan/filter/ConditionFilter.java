/**
 * 
 */
package com.coship.appstore.plan.filter;

import java.util.List;
import java.util.Map;

import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.plan.entity.PlanCondition;

/**
 * 条件过滤器. （根据客户端的条件找到优先级最高的方案）<br>
 * <br>
 * 
 * <b>注意： 所有过滤器实现涉及到查询数据库时，要按照{@link PlanCondition#getSortNum()} 升序排序，再按照{@link PlanCondition#getUpdateTime()} 倒序排序。</b>
 * 
 * <p>
 * 这样查询出来的结果列表，经过过滤器过滤后第一个方案就是最满足需求的方案
 * </p>
 * 
 * @author xiezhengrong/907948
 * 
 * @since 2014年4月8日
 */
public interface ConditionFilter {

    /**
     * 过滤器参数名.
     * 
     * @return
     */
    public String getConditionCode();

    /**
     * 根据方案条件查找方案.
     * 
     * @param paramMap request参数.
     * @param planList 返回满足条件的方案列表.
     * @param conditionFilterData 条件过滤器中间过程记录.
     * @return
     */
    public List<Plan> doFilter(Map<String, String[]> paramMap, List<Plan> planList, ConditionFilterData conditionFilterData);
}
