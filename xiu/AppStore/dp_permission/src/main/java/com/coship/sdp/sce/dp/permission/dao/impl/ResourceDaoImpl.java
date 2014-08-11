package com.coship.sdp.sce.dp.permission.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.coship.sdp.dp.access.dao.impl.PageGenericDaoImpl;
import com.coship.sdp.sce.dp.permission.dao.ResourceDao;
import com.coship.sdp.sce.dp.permission.entity.Resource;

@Repository("resDao")
public class ResourceDaoImpl extends PageGenericDaoImpl<Resource, Long> implements
		ResourceDao<Resource, Long> {

//	@Override
//	public void addResource(Resource res, long parentId) {
//		// TODO Auto-generated method stub
//	
//		if(parentId < 1) {
//			res.setLevel(1);
//		}
// 		this.save(res);
//		if (parentId > 0) {
//			Session session = null;
//			Transaction tx = null;
//			try {
//				session = this.getSession();
//				tx = session.getTransaction();
//				tx.begin();
//				Query q = session
//						.createSQLQuery("update t_resource set resource_parent_id = "
//								+ parentId + " where id=" + res.getId());
//				q.executeUpdate();
//			} catch (HibernateException e) {
//				if (tx != null) {
//					tx.rollback();
//				}
//				throw e;
//			}
//		}
//	}

}
