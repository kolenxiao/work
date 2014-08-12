package com.coship.depgm.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.core.dal.sync.CacheClusterSync;
import com.coship.core.dal.sync.SyncObjectList;
import com.coship.depgm.action.Pager;
import com.coship.depgm.common.UID;
import com.coship.depgm.dao.ProgramChapterDao;
import com.coship.depgm.exception.BusinessException;
import com.coship.depgm.model.ProgramChapter;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class ProgramChapterService {
	@Autowired
	private ProgramChapterDao programChapterDao;

	public void queryChapterPagerList(Pager pager, String contentId)
			throws Exception {
		programChapterDao.getChapterList(pager, contentId);
	}

	/**
	 * 根据Id删除节目下面的剧集
	 */
	public void deleteChapter(String chapterId) {
		programChapterDao.deleteByKey(chapterId);
	}

	/**
	 * 新增剧集
	 * 
	 * @param programContent
	 */
	public void addChapter(ProgramChapter programChapter) {
		// 判断剧集是否有重复
		int chapterNo = programChapter.getChapter();
		String contentId = programChapter.getContentId();

		DetachedCriteria detachedCriteria = programChapterDao
				.createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("chapter", chapterNo));
		detachedCriteria.add(Restrictions.eq("contentId", contentId));
		List<ProgramChapter> list = programChapterDao.findByCriteria(detachedCriteria);
		if (list.size() > 0) {
			throw new BusinessException("第【" + chapterNo + "】集已存在！");
		}
		// 生成id
		programChapter.setId(UID.create());

		// 插入数据库
		programChapterDao.save(programChapter);

		// 同步
		SyncObjectList syncList = new SyncObjectList();
		syncList.syncAddEntity(programChapter);
		CacheClusterSync.sync(syncList);
	}

	/**
	 * 更新剧集
	 * 
	 * @param programContent
	 */
	public void updateChapter(ProgramChapter programChapter) {
		// 判断剧集是否有重复
		int chapterNo = programChapter.getChapter();
		String contentId = programChapter.getContentId();

		ProgramChapter chapterIndb = programChapterDao.get(programChapter
				.getId());
		if (chapterIndb.getChapter() != programChapter.getChapter()) {
			DetachedCriteria detachedCriteria = programChapterDao
					.createDetachedCriteria();
			detachedCriteria.add(Restrictions.eq("chapter", chapterNo));
			detachedCriteria.add(Restrictions.eq("contentId", contentId));
			List<ProgramChapter> list = programChapterDao
					.findByCriteria(detachedCriteria);
			if (list.size() > 0) {
				throw new BusinessException("第【" + chapterNo + "】集已存在！");
			}
		}
		chapterIndb.setChapter(programChapter.getChapter());
		chapterIndb.setPoster(programChapter.getPoster());
		// 插入数据库
		programChapterDao.update(chapterIndb);

		// 同步
		SyncObjectList syncList = new SyncObjectList();
		syncList.syncModifyEntity(chapterIndb);
		CacheClusterSync.sync(syncList);
	}
	
	
	/**
	 * 通过节目Id获取节目子集信息
	 * @param contentId
	 * @return
	 */
	public List<ProgramChapter> getListByContentId(String contentId) {
		DetachedCriteria detachedCriteria = programChapterDao.createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("contentId", contentId));
		List<ProgramChapter> list = programChapterDao.findByCriteria(detachedCriteria);

		if (null == list) {
			list = new ArrayList<ProgramChapter>();
		}
		return list;
	}
}
