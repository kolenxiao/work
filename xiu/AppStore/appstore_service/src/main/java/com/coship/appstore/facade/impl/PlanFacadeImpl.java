/**
 * 
 */
package com.coship.appstore.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coship.appstore.facade.PlanFacade;
import com.coship.appstore.plan.processor.PlanProcessor;
import com.coship.appstore.service.common.Global;
import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.plan.entity.Item;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.utils.Page;

/**
 * @author xiezhengrong/907948
 *
 * @since 2014年4月10日
 */
@Service("planFacade")
public class PlanFacadeImpl implements PlanFacade {
    @Autowired
    private PlanProcessor planProcessor;
    
    @Override
    public Plan getPlan(Map<String, String[]> paramMap){
    	return planProcessor.process(paramMap);
    }

    @Override
    public List<DpType> getPlanedDpTypeList(Map<String, String[]> paramMap) {
    	List<DpType> lstResult = new ArrayList<DpType>();
        //方案id
        String strPlanId = planProcessor.process(paramMap).getId();
        List<String> lstTypeIds =  Global.planDpTypeIdMap.get(strPlanId);
        
        //应用类型标记  0:查询应用和游戏所有分类   1/null:查询所有的应用分类  2: 查询所有的游戏分类
        String[] flags = paramMap.get("flag");
        String strFlag = "0";
        if (flags != null && flags.length > 0) {
            strFlag = flags[0];
        }
        List<DpType> lstAllDeTypes = Global.appTypeList ;
		 if ("0".equals(strFlag)) {
			 lstAllDeTypes = Global.allDpTypeList;
        } else if ("2".equals(strFlag)) {
        	lstAllDeTypes = Global.gameTypeList;
        }
		 
		for(String strTypeId : lstTypeIds){
			for(DpType objType : lstAllDeTypes){
				if(StringUtils.isNotBlank(strTypeId)){
					if(strTypeId.equals(objType.getId())){
						lstResult.add(objType);
						break;
					}
				}
			}
		} 
		 return lstResult;
    }

    @Override
    public List<DpType> getPlanedSelfTypeList(Map<String, String[]> paramMap) {
    	List<DpType> lstResult = new ArrayList<DpType>();
        //获取方案id
        String planId = planProcessor.process(paramMap).getId();
        List<Item> selfTypeList =  Global.planSelfTypeMap.get(planId);
        if(CollectionUtils.isEmpty(selfTypeList)){
        	return lstResult;
        }
        
        //应用类型标记  0:查询应用和游戏所有分类   1/null:查询所有的应用分类  2: 查询所有的游戏分类
        String[] flags = paramMap.get("flag");
        String strFlag = "0";
        if (flags != null && flags.length > 0) {
            strFlag = flags[0];
        }

		if ("0".equals(strFlag)) {
			//应用+游戏
			for (Item item : selfTypeList) {
				if (DefaultTypeCodeConstants.APP_TYPE_CODE.equals(item.getParentTypeCode())
						|| DefaultTypeCodeConstants.GAME_TYPE_CODE.equals(item.getParentTypeCode())) {
					lstResult.add(this.converItemToDpType(item));
				}
			}
		} else if ("1".equals(strFlag)) {
			//应用
			for (Item item : selfTypeList) {
				if (DefaultTypeCodeConstants.APP_TYPE_CODE.equals(item.getParentTypeCode())) {
					lstResult.add(this.converItemToDpType(item));
				}
			}
		} else if ("2".equals(strFlag)) {
			//游戏
			for (Item item : selfTypeList) {
				if (DefaultTypeCodeConstants.GAME_TYPE_CODE.equals(item.getParentTypeCode())) {
					lstResult.add(this.converItemToDpType(item));
				}
			}
		}
		 return lstResult;
    }

	@Override
	public String getPlanedAppRecommendPanelJson(Map<String, String[]> paramMap) {
		// 获得方案.
		Plan plan = planProcessor.process(paramMap);
		//获取方案关联的精选页数据
		return Global.planAppRecommendPanelItemJsonMap.get(plan.getId());
	}

    @Override
    public List<AppSubjectType> getPlanedAppSubjectTypeList(Map<String, String[]> paramMap) {
		// 获得方案.
    	Plan plan = planProcessor.process(paramMap);
    	return this.getPlanedAppSubjectTypeList(plan.getId());
    }
    
