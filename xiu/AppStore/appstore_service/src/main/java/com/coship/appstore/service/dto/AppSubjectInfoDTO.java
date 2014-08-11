/*
 * 文件名称：AppSubjectInfoDTO.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-22
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.appstore.service.dto;

/**
 * 应用专题信息数据传输对象
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-22]
 * @since [产品/模块版本]
 */
public class AppSubjectInfoDTO
{
    /**
     * 专题id
     */
    private String subjectId;

    /**
     * 主题名称
     */
    private String subjectName;

    /**
     * 专题描述
     */
    private String subjectDesc;

    /**
     * 专题图片
     */
    private String subjectImg;

    /**
     * 专题图片2
     */
    private String subjectImg2;

    public String getSubjectId()
    {
        return subjectId;
    }

    public void setSubjectId(String subjectId)
    {
        this.subjectId = subjectId;
    }

    public String getSubjectName()
    {
        return subjectName;
    }

    public void setSubjectName(String subjectName)
    {
        this.subjectName = subjectName;
    }

    public String getSubjectDesc()
    {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc)
    {
        this.subjectDesc = subjectDesc;
    }

    public String getSubjectImg()
    {
        return subjectImg;
    }

    public void setSubjectImg(String subjectImg)
    {
        this.subjectImg = subjectImg;
    }

    public String getSubjectImg2()
    {
        return subjectImg2;
    }

    public void setSubjectImg2(String subjectImg2)
    {
        this.subjectImg2 = subjectImg2;
    }

}
