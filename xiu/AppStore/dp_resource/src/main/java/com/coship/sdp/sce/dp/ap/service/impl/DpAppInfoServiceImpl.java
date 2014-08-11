/*
 * 文件名称：DpAppInfoServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-9-5
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.dao.DpAppInfoDao;
import com.coship.sdp.sce.dp.ap.entity.AppStatisticsInfo;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.ap.entity.DpStaff;
import com.coship.sdp.sce.dp.ap.service.DpAppInfoService;
import com.coship.sdp.sce.dp.ap.service.HandAppRelateService;
import com.coship.sdp.sce.dp.audit.dao.DpAuditRecordDao;
import com.coship.sdp.sce.dp.audit.entity.DpAuditRecord;
import com.coship.sdp.sce.dp.common.AppStatusConstants;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.exception.BusinessException;
import com.coship.sdp.sce.dp.recommend.dao.AppRecommendDao;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommend;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.DateUtil;
import com.coship.sdp.utils.Page;

/**
 * 应用管理服务实现类
 *
 * @author FuJian/906126
 * @version [版本号, 2011-9-5]
 * @since [产品/模块版本]
 */
@Service("dpAppInfoService")
@Transactional
public class DpAppInfoServiceImpl implements DpAppInfoService
{

    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * dao对象.
     */
    @Autowired
    private DpAppInfoDao dpAppInfoDao;

    /**
     * 审核dao对象.
     */
    @Autowired
    private DpAuditRecordDao dpAuditRecordDao;

    /**
     * 应用推荐数据访问接口
     */
    @Autowired
    private AppRecommendDao appRecommendDao;
    
    @Autowired
    private HandAppRelateService handAppRelateService;
    

    /**
     * 统计的sql
     */
    private String STATISTICS_SQL = "select tempInfo.cid cid, tempInfo.appName appName, "

            + " tempInfo.typeName typeName, tempInfo.dpStaffName dpStaffName, "

            + " tempInfo.updateTime updateTime, "

            // 下载次数
            + "tempInfo.downloadCount downloadCount,"

            // 评论数
            + " count(appComment.C_ID) commentCount, "

            // 平均评分
            + " avg(appComment.C_SCORE) commentAvgScore"

            + " from  (select appinfo.C_ID  cid ,appinfo.C_APP_NAME appName,"
            + " appinfo.C_TYPE_ID typeName,  appinfo.C_DEVELOPER dpStaffName, "
            + "appinfo.C_UPDATE_TIME updateTime, appinfo.C_APP_STATUS appstatus,count(myapp.C_ID) downloadCount "
            + "from t_dp_app_info appinfo left  join t_my_app myapp on appinfo.C_ID = myapp.C_APP_ID "
            + "group by (appinfo.C_ID) having appstatus='1004'"
            + ")  tempInfo "
            + "left join t_app_comment_info appComment on appComment.C_APP_ID = tempInfo.cid group by(tempInfo.cid)";
    
    
    

