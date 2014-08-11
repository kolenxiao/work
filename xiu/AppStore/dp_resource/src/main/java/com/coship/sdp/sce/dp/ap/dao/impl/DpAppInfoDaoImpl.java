/*
 * 文件名称：DpAppInfoDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-9-5
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.ap.dao.DpAppInfoDao;
import com.coship.sdp.sce.dp.ap.entity.AppStatisticsInfo;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;
import com.coship.sdp.utils.Page;

/**
 * 应用数据访问实现类
 * @author FuJian/906126
 * @version [版本号, 2011-9-5]
 * @since [产品/模块版本]
 */
@Repository("dpAppInfoDao")
public class DpAppInfoDaoImpl extends GenericDaoImpl<DpAppInfo, String>
        implements DpAppInfoDao
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param string
     */
    @Override
    public void deleteSubjectRelation(String sql)
    {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void exeSqlQueryStatistic(Page<AppStatisticsInfo> page, String sql,
            Map<String, Object> param)
    {
        List<AppStatisticsInfo> list = new ArrayList<AppStatisticsInfo>();
        Query query = this.getSession().createSQLQuery(sql);
        query.setProperties(param);
        int pageSize = page.getPageSize();
        int fromResult = (page.getCurrentPage() - 1) * pageSize;
        query.setMaxResults(pageSize);
        query.setFirstResult(fromResult);

        // 数据封装
        List listData = query.list();
        for (Object props : listData)
        {
            String staffName = null;
            AppStatisticsInfo info = new AppStatisticsInfo();
            Object[] propArray = (Object[]) props;
            info.setAppId((String) propArray[0]);
            info.setAppName((String) propArray[1]);
            info.setAppType((String) propArray[2]);
            info.setAppTypeId((String) propArray[2]);
            info.setUpdateTime((Date) propArray[4]);
            String staffId = (String) propArray[3];
            String userId = (String) propArray[5];
            if (StringUtils.isNotEmpty(staffId))
            {
                Query queryStaff = getSession().createSQLQuery("select c_user_name from t_dp_staff where c_id='"+staffId+"'");
                List staffList = queryStaff.list();
                if (CollectionUtils.isNotEmpty(staffList))
                {
                    for (Object staffpro : staffList)
                    {
                        staffName = (String) staffpro;
                    }
                }
            }
            else if (StringUtils.isNotEmpty(userId))
            {
                Query queryUser = getSession().createSQLQuery("select c_user_name from t_user where c_id='"+userId+"'");
                List userList = queryUser.list();
                if (CollectionUtils.isNotEmpty(userList))
                {
                    for (Object proUser : userList)
                    {
                        staffName = (String) proUser;
                    }
                }
            }

            info.setDpStaffName(staffName);
            if (propArray[6] != null)
            {
                BigInteger down = (BigInteger) propArray[6];
                info.setDownloadCount(down.intValue());
            }
            if (propArray[7] != null)
            {
                BigInteger comment = (BigInteger) propArray[7];
                info.setCommentCount(comment.intValue());
            }
            if (propArray[8] != null)
            {
                BigDecimal avgScore = (BigDecimal) propArray[8];
                info.setCommentAvgScore(avgScore.doubleValue());
            }
            info.setAppStatus((String) propArray[9]);
            info.setPackageName((String) propArray[10]);
            list.add(info);
        }
        page.setResultList(list);

        String countSql = "select count(1) from (" + sql + ") tempTable";
        Query queryCount = this.getSession().createSQLQuery(countSql);
        queryCount.setProperties(param);
        BigInteger count = (BigInteger) queryCount.uniqueResult();
        page.setTotalCount(count.intValue());
    }

    @SuppressWarnings("rawtypes")
    public void exeSqlQueryAppInfo(Page<DpAppInfo> page, String sql)
    {
        List<DpAppInfo> list = new ArrayList<DpAppInfo>();
        Query query = this.getSession().createSQLQuery(sql);
        int pageSize = page.getPageSize();
        int fromResult = (page.getCurrentPage() - 1) * pageSize;
        query.setMaxResults(pageSize);
        query.setFirstResult(fromResult);

        // 数据封装
        List listData = query.list();

        for (int i = 0; i < listData.size(); i ++)
        {
            Object props  = listData.get(i);
            DpAppInfo info = new DpAppInfo();
            Object[] propArray = (Object[]) props;

            info = setDpAppInfo(info, propArray);
            list.add(info);
        }
        page.setResultList(list);

        String countSql = "select count(1) from (" + sql + ") tempTable";
        Query queryCount = this.getSession().createSQLQuery(countSql);
        BigInteger count = (BigInteger) queryCount.uniqueResult();
        page.setTotalCount(count.intValue());
    }

    /**
     * 封装DpAppInfo数据
     * @param ifo: 应用信息对象
     * @param propArray: 数据库查询的结果信息
     */
    private DpAppInfo setDpAppInfo(DpAppInfo info, Object[] propArray)
    {
        info.setId((String) propArray[0]);
        info.setAppName((String) propArray[1]);
        info.setUpdateTime((Date) propArray[2]);
        info.setSystem((String) propArray[3]);
        info.setVersion((String) propArray[4]);
        info.setDeveloper((String) propArray[5]);

        if (propArray[6] != null)
        {
            BigInteger down = (BigInteger) propArray[6];
            info.setDownloadCount(down.intValue());
        }
        if (propArray[7] != null)
        {
            BigInteger comment = (BigInteger) propArray[7];
            info.setCommentCount(comment.intValue());
        }

        if (propArray[8] != null)
        {
            BigDecimal avgScore = (BigDecimal) propArray[8];
            info.setAverageScore(avgScore.doubleValue());
        }

        info.setPrice((Double)propArray[9]);
        info.setAppFilePackage((String)propArray[10]);

        String attaHql = "from AttachmentFile a "
            + " where a.dpAppInfo.id='"
            + info.getId() +"'";

        // 格式化apk附件大小
        DecimalFormat df = new DecimalFormat("###.00");

        List<AttachmentFile> files = query(attaHql, new Object[0]);
        for (AttachmentFile file : files)
        {
            if ("appfile".equals(file.getFileDesc()))
            {
                // 转换成M单位
                file.setSize(Double.valueOf(df.format((file.getFileSize()+0.0)/(1024*1024))));
            }
        }

        info.setAttachmentList(files);

        return info;
    }

    @Override
    public AppStatisticsInfo exeSqlQueryStatisticUnique(String sql)
    {
        Query query = this.getSession().createSQLQuery(sql);
        Object props = query.uniqueResult();
        if (props != null)
        {
            AppStatisticsInfo info = new AppStatisticsInfo();

            Object[] propArray = (Object[]) props;
            info.setAppId((String) propArray[0]);
            info.setAppName((String) propArray[1]);
            info.setAppType((String) propArray[2]);
            info.setDpStaffName((String) propArray[3]);
            info.setUpdateTime((Date) propArray[4]);

            if (propArray[5] != null)
            {
                BigInteger down = (BigInteger) propArray[5];
                info.setDownloadCount(down.intValue());
            }
            if (propArray[6] != null)
            {
                BigInteger comment = (BigInteger) propArray[6];
                info.setCommentCount(comment.intValue());
            }
            if (propArray[7] != null)
            {
                BigDecimal avgScore = (BigDecimal) propArray[7];
                info.setCommentAvgScore(avgScore.doubleValue());
            }

            return info;
        }
        return null;
    }

    /**
     * 执行原生sql获取应用的信息列表
     * @param page
     * @param sql [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void queryForPageBySql(Page<DpAppInfo> page, String sql)
            throws Exception
    {
        Session session = getSession();
        SQLQuery sq = session.createSQLQuery(sql);
        sq.addEntity(DpAppInfo.class);
        sq.setFirstResult(page.getBeginCount());
        sq.setMaxResults(page.getPageSize());
        page.setResultList(sq.list());
        String countSql = "select count(1) from (" + sql + ") tempTab";
        SQLQuery sqCount = session.createSQLQuery(countSql);
        BigInteger total = (BigInteger) sqCount.uniqueResult();
        page.setTotalCount(total.intValue());
    }

    @Override
    public void queryForPageBySql(Page<DpAppInfo> page, String hql,
            Map<String, Object> params) throws Exception
    {
        Session session = getSession();
        SQLQuery sq = session.createSQLQuery(hql);
        sq.addEntity(DpAppInfo.class);
        if (params != null)
        {
            Set<String> keySet = params.keySet();
            for (String p : keySet)
            {
                sq.setParameter(p, params.get(p));
            }
        }
        sq.setFirstResult(page.getBeginCount());
        sq.setMaxResults(page.getPageSize());
        page.setResultList(sq.list());

        String countSql = "select count(1) from (" + hql + ") tempTab";
        SQLQuery sqCount = session.createSQLQuery(countSql);
        if (params != null)
        {
            Set<String> keySet = params.keySet();
            for (String p : keySet)
            {
                sqCount.setParameter(p, params.get(p));
            }
        }
        BigInteger total = (BigInteger) sqCount.uniqueResult();
        page.setTotalCount(total.intValue());
    }

    @Override
    public int getCount(String sql) throws Exception
    {
        Session session = getSession();
        SQLQuery query = session.createSQLQuery(sql);
        BigInteger total = (BigInteger)query.uniqueResult();
        return total.intValue();
    }

    @Override
    public Session createExecuteSqlSession()
    {
        return getSession();
    }

}
