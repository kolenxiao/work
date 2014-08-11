package com.coship.sdp.sce.dp.permission.dao;

import java.io.Serializable;

import com.coship.sdp.dp.access.dao.IPageGenericDao;
import com.coship.sdp.sce.dp.permission.entity.Resource;

public interface ResourceDao<T, PK extends Serializable> extends IPageGenericDao<T, PK> {

//	public void addResource(Resource res, long parentId);
	
}