    /**
     * 保存应用信息.
     * @param entity 应用信息对象
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void saveAppInfo(DpAppInfo entity) throws Exception
    {
        this.dpAppInfoDao.save(entity);

    }

    /**
     * 更新应用信息.
     * @param entity
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void updateAppInfo(DpAppInfo entity) throws Exception
    {
        this.dpAppInfoDao.update(entity);

    }

    /**
     * 上下架应用服务类
     * @param appInfo 应用信息对象
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void onOrOffLineAppInfo(DpAppInfo appInfo) throws Exception
    {
        if (appInfo != null
                && StringUtils.equals(AppStatusConstants.APP_STATUS_OFFLINE,
                        appInfo.getAppStatus()))
        {
            // 下架的应用取消推荐
            String hql = "from AppRecommend ar where ar.dpAppInfo.id=?";
            AppRecommend appRecommend = appRecommendDao.findUnique(hql,
                    new Object[]
                    { appInfo.getId() });
            if (appRecommend != null)
            {
                appRecommendDao.delete(appRecommend);
            }

            // 删除专题和应用的关系表
            deleteSubjectRelation(appInfo);

			//如果该应用被人工推荐，则将推荐关系删除
			handAppRelateService.deleteByRelateAppPkgName(null, appInfo.getAppFilePackage());
        }
        else if (appInfo != null
                && StringUtils.equals(AppStatusConstants.APP_STATUS_ONLINE,
                        appInfo.getAppStatus()))
        {
            // 如果应用是升级应用则需更新应用推荐、应用专题的关联和上一个应用状态
            String hql = "from DpAppInfo da where da.appFilePackage=? and da.appStatus=? and da.id != ?";
            List<DpAppInfo> dpAppInfoList = dpAppInfoDao.query(hql,
                    new Object[]
                    { appInfo.getAppFilePackage(), AppStatusConstants.APP_STATUS_ONLINE,
                            appInfo.getId() });

            if (dpAppInfoList.size() > 0)
            {
                // 更新上一个版本的状态
                DpAppInfo temp = dpAppInfoList.get(0);
                temp.setAppStatus(AppStatusConstants.APP_STATUS_UPDATE);
                dpAppInfoDao.update(temp);

                // 更新推荐的关联
                String updateRecommentSql = "update T_APP_RECOMMEND ta  set ta.C_APP_ID =? where ta.C_APP_ID=?";
                Session session = appRecommendDao.getExecuteSqlSession();
                SQLQuery sqlQuery = session.createSQLQuery(updateRecommentSql);
                sqlQuery.setParameter(0, appInfo.getId());
                sqlQuery.setParameter(1, temp.getId());
                sqlQuery.executeUpdate();

                // 更新应用专题
                String updateSubjectRelationSql = "update T_SUBJECT_APPINFO_RELATION tr  set tr.C_APP_ID =? where tr.C_APP_ID=?";
                SQLQuery updateSubjectSqlQuery = session
                        .createSQLQuery(updateSubjectRelationSql);
                updateSubjectSqlQuery.setParameter(0, appInfo.getId());
                updateSubjectSqlQuery.setParameter(1, temp.getId());
                updateSubjectSqlQuery.executeUpdate();
            }

        }
        dpAppInfoDao.update(appInfo);
    }

    /**
     * 删除应用和专题关联数据
     * @param appInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private void deleteSubjectRelation(DpAppInfo appInfo)
    {
        String sql = "delete from T_SUBJECT_APPINFO_RELATION where C_APP_ID='"
                + appInfo.getId() + "'";
        dpAppInfoDao.deleteSubjectRelation(sql);
    }

    /**
     * 删除应用信息和应用的审核记录数据
     * @param entity
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void deleteAppInfo(DpAppInfo entity) throws Exception
    {

        // 删除应用信息
        dpAppInfoDao.delete(entity);
    }

    /**
     * 通过ID查找应用信息
     * @param id 应用的id
     * @return
     * @throws Exception [参数说明]
     * @return DpAppInfo [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public DpAppInfo findAppInfo(String id) throws Exception
    {
        return this.dpAppInfoDao.get(id);
    }

    /**
     * 分页查询,获取应用信息列表
     * @param page 分页对象
     * @param hql 查询字符串
     * @param values hql中的参数
     * @return 应用信息分页列表
     * @throws Exception [参数说明]
     * @return Page<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public Page<DpAppInfo> listAppInfo(Page<DpAppInfo> page, String hql,
            Object[] values) throws Exception
    {
        return dpAppInfoDao.queryPage(page, hql, values);
    }

    @Override
    public Page<DpAppInfo> searchAppInfo(Page<DpAppInfo> page,
            DpAppInfo appInfo, String name, int flag) throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "from DpAppInfo da where 1 = 1 ";

        // 应用分类
        if (appInfo.getDpType() != null
        		&& StringUtils.isNotEmpty(appInfo.getDpType().getId()))
        {
        	hql += " and da.dpType.id=:dpTypeId";
        	map.put("dpTypeId", appInfo.getDpType().getId());
        }

        // 应用名称
        if (StringUtils.isNotEmpty(appInfo.getAppName()))
        {
            hql += " and da.appName like'%"
                    + SqlUtil.escapeSQLLike(appInfo.getAppName().trim())
                    + "%' escape'/'";
        }

        // 创建时间段
        if (StringUtils.isNotEmpty(appInfo.getBeginAppInfoCTime())
                || StringUtils.isNotEmpty(appInfo.getEndAppInfoCTime()))
        {
            if (StringUtils.isNotEmpty(appInfo.getBeginAppInfoCTime())
                    && StringUtils.isEmpty(appInfo.getEndAppInfoCTime()))
            {
                hql += " and da.createTime >= :beginAppTime";
                map.put("beginAppTime",
                        DateUtil.strToDate(appInfo.getBeginAppInfoCTime()
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else if (StringUtils.isEmpty(appInfo.getBeginAppInfoCTime())
                    && StringUtils.isNotEmpty(appInfo.getEndAppInfoCTime()))
            {
                hql += " and da.createTime <= :endAppTime";
                map.put("endAppTime",
                        DateUtil.strToDate(appInfo.getEndAppInfoCTime()
                                + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else
            {
                hql += " and da.createTime >= :beginAppTime"
                        + " and da.createTime <= :endAppTime";
                map.put("beginAppTime",
                        DateUtil.strToDate(appInfo.getBeginAppInfoCTime()
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
                map.put("endAppTime",
                        DateUtil.strToDate(appInfo.getEndAppInfoCTime()
                                + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }
        }

        // 状态
        if (StringUtils.isNotEmpty(appInfo.getAppStatus()))
        {
            hql += " and da.appStatus ='" + appInfo.getAppStatus() + "'";
        }

        // 所属SP 还要修改
        if (StringUtils.isNotEmpty(name))
        {
            if (flag == 0)
            {
                // 查全部应用列表
                hql += " and (da.dpStaffId in (select id from DpStaff where userName like '%"
                    + SqlUtil.escapeSQLLike(name)
                    + "%' escape'/') " +
                    		" or da.userId in (select id from User where userName like '%"
                    + SqlUtil.escapeSQLLike(name)
                    + "%' escape'/')) ";
            }
            else if (flag == 1)
            {
             // 查管理员应用列表
                hql += " and da.userId in (select id from User where userName like '%"
                    + SqlUtil.escapeSQLLike(name)
                    + "%' escape'/') ";
            }
            else
            {
                // 查开发者应用列表
                hql += " and da.dpStaffId in (select id from DpStaff where userName like '%"
                    + SqlUtil.escapeSQLLike(name)
                    + "%' escape'/') ";
            }

        }
        hql += " order by da.updateTime desc ";

        page = dpAppInfoDao.queryPage(page, hql, map);
        return page;
    }

    /**
     * 条件分页查询,获取上下架操作的应用信息列表
     * @param page
     * @param appInfo 应用查询条件对象
     * @param dpStaff 应用关联的开发者对象
     * @return
     * @throws Exception [参数说明]
     * @return Page<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public Page<DpAppInfo> searchOnOrOffLineOptAppInfo(Page<DpAppInfo> page,
            DpAppInfo appInfo, DpStaff dpStaff) throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "from DpAppInfo da where 1 = 1 ";

        // 应用分类
        if (appInfo.getDpType() != null
        		&& StringUtils.isNotEmpty(appInfo.getDpType().getId()))
        {
        	hql += " and da.dpType.id=:dpTypeId";
        	map.put("dpTypeId", appInfo.getDpType().getId());
        }

        // 应用名称
        if (StringUtils.isNotEmpty(appInfo.getAppName()))
        {
            hql += " and da.appName like'%"
                    + SqlUtil.escapeSQLLike(appInfo.getAppName().trim())
                    + "%' escape'/'";
        }

        // 创建时间段
        if (StringUtils.isNotEmpty(appInfo.getBeginAppInfoCTime())
                || StringUtils.isNotEmpty(appInfo.getEndAppInfoCTime()))
        {
            if (StringUtils.isNotEmpty(appInfo.getBeginAppInfoCTime())
                    && StringUtils.isEmpty(appInfo.getEndAppInfoCTime()))
            {
                hql += " and da.createTime >= :beginAppTime";
                map.put("beginAppTime",
                        DateUtil.strToDate(appInfo.getBeginAppInfoCTime()
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else if (StringUtils.isEmpty(appInfo.getBeginAppInfoCTime())
                    && StringUtils.isNotEmpty(appInfo.getEndAppInfoCTime()))
            {
                hql += " and da.createTime <= :endAppTime";
                map.put("endAppTime",
                        DateUtil.strToDate(appInfo.getEndAppInfoCTime()
                                + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else
            {
                hql += " and da.createTime >= :beginAppTime"
                        + " and da.createTime <= :endAppTime";
                map.put("beginAppTime",
                        DateUtil.strToDate(appInfo.getBeginAppInfoCTime()
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
                map.put("endAppTime",
                        DateUtil.strToDate(appInfo.getEndAppInfoCTime()
                                + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }
        }

        // 状态
        if (StringUtils.isNotEmpty(appInfo.getAppStatus()))
        {
            hql += " and da.appStatus ='" + appInfo.getAppStatus() + "'";
        }

        hql += " and (da.appStatus ='"
                + AppStatusConstants.APP_STATUS_PASS_AUDITED
                + "' or da.appStatus='" + AppStatusConstants.APP_STATUS_ONLINE
                + "' or da.appStatus='" + AppStatusConstants.APP_STATUS_OFFLINE
                + "')";

        // 所属SP 还要修改
        if (dpStaff != null && StringUtils.isNotEmpty(dpStaff.getUserName()))
        {
            hql += " and (da.dpStaffId in (select id from DpStaff where userName like '%"
                    + SqlUtil.escapeSQLLike(dpStaff.getUserName())
                    + "%' escape'/' ) or da.userId in (select id from User where userName like '%"
                    +SqlUtil.escapeSQLLike(dpStaff.getUserName())
                    +"%' escape'/'))";
        }

        hql += " order by da.sortNum, da.updateTime desc ";

        page = dpAppInfoDao.queryPage(page, hql, map);
        return page;
    }

    /**
     * 审核应用信息
     * @param appInfo 应用信息对象
     * @param auditRecord 审核记录对象
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void auditAppInfo(DpAppInfo appInfo, DpAuditRecord auditRecord)
            throws Exception
    {
        // 更新应用信息
        dpAppInfoDao.update(appInfo);

        // 新增审核记录
        this.dpAuditRecordDao.save(auditRecord);
    }

    /**
     * 获取所有待上架的应用
     * @return
     * @throws Exception [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public List<DpAppInfo> listAllPreOnlineApp() throws Exception
    {
        // 已下架的和已审核通过的的
        String hql = "from DpAppInfo where appStatus in ('"
                + AppStatusConstants.APP_STATUS_PASS_AUDITED + "',"
                + AppStatusConstants.APP_STATUS_OFFLINE + "')";
        List<DpAppInfo> list = this.dpAppInfoDao.query(hql, new Object[0]);
        return list;
    }

    /**
     * 根据应用名字
     * @param entity 应用名称
     * @return
     * @throws Exception [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public List<DpAppInfo> findByAppName(String appName) throws Exception
    {
        String hql = "from DpAppInfo where appName = ?";
        List<DpAppInfo> list = this.dpAppInfoDao.query(hql, new Object[]
        { appName });
        return list;
    }
    
    @Override
    public DpAppInfo findUniqueByPackageName(String packageName)
    {
        String hql = "from DpAppInfo where appFilePackage = ? and appStatus = ?";
        DpAppInfo dpAppInfo = dpAppInfoDao.findUnique(hql, packageName, Constants.APP_STATUS_ONLINE);
        if(null == dpAppInfo){
        	throw new BusinessException("找不到包名为"+packageName+"的应用");
        }
        return dpAppInfo;
    }

    /**
     * 根据hql查询应用信息列表
     * @param hql
     * @return
     * @throws Exception [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public List<DpAppInfo> listByHQL(String hql) throws Exception
    {
        List<DpAppInfo> list = this.dpAppInfoDao.query(hql, new Object[0]);
        return list;
    }
    
    @Override
    public List<DpAppInfo> listByHQL(String hql,List<String> pkgNames) throws Exception
    {
    	Map<String,Object> objMap = new HashMap<String,Object>();
    	objMap.put("PKG_NAMES", pkgNames);
        return dpAppInfoDao.query(hql, objMap);
    }

    @Override
    public List<DpAppInfo> listByHQL(String hql, Object[] objects)
            throws Exception
    {
        List<DpAppInfo> list = this.dpAppInfoDao.query(hql, objects);
        return list;
    }

    @Override
    public DpAppInfo findAppInfo(String appId, String userId) throws Exception
    {
        DpAppInfo dpAppInfo = null;

        String hql = "from DpAppInfo da where da.id = ? and da.dpStaffId =?";
        List<DpAppInfo> list = dpAppInfoDao.query(hql, new Object[]
        { appId, userId });

        if (null != list && !list.isEmpty())
        {

            dpAppInfo = list.get(0);
        }

        return dpAppInfo;
    }

    @Override
    public DpAppInfo uniqueByHQL(String getUpdateappListHql, Object[] param)
            throws Exception
    {
        return dpAppInfoDao.findUnique(getUpdateappListHql, param);
    }

    /**
     * 分页条件查询应用的统计信息
     * @param page 分页数据对象
     * @param appStatQuery 查询条件封装对象
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void searchAppStatisticList(Page<AppStatisticsInfo> page,
            AppStatisticsInfo appStatQuery)
    {

        String orderType = appStatQuery.getOrderType();
        String orderProperty = appStatQuery.getOrderProperty();
        String defaultOrder = "desc";
        String defaultProperty = "updateTime";

        if (StringUtils.isNotEmpty(orderType)
                && StringUtils.isNotEmpty(orderProperty))
        {
            defaultOrder = orderType;
            defaultProperty = orderProperty;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        String appName = appStatQuery.getAppName();
        String staffName = appStatQuery.getDpStaffName();
        String start = appStatQuery.getStartTime();
        String end = appStatQuery.getEndTime();
        String typeId = appStatQuery.getAppTypeId();

        String condition = buildQuerySql(param, appName, staffName, start, end, typeId);

        // 统计的sql
        String sql = getStaticSql(condition) + " order by " + defaultProperty
                + " " + defaultOrder;

        dpAppInfoDao.exeSqlQueryStatistic(page, sql, param);
    }

    /**
     * 构建sql查询条件
     * @param param
     * @param appName
     * @param staffName
     * @param start
     * @param end
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private String buildQuerySql(Map<String, Object> param, String appName,
            String staffName, String start, String end, String typeId)
    {
        String condition = "";
        // 应用名称
        if (StringUtils.isNotEmpty(appName))
        {
            condition = " and appName like'%" + SqlUtil.escapeSQLLike(appName)
                    + "%' escape'/'";
        }

        if (StringUtils.isNotEmpty(typeId))
        {
        	condition += " and appTypeId=:typeId";
        	param.put("typeId", typeId);
        }

        // 所属开发者
        if (StringUtils.isNotEmpty(staffName))
        {
            condition += " and (dpStaffId in (select c_id from t_dp_staff where c_user_name like'%"
                    + SqlUtil.escapeSQLLike(staffName) + "%' escape'/') or "
                    + " userId in (select c_id from t_user where c_user_name like '%"
                    + SqlUtil.escapeSQLLike(staffName) + "%' escape'/'))";
        }

        // 上架时间段
        if (StringUtils.isNotEmpty(start) || StringUtils.isNotEmpty(end))
        {
            if (StringUtils.isNotEmpty(start) && StringUtils.isEmpty(end))
            {
                condition += " and updateTime >= :beginTime";
                param.put("beginTime", DateUtil.strToDate(start + " 00:00:00",
                        DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else if (StringUtils.isEmpty(start) && StringUtils.isNotEmpty(end))
            {
                condition += " and updateTime <= :endAppTime";
                param.put("endAppTime", DateUtil.strToDate(end + " 23:59:59",
                        DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else
            {
                condition += " and updateTime >= :beginAppTime"
                        + " and updateTime <= :endAppTime";
                param.put("beginAppTime", DateUtil.strToDate(start
                        + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
                param.put("endAppTime", DateUtil.strToDate(end + " 23:59:59",
                        DateUtil.C_TIME_PATTON_DEFAULT));
            }
        }

        return condition;
    }

    @Override
    public AppStatisticsInfo searchAppStatisticInfoByAppId(String appId)
    {
        String sql = STATISTICS_SQL + "having cid='" + appId + "'";
        return dpAppInfoDao.exeSqlQueryStatisticUnique(sql);
    }

    @Override
    public void searchRecomentAppList(Page<DpAppInfo> page,
            DpAppInfo queryAppInfo) throws Exception
    {
        String hql = "from AppRecommend ar where ar.dpAppInfo.appStatus='"
            + AppStatusConstants.APP_STATUS_ONLINE + "' ";

        Map<String, Object> param = new HashMap<String, Object>();

        String appName = queryAppInfo.getAppName();
        String start = queryAppInfo.getBeginAppInfoCTime();
        String end = queryAppInfo.getEndAppInfoCTime();

        if (StringUtils.isNotEmpty(appName))
        {
            hql += " and ar.dpAppInfo.appName like'%"
                    + SqlUtil.escapeSQLLike(appName) + "%' escape'/'";
        }

        if (StringUtils.isNotEmpty(start) && StringUtils.isEmpty(end))
        {
            hql += " and ar.dpAppInfo.updateTime >= :beginAppTime";
            param.put("beginAppTime", DateUtil.strToDate(start + " 00:00:00",
                    DateUtil.C_TIME_PATTON_DEFAULT));
        }
        else if (StringUtils.isEmpty(start) && StringUtils.isNotEmpty(end))
        {
            hql += " and ar.dpAppInfo.updateTime <= :endAppTime";
            param.put("endAppTime", DateUtil.strToDate(end + " 23:59:59",
                    DateUtil.C_TIME_PATTON_DEFAULT));
        }
        else if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end))
        {
            hql += " and ar.dpAppInfo.updateTime >= :beginAppTime"
                    + " and ar.dpAppInfo.updateTime <= :endAppTime";

            param.put("beginAppTime", DateUtil.strToDate(start + " 00:00:00",
                    DateUtil.C_TIME_PATTON_DEFAULT));
            param.put("endAppTime", DateUtil.strToDate(end + " 23:59:59",
                    DateUtil.C_TIME_PATTON_DEFAULT));
        }


        dpAppInfoDao.queryForPageBySql(page, hql, param);
    }

    @Override
    public void searchPreRecomentAppList(Page<DpAppInfo> page,
            DpAppInfo queryAppInfo) throws Exception
    {
        String sql = "select appinfo.* from  T_DP_APP_INFO appinfo where appinfo.C_APP_STATUS='"
                + AppStatusConstants.APP_STATUS_ONLINE
                + "' and appinfo.C_ID not in (select ar.C_APP_ID from t_app_recommend ar where ar.C_APP_ID is not null) and "
                + "exists(select 1 from t_attachment_file att where C_DPAPPINFO_ID = appinfo.C_ID and c_file_desc='poster')"
                + "";

        Map<String, Object> param = new HashMap<String, Object>();

        String appName = queryAppInfo.getAppName();
        String start = queryAppInfo.getBeginAppInfoCTime();
        String end = queryAppInfo.getEndAppInfoCTime();

        if (StringUtils.isNotEmpty(appName))
        {
            sql += " and appinfo.C_APP_NAME like'%"
                    + SqlUtil.escapeSQLLike(appName) + "%' escape'/'";
        }

        // 应用分类
        if (queryAppInfo.getDpType() != null
        		&& StringUtils.isNotEmpty(queryAppInfo.getDpType().getId()))
        {
        	sql += " and appinfo.C_TYPE_ID=:dpTypeId";
        	param.put("dpTypeId", queryAppInfo.getDpType().getId());
        }

        if (StringUtils.isNotEmpty(start) && StringUtils.isEmpty(end))
        {
            sql += " and appinfo.c_update_time >= :beginAppTime";
            param.put("beginAppTime", DateUtil.strToDate(start + " 00:00:00",
                    DateUtil.C_TIME_PATTON_DEFAULT));
        }
        else if (StringUtils.isEmpty(start) && StringUtils.isNotEmpty(end))
        {
            sql += " and appinfo.c_update_time <= :endAppTime";
            param.put("endAppTime", DateUtil.strToDate(end + " 23:59:59",
                    DateUtil.C_TIME_PATTON_DEFAULT));
        }
        else if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end))
        {
            sql += " and appinfo.c_update_time >= :beginAppTime"
                    + " and appinfo.c_update_time <= :endAppTime";

            param.put("beginAppTime", DateUtil.strToDate(start + " 00:00:00",
                    DateUtil.C_TIME_PATTON_DEFAULT));
            param.put("endAppTime", DateUtil.strToDate(end + " 23:59:59",
                    DateUtil.C_TIME_PATTON_DEFAULT));
        }

        sql += " order by appinfo.c_update_time desc";

        dpAppInfoDao.queryForPageBySql(page, sql, param);
    }
    
    @Override
    public void searchPreRecomentAppTypeList(Page<DpAppInfo> page,DpAppInfo queryAppInfo) throws Exception
    {
        String sql = "select appinfo.* from  T_DP_APP_INFO appinfo where appinfo.C_APP_STATUS='"
                + AppStatusConstants.APP_STATUS_ONLINE
                + "' and appinfo.C_ID not in (select ar.C_APP_ID from t_app_type_recommend ar where ar.C_APP_ID is not null)"
                + "";

        Map<String, Object> param = new HashMap<String, Object>();

        String appName = queryAppInfo.getAppName();
        String start = queryAppInfo.getBeginAppInfoCTime();
        String end = queryAppInfo.getEndAppInfoCTime();

        if (StringUtils.isNotEmpty(appName))
        {
            sql += " and appinfo.C_APP_NAME like'%"
                    + SqlUtil.escapeSQLLike(appName) + "%' escape'/'";
        }

        // 应用分类
        if (queryAppInfo.getDpType() != null
        		&& StringUtils.isNotEmpty(queryAppInfo.getDpType().getId()))
        {
        	sql += " and appinfo.C_TYPE_ID=:dpTypeId";
        	param.put("dpTypeId", queryAppInfo.getDpType().getId());
        }

        if (StringUtils.isNotEmpty(start) && StringUtils.isEmpty(end))
        {
            sql += " and appinfo.c_update_time >= :beginAppTime";
            param.put("beginAppTime", DateUtil.strToDate(start + " 00:00:00",
                    DateUtil.C_TIME_PATTON_DEFAULT));
        }
        else if (StringUtils.isEmpty(start) && StringUtils.isNotEmpty(end))
        {
            sql += " and appinfo.c_update_time <= :endAppTime";
            param.put("endAppTime", DateUtil.strToDate(end + " 23:59:59",
                    DateUtil.C_TIME_PATTON_DEFAULT));
        }
        else if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end))
        {
            sql += " and appinfo.c_update_time >= :beginAppTime"
                    + " and appinfo.c_update_time <= :endAppTime";

            param.put("beginAppTime", DateUtil.strToDate(start + " 00:00:00",
                    DateUtil.C_TIME_PATTON_DEFAULT));
            param.put("endAppTime", DateUtil.strToDate(end + " 23:59:59",
                    DateUtil.C_TIME_PATTON_DEFAULT));
        }

        sql += " order by appinfo.c_update_time desc";

        dpAppInfoDao.queryForPageBySql(page, sql, param);
    }
    
    private String getStaticSql(String condition)
    {
        String staticSql = "select tempInfo.cid cid, tempInfo.appName appName, "

                + " tempInfo.typeName typeName, tempInfo.dpStaffId dpStaffId, "

                + " tempInfo.updateTime updateTime, "
                + " tempInfo.userId userId, "

                // 下载次数
                + "tempInfo.downloadCount downloadCount,"

                // 评论数
                + " count(appComment.C_ID) commentCount, "

                // 平均评分
                + " avg(appComment.C_SCORE) commentAvgScore, "
                + " tempInfo.appStatus appStatus, "
                + " tempInfo.packageName packageName "
                + " from  (select appinfo.C_ID  cid ,appinfo.C_APP_NAME appName,"
                + " appinfo.C_TYPE_ID typeName, appinfo.C_TYPE_ID appTypeId, appinfo.C_DP_STAFF_ID dpStaffId, "
                + " appinfo.C_UPDATE_TIME updateTime, "
                + " appinfo.C_USER_ID userId, "
                + " appinfo.C_APP_STATUS appstatus,appinfo.C_APP_FILE_PACKAGE packageName,count(myapp.C_ID) downloadCount "
                + "from t_dp_app_info appinfo left  join t_my_app myapp on appinfo.C_ID = myapp.C_APP_ID "
                + "group by (appinfo.C_ID) having (appstatus='1004' or appstatus='1005' or appstatus='1006') "
                + condition
                + ")  tempInfo "
                + "left join t_app_comment_info appComment on appComment.C_APP_ID = tempInfo.cid group by(tempInfo.cid)";

        return staticSql;
    }

    /**
     * @param appId
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public DpAppInfo findAdminAppInfo(String appId, String userId)
            throws Exception
    {
        DpAppInfo dpAppInfo = null;

        String hql = "from DpAppInfo da where da.id = ? and da.userId =?";
        List<DpAppInfo> list = dpAppInfoDao.query(hql, new Object[]
        { appId, userId });

        if (null != list && !list.isEmpty())
        {

            dpAppInfo = list.get(0);
        }

        return dpAppInfo;
    }

    /**
     * @param pacName
     * @throws Exception
     */
    @Override
    public List<DpAppInfo> searchAppListByPacNameAndAppName(String pacName, String appName) throws Exception
    {
        String hql = "from DpAppInfo app where app.appFilePackage=? and app.appName=?";
        return dpAppInfoDao.query(hql, new Object[]{pacName, appName});
    }

