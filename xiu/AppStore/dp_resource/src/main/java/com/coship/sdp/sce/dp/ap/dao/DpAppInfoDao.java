/*
 * 文件名称：DpAppInfoDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-9-5
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.dao;

import java.util.Map;

import org.hibernate.Session;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.ap.entity.AppStatisticsInfo;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.utils.Page;

/**
 * 应用信息数据访问接口
 * @author FuJian/906126
 * @version [版本号, 2011-9-5]
 * @since [产品/模块版本]
 */
public interface DpAppInfoDao extends IGenericDao<DpAppInfo, String>
{

    /**
     * 使用原生sql删除和主题管理关系数据
     * @param sql [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void deleteSubjectRelation(String sql);

    /**
     * 执行原生sql查询应用统计
     * @param page
     * @param sql [参数说明]
     * @param param
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void exeSqlQueryStatistic(Page<AppStatisticsInfo> page, String sql,
            Map<String, Object> param);


    /**
     * 执行原生sql查询应用信息
     * @param page
     * @param sql [参数说明]
     * @param param
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void exeSqlQueryAppInfo(Page<DpAppInfo> page, String sql);

    /**
     * 执行原生sql根据应用id获取应用的统计数据
     * @param sql
     * @return [参数说明]
     * @return AppStatisticsInfo [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    AppStatisticsInfo exeSqlQueryStatisticUnique(String sql);

    /**
     * 执行原生sql根据专题id获取应用的信息列表
     * @param page
     * @param sql [参数说明]
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    void queryForPageBySql(Page<DpAppInfo> page, String sql) throws Exception;

    void queryForPageBySql(Page<DpAppInfo> page, String sql,
            Map<String, Object> param) throws Exception;

    Session createExecuteSqlSession();

    /**
     * 根据应用Id获取下载次数
     * @param sql
     * @return [参数说明]
     * @return AppStatisticsInfo [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    int getCount(String sql) throws Exception;
}
