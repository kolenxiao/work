package com.coship.depgm.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.core.dal.sync.CacheClusterSync;
import com.coship.core.dal.sync.SyncObjectList;
import com.coship.depgm.action.Pager;
import com.coship.depgm.dao.ProgramContentDao;
import com.coship.depgm.dao.ProgramDao;
import com.coship.depgm.exception.BusinessException;
import com.coship.depgm.model.Program;
import com.coship.depgm.model.ProgramChapter;
import com.coship.depgm.model.ProgramContent;

@Service
@Transactional
public class ProgramContentService {
	@Autowired
	private ProgramContentDao programContentDao;
	
	@Autowired
	private ProgramDao programDao;
	
	@Autowired
	private ProgramService programService;

	@Autowired
	private ProgramChapterService programChapterService;
	/**
	 * 通过id获取节目类容
	 * 
	 * @param id
	 * @return
	 */
	public ProgramContent get(String id) {
		return programContentDao.get(id);
	}

	/**
	 * 通过节目名称和分类获取节目信息
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ProgramContent getByNameAndType(String name, String typeId) {
		DetachedCriteria detachedCriteria = programContentDao.createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("name", name));
		detachedCriteria.add(Restrictions.eq("typeId", typeId));
		List<ProgramContent> list = programContentDao.findByCriteria(detachedCriteria);

		ProgramContent result = null;
		if (CollectionUtils.isNotEmpty(list)) {
			result = list.get(0);
		}
		return result;
	}
	
	/**
	 * 通过分类获取节目信息
	 * @param typeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProgramContent> getListByType(String typeId) {
		DetachedCriteria detachedCriteria = programContentDao.createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("typeId", typeId));
		List<ProgramContent> list = programContentDao.findByCriteria(detachedCriteria);

		if (null == list) {
			list = new ArrayList<ProgramContent>();
		}
		return list;
	}

	/**
	 * 新增
	 * 
	 * @param programContent
	 * @throws Exception 
	 */
	public void add(ProgramContent programContent) throws Exception {
		// 判断分类名是否有重复
		String name = programContent.getName();
		String typeId = programContent.getTypeId();
		ProgramContent exist = this.getByNameAndType(name, typeId);
		if (null != exist) {
			throw new BusinessException("节目【" + name + "】已存在！");
		}
		
		//插入数据库
		programContentDao.save(programContent);
		
		//同步
		CacheClusterSync.syncAddEntity(programContent);
	}

	/**
	 * 更新
	 * 
	 * @param programContent
	 * @throws Exception 
	 */
	public void update(ProgramContent programContent) throws Exception {
		String id = programContent.getId();
		String name = programContent.getName();
		String typeId = programContent.getTypeId();
		int chapter = programContent.getChapter();
		
		ProgramContent current = this.get(id);
		if (!StringUtils.equals(current.getName(), name)) {
			ProgramContent exist = this.getByNameAndType(name, typeId);
			if (null != exist) {
				throw new BusinessException("节目【" + name + "】已存在！");
			}
			current.setName(name);
		}
		if(!StringUtils.equals(current.getTypeId(), programContent.getTypeId())){
			current.setTypeId(programContent.getTypeId());
			//修改节目单的typeId
			List<Program> programList = programService.getListByContentId(id);
			if(CollectionUtils.isNotEmpty(programList)){
				for (Program program : programList) {
					program.setTypeId(programContent.getTypeId());
					programDao.update(program);
				}
			}
		}
		if(!StringUtils.equals(current.getDescription(), programContent.getDescription())){
			current.setDescription(programContent.getDescription());
		}
		if(StringUtils.isNotBlank(programContent.getPoster())){
			current.setPoster(programContent.getPoster());
		}
		if(StringUtils.isNotBlank(programContent.getHoriPoster())){
			current.setHoriPoster(programContent.getHoriPoster());
		}
		current.setChapter(chapter);
		programContentDao.update(current);
		
		//同步
		CacheClusterSync.syncModifyEntity(current);
	}

	
	/**
	 * 删除
	 * 
	 * @param programType
	 */
	public void delete(String id) {
		SyncObjectList syncList = new SyncObjectList();
		
		//删除节目子集
		List<ProgramChapter> chapterList = programChapterService.getListByContentId(id);
		for (ProgramChapter programChapter : chapterList) {
			syncList.syncDeleteEntity(programChapter.getId(), ProgramChapter.class);
		}
		
		//删除节目信息
		programContentDao.deleteByKey(id);
		syncList.syncDeleteEntity(id, ProgramContent.class);
		
		//同步
		CacheClusterSync.sync(syncList);
	}
	

	/**
	 * 分页查询
	 * @param pager
	 * @param queryProgramContent
	 * @throws ParseException
	 */
	public void queryPagerList(Pager pager,ProgramContent queryProgramContent) throws ParseException {
		programContentDao.getProgramContentList(pager, queryProgramContent.getName(), queryProgramContent.getTypeId());
	}

}