    /**
     * @param page
     * @param subjectType
     * @param appInfo
     * @return
     * @throws Exception
     */
    @Override
    public Page<DpAppInfo> searchAppListBySubjectTypeAndApp(
            Page<DpAppInfo> page, AppSubjectType subjectType, DpAppInfo appInfo)
            throws Exception
    {
        String condition = "";

        String linkSql = " inner join t_subject_appinfo_relation ar " +
        		" on appinfo.c_id=ar.c_app_id  and ar.C_SUBJECT_ID='"
                    + subjectType.getId() + "' ";

        if (appInfo != null && appInfo.getAppName() != null)
        {
         // 根据应用名称查询的条件
            condition = " and appinfo.c_app_name like '%"
                + SqlUtil.escapeSQLLike(appInfo.getAppName())
                + "%' escape'/'";
        }

        String orderBy = " order by tempInfo.updateTime desc ";

        // 查询语句拼凑
        String appShopSql = getAppShopSql(condition, linkSql) + orderBy;
        dpAppInfoDao.exeSqlQueryAppInfo(page, appShopSql);

        return page;
    }

    /**
     * @param page
     * @param subjectType
     * @param appInfo
     * @return
     * @throws Exception
     */
    @Override
    public Page<DpAppInfo> searchAppListByAppRecommendAndApp(
            Page<DpAppInfo> page, AppRecommend appRec, DpAppInfo appInfo)
            throws Exception
    {
        String condition = "";

        String linkSql = " inner join t_app_recommend ar on appinfo.c_id=ar.c_app_id ";
        if (appInfo != null && appInfo.getAppName() != null)
        {
         // 根据应用名称查询的条件
            condition = " and appinfo.c_app_name like '%"
                + SqlUtil.escapeSQLLike(appInfo.getAppName())
                + "%' escape'/'";
        }

        String orderBy = " order by tempInfo.updateTime desc ";

        // 查询语句拼凑
        String appShopSql = getAppShopSql(condition, linkSql) + orderBy;

        dpAppInfoDao.exeSqlQueryAppInfo(page, appShopSql);
        return page;
    }

