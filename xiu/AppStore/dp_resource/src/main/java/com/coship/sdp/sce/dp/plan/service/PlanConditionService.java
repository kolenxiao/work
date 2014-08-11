/*
 * 文件名称：ConditionService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-03-27
 *
 */
package com.coship.sdp.sce.dp.plan.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.plan.entity.Condition;
import com.coship.sdp.sce.dp.plan.entity.PlanCondition;
import com.coship.sdp.utils.Page;

/**
 * 应用管理服务接口
 * @author 909194
 * @version [版本号, 2014-03-27]
 * @since [产品/模块版本]
 */
public interface PlanConditionService extends Serializable{

	void addPlanCondition(String planId, List<String> conditionIds);
	
	void deletePlanCondition(String planId, List<String> conditionIds);
	
	public Page<Condition> listForPlan(int start, int limit, String planId,
			Condition queryCondition);


    /**
     * 根据条件值获得符合条件的方案条件列表
     * 
     * @param condition 条件参数,如: channelId
     * @param value 条件值.
     * @return
     */
    List<PlanCondition> getPlanConditionList(String condition, String value);

    /**
     * 获得所有有效的方案条件.
     * 
     * @return
     */
    List<PlanCondition> getAllEnabledPlanConditionList();
}
