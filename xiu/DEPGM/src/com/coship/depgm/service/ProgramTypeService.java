package com.coship.depgm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.core.dal.sync.CacheClusterSync;
import com.coship.depgm.common.UID;
import com.coship.depgm.dao.ProgramTypeDao;
import com.coship.depgm.exception.BusinessException;
import com.coship.depgm.model.ProgramContent;
import com.coship.depgm.model.ProgramType;

@Service
@Transactional
public class ProgramTypeService {
	@Autowired
	private ProgramTypeDao programTypeDao;
	
	@Autowired
	private ProgramContentService programContentService;

	/**
	 * 通过id获取节目分类信息
	 * 
	 * @param id
	 * @return
	 */
	public ProgramType get(String id) {
		ProgramType programType = programTypeDao.get(id);
		if (null == programType) {
			throw new BusinessException("节目分类id【" + id + "】不存在！");
		}
		return programType;
	}

	/**
	 * 通过分类名称获取节目分类信息
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ProgramType getByName(String name) {
		DetachedCriteria detachedCriteria = programTypeDao.createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("name", name));
		List<ProgramType> list = programTypeDao.findByCriteria(detachedCriteria);

		ProgramType result = null;
		if (CollectionUtils.isNotEmpty(list)) {
			result = list.get(0);
		}
		return result;
	}

	public void add(String name, boolean visible, int rank, String tvSouTypeId) throws Exception {
		ProgramType programType = new ProgramType();
		programType.setId(UID.create());
		programType.setName(name);
		programType.setVisible(visible);
		programType.setRank(rank);
		programType.setTvSouTypeId(tvSouTypeId);
		
		programTypeDao.save(programType);
		CacheClusterSync.syncAddEntity(programType);
	}

	public void update(String id, String name, boolean visible, int rank, String tvSouTypeId) throws Exception {
		ProgramType programType = new ProgramType();
		programType.setId(id);
		programType.setName(name);
		programType.setVisible(visible);
		programType.setRank(rank);
		programType.setTvSouTypeId(tvSouTypeId);
		
		programTypeDao.update(programType);
		CacheClusterSync.syncModifyEntity(programType);
	}

	public void delete(String id) throws Exception {
		List<ProgramContent> contentList = programContentService.getListByType(id);
		if(contentList.size() > 0){
			throw new BusinessException("该分类下还有节目关联，不能删除！");
		}
		programTypeDao.deleteByKey(id);
		CacheClusterSync.syncDeleteEntity(id, ProgramType.class);
	}

	public List<ProgramType> queryList() {
		String hql = "from ProgramType order by rank";
		List<ProgramType> list = programTypeDao.find(hql);
		if (null == list) {
			list = new ArrayList<ProgramType>();
		}
		return list;
	}

	public List<ProgramType> queryVisibleList() {
		String hql = "from ProgramType where visible=1 order by rank";
		List<ProgramType> list = programTypeDao.find(hql);
		if (null == list) {
			list = new ArrayList<ProgramType>();
		}
		return list;
	}
}