    /**
     * @param page
     * @param flag
     * @param appInfo
     * @return
     * @throws Exception
     */
    @Override
    public Page<DpAppInfo> searchAppList(Page<DpAppInfo> page, int flag,
            DpAppInfo appInfo) throws Exception
    {
        String condition = "";
        String orderBy = "";

        if (appInfo != null && StringUtils.isNotEmpty(appInfo.getAppName()))
        {
            condition = " and appinfo.c_app_name like '%"
                + SqlUtil.escapeSQLLike(appInfo.getAppName())
                + "%' escape'/' ";
        }

        if (flag == 1)
        {
            // 获取免费应用
            condition += " and appinfo.c_price=0 ";
            orderBy = " order by tempInfo.updateTime desc";
        }
        else if (flag == 2)
        {
            // 获取收费应用
            condition += " and appinfo.c_price>0 ";
            orderBy = " order by tempInfo.updateTime desc ";
        }
        else if (flag == 3)
        {
            // 获取最热应用
            orderBy = " order by tempInfo.downloadCount desc  ";
        }
        else if (flag == 4)
        {
            // 获取最新应用
            orderBy = " order by tempInfo.updateTime desc  ";
        }
        else if (flag == 7)
        {
        	// 获取最佳好评的应用
        	orderBy = " order by commentAvgScore desc";
        }

        // 查询语句拼凑
        String appShopSql = getAppShopSql(condition, "") + orderBy;
        dpAppInfoDao.exeSqlQueryAppInfo(page, appShopSql);

        return page;
    }

