package com.coship.sdp.sce.dp.tag.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.tag.dao.TagDao;
import com.coship.sdp.sce.dp.tag.entity.TagInfo;

@Repository("tagDao")
public class TagDaoImpl extends GenericDaoImpl<TagInfo, String> implements TagDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
