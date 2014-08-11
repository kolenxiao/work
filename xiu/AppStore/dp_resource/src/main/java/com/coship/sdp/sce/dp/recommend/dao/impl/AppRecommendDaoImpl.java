/*
 * 文件名称：AppRecommendDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.recommend.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.recommend.dao.AppRecommendDao;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommend;

/**
 * 应用的推荐数据访问实现类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Repository("appRecommendDao")
public class AppRecommendDaoImpl extends GenericDaoImpl<AppRecommend, String>
        implements AppRecommendDao
{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 6498350985652296370L;

    /**
     * 执行原生sql删除推荐记录
     * @param sql [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void executeDelete(String sql)
    {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    /**
     * @param sql
     * @return
     */
    @Override
    public List<AppRecommend> executSqlQuery(String sql)
    {
        SQLQuery query = getSession().createSQLQuery(sql);
        query.addEntity(AppRecommend.class);
        return query.list();
    }

    /**
     * @return
     */
    @Override
    public Session getExecuteSqlSession()
    {
        return getSession();
    }

    /**
     * @param appRec
     */
    @Override
    public void updateAppRecommend(String sql)
    {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.executeUpdate();

    }
}
