package com.coship.sdp.sce.dp.plan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.dao.DpAppInfoDao;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.ap.service.DpAppInfoService;
import com.coship.sdp.sce.dp.ap.service.HandAppRelateService;
import com.coship.sdp.sce.dp.command.service.SynCommandService;
import com.coship.sdp.sce.dp.common.AppStatusConstants;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.PlanConstants;
import com.coship.sdp.sce.dp.exception.BusinessException;
import com.coship.sdp.sce.dp.plan.dao.PlanItemAppDao;
import com.coship.sdp.sce.dp.plan.dao.PlanItemDao;
import com.coship.sdp.sce.dp.plan.entity.PlanItem;
import com.coship.sdp.sce.dp.plan.entity.PlanItemApp;
import com.coship.sdp.sce.dp.plan.service.ItemService;
import com.coship.sdp.sce.dp.plan.service.PlanItemAppService;
import com.coship.sdp.sce.dp.plan.service.PlanItemService;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Page;

@Service("planItemAppService")
@Transactional
public class PlanItemAppServiceImpl implements PlanItemAppService {

	private static final long serialVersionUID = 1475800206380336487L;

	@Autowired
	private PlanItemAppDao planItemAppDao;
	@Autowired
	private PlanItemDao planItemDao;	
	@Autowired
	private PlanItemService planItemService;	
	@Autowired
	private ItemService itemService;	
	@Autowired
	private DpAppInfoService dpAppInfoService;	
	@Autowired
	private DpAppInfoDao dpAppInfoDao;	
	@Autowired
	private SynCommandService synCommandService;
    @Autowired
    private HandAppRelateService handAppRelateService;
	
	
	
	@Override
	public void save(String planItemId, String[] appIds) {
		DpAppInfo appInfo = null;
		PlanItemApp planItemApp = null;
		for (String appId : appIds) {
			try {
				appInfo = dpAppInfoService.findAppInfo(appId);
			} catch (Exception e) {
				throw new BusinessException("", e);
			}
			if(null != appInfo){
				if(!StringUtils.equals(AppStatusConstants.APP_STATUS_ONLINE, appInfo.getAppStatus())){
					throw new BusinessException("只能关联上架的应用");
				}
			
				PlanItem planItem = planItemService.getPlanItemById(planItemId);
				
				//增加类项与应用的关联
				planItemApp = new PlanItemApp();
				planItemApp.setAppPkgName(appInfo.getAppFilePackage());
				planItemApp.setPlanId(planItem.getPlanId());
				planItemApp.setItemId(planItem.getItemId());
				planItemApp.setItemType(planItem.getItemType());
				planItemApp.setSortNum(10000);
				Date sysdate = new Date();
				planItemApp.setCreateTime(sysdate);
				planItemApp.setUpdateTime(sysdate);
				planItemAppDao.save(planItemApp);
				
				//需要同步到推荐引擎
				synCommandService.updateStatusToModify(appInfo.getId(), appInfo.getAppStatus());
			}
		}
	}
	
	@Override
	public void modify(PlanItemApp planItemApp){
		planItemApp.setUpdateTime(new Date());
		planItemAppDao.update(planItemApp);
	}
	
	
	@Override
	public void delete(String[] planItemAppIds) {
		for (String planItemAppId : planItemAppIds) {
			PlanItemApp planItemApp = this.getPlanItemAppById(planItemAppId);
			planItemAppDao.delete(planItemApp);
			
			//如果该应用被人工推荐，则将推荐关系删除
			handAppRelateService.deleteByRelateAppPkgName(planItemApp.getPlanId(), planItemApp.getAppPkgName());

			//需要同步到推荐引擎
			DpAppInfo dpAppInfo = dpAppInfoService.findUniqueByPackageName(planItemApp.getAppPkgName());
			synCommandService.updateStatusToModify(dpAppInfo.getId(), dpAppInfo.getAppStatus());
		}
	}

	@Override
	public void deleteByPlanIdAndItemId(String planId, String itemId) {
		if (StringUtils.isNotBlank(planId) && StringUtils.isNotBlank(itemId)) {
			String hql = "delete from PlanItemApp where planId =? and itemId =?";
			planItemAppDao.executeUpdate(hql, new Object[] { planId, itemId });
		}
	}
	
