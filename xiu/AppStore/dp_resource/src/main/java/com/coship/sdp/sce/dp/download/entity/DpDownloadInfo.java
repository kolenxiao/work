/*
 * 文 件 名：DpDownloadInfo.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-9-5
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.download.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;
import com.coship.sdp.sce.dp.type.entity.DpType;

/**
 * <一句话功能简述>.
 * <功能详细描述>
 * @author  Sunwengang/903820
 * @version  [版本号, 2011-9-5]
 * @since  [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DpDownloadInfo extends EntityObject {

    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;
    /**
     * 文档标题.
     */
    private String downloadName;
    /**
     * 文档描述.
     */
    private String downloadDesc;
    /**
     * 文档来源
     */
    private String docSource;
    /**
     * 文档来源地址
     */
    private String docSourceUrl;
    /**
     * 创建时间.
     */
    private Date ctime;
    /**
     * 最后修改时间.
     */
    private Date updateTime;

    /**
     * 附件文件.
     */
    private AttachmentFile attachmentFile;

    /**
     * 所属分类.
     */
    private DpType dpType;

    /**
     * 创建者.
     */
    private String  createUser;

    /**
     * 创建开始时间.
     */
    private String ctimeStart;

    /**
     * 创建结束时间.
     */
    private String ctimeEnd;

    /**
     * 附件ID
     */
    private String attachFileId;

    /**
     * 首页推荐 0：表述没有推荐；1：表示首页推荐.
     */
    private String showIndex;
    /**
     * 设置描述.
     */
    @Column(length = 300000)
    public String getDownloadDesc()
    {
        return downloadDesc;
    }

    public void setDownloadDesc(String downloadDesc)
    {
        this.downloadDesc = downloadDesc;
    }

    @Transient
    public AttachmentFile getAttachmentFile()
    {
        return attachmentFile;
    }

    public void setAttachmentFile(AttachmentFile attachmentFile)
    {
        this.attachmentFile = attachmentFile;
    }

    @ManyToOne
    @JoinColumn(name="C_TYPE_ID")
    public DpType getDpType()
    {
        return dpType;
    }

    public void setDpType(DpType dpType)
    {
        this.dpType = dpType;
    }

    public Date getCtime()
    {
        Date date = ctime;
        return date;
    }

    public void setCtime(Date ctime)
    {
        if (null != ctime) {
            this.ctime = (Date) ctime.clone();
        } else {
            this.ctime = new Date();
        }
    }


    public Date getUpdateTime()
    {
        Date date = updateTime;
        return date;
    }

    public void setUpdateTime(Date updateTime)
    {
        if (null != updateTime) {
            this.updateTime = (Date) updateTime.clone();
        } else {
            this.updateTime = new Date();
        }
    }

    @Column(length=64)
    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }


    @Column(length = 200)
    public String getDownloadName()
    {
        return downloadName;
    }

    public void setDownloadName(String downloadName)
    {
        this.downloadName = downloadName;
    }

    @Transient
    public String getCtimeStart()
    {
        return ctimeStart;
    }

    public void setCtimeStart(String ctimeStart)
    {
        this.ctimeStart = ctimeStart;
    }

    @Transient
    public String getCtimeEnd()
    {
        return ctimeEnd;
    }

    public void setCtimeEnd(String ctimeEnd)
    {
        this.ctimeEnd = ctimeEnd;
    }
    @Column(length = 2)
    public String getShowIndex()
    {
        return showIndex;
    }

    public void setShowIndex(String showIndex)
    {
        this.showIndex = showIndex;
    }

    @Column(length = 128)
    public String getDocSource()
    {
        return docSource;
    }

    public void setDocSource(String docSource)
    {
        this.docSource = docSource;
    }

    @Column(length = 128)
    public String getDocSourceUrl()
    {
        return docSourceUrl;
    }

    public void setDocSourceUrl(String docSourceUrl)
    {
        this.docSourceUrl = docSourceUrl;
    }

    public String getAttachFileId()
    {
        return attachFileId;
    }

    public void setAttachFileId(String attachFileId)
    {
        this.attachFileId = attachFileId;
    }



}
