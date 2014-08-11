package com.coship.sdp.sce.dp.permission.dao.impl;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.coship.sdp.dp.access.dao.impl.PageGenericDaoImpl;
import com.coship.sdp.sce.dp.permission.dao.RoleDao;
import com.coship.sdp.sce.dp.permission.entity.Role;

@Repository("roleDao")
public class RoleDaoImpl extends PageGenericDaoImpl<Role, Long> implements RoleDao<Role, Long>{

//	@Override
//	public void update(Role entity) {
//		Session session = this.getSession();
//		org.hibernate.Transaction tran = session.getTransaction();
//		tran.begin();
//		session.update(entity);
//		session.flush();
//		//tran.commit();
//		//super.update(entity);
//	}

}
