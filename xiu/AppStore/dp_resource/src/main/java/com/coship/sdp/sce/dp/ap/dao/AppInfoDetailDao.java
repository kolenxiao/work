/*
 * 文件名称：AppInfoDetailDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-27
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.dao;

import java.util.List;

import org.hibernate.type.Type;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.ap.entity.AppInfoSubjectDetail;
import com.coship.sdp.utils.Page;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-27]
 * @since [产品/模块版本]
 */
public interface AppInfoDetailDao extends IGenericDao<AppInfoDetail, String>
{

    /**
     * <功能描述>
     * @param page
     * @param sql
     * @param objects
     * @return [参数说明]
     * @return Page<AppInfoDetail> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    Page<AppInfoDetail> queryPageBySql(Page<AppInfoDetail> page, String sql,
            Object[] values, Type[] types);


    /**
     * <功能描述>
     * @param page
     * @param sql
     * @param objects
     * @return [参数说明]
     * @return Page<AppInfoDetail> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<AppInfoDetail> queryListBySql(String sql, Object[] values, Type[] types);

    /**
     * <功能描述>
     * @param sql
     * @param appId
     * @return [参数说明]
     * @return AppInfoDetail [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    AppInfoDetail findUniqueByIdSql(String sql);

    /**
     * <功能描述>
     * @param sql
     * @param objects
     * @param types [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<AppInfoDetail> queryListBySql(String sql);

    /**
     * 查询专题的应用列表
     * @param sql
     * @param objects
     * @param types [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<AppInfoDetail> querySubjectListBySql(String sql);


}
