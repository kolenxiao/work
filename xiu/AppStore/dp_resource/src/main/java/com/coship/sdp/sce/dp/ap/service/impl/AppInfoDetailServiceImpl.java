/*
 * 文件名称：AppInfoDetailServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-27
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coship.sdp.sce.dp.ap.dao.AppInfoDetailDao;
import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.ap.service.AppInfoDetailService;
import com.coship.sdp.sce.dp.common.AppStatusConstants;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-27]
 * @since [产品/模块版本]
 */
@Service("AppInfoDetailService")
public class AppInfoDetailServiceImpl implements AppInfoDetailService
{
	
	private static final Logger logger = LoggerFactory.getLogger(AppInfoDetailServiceImpl.class);
	
	
    /**
     * 应用基本信息查询，包含应用分类，应用签名，应用下载次数，应用评论数，应用平均评分
     */
    public static final String BASE_QUERY_APP_SQL = "select appInfo.c_id id,appInfo.c_app_name appName,"
            + " appInfo.c_app_name_pinyin appNamePinyin, appInfo.c_app_desc appDesc,"
            + " appInfo.c_app_file_package appFilePackage, appInfo.c_update_time updateTime,"
            + " appInfo.c_create_time appTime, appInfo.c_price price, appInfo.c_version version,appInfo.c_version_code versionCode,"
            + " appInfo.c_language language, appInfo.c_system system, appInfo.c_developer developer,"
            + " appInfo.c_app_status appStatus, appInfo.c_type_id typeId, appType.c_type_name typeName,"
            + " sign.c_app_cert_id appCertId, t_my_apps.downloadCount downloadCount,"
            + " t_comments.commentNum commentNum, t_comments.avgScore avgScore,appInfo.c_subject_poster subjectPoster,"
            + " appInfo.c_op_mode opMode, appInfo.c_sort_num sortNum, "
            + " appInfo.c_hand_down_count handDownCount, appInfo.c_hand_avg_score handAvgScore, appInfo.c_hand_score_count handScoreCount"
            + " from t_dp_app_info appInfo left join t_dp_type appType on appInfo.c_type_id = appType.c_id"
            + " left join t_app_sign sign on appInfo.C_ID = sign.c_app_id"
            + " left join  (select  count(1) downloadCount, c_app_package_name  from t_my_app group by c_app_package_name)t_my_apps"
            + " on appInfo.c_app_file_package = t_my_apps.c_app_package_name"
            + " left join (select  count(1) commentNum,avg(c_score) avgScore, c_app_package_name  "
            + " from t_app_comment_info group by c_app_package_name) t_comments"
            + " on appInfo.c_app_file_package = t_comments.c_app_package_name";

    public static final String BASE_QUERY_APP_FOR_SUBJECT_SQL = "select appInfo.c_id id,appInfo.c_app_name appName,"
        + " appInfo.c_app_name_pinyin appNamePinyin, appInfo.c_app_desc appDesc,"
        + " appInfo.c_app_file_package appFilePackage, appInfo.c_update_time updateTime,"
        + " appInfo.c_create_time appTime, appInfo.c_price price, appInfo.c_version version,appInfo.c_version_code versionCode,"
        + " appInfo.c_language language, appInfo.c_system system, appInfo.c_developer developer,"
        + " appInfo.c_app_status appStatus, appInfo.c_type_id typeId, appType.c_type_name typeName,"
        + " sign.c_app_cert_id appCertId, t_my_apps.downloadCount downloadCount,"
        + " t_comments.commentNum commentNum, t_comments.avgScore avgScore, appInfo.c_subject_poster subjectPoster,"
        + " appInfo.c_op_mode opMode, appInfo.c_sort_num sortNum,"
        + " appInfo.c_hand_down_count handDownCount, appInfo.c_hand_avg_score handAvgScore, appInfo.c_hand_score_count handScoreCount,"
        +"  dr.C_SUBJECT_ID subjectId,dr.C_SORT sort"
        + " from t_dp_app_info appInfo "
        + " left join t_dp_type appType on appInfo.c_type_id = appType.c_id"
        + " left join t_app_sign sign on appInfo.C_ID = sign.c_app_id"
        + " left join  (select  count(1) downloadCount, c_app_package_name  from t_my_app group by c_app_package_name)t_my_apps"
        + " on appInfo.c_app_file_package = t_my_apps.c_app_package_name"
        + " left join (select  count(1) commentNum,avg(c_score) avgScore, c_app_package_name  "
        + " from t_app_comment_info group by c_app_package_name) t_comments"
        + " on appInfo.c_app_file_package = t_comments.c_app_package_name" +
        " , t_subject_appinfo_relation dr where appInfo.c_id = dr.c_app_id ";

