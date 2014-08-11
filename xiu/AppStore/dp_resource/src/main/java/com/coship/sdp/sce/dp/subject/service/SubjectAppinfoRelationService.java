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

import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.subject.entity.SubjectAppinfoRelation;
import com.coship.sdp.utils.Page;

/**
 * 应用专题服务接口
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
public interface SubjectAppinfoRelationService extends Serializable
{

    /**
     * 根据应用专题分页查询应用列表
     * @param page 分页参数
     * @param appSubjectType 应用专题
     * @return [参数说明]应用列表
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    void queryAppInfoPageBySubjectType(Page<SubjectAppinfoRelation> page,
            AppSubjectType appSubjectType) throws Exception;

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
     * 根据应用从专题中移除应用
     * @param idsStr [参数说明]
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    void deleteAppSubjectRelationByIds(AppSubjectType appSubjectType,
            String[] idsStr) throws Exception;

    /**
     * 根据应用专题获取所有的应用信息
     * @param page
     * @param sql
     * @return [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<SubjectAppinfoRelation> getSubjectAppinfoRelationsBySubType(AppSubjectType appSubjectType);


    /**
     * 根据id获取应用专题
     * @param id 应用专题的id
     * @return [参数说明] 应用专题对象
     * @return AppSubjectType [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    SubjectAppinfoRelation getAppSubjectAppById(String id) throws Exception;


    /**
     * 根据id获取应用专题
     * @param id 应用专题的id
     * @return [参数说明] 应用专题对象
     * @return AppSubjectType [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    SubjectAppinfoRelation getAppSubjectApp(String id) throws Exception;

    /**
     * 更新专题应用信息
     * @param id 应用专题的id
     * @return [参数说明] 应用专题对象
     * @return AppSubjectType [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    void updateSubjectAppinfoRelation(SubjectAppinfoRelation rela) throws Exception;

    /**
     * 更新专题应用排名信息
     * @param rela [参数说明] 应用专题对象
     * @return AppSubjectType [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    void updateSubjectAppinfoRelationSort(SubjectAppinfoRelation rela) throws Exception;

    /**
     * 根据专题列表获得专题下的所有应用包名列表.
     * 
     * @param subjectTypeIdList
     * @return
     */
    List<String> getSubjectAppPkgNameList(List<String> subjectTypeIdList);

}
