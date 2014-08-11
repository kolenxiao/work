/*
 * 文件名称：DpNewsService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Aug 31, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.news.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.news.entity.DpNews;
import com.coship.sdp.utils.Page;

/**
 * <资讯服务层接口>.
 * @author  Huangliufei/905735
 * @version  [版本号, Aug 31, 2011]
 * @since  [产品/模块版本]
 */
public interface DpNewsService extends Serializable {
    /**
     * 新增资讯.
     * @param dpNews [资讯实体]
     * @throws Exception 异常
     */
    void saveDpNews(DpNews dpNews)  throws Exception;
    /**
     * 修改新增资讯.
     * @param dpNews [资讯实体]
     *  * @throws Exception 异常
     */
    void updateDpNews(DpNews dpNews)  throws Exception;
    /**
     * 删除资讯.
     * @param dpNews [资讯实体]
      * @throws Exception 异常
     */
    void deleteDpNews(DpNews dpNews)  throws Exception;
    /**
     * 批量删除资讯.
     * @param idArray [资讯id集合]
     *  * @throws Exception 异常
     */
    void deleteDpNewsList(String[] idArray)  throws Exception;
    /**
     * 根据id查询资讯.
     * @param id 资讯id
     * @return DpNews [返回类型说明]
     */
    DpNews findDpNews(String id);

    /**
     * 分页查询.
     * @param page 分页对象
     * @param dpNews 资讯对象
     * @return 分页列表
     * @throws Exception 异常
     */
    Page<DpNews> listDpNews(Page<DpNews> page, DpNews dpNews) throws Exception;

    /**
     * 根据hql语句查询.
     * @param hql hql语句
     * @return List<DpNews> [资料列表]
     * * @throws Exception 异常
     */
    List<DpNews> findByHQL(String hql)throws Exception;

    /**
     * 根据属性名判断该对象是否唯一.
     ** @throws Exception 异常
     * @param propertyName 属性名称
     * @param name 属性值
     * @return boolean是否唯一
     */
    boolean isUniqueneByPropertyName(String propertyName, String name)
    throws Exception;

    /**
     *    得到限定的资讯记录条数.
     * @param firstResult 起始记录
     * @param maxResults 最大记录
     * @return 限定资讯条数列表
     */
    List<DpNews> findDpNewsByLimit(int firstResult, int maxResults);

    /**
     *    根据点击次数和时间排序得到限定的资讯记录条数.
     * @param firstResult 起始记录
     * @param maxResults 最大记录
     * @return 限定资讯条数列表
     */
    List<DpNews> findDpNewsByClickTime(int firstResult, int maxResults, int flag);

    /**
     *    根据类型的typeCode查询新闻资讯信息
     * @param typeCode 类型的typeCode标记
     * @return 类型的typeCode的所有资讯内容
     */
    List<DpNews> findDpNewsListByTypeCode(String typeCode);

    /**
     * 分页查询.
     * @param page 分页对象
     * @param dpNews 资讯对象
     * @return 分页列表
     * @throws Exception 异常
     */
    Page<DpNews> findNewsListByNews(Page<DpNews> page, DpNews dpNews) throws Exception;

}
