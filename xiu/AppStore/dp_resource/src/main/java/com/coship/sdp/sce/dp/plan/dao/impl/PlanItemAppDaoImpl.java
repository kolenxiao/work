package com.coship.sdp.sce.dp.plan.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.plan.dao.PlanItemAppDao;
import com.coship.sdp.sce.dp.plan.entity.PlanItemApp;

@Repository("planItemAppDao")
public class PlanItemAppDaoImpl extends GenericDaoImpl<PlanItemApp, String> implements PlanItemAppDao  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7412851743778949867L;

    @Override
    public List<PlanItemApp> getAllPlanItemAppList(int itemType) {
        String hql = "select pia from PlanItemApp pia where pia.itemType=? order by pia.planId, pia.sortNum, pia.updateTime desc ";

        return this.query(hql, itemType);
    }

	@Override
	public void appDeletePlan(String pkgName, String planId, List<String> itemIds) {
		Map<String,Object> objParam = new HashMap<String,Object>();
		objParam.put("PKG_NAME", pkgName);
		objParam.put("PLAN_ID", planId);
		objParam.put("ITEM_IDS", itemIds);
		String strHql = "delete from PlanItemApp where appPkgName = :PKG_NAME and planId = :PLAN_ID and itemId in (:ITEM_IDS)";
		this.executeUpdate(strHql, objParam);
	}
}
