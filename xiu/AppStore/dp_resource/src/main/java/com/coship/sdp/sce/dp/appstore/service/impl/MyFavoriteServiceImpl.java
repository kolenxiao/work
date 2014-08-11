/*
 * 文件名称：MyFavoriteServiceImpl.java.
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Oct 10, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.appstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.appstore.dao.MyFavoriteDao;
import com.coship.sdp.sce.dp.appstore.entity.MyFavorite;
import com.coship.sdp.sce.dp.appstore.service.MyFavoriteService;

/**
 * <我的收藏服务层接口>.
 * @author Huangliufei/905735
 * @version [版本号, Oct 10, 2011]
 * @since [产品/模块版本]
 */
@Service("myFavoriteService")
@Transactional
public class MyFavoriteServiceImpl implements MyFavoriteService {

     /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 3022124122232819409L;
    /**
     * dao层接口.
     */
    @Autowired
      private MyFavoriteDao myFavoriteDao;
    /**
     * 添加收藏.
     * @param myFavorite 收藏对象.
     * @throws Exception 异常类
     */
    @Override
    public void saveMyFavorite(MyFavorite myFavorite) throws Exception {
        this.myFavoriteDao.save(myFavorite);
    }

    /**
     * @param myFavorite.
     * * @throws Exception 异常类
     */
    @Override
    public void deleteMyFavorite(MyFavorite myFavorite) throws Exception {
        this.myFavoriteDao.delete(myFavorite);
    }

    /**
     * 根据机顶盒id查询应用id列表.
     * @param uid 用户id
     * @return 列表
     * @throws Exception 异常处理类
     */
    @Override
    public List<String> findByUid(String uid) throws Exception {
        String hql = "from MyFavorite mf where mf.uid = '"
            + uid + "' order by mf.favoriteTime desc";
        // 我的应用列表
        List<MyFavorite> myFavoriteList =
            this.myFavoriteDao.query(hql, new Object[0]);
        List<String> appIdList = new ArrayList<String>();
        for (MyFavorite myFavorite : myFavoriteList) {
            appIdList.add(myFavorite.getAppId());
        }

        return appIdList;
    }
    /**
     * 根据机顶盒id跟应用收藏对象列表.
     * @param uid [用户id]
     * @param appId 应用id
     * @return List<MyFavorite> 列表
     * @throws Exception 异常处理类
     */
    public List<MyFavorite> findMyFavorite(String uid, String appId)
    throws Exception {

        String hql = "from MyFavorite mf where mf.uid ='"
            + uid + "'" + " and mf.appId='" + appId + "'";
        List<MyFavorite> myFavoriteList =
            this.myFavoriteDao.query(hql, new Object[0]);
        return myFavoriteList;
    }

    @Override
    public void deleteMyFavoriteRelationByAppId(DpAppInfo dpAppInfo)
            throws Exception
    {
        String hql = "delete from MyFavorite where appId =?";
        myFavoriteDao.executeUpdate(hql, new Object[]{dpAppInfo.getId()});

    }

}
