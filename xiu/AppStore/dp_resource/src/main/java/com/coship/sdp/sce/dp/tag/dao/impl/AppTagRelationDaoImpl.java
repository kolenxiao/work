package com.coship.sdp.sce.dp.tag.dao.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.tag.dao.AppTagRelationDao;
import com.coship.sdp.sce.dp.tag.entity.AppTagRelation;

@Repository("appTagRelationDao")
public class AppTagRelationDaoImpl extends GenericDaoImpl<AppTagRelation, String> implements AppTagRelationDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public void deleteAppTagRelation(String sql) {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.executeUpdate();
	}

}
