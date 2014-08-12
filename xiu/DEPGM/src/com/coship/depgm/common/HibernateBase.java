package com.coship.depgm.common;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateBase extends HibernateDaoSupport{
	public SQLQuery sql(String sql){
		return getSession().createSQLQuery(sql);
	}
	
	public int sqlUpdate(String sql){
		return sql(sql).executeUpdate();
	}
	
	public List<?> sqlList(String sql){
		return sql(sql).list();
	}
	
	public void save(Object entity){
		getSession().save(entity);
	}
	
	public void update(Object entity){
		getSession().update(entity);
	}

	public void delete(Object entity){
		getSession().delete(entity);
	}
	
	public CriteriaWrapper from(Class<?> clazz){
		return new CriteriaWrapper(getSession().createCriteria(clazz));
	}
	
	public class CriteriaWrapper{
		private Criteria criteria;
		
		public CriteriaWrapper(Criteria criteria){
			this.criteria = criteria;
		}
		
		public CriteriaWrapper eq(String name, Object value){
			criteria.add(Restrictions.eq(name, value));
			return this;
		}
		
		public CriteriaWrapper lt(String name, Object value){
			criteria.add(Restrictions.lt(name, value));
			return this;
		}

		public CriteriaWrapper le(String name, Object value){
			criteria.add(Restrictions.le(name, value));
			return this;
		}

		public CriteriaWrapper gt(String name, Object value){
			criteria.add(Restrictions.gt(name, value));
			return this;
		}

		public CriteriaWrapper ge(String name, Object value){
			criteria.add(Restrictions.ge(name, value));
			return this;
		}

		public CriteriaWrapper notNull(String name){
			criteria.add(Restrictions.isNotNull(name));
			return this;
		}

		public CriteriaWrapper notEmpty(String name){
			criteria.add(Restrictions.isNotEmpty(name));
			return this;
		}
		
		@SuppressWarnings("rawtypes")
		public List list(){
			return criteria.list();
		}
		
		public Object unique(){
			return criteria.uniqueResult();
		}

		public Criteria raw() {
			return criteria;
		}
	}
}