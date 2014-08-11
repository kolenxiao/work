/*
 * 文件名称：AppSubjectTypeService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.subject.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.utils.Page;

/**
 * 应用专题服务接口
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
public interface AppSubjectTypeService extends Serializable
{

    /**
     * 分页查询专题列表
     * @param page
     * @param queryAppSubjectType [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void searchAppSubjectType(Page<AppSubjectType> page,
            AppSubjectType queryAppSubjectType);

    /**
     * 根据id获取应用专题
     * @param id 应用专题的id
     * @return [参数说明] 应用专题对象
     * @return AppSubjectType [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    AppSubjectType getAppSubjectTypeById(String id) throws Exception;

    /**
     * 保存编辑应用专题
     * @param appSubjectType [参数说明]
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    void saveAppSubjectType(AppSubjectType appSubjectType) throws Exception;

    /**
     * 根据专题id删除专题信息
     * @param idsStr [参数说明]
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    void deleteAppSubjectTypeByIds(String[] idsStr) throws Exception;

    /**
     * 根据应用专题分页查询应用列表
     * @param page 分页参数
     * @param appSubjectType 应用专题
     * @return [参数说明]应用列表
     * @return List<DpAppInfo> [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    List<DpAppInfo> queryAppInfoPageBySubjectType(Page<AppSubjectType> page,
            AppSubjectType appSubjectType) throws Exception;

    /**
     * 获取未被该专题关联的上架应用分页列表
     * @param page
     * @param appSubjectType
     * @param queryAppInfo
     * @return [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void queryAppInfoPageBySubjectTypeNoRel(
            Page<DpAppInfo> page, AppSubjectType appSubjectType,
            DpAppInfo queryAppInfo) throws Exception;

    /**
     * 根据应用从专题中移除应用
     * @param idsStr [参数说明]
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    void deleteAppSubjectRelationByIds(AppSubjectType appSubjectType,
            String[] idsStr) throws Exception;

    /**
     * 向主题中添加应用
     * @param appSubjectType 主题类型对象
     * @param idsStr [参数说明] 应用id
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    void addAppToSubjectType(AppSubjectType appSubjectType, String[] idsStr)
            throws Exception;

    /**
     * <功能描述>
     * @param appSubjectType
     * @param queryAppInfo
     * @return [参数说明]
     * @return int [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    int queryAppInfoPageBySubjectTypeNoRelCount(AppSubjectType appSubjectType,
            DpAppInfo queryAppInfo) throws Exception;

    /**
     * 根据应用id删除相关的专题关系.
     *
     * @param dpAppInfo 应用对象
     * @throws Exception
     * @return void
     * @exception throws [违例类型] [违例说明]
     */
    public void deleteAppSubjectRelationByAppId(DpAppInfo dpAppInfo)
            throws Exception;

    /**
     * 获取专题列表
     * @param page 分页对象
     * @param getAppSubjectHql 查询HQL
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void listAppSubjectType(Page<AppSubjectType> page,
            String getAppSubjectHql) throws Exception;

    /**
     * 获取主题下的应用分页列表
     * @param page [参数说明]
     * @param subjectId 专题id
     * @param orderBy 排序参数
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void getSubjectTypeAppInfoList(Page<DpAppInfo> page, String subjectId,
            String orderBy) throws Exception;

    /**
     * 获取主题下的应用数
     * @param subjectId 专题id
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    int getAppTotalBySubjectTypeId(String subjectId) throws Exception;

    /**
     * 获取所有的主题列表
     * @return List<AppSubjectType> 返回所有的专题列表信息
     * @exception throws [违例类型] [违例说明]
     */
    List<AppSubjectType> getAppSubjectTypeList() throws Exception;

}
