/*
 * 文 件 名：DpDownloadInfoService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-9-5
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.download.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.download.entity.DpDownloadInfo;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.utils.Page;

/**
 * <一句话功能简述>. <功能详细描述>
 * @author Sunwengang/903820
 * @version [版本号, 2011-9-5]
 * @since [产品/模块版本]
 */
public interface DpDownloadInfoService extends Serializable
{
    /**
     * 新增下载.
     * @throws Exception [参数说明]
     * @param entity 下载实体类
     */
    void saveDpDownloadInfo(DpDownloadInfo entity) throws Exception;

    /**
     * 修改下载信息.
     * @param entity 实体类型
     * @throws Exception [参数说明]
     */
    void updateDpDownloadInfo(DpDownloadInfo entity) throws Exception;

    /**
     * 删除下载信息.
     * @param entity 下载实体
     * @throws Exception [参数说明]
     */
    void deleteDpDownloadInfo(DpDownloadInfo entity) throws Exception;

    /**
     * 根据ID查询下载信息.
     * @param id 下载id值
     * @return DpDownloadInfo 实体对象
     * @throws Exception [参数说明]
     */
    DpDownloadInfo findDpDownloadInfo(String id) throws Exception;

    /**
     * 分页查询下载信息.
     * @param page 分页对象
     * @param hql hql语句
     * @param values 其它参数
     * @throws Exception 异常
     * @return Page<DpDownloadInfo> 分页列表
     */
    Page<DpDownloadInfo> listDpDownloadInfo(Page<DpDownloadInfo> page,
            String hql, Object[] values) throws Exception;

    /**
     * 按照条件分页查询下载信息.
     * @param page 分页对象
     * @param dpDownloadInfo 下载对象
     * @return
     * @throws Exception [参数说明]
     * @return Page<DpDownloadInfo> [返回类型说明]
     */
    Page<DpDownloadInfo> listDpDownloadInfo(Page<DpDownloadInfo> page,
            DpDownloadInfo dpDownloadInfo) throws Exception;

    /**
     *
     * 根据hql查询列表.
     * @param hql hql语句
     * @return List<DpDownloadInfo> 列表
     * @throws Exception 异常
     */
    List<DpDownloadInfo> findByHQL(String hql) throws Exception;

    /**
     *
     * 查询所有的下载信息.
     * @throws Exception [参数说明]
     * @return List<DpDownloadInfo> [返回类型说明]
     */
    List<DpDownloadInfo> findAll() throws Exception;

    /**
     * 根据属性名判断该对象是否唯一.
     *
     * @param propertyName 属性名称
     * @param name 属性值
     * @return 是否唯一
     * @throws Exception 异常信息
     */
    boolean isUniqueneByPropertyName(String propertyName, String name)
            throws Exception;

    /**
     * 根据id组删除下载信息.
     * @param ids 下载对象id组
     * @throws Exception 异常
     */
    void deleteDownloadInfoByIds(String[] ids) throws Exception;

    /**
     * 根据分类类型查找.
     * @param dpType 开发文档类型
     * @throws Exception 异常
     */
    List<DpDownloadInfo> findDownloadInfosByType(DpType dpType)
            throws Exception;

    /**
     * 根据文档分类和获取所有的文档列表
     * @param dpType 文档分类
     * @param  downloadName 文档标题
     * @return 返回某分类下的文档类表
     * @exception throws 异常
     */
    List<DpDownloadInfo> findDownloadInfosByTypeAndDownloadName(
            DpType dpType, String downloadName);
}
