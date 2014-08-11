/*
 * 文件名称：AppSubjectTypeDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.subject.dao;

import java.sql.SQLException;
import java.util.List;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.utils.Page;

/**
 * 应用专题类型数据访问接口
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
public interface AppSubjectTypeDao extends IGenericDao<AppSubjectType, String>
{
    /**
     * <功能描述>
     * @param sql [参数说明]
     * @return void [返回类型说明]
     * @throws SQLException
     * @exception throws [违例类型] [违例说明]
     */
    void executeNativeUpdateSql(String sql) throws SQLException;

    /**
     * <功能描述>
     * @param sql
     * @return
     * @throws SQLException [参数说明]
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public int executeNativeQueryCountSql(String sql) throws SQLException;

    /**
     * 使用原生sql根据应用专题分页查询应用信息列表
     * @param page
     * @param sql
     * @return [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<DpAppInfo> queryForPageBySql(Page<AppSubjectType> page, String sql);

    /**
     * 使用原生sql更新
     * @param appSubjectType [参数说明]
     * @return void [返回类型说明]
     * @throws SQLException
     * @exception throws [违例类型] [违例说明]
     */
    void updateNoCascade(AppSubjectType appSubjectType) throws SQLException;

    /**
     * <功能描述>
     * @param sqlList [参数说明]
     * @return void [返回类型说明]
     * @throws SQLException
     * @exception throws [违例类型] [违例说明]
     */
    void executeNativeBatch(List<String> sqlList) throws SQLException;

}
