package com.coship.sdp.sce.dp.plan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.service.DpAppInfoService;
import com.coship.sdp.sce.dp.common.PlanConstants;
import com.coship.sdp.sce.dp.exception.BusinessException;
import com.coship.sdp.sce.dp.plan.dao.PlanDao;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.plan.service.ConditionService;
import com.coship.sdp.sce.dp.plan.service.PlanConditionService;
import com.coship.sdp.sce.dp.plan.service.PlanItemService;
import com.coship.sdp.sce.dp.plan.service.PlanService;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Page;

@Service("planService")
@Transactional
public class PlanServiceImpl implements PlanService {

	private static final long serialVersionUID = -8893232133227200800L;

	@Autowired
	private PlanDao planDao;
	@Autowired
	private PlanItemService planItemService;
	@Autowired
	private DpAppInfoService dpAppInfoService;
	@Autowired
	private ConditionService conditionService;
	
	@Autowired
	private PlanConditionService planConditionService;


	@Override
	public void createPlan(Plan plan) {
		//判断方案名是否存在
		Boolean bl = this.isPlanNameExist(plan.getName());
		if(bl){
			throw new BusinessException("方案名已存在！");
		}
		
		//持久化方案
		plan.setStatus(PlanConstants.PLAN_STATUS_DISABLED);
		plan.setDefaulted(Boolean.FALSE);
		
		Date sysdate = new Date();
		plan.setCreateTime(sysdate);
		plan.setUpdateTime(sysdate);
		plan.setSortNum(10000);
		planDao.save(plan);
	}

	@Override
	public void modifyPlan(Plan plan) {
		Plan oldPlan = this.getPlanById(plan.getId());
		
		if(!StringUtils.equals(plan.getName(), oldPlan.getName())){
			oldPlan.setName(plan.getName());
		}
		if(plan.getStartTime() != oldPlan.getStartTime()){
			oldPlan.setStartTime(plan.getStartTime());
		}
		if(plan.getEndTime() != oldPlan.getEndTime()){
			oldPlan.setEndTime(plan.getEndTime());
		}
		if(!plan.getRegulation().equals(oldPlan.getRegulation())){
			oldPlan.setRegulation(plan.getRegulation());
		}
		if(!plan.getDescription().equals(oldPlan.getDescription())){
			oldPlan.setDescription(plan.getDescription());
		}
		if(!plan.getSortNum().equals(oldPlan.getSortNum())){
			oldPlan.setSortNum(plan.getSortNum());
		}
		oldPlan.setUpdateTime(new Date());
		planDao.update(oldPlan);
	}
	
	@Override
	public void modifyPlanStatus(String[] planIdArr, String operFlag) {
		Integer newStatus = null;
		for (String planId : planIdArr) {
			Plan plan = this.getPlanById(planId);
			if("enabled".equals(operFlag)){
				//启用
				if(PlanConstants.PLAN_STATUS_DELETED == plan.getStatus()){
					throw new BusinessException("已删除的应用不能再启用");
				}
				newStatus = PlanConstants.PLAN_STATUS_ENABLED;
			}else if("disable".equals(operFlag)){
				//禁用
				if(PlanConstants.PLAN_STATUS_DELETED == plan.getStatus()){
					throw new BusinessException("已删除的应用不能再禁用");
				}
				newStatus = PlanConstants.PLAN_STATUS_DISABLED;
			}
			
			//修改状态
			plan.setStatus(newStatus);
			plan.setUpdateTime(new Date());
			planDao.update(plan);
		}
	}
	
	@Override
	public void modifyPlanDetault(String planId){
		//将以前的默认方案设为非默认
		String hql = "from Plan plan where defaulted = 1 ";
		List<Plan> planList = planDao.query(hql);
		for (Plan e : planList) {
			e.setDefaulted(Boolean.FALSE);
			planDao.update(e);
		}
		
		//将此方案设为默认
		Plan plan = this.getPlanById(planId);
		if(PlanConstants.PLAN_STATUS_ENABLED != plan.getStatus()){
			throw new BusinessException("方案没有启用，不能设为默认");
		}
		plan.setDefaulted(Boolean.TRUE);
		plan.setUpdateTime(new Date());
		planDao.update(plan);
	}

	@Override
	public void deletePlan(String[] planIdArr) {
		for (String planId : planIdArr) {
			//获取方案信息
			Plan plan = this.getPlanById(planId);
			if(plan.getStatus() == PlanConstants.PLAN_STATUS_ENABLED){
				throw new BusinessException("方案已启用，不能删除！");
			}else if(plan.getStatus() == PlanConstants.PLAN_STATUS_DELETED){
				throw new BusinessException("该方案已经被删除过！");
			}
			
			//删除方案关联的条件
			planConditionService.deletePlanCondition(planId,null);
			
			//删除方案与版块、专题和分类的关联关系
			planItemService.deletePlanItemByPlanId(planId);

			//只是修改状态，并不真正删除方案表
			plan.setStatus(PlanConstants.PLAN_STATUS_DELETED);
			plan.setUpdateTime(new Date());
			planDao.update(plan);
		}
	}

	@Override
	public Plan getPlanById(String planId) {
		Plan plan = planDao.get(planId);
		if (null == plan) {
			throw new BusinessException("id为" + planId + "的方案不存在!");
		}
		return plan;
	}
	
