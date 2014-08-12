package com.coship.depgm.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coship.depgm.action.Pager;
import com.coship.depgm.model.Channel;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ChannelDao extends GenericHibernateDao<Channel, String> {

	public Channel getByServiceId(int serviceId){
		return (Channel) getSession().createCriteria(Channel.class)
				.add(Restrictions.eq("serviceID", serviceId)).uniqueResult();
	}
	
	/**
	 * 分页查询
	 * @param pager
	 * @param channel
	 */
	public void getListForPage(Pager pager, Channel channel){
		Criteria critaria = getSession().createCriteria(Channel.class);
		if(StringUtils.isNotBlank(channel.getName())){
			critaria.add(Restrictions.like("name", channel.getName(), MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(channel.getVideoType())){
			critaria.add(Restrictions.eq("videoType", channel.getVideoType()));
		}
		if(null != channel.getBtv()){
			critaria.add(Restrictions.eq("btv", channel.getBtv()));
		}
		critaria.addOrder(Order.asc("rank"));
		critaria.addOrder(Order.asc("name"));
		pager.setCriteriaDatas(critaria);
	}
	/**
	 * 不分页列出所有频道
	 */
	@SuppressWarnings("unchecked")
	public void getListNoPage(Pager pager, Channel channel){
		
		StringBuffer sb=new StringBuffer("select c.id,c.name,if((select count(1) from depg_program p where date(beginTime)=curdate() and p.channelId=c.id )>0,'1','0')as avaliableToday from depg_channel c where 1=1");
		if(StringUtils.isNotBlank(channel.getName())){
			sb.append(" and name like'%"+channel.getName()+"%' ");
		}
		sb.append(" order by rank asc,name asc");
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		query.setResultTransformer(Transformers.aliasToBean(Channel.class));
		List<Channel> datas=query.list();
		pager.setDatas(datas);
	}
}