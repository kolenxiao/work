/*
 * 文 件 名：AppCertificateServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：证书操作实现层
 * 修 改 人：wangchenbo/906055
 * 修改时间：2012-9-21
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.certificate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.certificate.dao.AppCertificateDao;
import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;
import com.coship.sdp.sce.dp.certificate.service.AppCertificateService;
import com.coship.sdp.sce.dp.utils.MethodsUtil;
import com.coship.sdp.utils.Page;

/**
 * 证书操作实现层.
 *
 * @author wangchenbo/906055
 * @version [版本号, 2012-9-21]
 * @since [产品/模块版本]
 */
@Service("AppCertificateService")
@Transactional
public class AppCertificateServiceImpl implements AppCertificateService
{

    /**
     * dao层接口.
     */
    @Autowired
    private AppCertificateDao appCertificateDao;

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 根据证书类型、证书吊销状态获得证书列表
     */
    private static final String LIST_APP_CERTIFICATE = "from AppCertificate ac where ac.certificateType = ? and ac.revokeFlag = ? order by ac.updatedDate desc";

    /**
     * 根据证书id\证书类型、证书吊销状态获得证书列表
     */
    private static final String LIST_APP_CERTIFICATE_BY_ID_TYPE_STUTAS = "from AppCertificate ac where  ac.id = ? and ac.certificateType = ? and ac.revokeFlag = ? order by ac.updatedDate desc";

    /**
     * 根据证书id获得证书信息
     */
    private static final String APP_CERTIFICATE_BY_ID = "from AppCertificate ac where ac.id = ?";

    @Override
    public void saveAppCertificate(AppCertificate entity) throws Exception
    {
        appCertificateDao.save(entity);

    }

    @Override
    public void deleteAppCertificate(AppCertificate entity) throws Exception
    {
        appCertificateDao.delete(entity);

    }

    @Override
    public void updateAppCertificate(AppCertificate entity) throws Exception
    {
        appCertificateDao.update(entity);

    }

    @Override
    public AppCertificate findAppCertificateByType(String certificateType,
            String revokeFlag) throws Exception
    {
        AppCertificate appCertificate = null;

        List<AppCertificate> listAppCertificate = findAppCertificateListByType(
                certificateType, revokeFlag);

        if (MethodsUtil.isNull(listAppCertificate))
        {

            appCertificate = null;
        }
        else
        {
            appCertificate = listAppCertificate.get(0);
        }
        return appCertificate;
    }


    @Override
	public AppCertificate getDefaultCertificate(String certificateType,
            String revokeFlag) throws Exception {

    	AppCertificate appCertificate = null;
    	 String hql ="from AppCertificate ac where ac.certificateType = ? and ac.revokeFlag = ? and ac.isDefault=?";
    	 AppCertificate defaultAppCertificate = appCertificateDao.findUnique(hql, new Object[]{certificateType,revokeFlag,1});
    	 if(defaultAppCertificate!=null){
    		 return defaultAppCertificate;
    	 }

         List<AppCertificate> listAppCertificate = findAppCertificateListByType(
                 certificateType, revokeFlag);

         if (MethodsUtil.isNull(listAppCertificate))
         {
             appCertificate = null;
         }
         else
         {
             appCertificate = listAppCertificate.get(0);
         }
		return appCertificate;
	}

	@Override
    public List<AppCertificate> findAppCertificateListByType(
            String certificateType, String revokeFlag) throws Exception
    {

        List<AppCertificate> listAppCertificate = appCertificateDao.query(
                LIST_APP_CERTIFICATE, new Object[]
                { certificateType, revokeFlag });

        return listAppCertificate;
    }

    @Override
    public Page<AppCertificate> pageAppCertificate(Page<AppCertificate> page,
            String hql, Object[] objects) throws Exception
    {

        return appCertificateDao.queryPage(page, hql, objects);
    }

    @Override
    public AppCertificate findAppCertificateById(String certificateId)
            throws Exception
    {
        return appCertificateDao.findUnique(APP_CERTIFICATE_BY_ID, new Object[]
        { certificateId });
    }

    @Override
    public int updateAppCertificate(String hql, Object[] objects)
            throws Exception
    {
        int result = appCertificateDao.executeUpdate(hql, objects);

        return result;
    }

    @Override
    public List<AppCertificate> findAppCertificateListByIdTypeStatus(
            String certId, String certificateType, String revokeFlag)
            throws Exception
    {

        List<AppCertificate> listAppCertificate = appCertificateDao.query(
                LIST_APP_CERTIFICATE_BY_ID_TYPE_STUTAS, new Object[]
                { certId, certificateType, revokeFlag });

        return listAppCertificate;
    }

	@Override
	public void setDefaultAppCertificate(String certificateId) throws Exception {
		String updateHql1 = "update AppCertificate ac set ac.isDefault=0 where ac.isDefault=1";
		String updateHql2 = "update AppCertificate ac set ac.isDefault=1 where ac.id=?";
		appCertificateDao.executeUpdate(updateHql1);
		appCertificateDao.executeUpdate(updateHql2,new Object[]{certificateId});
	}

}
