package com.coship.sdp.sce.dp.ap.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.ap.dao.HandAppRelateDao;
import com.coship.sdp.sce.dp.ap.entity.HandAppRelate;

@Repository("handAppRelateDao")
public class HandAppRelateDaoImpl extends GenericDaoImpl<HandAppRelate, String>
		implements HandAppRelateDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7182976841272809315L;

}
