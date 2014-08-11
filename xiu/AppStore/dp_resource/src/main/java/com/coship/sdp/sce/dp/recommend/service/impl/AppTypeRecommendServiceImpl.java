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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.common.AppStatusConstants;
import com.coship.sdp.sce.dp.recommend.dao.AppTypeRecommendDao;
import com.coship.sdp.sce.dp.recommend.entity.AppTypeRecommend;
import com.coship.sdp.sce.dp.recommend.service.AppTypeRecommendService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.DateUtil;
import com.coship.sdp.utils.Page;

/**
 * 应用分类的推荐服务接口
 * 
 * @author WangZhengHui/907632
 * @version [版本号, 2013-7-5]
 * @since [产品/模块版本]
 */
@Service("appTypeRecommendService")
@Transactional
public class AppTypeRecommendServiceImpl implements AppTypeRecommendService {
	@Autowired
	private AppTypeRecommendDao appTypeRecommendDao;

	@Override
	public void recommendAppTypeInfo(List<AppTypeRecommend> list)
			throws Exception {
		for (AppTypeRecommend temp : list) {
			appTypeRecommendDao.save(temp);
		}
	}

	@Override
	public void cancleRecommendDpTypeInfo(DpType aDpType) {
		String sql = "delete from T_APP_TYPE_RECOMMEND where C_TYPE_ID ='"
				+ aDpType.getId() + "'";
		appTypeRecommendDao.executeDelete(sql);
	}

	public int getAppTotalByTypeId(String typeId) throws Exception {
		String hql = "from AppTypeRecommend ar where ar.dpType.id=? and ar.dpAppInfo.appStatus=? and ar.dpAppInfo is not null and ar.dpAppInfo.dpType.id=?";
		int count = appTypeRecommendDao.countHqlResult(hql,
				new Object[] { typeId,AppStatusConstants.APP_STATUS_ONLINE,typeId});
		return count;
	}

	@Override
	public void searchAppTypeRecomendList(Page<AppTypeRecommend> page,
			DpAppInfo queryAppInfo) throws Exception {
		String hql = "from AppTypeRecommend ar where ar.dpAppInfo.appStatus='"
				+ AppStatusConstants.APP_STATUS_ONLINE + "' ";

		Map<String, Object> param = new HashMap<String, Object>();

		String appName = queryAppInfo.getAppName();
		String start = queryAppInfo.getBeginAppInfoCTime();
		String end = queryAppInfo.getEndAppInfoCTime();

		// 应用分类
		if (queryAppInfo.getDpType() != null
				&& StringUtils.isNotEmpty(queryAppInfo.getDpType().getId())) {
			hql += " and ar.dpAppInfo.dpType.id=:dpTypeId";
			param.put("dpTypeId", queryAppInfo.getDpType().getId());
		}

		if (StringUtils.isNotEmpty(appName)) {
			hql += " and ar.dpAppInfo.appName like'%"
					+ SqlUtil.escapeSQLLike(appName) + "%' escape'/'";
		}

		if (StringUtils.isNotEmpty(start) && StringUtils.isEmpty(end)) {
			hql += " and ar.dpAppInfo.updateTime >= :beginAppTime";
			param.put("beginAppTime", DateUtil.strToDate(start + " 00:00:00",
					DateUtil.C_TIME_PATTON_DEFAULT));
		} else if (StringUtils.isEmpty(start) && StringUtils.isNotEmpty(end)) {
			hql += " and ar.dpAppInfo.updateTime <= :endAppTime";
			param.put("endAppTime", DateUtil.strToDate(end + " 23:59:59",
					DateUtil.C_TIME_PATTON_DEFAULT));
		} else if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end)) {
			hql += " and ar.dpAppInfo.updateTime >= :beginAppTime"
					+ " and ar.dpAppInfo.updateTime <= :endAppTime";

			param.put("beginAppTime", DateUtil.strToDate(start + " 00:00:00",
					DateUtil.C_TIME_PATTON_DEFAULT));
			param.put("endAppTime", DateUtil.strToDate(end + " 23:59:59",
					DateUtil.C_TIME_PATTON_DEFAULT));
		}

		hql += " order by ar.sort asc";

		appTypeRecommendDao.queryPage(page, hql, param);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AppTypeRecommend> getRecommendList() throws Exception {
		String hql = "from AppTypeRecommend ar order by ar.sort asc";
		List<AppTypeRecommend> appTypeRecList = appTypeRecommendDao.query(hql,
				new Object[0]);
		return appTypeRecList;
	}

	@Override
	public void updateRecommendSort(AppTypeRecommend appTypeRecommend) throws Exception {
		String sql = "update T_APP_TYPE_RECOMMEND set " + " C_SORT='"
				+ appTypeRecommend.getSort() + "' " + " where c_id='"
				+ appTypeRecommend.getId() + "'";

		appTypeRecommendDao.updateAppTypeRecommend(sql);
	}
	
    @Override
    public AppTypeRecommend queryUniqueAppTypeRecommendById(String id) throws Exception
    {
        String hql = "from AppTypeRecommend ar where ar.id=:id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        List<AppTypeRecommend> list = appTypeRecommendDao.query(hql, map);
        if (CollectionUtils.isEmpty(list))
        {
            return null;
        }
        return list.get(0);

    }
    
    @Override
    public void recommendAppTypeByIds(List<AppTypeRecommend> list) throws Exception
    {
        for (AppTypeRecommend temp : list)
        {
        	appTypeRecommendDao.save(temp);
        }
    }
    
    /**
     * <功能描述>
     * @param appInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void recommendAppType(AppTypeRecommend appTypeRecommend)
    {
    	appTypeRecommendDao.save(appTypeRecommend);
    }
    
    
    /**
     * 根据取消应用推荐分类
     * @param appInfo应用实体对象
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void cancleRecommendDpAppInfo(DpAppInfo appInfo)
    {
        String sql = "delete from T_APP_TYPE_RECOMMEND where C_APP_ID ='"
                + appInfo.getId() + "'";
        appTypeRecommendDao.executeDelete(sql);
    }

	@Override
	public List<AppTypeRecommend> getRecommendListByType(DpType dpType)
			throws Exception {
		if (null == dpType) {
			dpType = new DpType();
		}

		String hql = "from AppTypeRecommend ar where ar.dpType.id=? order by ar.sort asc";
		List<AppTypeRecommend> appRecList = appTypeRecommendDao.query(hql,
				new Object[] { dpType.getId() });
		return appRecList;
	}

	@Override
	public void updateTypeRecommendSort(AppTypeRecommend ar) throws Exception {
        String sql = "update T_APP__TYPE_RECOMMEND set " + " C_SORT='"
        + ar.getSort() + "' " + " where c_id='"
        + ar.getId() + "'";

        appTypeRecommendDao.updateAppTypeRecommend(sql);
		
	}

	@Override
	public AppTypeRecommend queryUniqueAppRecommendById(String id)
			throws Exception {
        String hql = "from AppTypeRecommend ar where ar.id=:id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        List<AppTypeRecommend> list = appTypeRecommendDao.query(hql, map);
        if (CollectionUtils.isEmpty(list))
        {
            return null;
        }
        return list.get(0);
	}
}
