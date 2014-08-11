/*
 * 文件名称：AppCommentService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.comment.service;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.comment.entity.AppCommentInfo;
import com.coship.sdp.sce.dp.comment.entity.AppScoreSummary;
import com.coship.sdp.sce.dp.comment.entity.AppScoreSummaryEntity;
import com.coship.sdp.utils.Page;

/**
 * 应用的评论信息服务接口
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
public interface AppCommentService
{

    /**
     * 保存评论信息.
     * @param appCommentInfo 评论信息对象
     * @throws Exception [参数说明]
     * @return void
     */
    public void saveAppCommentInfo(AppCommentInfo appCommentInfo)
            throws Exception;

    /**
     * 更新评论信息.
     * @param appCommentInfo 评论信息对象
     * @throws Exception [参数说明]
     * @return void
     */
    public void updateAppCommentInfo(AppCommentInfo appCommentInfo)
            throws Exception;

    /**
     * 分页查询获得评论信息
     *
     * @param page 分页对象
     * @param hql 查询语句
     * @param values 参数
     * @return Page<AppCommentInfo> 分页评论信息
     * @throws Exception [参数说明]
     */
    public Page<AppCommentInfo> listAppCommentInfo(Page<AppCommentInfo> page,
            String hql, Object[] values) throws Exception;

    /**
     * 用户评论应用唯一性
     *
     * @param userId 用户id
     * @param appId 应用id
     * @return AppCommentInfo 应用评论对象
     * @throws Exception [参数说明]
     */
    public AppCommentInfo findUnique(String userId, DpAppInfo dpAppInfo)
            throws Exception;
    
    /**
     * 用户评论应用唯一性
     *
     * @param userId 用户id
     * @param appPackageName 应用包名
     * @return AppCommentInfo 应用评论对象
     * @throws Exception [参数说明]
     */
    public AppCommentInfo findUnique(String userId, String appPackageName)
            throws Exception;

    /**
     * 应用评分统计——平均评分，1-5星人数.
     *
     * @param dpAppInfo 应用对象
     * @return AppScoreSummary 评分统计对象
     * @throws Exception [参数说明]
     */
    public AppScoreSummary getAppScoreSummary(DpAppInfo dpAppInfo)
            throws Exception;

    /**
     * 根据应用id删除评论信息.
     *
     * @param dpAppInfo 应用对象
     * @throws Exception [参数说明]
     * @return void
     * @exception throws [违例类型] [违例说明]
     */
    public void deleteAppCommentInfo(DpAppInfo dpAppInfo) throws Exception;

    /**
     * 根据应用包名查询评论信息
     * @param page
     * @param pacName
     * @return [参数说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void listAppCommentInfoByPacName(Page<AppCommentInfo> page,
            String pacName) throws Exception;

    /**
     * 根据包名应用评分信息
     * @param page
     * @param pacName
     * @return [参数说明]
     * @exception throws [违例类型] [违例说明]
     */
    AppScoreSummary getAppScoreSummaryByPacName(String pacName)
            throws Exception;

    /**
     * <功能描述>
     * @param packageName
     * @return
     * @throws Exception [参数说明]
     * @return AppScoreSummary [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    AppScoreSummaryEntity getAppScoreSummaryByPackageName(String packageName)
            throws Exception;

    /**
     * 分页查询获得评论信息
     *
     * @param page 分页对象
     * @param appInfo 应用信息对象
     * @return Page<AppCommentInfo> 分页评论信息
     * @throws Exception [参数说明]
     */
    Page<AppCommentInfo> listAppCommentInfoByAppInfo(Page<AppCommentInfo> commnetPage,
            DpAppInfo appInfo) throws Exception;
}
