/*
 * 文 件 名：DpDownloadInfoServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-9-5
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.download.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;
import com.coship.sdp.sce.dp.atachmentfile.service.AttachmentFileService;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.download.dao.DpDownloadInfoDao;
import com.coship.sdp.sce.dp.download.entity.DpDownloadInfo;
import com.coship.sdp.sce.dp.download.service.DpDownloadInfoService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.DateUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 下载服务实现类.
 * @author Sunwengang/903820
 * @version [版本号, 2011-9-5]
 * @since [产品/模块版本]
 */
@Service("dpDownloadInfoService")
@Transactional
public class DpDownloadInfoServiceImpl implements DpDownloadInfoService
{
    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * className.
     */
    private static final String MODULE = DpDownloadInfoServiceImpl.class
            .getName();

    @Autowired
    private DpDownloadInfoDao dpDownloadInfoDao;

    @Autowired
    private AttachmentFileService attachmentFileService;

    /**
     * 查询语句.
     */
    private static final String QUERY_HQL = "from DpDownloadInfo dn where 1=1";

    /**
     * 新增下载.
     * @param entity 下载实体类
     * @throws Exception [参数说明]
     */
    public void saveDpDownloadInfo(DpDownloadInfo entity) throws Exception
    {
        this.dpDownloadInfoDao.save(entity);
    }

    /**
     * 修改下载信息.
     * @param entity 下载实体类
     * @throws Exception 异常
     */
    public void updateDpDownloadInfo(DpDownloadInfo entity) throws Exception
    {
        this.dpDownloadInfoDao.update(entity);
    }

    /**
     * 删除下载信息
     * @param entity 下载实体
     * @throws Exception 异常
     */
    public void deleteDpDownloadInfo(DpDownloadInfo entity) throws Exception
    {
        this.dpDownloadInfoDao.delete(entity);
    }

    /**
     * 根据ID查询下载信息.
     * @param id id值
     * @throws Exception 异常信息
     * @return DpDownloadInfo 下载对象
     */
    public DpDownloadInfo findDpDownloadInfo(String id) throws Exception
    {
        return this.dpDownloadInfoDao.get(id);
    }

    /**
     * 分页查询下载信息.
     * @param page 分页对象
     * @param hql hql语句
     * @param values 其它参数
     * @throws Exception 异常
     * @return Page<DpDownloadInfo> 分页列表
     */
    public Page<DpDownloadInfo> listDpDownloadInfo(Page<DpDownloadInfo> page,
            String hql, Object[] values) throws Exception
    {
        return this.dpDownloadInfoDao.queryPage(page, hql, values);
    }

    /**
     * @param page 分页对象
     * @param dpDownloadInfo 下载实体
     * @return Page<DpDownloadInfo> 列表
     * @throws Exception 异常
     */
    @Override
    public Page<DpDownloadInfo> listDpDownloadInfo(Page<DpDownloadInfo> page,
            DpDownloadInfo dpDownloadInfo) throws Exception
    {

        Map<String, Object> map = new HashMap<String, Object>();

        page = this.dpDownloadInfoDao.queryPage(page,
                buildHQL(dpDownloadInfo, map), map);
        return page;
    }

    /**
     * 拼装hql语句.
     * @return String 拼装的hql语句
     * @param dpDownloadInfo 下载实体对象
     * @param map 封装的参数
     */

