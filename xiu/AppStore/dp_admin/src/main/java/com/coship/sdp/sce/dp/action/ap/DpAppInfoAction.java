/**
 * 文件名称：DpAppInfoAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-9-6
 *
 * 修改记录：1. 2011-9-29 Huxiaofeng/905358  修改了下载文件的方法
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.action.ap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.permission.service.UserService;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.ap.entity.DpStaff;
import com.coship.sdp.sce.dp.ap.service.DpAppInfoService;
import com.coship.sdp.sce.dp.ap.service.DpStaffService;
import com.coship.sdp.sce.dp.ap.service.HandAppRelateService;
import com.coship.sdp.sce.dp.appstore.service.MyAppService;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;
import com.coship.sdp.sce.dp.atachmentfile.service.AttachmentFileService;
import com.coship.sdp.sce.dp.audit.entity.DpAuditRecord;
import com.coship.sdp.sce.dp.audit.service.DpAuditRecordService;
import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;
import com.coship.sdp.sce.dp.certificate.service.AppCertificateService;
import com.coship.sdp.sce.dp.comment.entity.AppScoreSummary;
import com.coship.sdp.sce.dp.comment.service.AppCommentService;
import com.coship.sdp.sce.dp.common.AppStatusConstants;
import com.coship.sdp.sce.dp.common.AuditRecordTypeConstants;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.common.QueryAppInfoThread;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.plan.service.PlanService;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendPanelItemService;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendService;
import com.coship.sdp.sce.dp.sign.entity.AppSign;
import com.coship.sdp.sce.dp.sign.service.SignService;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.subject.service.AppSubjectTypeService;
import com.coship.sdp.sce.dp.tag.service.TagService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.sce.dp.utils.ApkInfo;
import com.coship.sdp.sce.dp.utils.ApkUtil;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.sce.dp.utils.MethodsUtil;
import com.coship.sdp.sce.dp.utils.Pinyin4jUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/*
 * 处理应用管理操作请求的action类
 * @author FuJian/906126
 * @version [版本号, 2011-9-6]
 * @since [产品/模块版本]
 */
