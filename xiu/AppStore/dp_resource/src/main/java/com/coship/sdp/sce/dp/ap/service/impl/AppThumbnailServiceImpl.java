package com.coship.sdp.sce.dp.ap.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.dao.AppThumbnailDao;
import com.coship.sdp.sce.dp.ap.entity.AppThumbnail;
import com.coship.sdp.sce.dp.ap.service.AppThumbnailService;

@Service("appThumbnailService")
@Transactional
public class AppThumbnailServiceImpl implements AppThumbnailService {
	
	private static final long serialVersionUID = 7985738093229150082L;
	
	@Autowired
	private AppThumbnailDao appThumbnailDao;
	
	
	@Override
	public void save(AppThumbnail appThumbnail){
		appThumbnailDao.saveOrUpdate(appThumbnail);
	}

	@Override
	public Map<String, List<AppThumbnail>> searchByType(String srcType) {
		List<AppThumbnail> thumList = this.getListByType(srcType);

		Map<String, List<AppThumbnail>> resultMap = new HashMap<String, List<AppThumbnail>>();
		if (null != thumList) {
			for (AppThumbnail appThumbnail : thumList) {
				List<AppThumbnail> aloneList = resultMap.get(appThumbnail
						.getSrcImg());
				if (null == aloneList) {
					aloneList = new ArrayList<AppThumbnail>();
					resultMap.put(appThumbnail.getSrcImg(), aloneList);
				}
				aloneList.add(appThumbnail);
			}
		}
		return resultMap;
	}

	public List<AppThumbnail> getListByType(String srcType) {
		StringBuffer hql = new StringBuffer("from AppThumbnail where 1 = 1");
		if (null != srcType) {
			hql.append(" and srcType = '" + srcType + "'");
		}
		hql.append(" ORDER BY srcImg");

		List<AppThumbnail> thumList = appThumbnailDao.query(hql.toString());
		if (null == thumList) {
			thumList = new ArrayList<AppThumbnail>();
		}
		return thumList;
	}
}
