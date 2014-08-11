package com.coship.sdp.sce.dp.appstore.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.appstore.dao.UserAppDao;
import com.coship.sdp.sce.dp.appstore.entity.UserApp;

@Repository("userAppDao")
public class UserAppDaoImpl extends GenericDaoImpl<UserApp, String>
		implements UserAppDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 823883273806546069L;



}
