/*
 * 文件名称：AppRecommendPanelItemDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Xiezhengrong/907948
 *
 */
package com.coship.sdp.sce.dp.recommend.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.utils.Page;

/**
 * 精选页数据访问接口
 * 
 * @author Wangzhenghui/907632
 */
public interface AppRecommendPanelDao extends IGenericDao<AppRecommendPanel, String> {
    /**
     * 使用hibernate条件查询版块列表
     * @param page  分页对象
     * @param criterions 条件集合
     * @param order 排序条件，默认按sortNum降序排序
     * @return
     */
    public Page<AppRecommendPanel> queryAppRecommendPanelList(
            Page<AppRecommendPanel> page, Criterion[] criterions,Order order);

    /**
     * 获得所有有效的版块列表(前端接口初始化数据使用).
     * 
     * @return
     */
    public List<AppRecommendPanel> getAllEnabledPanelList();
}
