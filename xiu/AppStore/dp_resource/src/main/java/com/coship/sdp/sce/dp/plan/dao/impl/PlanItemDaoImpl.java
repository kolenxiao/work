package com.coship.sdp.sce.dp.plan.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.plan.dao.PlanItemDao;
import com.coship.sdp.sce.dp.plan.entity.PlanItem;
import com.coship.sdp.sce.dp.plan.util.PlanUtil;

@Repository("planItemDao")
public class PlanItemDaoImpl extends GenericDaoImpl<PlanItem, String> implements PlanItemDao  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8770248502798050989L;

	@Override
    public List<PlanItem> getAllPlanItemList(int itemType) {
        String hql = "select pi from PlanItem pi where pi.itemType=? order by pi.planId, pi.sortNum, pi.updateTime desc ";
        return this.query(hql, itemType);
    }


	@Override
	public List<String> lstExistsItemIds(String planId, List<String> itemIds) {
		Map<String,Object> objParams = new HashMap<String,Object>();
		objParams.put("PLAN_ID", planId);
		objParams.put("ITEM_IDS", itemIds);
		String strHql = "select i.C_ITEM_ID from t_plan_item i where i.C_PLAN_ID = :PLAN_ID and i.C_ITEM_ID in (:ITEM_IDS) ";
		
        Session session = getSession();
        List<Map<String,Object>> lstResult = PlanUtil.queryListBySql(session,  strHql, objParams);
        List<String> lstIds = new ArrayList<String>();
        for(Map<String,Object> objMap : lstResult){
        	lstIds.add(String.valueOf(objMap.get("C_ITEM_ID")));
        }
        return lstIds;
	}
}
