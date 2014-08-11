/*
 * 文件名称：AppRecommendPanelItemServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Xiezhengrong/907948
 *
 */
package com.coship.sdp.sce.dp.recommend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.common.PlanConstants;
import com.coship.sdp.sce.dp.plan.service.PlanItemService;
import com.coship.sdp.sce.dp.recommend.dao.AppRecommendPanelDao;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendPanelService;
import com.coship.sdp.utils.Page;

/**
 * 精选页版块数据业务接口
 * 
 * @author Xiezhengrong/907948
 */
@Service("appRecommendPanelService")
@Transactional
public class AppRecommendPanelServiceImpl implements AppRecommendPanelService {
    
	@Autowired
	private AppRecommendPanelDao appRecommendPanelDao;
    
    @Autowired
    private PlanItemService planItemService;


    public void queryAppRecommendPanelList(
            Page<AppRecommendPanel> page, String panelName)
    {
        List<Criterion> conditions = new ArrayList<Criterion>();

        if (!StringUtils.isEmpty(panelName))
        {
            conditions.add(Restrictions
                    .like("panelName", "%" + panelName + "%"));
        }
        
        conditions.add(Restrictions.eq("status", 1));//只查询可用的版块
        
        appRecommendPanelDao.queryAppRecommendPanelList(page,
                conditions.toArray(new Criterion[conditions.size()]),null);
    }
    
    public void saveAppRecommendPanel(AppRecommendPanel entity)
    {
        appRecommendPanelDao.save(entity);
    }
    
    public void updateAppRecoomendPanel(AppRecommendPanel entity)
    { 
        appRecommendPanelDao.update(entity);
    }
    
	public int deleteAppRecoomendPanel(List<String> listKeys) {
		int success = 0;
		if (listKeys != null && listKeys.size() != 0) {
			for (String panelId : listKeys) {
				//修改状态为"已删除"
				panelId = panelId.trim();
				AppRecommendPanel obj = appRecommendPanelDao.get(panelId);
				if (obj != null) {
					obj.setStatus(-1);
					appRecommendPanelDao.update(obj);
					success++;
				}
				//删除与方案的关联关系
				planItemService.deletePlanItem(panelId, PlanConstants.ITEM_TYPE_PANEL);
			}
		}

		return success;
	}
    
    public AppRecommendPanel getAppRecoomendPanelById(String id)
    {
      return appRecommendPanelDao.get(id);
    }
    
    public void doSortAppRecoomendPanel(String id, String sort)
    {
        AppRecommendPanel sortObj = getAppRecoomendPanelById(id);
        
        if (sortObj != null)
        {
            sortObj.setSortNum(Integer.parseInt(sort));
            updateAppRecoomendPanel(sortObj);
        }
    }

    @Override
    public List<AppRecommendPanel> getAllEnabledPanelList() {
        return appRecommendPanelDao.getAllEnabledPanelList();
    }
}