    private String buildHQL(DpDownloadInfo dpDownloadInfo,
            Map<String, Object> map)
    {

        StringBuilder hql = new StringBuilder(QUERY_HQL);
        if (null == map)
        {
            map = new HashMap<String, Object>();
        }
        if (StringUtils.isNotEmpty(dpDownloadInfo.getDownloadName()))
        {
            hql.append(" and dn.downloadName like'%"
                    + SqlUtil.escapeSQLLike(dpDownloadInfo.getDownloadName())
                    + "%' escape'/'");

        }
        // 创建时间段
        if (StringUtils.isNotEmpty(dpDownloadInfo.getCtimeStart())
                || StringUtils.isNotEmpty(dpDownloadInfo.getCtimeEnd()))
        {
            if (StringUtils.isNotEmpty(dpDownloadInfo.getCtimeStart())
                    && StringUtils.isEmpty(dpDownloadInfo.getCtimeEnd()))
            {
                hql.append(" and  dn.ctime >= :beginctime");

                map.put("beginctime",
                        DateUtil.strToDate(dpDownloadInfo.getCtimeStart()
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else if (StringUtils.isEmpty(dpDownloadInfo.getCtimeStart())
                    && StringUtils.isNotEmpty(dpDownloadInfo.getCtimeEnd()))
            {
                hql.append(" and dn.ctime <= :endCtime");
                map.put("endCtime",
                        DateUtil.strToDate(dpDownloadInfo.getCtimeEnd()
                                + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else
            {
                hql.append(" and dn.ctime >= :beginctime"
                        + " and dn.ctime <= :endCtime");
                map.put("beginctime",
                        DateUtil.strToDate(dpDownloadInfo.getCtimeStart()
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
                map.put("endCtime",
                        DateUtil.strToDate(dpDownloadInfo.getCtimeEnd()
                                + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }
        }
        // 下载类型
        if (null != dpDownloadInfo.getDpType()
                && null != dpDownloadInfo.getDpType().getId()
                && !"".equals(dpDownloadInfo.getDpType().getId()))
        {
            hql.append(" and dn.dpType ='" + dpDownloadInfo.getDpType().getId()
                    + "'");
        }
        // 上传者
        if (StringUtils.isNotEmpty(dpDownloadInfo.getDocSource()))
        {
            hql.append(" and dn.docSource like'%"
                    + SqlUtil.escapeSQLLike(dpDownloadInfo.getDocSource()
                            .trim()) + "%' escape'/'");

        }
        hql.append(" order by ctime desc");
        Debug.logVerbose("hql:" + hql.toString(), MODULE);
        return hql.toString();
    }

    /**
     * 根据hql查询下载列表.
     * @param hql hql语句参数
     * @return List<DpDownloadInfo> 下载列表值
     * @throws Exception 异常对象
     */
    @Override
    public List<DpDownloadInfo> findByHQL(String hql) throws Exception
    {
        return this.dpDownloadInfoDao.query(hql, new Object[0]);
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public List<DpDownloadInfo> findAll() throws Exception
    {
        List<DpDownloadInfo> list = findByHQL(QUERY_HQL);
        return list;
    }

    /**
     * 根据属性名判断该对象是否唯一.
     * @param propertyName 属性名称 。
     * @param value 属性对应的值
     * @return TRUE 表示唯一，反之则否
     * @throws Exception 异常
     */
    @Override
    public boolean isUniqueneByPropertyName(String propertyName, String value)
            throws Exception
    {
        String hql = "from DpDownloadInfo dn where dn." + propertyName + "='"
                + value + "'";
        Boolean isUniquen = true;

        List<DpDownloadInfo> downList;
        downList = findByHQL(hql);
        if (null != downList && downList.size() > 0)
        {
            isUniquen = false;
        }
        return isUniquen;

    }

    /**
     * 根据id数组删除下载.
     * @param ids id数组
     * @throws Exception 异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDownloadInfoByIds(String[] ids) throws Exception
    {
        AttachmentFile attachmentFile = null;
        int idleng = ids.length;

        for (int i = 0; i < idleng; i++)
        {
            DpDownloadInfo dpDownloadInfo = findDpDownloadInfo(ids[i].trim());

            if (dpDownloadInfo.getAttachFileId() != null)
            {
                attachmentFile = attachmentFileService.findAttachmentFileById(
                        dpDownloadInfo.getAttachFileId());
            }

            // 删除下载
            deleteDpDownloadInfo(dpDownloadInfo);
            // 删除本地文件
            if (attachmentFile != null)
            {
                String attaFileName = attachmentFile.getFileName();
                // 2011.10.28 edited of 删除本地文件
                FileUtil.deleteFile(Constants.DOWNLOAD_IMG_SAVE_PATH
                        + File.separator + attachmentFile.getFileSaveName());

                attachmentFileService.deleteAttachmentFile(attachmentFile);
                Debug.logInfo(attaFileName + "attachment deleted successfully!");
            }
            Debug.logInfo(dpDownloadInfo.getDownloadName()
                    + "Download deleted successfully!");
        }
    }

    /**
     * 根据分类类型查找.
     * @param dpType 开发文档类型
     * @throws Exception 异常
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DpDownloadInfo> findDownloadInfosByType(DpType dpType)
            throws Exception
    {
        List<DpDownloadInfo> dpDownloadInfos = null;
        if (dpType != null)
        {
            String hql = "from DpDownloadInfo dl where dl.dpType.id=? order by dl.ctime desc";
            dpDownloadInfos = this.dpDownloadInfoDao.createQuery(hql)
                    .setParameter(0, dpType.getId()).list();
        }
        else
        {
            dpDownloadInfos = new ArrayList<DpDownloadInfo>();
        }

        return dpDownloadInfos;
    }

    /**
     * @param dpType
     * @param downloadName
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DpDownloadInfo> findDownloadInfosByTypeAndDownloadName(
            DpType dpType, String downloadName)
    {
        List<DpDownloadInfo> dpDownloadInfos = null;
        if (dpType != null)
        {
            String hql = null;
            if (StringUtils.isEmpty(downloadName))
            {
                // 如果文档名称为空的话，查处所有的列表
                hql = "from DpDownloadInfo dl where dl.dpType.id=?";
                dpDownloadInfos = this.dpDownloadInfoDao.createQuery(hql)
                        .setParameter(0, dpType.getId()).list();
            }
            else
            {
                // 如果文档名称不为空的话，根据文档名称和分类查处所有的列表
                hql = "from DpDownloadInfo dl where dl.dpType.id=? and dl.downloadName like ?";
                dpDownloadInfos = this.dpDownloadInfoDao.createQuery(hql)
                        .setParameter(0, dpType.getId())
                        .setParameter(1, "%" + downloadName + "%").list();
            }
        }
        else
        {
            dpDownloadInfos = new ArrayList<DpDownloadInfo>();
        }

        return dpDownloadInfos;
    }

}
