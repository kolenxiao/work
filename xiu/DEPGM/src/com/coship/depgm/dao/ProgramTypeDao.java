package com.coship.depgm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coship.depgm.model.ProgramType;

@Repository
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class ProgramTypeDao extends GenericHibernateDao<ProgramType, String> {

	@SuppressWarnings("unchecked")
	public List<ProgramType> getProgramTypeList() {
		return getSession().createCriteria(ProgramType.class).list();
	}

}
