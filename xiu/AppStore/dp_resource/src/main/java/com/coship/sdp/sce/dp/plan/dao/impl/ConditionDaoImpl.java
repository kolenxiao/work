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
import com.coship.sdp.sce.dp.plan.dao.ConditionDao;
import com.coship.sdp.sce.dp.plan.entity.Condition;
import com.coship.sdp.sce.dp.plan.util.PlanUtil;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Page;

/**
 * 应用数据访问实现类
 * @author 909194
 * @version [版本号, 2014-03-27]
 * @since [产品/模块版本]
 */
@Repository("conditionDao")
public class ConditionDaoImpl extends GenericDaoImpl<Condition, String>implements ConditionDao{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4260784201984404620L;
	
	@Override
	public Page<Condition> list(int start, int limit,Condition queryCondition) {
		Page<Condition> objPage = new Page<Condition>();
		objPage.setPageSize(limit);
		objPage.setCurrentPage(start);
		
		StringBuffer objSb = new StringBuffer(128);
		objSb.append("select tt.* from ( ");
		objSb.append(" select c.*, (select  count(1) from  t_plan_condition pc  ");
		objSb.append("  where pc.C_CONDITION_ID = c.c_id and pc.C_STATUS = 1) as PLAN_FLAG ");
		objSb.append("  from t_condition c  ");
		objSb.append(") tt where 1 = 1");
		Map<String,Object> objParam = new HashMap<String,Object>();
		if( null != queryCondition){
			if(StringUtils.isNotBlank(queryCondition.getId())){
				objParam.put("ID", queryCondition.getId());
				objSb.append(" and tt.c_id=:ID");
			}	
			if( -2 !=queryCondition.getStatus()){
				objParam.put("STATUS", queryCondition.getStatus());
				objSb.append(" and tt.c_status=:STATUS");
			}else{
				//当 status= -2时，则查询全部(不包括"已删除")
				objSb.append(" and tt.c_status != -1 ");
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
	        int iPlanFlag = queryCondition.getPlanSearchFlag();
			if( -2 != iPlanFlag){
				if(iPlanFlag == 0){
					objSb.append(" and tt.PLAN_FLAG = 0 ");
				}else{
					objSb.append(" and tt.PLAN_FLAG > 0 ");
				}
			} 
		}
		objSb.append(" order by tt.c_update_time desc");
		
		
		List<Condition> lstCondition = new ArrayList<Condition>();
		List<String> lstConditionIds = new ArrayList<String>();
		Session session = this.getSession();
		List<Map<String,Object>> lstResult = PlanUtil.queryListBySql(session,  objSb.toString(), objParam, 
				objPage.getBeginCount(), objPage.getPageSize());
		
        for(Map<String,Object> objMap : lstResult){
        	Condition objCondition = new Condition();
        	String strId = String.valueOf(objMap.get("C_ID"));
        	lstConditionIds.add(strId);
        	objCondition.setId(strId);
        	objCondition.setCode(String.valueOf(objMap.get("C_CODE")));
        	objCondition.setName(String.valueOf(objMap.get("C_NAME")));
        	objCondition.setValue(String.valueOf(objMap.get("C_VALUE")));
        	objCondition.setDescription(String.valueOf(objMap.get("C_DESCRIPTION")));
        	objCondition.setStatus(Integer.valueOf(objMap.get("C_STATUS")+""));
        	
        	int iPlanFlag = Integer.valueOf(objMap.get("PLAN_FLAG")+"");
        	if(iPlanFlag > 0){
        		objCondition.setPlanFlag(1);
        	}
        	lstCondition.add(objCondition);
        }
        objPage.setResultList(lstCondition);
        
        int iTotal = PlanUtil.queryCountBySql(session, objSb.toString(), objParam);
        objPage.setTotalCount(iTotal);
        
        if(lstConditionIds.size() < 1){
        	return objPage;
        }
        
        //加载 planInfo信息
        Map<String,String> objPlanInfoMap = this.getPlanInfoForCondition(lstConditionIds);
        for( Condition objCondition : lstCondition){
        	objCondition.setPlanInfo(objPlanInfoMap.get(objCondition.getId()));
        }
        
		return objPage;
	}

	private Map<String, String> getPlanInfoForCondition(List<String> conditionIds) {
		Map<String, String> objReturnMap = new HashMap<String, String>();
		Map<String,Object> objParam = new HashMap<String,Object>();
		objParam.put("CONDITION_IDS",conditionIds);
		StringBuffer objSb = new StringBuffer(512);
		objSb.append("select  t.c_id,group_concat(t.c_name order by t.c_sort_num separator '<->') as plan_info ");
		objSb.append(" from (select p.c_name, c.c_id,p.c_sort_num from t_plan p, t_plan_condition pc, t_condition c ");
		objSb.append(" where c.c_id = pc.c_condition_id  and p.c_id = pc.c_plan_id ");
		objSb.append(" and pc.c_condition_id = c.c_id  and pc.c_status = 1  and p.c_status = 1 and c.c_id in (:CONDITION_IDS)) t group by t.c_id ");
		Session session = this.getSession();
		List<Map<String,Object>> lstResult = PlanUtil.queryListBySql(session,  objSb.toString(), objParam);
		for(Map<String, Object> objMap : lstResult){
			String strConditionId = String.valueOf(objMap.get("c_id"));
			objReturnMap.put(strConditionId, String.valueOf(objMap.get("plan_info")));
		}
		return objReturnMap;
	}

	@Override
	public void delete(List<String> lstIds) {
		Map<String,Object> objParam = new HashMap<String,Object>();
		objParam.put("IDS", lstIds);
		String strHql = "update Condition c set status = -1,updateTime=now()  where c.status = 0 and c.id in (:IDS) "
				+ " and not exists (select 1 from PlanCondition pc where pc.condition.id = c.id and pc.status = 1)";
		this.executeUpdate(strHql, objParam);
	}

	@Override
	public void enable(List<String> lstIds) {
		Map<String,Object> objParam = new HashMap<String,Object>();
		objParam.put("IDS", lstIds);
		String strHql = "update Condition set status = 1,updateTime=now()   where status = 0 and id in (:IDS)";
		this.executeUpdate(strHql, objParam);
	}

	@Override
	public void disable(List<String> lstIds) {
		Map<String,Object> objParam = new HashMap<String,Object>();
		objParam.put("IDS", lstIds);
		String strHql = "update Condition c set status = 0,updateTime=now()  where c.status = 1 and c.id in (:IDS) ";
		this.executeUpdate(strHql, objParam);
	}

	@Override
	public List<Map<String, Object>> listConditionInfoByPlanIds(List<String> planIds) {
		Map<String,Object> objParam = new HashMap<String,Object>();
		objParam.put("PLAN_IDS", planIds);
		String strHql = "select  p.c_id, c.c_name, c.c_code, c.c_value from t_plan_condition pc, t_plan p, t_condition c "
				+ "where  pc.c_plan_id = p.c_id and pc.c_condition_id = c.c_id  and pc.c_status = 1 and c.c_status = 1 and p.c_id in (:PLAN_IDS) ";
		Session session = this.getSession();
		return PlanUtil.queryListBySql(session,  strHql, objParam);
	}
}
