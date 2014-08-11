/*
 * 文件名称：DpType.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-8-31
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.type.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coship.sdp.access.entity.EntityObject;

/**
 * <功能描述>
 * @author FuJian/906126
 * @version [版本号, 2011-8-31]
 * @since [产品/模块版本]
 */
@Entity
public class DpType extends EntityObject
{

    /**
     * 序列号
     */
    private static final long serialVersionUID = -5335681342405474704L;

    /**
     * 类型编码
     */
    private String typeCode;

    /**
     * 父类型编码
     */
    private String parentTypeCode;

    /**
     * 父类型名称
     */
    private String parentTypeName;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 分类描述
     */
    private String typeDesc;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 类型截图一
     */
    private String typeImg1;

    /**
     * 类型截图一
     */
    private String typeImg2;

    /**
     * 显示标记
     * 1:显示；2：隐藏
     */
    private int visibleFlag;
    
    /**
     * 分类下的应用
     */
    private int appTotal;
    
    /**
     * 分类排序
     */
    private int sortNum;
    
    @Column
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

    @Column(length = 64)
    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    @Column
    public Date getUpdateDate()
    {
        Date date = updateDate;
        return date;
    }

    public void setUpdateDate(Date updateDate)
    {
        if (null != updateDate)
        {
            this.updateDate = (Date) updateDate.clone();
        }
        else
        {
            this.updateDate = new Date();
        }
    }

    @Column
    public String getTypeCode()
    {
        return typeCode;
    }

    public void setTypeCode(String typeCode)
    {
        this.typeCode = typeCode;
    }

    @Column
    public String getParentTypeCode()
    {
        return parentTypeCode;
    }

    public void setParentTypeCode(String parentTypeCode)
    {
        this.parentTypeCode = parentTypeCode;
    }

    @Transient
    public String getParentTypeName()
    {
        return parentTypeName;
    }

    public void setParentTypeName(String parentTypeName)
    {
        this.parentTypeName = parentTypeName;
    }

    @Column(length = 32)
    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    @Column(length = 64)
    public String getTypeDesc()
    {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc)
    {
        this.typeDesc = typeDesc;
    }

    @Column
    public String getTypeImg1()
    {
        return typeImg1;
    }

    public void setTypeImg1(String typeImg1)
    {
        this.typeImg1 = typeImg1;
    }

    @Column
    public String getTypeImg2()
    {
        return typeImg2;
    }

    public void setTypeImg2(String typeImg2)
    {
        this.typeImg2 = typeImg2;
    }

    @Column
	public int getVisibleFlag() {
		return visibleFlag;
	}

	public void setVisibleFlag(int visibleFlag) {
		this.visibleFlag = visibleFlag;
	}
	
	@Transient
	public int getAppTotal() {
		return appTotal;
	}

	public void setAppTotal(int appTotal) {
		this.appTotal = appTotal;
	}

	@Column(length=4)
	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	

}