    /**
     * <注释内容>
     */
    @Autowired
    private AppInfoDetailDao appInfoDetailDao;

    /**
     * @param page
     * @param hql
     * @param objects
     * @return
     */
    @Override
    public Page<AppInfoDetail> listAppInfoDetail(Page<AppInfoDetail> page,
            String sql, Object[] values, Type[] types)
    {
        return appInfoDetailDao.queryPageBySql(page, sql, values, types);
    }

    @Override
    public AppInfoDetail findAppInfoDetail(String appId)
    {
        String sql = "select appDetail.* from (" + BASE_QUERY_APP_SQL
                + ")appDetail where appDetail.id='" + appId + "'";
        return appInfoDetailDao.findUniqueByIdSql(sql);
    }

    @SuppressWarnings("deprecation")
    public void getTypeAppInfoList(Page<AppInfoDetail> page, String typeId,
            String orderByFlag, int osVersion)
    {
        String sql = "select appDetail.*,  from ("
                + BASE_QUERY_APP_SQL
                + ")appDetail j where appDetail.appStatus='"
                + AppStatusConstants.APP_STATUS_ONLINE
                + "' and appDetail.typeId=? and CAST(appDetail.system AS SIGNED)<=?";
        if (StringUtils.equals(orderByFlag, Constants.APP_ORDER_HOT))
        {
            sql = sql + " order by appDetail.downloadCount desc ";
        }
        else if (StringUtils.equals(orderByFlag, Constants.APP_ORDER_GOOD))
        {
            sql = sql + " order by appDetail.avgScore desc ";
        }
        else
        {
            sql = sql + " order by appDetail.updateTime desc ";
        }
        appInfoDetailDao.queryPageBySql(page, sql, new Object[]
        { typeId, osVersion }, new Type[]
        { Hibernate.STRING, Hibernate.INTEGER });
    }

    @Override
    public List<AppInfoDetail> getSubjectTypeAppInfoList(String orderByFlag)
    {
        String sql = "select appDetail.* from ("
                + BASE_QUERY_APP_FOR_SUBJECT_SQL
                + ")appDetail where appDetail.appStatus='"
                + AppStatusConstants.APP_STATUS_ONLINE
                + "' ";
        if (StringUtils.equals(orderByFlag, Constants.APP_ORDER_HOT))
        {
            sql += " order by appDetail.downloadCount desc ";
        }
        else if (StringUtils.equals(orderByFlag, Constants.APP_ORDER_GOOD))
        {
            sql += " order by appDetail.avgScore desc ";
        }
        else if (StringUtils.equals(orderByFlag, Constants.APP_ORDER_NEW))
        {
            sql += " order by appDetail.updateTime desc ";
        }
        else
        {
            sql += " order by appDetail.sort asc ";
        }
        return appInfoDetailDao.querySubjectListBySql(sql);
    }

