/*
 * 文件名称：DpTypeDTO.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：分类实体
 * 创 建 人：Huangliufei/905735
 * 创建时间：Sep 29, 2011
 *
 * 修改记录：1. 2012-09-06  wangchenbo/906055  重构
 *
 */
package com.coship.appstore.service.dto;

/**
 * DpTypeDTO对象是保存分类信息，封装成json格式对象发送给AppStore Client.
 * @author Huangliufei/905735
 * @version v100R001B010, 2012-09-06
 * @since 应用商店/应用商店服务端
 */
public class DpTypeDTO
{
    /**
     * 应用类型id.
     */
    private String id;

    /**
     * 应用类型名称.
     */
    private String name;

    /**
     * . 类型截图一.
     */
    private String typeImg1;

    /**
     * 类型截图一.
     */
	private String typeImg2 = "";

    /**
     * 分类描述
     */
	private String desc = "";
    
    /**
     * 分类下的应用数<br/>
     * add  2013-07-18
     */
    private int appTotal;

    public String getTypeImg1()
    {
        return typeImg1;
    }

    public void setTypeImg1(String typeImg1)
    {
        this.typeImg1 = typeImg1;
    }

    public String getTypeImg2()
    {
        return typeImg2;
    }

    public void setTypeImg2(String typeImg2)
    {
        this.typeImg2 = typeImg2;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

	public int getAppTotal()
	{
		return appTotal;
	}

	public void setAppTotal(int appTotal)
	{
		this.appTotal = appTotal;
	}
    
    
}
