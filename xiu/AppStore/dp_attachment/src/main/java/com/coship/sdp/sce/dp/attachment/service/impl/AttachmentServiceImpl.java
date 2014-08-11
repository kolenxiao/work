/*
 * 文 件 名：AttachmentFileServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-8-30
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.attachment.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.vfs.FileContent;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.Selectors;
import org.apache.commons.vfs.VFS;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.attachment.common.AttachmentConstants;
import com.coship.sdp.sce.dp.attachment.config.AttachmentConfig;
import com.coship.sdp.sce.dp.attachment.dao.AttachmentFileDao;
import com.coship.sdp.sce.dp.attachment.dao.AttachmentGroupDao;
import com.coship.sdp.sce.dp.attachment.dao.AttachmentTypeDao;
import com.coship.sdp.sce.dp.attachment.entity.AttachmentFile;
import com.coship.sdp.sce.dp.attachment.entity.AttachmentGroup;
import com.coship.sdp.sce.dp.attachment.entity.AttachmentType;
import com.coship.sdp.sce.dp.attachment.exception.AttachIsFormalException;
import com.coship.sdp.sce.dp.attachment.exception.AttachNumberOverLimitException;
import com.coship.sdp.sce.dp.attachment.exception.AttachSizeOverLimitException;
import com.coship.sdp.sce.dp.attachment.exception.AttachTotalSizeOverLimitException;
import com.coship.sdp.sce.dp.attachment.exception.AttachTypeNotExistException;
import com.coship.sdp.sce.dp.attachment.exception.AttachmentException;
import com.coship.sdp.sce.dp.attachment.service.AttachmentService;

/**
 * <一句话功能简述> <功能详细描述>
 * @author Sunwengang/903820
 * @version [版本号, 2011-8-30]
 * @since [产品/模块版本]
 */
@Service("attachmentService")
@Transactional
public class AttachmentServiceImpl implements AttachmentService
{
    private static final Logger logger = Logger
            .getLogger(AttachmentServiceImpl.class);

    @Autowired
    private AttachmentFileDao<AttachmentFile, Long> attachmentFileDao;

    @Autowired
    private AttachmentGroupDao<AttachmentGroup, Long> attachmentGroupDao;

    @Autowired
    private AttachmentTypeDao<AttachmentType, Long> attachmentTypeDao;