	@Override
	public Page<Plan> searchPlanListByPage(Page<Plan> page, Plan planQuery, Map<String, Object> params) {
		//构造查询条件
        StringBuffer sbf = new StringBuffer("from Plan where 1 = 1 ");

        String name = planQuery.getName();
        if(StringUtils.isNotBlank(name)){
        	sbf.append( " and name like '");
        	sbf.append(SqlUtil.getLikeSql(name));
        	sbf.append("' escape '/' ");
        }
        
        Integer status = planQuery.getStatus();
        if(-2 != status){
        	sbf.append(" and status = :status");
        	params.put("status", status);
        }else{
        	sbf.append(" and status != :status");
        	params.put("status", -1);
        }
        
        if(null != planQuery.getStartTime()){
        	sbf.append(" and startTime >= :startTime");
        	params.put("startTime", planQuery.getStartTime());
        }
        if(null != planQuery.getEndTime()){
        	sbf.append(" and endTime <= :endTime");
        	params.put("endTime", planQuery.getEndTime());
        }
        
        sbf.append(" order by sortNum, updateTime Desc");
        
        
        //查询
        String hql = sbf.toString();
        
		page = planDao.queryPage(page, hql, params);
		
		List<Plan> lstPlan = page.getResultList();
		List<String> lstPlanIds = new ArrayList<String>();
		for(Plan objPlan : lstPlan){
			lstPlanIds.add(objPlan.getId());
		}
		
		if(lstPlanIds.size() < 1){
			return page;
		}
		List<Map<String,Object>> lstConditionInfo =  conditionService.listConditionInfoByPlanIds(lstPlanIds);
		for(Plan objPlan : lstPlan){
			StringBuffer sbConditionInfo = new StringBuffer(128);
			for(Map<String,Object> objMap : lstConditionInfo){
				if(objPlan.getId().equals(objMap.get("C_ID"))){
					sbConditionInfo.append( objMap.get("C_NAME"));
					sbConditionInfo.append(" ( ").append( objMap.get("C_CODE")).append(" ) = ");
					sbConditionInfo.append(objMap.get("C_VALUE")).append("<->");
				}
			}
			objPlan.setConditionInfo(sbConditionInfo.toString());
		}
		return page;
	}
	
	/**
	 * 根据方案名获取方案信息
	 * @param name
	 * @return
	 */
	private Plan getPlanByName(String name){
		String hql = "from Plan where name =? and status != ?";
		Plan plan = null;
		if(StringUtils.isNotBlank(name)){
			List<Plan> lstPlan =  planDao.query(hql, name, PlanConstants.PLAN_STATUS_DELETED);
			if(null != lstPlan && lstPlan.size() > 0){
				plan = lstPlan.get(0);
			}
//			plan = planDao.findUnique(hql, name, PlanConstants.PLAN_STATUS_DELETED);
		}
		return plan;
	}
	
	/**
	 * 判断方案名是否存在
	 * @param name
	 * @return
	 */
	private Boolean isPlanNameExist(String name){
		Plan plan = this.getPlanByName(name);
		return !(null == plan);
	}

	@Override
	public Page<Plan> listForApp(int start, int limit,String appId, Plan plan) {
		return planDao.listForApp(start,limit,appId,plan);
	}

    @Override
    public Plan getDefaultPlan() {
        return planDao.getDefaultPlan();
    }

    @Override
    public List<Plan> getAllEnabledPlanList() {
        return planDao.getAllEnabledPlanList();
    }
    
    @Override
    public Set<Plan> getPlanSetByApp(String appPkgName){
    	Set<Plan> planSet = new HashSet<Plan>();
    	
    	//查询应用被分类关联的方案
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("appPkgName", appPkgName);

    	String hql = "from Plan as p where p.status != -1 "
    			                    + "and exists (select 1 From PlanItemApp as pia where pia.planId = p.id "
    			                                                                   + "and pia.appPkgName = :appPkgName)";
    	List<Plan> planList = planDao.query(hql, params);
    	planSet.addAll(planList);
    	
    	//查询应用被专题关联的方案
    	params.put("itemType", PlanConstants.ITEM_TYPE_SUBJECT);
    	hql = "from Plan as p where p.status != -1 "
    			                  + "and exists (select 1 From PlanItem as pi where pi.planId = p.id "
    			                                                             + "and pi.itemType = :itemType "
    			                                                             + "and exists (select 1 From SubjectAppinfoRelation as sar where sar.appSubjectType.id = pi.itemId "
    			                                                                                                                       + "and sar.appInfo.appFilePackage = :appPkgName"
    			                                                                         + ")"
    			                              + ")";
    	planList = planDao.query(hql, params);
    	planSet.addAll(planList);

    	//查询应用被精选页关联的方案
    	params.put("itemType", PlanConstants.ITEM_TYPE_PANEL);
    	hql = "from Plan as p where p.status != -1 "
    			                  + "and exists (select 1 From PlanItem as pi where pi.planId = p.id "
    			                                                             + "and pi.itemType = :itemType "
    			                                                             + "and exists (select 1 From AppRecommendPanelItem as api where api.appRecommendPanel.id = pi.itemId "
    			                                                                                                                      + "and ((api.type = 1 and api.typeValue = :appPkgName) or "
    			                                                                                                                           + "(api.type = 2 and exists (select 1 From SubjectAppinfoRelation as sar where sar.appSubjectType.id = api.typeValue "
    			                                                                                                                                                                                                   + "and sar.appInfo.appFilePackage = :appPkgName))"
    			                                                                                                                           + ")"
    			                                                                         + ")"
    			                              + ")";
    	planList = planDao.query(hql, params);
    	planSet.addAll(planList);
    	
    	
    	return planSet;
    }

	@Override
	public String copy(String planId) throws Exception {
		Plan plan = this.getPlanById(planId);
		String strCopyName = plan.getName()+"_复制方案";
		//检查名字是否存在
		Boolean bl = this.isPlanNameExist(strCopyName);
		if(bl){
			return "方案名【"+strCopyName+"】已存在！";
		}
		return planDao.copy(planId);
	}
}
