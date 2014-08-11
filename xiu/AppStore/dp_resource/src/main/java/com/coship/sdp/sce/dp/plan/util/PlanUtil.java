/*
 * 文件名称：PlanUtil.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-04-04
 *
 */
package com.coship.sdp.sce.dp.plan.util;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.SessionFactoryUtils;


public class PlanUtil {
	/**
	 * 根据sql和分页参数得到list<map>数据
	 *@param session session
	 *@param sql   原生sql
	 *@param paramsMap 参数map
	 *@param beginCount  page.getBeginCount();
	 *@param pageSize page.getPageSize();
	 * @return List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> queryListBySql(Session session,String sql,Map<String, Object> paramsMap,int beginCount,int pageSize) {
        SQLQuery objSQLQuery = session.createSQLQuery(sql);
        objSQLQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (paramsMap != null) {
            Set<String> keySet = paramsMap.keySet();
            for (String strKey : keySet){
            	if(paramsMap.get(strKey) instanceof List){
            		objSQLQuery.setParameterList(strKey, (List<?>)paramsMap.get(strKey));
            	}else{
            		objSQLQuery.setParameter(strKey, paramsMap.get(strKey));
            	}
            }
        }
        objSQLQuery.setFirstResult(beginCount);
        objSQLQuery.setMaxResults(pageSize);
        return objSQLQuery.list();
	}
	
	/**
	 * 根据sql得到list<map>数据
	 *@param session session
	 *@param sql   原生sql
	 *@param paramsMap 参数map
	 * @return List<Map<String,Object>>
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> queryListBySql(Session session,String sql,Map<String, Object> paramsMap) {
        SQLQuery objSQLQuery = session.createSQLQuery(sql);
        objSQLQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (paramsMap != null) {
            Set<String> keySet = paramsMap.keySet();
            for (String strKey : keySet){
            	if(paramsMap.get(strKey) instanceof List){
            		objSQLQuery.setParameterList(strKey, (List<?>)paramsMap.get(strKey));
            	}else{
            		objSQLQuery.setParameter(strKey, paramsMap.get(strKey));
            	}
            }
        }
        return objSQLQuery.list();
	}
	
	/**
	 * 根据sql得到list<map>数据
	 *@param session session
	 *@param sql   原生sql
	 *@param paramsMap 参数map
	 * @return BigInteger
	 */
	public static int queryCountBySql(Session session,String sql,Map<String, Object> paramsMap) {
        String countSql = "select count(1) from (" + sql + ") tmp ";
        SQLQuery objSQLQuery = session.createSQLQuery(countSql);
        if (paramsMap != null){
            Set<String> keySet = paramsMap.keySet();
            for (String strKey : keySet){
            	if(paramsMap.get(strKey) instanceof List){
            		objSQLQuery.setParameterList(strKey, (List<?>)paramsMap.get(strKey));
            	}else{
            		objSQLQuery.setParameter(strKey, paramsMap.get(strKey));
            	}
            }
        }
        BigInteger total = (BigInteger) objSQLQuery.uniqueResult();
        return total.intValue();
	}
	
	/**
	 * 执行 原生sql语句
	 *@param session session
	 *@param sql   原生sql
	 *@param paramsMap 参数map
	 * @return int
	 */
	public static int executeUpdate(Session session,String sql,Map<String, Object> paramsMap) {
        Query objQuery = session.createSQLQuery(sql);
        if (paramsMap != null) {
            Set<String> keySet = paramsMap.keySet();
            for (String strKey : keySet){
            	if(paramsMap.get(strKey) instanceof List){
            		objQuery.setParameterList(strKey, (List<?>)paramsMap.get(strKey));
            	}else{
            		objQuery.setParameter(strKey, paramsMap.get(strKey));
            	}
            }
        }
        return objQuery.executeUpdate();
	}
	
	/**
	 * 调用存储过程 
	 *@param session session
	 *@param sql   存储过程sql
	 * @throws SQLException 
	 */
	public static String callProcedure(Session session,String sql) throws SQLException {
		  Connection objConnection = null;
		  CallableStatement cstmt = null;
		try {
			objConnection = SessionFactoryUtils.getDataSource(session.getSessionFactory()).getConnection();
			  String   procedure = "{call  "+sql+"  }";       
			  cstmt = objConnection.prepareCall(procedure);       
			  cstmt.executeUpdate();       
		} catch (SQLException e) {
			e.printStackTrace();
			return "数据库执行出错";
		}finally{
			if( null != cstmt){
				cstmt.close();
			}
			if(null != objConnection){
				objConnection.close();
			}
		}
		return "success";
	}
      

}
