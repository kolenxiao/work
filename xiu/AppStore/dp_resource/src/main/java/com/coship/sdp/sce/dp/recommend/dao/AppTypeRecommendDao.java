/*
 * 文件名称：AppRecommendDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.recommend.dao;

import java.util.List;

import org.hibernate.Session;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommend;
import com.coship.sdp.sce.dp.recommend.entity.AppTypeRecommend;

/**
 * 应用分类的推荐数据访问接口
 * @author WangZhengHui/907632
 * @version [版本号, 2013-7-5]
 * @since [产品/模块版本]
 */
public interface AppTypeRecommendDao extends IGenericDao<AppTypeRecommend, String>
{

    /**
     * 执行原生sql删除推荐记录
     * @param sql [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void executeDelete(String sql);

    /**
     * 执行原生sql查询推荐记录
     * @param sql [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<AppRecommend> executSqlQuery(String sql);

    Session getExecuteSqlSession();

    void updateAppTypeRecommend(String sql);
}
