package com.coship.sdp.sce.dp.ap.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.ap.dao.AppThumbnailDao;
import com.coship.sdp.sce.dp.ap.entity.AppThumbnail;

@Repository("appThumbnailDao")
public class AppThumbnailDaoImpl extends GenericDaoImpl<AppThumbnail, String> implements AppThumbnailDao {

	private static final long serialVersionUID = -1279107564635629286L;



}
