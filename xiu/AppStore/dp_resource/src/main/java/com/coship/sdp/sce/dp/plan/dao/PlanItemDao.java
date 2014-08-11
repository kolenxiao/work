package com.coship.sdp.sce.dp.plan.dao;

import java.util.List;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.plan.entity.PlanItem;

public interface PlanItemDao extends IGenericDao<PlanItem, String> {

    /**
     * 根据方案类项类型返回列表.
     * 
     * @param itemType 方案类项类型. {@link PlanItem#getItemType()}
     * @return
     */
    List<PlanItem> getAllPlanItemList(int itemType);

	List<String> lstExistsItemIds(String planId, List<String> itemIds);
}
