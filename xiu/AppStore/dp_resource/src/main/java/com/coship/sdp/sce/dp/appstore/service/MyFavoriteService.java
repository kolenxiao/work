/*
 * 文件名称：MyFavoriteService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Oct 10, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.appstore.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.appstore.entity.MyApp;
import com.coship.sdp.sce.dp.appstore.entity.MyFavorite;



/**
 * <我的收藏>.
 * @author  Huangliufei/905735
 * @version  [版本号, Oct 10, 2011]
 * @since  [产品/模块版本]
 */
public interface  MyFavoriteService extends Serializable {
    /**
     * 新增收藏.
     * @param myFavorite [收藏实体]
     * @throws Exception 异常处理类
     */
    void saveMyFavorite(MyFavorite myFavorite)  throws Exception;
    /**
     * 删除收藏.
     * @param myFavorite [收藏实体]
     * @throws Exception 异常处理类
     */
    void deleteMyFavorite(MyFavorite myFavorite)  throws Exception;
    /**
     * 根据机顶盒Uid查询应用id列表.
     * @param uid [用户id]
     * @return list 应用列表id
     * * @throws Exception 异常处理类
     */
    List<String> findByUid(String uid) throws Exception;
    /**
     * 根据机顶盒id跟应用收藏对象列表.
     * @param uid [收藏实体]
     * @param appId 应用id
     * @return  List<MyFavorite> 我的收藏列表
     * @throws Exception 异常处理类
     */
    List<MyFavorite> findMyFavorite(String uid, String appId) throws Exception;

    /**
     * 根据应用id删除相关的我的收藏信息.
     *
     * @param dpAppInfo  应用对象
     * @throws Exception [参数说明]
     * @return void
     * @exception throws [违例类型] [违例说明]
     */
    public void deleteMyFavoriteRelationByAppId(DpAppInfo dpAppInfo ) throws Exception;



}
