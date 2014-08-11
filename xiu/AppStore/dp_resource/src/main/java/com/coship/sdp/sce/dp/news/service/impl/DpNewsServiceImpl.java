/*
 * 文件名称：DpNewsServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Aug 31, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.news.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.news.dao.DpNewsDao;
import com.coship.sdp.sce.dp.news.entity.DpNews;
import com.coship.sdp.sce.dp.news.service.DpNewsService;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.DateUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * <功能描述>.
 * @author Huangliufei/905735
 * @version [版本号, Aug 31, 2011]
 * @since [产品/模块版本]
 */
@Service("dpNewsService")
@Transactional
public class DpNewsServiceImpl implements DpNewsService
{
    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * classname.
     */
    private static final String MODULE = DpNewsServiceImpl.class.getName();

    /**
     * 查询所有的hql语句.
     */
    private static final String QUERY_HQL = "from DpNews dn where 1=1";

    /**
     * dao层接口.
     */
    @Autowired
    private DpNewsDao dpNewsDao;

    /**
     * 保存资讯.
     * @param dpNews 资讯实体
     * @throws Exception 异常
     */
    @Override
    public void saveDpNews(DpNews dpNews) throws Exception
    {
        this.dpNewsDao.save(dpNews);
    }

    /**
     * 更新资讯.
     * @param dpNews 资讯实体
     * @throws Exception 异常
     */
    @Override
    public void updateDpNews(DpNews dpNews) throws Exception
    {
        this.dpNewsDao.saveOrUpdate(dpNews);
    }

    /**
     * 删除资讯.
     * @param dpNews 资讯实体
     * @throws Exception 异常
     */
    @Override
    public void deleteDpNews(DpNews dpNews) throws Exception
    {
        this.dpNewsDao.delete(dpNews);
    }

    /**
     * 根据id组删除资讯.
     * @param idArray id数组
     * @throws Exception 异常
     */
    @Override
    public void deleteDpNewsList(String[] idArray) throws Exception
    {
        try
        {
            for (int i = 0, leng = idArray.length; i < leng; i++)
            {
                DpNews dpNews = findDpNews(idArray[i].trim());
                deleteDpNews(dpNews);
                Debug.logInfo("delete news success");
            }
        }
        catch (Exception e)
        {
            Debug.logError(e, "delete news error" + e.getMessage(), MODULE);
        }

    }

    /**
     * 根据id查询资讯.
     * @param id 资讯id
     * @return 资讯对象
     */
    @Override
    public DpNews findDpNews(String id)
    {
        return this.dpNewsDao.get(id);
    }

