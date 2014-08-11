/*
 * 文件名称：AppSubjectTypeDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.subject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.subject.dao.SubjectAppinfoRelationDao;
import com.coship.sdp.sce.dp.subject.entity.SubjectAppinfoRelation;
import com.coship.sdp.utils.Page;

/**
 * 专题分类数据访问实现类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Repository("subjectappinfoRelationDao")
public class SubjectappinfoRelationDaoImpl extends
        GenericDaoImpl<SubjectAppinfoRelation, String> implements SubjectAppinfoRelationDao
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 使用原生sql根据应用专题分页查询应用信息列表
     * @param page
     * @param sql
     * @return [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SubjectAppinfoRelation> queryForPageBySql(Page<SubjectAppinfoRelation> page,
            String hql)
    {
        Session session = getSession();
        SQLQuery sq = session.createSQLQuery(hql);
        sq.addEntity(SubjectAppinfoRelation.class);
        sq.setFirstResult(page.getBeginCount());
        sq.setMaxResults(page.getPageSize());
        return sq.list();
    }

    /**
     * 执行原生更新sql
     * @param sql
     * @throws SQLException [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void executeNativeUpdateSql(String sql) throws SQLException
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            Session session = getSession();
            con = session.connection();
            ps = con.prepareStatement(sql);
            ps.execute();
        }
        catch (SQLException e)
        {
            throw e;
        }
        finally
        {
            closeResource(con, ps);
        }
    }


    private void closeResource(Connection con, Statement ps)
            throws SQLException
    {
        if (ps != null)
        {
            ps.close();
        }
        if (con != null)
        {
            con.close();
        }
    }


    /**
     * @param sql
     * @throws SQLException
     */
    @Override
    public void updateSubjectAppinfoRelation(String sql) throws SQLException
    {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getSubjectAppPkgNameList(List<String> subjectTypeIdList) {
        String hql = "select distinct app.appFilePackage from SubjectAppinfoRelation r left join r.appInfo app where r.appSubjectType.id in (:subjectIdList) ";

        return this.getSession().createQuery(hql).setParameterList("subjectIdList", subjectTypeIdList).list();
    }
}
