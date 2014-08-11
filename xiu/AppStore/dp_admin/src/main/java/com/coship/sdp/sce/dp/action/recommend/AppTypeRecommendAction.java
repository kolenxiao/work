package com.coship.sdp.sce.dp.action.recommend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.permission.service.UserService;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.ap.entity.DpStaff;
import com.coship.sdp.sce.dp.ap.service.DpAppInfoService;
import com.coship.sdp.sce.dp.ap.service.DpStaffService;
import com.coship.sdp.sce.dp.common.AppStatusConstants;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.common.QueryAppInfoThread;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommend;
import com.coship.sdp.sce.dp.recommend.entity.AppTypeRecommend;
import com.coship.sdp.sce.dp.recommend.service.AppTypeRecommendService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/*
 * 处理应用分类推荐操作请求的action类
 * @author WangZhengHui/907632
 * @version [版本号, 2013-7-5]
 * @since [产品/模块版本]
 */
@Controller
public class AppTypeRecommendAction extends BaseAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5290853994452649296L;
	
    /**
     * 推荐操作标识
     */
    public static final String RECOMEND_FLAG = "1";

    /**
     * 取消推荐标识
     */
    public static final String DERECOMEND_FLAG = "0";

    /**
     * 多个应用推荐
     */
    public static final String MULTIP_RECOMEND = "1";
    
    /**
     * 模块的名称.
     */
    private static final String MODULE = AppRecommentAction.class.getName();
    
    /**
     * id集合.
     */
    private String ids;
    /**
     * 分页.
     */
    private Page<AppTypeRecommend> page;
    
    /**
     * 专用于查询.
     */
    private DpAppInfo queryAppInfo;
    
    /**
     * 应用分类
     */
    private DpType dpTypeQueryInfo;
    
	/**
	 * 应用类型接口.
	 */
	@Autowired
	private DpTypeService dpTypeService;
	
    @Autowired
    private AppTypeRecommendService appTypeRecommendService;
    
    /**
     * dpStaff服务对象.
     */
    @Autowired
    private DpStaffService dpStaffService;
    
    /**
     * 管理员用户服务对象
     */
    @Autowired
    private UserService userService;
    
    /**
     * 应用服务层对象.
     */
    @Autowired
    private DpAppInfoService dpAppInfoService;
    
    /**
     * 操作日志对象.
     */
    @Autowired
    private OpLoggerService opLoggerService;
    
	/**
	 * 应用类型列表.
	 */
	private List<DpType> dpTypeList;
	
    /**
     * 应用对象.
     */
    private DpAppInfo appInfo;
	
    
    private int sort;
    
    private int index;
    
    private AppTypeRecommend appTypeRecommend;
    
	/**
     * 应用推荐列表
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doSearchAppTypeRecommendList()
    {
        Debug.logVerbose("AppTypeRecommendAction.doSearchRecommendAppList() start...");

        // 查询条件数据封装对象
        if (queryAppInfo == null)
        {
            queryAppInfo = new DpAppInfo();
        }

        // 已上架的应用
        queryAppInfo.setAppStatus(AppStatusConstants.APP_STATUS_ONLINE);
        
        this.page = setUpPage(page, limit, start);

        try
        {   
        	setDpTypeQueryInfo(dpTypeService.findType(queryAppInfo.getDpType().getId()));//设置分类信息
        	
        	appTypeRecommendService.searchAppTypeRecomendList(page, queryAppInfo);

            // 获取所有的应用分类列表
            searchTypeList();

            // 将DpAppInfo中存储的DpStaff设置值
            this.setStaffInPage();
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        Debug.logVerbose("AppTypeRecommendAction.doSearchRecommendAppList() end...");
        return "doSearchAppTypeRecommandList";
    }
    
    
    /**
     * 推荐 commendFlag=1：推荐；commendFlag=0：取消推荐.
     * @return
     */
    public String doCommend()
    {
        Debug.logVerbose("AppTypeRecommendAction.doCommend() start...");
        User userObj = (User) request.getSession().getAttribute("user");
        try
        {
            String operaterLog = "";

            List<AppTypeRecommend> list = appTypeRecommendService.getRecommendList();
            AppTypeRecommend appRec = null;
            if (CollectionUtils.isNotEmpty(list))
            {
                // 获取排行第一的信息
                appRec = list.get(0);

                // 如果为空，表示初始化的情况，重排序
                if (appRec.getSort() == null || appRec.getSort() == 0.0)
                {
                    for (int i = 0; i < list.size(); i++)
                    {
                    	AppTypeRecommend ar = list.get(i);
                        ar.setSort(i+1.0);
                        appTypeRecommendService.updateRecommendSort(ar);
                    }
                }
            }

            String commendFlag = request.getParameter("commendFlag");
            String operater = request.getParameter("operater");
            // 新增推荐操作
            if (StringUtils.equals(commendFlag, RECOMEND_FLAG))
            {
                // 多个应用推荐
                if (StringUtils.equals(operater, MULTIP_RECOMEND))
                {
                    if (StringUtils.isNotEmpty(ids))
                    {
                        String[] idArr = ids.trim().split(",");
                        List<AppTypeRecommend> tempList = new ArrayList<AppTypeRecommend>();
                        for (int k = 0; k < idArr.length; k++)
                        {
                            String tempId = idArr[k];
                            DpAppInfo tempAppInfo = dpAppInfoService
                                    .findAppInfo(tempId.trim());             
                            AppTypeRecommend appRecommend = new AppTypeRecommend();
                            appRecommend.setAppRecommendCTime(new Date());
                            appRecommend
                                    .setCreateUser(userObj.getCreatedUser());
                            appRecommend.setDpAppInfo(tempAppInfo);
                            appRecommend.setDpType(tempAppInfo.getDpType());
                            Double sort = 0.0;
                            if (appRec == null)
                            {
                                sort = Double.valueOf(k+1.0);
                            }
                            else
                            {
                                appRec = appTypeRecommendService.queryUniqueAppTypeRecommendById(appRec.getId());
                                sort = getRandomSort(appRec);
                            }

                            appRecommend.setSort(sort);
                            tempList.add(appRecommend);
                        }
                        
                        appTypeRecommendService.recommendAppTypeByIds(tempList);
                        setResult("success", "操作成功");
                        write(JSONObject.fromObject(getResult()).toString());

                        // 更新appstore_service存放在内存的应用信息
            			new QueryAppInfoThread().start();

                        return null;
                    }
                }
                else
                {
                    appInfo = dpAppInfoService.findAppInfo(appInfo.getId());
                    AppTypeRecommend appRecommend = new AppTypeRecommend();
                    appRecommend.setAppRecommendCTime(new Date());
                    appRecommend.setCreateUser(userObj.getCreatedUser());
                    appRecommend.setDpAppInfo(appInfo);
                    appTypeRecommendService.recommendAppType(appRecommend);
                }

                // 记录日志操作
                operaterLog = "sdp.sce.dp.admin.log.recommended.operate";
            }
            else
            {
            	// 取消推荐操作
            	appTypeRecommendService.cancleRecommendDpAppInfo(appInfo);
                operaterLog = "sdp.sce.dp.admin.log.cancel.recommended";
            }

            // 更新appstore_service存放在内存的应用信息
			new QueryAppInfoThread().start();

            List<String> logParamList = initLogParames(userObj,
                    "sdp.sce.dp.admin.ap.appinfo", operaterLog,
                    "sdp.sce.dp.admin.log.operate.result.success");
            if (appInfo != null)
            {
                logParamList.add(appInfo.getAppName());
            }
            else
            {
                logParamList.add(ids);
            }
            opLoggerService.info(getText("sdp.sce.dp.admin.ap.name"),
                    getText("sdp.sce.dp.admin.ap.commend", logParamList),
                    getText(operaterLog));
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.ap.commend.error");
            return ERROR;
        }
        Debug.logVerbose("AppTypeRecommendAction.doCommend() end...");
        return doSearchAppTypeRecommendList();
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
                appTypeRecommendService.cancleRecommendDpAppInfo(appInfo);

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
                Debug.logError(e, e.getMessage(), MODULE);
                exception_msg = this
                        .getText("sdp.sce.dp.admin.ap.commend.error");

                setResult("exception", exception_msg);

                write(JSONObject.fromObject(getResult()).toString());
                Debug.logError(e, e.getMessage(), MODULE);

                return null;
            }
        }

        // 更新appstore_service存放在内存的应用信息
		new QueryAppInfoThread().start();

        setResult("success", true);
        write(JSONObject.fromObject(getResult()).toString());
        Debug.logVerbose("AppTypeRecommendAction.doBatchCommend() end...");
        return null;
    }
    
    
    /**
     * 应用推荐自定义排序
     */
    public String doRecommendSort()
    {
        Debug.logVerbose("AppRecommendAction.doRecommendSort() start...");

        List<AppTypeRecommend> preAppRecList = null;
        try
        {
            // 找出原先第sort名次的应用信息
            preAppRecList = appTypeRecommendService.getRecommendListByType(queryAppInfo.getDpType());//修改于2013-07-01 ,只查询分类下的应用，并按升序排序
        }
        catch (Exception e)
        {
            e.printStackTrace();
            exception_msg = "系统异常";
            return ERROR;
        }

        if (sort > preAppRecList.size())
        {
            exception_msg = "填写的排行名次超过应用推荐的个数";
            return ERROR;
        }

        AppTypeRecommend far = preAppRecList.get(0);
        // 如果为空，重排序
        if (far.getSort() == null || far.getSort() == 0.0)
        {
            try
            {
                for (int i = 0; i < preAppRecList.size(); i++)
                {
                	AppTypeRecommend ar = preAppRecList.get(i);
                    ar.setSort(Double.valueOf(i+1.0));

                    appTypeRecommendService.updateTypeRecommendSort(ar);
                }
                preAppRecList = appTypeRecommendService.getRecommendList();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        try {
            // 排序前排名为sort的应用推荐记录
        	AppTypeRecommend sortApp = preAppRecList.get(sort-1);
            Double order = getRandom(sortApp, preAppRecList);

            // 要重排名的应用推荐信息
            AppTypeRecommend appRec = appTypeRecommendService.queryUniqueAppRecommendById(appTypeRecommend.getId());
            appRec.setSort(order);
            appTypeRecommendService.updateRecommendSort(appRec);
        } catch (Exception e) {
            // 重排序
            for (int i = 0; i < preAppRecList.size(); i++) {
            	AppTypeRecommend appR = preAppRecList.get(i);
                Double one = 1.0;
                appR.setSort(i + one);
                try
                {
                	appTypeRecommendService.updateRecommendSort(appR);
                }
                catch (Exception e1)
                {
                    e.printStackTrace();
                    exception_msg = "系统异常";
                    return ERROR;
                }
            }

        }

        Debug.logVerbose("AppRecommendAction.doRecommendSort() end...");

        return doSearchAppTypeRecommendList();
    }

    
	/**
	 * 查询所有的应用分类列表
	 */
	private void searchTypeList() throws Exception
	{
		dpTypeList = dpTypeService.getGameAndAppTypeList();
	}
	
    /**
     * 将翻页列表中查询出的用户id转换为对象存储.
     *
     * @return 无
     */
    private void setStaffInPage() throws Exception
    {
        if (page != null)
        {
            DpStaff staff = new DpStaff();
            User user = new User();
            for (AppTypeRecommend appTypeRecommend : page.getResultList())
            {
             // 设置staff对象或者user对象
                String staffId = appTypeRecommend.getDpAppInfo().getDpStaffId();
                if (staffId != null)
                {
                    staff = dpStaffService.findDpStaff(staffId);
                    appTypeRecommend.getDpAppInfo().setDpStaff(staff);
                }

                String userId = appTypeRecommend.getDpAppInfo().getUserId();
                if (userId != null)
                {
                    user = userService.getUser(userId);
                    appTypeRecommend.getDpAppInfo().setUser(user);
                }
            }
        }
    }
	
    /**
     * 应用分类推荐自定义排序
     * @param appRec :应用推荐信息
     * @return 应用排行随机序号
     */
    private Double getRandomSort(AppTypeRecommend appRec)
    {
        Double sort = Math.random()*appRec.getSort();
        if (sort == 0)
        {
            getRandomSort(appRec);
        }
        return sort;
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
    // TODO 可以提取到一个公用的类取
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
     * 随机获取某个范围的方法
     * @param sortApp 未排序之前第sort个应用推荐的信息
     * @param order 返回的order随机数
     * @param preAppList 应用推荐列表
     * @return 返回随机数
     */
    private double getRandom(AppTypeRecommend sortApp,
            List<AppTypeRecommend> preAppList) throws Exception
    {
        Double order = 0.0;
        if (sort == 1) {
            // 取0到app.getOrder()之间的随机数
            order = Math.random()*sortApp.getSort();
            if (order == 0.0) {
                // 使用递归
                getRandom(sortApp, preAppList);
            }
        } else if (sort == preAppList.size()) {
            // 排名最后一个
            order = Double.valueOf(sortApp.getSort() + 1.0);
        } else {
        	AppTypeRecommend apR = null;
            if (sort > index)
            {
                // 如果从高的名次排到低的名词，如：1->4
                apR = preAppList.get(sort);
            }
            else
            {
             // 如果从地的名次排到高的名词，如：4->1
                apR = preAppList.get(sort-2);
            }

            // 取原有第sort的上一个到第sort条之间的随机数（apR.getSort()~sortApp.getSort()之间的数）
            order = Double.valueOf(Math.random()*(sortApp.getSort()-apR.getSort()) + apR.getSort());
            if (order == apR.getSort()) {
                // 使用递归
                getRandom(sortApp, preAppList);
            }
        }
        return order;
    }

    
	public Page<AppTypeRecommend> getPage() {
		return page;
	}

	public void setPage(Page<AppTypeRecommend> page) {
		this.page = page;
	}

	public DpAppInfo getQueryAppInfo() {
		return queryAppInfo;
	}

	public void setQueryAppInfo(DpAppInfo queryAppInfo) {
		this.queryAppInfo = queryAppInfo;
	}
	
	public DpType getDpTypeQueryInfo() {
		return dpTypeQueryInfo;
	}

	public void setDpTypeQueryInfo(DpType dpTypeQueryInfo) {
		this.dpTypeQueryInfo = dpTypeQueryInfo;
	}

	public DpTypeService getDpTypeService() {
		return dpTypeService;
	}

	public void setDpTypeService(DpTypeService dpTypeService) {
		this.dpTypeService = dpTypeService;
	}

	public AppTypeRecommendService getAppTypeRecommendService() {
		return appTypeRecommendService;
	}
	
	public List<DpType> getDpTypeList() {
		return dpTypeList;
	}

	public void setDpTypeList(List<DpType> dpTypeList) {
		this.dpTypeList = dpTypeList;
	}

	public void setAppTypeRecommendService(
			AppTypeRecommendService appTypeRecommendService) {
		this.appTypeRecommendService = appTypeRecommendService;
	}

	public DpStaffService getDpStaffService() {
		return dpStaffService;
	}

	public void setDpStaffService(DpStaffService dpStaffService) {
		this.dpStaffService = dpStaffService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public DpAppInfoService getDpAppInfoService() {
		return dpAppInfoService;
	}

	public void setDpAppInfoService(DpAppInfoService dpAppInfoService) {
		this.dpAppInfoService = dpAppInfoService;
	}

	public DpAppInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(DpAppInfo appInfo) {
		this.appInfo = appInfo;
	}

	public OpLoggerService getOpLoggerService() {
		return opLoggerService;
	}

	public void setOpLoggerService(OpLoggerService opLoggerService) {
		this.opLoggerService = opLoggerService;
	}


	public int getSort() {
		return sort;
	}


	public void setSort(int sort) {
		this.sort = sort;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public AppTypeRecommend getAppTypeRecommend() {
		return appTypeRecommend;
	}


	public void setAppTypeRecommend(AppTypeRecommend appTypeRecommend) {
		this.appTypeRecommend = appTypeRecommend;
	}
	
	
	
	
}
