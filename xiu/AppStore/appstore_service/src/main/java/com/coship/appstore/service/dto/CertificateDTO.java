/*
 * 文件名称：CertificateDTO.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-10-23
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.appstore.service.dto;

import java.util.List;

/**
 * 证书对象
 * @author jiangjinfeng/906974
 * @version [版本号, 2012-10-23]
 * @since [产品/模块版本]
 */
public class CertificateDTO
{
    /**
     * 签名证书列表
     */
    private List<String> certificates;

    /**
     * 证书吊销列表
     */
    private List<String> revokes;

    public List<String> getCertificates()
    {
        return certificates;
    }

    public void setCertificates(List<String> certificates)
    {
        this.certificates = certificates;
    }

    public List<String> getRevokes()
    {
        return revokes;
    }

    public void setRevokes(List<String> revokes)
    {
        this.revokes = revokes;
    }

}
