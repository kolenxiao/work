/*
 * 文件名称：AppStoreAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd.
 *        Copyright 2010-2020,  All rights reserved
 * 描    述：应用商店服务端
 * 创 建 人：wangchenbo/906055
 * 创建时间：2012-9-6
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.appstore.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coship.appstore.cache.AppStoreCacheService;
import com.coship.appstore.facade.PlanFacade;
import com.coship.appstore.fuc.FucUser;
import com.coship.appstore.iuc.DvbAuthUtils;
import com.coship.appstore.plan.processor.PlanProcessor;
import com.coship.appstore.service.common.AppConstants;
import com.coship.appstore.service.common.Global;
import com.coship.appstore.service.dto.AddScoreResultDTO;
import com.coship.appstore.service.dto.AppCommentInfoDTO;
import com.coship.appstore.service.dto.AppInfoDTO;
import com.coship.appstore.service.dto.AppRankDTO;
import com.coship.appstore.service.dto.AppRecommendPanelDTO;
import com.coship.appstore.service.dto.AppRecommendPanelItemDTO;
import com.coship.appstore.service.dto.CertificateDTO;
import com.coship.appstore.service.dto.DpTypeDTO;
import com.coship.appstore.service.dto.ImplicitAppInfoDTO;
import com.coship.appstore.service.dto.PageDTO;
import com.coship.appstore.service.dto.ResponseDTO;
import com.coship.appstore.service.dto.SystemParamDTO;
import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.ap.entity.AppThumbnail;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.ap.service.AppInfoDetailService;
import com.coship.sdp.sce.dp.ap.service.AppThumbnailService;
import com.coship.sdp.sce.dp.ap.service.DpAppInfoService;
import com.coship.sdp.sce.dp.ap.service.HandAppRelateService;
import com.coship.sdp.sce.dp.appstore.entity.AppStoreClient;
import com.coship.sdp.sce.dp.appstore.entity.MyApp;
import com.coship.sdp.sce.dp.appstore.entity.MyFavorite;
import com.coship.sdp.sce.dp.appstore.entity.UserApp;
import com.coship.sdp.sce.dp.appstore.service.AppStoreClientService;
import com.coship.sdp.sce.dp.appstore.service.MyAppService;
import com.coship.sdp.sce.dp.appstore.service.MyFavoriteService;
import com.coship.sdp.sce.dp.appstore.service.UserAppService;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFileEntity;
import com.coship.sdp.sce.dp.atachmentfile.service.AttachmentFileService;
import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;
import com.coship.sdp.sce.dp.certificate.service.AppCertificateService;
import com.coship.sdp.sce.dp.comment.entity.AppCommentInfo;
import com.coship.sdp.sce.dp.comment.entity.AppScoreSummaryEntity;
import com.coship.sdp.sce.dp.comment.service.AppCommentService;
import com.coship.sdp.sce.dp.comment.service.impl.AppCommentHelper;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.common.PlanConstants;
import com.coship.sdp.sce.dp.device.entity.DeviceInfo;
import com.coship.sdp.sce.dp.device.service.impl.DeviceInfoServiceImpl;
import com.coship.sdp.sce.dp.exception.BusinessException;
import com.coship.sdp.sce.dp.imoker.service.UserFeedbackService;
import com.coship.sdp.sce.dp.implicit.entity.ImplicitApp;
import com.coship.sdp.sce.dp.implicit.service.ImplicitAppService;
import com.coship.sdp.sce.dp.operation.entity.UserOperation;
import com.coship.sdp.sce.dp.operation.service.impl.UserOperationServiceImpl;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.plan.entity.PlanItem;
import com.coship.sdp.sce.dp.plan.service.PlanItemService;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanelItem;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendPanelItemService;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendPanelService;
import com.coship.sdp.sce.dp.recommend.service.AppTypeRecommendService;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.subject.service.AppSubjectTypeService;
import com.coship.sdp.sce.dp.system.entity.SystemParam;
import com.coship.sdp.sce.dp.system.service.SystemParamService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.sce.dp.utils.AndroidVersionUtil;
import com.coship.sdp.sce.dp.utils.HttpUtil;
import com.coship.sdp.sce.dp.utils.MethodsUtil;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.DateUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 
 * 应用商店服务端调用接口 提供给应用商店客户端的查询、新增、修改、删除等接口
 * 
 * @author wangchenbo/906055
 * @version V100R001B010, 2012-9-6
 * @since 应用商店服务端/V100R001B010
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AppStoreActionV4
{

    private static final Logger logger = LoggerFactory.getLogger(AppStoreActionV4.class);
    private static final String MODULE = AppStoreActionV4.class.getName();


	@Autowired
	private DpAppInfoService dpAppInfoService;// 应用服务层对象
	@Autowired
	private MyAppService myAppService;// 我的应用接口服务层
	@Autowired
	private MyFavoriteService myFavoriteService;// 我的收藏接口服务层
    @Autowired
    private DpTypeService dpTypeService;//分类类型接口
    @Autowired
    private AppCommentService appCommentService;//应用评论接口
    @Autowired
    private AppSubjectTypeService appSubjectTypeService;//专题管理服务接口
    @Autowired
    private AppCertificateService appCertificateService;//签名证书
    @Autowired
    private AppStoreClientService appStoreClientService;//应用商店客户端服务类
    @Autowired
    private AppInfoDetailService appInfoDetailService;//应用详细信息
    @Autowired
    private AttachmentFileService attachmentFileService;//应用的附件
    @Autowired
    private ImplicitAppService implicitAppService;//隐式应用服务类
    @Autowired
    private DeviceInfoServiceImpl deviceInfoServiceImpl;//终端设备服务
    @Autowired
    private UserOperationServiceImpl userOperationServiceImpl;//终端设备操作记录
    @Autowired
    private UserFeedbackService userFeedbackService;
    @Autowired
    private AppRecommendPanelItemService appRecommendPanelItemService;
    @Autowired
    private AppRecommendPanelService appRecommendPanelService;
    @Autowired
    private AppTypeRecommendService appTypeRecommendService;//应用分类推荐业务逻辑处理
    @Autowired
    private PlanFacade planFacade;//方案处理接口类
    @Autowired
    private AppStoreCacheService appStoreCacheService;
    @Autowired
    private PlanItemService planItemService;
    @Autowired
    private SystemParamService systemParamService;
    @Autowired
    private PlanProcessor planProcessor;
	@Autowired		
	private AppThumbnailService appThumbnailService;
	@Autowired
	private HandAppRelateService handAppRelateService;
	@Autowired
	private UserAppService userAppService;


    //返回消息对象
    private ResponseDTO responseDTO = new ResponseDTO();
    private HttpServletRequest request;
    private HttpServletResponse response;
    //起始 页号
    protected int start = 1;
    //每页行数
    protected int limit = 10;
    //获取当前机顶盒id
    private String userId;
    //评论用户昵称
    private String userName;
    //获取当前应用id
    private String appId;
    //应用包名
    private String packageName;
    //应用的包名
    private String pacNames;
    //应用包名
    private String appPackageNames;
    //应用版本
    private String versions;
    //存储apk的xml中android:versionCode字段
    private String versionCodes;
    //分类id
    private String typeId;
    //应用评分值
    private int score;
    //专题id
    private String subjectId;
    //排序
    private String orderBy;
    //排行标识
    private String rankType;
    //应用的主键
    private String appIds;
    //终端安装的应用的版本号
    private int appStoreVersion;
    //终端系统版本号
    private int osVersion;
    //应用的签名证书id
    private String appCertificateId;
    //终端型号
    private String clientModel;
    //设备信息
    private String deviceType;
    private String deviceSerialNo;
    private String deviceMac;
    private String deviceIp;
    private String appStoreClientInstallDate;
    private String appStoreClientVersion;
    private int optType;
    private String optContent;
    private String optContentId;
    private String param1;
    private String param2;
    private String param3;
    private String feedbackText;
    private String userEmail;
    private int oldScore;
    private String channelId;
    private String cityCode;
    private String userCode;
    private String token;
    
    /**
     * 应用类型标记 
     * 0：查询所有的应用类型包括游戏 
     * 1：查询应用所有类型
     * 2：查询游戏所有类型 
     * null：默认查询应用所有类型
     */
    private String flag;

    public AppStoreActionV4() {
    }
    
	public AppStoreActionV4(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
 
	/**
	 * 按关键字查询应用列表
	 */
	public void getAppListByKeyword() {
		long t1 = System.currentTimeMillis();
		logger.info("getAppListByKeyword([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });
		

		String keyWord = request.getParameter("keyWord");
		if (isNull(keyWord) || (osVersion == 0)) {
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MUST_PARAM_NULL, null);
			write(JSON.toJSONString(responseDTO));
		} else {
			initDto(keyWord);
		}

		long t2 = System.currentTimeMillis();
		logger.info("getAppListByKeyword([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

    

	/**
	 * 获取应用类型列表.
	 */
    @SuppressWarnings("unchecked")
	public void getAppTypeList() {
		long t1 = System.currentTimeMillis();
		logger.info("getAppTypeList([{}]) entry:{}", t1, JSON.toJSONString(request.getParameterMap()));
		
		try {
			//获取分类列表
            List<DpType> dpTypeList = planFacade.getPlanedSelfTypeList(request.getParameterMap());

			// 转换对象
            String url = initURL(request.getLocalAddr(), request.getServerPort());
            List<DpTypeDTO> dpTypeDtoList = new ArrayList<DpTypeDTO>();
			for (DpType dpType : dpTypeList) {
				dpTypeDtoList.add(toDpTypeDTO(dpType, url));
			}
			// 转换成json格式返回给appstore client
			if (isNull(dpTypeDtoList)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MSG_NULL, null);
			} else {
				generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS, null, dpTypeDtoList);
			}

		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		// 返回结果
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getAppTypeList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	
	/**
	 * 获取推荐类型列表.
	 */
	public void getRecommentTypeList() {
		long t1 = System.currentTimeMillis();
		logger.info("getRecommentTypeList([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });
		
		try {
			String url = initURL(request.getLocalAddr(), request.getServerPort());

			// List<DpType> recommentTypeList =
			// dpTypeService.getRecommentTypeList();
			List<DpType> recommentTypeList = Global.getRecommentTypeList();

			List<DpTypeDTO> dpTypeDtoList = new ArrayList<DpTypeDTO>();
			// 转换对象
			for (DpType dpType : recommentTypeList) {
				dpTypeDtoList.add(toDpTypeDTO(dpType, url));
			}
			// 转换成json格式返回给appstore client
			if (isNull(dpTypeDtoList)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MSG_NULL, null);
			} else {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_SUCESS, null, dpTypeDtoList);
			}

		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		// 返回结果
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getRecommentTypeList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	/**
	 * 获取分类下的应用分页列表
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
    @SuppressWarnings("unchecked")
    public void getTypeAppInfoList() {
		long t1 = System.currentTimeMillis();
		logger.info("getTypeAppInfoList([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (StringUtils.isEmpty(typeId) || StringUtils.isEmpty(orderBy) || osVersion == 0) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				// 分页查询已上架指定专题的app应用
				Page<AppInfoDetail> page = new Page<AppInfoDetail>();
				page.setPageSize(limit);
				page.setCurrentPage(start);
				page.setBeginCount(((start - 1) * limit + 1));
                List<AppInfoDetail> appInfoList = planFacade.getPlanedAppDetailListByType(
                        request.getParameterMap(), page);

				// 返回给appStore client的数据信息列表
				List<AppInfoDTO> appInfoStoreList = new ArrayList<AppInfoDTO>();
				for (AppInfoDetail appInfo : appInfoList) {
					// 获得已经初始化的dto对象
					appInfoStoreList.add(initAppInfoToDto(appInfo));
				}

				// 转换成json格式发送出去
				if (isNull(appInfoStoreList)) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NULL, null);
				} else {
					PageDTO<AppInfoDTO> appPage = initAppInfoPageDTO(page, appInfoStoreList);
					generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS, null, appPage);
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getTypeAppInfoList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	/**
	 * 获取我的应用列表.
	 */
	public void getMyAppList() {
		long t1 = System.currentTimeMillis();
		logger.info("getMyAppList([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(userId)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				List<String> appPkgNameList = myAppService.findByUid(userId);
				List<AppInfoDTO> appInfoStoreList = new ArrayList<AppInfoDTO>();

				if (isNull(appPkgNameList)) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NULL, null);
				} else {
					for (String appPkgName : appPkgNameList) {
						for (AppInfoDetail e : Global.getNewestAppInfoList()) {
							if(appPkgName.equals(e.getAppFilePackage())){
								AppInfoDTO appInfoDTO = initAppInfoToDto(e);
								appInfoStoreList.add(appInfoDTO);
								break;
							}
						}
					}
					// 转换成json格式发送出去 begin
					if (isNull(appInfoStoreList)) {
						generateResposeDTO(responseDTO,
								AppConstants.RESPOSE_STATUS_ERROR,
								AppConstants.MSG_NULL, null);
					} else {
						generateResposeDTO(responseDTO,
								AppConstants.RESPOSE_STATUS_SUCESS, null,
								appInfoStoreList);
					}
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getMyAppList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

    
	/**
	 * 添加我的应用列表.
	 */
	public void addMyApp() {
		long t1 = System.currentTimeMillis();
		logger.info("addMyApp([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(userId) || (isNull(appId) && isNull(packageName))) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				//判断我的应用是否存在
				DpAppInfo dpAppInfoTemp = null;
				if(StringUtils.isNotEmpty(packageName)){
					dpAppInfoTemp = dpAppInfoService.findUniqueByPackageName(packageName);
				}else{
					dpAppInfoTemp = dpAppInfoService.findAppInfo(appId);
				}
				if (dpAppInfoTemp == null) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NOT_EXISTS, null);
				} else {
					appId = dpAppInfoTemp.getId();
					packageName = dpAppInfoTemp.getAppFilePackage();
					List<MyApp> myAppList = myAppService.findMyAppByUidAndPkgName(userId, packageName);
					if(null == myAppList){
						//新增
						MyApp myApp = this.getMyApp(appId, userId, packageName);
						myAppService.saveMyApp(myApp);
					}else{
						//升级
						for (MyApp myApp : myAppList) {
							myAppService.deleteMyApp(myApp);
						}
						MyApp myApp = this.getMyApp(appId, userId, packageName);
						myAppService.saveMyApp(myApp);
					}
					
					AppInfoDetail appInfoDetail = appInfoDetailService.findAppInfoDetail(appId);
					if (appInfoDetail != null) {
						AppInfoDTO appInfoDTO = initAppInfoToDto(appInfoDetail);
						generateResposeDTO(responseDTO,
								AppConstants.RESPOSE_STATUS_SUCESS,
								AppConstants.MSG_SUCCESS, appInfoDTO);
					} else {
						generateResposeDTO(responseDTO,
								AppConstants.RESPOSE_STATUS_ERROR,
								AppConstants.MSG_SYS_ERROR, null);
					}
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("addMyApp([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });

	}

	/**
	 * 删除我的应用列表.
	 */
	public void delMyApp() {
		long t1 = System.currentTimeMillis();
		logger.info("delMyApp([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(userId) || (isNull(appId) && isNull(packageName))) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				List<MyApp> myAppList = null;
				if(StringUtils.isNotEmpty(packageName)){
					myAppList = myAppService.findMyAppByUidAndPkgName(userId, packageName);
				}else{
					myAppList = myAppService.findMyApp(userId, appId);
				}
				if(null != myAppList){
					for (MyApp myApp : myAppList) {
						myAppService.deleteMyApp(myApp);
					}
				}
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_SUCESS, null, null);
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("delMyApp([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	
	/**
	 * 获取我的收藏.
	 */
	public void getMyFavorite() {
		long t1 = System.currentTimeMillis();
		logger.info("getMyFavorite([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(userId)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				List<String> appIdList = myFavoriteService.findByUid(userId);

				List<AppInfoDTO> appInfoStoreList = new ArrayList<AppInfoDTO>();
				if (isNull(appIdList)) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NULL, null);
				} else {
					for (String appId : appIdList) {
						AppInfoDetail appinfoDetail = appInfoDetailService.findAppInfoDetail(appId);
						if (null != appinfoDetail) {
							AppInfoDTO appInfoDTO = initAppInfoToDto(appinfoDetail);
							appInfoStoreList.add(appInfoDTO);
						}
					}

					if (isNull(appInfoStoreList)) {
						generateResposeDTO(responseDTO,
								AppConstants.RESPOSE_STATUS_ERROR,
								AppConstants.MSG_NULL, null);
					} else {
						generateResposeDTO(responseDTO,
								AppConstants.RESPOSE_STATUS_SUCESS, null,
								appInfoStoreList);
					}
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getMyFavorite([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	
	/**
	 * 添加我的收藏.
	 */
	public void addMyFavorite() {
		long t1 = System.currentTimeMillis();
		logger.info("addMyFavorite([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(userId) || isNull(appId)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				// 添加我的收藏中的应用不存在
				DpAppInfo dpAppInfoTemp = dpAppInfoService.findAppInfo(appId);
				if (dpAppInfoTemp == null) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NOT_EXISTS, null);
				} else {
					List<MyFavorite> myFavoriteList = myFavoriteService.findMyFavorite(userId, appId);

					// 检查是否已经收藏过了应用，没有就添加记录
					if (isNull(myFavoriteList)) {
						myFavoriteService.saveMyFavorite(getMyFavorite(appId, userId));
						generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS, null, null);
					} else {
						generateResposeDTO(responseDTO,
								AppConstants.RESPOSE_STATUS_ERROR,
								AppConstants.MSG_EXISTS, null);
					}
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("addMyFavorite([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });

	}

	
	/**
	 * 删除我的收藏.
	 */
	public void delMyFavorite() {
		long t1 = System.currentTimeMillis();
		logger.info("delMyFavorite([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(userId) || isNull(appId)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				List<MyFavorite> myFavoriteList = myFavoriteService.findMyFavorite(userId, appId);

				if (isNull(myFavoriteList)) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NULL, null);
				} else {
					for (MyFavorite myFavorite : myFavoriteList) {
						myFavoriteService.deleteMyFavorite(myFavorite);
					}
					responseDTO.setRespseStatus(AppConstants.MSG_SUCCESS);
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("delMyFavorite([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}
	

	/**
	 * 获取用户拥有的应用列表.
	 */
	public void getUserAppList() {
		long t1 = System.currentTimeMillis();
		logger.info("getUserAppList([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(userCode)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				//获取拥有的应用Map
				Map<String, UserApp> userAppMap = userAppService.search(userCode);
				
				//转化成AppInfoDTO列表
				List<AppInfoDTO> appInfoList = new ArrayList<AppInfoDTO>();
				for (String appPkgName : userAppMap.keySet()) {
					for (AppInfoDetail e : Global.getNewestAppInfoList()) {
						if(appPkgName.equals(e.getAppFilePackage())){
							AppInfoDTO appInfoDTO = initAppInfoToDto(e);
							appInfoList.add(appInfoDTO);
							break;
						}
					}
				}

				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_SUCESS, null, appInfoList);
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					e.getMessage(), null);
		}
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getUserAppList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}
	
    
	/**
	 * 添加用户拥有的应用.
	 */
	public void addUserApp() {
		long t1 = System.currentTimeMillis();
		logger.info("addUserApp([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(userCode) || isNull(packageName)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				//判断我的应用是否存在
				DpAppInfo dpAppInfoTemp = dpAppInfoService.findUniqueByPackageName(packageName);
				if (dpAppInfoTemp == null) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NOT_EXISTS, null);
				} else {
					userAppService.save(userCode, packageName);
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_SUCESS, null, null);
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					e.getMessage(), null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("addUserApp([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });

	}
	

	/**
	 * 删除用户拥有的应用
	 */
	public void delUserApp() {
		long t1 = System.currentTimeMillis();
		logger.info("delUserApp([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(userCode) || isNull(packageName)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				userAppService.delete(userCode, packageName);
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_SUCESS, null, null);
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					e.getMessage(), null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("delUserApp([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	
	/**
	 * 根据appId 获取详情.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doDetail() {
		long t1 = System.currentTimeMillis();
		Map paramMap = request.getParameterMap();
		logger.info("doDetail([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(paramMap) });

		try {
			// 判断packageName是否为空
			if (isNull(packageName)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				AppInfoDetail appInfo = Global.appInfoDetailMap.get(packageName);
				if (null == appInfo) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NULL, null);
				} else {
					// 转换成DTO
					AppInfoDTO appInfoDto = initAppInfoToDto(appInfo);
					
					//缩略图处理
					this.thumbnailProcess(appInfoDto);
					
					//某些dvb用户需要进行鉴权
					boolean bl = this.hasPrivilege(packageName, paramMap);
					if(bl){
						appInfoDto.setHasPrivilege(1);//有权限
					}else{
						appInfoDto.setHasPrivilege(0);//无权限
					}
					
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_SUCESS, null,
							appInfoDto);
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("doDetail([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}


	/**
	 * 查询应用评论.
	 */
	public void getAppComment() {
		long t1 = System.currentTimeMillis();
		logger.info("getAppComment([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(packageName)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				// 分页查询已上架的app应用
				Page<AppCommentInfo> page = new Page<AppCommentInfo>();
				page.setPageSize(limit);
				page.setCurrentPage(start);
				appCommentService.listAppCommentInfoByPacName(page, packageName);
				List<AppCommentInfo> appCommentInfoList = page.getResultList();

				// 返回给appStore client的数据信息列表
				List<AppCommentInfoDTO> appCommentInfoStoreList = new ArrayList<AppCommentInfoDTO>();
				for (AppCommentInfo appCommentInfo : appCommentInfoList) {
					// 获得已经初始化的dto对象
					appCommentInfoStoreList.add(initAppCommentInfoDto(appCommentInfo));
				}
				// 转换成json格式发送出去 begin 1
				if (isNull(appCommentInfoStoreList)) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NULL, null);
				} else {
					PageDTO<AppCommentInfoDTO> commentDtoPage = new PageDTO<AppCommentInfoDTO>();
					commentDtoPage.setResultList(appCommentInfoStoreList);
					commentDtoPage.setCurrentPage(page.getCurrentPage());
					commentDtoPage.setHasNextPage(page.isHasNextPage());
					commentDtoPage.setHasPrePage(page.isHasPrePage());
					commentDtoPage.setPageSize(page.getPageSize());
					commentDtoPage.setTotalCount(page.getTotalCount());
					commentDtoPage.setTotalPage(page.getTotalPage());
					generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS, null,
							commentDtoPage);
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getAppComment([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	
	/**
	 * 添加应用评论.
	 */
	public void addAppComment() {
		long t1 = System.currentTimeMillis();
		logger.info("addAppComment([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			String commentContent = request.getParameter("commentContent");
			if (isNull(userId) || isNull(appId) || isNull(commentContent)
					|| isNull(userName) || (score == 0)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				DpAppInfo dpAppInfo = dpAppInfoService.findAppInfo(appId);
				if (dpAppInfo != null) {
					AppCommentInfo appCommentInfo = appCommentService.findUnique(userId, dpAppInfo.getAppFilePackage());

					if (null == appCommentInfo) // 新增
					{
						appCommentService.saveAppCommentInfo(getAppCommentInfo(
								appId, userId, userName, commentContent, score,
								dpAppInfo.getAppFilePackage()));
						AppInfoDetail appInfoDetail = appInfoDetailService.findAppInfoDetail(appId);
						if (appInfoDetail != null) {
							AppInfoDTO appInfoDTO = initAppInfoToDto(appInfoDetail);
							generateResposeDTO(responseDTO,
									AppConstants.RESPOSE_STATUS_SUCESS,
									AppConstants.MSG_SUCCESS, appInfoDTO);
						} else {
							generateResposeDTO(responseDTO,
									AppConstants.RESPOSE_STATUS_ERROR,
									AppConstants.MSG_SYS_ERROR, null);
						}
					} else if (isNull(appCommentInfo.getCommentContent())) // 更新
					{
						appCommentInfo.setCommentContent(commentContent);
						appCommentService.saveAppCommentInfo(appCommentInfo);

						AppInfoDetail appInfoDetail = appInfoDetailService
								.findAppInfoDetail(appId);

						if (appInfoDetail != null) {
							AppInfoDTO appInfoDTO = initAppInfoToDto(appInfoDetail);
							generateResposeDTO(responseDTO,
									AppConstants.RESPOSE_STATUS_SUCESS,
									AppConstants.MSG_SUCCESS, appInfoDTO);
						} else {
							generateResposeDTO(responseDTO,
									AppConstants.RESPOSE_STATUS_ERROR,
									AppConstants.MSG_SYS_ERROR, null);
						}
					} else {
						generateResposeDTO(responseDTO,
								AppConstants.RESPOSE_STATUS_ERROR,
								AppConstants.MSG_EXISTS, null);
					}
				} else {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NOT_EXISTS, null);
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("addAppComment([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	
	/**
	 * 根据应用的appid查询应用评分.
	 */
	public void getAppScore() {
		long t1 = System.currentTimeMillis();
		logger.info("getAppScore([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(packageName)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				AppScoreSummaryEntity appScoreSummary = appCommentService.getAppScoreSummaryByPackageName(packageName);
				if (null == appScoreSummary) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NULL, null);
				} else {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_SUCESS, null,
							appScoreSummary);
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getAppScore([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	
	/**
	 * 添加应用评分.
	 */
	public void addAppScore() {
		long t1 = System.currentTimeMillis();
		logger.info("addAppScore([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (isNull(userId) || isNull(appId)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else if (isScoreRange(score)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MSG_WRONG_NUMBER, null);
			} else {
				
				DpAppInfo dpAppInfo = dpAppInfoService.findAppInfo(appId);
				AppCommentInfo appCommentInfo = appCommentService.findUnique(userId, dpAppInfo.getAppFilePackage());

				// 设置返回信息
				List<NameValuePair> paramList = new ArrayList<NameValuePair>(); // 刷新缓存参数
				AddScoreResultDTO resultDTO = new AddScoreResultDTO();
				if (null == appCommentInfo) {
					// 新增
					appCommentService.saveAppCommentInfo(getAppCommentInfo(
							appId, userId, userName, null, score,
							dpAppInfo.getAppFilePackage()));

					resultDTO.setIsRepeatScore(0);// 0-首次评分
				} else {
					paramList.add(new BasicNameValuePair("oldScore", ""
							+ appCommentInfo.getScore()));
					// 更新
					appCommentInfo.setScore(score);
					appCommentService.updateAppCommentInfo(appCommentInfo);

					resultDTO.setIsRepeatScore(1);// 1-重复评分
				}

				// 通知刷新评分缓存
				paramList.add(new BasicNameValuePair("score", "" + score));
				paramList.add(new BasicNameValuePair("packageName", dpAppInfo.getAppFilePackage()));
				HttpUtil.updateAppScore(paramList);
				
				//返回
				AppScoreSummaryEntity summaryEntity = Global.getAppScoreSummaryMap().get(dpAppInfo.getAppFilePackage());
				resultDTO.setAvgScore(summaryEntity.getAvgScore());
				resultDTO.setScoreCount(summaryEntity.getScoreCount());
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_SUCESS, null, resultDTO);
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("addAppScore([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	
	/**
	 * 获取应用专题列表
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getAppSubjectList() {
		long t1 = System.currentTimeMillis();
		Map paramMap = request.getParameterMap();
		logger.info("getAppSubjectList([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(paramMap) });

		try {
			// 获取应用专题列表
			List<AppSubjectType> appSubjectTypeList = planFacade.getPlanedAppSubjectTypeList(paramMap);

			// 返回给appStore client的数据信息列表
			List<DpTypeDTO> appSubjectList = new ArrayList<DpTypeDTO>();
			for (AppSubjectType appSubjectType : appSubjectTypeList) {
				// 获得已经初始化的dto对象
				appSubjectList.add(initAppSubjectInfoDTO(appSubjectType));
			}

			// 转换成json格式发送出去
			if (isNull(appSubjectList)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MSG_NULL, null);
			} else {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_SUCESS, null,
						appSubjectList);
			}

		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getAppSubjectList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	
	/**
	 * 获取专题下的应用列表
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
    public void getSubjectAppInfoList() {
		long t1 = System.currentTimeMillis();
        logger.info("getSubjectAppInfoList([{}]) entry:{}",
                new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (StringUtils.isEmpty(subjectId) || StringUtils.isEmpty(orderBy) || osVersion == 0) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				// 分页查询已上架指定专题的app应用
				Page<AppInfoDetail> page = new Page<AppInfoDetail>();
				page.setPageSize(limit);
				page.setCurrentPage(start);
				page.setBeginCount(((start - 1) * limit + 1));

				List<AppInfoDetail> appInfoList = filterSubjectAppDetailList(page);

				// 返回给appStore client的数据信息列表
				List<AppInfoDTO> appInfoStoreList = new ArrayList<AppInfoDTO>();
				for (AppInfoDetail appInfo : appInfoList) {
					// 获得已经初始化的dto对象
					appInfoStoreList.add(initAppInfoToDto(appInfo));
				}

                PageDTO<AppInfoDTO> appPage = initAppInfoPageDTO(page, appInfoStoreList);

                // 鉴权处理.
                dvbSubjectTypeAuth(appPage);

                generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS, null, appPage);
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getSubjectAppInfoList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

    /**
     * 设置用户是否有使用套餐的权限.
     * 
     * @param paramMap
     * @param appPage
     */
    private void dvbSubjectTypeAuth(PageDTO<AppInfoDTO> appPage) {
        List<AppSubjectType> subjectTypeList = Global.getSubjectTypeList();
        String productCode = null;
        for (AppSubjectType subjectType : subjectTypeList) {
            String id = subjectType.getId();
            if (id.equals(subjectId)) {
                productCode = subjectType.getProductCode();
                break;
            }
        }
        // 有产品编码，表示需要付费的套餐，就需要做鉴权。
        if (StringUtils.isNotBlank(productCode)) {
            // 检查userCode是否有权限使用productCode对应的专题下的应用.
            boolean hasPrivilege = DvbAuthUtils.dvbAuthority(userCode, cityCode, new String[] { productCode });
            appPage.setHasPrivilege(hasPrivilege ? 1 : 0);
        }
    }

	/**
	 * 获取推荐应用列表
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	public void getRecommendAppList() {
		long t1 = System.currentTimeMillis();
		logger.info("getRecommendAppList([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (osVersion == 0) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				List<AppInfoDetail> appInfoList = Global.getRecommendAppList();
				List<AppInfoDetail> tempAppInfoList = filterRecommendList(appInfoList);

				Page<AppInfoDetail> page = new Page<AppInfoDetail>();
				page.setPageSize(limit);
				page.setCurrentPage(start);
				page.setBeginCount(((start - 1) * limit + 1));
				page.setTotalCount(tempAppInfoList.size());

				// 返回给appStore client的数据信息列表
				List<AppInfoDTO> appInfoStoreList = new ArrayList<AppInfoDTO>();
				for (AppInfoDetail appInfo : tempAppInfoList) {
					// 获得已经初始化的dto对象
					appInfoStoreList.add(initAppInfoToDto(appInfo));
				}

				// 构造对象，以便转换为json对象
				setResponseObject(appInfoStoreList, page);
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getRecommendAppList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	
	/**
	 * 根据推荐类型获取推荐应用列表
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	public void getRecommendAppListByType() {
		long t1 = System.currentTimeMillis();
		logger.info("getRecommendAppListByType([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (osVersion == 0 || StringUtils.isEmpty(typeId)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				List<AppInfoDetail> appInfoList = Global.getRecommendAppTypeList();
				List<AppInfoDetail> tempAppInfoList = filterRecommendList(appInfoList);

				Page<AppInfoDetail> page = new Page<AppInfoDetail>();
				page.setPageSize(limit);
				page.setCurrentPage(start);
				page.setBeginCount(((start - 1) * limit + 1));
				page.setTotalCount(tempAppInfoList.size());// 修改为满足条件的记录数

				// 返回给appStore client的数据信息列表
				List<AppInfoDTO> appInfoStoreList = new ArrayList<AppInfoDTO>();
				for (AppInfoDetail appInfo : tempAppInfoList) {
					// 获得已经初始化的dto对象
					appInfoStoreList.add(initAppInfoToDto(appInfo));
				}

				// 构造对象，以便转换为json对象
				setResponseObject(appInfoStoreList, page);
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getRecommendAppListByType([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}
    

	/**
	 * 获取应用排行列表
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	public void getRankAppList() {
		long t1 = System.currentTimeMillis();
		logger.info("getRankAppList([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (StringUtils.isEmpty(rankType) || osVersion == 0) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
				write(JSON.toJSONString(responseDTO));
				return;
			}
			Page<AppInfoDetail> page = new Page<AppInfoDetail>();
			page.setPageSize(limit);
			page.setCurrentPage(start);
			page.setBeginCount(((start - 1) * limit + 1));

			List<AppInfoDetail> appInfoList = filterAppDetailList(page, rankType);
			logger.info("appInfoList size is :" + appInfoList.size());

			// 返回给appStore client的数据信息列表
			List<AppInfoDTO> appInfoStoreList = new ArrayList<AppInfoDTO>();
			for (AppInfoDetail appInfo : appInfoList) {
				// 获得已经初始化的dto对象
				appInfoStoreList.add(initAppInfoToDto(appInfo));
			}

			// 构造对象，以便转换为json对象
			setResponseObject(appInfoStoreList, page);
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getRankAppList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}
	
	/**
	 * 获取应用更新列表
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	public void getUpdateAppList() {
		long t1 = System.currentTimeMillis();
		logger.info("getUpdateAppList([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			int versionFlag = 0;//接口旧版本
			if(StringUtils.isEmpty(appIds)){
				versionFlag = 1;//接口新版本
			}
			
			//验证参数
			String errmsg = null;
			if (StringUtils.isBlank(appPackageNames) || osVersion == 0) {
				errmsg = "参数错误";
			}else if(1 == versionFlag){
				if(StringUtils.isBlank(versions) || StringUtils.isBlank(versionCodes)){
					errmsg = "参数错误";
				}
			}
			if(StringUtils.isNotBlank(errmsg)){
				logger.error("getUpdateAppList([{}]) fail, errmsg={}", new Object[] { t1, errmsg });
				generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
						errmsg, null);
				write(JSON.toJSONString(responseDTO));
				return;
			}
			
			//获取有升级的应用
			String[] packageNameArr = StringUtils.split(appPackageNames, ",");
			List<AppInfoDTO> updateAppInfoList = new ArrayList<AppInfoDTO>();
			for (int i = 0; i < packageNameArr.length; i++) {
				AppInfoDetail updateInfoTemp = null;
				if (0 == versionFlag) {
					//旧版本
					String[] appIdArr = StringUtils.split(appIds, ",");
					updateInfoTemp = Global.getUpdateAppBySQL(packageNameArr[i], appIdArr[i], osVersion);
				} else {
					//新版本
					String[] versionArr = StringUtils.split(versions, ",");
					String[] versionCodeArr = StringUtils.split(versionCodes, ",");
					updateInfoTemp = Global.getUpdateAppBySQLNew(packageNameArr[i], versionArr[i], versionCodeArr[i], osVersion);
				}
				if (updateInfoTemp != null) {
					updateAppInfoList.add(initAppInfoToDto(updateInfoTemp));
				}
			}
			
			//返回
			PageDTO<AppInfoDTO> appPage = initAppInfoPageDTO(new Page<AppInfoDetail>(), updateAppInfoList);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS,
					null, appPage);
		
			write(JSON.toJSONString(responseDTO));
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					e.getMessage(), null);
			write(JSON.toJSONString(responseDTO));
		}
		
		long t2 = System.currentTimeMillis();
		logger.info("getUpdateAppList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	/**
	 * 获取证书
	 * 
	 * @return void [返回类型说明]
	 */
	public void getCertificate() {
		long t1 = System.currentTimeMillis();
		logger.info("getCertificate([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			List<AppCertificate> certificateList = Global.getEnableAppCertificateList();
			String url = initURL(request.getLocalAddr(), request.getServerPort()) + Constants.CERTIFICATE_MAPPE_PATH;
			// 签名证书url列表
			List<String> certificates = new ArrayList<String>();
			for (AppCertificate apc : certificateList) {
				certificates.add(url + apc.getCertificateSaveName());
			}
			List<AppCertificate> revokeList = Global.getRevokeAppCertificateList();
			// 吊销证书url列表
			List<String> revokes = new ArrayList<String>();
			for (AppCertificate rev : revokeList) {
				revokes.add(url + rev.getRevokeSaveName());
			}

			CertificateDTO certificateDTO = new CertificateDTO();
			certificateDTO.setCertificates(certificates);
			certificateDTO.setRevokes(revokes);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS, null, certificateDTO);
			write(JSON.toJSONString(responseDTO));
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR, AppConstants.MSG_SYS_ERROR, null);
			write(JSON.toJSONString(responseDTO));
		}
		
		long t2 = System.currentTimeMillis();
		logger.info("getCertificate([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	/**
	 * 根据证书id返回的有效（未过期、未吊销）证书
	 * 
	 * @return void
	 * @exception throws [违例类型] [违例说明]
	 */
	public void getCertificateById() {
		long t1 = System.currentTimeMillis();
		logger.info("getCertificateById([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		if (MethodsUtil.isNull(appCertificateId)) {
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR, AppConstants.MSG_NULL, null);
			write(JSON.toJSONString(responseDTO));
		} else {
			try {
				List<AppCertificate> listCert = null;

				for (AppCertificate obj : Global.getEnableAppCertificateList()) {
					if (appCertificateId.equals(obj.getId())) {
						listCert = new ArrayList<AppCertificate>();
						listCert.add(obj);
					}
				}

				// 签名证书url列表
				if (MethodsUtil.isNull(listCert)) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NULL, null);
					write(JSON.toJSONString(responseDTO));
				} else {
					List<String> certificates = new ArrayList<String>();
					String url = initURL(request.getLocalAddr(), request.getServerPort())
							+ Constants.CERTIFICATE_MAPPE_PATH;
					for (AppCertificate apc : listCert) {
						certificates.add(url + apc.getCertificateSaveName());
					}
					CertificateDTO certificateDTO = new CertificateDTO();
					certificateDTO.setCertificates(certificates);
					generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS, null,
							certificateDTO);
					write(JSON.toJSONString(responseDTO));
				}
			} catch (Exception e) {
				Debug.logError(e, e.getMessage(), MODULE);
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MSG_SYS_ERROR, null);
				write(JSON.toJSONString(responseDTO));
			}
		}
		
		long t2 = System.currentTimeMillis();
		logger.info("getCertificateById([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	/**
	 * 获取应用商店客户端更新路径
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	public void getAppStoreClientUpdateUrl() {
		long t1 = System.currentTimeMillis();
		logger.info("getAppStoreClientUpdateUrl([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		AppStoreClient appStoreClient = null;
		if (appStoreVersion != 0 && osVersion != 0) {
			if (clientModel != null) {
				clientModel = getClientplatform(clientModel);
			}
			// appStoreClient = appStoreClientService
			// .getAppStoreClientUpdateVersion(appStoreVersion,
			// osVersion,clientModel);
			appStoreClient = Global.getAppStoreClientUpdateVersion(
					appStoreVersion, osVersion, clientModel);
		}

		if (appStoreClient != null) {
			responseDTO.setRespseDesc(AppConstants.MSG_SUCCESS);
			responseDTO.setRespseStatus(AppConstants.RESPOSE_STATUS_SUCESS);
			String url = initApkURL(request.getLocalAddr(), request.getServerPort());

			responseDTO.setResultObject(url
					+ Constants.APP_STORE_CLIENT_MAPPE_PATH
					+ appStoreClient.getApkFileSavePath());
		} else {
			responseDTO.setRespseDesc(AppConstants.MSG_SUCCESS);
			responseDTO.setRespseStatus(AppConstants.RESPOSE_STATUS_SUCESS);
			responseDTO.setResultObject(null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getAppStoreClientUpdateUrl([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}
	
	
	
	/**
	 * 获取应用商店客户端升级信息
	 */
	public void getAppStoreUpgradeInfo(){
		long t1 = System.currentTimeMillis();
		logger.info("getAppStoreUpgradeInfo([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });
		try {
			String terminaltype = "6";//6:盒子
			String appStorePkgName = request.getParameter("appStorePkgName");//应用商店APK包名
			String appStoreVersion = request.getParameter("appStoreVersion");//应用商店APK的版本号
			if(StringUtils.isBlank(appStorePkgName)){
				throw new IllegalArgumentException("appStorePkgName can not is null");
			}
			if(StringUtils.isBlank(appStoreVersion)){
				throw new IllegalArgumentException("appStoreVersion can not is null");
			}
			String producttype = request.getParameter("producttype");//产品型号
			String areacode = request.getParameter("cityCode");//区域码
			String channelId = request.getParameter("channelId");//渠道
			String mac = request.getParameter("mac");//mac地址
			
			//调用版本升级服务器
			List<NameValuePair> paramList = new ArrayList<NameValuePair>();
			paramList.add(new BasicNameValuePair("terminaltype", terminaltype));
			paramList.add(new BasicNameValuePair("application", appStorePkgName + ":" + appStoreVersion));
			if(StringUtils.isNotBlank(producttype)){
				paramList.add(new BasicNameValuePair("producttype", producttype));
			}
			if(StringUtils.isNotBlank(areacode)){
				paramList.add(new BasicNameValuePair("areacode", areacode));
			}
			if(StringUtils.isNotBlank(channelId)){
				paramList.add(new BasicNameValuePair("channelId", channelId));
			}
			if(StringUtils.isNotBlank(mac)){
				paramList.add(new BasicNameValuePair("mac", mac));
			}

			String upgradeUrl = "http://" + Constants.UPGRADE_SERVER_IP + ":"
					+ Constants.UPGRADE_SERVER_PORT + "/versionupdate";
			logger.info("客户端版本升级Url：" + upgradeUrl);
			HttpResponse response = HttpUtil.doPost(upgradeUrl, paramList);
			if(null == response){
				String errorMsg = "请求升级服务器失败，response为null";
				logger.error(errorMsg);
				generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR, errorMsg, null);
			}else if (response.getStatusLine().getStatusCode() == 200) {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String upgradeJson = EntityUtils.toString(entity, "UTF-8");
					logger.info("从服务器返回的登录验证结果对应的json为：" + upgradeJson);
					
					JSONObject updateObject = null;
					if(StringUtils.isNotBlank(upgradeJson)){
						JSONObject upgradeResult = JSON.parseObject(upgradeJson);
						String ret = upgradeResult.getString("ret");
						
						if("0".equals(ret)){//成功
							JSONArray updateinfoArray = upgradeResult.getJSONArray("updateinfo");
							if(null != updateinfoArray){
								updateObject = updateinfoArray.getJSONObject(0);
							}
						}
					}
					generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS, null, updateObject);
				}
			} else {
				String errorMsg = "请求升级服务器失败，错误代码为：" + response.getStatusLine().getStatusCode();
				logger.error(errorMsg);
				generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR, errorMsg, null);
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}
		
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getAppStoreUpgradeInfo([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	/**
	 * 获取隐式应用列表 <功能描述> [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	public void getImplicitAppList() {
		long t1 = System.currentTimeMillis();
		logger.info("getImplicitAppList([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		if (StringUtils.isNotEmpty(clientModel) && osVersion != 0) {
			List<ImplicitApp> impAppList = null;
			try {
				clientModel = getClientplatform(clientModel);
				// impAppList = implicitAppService
				// .getImplicitAppListByTeminalNumAndOsVersion(clientModel,
				// osVersion);
				impAppList = Global.getImplicitAppListByTeminalNumAndOsVersion(
						clientModel, osVersion);

			} catch (Exception e) {
				e.printStackTrace();
			}

			List<ImplicitAppInfoDTO> resultObjectList = convertToImplicitAppDTO(impAppList);
			if (isNull(resultObjectList)) {
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MSG_NULL, null);
			} else {
				PageDTO<ImplicitAppInfoDTO> appPage = new PageDTO<ImplicitAppInfoDTO>();
				appPage.setPageSize(10);
				appPage.setCurrentPage(1);
				appPage.setTotalPage(0);
				appPage.setTotalCount(resultObjectList.size());
				appPage.setResultList(resultObjectList);

				responseDTO.setResultObject(resultObjectList);
				responseDTO.setRespseDesc(AppConstants.MSG_SUCCESS);
				responseDTO.setRespseStatus(AppConstants.RESPOSE_STATUS_SUCESS);
				responseDTO.setResultObject(appPage);
			}
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getImplicitAppList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	/**
	 * 获取应用排行下的应用数
	 */
	public void getAppRankCount() {
		long t1 = System.currentTimeMillis();
		logger.info("getAppRankCount([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {

			List<AppRankDTO> appRankList = new ArrayList<AppRankDTO>();
			for (int i = 1; i <= 5; i++) {
				AppRankDTO aAppRankDTO = new AppRankDTO();
				aAppRankDTO.setId("" + i);
				List<AppInfoDetail> list = getAllRankAppInfoList(i + "");
				int count = 0;

				for (AppInfoDetail obj : list) {
					if (Integer.valueOf(obj.getSystem()) <= osVersion) {
						count++;
					}
				}

				aAppRankDTO.setAppTotal(count);
				appRankList.add(aAppRankDTO);
			}

			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS,
					null, appRankList);

		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getAppRankCount([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}

	/**
	 * 获取精选页数据列表接口，返回JSON结构.
	 */
	@SuppressWarnings("unchecked")
	public void getAppRecommendPanelList() {
		long t1 = System.currentTimeMillis();
		logger.info("getAppRecommendPanelList([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		if (osVersion <= 0) {
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MUST_PARAM_NULL, null);
			write(JSON.toJSONString(responseDTO));
			return;
		}

		String jsonResult = planFacade.getPlanedAppRecommendPanelJson(request.getParameterMap());
		if (StringUtils.isEmpty(jsonResult)) {
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
			jsonResult = JSON.toJSONString(responseDTO);
		}
		write(jsonResult);
		long t2 = System.currentTimeMillis();
		logger.info("getAppRecommendPanelList([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}
      
    
	public void submitDeviceInfo() {
		long t1 = System.currentTimeMillis();
		logger.info("submitDeviceInfo([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		try {
			if (StringUtils.isNotBlank(deviceMac)) {
				DeviceInfo deviceInfo = deviceInfoServiceImpl.getDeviceInfoByMac(deviceMac);
				if (deviceInfo != null) {
					if (StringUtils.isNotEmpty(appStoreClientVersion)
							&& appStoreClientVersion != deviceInfo.getAppStoreClientVersion()) {
						deviceInfo.setAppStoreClientVersion(appStoreClientVersion);
					}
					deviceInfo.setDeviceIp(getIpAddr());
				} else {
					deviceInfo = new DeviceInfo();
					deviceInfo.setAppStoreClientVersion(appStoreClientVersion);
					deviceInfo.setAppStoreClientInstallDate(new Date());
					deviceInfo.setDeviceIp(getIpAddr());
					deviceInfo.setDeviceMac(deviceMac);
					deviceInfo.setDeviceSerialNo(deviceSerialNo);
					deviceInfo.setDeviceType(deviceType);
					deviceInfo.setOsVersion(osVersion);
				}
				deviceInfo.setChannelId(channelId);
				deviceInfo.setCityCode(cityCode);
				deviceInfoServiceImpl.saveOrUpdate(deviceInfo);
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
		}
		
		long t2 = System.currentTimeMillis();
		logger.info("submitDeviceInfo([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });

	}

	
	/**
	 * 客户端用户操作记录接口
	 */
	public void submitUserOpera() {
		long t1 = System.currentTimeMillis();
		logger.info("submitUserOpera([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		if (checkAllow(optType)) {
			try {
				boolean isNormal = (optType == 7 && StringUtils.isEmpty(optContentId));

				if (optType < 0 || StringUtils.isEmpty(optContent)
						|| StringUtils.isEmpty(deviceMac) || isNormal) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MUST_PARAM_NULL, null);
				} else {
					UserOperation userOperation = new UserOperation();
					
					//记录用户信息
					if(StringUtils.isNotBlank(userCode)){
						//判断用户是否有登录
						FucUser loginUser = this.getLoginUserFromFUC(token);
						if(null != loginUser){
							userOperation.setUserCode(userCode);
						}
					}
					
					//处理optContent的URL转码
					if(this.isEncoded(optContent)){
						optContent = URLDecoder.decode(optContent, "UTF-8");
					}

					userOperation.setOptType(optType);
					userOperation.setOptTime(new Date());
					userOperation.setOptContent(optContent);
					userOperation.setOptContentId(optContentId);
					userOperation.setDeviceMac(deviceMac);
					userOperation.setChannelId(channelId);
					userOperation.setCityCode(cityCode);
					userOperation.setParam1(param1);
					userOperation.setParam2(param2);
					userOperation.setParam3(param3);
					
					//保存到数据库
					userOperationServiceImpl.saveUserOperation(userOperation);
					
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_SUCESS,
							AppConstants.MUST_PARAM_NULL, null);
				}
			} catch (Exception e) {
				Debug.logError(e, e.getMessage(), MODULE);
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MSG_SYS_ERROR, null);
			}
		}
		
		long t2 = System.currentTimeMillis();
		logger.info("submitUserOpera([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}
    
    
    
    

    /**
     * 获应用关联推荐列表
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getAppRelate() {
		List<AppInfoDTO> resultList = null;
		
		long t1 = System.currentTimeMillis();
		Map paramMap = request.getParameterMap();
		logger.info("getAppRelate([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(paramMap) });
		
		String appPackageName = request.getParameter("appPackageName");
		try {	
            if (StringUtils.isEmpty(appPackageName) || osVersion <= 0) {
				//参数不合法
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MUST_PARAM_NULL, null);
			} else {
				//判断应用是否存在
				List<AppInfoDetail> planedAppList = planFacade.getPlanedAppInfoList(paramMap);
				AppInfoDetail app = null;
				for (AppInfoDetail tempApp : planedAppList) {
					if (appPackageName.equals(tempApp.getAppFilePackage())) {
						app = tempApp;
						break;
					}
				}
				if (null == app) {
					generateResposeDTO(responseDTO,
							AppConstants.RESPOSE_STATUS_ERROR,
							AppConstants.MSG_NOT_EXISTS, null);
				}else{
					//获取方案
					String planId = planProcessor.process(paramMap).getId();
					
					//从缓存中取值
					String key = appPackageName + "_" + planId;
					resultList = Global.appRelateMap.get(key);
					if(null == resultList){						
						//获取推荐的应用列表
						List<DpAppInfo> dpAppInfoList = handAppRelateService.getAppRelateList(planId, appPackageName);
						
				    	//获取所有的应用Map
				        Map<String, AppInfoDetail> allAppInfoMap = Global.appInfoDetailMap;

				        //过滤
				        List<AppInfoDetail> recommendList = new ArrayList<AppInfoDetail>();
				        for (DpAppInfo dpAppInfo : dpAppInfoList) {
				        	if(allAppInfoMap.containsKey(dpAppInfo.getAppFilePackage())){
				        		recommendList.add(allAppInfoMap.get(dpAppInfo.getAppFilePackage()));
				        	}
						}

						resultList = new ArrayList<AppInfoDTO>();
						for (AppInfoDetail appInfo : recommendList) {
							resultList.add(initAppInfoToDto(appInfo));
						}
						
						//存入缓存
						Global.appRelateMap.put(key, resultList);
					}


		            // 分页查询已上架的app应用
		            Page<AppInfoDetail> page = new Page<AppInfoDetail>();
		            page.setPageSize(limit);
		            page.setCurrentPage(start);
		            page.setBeginCount(((start - 1) * limit + 1));
		            
		            // 构造对象，以便转换为json对象
		            setResponseObject(resultList, page);
				}
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}
		
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getAppRelate([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}
	
	/**
	 * 获取方案id
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getStorePlanId(){
		long t1 = System.currentTimeMillis();
		Map paramMap = request.getParameterMap();
		logger.info("getStorePlanId([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(paramMap) });
		Plan plan = planProcessor.process(paramMap);
		try {
			Properties prop = new Properties();
			prop.setProperty("planId", plan.getId());
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS,
			        null, prop);
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE);
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MSG_SYS_ERROR, null);
		}
		
		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getStorePlanId([{}]) end, planId={}, costTime={}ms...", new Object[] { t1,plan.getId(), (t2 - t1) });
	}
	
	
	
	/**
	 * 获取系统参数
	 */
	@SuppressWarnings("rawtypes")
	public void getSystemParam(){
		long t1 = System.currentTimeMillis();
		Map paramMap = request.getParameterMap();
		logger.info("getSystemParam([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(paramMap) });
		
		String code = request.getParameter("code");
		if (StringUtils.isEmpty(code)) {
			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
					AppConstants.MUST_PARAM_NULL, null);
		} else {
			SystemParamDTO systemParamDTO = null;
			try {
				SystemParam systemParam = Global.systemParamMap.get(code);
				if (null != systemParam) {
					String url = initURL(request.getLocalAddr(),
							request.getServerPort());
					systemParamDTO = toSystemParamDTO(systemParam, url);
				}

				generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS,
						null, systemParamDTO);
			} catch (Exception e) {
				Debug.logError(e, e.getMessage(), MODULE);
				generateResposeDTO(responseDTO,
						AppConstants.RESPOSE_STATUS_ERROR,
						AppConstants.MSG_SYS_ERROR, null);
			}
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("getSystemParam([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}
	
	

    
	/**
	 * 刷新app评分缓存
	 * 
	 * @param packageName 应用包名
	 * @param oldScore 重复评分时旧的评分
	 * @param score 应用评分
	 */
	public void updateCacheAppScore() {
		long t1 = System.currentTimeMillis();
		logger.info("updateCacheAppScore([{}]) entry:{}", new Object[] { t1, JSON.toJSONString(request.getParameterMap()) });

		responseDTO.setRespseStatus(AppConstants.RESPOSE_STATUS_ERROR);
		// 参数校验
		if (isNull(packageName) || isScoreRange(score)) {
			responseDTO.setRespseDesc(AppConstants.MUST_PARAM_NULL);
		}
		
		// 获取应用信息
		AppInfoDetail appInfo = null;
		List<AppInfoDetail> apps = getAllRankAppInfoList(null);
		for (AppInfoDetail app : apps) {
			if (packageName.equals(app.getAppFilePackage())) {
				appInfo = app;
				break;
			}
		}

		// 获取应用的评分缓存
		Map<String, AppScoreSummaryEntity> appScoreSummaryMap = Global.getAppScoreSummaryMap();
		AppScoreSummaryEntity commentInfo = appScoreSummaryMap.get(packageName);
		if (commentInfo != null) {
			// 旧评分人数减1
			if (oldScore > 0) {
				switch (oldScore) {
				case 1:
					commentInfo.setStar1(commentInfo.getStar1() - 1);
					break;
				case 2:
					commentInfo.setStar2(commentInfo.getStar2() - 1);
					break;
				case 3:
					commentInfo.setStar3(commentInfo.getStar3() - 1);
					break;
				case 4:
					commentInfo.setStar4(commentInfo.getStar4() - 1);
					break;
				case 5:
					commentInfo.setStar5(commentInfo.getStar5() - 1);
					break;
				default:
					break;
				}
			}
			// 新评分人数加1
			switch (score) {
			case 1:
				commentInfo.setStar1(commentInfo.getStar1() + 1);
				break;
			case 2:
				commentInfo.setStar2(commentInfo.getStar2() + 1);
				break;
			case 3:
				commentInfo.setStar3(commentInfo.getStar3() + 1);
				break;
			case 4:
				commentInfo.setStar4(commentInfo.getStar4() + 1);
				break;
			case 5:
				commentInfo.setStar5(commentInfo.getStar5() + 1);
				break;
			default:
				break;
			}

			// 重算平均分
			double newScoreSum = commentInfo.getStar1() + 2
					* commentInfo.getStar2() + 3 * commentInfo.getStar3() + 4
					* commentInfo.getStar4() + 5 * commentInfo.getStar5();
			long newScoreCount = commentInfo.getStar1()
					+ commentInfo.getStar2() + commentInfo.getStar3()
					+ commentInfo.getStar4() + commentInfo.getStar5();
			if(null != appInfo){
				newScoreSum = newScoreSum + appInfo.getHandAvgScore() * appInfo.getHandScoreCount();
				newScoreCount += appInfo.getHandScoreCount();
			}
			
			double avgScore = newScoreSum / newScoreCount;
			avgScore = AppCommentHelper.processAvgScore(avgScore);
			
			//重算星值
			double avgStar = AppCommentHelper.processAvgStar(avgScore);
			
			//刷新缓存
			commentInfo.setAvgScore(avgScore);
			commentInfo.setAvgStar(avgStar);
			commentInfo.setScoreCount(newScoreCount);
			commentInfo.setScoreSum(newScoreSum);

			generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS,
					null, null);
		}

		write(JSON.toJSONString(responseDTO));
		long t2 = System.currentTimeMillis();
		logger.info("updateCacheAppScore([{}]) end, costTime={}ms...", new Object[] { t1, (t2 - t1) });
	}


    
    
    
    
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------
	/**
	 * 初始化最新、最热、最好、免费和收费应用列表，由定时器调用
	 * 
	 * @param rankType
	 *            :排行字段
	 */
	public void initAppList() {
		try {
			// 查询应用列表
			List<AppInfoDetail> appInfoDetailList = appInfoDetailService
					.getRankTypeAppInfoList(rankType);
			if (null != appInfoDetailList) {
				logger.info("appInfoDetailList size is :"
						+ appInfoDetailList.size());
			}

            if (Constants.APP_ORDER_NEW.equals(rankType)) {
                appStoreCacheService.initAppInfoDetailMap(appInfoDetailList);
            }

			if (Constants.APP_ORDER_HOT.equals(rankType)) {
				Global.setHotAppInfoList(appInfoDetailList);

				// 初始化应用的附件缓存
				Map<String, List<AttachmentFileEntity>> appAttachmentMap = new ConcurrentHashMap<String, List<AttachmentFileEntity>>();
				for (AppInfoDetail appInfoDtl : appInfoDetailList) {
					List<AttachmentFileEntity> attachmentList = attachmentFileService
							.findAttachmentByAppId(appInfoDtl.getId());
					appAttachmentMap.put(appInfoDtl.getId(), attachmentList);
				}
				Global.setAppAttachmentMap(appAttachmentMap);

				// 缓存应用的平均评分和星值
				Map<String, AppScoreSummaryEntity> appScoreSummaryMap = initAppScoreSummaryMap(appInfoDetailList);
				Global.setAppScoreSummaryMap(appScoreSummaryMap);

				// 缓存所有分类列表
				List<DpType> allDpTypeList = dpTypeService
						.getGameAndAppTypeList();
				Global.setAllDpTypeList(allDpTypeList);

				// 缓存应用分类列表
				List<DpType> appTypeList = dpTypeService
						.findVisibleTypeByParentTypeCode(DefaultTypeCodeConstants.APP_TYPE_CODE);
				Global.setAppTypeList(appTypeList);

				// 缓存游戏分类列表
				List<DpType> gameTypeList = dpTypeService
						.findVisibleTypeByParentTypeCode(DefaultTypeCodeConstants.GAME_TYPE_CODE);
				Global.setGameTypeList(gameTypeList);

				// 缓存隐式应用列表
				Global.setImpAppList(implicitAppService.getAllImplicitAppList());

				// 缓存推荐分类列表
				Global.setRecommentTypeList(dpTypeService
						.getRecommentTypeList());

				// 缓存在线商店终端列表
				Page<AppStoreClient> page = new Page<AppStoreClient>();
				page.setPageSize(Integer.MAX_VALUE);
				String hql = "from AppStoreClient ac where ac.status = 1 order by ac.createTime desc";
				appStoreClientService.listAppStoreClient(page, hql, null);
				Global.setOnLineAppStoreClientList(page.getResultList());
			} else if (Constants.APP_ORDER_NEW.equals(rankType)) {
				Global.setNewestAppInfoList(appInfoDetailList);
			} else if (Constants.APP_ORDER_GOOD.equals(rankType)) {
				Global.setGoodAppInfoList(appInfoDetailList);
			} else if (Constants.APP_ORDER_FREE.equals(rankType)) {
				Global.setFreeAppInfoList(appInfoDetailList);
			} else if (Constants.APP_ORDER_PAY.equals(rankType)) {
				Global.setPayAppInfoList(appInfoDetailList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 初始化专题下应用列表，由定时器调用
     */
    public void initSubjectAppList()
    {
        List<AppInfoDetail> subjectDetailList = appInfoDetailService.getSubjectTypeAppInfoList(rankType);

        if (Constants.APP_ORDER_HOT.equals(rankType))
        {
            Global.setHotSubjectAppInfoList(subjectDetailList);
            try
            {
                // 获取应用专题列表
                Page<AppSubjectType> page = new Page<AppSubjectType>();
                page.setPageSize(Integer.MAX_VALUE);
                page.setCurrentPage(start);
                appSubjectTypeService.listAppSubjectType(page, AppConstants.GET_APP_SUBJECT_HQL);

                if (page.getResultList() != null)
                {
                    Global.setSubjectTypeList(page.getResultList());
                }
                else
                {
                    Global.setSubjectTypeList(new ArrayList<AppSubjectType>());
                }
            }
            catch (Exception e)
            {
                Debug.logError(e, e.getMessage(), MODULE);
            }
        }
        else if (Constants.APP_ORDER_NEW.equals(rankType))
        {
            Global.setNewestSubjectAppInfoList(subjectDetailList);
        }
        else if (Constants.APP_ORDER_GOOD.equals(rankType))
        {
            Global.setGoodSubjectAppInfoList(subjectDetailList);
        }
        else if (Constants.APP_ORDER_ALL.equals(rankType))
        {
            Global.setAllSubjectAppInfoList(subjectDetailList);
        }
    }

	/**
	 * 初始化推荐应用列表，由定时器调用
	 */
	public void initRecommendAppList() {
		List<AppInfoDetail> recommendList = appInfoDetailService.getRecommendAppInfos();
		Global.setRecommendAppList(recommendList);
		
		if (CollectionUtils.isNotEmpty(Global.recommendAppList)) {
			logger.info("recommendAppList size is :"
					+ Global.getRecommendAppList().size());
		}
	}

	/**
	 * 初始化推荐分类列表
	 */
	public void initRecommendTypeList() {
		List<DpType> recommentTypeList = new ArrayList<DpType>();
		try {
			recommentTypeList = dpTypeService.getRecommentTypeList();
		} catch (Exception e) {
			logger.error("", e);
		}

		Global.setRecommentTypeList(recommentTypeList);
		if (null != recommentTypeList) {
			logger.info("recommendAppList size is :" + recommentTypeList.size());
		}

	}

	/**
	 * 初始化推荐分类下关联的应用列表，由定时器调用
	 */
	public void initRecommendAppTypeList() {
		List<AppInfoDetail> recommendList = appInfoDetailService.getTypeRecommendAppInfos();
		Global.setRecommendAppTypeList(recommendList);
		if (CollectionUtils.isNotEmpty(Global.recommendAppList)) {
			logger.info("recommendAppList size is :"
					+ Global.getRecommendAppList().size());
		}
	}

	/**
	 * 缓存精选页的json结构.
	 */
    public void initPlanAppRecommendPanelItemJsonMap() {
		try {
            // 获取已上架应用列表.
            List<AppInfoDetail> appInfoDetailList = appInfoDetailService.getRankTypeAppInfoList(null);
            Map<String, AppInfoDetail> appInfoDetailMap = new HashMap<String, AppInfoDetail>();
            for (AppInfoDetail appInfoDetail : appInfoDetailList) {
                String appPkgName = appInfoDetail.getAppFilePackage();
                appInfoDetailMap.put(appPkgName, appInfoDetail);
            }

            // 获取专题列表.
            List<AppSubjectType> appSubjectTypeList = appSubjectTypeService.getAppSubjectTypeList();

            Map<String, AppSubjectType> appSubjectTypeMap = new HashMap<String, AppSubjectType>();
            for (AppSubjectType appSubjectType : appSubjectTypeList) {
                // 过滤已隐藏的专题.
                if (Constants.APP_SUBJECT_HIDE == appSubjectType.getVisibleFlag()) {
                    continue;
                }
                appSubjectTypeMap.put(appSubjectType.getId(), appSubjectType);
            }
            
            // 方案首页类项（即版块）列表.
            List<PlanItem> planItemList = planItemService.getAllPlanItemList(PlanConstants.ITEM_TYPE_PANEL);

            Map<String, List<String>> planPanelIdMap = new HashMap<String, List<String>>();
            for (PlanItem planItem : planItemList) {
                String planId = planItem.getPlanId();

                List<String> planPanelIdList = planPanelIdMap.get(planId);
                if (planPanelIdList == null) {
                	planPanelIdList = new ArrayList<String>();
                    planPanelIdMap.put(planId, planPanelIdList);
                }
                planPanelIdList.add(planItem.getItemId());
            }

            // 所有首页版块map.
            List<AppRecommendPanel> allEnabledPanelList = appRecommendPanelService.getAllEnabledPanelList();
            Map<String, AppRecommendPanel> allEnabledPanelMap = new HashMap<String, AppRecommendPanel>();
            for (AppRecommendPanel panel : allEnabledPanelList) {
                allEnabledPanelMap.put(panel.getId(), panel);
            }

            // 缓存版块元素列表map.key=版块id.
            Map<String, List<AppRecommendPanelItem>> panelItemMap = new HashMap<String, List<AppRecommendPanelItem>>();

            // 缓存版块dto对象.
            Map<String, AppRecommendPanelDTO> appRecommendPanelDTOMap = new HashMap<String, AppRecommendPanelDTO>();

            Map<String, String> planAppRecommendPanelItemJsonMap = new HashMap<String, String>();

            for (String planId : planPanelIdMap.keySet()) {
                List<String> panelIdList = planPanelIdMap.get(planId);

                List<AppRecommendPanelDTO> panelDtoList = new ArrayList<AppRecommendPanelDTO>();
                for (String panelId : panelIdList) {
                    AppRecommendPanelDTO panelDto = appRecommendPanelDTOMap.get(panelId);
                    if (panelDto != null) {
                        panelDtoList.add(panelDto);
                        continue;
                    }

                    panelDto = convertPanelToDto(allEnabledPanelMap.get(panelId));

                    List<AppRecommendPanelItem> panelItemList = panelItemMap.get(panelId);
                    if (panelItemList == null) {
                        panelItemList = appRecommendPanelItemService.getAppRecommendPanelItemList(panelId);

                        panelItemMap.put(panelId, panelItemList);
                    }
                    List<AppRecommendPanelItemDTO> itemDtoList = panelDto.getItemList();
                    for (AppRecommendPanelItem panelItem : panelItemList) {
                        AppRecommendPanelItemDTO itemDto = convertPanelItemToDto(panelItem, appInfoDetailMap,
                                appSubjectTypeMap);
                        if (itemDto == null) {
                            continue;
                        }
                        itemDtoList.add(itemDto);
                    }
                    panelDto.setItemList(itemDtoList);

                    panelDtoList.add(panelDto);

                    appRecommendPanelDTOMap.put(panelId, panelDto);
                }

                PageDTO<AppRecommendPanelDTO> pageDto = new PageDTO<AppRecommendPanelDTO>();
                pageDto.setCurrentPage(1);
                pageDto.setHasNextPage(false);
                pageDto.setHasPrePage(false);
                pageDto.setPageSize(100);
                pageDto.setResultList(panelDtoList);
                pageDto.setTotalCount(panelDtoList.size());
                pageDto.setTotalPage(1);

                generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS, null, pageDto);

                planAppRecommendPanelItemJsonMap.put(planId, JSON.toJSONString(responseDTO));
            }

            Global.planAppRecommendPanelItemJsonMap = planAppRecommendPanelItemJsonMap;
		} catch (Exception e) {
            logger.error("获取精选页数据出现异常.", e);
		}
	}


    /**
     * 初始化证书列表，由定时器调用
     */
    public void initCertificate()
    {

        try
        {
            Page<AppCertificate> page = new Page<AppCertificate>();
            // 签名证书与私钥参数
            Object[] objects = new Object[]
            { Constants.CERTIFICATE_SIGNED_FLAG,
                    Constants.CERTIFICATE_STATUS_NORMAL };
            // 分页
            page.setPageSize(Integer.MAX_VALUE);
            page.setCurrentPage(start);
            appCertificateService.pageAppCertificate(page,
                    AppConstants.CERTIFICATE_TYPE_REVOKEFLAG_HQL, objects);
            Global.setEnableAppCertificateList(page.getResultList());
            if (CollectionUtils.isNotEmpty(Global.enableAppCertificateList))
            {
                logger.info("enableAppCertificateList size is :"
                        + Global.getEnableAppCertificateList().size());
            }
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
        }

    }

    /**
     * 初始化吊销证书列表，由定时器调用
     */
    public void initRevokeCertificate()
    {

        try
        {
            Page<AppCertificate> page = new Page<AppCertificate>();
            // 签名证书与私钥参数
            Object[] parms = new Object[]
            { Constants.CERTIFICATE_REVOKE_FLAG,
                    Constants.CERTIFICATE_STATUS_NORMAL };
            page.setPageSize(Integer.MAX_VALUE);
            appCertificateService.pageAppCertificate(page,
                    AppConstants.CERTIFICATE_TYPE_REVOKEFLAG_HQL, parms);
            List<AppCertificate> revokeList = page.getResultList();
            Global.setRevokeAppCertificateList(revokeList);
            if (CollectionUtils.isNotEmpty(Global.revokeAppCertificateList))
            {
                logger.info("revokeAppCertificateList size is :"
                        + Global.getRecommendAppList().size());
            }
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
        }

    }
    
    /**
     * 初始化方案.
     */
    public void initPlanMap() {
        appStoreCacheService.initPlanMap();
    }
    
    public void initConditionPlanIdMap() {
        appStoreCacheService.initConditionPlanIdMap();
    }

    public void initPlanDpTypeIdMap() {
        appStoreCacheService.initPlanDpTypeIdMap();
    }
    
    public void initPlanSelfTypeMap() {
        appStoreCacheService.initPlanSelfTypeMap();
    }

    public void initPlanDpTypeAppPackageNameMap() {
        appStoreCacheService.initPlanDpTypeAppPackageNameMap();
    }

    public void initPlanAppPackageNameMap() {
        appStoreCacheService.initPlanAppPackageNameMap();
    }

    public void initPlanSubjectTypeMap() {
        appStoreCacheService.initPlanSubjectTypeMap();
    }
    
    /**
     * 缓存方案专题列表.
     */
    public void initSystemParamMap() {
        List<SystemParam> systemParamList = systemParamService.getAll();

        Map<String, SystemParam> systemParamMap = new HashMap<String, SystemParam>();
        for (SystemParam systemParam : systemParamList) {
            String code = systemParam.getCode();
            systemParamMap.put(code, systemParam);
        }

        Global.systemParamMap = systemParamMap;
    }
    
    
    /**
     * 缓存缩略图.
     */
    public void initAppThumbnail() {
    	List<AppThumbnail> thumList = appThumbnailService.getListByType(null);
    	Map<String, AppThumbnail> resultMap = new HashMap<String, AppThumbnail>();
    	for (AppThumbnail appThumbnail : thumList) {
    		if(appThumbnail.getStatus().equals(Constants.THUMBNAIL_SUCCESS)){
    			resultMap.put(appThumbnail.getSrcImg()+"_"+appThumbnail.getDimension(), appThumbnail);
    		}
		}

        Global.appThumbnailMap = resultMap;
    }
    
    
    /**
     * 缓存缩略图.
     */
    public void clearOther() {
        Global.appRelateMap = new HashMap<String,List<AppInfoDTO>>();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	// ----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * 根据条件过滤 列表内容
	 */
	@SuppressWarnings("unchecked")
	private List<AppInfoDetail> filterAppDetailListByKeyWord(Page<AppInfoDetail> page, String keyWord) {
		keyWord = SqlUtil.escapeSQLLike(keyWord.toLowerCase(Locale.US));

		int total = 0; // 符合条件的记录总数
		int tempLimit = 0; // 临时记录总共需要的总数，最大值为limit
		String regex = "^.*";
		final char[] keyWordArray = keyWord.toCharArray();
		// 这里不作空指针校验，校验在上层作
		for (int j = 0; j < keyWord.length(); j++) {
			regex += keyWordArray[j] + ".*";
		}

		// 过滤条件
		// begin 无根据分类查询时
		List<AppInfoDetail> planedAppInfoList = planFacade.getPlanedAppInfoList(request.getParameterMap());
		List<AppInfoDetail> appDetailList = new ArrayList<AppInfoDetail>();
		for (int i = 0; i < planedAppInfoList.size(); i++) {
			// 为了取出第从几个开始
			AppInfoDetail appDetail = planedAppInfoList.get(i);
			// 改用正则进行校验
			if (Integer.valueOf(appDetail.getSystem()) <= osVersion
					&& (Pattern.matches(regex, appDetail.getAppName()) || Pattern
							.matches(regex, appDetail.getAppNamePinyin()))) {
				// 从(start-1)*limit)开始取出limit条数据
				if (((start - 1) * limit) <= total) {
					if (tempLimit < limit) {
						// 循环limit次，为appDetailList添加limit条记录
						appDetailList.add(appDetail);
						++tempLimit;
					}
				} // +1 end 从(start-1)*limit开始获取数据
				++total;
			}
		} // +1 end for循环

		// 关键字相连的排在前面 2013-7-25修改 ，新需求
		sortSearchResult(appDetailList, keyWord);

		page.setTotalCount(total);
		logger.info("page's TotalCount size is :" + total);
		return appDetailList;
	}

    /**
     * 根据条件过滤应用推荐的应用列表
     */
    private List<AppInfoDetail> filterRecommendList(
            List<AppInfoDetail> appInfoList)
    {
        if (appInfoList == null)
        {
            appInfoList = new ArrayList<AppInfoDetail>();
        }

        List<AppInfoDetail> tempAppInfoList = new ArrayList<AppInfoDetail>();
        int total = 0; // 符合过滤条件的记录总数
        int tempLimit = 0; // 临时记录总共需要的总数，最大值为limit

        if (StringUtils.isEmpty(typeId))
        {
            for (int i = 0; i < appInfoList.size(); i++)
            {
                // 为了取出第从几个开始
                AppInfoDetail appDetail = appInfoList.get(i);
                if (Integer.valueOf(appDetail.getSystem()) <= osVersion)
                {
                    // 从(start-1)*limit)开始取出limit条数据
                    if (((start - 1) * limit) <= total)
                    {
                        if (tempLimit < limit)
                        {
                            // 循环limit次，为appDetailList添加limit条记录
                            tempAppInfoList.add(appDetail);
                            ++tempLimit;
                        }
                    } // +1 end 从(start-1)*limit开始获取数据
                    ++total;
                } // +1 end for循环
            }
        }
        else
        {
            for (int i = 0; i < appInfoList.size(); i++)
            {
                // 为了取出第从几个开始
                AppInfoDetail appDetail = appInfoList.get(i);
                if (typeId.equals(appDetail.getTypeId())
                        && Integer.valueOf(appDetail.getSystem()) <= osVersion)
                {
                    // 从(start-1)*limit)开始取出limit条数据
                    if (((start - 1) * limit) <= total)
                    {
                        if (tempLimit < limit)
                        {
                            // 循环limit次，为appDetailList添加limit条记录
                            tempAppInfoList.add(appDetail);
                            ++tempLimit;
                        }
                    } // +1 end 从(start-1)*limit开始获取数据
                    ++total;
                } // +1 end for循环
            } // +1 分类查询结束
        }

        return tempAppInfoList;
    }
    

    /**
     * 根据条件过滤 列表内容
     */
    private List<AppInfoDetail> filterAppDetailList(Page<AppInfoDetail> page,
            String rankType)
    {
        List<AppInfoDetail> allAppInfoList = getAllRankAppInfoList(rankType);
        List<AppInfoDetail> appDetailList = this.getAppDetailListByType(typeId, osVersion, allAppInfoList);

        int totalCount = appDetailList.size();
        page.setTotalCount(totalCount);

        if (totalCount == 0 || totalCount <= limit)
        {
            return appDetailList;
        }

        int startIndex = (start - 1) * limit;
        int endIndex = start * limit;

        startIndex = (startIndex < 0 ? 0 : startIndex);
        startIndex = (startIndex > totalCount ? totalCount : startIndex);
        endIndex = (endIndex > totalCount ? totalCount : endIndex);

        logger.info("page's TotalCount size is :" + page.getTotalCount());

        return appDetailList.subList(startIndex, endIndex);
    }
    
	private List<AppInfoDetail> getAppDetailListByType(String typeId, int osVersion, List<AppInfoDetail> list) {
		List<AppInfoDetail> appDetailList = new ArrayList<AppInfoDetail>();
		if (StringUtils.isNotBlank(typeId)) {
			//分类不为空
			for (AppInfoDetail appDetail : list) {
				if (typeId.equals(appDetail.getTypeId())
						&& Integer.valueOf(appDetail.getSystem()) <= osVersion) {
					appDetailList.add(appDetail);
				}
			}
		} else {
			//分类为空
			for (AppInfoDetail appDetail : list) {
				if (Integer.valueOf(appDetail.getSystem()) <= osVersion) {
					appDetailList.add(appDetail);
				}
			}
		}
		return appDetailList;
	}

    /**
     * 构造对象，以便转换为json对象
     */
    private void setResponseObject(List<AppInfoDTO> appInfoStoreList,
            Page<AppInfoDetail> page)
    {
        if (isNull(appInfoStoreList))
        {
            generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
                    AppConstants.MSG_NULL, null);
        }
        else
        {
            PageDTO<AppInfoDTO> appPage = initAppInfoPageDTO(page,
                    appInfoStoreList);
            generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_SUCESS,
                    null, appPage);
        }
    }

    /**
     * 获取所有的排行应用列表
     */
    private List<AppInfoDetail> getAllRankAppInfoList(String rankType)
    {
        List<AppInfoDetail> allAppInfoList = null;
        if (Constants.APP_ORDER_HOT.equals(rankType))
        {
            allAppInfoList = Global.getHotAppInfoList();
        }
        else if (Constants.APP_ORDER_NEW.equals(rankType))
        {
            allAppInfoList = Global.getNewestAppInfoList();
        }
        else if (Constants.APP_ORDER_GOOD.equals(rankType))
        {
            allAppInfoList = Global.getGoodAppInfoList();
        }
        else if (Constants.APP_ORDER_FREE.equals(rankType))
        {
            allAppInfoList = Global.getFreeAppInfoList();
        }
        else if (Constants.APP_ORDER_PAY.equals(rankType))
        {
            allAppInfoList = Global.getPayAppInfoList();
        }
        else
        {
            allAppInfoList = Global.getNewestAppInfoList();
        }

        if (allAppInfoList == null)
        {
            allAppInfoList = new ArrayList<AppInfoDetail>();
        }
        return allAppInfoList;
    }

    /**
     * 根据条件过滤 列表内容
     */
    private List<AppInfoDetail> filterSubjectAppDetailList(
            Page<AppInfoDetail> page)
    {
        List<AppInfoDetail> allAppInfoList = getAllSubjectAppInfoList();
        List<AppInfoDetail> appDetailList = new ArrayList<AppInfoDetail>();

        int total = 0; // 符合过滤条件的记录总数
        int tempLimit = 0; // 临时记录总共需要的总数，最大值为limit
        for (int i = 0; i < allAppInfoList.size(); i++)
        {
            // 为了取出第从几个开始
            AppInfoDetail appSubDetail = allAppInfoList.get(i);
            if (subjectId.equals(appSubDetail.getSubjectId())
                    && Integer.valueOf(appSubDetail.getSystem()) <= osVersion)
            {
                // 从(start-1)*limit)开始取出limit条数据
                if (((start - 1) * limit) <= total)
                {
                    if (tempLimit < limit)
                    {
                        // 循环limit次，为appDetailList添加limit条记录
                        appDetailList.add(appSubDetail);
                        ++tempLimit;
                    }
                } // +1 end 从(start-1)*limit开始获取数据
                ++total;
            } // +1 end 根据条件判断
        } // +1 end for循环
        page.setTotalCount(total);

        return appDetailList;
    }
    
	private List<String> getAppPkgNageListBySubjectId(String subjectId) {
		List<String> appPkgNageList = new ArrayList<String>();

		List<AppInfoDetail> allAppInfoList = getAllSubjectAppInfoList();
		for (int i = 0; i < allAppInfoList.size(); i++) {
			AppInfoDetail appSubDetail = allAppInfoList.get(i);
			if (subjectId.equals(appSubDetail.getSubjectId())) {
				appPkgNageList.add(appSubDetail.getAppFilePackage());
			}
		}
		return appPkgNageList;
	}

    /**
     * 获取所有的专题应用排行列表
     */
    private List<AppInfoDetail> getAllSubjectAppInfoList()
    {
        List<AppInfoDetail> allAppInfoList = null;
        if (Constants.APP_ORDER_HOT.equals(orderBy))
        {
            allAppInfoList = Global.getHotSubjectAppInfoList();
        }
        else if (Constants.APP_ORDER_NEW.equals(orderBy))
        {
            allAppInfoList = Global.getNewestSubjectAppInfoList();
        }
        else if (Constants.APP_ORDER_GOOD.equals(orderBy))
        {
            allAppInfoList = Global.getGoodSubjectAppInfoList();
        }
        else
        {
            allAppInfoList = Global.getAllSubjectAppInfoList();
        }

        if (allAppInfoList == null)
        {
            allAppInfoList = new ArrayList<AppInfoDetail>();
        }
        return allAppInfoList;
    }


    private AppRecommendPanelItemDTO convertPanelItemToDto(
            AppRecommendPanelItem item,
            Map<String, AppInfoDetail> appInfoDetailMap,
            Map<String, AppSubjectType> appSubjectTypeMap)
    {

        Integer type = item.getType();
        type = (type == null ? 0 : type);
        String typeValue = item.getTypeValue();

        Object itemInfo = null;
        if (type == Constants.APP_RECOMMEND_PANEL_ITEM_APP_TYPE)
        {
            AppInfoDetail appInfoDetail = appInfoDetailMap.get(typeValue);

            // 应用已下架或者已删除.
            if (appInfoDetail == null)
            {
                return null;
            }

            try
            {
                itemInfo = initAppInfoToDto(appInfoDetail);
            }
            catch (Exception e)
            {
                Debug.logError(e, e.getMessage(), MODULE);
            }
        }
        else if (type == Constants.APP_RECOMMEND_PANEL_ITEM_APP_SUBJECT_TYPE)
        {
            AppSubjectType appSubjectType = appSubjectTypeMap.get(typeValue);

            // 专题已隐藏或已删除.
            if (appSubjectType == null)
            {
                return null;
            }

            itemInfo = initAppSubjectInfoDTO(appSubjectType);
        }

        String url = initURL(request.getLocalAddr(), request.getServerPort());

        AppRecommendPanelItemDTO panelItemDto = new AppRecommendPanelItemDTO();

        panelItemDto.setType(item.getType());
        panelItemDto.setTypeValue(item.getTypeValue());
        panelItemDto.setItemName(item.getItemName());
        panelItemDto.setItemPoster(url + Constants.APP_IMAGES_MAPPE_PATH
                + item.getItemPoster());
        panelItemDto.setItemInfo(itemInfo);

        return panelItemDto;
    }

    private AppRecommendPanelDTO convertPanelToDto(AppRecommendPanel panel)
    {
        AppRecommendPanelDTO panelDto = new AppRecommendPanelDTO();

        panelDto.setLayoutType(panel.getLayoutType());
        panelDto.setPanelName(panel.getPanelName());

        return panelDto;
    }


    /**
     * 将启用的隐式应用列表转换为终端需要的DTO对象
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private List<ImplicitAppInfoDTO> convertToImplicitAppDTO(
            List<ImplicitApp> impAppList)
    {
        String url = initApkURL(request.getLocalAddr(), request.getServerPort());

        List<ImplicitAppInfoDTO> impAppDTOList = new ArrayList<ImplicitAppInfoDTO>();
        if (isNull(impAppList))
        {
            return null;
        }
        else
        {
            for (ImplicitApp impApp : impAppList)
            {
                ImplicitAppInfoDTO tempObj = new ImplicitAppInfoDTO();
                tempObj.setDownloadUrl(url + Constants.APP_FILE_MAPPE_PATH
                        + impApp.getApkFileSavePath());
                tempObj.setPackageName(impApp.getAppFilePackage());
                tempObj.setVersionCode(impApp.getVersionCode());
                tempObj.setExecuteAction(Integer.valueOf(impApp
                        .getImplicitType()));
                tempObj.setCertificateId(impApp.getAppCertId());
                impAppDTOList.add(tempObj);
            }
        }
        return impAppDTOList;
    }

    /**
     * 应用分页DTO封装
     * 
     * @param page
     * @param appInfoStoreList
     * @return [参数说明]
     * @return PageDTO<AppInfoDTO> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private PageDTO<AppInfoDTO> initAppInfoPageDTO(Page<AppInfoDetail> page,
            List<AppInfoDTO> appInfoStoreList)
    {
        PageDTO<AppInfoDTO> appPage = new PageDTO<AppInfoDTO>();
        appPage.setPageSize(page.getPageSize());
        appPage.setCurrentPage(page.getCurrentPage());
        appPage.setTotalPage(page.getTotalPage());
        appPage.setTotalCount(page.getTotalCount());
        appPage.setHasNextPage(page.isHasNextPage());
        appPage.setHasPrePage(page.isHasPrePage());
        appPage.setResultList(appInfoStoreList);
        return appPage;
    }

	
	
	
	

    /**
     * 提交爱摸客用户反馈 该接口已经过时
     */
    /*
     * public void submitUserFeedback(){ if (StringUtils.isEmpty(feedbackText) || feedbackText.length() > 300 || (!StringUtils.isEmpty(userEmail) && userEmail.length() > 30)) {
     * generateResposeDTO(responseDTO,AppConstants.RESPOSE_STATUS_ERROR, AppConstants.MUST_PARAM_NULL, null); }else{ try { UserFeedback feedback = new UserFeedback(); feedback.setFeedbackTime(new
     * Date()); feedback.setUserEmail(userEmail); feedback.setContext(URLDecoder.decode(feedbackText, "utf-8")); userFeedbackService.saveImokerFeedback(feedback);
     * generateResposeDTO(responseDTO,AppConstants.RESPOSE_STATUS_SUCESS, null, null); } catch (Exception e) { Debug.logError(e, e.getMessage(), MODULE); generateResposeDTO(responseDTO,
     * AppConstants.RESPOSE_STATUS_ERROR, AppConstants.MSG_SYS_ERROR, null); } }
     * 
     * write(JSON.toJSONString(responseDTO)); }
     */

    /**
     * 1、 启动 2、退出 3、专题、4、分类、5、推荐 6、应用详情浏览 7、浏览其它 8、安装 9、卸载 10、下载
     * 
     * @param optType
     * @return
     */
    private boolean checkAllow(int optType)
    {
        boolean ret = true;

        if (optType <= 0 || (optType >= 3 && optType <= 7))
        {
            ret = false;
        }

        return ret;
    }

    /**
     * 专题对象转换
     * 
     * @param appSubjectType
     * @return [参数说明]
     * @return AppSubjectInfoDTO [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private DpTypeDTO initAppSubjectInfoDTO(AppSubjectType appSubjectType)
    {
        String url = initURL(request.getLocalAddr(), request.getServerPort());
        // AppSubjectInfoDTO appSubjetInfo = new AppSubjectInfoDTO();
        // appSubjetInfo.setSubjectId(appSubjectType.getId());
        // appSubjetInfo.setSubjectName(appSubjectType.getSubjectName());
        // appSubjetInfo.setSubjectImg(url + Constants.APP_IMAGES_MAPPE_PATH
        // + appSubjectType.getSubjectImg());
        // appSubjetInfo.setSubjectDesc(appSubjectType.getSubjectDesc());

        DpTypeDTO dpTypeDTO = new DpTypeDTO();
        dpTypeDTO.setId(appSubjectType.getId());
        dpTypeDTO.setName(appSubjectType.getSubjectName());
        dpTypeDTO.setTypeImg1(url + Constants.APP_IMAGES_MAPPE_PATH
                + appSubjectType.getSubjectImg());
        dpTypeDTO.setDesc(appSubjectType.getSubjectDesc());

        int count = 0;
        try
        {
            List<AppInfoDetail> allAppInfoList = getAllSubjectAppInfoList();
            subjectId = appSubjectType.getId() + "";
            for (AppInfoDetail appSubDetail : allAppInfoList)
            {
                if (subjectId.equals(appSubDetail.getSubjectId())
                        && Integer.valueOf(appSubDetail.getSystem()) <= osVersion)
                {
                    count++;
                }
            }
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), this.getClass().getName());
        }

        dpTypeDTO.setAppTotal(count);

        return dpTypeDTO;
    }

    /**
     * 根据hql语句初始化列表.
     * 
     * @param hql [参数说明]
     * 
     * @exception throws [违例类型] [违例说明]
     */
    private void initDto(String keyWord)
    {
        try
        {
            // 分页查询已上架的app应用
            Page<AppInfoDetail> page = new Page<AppInfoDetail>();
            page.setPageSize(limit);
            page.setCurrentPage(start);
            page.setBeginCount(((start - 1) * limit + 1));

            List<AppInfoDetail> appInfoList = filterAppDetailListByKeyWord(page, keyWord);

            // 返回给appStore client的数据信息列表
            List<AppInfoDTO> appInfoStoreList = new ArrayList<AppInfoDTO>();
            for (AppInfoDetail appInfoDtlTemp : appInfoList)
            {
                // 获得已经初始化的dto对象
                appInfoStoreList.add(initAppInfoToDto(appInfoDtlTemp));
            }

            // 构造对象，以便转换为json对象
            setResponseObject(appInfoStoreList, page);

        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            generateResposeDTO(responseDTO, AppConstants.RESPOSE_STATUS_ERROR,
                    AppConstants.MSG_SYS_ERROR, null);
        }
        write(JSON.toJSONString(responseDTO));
    }

    /**
     * 根据应用对象初始化数据.
     * 
     * @param appInfoDtl DpAppInfo
     * @return AppInfoDTO
     * @throws Exception
     */
    private AppInfoDTO initAppInfoToDto(AppInfoDetail appInfoDtl)
            throws Exception
    {
        String url = initURL(request.getLocalAddr(), request.getServerPort());
        String strApkUrl = initApkURL(request.getLocalAddr(), request.getServerPort());

        // 保存发送到appStore那边的数据信息列表
        AppInfoDTO appInfoDto = new AppInfoDTO();

        // 拼装给appStore那边需要返回的json格式信息
        appInfoDto.setAppId(appInfoDtl.getId());
        appInfoDto.setDescription(appInfoDtl.getAppDesc());
        appInfoDto.setName(appInfoDtl.getAppName());
        appInfoDto.setAppNamePinyin(appInfoDtl.getAppNamePinyin());
        appInfoDto.setAppFilePackage(appInfoDtl.getAppFilePackage());
        appInfoDto.setPublishTime(DateUtil.dateToString(
                appInfoDtl.getAppTime(), AppConstants.TIMEF_FORMAT));
        appInfoDto.setVersion(appInfoDtl.getVersion());
        appInfoDto.setVersionCode(appInfoDtl.getVersionCode());
        appInfoDto.setDeveloper(appInfoDtl.getDeveloper());
        appInfoDto.setPrice(appInfoDtl.getPrice());
        appInfoDto.setOpMode(appInfoDtl.getOpMode());

        // 修改版本
        appInfoDto.setSystem(AndroidVersionUtil
                .getVersionNameBySdkLevel(Integer.valueOf(appInfoDtl
                        .getSystem())));
        appInfoDto.setLanguage(appInfoDtl.getLanguage());

        appInfoDto.setAppType(appInfoDtl.getTypeName());

        appInfoDto.setSubjectPoster(url + Constants.APP_IMAGES_MAPPE_PATH
                + appInfoDtl.getSubjectPoster());
        // 获取附件
        // List<AttachmentFileEntity> attachmentList = attachmentFileService
        // .findAttachmentByAppId(appInfoDtl.getId());
        List<AttachmentFileEntity> attachmentList = Global
                .getAppAttachmentMap().get(appInfoDtl.getId());

        // 图片logo地址
        List<String> appLogos = new ArrayList<String>();
        // 应用截图
        List<String> appImages = new ArrayList<String>();
        // 应用的海报
        List<String> appPosters = new ArrayList<String>();
        // 图片logo地址
        List<String> appGameLogos = new ArrayList<String>();

        if (attachmentList != null)
        {
            for (AttachmentFileEntity tempAttach : attachmentList)
            {
				// apk的设置
				if (Constants.APPFILE.equals(tempAttach.getFileDesc())) {
					appInfoDto.setApkFileUrl(strApkUrl
							+ Constants.APP_FILE_MAPPE_PATH
							+ tempAttach.getFileSaveName());
				} else if (Constants.LOGO.equals(tempAttach.getFileDesc())) {
					appLogos.add(url + Constants.APP_LOGO_MAPPE_PATH
							+ tempAttach.getFileSaveName());
				} else if (Constants.IMG.equals(tempAttach.getFileDesc())) {
					appImages.add(url + Constants.APP_IMAGES_MAPPE_PATH
							+ tempAttach.getFileSaveName());
				} else if (Constants.POSTER.equals(tempAttach.getFileDesc())) {
					appPosters.add(url + Constants.APP_IMAGES_MAPPE_PATH
							+ tempAttach.getFileSaveName());
				} else if (Constants.GAME_LOGO.equals(tempAttach.getFileDesc())) {
					appGameLogos.add(url + Constants.APP_LOGO_MAPPE_PATH
							+ tempAttach.getFileSaveName());
				}
                appInfoDto.setSize(appInfoDto.getSize() + tempAttach.getFileSize());
            }
        }

        appInfoDto.setAppLogos(appLogos);
        appInfoDto.setAppImages(appImages);
        appInfoDto.setAppPosters(appPosters);
        appInfoDto.setAppGameLogos(appGameLogos);

        // 平均评分
        // AppScoreSummaryEntity appScoreSummary = appCommentService
        // .getAppScoreSummaryByPackageName(appInfoDtl.getAppFilePackage());
		AppScoreSummaryEntity appScoreSummary = Global.getAppScoreSummaryMap().get(appInfoDtl.getAppFilePackage());
		if (appScoreSummary == null) {
			appScoreSummary = new AppScoreSummaryEntity();
		}

        // 设置平均评分的精度
		appInfoDto.setAverageScore(appScoreSummary.getAvgScore());
		appInfoDto.setAverageStar(appScoreSummary.getAvgStar());

        // 设置评论数
        appInfoDto.setCommentCount(appScoreSummary.getScoreCount());

        String perStarCountStr = appScoreSummary.getStar1() + ","
                + appScoreSummary.getStar2() + ","
                + +appScoreSummary.getStar3() + ","
                + appScoreSummary.getStar4() + ","
                + +appScoreSummary.getStar5();

        appInfoDto.setPerStarCountStr(perStarCountStr);
        //设置下载次数
        appInfoDto.setDownloadCount(appInfoDtl.getDownloadCount()+appInfoDtl.getHandDownCount());

        // 获取应用的签名证书id
        appInfoDto.setCertificateId(appInfoDtl.getAppCertId());

        // 设置应用的根分类，目前有： 应用GAME_TYPE和游戏APP_TYPE
        String typeId = appInfoDtl.getTypeId();
        String parentTypeCode = "APP_TYPE"; //默认为应用.
        for (DpType dpType : Global.allDpTypeList) {
			if(typeId.equals(dpType.getId())) {
				parentTypeCode = dpType.getParentTypeCode();
				break;
			}
		}
        appInfoDto.setParentTypeCode(parentTypeCode);
        
        return appInfoDto;
    }

    /**
     * 拼装服务器端资源访问路径.
     * 
     * @return String
     */
    private static final String initURL(final String serverIp,
            final int serverPort)
    {
        // 访问协议
        String protocol = null;
        // 网络ip地址
        String appStoreIp = null;
        // 端口地址
        String port;
        String url = null;

        // 获取当然服务器端协议
        if (isNull(Constants.APPSTORE_UPLOAD_PROTOCOL))
        {
            protocol = "http://";
        }
        else
        {
            protocol = Constants.APPSTORE_UPLOAD_PROTOCOL;
        }

        // 获取当前服务器ip
        if (isNull(Constants.APPSTORE_UPLOAD_IP))
        {
            appStoreIp = serverIp;
        }
        else
        {
            appStoreIp = Constants.APPSTORE_UPLOAD_IP;
        }

        // 获取当然服务器端口号
        if (isNull(Constants.APPSTORE_UPLOAD_PORT))
        {
            port = String.valueOf(serverPort);
        }
        else
        {
            port = Constants.APPSTORE_UPLOAD_PORT;
        }
        url = protocol + appStoreIp + ":" + port;
        return url;
    }
    
    /**
     * 拼装服务器端资源访问路径.(保存apk文件的路径)
     * 
     * @return String
     */
    private static final String initApkURL(final String serverIp,final int serverPort)
    {
        // 访问协议
        String protocol = null;
        // 网络ip地址
        String appStoreIp = null;
        // 端口地址
        String port;
        String url = null;

        // 获取当然服务器端协议
        if (isNull(Constants.APPSTORE_UPLOAD_PROTOCOL))
        {
            protocol = "http://";
        }
        else
        {
            protocol = Constants.APPSTORE_UPLOAD_PROTOCOL;
        }

        // 获取当前服务器ip
        if (isNull(Constants.APPSTORE_APK_UPLOAD_IP))
        {
            appStoreIp = serverIp;
        }
        else
        {
            appStoreIp = Constants.APPSTORE_APK_UPLOAD_IP;
        }

        // 获取当然服务器端口号
        if (isNull(Constants.APPSTORE_APK_UPLOAD_PORT))
        {
            port = String.valueOf(serverPort);
        }
        else
        {
            port = Constants.APPSTORE_APK_UPLOAD_PORT;
        }
        url = protocol + appStoreIp + ":" + port;
        return url;
    }

    /**
     * 应用商店服务端（server）关键字查询应用,查询结果按升序排序
     * 
     * @param appDetailList
     * @param aKeyWord
     */
    private void sortSearchResult(List<AppInfoDetail> appDetailList,
            final String aKeyWord)
    {
        Collections.sort(appDetailList, new Comparator<AppInfoDetail>()
        {
            public int compare(AppInfoDetail o1, AppInfoDetail o2)
            {
                if (o1.getAppNamePinyin().contains(aKeyWord))
                {

                    if (o2.getAppNamePinyin().contains(aKeyWord))
                    {
                        return checkSameIndexChar(o1, o2);
                    }
                    else
                    {
                        return -1;
                    }
                }
                else if (o2.getAppNamePinyin().contains(aKeyWord))
                {
                    return 1;
                }
                else
                {
                    return checkSameIndexChar(o1, o2);
                }
            }

            public int checkSameIndexChar(AppInfoDetail o1, AppInfoDetail o2)
            {
                char[] keyArray = aKeyWord.toCharArray();
                for (int i = 0; i < keyArray.length; i++)
                {
                    int index1 = o1.getAppNamePinyin().indexOf(keyArray[i]);
                    int index2 = o2.getAppNamePinyin().indexOf(keyArray[i]);

                    if (index1 == index2)
                    {
                        continue;
                    }
                    else
                    {
                        return index1 - index2;
                    }
                }

                return 0;
            }

        });
    }

    /**
     * 判断对象是否为空.
     * 
     * @param str 字符串
     * @return boolean 布尔值 true为空 ，false 不为空
     */
    private static boolean isNull(final String str)
    {
        boolean nullFlag = false;
        if (StringUtils.isEmpty(str))
        {
            nullFlag = true;
        }
        return nullFlag;
    }

    /**
     * 判断对象是否为空.
     * 
     * @param list List
     * @return boolean 布尔值 true为空 ，false 不为空
     */
    private boolean isNull(final List<?> list)
    {
        boolean nullFlag = false;

        if (null == list || list.isEmpty())
        {
            nullFlag = true;
        }

        return nullFlag;

    }

    /**
     * dpType分类对象转换为DpTypeDTO应用分类
     * 
     * @param dpType 分类对象
     * @param url 图片服务器ip和端口号
     * @return DpTypeDTO 应用分类
     */
    @SuppressWarnings("unchecked")
	private DpTypeDTO toDpTypeDTO(final DpType dpType, final String url)
    {
        DpTypeDTO dpTypeDTO = new DpTypeDTO();
        dpTypeDTO.setId(dpType.getId());
        dpTypeDTO.setName(dpType.getTypeName());
        dpTypeDTO.setTypeImg1(url + Constants.APP_IMAGES_MAPPE_PATH
                + dpType.getTypeImg1());
        dpTypeDTO.setTypeImg2(url + Constants.APP_IMAGES_MAPPE_PATH
                + dpType.getTypeImg2());
        dpTypeDTO.setDesc(dpType.getTypeDesc());

        int count = 0;

        try
        {
            typeId = dpType.getId() + "";
            //List<AppInfoDetail> allAppInfoList = getAllRankAppInfoList(typeId);
            String planId = planProcessor.process(request.getParameterMap()).getId();
            List<AppInfoDetail> allAppInfoList = planFacade.getPlanedAppDetailListByType(planId, typeId, String.valueOf(osVersion));
            for (AppInfoDetail appDetail : allAppInfoList)
            {
                if (Integer.valueOf(appDetail.getSystem()) <= osVersion)
                {
                    count++;
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        dpTypeDTO.setAppTotal(count);

        return dpTypeDTO;
    }

    /**
     * appId、userId转换为MyApp对象
     * 
     * @param appId 应用id
     * @param userId 用户id
     * @return MyApp 我的应用对象
     */
    private MyApp getMyApp(final String appId, final String userId,
            String packageName)
    {
        MyApp myAppTemp = new MyApp();
        myAppTemp.setAddTime(new Date());
        myAppTemp.setAppId(appId);
        myAppTemp.setUid(userId);
        myAppTemp.setAppPackageName(packageName);
        return myAppTemp;
    }

    /**
     * appId、userId转换为MyFavorite对象
     * 
     * @param appId 应用id
     * @param userId 用户id
     * @return MyFavorite 我的收藏对象
     */
    private MyFavorite getMyFavorite(final String appId, final String userId)
    {
        MyFavorite myFavorite = new MyFavorite();
        myFavorite.setAppId(appId);
        myFavorite.setFavoriteTime(new Date());
        myFavorite.setUid(userId);
        return myFavorite;
    }

    /**
     * appId、userId、commentContent转换为AppCommentInfo对象
     * 
     * @param appId 应用id
     * @param userId 用户id
     * @param commentContent 评论内容
     * @return AppCommentInfo 评论对象
     */
    private AppCommentInfo getAppCommentInfo(final String appId,
            final String userId, final String commentUserName,
            final String commentContent, final int appScore, String packageName)
    {

        AppCommentInfo appCommentInfo = new AppCommentInfo();
        DpAppInfo dpAppInfo = new DpAppInfo();
        dpAppInfo.setId(appId);
        appCommentInfo.setAppPackageName(packageName);
        appCommentInfo.setDpAppInfo(dpAppInfo);
        appCommentInfo.setCreateDate(new Date());
        appCommentInfo.setCommentUserId(userId);
        appCommentInfo.setCommentUserName(commentUserName);
        appCommentInfo.setCommentContent(commentContent);
        appCommentInfo.setScore(appScore);
        return appCommentInfo;
    }

    /**
     * 根据应用评论对象初始化数据.
     * 
     * @param appCommentInfo 评论对象
     * @return AppCommentInfoDTO 对象
     */
    private AppCommentInfoDTO initAppCommentInfoDto(
            AppCommentInfo appCommentInfo)
    {
        AppCommentInfoDTO appCommentInfoDTO = new AppCommentInfoDTO();

        appCommentInfoDTO.setCommentUserName(appCommentInfo
                .getCommentUserName());
        appCommentInfoDTO.setCreateDate(DateUtil.dateToString(
                appCommentInfo.getCreateDate(), AppConstants.TIMEF_FORMAT));
        appCommentInfoDTO.setCommentContent(appCommentInfo.getCommentContent());
        appCommentInfoDTO.setAppId(appCommentInfo.getDpAppInfo().getId());
        return appCommentInfoDTO;
    }

	// 初始化应用的评分缓存
	private Map<String, AppScoreSummaryEntity> initAppScoreSummaryMap(List<AppInfoDetail> appDetailList) throws Exception {
		Map<String, AppScoreSummaryEntity> appScoreSummaryMap = new ConcurrentHashMap<String, AppScoreSummaryEntity>();
		for (AppInfoDetail appInfoDtl : appDetailList) {
			//获取真实的评分和星值
			AppScoreSummaryEntity appScoreSummary = appCommentService.getAppScoreSummaryByPackageName(appInfoDtl.getAppFilePackage());
			
			// 计算人工增加后的平均积分
			double allScoreSum = appScoreSummary.getScoreSum()
					+ appInfoDtl.getHandAvgScore() * appInfoDtl.getHandScoreCount();
			long allScoreCount = appScoreSummary.getScoreCount()
					+ appInfoDtl.getHandScoreCount();
			double afterHandAvgScore = appScoreSummary.getAvgScore();
			if (0 != allScoreCount) {
				afterHandAvgScore = allScoreSum / allScoreCount;
			}

			afterHandAvgScore = AppCommentHelper.processAvgScore(afterHandAvgScore);
			appScoreSummary.setAvgScore(afterHandAvgScore);
			
			// 计算人工增加后的星值
			double afterHandAvgStar = AppCommentHelper.processAvgStar(afterHandAvgScore);
			appScoreSummary.setAvgStar(afterHandAvgStar);
			
			//计算人工增加后的评分人数
			appScoreSummary.setScoreCount(allScoreCount);
			
			appScoreSummaryMap.put(appInfoDtl.getAppFilePackage(), appScoreSummary);
		}
		return appScoreSummaryMap;
	}

    /**
     * 
     * 判断评分在1-5之间
     * 
     * @param sco 评分
     * @return boolean true超出范围，false 1-5之间
     */
    private boolean isScoreRange(int sco)
    {
        boolean flag = false;

        if (sco < 1 || sco > 5)
        {
            flag = true;
        }
        return flag;
    }

    /**
     * 生成返回结果对象
     * 
     * @param responseDTO
     * @param respseStatus
     * @param respseDesc
     * @param resultObject [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private void generateResposeDTO(ResponseDTO responseDTO,
            String respseStatus, String respseDesc, Object resultObject)
    {
        responseDTO.setRespseStatus(respseStatus);
        responseDTO.setRespseDesc(respseDesc == null ? "" : respseDesc);
        responseDTO.setResultObject(resultObject == null ? new Object() : resultObject);
    }

    private String getIpAddr()
    {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");

        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress))
        {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress))
        {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress))
        {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1"))
            {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try
                {
                    inet = InetAddress.getLocalHost();
                }
                catch (UnknownHostException e)
                {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        if (ipAddress != null && ipAddress.length() > 15)
        {
            if (ipAddress.indexOf(",") > 0)
            {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    private void write(String value)
    {
        try
        {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(value);
        }
        catch (IOException e)
        {
            Debug.logError(e, e.getMessage());
        }
    }
	
	//获取客户端运行的平台编码
	private String getClientplatform(String clientModel) {
		String device = clientModel.toLowerCase();
		String retCode = "1";
		if (device.contains(AppConstants.DEVICE_MSTAR)) {
			retCode = "1";
		}
		if (device.contains(AppConstants.DEVICE_HISI)) {
			retCode = "2";
		}
		if (device.contains(AppConstants.DEVICE_MTK)) {
			retCode = "3";
		}
		return retCode;
	}
	
	// 判断字条串是否是被URL Encode过的(UTF-8)
	private boolean isEncoded(String value) {
		try {
			String decode = URLDecoder.decode(value, "UTF-8");
			if (!value.equals(decode)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	private SystemParamDTO toSystemParamDTO(final SystemParam systemParam, final String url)
    {
		SystemParamDTO systemParamDTO = new SystemParamDTO();
		systemParamDTO.setId(systemParam.getId());
		systemParamDTO.setCode(systemParam.getCode());
		systemParamDTO.setName(systemParam.getName());
		systemParamDTO.setType(systemParam.getType());
		if(Constants.PARAM_TYPE_PHOTO.equals(systemParam.getType())){
			systemParamDTO.setValue(url + Constants.SYSTEM_IMAGES_MAPPE_PATH
	                + systemParam.getValue());
		}else{
			systemParamDTO.setValue(systemParam.getValue());
		}
		

        return systemParamDTO;
    }

	private void thumbnailProcess(AppInfoDTO appInfoDTO){
		//存储缩略图的List
		Map<String, List<String>> thumMap = new HashMap<String, List<String>>();
		
		//得到请求的缩略图尺寸参数
		String picSize = request.getParameter("picSize");
		if(StringUtils.isNotBlank(picSize)){
			List<Properties> sizeList = JSON.parseArray(picSize, Properties.class);
			for (Properties thum : sizeList) {
				String type = thum.getProperty("type");//图片类型
				String size = thum.getProperty("size");//尺寸
				if(StringUtils.isNotBlank(size)){
					List<String> pics = null;
					if(StringUtils.equals(type, Constants.IMG)){
						//截图
						pics = appInfoDTO.getAppImages();
					}
					if(null != pics){
						List<String> thumFileUrlList = new ArrayList<String>();
						for (String imageStr : pics) {
							String imageStrBefore = StringUtils.substringBeforeLast(imageStr, "/");
							String imageStrAfter = StringUtils.substringAfterLast(imageStr, "/");
							AppThumbnail appThumbnail = Global.appThumbnailMap.get(imageStrAfter+"_"+size);
							if(null != appThumbnail){
								thumFileUrlList.add(imageStrBefore + "/" + appThumbnail.getThumbImg());
							}else{
								thumFileUrlList.add(imageStr);
							}
							
						}
						
						//加入返回的结果中
						thumMap.put(type, thumFileUrlList);
					}

				}
			}
		}

		appInfoDTO.setThumbnail(thumMap);
	}

	private boolean hasPrivilege(String packageName, Map<String,String[]> paramMap) {
		//不需要鉴权的cityCode直接返回有权限
		if(!DvbAuthUtils.isNeedDvbAuth(cityCode)){
			return true;
		}

		// 获取应用被哪些专题包含
		List<String> productCodeList = new ArrayList<String>();
		Plan plan = planFacade.getPlan(paramMap);
		List<AppSubjectType> planAppSubjectTypeList = planFacade.getPlanedAppSubjectTypeList(plan.getId());
		for (AppSubjectType subjectType : planAppSubjectTypeList) {
			//判断用户是否拥有app的权限
			List<String> appPkgNageList = this.getAppPkgNageListBySubjectId(subjectType.getId());
			if (appPkgNageList.contains(packageName)
					&& StringUtils.isNotBlank(subjectType.getProductCode())) {
				productCodeList.add(subjectType.getProductCode());
			}
		}
		
		//调用IUC接口进行鉴权
        boolean isAuth = DvbAuthUtils.dvbAuthority(userCode, cityCode, productCodeList);
        return isAuth;
	}
	
	private FucUser getLoginUserFromFUC(String token){
		FucUser user = null;
		if(StringUtils.isBlank(token)){
			return user;
		}
		String fuc_ip = Constants.FUC_SERVER_URL;
		if(StringUtils.isBlank(fuc_ip)){
			throw new BusinessException("没有设置FUC服务器地址");
		}
		if(!StringUtils.endsWith(fuc_ip, "/")){
			fuc_ip += "/";
		}
		
		//组装FUC请求
		String fuc_url = "http://" + fuc_ip + "user_getTicketUser";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ticket", token));
		
		//解析FUC的响应
		try {
			HttpResponse response = HttpUtil.doPost(fuc_url, params);
			if(null == response){
				logger.error("请求FUC失败，response为null，ticket={}", token);
			}else if (response.getStatusLine().getStatusCode() == 200) {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String retJson = EntityUtils.toString(entity, "UTF-8");
					logger.info("从FUC返回的登录验证结果为：{}, ticket={}：", new Object[]{retJson, token});

					if(StringUtils.isNotBlank(retJson)){
						JSONObject retResult = JSON.parseObject(retJson);
						
						String ret = retResult.getString("ret");	
						if("0".equals(ret)){//成功
							user = retResult.getObject("result", FucUser.class);
						}else{
							logger.info("请求FUC成功，但是返回结果不正确，ticket={}, ret={}", new Object[]{token, ret});
						}
					}
				}
			} else {
				logger.error("请求FUC失败，错误代码为{}", response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			logger.error("请求FUC失败，异常：{}", e.getMessage());
		}

		return user;
	}
    

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getAppId()
    {
        return appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getTypeId()
    {
        return typeId;
    }

    public void setTypeId(String typeId)
    {
        this.typeId = typeId;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public String getSubjectId()
    {
        return subjectId;
    }

    public void setSubjectId(String subjectId)
    {
        this.subjectId = subjectId;
    }

    public String getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }

    public String getRankType()
    {
        return rankType;
    }

    public void setRankType(String rankType)
    {
        this.rankType = rankType;
    }

    public String getAppIds()
    {
        return appIds;
    }

    public void setAppIds(String appIds)
    {
        this.appIds = appIds;
    }

    public String getPacNames()
    {
        return pacNames;
    }

    public void setPacNames(String pacNames)
    {
        this.pacNames = pacNames;
    }

    public String getAppPackageNames()
    {
        return appPackageNames;
    }

    public void setAppPackageNames(String appPackageNames)
    {
        this.appPackageNames = appPackageNames;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getAppStoreVersion()
    {
        return appStoreVersion;
    }

    public void setAppStoreVersion(int appStoreVersion)
    {
        this.appStoreVersion = appStoreVersion;
    }

    public int getOsVersion()
    {
        return osVersion;
    }

    public void setOsVersion(int osVersion)
    {
        this.osVersion = osVersion;
    }

    public String getAppCertificateId()
    {
        return appCertificateId;
    }

    public void setAppCertificateId(String appCertificateId)
    {
        this.appCertificateId = appCertificateId;
    }

    public String getClientModel()
    {
        return clientModel;
    }

    public void setClientModel(String clientModel)
    {
        this.clientModel = clientModel;
    }

    public String getDeviceType()
    {
        return deviceType;
    }

    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    public String getDeviceSerialNo()
    {
        return deviceSerialNo;
    }

    public void setDeviceSerialNo(String deviceSerialNo)
    {
        this.deviceSerialNo = deviceSerialNo;
    }

    public String getDeviceMac()
    {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac)
    {
        this.deviceMac = deviceMac;
    }

    public String getDeviceIp()
    {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp)
    {
        this.deviceIp = deviceIp;
    }

    public String getAppStoreClientVersion()
    {
        return appStoreClientVersion;
    }

    public void setAppStoreClientVersion(String appStoreClientVersion)
    {
        this.appStoreClientVersion = appStoreClientVersion;
    }

    public String getAppStoreClientInstallDate()
    {
        return appStoreClientInstallDate;
    }

    public void setAppStoreClientInstallDate(String appStoreClientInstallDate)
    {
        this.appStoreClientInstallDate = appStoreClientInstallDate;
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }


    public int getOptType()
    {
        return optType;
    }

    public void setOptType(int optType)
    {
        this.optType = optType;
    }

    public String getOptContent()
    {
        return optContent;
    }

    public void setOptContent(String optContent)
    {
        this.optContent = optContent;
    }

    public String getOptContentId()
    {
        return optContentId;
    }

    public void setOptContentId(String optContentId)
    {
        this.optContentId = optContentId;
    }

    public String getParam1()
    {
        return param1;
    }

    public void setParam1(String param1)
    {
        this.param1 = param1;
    }

    public String getParam2()
    {
        return param2;
    }

    public void setParam2(String param2)
    {
        this.param2 = param2;
    }

    public String getParam3()
    {
        return param3;
    }

    public void setParam3(String param3)
    {
        this.param3 = param3;
    }

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public int getLimit()
    {
        return limit;
    }

    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    public HttpServletRequest getRequest()
    {
        return request;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public HttpServletResponse getResponse()
    {
        return response;
    }

    public void setResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    public String getFeedbackText()
    {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText)
    {
        this.feedbackText = feedbackText;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public UserFeedbackService getUserFeedbackService()
    {
        return userFeedbackService;
    }

    public void setUserFeedbackService(UserFeedbackService userFeedbackService)
    {
        this.userFeedbackService = userFeedbackService;
    }

	public int getOldScore() {
		return oldScore;
	}

	public void setOldScore(int oldScore) {
		this.oldScore = oldScore;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public String getVersionCodes() {
		return versionCodes;
	}

	public void setVersionCodes(String versionCodes) {
		this.versionCodes = versionCodes;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

    
}
