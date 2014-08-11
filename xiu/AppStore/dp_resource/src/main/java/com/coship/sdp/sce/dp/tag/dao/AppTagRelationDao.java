package com.coship.sdp.sce.dp.tag.dao;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.tag.entity.AppTagRelation;

public interface AppTagRelationDao extends IGenericDao<AppTagRelation, String> {
	
	public void deleteAppTagRelation(String sql);

}
