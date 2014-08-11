/*
 * 文件名称：ItemDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-04-01
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
import com.coship.sdp.sce.dp.plan.dao.ItemDao;
import com.coship.sdp.sce.dp.plan.entity.Item;
import com.coship.sdp.sce.dp.plan.util.PlanUtil;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Page;

/**
 * 应用数据访问实现类
 * @author 909194
 * @version [版本号, 2014-04-01]
 * @since [产品/模块版本]
 */
@Repository("itemDao")
public class ItemDaoImpl extends GenericDaoImpl<Item, String>implements ItemDao{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5065257181267222461L;
	
	@Override
	public Page<Item> list(int start, int limit,Item queryItem) {
		Page<Item> objPage = new Page<Item>();
		objPage.setPageSize(limit);
		objPage.setCurrentPage(start);
		
		StringBuffer objSb = new StringBuffer(128);
		objSb.append("select  i.*,  (select  count(1) from  t_plan_item pi   ");
		objSb.append(" where pi.C_ITEM_ID = i.c_id) as APP_FLAG from t_item i where 1 = 1");
		Map<String,Object> objParam = new HashMap<String,Object>();
		if( null != queryItem){
			if(StringUtils.isNotBlank(queryItem.getId())){
				objParam.put("ID", queryItem.getId());
				objSb.append(" and i.c_id=:ID");
			}	
			if( -2 !=queryItem.getStatus()){
				objParam.put("STATUS", queryItem.getStatus());
				objSb.append(" and i.c_status=:STATUS");
			}else{
				//当 status= -2时，则查询全部(不包括"已删除")
				objSb.append(" and i.c_status != -1 ");
			}	
			//当 status= -2时，则查询全部
			if( -2 !=queryItem.getItemType()){
				objParam.put("ITEM_TYPE", queryItem.getItemType());
				objSb.append(" and i.c_item_type=:ITEM_TYPE");
			}	
		
	        if (StringUtils.isNotEmpty(queryItem.getName())){
	        	objSb.append( " and i.c_name like '");
	        	objSb.append(SqlUtil.getLikeSql(queryItem.getName()));
	        	objSb.append("' escape '/' ");
	        }       
		}
		objSb.append(" order by i.c_update_time desc");
		
		
		List<Item> lstItem = new ArrayList<Item>();
		Session session = this.getSession();
		List<Map<String,Object>> lstResult = PlanUtil.queryListBySql(session,  objSb.toString(), objParam, 
				objPage.getBeginCount(), objPage.getPageSize());
		
        for(Map<String,Object> objMap : lstResult){
        	Item objItem = new Item();
        	objItem.setId(String.valueOf(objMap.get("C_ID")));
        	objItem.setName(String.valueOf(objMap.get("C_NAME")));
        	objItem.setItemType(Integer.valueOf(objMap.get("C_ITEM_TYPE")+""));
        	objItem.setParentTypeCode(String.valueOf(objMap.get("C_PARENT_TYPE_CODE")));
        	objItem.setDescription(String.valueOf(objMap.get("C_DESCRIPTION")));
        	objItem.setStatus(Integer.valueOf(objMap.get("C_STATUS")+""));
        	int iAppFlag = Integer.valueOf(objMap.get("APP_FLAG")+"");
        	if(iAppFlag > 0){
        		objItem.setAppFlag(1);
        	}
        	
        	
        	lstItem.add(objItem);
        }
        objPage.setResultList(lstItem);
        
        int iTotal = PlanUtil.queryCountBySql(session, objSb.toString(), objParam);
        objPage.setTotalCount(iTotal);
		return objPage;
	}
	

	@Override
	public void delete(List<String> lstIds) {
		Map<String,Object> objParam = new HashMap<String,Object>();
		objParam.put("IDS", lstIds);
		String strHql = "update Item i set i.status = -1,i.updateTime=now()  where i.status = 0 and i.id in (:IDS)"
				+ "and not exists(select 1 from PlanItemApp a,DpAppInfo d "
				+ "where a.appPkgName = d.appFilePackage and d.appStatus = 1004 and a.itemId = i.id )";
		this.executeUpdate(strHql, objParam);
	}

	@Override
	public void enable(List<String> lstIds) {
		Map<String,Object> objParam = new HashMap<String,Object>();
		objParam.put("IDS", lstIds);
		String strHql = "update Item set status = 1,updateTime=now()  where status = 0 and id in (:IDS)";
		this.executeUpdate(strHql, objParam);
	}

	@Override
	public void disable(List<String> lstIds) {
		Map<String,Object> objParam = new HashMap<String,Object>();
		objParam.put("IDS", lstIds);
		String strHql = "update Item i set i.status = 0,i.updateTime=now() where i.status = 1 and i.id in (:IDS) "
				+ "and not exists(select 1 from PlanItemApp a,DpAppInfo d "
				+ "where a.appPkgName = d.appFilePackage and d.appStatus = 1004 and a.itemId = i.id )";
		this.executeUpdate(strHql, objParam);
	}

}