	@Override
	public void batchSort(String[] planItemAppIds, String[] sortNums) {
		PlanItemApp planItemApp = null;
		for (int i = 0; i < planItemAppIds.length; i++) {
			planItemApp = this.getPlanItemAppById(planItemAppIds[i]);
			if (null != planItemApp) {
				planItemApp.setSortNum(Integer.valueOf(sortNums[i]));
				planItemApp.setUpdateTime(new Date());
				planItemAppDao.update(planItemApp);
			}
		}
	}
	
	
	@Override
	public PlanItemApp getPlanItemAppById(String id) {
		PlanItemApp planItemApp = planItemAppDao.get(id);
		if (null == planItemApp) {
			throw new BusinessException("id为" + id + "的PlanItemApp不存在!");
		}
		return planItemApp;
	}
	
	
	@Override
	public Page<PlanItemApp> searchPlanItemAppListByPage(Page<PlanItemApp> page, PlanItemApp planItemAppQuery) {
		//构造查询条件
		Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer sbf = new StringBuffer("from PlanItemApp where 1 = 1 ");
        
        String planId = planItemAppQuery.getPlanId();
        if(StringUtils.isNotBlank(planId)){
        	sbf.append(" and planId = :planId");
        	params.put("planId", planId);
        }
        
        String itemId = planItemAppQuery.getItemId();
        if(StringUtils.isNotBlank(itemId)){
        	sbf.append(" and itemId = :itemId");
        	params.put("itemId", itemId);
        }

        sbf.append(" order by sortNum, updateTime desc");

        //查询
        String hql = sbf.toString();
		page = planItemAppDao.queryPage(page, hql, params);
		return page;
	}
	
	
	@Override
	public Page<PlanItemApp> searchSelectedApp(Page<PlanItemApp> page, String planItemId, Map<String, Object> specialParams) {
		PlanItem planItem = planItemService.getPlanItemById(planItemId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("planId", planItem.getPlanId());
		params.put("itemId", planItem.getItemId());
		String appName = "";
		if(null != specialParams){
			appName = (String)specialParams.get("appName");
		}

		StringBuffer sbf = new StringBuffer();
    	sbf.append("select pia from PlanItemApp as pia where pia.planId = :planId and pia.itemId = :itemId");
    	
        if(StringUtils.isNotBlank(appName)){
        	sbf.append(" and exists (select 1 from DpAppInfo as da where da.appFilePackage = pia.appPkgName");
        	sbf.append( " and da.appName like '");
        	sbf.append(SqlUtil.getLikeSql(appName));
        	sbf.append("' escape '/') ");
        }
    	sbf.append(" order by pia.sortNum, pia.updateTime desc");
    	planItemAppDao.queryPage(page, sbf.toString(), params);
    	if(CollectionUtils.isEmpty(page.getResultList())){
			return page;
		}
		
		//根据 lstPkgName查询应用数据 DpAppInfo
		List<String> lstPkgName = new ArrayList<String>();
		for (PlanItemApp planItemApp : page.getResultList()) {
			lstPkgName.add(planItemApp.getAppPkgName());
		}

		sbf = new StringBuffer();
    	sbf.append("select da from DpAppInfo as da where exists (");
    	sbf.append("select 1 from DpType as dt where dt.id = da.dpType.id and dt.parentTypeCode in('APP_TYPE','GAME_TYPE'))");
    	sbf.append(" and da.appFilePackage in (:PKG_NAMES) ");
    	List<DpAppInfo> dppAppList = null;
    	try {
			dppAppList = dpAppInfoService.listByHQL(sbf.toString(), lstPkgName);
		} catch (Exception e) {
			throw new BusinessException("", e);
		}
    	
    	if(null == dppAppList){
    		dppAppList = new ArrayList<DpAppInfo>();
    	}
		
		//组装并返回结果
		for (PlanItemApp planItemApp : page.getResultList()) {
			for (DpAppInfo dpAppInfo : dppAppList) {
				if(planItemApp.getAppPkgName().equals(dpAppInfo.getAppFilePackage())){
					planItemApp.setDpAppInfo(dpAppInfo);
					//优先装入 已上架的
					if(AppStatusConstants.APP_STATUS_ONLINE.equals(dpAppInfo.getAppStatus())){
						break;
					}
				}
			}
		}
		return page;
	}
	
	@Override
	public Page<DpAppInfo> searchUnSelectedApp(Page<DpAppInfo> page, String planItemId, Map<String, Object> specialParams) {
		//构造查询条件
		PlanItem planItem = planItemService.getPlanItemById(planItemId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("planId", planItem.getPlanId());
		params.put("itemId", planItem.getItemId());
		
		StringBuffer sbf = new StringBuffer();
    	sbf.append("select da from DpAppInfo as da where da.appStatus = ").append(AppStatusConstants.APP_STATUS_ONLINE);
    	sbf.append(" and da.appFilePackage not in (select ia.appPkgName from PlanItemApp as ia where ia.planId=:planId");
    	sbf.append(" and ia.itemId = :itemId) ");
    	String appName = (String)specialParams.get("appName");
        if(StringUtils.isNotBlank(appName)){
        	sbf.append( " and da.appName like '");
        	sbf.append(SqlUtil.getLikeSql(appName));
        	sbf.append("' escape '/' ");
        }
    	if(PlanConstants.ITEM_TYPE_DPTYPE == planItem.getItemType()){
    		sbf.append(" and da.dpType.id = :itemId ");
    		sbf.append(" and da.dpType.id in (select dt.id from DpType as dt where dt.parentTypeCode in('APP_TYPE','GAME_TYPE'))");
    	}else if(PlanConstants.ITEM_TYPE_SELFTYPE == planItem.getItemType()){
    		String typeId = (String)specialParams.get("typeId");
            if(StringUtils.isNotBlank(typeId)){
            	sbf.append( " and da.dpType.id = :typeId");
            	params.put("typeId", typeId);
            }
    	}
    	sbf.append(" order by da.updateTime desc ");

        String hql = sbf.toString();
        if(!"".equals(hql)){
        	page = dpAppInfoDao.queryPage((Page<DpAppInfo>) page, hql, params);
        }
		return page;
	}


	@Override
	public void appAddPlan(String appId, String planId,List<String> addItemIds, List<String> deleteItemIds) {
		
		List<String> lstExistsItemIds =  planItemDao.lstExistsItemIds(planId,addItemIds);
		
		//添加 addItemIds
		for(String strItemId : addItemIds){
			//t_plan_item表中不存在
			if(!lstExistsItemIds.contains(strItemId)){
				PlanItem objPlanItem = new PlanItem();
				objPlanItem.setPlanId(planId);
				objPlanItem.setItemId(strItemId);
				objPlanItem.setItemType(PlanConstants.ITEM_TYPE_SELFTYPE);
				objPlanItem.setSortNum(10000);
				objPlanItem.setCreateTime(new Date());
				objPlanItem.setUpdateTime(new Date());
				planItemDao.save(objPlanItem);
			} 
			
			DpAppInfo objDpAppInfo = dpAppInfoDao.get(appId);
			
			//插入 t_plan_item_app信息
			PlanItemApp objPlanItemApp = new PlanItemApp();
			objPlanItemApp.setPlanId(planId);
			objPlanItemApp.setItemId(strItemId);
			objPlanItemApp.setAppPkgName(objDpAppInfo.getAppFilePackage());
			objPlanItemApp.setItemType(PlanConstants.ITEM_TYPE_SELFTYPE);
			objPlanItemApp.setSortNum(10000);
			objPlanItemApp.setCreateTime(new Date());
			objPlanItemApp.setUpdateTime(new Date());
			planItemAppDao.save(objPlanItemApp);
		}
		
		//去掉deleteItemIds
		if(deleteItemIds.size() > 0){
			DpAppInfo objDpAppInfo = dpAppInfoDao.get(appId);
			String strPkgName = objDpAppInfo.getAppFilePackage();
			planItemAppDao.appDeletePlan(strPkgName,planId,deleteItemIds);
		}
	}

    @Override
    public List<PlanItemApp> getAllPlanItemAppList(int itemType) {
    	List<PlanItemApp> list = planItemAppDao.getAllPlanItemAppList(itemType);
    	if(null == list){
    		list = new ArrayList<PlanItemApp>();
    	}
        return list;
    }
    
    @Override
    public List<PlanItemApp> getSelectedAppList(String planItemId){
    	Page<PlanItemApp> page = new Page<PlanItemApp>();
    	page.setPageSize(Integer.MAX_VALUE);
    	this.searchSelectedApp(page, planItemId, new HashMap<String, Object>());
    	List<PlanItemApp> list = page.getResultList();
    	if(null == list){
    		list = new ArrayList<PlanItemApp>();
    	}
    	return list;
    }
    
    @Override
    public Map<String, PlanItemApp> getItemAppMapByPlanId(String planId){
		String hql = "from PlanItemApp where planId = ? ";
		List<PlanItemApp> planItemAppList = planItemAppDao.query(hql, planId);
		if (null == planItemAppList) {
			planItemAppList = new ArrayList<PlanItemApp>();
		}
		
		Map<String, DpAppInfo> dpAppInfoMap = dpAppInfoService.searchAppAndGameMap();
		Map<String, PlanItemApp> resultMap = new HashMap<String, PlanItemApp>();
		for (PlanItemApp planItemApp : planItemAppList) {
			String appPkgName = planItemApp.getAppPkgName();
			planItemApp.setDpAppInfo(dpAppInfoMap.get(appPkgName));
			resultMap.put(appPkgName, planItemApp);
		}
		return resultMap;
    }
    
    @Override
    public Map<String, PlanItemApp> getItemAppOnlineMapByPlanId(String planId) {
		Map<String, PlanItemApp> planItemAppMap = this.getItemAppMapByPlanId(planId);
		
		Map<String, PlanItemApp> resultMap = new HashMap<String, PlanItemApp>();
		for (String appPkgName : planItemAppMap.keySet()) {
			PlanItemApp planItemApp = planItemAppMap.get(appPkgName);
			
			DpAppInfo dpAppInfo = planItemApp.getDpAppInfo();
			if(null != dpAppInfo && StringUtils.equals(Constants.APP_STATUS_ONLINE, dpAppInfo.getAppStatus())){
				resultMap.put(appPkgName, planItemApp);
			}
		}
		return resultMap;
    }

}
