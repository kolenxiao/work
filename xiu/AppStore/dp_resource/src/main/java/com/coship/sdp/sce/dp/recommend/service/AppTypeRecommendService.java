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
import com.coship.sdp.sce.dp.recommend.entity.AppTypeRecommend;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.utils.Page;


/**
 * 应用分类的推荐服务接口
 * @author WangZhengHui/907632
 * @version [版本号, 2013-7-5]
 * @since [产品/模块版本]
 */
public interface AppTypeRecommendService extends Serializable
{
	
	/**
	 * 批量添加分类推荐
	 * @param list
	 * @throws Exception
	 */
	public void recommendAppTypeInfo(List<AppTypeRecommend> list) throws Exception;
	
    /**
     * 根据取消应用推荐
     * @param appInfo应用实体对象
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
	public void cancleRecommendDpTypeInfo(DpType aDpType);
	
    /**
     * 计算分类下的应用数
     * @param typeId 分类ID
     * @return
     * @throws Exception [违例类型] [违例说明]
     */
	public int getAppTotalByTypeId(String id) throws Exception;
	
	/**
	 * 
	 * @param page
	 * @param queryAppInfo
	 */
	public void searchAppTypeRecomendList(Page<AppTypeRecommend> page,
			DpAppInfo queryAppInfo)throws Exception;
	
	
	public List<AppTypeRecommend> getRecommendList() throws Exception;

	public void updateRecommendSort(AppTypeRecommend ar) throws Exception;

	public AppTypeRecommend queryUniqueAppTypeRecommendById(String id) throws Exception;

	public void recommendAppTypeByIds(List<AppTypeRecommend> tempList) throws Exception;

	public void recommendAppType(AppTypeRecommend appRecommend) throws Exception;

	public void cancleRecommendDpAppInfo(DpAppInfo appInfo);

	public List<AppTypeRecommend> getRecommendListByType(DpType dpType) throws Exception;

	public void updateTypeRecommendSort(AppTypeRecommend ar) throws Exception;

	public AppTypeRecommend queryUniqueAppRecommendById(String id) throws Exception;
}
