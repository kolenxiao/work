/*
 * 文件名称：MyFavoriteServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Oct 10, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.appstore.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.appstore.dao.MyAppDao;
import com.coship.sdp.sce.dp.appstore.entity.MyApp;
import com.coship.sdp.sce.dp.appstore.service.MyAppService;

/**
 * <我的收藏服务层接口>.
 * @author Huangliufei/905735
 * @version [版本号, Oct 10, 2011]
 * @since [产品/模块版本]
 */
@Service("myAppService")
@Transactional
public class MyAppServiceImpl implements MyAppService
{

    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = -4947602403583861772L;

    /**
     * dao对象.
     */
    @Autowired
    private MyAppDao myAppDao;

    /**
     * 保存我的应用.
     * @param myApp 我的应用实体
     * @throws Exception 异常
     */
    @Override
    public void saveMyApp(MyApp myApp) throws Exception
    {
        this.myAppDao.save(myApp);

    }

    /**
     * 删除我的应用.
     * @param myApp 我的应用实体
     * @throws Exception 异常处理类
     */
    @Override
    public void deleteMyApp(MyApp myApp) throws Exception
    {
        this.myAppDao.delete(myApp);

    }

    /**
     * 根据用户账号查询应用列表(应用id).
     * @param uid 用户id
     * @return List<String> 应用id值列表
     * @throws Exception 异常
     */
    @Override
    public List<String> findByUid(String uid) throws Exception
    {
        String hql = "from MyApp ma where ma.uid = '" + uid
                + "' order by ma.addTime desc";
        // 我的应用列表
        List<MyApp> myAppList = this.myAppDao.query(hql, new Object[0]);
        List<String> appPkgNameList = new ArrayList<String>();
        for (MyApp myApp : myAppList)
        {
        	appPkgNameList.add(myApp.getAppPackageName());
        }
        return appPkgNameList;
    }

    /**
     * 根据用户id和应用id查询我的应用列表.
     * @param uid 用户id值
     * @param appId 应用id值
     * @return 我的应用列表
     * @throws Exception 异常处理类
     */
    @Override
    public List<MyApp> findMyApp(String uid, String appId) throws Exception
    {
        String hql = "from MyApp ma where ma.uid ='" + uid + "'"
                + " and ma.appId='" + appId + "'";

        List<MyApp> myAppList = this.myAppDao.query(hql, new Object[0]);
        if (myAppList != null && myAppList.size() > 0)
        {
            return myAppList;
        }
        else
        {
            return null;
        }
    }
    
	@Override
	public List<MyApp> findMyAppByUidAndPkgName(String uid, String packageName) {
		String hql = "from MyApp ma where ma.uid ='" + uid + "'"
				+ " and ma.appPackageName='" + packageName + "'";

		List<MyApp> myAppList = this.myAppDao.query(hql, new Object[0]);
		if (myAppList != null && myAppList.size() > 0) {
			return myAppList;
		} else {
			return null;
		}

	}
    
   

    @Override
    public void deleteMyAppRelationByAppId(DpAppInfo dpAppInfo)
            throws Exception
    {
        String hql = "delete from MyApp where appId =?";
        myAppDao.executeUpdate(hql, new Object[]
        { dpAppInfo.getId() });

    }

    @Override
    public long getDownloadCount(DpAppInfo appInfo)
    {
        String hql = "from MyApp ma where  ma.appId=?";
        return myAppDao.countHqlResult(hql, new Object[]
        { appInfo.getId() });
    }

    @Override
    public long getDownloadCountByPacName(String pacName)
    {
        String sql = "select count(1) from T_MY_APP tm,T_DP_APP_INFO appInfo where tm.C_APP_ID=appInfo.C_ID and appInfo.C_APP_FILE_PACKAGE=?";

        Session session = myAppDao.creatSqlSession();

        SQLQuery sqlQuery = session.createSQLQuery(sql);

        sqlQuery.setParameters(new Object[]
        { pacName }, new Type[]
        { Hibernate.STRING });

        Object count = sqlQuery.uniqueResult();

        if (count != null)
        {
            BigInteger temp = (BigInteger) count;
            return temp.longValue();
        }
        return 0;
    }

}
