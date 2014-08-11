/*
 * 文 件 名：AttachmentFileService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-8-30
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.attachment.service;

import java.io.File;
import java.util.List;
import java.util.Set;
import com.coship.sdp.sce.dp.attachment.entity.AttachmentFile;
import com.coship.sdp.sce.dp.attachment.exception.AttachNumberOverLimitException;
import com.coship.sdp.sce.dp.attachment.exception.AttachSizeOverLimitException;
import com.coship.sdp.sce.dp.attachment.exception.AttachTotalSizeOverLimitException;
import com.coship.sdp.sce.dp.attachment.exception.AttachTypeNotExistException;
import com.coship.sdp.sce.dp.attachment.exception.AttachmentException;

/**
 * <一句话功能简述> <功能详细描述>
 * @author Sunwengang/903820
 * @version [版本号, 2011-8-30]
 * @since [产品/模块版本]
 */
public interface AttachmentService
{
    /**
     * 添加附件
     * @param attachGroupId 附件组id
     * @param fileName 文件名称
     * @param contentType 上传类型
     * @param file 文件
     * @param attachTypeId
     * @return
     * @throws AttachNumberOverLimitException
     * @throws AttachSizeOverLimitException
     * @throws AttachTypeNotExistException
     * @throws AttachTotalSizeOverLimitException
     * @throws AttachmentException [参数说明]
     * @return AttachmentFile [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public abstract AttachmentFile addAttachmentFile(String attachGroupId,
            String fileName, String contentType, File file, String attachTypeId)
            throws AttachNumberOverLimitException,
            AttachSizeOverLimitException, AttachTypeNotExistException,
            AttachTotalSizeOverLimitException, AttachmentException;
    
    /**
     * 验证附件的合法性
     * @param attachGroupId
     * @param fileName
     * @param file
     * @param attachTypeId
     * @param willAddNum
     * @param willAddSize
     * @param willDeleteNum
     * @param willDeleteSize
     * @throws AttachNumberOverLimitException
     * @throws AttachSizeOverLimitException
     * @throws AttachTypeNotExistException
     * @throws AttachTotalSizeOverLimitException
     * @throws AttachmentException [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public abstract void validateAttachmentFile(long attachGroupId,
            String fileName, File file, String attachTypeName, int willAddNum,
            long willAddSize, int willDeleteNum, long willDeleteSize)
            throws AttachNumberOverLimitException,
            AttachSizeOverLimitException, AttachTypeNotExistException,
            AttachTotalSizeOverLimitException, AttachmentException;
    /**
     * 取消暂存附件
     * @param attachFileId [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public abstract void withdrawAttachmentFile(long attachFileId);

    public abstract void formalAttachmentGroup(Long paramString)
            throws AttachmentException;

    public abstract void changeAttachmentFiles(List<String> paramList1,
            List<String> paramList2) throws AttachmentException;

    public abstract void deleteAttachmentFiles(List<String> paramList)
            throws AttachmentException;

    public abstract void cloneAttachmentGroup(String paramString);

    public abstract AttachmentFile findAttachmentFile(long attachFileId)
            throws AttachmentException;
    
    public abstract AttachmentFile findSimpleAttachmentFile(Long paramString)
    throws AttachmentException;

    public abstract List<AttachmentFile> listAttachmentFile(long attachGroupId);

    public abstract List<AttachmentFile> listFormalAttachmentFile(
            String paramString);
    
    public  abstract List<AttachmentFile> findByHQl(String hql);
}
