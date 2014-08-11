/*
 * 文 件 名：AttachmentFile.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：sunwengang/903820
 * 修改时间：2011-7-25
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.atachmentfile.entity;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.common.Constants;

/**
 * <一句话功能简述>. <功能详细描述>
 * @author sunwengang/903820
 * @version [版本号, 2011-7-25]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AttachmentFile extends EntityObject
{

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件保存名称
     */
    private String fileSaveName;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 文件描述
     */
    private String fileDesc;

    private String iscanDown;

    /**
     * 转换后文件大小，单位MB
     */
    private double size;

    /**
     * 应用信息
     */
    private DpAppInfo dpAppInfo;

    // 下载时候，如果本地附件不存在，提示不能下载
    @Transient
    public String getIscanDown()
    {
        // String filePath = (String) SystemConfig.getInstance().getProperty(
        // Constants.ATTACHMENT_UPLOAD_DIR);

        String path = Constants.DOWNLOAD_IMG_SAVE_PATH + File.separator
                + this.getFileSaveName();

        File file = new File(path);

        // 附件不存在，不可以下载
        if (!file.exists())
        {
            iscanDown = "0";
        }
        else
        {
            iscanDown = "1";
        }
        return iscanDown;
    }

    @Transient
    public void setIscanDown(String iscanDown)
    {
        this.iscanDown = iscanDown;
    }

    @Column(length = 128)
    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    @Column(length = 128)
    public String getFileSaveName()
    {
        return fileSaveName;
    }

    public void setFileSaveName(String fileSaveName)
    {
        this.fileSaveName = fileSaveName;
    }

    public long getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(long fileSize)
    {
        this.fileSize = fileSize;
    }

    @Column(length = 255)
    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public Date getCreateDate()
    {
        Date date = createDate;
        return date;
    }

    public void setCreateDate(Date createDate)
    {
        if (null != createDate)
        {
            this.createDate = (Date) createDate.clone();
        }
        else
        {
            this.createDate = new Date();
        }

    }

    @Column(length = 512)
    public String getFileDesc()
    {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc)
    {
        this.fileDesc = fileDesc;
    }

    @ManyToOne
    @JoinColumn(name = "C_DPAPPINFO_ID")
    public DpAppInfo getDpAppInfo()
    {
        return dpAppInfo;
    }

    public void setDpAppInfo(DpAppInfo dpAppInfo)
    {
        this.dpAppInfo = dpAppInfo;
    }

    @Transient
    public double getSize()
    {
        return size;
    }

    public void setSize(double size)
    {
        this.size = size;
    }



}
