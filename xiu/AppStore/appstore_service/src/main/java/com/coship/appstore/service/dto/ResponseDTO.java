/*
 * 文件名称：BaseDTO.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-23
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.appstore.service.dto;

/**
 * 返回给客户端基类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-23]
 * @since [产品/模块版本]
 */
public class ResponseDTO
{
    /**
     * 返回信息状态码
     */
    private String respseStatus;

    /**
     * 返回信息描述
     */
    private String respseDesc;

    /**
     * 放回结果对象
     */
    private Object resultObject;

    public String getRespseStatus()
    {
        return respseStatus;
    }

    public void setRespseStatus(String respseStatus)
    {
        this.respseStatus = respseStatus;
    }

    public String getRespseDesc()
    {
        return respseDesc;
    }

    public void setRespseDesc(String respseDesc)
    {
        this.respseDesc = respseDesc;
    }

    public Object getResultObject()
    {
        return resultObject;
    }

    public void setResultObject(Object resultObject)
    {
        this.resultObject = resultObject;
    }

}
