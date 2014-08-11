/*
 * 文件名称：AppCommentDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.comment.dao.impl;

import java.math.BigInteger;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.comment.dao.AppCommentDao;
import com.coship.sdp.sce.dp.comment.entity.AppCommentInfo;
import com.coship.sdp.utils.Page;

/**
 * 评论信息数据访问实现类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Repository("appCommentDao")
public class AppCommentDaoImpl extends GenericDaoImpl<AppCommentInfo, String>
        implements AppCommentDao
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = -4011060204253332728L;

    /**
     * @param page
     * @param objects
     */
    @Override
    public void queryPageBySql(Page<AppCommentInfo> page, String sql,
            Object[] objects, Type[] types)
    {
        Session session = getSession();
        SQLQuery sq = session.createSQLQuery(sql);
        sq.setParameters(objects, types);
        sq.addEntity(AppCommentInfo.class);
        sq.setFirstResult(page.getBeginCount());
        sq.setMaxResults(page.getPageSize());
        page.setResultList(sq.list());

        String countSql = "select count(1) from (" + sql + ") tempTab";
        SQLQuery sqCount = session.createSQLQuery(countSql);
        sqCount.setParameters(objects, types);
        BigInteger total = (BigInteger) sqCount.uniqueResult();
        page.setTotalCount(total.intValue());
    }

    @Override
    public Session creatSqlSession()
    {
        return getSession();
    }

}
