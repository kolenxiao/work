package com.coship.appstore.service.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.coship.appstore.service.dto.AppInfoDTO;
import com.coship.appstore.service.dto.DpTypeDTO;
import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.ap.entity.AppThumbnail;
import com.coship.sdp.sce.dp.appstore.entity.AppStoreClient;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFileEntity;
import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;
import com.coship.sdp.sce.dp.comment.entity.AppScoreSummaryEntity;
import com.coship.sdp.sce.dp.implicit.entity.ImplicitApp;
import com.coship.sdp.sce.dp.plan.entity.Item;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.system.entity.SystemParam;
import com.coship.sdp.sce.dp.type.entity.DpType;

/**
 * 主要存储应用列表信息，提供给接口使用，减少通过SQL/HQL查询数据库的时间开销
 */
public class Global {
	/**
	 * 推荐应用列表
	 */
	public static List<AppInfoDetail> recommendAppList;

	/**
	 * 最热应用列表
	 */
	public static List<AppInfoDetail> hotAppInfoList;

	/**
	 * 最好应用列表
	 */
	public static List<AppInfoDetail> goodAppInfoList;

	/**
	 * 最新应用列表
	 */
	public static List<AppInfoDetail> newestAppInfoList;

	/**
	 * 免费应用列表
	 */
	public static List<AppInfoDetail> freeAppInfoList;

	/**
	 * 付费应用列表
	 */
	public static List<AppInfoDetail> payAppInfoList;

	/**
	 * 最好专题应用列表
	 */
	public static List<AppInfoDetail> goodSubjectAppInfoList;

	/**
	 * 最新专题应用列表
	 */
	public static List<AppInfoDetail> newestSubjectAppInfoList;

	/**
	 * 最热专题应用列表
	 */
	public static List<AppInfoDetail> hotSubjectAppInfoList;

	/**
	 * 分类推荐应用列表
	 * 2013-07-06
	 */
	public static List<AppInfoDetail> recommendAppTypeList;

	/**
	 * 最热专题应用列表
	 * 2013-8-5
	 */
	public static List<AppInfoDetail> allSubjectAppInfoList;

	/**
	 * 可用签名证书列表
	 */
	public static List<AppCertificate> enableAppCertificateList;

	/**
	 * 吊销证书列表
	 */
	public static List<AppCertificate> revokeAppCertificateList;

	/**
	 * 应用的附件列表
	 */
	public static Map<String,List<AttachmentFileEntity>> appAttachmentMap;

	/**
	 * 应用的评分
	 */
	public static Map<String,AppScoreSummaryEntity> appScoreSummaryMap;

	public static List<DpType> allDpTypeList;

	public static List<DpType> gameTypeList;

	public static List<DpType> appTypeList;

	public static List<DpType> recommentTypeList;

	public static List<ImplicitApp> impAppList;

	public static List<AppStoreClient> onLineAppStoreClientList;
	
	//-----------------------------2013-09-12-----------------------------------------//
	
    //根据分类信息存储应用信息
	public static Map<String,List<AppInfoDTO>> storeAppInfoByType = new HashMap<String,List<AppInfoDTO>>();
	
	//根据最新、最热等存储应用信息
	public static Map<String,List<AppInfoDTO>> storeAppInfoByFlag = new HashMap<String,List<AppInfoDTO>>();
	
	public static List<AppInfoDetail> tempAppInfoDetail;
	
	public static List<DpTypeDTO> recommendTypeDTOList;
	
	//首页推荐分类
	public static Map<String,List<AppInfoDTO>> storeAppInfoByRecType = new HashMap<String,List<AppInfoDTO>>();
	
	private static Object LOCK_TEMP_OBJ = new Object();
	
	// add by 2013-10-17
	public static List<AppSubjectType> subjectTypeList;

    //系统参数缓存
    public static Map<String, SystemParam> systemParamMap;
    
    //缩略图缓存
    public static Map<String, AppThumbnail> appThumbnailMap;
    
    //猜你喜欢缓存
    public static Map<String, List<AppInfoDTO>> appRelateMap = new HashMap<String,List<AppInfoDTO>>();
    
    
	
    /**
     * 缓存初始化方案Map. key=方案id, value=方案.
     */
    public static Map<String, Plan> planMap;