    /**
     * @param page 分页对象
     * @param dpNews 资讯对象
     * @return Page<DpNews>资讯列表
     * @throws Exception 异常
     */
    @Override
    public Page<DpNews> listDpNews(Page<DpNews> page, DpNews dpNews)
            throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();
        page = this.dpNewsDao.queryPage(page, buildHQL(dpNews, map), map);
        return page;
    }

    /**
     * 拼装hql语句.
     * @param map map对象
     * @param dpNews 资讯对象
     * @return String hql语句
     */
    private String buildHQL(DpNews dpNews, Map<String, Object> map)
    {
        StringBuilder hql = new StringBuilder(QUERY_HQL);
        // 资讯标题
        if (StringUtils.isNotEmpty(dpNews.getNewsTitle()))
        {
            hql.append(" and dn.newsTitle like'%"
                    + SqlUtil.escapeSQLLike(dpNews.getNewsTitle().trim())
                    + "%'  escape'/'");
        }
        // 创建时间段
        if (StringUtils.isNotEmpty(dpNews.getBeginNewsCtime())
                || StringUtils.isNotEmpty(dpNews.getEndNewsCtime()))
        {
            if (StringUtils.isNotEmpty(dpNews.getBeginNewsCtime())
                    && StringUtils.isEmpty(dpNews.getEndNewsCtime()))
            {
                hql.append(" and dn.newsCreateTime >= :beginNewsCreateTime");

                map.put("beginNewsCreateTime",
                        DateUtil.strToDate(dpNews.getBeginNewsCtime()
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else if (StringUtils.isEmpty(dpNews.getBeginNewsCtime())
                    && StringUtils.isNotEmpty(dpNews.getEndNewsCtime()))
            {
                hql.append(" and dn.newsCreateTime <= :endNewsCreateTime");
                map.put("endNewsCreateTime",
                        DateUtil.strToDate(dpNews.getEndNewsCtime()
                                + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else
            {
                hql.append(" and dn.newsCreateTime >= :beginNewsCreateTime"
                        + " and dn.newsCreateTime <= :endNewsCreateTime");
                map.put("beginNewsCreateTime",
                        DateUtil.strToDate(dpNews.getBeginNewsCtime()
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
                map.put("endNewsCreateTime",
                        DateUtil.strToDate(dpNews.getEndNewsCtime()
                                + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }
        }
        // 资讯类型
        if (dpNews.getDpType() != null && dpNews.getDpType().getId() != null
                && !"".equals(dpNews.getDpType().getId()))
        {
            hql.append(" and dn.dpType ='" + dpNews.getDpType().getId() + "'");
        }
        // 资讯来源
        if (StringUtils.isNotEmpty(dpNews.getNewsSource()))
        {

            hql.append(" and dn.newsSource like'%"
                    + SqlUtil.escapeSQLLike(dpNews.getNewsSource().trim())
                    + "%'  escape'/'");
        }
        hql.append(" order by dn.newsCreateTime desc");
        return hql.toString();
    }

    /**
     * @param hql hql语句
     * @return List<DpNews> 资讯列表
     * @throws Exception 异常
     */
    @Override
    public List<DpNews> findByHQL(String hql) throws Exception
    {
        return this.dpNewsDao.query(hql);
    }

    /**
     * 根据属性名判断该对象是否唯一.
     * @param propertyName 属性名称 。
     * @param value 属性对应的值
     * @return TRUE 表示唯一，反之则否
     * @throws Exception 异常
     */
    @Override
    public boolean isUniqueneByPropertyName(String propertyName, String value)
            throws Exception
    {
        String hql = "from DpNews dn where dn." + propertyName + "='" + value
                + "'";
        List<DpNews> dpNewsList;
        boolean isUniquene = true;
        dpNewsList = findByHQL(hql);
        if (null != dpNewsList && dpNewsList.size() > 0)
        {
            isUniquene = false;
        }
        return isUniquene;

    }

    /**
     * 分页查询.
     * @param firstResult 起始页码
     * @param maxResults 每页显示多少条
     * @return 资讯列表.
     */
    @Override
    public List<DpNews> findDpNewsByLimit(int firstResult, int maxResults)
    {
        String hql = "from DpNews n where n.dpType.id != '1000' order by n.newsCreateTime desc";
        // @SuppressWarnings("unchecked")
        List<DpNews> list = this.dpNewsDao.createQuery(hql)
                .setFirstResult(firstResult).setMaxResults(maxResults).list();
        return list;
    }

    /**
     * @param firstResult
     * @param maxResults
     * @return
     */
    @Override
    public List<DpNews> findDpNewsByClickTime(int firstResult, int maxResults,
            int flag)
    {
        Map<String, Date> map = getRecentDate(flag);
        Date startDate = map.get("startDate");
        Date endDate = map.get("endDate");
        String hql = "from DpNews dn where dn.newsCreateTime>=? and dn.newsCreateTime<=? order by dn.clickTime desc";

        @SuppressWarnings("unchecked")
        List<DpNews> list = this.dpNewsDao.createQuery(hql)
                .setParameter(0, startDate)
                .setParameter(1, endDate)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults).list();
        return list;
    }

    private Map<String, Date> getRecentDate(int flag)
    {
        Date endDate = new Date();

        // 创建基于当前时间的日历对象
        Calendar cl = Calendar.getInstance();
        cl.setTime(endDate);

        // 距离今天，一周内的数据
        if (Constants.ONE_WEEK_FLAG == flag)
        {
            cl.add(Calendar.DATE, -7);
        }
        // 距离今天，一个月内数据
        if (Constants.ONE_MONTH_FLAG == flag)
        {
            cl.add(Calendar.MONTH, -1);
        }
        Date startDate = cl.getTime();

        Map<String, Date> map = new HashMap<String, Date>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);

        return map;
    }

    /**
     * @param typeCode
     * @return
     */
    @Override
    public List<DpNews> findDpNewsListByTypeCode(String typeCode)
    {
        String hql = "from DpNews n where n.dpType.typeCode=?";
        return dpNewsDao.query(hql, new Object[]{typeCode});
    }

    /**
     * @param page
     * @param dpNews
     * @return
     * @throws Exception
     */
    @Override
    public Page<DpNews> findNewsListByNews(Page<DpNews> page, DpNews dpNews)
            throws Exception
    {
        String hql = "from DpNews dn where dn.dpType.id!='1000'";
        if (StringUtils.isNotEmpty(dpNews.getNewsTitle()))
        {
            hql = hql + " and dn.newsTitle like'%"
                    + SqlUtil.escapeSQLLike(dpNews.getNewsTitle().trim())
                    + "%'  escape'/'";
        }

     // 资讯类型
        if (dpNews.getDpType() != null && dpNews.getDpType().getId() != null
                && !"".equals(dpNews.getDpType().getId()))
        {
            hql = hql + " and dn.dpType ='" + dpNews.getDpType().getId() + "'";
        }

        page = this.dpNewsDao.queryPage(page, hql, new Object[0]);
        return page;
    }



}
