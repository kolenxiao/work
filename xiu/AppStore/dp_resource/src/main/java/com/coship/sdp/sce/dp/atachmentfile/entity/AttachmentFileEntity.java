/*
 * 文件名称：AttachmentFileEntity.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-27
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.atachmentfile.entity;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-27]
 * @since [产品/模块版本]
 */
public class AttachmentFileEntity
{
    /**
     * 文件保存名称
     */
    private String fileSaveName;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 文件描述
     */
    private String fileDesc;

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

    public String getFileDesc()
    {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc)
    {
        this.fileDesc = fileDesc;
    }
}
