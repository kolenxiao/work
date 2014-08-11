/*
 * 文件名称：DpNewsAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Sep 2, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.action.news;

import java.util.Date;
import java.util.List;

import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.news.entity.DpNews;
import com.coship.sdp.sce.dp.news.service.DpNewsService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * <资讯action类>
 *
 * @author Huangliufei/905735
 * @version [版本号, Sep 2, 2011]
 * @since [产品/模块版本]
 */
public class DpNewsAction extends BaseAction
{

    private static final long serialVersionUID = 1L;

    private static final String MODULE = DpNewsAction.class.getName();

    // 资讯类型接口
    private DpTypeService dpTypeService;

    // 资讯接口
    private DpNewsService dpNewsService;

    // 资讯对象
    private DpNews dpNews;

    // 专门为查询设立资讯对象
    private DpNews queryDpNews;

    // 资讯类型对象
    private DpType dpType;

    // 专门为查询设立资讯类型对象。
    private DpType querydpType;

    // 资讯列表
    private List<DpNews> dpNewsList;

    // 资讯类型列表
    private List<DpType> dpTypeList;

    // 资讯id数组(删除操作时使用)
    private String id;

    // 操作日志服务接口
    private OpLoggerService opLoggerService;

    // 分页对象
    private Page<DpNews> page;

    // 用户对象
    private User user;

    /**
     *
     * 获取资讯列表
     *
     */
    public String doList()
    {

        if (this.queryDpNews == null)
        {
            this.queryDpNews = new DpNews();
        }
        this.page = new Page<DpNews>();
        page.setPageSize(this.limit);
        page.setCurrentPage(this.start);
        this.queryDpNews.setDpType(querydpType);
        try
        {
            // 获取资讯类型列表
            this.dpTypeList = this.dpTypeService
                    .findByParentTypeCode(DefaultTypeCodeConstants.NEWS_TYPE_CODE);
            this.page = this.dpNewsService.listDpNews(page, queryDpNews);
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            // 查询资讯列表出错
            exception_msg = this.getText(Constants.NEWS_QUERY_ERROR_MSG);
            return ERROR;
        }

        return "dpNewsList";
    }

    /**
     *
     * 添加资讯
     */
    public String doAdd()
    {
        User user = (User) request.getSession().getAttribute("user");
        dpNews.setCreateUser(user.getUserName());
        dpNews.setNewsCreateTime(new Date());
        dpNews.setUpdateTime(new Date());
        dpNews.setNewsStstus(Constants.NEWS_DRAFT_STATUS);
        // dpNews.setDpType(dpType);
        try
        {
            // 设置资讯类型
            dpNews.setDpType(this.dpTypeService.findType(dpType.getId()));
            if (!this.dpNewsService.isUniqueneByPropertyName("newsTitle",
                    this.dpNews.getNewsTitle()))
            {
                exception_msg = this
                        .getText("sdp.sce.dp.admin.news.news.title.exist");
                return ERROR;
            }
            else
            {
                this.dpNewsService.saveDpNews(dpNews);
                // 日志参数
                List<String> logParamList = initLogParame(user.getUserName(),
                        "sdp.sce.dp.admin.news.info",
                        "sdp.sce.dp.admin.log.add.operate",
                        this.dpNews.getNewsTitle());
                opLoggerService.info(this.getText(Constants.NEWS_MANAGEMENT),
                        this.getText("sdp.sce.dp.admin.log.news.operate.log",
                                logParamList), this.getText(Constants.ADD));
            }
        }
        catch (Exception e)
        {
            Debug.logError(e, "doAdd()" + e.getMessage(), MODULE);
            // 新增资讯失败
            exception_msg = this
                    .getText("sdp.sce.dp.admin.news.add.news.error");
            return ERROR;
        }
        return doList();

    }

    /**
     *
     * 跳转到添加和修改页面
     */
    public String doDisplay()
    {
        String path = "toAdd";
        try
        {
            // 获取资讯类型列表
            this.dpTypeList = this.dpTypeService
                    .findByParentTypeCode(DefaultTypeCodeConstants.NEWS_TYPE_CODE);
            if (this.dpNews != null)
            {
                this.dpNews = this.dpNewsService
                        .findDpNews(this.dpNews.getId());
            }

        }
        catch (Exception e)
        {
            Debug.logError(e, "doDisplay()" + e.getMessage(), MODULE);
            exception_msg = this.getText("sdp.sce.dp.admin.common.jump.error");
            return ERROR;
        }
        return path;
    }

    /**
     * 跳转到资讯详情界面
     */
    public String doDetail()
    {
        try
        {
            this.dpNews = this.dpNewsService.findDpNews(this.dpNews.getId());
        }
        catch (Exception e)
        {
            Debug.logError(e, "doDetail()" + e.getMessage(), MODULE);
            exception_msg = this.getText("sdp.sce.dp.admin.common.jump.error");
            return ERROR;
        }
        return "newsDetail";

    }

