/*
 * 文件名称：AppRecommendPanelItemServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Xiezhengrong/907948
 *
 */
package com.coship.sdp.sce.dp.recommend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.dao.DpAppInfoDao;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.common.AppStatusConstants;
import com.coship.sdp.sce.dp.recommend.dao.AppRecommendPanelDao;
import com.coship.sdp.sce.dp.recommend.dao.AppRecommendPanelItemDao;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanelItem;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendPanelItemService;
import com.coship.sdp.sce.dp.subject.dao.AppSubjectTypeDao;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.utils.Page;

/**
 * 精选页数据业务接口
 * 
 * @author Xiezhengrong/907948
 */
@Service("appRecommendPanelItemService")
@Transactional
public class AppRecommendPanelItemServiceImpl implements AppRecommendPanelItemService {
    
	@Autowired
	private AppRecommendPanelItemDao appRecommendPanelItemDao;
	
	@Autowired
	private AppRecommendPanelDao appRecommendPanelDao;
	
    @Autowired
    private AppSubjectTypeDao appSubjectTypeDao;
    
    @Autowired
    private DpAppInfoDao dpAppInfoDao;
    
    private static final Integer APPINFO = 1;
    
    private static final Integer SUBJECT = 2;

	@Override
	public List<AppRecommendPanelItem> getAllAppRecommendPanelItemList() {
		List<AppRecommendPanelItem> result = appRecommendPanelItemDao.getAllAppRecommendPanelItemList();
		if (result == null) {
			result = new ArrayList<AppRecommendPanelItem>();
		}

		return result;
	}

    @Override
    public void queryAppRecommendPanelItemList(
            Page<AppRecommendPanelItem> page, String itemName, Integer type)
    {
        List<Criterion> conditions = new ArrayList<Criterion>();

        if (!StringUtils.isEmpty(itemName))// 元素名称
        {
            conditions.add(Restrictions.like("itemName", "%" + itemName + "%"));
        }

        if (null != type)// 元素类型
        {
            conditions.add(Restrictions.eq("type", type));
        }

        conditions.add(Restrictions.eq("status", 1));// 只查询可用的版块

        appRecommendPanelItemDao.queryAppRecommendPanelItemList(page,
                conditions.toArray(new Criterion[conditions.size()]), null);
    }

    @Override
    public void saveAppRecommendPanelItem(AppRecommendPanelItem entity)
    {
        if (entity != null)
        {
            if (APPINFO.equals(entity.getType()))
            {
                Object appInfo = findAPPByPackage(entity.getTypeValue());
                if (appInfo == null)
                {
                    throw new RuntimeException("could not foud app by package : "+ entity.getTypeValue());
                }
            }
            else if (SUBJECT.equals(entity.getType()))
            {
                Object subject = findSubjectByID(entity.getTypeValue());
                if (subject == null)
                {
                    throw new RuntimeException("could not foud subject by id : "+ entity.getTypeValue());
                }
            }
        }
        appRecommendPanelItemDao.save(entity);
    }

    @Override
    public void updateAppRecoomendPanelItem(AppRecommendPanelItem entity)
    {
        if (entity != null)
        {
            if (APPINFO.equals(entity.getType()))
            {
                Object appInfo = findAPPByPackage(entity.getTypeValue());
                if (appInfo == null)
                {
                    throw new RuntimeException("could not foud app by package : " + entity.getTypeValue());
                }
            }
            else if (SUBJECT.equals(entity.getType()))
            {
                Object subject = findSubjectByID(entity.getTypeValue());
                if (subject == null)
                {
                    throw new RuntimeException("could not foud subject by id : " + entity.getTypeValue());
                }
            }
        }
        appRecommendPanelItemDao.update(entity);
    }

    @Override
    public int unRecommendItemToPanel(List<String> listKeys)
    {
        int success = 0;
        if (listKeys != null && listKeys.size() != 0)
        {
            for (String key : listKeys)
            {
                try
                {
                    AppRecommendPanelItem obj = appRecommendPanelItemDao.get(key.trim());// 先取出在修改

                    if (obj != null)
                    {
                        obj.setAppRecommendPanel(null);
                        obj.setSortNum(10);
                        appRecommendPanelItemDao.update(obj);//修改对象信息
                        success++;
                    }     
                }
                catch (Exception e)
                {

                }
            }
        }
        
        return success;
    }

    @Override
    public AppRecommendPanelItem getAppRecoomendPanelItemById(String id)
    {
        return appRecommendPanelItemDao.get(id);
    }

    @Override
    public void doSortAppRecoomendPanelItem(String id, String sort)
    {
        AppRecommendPanelItem sortObj = getAppRecoomendPanelItemById(id);
        
        if (sortObj != null)
        {
            sortObj.setSortNum(Integer.parseInt(sort));
            updateAppRecoomendPanelItem(sortObj);
        }
    }
    
