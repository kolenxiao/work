/*
 * 文 件 名：AttachmentFile.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-8-30
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.attachment.entity;

import java.io.File;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.coship.sdp.dp.access.entity.EntityObject;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * @author  Sunwengang/903820
 * @version  [版本号, 2011-8-30]
 * @since  [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AttachmentFile extends EntityObject
{
    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 件存储名
     */
    private String fileSaveName;
    /**
     * 文件大小（字节为单位）
     */
    private int fileSize;
    /**
     * 文件类型（doc,pdf等）
     */
    private String fileType;
    /**
     * 附件状态（temp：暂存；formal：正式）
     */
    private String attachFileStatus;
    /**
     * 附件创建时间
     */
    private Date createDate;
    
    private File file;

    @Column(length = 256)
    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    @Column(length = 256)
    public String getFileSaveName()
    {
        return fileSaveName;
    }

    public void setFileSaveName(String fileSaveName)
    {
        this.fileSaveName = fileSaveName;
    }

    public int getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(int fileSize)
    {
        this.fileSize = fileSize;
    }

    @Column(length = 100)
    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    @Column(length = 12)
    public String getAttachFileStatus()
    {
        return attachFileStatus;
    }

    public void setAttachFileStatus(String attachFileStatus)
    {
        this.attachFileStatus = attachFileStatus;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    @Transient
    public File getFile()
    {
        return file;
    }


    public void setFile(File file)
    {
        this.file = file;
    }
    
    
    
}
