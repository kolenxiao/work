package com.coship.sdp.sce.dp.plan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.plan.entity.PlanItem;
import com.coship.sdp.sce.dp.plan.entity.PlanItemApp;
import com.coship.sdp.utils.Page;

/**
 * 精细化运营-方案类项与应用的关联关系Service
 * 
 * @author 908618
 * 
 */
public interface PlanItemAppService extends Serializable {
	
	
	/**
	 * 保存
	 * @param planItemId
	 * @param appIds
	 */
	public void save(String planItemId, String[] appIds);
	
	
	/**
	 * 修改
	 * @param planItemApp
	 */
	public void modify(PlanItemApp planItemApp);

	/**
	 * 删除
	 * @param planItemAppIds
	 */
	public void delete(String[] planItemAppIds);
	
	/**
	 * 根据方案id和类项id删除PlanItemApp
	 * @param planId
	 * @param itemId
	 */
	public void deleteByPlanIdAndItemId(String planId, String itemId);
	
	/**
	 * 批量修改排序
	 * @param planItemAppIds
	 * @param sortNums
	 */
	public void batchSort(String[] planItemAppIds, String[] sortNums);
	
	/**
	 * 根据id获取PlanItemApp
	 * @param id
	 * @return
	 */
	public PlanItemApp getPlanItemAppById(String id);
	
	/**
	 * 分面查询
	 * @param page
	 * @param planItemAppQuery
	 * @return
	 */
	public Page<PlanItemApp> searchPlanItemAppListByPage(Page<PlanItemApp> page, PlanItemApp planItemAppQuery);
	
	/**
	 * 查询已关联的应用
	 * @param page
	 * @param planItemId
	 * @param specialParams
	 * @return
	 */
	public Page<PlanItemApp> searchSelectedApp(Page<PlanItemApp> page, String planItemId, Map<String, Object> specialParams);
	
	/**
	 * 查询planItemId下已关联的应用列表
	 * @param planItemId
	 * @return
	 */
	public List<PlanItemApp> getSelectedAppList(String planItemId);
	
	/**
	 * 查询planItemId下未关联的应用列表
	 * @param page
	 * @param planItemId
	 * @param specialParams
	 * @return
	 */
	public Page<DpAppInfo> searchUnSelectedApp(Page<DpAppInfo> page, String planItemId, Map<String, Object> specialParams);
	
	/**
	 * 通过planId获取自定义分类下的应用列表
	 * @param planId
	 * @return
	 */
	public Map<String, PlanItemApp> getItemAppMapByPlanId(String planId);
	public Map<String, PlanItemApp> getItemAppOnlineMapByPlanId(String planId);


    /**
     * 根据方案类项类型查找.
     * 
     * @param itemType 方案类项类型,值参考： {@link PlanItem#getItemType()}
     * @return
     */
    public List<PlanItemApp> getAllPlanItemAppList(int itemType);


	public void appAddPlan(String appId, String planId, List<String> addItemIds, List<String> deleteItemIds);
}
