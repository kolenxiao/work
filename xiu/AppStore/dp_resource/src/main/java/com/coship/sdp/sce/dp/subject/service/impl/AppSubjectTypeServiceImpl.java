/*
 * 文件名称：AppSubjectTypeServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.subject.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.dao.DpAppInfoDao;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.common.PlanConstants;
import com.coship.sdp.sce.dp.plan.service.PlanItemService;
import com.coship.sdp.sce.dp.subject.dao.AppSubjectTypeDao;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.subject.service.AppSubjectTypeService;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.DateUtil;
import com.coship.sdp.utils.Page;

/**
 * 应用专题服务实现类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Service("AppSubjectTypeService")
@Transactional
public class AppSubjectTypeServiceImpl implements AppSubjectTypeService
{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1505755436663723461L;

    /**
     * 应用信息表的column
     */
    private static final String APP_INFO_COLUMN = "appinfo.C_ID,appinfo.C_APP_DESC,"
            + "appinfo.C_APP_FILE_PACKAGE,appinfo.C_VERSION_CODE,"
            + "appinfo.C_APP_NAME,appinfo.C_APP_NAME_PINYIN,"
            + "appinfo.C_APP_STATUS,appinfo.C_CREATE_TIME,appinfo.C_AVERAGE_SCORE,appinfo.C_DEVELOPER,"
            + "appinfo.C_DP_STAFF_ID,appinfo.C_LANGUAGE,appinfo.C_PRICE,appinfo.C_SYSTEM,"
            + "appinfo.C_UPDATE_TIME,appinfo.C_VERSION,appinfo.C_TYPE_ID,appinfo.C_USER_ID";

    /**
     * 不排序
     */
    public static final String APP_ORDER_ALL = "0";

    /**
     * 最热排序(下载的次数)
     */
    public static final String APP_ORDER_HOT = "1";

    /**
     * 最新排序(更新的时间)
     */
    public static final String APP_ORDER_NEW = "2";

    /**
     * 好评排序(平均评分)
     */
    public static final String APP_ORDER_GOOD = "3";

    @Autowired
    private AppSubjectTypeDao appSubjectTypeDao;

    /**
     * 应用信息数据访问接口
     */
    @Autowired
    private DpAppInfoDao dpAppInfoDao;
    
    @Autowired
    private PlanItemService planItemService;

    /**
     * @param page
     * @param queryAppSubjectType
     */
    @Override
    public void searchAppSubjectType(Page<AppSubjectType> page,
            AppSubjectType queryAppSubjectType)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "from AppSubjectType st where 1 = 1";

		// 专题名称
		if (StringUtils.isNotEmpty(queryAppSubjectType.getSubjectName())) {
			hql += " and st.subjectName like'%"
					+ SqlUtil.escapeSQLLike(queryAppSubjectType
							.getSubjectName().trim()) + "%' escape'/'";
		}
		if (queryAppSubjectType.getVisibleFlag() != 0) {
			hql += " and st.visibleFlag = "
					+ queryAppSubjectType.getVisibleFlag();
		}
		if (StringUtils.isNotBlank(queryAppSubjectType.getProductCode())) {
			hql += " and st.productCode = '"
					+ queryAppSubjectType.getProductCode() + "' ";
		}

        String start = queryAppSubjectType.getBeginSujectCTime();

        String end = queryAppSubjectType.getEndSubjectCTime();

        // 创建时间段
        if (StringUtils.isNotEmpty(start) || StringUtils.isNotEmpty(end))
        {
            if (StringUtils.isNotEmpty(start) && StringUtils.isEmpty(end))
            {
                hql += " and st.createDate >= :beginCTime";
                map.put("beginCTime", DateUtil.strToDate(start + " 00:00:00",
                        DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else if (StringUtils.isEmpty(start) && StringUtils.isNotEmpty(end))
            {
                hql += " and st.createDate <= :endCTime";
                map.put("endCTime", DateUtil.strToDate(end + " 23:59:59",
                        DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else
            {
                hql += " and st.createDate >= :beginCTime"
                        + " and st.createDate <= :endCTime";
                map.put("beginAppTime", DateUtil.strToDate(start + " 00:00:00",
                        DateUtil.C_TIME_PATTON_DEFAULT));
                map.put("endAppTime", DateUtil.strToDate(end + " 23:59:59",
                        DateUtil.C_TIME_PATTON_DEFAULT));
            }
        }
        appSubjectTypeDao.queryPage(page, hql, map);
    }

    /**
     * 根据id获取应用主题
     * @param id 应用主题的id
     * @return [参数说明] 应用主题对象
     * @return AppSubjectType [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public AppSubjectType getAppSubjectTypeById(String id) throws Exception
    {
        return appSubjectTypeDao.get(id);
    }

    @Override
    public void saveAppSubjectType(AppSubjectType appSubjectType)
            throws Exception
    {
        if (StringUtils.isEmpty(appSubjectType.getId()))
        {
            appSubjectTypeDao.save(appSubjectType);
        }
        else
        {
            appSubjectTypeDao.updateNoCascade(appSubjectType);
        }
    }

    @Override
    public void deleteAppSubjectTypeByIds(String[] idsStr) throws Exception
    {
        String ids = buildIdSqlStr(idsStr);

        String sql1 = "delete from T_SUBJECT_APPINFO_RELATION where C_SUBJECT_ID in("
                + ids + ")";

        String sql2 = "delete from T_APP_SUBJECT_TYPE where C_ID in(" + ids
                + ")";
        
        //删除专题与应用的关联关系
        appSubjectTypeDao.executeNativeUpdateSql(sql1);
        
        //删除专题与方案的关联关系
        for (String itemId : idsStr) {
        	planItemService.deletePlanItem(itemId, PlanConstants.ITEM_TYPE_SUBJECT);
		}
        
        //删除专题
        appSubjectTypeDao.executeNativeUpdateSql(sql2);
    }

    @Override
    public void deleteAppSubjectRelationByIds(AppSubjectType appSubjectType,
            String[] idsStr) throws Exception
    {
        List<String> sqlList = new ArrayList<String>();

        for (String id : idsStr)
        {
            String sql1 = "delete from T_SUBJECT_APPINFO_RELATION where C_SUBJECT_ID='"
                    + appSubjectType.getId()
                    + "' and C_APP_ID ='"
                    + StringUtils.trim(id) + "'";
            sqlList.add(sql1);
        }
        appSubjectTypeDao.executeNativeBatch(sqlList);

    }

    /**
     * 拼装id字符串
     * @param idsStr
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private String buildIdSqlStr(String[] idsStr)
    {
        String ids = "";
        int len = idsStr.length;
        if (len == 1)
        {
            ids = "'" + StringUtils.trim(idsStr[0]) + "'";
        }
        else
        {
            for (int i = 0; i < idsStr.length; i++)
            {
                if (i == len - 1)
                {
                    ids += "'" + StringUtils.trim(idsStr[i]) + "'";
                }
                else
                {
                    ids += "'" + StringUtils.trim(idsStr[i]) + "'" + ",";
                }
            }
        }
        return ids;
    }

    /**
     * 向主题中添加应用
     * @param appSubjectType 主题类型对象
     * @param idsStr [参数说明] 应用id
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void addAppToSubjectType(AppSubjectType appSubjectType,
            String[] idsStr) throws Exception
    {
        for (String id : idsStr)
        {
            String sql1 = "insert into T_SUBJECT_APPINFO_RELATION(C_SUBJECT_ID,C_APP_ID) values('"
                    + appSubjectType.getId()
                    + "','"
                    + StringUtils.trim(id)
                    + "')";
            appSubjectTypeDao.executeNativeUpdateSql(sql1);
        }
    }

    /**
     * 根据应用专题分页查询应用列表
     * @param page 分页参数
     * @param appSubjectType 应用专题
     * @return [参数说明]应用列表
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public List<DpAppInfo> queryAppInfoPageBySubjectType(
            Page<AppSubjectType> page, AppSubjectType appSubjectType)
            throws Exception
    {
        String sql = "select dp.* from T_DP_APP_INFO dp, T_SUBJECT_APPINFO_RELATION dr where dp.C_ID=dr.C_APP_ID and dr.C_SUBJECT_ID='"
                + appSubjectType.getId() + "'";

        List<DpAppInfo> list = appSubjectTypeDao.queryForPageBySql(page, sql);
        return list;
    }

    /**
     * 获取未被该专题关联的上架应用分页列表
     * @param page
     * @param appSubjectType
     * @return [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void queryAppInfoPageBySubjectTypeNoRel(
            Page<DpAppInfo> page, AppSubjectType appSubjectType,
            DpAppInfo queryAppInfo) throws Exception
    {
        String sql = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String searchStr = "";
        if (queryAppInfo != null)
        {
            String appName = queryAppInfo.getAppName();
            String start = queryAppInfo.getBeginAppInfoCTime();
            String end = queryAppInfo.getEndAppInfoCTime();

            // 应用名称
            if (StringUtils.isNotEmpty(appName))
            {
                searchStr += " and dp.C_APP_NAME like'%"
                        + SqlUtil.escapeSQLLike(queryAppInfo.getAppName().trim())
                        + "%' escape'/'";
            }

            // 应用分类
            if (queryAppInfo.getDpType() != null
            		&& StringUtils.isNotEmpty(queryAppInfo.getDpType().getId()))
            {
            	searchStr += " and dp.C_TYPE_ID=:dpTypeId";
            	map.put("dpTypeId", queryAppInfo.getDpType().getId());
            }

            if (StringUtils.isNotEmpty(start) && StringUtils.isEmpty(end))
            {
                searchStr += " and dp.c_update_time >=:beginAppTime";
                map.put("beginAppTime",
                        DateUtil.strToDate(start
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else if (StringUtils.isEmpty(start) && StringUtils.isNotEmpty(end))
            {
                searchStr += " and dp.c_update_time <=:endAppTime";
                map.put("endAppTime", DateUtil.strToDate(end
                                + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end))
            {
                searchStr += " and dp.c_update_time >= :beginAppTime "
                        + " and dp.c_update_time <=:endAppTime";
                map.put("beginAppTime", DateUtil.strToDate(start
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
                map.put("endAppTime", DateUtil.strToDate(end
                        + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }

        }

        int appTotal = getAppTotalBySubjectTypeId(appSubjectType.getId());
        if (appTotal > 0)
        {
            sql = "select dp.* from T_DP_APP_INFO dp"
                    + " where dp.C_APP_STATUS='1004' and dp.C_ID not in (select dr.C_APP_ID from T_SUBJECT_APPINFO_RELATION dr"
                    + " where dr.C_SUBJECT_ID='" + appSubjectType.getId()
                    + "')" + searchStr;
        }
        else
        {
            sql = "select dp.* from T_DP_APP_INFO dp where dp.C_APP_STATUS='1004'"
                    + searchStr;
        }
        sql += " order by dp.c_update_time desc ";
        dpAppInfoDao.queryForPageBySql(page, sql, map);
    }

    /**
     * <功能描述>
     * @param queryAppInfo
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private String buildQueryStr(DpAppInfo queryAppInfo)
    {
        String sql = "";
        String appName = queryAppInfo.getAppName();
        String start = queryAppInfo.getBeginAppInfoCTime();
        String end = queryAppInfo.getEndAppInfoCTime();

        // 应用名称
        if (StringUtils.isNotEmpty(appName))
        {
            sql += " and dp.C_APP_NAME like'%"
                    + SqlUtil.escapeSQLLike(queryAppInfo.getAppName().trim())
                    + "%' escape'/'";
        }
        if (StringUtils.isNotEmpty(start) && StringUtils.isEmpty(end))
        {
            sql += " and dp.c_update_time >= '"+DateUtil.strToDate(start + " 00:00:00",
                    DateUtil.C_TIME_PATTON_DEFAULT)+"'";
        }
        else if (StringUtils.isEmpty(start) && StringUtils.isNotEmpty(end))
        {
            sql += " and dp.c_update_time <= '"+DateUtil.strToDate(end + " 23:59:59",
                    DateUtil.C_TIME_PATTON_DEFAULT)+"'";
        }
        else if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end))
        {
            sql += " and dp.c_update_time >= '"
                +DateUtil.strToDate(start + " 00:00:00",DateUtil.C_TIME_PATTON_DEFAULT)+"'"
                    + " and dp.c_update_time <= '"
                +DateUtil.strToDate(end + " 23:59:59",DateUtil.C_TIME_PATTON_DEFAULT)+"'";
        }

        return sql;
    }

    /**
     * 获取未被该专题关联的上架应用数
     * @param page
     * @param appSubjectType
     * @return [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public int queryAppInfoPageBySubjectTypeNoRelCount(
            AppSubjectType appSubjectType, DpAppInfo queryAppInfo)
            throws Exception
    {
        String sql = "";
        String queryStr = "";
        if (queryAppInfo != null)
        {
            queryStr = buildQueryStr(queryAppInfo);
        }

        int appTotal = getAppTotalBySubjectTypeId(appSubjectType.getId());
        if (appTotal > 0)
        {
            sql = "select count(1) from T_DP_APP_INFO dp"
                    + " where dp.C_APP_STATUS='1004' and dp.C_ID not in (select dr.C_APP_ID from T_SUBJECT_APPINFO_RELATION dr"
                    + " where dr.C_SUBJECT_ID='" + appSubjectType.getId()
                    + "')" + queryStr;
        }
        else
        {
            sql = "select count(1) from T_DP_APP_INFO dp where dp.C_APP_STATUS='1004'"
                    + queryStr;
        }
        int count = appSubjectTypeDao.executeNativeQueryCountSql(sql);
        return count;
    }

    @Override
    public void deleteAppSubjectRelationByAppId(DpAppInfo dpAppInfo)
            throws Exception
    {
        String sql = "delete from T_SUBJECT_APPINFO_RELATION where C_APP_ID ='"
                + dpAppInfo.getId() + "'";

        appSubjectTypeDao.executeNativeUpdateSql(sql);

    }

    /**
     * 获取专题列表
     * @param page 分页对象
     * @param getAppSubjectHql 查询HQL
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void listAppSubjectType(Page<AppSubjectType> page,
            String getAppSubjectHql) throws Exception
    {
        appSubjectTypeDao.queryPage(page, getAppSubjectHql);
    }

    /**
     * 获取主题下的应用分页列表
     * @param page 分页数据对象
     * @param subjectId 专题id
     * @param orderByFlag 排序标识
     * @throws Exception
     */
    @Override
    public void getSubjectTypeAppInfoList(Page<DpAppInfo> page,
            String subjectId, String orderByFlag) throws Exception
    {
        String orderProp = "";
        String sql = "";

        sql = "select dp.* from T_DP_APP_INFO dp ,"
                + "T_SUBJECT_APPINFO_RELATION dr where dp.C_APP_STATUS='1004' and dp.C_ID=dr.C_APP_ID and dr.C_SUBJECT_ID='"
                + subjectId + "'";

        if (StringUtils.equals(orderByFlag, APP_ORDER_NEW))
        {
            orderProp = "dp.C_UPDATE_TIME";
            sql += " order by " + orderProp + " desc,dr.C_SORT asc";
        }
        else if (StringUtils.equals(orderByFlag, APP_ORDER_HOT))
        {
            sql = getOrderByHotSql(subjectId);
        }
        else if (StringUtils.equals(orderByFlag, APP_ORDER_GOOD))
        {
            sql = getOrderByGoodSql(subjectId);
        }
        else
        {
            // 所有的专题应用
            sql += " order by dr.C_SORT asc";
        }
        dpAppInfoDao.queryForPageBySql(page, sql);
    }

    /**
     * 根据下载量排序
     * @param subjectId
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private String getOrderByHotSql(String subjectId)
    {
        String hotSql = "select "
                + APP_INFO_COLUMN
                + " from (select "
                + " appinfoTemp.* , count(myapp.C_ID) downloadCount from "
                + " T_DP_APP_INFO appinfoTemp left join T_MY_APP myapp on appinfoTemp.C_ID = myapp.C_APP_ID  "
                + "group by (appinfoTemp.C_ID) having appinfoTemp.C_APP_STATUS='1004') appinfo ,T_SUBJECT_APPINFO_RELATION dr "
                + "where appinfo.C_ID=dr.C_APP_ID and dr.C_SUBJECT_ID='"
                + subjectId + "' order by appinfo.downloadCount desc,dr.C_SORT asc";
        return hotSql;
    }

    /**
     * 根据平均评分排序
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private String getOrderByGoodSql(String subjectId)
    {
        String goodSql = "select "
                + APP_INFO_COLUMN
                + " from (select appinfoTemp.*, avg(appComment.C_SCORE) commentAvgScore"
                + " from T_DP_APP_INFO appinfoTemp left join T_APP_COMMENT_INFO appComment "
                + " on appinfoTemp.C_ID = appComment.C_APP_ID group by (appinfoTemp.C_ID) having "
                + " appinfoTemp.C_APP_STATUS='1004') appinfo ,"
                + " T_SUBJECT_APPINFO_RELATION dr where appinfo.C_ID=dr.C_APP_ID and dr.C_SUBJECT_ID='"
                + subjectId + "' order by appinfo.commentAvgScore desc,dr.C_SORT asc";
        return goodSql;
    }

    /**
     * @param subjectId
     * @throws Exception
     */
    @Override
    public int getAppTotalBySubjectTypeId(String subjectId) throws Exception
    {
        String sql = "select count(*) from t_subject_appinfo_relation where C_SUBJECT_ID='"
            +subjectId+"'";
        int count = appSubjectTypeDao.executeNativeQueryCountSql(sql);

        return count;
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public List<AppSubjectType> getAppSubjectTypeList() throws Exception
    {
        String hql = "from AppSubjectType ";
        List<AppSubjectType> list = appSubjectTypeDao.query(hql, new Object[0]);
        return list;
    }

}