@Controller
public class DpAppInfoAction extends BaseAction
{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DpAppInfoAction.class);
	private static final String MODULE_NAME = DpAppInfoAction.class.getName();
	
	/**
	 * 时间转换格式.
	 */
	public static final String TIMEF_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 推荐操作标识
	 */
	public static final String RECOMEND_FLAG = "1";//推荐
	public static final String DERECOMEND_FLAG = "0";//取消推荐

	/**
	 * 应用推荐
	 */
	public static final String SINGLE_RECOMEND = "0";//单个
	public static final String MULTIP_RECOMEND = "1";//多个

	/**
	 * 应用对象.
	 */
	private DpAppInfo appInfo;

	/**
	 * AP对象.
	 */
	private DpStaff dpStaff;

	/**
	 * 分页.
	 */
	private Page<DpAppInfo> page;

	/**
	 * 审核信息对象.
	 */
	private DpAuditRecord auditRecord;

	/**
	 * 审核人.
	 */
	private User user;

	/**
	 * 审核历史列表.
	 */
	private List<DpAuditRecord> auditRecordList;

	/**
	 * 审核意见.
	 */
	private String auditOption;

	/**
	 * 审核结果.
	 */
	private String auditResult;

	/**
	 * id集合.
	 */
	private String ids;

	/**
	 * 应用列表.
	 */
	private List<DpAppInfo> list;

	/**
	 * 判断文档类型.
	 */
	private int flag;

	/**
	 * 返回按钮标识
	 */
	private String noBackBtn;

	/**
	 * 上下架标志.
	 */
	private String onLineFlag;

	/**
	 * 附件对象.
	 */
	private AttachmentFile attachmentFile;

	/**
	 * 附件列表.
	 */
	private List<AttachmentFile> attachmentList;

	/**
	 * 应用类型对象.
	 */
	private DpType dpType;

	/**
	 * 专用于查询.
	 */
	private DpAppInfo queryAppInfo;

	/**
	 * 专用于查询.
	 */
	private List<DpAppInfo> queryList;

	/**
	 * 专用于查询.
	 */
	private DpStaff queryDpStaff;

	/**
	 * 评论统计类
	 */
	private AppScoreSummary appScoreSummary;

	/**
	 * 用于未审核的查询的标志.
	 */
	private int flags;

	/**
	 * 页面上传进来的页面跳转标识：audit：跳到审核页面；detail:跳到详情界面.
	 */
	private String forward;

	/**
	 * 操作选项：新增、修改、升级.
	 */
	private String operateOption;

	/**
	 * 记录异常信息.
	 */
	public String exceptionMsg = "";

	private String isModify;
	
	private String appTags;


    /**
     * 应用专题对象
     */
    private AppSubjectType appSubjectType;

	/**
	 * 应用类型列表.
	 */
	private List<DpType> dpTypeList;

	/**
	 * 语言类型列表.
	 */
	private List<DpType> languageDpTypeList;

	/**
	 * android版本类型列表.
	 */
	private List<DpType> androidDpTypeList;

	/**
	 * 操作类型列表.
	 */
	private List<DpType> opModeDpTypeList;
	
	private String planId;
	private String sourceAppPkgName;
	private String appName;
	private String typeId;
	


	/**
	 * 审核service层对象.
	 */
	private DpAuditRecordService dpAuditRecordService;
	
	/**
	 * 应用类型接口.
	 */
	private DpTypeService dpTypeService;

	/**
	 * 应用服务层对象.
	 */
	private DpAppInfoService dpAppInfoService;

	/**
	 * 应用推荐服务接口
	 */
	private AppRecommendService appRecommendService;

	/**
	 * 操作日志对象.
	 */
	private OpLoggerService opLoggerService;

	/**
	 * 附件服务对象.
	 */
	@Autowired
	private AttachmentFileService attachmentService;

	/**
	 * 评论服务类
	 */
	@Autowired
	private AppCommentService appCommentService;

	/**
	 * 管理员用户服务对象
	 */
	@Autowired
	private UserService userService;

	/**
	 * 应用签名信息服务对象.
	 */
	@Autowired
	private SignService signService;

	/**
	 * dpStaff服务对象.
	 */
	@Autowired
	private DpStaffService dpStaffService;

	/**
	 * 附件接口对象.
	 */
	@Autowired
	private AttachmentFileService attachmentFileService;

	/**
	 * 证书服务对象.
	 */
	@Autowired
	private AppCertificateService appCertificateService;

    /**
     * 专题dao对象.
     */
    @Autowired
    private AppSubjectTypeService appSubjectTypeService;

    /**
     * 我的应用dao对象.
     */
    @Autowired
    private MyAppService myAppService;
    
    /**
     * 精选页数据业务接口
     */
    @Autowired
    private AppRecommendPanelItemService appRecommendPanelItemService;
    
    /**
     * 应用标签Service
     */
    @Autowired
    private TagService tagService;
    
    /**
     * 方案Service
     */
    @Autowired
    private PlanService planService;
    
    /**
     * 猜你喜欢Service
     */
    @Autowired
    private HandAppRelateService handAppRelateService;
    
    
    //apk
    private File apk;
    private String apkFileName;
    private String apkFileSaveName;
    private String apkContentType;
    
    //应用logo
    private File[] logos;
    private String logoFileName;
    private String logoFileSaveName;
    private String[] logosFileName;
    private String[] logosFileSaveName;
	private String[] logosContentType;
    
    //应用截图
	private File[] imgs;
    private String imgFileName;
    private String imgFileSaveName;
    private String[] imgsFileName;
    private String[] imgsFileSaveName;
    private String[] imgsContentType;
    
    //应用海报
    private File[] posters;
    private String posterFileName;
    private String posterFileSaveName;
    private String[] postersFileName;
    private String[] postersFileSaveName;
    private String[] postersContentType;
    
    //游戏logo
    private File[] gameLogos;
    private String gameLogoFileName;
    private String gameLogoFileSaveName;
    private String[] gameLogosFileName;
    private String[] gameLogosFileSaveName;
	private String[] gameLogosContentType;
    
    
    private List<Map<String,String>> preLogo;
    private List<Map<String,String>> preImg;
    private List<Map<String,String>> prePoster;
    private List<Map<String,String>> preGameLogo;


	
	
	

	
	
	
    
    
    /**
     * 应用相关文档下载.
     * @return 返回下载结果字符串
     * @throws Exception 异常信息
     */
    public String doDownLoad() throws Exception
    {
        Debug.logVerbose("DpAppInfoAction.doDownLoad() start...");

        try
        {
            // 记录是部署文档还是文件包
            int mark = getFlag();

            // 文件路径
            String filePath = "";

            int length;
            appInfo = dpAppInfoService.findAppInfo(appInfo.getId());

            attachmentList = appInfo.getAttachmentList();

            // 获取list长度
            length = attachmentList.size();

            // 文件名称
            String fileName = null;

            if (mark == 1)
            {
                // app文档包路径
                filePath = Constants.SIGNED_APP_SAVE_PATH;
                for (int i = 0; i < length; i++)
                {
                    if (Constants.APPFILE.equals(attachmentList.get(i)
                            .getFileDesc()))
                    {
                        fileName = attachmentList.get(i).getFileSaveName();
                    }
                }
            }
            FileUtil.downLoad(filePath, fileName, response);
        }
        catch (IOException e)
        {
            exception_msg = getText("sdp.sce.dp.admin.ap.download.fail");
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            return ERROR;
        }
        Debug.logVerbose("DpAppInfoAction.doDownLoad() end...");
        return null;
    }


	/**
	 * 查询全部AP应用列表.
	 *
	 * @return 返回列表页面配置字符串
	 */
	public String doList()
	{
		Debug.logVerbose("DpAppInfoAction.doList() start...");
		try
		{
			page = new Page<DpAppInfo>();
			page.setPageSize(limit);
			page.setCurrentPage(start);
			String hql = "from DpAppInfo order by createTime desc ";

			page = dpAppInfoService.listAppInfo(page, hql, new Object[0]);

			setStaffOrUser();

			searchTypeList();
		}
		catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = getText("sdp.sce.dp.admin.common.query.error");
			return ERROR;
		}
		Debug.logVerbose("DpAppInfoAction.doList() end...");
		return "doList";
	}
	
	/**
	 * 精选页版块元素查询可用的应用信息
	 * @return
	 */
	 public String doAppList()
	    {
	        Debug.logVerbose("DpAppInfoAction.doAppList() start...");

	        if (queryAppInfo == null)
	        {
	            queryAppInfo = new DpAppInfo();
	        }

	        try
	        {
	            searchTypeList();
                queryAppInfo.setAppStatus(AppStatusConstants.APP_STATUS_ONLINE);
	            page = new Page<DpAppInfo>();
	            page.setPageSize(limit);
	            page.setCurrentPage(start);

	            String userName = null;
	            if (queryDpStaff != null)
	            {
	                userName = queryDpStaff.getUserName();
	            }
	            page = dpAppInfoService.searchAppInfo(page, queryAppInfo,
	                    userName, 0);

	            // 为user或dpStaff对象设值
	            this.setStaffOrUser();
	        }
	        catch (Exception e)
	        {
	            Debug.logError(e, e.getMessage(), MODULE_NAME);
	            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
	            return ERROR;
	        }
	        Debug.logVerbose("DpAppInfoAction.doSearchList() end...");
	        return "doAppList";
	    }

	/**
	 * 跳转到审核或详情界面.
	 * 
	 * @return
	 */
	public String doDisplay() {
		// 跳转到审核界面
		String path = "gotoAudit";
		Debug.logVerbose("DpAppInfoAction.doDisplay() start...");
		try {
			appInfo = dpAppInfoService.findAppInfo(appInfo.getId());
			auditRecordList = dpAuditRecordService.listAuditRecord(
					appInfo.getId(),
					AuditRecordTypeConstants.APP_AUDIT_RECORD_TYPE);

			appInfo.setSystemDpType(dpTypeService.findByTypeCode(String
					.valueOf(appInfo.getSystem())));

			appInfo.setLanguageDpType(dpTypeService.findByTypeCode(String
					.valueOf(appInfo.getLanguage())));

			opModeDpTypeList = dpTypeService
					.findByParentTypeCode(DefaultTypeCodeConstants.OP_MODE_TYPE_CODE);

			// 获取应用标签
			String appTags = tagService.getTagsNameByAppPackageName(appInfo
					.getAppFilePackage());
			setAppTags(appTags);

			if ("detail".equals(forward)) {
				appScoreSummary = appCommentService.getAppScoreSummary(appInfo);
				// 跳转到详情界面
				path = "gotoDetail";
			}else if("modifyOnline".equals(forward)){
				path = "modifyOnline";
				//查询该应用所在的方案
				Set<Plan> planSet = planService.getPlanSetByApp(appInfo.getAppFilePackage());
				this.setResult("planList", new ArrayList<Plan>(planSet));
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = e.getMessage();
			return ERROR;
		}
		Debug.logVerbose("DpAppInfoAction.doDisplay() end...");
		return path;
	}

	/**
	 * 初始化日志参数.
	 *
	 * @param user 用户对象
	 * @param operate1 操作1
	 * @param operate2 操作2
	 * @param operate3 操作3
	 * @return
	 */
	private List<String> initLogParames(User user, String operate1,
			String operate2, String operate3)
	{

		// 日志参数
		List<String> logParamList = new ArrayList<String>();

		logParamList.add(user.getUserName());

		logParamList.add(getText(operate1));

		logParamList.add(getText(operate2));

		logParamList.add(getText(operate3));

		return logParamList;
	}

	/**
	 * 审核应用 .
	 *
	 * @return
	 */
	public String doAudit()
	{
		Debug.logVerbose("DpAppInfoAction.doAudit() start...");
		User userObj = (User) request.getSession().getAttribute("user");
		DpAuditRecord auditRecord = new DpAuditRecord();
		try
		{
			appInfo = dpAppInfoService.findAppInfo(appInfo.getId());
			auditRecord.setAuditResult(auditResult);
			auditRecord.setAuditOption(auditOption);
			auditRecord.setAuditDate(new Date());
			auditRecord
					.setAuditFlag(AuditRecordTypeConstants.APP_AUDIT_RECORD_TYPE);
			auditRecord.setAuditRecordId(appInfo.getId());
			auditRecord.setAssessor(userObj.getUserName());

			// 审核是否通过 1: 通过 2:不通过
			if ("1".equals(auditResult))
			{
				appInfo.setAppStatus(AppStatusConstants.APP_STATUS_PASS_AUDITED);
				appInfo.setSortNum(1);
				appInfo.setUpdateTime(new Date());
			}
			else if ("2".equals(auditResult))
			{
				appInfo.setAppStatus(AppStatusConstants.APP_STATUS_NOPASS_AUDITED);
			}
			// 审核 数据存入数据库
			dpAppInfoService.auditAppInfo(appInfo, auditRecord);

			List<String> deployLogParam = initLogParame(userObj.getUserName(),
					"sdp.sce.dp.admin.ap.appinfo",
					"sdp.sce.dp.common.log.deploy.operate",
					appInfo.getAppName());

			opLoggerService.info(
					getText("sdp.sce.dp.admin.ap.name"),
					getText("sdp.sce.dp.admin.log.app.deploy.log",
							deployLogParam),
					getText("sdp.sce.dp.common.log.deploy.operate"));
		}
		catch (RuntimeException re)
		{
			exception_msg = getText("sdp.sce.dp.admin.ap.audit.error");
			Debug.logError(re, re.getMessage(), MODULE_NAME);
			return ERROR;
		}
		catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = getText("sdp.sce.dp.admin.ap.audit.error");
			return ERROR;
		}

		// 判断返回哪个页面
		String flagPage = request.getParameter("flag");
		if ("0".equals(flagPage))
		{
			setFlags(1); // 将标记设为 待审核
			return doSearchList();
		}
		else if ("1".equals(flagPage))
		{
			return doList();
		}
		Debug.logVerbose("DpAppInfoAction.doAudit() end...");
		return null;
	}
	

	/**
	 * 删除应用
	 * @return
	 */
	public String doDelete() {
		Debug.logVerbose("DpAppInfoAction.doDelete() start...");

		if (ids == null) {
			Debug.logWarning("ids is null", MODULE_NAME);
			return doList();
		}

		
		StringBuffer sbf1 = new StringBuffer();
		StringBuffer sbf2 = new StringBuffer();
		List<DpAppInfo> appList = new ArrayList<DpAppInfo>();
		String[] idsStr = ids.split(","); // 获取要删除的id
		for (String str : idsStr) {
			// 得到要删除的应用
			try {
				DpAppInfo dpAppInfo = dpAppInfoService.findAppInfo(str.trim());
				if (AppStatusConstants.APP_STATUS_ONLINE.equals(dpAppInfo.getAppStatus())) {
					// 如果应用已上架，则不能删除
					sbf1.append(dpAppInfo.getAppName()).append("、");
				}else if(appRecommendPanelItemService.isRelated(dpAppInfo.getAppFilePackage())){
					sbf2.append(dpAppInfo.getAppName()).append("、");
				}else {
					appList.add(dpAppInfo);
				}
			} catch (Exception e) {
				setResult(
						"exception",
						getText("sdp.sce.dp.admin.common.data.delete.exception"));
				write(JSONObject.fromObject(getResult()).toString());
				Debug.logError(e, e.getMessage(), MODULE_NAME);
				exception_msg = getText("sdp.sce.dp.admin.common.data.delete.exception");
			}
		}
        
		String errStr = sbf1.toString();
        if(!"".equals(errStr)){
        	errStr = "应用:{"+errStr.substring(0, errStr.length()-1)+"}已上架，不允许删除";
        }else{
        	errStr = sbf2.toString();
        	if(!"".equals(errStr)){
        		errStr = "应用:{"+errStr.substring(0, errStr.length()-1)+"}已被精选页关联，请先取消";
        	}
        }
        
        if(!"".equals(errStr)){
        	setResult("msg", errStr);
			write(JSONObject.fromObject(getResult()).toString());
			return null;
        }


		try {
			for (DpAppInfo dpAppInfo : appList) {
				// 删除文件
				deleteFileList(dpAppInfo.getAttachmentList());

				// 删除签名信息
				signService.deltelSignByAppId(dpAppInfo);

				// 删除附件关联
				attachmentFileService
						.deleteAttachmentRelationByAppId(dpAppInfo);

				// 删除我的应用关联
				myAppService.deleteMyAppRelationByAppId(dpAppInfo);
				// 删除专题关联
				appSubjectTypeService
						.deleteAppSubjectRelationByAppId(dpAppInfo);
				// 删除应用推荐关联
				appRecommendService.cancleRecommendDpAppInfo(dpAppInfo);
				// 删除应用评论关联
				appCommentService.deleteAppCommentInfo(dpAppInfo);
				// 删除审核记录关联
				dpAuditRecordService.deleteAuditRecord(dpAppInfo.getId(),
						Constants.AUDIT_APP_RECORD_FLAG);
				// 删除数据库信息
				dpAppInfo.setAttachmentList(null);
				dpAppInfoService.deleteAppInfo(dpAppInfo);
				// dpAppInfoService.deleteAppInfo(dpAppInfo);
				// deleteFile(dpAppInfo, attachmentFileList); // 删除文件

				User userSession = (User) request.getSession().getAttribute("user");
				List<String> logParamList = initLogParames(userSession,
						"sdp.sce.dp.admin.ap.appinfo",
						"sdp.sce.dp.admin.log.delete.operate",
						"sdp.sce.dp.admin.log.operate.result.success");
				logParamList.add(dpAppInfo.getId());
				opLoggerService.info(getText("sdp.sce.dp.admin.ap.name"),
						getText("sdp.sce.dp.admin.ap.release", logParamList),
						getText(Constants.DEL));
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = getText("sdp.sce.dp.admin.ap.delete.file.error");
		}

		setResult("success", true);
		write(JSONObject.fromObject(getResult()).toString());
		Debug.logVerbose("DpAppInfoAction.doDelete() end...");
		return null;
	}

	/**
	 * 按条件查询AP信息.
	 * @return
	 */
	public String doSearchList()
	{
		Debug.logVerbose("DpAppInfoAction.doSearchList() start...");
		// 返回路径
		String returnPath = "doSearchList";
		if (queryAppInfo == null)
		{
			queryAppInfo = new DpAppInfo();
		}

		try
		{
			searchTypeList();

			// flags为1：待审核的AP信息查询请求
			if (flags == 1)
			{
				queryAppInfo
						.setAppStatus(AppStatusConstants.APP_STATUS_PENDING_AUDITE);
				returnPath = "doSearchNotAuditList";
			}

			page = new Page<DpAppInfo>();
			page.setPageSize(limit);
			page.setCurrentPage(start);

			String userName = null;
			if (queryDpStaff != null)
			{
				userName = queryDpStaff.getUserName();
			}
			page = dpAppInfoService.searchAppInfo(page, queryAppInfo,
					userName, 0);

			// 为user或dpStaff对象设值
            this.setStaffOrUser();
		}
		catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = getText("sdp.sce.dp.admin.common.query.error");
			return ERROR;
		}
		Debug.logVerbose("DpAppInfoAction.doSearchList() end...");
		return returnPath;
	}

	/**
	 * 获取上下架操作的列表
	 * @return 待上下架列表页面字符串
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	public String doSearchOnOrOffList()
	{

		Debug.logVerbose("DpAppInfoAction.doSearchOnOrOffList() start...");

		// 查询条件数据封装对象
		if (queryAppInfo == null)
		{
			queryAppInfo = new DpAppInfo();
		}

		page = new Page<DpAppInfo>();
		page.setPageSize(limit);
		page.setCurrentPage(start);
		try
		{
			dpAppInfoService.searchOnOrOffLineOptAppInfo(page, queryAppInfo,
					queryDpStaff);

			searchTypeList();

			// 为user或dpStaff对象设值
			this.setStaffOrUser();
		}
		catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = getText("sdp.sce.dp.admin.common.query.error");
			return ERROR;
		}
		Debug.logVerbose("DpAppInfoAction.doSearchOnOrOffList() end...");
		return "doOnOrOffLineList";
	}

	/**
	 * 批量取消推荐.
	 * @return
	 */
	public String doBatchCommend()
	{
		String[] idsStr = ids.split(","); // 获取要删除的id
		User userSession = (User) request.getSession().getAttribute("user");
		for (String str : idsStr)
		{
			try
			{
				// 得到要取消推荐的应用
				appInfo = dpAppInfoService.findAppInfo(str.trim());

				// 取消首页推荐
				appRecommendService.cancleRecommendDpAppInfo(appInfo);

				List<String> logParamList = initLogParames(userSession,
						"sdp.sce.dp.admin.ap.appinfo",
						"sdp.sce.dp.admin.log.delete.operate",
						"sdp.sce.dp.admin.log.operate.result.success");
				logParamList.add(str);
				opLoggerService.info(getText("sdp.sce.dp.admin.ap.name"),
						getText("sdp.sce.dp.admin.ap.release", logParamList),
						getText(Constants.DEL));

			}
			catch (Exception e)
			{
				Debug.logError(e, e.getMessage(), MODULE_NAME);
				exception_msg = this
						.getText("sdp.sce.dp.admin.ap.commend.error");

				setResult("exception", exception_msg);

				write(JSONObject.fromObject(getResult()).toString());
				Debug.logError(e, e.getMessage(), MODULE_NAME);
			}
		}
		setResult("success", true);
		write(JSONObject.fromObject(getResult()).toString());
		Debug.logVerbose("DpAppInfoAction.doBatchCommend() end...");
		return null;
	}

	/**
	 * 应用上下架.
	 *
	 * @return
	 */
	public String doOnOrDownline()
	{
		Debug.logVerbose("DpAppInfoAction.doOnOrDownline() start...");
		User userObj = (User) request.getSession().getAttribute("user");
		try
		{
			appInfo = dpAppInfoService.findAppInfo(appInfo.getId());

			// 记录日志
			String onLine = "";
			if (("1").equals(onLineFlag))
			{
				appInfo.setAppStatus(AppStatusConstants.APP_STATUS_ONLINE);
				onLine = getText("sdp.sce.dp.admin.log.shelves.operate");
			}
			else
			{
	        	//是否有关联精选页
	    		if(appRecommendPanelItemService.isRelated(appInfo.getAppFilePackage())){
	    			throw new Exception("应用:"+appInfo.getAppName()+" 已被精选页关联，请先取消");
	    		}
	    		
				appInfo.setAppStatus(AppStatusConstants.APP_STATUS_OFFLINE);
				onLine = getText("sdp.sce.dp.admin.log.downline.operate");
			}

			dpAppInfoService.onOrOffLineAppInfo(appInfo);

			// 更新appstore_service存放在内存的应用信息
			new QueryAppInfoThread().start();

			List<String> logParamList = initLogParames(userObj,
					"sdp.sce.dp.admin.ap.appinfo", onLine,
					"sdp.sce.dp.admin.log.operate.result.success");
			logParamList.add(appInfo.getAppName());

			opLoggerService.info(getText("sdp.sce.dp.admin.ap.name"),
					getText("sdp.sce.dp.admin.ap.examine", logParamList),
					onLine);
		}
		catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = this
						.getText("sdp.sce.dp.admin.ap.doOnOrDownline.error");
			}
			return ERROR;
		}

		// 全部应用的列表上下架操作
		if (flag == 0)
		{
			return doSearchList();
		}
		// 上下架应用的列表上下架操作
		else
		{
			return doSearchOnOrOffList();
		}
	}


	/**
	 * <功能描述>
	 * @return [参数说明]
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	public String toAddRecommentList()
	{
		// 查询条件数据封装对象
		if (queryAppInfo == null)
		{
			queryAppInfo = new DpAppInfo();
		}

		// 已上架的应用
		queryAppInfo.setAppStatus(AppStatusConstants.APP_STATUS_ONLINE);
		page = new Page<DpAppInfo>();
		page.setPageSize(limit);
		page.setCurrentPage(start);
		try
		{
			dpAppInfoService.searchPreRecomentAppList(page, queryAppInfo);

            // 获取所有的应用分类列表
            searchTypeList();
		}
		catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = getText("sdp.sce.dp.admin.common.query.error");
			return ERROR;
		}
		Debug.logVerbose("DpAppInfoAction.toAddRecommentList() end...");
		return "toAddRecommandList";
	}
	
	/**
	 * <功能描述>
	 * @return [参数说明]
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	public String toAddTypeRecommentList()
	{
		// 查询条件数据封装对象
		if (queryAppInfo == null)
		{
			queryAppInfo = new DpAppInfo();
		}

		// 已上架的应用
		queryAppInfo.setAppStatus(AppStatusConstants.APP_STATUS_ONLINE);
		page = new Page<DpAppInfo>();
		page.setPageSize(limit);
		page.setCurrentPage(start);
		try
		{
			dpAppInfoService.searchPreRecomentAppTypeList(page, queryAppInfo);

            // 获取所有的应用分类列表
            searchTypeList();
		}
		catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = getText("sdp.sce.dp.admin.common.query.error");
			return ERROR;
		}
		Debug.logVerbose("DpAppInfoAction.toAddTypeRecommentList() end...");
		return "toAddTypeRecommandList";
	}

    /**
     * 批量删除应用文件logo\截图\ 海报\应用文件.
     *
     * @param attachmentList 附件列表
     * @return void
     */
    public void deleteFileList(List<AttachmentFile> attachmentList)
    {

        for (AttachmentFile attFile : attachmentList)
        {
            deleteFile(attFile);

        }
    }

	/**
	 * 应用删除/判断从IDE传过来的是否为默认图片 true为不是.
	 *
	 * @param attachment 附件对象
	 * @return
	 */
	public boolean checkLogo(AttachmentFile attachment)
	{
		String fileName = attachment.getFileName();
		if ((!"IDEDefaultLogo.png".equals(fileName))
				&& (!"IDEDefaultLogo2.png".equals(fileName))
				&& (!"IDEDefaultLogo3.png".equals(fileName))
				&& (!"tripLogo.png".equals(fileName))
				&& (!"tripLogo2.png".equals(fileName))
				&& (!"tripLogo3.png".equals(fileName)))
		{

			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 检查附件的图片.
	 *
	 * @param attachment 附件对象
	 * @return
	 */
	public boolean checkImg(AttachmentFile attachment)
	{
		String fileName = attachment.getFileName();
		if ((!"tripImg1.png".equals(fileName))
				&& (!"tripImg2.png".equals(fileName))
				&& (!"tripImg3.png".equals(fileName))
				&& (!"IDEDefaultImg1.png".equals(fileName))
				&& (!"IDEDefaultImg2.png".equals(fileName))
				&& (!"IDEDefaultImg3.png".equals(fileName)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 将翻页列表中查询出的用户id转换为对象存储.
	 *
	 * @return 无
	 */
	private void setStaffOrUser() throws Exception
	{
		if (page != null)
		{
			DpStaff staff = new DpStaff();
			User user = new User();
			for (DpAppInfo appInfo : page.getResultList())
			{
				// 设置staff对象或者user对象
				String staffId = appInfo.getDpStaffId();
				if (staffId != null)
				{
					staff = dpStaffService.findDpStaff(staffId);
					appInfo.setDpStaff(staff);
				}

				String userId = appInfo.getUserId();
				if (userId != null)
				{
				    user = userService.getUser(userId);
				    appInfo.setUser(user);
				}

			}
		}
	}

	/**
     * 后台管理员升级应用
     *
     * @return 无
     */
    public String doUpgradeApp()
    {
        Debug.logVerbose("DpAppInfoAction.doUpgradeApp() start...");

        User userObj = (User) request.getSession().getAttribute("user");
        if (userObj == null)
        {
            return "logout";
        }

        // 修改应用的时候只有在apk对象不为空的时候才验证，为空的情况已经在提交页面时通过ajax请求验证
/*        if (apk != null && apk.length() <= 0)
        {
            exception_msg = getText("sdp.sce.dp.admin.download.upload.length.is0");
            return ERROR;
        }*/

        DpAppInfo appObj = null;
        // 升级之前的应用包名，为了方便和上传后的包名进行比较
        String prePackageName = null;
        DpAppInfo preAppInfo = null;
        try
        {
            preAppInfo = dpAppInfoService.findAppInfo(appInfo.getId());
            prePackageName = preAppInfo.getAppFilePackage();

            // 获取原有的versionCode字段
            appInfo.setVersionCode(preAppInfo.getVersionCode());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ERROR;
        }

        try
        {
            // 持久化应用信息
            saveAppInfo(userObj);

            appObj = dpAppInfoService.findAppInfo(appInfo.getId());

            dpType = dpTypeService.findType(dpType.getId());
            appObj.setDpType(dpType);

            // 上传附件，并返回附件列表
            List<AttachmentFile> attachmentFileList = getAttachmentFileList();
            if(!StringUtils.equals(exception_msg, "")){
            	return ERROR;
            }
            if (CollectionUtils.isEmpty(attachmentFileList))
            {
                // 为空，证明不能上传文件或者上传有问题，删除原先应用草稿
                dpAppInfoService.deleteAppInfo(appObj);
                return ERROR;
            }

            if (apk != null && StringUtils.isEmpty(appInfo.getAppFilePackage()))
            {
            	// 为了处理有时appInfo.getAppFilePackage()获取到的值还是为空的异常情况
				exception_msg = "上传的APK包名不能为空，请重新上传！";
				dpAppInfoService.deleteAppInfo(appObj);
				return ERROR;
            }

            if (!appInfo.getAppFilePackage().equals(prePackageName))
            {
                // 升级时判断包名是否和原来一致，不一致，则不能升级
                if (appObj != null)
                {
                    // 删除签名信息
                    signService.deltelSignByAppId(appObj);

                    dpAppInfoService.deleteAppInfo(appObj);
                }
                exception_msg = getText("sdp.sce.dp.admin.ap.upload.package.name.error");
                return ERROR;
            }

            // 持久化附件信息
            if (CollectionUtils.isNotEmpty(attachmentFileList))
            {
                for (AttachmentFile attachment : attachmentFileList)
                {
                    attachment.setDpAppInfo(appObj);
                    attachmentFileService.saveAttachmentFile(attachment);
                }
            }

            // 设置包名,并更新应用
            appObj.setAppFilePackage(appInfo.getAppFilePackage());
            appObj.setSystem(appInfo.getSystem());
            appObj.setVersion(appInfo.getVersion());
            appObj.setVersionCode(appInfo.getVersionCode());
            appObj.setHandAvgScore(preAppInfo.getHandAvgScore());
            appObj.setHandDownCount(preAppInfo.getHandDownCount());
            appObj.setHandScoreCount(preAppInfo.getHandScoreCount());
            appObj.setUpdateTime(new Date());
            appObj.setAppStatus(AppStatusConstants.APP_STATUS_PENDING_AUDITE);
            dpAppInfoService.updateAppInfo(appObj);

            // 日志参数
            List<String> logParamList = initLogParame(userObj.getUserName(),
                    "应用更新",
                    "sdp.sce.dp.admin.log.add.operate",
                    appObj.getAppName());
            // 记录日志
            opLoggerService.info("应用更新",
                    this.getText("sdp.sce.dp.admin.log.download.operate.log",
                            logParamList), this.getText(Constants.ADD));

        }
        catch (Exception e)
        {
            if (appObj != null)
            {
                try
                {
                    // 执行过程出现异常，那将删除原先新增的对象
                    dpAppInfoService.deleteAppInfo(appObj);
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                    return ERROR;
                }
            }
            e.printStackTrace();
            return ERROR;
        }

        Debug.logVerbose("DpAppInfoAction.doUpgradeApp() end...");

        return doSearchAdminAppList();
    }

	/**
	 * 后台管理员发布应用
	 * 
	 * @return 无
	 */
	public String doAddApp() {
		Debug.logVerbose("DpAppInfoAction.doAddApp() start...");

		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj == null) {
			return "logout";
		}

		DpAppInfo appObj = null;
		try {
			// 持久化应用信息
			this.saveAppInfo(userObj);
			appObj = dpAppInfoService.findAppInfo(appInfo.getId());

			//查询分类信息
			dpType = dpTypeService.findType(dpType.getId());
			appObj.setDpType(dpType);

			//下拉选择框
			this.searchTypeList();
			languageDpTypeList = dpTypeService
					.findByParentTypeCode(DefaultTypeCodeConstants.LANGUAGE_TYPE_CODE);
			androidDpTypeList = dpTypeService
					.findByParentTypeCode(DefaultTypeCodeConstants.ANDROID_TYPE_CODE);
			opModeDpTypeList = dpTypeService
					.findByParentTypeCode(DefaultTypeCodeConstants.OP_MODE_TYPE_CODE);

			// 上传附件，并返回附件列表
			List<AttachmentFile> attachmentFileList = this.getAttachmentFileList();
			if (StringUtils.isNotBlank(exception_msg)) {
				return ERROR;
			}
			if (CollectionUtils.isEmpty(attachmentFileList)) {
				dpAppInfoService.deleteAppInfo(appObj);
				setApkFileName(null);
				setApkFileSaveName(null);
				return "addUploadApp";
			}

			if (apk == null || StringUtils.isEmpty(appInfo.getAppFilePackage())) {
				// 为了处理有时appInfo.getAppFilePackage()获取到的值还是为空的异常情况
				exception_msg = "上传的APK包名不能为空，请重新上传！";
				dpAppInfoService.deleteAppInfo(appObj);
				return "addUploadApp";
			}

			// 持久化附件信息
			for (AttachmentFile attachment : attachmentFileList) {
				attachment.setDpAppInfo(appObj);
				attachmentFileService.saveAttachmentFile(attachment);
			}

			// 设置包名,并更新应用
			appObj.setAppFilePackage(appInfo.getAppFilePackage());
			appObj.setSystem(appInfo.getSystem());
			appObj.setVersion(appInfo.getVersion());
			appObj.setVersionCode(appInfo.getVersionCode());
			appObj.setUpdateTime(new Date());
			appObj.setAppStatus(AppStatusConstants.APP_STATUS_PENDING_AUDITE);
			dpAppInfoService.updateAppInfo(appObj);

			// 持久化应用标签信息
			if (StringUtils.isNotBlank(appTags)) {
				tagService.saveAppTag(appInfo.getAppFilePackage(), appTags.split(","));
			}

			// 日志参数
			List<String> logParamList = initLogParame(userObj.getUserName(),
					"应用新增", "sdp.sce.dp.admin.log.add.operate",
					appObj.getAppName());
			// 记录日志
			opLoggerService.info("应用新增", this.getText(
					"sdp.sce.dp.admin.log.download.operate.log", logParamList),
					this.getText(Constants.ADD));
		} catch (Exception e) {
			if (appObj != null) {
				// 如果是新增和升级应用的时候发生异常，那将持久化的应用记录删除
				try {
					// 执行过程出现异常，那将删除原先新增的对象
					dpAppInfoService.deleteAppInfo(appObj);
				} catch (Exception e1) {
					e1.printStackTrace();
					return ERROR;
				}
			}

			e.printStackTrace();
			return "addUploadApp";
		}

		Debug.logVerbose("DpAppInfoAction.doAddApp() end...");

		return doSearchAdminAppList();
	}

	/**
	 * 后台管理员修改应用
	 * 
	 * @return 无
	 */
	public String doModifyApp() {
		Debug.logVerbose("DpAppInfoAction.doModifyApp() start...");

		//判断用户是否登陆
		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj == null) {
			return "logout";
		}

		// 修改应用的时候只有在apk对象不为空的时候才验证，为空的情况已经在提交页面时通过ajax请求验证
		/*
		 * if (apk != null && apk.length() <= 0) { exception_msg =
		 * getText("sdp.sce.dp.admin.download.upload.length.is0"); return ERROR;
		 * }
		 */

		try {
			//获取应用信息
			DpAppInfo appObj = dpAppInfoService.findAppInfo(appInfo.getId());

			//获取应用分类信息
			dpType = dpTypeService.findType(dpType.getId());
			appObj.setDpType(dpType);

			// 上传附件，并返回附件列表
			List<AttachmentFile> attachmentFileList = getAttachmentFileList();
			if (!StringUtils.equals(exception_msg, "")) {
				return ERROR;
			}
			if (CollectionUtils.isEmpty(attachmentFileList)) {
				return ERROR;
			}

			if (apk != null && StringUtils.isEmpty(appInfo.getAppFilePackage())) {
				// 为了处理有时appInfo.getAppFilePackage()获取到的值还是为空的异常情况
				exception_msg = "上传的APK包名不能为空，请重新上传！";
				dpAppInfoService.deleteAppInfo(appObj);
				return ERROR;
			}

			List<AttachmentFile> attaList = new ArrayList<AttachmentFile>();
			for (AttachmentFile entity : appObj.getAttachmentList()) {
				attaList.add(entity);
			}

			Iterator<AttachmentFile> newAttaList = attachmentFileList.iterator();
			while (newAttaList.hasNext()) {
				AttachmentFile uploadAtta = newAttaList.next();

				Iterator<AttachmentFile> oldIterator = attaList.iterator();
				while (oldIterator.hasNext()) {
					if (oldIterator.next().getFileSaveName()
							.equals(uploadAtta.getFileSaveName())) {
						newAttaList.remove();
						oldIterator.remove();
					}
				}
			}

			// 删除无用的附件
			for (AttachmentFile uploadAtta : attaList) {
				if (Constants.APPFILE.equals(uploadAtta.getFileDesc())
						&& null == apk) {
					continue;
				}

				attachmentFileService.deleteAttachmentFileById(uploadAtta
						.getId());
			}

			// 持久化附件信息
			for (AttachmentFile attachment : attachmentFileList) {
				attachment.setDpAppInfo(appObj);
				attachmentFileService.saveAttachmentFile(attachment);
			}

			// 持久化应用标签信息
			if (StringUtils.isNotBlank(appTags)) {
				tagService.saveAppTag(appObj.getAppFilePackage(), appTags.split(","));
			}

			// 创建文档后应用为草稿状态
			appObj.setAppDesc(appInfo.getAppDesc());
			appObj.setDpType(dpType);
			appObj.setLanguage(appInfo.getLanguage());
			appObj.setOpMode(appInfo.getOpMode());
			appObj.setHandAvgScore(appInfo.getHandAvgScore());
			appObj.setHandDownCount(appInfo.getHandDownCount());
			appObj.setHandScoreCount(appInfo.getHandScoreCount());
			if (StringUtils.isNotEmpty(appInfo.getSystem())) {
				appObj.setSystem(appInfo.getSystem());
			}
			if (StringUtils.isNotEmpty(appInfo.getVersion())) {
				appObj.setVersion(appInfo.getVersion());
			}
			if (appInfo.getVersionCode() != 0) {
				appObj.setVersionCode(appInfo.getVersionCode());
			}
			appObj.setDeveloper(appInfo.getDeveloper());
			appObj.setPrice(appInfo.getPrice());
			appObj.setAppName(appInfo.getAppName());
			appObj.setAppNamePinyin(doGetPinyinHeadChar(appInfo.getAppName()));
			// 设置包名
			if (appInfo != null && appInfo.getAppFilePackage() != null) {
				appObj.setAppFilePackage(appInfo.getAppFilePackage());
			}

			appObj.setUpdateTime(new Date());

			// 应用上架功能中修改应用信息时不修改应用状态. flags=100 应用上架模块进行应用信息更新.
			if (flags != 100) {
				appObj.setAppStatus(AppStatusConstants.APP_STATUS_PENDING_AUDITE);
			}
			appObj.setSubjectPoster(appInfo.getSubjectPoster());
			dpAppInfoService.updateAppInfo(appObj);

			// 日志参数
			List<String> logParamList = initLogParame(userObj.getUserName(),
					"应用更新", "sdp.sce.dp.admin.log.add.operate",
					appObj.getAppName());
			// 记录日志
			opLoggerService.info("应用更新", this.getText(
					"sdp.sce.dp.admin.log.download.operate.log", logParamList),
					this.getText(Constants.ADD));

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

		Debug.logVerbose("DpAppInfoAction.doModifyApp() end...");

		// 应用上架功能模块中修改应用信息，返回到应用上架列表.
		if (flags == 100) {
			return doSearchOnOrOffList();
		}
		return doSearchAdminAppList();
	}

	/*
     * 持久化非修改时的应用信息
     *
     * @return 返回应用名称是否唯一的标记
     */
	private void saveAppInfo(User userObj) throws Exception {
		Debug.logVerbose("DpAppInfoAction.saveAppInfo() start...");

		// 新增和升级应用的时候需要新增一个应用
		Date date = new Date();
		appInfo.setCreateTime(date);
		appInfo.setUpdateTime(date);
		appInfo.setDpType(dpType);

		// 管理员身份
		appInfo.setUserId(userObj.getId());

		// 创建文档后应用为草稿状态
		appInfo.setAppStatus(AppStatusConstants.APP_STATUS_DRAFT);
		appInfo.setAppNamePinyin(doGetPinyinHeadChar(appInfo.getAppName()));

		//持久化
		dpAppInfoService.saveAppInfo(appInfo);

		Debug.logVerbose("DpAppInfoAction.saveAppInfo() end...");
	}

    /**
     * 提取每个汉字的首字母.
     * @param cnStr 汉字.
     * @return 首字母.
     */
    public String doGetPinyinHeadChar(String cnStr)
    {
        return Pinyin4jUtil.getPinYinHeadChar(cnStr);
    }

	/**
	 * 查询管理员全部应用列表.
	 *
	 * @return 返回列表页面配置字符串
	 */
	public String doSearchAdminAppList()
	{
		Debug.logVerbose("DpAppInfoAction.doSearchAdminAppList() start...");
		try
		{
			User userObj = (User) request.getSession().getAttribute("user");
			if (userObj == null)
			{
				return "logout";
			}

			if (queryAppInfo == null)
			{
				queryAppInfo = new DpAppInfo();
			}

			page = new Page<DpAppInfo>();
			page.setPageSize(limit);
			page.setCurrentPage(start);

			page = dpAppInfoService.searchAppInfo(page, queryAppInfo,
					userObj.getUserName(), 1);

			searchTypeList();
		}
		catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = getText("sdp.sce.dp.admin.common.query.error");
			return ERROR;
		}

		Debug.logVerbose("DpAppInfoAction.doSearchAdminAppList() end...");

		return "doAdminList";
	}

	/**
	 * 初始化附件信息.
	 *
	 */
	public List<AttachmentFile> getAttachmentFileList() {
		List<AttachmentFile> attachmentFileList = new ArrayList<AttachmentFile>();

		// logo上传，并返回附件列表
		attachmentFileList = getLogoAttachmentList(attachmentFileList);
		// 截图上传，并返回附件列表
		attachmentFileList = getImgAttachmentList(attachmentFileList);
		// 海报上传，并返回附件列表
		attachmentFileList = getPosterAttachmentList(attachmentFileList);
		// apk上传，并返回附件列表
		attachmentFileList = getApkAttachmentList(attachmentFileList);
		// gameLogo上传，并返回附件列表
		attachmentFileList = this.getGameLogoAttachmentList(attachmentFileList);

		return attachmentFileList;
	}

	/**
	 * 上传apk文件，并返回附件列表
	 * 
	 * @return List<AttachmentFile> 返回null表示错误，否则上传成功
	 */
	private List<AttachmentFile> getApkAttachmentList(
			List<AttachmentFile> attachmentFileList) {
		if (!StringUtils.isEmpty(apkFileName)
				&& !StringUtils.isEmpty(apkFileSaveName)) {
			apk = new File(Constants.APP_FILE_SAVE_PATH + File.separator + apkFileSaveName);
		}

		// apkFile
		if (apk != null && apk.exists() && apk.isFile()) {
			String fileSaveName = apkFileSaveName;
			String fileType = fileSaveName.substring(fileSaveName
					.lastIndexOf(".") + 1);

			// 保存一条附件信息
			AttachmentFile apkAttachmentFile = initAttachmentFile(apkFileName,
					fileSaveName, fileType, apk, Constants.APP_FILE_SAVE_PATH,
					Constants.APPFILE);

			ApkInfo apkInfo = ApkUtil.getApkInfo(Constants.APP_FILE_SAVE_PATH
					+ File.separator + apkAttachmentFile.getFileSaveName());

			// 验证apk的xml信息是否合法
			if (validateApkFile(apkInfo, apkAttachmentFile)) {
				return null;
			}

			String apkPackageName = apkInfo.getPackageName();
			List<DpAppInfo> dpAppInfos = null;
			if (!"Upgrade".equals(forward)) {
				dpAppInfos = uniqueApkPackageName(apkPackageName);
				if(null != dpAppInfos){
					for (DpAppInfo temp : dpAppInfos) {
						if(!StringUtils.equals(temp.getId(), appInfo.getId())){
							exception_msg = "包名" + apkPackageName + "与应用("
									+ dpAppInfos.get(0).getAppName() + ")重复!";
							deleteAttachFile(apkAttachmentFile);
							return null;
						}
					}
				}
			}

			// 为空不存在相同包名
			boolean signed = signedAppFile(apkAttachmentFile.getFileSaveName());
			if (signed) {
				String targetFilePath = Constants.SIGNED_APP_SAVE_PATH
						+ File.separator
						+ apkAttachmentFile.getFileSaveName();

				File file = new File(targetFilePath);
				if (file != null) {
					// 设置签名后的文件大小
					apkAttachmentFile.setFileSize(file.length());
				}

				appInfo.setAppFilePackage(apkPackageName);
				appInfo.setSystem(String.valueOf(apkInfo.getMinSdkVersion()));
				appInfo.setVersion(apkInfo.getVersionName());
				appInfo.setVersionCode(apkInfo.getVersionCode());

				// 签名成功才将对象持久化
				attachmentFileList.add(apkAttachmentFile);
			} else {
				// 服务器内部出错
				exception_msg = apkFileName
						+ getText("sdp.sce.dp.admin.ap.sign.error");
				deleteAttachFile(apkAttachmentFile);
				return null;
			}
			
			if (signed || !MethodsUtil.isNull(dpAppInfos)) {
				// 签名成功或者文件存在apk包，那删除的appfile下的文件
				deleteAttachFile(apkAttachmentFile);
			}
		} else {
			apk = null;
			setApkFileName(null);
			setApkFileSaveName(null);
		}

		return attachmentFileList;
	}

	/**
	 * 验证解析的apk是否合法
	 * true: 表明不合法
	 */
	private boolean validateApkFile(ApkInfo apkInfo, AttachmentFile attaFile)
	{
	    if (null == apkInfo)
        {
            exception_msg = "apk文件无法解析";
            deleteAttachFile(attaFile);
            return true;
        }

        // 判断apk中versionCode是否有填写
        if (apkInfo.getVersionCode() == 0)
        {
            exception_msg = "apk不规范，AndroidMantifest.xml"
                    + " 必须包含android:versionCode字段";
            deleteAttachFile(attaFile);
            return true;
        }

        if (apkInfo.getMinSdkVersion() == 0)
        {
            exception_msg = "apk不规范，AndroidMantifest.xml"
                    + " 必须包含uses-sdk标签";
            return true;
        }

        // 应用包名
        String apkPackageName = apkInfo.getPackageName();

        // 如果解析的包名为空，则直接返回空的附件予以标记不能解析包
        if (apkPackageName == null)
        {
            exception_msg = getText("sdp.sce.dp.admin.ap.package.not.found");
            deleteAttachFile(attaFile);
            return true;
        }

        if ("Upgrade".equals(forward))
        {
            int preVersionCode = appInfo.getVersionCode();
            int newVersionCode = apkInfo.getVersionCode();
            if (preVersionCode >= newVersionCode)
            {
                exception_msg = "升级的apk的版本号不能小于或等于原有的apk版本号！";
                deleteAttachFile(attaFile);
                return true;
            }
        }

        return false;
	}

	/**
	 * 删除appFile文件下的apk
	 */
	private void deleteAttachFile(AttachmentFile apkAttachmentFile)
	{
	    FileUtil.forceDelete(Constants.APP_FILE_SAVE_PATH + File.separator
                + apkAttachmentFile.getFileSaveName());
	}

	/**
	 * 上传logo图片，并返回附件列表
	 * @return List<AttachmentFile> 返回null表示错误，否则上传成功
	 */
	private List<AttachmentFile> getLogoAttachmentList(List<AttachmentFile> attachmentFileList)
	{	
		preLogo = new ArrayList<Map<String,String>>(); 
		// 空指针校验
		if (!StringUtils.isEmpty(logoFileName) && !StringUtils.isEmpty(logoFileSaveName))
		{
			logosFileName = logoFileName.split(",");
			logosFileSaveName = logoFileSaveName.split(",");

			// 判断两个数组长度是否相等,防止出现空指针
			if (logosFileName.length == logosFileSaveName.length && logosFileSaveName.length > 0)
			{
				logos = new File[logosFileSaveName.length];
				for (int j = 0; j < logosFileSaveName.length; j++)
				{
					if(StringUtils.isEmpty(logosFileSaveName[j]) || StringUtils.isEmpty(logosFileName[j])){
						continue;
					}
					logos[j] = new File(Constants.APP_LOGO_SAVE_PATH
							+ File.separator + logosFileSaveName[j]);
					
					Map<String,String> map = new HashMap<String,String>();
					map.put(logosFileSaveName[j],logosFileName[j]);
					preLogo.add(map);
				}
			}
		}
		
		if (logos != null)
		{
			for (int i = 0; i < logos.length; i++)
			{
				if (null == logos[i])
				{
					continue;
				}
				
				File logo = logos[i];
				String logoFileName = logosFileName[i];
				String logoContenType = logoFileName.substring(logoFileName.lastIndexOf(".")+1);

				String fileSaveName = logosFileSaveName[i];
				if (!logo.exists() && !logo.isFile())
				{
					continue;
				}
				
				//判断图片尺寸是否正确
				if(!isPictureSizeTrue(i+1, logo)){
					break;
				}

			 // 保存一条附件信息
				AttachmentFile logoAttachmentFile = initAttachmentFile(
						logoFileName,
						fileSaveName,
						logoContenType,
						logo, Constants.APP_LOGO_SAVE_PATH,
						Constants.LOGO);
				attachmentFileList.add(logoAttachmentFile);
			}
		}

		return attachmentFileList;
	}
	

	/**
	 * 上传logo图片，并返回附件列表
	 * 
	 * @return List<AttachmentFile> 返回null表示错误，否则上传成功
	 */
	private List<AttachmentFile> getGameLogoAttachmentList(List<AttachmentFile> attachmentFileList) {
		if(null == attachmentFileList){
			attachmentFileList = new ArrayList<AttachmentFile>();
		}
		
		preGameLogo = new ArrayList<Map<String, String>>();
		// 空指针校验
		if (StringUtils.isNotBlank(gameLogoFileName)
				&& StringUtils.isNotBlank(gameLogoFileSaveName)) {
			gameLogosFileName = gameLogoFileName.split(",");
			gameLogosFileSaveName = gameLogoFileSaveName.split(",");

			// 判断两个数组长度是否相等,防止出现空指针
			if (gameLogosFileName.length == gameLogosFileSaveName.length
					&& gameLogosFileSaveName.length > 0) {
				gameLogos = new File[gameLogosFileSaveName.length];
				for (int j = 0; j < gameLogosFileSaveName.length; j++) {
					if (StringUtils.isEmpty(gameLogosFileSaveName[j])
							|| StringUtils.isEmpty(gameLogosFileName[j])) {
						continue;
					}
					gameLogos[j] = new File(Constants.APP_LOGO_SAVE_PATH + File.separator + gameLogosFileSaveName[j]);

					Map<String, String> map = new HashMap<String, String>();
					map.put(gameLogosFileSaveName[j], gameLogosFileName[j]);
					preGameLogo.add(map);
				}
			}
		}

		if (gameLogos != null) {
			for (int i = 0; i < gameLogos.length; i++) {
				if (null == gameLogos[i]) {
					continue;
				}

				File gameLogo = gameLogos[i];
				String gameLogoFileName = gameLogosFileName[i];
				String gameLogoContenType = gameLogoFileName.substring(gameLogoFileName.lastIndexOf(".") + 1);

				String fileSaveName = gameLogosFileSaveName[i];
				if (!gameLogo.exists() && !gameLogo.isFile()) {
					continue;
				}

				// 保存一条附件信息
				AttachmentFile gameLogoAttachmentFile = initAttachmentFile(
						gameLogoFileName, fileSaveName, gameLogoContenType, gameLogo,
						Constants.APP_LOGO_SAVE_PATH, Constants.GAME_LOGO);
				attachmentFileList.add(gameLogoAttachmentFile);
			}
		}

		return attachmentFileList;
	}
	
	private boolean isPictureSizeTrue(int num, File pictrueFile) {
		boolean result = true;
//		BufferedImage image;
//		try {
//			image = ImageIO.read(new FileInputStream(pictrueFile));
//			int width = image.getWidth();
//			int height = image.getHeight();
//
//			int pictrueWidth = 0;
//			int pictrueHeight = 0;
//			if (num == 1) {
//				pictrueWidth = 215;
//				pictrueHeight = 215;
//			} else if (num == 2) {
//				pictrueWidth = 140;
//				pictrueHeight = 140;
//			} else if (num == 3) {
//				pictrueWidth = 290;
//				pictrueHeight = 140;
//			} else if (num == 4) {
//				pictrueWidth = 290;
//				pictrueHeight = 290;
//			}
//			if(pictrueWidth>0 && pictrueHeight>0){
//				if (pictrueWidth != width || pictrueHeight != height) {
//					result = false;
//					exception_msg = "请严格按照规定的尺寸上传Logo,第"+num+"张图片的正确尺寸是: "+pictrueWidth+" * " + pictrueHeight;
//				}
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return result;
	}

	/**
	 * 上传img图片，并返回附件列表
	 * @return List<AttachmentFile> 返回null表示错误，否则上传成功
	 */
	private List<AttachmentFile> getImgAttachmentList(List<AttachmentFile> attachmentFileList)
	{   
		preImg = new ArrayList<Map<String,String>>();
		// 空指针校验
  		if (!StringUtils.isEmpty(imgFileName) && !StringUtils.isEmpty(imgFileSaveName))
		{
			imgsFileName = imgFileName.split(",");
			imgsFileSaveName = imgFileSaveName.split(",");

			// 判断两个数组长度是否相等,防止出现空指针
			if (imgsFileName.length == imgsFileSaveName.length && imgsFileSaveName.length > 0)
			{
				imgs = new File[imgsFileSaveName.length];
				for (int j = 0; j < imgsFileSaveName.length; j++)
				{
					if (StringUtils.isEmpty(imgsFileSaveName[j]) || StringUtils.isEmpty(imgsFileName[j]))
					{
                         continue;
					}
					imgs[j] = new File(Constants.APP_IMAGES_SAVE_PATH + File.separator + imgsFileSaveName[j]);
					Map<String,String> map = new HashMap<String,String>();
					map.put(imgsFileSaveName[j], imgsFileName[j]);
					preImg.add(map);
				}
			}
		}
		
		if (imgs != null)
		{
			for (int i = 0; i < imgs.length; i++)
			{
				if(null == imgs[i]){
					continue;
				}
				
				File img = imgs[i];
				String imgFileName = imgsFileName[i];
				String imgContenType = imgFileName.substring(imgFileName.lastIndexOf(".")+1);

				String fileSaveName = imgsFileSaveName[i];
				if (!img.exists() && !img.isFile())
				{
					continue;
				}

				// 保存一条附件信息
				AttachmentFile imgAttachmentFile = initAttachmentFile(
						imgFileName,
						fileSaveName,
						imgContenType,
						img, Constants.APP_IMAGES_SAVE_PATH,
						Constants.IMG);
				attachmentFileList.add(imgAttachmentFile);
			}
		}

		return attachmentFileList;
	}

	/**
	 * 上传poster图片，并返回附件列表
	 * @return List<AttachmentFile>类型，返回null表示错误，否则上传成功
	 */
	private List<AttachmentFile> getPosterAttachmentList(List<AttachmentFile> attachmentFileList)
	{
		prePoster = new ArrayList<Map<String,String>>();
		// 空指针校验
		if (!StringUtils.isEmpty(posterFileName) && !StringUtils.isEmpty(posterFileSaveName))
		{
			postersFileName = posterFileName.split(",");
			postersFileSaveName = posterFileSaveName.split(",");

			// 判断两个数组长度是否相等,防止出现空指针
			if (postersFileName.length == postersFileSaveName.length && postersFileSaveName.length > 0)
			{
				posters = new File[postersFileSaveName.length];
				for (int j = 0; j < postersFileSaveName.length; j++)
				{
					if (StringUtils.isEmpty(postersFileSaveName[j]) || StringUtils.isEmpty(postersFileName[j])){
						continue;
					}
						
					posters[j] = new File(Constants.APP_IMAGES_SAVE_PATH + File.separator + postersFileSaveName[j]);
					Map<String,String> map = new HashMap<String,String>();
					map.put(postersFileSaveName[j], postersFileName[j]);
					prePoster.add(map);
				}
			}
		}
		
		if (posters != null)
		{
			for (int i = 0; i < posters.length; i++)
			{
				if (null == posters[i])
				{
					continue;
				}
				
				File poster = posters[i];
				String posterFileName = postersFileName[i];
				String posterContenType = posterFileName.substring(posterFileName.lastIndexOf(".")+1);

				String fileSaveName = postersFileSaveName[i];
				if (!poster.exists() && !poster.isFile())
				{
					continue;
				}

				// 保存一条附件信息
				AttachmentFile imgAttachmentFile = initAttachmentFile(
						posterFileName,
						fileSaveName,
						posterContenType,
						poster, Constants.APP_IMAGES_SAVE_PATH,
						Constants.POSTER);
				attachmentFileList.add(imgAttachmentFile);
			}
		}

		return attachmentFileList;
	}

	/**
	 * 上传文件，并返回文件类型
	 * @return String 返回文件类型，返回null表示错误，否则上传成功
	 */
	private String uploadFile(File file, String savePath, String fileName)
	{
		// 文件全路径名
		String fileSaveName = getSaveFileName(fileName);

		try
		{
			FileUtil.uploadFile(file, savePath, fileSaveName);
		}
		catch (FileNotFoundException e)
		{
			exception_msg = getText("sdp.sce.dp.admin.ap.file.name.begin")
				+ apkFileName + getText("sdp.sce.dp.admin.ap.file.name.end");
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			return null;
		}
		catch (IOException e)
		{
			exception_msg = getText("sdp.sce.dp.admin.ap.file.name.begin")
				+ apkFileName + getText("sdp.sce.dp.admin.ap.file.name.upload.error");
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			return null;
		}

		return fileSaveName;
	}

	 /**
     * 校验应用名称是否唯一.
     *
     * @return void 返回json串
     * @exception throws [违例类型] [违例说明]
     */
    public void doUniqueAppName()
    {
        setResult("success", false);

        try
        {
            if (null != appInfo)
            {
                List<DpAppInfo> appInfoList = dpAppInfoService
                        .findByAppName(appInfo.getAppName());

                if (null != appInfoList && !appInfoList.isEmpty())
                {
                    setResult("success", true);
                }
            }
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            write(JSONArray.fromObject(getResult()).toString());
        }
        write(JSONArray.fromObject(getResult()).toString());
    }


	/**
	 * 查询应用分类、语言分类、android版本分类，跳转至上传页面.
	 *
	 * @return String 跳转页面的result
	 */
	public String doApp()
	{

		Debug.logVerbose("DpAppInfoAction.doApp() start...");
		try
		{
			searchTypeList();

			languageDpTypeList = dpTypeService
					.findByParentTypeCode(DefaultTypeCodeConstants.LANGUAGE_TYPE_CODE);

			androidDpTypeList = dpTypeService
					.findByParentTypeCode(DefaultTypeCodeConstants.ANDROID_TYPE_CODE);

            opModeDpTypeList = dpTypeService
                    .findByParentTypeCode(DefaultTypeCodeConstants.OP_MODE_TYPE_CODE);
		}
		catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exceptionMsg = this.getText("sdp.sce.dp.admin.ap.query.error");
			return ERROR;
		}

		// 新增操作
		operateOption = forward;

		if (!Constants.OPERATE_ADD.equals(forward))
		{
			if (appInfo != null)
			{
				try
				{
					appInfo = dpAppInfoService.findAppInfo(appInfo.getId());
				}
				catch (Exception e)
				{
					Debug.logError(e, "connot find the appInfo object by id【" + appInfo.getId() + "】");
					e.printStackTrace();
				}
			}

            //设置附件信息
            setUpAttachment(appInfo);
            
            //获取应用标签
            String appTags = tagService.getTagsNameByAppPackageName(appInfo.getAppFilePackage());
            setAppTags(appTags);
        
			if (Constants.OPERATE_UPGRADE.equals(forward))
			{
				operateOption = forward;
			}
			else
			{
				// 修改操作
				operateOption = forward;
			}
		}
		
		if(null == appInfo){
			appInfo = new DpAppInfo();
		}

		Debug.logVerbose("DpAppInfoAction.doApp() end...");

		return "addUploadApp";
	}


	 /**
     * 跳转到新增专题应用列表页面
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String toAddAppToSubjectType()
    {
        try
        {
            if (StringUtils.isNotEmpty(appSubjectType.getId()))
            {
                appSubjectType = appSubjectTypeService
                        .getAppSubjectTypeById(appSubjectType.getId());

                if (page == null)
                {
                    page = new Page<DpAppInfo>();
                    page.setPageSize(limit);
                    page.setCurrentPage(start);
                }

                // 获取未被该专题关联的上架应用
                appSubjectTypeService
                        .queryAppInfoPageBySubjectTypeNoRel(page,
                                appSubjectType, queryAppInfo);

                // 获取应用分类列表
                searchTypeList();
            }
            return "toAddAppToSubject";
        }
        catch (Exception e)
        {
            exception_msg = "跳转编辑页面出错。";
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            return ERROR;
        }
    }

	/**
	 * 删除附件
	 *
	 * @return String json
	 * @exception throws [违例类型] [违例说明]
	 */
	public String doDeleteAttachment()
	{
		Debug.logVerbose("DpAppInfoAction.doDeleteAttachment() start...");
		setResult("success", true);

		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj == null)
		{
			return "logout";
		}

		try
		{
			if (attachmentFile == null)
			{
				setResult("exception",
						getText("sdp.sce.dp.admin.ap.appInfo.del.exception"));
				setResult("success", false);
				write(JSONObject.fromObject(getResult()).toString());
				return null;
			}

			attachmentFile = attachmentFileService
					.findAttachmentFileById(attachmentFile.getId());

			appInfo = dpAppInfoService.findAppInfo(attachmentFile
					.getDpAppInfo().getId());

			// 删除物理文件
			deleteFile(attachmentFile);

			// 删除附件信息
			attachmentFileService.deleteAttachmentFileById(attachmentFile
					.getId());

			if (Constants.APPFILE.equals(attachmentFile.getFileDesc()))
			{
				// 删除签名信息
				signService.deltelSignByAppId(appInfo);
				appInfo.setAppFilePackage(null);
			}

			// 删除附件成功应用变成草稿状态
			appInfo.setAppStatus(AppStatusConstants.APP_STATUS_DRAFT);
			dpAppInfoService.updateAppInfo(appInfo);
		}
		catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			setResult("exception",
					getText("sdp.sce.dp.admin.ap.appInfo.del.exception"));
			setResult("success", false);

		}
		write(JSONObject.fromObject(getResult()).toString());
		return null;
	}

	/**
	 * 在提交修改的应用的时候，使用ajax判断应用是否有附件
	 * flag：0：表apk，logo，海报，截取都没有
	 * flag：1：表应用没有apk包
	 * flag：2：表应用没有logo图标
	 * flag：3：表应用没有截图
	 * flag：4：表应用没有海报
	 * @return String json
	 * @exception throws [违例类型] [违例说明]
	 */
	public String doValidateAttachment() {
		Debug.logVerbose("DpAppInfoAction.doValidateAttachment() start...");

		if (appInfo == null)
		{
			setResult("exception", getText("sdp.sce.dp.admin.server.error"));
			setResult("success", false);
			write(JSONObject.fromObject(getResult()).toString());
			return null;
		}

		try
		{
			appInfo = dpAppInfoService.findAppInfo(appInfo.getId());
			List<AttachmentFile> attachmentList = appInfo.getAttachmentList();
			if (CollectionUtils.isEmpty(attachmentList))
			{
				setResult("exception", getText("sdp.sce.dp.admin.ap.upload.empty"));
				setResult("flag", "0");
				setResult("success", false);
				write(JSONObject.fromObject(getResult()).toString());
				return null;
			}

			int appFileCount = 0, logoCount = 0, imgCount = 0, posterCount = 0;
			for (AttachmentFile attachment : attachmentList)
			{
				if ("appfile".equals(attachment.getFileDesc()))
				{
					++appFileCount;
				}
				else if ("logo".equals(attachment.getFileDesc()))
				{
					++logoCount;
				}
				else if ("img".equals(attachment.getFileDesc()))
				{
					++imgCount;
				}
				else
				{
					++posterCount;
				}
			}

			// 分别判断应用是否有包含附件，如果没有，则显示提示信息
			if (appFileCount == 0)
			{
				setResult("exception", getText("sdp.sce.dp.admin.ap.apk.empty"));
				setResult("flag", "1");
				setResult("success", false);
				write(JSONObject.fromObject(getResult()).toString());
				return null;
			}
			if (logoCount == 0)
			{
				setResult("exception", getText("sdp.sce.dp.admin.ap.logo.empty"));
				setResult("flag", "2");
				setResult("success", false);
				write(JSONObject.fromObject(getResult()).toString());
				return null;
			}
			if (imgCount == 0)
			{
				setResult("exception", getText("sdp.sce.dp.admin.ap.img.empty"));
				setResult("flag", "3");
				setResult("success", false);
				write(JSONObject.fromObject(getResult()).toString());
				return null;
			}
			if (posterCount == 0)
			{
				setResult("exception", getText("sdp.sce.dp.admin.ap.poster.empty"));
				setResult("flag", "4");
				setResult("success", false);
				write(JSONObject.fromObject(getResult()).toString());
				return null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			setResult("exception", getText("sdp.sce.dp.admin.server.error"));
			setResult("success", false);
			write(JSONObject.fromObject(getResult()).toString());
			return null;
		}
		Debug.logVerbose("DpAppInfoAction.doValidateAttachment() end...");

		setResult("success", true);
		write(JSONObject.fromObject(getResult()).toString());

		return null;
	}

	/**
	 * 删除应用文件logo\截图\ 海报\应用文件.
	 *
	 * @param attFile 附件
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	public void deleteFile(AttachmentFile attFile)
	{

		// 保存文件的路径
		String filePath = "";
		String fileDesc = attFile.getFileDesc();

		if (Constants.APPFILE.equals(fileDesc))
		{
			// 删除已签名应用文件
			filePath = Constants.SIGNED_APP_SAVE_PATH + File.separator
					+ attFile.getFileSaveName();
		}
		else if (Constants.LOGO.equals(fileDesc))
		{

			// 删除Logo
			filePath = Constants.APP_LOGO_SAVE_PATH + File.separator
					+ attFile.getFileSaveName();

		}
		else if (Constants.IMG.equals(fileDesc)
				|| Constants.POSTER.equals(fileDesc))
		{

			// 删除应用截图

			filePath = Constants.APP_IMAGES_SAVE_PATH + File.separator
					+ attFile.getFileSaveName();
		}

		FileUtil.deleteFile(filePath);

	}


    /**
     * 根据证书、私钥签名应用文件.
     *
     * @param publicKeyName 签名证书
     * @param privateKeyName 签名证书私钥
     * @param sourceFileName 原始文件,及签名后的文件（不同目录）
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private boolean signedAppFile(String sourceFileName)
    {
        boolean flag = true;

        try
        {
            AppCertificate appCert = appCertificateService
                    .findAppCertificateByType(
                            Constants.CERTIFICATE_SIGNED_FLAG,
                            Constants.CERTIFICATE_STATUS_NORMAL);

            // if +1 begin
            if (null == appCert
                    || StringUtils.isEmpty(sourceFileName))
            {
                flag = false;
            }
            else
            {
                // 签名证书
                String publicKeyPath = Constants.CERTIFICATE_SAVE_PATH
                        + File.separator + appCert.getCertificateSaveName();

                // 证书私钥
                String privateKeyPath = Constants.CERTIFICATE_SAVE_PATH
                        + File.separator + appCert.getPrivateKeySaveName();

                // 未签名应用文件
                String sourceFilePath = Constants.APP_FILE_SAVE_PATH
                        + File.separator + sourceFileName;

                // 签名后的应用文件
                String targetFilePath = Constants.SIGNED_APP_SAVE_PATH
                        + File.separator + sourceFileName;

                boolean signedFlag = MethodsUtil.signed(publicKeyPath,
                        privateKeyPath, sourceFilePath, targetFilePath);

                // 签名成功保存记录到数据库，app对应证书\私钥.
                AppSign sign = new AppSign();
                sign.setDpAppInfo(appInfo);
                if (signedFlag)
                {
                    sign.setAppCert(appCert);
                    sign.setCreateTime(new Date());

                }
                // 保存之前先清理掉旧的证书  xiewangxing@20140213
                signService.deltelSignByAppId(appInfo);
                signService.saveSign(sign);
            }
            // if +1 end

        }
        catch (Exception e)
        {
            flag = false;
            Debug.logError(e, e.getMessage(), MODULE_NAME);
        }
        return flag;

    }

	/**
	 * 获得唯一包名,此判断不适合在升级的时候
	 *
	 * @param apkPackageName 包名
	 * @return List<DpAppInfo> 应用对象
	 * @exception throws [违例类型] [违例说明]
	 */
	private List<DpAppInfo> uniqueApkPackageName(String apkPackageName)
	{
		String hql = "from DpAppInfo da where da.appFilePackage = ?";
		List<DpAppInfo> uniqueDpAppInfo = null;
		try
		{
			uniqueDpAppInfo = dpAppInfoService.listByHQL(hql, new Object[]
			{ apkPackageName});
		}
		catch (Exception e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
		}

		return uniqueDpAppInfo;
	}

	/**
	 * 获得apk包名.
	 * @param saveName apk存储文件名
	 * @return 包名
	 */
	private String getPkgName(String saveName)
	{
		String pkgName = null;

		// 获得包名
		if (!MethodsUtil.isNull(saveName))
		{
			pkgName = ApkUtil.getApkPkgName(Constants.APP_FILE_SAVE_PATH
					+ File.separator + saveName);
		}

		return pkgName;
	}

	/**
	 * 创建附件对象.
	 *
	 * @param fileName 文件名称
	 * @param saveFileName 保存的文件名称
	 * @param fileType 文件类型
	 * @param file 文件
	 * @return AttachmentFile 附件对象
	 */
	private AttachmentFile initAttachmentFile(String fileName, String fileSaveName,
			String fileType, File file, String path, String fileDesc)
	{
		// 保存附件信息
		AttachmentFile attachment = new AttachmentFile();
		attachment.setCreateDate(new Date());
		attachment.setFileName(fileName);
		attachment.setFileSaveName(fileSaveName);
		attachment.setFileType(fileType);
		attachment.setFileDesc(fileDesc);
		attachment.setFileSize(file.length());
		attachment.setDpAppInfo(appInfo);
		return attachment;
	}

	/**
	 * 查询所有的应用分类列表
	 */
	private void searchTypeList() throws Exception
	{
		dpTypeList = dpTypeService.getGameAndAppTypeList();
	}

	/**
	 * 获得保存后的文件名.
	 *
	 * @param fileName 原文件名
	 * @param file 文件对象
	 * @param path 保存路径
	 * @return String 保存后的文件名
	 */
	private String getSaveFileName(String fileName)
	{
		String Suffix = fileName.substring(fileName.lastIndexOf("."));
		String saveUploadFileName = UUID.randomUUID().toString() + Suffix;
		return saveUploadFileName;
	}
	
	private void setUpAttachment(DpAppInfo appInfo){
		List<AttachmentFile> attachmentFiles = appInfo.getAttachmentList();
		String apk = "";
		String apkSave = "";
		String logo = "";
		String logoSave = "";
		String img = "";
		String imgSave = "";
		String poster = "";
		String posterSave = "";
		String gameLogo = "";
		String gameLogoSave = "";
		preLogo = new ArrayList<Map<String,String>>();
		preImg = new ArrayList<Map<String,String>>();
		prePoster = new ArrayList<Map<String,String>>();
		preGameLogo = new ArrayList<Map<String,String>>();
		
		
		for (AttachmentFile file : attachmentFiles)
		{
			if("appfile".equals(file.getFileDesc())){
				apk = file.getFileName();
				apkSave = file.getFileSaveName();
			}
			else if ("logo".equals(file.getFileDesc()))
			{
				logo += file.getFileName()+",";
				logoSave += file.getFileSaveName()+",";
				Map<String,String> map = new HashMap<String,String>();
				map.put(file.getFileSaveName(),file.getFileName());
				preLogo.add(map);
			} 
			else if ("img".equals(file.getFileDesc()))
			{
				img += file.getFileName() + ",";
				imgSave += file.getFileSaveName() + ",";
				Map<String,String> map = new HashMap<String,String>();
				map.put(file.getFileSaveName(),file.getFileName());
				preImg.add(map);
			} 
			else if ("poster".equals(file.getFileDesc()))
			{
				poster += file.getFileName() + ",";
				posterSave += file.getFileSaveName() + ",";
				Map<String,String> map = new HashMap<String,String>();
				map.put(file.getFileSaveName(),file.getFileName());
				prePoster.add(map);
			}
			else if ("gameLogo".equals(file.getFileDesc()))
			{
				gameLogo += file.getFileName() + ",";
				gameLogoSave += file.getFileSaveName() + ",";
				Map<String,String> map = new HashMap<String,String>();
				map.put(file.getFileSaveName(),file.getFileName());
				preGameLogo.add(map);
			}
		}

		setApkFileName(apk);
		setApkFileSaveName(apkSave);
		setLogoFileName(logo);
		setLogoFileSaveName(logoSave);
		setImgFileName(img);
		setImgFileSaveName(imgSave);
		setPosterFileName(poster);
		setPosterFileSaveName(posterSave);
		setGameLogoFileName(gameLogoSave);
		setGameLogoFileSaveName(gameLogoSave);
		setPreLogo(preLogo);
		setPreImg(preImg);
		setPrePoster(prePoster);
		setPreGameLogo(preGameLogo);
		
	}
	
	/**
     * 应用批量上下架
     * @return
     */
    public String doBatchOnOrDownline()
    {
        Debug.logVerbose("DpAppInfoAction.doOnOrDownline() start...");
        User userObj = (User) request.getSession().getAttribute("user");
        try
        {
            String[] appIds = ids.split(",");
            String status = "";

            StringBuffer sbf = new StringBuffer();
            List<DpAppInfo> list = new ArrayList<DpAppInfo>();
			for (String key : appIds) {
				appInfo = dpAppInfoService.findAppInfo(key.trim());

				if (("1").equals(onLineFlag)) {
					status = AppStatusConstants.APP_STATUS_ONLINE;
				} else {
					// 是否有关联精选页
					if (appRecommendPanelItemService.isRelated(appInfo
							.getAppFilePackage())) {
						sbf.append(appInfo.getAppName()).append("、");
					}
					status = AppStatusConstants.APP_STATUS_OFFLINE;
				}

				appInfo.setAppStatus(status);
				list.add(appInfo);
			}
            
            String errStr = sbf.toString();
            if(!"".equals(errStr)){
            	exception_msg = "应用:{"+errStr.substring(0, errStr.length()-1)+"}已被精选页关联，请先取消";
                return ERROR;
            }
            
            for (DpAppInfo dpAppInfo : list) {
            	dpAppInfoService.onOrOffLineAppInfo(dpAppInfo);
			}

            String onLine = getText("sdp.sce.dp.admin.log.shelves.operate");

            // 更新appstore_service存放在内存的应用信息
            new QueryAppInfoThread().start();

            List<String> logParamList = initLogParames(userObj,
                    "sdp.sce.dp.admin.ap.appinfo", onLine,
                    "sdp.sce.dp.admin.log.operate.result.success");
            logParamList.add(appInfo.getAppName());

            opLoggerService.info(getText("sdp.sce.dp.admin.ap.name"),
                    getText("sdp.sce.dp.admin.ap.examine", logParamList),
                    onLine);
        }
        catch (Exception e)
        {
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = this
						.getText("sdp.sce.dp.admin.ap.doOnOrDownline.error");
			}
			return ERROR;
        }

        return doSearchOnOrOffList();
    }
    
    

    /**
     * 排序
     * @return
     */
	public String doSort() {
		this.setResult("success", false);
		String appId = request.getParameter("appId");
		String sortNum = request.getParameter("sortNum");
		
		try {
			if(StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(sortNum)){
				DpAppInfo appInfo = dpAppInfoService.findAppInfo(appId);
				if(null != appInfo){
					appInfo.setSortNum(Integer.valueOf(sortNum).intValue());
					appInfo.setUpdateTime(new Date());
					this.dpAppInfoService.updateAppInfo(appInfo);
	
					// 日志参数
					User user = (User) request.getSession().getAttribute("user");
					List<String> logParamList = initLogParame(user.getUserName(),
							"sdp.sce.dp.admin.ap.appinfo",
							"sdp.sce.dp.admin.log.sort.operate",
							appInfo.getAppName());
					
					// 记录日志
					opLoggerService.info(this.getText("sdp.sce.dp.admin.ap.name"),
							this.getText("sdp.sce.dp.admin.ap.examine",
									logParamList), this.getText(Constants.SORT));
		            
					this.setResult("success", true);
				}else{
					this.setResult("msg", "找不到应用信息");
				}
			}else{
				this.setResult("msg", "参数错误");
			}
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE_NAME);
		}
		
		this.write(JSONObject.fromObject(this.getResult()).toString());
		return null;
	}
	
	/**
	 * 应用上架状态时可以进行某些修改
	 * @return
	 */
	public String modifyAppOnline(){
		try {
			// 验证参数
			String appId = appInfo.getId();
			logger.info("DpAppInfoAction.modifyAppOnline start..., appId={}", appId);
			if(StringUtils.isBlank(appId)){
				throw new IllegalArgumentException("传入的参数不正确");
			}
			
			DpAppInfo appObj = dpAppInfoService.findAppInfo(appId);
			appObj.setHandDownCount(appInfo.getHandDownCount());
			appObj.setHandScoreCount(appInfo.getHandScoreCount());
			appObj.setHandAvgScore(appInfo.getHandAvgScore());
			appObj.setUpdateTime(new Date());
			dpAppInfoService.updateAppInfo(appObj);
			
			String planId = request.getParameter("planId");
			String relateType = request.getParameter("relateType");
			String[] relateAppPkgNames = request.getParameterValues("relateAppFilePackage");
			if(StringUtils.isNotBlank(planId) && StringUtils.isNotBlank(relateType)){
				if(Constants.APP_RELATE_TYPE_HAND.equals(relateType)){
					handAppRelateService.save(planId, appObj.getAppFilePackage(), relateAppPkgNames);
				}else{
					handAppRelateService.deleteByAppPkgName(planId, appObj.getAppFilePackage());
				}
			}
			
			write("success");
			logger.info("DpAppInfoAction.modifyAppOnline success, appId={}", appId);
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败！";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write(exception_msg);
		}
		return NONE;
	}
	
	/**
	 * 查询方案下该应用的推荐关联信息
	 * @return
	 */
	public String getAppRelateByPlanIdAndPkgName(){
		String planId = request.getParameter("planId");
		String appPackage = request.getParameter("appFilePackage");
		String relateType = request.getParameter("relateType");
		
		List<DpAppInfo>  dpApplist = null;
		try {
			if(StringUtils.isBlank(relateType)){
				//没有选择推荐类型,首先查询是否有手工推荐
				dpApplist = handAppRelateService.getAppRelateAppListByHand(planId, appPackage);
				if(dpApplist.size() > 0){
					relateType = Constants.APP_RELATE_TYPE_HAND;
				}else{
					relateType = Constants.APP_RELATE_TYPE_SYSTEM;
					//获取系统自动推荐的应用列表
					dpApplist = handAppRelateService.getAppRelateAppListBySystem(planId, appPackage);
				}
			}if(StringUtils.equals(Constants.APP_RELATE_TYPE_HAND, relateType)){
				dpApplist = handAppRelateService.getAppRelateAppListByHand(planId, appPackage);
				if(dpApplist.size() == 0){
					dpApplist = handAppRelateService.getAppRelateAppListBySystem(planId, appPackage);
				}
			}else {
				dpApplist = handAppRelateService.getAppRelateAppListBySystem(planId, appPackage);
			}
			
			this.setResult("success", true);
		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			this.setResult("success", false);
		}

		this.setResult("planId", planId);
		this.setResult("relateType", relateType);
		this.setResult("dpApplist", dpApplist);
		this.write(JSON.toJSONString(this.getResult()));
		return NONE;
	}
	
	public String getAppListForReplace(){
        Map<String, Object> specialParams = new HashMap<String, Object>();
        specialParams.put("planId", planId);
        specialParams.put("sourceAppPkgName", sourceAppPkgName);
        specialParams.put("appName", appName);
        specialParams.put("typeId", typeId);
        
        try {
        	//获取首页的版块列表
        	page = setUpPage(page, limit, start);
        	handAppRelateService.getAppListForReplace(page, specialParams);
        	
        	//分类列表
            dpTypeList = dpTypeService.getGameAndAppTypeList();
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败";
			}
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			return ERROR;
		}

		// 返回
		return "relateAppListReplace";
	}
	
	
	
	
	
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public AttachmentFileService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentFileService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public List<DpAppInfo> getList() {
		return list;
	}

	public void setList(List<DpAppInfo> list) {
		this.list = list;
	}

	public OpLoggerService getOpLoggerService() {
		return opLoggerService;
	}

	public void setOpLoggerService(OpLoggerService opLoggerService) {
		this.opLoggerService = opLoggerService;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getAuditOption() {
		return auditOption;
	}

	public void setAuditOption(String auditOption) {
		this.auditOption = auditOption;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public DpAuditRecordService getDpAuditRecordService() {
		return dpAuditRecordService;
	}

	public void setDpAuditRecordService(
			DpAuditRecordService dpAuditRecordService) {
		this.dpAuditRecordService = dpAuditRecordService;
	}

	public List<DpAuditRecord> getAuditRecordList() {
		return auditRecordList;
	}

	public void setAuditRecordList(List<DpAuditRecord> auditRecordList) {
		this.auditRecordList = auditRecordList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DpAuditRecord getAuditRecord() {
		return auditRecord;
	}

	public void setAuditRecord(DpAuditRecord auditRecord) {
		this.auditRecord = auditRecord;
	}

	public Page<DpAppInfo> getPage() {
		return page;
	}

	public void setPage(Page<DpAppInfo> page) {
		this.page = page;
	}

	public AppRecommendService getAppRecommendService() {
		return appRecommendService;
	}

	public void setAppRecommendService(AppRecommendService appRecommendService) {
		this.appRecommendService = appRecommendService;
	}

	public DpAppInfoService getDpAppInfoService() {
		return dpAppInfoService;
	}

	public void setDpAppInfoService(DpAppInfoService dpAppInfoService) {
		this.dpAppInfoService = dpAppInfoService;
	}

	public DpStaff getDpStaff() {
		return dpStaff;
	}

	public void setDpStaff(DpStaff dpStaff) {
		this.dpStaff = dpStaff;
	}

	public DpAppInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(DpAppInfo appInfo) {
		this.appInfo = appInfo;
	}

	public List<AttachmentFile> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<AttachmentFile> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getOnLineFlag() {
		return onLineFlag;
	}

	public void setOnLineFlag(String onLineFlag) {
		this.onLineFlag = onLineFlag;
	}

	public DpAppInfo getQueryAppInfo() {
		return queryAppInfo;
	}

	public void setQueryAppInfo(DpAppInfo queryAppInfo) {
		this.queryAppInfo = queryAppInfo;
	}

	public List<DpAppInfo> getQueryList() {
		return queryList;
	}

	public void setQueryList(List<DpAppInfo> queryList) {
		this.queryList = queryList;
	}

	public AttachmentFile getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(AttachmentFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	/**
	 * 获得DpStaff查询.
	 * 
	 * @return DpStaff
	 */
	public DpStaff getQueryDpStaff() {
		return queryDpStaff;
	}

	/**
	 * 设置DpStaff查询.
	 * 
	 * @param queryDpStaff
	 */
	public void setQueryDpStaff(DpStaff queryDpStaff) {
		this.queryDpStaff = queryDpStaff;
	}

	/**
	 * 设置未审核的查询的标志.
	 * 
	 * @param flags
	 *            int类型
	 */
	public void setFlags(int flags) {
		this.flags = flags;
	}

	/**
	 * 获得未审核的查询的标志.
	 * 
	 * @return int
	 */
	public int getFlags() {
		return flags;
	}

	/**
	 * 获得页面上传进来的页面跳转标识：audit：跳到审核页面；detail:跳到详情界面.
	 * 
	 * @return 跳转页面配置字符串
	 */
	public String getForward() {
		return forward;
	}

	/**
	 * 设置页面上传进来的页面跳转标识：audit：跳到审核页面；detail:跳到详情界面.
	 * 
	 * @param forward
	 */
	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getNoBackBtn() {
		return noBackBtn;
	}

	public void setNoBackBtn(String noBackBtn) {
		this.noBackBtn = noBackBtn;
	}

	public AppCommentService getAppCommentService() {
		return appCommentService;
	}

	public void setAppCommentService(AppCommentService appCommentService) {
		this.appCommentService = appCommentService;
	}

	public AppScoreSummary getAppScoreSummary() {
		return appScoreSummary;
	}

	public void setAppScoreSummary(AppScoreSummary appScoreSummary) {
		this.appScoreSummary = appScoreSummary;
	}

	public List<DpType> getDpTypeList() {
		return dpTypeList;
	}

	public void setDpTypeList(List<DpType> dpTypeList) {
		this.dpTypeList = dpTypeList;
	}

	public List<DpType> getLanguageDpTypeList() {
		return languageDpTypeList;
	}

	public void setLanguageDpTypeList(List<DpType> languageDpTypeList) {
		this.languageDpTypeList = languageDpTypeList;
	}

	public List<DpType> getAndroidDpTypeList() {
		return androidDpTypeList;
	}

	public void setAndroidDpTypeList(List<DpType> androidDpTypeList) {
		this.androidDpTypeList = androidDpTypeList;
	}

	public DpTypeService getDpTypeService() {
		return dpTypeService;
	}

	public void setDpTypeService(DpTypeService dpTypeService) {
		this.dpTypeService = dpTypeService;
	}

	public DpStaffService getDpStaffService() {
		return dpStaffService;
	}

	public void setDpStaffService(DpStaffService dpStaffService) {
		this.dpStaffService = dpStaffService;
	}

	public DpType getDpType() {
		return dpType;
	}

	public void setDpType(DpType dpType) {
		this.dpType = dpType;
	}

	public String getOperateOption() {
		return operateOption;
	}

	public void setOperateOption(String operateOption) {
		this.operateOption = operateOption;
	}

	public File getApk() {
		return apk;
	}

	public void setApk(File apk) {
		this.apk = apk;
	}

	public String getApkFileName() {
		return apkFileName;
	}

	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}

	public String getApkContentType() {
		return apkContentType;
	}

	public void setApkContentType(String apkContentType) {
		this.apkContentType = apkContentType;
	}

	public File[] getLogos() {
		return logos;
	}

	public void setLogos(File[] logos) {
		this.logos = logos;
	}

	public String[] getLogosFileName() {
		return logosFileName;
	}

	public void setLogosFileName(String[] logosFileName) {
		this.logosFileName = logosFileName;
	}

	public String[] getLogosContentType() {
		return logosContentType;
	}

	public void setLogosContentType(String[] logosContentType) {
		this.logosContentType = logosContentType;
	}

	public File[] getImgs() {
		return imgs;
	}

	public void setImgs(File[] imgs) {
		this.imgs = imgs;
	}

	public String[] getImgsFileName() {
		return imgsFileName;
	}

	public void setImgsFileName(String[] imgsFileName) {
		this.imgsFileName = imgsFileName;
	}

	public String[] getImgsContentType() {
		return imgsContentType;
	}

	public void setImgsContentType(String[] imgsContentType) {
		this.imgsContentType = imgsContentType;
	}

	public File[] getPosters() {
		return posters;
	}

	public void setPosters(File[] posters) {
		this.posters = posters;
	}

	public String[] getPostersFileName() {
		return postersFileName;
	}

	public void setPostersFileName(String[] postersFileName) {
		this.postersFileName = postersFileName;
	}

	public String[] getPostersContentType() {
		return postersContentType;
	}

	public void setPostersContentType(String[] postersContentType) {
		this.postersContentType = postersContentType;
	}

	public String getIsModify() {
		return isModify;
	}

	public void setIsModify(String isModify) {
		this.isModify = isModify;
	}

	public AppSubjectType getAppSubjectType() {
		return appSubjectType;
	}

	public void setAppSubjectType(AppSubjectType appSubjectType) {
		this.appSubjectType = appSubjectType;
	}

	public String getApkFileSaveName() {
		return apkFileSaveName;
	}

	public void setApkFileSaveName(String apkFileSaveName) {
		this.apkFileSaveName = apkFileSaveName;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public String getLogoFileSaveName() {
		return logoFileSaveName;
	}

	public void setLogoFileSaveName(String logoFileSaveName) {
		this.logoFileSaveName = logoFileSaveName;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgFileSaveName() {
		return imgFileSaveName;
	}

	public void setImgFileSaveName(String imgFileSaveName) {
		this.imgFileSaveName = imgFileSaveName;
	}

	public String getPosterFileName() {
		return posterFileName;
	}

	public void setPosterFileName(String posterFileName) {
		this.posterFileName = posterFileName;
	}

	public String getPosterFileSaveName() {
		return posterFileSaveName;
	}

	public void setPosterFileSaveName(String posterFileSaveName) {
		this.posterFileSaveName = posterFileSaveName;
	}

	public String[] getLogosFileSaveName() {
		return logosFileSaveName;
	}

	public void setLogosFileSaveName(String[] logosFileSaveName) {
		this.logosFileSaveName = logosFileSaveName;
	}

	public String[] getImgsFileSaveName() {
		return imgsFileSaveName;
	}

	public void setImgsFileSaveName(String[] imgsFileSaveName) {
		this.imgsFileSaveName = imgsFileSaveName;
	}

	public String[] getPostersFileSaveName() {
		return postersFileSaveName;
	}

	public void setPostersFileSaveName(String[] postersFileSaveName) {
		this.postersFileSaveName = postersFileSaveName;
	}

	public List<Map<String, String>> getPreLogo() {
		return preLogo;
	}

	public void setPreLogo(List<Map<String, String>> preLogo) {
		this.preLogo = preLogo;
	}

	public List<Map<String, String>> getPreImg() {
		return preImg;
	}

	public void setPreImg(List<Map<String, String>> preImg) {
		this.preImg = preImg;
	}

	public List<Map<String, String>> getPrePoster() {
		return prePoster;
	}

	public void setPrePoster(List<Map<String, String>> prePoster) {
		this.prePoster = prePoster;
	}

	public List<DpType> getOpModeDpTypeList() {
		return opModeDpTypeList;
	}

	public void setOpModeDpTypeList(List<DpType> opModeDpTypeList) {
		this.opModeDpTypeList = opModeDpTypeList;
	}

	public String getAppTags() {
		return appTags;
	}

	public void setAppTags(String appTags) {
		this.appTags = appTags;
	}


	public File[] getGameLogos() {
		return gameLogos;
	}


	public void setGameLogos(File[] gameLogos) {
		this.gameLogos = gameLogos;
	}


	public String getGameLogoFileName() {
		return gameLogoFileName;
	}


	public void setGameLogoFileName(String gameLogoFileName) {
		this.gameLogoFileName = gameLogoFileName;
	}


	public String getGameLogoFileSaveName() {
		return gameLogoFileSaveName;
	}


	public void setGameLogoFileSaveName(String gameLogoFileSaveName) {
		this.gameLogoFileSaveName = gameLogoFileSaveName;
	}


	public String[] getGameLogosFileName() {
		return gameLogosFileName;
	}


	public void setGameLogosFileName(String[] gameLogosFileName) {
		this.gameLogosFileName = gameLogosFileName;
	}


	public String[] getGameLogosFileSaveName() {
		return gameLogosFileSaveName;
	}


	public void setGameLogosFileSaveName(String[] gameLogosFileSaveName) {
		this.gameLogosFileSaveName = gameLogosFileSaveName;
	}


	public String[] getGameLogosContentType() {
		return gameLogosContentType;
	}


	public void setGameLogosContentType(String[] gameLogosContentType) {
		this.gameLogosContentType = gameLogosContentType;
	}


	public List<Map<String, String>> getPreGameLogo() {
		return preGameLogo;
	}


	public void setPreGameLogo(List<Map<String, String>> preGameLogo) {
		this.preGameLogo = preGameLogo;
	}


	public String getPlanId() {
		return planId;
	}


	public void setPlanId(String planId) {
		this.planId = planId;
	}


	public String getSourceAppPkgName() {
		return sourceAppPkgName;
	}


	public void setSourceAppPkgName(String sourceAppPkgName) {
		this.sourceAppPkgName = sourceAppPkgName;
	}


	public String getAppName() {
		return appName;
	}


	public void setAppName(String appName) {
		this.appName = appName;
	}


	public String getTypeId() {
		return typeId;
	}


	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
    
}
