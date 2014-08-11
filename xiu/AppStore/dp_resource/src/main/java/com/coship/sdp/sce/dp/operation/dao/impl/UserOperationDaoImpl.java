package com.coship.sdp.sce.dp.operation.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.operation.dao.UserOperationDao;
import com.coship.sdp.sce.dp.operation.entity.UserOperation;

@Repository("userOperationDao")
public class UserOperationDaoImpl extends GenericDaoImpl<UserOperation, String>
		implements UserOperationDao
{
	private static final long serialVersionUID = -2281772096545103345L;


}
