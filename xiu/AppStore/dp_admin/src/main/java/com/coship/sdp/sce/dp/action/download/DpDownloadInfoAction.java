/*
 * 文 件 名：DpDownloadInfoAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd.
 * Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-9-5
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.action.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;
import com.coship.sdp.sce.dp.atachmentfile.service.AttachmentFileService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.download.entity.DpDownloadInfo;
import com.coship.sdp.sce.dp.download.service.DpDownloadInfoService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;
import com.coship.sdp.utils.SystemConfig;

/**
 * 开发文档管理action.
 *
 * @author huangliufei/905735
 * @version [版本号, 2011-9-5]
 * @since [产品/模块版本]
 */
public class DpDownloadInfoAction extends BaseAction
{
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模块名称.
     */
    private static final String MODULE = DpDownloadInfoAction.class.getName();

    /**
     * 下载信息服务接口.
     */
    private DpDownloadInfoService dpDownloadInfoService;

    /**
     * 下载类型接口.
     */
    private DpTypeService dpTypeService;

    /**
     * 操作日志服务接口.
     */
    private OpLoggerService opLoggerService;

    /**
     * 附件服务接口.
     */
    private AttachmentFileService attachmentFileService;

    /**
     * 下载信息对象.
     */
    private DpDownloadInfo dpDownloadInfo;

    /**
     * 查询下载信息对象.
     */
    private DpDownloadInfo queryDownloadInfo;

    /**
     * 分页对象.
     */
    private Page<DpDownloadInfo> page;

    /**
     * 下载类型列表.
     */
    private List<DpType> dpTypeList;

    /**
     * 用户对象.
     */
    private User user;

    /**
     * 下载类型对象.
     */
    private DpType dpType;

    /**
     * 查询下载类型对象.
     */
    private DpType querydpType;

    /**
     * 下载id数组(删除操作时).
     */
    private String id;

    /**
     * 附件.
     */
    private File attachment;

    /**
     * 附件文件名称.
     */
    private String attachmentFileName;

    /**
     * 附件类型.
     */
    private String attachmentContentType;

    /**
     * 页面跳转标识.
     */
    private String forward;

