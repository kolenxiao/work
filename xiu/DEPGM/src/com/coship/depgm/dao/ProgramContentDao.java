package com.coship.depgm.dao;

import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coship.depgm.action.Pager;
import com.coship.depgm.model.ProgramContent;

@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProgramContentDao extends GenericHibernateDao<ProgramContent, String> {
	public void getProgramContentList(Pager pager, String name, String typeId)
			throws ParseException {
		StringBuffer sb = new StringBuffer(
				"select p.*,t.name typeName from depg_program_content p left join depg_program_type t on p.typeId=t.id where 1=1");
		if (StringUtils.isNotBlank(name)) {
			sb.append(" and p.name like '%" + name + "%' ");
		}
		if (StringUtils.isNotBlank(typeId)) {
			sb.append(" and typeId = '" + typeId + "' ");
		}
		pager.setSql(getSession(), sb.toString(), ProgramContent.class);
	}
}
