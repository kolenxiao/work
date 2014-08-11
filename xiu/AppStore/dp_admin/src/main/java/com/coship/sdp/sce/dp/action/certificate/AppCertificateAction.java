/**
 * 文件名称：AppCertificateAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：证书处理action
 * 创 建 人：wangchenbo/906055
 * 创建时间：2012-9-21
 *
 * 修改记录：1. <修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.action.certificate;

import java.io.File;
import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.permission.entity.User;
import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;
import com.coship.sdp.sce.dp.certificate.service.AppCertificateService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.QueryAppInfoThread;
import com.coship.sdp.sce.dp.sign.service.SignService;
import com.coship.sdp.sce.dp.utils.CertificateUtil;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.sce.dp.utils.MethodsUtil;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 证书管理action.
 *
 * @author wangchenbo/906055
 * @version [版本号, 2012-9-21]
 * @since [产品/模块版本]
 */
@Controller
public class AppCertificateAction extends BaseAction
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模块名称.
     */
    private static final String MODULE = AppCertificateAction.class.getName();

    /**
     * 签名证书.
     */
    private File signed;

    /**
     * 签名证书文件名称.
     */
    private String signedFileName;

    /**
     * 签名证书私钥.
     */
    private File privateKey;

    /**
     * 签名证书私钥文件名称.
     */
    private String privateKeyFileName;

    /**
     * 证书吊销列表.
     */
    private File revoke;

    /**
     * 证书吊销列表文件名称.
     */
    private String revokeFileName;

    /**
     * 证书对象.
     */
    private AppCertificate appCertificate;

    /**
     * 查询证书对象.
     */
    private AppCertificate queryAppCertificate;

    /**
     * 证书服务接口.
     */
    @Autowired
    private AppCertificateService appCertificateService;

    /**
     * 应用签名服务接口.
     */
    @Autowired
    private SignService signService;

    /**
     * 分页显示.
     */
    private Page<AppCertificate> page;

    /**
     * 默认查询证书列表.
     */
    private static final String CERTIFICATE_LIST_HQL = "from AppCertificate apc";

    /**
     * 证书id串
     */
    private String ids;

    /**
     * 吊销证书列表、签名证书和私钥标识：0-吊销证书列表，1-签名证书和私钥
     */
    private String publicPrivateRevokeFlag;

    private static final String REVOKE_FLAG = "0";

    /**
     * 添加或修改证书页面.
     *
     * @return
     */
    public String doAddOrModify()
    {

        Debug.logVerbose("AppCertificateActiondo.AddOrModify()  start...",
                MODULE);

        try
        {
            // if +0 begin
            if (null == appCertificate)
            {

                Debug.logInfo("appCertificate = null", MODULE);
            }
            else
            {

                AppCertificate appCertObj = appCertificateService
                        .findAppCertificateById(appCertificate.getId());

                // 修改证书时为空
                // if +1 begin
                if (null == appCertObj)
                {

                    setResult("exception",
                            getText("sdp.sce.dp.admin.certificate.add.null"));
                    return ERROR;

                }
                else
                {

                    // 判断签名证书
                    // if +2 begin
                    if (Constants.CERTIFICATE_SIGNED_FLAG.equals(appCertObj
                            .getCertificateType()))
                    {
                        // 签名证书
                        signedFileName = appCertObj.getCertificateSrcName();

                        // 私钥
                        privateKeyFileName = appCertObj.getPrivateKeySrcName();

                    }
                    else
                    {

                        Debug.logInfo("appCertObj.getCertificateType() ="
                                + appCertObj.getCertificateType(), MODULE);
                    }
                    // if +2 end

                }
                // if +1 end

            }
            // if +0 end
            new QueryAppInfoThread().start();
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        Debug.logVerbose("AppCertificateAction.doAddOrModify()  end...", MODULE);

        return "addOrEdit";
    }

    /**
     * 添加或修改吊销证书列表页面.
     *
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doAddOrModifyRevoke()
    {
        Debug.logVerbose(
                "AppCertificateActiondo.doAddOrModifyRevoke()  start...",
                MODULE);

        try
        {
            // if +0 begin
            if (null == appCertificate)
            {

                Debug.logInfo("appCertificate = null", MODULE);
            }
            else
            {

                AppCertificate appCertObj = appCertificateService
                        .findAppCertificateById(appCertificate.getId());

                // 修改证书时为空
                // if +1 begin
                if (null == appCertObj)
                {

                    setResult("exception",
                            getText("sdp.sce.dp.admin.certificate.add.null"));
                    return ERROR;

                }
                else
                {

                    // 判断签名证书
                    // if +2 begin
                    if (Constants.CERTIFICATE_REVOKE_FLAG.equals(appCertObj
                            .getCertificateType()))
                    {

                        // 吊销列表
                        revokeFileName = appCertObj.getRevokeSrcName();

                    }
                    else
                    {

                        Debug.logInfo("appCertObj.getCertificateType() ="
                                + appCertObj.getCertificateType(), MODULE);
                    }
                    // if +2 end

                }
                // if +1 end

            }
            // if +0 end
            new QueryAppInfoThread().start();//有更新时同步接口
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        Debug.logVerbose("AppCertificateAction.doAddOrModifyRevoke()  end...",
                MODULE);
        return "addOrEditRevoke";
    }

    /**
     * 添加证书.
     *
     * @return
     */
    public String doAdd()
    {

        Debug.logVerbose("AppCertificateAction.doAdd() start...", MODULE);

        try
        {

            if (null == appCertificate)
            {

                setResult("exception",
                        getText("sdp.sce.dp.admin.certificate.add.null"));
                return ERROR;

            }
            else
            {

                // 上传证书
                uploadCertificateFile();

            }

        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }

        Debug.logVerbose("AppCertificateAction.doAdd() end...", MODULE);

        // 上传吊销文件为空，跳转到证书列表，否则跳转吊销证书列表
        if (null == revoke)
        {
            return doList();
        }
        else
        {
            return doRevokeList();
        }

    }

    /**
     * 证书列表.
     *
     * @return
     */
    public String doList()
    {

        Debug.logVerbose("AppCertificateAction.doList() start...", MODULE);

        try
        {
            // 查询证书列表
            if (null == page)
            {
                page = new Page<AppCertificate>();
            }
            page.setPageSize(limit);
            page.setCurrentPage(start);

            if (null == queryAppCertificate)
            {
                queryAppCertificate = new AppCertificate();
            }
            // 检查证书是否过期
            /*checkExpiredCertificate();*/
            // 证书和私钥
            queryAppCertificate
                    .setCertificateType(Constants.CERTIFICATE_SIGNED_FLAG);
            page = queryCertPage(queryAppCertificate);

        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }

        Debug.logVerbose("AppCertificateAction.doList() end...", MODULE);

        return "doList";
    }

    /**
     * 吊销证书列表.
     *
     * @return
     */
    public String doRevokeList()
    {

        Debug.logVerbose("AppCertificateAction.doRevokeList() start...", MODULE);

        try
        {
            // 查询证书列表
            if (null == page)
            {
                page = new Page<AppCertificate>();
            }
            page.setPageSize(limit);
            page.setCurrentPage(start);

            if (null == queryAppCertificate)
            {
                queryAppCertificate = new AppCertificate();
            }
            // 检查证书是否过期
            /*checkExpiredCertificate();*/
            // 吊销证书
            queryAppCertificate
                    .setCertificateType(Constants.CERTIFICATE_REVOKE_FLAG);
            page = queryCertPage(queryAppCertificate);

        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }

        Debug.logVerbose("AppCertificateAction.doRevokeList() end...", MODULE);

        return "doRevokeList";
    }

    /**
     * 修改证书.
     *
     * @return
     */
    public String doModify()
    {

        Debug.logVerbose("AppCertificateAction.doModify() start...", MODULE);

        try
        {
            if (null == appCertificate)
            {

                exception_msg = getText("sdp.sce.dp.admin.certificate.add.null");
                return ERROR;

            }
            else
            {

                // 检查是否有应用使用该证书进行签名
                int appSignSize = getAppSignSizeByCertId(appCertificate);

                if (0 == appSignSize)
                {
                    uploadUpdateCertificateFile();
                }
                else
                {
                    exception_msg = getText("sdp.sce.dp.admin.certificate.used");
                    return ERROR;
                }

            }
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }

        Debug.logVerbose("AppCertificateAction.doModify() end...", MODULE);

        // 上传吊销文件为空，跳转到证书列表，否则跳转吊销证书列表
        if (null == revoke)
        {
            return doList();
        }
        else
        {
            return doRevokeList();
        }
    }

    /**
     * 删除证书.
     *
     * @return
     */
    public String doDelete()
    {

        Debug.logVerbose("AppCertificateAction.doDelete() start...", MODULE);

        if (ids == null)
        {

            exception_msg = getText("sdp.sce.dp.admin.global.select.del.data");
            return ERROR;
        }

        String[] idArray = MethodsUtil.stringToArray(ids, Constants.SPLIT_FLAG);
        try
        {
            for (String cerId : idArray)
            {

                AppCertificate appCertificateList = appCertificateService
                        .findAppCertificateById(cerId);

                // 校验是否有应用使用该证书进行签名
                boolean isSign = isSign4Certificate(appCertificateList);

                if (isSign)
                {
                    deleteCertificate(appCertificateList);
                }
                else
                {
                    exception_msg = getText("sdp.sce.dp.admin.certificate.used");
                    return ERROR;
                }
            }
        }
        catch (Exception e)
        {

            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.global.select.del.data");
            return ERROR;
        }

        Debug.logVerbose("AppCertificateAction.doDelete() end...", MODULE);

        if (REVOKE_FLAG.equals(publicPrivateRevokeFlag))
        {
            return doRevokeList();
        }
        else
        {
            return doList();
        }

    }

    /**
     * 设置默认证书.
     *
     * @return
     */
    public String setDefault()
    {

        Debug.logVerbose("AppCertificateAction.doSetDefault() start...", MODULE);

        if (ids == null)
        {

            exception_msg = "设置默认证书出错";
            return ERROR;
        }

		String[] idArray = MethodsUtil.stringToArray(ids, Constants.SPLIT_FLAG);
		try {
			for (String cerId : idArray) {

				AppCertificate appCertificate = appCertificateService
						.findAppCertificateById(cerId);
				if (appCertificate != null && StringUtils.equals("1000",appCertificate.getRevokeFlag())) {
					appCertificateService.setDefaultAppCertificate(cerId);
				} else if(appCertificate!=null && !StringUtils.equals("1000",appCertificate.getRevokeFlag())){
					exception_msg = "证书不可用";
					setResult(
							"exception",
							"证书不可用");
					write(JSONObject.fromObject(getResult()).toString());
					return null;
				}else{
					exception_msg = "证书不存在";
					setResult(
							"exception",
							"证书不存在");
					write(JSONObject.fromObject(getResult()).toString());
					return null;
				}
			}
		}
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
			setResult(
					"exception",
					"设置默认证书出错");
			write(JSONObject.fromObject(getResult()).toString());
            exception_msg = "设置默认证书出错";
            return null;
        }
        setResult("success", true);
		write(JSONObject.fromObject(getResult()).toString());
        Debug.logVerbose("AppCertificateAction.doSetDefault() end...", MODULE);
        return null;
    }

    /**
     * 根据证书id查询签名的应用数量.
     *
     * @param appCert 证书对象
     * @return int 应用数量,-1代表出错了
     * @exception throws [违例类型] [违例说明]
     */
    private int getAppSignSizeByCertId(AppCertificate appCert)
    {
        int size = 0;
        try
        {
            if (null != appCert)
            {

                size = signService.countSignByCertId(appCert);

            }
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            size = -1;
        }

        return size;
    }

    /**
     * 检查证书对象是否已签名应用
     *
     * @param appCert 证书列表
     * @return boolean true 无签名应用，false有签名应用或查询出错
     * @exception throws [违例类型] [违例说明]
     */
    private boolean isSign4Certificate(AppCertificate appCert)
    {

        boolean flag = true;

        int appSignSize = 0;

        appSignSize = getAppSignSizeByCertId(appCert);

        if (0 != appSignSize)
        {
            flag = false;

        }

        return flag;

    }

    /**
     * 获得上传的证书文件.
     *
     * @param certificate 证书对象
     * @param fileName 证书文件名
     * @param file 证书文件
     * @param savePath 服务器保存路径
     * @param certificateType 证书类型
     * @return [参数说明]
     * @return AppCertificate [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private AppCertificate initAppCertificate(AppCertificate certificate,
            String fileName, File file, String certificateType)
    {

        String saveSigned = FileUtil.getSaveFileName(fileName, file,
                Constants.CERTIFICATE_SAVE_PATH);

        Date date = new Date();
        AppCertificate signedCertificate = new AppCertificate();

        signedCertificate.setCertificateSrcName(fileName);
        signedCertificate.setCertificateSaveName(saveSigned);
        signedCertificate.setCertificateType(certificateType);
        signedCertificate.setRevokeFlag(Constants.CERTIFICATE_STATUS_NORMAL);
        signedCertificate.setCertificateDesc(certificate.getCertificateDesc());
        signedCertificate.setCreatedDate(date);
        signedCertificate.setUpdatedDate(date);

        User user = (User) request.getSession().getAttribute("user");
        if (null != user)
        {
            signedCertificate.setCreatedUser(user.getUserName());
        }

        return signedCertificate;

    }

    /**
     * 获得保存后的证书文件名
     *
     * @param certificate 证书对象
     * @param fileName 文件名
     * @param file 文件对象
     * @return String 证书保存的文件名
     * @exception throws [违例类型] [违例说明]
     */
    private String getAppCertSaveName(AppCertificate certificate,
            String fileName, File file)
    {
        String saveSigned = FileUtil.getSaveFileName(fileName, file,
                Constants.CERTIFICATE_SAVE_PATH);

        return saveSigned;

    }

    /**
     * 上传并保存证书文件.
     *
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private void uploadCertificateFile() throws Exception
    {

        // 上传签名证书、密钥文件
        if (null != signed && null != privateKey)
        {
            AppCertificate appCertificateSigned = initAppCertificate(
                    appCertificate, signedFileName, signed,
                    Constants.CERTIFICATE_SIGNED_FLAG);

            // 证书
            X509Certificate ca = CertificateUtil
                    .readCa(Constants.CERTIFICATE_SAVE_PATH + File.separator
                            + appCertificateSigned.getCertificateSaveName());
            // 序列号
            appCertificateSigned.setSerialNumber(ca.getSerialNumber().toString(
                    CertificateUtil.HEXADECIMAL));
            // 有效起始日期
            appCertificateSigned.setNotBefore(ca.getNotBefore());
            // 有效终止日期
            appCertificateSigned.setNotAfter(ca.getNotAfter());
            // 证书过期校验
            /*if (expiredDate(ca.getNotAfter()))
            {
                appCertificateSigned
                        .setRevokeFlag(Constants.CERTIFICATE_STATUS_EXPIRED);
            }*/

            // 上传签名证书密钥文件

            AppCertificate appCertificatePrivateKey = initAppCertificate(
                    appCertificate, privateKeyFileName, privateKey,
                    Constants.CERTIFICATE_SIGNED_FLAG);
            appCertificateSigned.setPrivateKeySrcName(appCertificatePrivateKey
                    .getCertificateSrcName());
            appCertificateSigned.setPrivateKeySaveName(appCertificatePrivateKey
                    .getCertificateSaveName());

            appCertificateService.saveAppCertificate(appCertificateSigned);
        }
        // 上传吊销列表
        if (null != revoke)
        {
            AppCertificate appCertificateRevoke = initAppCertificate(
                    appCertificate, revokeFileName, revoke,
                    Constants.CERTIFICATE_REVOKE_FLAG);

            // 吊销证书列表名称
            appCertificateRevoke.setRevokeSrcName(appCertificateRevoke
                    .getCertificateSrcName());
            appCertificateRevoke.setRevokeSaveName(appCertificateRevoke
                    .getCertificateSaveName());
            appCertificateRevoke.setCertificateSrcName(null);
            appCertificateRevoke.setCertificateSaveName(null);

            // 获得吊销信息
            appCertificateRevoke = getCrlInfo(appCertificateRevoke);
            // 证书过期校验
            /*if (expiredDate(appCertificateRevoke.getThisUpdate()))
            {
                appCertificateRevoke
                        .setRevokeFlag(Constants.CERTIFICATE_STATUS_EXPIRED);
            }*/

            appCertificateService.saveAppCertificate(appCertificateRevoke);
            checkRevokedCertificate(appCertificateRevoke.getSerialNumber());
        }

    }

    /**
     * 上传并更新证书文件.
     *
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private void uploadUpdateCertificateFile() throws Exception
    {

        // if +0 begin
        if (null != appCertificate)
        {

            AppCertificate appCert = appCertificateService
                    .findAppCertificateById(appCertificate.getId());

            String filePath = Constants.CERTIFICATE_SAVE_PATH + File.separator;

            // if +1 begin
            if (null == appCert)
            {

                setResult("exception",
                        getText("sdp.sce.dp.admin.certificate.add.null"));

            }
            else
            {
                // if +2 begin
                if (Constants.CERTIFICATE_SIGNED_FLAG.equals(appCert
                        .getCertificateType()))
                {
                    // 上传签名证书/密钥文件
                    uploadUpdateSignedPrivateKey(filePath, appCert);

                }
                else if (Constants.CERTIFICATE_REVOKE_FLAG.equals(appCert
                        .getCertificateType()))
                {

                    // 上传吊销列表
                    uploadUpdateRevokeCert(filePath, appCert);

                }
                // if +2 end

            }

            // if +1 end

        }
        else
        {
            setResult("exception",
                    getText("sdp.sce.dp.admin.certificate.add.null"));
        }
        // if +0 end

    }

    /**
     * 更新上传签名证书和私钥
     *
     * @param filePath 证书文件保存路径
     * @param appCert 证书对象
     * @throws Exception 异常
     * @return void
     * @exception throws [违例类型] [违例说明]
     */
    private void uploadUpdateSignedPrivateKey(String filePath,
            AppCertificate appCert) throws Exception
    {
        // 签名证书和私钥
        if (null != signed && null != privateKey)
        {
            // 删除证书文件
            FileUtil.deleteFile(filePath + appCert.getCertificateSaveName());

            // 删除私钥文件
            FileUtil.deleteFile(filePath + appCert.getPrivateKeySaveName());

            // 签名证书名
            String signedSaveName = getAppCertSaveName(appCert, signedFileName,
                    signed);

            // 签名证书密钥名

            String privateKeySaveName = getAppCertSaveName(appCert,
                    privateKeyFileName, privateKey);

            // 更新
            appCert.setCertificateSrcName(signedFileName);
            appCert.setCertificateSaveName(signedSaveName);
            // 证书
            X509Certificate ca = CertificateUtil
                    .readCa(Constants.CERTIFICATE_SAVE_PATH + File.separator
                            + signedSaveName);
            // 序列号
            appCert.setSerialNumber(ca.getSerialNumber().toString(
                    CertificateUtil.HEXADECIMAL));
            // 有效起始日期
            appCert.setNotBefore(ca.getNotBefore());
            // 有效终止日期
            appCert.setNotAfter(ca.getNotAfter());
            // 证书过期校验
           /* if (expiredDate(ca.getNotAfter()))
            {
                appCert.setRevokeFlag(Constants.CERTIFICATE_STATUS_EXPIRED);
            }*/
            appCert.setPrivateKeySrcName(privateKeyFileName);
            appCert.setPrivateKeySaveName(privateKeySaveName);
            appCert.setRevokeFlag(Constants.CERTIFICATE_STATUS_NORMAL);
            appCert.setUpdatedDate(new Date());
            User user = (User) request.getSession().getAttribute("user");
            if (null != user)
            {
                appCert.setCreatedUser(user.getUserName());
            }
            appCertificateService.updateAppCertificate(appCert);
        }
        else
        {
            Debug.logInfo("signed = null Or privateKey = null ", MODULE);
        }
    }

    /**
     * 更新上传吊销证书列表
     *
     * @param filePath 证书文件保存路径
     * @param appCert 证书对象
     * @throws Exception 异常
     * @return void
     * @exception throws [违例类型] [违例说明]
     */
    private void uploadUpdateRevokeCert(String filePath, AppCertificate appCert)
            throws Exception
    {
        if (null != revoke)
        {

            // 删除旧文件
            FileUtil.deleteFile(filePath + appCert.getCertificateSaveName());

            String revokeSaveName = getAppCertSaveName(appCert, revokeFileName,
                    revoke);
            // 更新
            appCert.setRevokeSrcName(revokeFileName);
            appCert.setRevokeSaveName(revokeSaveName);

            appCert.setRevokeFlag(Constants.CERTIFICATE_STATUS_NORMAL);
            appCert.setUpdatedDate(new Date());
            User user = (User) request.getSession().getAttribute("user");
            if (null != user)
            {
                appCert.setCreatedUser(user.getUserName());
            }
            appCert = getCrlInfo(appCert);
            // 证书过期校验
            /*if (expiredDate(appCert.getThisUpdate()))
            {
                appCert.setRevokeFlag(Constants.CERTIFICATE_STATUS_EXPIRED);
            }*/
            appCertificateService.updateAppCertificate(appCert);
            checkRevokedCertificate(appCert.getSerialNumber());
        }
        else
        {
            Debug.logInfo("revoke = null", MODULE);
        }
    }

    /**
     * 获得吊销证书列表crl信息
     *
     * @param crlAppCertificate 证书
     * @throws CertificateException
     * @throws CRLException
     * @throws IOException [参数说明]
     * @return AppCertificate 吊销证书列表
     * @exception throws [违例类型] [违例说明]
     */
    private AppCertificate getCrlInfo(AppCertificate crlAppCertificate)
            throws CertificateException, CRLException, IOException
    {

        // 证书
        X509CRL crl = CertificateUtil.readCrl(Constants.CERTIFICATE_SAVE_PATH
                + File.separator + crlAppCertificate.getRevokeSaveName());
        List<String> crlList = CertificateUtil.getCrlList(crl);
        // 序列号
        if (MethodsUtil.isNull(crlList))
        {
            crlAppCertificate.setSerialNumber("");
        }
        else
        {
            crlAppCertificate.setSerialNumber(crlList.toString()
                    .replace("[", "").replace("]", "").replace(" ", "")
                    .replace(",", " "));
        }
        // 本次更新时间
        crlAppCertificate.setThisUpdate(crl.getThisUpdate());
        // 下次更新时间
        crlAppCertificate.setNextUpdate(crl.getNextUpdate());

        return crlAppCertificate;

    }

    /**
     * 根据序列号、证书类型设置签名证书的吊销状态
     *
     * @param revokedSerialNumber 序列号
     *
     * @throws Exception [参数说明]
     * @return int 更新影响的数据记录条数
     * @exception throws [违例类型] [违例说明]
     */
    private int checkRevokedCertificate(String revokedSerialNumber)
            throws Exception
    {
        /**
         * 根据序列号、证书类型设置签名证书的吊销状态
         */
        String hql = "update AppCertificate ac set ac.revokeFlag =? where ac.certificateType=?";

        String serialNumber = "";

        if (null == revokedSerialNumber)
        {
            Debug.logInfo("revokedSerialNumber = null", MODULE);
        }
        else
        {
            // 把空格分隔的转换成逗号
            serialNumber = revokedSerialNumber.replace(" ", "','");
            serialNumber = "'" + serialNumber + "'";
        }
        // 拼hql，序列号
        if (!MethodsUtil.isNull(serialNumber))
        {
            hql += " and ac.serialNumber in (" + serialNumber + ") ";

        }

        // 更新数据库
        Object[] objects = new Object[]
        { Constants.CERTIFICATE_STATUS_REVOKE,
                Constants.CERTIFICATE_SIGNED_FLAG };

        int result = appCertificateService.updateAppCertificate(hql, objects);

        return result;

    }

    /**
     * 根据过期日期设置签名证书的吊销状态
     * @throws Exception
     */
    private int checkExpiredCertificate() throws Exception
    {

        final String hql = "update AppCertificate ac set ac.revokeFlag =? where (ac.notAfter <= ? or ac.thisUpdate <= ?)";

        // 更新数据库
        Date date = new Date();
        Object[] objects = new Object[]
        { Constants.CERTIFICATE_STATUS_EXPIRED, date, date };
        int result = appCertificateService.updateAppCertificate(hql, objects);

        return result;
    }

    /**
     * 根据证书名称、证书类型、证书标识查询证书列表
     *
     * @param query 证书查询对象
     * @throws Exception [参数说明]
     * @return Page<AppCertificate> 证书分页列表
     * @exception throws [违例类型] [违例说明]
     */
    private Page<AppCertificate> queryCertPage(AppCertificate query)
            throws Exception
    {

        // 查询语句
        String hql = "";
        // 参数对象
        Object[] objects = null;
        // 参数
        ArrayList<Object> certObjects = new ArrayList<Object>();

        // +0 begin
        if (null != query)
        {

            // 证书名称为空 +1 begin
            if (MethodsUtil.isNull(query.getCertificateSrcName()))
            {
                Debug.logInfo("query.getCertificateSrcName() = null", MODULE);
            }
            else
            {
                hql += " (apc.certificateSrcName like ? escape'/' or apc.privateKeySrcName like ? escape'/' or apc.revokeSrcName like ? escape'/')";

                // 参数
                String certName = SqlUtil.getLikeSql(query
                        .getCertificateSrcName());
                certObjects.add(certName);
                certObjects.add(certName);
                certObjects.add(certName);

            }
            // +1 end

            // 证书类型为空 +2 begin
            if (MethodsUtil.isNull(query.getCertificateType()))
            {
                Debug.logInfo("query.getCertificateType() = null", MODULE);
            }
            else
            {
                if (MethodsUtil.isNull(hql))
                {
                    hql += " apc.certificateType = ? ";
                }
                else
                {
                    hql += " and apc.certificateType = ? ";
                }

                // 参数
                certObjects.add(query.getCertificateType());
            }
            // +2 end

            // 证书吊销标识为空 +3 begin
            if (MethodsUtil.isNull(query.getRevokeFlag()))
            {
                Debug.logInfo("query.getRevokeFlag() = null", MODULE);
            }
            else
            {
                if (MethodsUtil.isNull(hql))
                {
                    hql += "  apc.revokeFlag = ?  ";
                }
                else
                {
                    hql += " and  apc.revokeFlag = ?  ";
                }
                // 参数
                certObjects.add(query.getRevokeFlag());
            }
            // +3 end

        }
        // +0 end

        // 拼hql语句 +4 begin
        if (MethodsUtil.isNull(hql))
        {

            hql = CERTIFICATE_LIST_HQL;
        }
        else
        {
            hql = CERTIFICATE_LIST_HQL + " where " + hql;
        }
        // +4 end

        // 按更新日期排序
        hql += "  order by apc.updatedDate desc";

        Debug.logVerbose("hql = " + hql);
        if (MethodsUtil.isNull(certObjects))
        {

            Debug.logInfo("certObjects = null");
        }
        else
        {
            objects = certObjects.toArray();
        }

        if (null == objects)
        {
            objects = new Object[0];
        }
        Page<AppCertificate> pageCert = appCertificateService
                .pageAppCertificate(page, hql, objects);

        return pageCert;

    }

    /**
     * 删除证书.
     *
     * @param appCert 证书
     * @return void
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    private void deleteCertificate(AppCertificate appCert) throws Exception
    {

        if (null != appCert)
        {

            // 证书保存的物理路径
            String filePath = Constants.CERTIFICATE_SAVE_PATH + File.separator;

            // 签名证书
            if (!MethodsUtil.isNull(appCert.getCertificateSaveName()))
            {
                FileUtil.deleteFile(filePath + appCert.getCertificateSaveName());

            }
            // 签名私钥
            if (!MethodsUtil.isNull(appCert.getPrivateKeySaveName()))
            {
                FileUtil.deleteFile(filePath + appCert.getPrivateKeySaveName());
            }
            // 吊销证书列表
            if (!MethodsUtil.isNull(appCert.getRevokeSaveName()))
            {
                FileUtil.deleteFile(filePath + appCert.getRevokeSaveName());
            }

            appCertificateService.deleteAppCertificate(appCert);

        }
        else
        {
            Debug.logInfo("appCert = null", MODULE);
        }

    }

    /**
     * 校验日期大小
     *
     * @param expiredDate 待校验的日期
     * @return boolean true 日期早于当前，false日期晚于当前
     * @exception throws [违例类型] [违例说明]
     */
    private boolean expiredDate(Date expiredDate)
    {
        boolean expiredFlag = expiredDate.before(new Date());
        return expiredFlag;
    }

    public File getSigned()
    {
        return signed;
    }

    public void setSigned(File signed)
    {
        this.signed = signed;
    }

    public String getSignedFileName()
    {
        return signedFileName;
    }

    public void setSignedFileName(String signedFileName)
    {
        this.signedFileName = signedFileName;
    }

    public File getPrivateKey()
    {
        return privateKey;
    }

    public void setPrivateKey(File privateKey)
    {
        this.privateKey = privateKey;
    }

    public String getPrivateKeyFileName()
    {
        return privateKeyFileName;
    }

    public void setPrivateKeyFileName(String privateKeyFileName)
    {
        this.privateKeyFileName = privateKeyFileName;
    }

    public File getRevoke()
    {
        return revoke;
    }

    public void setRevoke(File revoke)
    {
        this.revoke = revoke;
    }

    public String getRevokeFileName()
    {
        return revokeFileName;
    }

    public void setRevokeFileName(String revokeFileName)
    {
        this.revokeFileName = revokeFileName;
    }

    public AppCertificate getAppCertificate()
    {
        return appCertificate;
    }

    public void setAppCertificate(AppCertificate appCertificate)
    {
        this.appCertificate = appCertificate;
    }

    public Page<AppCertificate> getPage()
    {
        return page;
    }

    public void setPage(Page<AppCertificate> page)
    {
        this.page = page;
    }

    public AppCertificate getQueryAppCertificate()
    {
        return queryAppCertificate;
    }

    public void setQueryAppCertificate(AppCertificate queryAppCertificate)
    {
        this.queryAppCertificate = queryAppCertificate;
    }

    public String getIds()
    {
        return ids;
    }

    public void setIds(String ids)
    {
        this.ids = ids;
    }

    public String getPublicPrivateRevokeFlag()
    {
        return publicPrivateRevokeFlag;
    }

    public void setPublicPrivateRevokeFlag(String publicPrivateRevokeFlag)
    {
        this.publicPrivateRevokeFlag = publicPrivateRevokeFlag;
    }

}