    /**
     * 列表查询.
     *
     * @return
     */
    public String doList()
    {
        if (this.queryDownloadInfo == null)
        {
            this.queryDownloadInfo = new DpDownloadInfo();
        }
        this.page = new Page<DpDownloadInfo>();
        this.page.setPageSize(this.limit);
        this.page.setCurrentPage(this.start);
        this.queryDownloadInfo.setDpType(querydpType);
        try
        {
            // 获取文档类型列表
            this.dpTypeList = this.dpTypeService
                    .findByParentTypeCode(DefaultTypeCodeConstants.DOCUMENT_TYPE_CODE);
            this.page = this.dpDownloadInfoService.listDpDownloadInfo(
                    this.page, queryDownloadInfo);
        }
        catch (Exception e)
        {

            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = this.getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        return "list";
    }

    /**
     * 跳转到添加、修改或者查看下载页面.
     *
     * @return String
     */
    public String doDisplay()
    {
        String path = "toDownloadAdd";
        try
        {
            // 获取文档类型列表数据
            dpTypeList = dpTypeService
                    .findByParentTypeCode(DefaultTypeCodeConstants.DOCUMENT_TYPE_CODE);
            if (dpDownloadInfo != null)
            {
                dpDownloadInfo = dpDownloadInfoService
                        .findDpDownloadInfo(dpDownloadInfo.getId());

                if (dpDownloadInfo.getAttachFileId() != null)
                {
                    AttachmentFile attaFile = attachmentFileService.findAttachmentFileById(
                            dpDownloadInfo.getAttachFileId());

                    dpDownloadInfo.setAttachmentFile(attaFile);
                }

                if (Constants.TO_EDIT_FORWARD.equals(forward))
                {
                    path = "toDownloadEdit";
                }
                if (Constants.TO_DETAIL_FORWARD.equals(forward))
                {
                    path = "downloadInfoDetail";
                }
            }
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.common.jump.error");
            return ERROR;
        }
        return path;
    }

    /**
     * 添加文档
     *
     * @return String
     */
    public String doAdd()
    {
        // 验证文档的信息是否合法
        boolean flag = isxDownloadInfoLegal();

        if (!flag)
        {
            return ERROR;
        }

        try
        {
            // 判断该对象是否唯一
            if (!dpDownloadInfoService.isUniqueneByPropertyName(
                    "downloadName", dpDownloadInfo.getDownloadName()))
            {
                exception_msg = getText("sdp.sce.dp.admin.download.name.exits");
                return ERROR;
            }
            user = (User) request.getSession().getAttribute(
                    Constants.LOGIN_SESSION_USER);
            // 设置下载基本信息
            dpDownloadInfo.setCreateUser(user.getUserName());
            dpDownloadInfo.setCtime(new Date());
            dpDownloadInfo.setUpdateTime(new Date());
            // 0表示不是首页展示
            dpDownloadInfo.setShowIndex("0");
            dpDownloadInfo
                    .setDpType(dpTypeService.findType(dpType.getId()));

            if (attachment != null)
            {
                // 初始化并设置附件信息
                String attaId = initAttachmentFile();
                if (attaId == null)
                {
                    exception_msg = getText("sdp.sce.dp.admin.download.upload.length.lager5m");
                    return ERROR;
                }
                dpDownloadInfo.setAttachFileId(attaId);
            }

            dpDownloadInfoService.saveDpDownloadInfo(dpDownloadInfo);
            // 日志参数
            List<String> logParamList = initLogParame(user.getUserName(),
                    "sdp.sce.dp.admin.download",
                    "sdp.sce.dp.admin.log.add.operate",
                    dpDownloadInfo.getDownloadName());
            // 记录日志
            opLoggerService.info(getText("sdp.sce.dp.admin.download"),
                    getText("sdp.sce.dp.admin.log.download.operate.log",
                            logParamList), getText(Constants.ADD));

        }
        catch (FileNotFoundException e)
        {
            Debug.logError(e, "file not found " + e.getMessage(), MODULE);
        }
        catch (IOException e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
        }
        return doList();
    }

    /**
     * 验证文档的信息是否合法
     * true: 合法
     * false：非法
     */
    private boolean isxDownloadInfoLegal()
    {
        boolean flag = true;
        if (this.dpDownloadInfo == null)
        {
            Debug.logWarning("uploadInfo is null", MODULE);
            exception_msg = getText("sdp.sce.dp.admin.download.upload.null");
            flag = false;
        }
        if (attachment != null)
        {
            if (attachment.length() <= 0)
            {
                exception_msg = getText("sdp.sce.dp.admin.download.upload.length.is0");
                flag = false;
            }
            else if (attachment.length() > Long
                    .valueOf((String) SystemConfig.getInstance().getProperty(
                            Constants.ATTACHMENT_UPLOAD_MAXSIZE)))
            {
                exception_msg = getText("sdp.sce.dp.admin.download.upload.length.lager5m");
                flag = false;
            }
        }

        return flag;
    }

    /**
     * 修改下载信息.
     * @return String
     */
    public String doEdit()
    {
        try
        {
            // 是否重新上传新的文件
            boolean flag = false;
            user = (User) request.getSession().getAttribute(
                    Constants.LOGIN_SESSION_USER);
            // 校验下载名称是否有重复
            if (!request.getParameter("oldDownloadName").equals(
                    dpDownloadInfo.getDownloadName())
                    && !dpDownloadInfoService.isUniqueneByPropertyName(
                            "downloadName", dpDownloadInfo.getDownloadName()))
            {
                exception_msg = this
                        .getText("sdp.sce.dp.admin.download.name.exits");
                return ERROR;
            }
            else
            {
                AttachmentFile attachmentFile = null;
                String attaFileId = null;
                // 如果原先的附件不为空的话
                if (dpDownloadInfo.getAttachFileId() != null)
                {
                 // 获取原来下载的附件对象
                    attachmentFile = attachmentFileService
                            .findAttachmentFileById(dpDownloadInfo.getAttachFileId());
                }

                // 原先的附件为空
                // 如果附件参数不为空，则表示重新上传了附件
                if (attachment != null)
                {
                    attaFileId = initAttachmentFile();
                    dpDownloadInfo.setAttachFileId(attaFileId);
                    flag = true;
                }

                // 设置下载类型
                dpDownloadInfo.setDpType(dpTypeService
                        .findType(dpDownloadInfo.getDpType().getId()));

                // 设置最后更新时间
                dpDownloadInfo.setUpdateTime(new Date());
                // 更新下载信息
                dpDownloadInfoService.updateDpDownloadInfo(dpDownloadInfo);

                // 如果新上传了文件则删除原来的附件信息
                if (flag && attachmentFile != null)
                {
                    // 删除原来附件的本地文件
                    FileUtil.deleteFile(Constants.DOWNLOAD_IMG_SAVE_PATH
                            + File.separator + attachmentFile.getFileSaveName());
                    // 删除原来数据库中附件表记录
                    attachmentFileService
                            .deleteAttachmentFile(attachmentFile);
                }
                // 日志参数
                List<String> logParamList = initLogParame(user.getUserName(),
                        "sdp.sce.dp.admin.download",
                        "sdp.sce.dp.admin.log.update.operate",
                        dpDownloadInfo.getDownloadName());
                // 记录日志
                opLoggerService.info(getText("sdp.sce.dp.admin.download"),
                        getText(
                                "sdp.sce.dp.admin.log.download.operate.log",
                                logParamList), getText(Constants.MOD));
            }

        }
        catch (FileNotFoundException e)
        {
            exception_msg = this
                    .getText("sdp.sce.dp.admin.download.update.fail");
            Debug.logError(e, e.getMessage(), MODULE);
            return ERROR;
        }
        catch (IOException e)
        {
            exception_msg = this
                    .getText("sdp.sce.dp.admin.download.update.fail");
            Debug.logError(e, e.getMessage(), MODULE);
            return ERROR;
        }
        catch (Exception e)
        {
            exception_msg = this
                    .getText("sdp.sce.dp.admin.download.update.fail");
            Debug.logError(e, e.getMessage(), MODULE);
            return ERROR;
        }
        return doList();
    }

    /**
     * 初始化附件信息.
     *
     */
    public String initAttachmentFile()
    {

        String fileName = null;
        AttachmentFile attachmentTemp = new AttachmentFile();
        try
        {
            fileName = FileUtil.resetFileName(attachmentFileName);
            FileUtil.uploadFile(attachment,
                    Constants.DOWNLOAD_IMG_SAVE_PATH, fileName);
            // 保存一条附件信息
            attachmentTemp.setCreateDate(new Date());
            attachmentTemp.setFileName(attachmentFileName);
            attachmentTemp.setFileSaveName(fileName);
            attachmentTemp.setFileType(attachmentContentType);
            attachmentTemp.setFileSize(attachment.length());
            attachmentFileService.saveAttachmentFile(attachmentTemp);
        }
        catch (FileNotFoundException e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            return null;
        }
        catch (IOException e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            return null;
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            return null;
        }

        return attachmentTemp.getId();
    }

    /**
     * 删除下载.
     *
     * @return String
     */
    public String doDelete()
    {
        try
        {
            this.user = (User) this.request.getSession().getAttribute(
                    Constants.LOGIN_SESSION_USER);
            if (null != id && !"".equals(id))
            {
                // 根据“,”来切割界面上传来的id组
                String[] idArray = this.id.split(",");
                this.dpDownloadInfoService.deleteDownloadInfoByIds(idArray);

                // 获取文档类型列表数据
                this.dpTypeList = this.dpTypeService
                        .findByParentTypeCode(DefaultTypeCodeConstants.DOCUMENT_TYPE_CODE);
                // 日志参数
                List<String> logParamList = initLogParame(user.getUserName(),
                        "sdp.sce.dp.admin.download",
                        "sdp.sce.dp.admin.log.delete.operate", id);
                // 记录日志
                opLoggerService.info(this.getText("sdp.sce.dp.admin.download"),
                        this.getText(
                                "sdp.sce.dp.admin.log.download.delete.log",
                                logParamList), this.getText(Constants.DEL));
            }
            else
            {
                Debug.logWarning("The id is null!", MODULE);
                return ERROR;
            }

        }
        catch (Exception e)
        {
            exception_msg = this
                    .getText("sdp.sce.dp.admin.download.delete.fail");
            Debug.logError(e, e.getMessage(), MODULE);
            return ERROR;
        }
        return doList();
    }

    /**
     * 附件下载.
     */
    public void doDownLoad()
    {

        try
        {
            if (dpDownloadInfo.getAttachmentFile() != null)
            {
                // 2011.10.28 edited of 下载文件
                FileUtil.downLoad(Constants.DOWNLOAD_IMG_SAVE_PATH,
                        dpDownloadInfo.getAttachmentFile()
                        .getFileSaveName(), response);
            }
        }
        catch (FileNotFoundException e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
        }
        catch (IOException e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
        }
    }

    /**
     * get服务层对象.
     */
    public DpDownloadInfoService getDpDownloadInfoService()
    {
        return dpDownloadInfoService;
    }

    public void setDpDownloadInfoService(
            DpDownloadInfoService dpDownloadInfoService)
    {
        this.dpDownloadInfoService = dpDownloadInfoService;
    }

    public DpDownloadInfo getDpDownloadInfo()
    {
        return dpDownloadInfo;
    }

    public void setDpDownloadInfo(DpDownloadInfo dpDownloadInfo)
    {
        this.dpDownloadInfo = dpDownloadInfo;
    }

    public Page<DpDownloadInfo> getPage()
    {
        return page;
    }

    public void setPage(Page<DpDownloadInfo> page)
    {
        this.page = page;
    }

    public DpType getDpType()
    {
        return dpType;
    }

    public void setDpType(DpType dpType)
    {
        this.dpType = dpType;
    }

    public List<DpType> getDpTypeList()
    {
        return dpTypeList;
    }

    public void setDpTypeList(List<DpType> dpTypeList)
    {
        this.dpTypeList = dpTypeList;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public DpTypeService getDpTypeService()
    {
        return dpTypeService;
    }

    public void setDpTypeService(DpTypeService dpTypeService)
    {
        this.dpTypeService = dpTypeService;
    }

    public OpLoggerService getOpLoggerService()
    {
        return opLoggerService;
    }

    public void setOpLoggerService(OpLoggerService opLoggerService)
    {
        this.opLoggerService = opLoggerService;
    }

    public File getAttachment()
    {
        return attachment;
    }

    public void setAttachment(File attachment)
    {
        this.attachment = attachment;
    }

    public String getAttachmentFileName()
    {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName)
    {
        this.attachmentFileName = attachmentFileName;
    }

    public String getAttachmentContentType()
    {
        return attachmentContentType;
    }

    public void setAttachmentContentType(String attachmentContentType)
    {
        this.attachmentContentType = attachmentContentType;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public DpDownloadInfo getQueryDownloadInfo()
    {
        return queryDownloadInfo;
    }

    public void setQueryDownloadInfo(DpDownloadInfo queryDownloadInfo)
    {
        this.queryDownloadInfo = queryDownloadInfo;
    }

    public DpType getQuerydpType()
    {
        return querydpType;
    }

    public void setQuerydpType(DpType querydpType)
    {
        this.querydpType = querydpType;
    }

    public AttachmentFileService getAttachmentFileService()
    {
        return attachmentFileService;
    }

    public void setAttachmentFileService(
            AttachmentFileService attachmentFileService)
    {
        this.attachmentFileService = attachmentFileService;
    }

    public String getForward()
    {
        return forward;
    }

    public void setForward(String forward)
    {
        this.forward = forward;
    }

}
