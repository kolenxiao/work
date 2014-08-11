/*
 * 文件名称：AppStoreClientService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-13
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.appstore.service;

import java.io.Serializable;

import com.coship.sdp.sce.dp.appstore.entity.AppStoreClient;
import com.coship.sdp.utils.Page;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-13]
 * @since [产品/模块版本]
 */
public interface AppStoreClientService extends Serializable
{
    /**
     * 保存AppStoreClient信息.
     * @param entity 应用信息对象
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void saveAppStoreClient(AppStoreClient entity) throws Exception;

    /**
     * 更新应用信息.
     * @param entity
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void updateAppStoreClient(AppStoreClient entity) throws Exception;

    /**
     * 删除应用信息和应用的审核记录数据
     * @param entity
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void deleteAppStoreClient(AppStoreClient entity) throws Exception;

    /**
     * 通过ID查找应用信息
     * @param id 应用的id
     * @return
     * @throws Exception [参数说明]
     * @return DpAppInfo [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public AppStoreClient findAppStoreClient(String id) throws Exception;

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
    public Page<AppStoreClient> listAppStoreClient(Page<AppStoreClient> page,
            String hql, Object[] values) throws Exception;

    /**
     * <功能描述>
     * @param versionCode
     * @return [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public boolean isExistVersion(int versionCode,String teminalNum);

    /**
     * <功能描述>
     * @param appStoreClientDbTemp [参数说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void onOrOffAppStoreClient(AppStoreClient appStoreClientDbTemp);

    /**
     * <功能描述>
     * @param appStoreVersion
     * @param osVersion
     * @param clientModel
     * @return [参数说明]
     * @return AppStoreClient [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public AppStoreClient getAppStoreClientUpdateVersion(int appStoreVersion,
            int osVersion, String clientModel);

    /**
     * <功能描述>
     * @return [参数说明]
     * @return AppStoreClient [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public AppStoreClient getOnlineAppStoreClient(String teminalNum);
}
