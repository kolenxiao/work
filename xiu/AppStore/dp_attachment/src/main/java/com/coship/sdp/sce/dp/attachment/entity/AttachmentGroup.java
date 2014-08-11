package com.coship.sdp.sce.dp.attachment.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OrderBy;

import com.coship.sdp.dp.access.entity.EntityObject;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AttachmentGroup extends EntityObject{
    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 附件类型id（为空表示无类型）
     */
    private AttachmentType attachmentType;
    /**
     * 附件状态（temp：暂存；formal：正式）
     */
    private String attachGroupStatus;
    /**
     * 附件组创建时间
     */
    private Date createDate;
    /**
     * 附件集合
     */
    private Set<AttachmentFile> attachmentFileSet;
    
   
    
    @OneToMany
    @Cascade(value={CascadeType.SAVE_UPDATE,CascadeType.REMOVE})
    @JoinColumn(name="ATTACH_GROUP_ID")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OrderBy(clause="id desc")   
    public Set<AttachmentFile> getAttachmentFileSet()
    {
        return attachmentFileSet;
    }

    public void setAttachmentFileSet(Set<AttachmentFile> attachmentFileSet)
    {
        this.attachmentFileSet = attachmentFileSet;
    }


    @ManyToOne
    @JoinColumn(name="ATTACH_TYPE_ID")
    public AttachmentType getAttachmentType()
    {
        return attachmentType;
    }


    public void setAttachmentType(AttachmentType attachmentType)
    {
        this.attachmentType = attachmentType;
    }

    @Column(length = 12)
    public String getAttachGroupStatus()
    {
        return attachGroupStatus;
    }

    public void setAttachGroupStatus(String attachGroupStatus)
    {
        this.attachGroupStatus = attachGroupStatus;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
    
    
    
    
}
