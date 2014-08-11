/**
 * 文件名称：DpAppSignAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：应用签名信息处理action
 * 创 建 人：wangchenbo/906055
 * 创建时间：2012-9-23
 *
 * 修改记录：1. <修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.action.ap;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;
import com.coship.sdp.sce.dp.atachmentfile.service.AttachmentFileService;
import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;
import com.coship.sdp.sce.dp.certificate.service.AppCertificateService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.sign.entity.AppSign;
import com.coship.sdp.sce.dp.sign.service.SignService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.sce.dp.utils.MethodsUtil;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/*
 * 应用签名信操作请求的action类
 * @author wangchenbo/906055
 * @version [版本号, 2011-9-23]
 * @since [产品/模块版本]
 */
@Controller
public class DpAppSignAction extends BaseAction
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类名称.
     */
    private static final String MODULE = DpAppSignAction.class.getName();

    /**
     * 查询应用签名列表.
     */
    private static final String APP_SIGN_LIST = "from AppSign aps ";

    /**
     * 应用签名分页显示.
     */
    private Page<AppSign> page;

    /**
     * 应用签名信息服务接口
     */
    @Autowired
    private SignService signService;

    /**
     * 按应用名称查询
     */
    private String appName;

    /**
     * 按证书状态查询
     */
    private String revokeFlag;

	/**
	 * 应用类型列表.
	 */
	private List<DpType> dpTypeList;

    /**
     * 应用id串
     */
    private String ids;

    /**
     * 签名证书id
     */
    private String signedCertId;

	/**
	 * 应用类型接口.
	 */
    @Autowired
	private DpTypeService dpTypeService;

    /**
     * 证书服务对象.
     */
    @Autowired
    private AppCertificateService appCertificateService;

    /**
     * 附件接口对象.
     */
    @Autowired
    private AttachmentFileService attachmentFileService;

    /**
     * 查询应用.
     */
    private DpAppInfo queryAppInfo;

    /**
     * 查询应用.
     */
    private AppCertificate queryAppCert;

    private List<AppCertificate> signedPirvateAppCertificates;

    /**
     *
     * 应用签名信息列表.
     * @return String 页面跳转url
     * @exception throws [违例类型] [违例说明]
     */
    public String doList()
    {

        Debug.logVerbose("DpAppSignAction.doList() start...");

        try
        {
            if (null == page)
            {

                page = new Page<AppSign>();
            }

            page.setPageSize(limit);
            page.setCurrentPage(start);

            // 签名对象
            AppSign queryAppSign = new AppSign();
            queryAppSign.setAppCert(queryAppCert);
            queryAppSign.setDpAppInfo(queryAppInfo);

            page = querySignPage(queryAppSign);

            // 获取所有的应用分类列表
            searchTypeList();

            // 签名证书
            signedPirvateAppCertificates = appCertificateService
                    .findAppCertificateListByType(
                            Constants.CERTIFICATE_SIGNED_FLAG,
                            Constants.CERTIFICATE_STATUS_NORMAL);
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        Debug.logVerbose("DpAppSignAction.doList() end...");
        return "doList";
    }

    /**
     * 签名应用.
     *
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doAppSign()
    {

        Debug.logVerbose("DpAppSignAction.doAppSign() start...");

        if (MethodsUtil.isNull(ids))
        {

            Debug.logWarning("ids is null", MODULE);

            return doList();
        }

        if (MethodsUtil.isNull(signedCertId))
        {

            Debug.logWarning("signedCertId is null", MODULE);

            return doList();
        }

        String[] idArray = MethodsUtil.stringToArray(ids.replace(" ", ""),
                Constants.SPLIT_FLAG);

        try
        {
            // 签名证书
            AppCertificate appCert = appCertificateService
                    .findAppCertificateById(signedCertId);

            // 传入的签名证书不存在取最新的
            if (null == appCert)
            {
                appCert = appCertificateService.findAppCertificateByType(
                        Constants.CERTIFICATE_SIGNED_FLAG,
                        Constants.CERTIFICATE_STATUS_NORMAL);
            }

            if (null == appCert)
            {
                Debug.logWarning("appCert is null", MODULE);
                return doList();

            }else {

             // 签名证书
                String publicKeyPath = Constants.CERTIFICATE_SAVE_PATH
                        + File.separator + appCert.getCertificateSaveName();

                // 证书私钥
                String privateKeyPath = Constants.CERTIFICATE_SAVE_PATH
                        + File.separator + appCert.getPrivateKeySaveName();

                // 校验证书文件是否存在
                if (!MethodsUtil.isFileExists(publicKeyPath)
                        || !MethodsUtil.isFileExists(privateKeyPath)){


                    setResult("exception",getText("sdp.sce.dp.admin.certificate.file.not.exists"));
                    setResult("success", false);
                    write(JSONObject.fromObject(getResult()).toString());
                    return null;
                }

            }

            // 应用信息
            DpAppInfo dpAppInfo = new DpAppInfo();

            // 需要处理的应用列表
            for (String appId : idArray)
            {

                dpAppInfo.setId(appId);

                // 应用文件列表
                List<AttachmentFile> attFileList = attachmentFileService
                        .findAttachmentByAppIdFileDesc(dpAppInfo,
                                Constants.APPFILE);

                for (AttachmentFile attFile : attFileList)
                {

                    signedAppFile(appCert, attFile.getFileSaveName(), dpAppInfo);
                }

            }

        }
        catch (Exception e)
        {

            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }

        setResult("success", true);
        write(JSONObject.fromObject(getResult()).toString());
        return null;
    }

    private Page<AppSign> querySignPage(AppSign querySign) throws Exception
    {

        // 查询语句
        String hql = "";
        // 参数对象
        Object[] objects = null;
        // 参数
        ArrayList<Object> signObjects = new ArrayList<Object>();

        // +0 begin
        if (null != querySign)
        {

            // 证书为空 +1 begin
            if (null == querySign.getAppCert())
            {
                Debug.logInfo("querySign.getAppCert() = null", MODULE);
            }
            else
            {
                // 证书名称为空 +2 begin
                if (MethodsUtil.isNull(querySign.getAppCert()
                        .getCertificateSrcName()))
                {
                    Debug.logInfo(
                            "querySign.getAppCert().getCertificateSrcName() = null",
                            MODULE);
                }
                else
                {
                    hql += " from AppCertificate apc where (apc.certificateSrcName like ? escape'/' or apc.privateKeySrcName like ? escape'/') ";

                    // 参数
                    String certName = SqlUtil.getLikeSql(querySign.getAppCert()
                            .getCertificateSrcName());
                    signObjects.add(certName);
                    signObjects.add(certName);
                }
                // +2 end

                // 证书吊销标识为空 +3 begin
                if (MethodsUtil.isNull(querySign.getAppCert().getRevokeFlag()))
                {
                    Debug.logInfo(
                            "querySign.getAppCert().getRevokeFlag() = null",
                            MODULE);
                }
                else
                {
                    if (MethodsUtil.isNull(hql))
                    {
                        hql += "  from AppCertificate apc where apc.revokeFlag = ?  ";
                    }
                    else
                    {
                        hql += " and  apc.revokeFlag = ?  ";
                    }
                    // 参数
                    signObjects.add(querySign.getAppCert().getRevokeFlag());
                }
                // +3 end

                if (MethodsUtil.isNull(hql))
                {

                }
                else
                {
                    hql = " aps.appCert in ( " + hql + " )";
                }

            }
            // +1 end

            // 应用 +4 begin
            if (null == querySign.getDpAppInfo())
            {
                Debug.logInfo("querySign.getDpAppInfo() = null", MODULE);
            }
            else
            {
                if (MethodsUtil.isNull(querySign.getDpAppInfo().getAppName()))
                {
                    Debug.logInfo(
                            "querySign.getDpAppInfo().getAppName() = null",
                            MODULE);
                }
                else
                {
                    if (MethodsUtil.isNull(hql))
                    {
                        hql += " aps.dpAppInfo in( from DpAppInfo da where da.appName like ?  escape'/' or da.appNamePinyin like ? escape'/') ";
                    }
                    else
                    {
                        hql += " and aps.dpAppInfo in( from DpAppInfo da where da.appName like ?  escape'/' or da.appNamePinyin like ? escape'/')  ";
                    }

                    // 参数
                    String appName = SqlUtil.getLikeSql(querySign
                            .getDpAppInfo().getAppName());
                    signObjects.add(appName);
                    signObjects.add(appName);
                }

                if (querySign.getDpAppInfo().getDpType() != null
                		&& StringUtils.isNotEmpty(querySign.getDpAppInfo().getDpType().getId()))
                {
                	if (MethodsUtil.isNull(hql))
                    {
                        hql += " aps.dpAppInfo in( from DpAppInfo da where da.dpType.id=?) ";
                    }
                    else
                    {
                        hql += " and aps.dpAppInfo in( from DpAppInfo da where da.dpType.id=?)  ";
                    }
                	signObjects.add(querySign.getDpAppInfo().getDpType().getId());
                }
            }
            // +4 end

        }
        // +0 end

        // 拼hql语句 +4 begin
        if (MethodsUtil.isNull(hql))
        {

            hql = APP_SIGN_LIST;
        }
        else
        {
            hql = APP_SIGN_LIST + " where " + hql;
        }
        // +4 end

        // 按更新日期排序
        hql += "  order by aps.createTime desc";

        Debug.logVerbose("hql = " + hql);

        // 转换为数组
        if (MethodsUtil.isNull(signObjects))
        {

            Debug.logInfo("signObjects = null");
        }
        else
        {
            objects = signObjects.toArray();
        }

        if (null == objects)
        {
            objects = new Object[0];
        }

        // 查询应用名称、证书名称、证书状态
        Page<AppSign> pageSign = signService.pageAppSign(page, hql, objects);

        return pageSign;

    }

    /**
     * 根据证书、私钥签名应用文件.
     *
     * @param appCert 签名证书
     * @param sourceFileName 原始文件,及签名后的文件（不同目录）
     * @param appInfo 应用信息
     * @return boolean 成功true,失败false
     * @exception throws [违例类型] [违例说明]
     */

    private boolean signedAppFile(AppCertificate appCert,
            String sourceFileName, DpAppInfo appInfo)
    {

        boolean flag = true;

        try
        {

            // if +1 begin
            if (null == appCert || StringUtils.isEmpty(sourceFileName))
            {

                flag = false;

            }
            else
            {

                // 签名证书
                String publicKeyPath = Constants.CERTIFICATE_SAVE_PATH
                        + File.separator + appCert.getCertificateSaveName();

                // 证书私钥
                String privateKeyPath = Constants.CERTIFICATE_SAVE_PATH
                        + File.separator + appCert.getPrivateKeySaveName();

                // 校验证书文件是否存在
                if (MethodsUtil.isFileExists(publicKeyPath)
                        && MethodsUtil.isFileExists(privateKeyPath))
                {

                    // 未签名应用文件
                    String sourceFilePath = Constants.APP_FILE_SAVE_PATH
                            + File.separator + sourceFileName;

                    // 签名后的应用文件
                    String targetFilePath = Constants.SIGNED_APP_SAVE_PATH
                            + File.separator + sourceFileName;

                    // 首先把已签名的应用拷贝到临时目录
                    boolean copyFlag = FileUtil.copyFile4Transfer(new File(
                            targetFilePath), new File(sourceFilePath));

                    boolean signedFlag = false;

                    // 拷贝成功再签名
                    if (copyFlag)
                    {
                        signedFlag = MethodsUtil.signed(publicKeyPath,
                                privateKeyPath, sourceFilePath, targetFilePath);
                    }

                    // 签名成功保存记录到数据库，app对应证书\私钥.
                    if (signedFlag)
                    {

                        // 清除旧签名信息
                        signService.deltelSignByAppId(appInfo);

                        AppSign sign = new AppSign();
                        sign.setDpAppInfo(appInfo);
                        sign.setAppCert(appCert);
                        sign.setCreateTime(new Date());
                        signService.saveSign(sign);

                        // 删除签名的临时文件
                        FileUtil.deleteFile(sourceFilePath);

                    }

                }
                else
                {
                    // 签名证书不存在
                    flag = false;
                }
            }
            // if +1 end

        }
        catch (Exception e)
        {
            flag = false;
            Debug.logError(e, e.getMessage(), MODULE);
        }
        return flag;

    }

	/**
	 * 查询所有的应用分类列表
	 */
	private void searchTypeList() throws Exception
	{
		dpTypeList = dpTypeService.getGameAndAppTypeList();
	}

    public Page<AppSign> getPage()
    {
        return page;
    }

    public void setPage(Page<AppSign> page)
    {
        this.page = page;
    }

    public SignService getSignService()
    {
        return signService;
    }

    public void setSignService(SignService signService)
    {
        this.signService = signService;
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getRevokeFlag()
    {
        return revokeFlag;
    }

    public void setRevokeFlag(String revokeFlag)
    {
        this.revokeFlag = revokeFlag;
    }

    public String getIds()
    {
        return ids;
    }

    public void setIds(String ids)
    {
        this.ids = ids;
    }

    public DpAppInfo getQueryAppInfo()
    {
        return queryAppInfo;
    }

    public void setQueryAppInfo(DpAppInfo queryAppInfo)
    {
        this.queryAppInfo = queryAppInfo;
    }

    public AppCertificate getQueryAppCert()
    {
        return queryAppCert;
    }

    public void setQueryAppCert(AppCertificate queryAppCert)
    {
        this.queryAppCert = queryAppCert;
    }

    public List<AppCertificate> getSignedPirvateAppCertificates()
    {
        return signedPirvateAppCertificates;
    }

    public void setSignedPirvateAppCertificates(
            List<AppCertificate> signedPirvateAppCertificates)
    {
        this.signedPirvateAppCertificates = signedPirvateAppCertificates;
    }

    public String getSignedCertId()
    {
        return signedCertId;
    }

    public void setSignedCertId(String signedCertId)
    {
        this.signedCertId = signedCertId;
    }

	public List<DpType> getDpTypeList() {
		return dpTypeList;
	}

	public void setDpTypeList(List<DpType> dpTypeList) {
		this.dpTypeList = dpTypeList;
	}


}