    @Override
    public void queryItemListByPanelId(Page<AppRecommendPanelItem> page,
            String itemName, Integer type, String panelId)
    {
        List<Criterion> conditions = new ArrayList<Criterion>();

        if (!StringUtils.isEmpty(itemName))// 元素名称
        {
            conditions.add(Restrictions.like("itemName", "%" + itemName + "%"));
        }

        if (null != type)// 元素类型
        {
            conditions.add(Restrictions.eq("type", type));
        }

        // 版块ID
        conditions.add(Restrictions.eq("appRecommendPanel.id", panelId));

        // 只查询可用的版块
        conditions.add(Restrictions.eq("status", 1));

        appRecommendPanelItemDao.queryAppRecommendPanelItemList(page,
                conditions.toArray(new Criterion[conditions.size()]), null);
    }
    
    public void queryUnRecommendItemList(
            Page<AppRecommendPanelItem> page, String itemName, Integer type)
    {
        List<Criterion> conditions = new ArrayList<Criterion>();

        if (!StringUtils.isEmpty(itemName))// 元素名称
        {
            conditions.add(Restrictions.like("itemName", "%" + itemName + "%"));
        }

        if (null != type)// 元素类型
        {
            conditions.add(Restrictions.eq("type", type));
        }
        
        conditions.add(Restrictions.isNull("appRecommendPanel.id"));
        
        // 只查询可用的版块
        conditions.add(Restrictions.eq("status", 1));
        
        appRecommendPanelItemDao.queryAppRecommendPanelItemList(page,
                conditions.toArray(new Criterion[conditions.size()]), null);
        
    }
    
    public void doRecommendItem(String[] ids, String panelKey)
    {
        if (ids != null)
        {
            AppRecommendPanel panel = appRecommendPanelDao.get(panelKey);
            for (String id : ids)
            {
                AppRecommendPanelItem recommendItem = appRecommendPanelItemDao.get(id.trim());
                recommendItem.setAppRecommendPanel(panel);
                appRecommendPanelItemDao.update(recommendItem);
            }

        }
    }
    
    
    @Override
    public int deleteItem(List<String> listKeys)
    {
        int success = 0;
        if (listKeys != null && listKeys.size() != 0)
        {
            for (String key : listKeys)
            {
                try
                {
                    AppRecommendPanelItem obj = appRecommendPanelItemDao.get(key.trim());// 先取出在修改
                    if (obj != null)
                    {
                        obj.setStatus(-1);
                        appRecommendPanelItemDao.update(obj);//修改对象信息
                        success++;
                    }     
                }
                catch (Exception e)
                {

                }
            }
        }
        
        return success;
    }
    
    public String getItemTypeValue(Integer type, String key)
    {

        String name = "";

        if (APPINFO.equals(type))
        {
            DpAppInfo appInfo = findAPPByPackage(key);
            if (appInfo != null)
            {
                name = appInfo.getAppName();
            }

        }
        else if (SUBJECT.equals(type))
        {
            AppSubjectType subject = findSubjectByID(key);

            if (subject != null)
            {
                name = subject.getSubjectName();
            }

        }else{
            name =  key;
        }

        return name;

    }

	public boolean isRelated(String typeValue) {
		boolean bl = false;
    	if(StringUtils.isNotBlank(typeValue)){
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("typeValue", typeValue);
    		Page<AppRecommendPanelItem> page = appRecommendPanelItemDao.getAppRecommendPanelItemListByParam(new Page<AppRecommendPanelItem>(), map);
    		
    		List<AppRecommendPanelItem> resultList = page.getResultList();
    		for (AppRecommendPanelItem temp : resultList) {
				if(null != temp.getAppRecommendPanel()){
					bl = true;
				}
			}
    	}
    	return bl;
    }
    
    private DpAppInfo findAPPByPackage(String packageName)
    {
        Page<DpAppInfo> page = new Page<DpAppInfo>();
        List<Criterion> conditions = new ArrayList<Criterion>();
        conditions.add(Restrictions.eq("appFilePackage", packageName));
        conditions.add(Restrictions.eq("appStatus", AppStatusConstants.APP_STATUS_ONLINE));
        dpAppInfoDao.queryPage(page,conditions.toArray(new Criterion[conditions.size()]));
        
        if (page.getResultList() != null && page.getResultList().size() > 0)
        {
            return page.getResultList().get(0);
        }
        
        return null;
    }
    
    
    private AppSubjectType findSubjectByID(String key)
    {
        AppSubjectType subject = appSubjectTypeDao.get(key);
        return subject;
    }

    @Override
    public List<AppRecommendPanelItem> getAppRecommendPanelItemList(String panelId) {
        return appRecommendPanelItemDao.getAppRecommendPanelItemList(panelId);
    }
}
