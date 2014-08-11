/*
 * 文件名称：AppRecommendServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.recommend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.dao.DpAppInfoDao;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.common.AppStatusConstants;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.recommend.dao.AppRecommendDao;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommend;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.DateUtil;
import com.coship.sdp.utils.Page;

/**
 * 应用的推荐服务类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Service("appRecommendService")
@Transactional
public class AppRecommendServiceImpl implements AppRecommendService
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = -4381074201118146373L;

    /**
     * 应用信息表的column
     */
    private static final String APP_INFO_COLUMN = "appinfo.C_ID,appinfo.C_APP_DESC,"
            + "appinfo.C_APP_FILE_PACKAGE,appinfo.C_VERSION_CODE,"
            + "appinfo.C_APP_NAME,appinfo.C_APP_NAME_PINYIN,"
            + "appinfo.C_APP_STATUS,appinfo.C_CREATE_TIME,appinfo.C_AVERAGE_SCORE,appinfo.C_DEVELOPER,"
            + "appinfo.C_DP_STAFF_ID,appinfo.C_LANGUAGE,appinfo.C_PRICE,appinfo.C_SYSTEM,"
            + "appinfo.C_UPDATE_TIME,appinfo.C_VERSION,appinfo.C_TYPE_ID,appinfo.C_USER_ID";

    @Autowired
    private AppRecommendDao appRecommendDao;

    /**
     * 应用信息数据访问
     */
    @Autowired
    private DpAppInfoDao dpAppInfoDao;

    /**
     * 根据取消应用推荐
     * @param appInfo应用实体对象
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void cancleRecommendDpAppInfo(DpAppInfo appInfo)
    {
        String sql = "delete from T_APP_RECOMMEND where C_APP_ID ='"
                + appInfo.getId() + "'";
        appRecommendDao.executeDelete(sql);
    }
    
    /**
     * <功能描述>
     * @param appInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void recommendAppInfo(AppRecommend appRecommend)
    {
        appRecommendDao.save(appRecommend);
    }

    /**
     * 获取应用的推荐信息
     * @param dpAppInfoList 应用列表数据
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void getAppInfoRecommend(List<DpAppInfo> dpAppInfoList)
    {
        for (DpAppInfo appInfo : dpAppInfoList)
        {
            String sql = "select * from T_APP_RECOMMEND ar where ar.C_APP_ID='"
                    + appInfo.getId() + "'";

            List<AppRecommend> recommendList = appRecommendDao
                    .executSqlQuery(sql);
            if (recommendList != null && recommendList.size() > 0)
            {
                appInfo.setAppRecommend(recommendList.get(0));
            }
        }
    }

    @Override
    public List<DpAppInfo> getRecommendAppInfos(int count) throws Exception
    {
        String sql = "select "
                + APP_INFO_COLUMN
                + " from T_DP_APP_INFO appinfo , T_APP_RECOMMEND ar "
                + "where appinfo.C_ID =ar.C_APP_ID and appinfo.C_APP_STATUS ='1004' order by ar.C_SORT asc";
        Page<DpAppInfo> page = new Page<DpAppInfo>();
        page.setCurrentPage(1);
        page.setPageSize(count);
        dpAppInfoDao.queryForPageBySql(page, sql);
        return page.getResultList();
    }

    @Override
    public void recommendAppInfoByIds(List<AppRecommend> list) throws Exception
    {
        for (AppRecommend temp : list)
        {
            appRecommendDao.save(temp);
        }
    }

    @Override
    public void searchRecomentAppList(Page<AppRecommend> page,
            DpAppInfo queryAppInfo) throws Exception
    {
        String hql = "from AppRecommend ar where ar.dpAppInfo.appStatus='"
                + AppStatusConstants.APP_STATUS_ONLINE + "' ";

        Map<String, Object> param = new HashMap<String, Object>();

        String appName = queryAppInfo.getAppName();
        String start = queryAppInfo.getBeginAppInfoCTime();
        String end = queryAppInfo.getEndAppInfoCTime();

        // 应用分类
        if (queryAppInfo.getDpType() != null
        		&& StringUtils.isNotEmpty(queryAppInfo.getDpType().getId()))
        {
        	hql += " and ar.dpAppInfo.dpType.id=:dpTypeId";
        	param.put("dpTypeId", queryAppInfo.getDpType().getId());
        }

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

        hql += " order by ar.sort asc";

        appRecommendDao.queryPage(page, hql, param);
    }

	
    /**
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public AppRecommend findAppRecommendById(String id) throws Exception
    {
        return appRecommendDao.get(id);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public AppRecommend queryUniqueAppRecommendById(String id) throws Exception
    {
        String hql = "from AppRecommend ar where ar.id=:id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        List<AppRecommend> list = appRecommendDao.query(hql, map);
        if (CollectionUtils.isEmpty(list))
        {
            return null;
        }
        return list.get(0);

    }

    /**
     * @param appRecommend
     * @return
     * @throws Exception
     */
    @Override
    public void updateRecommend(AppRecommend appRecommend) throws Exception
    {
        appRecommendDao.update(appRecommend);
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public List<AppRecommend> getRecommendList() throws Exception
    {
        String hql = "from AppRecommend ar order by ar.sort asc";
        List<AppRecommend> appRecList = appRecommendDao.query(hql,
                new Object[0]);
        return appRecList;
    }

    /**
     * @param appRecommend
     * @throws Exception
     */
    @Override
    public void updateRecommendSort(AppRecommend appRecommend) throws Exception
    {
        String sql = "update T_APP_RECOMMEND set " + " C_SORT='"
                + appRecommend.getSort() + "' " + " where c_id='"
                + appRecommend.getId() + "'";

        appRecommendDao.updateAppRecommend(sql);
    }

    /**
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AppRecommend> getIndexRecommendList() throws Exception
    {
        String hql = "from AppRecommend ar order by ar.sort asc";
        Session session = appRecommendDao.getExecuteSqlSession();
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(4);

        return query.list();
    }

	@Override
	public void addRecommendType() throws Exception {
		String hql = "from AppRecommend ar";

		List<AppRecommend> recommList = appRecommendDao.query(hql, new Object[0]);
		for (AppRecommend recommend : recommList)
		{
			DpType type = recommend.getDpAppInfo().getDpType();
			recommend.setDpType(type);
		}
	}
	

	
    public List<AppRecommend> getRecommendListByType(DpType aDpType) throws Exception
    {
    	if(null == aDpType){
    		aDpType = new DpType();
    	}
    	
        String hql = "from AppRecommend ar where ar.dpType.id=? order by ar.sort asc";
        List<AppRecommend> appRecList = appRecommendDao.query(hql,
                new Object[]{aDpType.getId()});
        return appRecList;
    }

}
