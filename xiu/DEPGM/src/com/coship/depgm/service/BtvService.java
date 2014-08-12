package com.coship.depgm.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coship.core.dal.sync.CacheClusterSync;
import com.coship.core.dal.sync.SyncObjectList;
import com.coship.depgm.common.DepgmConfig;
import com.coship.depgm.model.ProgramContent;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class BtvService extends HibernateDaoSupport{
	protected static Logger logger = Logger.getLogger(BtvService.class);
	
	/**
	 * 计算当前的回看节目
	 */
	public void caculateAvailableBtv() {
		SyncObjectList syncList = new SyncObjectList();
		Date now = new Date();
		SQLQuery query = getSession().createSQLQuery("select distinct c.* from depg_program_content c " +
				"left join depg_program p on c.id=p.contentId where p.endTime <= ? and p.btvCaculated=0");
		query.setDate(0, now);
		query.setResultTransformer(Transformers.aliasToBean(ProgramContent.class));
		List<ProgramContent> contentList = (List<ProgramContent>) query.list();
		if (contentList.size() > 0) {
			logger.info("回看计算" + contentList.size() + "条数据");
		}
		for(ProgramContent content : contentList){
			content.setBtv(true);
			getSession().update(content);
			syncList.syncModifyEntity(content);
		}
		CacheClusterSync.sync(syncList);
		query = getSession().createSQLQuery("update depg_program set btvCaculated = 1 where endTime <= ? and btvCaculated = 0");
		query.setDate(0, now);
		query.executeUpdate();
	}
	
	/**
	 * 计算过期的回看节目
	 */
	public void caculateDisableBtv() {
		SQLQuery query = getSession().createSQLQuery("select distinct c.* from depg_program_content c " +
				"left join depg_program p on c.id=p.contentId where p.eventDate = ?");
		query.setDate(0, DateService.getDate(-DepgmConfig.getBtvDays()));
		query.setResultTransformer(Transformers.aliasToBean(ProgramContent.class));
		List<ProgramContent> disabledList = (List<ProgramContent>) query.list();
		
		query = getSession().createSQLQuery("select distinct c.* from depg_program_content c " +
				"left join depg_program p on c.id=p.contentId where p.eventDate <= ? and p.eventDate >= ?");
		query.setDate(0, DateService.getDate(-1));
		query.setDate(1, DateService.getDate(1 - DepgmConfig.getBtvDays()));
		List<ProgramContent> availableList = (List<ProgramContent>) query.list();
		Set<ProgramContent> availableSet = new HashSet<ProgramContent>();
		availableSet.addAll(availableList);
		
		SyncObjectList syncList = new SyncObjectList();
		for(ProgramContent content : disabledList){
			if(!availableSet.contains(content)){
				content.setBtv(false);
				getSession().update(content);
				syncList.syncModifyEntity(content);
			}
		}
		logger.info("过期回看计算" + syncList.getSyncList().size() + "条数据");
		CacheClusterSync.sync(syncList);
	}
}