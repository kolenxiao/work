/*
 * 文件名称：SignServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：签名信息服务实现
 * 创 建 人：wangchenbo/906055
 * 创建时间：2012-9-22
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.sign.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;
import com.coship.sdp.sce.dp.sign.dao.SignDao;
import com.coship.sdp.sce.dp.sign.entity.AppSign;
import com.coship.sdp.sce.dp.sign.service.SignService;
import com.coship.sdp.utils.Page;

/**
 * 应用签名信息服务类
 * @author wangchenbo/906055
 * @version [版本号, 2012-9-23]
 * @since [产品/模块版本]
 */
@Service("SignService")
@Transactional
public class SignServiceImpl implements SignService
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 应用签名信息dao层接口.
     */
    @Autowired
    private SignDao signDao;

    /**
     * 根据应用id获得签名信息.
     */
    private static final String FIND_SIGN_BY_APPID = " from AppSign aps where aps.dpAppInfo =? order by aps.createTime desc";

    /**
     * 根据应用id删除签名信息.
     */
    private static final String DETELE_SIGN_BY_APPID = " delete AppSign aps where aps.dpAppInfo =?  order by aps.createTime desc";

    /**
     * 根证书id获得签名信息.
     */
    private static final String FIND_SIGN_BY_CERTID = " from AppSign aps where aps.appCert =?  order by aps.createTime desc";

    @Override
    public void saveSign(AppSign sign) throws Exception
    {
        signDao.save(sign);

    }

    @Override
    public void deleteSign(AppSign sign) throws Exception
    {
        signDao.delete(sign);

    }

    @Override
    public Page<AppSign> pageAppSign(Page<AppSign> page, String hql,
            Object[] objects) throws Exception
    {

        return signDao.queryPage(page, hql, objects);
    }

    @Override
    public void updateSign(AppSign sign) throws Exception
    {
        signDao.update(sign);

    }

    @Override
    public AppSign findSignByAppId(DpAppInfo dpAppInfo) throws Exception
    {
        return signDao.findUnique(FIND_SIGN_BY_APPID, new Object[]
        { dpAppInfo });

    }

    @Override
    public void deltelSignByAppId(DpAppInfo dpAppInfo) throws Exception
    {
        signDao.executeUpdate(DETELE_SIGN_BY_APPID, new Object[]
        { dpAppInfo });

    }

    @Override
    public int countSignByCertId(AppCertificate appCertificate)
            throws Exception
    {
        return signDao.countHqlResult(FIND_SIGN_BY_CERTID, new Object[]
        { appCertificate});

    }

}