    /**
     * @param page
     * @param type
     * @param appInfo
     * @return
     * @throws Exception
     */
    @Override
    public Page<DpAppInfo> searchAppListByAppTypeAndApp(Page<DpAppInfo> page,
            DpType type, DpAppInfo appInfo) throws Exception
    {

        String linkSql = " inner join t_dp_type dt on dt.c_id=appinfo.c_type_id "
                + "and dt.c_id='" + type.getId() + "' ";

        String condition = "";

        if (appInfo != null && appInfo.getAppName() != null)
        {
         // 根据应用名称查询的条件
            condition = " and appinfo.c_app_name like '%"
                + SqlUtil.escapeSQLLike(appInfo.getAppName())
                + "%' escape'/' ";
        }

        // 最新应用排序
        String orderBy = " order by tempInfo.updateTime desc  ";

        // 查询语句拼凑
        String appShopSql = getAppShopSql(condition, linkSql) + orderBy;
        dpAppInfoDao.exeSqlQueryAppInfo(page, appShopSql);

        return page;
    }

    /**
     * 拼凑应用商店查询的sql语句
     * @param condition  查询的条件语句
     * @param linkSql    内连接的查询语句
     * @param appInfo
     * @return
     * @throws Exception
     */
    private String getAppShopSql(String condition, String linkSql)
    {
        String appShopSql = "select tempInfo.cid cid, tempInfo.appName appName, "
            + " tempInfo.updateTime updateTime, "
            + " tempInfo.system system, "
            + " tempInfo.version version, "
            + " tempInfo.developer developer, "
            // 下载次数
            + "tempInfo.downloadCount downloadCount,"
            // 评论数
            + " count(appComment.C_ID) commentCount, "
            // 平均评分
            + " avg(appComment.C_SCORE) commentAvgScore, "
            + "tempInfo.price price, "
            + "tempInfo.appFilePackage"
            + " from (select appinfo.C_ID cid ,appinfo.C_APP_NAME appName,"
            + "appinfo.C_APP_FILE_PACKAGE appFilePackage,"
            + "appinfo.C_UPDATE_TIME updateTime, "
            + "appinfo.c_system system , "
            + "appinfo.c_version version , "
            + "appinfo.c_developer developer , "
            + "appinfo.c_price price, "
            + "count(myapp.C_ID) downloadCount, appinfo.C_APP_STATUS appstatus "
            + "from t_dp_app_info appinfo left  join t_my_app myapp on appinfo.C_ID = myapp.C_APP_ID "
            + linkSql
            + " group by (appinfo.C_ID) having (appstatus='1004') "
            // 条件查询on appinfo.c_id=ar.c_app_id )  tempInfo left join t_app_comment_info appComment
            + condition
            + ")  tempInfo "
            + "left join t_app_comment_info appComment on appComment.C_APP_ID = tempInfo.cid group by(tempInfo.cid)";

        return appShopSql;
    }

