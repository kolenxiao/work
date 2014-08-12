package com.coship.depgm.dao;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coship.depgm.action.Pager;
import com.coship.depgm.model.Program;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProgramDao extends GenericHibernateDao<Program, String> {

	public void getProgramList(Pager pager, String channelId, String eventDate) throws ParseException {
		StringBuffer sb = new StringBuffer("select p.*,c.name contentName,t.name typeName from depg_program p left join depg_program_content c " +
				"on c.id=p.contentId left join depg_program_type t on t.id=p.typeId where 1=1 ");
		if (eventDate != null) {
			sb.append(" and eventDate = '" + eventDate+"'");
		}
		if (StringUtils.isNotBlank(channelId)) {
			sb.append(" and channelId = '" + channelId + "' ");
		}
		sb.append(" order by beginTime asc");
		pager.setSql(getSession(), sb.toString(), Program.class);
	}

	public boolean updateProgramContent(String programId, String contentId) {
		String sql = "update depg_program set contentId='" + contentId
				+ "',typeId=(select typeId from depg_program_content where id='"+contentId+"') where id='" + programId + "'";
		SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
		return sqlQuery.executeUpdate() > 0;
	}
	
	public int clearProgram(Date date) {
		return getSession().createSQLQuery("delete from depg_program where eventDate<=?").setDate(0, date).executeUpdate();
	}
}
