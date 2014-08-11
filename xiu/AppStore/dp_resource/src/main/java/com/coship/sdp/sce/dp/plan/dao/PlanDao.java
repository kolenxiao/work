package com.coship.sdp.sce.dp.plan.dao;

import java.sql.SQLException;
import java.util.List;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.utils.Page;

public interface PlanDao extends IGenericDao<Plan, String> {

	Page<Plan> listForApp(int start, int limit, String appId, Plan plan);

    /**
     * 默认方案.(全局默认)
     * 
     * @return
     */
    Plan getDefaultPlan();

    /**
     * 获得所有启用状态的方案map.
     * 
     * @return
     */
    List<Plan> getAllEnabledPlanList();

	String copy(String planId) throws SQLException;
}
