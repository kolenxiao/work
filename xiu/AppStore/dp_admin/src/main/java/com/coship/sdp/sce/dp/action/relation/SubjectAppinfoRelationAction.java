/*
 * 文件名称：AppSubjectAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.action.relation;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.coship.sdp.permission.entity.User;
import com.coship.sdp.permission.service.UserService;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.ap.entity.DpStaff;
import com.coship.sdp.sce.dp.ap.service.DpStaffService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.QueryAppInfoThread;
import com.coship.sdp.sce.dp.sign.service.SignService;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.subject.entity.SubjectAppinfoRelation;
import com.coship.sdp.sce.dp.subject.service.AppSubjectTypeService;
import com.coship.sdp.sce.dp.subject.service.SubjectAppinfoRelationService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 专题应用管理action
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
public class SubjectAppinfoRelationAction extends BaseAction
{
    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模块的名称.
     */
    private static final String MODULE_NAME = SubjectAppinfoRelationAction.class.getName();

    /**
     * 应用专题对象
     */
    private AppSubjectType appSubjectType;

    /**
     * 应用专题列表对象
     */
    private Page<SubjectAppinfoRelation> page;

    /**
     * 编辑操作标识
     */
    private String operate;

    /**
     * 专题排行名词
     */
    private int sort;

    /**
     * 当前专题应用的名次
     */
    private int index;

    /**
     * 关联关系对象
     */
    private SubjectAppinfoRelation relationQuery;

    /**
     * 应用专题服务类
     */
    @Autowired
    private AppSubjectTypeService appSubjectTypeService;

    /**
     * 应用专题关系服务类
     */
    @Autowired
    private SubjectAppinfoRelationService subjectAppinfoRelationService;

    /**
     * 管理员用户服务对象
     */
    @Autowired
    private UserService userService;

    /**
     * dpStaff服务对象.
     */
    @Autowired
    private DpStaffService dpStaffService;


    /**
     * 跳转到应用专题信息编辑页面(新增/修改)
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String toEditSubject()
    {
        Debug.logVerbose("SubjectAppinfoRelationAction.toEditSubject() start...");

        try
        {
            // 请求为修改专题
            if (appSubjectType != null
                    && StringUtils.isNotEmpty(appSubjectType.getId()))
            {
                // 获取修改的专题以及专题下的应用
                appSubjectType = appSubjectTypeService
                        .getAppSubjectTypeById(appSubjectType.getId());

                // 设置分页数据
                if (page == null)
                {
                    page = new Page<SubjectAppinfoRelation>();
                    page.setPageSize(limit);
                    page.setCurrentPage(start);
                }

                subjectAppinfoRelationService.queryAppInfoPageBySubjectType(page, appSubjectType);

                // 为user或dpStaff对象设值
                this.setStaffOrUser();

                Debug.logVerbose("SubjectAppinfoRelationAction.toEditSubject() end...");
            }
            return "toEdit";
        }
        catch (Exception e)
        {
            exception_msg = "跳转编辑页面出错。";
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            return ERROR;
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
            for (SubjectAppinfoRelation rela : page.getResultList())
            {
                DpAppInfo appInfo = rela.getAppInfo();
                if (appInfo != null)
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
    }


    /**
     * 应用专题自定义排序
     */
    public String doSubjectAppSort()
    {
        Debug.logVerbose("AppRecommendAction.doRecommendSort() start...");

        List<SubjectAppinfoRelation> preRelactionList = null;
        try
        {
            // 找出原先第sort名次的应用专题信息
            preRelactionList = subjectAppinfoRelationService.getSubjectAppinfoRelationsBySubType(appSubjectType);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            exception_msg = "系统异常";
            return ERROR;
        }

        // 重排序
        SubjectAppinfoRelation fir = preRelactionList.get(0);
        if (fir.getSort() == null || fir.getSort() == 0.0)
        {
            for (int i = 0; i < preRelactionList.size(); i++)
            {
                SubjectAppinfoRelation ur = preRelactionList.get(i);
                ur.setSort(i+1.0);
                try
                {
                    subjectAppinfoRelationService.updateSubjectAppinfoRelationSort(ur);
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // 找出更新第sort名次的应用专题信息
            preRelactionList = subjectAppinfoRelationService.getSubjectAppinfoRelationsBySubType(appSubjectType);
        }

        if (sort > preRelactionList.size())
        {
            exception_msg = "填写的排行名次超过应用专题的个数";
            return ERROR;
        }

        // 排名为sort的应用专题记录
        SubjectAppinfoRelation nextRelationApp = preRelactionList.get(sort-1);

        try {
            Double order = getRandom(nextRelationApp, preRelactionList);

            // 要重排名的应用专题信息
            SubjectAppinfoRelation appRela = subjectAppinfoRelationService
                .getAppSubjectApp(relationQuery.getId());
            appRela.setSort(order);
            subjectAppinfoRelationService.updateSubjectAppinfoRelationSort(appRela);
        } catch (Exception e) {
            // 重排序
            for (int i = 0; i < preRelactionList.size(); i++)
            {
                SubjectAppinfoRelation rel  = preRelactionList.get(i);
                Double one = 1.0;
                rel.setSort(i + one);
                try
                {
                    subjectAppinfoRelationService.updateSubjectAppinfoRelationSort(rel);
                }
                catch (Exception e1)
                {
                    e.printStackTrace();
                    exception_msg = "系统异常";
                    return ERROR;
                }
            }
        }

        operate = "sort";
        // 更新appstore_service存放在内存的应用信息
		new QueryAppInfoThread().start();
        return toEditSubject();
    }

    /**
     * 随机获取某个范围的方法
     * @param sortApp 未排序之前第sort个应用推荐的信息
     * @param order 返回的order随机数
     * @param preAppList 应用推荐列表
     * @return 返回随机数
     */
    private double getRandom(SubjectAppinfoRelation nextRelationApp,
            List<SubjectAppinfoRelation> preRelactionList) throws Exception
    {
        Double order = 0.0;
        if (sort == 1) {
            // 取0到app.getOrder()之间的随机数
            order = Math.random()*nextRelationApp.getSort();
            if (order == 0) {
                // 使用递归
                getRandom(nextRelationApp, preRelactionList);
            }
        } else if (sort == preRelactionList.size()) {
            // 排名最后一个
            order = Double.valueOf(nextRelationApp.getSort() + 1.0);
        } else {
            SubjectAppinfoRelation apR = null;
            if (sort > index)
            {
                // 如果从高的名次排到低的名词，如：1->4
                apR = preRelactionList.get(sort);
            }
            else
            {
                // 如果从地的名次排到高的名词，如：4->1
                apR = preRelactionList.get(sort-2);
            }


            // 取原有第sort的上一个到第sort条之间的随机数
            order = Double.valueOf(Math.random()*(nextRelationApp.getSort()-apR.getSort()) + apR.getSort());
            if (order == apR.getSort()) {
                // 使用递归
                getRandom(nextRelationApp, preRelactionList);
            }
        }
        return order;
    }


    public Page<SubjectAppinfoRelation> getPage()
    {
        return page;
    }

    public void setPage(Page<SubjectAppinfoRelation> page)
    {
        this.page = page;
    }

    public AppSubjectType getAppSubjectType()
    {
        return appSubjectType;
    }

    public void setAppSubjectType(AppSubjectType appSubjectType)
    {
        this.appSubjectType = appSubjectType;
    }

    public String getOperate()
    {
        return operate;
    }

    public SubjectAppinfoRelation getRelationQuery()
    {
        return relationQuery;
    }

    public void setRelationQuery(SubjectAppinfoRelation relationQuery)
    {
        this.relationQuery = relationQuery;
    }

    public void setOperate(String operate)
    {
        this.operate = operate;
    }

    public int getSort()
    {
        return sort;
    }

    public void setSort(int sort)
    {
        this.sort = sort;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }


}