    /**
     * 缓存初始化条件方案Map. key=条件code_条件值_启用状态, value=方案Id列表(plan只包含id).
     */
    public static Map<String, List<Plan>> conditionPlanIdMap;

    /**
     * 缓存默认方案.
     */
    public static Plan defaultPlan;

    /**
     * 缓存方案包含的应用map. key=方案id, value=应用包名列表.
     */
    public static Map<String, Set<String>> planAppPackageNameMap;
    
    /**
     * 缓存方案包含的固定分类map. key=方案id, value=分类id列表.
     */
    public static Map<String, List<String>> planDpTypeIdMap;
    
    /**
     * 缓存方案包含的自定义分类map. key=方案id, value=自定义分类列表.
     */
    public static Map<String, List<Item>> planSelfTypeMap;

    /**
     * 缓存方案下的分类的应用map. key=方案id_分类id, value=应用包名列表.
     */
    public static Map<String, List<String>> planDpTypeAppPackageNameMap;

    /**
     * 缓存方案下的分类的应用map. key=方案id, value=应用包名列表.(用于计算方案下的所有应用)
     */
    public static Map<String, Set<String>> planIdKeyDpTypeAppPackageNameMap;

    /**
     * 缓存方案下的专题map. key=方案id, value=专题id列表.
     */
    public static Map<String, List<String>> planSubjectTypeMap;

    /**
     * 缓存方案下的精选页的json结构列表.
     */
    public static Map<String, String> planAppRecommendPanelItemJsonMap;

    /**
     * 缓存应用map. key=应用包名, value=应用.
     */
    public static Map<String, AppInfoDetail> appInfoDetailMap;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	public static List<AppInfoDetail> getRecommendAppList()
	{
		return recommendAppList;
	}

	public static void setRecommendAppList(List<AppInfoDetail> recommendAppList)
	{
		Global.recommendAppList = recommendAppList;
	}

	public static List<AppInfoDetail> getHotAppInfoList()
	{
		return hotAppInfoList;
	}

	public static void setHotAppInfoList(List<AppInfoDetail> hotAppInfoList)
	{
		Global.hotAppInfoList = hotAppInfoList;
	}

	public static List<AppInfoDetail> getGoodAppInfoList()
	{
		return goodAppInfoList;
	}

	public static void setGoodAppInfoList(List<AppInfoDetail> goodAppInfoList)
	{
		Global.goodAppInfoList = goodAppInfoList;
	}

	public static List<AppInfoDetail> getNewestAppInfoList()
	{
		return newestAppInfoList;
	}

	public static void setNewestAppInfoList(
			List<AppInfoDetail> newestAppInfoList)
	{
		Global.newestAppInfoList = newestAppInfoList;
	}

	public static List<AppInfoDetail> getFreeAppInfoList()
	{
		return freeAppInfoList;
	}

	public static void setFreeAppInfoList(List<AppInfoDetail> freeAppInfoList)
	{
		Global.freeAppInfoList = freeAppInfoList;
	}

	public static List<AppInfoDetail> getPayAppInfoList()
	{
		return payAppInfoList;
	}

	public static void setPayAppInfoList(List<AppInfoDetail> payAppInfoList)
	{
		Global.payAppInfoList = payAppInfoList;
	}

	public static List<AppInfoDetail> getGoodSubjectAppInfoList()
	{
		return goodSubjectAppInfoList;
	}

	public static void setGoodSubjectAppInfoList(
			List<AppInfoDetail> goodSubjectAppInfoList)
	{
		Global.goodSubjectAppInfoList = goodSubjectAppInfoList;
	}

	public static List<AppInfoDetail> getNewestSubjectAppInfoList()
	{
		return newestSubjectAppInfoList;
	}

	public static void setNewestSubjectAppInfoList(
			List<AppInfoDetail> newestSubjectAppInfoList)
	{
		Global.newestSubjectAppInfoList = newestSubjectAppInfoList;
	}

	public static List<AppInfoDetail> getHotSubjectAppInfoList()
	{
		return hotSubjectAppInfoList;
	}

	public static void setHotSubjectAppInfoList(
			List<AppInfoDetail> hotSubjectAppInfoList)
	{
		Global.hotSubjectAppInfoList = hotSubjectAppInfoList;
	}

	public static List<AppInfoDetail> getRecommendAppTypeList()
	{
		return recommendAppTypeList;
	}

