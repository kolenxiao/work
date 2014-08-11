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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.subject.dao.AppSubjectTypeDao;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.utils.Page;

/**
 * 专题分类数据访问实现类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Repository("appSubjectTypeDao")
@SuppressWarnings("deprecation")
public class AppSubjectTypeDaoImpl extends
        GenericDaoImpl<AppSubjectType, String> implements AppSubjectTypeDao
{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 697592455982868240L;

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

    /**
     * 执行原生查询记录条数的sql
     * @param sql
     * @throws SQLException [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public int executeNativeQueryCountSql(String sql) throws SQLException
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            Session session = getSession();
            con = session.connection();
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return rs.getInt(1);
            }
            return 0;
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
    public List<DpAppInfo> queryForPageBySql(Page<AppSubjectType> page,
            String sql)
    {
        Session session = getSession();
        SQLQuery sq = session.createSQLQuery(sql);
        sq.addEntity(DpAppInfo.class);
        sq.setFirstResult(page.getBeginCount());
        sq.setMaxResults(page.getPageSize());
        return sq.list();
    }

    @Override
    public void updateNoCascade(AppSubjectType appSubjectType)
            throws SQLException
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            Session session = getSession();
            con = session.connection();

            String sql = "update T_APP_SUBJECT_TYPE st set st.C_UPDATE_DATE=? , st.C_SUBJECT_NAME=? "
                    + ",st.C_SUBJECT_IMG=?,st.C_SUBJECT_DESC=?,st.C_PRODUCT_CODE=? where st.C_ID=?";
            ps = con.prepareStatement(sql);
            ps.setDate(1, new Date(System.currentTimeMillis()));
            ps.setString(2, appSubjectType.getSubjectName());
            ps.setString(3, appSubjectType.getSubjectImg());
            ps.setString(4, appSubjectType.getSubjectDesc());
            ps.setString(5, appSubjectType.getProductCode());
            ps.setString(6, appSubjectType.getId());
            ps.executeUpdate();
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

    /**
     * @param sqlList
     * @throws SQLException
     */
    @Override
    public void executeNativeBatch(List<String> sqlList) throws SQLException
    {
        Connection con = null;
        Statement ps = null;
        try
        {
            Session session = getSession();
            con = session.connection();
            ps = con.createStatement();
            for (String sql : sqlList)
            {
                ps.addBatch(sql);
            }
            ps.executeBatch();
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
}
