package com.coship.sdp.sce.dp.plan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.common.PlanConstants;
import com.coship.sdp.sce.dp.exception.BusinessException;
import com.coship.sdp.sce.dp.plan.dao.ItemDao;
import com.coship.sdp.sce.dp.plan.dao.PlanItemDao;
import com.coship.sdp.sce.dp.plan.entity.Item;
import com.coship.sdp.sce.dp.plan.entity.PlanItem;
import com.coship.sdp.sce.dp.plan.entity.PlanItemApp;
import com.coship.sdp.sce.dp.plan.service.PlanItemAppService;
import com.coship.sdp.sce.dp.plan.service.PlanItemService;
import com.coship.sdp.sce.dp.recommend.dao.AppRecommendPanelDao;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.sce.dp.subject.dao.AppSubjectTypeDao;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.type.dao.DpTypeDao;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Page;

@Service("planItemService")
@Transactional
public class PlanItemServiceImpl implements PlanItemService {

	private static final long serialVersionUID = -2984185030995613509L;


	@Autowired
	private PlanItemDao planItemDao;
	
	@Autowired
	private AppRecommendPanelDao appRecommendPanelDao;
	@Autowired
	private AppSubjectTypeDao appSubjectTypeDao;
	@Autowired
	private DpTypeDao dpTypeDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private PlanItemAppService planItemAppService;


	@Override
	public void savePlanItem(String planId, String[] panelIds, Integer itemType) {
		for (String panelId : panelIds) {
			PlanItem planItem = new PlanItem();
			planItem.setPlanId(planId);
			planItem.setItemId(panelId);
			planItem.setItemType(itemType);
			planItem.setSortNum(10000);
			
			Date sysdate = new Date();
			planItem.setCreateTime(sysdate);
			planItem.setUpdateTime(sysdate);
			planItemDao.save(planItem);
		}
	}
	
	@Override
	public void deletePlanItem(String[] planItemIds) {
		for (String planItemId : planItemIds) {
			this.deletePlanItemById(planItemId);
		}
	}
	
	@Override
	public void deletePlanItemById(String planItemId) {
		if (null != planItemId) {
			PlanItem planItem = this.getPlanItemById(planItemId);
			if(null != planItem){
				int itemType = planItem.getItemType();
				if(PlanConstants.ITEM_TYPE_PANEL == itemType || PlanConstants.ITEM_TYPE_SUBJECT == itemType){
					//删除PlanItem
					planItemDao.delete(planItem);
				}else if(PlanConstants.ITEM_TYPE_DPTYPE == itemType || PlanConstants.ITEM_TYPE_SELFTYPE == itemType){
					//删除PlanItemApp
					planItemAppService.deleteByPlanIdAndItemId(planItem.getPlanId(), planItem.getItemId());
					//删除PlanItem
					planItemDao.delete(planItem);
				}
			}
		}
	}
	
	@Override
	public void deletePlanItemByPlanId(String planId){
		List<PlanItem> planItemList = this.getPlanItemListByPlanIdAndType(planId, null);
		for (PlanItem planItem : planItemList) {
			this.deletePlanItemById(planItem.getId());
		}
	}
	
	@Override
	public void deletePlanItem(String itemId, int itemType){
		String hql = "delete from PlanItem where itemId = ? and itemType = ?" ;
		planItemDao.executeUpdate(hql, itemId, itemType);
	}
	
	@Override
	public void modifyPlanItem(PlanItem planItem){
		planItem.setUpdateTime(new Date());
		planItemDao.update(planItem);
	}

	@Override
	public PlanItem getPlanItemById(String id){
		PlanItem planItem = planItemDao.get(id);
		if (null == planItem) {
			throw new BusinessException("id为" + id + "的PlanItem不存在!");
		}
		return planItem;
	}
	
	
	@Override
	public Page<PlanItem> searchPlanItemListByPage(Page<PlanItem> page, PlanItem planItemQuery) {
		//构造查询条件
		Map<String, Object> params = new HashMap<String, Object>();
        StringBuffer sbf = new StringBuffer("from PlanItem where 1 = 1 ");
        
        String planId = planItemQuery.getPlanId();
        if(null != planId){
        	sbf.append(" and planId = :planId");
        	params.put("planId", planId);
        }
        
        Integer itemType = planItemQuery.getItemType();
        if(null != itemType){
        	sbf.append(" and itemType = :itemType");
        	params.put("itemType", itemType);
        }

        sbf.append(" order by sortNum, updateTime desc");

        //查询
        String hql = sbf.toString();
		page = planItemDao.queryPage(page, hql, params);
		return page;
	}
	
	@Override
	public Page<PlanItem> getPlanItemListByPlanIdAndType(Page<PlanItem> page, String planId, Integer itemType) {
        PlanItem planItemQuery = new PlanItem();
        planItemQuery.setPlanId(planId);
        planItemQuery.setItemType(itemType);
        return this.searchPlanItemListByPage(page, planItemQuery);
	}
	