	public static void setRecommendAppTypeList(
			List<AppInfoDetail> recommendAppList)
	{
		Global.recommendAppTypeList = recommendAppList;
	}

	public static List<AppInfoDetail> getAllSubjectAppInfoList() {
			return allSubjectAppInfoList;
	}

	public static void setAllSubjectAppInfoList(
			List<AppInfoDetail> allSubjectAppInfoList)
	{
		Global.allSubjectAppInfoList = allSubjectAppInfoList;
	}

	public static List<AppCertificate> getEnableAppCertificateList()
	{
		return enableAppCertificateList;
	}

	public static void setEnableAppCertificateList(
			List<AppCertificate> enableAppCertificateList)
	{
		Global.enableAppCertificateList = enableAppCertificateList;
	}

	public static List<AppCertificate> getRevokeAppCertificateList()
	{
		return revokeAppCertificateList;
	}

	public static void setRevokeAppCertificateList(
			List<AppCertificate> revokeAppCertificateList)
	{
		Global.revokeAppCertificateList = revokeAppCertificateList;
	}

	public static Map<String, List<AttachmentFileEntity>> getAppAttachmentMap()
	{
		return Global.appAttachmentMap;
	}

	public static void setAppAttachmentMap(
			Map<String, List<AttachmentFileEntity>> appAttachmentMap)
	{
			Global.appAttachmentMap = appAttachmentMap;	
	}

	public static Map<String, AppScoreSummaryEntity> getAppScoreSummaryMap()
	{
		return appScoreSummaryMap;
	}


	public static void setAppScoreSummaryMap(
			Map<String, AppScoreSummaryEntity> appScoreSummaryList)
	{
			Global.appScoreSummaryMap = appScoreSummaryList;
	}

	public static List<DpType> getAllDpTypeList()
	{
		return allDpTypeList;
	}

	public static void setAllDpTypeList(List<DpType> allDpTypeList)
	{
		Global.allDpTypeList = allDpTypeList;
	}

	public static List<DpType> getGameTypeList()
	{
		return gameTypeList;
	}

	public static void setGameTypeList(List<DpType> gameTypeList)
	{
		Global.gameTypeList = gameTypeList;
	}

	public static List<DpType> getAppTypeList()
	{
		return appTypeList;
	}

	public static void setAppTypeList(List<DpType> appTypeList)
	{
		Global.appTypeList = appTypeList;
	}


	public static List<DpType> getRecommentTypeList()
	{
		return recommentTypeList;
	}

	public static void setRecommentTypeList(List<DpType> recommentTypeList)
	{
		Global.recommentTypeList = recommentTypeList;
	}

	public static List<ImplicitApp> getImpAppList()
	{
		return impAppList;
	}

	public static void setImpAppList(List<ImplicitApp> impAppList)
	{
		Global.impAppList = impAppList;
	}

	public static List<AppStoreClient> getOnLineAppStoreClientList()
	{
		return onLineAppStoreClientList;
	}

	public static void setOnLineAppStoreClientList(
			List<AppStoreClient> onLineAppStoreClientList)
	{
		Global.onLineAppStoreClientList = onLineAppStoreClientList;
	}

	public static AppInfoDetail getUpdateAppBySQL(String packageName, String appId, int osVersion) {
		AppInfoDetail appDetail = null;
		List<AppInfoDetail> appInfoList = getHotAppInfoList();
		for (AppInfoDetail appDatailTemp : appInfoList) {
			if (appDatailTemp.getAppFilePackage().equals(packageName)
					&& Integer.valueOf(appDatailTemp.getSystem()) < osVersion
					&& !appDatailTemp.getId().equals(appId)) {
				appDetail = appDatailTemp;
				break;
			}
		}
		return appDetail;

	}
	
	public static AppInfoDetail getUpdateAppBySQLNew(String packageName, String version, String versionCode, int osVersion) {
		AppInfoDetail appDetail = null;
		
		List<AppInfoDetail> appInfoList = getHotAppInfoList();
		for (AppInfoDetail appDatailTemp : appInfoList) {
			if (appDatailTemp.getAppFilePackage().equals(packageName)
					&& Integer.valueOf(appDatailTemp.getSystem()) < osVersion) {
				if (Integer.valueOf(versionCode) < Integer.valueOf(appDatailTemp.getVersionCode())) {
					appDetail = appDatailTemp;
					break;
				} else if (Integer.valueOf(versionCode).equals(Integer.valueOf(appDatailTemp.getVersionCode()))) {
					if (version.compareToIgnoreCase(appDatailTemp.getVersion()) < 0) {
						appDetail = appDatailTemp;
						break;
					}
				}
			}
		}
		return appDetail;
	}
	
	

