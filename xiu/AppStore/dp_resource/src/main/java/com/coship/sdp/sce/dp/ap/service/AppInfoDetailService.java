/*
 * 文件名称：AppInfoDetailService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-27
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.service;

import java.util.List;

import org.hibernate.type.Type;

import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.ap.entity.AppInfoSubjectDetail;
import com.coship.sdp.utils.Page;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-27]
 * @since [产品/模块版本]
 */
public interface AppInfoDetailService
{

    /**
     * <功能描述>
     * @param page
     * @param hql
     * @param objects
     * @return [参数说明]
     * @return Page<AppInfoDetail> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    Page<AppInfoDetail> listAppInfoDetail(Page<AppInfoDetail> page, String hql,
            Object[] values, Type[] types);

    /**
     * <功能描述>
     * @param appId
     * @return [参数说明]
     * @return AppInfoDetail [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    AppInfoDetail findAppInfoDetail(String appId);

    /**
     * <功能描述>
     * @param page
     * @param typeId
     * @param orderBy [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void getTypeAppInfoList(Page<AppInfoDetail> page, String typeId,
            String orderBy, int osVersion);

    /**
     * <功能描述>
     * @param page
     * @param subjectId
     * @param orderBy [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<AppInfoDetail> getSubjectTypeAppInfoList(String orderBy);

    /**
     * <功能描述>
     * @param page
     * @param subjectId
     * @param orderBy [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void getSubjectTypeAppInfoList(Page<AppInfoDetail> page, String subjectId,
            String orderBy, int osVersion);

    /**
     * <功能描述>
     * @param limit
     * @return [参数说明]
     * @return List<AppInfoDetail> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<AppInfoDetail> getRecommendAppInfos(int limit, int osVersion);

    /**
     * <功能描述>
     * @return List<AppInfoDetail> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<AppInfoDetail> getRecommendAppInfos();

    /**
     * 查询所有的应用排行信息
     * @param appList
     * @param typeId
     * @param rankType [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<AppInfoDetail> getRankTypeAppInfoList(String rankType);

    /**
     * <功能描述>
     * @param page
     * @param typeId
     * @param rankType [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void getRankTypeAppInfoList(Page<AppInfoDetail> page, String typeId,
            String rankType, int osVersion);

    /**
     * <功能描述>
     * @param getUpdateappListHql
     * @param objects
     * @return [参数说明]
     * @return AppInfoDetail [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    AppInfoDetail getUpdateAppBySQL(String getUpdateappListHql, String id,
            String packageName, int osVersion);
   
    /**
     * <功能描述>
     * @return List<AppInfoDetail> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<AppInfoDetail> getTypeRecommendAppInfos();
}
