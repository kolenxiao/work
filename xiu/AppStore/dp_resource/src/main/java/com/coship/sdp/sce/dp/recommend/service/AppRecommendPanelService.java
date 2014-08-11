/*
 * 文件名称：AppRecommendPanelItemService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Xiezhengrong/907948
 *
 */
package com.coship.sdp.sce.dp.recommend.service;

import java.util.List;

import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.utils.Page;

/**
 * 精选页版块数据业务接口
 * 
 * @author wangzhenghui/907632
 */
public interface AppRecommendPanelService {

    /**
     * 查询版块列表,根据版块名称可进行模糊查询
     * @param page 分页对象
     * @param panelName 可选条件，版块名称
     */
    public void queryAppRecommendPanelList(
            Page<AppRecommendPanel> page, String panelName);
    
    /**
     * 保存页版块信息
     * @param entity 版块对象信息
     * @return
     */
    public void saveAppRecommendPanel(AppRecommendPanel entity);
    
    /**
     * 修改版块信息
     */
    public void updateAppRecoomendPanel(AppRecommendPanel entity);

    /**
     * 批量删除版块信息，该方法实际为修改显示字段为不显示，没有在数据库中删除记录
     */
    public int deleteAppRecoomendPanel(List<String> listKeys);
    
    /**
     * 根据ID获取版块信息
     * @param id
     * @return
     */
    public AppRecommendPanel getAppRecoomendPanelById(String id);
    
    
    /**
     * 根据版块ID，人工调整版块排序顺序
     */
    public void doSortAppRecoomendPanel(String id, String sort);

    /**
     * 获得所有有效的版块列表(前端接口初始化数据使用).
     * 
     * @return
     */
    public List<AppRecommendPanel> getAllEnabledPanelList();
}
