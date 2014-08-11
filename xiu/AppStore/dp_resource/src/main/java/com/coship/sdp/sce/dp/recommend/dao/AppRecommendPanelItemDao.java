/*
 * 文件名称：AppRecommendPanelItemDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Xiezhengrong/907948
 *
 */
package com.coship.sdp.sce.dp.recommend.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanelItem;
import com.coship.sdp.utils.Page;

/**
 * 精选页数据访问接口
 * 
 * @author Xiezhengrong/907948
 */
public interface AppRecommendPanelItemDao extends IGenericDao<AppRecommendPanelItem, String> {
	/**
	 * 获取所有有效的精选页元素(先根据精选页元素所属板块排序，再根据精选页元素排序).
	 * 
	 * @return
	 */
	public List<AppRecommendPanelItem> getAllAppRecommendPanelItemList();
	
	   /**
     * 获取所有有效的精选页元素,可带查询条件；查询条件必须和对象属性相同,如panelName=精选
     * 
     * @return List<AppRecommendPanel>
     */
    public Page<AppRecommendPanelItem> getAppRecommendPanelItemListByParam(
            Page<AppRecommendPanelItem> page, Map<String, Object> map);
    
    /**
     * 使用hibernate条件查询元素列表
     * @param page  分页对象
     * @param criterions 条件集合
     * @param order 排序条件，默认按sortNum降序排序
     * @return
     */
    public Page<AppRecommendPanelItem> queryAppRecommendPanelItemList(
            Page<AppRecommendPanelItem> page, Criterion[] criterions,Order order);

    /**
     * 根据版块获得元素列表.
     * 
     * @param panelId
     * @return
     */
    public List<AppRecommendPanelItem> getAppRecommendPanelItemList(String panelId);
}
