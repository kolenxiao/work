package com.coship.sdp.sce.dp.plan.dao.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.plan.dao.PlanDao;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.plan.util.PlanUtil;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Page;

@Repository("planDao")
public class PlanDaoImpl extends GenericDaoImpl<Plan, String> implements PlanDao  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3219490768164373853L;

	@Override
	public Page<Plan> listForApp(int start, int limit,String appId, Plan queryPlan) {
		Page<Plan> objPage = new Page<Plan>();
		objPage.setPageSize(limit);
		objPage.setCurrentPage(start);
		
		Map<String,Object> objParam = new HashMap<String,Object>();
		
		StringBuffer objSb = new StringBuffer(256);
		objSb.append("select ott.* from t_plan ott where ott.c_status != - 1  ");
		 
		if( null != queryPlan){
			if(StringUtils.isNotBlank(queryPlan.getId())){
				objParam.put("ID", queryPlan.getId());
				objSb.append(" and ott.c_id=:ID");
			}				
			
	        if (StringUtils.isNotEmpty(queryPlan.getName())){
	        	objSb.append( " and ott.c_name like '");
	        	objSb.append(SqlUtil.getLikeSql(queryPlan.getName()));
	        	objSb.append("' escape '/' ");
	        }
	        
	        //当 status= -2时，则查询全部
			if( null != queryPlan.getStatus() &&  -2 != queryPlan.getStatus()){
				objParam.put("STATUS", queryPlan.getStatus());
				objSb.append(" and ott.c_status = :STATUS ");
			}else{
				objSb.append(" and ott.c_status != -1 ");
			} 
			
	        if(null != queryPlan.getStartTime()){
	        	objParam.put("START_TIME", queryPlan.getStartTime());
	        	objSb.append(" and ott.c_start_time <= :START_TIME");
	        }
	        if(null != queryPlan.getEndTime()){
	        	objParam.put("END_TIME", queryPlan.getEndTime());
	        	objSb.append(" and ott.c_end_time >= :END_TIME");
	        }
		}
		objSb.append(" order by ott.c_update_time desc ");
		
		List<String> lstPlanIds = new ArrayList<String>();
		List<Plan> lstPlan = new ArrayList<Plan>();
		Session session = this.getSession();
		List<Map<String,Object>> lstResult = PlanUtil.queryListBySql(session,  objSb.toString(), objParam, 
				objPage.getBeginCount(), objPage.getPageSize());
		
		SimpleDateFormat objSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
        for(Map<String,Object> objMap : lstResult){
        	Plan objPlan = new Plan();
        	objPlan.setId(String.valueOf(objMap.get("C_ID")));
        	objPlan.setName(String.valueOf(objMap.get("C_NAME")));
        	objPlan.setDescription(String.valueOf(objMap.get("C_DESCRIPTION")));
        	objPlan.setDefaulted(Boolean.valueOf(objMap.get("C_DEFAULTED")+""));
        	objPlan.setStatus(Integer.valueOf(objMap.get("C_STATUS")+""));
        	
        	Date objStartTime = null;
        	Date objEndTime = null;
        	try {
        		objStartTime = objSdf.parse(String.valueOf(objMap.get("C_START_TIME")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        	try {
				objEndTime = objSdf.parse(String.valueOf(objMap.get("C_END_TIME")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        	objPlan.setStartTime(objStartTime);
        	objPlan.setEndTime(objEndTime);
        	lstPlan.add(objPlan);
        	lstPlanIds.add(objPlan.getId());
        }
        objPage.setResultList(lstPlan);
        
        int iTotal = PlanUtil.queryCountBySql(session, objSb.toString(), objParam);
        objPage.setTotalCount(iTotal);
        
        if(lstPlanIds.size() > 0){
            //查询该应用与该方案 中间的 自定义类项情况
            Map<String,List<Map<String,String>>> objItemInfoMap = this.getItemInfo(appId,lstPlanIds);
            
            for(Plan objPlan : lstPlan){
            	objPlan.setItemInfo(objItemInfoMap.get(objPlan.getId()));
            }
        }
		return objPage;
	}

	
    private Map<String,List<Map<String,String>>> getItemInfo(String appId,List<String> lstPlanIds) {
    	Map<String,List<Map<String,String>>> objResulet= new HashMap<String,List<Map<String,String>>>();
    	Map<String,Object> objParam = new HashMap<String,Object>();
    	objParam.put("APP_ID", appId);
    	objParam.put("PLAN_IDS", lstPlanIds);
		StringBuffer objSb = new StringBuffer(628);
		objSb.append("select  t.plan_id, group_concat(concat(type_code, '<->',item_id, '<->', item_name, '<->', item_flag) order by type_code,item_name separator '>-<') as item_info ");
		objSb.append(" from (select  p.c_id as plan_id, i.c_id as item_id, i.c_name as item_name, i.c_parent_type_code as type_code, ");
		objSb.append(" (select  if(count(1)>0,1,0) from t_plan_item_app ia, t_dp_app_info dai where ia.c_app_pkg_name = dai.C_APP_FILE_PACKAGE ");
		objSb.append(" and ia.c_item_id = i.c_id and ia.C_PLAN_ID = p.c_id and dai.C_ID = :APP_ID) as item_flag ");
		objSb.append(" from t_item i, t_plan p where p.c_id in (:PLAN_IDS) and i.C_STATUS = 1) t group by t.plan_id ");
		Session session = this.getSession();
		List<Map<String,Object>> lstSqlData = PlanUtil.queryListBySql(session,  objSb.toString(), objParam);
		
		
		for(Map<String,Object> objMap : lstSqlData){
			List<Map<String,String>> lstSubResult= new ArrayList<Map<String,String>>();
			String strPlanId = String.valueOf(objMap.get("plan_id"));
			String strItemInfo = String.valueOf(objMap.get("item_info"));
			String[] arrItem = strItemInfo.split(">-<");
			for(String strItem : arrItem){
				String[] arrItemDetail = strItem.split("<->");
				Map<String,String> objItemMap = new HashMap<String,String>();
				objItemMap.put("codeType", arrItemDetail[0]);
				objItemMap.put("itemId", arrItemDetail[1]);
				objItemMap.put("itemName", arrItemDetail[2]);
				objItemMap.put("itemFlag", arrItemDetail[3]);
				lstSubResult.add(objItemMap);
			}
			objResulet.put(strPlanId, lstSubResult);
		}
		return objResulet;
	}

	@Override
    public Plan getDefaultPlan() {
        String hql = "select p from Plan p where p.defaulted = true and p.status=1 limit 1";

        Query query = this.getSession().createQuery(hql);

        Plan plan = (Plan) query.uniqueResult();

        if (plan == null) {
            throw new RuntimeException("没有设置默认方案！！！");
        }

        return plan;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Plan> getAllEnabledPlanList() {
        String hql = "select p from Plan p where p.status=1 order by sortNum";
        return this.getSession().createQuery(hql).list();
    }
    
	@Override
	public String copy(String planId) throws SQLException {
		Session session = this.getSession();
		String strUuid = UUID.randomUUID().toString();
		strUuid = strUuid.replaceAll("-", "");
		
		//调用存储过程  PRO_APPSTORE_COPY_PLAN
		String planHql = " PRO_APPSTORE_COPY_PLAN('"+strUuid+"','"+planId+"') ";
		return PlanUtil.callProcedure(session,planHql);
	}
}
