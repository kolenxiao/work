package com.coship.sdp.sce.dp.action.implicitapp;

import java.io.File;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;
import com.coship.sdp.sce.dp.certificate.service.AppCertificateService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.implicit.entity.ImplicitApp;
import com.coship.sdp.sce.dp.implicit.service.ImplicitAppService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.sce.dp.util.ApkInfo;
import com.coship.sdp.sce.dp.util.ApkParserUtil;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.sce.dp.utils.MethodsUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

@Controller
public class ImplicitAppAction extends BaseAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	 /**
     * log日志打印前缀
     */
    private static final String MODULE_NAME = "ImplicitAppAction";

    /**
     * 分页.
     */
    private Page<ImplicitApp> page;

    /**
     * id集合.
     */
    private String ids;

    /**
     * 隐式应用安装包
     */
    private File implicitAppPackage;

    /**
     * 隐式应用文件名称.
     */
    private String implicitAppPackageFileName;

    /**
     * 隐式应用类型.
     */
    private String implicitAppPackageContentType;

    /**
     * 隐式应用查询对象
     */
    private ImplicitApp queryImplicitApp;

    /**
     * 隐式应用对象
     */
    private ImplicitApp implicitApp;

    /**
     * 隐式类型
     */
    private String impType;

    /**
     * 终端系统类型
     */
    private List<DpType> androidDpTypeList;

    /**
     * 客户端服务类
     */
    @Autowired
    private ImplicitAppService implicitAppService;

	/**
	 * 证书服务对象.
	 */
	@Autowired
	private AppCertificateService appCertificateService;

    /**
     * 分类信息服务类
     */
    @Autowired
    private DpTypeService dpTypeService;

    /**
     * 操作日志对象.
     */
    @Autowired
    private OpLoggerService opLoggerService;

    /**
     * 获取客户端分页数据
     * @return 返回列表页面
     */
    public String doList()
    {
    	if (queryImplicitApp == null)
    	{
    		queryImplicitApp = new ImplicitApp();
    	}
        page = new Page<ImplicitApp>();
        page.setPageSize(limit);
        page.setCurrentPage(start);
        try {
            implicitAppService.getImplicitAppList(page, queryImplicitApp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "doList";
    }

    /**
     * 跳转到隐式应用新增页面
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String toAdd() {
        try {
            // 获取系统版本列表
            androidDpTypeList = dpTypeService
                    .findByParentTypeCode(DefaultTypeCodeConstants.ANDROID_TYPE_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            exception_msg = "进入新增隐式应用页面失败.";
            return doList();
        }
        return "toEdit";
    }

    /**
     * 跳转到隐式应用修改页面
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String toModify() {
        try {

            // 获取要修改的客户端对象
            ImplicitApp implicitAppTemp = implicitAppService
            	.findImplicitAppById(implicitApp.getId());

            if (implicitAppTemp == null) {
                exception_msg = "修改的记录已被删除.";
                return ERROR;
            }

            androidDpTypeList = dpTypeService
            	.findByParentTypeCode(DefaultTypeCodeConstants.ANDROID_TYPE_CODE);

            // 启动的隐式应用不许修改
            if (Constants.IMPLICIT_APP_ACTIVATE_STATUS.equals(implicitAppTemp
                    .getStatus())) {
                exception_msg = "启动的隐式应用不允许修改";
                return ERROR;
            }
            implicitApp = implicitAppTemp;
        } catch (Exception e) {
            e.printStackTrace();
            exception_msg = "进入修改隐式应用的页面失败.";
            return ERROR;
        }
        return "toEdit";
    }


    /**
     * 新增隐式应用
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doAdd() {
        try {
            User userSession = (User) request.getSession().getAttribute("user");

            if (implicitApp != null && implicitAppPackage != null) {


            		// 升级和安装类型的隐式应用
            		String fileName = FileUtil
                    	.resetFileName(this.implicitAppPackageFileName);

		            FileUtil.uploadFile(implicitAppPackage,
		                    Constants.IMPLICIT_APP_SAVE_PATH, fileName);

		            String implicitAppRealPath = Constants.IMPLICIT_APP_SAVE_PATH
		                    + File.separator + fileName;
		            ApkInfo apkInfo = processImplicitApp(implicitAppRealPath);

		            boolean signed = false;
		   			// 验证apk的xml信息是否合法
		   			if (validateApkFile(apkInfo))
		   			{
		   				deleteApk(fileName);
		   			    return ERROR;
		   			}

		   			if (Constants.IMPLICIT_INSTALL.equals(implicitApp.getImplicitType()))
		   			{
		   				// 安装操作，需要校验是否上传了同个包名的文件
		   				boolean isExistPackage = implicitAppService
		   					.isApkPackageExist(apkInfo.getPackageName(),implicitApp.getTeminalNum());
		   				if (isExistPackage)
		   				{
		   					// 删除上传的文件
		   					deleteApk(fileName);
		   					exception_msg = "上传的版本已存在";
		   					return ERROR;
		   				}
		   			}
		   			else if (Constants.IMPLICIT_UPGRADE.equals(implicitApp.getImplicitType()))
		   			{
		   				// 升级的时候判断相同包名的隐式应用是否小于升级的versionCode
		   				boolean flag = isImplicitAppUpgradeLegal(apkInfo,implicitApp.getTeminalNum());
		   				if (flag)
		   				{
		   					return ERROR;
		   				}
		   			}

		   			AppCertificate appCert = appCertificateService
                    .getDefaultCertificate(
                            Constants.CERTIFICATE_SIGNED_FLAG,
                            Constants.CERTIFICATE_STATUS_NORMAL);

		            signed = signedAppFile(fileName,appCert);
					if (!signed)
					{
						// 服务器内部出错
						exception_msg = implicitAppPackageFileName + getText("sdp.sce.dp.admin.ap.sign.error");
						deleteApk(fileName);
						return ERROR;
					}

					// 持久化隐式应用操作
					saveImplicitApp(fileName, apkInfo,appCert);

		            String logStr = "[" + userSession.getUserName() + "]新增隐式应用:["
		                    + implicitApp.getAppName() + "], 新增成功.";
		            opLoggerService.info("隐式应用管理",
		                    logStr, getText(Constants.ADD));

            } else {
            	if (Constants.IMPLICIT_UNINSTALL.equals(implicitApp.getImplicitType()))
            	{
            		// 如果是新增卸载应用，那么直接判断包名，并保存一条记录
            		if (StringUtils.isNotEmpty(implicitApp.getAppFilePackage()))
            		{
            			boolean ret = implicitAppService.isApkPackageExist(implicitApp.getAppFilePackage(), implicitApp.getTeminalNum());
            			if(!ret){
                			implicitApp.setVersionCode(0);
                	        implicitApp.setStatus(Constants.IMPLICIT_APP_FORBID_STATUS);
                	        implicitApp.setCreateTime(new Date());
                	        implicitAppService.saveImplicitApp(implicitApp);
            			}else{
            				impType = implicitApp.getImplicitType();
                			exception_msg = "该应用的卸载记录已存在";
                			return ERROR;
            			}
            		}
            		else
            		{
            			exception_msg = "填写的包名不能为空";
   	   					return ERROR;
            		}
            	}
            	else
            	{
            		Debug.logWarning("uploadInfo is null or upload is null",
            				MODULE_NAME);
            		exception_msg = this
            		.getText("sdp.sce.dp.admin.appstore.add.apk.error");
            		return ERROR;
            	}

            }
        } catch (Exception e) {
            e.printStackTrace();
            exception_msg = this.getText("sdp.sce.dp.admin.appstore.add.error");
            return ERROR;
        }
        return doList();
    }

    /**
     * 修改隐式应用
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doModify() {
        try {
            if (implicitApp != null) {
                User userSession = (User) request.getSession().getAttribute(
                        "user");

                ImplicitApp implicitAppDbTemp = implicitAppService
                	.findImplicitAppById(implicitApp.getId());

                if (implicitAppDbTemp == null) {
                    exception_msg = "修改的记录已被删除.";
                    return ERROR;
                }

                // 启用的隐式应用不许修改
                if (Constants.IMPLICIT_APP_ACTIVATE_STATUS
                        .equals(implicitAppDbTemp.getStatus())) {
                    exception_msg = "启用的隐式应用不许修改";
                    return ERROR;
                }

                boolean signed = false;
                // 处理上传的文件
                if (implicitAppPackage != null)
                {
                	String fileName = FileUtil
                            .resetFileName(this.implicitAppPackageFileName);

                    FileUtil.uploadFile(implicitAppPackage,
                            Constants.IMPLICIT_APP_SAVE_PATH, fileName);

                    String implicitAppRealPath = Constants.IMPLICIT_APP_SAVE_PATH
                            + File.separator + fileName;

                    ApkInfo apkInfo = processImplicitApp(implicitAppRealPath);

	   	   			// 验证apk的xml信息是否合法
	   	   			if (validateApkFile(apkInfo))
	   	   			{
	   	   				deleteApk(fileName);
	   	   			    return ERROR;
	   	   			}

                    boolean isExistApk = implicitAppService
                    		.isApkExistByPackageAndImpType(apkInfo.getPackageName()
                    				, implicitApp.getImplicitType(), implicitApp.getId());
                    if (isExistApk)
                    {
                        // 删除上传的文件
                        deleteApk(fileName);
                        exception_msg = "上传的版本已存在";
                        return ERROR;
                    }

                    AppCertificate appCert = appCertificateService
                    .getDefaultCertificate(
                            Constants.CERTIFICATE_SIGNED_FLAG,
                            Constants.CERTIFICATE_STATUS_NORMAL);

                    signed = signedAppFile(fileName,appCert);
    				if (!signed)
    				{
    					// 服务器内部出错
    					exception_msg = implicitAppPackageFileName + getText("sdp.sce.dp.admin.ap.sign.error");
    					deleteApk(fileName);
    					return ERROR;
    				}
    				implicitAppDbTemp.setAppCertId(appCert.getId());
                    // 删除旧文件
    				deleteSignedApk(implicitAppDbTemp.getApkFileSavePath());

                    implicitAppDbTemp.setVersionCode(apkInfo.getVersionCode());
                    implicitAppDbTemp.setApkFileSavePath(fileName);
                    implicitAppDbTemp.setFileName(implicitAppPackageFileName);
                    implicitAppDbTemp.setAppFilePackage(apkInfo.getPackageName());
                    implicitAppDbTemp.setSystem(String.valueOf(apkInfo.getMinSdkVersion()));
                }
                else
                {
                	// 判断选择的隐式类型是否已经存在记录
            		boolean isExistApk = implicitAppService
            			.isApkExistByPackageAndImpType(implicitAppDbTemp.getAppFilePackage()
            				, implicitApp.getImplicitType(), implicitApp.getId());
            		if (isExistApk)
            		{
            			exception_msg = "上传的版本已存在";
            			return ERROR;
            		}

                	if (Constants.IMPLICIT_UNINSTALL.equals(implicitApp.getImplicitType()))
                	{
                		// 应用升级的时候执行的修改操作
                		if (Constants.TEMINALNUM_MSTAR_HISI.equals(implicitApp.getTeminalNum()))
    		            {
                			implicitAppDbTemp.setTeminalNum(Constants.TEMINALNUM_MSTAR_AND_HISI);
    		            }
                		else
    		   	   		{
                			implicitAppDbTemp.setTeminalNum(implicitApp.getTeminalNum());
    		   	   		}
                		implicitAppDbTemp.setAppName(implicitApp.getAppName());
                		implicitAppDbTemp.setDescription(implicitApp.getDescription());
                		implicitAppDbTemp.setImplicitType(implicitApp.getImplicitType());
                		implicitAppDbTemp.setAppFilePackage(implicitApp.getAppFilePackage());
                		implicitAppDbTemp.setStatus(Constants.IMPLICIT_APP_FORBID_STATUS);
                		implicitAppService.updateImplicitApp(implicitAppDbTemp);

                		return doList();
                	}


                }

                if (Constants.TEMINALNUM_MSTAR_HISI.equals(implicitApp.getTeminalNum()))
                {
                	implicitApp.setTeminalNum(Constants.TEMINALNUM_MSTAR_AND_HISI);
                }

                // 封装数据
                implicitAppDbTemp.setAppName(implicitApp.getAppName());
                implicitAppDbTemp
                        .setStatus(Constants.IMPLICIT_APP_FORBID_STATUS);
                implicitAppDbTemp.setCreateTime(new Date());
                implicitAppDbTemp.setDescription(implicitApp.getDescription());
                implicitAppDbTemp.setImplicitType(implicitApp.getImplicitType());
                implicitAppDbTemp.setTeminalNum(implicitApp.getTeminalNum());

                implicitAppService.updateImplicitApp(implicitAppDbTemp);

                String logStr = "[" + userSession.getUserName() + "]修改隐式应用:["
                        + implicitApp.getAppName() + "], 修改成功.";
                opLoggerService.info("隐式应用管理", logStr, getText(Constants.MOD));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return doList();
    }

    /**
     * 批量删除隐式应用
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doDelete() {
        try {
        	Debug.logVerbose("ImplicitAppAction.doDelete() end...");
            User userSession = (User) request.getSession().getAttribute("user");
            if (StringUtils.isNotEmpty(ids)) {
                String[] idArr = ids.split(",");
                for (String idTemp : idArr) {
                    ImplicitApp implicitAppTemp = implicitAppService
                    	.findImplicitAppById(StringUtils.trim(idTemp));
                    if (implicitAppTemp != null && Constants.IMPLICIT_APP_ACTIVATE_STATUS
                                    .equals(implicitAppTemp.getStatus()))
                    {
                    	// 启动的隐式应用不能删除
                        setResult("msg", "隐式应用启动不许删除.");
                        write(JSONObject.fromObject(getResult()).toString());
                        return null;
                    }

                	String fileSavePath = implicitAppTemp.getApkFileSavePath();

                	if (!Constants.IMPLICIT_UNINSTALL.equals(implicitAppTemp.getImplicitType()))
                	{
                		// 删除升级或者安装的隐式应用时，判断相同包名和apk存储全路径是否有记录
                		boolean flag = implicitAppService
                			.isApkExistByPackageNameAndSavePath(implicitAppTemp);

                		// 删除apk文件
                		if (!flag)
                		{
                			deleteSignedApk(fileSavePath);
                		}
                	}

                	implicitAppService.deleteImplicitApp(implicitAppTemp);

                    String logStr = "[" + userSession.getUserName()
                            + "]删除 隐式应用:[" + implicitAppTemp.getAppName()
                            + "],删除成功.";
                    opLoggerService
                            .info(getText("sdp.sce.dp.admin.appstore.clientManage"),
                                    logStr, getText(Constants.DEL));
                }
            } else {
                setResult("msg", "请选择要删除的隐式应用.");
                write(JSONObject.fromObject(getResult()).toString());
                exception_msg = getText("sdp.sce.dp.admin.common.data.delete.exception");
                return null;
            }
        } catch (Exception e) {
            setResult("msg", "隐式应用删除失败.");
            write(JSONObject.fromObject(getResult()).toString());
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            return null;
        }
        setResult("success", true);
        write(JSONObject.fromObject(getResult()).toString());
        Debug.logVerbose("ImplicitAppAction.doDelete() end...");
        return null;
    }

    /**
     * 查看隐式应用详情
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doDetail() {
        try {
        	implicitApp = implicitAppService.findImplicitAppById(implicitApp.getId());
        	// 设置隐式应用最低版本的VO对象
        	setTypeName();
        } catch (Exception e) {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
        }
        return "doDetail";
    }

    /**
     * 隐式应用启动、禁止操作
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doOnOrOffLine() {
        try {
            if (implicitApp != null) {
                User userSession = (User) request.getSession().getAttribute(
                        "user");
                ImplicitApp implicitAppDbTemp = implicitAppService
            		.findImplicitAppById(implicitApp.getId());

                if (implicitAppDbTemp == null) {
                	setResult("msg", "修改的记录已被删除.");
                    write(JSONObject.fromObject(getResult()).toString());
                    return null;
                }

                // 判断如果是启用的话，要将相同的包名和相同终端型号的状态置为停用
                if (Constants.IMPLICIT_APP_ACTIVATE_STATUS.equals(implicitApp.getStatus()))
                {
                	implicitAppService.offImplicitApp(implicitAppDbTemp
                			.getAppFilePackage(),implicitAppDbTemp.getTeminalNum());
                }

                implicitAppService.onOrOffImplicitApp(implicitApp);

                String logStr = "[" + userSession.getUserName() + "]修改:["
                        + implicitApp.getAppName() + "], 修改成功.";
                opLoggerService.info(
                        getText("sdp.sce.dp.admin.appstore.clientManage"),
                        logStr, "隐式应用状态修改");
            }
        } catch (Exception e) {
            e.printStackTrace();

            setResult("msg", "执行异常.");
            write(JSONObject.fromObject(getResult()).toString());
            return null;
        }

        setResult("success", true);
        write(JSONObject.fromObject(getResult()).toString());
        return null;
    }

    /**
     * 验证升级的应用版本是否比相同包名的应用要低
     * @param apkInfo 上传的apk解析后的对象
     * @return true:上传的versionCode版本比数据库的低，非法
     * 		   false:上传的versionCode版本比数据库的高，合法
     */
    private boolean isImplicitAppUpgradeLegal(ApkInfo apkInfo,String teminalNum) throws Exception
    {
    	List<ImplicitApp> impList = implicitAppService
    		.getImplicitAppListByPackageName(apkInfo.getPackageName(),teminalNum);
    	if (CollectionUtils.isNotEmpty(impList))
    	{
    		for (ImplicitApp imp : impList)
    		{
    			if (imp.getVersionCode() >= apkInfo.getVersionCode())
    			{
    				exception_msg = "上传的apk版本低于原有的apk版本，请重新上传！";
    				return true;
    			}
    		}
    	}
    	return false;
    }

    /**
     * 保存隐式应用
     */
    private void saveImplicitApp(String fileName, ApkInfo apkInfo,AppCertificate appCert) throws Exception
    {
		implicitApp.setVersionCode(apkInfo.getVersionCode());
		implicitApp.setSystem(String.valueOf(apkInfo.getMinSdkVersion()));
        implicitApp.setStatus(Constants.IMPLICIT_APP_FORBID_STATUS);
        implicitApp.setCreateTime(new Date());
        implicitApp.setFileName(implicitAppPackageFileName);
        implicitApp.setApkFileSavePath(fileName);
        implicitApp.setAppFilePackage(apkInfo.getPackageName());
        implicitApp.setAppCertId(appCert.getId());
        implicitAppService.saveImplicitApp(implicitApp);
    }

    private void setTypeName() throws Exception
    {
    	if (implicitApp != null)
    	{
    		DpType type = dpTypeService.findByTypeCode(implicitApp.getSystem());
			if (type != null)
			{
				implicitApp.setSystemVo(type.getTypeName());
			}
    	}
    }

    /**
     * 根据证书、私钥签名应用文件.
     *
     * @param publicKeyName 签名证书
     * @param privateKeyName 签名证书私钥
     * @param sourceFileName 原始文件,及签名后的文件（不同目录）
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private boolean signedAppFile(String sourceFileName,AppCertificate appCert)
    {
        boolean flag = true;

        try
        {
            // if +1 begin
            if (null == appCert
                    || StringUtils.isEmpty(sourceFileName))
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

                // 未签名应用文件
                String sourceFilePath = Constants.IMPLICIT_APP_SAVE_PATH
                        + File.separator + sourceFileName;

                // 签名后的应用文件
                String targetFilePath = Constants.SIGNED_APP_SAVE_PATH
                        + File.separator + sourceFileName;

                boolean signedFlag = MethodsUtil.signed(publicKeyPath,
                        privateKeyPath, sourceFilePath, targetFilePath);

                if (!signedFlag)
                {
                	flag = false;
                }

            }
            // if +1 end

        }
        catch (Exception e)
        {
            flag = false;
            Debug.logError(e, e.getMessage(), MODULE_NAME);
        }
        return flag;

    }

    /**
	 * 验证解析的apk是否合法
	 * true: 表明不合法
	 */
	private boolean validateApkFile(ApkInfo apkInfo)
	{
	    if (null == apkInfo)
        {
            exception_msg = "apk文件无法解析";

            return true;
        }

        // 判断apk中versionCode是否有填写
        if (apkInfo.getVersionCode() == 0)
        {
            exception_msg = "apk不规范，AndroidMantifest.xml"
                    + " 必须包含android:versionCode字段";
            return true;
        }

        if (apkInfo.getMinSdkVersion() == 0)
        {
            exception_msg = "apk不规范，AndroidMantifest.xml"
                    + " 必须包含uses-sdk标签";
            return true;
        }

        // 应用包名
        String apkPackageName = apkInfo.getPackageName();

        // 如果解析的包名为空，则直接返回空的附件予以标记不能解析包
        if (apkPackageName == null)
        {
            exception_msg = getText("sdp.sce.dp.admin.ap.package.not.found");
            return true;
        }

        return false;
	}

	/**
	 * 删除未签名的apk
	 */
	private void deleteApk(String fileSaveName)
	{
	    FileUtil.forceDelete(Constants.IMPLICIT_APP_SAVE_PATH + File.separator
                + fileSaveName);
	}

	/**
	 * 删除已签名的apk
	 */
	private void deleteSignedApk(String fileSaveName)
	{
		FileUtil.forceDelete(Constants.SIGNED_APP_SAVE_PATH + File.separator
                + fileSaveName);
	}

    /**
     * @param appStoreClient
     * @param appStoreClientPackage [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private ApkInfo processImplicitApp(String implicitAppPackage) {
        File apk = new File(implicitAppPackage);
        ApkInfo apkInfo = ApkParserUtil.parser(apk);
        return apkInfo;
    }


	public Page<ImplicitApp> getPage() {
		return page;
	}

	public void setPage(Page<ImplicitApp> page) {
		this.page = page;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public File getImplicitAppPackage() {
		return implicitAppPackage;
	}

	public void setImplicitAppPackage(File implicitAppPackage) {
		this.implicitAppPackage = implicitAppPackage;
	}

	public String getImplicitAppPackageFileName() {
		return implicitAppPackageFileName;
	}

	public void setImplicitAppPackageFileName(String implicitAppPackageFileName) {
		this.implicitAppPackageFileName = implicitAppPackageFileName;
	}

	public String getImplicitAppPackageContentType() {
		return implicitAppPackageContentType;
	}

	public void setImplicitAppPackageContentType(
			String implicitAppPackageContentType) {
		this.implicitAppPackageContentType = implicitAppPackageContentType;
	}

	public ImplicitApp getQueryImplicitApp() {
		return queryImplicitApp;
	}

	public void setQueryImplicitApp(ImplicitApp queryImplicitApp) {
		this.queryImplicitApp = queryImplicitApp;
	}

	public List<DpType> getAndroidDpTypeList() {
		return androidDpTypeList;
	}

	public void setAndroidDpTypeList(List<DpType> androidDpTypeList) {
		this.androidDpTypeList = androidDpTypeList;
	}

	public DpTypeService getDpTypeService() {
		return dpTypeService;
	}

	public void setDpTypeService(DpTypeService dpTypeService) {
		this.dpTypeService = dpTypeService;
	}

	public ImplicitApp getImplicitApp() {
		return implicitApp;
	}

	public void setImplicitApp(ImplicitApp implicitApp) {
		this.implicitApp = implicitApp;
	}

	public String getImpType() {
		return impType;
	}

	public void setImpType(String impType) {
		this.impType = impType;
	}





















}
