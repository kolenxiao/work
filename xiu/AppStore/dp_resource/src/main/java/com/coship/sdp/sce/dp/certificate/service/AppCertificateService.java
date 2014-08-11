/*
 * 文 件 名：AppCertificateService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：证书操作service层
 * 修 改 人：wangchenbo/906055
 * 修改时间：2012-9-21
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.certificate.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;
import com.coship.sdp.sce.dp.sign.entity.AppSign;
import com.coship.sdp.utils.Page;

/**
 * 证书操作service层.
 *
 * @author wangchenbo/906055
 * @version [版本号, 2012-9-21]
 * @since [产品/模块版本]
 */
public interface AppCertificateService extends Serializable
{

    /**
     * 保存证书.
     *
     * @param entity 证书对象.
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void saveAppCertificate(AppCertificate entity) throws Exception;

    /**
     * 删除证书.
     *
     * @param entity 证书对象.
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void deleteAppCertificate(AppCertificate entity) throws Exception;

    /**
     * 修改证书.
     *
     * @param entity 证书对象.
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void updateAppCertificate(AppCertificate entity) throws Exception;

    /**
     * 根据证书类型\吊销状态查询证书.
     *
     * @param certificateType 证书类型
     * @return
     * @throws Exception [参数说明]
     * @return AppCertificate 证书
     * @exception throws [违例类型] [违例说明]
     */
    public AppCertificate findAppCertificateByType(String certificateType,
            String revokeFlag) throws Exception;

    public AppCertificate getDefaultCertificate(String certificateType,
            String revokeFlag) throws Exception;

    /**
     * 根据证书类型\吊销状态查询证书列表.
     *
     * @param certificateType
     * @param revokeFlag
     * @return
     * @throws Exception [参数说明]
     * @return List<AppCertificate> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public List<AppCertificate> findAppCertificateListByType(
            String certificateType, String revokeFlag) throws Exception;

    /**
     * 分页查询证书信息.
     *
     * @param page 分页对象
     * @param hql 查询语句
     * @param objects 参数数组
     * @throws Exception [参数说明]
     * @return Page<AppCertificate> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public Page<AppCertificate> pageAppCertificate(Page<AppCertificate> page,
            String hql, Object[] objects) throws Exception;

    /**
     * 根据证书id查询证书信息.
     *
     * @param certificateId 证书id
     * @throws Exception [参数说明]
     * @return AppCertificate 证书信息
     * @exception throws [违例类型] [违例说明]
     */
    public AppCertificate findAppCertificateById(String certificateId)
            throws Exception;

    public void setDefaultAppCertificate(String certificateId) throws Exception;

    /**
     * 更新证书
     *
     * @param hql
     * @param objects
     * @return
     * @throws Exception [参数说明]
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public int updateAppCertificate(String hql, Object[] objects)
            throws Exception;

    /**
     * 根据证书id、证书类型、证书状态查询证书
     *
     * @param certId 证书id
     * @param certificateType 证书类型
     * @param revokeFlag 证书状态
     * @throws Exception [参数说明]
     * @return List<AppCertificate> 证书列表

     */
    public List<AppCertificate> findAppCertificateListByIdTypeStatus(
            String certId, String certificateType, String revokeFlag)
            throws Exception;

}