    @Override
    public List<AppSubjectType> getPlanedAppSubjectTypeList(String planId) {		
		// 获取方案关联的专题id列表
		List<String> subjectIdList = Global.planSubjectTypeMap.get(planId);
		// 获取所有的专题列表
		List<AppSubjectType> allSubjectTypeList = Global.getSubjectTypeList();

		// 过滤
		List<AppSubjectType> resultList = new ArrayList<AppSubjectType>();
		if(CollectionUtils.isEmpty(subjectIdList) || CollectionUtils.isEmpty(allSubjectTypeList)){
			return resultList;
		}
		
		for (String subjectId : subjectIdList) {
			for (AppSubjectType appSubjectType : allSubjectTypeList) {
				if(StringUtils.equals(subjectId, appSubjectType.getId())){
					resultList.add(appSubjectType);
					break;
				}
			}
		}
		return resultList;
    }

    @Override
    public List<AppInfoDetail> getPlanedAppDetailListByType(Map<String, String[]> paramMap, Page<AppInfoDetail> page) {
        // 1. 有用的参数： start, limit, typeId, osVersion    osVersion指的是终端的操作系统版本。如appDetail.getSystem() <= osVersion
        // 2. 需进行数据库分页.
        // 3. 分类下应用的排序处理.
        // 4. 返回的列表是当前页数据, page对象需要设置总记录数.
    	String[] objTypeId = paramMap.get("typeId");
    	String[] objOsVersion = paramMap.get("osVersion");
        String strTypeId = "";
        String strOsVersion = "";
        if (objTypeId != null && objTypeId.length > 0) {
        	strTypeId = objTypeId[0];
        }
        if (objOsVersion != null && objOsVersion.length > 0) {
        	strOsVersion = objOsVersion[0];
        }
        //方案id
        String strPlanId = planProcessor.process(paramMap).getId();  

    	List<AppInfoDetail> lstResult = new ArrayList<AppInfoDetail>();
    	List<AppInfoDetail> lstAppInfoTemp = this.getPlanedAppDetailListByType(strPlanId, strTypeId, strOsVersion);
        //分页 getBeginCount 从1开始算
        int iBiginNum = page.getBeginCount() - 1;
        if(iBiginNum < 0) iBiginNum = 0;
        int iEndNum = iBiginNum + page.getPageSize();
        int iTotal = lstAppInfoTemp.size();
        if( iEndNum  > iTotal){
        	iEndNum = iTotal;
        }
        //list.subList(n,m) 左闭右开，下标从0开始算
        lstResult = lstAppInfoTemp.subList(iBiginNum, iEndNum);
        
        //设置总数
        page.setTotalCount(iTotal);
        return lstResult;
    }
    
    @Override
	public List<AppInfoDetail> getPlanedAppDetailListByType(String planId, String typeId, String osVersion) {
		Map<String, AppInfoDetail> objAppInfoDetailMap = Global.appInfoDetailMap;
		Map<String, List<String>> objPlanDpTypeAppPackageNameMap = Global.planDpTypeAppPackageNameMap;
		List<String> lstPkgName = objPlanDpTypeAppPackageNameMap.get(planId + "_" + typeId);
		List<AppInfoDetail> lstResult = new ArrayList<AppInfoDetail>();
		if(null == lstPkgName){
			return lstResult;
		}
		
		for (String strPkgName : lstPkgName) {
			if (StringUtils.isNotBlank(strPkgName)) {
				AppInfoDetail objAppInfoDetail = objAppInfoDetailMap.get(strPkgName);
				if (StringUtils.isNotBlank(osVersion) && null != objAppInfoDetail && Double.valueOf(objAppInfoDetail.getSystem()) <= Double.valueOf(osVersion)) {
					lstResult.add(objAppInfoDetail);
				}
			}
		}

		return lstResult;
	}
    
    @Override
    public List<AppInfoDetail> getPlanedAppInfoList(Map<String, String[]> paramMap){
        // 获得方案.
        Plan plan = planProcessor.process(paramMap);
    	//获取方案关联的应用包名列表
        Set<String> appPackageNameList = Global.planAppPackageNameMap.get(plan.getId());
    	//获取所有的应用Map
        Map<String, AppInfoDetail> allAppInfoMap = Global.appInfoDetailMap;

        //过滤
        List<AppInfoDetail> resultList = new ArrayList<AppInfoDetail>();
        if(CollectionUtils.isEmpty(appPackageNameList) || null == allAppInfoMap){
			return resultList;
		}
        for (String appPackageName : appPackageNameList) {
        	if(allAppInfoMap.containsKey(appPackageName)){
        		resultList.add(allAppInfoMap.get(appPackageName));
        	}
		}

        //返回
    	return resultList;
    }
    
    
    private DpType converItemToDpType(Item item){
    	DpType dpType = new DpType();
    	dpType.setId(item.getId());
    	dpType.setTypeName(item.getName());
    	dpType.setParentTypeCode(item.getParentTypeCode());
    	dpType.setTypeImg1(item.getTypeImg1());
    	dpType.setTypeImg2(item.getTypeImg2());
    	dpType.setTypeDesc(item.getDescription());
    	return dpType;
    }

}
