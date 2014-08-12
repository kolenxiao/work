package com.coship.depgm.action;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

public class Pager implements Serializable {
	private static final long serialVersionUID = 4897385194437525484L;

	private int page = 1;
	private int max = 20;
	private int first;
	private int total;
	private String sort;
	private String order;
	private List<?> datas;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getFirst() {
		return first == 0 ? (page - 1) * max : first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setSql(Session session, String sql, Class<?> entityClass) {
		String countSql = "select count(1) from (" + sql + ") t";
		BigInteger total = (BigInteger) session.createSQLQuery(countSql).uniqueResult();
		this.total = total.intValue();
		SQLQuery query = session.createSQLQuery(sql);
		query.setFirstResult(getFirst()).setMaxResults(max);
		query.setResultTransformer(Transformers.aliasToBean(entityClass));
		this.datas = query.list();
	}
	
	public void setCriteriaDatas(Criteria criteria) {
		long total=(Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
		this.total = (int)total;
		criteria.setProjection(null); 		
		if (!StringUtils.isBlank(sort)) {
			if ("desc".equals(order)) {
				criteria.addOrder(Order.desc(sort));
			} else {
				criteria.addOrder(Order.asc(sort));
			}
		}
		criteria.setFirstResult(getFirst()).setMaxResults(max);
		this.datas = criteria.list();
	}
}