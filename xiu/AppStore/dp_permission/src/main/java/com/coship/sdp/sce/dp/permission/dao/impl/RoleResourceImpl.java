package com.coship.sdp.sce.dp.permission.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.coship.sdp.dp.access.dao.impl.PageGenericDaoImpl;
import com.coship.sdp.sce.dp.permission.dao.RoleResourceDao;
import com.coship.sdp.sce.dp.permission.entity.RoleResource;

@Repository("roleResDao")
public class RoleResourceImpl extends PageGenericDaoImpl<RoleResource, Long> implements RoleResourceDao<RoleResource, Long> {

//	@Override
//	public void delete(RoleResource entity) {
//		// TODO Auto-generated method stub
//		Session session = this.getSession();
//		session.beginTransaction();
//		session.delete(entity);
//		session.getTransaction().commit();
//		
//	}

}
