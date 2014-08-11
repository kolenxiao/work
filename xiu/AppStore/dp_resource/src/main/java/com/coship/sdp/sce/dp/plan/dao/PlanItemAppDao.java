package com.coship.sdp.sce.dp.plan.dao;

import java.util.List;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.plan.entity.PlanItem;
import com.coship.sdp.sce.dp.plan.entity.PlanItemApp;

public interface PlanItemAppDao extends IGenericDao<PlanItemApp, String> {

    /**
     * 
     * 根据方案类项类型查找.
     * 
     * @param itemType 方案类项类型,值参考： {@link PlanItem#getItemType()}
     * @return
     */
    List<PlanItemApp> getAllPlanItemAppList(int itemType);

	void appDeletePlan(String pkgName, String planId,List<String> deleteItemIds);
}
