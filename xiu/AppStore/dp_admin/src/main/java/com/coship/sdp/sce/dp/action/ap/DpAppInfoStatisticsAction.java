/*
 * 文件名称：DpAppInfoStatisticsAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-20
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.action.ap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.coship.sdp.sce.dp.ap.entity.AppStatisticsInfo;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.ap.service.DpAppInfoService;
import com.coship.sdp.sce.dp.comment.entity.AppCommentInfo;
import com.coship.sdp.sce.dp.comment.entity.AppScoreSummary;
import com.coship.sdp.sce.dp.comment.service.AppCommentService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 应用统计分析acion,处理统计列表请求，分页查询列表请求，应用统计详情请求
 * @author JiangJinfeng/906974
 * @version [版本号, 2012-9-20]
 * @since [产品/模块版本]
 */
public class DpAppInfoStatisticsAction extends BaseAction
{

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -1733830303917383033L;

    /**
     * 类名称.
     */
    private static final String MODULE = DpAppInfoStatisticsAction.class
            .getName();

    /**
     * 降序常量标识
     */
    public static final String DESC_ORDER = "desc";

    /**
     * 升序常量标识
     */
    public static final String ASC_ORDER = "asc";

    /**
     * 默认排序属性
     */
    public static final String DEFAULT_ORDER_PROPERTY = "updateTime";

    /**
     * 根据应用id查询评论列表.
     */
    public static final String GET_APP_COMMENT_HQL = "from AppCommentInfo aci where  aci.dpAppInfo=? order by  aci.createDate";

    /**
     * 应用统计查询数据封装对象
     */
    private AppStatisticsInfo appStatQuery;

    /**
     * 应用统计信息列表
     */
    private AppStatisticsInfo appStatisticsInfo;

    /**
     * 应用基本信息
     */
    private DpAppInfo dpAppInfo;

    /**
     * 评论统计类
     */
    private AppScoreSummary appScoreSummary;

    /**
     * 分页应用统计数据
     */
    private Page<AppStatisticsInfo> page;

    /**
     * 分页应用的评论数据
     */
    private Page<AppCommentInfo> commnetPage;

	/**
	 * 应用类型列表.
	 */
	private List<DpType> dpTypeList;

    /**
     * 应用基本信息服务类
     */
    @Autowired
    private DpAppInfoService dpAppInfoService;

    /**
     * 评论服务类
     */
    @Autowired
    private AppCommentService appCommentService;

    /**
     * 类别服务接口
     */
    @Autowired
    private DpTypeService dpTypeService;

    /**
     * 分页查询应用统计列表数据
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String searchAppStatistic()
    {
        try
        {
            // 查询数据对象为空
            if (appStatQuery == null)
            {
                appStatQuery = new AppStatisticsInfo();
                // 设置默认的排序
                appStatQuery.setOrderType(DESC_ORDER);
                appStatQuery.setOrderProperty(DEFAULT_ORDER_PROPERTY);
            }

            if (page == null)
            {
                page = new Page<AppStatisticsInfo>();
                page.setPageSize(limit);
                page.setCurrentPage(start);
            }
            dpAppInfoService.searchAppStatisticList(page, appStatQuery);
            List<AppStatisticsInfo> tempList = page.getResultList();
            for (AppStatisticsInfo tempObj : tempList)
            {
                DpType dpTypeTemp = dpTypeService
                        .findType(tempObj.getAppType());
                tempObj.setAppType(dpTypeTemp.getTypeName());
            }

            // 获取应用分类列表
            searchTypeList();
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = "应用统计失败";
            return ERROR;
        }
        return "doSearchList";
    }

    /**
     * 查看应用统计详情
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String viewDetailAppStatic()
    {
        try
        {
            appStatisticsInfo = dpAppInfoService
                    .searchAppStatisticInfoByAppId(appStatQuery.getAppId());

            dpAppInfo = dpAppInfoService.findAppInfo(appStatQuery.getAppId());

            commnetPage = new Page<AppCommentInfo>();

            if (page == null)
            {
                commnetPage.setPageSize(limit);
                commnetPage.setCurrentPage(start);
            }
            else
            {
                commnetPage.setPageSize(page.getPageSize());
                commnetPage.setCurrentPage(page.getCurrentPage());
            }
            appScoreSummary = appCommentService.getAppScoreSummary(dpAppInfo);
            appCommentService.listAppCommentInfo(commnetPage,
                    GET_APP_COMMENT_HQL, new Object[]
                    { dpAppInfo });
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage());
        }
        return "gotoDetail";
    }

	/**
	 * 查询所有的应用分类列表
	 */
	private void searchTypeList() throws Exception
	{
		dpTypeList = dpTypeService.getGameAndAppTypeList();
	}

    public AppStatisticsInfo getAppStatQuery()
    {
        return appStatQuery;
    }

    public void setAppStatQuery(AppStatisticsInfo appStatQuery)
    {
        this.appStatQuery = appStatQuery;
    }

    public AppStatisticsInfo getAppStatisticsInfo()
    {
        return appStatisticsInfo;
    }

    public void setAppStatisticsInfo(AppStatisticsInfo appStatisticsInfo)
    {
        this.appStatisticsInfo = appStatisticsInfo;
    }

    public Page<AppStatisticsInfo> getPage()
    {
        return page;
    }

    public void setPage(Page<AppStatisticsInfo> page)
    {
        this.page = page;
    }

    public Page<AppCommentInfo> getCommnetPage()
    {
        return commnetPage;
    }

    public void setCommnetPage(Page<AppCommentInfo> commnetPage)
    {
        this.commnetPage = commnetPage;
    }

    public static String getDescOrder()
    {
        return DESC_ORDER;
    }

    public static String getAscOrder()
    {
        return ASC_ORDER;
    }

    public DpAppInfo getDpAppInfo()
    {
        return dpAppInfo;
    }

    public void setDpAppInfo(DpAppInfo dpAppInfo)
    {
        this.dpAppInfo = dpAppInfo;
    }

    public AppScoreSummary getAppScoreSummary()
    {
        return appScoreSummary;
    }

    public void setAppScoreSummary(AppScoreSummary appScoreSummary)
    {
        this.appScoreSummary = appScoreSummary;
    }

	public List<DpType> getDpTypeList() {
		return dpTypeList;
	}

	public void setDpTypeList(List<DpType> dpTypeList) {
		this.dpTypeList = dpTypeList;
	}



}
