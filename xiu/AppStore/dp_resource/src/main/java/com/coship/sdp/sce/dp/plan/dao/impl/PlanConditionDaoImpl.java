/*
 * 文件名称：ConditionDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-03-27
 *
 */
package com.coship.sdp.sce.dp.plan.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.plan.dao.PlanConditionDao;
import com.coship.sdp.sce.dp.plan.entity.Condition;
import com.coship.sdp.sce.dp.plan.entity.PlanCondition;
import com.coship.sdp.sce.dp.plan.util.PlanUtil;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Page;

/**
 * 应用数据访问实现类
 * @author 909194
 * @version [版本号, 2014-03-27]
 * @since [产品/模块版本]
 */
@Repository("planConditionDao")
public class PlanConditionDaoImpl extends GenericDaoImpl<PlanCondition, String>implements PlanConditionDao{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6299274775389899475L;

	@Override
	public Page<Condition> listForPlan(int start, int limit,String planId, Condition queryCondition){
		Page<Condition> objPage = new Page<Condition>();
		objPage.setPageSize(limit);
		objPage.setCurrentPage(start);
		
		Map<String,Object> objParam = new HashMap<String,Object>();
		objParam.put("PLAN_ID", planId);
		
		StringBuffer objSb = new StringBuffer(128);
		objSb.append("select tt.* from ( ");
		objSb.append(" select  c . *, if(pc.c_status = 1,1,0) as PLAN_FLAG, pc.C_ID as PC_ID,pc.c_update_time as sort_time ");
		objSb.append(" from  t_condition c, t_plan_condition pc where pc.c_condition_id = c.c_id ");
		objSb.append(" and pc.c_plan_id = :PLAN_ID  and c.c_status != -1  ");
		objSb.append(" union all select  c . *, 0 as PLAN_FLAG, '' as PC_ID,c.c_create_time as sort_time ");
		objSb.append(" from t_condition c where c.c_status != -1 and not exists  ");
		objSb.append(" (select  1  from  t_plan_condition pc where  pc.c_condition_id = c.c_id  ");
		objSb.append(" and pc.c_plan_id = :PLAN_ID and c.c_status != -1) ");
		objSb.append(") tt where 1 = 1");
 
		if( null != queryCondition){
			if(StringUtils.isNotBlank(queryCondition.getId())){
				objParam.put("ID", queryCondition.getId());
				objSb.append(" and tt.c_id=:ID");
			}				
	        if (StringUtils.isNotEmpty(queryCondition.getCode())){
	        	objSb.append( " and tt.c_code like '");
	        	objSb.append(SqlUtil.getLikeSql(queryCondition.getCode()));
	        	objSb.append("' escape '/' ");
	        }			
	        if (StringUtils.isNotEmpty(queryCondition.getName())){
	        	objSb.append( " and tt.c_name like '");
	        	objSb.append(SqlUtil.getLikeSql(queryCondition.getName()));
	        	objSb.append("' escape '/' ");
	        }
	        if (StringUtils.isNotEmpty(queryCondition.getValue())){
	        	objSb.append( " and tt.c_value like '");
	        	objSb.append(SqlUtil.getLikeSql(queryCondition.getValue()));
	        	objSb.append("' escape '/' ");
	        }	   
	        //当 status= -2时，则查询全部
			if( -2 !=queryCondition.getStatus()){
				objParam.put("STATUS", queryCondition.getStatus());
				objSb.append(" and tt.c_status = :STATUS ");
			} 
	        //当 searchFlag= -2时，则查询全部
			if( -2 !=queryCondition.getPlanSearchFlag()){
				objParam.put("PLAN_FLAG", queryCondition.getPlanSearchFlag());
				objSb.append(" and tt.PLAN_FLAG = :PLAN_FLAG ");
			} 
		}
		objSb.append(" order by tt.PLAN_FLAG desc,tt.c_status desc,tt.sort_time desc ");
		
		List<Condition> lstCondition = new ArrayList<Condition>();
		Session session = this.getSession();
		List<Map<String,Object>> lstResult = PlanUtil.queryListBySql(session,  objSb.toString(), objParam, 
				objPage.getBeginCount(), objPage.getPageSize());
		
        for(Map<String,Object> objMap : lstResult){
        	Condition objCondition = new Condition();
        	objCondition.setId(String.valueOf(objMap.get("C_ID")));
        	objCondition.setCode(String.valueOf(objMap.get("C_CODE")));
        	objCondition.setName(String.valueOf(objMap.get("C_NAME")));
        	objCondition.setValue(String.valueOf(objMap.get("C_VALUE")));
        	objCondition.setDescription(String.valueOf(objMap.get("C_DESCRIPTION")));
        	objCondition.setStatus(Integer.valueOf(objMap.get("C_STATUS")+""));
        	objCondition.setPlanFlag(Integer.valueOf(objMap.get("PLAN_FLAG")+""));
        	objCondition.setPcId(String.valueOf(objMap.get("PC_ID")));
        	lstCondition.add(objCondition);
        }
        objPage.setResultList(lstCondition);
        
        int iTotal = PlanUtil.queryCountBySql(session, objSb.toString(), objParam);
        objPage.setTotalCount(iTotal);
		return objPage;
	}
	
	@Override
	public List<String> listIds(String planId, List<String> conditionIds) {
		Map<String,Object> objParams = new HashMap<String,Object>();
		objParams.put("PLAN_ID", planId);
		String strHql = "select c_id  from t_plan_condition where c_status != -1 and c_plan_id = :PLAN_ID ";
		if( null != conditionIds && conditionIds.size() > 0){
			objParams.put("CONDITION_IDS", conditionIds);
			strHql += " and c_condition_id in (:CONDITION_IDS)";
		}
		
        Session session = getSession();
        List<Map<String,Object>> lstResult = PlanUtil.queryListBySql(session,  strHql, objParams);
        List<String> lstIds = new ArrayList<String>();
        for(Map<String,Object> objMap : lstResult){
        	lstIds.add(String.valueOf(objMap.get("C_ID")));
        }
        return lstIds;
	}


    @Override
    public List<PlanCondition> getPlanConditionList(String condition, String value) {
        String hql = "select p from PlanCondition p left join p.condition c "
                + "   where c.code=? and c.value=? and c.status=1 and p.status=1 "
                + "   order by p.sortNum, p.updateTime desc ";

        return this.query(hql, condition, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PlanCondition> getAllEnabledPlanConditionList() {
        String hql = "select new PlanCondition(p.id,c.code,c.value,c.status) from PlanCondition pc left join pc.plan p left join pc.condition c "
                + "   where pc.status = 1 and p.status = 1 and c.status >= 0 ";

        return this.getSession().createQuery(hql).list();
    }
}