    @Override
    public void getSubjectTypeAppInfoList(Page<AppInfoDetail> page,
            String subjectId, String orderByFlag, int osVersion)
    {

        String sql = "select appDetail.* from ("
                + BASE_QUERY_APP_SQL
                + ")appDetail,t_subject_appinfo_relation dr where appDetail.appStatus='"
                + AppStatusConstants.APP_STATUS_ONLINE
                + "' and appDetail.id = dr.c_app_id "
                + " and dr.c_subject_id =? and CAST(appDetail.system AS SIGNED)<=?";
        if (StringUtils.equals(orderByFlag, Constants.APP_ORDER_HOT))
        {
            sql += " order by appDetail.downloadCount desc ";
        }
        else if (StringUtils.equals(orderByFlag, Constants.APP_ORDER_GOOD))
        {
            sql += " order by appDetail.avgScore desc ";
        }
        else if (StringUtils.equals(orderByFlag, Constants.APP_ORDER_NEW))
        {
            sql += " order by appDetail.updateTime desc ";
        }
        else
        {
            sql += " order by dr.c_sort asc ";
        }
        appInfoDetailDao.queryPageBySql(page, sql, new Object[]
        { subjectId, osVersion }, new Type[]
        { Hibernate.STRING, Hibernate.INTEGER });
    }

    @Override
    public List<AppInfoDetail> getRecommendAppInfos()
    {
        String sql = "select appDetail.* from ("
                + BASE_QUERY_APP_SQL
                + ")appDetail, t_app_recommend ar "
                + " where appDetail.appStatus='"
                + AppStatusConstants.APP_STATUS_ONLINE
                + "' and appDetail.id =ar.c_app_id order by ar.c_sort asc";

        List<AppInfoDetail> appList = appInfoDetailDao.queryListBySql(sql);

        return appList;
    }
    
    @Override
    public List<AppInfoDetail> getTypeRecommendAppInfos()
    {
        String sql = "select appDetail.* from ("
                + BASE_QUERY_APP_SQL
                + ")appDetail, t_app_type_recommend ar "
                + " where appDetail.appStatus='"
                + AppStatusConstants.APP_STATUS_ONLINE
                + "' and appDetail.id =ar.c_app_id order by ar.c_sort asc";

        List<AppInfoDetail> appList = appInfoDetailDao.queryListBySql(sql);

        return appList;
    }

    @Override
    public List<AppInfoDetail> getRecommendAppInfos(int limit, int osVersion)
    {
        Page<AppInfoDetail> page = new Page<AppInfoDetail>();
        page.setPageSize(limit);
        String sql = "select appDetail.* from ("
                + BASE_QUERY_APP_SQL
                + ")appDetail, t_app_recommend ar "
                + " where appDetail.appStatus='"
                + AppStatusConstants.APP_STATUS_ONLINE
                + "' and appDetail.id =ar.c_app_id and CAST(appDetail.system AS SIGNED) <=? order by ar.c_sort asc";
        appInfoDetailDao.queryPageBySql(page, sql, new Object[]
        { osVersion }, new Type[]
        { Hibernate.INTEGER });
        return page.getResultList();
    }

    @Override
    public List<AppInfoDetail> getRankTypeAppInfoList(String rankType)
    {
    	long t1 = System.currentTimeMillis();
        String sql = "select appDetail.* from (" + BASE_QUERY_APP_SQL
                    + ")appDetail where appDetail.appStatus='"
                    + AppStatusConstants.APP_STATUS_ONLINE
                    + "' ";

        if (StringUtils.equals(rankType, Constants.APP_ORDER_HOT))
        {
            sql += " order by appDetail.downloadCount desc, appDetail.sortNum desc, appDetail.updateTime desc";
        }
        else if (StringUtils.equals(rankType, Constants.APP_ORDER_NEW))
        {
        	sql += " order by appDetail.updateTime desc, appDetail.sortNum desc";
        }
        else if (StringUtils.equals(rankType, Constants.APP_ORDER_GOOD))
        {
            sql += " order by appDetail.avgScore desc, appDetail.sortNum desc, appDetail.updateTime desc";
        }
        else if (StringUtils.equals(rankType, Constants.APP_ORDER_FREE))
        {
            sql += " and appDetail.price=0 order by appDetail.sortNum desc, appDetail.updateTime desc";
        }
        else if (StringUtils.equals(rankType, Constants.APP_ORDER_PAY))
        {
            sql += " and appDetail.price >0 order by appDetail.price desc, appDetail.sortNum desc, appDetail.updateTime desc";
        }
        else
        {
            sql += " order by appDetail.sortNum desc, appDetail.updateTime desc ";
        }

        List<AppInfoDetail> appList = appInfoDetailDao.queryListBySql(sql);

        long t2 = System.currentTimeMillis();
        Debug.logVerbose("getRankTypeAppInfoList()初始化数据库查询所执行的时间为：" + (t2-t1) + "ms");
        return appList;

    }


