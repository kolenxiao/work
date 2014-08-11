package com.coship.sdp.sce.dp.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.system.dao.SystemParamDao;
import com.coship.sdp.sce.dp.system.entity.SystemParam;

@Repository("systemParamDao")
public class SystemParamDaoImpl extends GenericDaoImpl<SystemParam, String> implements SystemParamDao {

	private static final long serialVersionUID = -8093973433260460578L;

}
