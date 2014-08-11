/*
 * 文件名称：AppRecommendPanelItemService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Xiezhengrong/907948
 *
 */
package com.coship.sdp.sce.dp.recommend.service;

import java.util.List;

import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanelItem;
import com.coship.sdp.utils.Page;

/**
 * 精选页数据业务接口
 * 
 * @author Xiezhengrong/907948
 */
public interface AppRecommendPanelItemService {
	/**
	 * 获取所有有效的精选页元素(先根据精选页元素所属板块排序，再根据精选页元素排序).
	 * 
	 * @return
	 */
	public List<AppRecommendPanelItem> getAllAppRecommendPanelItemList();
	
    /**
     * 查询版块元素信息，可根据元素名称（模糊查询）和分类进行查询
     * @param page  分页对象
     * @param itemName 元素名称
     * @param type  精选页板块元素的类型，包含：1=应用；2=专题；3=文本
     */
    public void queryAppRecommendPanelItemList(
            Page<AppRecommendPanelItem> page, String itemName, Integer type);
    
    /**
     * 保存精选页版块元素信息,如果类型为1或2，保存前进行完整性校验，校验失败抛出运行期异常 
     * @param entity
     * @return
     */
    public void saveAppRecommendPanelItem(AppRecommendPanelItem entity);
    
    /**
     * 修改精选页版块元素信息,如果类型为1或2，保存前进行完整性校验，校验失败抛出运行期异常 
     * @param entity
     * @return
     */
    public void updateAppRecoomendPanelItem(AppRecommendPanelItem entity);
    
    /**
     * 取消元素和版块之间的关联
     * @param listKeys 元素ID列表
     * @return
     */
    public int unRecommendItemToPanel(List<String> listKeys);
    
    /**
     * 根据ID获取版块元素
     * @param id 元素主键
     * @return
     */
    public AppRecommendPanelItem getAppRecoomendPanelItemById(String id);
    
    
    /**
     * 对版块下的元素进行人工排序
     * @param id 版块ID
     * @param sort 排列的顺序
     */
    public void doSortAppRecoomendPanelItem(String id, String sort);
    
    /**
     * 根据版块ID获取版块下的元素列表
     * @param page
     * @param itemName
     * @param type
     * @param panelId
     */
    public void queryItemListByPanelId(
            Page<AppRecommendPanelItem> page, String itemName, Integer type, String panelId);
    
    /**
     * 查询未和版块关联的元素
     * @param page  分页对象
     * @param itemName 元素名称
     * @param type 元素类型
     * @param panelId 版块ID
     */
    public void queryUnRecommendItemList(
            Page<AppRecommendPanelItem> page, String itemName, Integer type);
    
    
    /**
     * 元素和版块进行关联
     * @param ids 元素ID集合
     * @param panelKey 版块ID
     */
    public void doRecommendItem(String[] ids, String panelKey);
    
    /**
     * 删除元素，该方法只修改元素显示状态，并未在数据库中删除该记录
     * @param listKeys
     * @return
     */
    public int deleteItem(List<String> listKeys);
    
    /**
     * 根据元素分类，查询分类名称,如分类类型是应用，则根据类型值获取该应用的名称
     * @param type 分类类型
     * @param key 分类主键
     * @return
     */
    public String getItemTypeValue(Integer type, String key);
    
    /**
     * 根据typeValue查询元素是否已关联
     * @param typeValue
     * @return
     */
    public boolean isRelated(String typeValue);

    /**
     * 根据版块获得元素列表.
     * 
     * @param panelId
     * @return
     */
    public List<AppRecommendPanelItem> getAppRecommendPanelItemList(String panelId);
}
