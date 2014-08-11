/*
 * 文件名称：AppStoreClientServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-13
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.appstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.appstore.dao.AppStoreClientDao;
import com.coship.sdp.sce.dp.appstore.entity.AppStoreClient;
import com.coship.sdp.sce.dp.appstore.service.AppStoreClientService;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.utils.Page;

/**
 * 应用商店客户端服务类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-13]
 * @since [产品/模块版本]
 */
@Service("appStoreClientService")
@Transactional
public class AppStoreClientServiceImpl implements AppStoreClientService
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private AppStoreClientDao appStoreClientDao;

    /**
     * @param entity
     * @throws Exception
     */
    @Override
    public void saveAppStoreClient(AppStoreClient entity) throws Exception
    {
        appStoreClientDao.save(entity);
    }

    /**
     * @param entity
     * @throws Exception
     */
    @Override
    public void updateAppStoreClient(AppStoreClient entity) throws Exception
    {

    }

    /**
     * @param entity
     * @throws Exception
     */
    @Override
    public void deleteAppStoreClient(AppStoreClient entity) throws Exception
    {
        appStoreClientDao.delete(entity);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public AppStoreClient findAppStoreClient(String id) throws Exception
    {
        return appStoreClientDao.get(id);
    }

    /**
     * @param page
     * @param hql
     * @param values
     * @return
     * @throws Exception
     */
    @Override
    public Page<AppStoreClient> listAppStoreClient(Page<AppStoreClient> page,
            String hql, Object[] values) throws Exception
    {
        page = this.appStoreClientDao.queryPage(page, hql, values);
        return page;
    }

    /**
     * @param versionCode
     * @return
     */
    @Override
    public boolean isExistVersion(int versionCode,String teminalNum)
    {
        boolean ret = false;
        String hql = "from AppStoreClient appClient where appClient.versionCode=? and appClient.teminalNum=?";
        List<AppStoreClient> retList = appStoreClientDao.query(hql,
                new Object[]
                { versionCode ,teminalNum});
        if (retList != null && retList.size() > 0)
        {
            ret = true;
        }
        return ret;
    }

    /**
     * @param appStoreClientDbTemp
     * @return
     */
    @Override
    public void onOrOffAppStoreClient(AppStoreClient appStoreClient)
    {
        String hql = "update AppStoreClient appClient set appClient.status='"
                + Constants.APP_STORE_OFFLINE_STATUS
                + "' where appClient.status="
                + Constants.APP_STORE_ONLINE_STATUS + "and appClient.teminalNum =?";
        // 下架 在线的应用客户端
        appStoreClientDao.executeUpdate(hql,new Object[]{appStoreClient.getTeminalNum()});

		String hql2 = "update AppStoreClient appClient set appClient.status=?"
				+ "where appClient.teminalNum =? and appClient.id = ?";
		appStoreClientDao
				.executeUpdate(
						hql2,
						new Object[] { appStoreClient.getStatus(),
								appStoreClient.getTeminalNum(),
								appStoreClient.getId() });
 }

    /**
     * @param appStoreVersion
     * @param osVersion
     * @return
     */
    @Override
    public AppStoreClient getAppStoreClientUpdateVersion(int appStoreVersion,
            int osVersion,String clientModel)
    {
        String hql = "from AppStoreClient appClient where appClient.status='"
                + Constants.APP_STORE_ONLINE_STATUS
                + "' and appClient.versionCode != ? and appClient.system <= ? and appClient.teminalNum =?";
        AppStoreClient appStoreClient = appStoreClientDao.findUnique(hql,
                new Object[]
                { appStoreVersion, osVersion ,clientModel});
        return appStoreClient;
    }

    @Override
    public AppStoreClient getOnlineAppStoreClient(String teminalNum)
    {
        String hql = "from AppStoreClient appClient where appClient.status='"
                + Constants.APP_STORE_ONLINE_STATUS
                + "' and appClient.status = 1 and appClient.teminalNum=?";
        AppStoreClient appStoreClient = appStoreClientDao.findUnique(hql,
                new Object[] {teminalNum});
        return appStoreClient;
    }

}