    @Override
    public void getRankTypeAppInfoList(Page<AppInfoDetail> page, String typeId,
            String rankType, int osVersion)
    {
    	  String sql = "";
          if (StringUtils.isEmpty(typeId))
          {
              sql = "select appDetail.* from (" + BASE_QUERY_APP_SQL
                      + ")appDetail where appDetail.appStatus='"
                      + AppStatusConstants.APP_STATUS_ONLINE
                      + "' and CAST(appDetail.system AS SIGNED)<=?";
          }
          else
          {
              sql = "select appDetail.* from ("
                      + BASE_QUERY_APP_SQL
                      + ")appDetail where appDetail.appStatus='"
                      + AppStatusConstants.APP_STATUS_ONLINE
                      + "' and appDetail.typeId=? and CAST(appDetail.system AS SIGNED)<=?";
          }

          if (StringUtils.equals(rankType, Constants.APP_ORDER_HOT))
          {
              sql += " order by appDetail.downloadCount desc, appDetail.sortNum desc ";
          }
          else if (StringUtils.equals(rankType, Constants.APP_ORDER_GOOD))
          {
              sql += " order by appDetail.avgScore desc, appDetail.sortNum desc ";
          }
          else if (StringUtils.equals(rankType, Constants.APP_ORDER_FREE))
          {
              sql += " and appDetail.price=0 order by appDetail.sortNum desc, appDetail.updateTime desc ";
          }
          else if (StringUtils.equals(rankType, Constants.APP_ORDER_PAY))
          {
              sql += " and appDetail.price >0 order by appDetail.price desc, appDetail.sortNum desc ";
          }
          else
          {
              sql += " order by appDetail.sortNum desc, appDetail.updateTime desc ";
          }

          if (StringUtils.isEmpty(typeId))
          {
              appInfoDetailDao.queryPageBySql(page, sql, new Object[]
              { osVersion }, new Type[]
              { Hibernate.INTEGER });
          }
          else
          {
              appInfoDetailDao.queryPageBySql(page, sql, new Object[]
              { typeId, osVersion }, new Type[]
              { Hibernate.STRING, Hibernate.INTEGER });
          }
    }

    @Override
    public AppInfoDetail getUpdateAppBySQL(String getUpdateappListSql,
            String id, String packageName, int osVersion)
    {
        String sql = "select appDetail.* from ("
                + BASE_QUERY_APP_SQL
                + ")appDetail where appDetail.appStatus='"
                + AppStatusConstants.APP_STATUS_ONLINE
                + "' and appDetail.id !=? and appDetail.appFilePackage=? and CAST(appDetail.system AS SIGNED)<=?";
        List<AppInfoDetail> list = appInfoDetailDao.queryListBySql(sql,
                new Object[]
                { id, packageName, osVersion }, new Type[]
                { Hibernate.STRING, Hibernate.STRING, Hibernate.INTEGER });
        if (list != null && list.size() > 0)
        {
            return list.get(0);
        }
        else
        {
            return null;
        }
    }

}