	public static List<ImplicitApp> getImplicitAppListByTeminalNumAndOsVersion(String clientModel, int osVersion){
		List<ImplicitApp> implicitApps = new ArrayList<ImplicitApp>();
		for(ImplicitApp tempApp:impAppList){
			boolean isTure1 = osVersion < Integer.valueOf(tempApp.getSystem());
			boolean isTure2 = tempApp.getImplicitType().equals("3");
			boolean isTure3 = tempApp.getTeminalNum().equals(clientModel);
			boolean isTure4 = tempApp.getTeminalNum().equals("0");
			if((isTure1||isTure2)&&(isTure3||isTure4)){
				implicitApps.add(tempApp);
			}
		}
		return implicitApps;
	}

	public static AppStoreClient getAppStoreClientUpdateVersion(int appStoreVersion, int osVersion,String clientModel){
		AppStoreClient appClien = null;
		for(AppStoreClient appClient : onLineAppStoreClientList){
			if(appClient.getVersionCode()!=appStoreVersion && appClient.getSystem() < osVersion && appClient.getTeminalNum().equals(clientModel)){
				appClien = appClient;
				break;
			}
		}
		return appClien;

	}

   public static void setUpAppbyType(String typeId,List<AppInfoDTO> appInfoDTOList){
		if (!StringUtils.isEmpty(typeId) && null != appInfoDTOList && appInfoDTOList.size() >0)
		{
			storeAppInfoByType.put(typeId, appInfoDTOList);
		}
   }
   
	public static List<AppInfoDTO> getAppByType(String typeId)
	{
		List<AppInfoDTO> list = storeAppInfoByType.get(typeId);

		if (null == list)
		{
			list = new ArrayList<AppInfoDTO>();
		}

		return list;
	}
   
	
    public static void setUpAppByFlag(String rankType,List<AppInfoDTO> appInfoDTOList){
		   
		if (!StringUtils.isEmpty(rankType) && null != appInfoDTOList && appInfoDTOList.size() >0)
		{
			storeAppInfoByFlag.put(rankType, appInfoDTOList);
		}
	  }
	   
	public static List<AppInfoDTO> getAppByFlag(String rankType)
	{
		List<AppInfoDTO> list = storeAppInfoByFlag.get(rankType);

		if (null == list)
		{
			list = new ArrayList<AppInfoDTO>();
		}

		return list;
	}

	public static List<AppInfoDetail> getTempAppInfoDetail()
	{
		synchronized (LOCK_TEMP_OBJ) {
			return tempAppInfoDetail;
		}
		
	}

	public static void setTempAppInfoDetail(List<AppInfoDetail> tempAppInfoDetail)
	{
		synchronized (LOCK_TEMP_OBJ) {
			if(tempAppInfoDetail != null){
				getTempAppInfoDetail().addAll(tempAppInfoDetail);
			}
		}
	}

	public static List<DpTypeDTO> getRecommendTypeDTOList()
	{
		return recommendTypeDTOList;
	}

	public static void setRecommendTypeDTOList(List<DpTypeDTO> recommendTypeDTOList)
	{
		Global.recommendTypeDTOList = recommendTypeDTOList;
	}
	
	
	public static void setUpAppbyRecType(String typeId,List<AppInfoDTO> appInfoDTOList){
		if (!StringUtils.isEmpty(typeId) && null != appInfoDTOList && appInfoDTOList.size() >0)
		{
			storeAppInfoByType.put(typeId, appInfoDTOList);
		}
	}
	   
	public static List<AppInfoDTO> getAppByRecType(String typeId)
	{
		List<AppInfoDTO> list = storeAppInfoByType.get(typeId);

		if (null == list)
		{
			list = new ArrayList<AppInfoDTO>();
		}

		return list;
	}
	
    public static List<AppSubjectType> getSubjectTypeList()
    {
        return subjectTypeList;
    }

    public static void setSubjectTypeList(List<AppSubjectType> subjectTypeList)
    {
        Global.subjectTypeList = subjectTypeList;
    }
}