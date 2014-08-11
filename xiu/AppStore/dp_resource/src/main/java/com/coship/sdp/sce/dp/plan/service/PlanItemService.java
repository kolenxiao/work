package com.coship.sdp.sce.dp.plan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.coship.sdp.sce.dp.plan.entity.PlanItem;
import com.coship.sdp.utils.Page;

/**
 * 精细化运营-方案与类项的关联关系Service
 * @author 908618
 *
 */
public interface PlanItemService extends Serializable {
	
	/**
	 * 保存方案关联的类项
	 * @param planId
	 * @param panelIds
	 */
	public void savePlanItem(String planId, String[] panelIds, Integer itemType);
	
	/**
	 * 修改方案类型
	 * @param planItem
	 */
	public void modifyPlanItem(PlanItem planItem);
	
	/**
	 * 删除方案关联的类项
	 * @param ids
	 */
	public void deletePlanItem(String[] ids);
	
	/**
	 * 根据id删除类项
	 * @param id
	 */
	public void deletePlanItemById(String id);
	
	/**
	 * 根据方案id删除类项列表
	 * @param planId
	 */
	public void deletePlanItemByPlanId(String planId);
	
	/**
	 * 根据类型id和类型删除
	 * @param itemId
	 * @param itemType
	 */
	public void deletePlanItem(String itemId, int itemType);
	
	/**
	 * 根据id获取方案类型信息
	 * @param id
	 * @return
	 */
	public PlanItem getPlanItemById(String id);
	
	/**
	 * 查询方案类项分页列表
	 * @param page
	 * @param planItemQuery
	 * @return
	 */
	public Page<PlanItem> searchPlanItemListByPage(Page<PlanItem> page, PlanItem planItemQuery);
	
	/**
	 * 通过方案id和类项类型获取列表
	 * @param page
	 * @param planId
	 * @param itemType
	 * @return
	 */
	public Page<PlanItem> getPlanItemListByPlanIdAndType(Page<PlanItem> page, String planId, Integer itemType);
	public List<PlanItem> getPlanItemListByPlanIdAndType(String planId, Integer itemType);
	
	/**
	 * 通过方案id查询已关联的分类(包括固定和自定义)
	 * @param planId
	 * @return
	 */
	public List<PlanItem> getSelectedClass(String planId);
	
	/**
	 * 查询未关联的类项
	 * @param page
	 * @param planId
	 * @param itemType
	 * @param specialParams
	 * @return
	 */
	public <T> Page<T> queryUnSelectedPlanItem(Page<T> page, String planId, Integer itemType, Map<String, Object> specialParams);

    /**
     * 获得所有分类类项列表.(多个方案会有重复的分类类项)
     * 
     * @param itemType
     * @return
     */
    public List<PlanItem> getAllPlanItemList(int itemType);

	public List<String> lstExistsItemIds(String planId, List<String> itemIds);

}
