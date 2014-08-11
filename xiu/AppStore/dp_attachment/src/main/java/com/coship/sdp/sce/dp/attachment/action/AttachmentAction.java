/*
 * 文 件 名：AttachmentAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-8-31
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.attachment.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coship.sdp.sce.dp.attachment.config.AttachmentConfig;
import com.coship.sdp.sce.dp.attachment.entity.AttachmentFile;
import com.coship.sdp.sce.dp.attachment.entity.AttachmentType;
import com.coship.sdp.sce.dp.attachment.exception.AttachIsFormalException;
import com.coship.sdp.sce.dp.attachment.exception.AttachNumberOverLimitException;
import com.coship.sdp.sce.dp.attachment.exception.AttachSizeOverLimitException;
import com.coship.sdp.sce.dp.attachment.exception.AttachTotalSizeOverLimitException;
import com.coship.sdp.sce.dp.attachment.exception.AttachTypeNotExistException;
import com.coship.sdp.sce.dp.attachment.exception.AttachmentException;
import com.coship.sdp.sce.dp.attachment.service.AttachmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <一句话功能简述> <功能详细描述>
 * @author Sunwengang/903820
 * @version [版本号, 2011-8-31]
 * @since [产品/模块版本]
 */
public class AttachmentAction extends ActionSupport implements
        ServletRequestAware, ServletResponseAware
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = -6181234096882212317L;

    private static final Logger logger = Logger
            .getLogger(AttachmentAction.class);

    /**
     * request对象.
     */
    protected HttpServletRequest request;

    /**
     * response对象.
     */
    protected HttpServletResponse response;

    private boolean result;

    /**
     * 错误信息map
     */
    private Map errorReason;

    /**
     * 数据map
     */
    private Map datas;

    /**
     * 附件
     */
    private File attachment;

    /**
     * 附件文件名称
     */
    private String attachmentFileName;

    /**
     * 附件类型
     */
    private String attachmentContentType;

    /**
     * 附件类型名
     */
    private String attachTypeName;

    /**
     * 附件组id
     */
    private String attachGroupId;

    /**
     * 附件文件id
     */
    private Long attachFileId;

    /**
     * 附件文件id组
     */
    private String attachFileIds;

    /**
     * 附件对象
     */
    private AttachmentType attachmentType;

    /**
     * 附件服务接口对象
     */
    private AttachmentService attachmentService;

    public AttachmentAction()
    {
        this.result = true;

        this.errorReason = new HashMap();

        this.datas = null;

        this.attachment = null;
        this.attachmentFileName = null;
        this.attachmentContentType = null;
        this.attachTypeName = null;
        this.attachGroupId = null;
        this.attachFileId = null;
        this.attachFileIds = null;
        this.attachmentType = null;
    }

    public String doAdd()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("进入暂存附件Action，参数attachGroupId=" + this.attachGroupId
                    + ",attachmentFileName=" + this.attachmentFileName
                    + ",attachmentContentType=" + this.attachmentContentType
                    + ",attachTypeId=" + this.attachTypeName
                    + ",attachGroupId=" + this.attachGroupId);
        }

        AttachmentFile attachFile = null;
        try
        {
            // 校验上传附件
            // attachmentService.validateAttachmentFile(Long.valueOf(this.attachGroupId), this.attachmentFileName, this.attachment, this.attachTypeName, 0, 0L, 0, 0L);

            attachFile = attachmentService.addAttachmentFile(
                    this.attachGroupId, this.attachmentFileName,
                    this.attachmentContentType, this.attachment,
                    this.attachTypeName);
        }
        catch (AttachNumberOverLimitException e)
        {
            logger.error("附件组总数超过了上限");
            setErrorReason("附件组总数超过了上限", e);
        }
        catch (AttachSizeOverLimitException e)
        {
            logger.error("附件的大小超过了上限");
            setErrorReason("附件的大小超过了上限", e);
        }
        catch (AttachTypeNotExistException e)
        {
            logger.error("附件的类型不在限制类型范围内");
            setErrorReason("附件的类型不在限制类型范围内", e);
        }
        catch (AttachTotalSizeOverLimitException e)
        {
            logger.error("附件组总的大小超过了上限");
            setErrorReason("附件组总的大小超过了上限", e);
        }
        catch (AttachmentException e)
        {
            logger.error(e.getMessage());
            setErrorReason(e.getMessage(), e);
        }
        catch (Exception e)
        {
            logger.error("上载附件失败");
            setErrorReason("上载附件失败", e);
        }
        // this.write(JSONObject.fromObject(this.getErrorReason()).toString());
        // 给附件组id赋值
        this.attachGroupId = String.valueOf(attachFile.getAttachmentGroup()
                .getId());

        this.attachFileId = attachFile.getId();
        // 获取附件组id中的附件列表
        List<AttachmentFile> AttachmentSet = attachmentService
                .listAttachmentFile(Long.valueOf(attachGroupId));
        if (this.datas == null)
        {
            this.datas = new HashMap();
        }
        this.datas.put("success", true);
        this.datas.put("attachGroupId", attachGroupId);
        this.datas.put("attachFiles", AttachmentSet);
        // 写数据
        this.write(JSONObject.fromObject(this.getDatas()).toString());

        logger.debug("离开暂存附件Action");
        return null;
    }

    /**
     * 
     * <取消暂存附件>
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doWithdraw()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("进入取消暂存附件Action，参数attachGroupId=" + this.attachGroupId
                    + "参数attachFileId=" + this.attachFileId);
        }

        if (0 > this.attachFileId)
        {
            logger.error("取消暂存附件组时参数为空(attachFileId)");
            setErrorReason("取消暂存附件组时参数为空(attachFileId)");
            return "error";
        }

        try
        {

            AttachmentFile attachFile = attachmentService
                    .findSimpleAttachmentFile(this.attachFileId);
            if (attachFile == null)
            {
                setErrorReason("取消暂存附件组时附件不存在");
                return "error";
            }

            if ("temp".equalsIgnoreCase(attachFile.getAttachFileStatus()))
                attachmentService.withdrawAttachmentFile(this.attachFileId);
        }
        catch (AttachIsFormalException e)
        {
            if (logger.isDebugEnabled())
            {
                logger.debug("附件是正式状态，不能删除");
            }
            return "success";
        }
        catch (Exception e)
        {
            logger.error("取消暂存附件失败");
            setErrorReason("取消暂存附件失败", e);
            return "error";
        }
        logger.debug("离开取消暂存附件Action");
        return "success";
    }

    /**
     * 
     * <获取附件列表>
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doList()
    {

        List<AttachmentFile> list = null;
        try
        {
            // 根据附件组id获取附件列表
            list = attachmentService
                    .listFormalAttachmentFile(this.attachGroupId);

            if (this.datas == null)
            {
                this.datas = new HashMap();
            }

            this.datas.put("attachFiles", list);

            System.err.println("attachGroupId=" + this.attachGroupId
                    + " 获取文件数：" + list.size());
        }
        catch (Exception e)
        {
            logger.error("无法获取groupId=" + this.attachGroupId + "的附件");
            setErrorReason("无法获取groupId=" + this.attachGroupId + "的附件", e);
            return "fail";
        }

        return "success";
    }

    /**
     * 附件下载 <功能描述>
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doDownload()
    {
        logger.debug("进入下载附件Action，参数attachFileId=" + this.attachFileId);

        /*
         * ActionContext context = ActionContext.getContext(); HttpServletResponse response = (HttpServletResponse)context.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");
         * FileInputStream fis = null; String encodeFileName; try { //获取附件 AttachmentFile attachFile = attachmentService.findAttachmentFile(this.attachFileId); if (null == attachFile) {
         * logger.error("下载附件失败"); setErrorReason("下载附件失败"); return null; } String uploadPath = AttachmentConfig.getInstance().getUploadPath(); //编码 encodeFileName = new
         * String(attachFile.getFileName().getBytes("GBK"), "ISO-8859-1");
         * 
         * response.reset(); response.setContentType("application/octet-stream"); response.addHeader("Content-Disposition", "attachment; filename=\"" + encodeFileName + "\"");
         * 
         * File file = new File(uploadPath+attachFile.getFileSaveName());
         * 
         * fis = new FileInputStream(file); IOUtils.copy(fis, response.getOutputStream()); response.getOutputStream().flush(); } catch (Exception e) { logger.error(e); setErrorReason(e.getMessage(),
         * e); encodeFileName = null;
         * 
         * return encodeFileName; } finally { if (null != fis) { try { fis.close(); } catch (IOException e) { logger.error(e); } } }
         * 
         * logger.debug("离开下载附件Action"); return null;
         */
        try
        {
            AttachmentFile attachFile = attachmentService
                    .findAttachmentFile(this.attachFileId);
            // 取得文件名。
            String uploadPath = AttachmentConfig.getInstance().getUploadPath();

            String path = uploadPath + File.separator
                    + attachFile.getFileSaveName();
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件的后缀名。
            // String ext = filename.substring(filename.lastIndexOf( "." ) + 1
            // ).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader(" Content-Length ", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(
                    response.getOutputStream());
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + attachFile.getFileName());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        }
        catch (IOException ex)
        {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    private String generateStackTrace(Exception e)
    {
        if (e == null)
        {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        ByteArrayOutputStream byteArrayOutputStream = null;
        try
        {
            byteArrayOutputStream = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(byteArrayOutputStream));
            stringBuffer.append(byteArrayOutputStream.toString());
        }
        catch (Exception ex)
        {
        }
        finally
        {
            if (byteArrayOutputStream != null)
                try
                {
                    byteArrayOutputStream.close();
                }
                catch (IOException ex2)
                {
                }
        }
        return stringBuffer.toString();
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

    public String getAttachTypeName()
    {
        return attachTypeName;
    }

    public void setAttachTypeName(String attachTypeName)
    {
        this.attachTypeName = attachTypeName;
    }

    public String getAttachGroupId()
    {
        return attachGroupId;
    }

    public void setAttachGroupId(String attachGroupId)
    {
        this.attachGroupId = attachGroupId;
    }

    public Long getAttachFileId()
    {
        return attachFileId;
    }

    public void setAttachFileId(Long attachFileId)
    {
        this.attachFileId = attachFileId;
    }

    public String getAttachFileIds()
    {
        return attachFileIds;
    }

    public void setAttachFileIds(String attachFileIds)
    {
        this.attachFileIds = attachFileIds;
    }

    public AttachmentType getAttachmentType()
    {
        return attachmentType;
    }

    public void setAttachmentType(AttachmentType attachmentType)
    {
        this.attachmentType = attachmentType;
    }

    public AttachmentService getAttachmentService()
    {
        return attachmentService;
    }

    public void setAttachmentService(AttachmentService attachmentService)
    {
        this.attachmentService = attachmentService;
    }

    // @JSON(name="success")
    public boolean getResult()
    {
        return this.result;
    }

    public void setResult(boolean result)
    {
        this.result = result;
    }

    // @JSON(name="data")
    public Map getErrorReason()
    {
        return this.errorReason;
    }

    public void setErrorReason(Map errorReason)
    {
        this.errorReason = errorReason;
    }

    public void setErrorReason(String errorReason)
    {
        setResult(false);
        this.errorReason.put("msg", errorReason);
        this.errorReason.put("errorStack", "");
    }

    public void setErrorReason(String errorMsg, Exception e)
    {
        setResult(false);
        this.errorReason.put("msg", errorMsg);
        this.errorReason.put("errorStack", generateStackTrace(e));
    }

    public Map getDatas()
    {
        return this.datas;
    }

    public void setDatas(Map data)
    {
        this.datas = data;
    }

    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;

    }

    public void write(String value)
    {
        try
        {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(value);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
