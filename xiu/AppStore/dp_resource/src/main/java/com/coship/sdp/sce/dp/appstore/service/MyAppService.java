/*
 * 文件名称：MyAppService.java
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
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

/**
 * <我的应用>.
 * @author Huangliufei/905735
 * @version [版本号, Oct 10, 2011]
 * @since [产品/模块版本]
 */
public interface MyAppService extends Serializable
{
    /**
     * 新增应用.
     * @param myApp [我的应用实体] * @throws Exception 异常处理
     */
    void saveMyApp(MyApp myApp) throws Exception;

    /**
     * 删除我的一条应用.
     * @param myApp [我的应用实体] * @throws Exception 异常处理
     */
    void deleteMyApp(MyApp myApp) throws Exception;

    /**
     * 根据机顶盒用户查询应用id列表.
     * @param uid [收藏实体]
     * @return list 应用列表id
     * @throws Exception 异常处理
     */
    List<String> findByUid(String uid) throws Exception;

    /**
     * 根据机顶盒id跟应用id查询我的应用列表. 目的是在添加应用时不能重复添加
     * @param uid [机顶盒id]
     * @param appId [应用id]
     * @return list 应用列表
     * @throws Exception 异常处理
     */
    List<MyApp> findMyApp(String uid, String appId) throws Exception;
    List<MyApp> findMyAppByUidAndPkgName(String userId, String packageName);

    /**
     * 根据应用id删除相关的我的应用关系.
     *
     * @param dpAppInfo 应用对象
     * @throws Exception
     * @return void
     * @exception throws [违例类型] [违例说明]
     */
    public void deleteMyAppRelationByAppId(DpAppInfo dpAppInfo)
            throws Exception;

    /**
     * 获取应用下载的次数
     * @param appInfo
     * @return [参数说明]
     * @return long [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    long getDownloadCount(DpAppInfo appInfo);

    /**
     * <功能描述>
     * @param appId
     * @return [参数说明]
     * @return long [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    long getDownloadCountByPacName(String pacName);

}