    /**
     * @param attachGroupId
     * @param fileName
     * @param contentType
     * @param file
     * @param attachTypeName
     * @return
     * @throws AttachNumberOverLimitException
     * @throws AttachSizeOverLimitException
     * @throws AttachTypeNotExistException
     * @throws AttachTotalSizeOverLimitException
     * @throws AttachmentException
     */
    public AttachmentFile addAttachmentFile(String attachGroupId,
            String fileName, String contentType, File file,
            String attachTypeName) throws AttachNumberOverLimitException,
            AttachSizeOverLimitException, AttachTypeNotExistException,
            AttachTotalSizeOverLimitException, AttachmentException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("进入暂存附件方法，参数attachGroupId=" + attachGroupId
                    + ",fileName=" + fileName + ",fileType=" + contentType
                    + ",file=" + file + ",attachTypeName=" + attachTypeName);
        }

        if ((StringUtils.isEmpty(fileName))
                || (StringUtils.isEmpty(contentType)) || (null == file))
        {
            logger.error("暂存附件时，传入的参数为空");
            throw new IllegalArgumentException("暂存附件时，传入的参数为空");
        }

        if (file.length() == 0L)
        {
            logger.error("不允许暂存0字节的文件！");
            throw new AttachmentException("不允许暂存0字节的文件！");
        }

        if (!(file.exists()))
        {
            logger.error("暂存附件时，暂存的文件不存在");
            throw new IllegalArgumentException("暂存的文件不存在");
        }

        String uploadPath = AttachmentConfig.getInstance().getUploadPath();

        if ((uploadPath == null) || (uploadPath.trim().length() == 0))
        {
            throw new IllegalArgumentException(
                    "解析配置文件错误，无法从配置文件中取到uploadPath值。");
        }
        AttachmentGroup attachmentGroup =null;
        if (StringUtils.isEmpty(attachGroupId))
        {
            attachmentGroup = new AttachmentGroup();
            attachmentGroup
                    .setAttachGroupStatus(AttachmentConstants.ATTACH_STATUS_TEMP);
            if (null != attachTypeName)
            {
                attachmentGroup.setAttachmentType(this.attachmentTypeDao
                        .findAttachmentTypeByTypeName(attachTypeName).get(0));
            }
           /* Set<AttachmentFile> set = new HashSet<AttachmentFile>();
            set.add(attachmentFile);
            attachmentGroup.setAttachmentFileSet(set);*/
            this.attachmentGroupDao.save(attachmentGroup);
        }
        else
        {
             attachmentGroup = this.attachmentGroupDao.get(Long
                    .valueOf(attachGroupId));
//            attachmentGroup.getAttachmentFileSet().add(attachmentFile);
            this.attachmentGroupDao.update(attachmentGroup);
        }
        
        
        Date today = new Date();
        String datePath = new SimpleDateFormat("yyMMdd").format(today);
         UUID uuid = UUID.randomUUID();
         //添加上uuid值，是为了每个文件名都不重复。
         datePath =datePath+ uuid.toString() ;
        // 获取文件名后缀
        String Suffix = fileName.substring(fileName.lastIndexOf("."));

        AttachmentFile attachmentFile = new AttachmentFile();
        attachmentFile.setFileName(fileName);
        attachmentFile.setFileSize((int) file.length());
        attachmentFile.setFileType(contentType);
        attachmentFile
                .setAttachFileStatus(AttachmentConstants.ATTACH_STATUS_TEMP);
        attachmentFile.setFileSaveName(datePath + Suffix);
        attachmentFile.setCreateDate(new Date());
        attachmentFile.setAttachmentGroup(attachmentGroup);
        // 保存附件信息
        this.attachmentFileDao.save(attachmentFile);

     

        String fileUri = uploadPath + attachmentFile.getFileSaveName();
        FileObject vsfFileObject = null;
        FileObject localFileObject = null;
        try
        {
            FileSystemManager vfsManager = VFS.getManager();
            vsfFileObject = vfsManager.resolveFile(fileUri);
            if (!(vsfFileObject.exists()))
            {
                vsfFileObject.createFile();
            }

            localFileObject = vfsManager.resolveFile(file.getAbsolutePath());
            vsfFileObject.copyFrom(localFileObject, Selectors.SELECT_ALL);
        }
        catch (Exception e)
        {
        }
        finally
        {
            if (vsfFileObject != null)
            {
                try
                {
                    vsfFileObject.close();
                }
                catch (FileSystemException e)
                {
                    logger.error("关闭文件服务器文件失败，error:" + e);
                }
            }
            if (localFileObject != null)
            {
                try
                {
                    localFileObject.close();
                }
                catch (IOException e)
                {
                    logger.error("关闭文件失败，error:" + e);
                }
            }
        }

        logger.debug("离开暂存附件方法，返回值=" + attachmentFile);
        return attachmentFile;
    }
    /**
     * 校验上传附件
     * @param attachGroupId
     * @param fileName
     * @param file
     * @param attachTypeName 类型名称
     * @param willAddNum 可上传数量
     * @param willAddSize 可上传附件大小
     * @param willDeleteNum 将要删除的个数
     * @param willDeleteSize 将要删除的大小
     * @throws AttachNumberOverLimitException
     * @throws AttachSizeOverLimitException
     * @throws AttachTypeNotExistException
     * @throws AttachTotalSizeOverLimitException
     * @throws AttachmentException
     */
    public void validateAttachmentFile(long attachGroupId, String fileName,
            File file, String attachTypeName, int willAddNum, long willAddSize,
            int willDeleteNum, long willDeleteSize)
            throws AttachNumberOverLimitException,
            AttachSizeOverLimitException, AttachTypeNotExistException,
            AttachTotalSizeOverLimitException, AttachmentException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("进入校验附件方法，参数attachGroupId=" + attachGroupId
                    + ",fileName=" + fileName + ",file=" + file
                    + ",attachTypeName=" + attachTypeName);
        }

        if ((StringUtils.isEmpty(fileName)) || (null == file))
        {
            logger.error("校验附件，传入的参数为空");
            throw new IllegalArgumentException("校验附件时，传入的参数为空");
        }

        if (file.length() == 0L)
        {
            logger.error("不允许上传0字节的文件！");
            throw new AttachmentException("不允许上传0字节的文件");
        }

        if (!(file.exists()))
        {
            logger.error("校验附件时，文件不存在");
            throw new IllegalArgumentException("校验附件时，文件不存在");
        }
        //获取文件后缀名称
        String fileSuffixName = StringUtils.substringAfterLast(fileName, ".");

        if (StringUtils.isEmpty(attachTypeName))
        {
            AttachmentType attachType = this.attachmentTypeDao
                    .findAttachmentTypeByTypeName(attachTypeName).get(0);

            if (null == attachType)
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug("不存在对应的附件类型，对上传的附件不做类型的检查，attachTypeName="
                            + attachTypeName);
                }
                return;
            }

            if (fileName.length() > 255)
            {
                logger.error("文件名总长度(含后缀名)超出数据库库最大限制（256个字符）。");
                throw new AttachmentException("文件名总长度(含后缀名)超出数据库库最大限制(256个字符)");
            }
            //文件前缀名
            String filePrefixName = "";
            if (fileName.indexOf(".") != -1)
                filePrefixName = fileName.substring(0, fileName.indexOf("."));
            else
            {
                filePrefixName = fileName;
            }
            // if (filePrefixName.length() > attachType.getMaxFileNameLength())
            // {
            // logger.error("文件名超出" + attachType.getMaxFileNameLength()
            // + "个字符。");
            // throw new AttachmentException("文件名长度超出"
            // + attachType.getMaxFileNameLength()
            // + "个字符限制(1个中文字相当于2个字符)");
            // }

            if (file.length() > attachType.getSingleSizeLimit())
            {
                logger.error("附件的大小超过了上限");
                throw new AttachSizeOverLimitException("附件的大小超过了上限(M):"
                        + attachType.getSingleSizeLimit());
            }

            if ((null != attachType.getFileSuffixLimit())
                    && (attachType.getFileSuffixLimit().indexOf(
                            fileSuffixName.toLowerCase()) < 0))
            {
                logger.error("附件的类型不在限制类型范围内");
                throw new AttachTypeNotExistException("附件的类型不在限制类型范围内");
            }

            if (attachGroupId > 0)
            {
                if (file.length() + willAddSize - willDeleteSize > attachType
                        .getAttachSizeLimit())
                {
                    logger.error("附件组总的大小超过了上限");
                    throw new AttachTotalSizeOverLimitException(
                            "附件组总的大小超过了上限(M):"
                                    + attachType.getAttachSizeLimit());
                }

                if (1 + willAddNum - willDeleteNum > attachType
                        .getAttachCountLimit())
                {
                    logger.error("附件组总数超过了上限");
                    throw new AttachNumberOverLimitException("附件组总数超过了上限:"
                            + attachType.getAttachCountLimit());
                }
            }
        }

        if (logger.isDebugEnabled())
        {
            logger.debug("离开校验附件方法");
        }
    }

    public AttachmentType findAttachmentType(long typeId)
            throws AttachmentException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("进入findAttachmentType(String), 输入参数[" + typeId + "]");
        }
        if (0 > typeId)
        {
            logger.error("参数为空");
            throw new AttachmentException("参数为空");
        }

        AttachmentType type = this.attachmentTypeDao.get(typeId);
        return type;
    }
    /**
     * 
     * <生效附件>
     * @param attachmentFile
     * @throws AttachmentException [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
  /*  private void formalAttachmentFile(AttachmentFile attachmentFile)
    throws AttachmentException
  {
    if (attachmentFile == null) {
      throw new AttachmentException("生效附件时参数为空");
    }

    AttachmentGroup attachGroup = this.attachmentGroupDao.get(attachmentFile.getAttachmentGroup().getId());

    if (null == attachGroup) {
      logger.error("生效附件时，对应的附件组记录不存在，attachGroupId=" + attachmentFile.getAttachmentGroup().getId());

      return;
    }

    boolean bSync = false;
    Long attachTypeId = attachGroup.getAttachmentType().getId();
    if (attachTypeId>0) {
      AttachmentType attachmentType = this.attachmentTypeDao.get(attachTypeId);

      if (attachmentType != null) {
        bSync = attachmentType.isSync();
      }
    }
    formalAttachmentFile(attachmentFile, bSync);
   }*/

    /**
     * 
     * <生效附件，把附件转换成正式附件>
     * @param attachmentFile
     * @param bSync
     * @throws AttachmentException [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private void formalAttachmentFile(AttachmentFile attachmentFile,
            boolean bSync) throws AttachmentException
    {
        String uploadPath = AttachmentConfig.getInstance().getUploadPath();
        String syncPath = AttachmentConfig.getInstance().getSyncPath();

        String fileSaveName = attachmentFile.getFileSaveName();

        String uploadFileUri = uploadPath + fileSaveName;
        String syncFileUri = syncPath + fileSaveName;
        FileObject uploadFileObject = null;
        FileObject syncFileObject = null;
        try
        {
            if (bSync)
            {
                FileSystemManager vfsManager = VFS.getManager();
                uploadFileObject = vfsManager.resolveFile(uploadPath
                        + attachmentFile.getFileSaveName());

                syncFileObject = vfsManager.resolveFile(syncFileUri);
                if (!(syncFileObject.exists()))
                {
                    syncFileObject.createFile();
                }
                syncFileObject.copyFrom(uploadFileObject, Selectors.SELECT_ALL);
            }
        }
        catch (Exception e)
        {

        }
        finally
        {
            if (uploadFileObject != null)
            {
                try
                {
                    uploadFileObject.close();
                }
                catch (FileSystemException e)
                {
                    logger.error("关闭文件服务器文件失败，error:" + e);
                }
            }
            if (syncFileObject != null)
            {
                try
                {
                    syncFileObject.close();
                }
                catch (IOException e)
                {
                    logger.error("关闭文件失败，error:" + e);
                }
            }
        }
        //把附件状态设置为“正式”
        attachmentFile
                .setAttachFileStatus(AttachmentConstants.ATTACH_STATUS_FORMAL);
        this.attachmentFileDao.update(attachmentFile);
    }

    /**
     * 
     * 删除附件
     * @param fileSaveName [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private void deleteFile(String fileSaveName)
    {
        String vfsFileUri = AttachmentConfig.getInstance().getUploadPath()
                + fileSaveName;
        FileObject vfsFileObject = null;
        try
        {
            FileSystemManager vfsManager = VFS.getManager();
            vfsFileObject = vfsManager.resolveFile(vfsFileUri);
            vfsFileObject.delete();
        }
        catch (Exception e)
        {
            logger.error("删除文件时失败，vfsFileUri=" + vfsFileUri + " error: " + e);
        }
        finally
        {
            if (vfsFileObject != null)
                try
                {
                    vfsFileObject.close();
                }
                catch (FileSystemException e)
                {
                    logger.error("关闭文件失败，error:" + e);
                }
        }
    }

    /**
     * 取消附件
     * @param paramString
     */
    @Override
    public void withdrawAttachmentFile(long attachFileId)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("进入取消暂存附件方法，参数attachFileId=" + attachFileId);
        }

        if (0 > attachFileId)
        {
            logger.error("取消暂存附件时，传入的参数附件id为空");
            throw new AttachmentException("取消暂存附件时，传入的参数附件id为空");
        }

        AttachmentFile attachFile = this.attachmentFileDao.get(attachFileId);

        if (null == attachFile)
        {
            logger.error("取消暂存附件时，对应的附件记录不存在，attachFileId=" + attachFileId);
            throw new AttachmentException("取消暂存附件时，对应的附件记录不存在，attachFileId="
                    + attachFileId);
        }

        if (!("temp".equalsIgnoreCase(attachFile.getAttachFileStatus())))
        {
            if (logger.isDebugEnabled())
            {
                logger.debug("取消暂存附件时，对应的附件状态不为暂存，不能取消暂存");
            }
            throw new AttachIsFormalException("取消暂存附件时，对应的附件状态不为暂存，不能取消暂存");
        }

        deleteFile(attachFile.getFileSaveName());
        this.attachmentFileDao.delete(attachFile);

        if (logger.isDebugEnabled())
            logger.debug("离开取消暂存附件方法，无返回值");
    }

    /**
     * 生效附件组
     * @param paramString
     * @throws AttachmentException
     */
    @Override
    public void formalAttachmentGroup(Long attachGroupId)
            throws AttachmentException
    {
        if (logger.isDebugEnabled()) {
            logger.debug("进入生效附件组方法，参数attachGroupId=" + attachGroupId);
          }
          if (null == attachGroupId) {
            logger.error("生效附件组时，传入的参数附件组id为空");
            return;
          }

          AttachmentGroup attachGroup = this.attachmentGroupDao.get(attachGroupId);

          if (null == attachGroup) {
            logger.error("生效附件组时，对应的附件组记录不存在，attachGroupId=" + attachGroupId);
            return;
          }
          //不是临时状态不能生效附件组
          if (!("temp".equals(attachGroup.getAttachGroupStatus())))
          {
            logger.error("生效附件组时，对应的附件组状态不为暂存状态，不能生效附件组");
            return;
          }

          boolean bSync = false;
          //获取附件类型id
          Long attachTypeId = attachGroup.getAttachmentType().getId();
          if (attachTypeId > 0) {
            AttachmentType attachmentType = this.attachmentTypeDao.get(attachTypeId);

//            if (attachmentType != null) {
//              bSync = attachmentType.isSync();
//            }
            bSync = true;
          }

          AttachmentFile attachmentFile = new AttachmentFile();
//          attachmentFile.setGroupId(attachGroupId);
          
//          List<AttachmentFile> attachFileList = this.attachmentFileDao.listAttachmentFile(attachmentFile);
          
//          Set<AttachmentFile> attachFileList = this.attachmentGroupDao.get(attachGroupId).getAttachmentFileSet();
          List<AttachmentFile> attachFileList = this.attachmentFileDao.query("from AttachmentFile a where a.attachmentGroup='"+ attachGroupId +"'");
          for(AttachmentFile attachFile :attachFileList){
              if (!("temp".equals(attachFile.getAttachFileStatus())))
                  continue;
                formalAttachmentFile(attachFile, bSync);
          }
//          for (int i = 0; i < attachFileList.size(); ++i) {
//            AttachmentFile attachFile = (AttachmentFile)attachFileList.get(i);
//            if (!("temp".equals(attachFile.getAttachFileStatus())))
//              continue;
//            formalAttachmentFile(attachFile, bSync);
//          }

          attachGroup.setAttachGroupStatus("formal");
          this.attachmentGroupDao.update(attachGroup);

          if (logger.isDebugEnabled())
            logger.debug("离开生效附件组方法");
          
    }

    /**
     * @param paramList1
     * @param paramList2
     * @throws AttachmentException
     */
    @Override
    public void changeAttachmentFiles(List<String> paramList1,
            List<String> paramList2) throws AttachmentException
    {

    }

    /**
     * @param paramList
     * @throws AttachmentException
     */
    @Override
    public void deleteAttachmentFiles(List<String> paramList)
            throws AttachmentException
    {

    }

    /**
     * @param paramString
     */
    @Override
    public void cloneAttachmentGroup(String paramString)
    {

    }

    /**
     * @param paramString
     * @return
     * @throws AttachmentException
     */
    @Override
    public AttachmentFile findAttachmentFile(long attachFileId)
            throws AttachmentException
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("进入查找附件方法，参数attachFileId=" + attachFileId);
        }

        if (1 > attachFileId)
        {
            logger.error("查找附件时，传入的参数attachFileId为空");
            return null;
        }

        AttachmentFile attachFile = this.attachmentFileDao.get(attachFileId);

        if (null == attachFile)
        {
            logger.error("查找附件时，对应的附件记录不存在");
            return null;
        }

        String fileUri = AttachmentConfig.getInstance().getUploadPath()
                + attachFile.getFileSaveName();
        String fileName = StringUtils.substringAfterLast(fileUri, "/");
        String localFileName = AttachmentConfig.getInstance()
                .getLocalAbsoluteTempPath() + fileName;

        FileObject vsfFileObject = null;
        FileContent fileContent = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            FileSystemManager vfsManager = VFS.getManager();
            vsfFileObject = vfsManager.resolveFile(fileUri);
            fileContent = vsfFileObject.getContent();

            File localFile = new File(localFileName);
            fileOutputStream = new FileOutputStream(localFile);
            IOUtils.copy(fileContent.getInputStream(), fileOutputStream);
            attachFile.setFile(localFile);
        }
        catch (Exception e)
        {
            logger.error("从文件服务器取附件失败，error:" + e);
            throw new AttachmentException("查找附件时失败：" + e + "文件URI：" + fileUri);
        }
        finally
        {
            if (fileContent != null)
            {
                try
                {
                    fileContent.close();
                }
                catch (FileSystemException e)
                {
                    logger.error("关闭文件服务器文件失败，error:" + e);
                }
            }

            if (vsfFileObject != null)
            {
                try
                {
                    vsfFileObject.close();
                }
                catch (FileSystemException e)
                {
                    logger.error("关闭文件服务器文件失败，error:" + e);
                }
            }

            if (fileOutputStream != null)
            {
                try
                {
                    fileOutputStream.close();
                }
                catch (IOException e)
                {
                    logger.error("关闭文件失败，error:" + e);
                }
            }
        }

        if (logger.isDebugEnabled())
        {
            logger.debug("离开查找附件方法，返回值=" + attachFile);
        }
        return attachFile;
    }

    /**
     * 根据附件组获取附件列表
     * @param attachGroupId 附件组id
     * @return 附件列表
     */
    public List<AttachmentFile> listAttachmentFile(long attachGroupId)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("进入列表附件方法，参数attachGroupId=" + attachGroupId);
        }

        if (1 > attachGroupId)
        {
            logger.error("列表附件时，传入的参数attachGroupId为空");
            return null;
        }
