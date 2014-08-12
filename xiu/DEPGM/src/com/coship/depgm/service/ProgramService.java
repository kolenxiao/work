package com.coship.depgm.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coship.core.dal.sync.CacheClusterSync;
import com.coship.depgm.action.Pager;
import com.coship.depgm.common.DepgmConfig;
import com.coship.depgm.dao.ProgramDao;
import com.coship.depgm.dao.ProgramTypeDao;
import com.coship.depgm.model.Program;
import com.coship.depgm.model.ProgramType;

@Component
public class ProgramService {
	protected static final Logger logger = LoggerFactory.getLogger(ProgramService.class);
	
	@Autowired
	private ProgramTypeDao programTypeDao;
	
	@Autowired
	private ProgramDao programDao;
	
	public List<ProgramType> getProgramTypeList(){
		return programTypeDao.getProgramTypeList();
	}
	
	public void getProgramList(Pager pager, String channelId, String date) throws ParseException{
		programDao.getProgramList(pager, channelId, date);
	}
	
	public boolean updateProgramContent(String id,String content) throws Exception{
		boolean boo = programDao.updateProgramContent(id, content);
		//同步
		CacheClusterSync.syncModifyEntity(programDao.get(id));
		return boo;
	}
	
	
	/**
	 * 通过节目获取节目单列表
	 * @param contentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Program> getListByContentId(String contentId) {
		DetachedCriteria detachedCriteria = programDao.createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("contentId", contentId));
		List<Program> list = programDao.findByCriteria(detachedCriteria);

		if (null == list) {
			list = new ArrayList<Program>();
		}
		return list;
	}
	
	public void clearProgram() throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, DepgmConfig.getBtvDays());
		calendar = DateUtils.truncate(calendar, Calendar.DATE);
		int count = programDao.clearProgram(calendar.getTime());
		logger.info("清理" + DepgmConfig.getBtvDays() + "天前节目单" + count + "条");
	}
}