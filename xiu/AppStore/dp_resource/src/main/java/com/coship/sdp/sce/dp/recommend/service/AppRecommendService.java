/*
 * 文件名称：AppRecommendService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.recommend.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommend;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.utils.Page;

/**
 * 应用的推荐服务接口
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
public interface AppRecommendService extends Serializable
{

    /**
     * 根据取消应用推荐
     * @param appInfo应用实体对象
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void cancleRecommendDpAppInfo(DpAppInfo appInfo);
        
    /**
     * <功能描述>
     * @param appInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void recommendAppInfo(AppRecommend appRecommend);

    /**
     * 获取应用的推荐信息
     * @param dpAppInfoList 应用列表数据
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void getAppInfoRecommend(List<DpAppInfo> dpAppInfoList);

    /**
     * 获取推荐的应用 获取限制的条数
     * @return
     * @throws Exception
     */
    List<DpAppInfo> getRecommendAppInfos(int count) throws Exception;

    /**
     * 获取所有的推荐应用信息
     * @return
     * @throws Exception
     */
    List<AppRecommend> getRecommendList() throws Exception;

    /**
     * 获取首页推荐应用信息，获取前四条应用推荐信息
     * @return
     * @throws Exception
     */
    List<AppRecommend> getIndexRecommendList() throws Exception;


    /**
     * <功能描述>
     * @param ids [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void recommendAppInfoByIds(List<AppRecommend> list) throws Exception;

    /**
     * 查询已推荐的应用
     * @param page
     * @param queryAppInfo
     * @param queryDpStaff [参数说明]
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    public void searchRecomentAppList(Page<AppRecommend> page,
            DpAppInfo queryAppInfo) throws Exception;

    /**
     * 根据Id获取应用推荐对象
     *
     * @param id 应用推荐Id
     * @return AppRecommend 返回应用推荐对象
     * @exception throws [违例类型] [违例说明]
     */
    public AppRecommend findAppRecommendById(String id) throws Exception;

    /**
     * 根据Id获取应用推荐对象
     *
     * @param id 应用推荐Id
     * @return AppRecommend 返回应用推荐对象
     * @exception throws [违例类型] [违例说明]
     */
    public AppRecommend queryUniqueAppRecommendById(String id) throws Exception;

    /**
     * 更新应用推荐对象
     *
     * @param appRecommend
     * @return AppRecommend 返回更新后的应用推荐对象
     * @exception throws [违例类型] [违例说明]
     */
    public void updateRecommend(AppRecommend appRecommend) throws Exception;

    /**
     * 更新应用推荐对象
     *
     * @param appRecommend
     * @return AppRecommend 返回更新后的应用推荐对象
     * @exception throws [违例类型] [违例说明]
     */
    public void updateRecommendSort(AppRecommend appRecommend) throws Exception;

    /**
     * 为推荐分类新增c_type_id字段
     * @exception throws [违例类型] [违例说明]
     */
    public void addRecommendType() throws Exception;
    
    /**
     * 查询分类下的应用,并按升序排序
     * @param aDpType
     * @return
     * @throws Exception
     */
    public List<AppRecommend> getRecommendListByType(DpType aDpType) throws Exception;
    
}