/*
        List<AttachmentFile> attachFiles = this.attachmentGroupDao.get(
                attachGroupId).getAttachmentFileSet();*/
        List<AttachmentFile> attachFiles = this.attachmentFileDao.query("from AttachmentFile a where a.attachmentGroup='"+ attachGroupId +"'");
        if (null == attachFiles)
        {
            logger.error("列表附件时，对应的附件记录不存在");
            return null;
        }

        if (logger.isDebugEnabled())
        {
            logger.debug("离开列表附件方法，返回值=" + attachFiles);
        }
        return attachFiles;
    }
    /**
     * @param paramString
     * @return
     * @throws AttachmentException
     */
    @Override
    public AttachmentFile findSimpleAttachmentFile(Long attachFileId)
            throws AttachmentException
    {
        if (logger.isDebugEnabled()) {
            logger.debug("进入查找简单附件方法，参数attachFileId=" + attachFileId);
          }

          if (null == attachFileId) {
            logger.error("查找简单附件时，传入的参数attachFileId为空");
            return null;
          }

          AttachmentFile attachFile = this.attachmentFileDao.get(attachFileId);

          if (null == attachFile) {
            logger.error("查找简单附件时，对应的附件记录不存在");
            return null;
          }

          if (logger.isDebugEnabled()) {
            logger.debug("离开查找附件方法，返回值=" + attachFile);
          }
          return attachFile;
    }
    /**
     * @param paramString
     * @return
     */
    @Override
    public List<AttachmentFile> listFormalAttachmentFile(String attachGroupId)
    {
        if (logger.isDebugEnabled()) {
            logger.debug("进入列表有效附件方法，参数attachGroupId=" + attachGroupId);
          }

          if (null == attachGroupId) {
            logger.error("列表附件时，传入的参数attachGroupId为空");
            return null;
          }

//          AttachmentFile attachmentFile = new AttachmentFile();
//          attachmentFile.setGroupId(attachGroupId);
//          attachmentFile.setStatus("formal");
        /*  AttachmentGroup attachmentGroup = this.attachmentGroupDao.get(Long
                  .parseLong(attachGroupId));
          Set<AttachmentFile> attachFilesSet = attachmentGroup.getAttachmentFileSet();*/
          List<AttachmentFile> attachFiles = this.attachmentFileDao.query("from AttachmentFile a where a.attachmentGroup='"+ attachGroupId +"'");
          if (null == attachFiles) {
              
              logger.error("列表有效附件时，对应的附件记录不存在");
              return null;
           }
         
          if (logger.isDebugEnabled()) {
            logger.debug("离开有效列表附件方法，返回值=" + attachFiles);
          }
          return attachFiles;
    }

    /**
     * @param hql
     * @return
     */
    @Override
    public List<AttachmentFile> findByHQl(String hql)
    {
        return attachmentFileDao.query(hql, new Object[0]);
    }
    public AttachmentFileDao<AttachmentFile, Long> getAttachmentFileDao()
    {
        return attachmentFileDao;
    }

    public void setAttachmentFileDao(
            AttachmentFileDao<AttachmentFile, Long> attachmentFileDao)
    {
        this.attachmentFileDao = attachmentFileDao;
    }

    public AttachmentGroupDao<AttachmentGroup, Long> getAttachmentGroupDao()
    {
        return attachmentGroupDao;
    }

    public void setAttachmentGroupDao(
            AttachmentGroupDao<AttachmentGroup, Long> attachmentGroupDao)
    {
        this.attachmentGroupDao = attachmentGroupDao;
    }

    public AttachmentTypeDao<AttachmentType, Long> getAttachmentTypeDao()
    {
        return attachmentTypeDao;
    }

    public void setAttachmentTypeDao(
            AttachmentTypeDao<AttachmentType, Long> attachmentTypeDao)
    {
        this.attachmentTypeDao = attachmentTypeDao;
    }
   
  

}
