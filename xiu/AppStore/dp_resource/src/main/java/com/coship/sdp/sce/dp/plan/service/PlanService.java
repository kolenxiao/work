package com.coship.sdp.sce.dp.plan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.utils.Page;

/**
 * 精细化运营-方案管理Service
 * @author 908618
 *
 */
public interface PlanService extends Serializable {
	
	/**
	 * 新增方案
	 * @param plan
	 */
	public void createPlan(Plan plan);
	
	/**
	 * 修改方案
	 * @param plan
	 */
	public void modifyPlan(Plan plan);
	
	/**
	 * 删除方案
	 * @param planIdArr
	 */
	public void deletePlan(String[] planIdArr);
	
	/**
	 * 修改方案状态
	 * @param planIdArr
	 * @param operFlag
	 */
	public void modifyPlanStatus(String[] planIdArr, String operFlag);
	
	/**
	 * 设为默认方案
	 * @param planId
	 */
	public void modifyPlanDetault(String planId);
	
	/**
	 * 根据方案id获取方案信息
	 * @param planId
	 * @return Plan
	 */
	public Plan getPlanById(String planId);
	
	/**
	 * 方案列表分页查询
	 * @param page
	 * @param planQuery
	 * @param params
	 * @return
	 */
	public Page<Plan> searchPlanListByPage(Page<Plan> page, Plan planQuery, Map<String, Object> params);

	/**
	 * 应用关联方案页面 列表分页查询
	 * @param start
	 * @param limit
	 * @param appId
	 * @param plan
	 * @return
	 */
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
    
    /**
     * 根据应用包名获取所有关联的方案
     * @param appPkgName
     * @return
     */
    public Set<Plan> getPlanSetByApp(String appPkgName);

    /**
     * 根据planId 复制方案
     * @param planId
     * @throws Exception 
     */
	public String copy(String planId) throws Exception;
}
