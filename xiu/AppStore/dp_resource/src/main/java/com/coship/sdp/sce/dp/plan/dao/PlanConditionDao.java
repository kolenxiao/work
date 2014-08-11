/*
 * 文件名称：ConditionDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-03-27
 *
 */
package com.coship.sdp.sce.dp.plan.dao;

import java.util.List;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.plan.entity.Condition;
import com.coship.sdp.sce.dp.plan.entity.PlanCondition;
import com.coship.sdp.utils.Page;

/**
 * 应用数据访问接口
 * @author 909194
 * @version [版本号, 2014-03-27]
 * @since [产品/模块版本]
 */
public interface PlanConditionDao extends IGenericDao<PlanCondition, String>{

	Page<Condition> listForPlan(int start, int limit, String planId, Condition queryCondition);

	List<String> listIds(String planId, List<String> conditionIds);

    /**
     * 根据条件值获得符合条件的方案条件列表
     * 
     * @param condition 条件参数,如: channelId
     * @param value 条件值.
     * @return
     */
    List<PlanCondition> getPlanConditionList(String condition, String value);

    /**
     * 查询所有有效方案条件, 带有条件相关字段和方案相关字段
     * 
     * @return
     */
    List<PlanCondition> getAllEnabledPlanConditionList();
}