	@Override
	public List<PlanItem> getPlanItemListByPlanIdAndType(String planId, Integer itemType) {
		Page<PlanItem> page = new Page<PlanItem>();
		page.setPageSize(Integer.MAX_VALUE);
		this.getPlanItemListByPlanIdAndType(page, planId, itemType);
		List<PlanItem> list = page.getResultList();
		if(null == list){
			list = new ArrayList<PlanItem>();
		}
		return list;
	}
	
	
	@Override
	public List<PlanItem> getSelectedClass(String planId) {
		//构造查询条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("planId", planId);

        StringBuffer sbf = new StringBuffer("from PlanItem where 1 = 1 ");
        sbf.append(" and planId = :planId");
        sbf.append(" and itemType in ('3', '4')");
        sbf.append(" order by sortNum, updateTime desc");

        //查询
        String hql = sbf.toString();
        List<PlanItem> list = planItemDao.query(hql, params);
        if(null == list){
        	list = new ArrayList<PlanItem>();
        }
        
        //查询关联的应用数量
        for (PlanItem planItem : list) {
        	List<PlanItemApp> appList = planItemAppService.getSelectedAppList(planItem.getId());
        	planItem.setAppNum(appList.size());
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> Page<T> queryUnSelectedPlanItem(Page<T> page, String planId, Integer itemType, Map<String, Object> specialParams) {
		//构造查询条件
		Map<String, Object> params = new HashMap<String, Object>();
        if(null != planId){
        	params.put("planId", planId);
        }
        
        StringBuffer sbf = new StringBuffer();
        if(PlanConstants.ITEM_TYPE_PANEL == itemType){
        	sbf.append("select rp from AppRecommendPanel as rp where rp.status = 1");
        	sbf.append(" and rp.id not in (select pi.itemId from PlanItem pi where pi.planId=:planId");
        	sbf.append(" and pi.itemType = 1) ");
        	sbf.append(" and rp.layoutType = 1");
        	String panelName = (String)specialParams.get("panelName");
            if(StringUtils.isNotBlank(panelName)){
            	sbf.append( " and rp.panelName like '");
            	sbf.append(SqlUtil.getLikeSql(panelName));
            	sbf.append("' escape '/' ");
            }
        	sbf.append(" order by rp.sortNum");
        	
            String hql = sbf.toString();
    		page = (Page<T>) appRecommendPanelDao.queryPage((Page<AppRecommendPanel>) page, hql, params);
        }else if(PlanConstants.ITEM_TYPE_SUBJECT == itemType){
        	sbf.append("select st from AppSubjectType as st where st.visibleFlag = 1");
        	sbf.append(" and st.id not in (select pi.itemId from PlanItem pi where pi.planId=:planId");
        	sbf.append(" and pi.itemType = 2) ");
        	String subjectName = (String)specialParams.get("subjectName");
        	if(StringUtils.isNotBlank(subjectName)){
            	sbf.append( " and st.subjectName like '");
            	sbf.append(SqlUtil.getLikeSql(subjectName));
            	sbf.append("' escape '/' ");
            }
        	sbf.append(" order by st.createDate desc");
        	
            String hql = sbf.toString();
    		page = (Page<T>) appSubjectTypeDao.queryPage((Page<AppSubjectType>) page, hql, params);
        }else if(PlanConstants.ITEM_TYPE_DPTYPE == itemType){
        	sbf.append("select dt from DpType as dt where dt.parentTypeCode in('APP_TYPE','GAME_TYPE')");
        	sbf.append(" and dt.id not in (select pi.itemId from PlanItem pi where pi.planId=:planId");
        	sbf.append(" and pi.itemType = 3) ");
        	sbf.append(" and dt.visibleFlag = 1");
        	String typeName = (String)specialParams.get("typeName");
        	if(StringUtils.isNotBlank(typeName)){
            	sbf.append( " and dt.typeName like '");
            	sbf.append(SqlUtil.getLikeSql(typeName));
            	sbf.append("' escape '/' ");
            }
        	sbf.append(" order by dt.parentTypeCode, dt.sortNum");
        	
            String hql = sbf.toString();
    		page = (Page<T>) dpTypeDao.queryPage((Page<DpType>) page, hql, params);
		}else if(PlanConstants.ITEM_TYPE_SELFTYPE == itemType){
        	sbf.append("select it from Item as it where it.status = 1");
        	sbf.append(" and it.id not in (select pi.itemId from PlanItem pi where pi.planId=:planId");
        	sbf.append(" and pi.itemType = 4) ");
        	String name = (String)specialParams.get("name");
        	if(StringUtils.isNotBlank(name)){
            	sbf.append( " and it.name like '");
            	sbf.append(SqlUtil.getLikeSql(name));
            	sbf.append("' escape '/' ");
            }
        	sbf.append(" order by it.id");
        	
            String hql = sbf.toString();
    		page = (Page<T>) itemDao.queryPage((Page<Item>) page, hql, params);
		}

		return page;
	}

    @Override
    public List<PlanItem> getAllPlanItemList(int itemType) {
        return planItemDao.getAllPlanItemList(itemType);
    }

	@Override
	public List<String> lstExistsItemIds(String planId, List<String> itemIds) {
		return planItemDao.lstExistsItemIds(planId,itemIds);
	}
}
