package com.coship.depgm.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coship.depgm.action.Pager;
import com.coship.depgm.model.ProgramChapter;

@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProgramChapterDao extends GenericHibernateDao<ProgramChapter, String> {
	public void getChapterList(Pager pager,String contentId){
		StringBuffer sb = new StringBuffer("select c.*,t.name contentName from depg_program_chapter c " +
				"join depg_program_content t on t.id=c.contentId where 1=1 ");
		sb.append(" and contentId = '" + contentId + "' ");
		sb.append(" order by chapter asc");
		pager.setSql(getSession(), sb.toString(), ProgramChapter.class);
	}
}