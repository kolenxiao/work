package com.coship.sdp.sce.dp.attachment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.coship.sdp.dp.access.entity.EntityObject;


@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AttachmentType extends EntityObject{
    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 附件类型名称
     */
    private String attachTypeName;
    /**
     * 附件类型描述
     */
    private String attachTypeDesc;
    /**
     * 附件数量限制
     */
    private int attachCountLimit;
    /**
     * 附件大小总限制（字节为单位）
     */
    private int attachSizeLimit;
    /**
     * 单个附件大小限制（字节为单位）
     */
    private int singleSizeLimit;
    /**
     * 文件后缀名限制（空表示不限制，如果支持多种后缀，通过；号分隔，如doc;pdf;rar等）
     */
    private String fileSuffixLimit;
    
    
    
    @Column(length = 32)
    public String getAttachTypeName()
    {
        return attachTypeName;
    }

    public void setAttachTypeName(String attachTypeName)
    {
        this.attachTypeName = attachTypeName;
    }

    @Column(length = 64)
    public String getAttachTypeDesc()
    {
        return attachTypeDesc;
    }

    public void setAttachTypeDesc(String attachTypeDesc)
    {
        this.attachTypeDesc = attachTypeDesc;
    }

    
    public int getAttachCountLimit()
    {
        return attachCountLimit;
    }

    public void setAttachCountLimit(int attachCountLimit)
    {
        this.attachCountLimit = attachCountLimit;
    }

    public int getAttachSizeLimit()
    {
        return attachSizeLimit;
    }

    public void setAttachSizeLimit(int attachSizeLimit)
    {
        this.attachSizeLimit = attachSizeLimit;
    }

    public int getSingleSizeLimit()
    {
        return singleSizeLimit;
    }

    public void setSingleSizeLimit(int singleSizeLimit)
    {
        this.singleSizeLimit = singleSizeLimit;
    }

    @Column(length = 128)
    public String getFileSuffixLimit()
    {
        return fileSuffixLimit;
    }

    public void setFileSuffixLimit(String fileSuffixLimit)
    {
        this.fileSuffixLimit = fileSuffixLimit;
    }
    
    
    
}
