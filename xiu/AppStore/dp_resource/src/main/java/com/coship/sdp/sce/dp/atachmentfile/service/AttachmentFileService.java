/*
 * 文 件 名：AttachmentFileService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：sunwengang/903820
 * 修改时间：2011-7-25
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.atachmentfile.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFileEntity;

/**
 * <附件服务层接口>.
 * @author sunwengang/903820
 * @version [版本号, 2011-7-25]
 * @since [产品/模块版本]
 */
public interface AttachmentFileService extends Serializable
{
    /**
     * 添加附件.
     * @param entity 附件实体类
     * @throws Exception 异常信息类
     */
    void saveAttachmentFile(AttachmentFile entity) throws Exception;

    /**
     * 删除附件.
     * @param entity 附件实体
     * @throws Exception 异常信息类
     */
    void deleteAttachmentFile(AttachmentFile entity) throws Exception;

    /**
     * 根据id删除附件.
     * 
     * @param AttachmentFileId 附件id
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void deleteAttachmentFileById(String AttachmentFileId) throws Exception;

    /**
     * 修改附件.
     * @param entity 附件实体类
     * @throws Exception 异常信息类
     */
    void updateAttachmentFil(AttachmentFile entity) throws Exception;

    /**
     * 根据ID查找附件.
     * @param id 附件id
     * @return 附件实体
     * @throws Exception 异常处理类
     */
    public AttachmentFile findAttachmentFileById(String id) throws Exception;

    /**
     * 根据附件名称查询附件.
     * @param fileName 文件名称
     * @throws Exception 异常处理类
     * @return AttachmentFile 附件对象
     */
    public AttachmentFile findAttachmentFile(String fileName) throws Exception;

    /**
     * 根据应用id和描述标识查询附件
     * 
     * @param dpAppInfo 应用对象
     * @param fileDesc 描述
     * @return
     * @return AttachmentFile [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public List<AttachmentFile> findAttachmentByAppIdFileDesc(
            DpAppInfo dpAppInfo, String fileDesc) ;
    
    /**
     * 根据应用id和描述标识查询附件
     * 
     * @param dpAppInfo 应用对象
     * @param fileDesc 描述
     * @return
     * @throws Exception [参数说明]
     * @return AttachmentFile [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public Map<String, String> findAttachByAppIdsAndFileDesc(
    		List<AppInfoDetail> appIdList, String fileDesc) throws Exception;

    /**
     * 根据应用id删除相关的附件信息.
     * 
     * @param dpAppInfo 应用对象
     * @throws Exception [参数说明]
     * @return void
     * @exception throws [违例类型] [违例说明]
     */
    public void deleteAttachmentRelationByAppId(DpAppInfo dpAppInfo)
            throws Exception;

    /**
     * 根据应用id\文件描述删除相关的附件信息.
     * 
     * @param dpAppInfo 应用对象
     * @throws Exception [参数说明]
     * @return void
     * @exception throws [违例类型] [违例说明]
     */
    void deleteAttachmentRelationByAppId(DpAppInfo dpAppInfo, String fileDesc)
            throws Exception;

    /**
     * <功能描述>
     * @param id
     * @return [参数说明]
     * @return List<AttachmentFile> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<AttachmentFileEntity> findAttachmentByAppId(String id);
}