    /**
     * 资讯上线
     */
    /**public String doOnLine()
    {
        this.user = (User) request.getSession().getAttribute("user");
        DpNews dpNews = this.dpNewsService.findDpNews(this.dpNews.getId());
        // 修改资讯状态为上线状态。
        dpNews.setNewsStstus(Constants.NEWS_ONLINE_STATUS);
        try
        {
            this.dpNewsService.updateDpNews(dpNews);
            // 日志参数
            List<String> logParamList = initLogParame(user.getUserName(),
                    "sdp.sce.dp.admin.news.info",
                    "sdp.sce.dp.common.log.online.operate",
                    this.dpNews.getNewsTitle());

            opLoggerService.info(this.getText(Constants.NEWS_MANAGEMENT), this
                    .getText("sdp.sce.dp.admin.log.news.operate.log",
                            logParamList), this
                    .getText("sdp.sce.dp.common.log.online.operate"));

        }
        catch (Exception e)
        {
            Debug.logError(e, "doOnLine()" + e.getMessage(), MODULE);
            // 资讯上线失败
            exception_msg = this.getText("sdp.sce.dp.admin.news.online.error");
            return ERROR;
        }
        return doList();
    }*/

    /**
     * 修改资讯
     */
    public String doEdit()
    {
        user = (User) request.getSession().getAttribute("user");
        try
        {
            // 设置资讯类型
            dpNews.setDpType(dpTypeService.findType(dpNews.getDpType()
                    .getId()));
            // 设置更新时间
            dpNews.setUpdateTime(new Date());
            if (!request.getParameter("oldNewsTitle").equals(
                    dpNews.getNewsTitle())
                    && !dpNewsService.isUniqueneByPropertyName(
                            "newsTitle", dpNews.getNewsTitle()))
            {
                exception_msg = getText("sdp.sce.dp.admin.news.news.title.exist");
                return ERROR;
            }
            else
            {
                dpNewsService.updateDpNews(dpNews);
                // 日志参数
                List<String> logParamList = initLogParame(user.getUserName(),
                        "sdp.sce.dp.admin.news.info",
                        "sdp.sce.dp.admin.log.update.operate",
                        dpNews.getNewsTitle());
                opLoggerService.info(getText(Constants.NEWS_MANAGEMENT),
                                getText(
                                        "sdp.sce.dp.admin.log.news.operate.log",
                                        logParamList),
                                getText("sdp.sce.dp.admin.log.update.operate"));
            }
        }
        catch (Exception e)
        {
            Debug.logError(e, "doEdit()" + e.getMessage(), MODULE);
            // 修改资讯失败
            exception_msg = this
                    .getText("sdp.sce.dp.admin.news.update.news.error");
            return ERROR;
        }

        return doList();
    }

    /**
     *
     * 删除资讯
     */
    public String doDelete()
    {

        try
        {
            this.user = (User) this.request.getSession().getAttribute(
                    Constants.LOGIN_SESSION_USER);
            if (null != id && !"".equals(id))
            {
                String[] idArray = this.id.split(",");
                this.dpNewsService.deleteDpNewsList(idArray);
                // 获取资讯类型列表
                this.dpTypeList = this.dpTypeService
                        .findByParentTypeCode(DefaultTypeCodeConstants.NEWS_TYPE_CODE);
                // 日志参数
                List<String> logParamList = initLogParame(user.getUserName(),
                        "sdp.sce.dp.admin.news.info",
                        "sdp.sce.dp.admin.log.delete.operate", id);
                opLoggerService
                        .info(this.getText(Constants.NEWS_MANAGEMENT), this
                                .getText(
                                        "sdp.sce.dp.admin.log.news.delete.log",
                                        logParamList), this
                                .getText("sdp.sce.dp.admin.log.delete.operate"));

            }
            else
            {
                Debug.logWarning("The id is null", MODULE);
                return ERROR;
            }
        }
        catch (Exception e)
        {
            Debug.logError(e, "doDelete()" + e.getMessage(), MODULE);
            // 删除资讯失败
            exception_msg = this
                    .getText("sdp.sce.dp.admin.news.delete.news.error");
            return ERROR;
        }
        return doList();
    }

    public DpTypeService getDpTypeService()
    {
        return dpTypeService;
    }

    public void setDpTypeService(DpTypeService dpTypeService)
    {
        this.dpTypeService = dpTypeService;
    }

    public DpNewsService getDpNewsService()
    {
        return dpNewsService;
    }

    public void setDpNewsService(DpNewsService dpNewsService)
    {
        this.dpNewsService = dpNewsService;
    }

    public DpNews getDpNews()
    {
        return dpNews;
    }

    public void setDpNews(DpNews dpNews)
    {
        this.dpNews = dpNews;
    }

    public DpNews getQueryDpNews()
    {
        return queryDpNews;
    }

    public void setQueryDpNews(DpNews queryDpNews)
    {
        this.queryDpNews = queryDpNews;
    }

    public DpType getDpType()
    {
        return dpType;
    }

    public void setDpType(DpType dpType)
    {
        this.dpType = dpType;
    }

    public List<DpNews> getDpNewsList()
    {
        return dpNewsList;
    }

    public void setDpNewsList(List<DpNews> dpNewsList)
    {
        this.dpNewsList = dpNewsList;
    }

    public List<DpType> getDpTypeList()
    {
        return dpTypeList;
    }

    public void setDpTypeList(List<DpType> dpTypeList)
    {
        this.dpTypeList = dpTypeList;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public OpLoggerService getOpLoggerService()
    {
        return opLoggerService;
    }

    public void setOpLoggerService(OpLoggerService opLoggerService)
    {
        this.opLoggerService = opLoggerService;
    }

    public Page<DpNews> getPage()
    {
        return page;
    }

    public void setPage(Page<DpNews> page)
    {
        this.page = page;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public DpType getQuerydpType()
    {
        return querydpType;
    }

    public void setQuerydpType(DpType querydpType)
    {
        this.querydpType = querydpType;
    }

}