    /**
     * 根据hql、开始页数、每页最大记录数查询应用信息列表
     * @param hql
     * @return
     * @throws Exception [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public List<DpAppInfo> listMaxResultByHQL(String hql, Object[] objects, int start, int limit)
            throws Exception
    {


       List<DpAppInfo> results = null;
        Page<DpAppInfo> appPage =  new  Page<DpAppInfo>();
        appPage.setCurrentPage(start);
        appPage.setPageSize(limit);
        appPage = dpAppInfoDao.queryPage(appPage, hql, objects);

        if(null != appPage){
            results = appPage.getResultList();
        }

        return results;

    }

    /**
     * 根据应用Id获取下载次数
     * @param appId 应用的Id
     * @return int 下载次数
     * @throws Exception [参数说明]
     */
    @Override
    public int getDownloadCountByAppId(String appId) throws Exception
    {
        String sql = "select count(myapp.C_ID) from t_dp_app_info appinfo"
            + " left join t_my_app myapp on appinfo.C_ID = myapp.C_APP_ID"
            + " where appinfo.c_id='" + appId + "'";

        int count = dpAppInfoDao.getCount(sql);
        return count;
    }

	@Override
	public Map<String, String> getStaffAndTypeByPacName(String packageName)
			throws Exception {
		String hql = "from DpAppInfo info where info.appFilePackage=? order by info.createTime desc";

		List<DpAppInfo> list = dpAppInfoDao.query(hql, new Object[]{packageName});

		DpAppInfo appInfo = null;
		Map<String, String> map = new HashMap<String, String>();
		if (CollectionUtils.isNotEmpty(list))
		{
			appInfo = list.get(0);
			map.put("typeName", appInfo.getDpType().getTypeName());
			map.put("appName", appInfo.getAppName());
		}

		return map;
	}
	
	@Override
	public Map<String, DpAppInfo> searchAppAndGameMap() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select da from DpAppInfo as da where exists (");
		sbf.append("select 1 from DpType as dt where dt.id = da.dpType.id and dt.parentTypeCode in('APP_TYPE','GAME_TYPE'))");
		sbf.append("and da.appStatus != '").append(Constants.APP_STATUS_UPDATE).append("'");
		List<DpAppInfo> list = dpAppInfoDao.query(sbf.toString());

		Map<String, DpAppInfo> map = new HashMap<String, DpAppInfo>();
		if (null != list) {
			for (DpAppInfo e : list) {
				map.put(e.getAppFilePackage(), e);
			}
		}
		return map;
	}

	@Override
	public Map<String, DpAppInfo> searchAppAndGameOnlineMap() {
		Map<String, DpAppInfo> oldMap = this.searchAppAndGameMap();

		Map<String, DpAppInfo> newMap = new HashMap<String, DpAppInfo>();
		for (DpAppInfo e : oldMap.values()) {
			if (StringUtils.equals(e.getAppStatus(), Constants.APP_STATUS_ONLINE)) {
				newMap.put(e.getAppFilePackage(), e);
			}
		}
		return newMap;
	}

}
