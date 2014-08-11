/*
 * 文件名称：ConditionServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-03-27
 *
 */
package com.coship.sdp.sce.dp.plan.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.plan.dao.ConditionDao;
import com.coship.sdp.sce.dp.plan.dao.PlanConditionDao;
import com.coship.sdp.sce.dp.plan.dao.PlanDao;
import com.coship.sdp.sce.dp.plan.entity.Condition;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.plan.entity.PlanCondition;
import com.coship.sdp.sce.dp.plan.service.PlanConditionService;
import com.coship.sdp.utils.Page;

/**
 * 应用管理服务实现类
 *
 * @author 909194
 * @version [版本号, 2014-03-27]
 * @since [产品/模块版本]
 */
@Service("planConditionService")
@Transactional
public class PlanConditionServiceImpl implements PlanConditionService{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7410104779263679279L;
	
	/**
     * conditionDao对象
     */
    @Autowired
    private PlanConditionDao planConditionDao;
    
    @Autowired
    private ConditionDao conditionDao;
    
    @Autowired
    private PlanDao planDao;

	
	@Override
	public Page<Condition> listForPlan(int start, int limit, String planId, Condition queryCondition) {
		return planConditionDao.listForPlan(start, limit,planId ,queryCondition);
	}
	
	@Override
	public void addPlanCondition(String planId, List<String> conditionIds) {
		List<String> lstIds = planConditionDao.listIds(planId,conditionIds);
		if(null !=lstIds && lstIds.size() > 0){
			for(String strId : lstIds){
				PlanCondition objPlanCondition = planConditionDao.get(strId);
				objPlanCondition.setStatus(1);
				objPlanCondition.setUpdateTime(new Date());
				planConditionDao.update(objPlanCondition);
			}
		}else{
			for(String strConditionId : conditionIds){
				Date objDate = new Date();
				PlanCondition objPlanCondition = new PlanCondition();
				Plan objPlan = new Plan();
				objPlan.setId(planId);
				objPlanCondition.setPlan(objPlan);
				
				Condition objCondition = new Condition();
				objCondition.setId(strConditionId);
				objPlanCondition.setCondition(objCondition);
				objPlanCondition.setCreateTime(objDate);
				objPlanCondition.setUpdateTime(objDate);
				planConditionDao.save(objPlanCondition);
			}
		}
	}

	@Override
	public void deletePlanCondition(String planId, List<String> conditionIds) {
		List<String> lstIds = planConditionDao.listIds(planId,conditionIds);
		if(null !=lstIds && lstIds.size() > 0){
			for(String strId : lstIds){
				PlanCondition objPlanCondition = planConditionDao.get(strId);
				planConditionDao.delete(objPlanCondition);
			}
		}
	}

    @Override
    public List<PlanCondition> getPlanConditionList(String condition, String value) {
        return planConditionDao.getPlanConditionList(condition, value);
    }

    @Override
    public List<PlanCondition> getAllEnabledPlanConditionList() {
        return planConditionDao.getAllEnabledPlanConditionList();
    }
}
