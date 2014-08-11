/*
 * 文件名称：AppstoreAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-13
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.action.appstore;

import java.io.File;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.sce.dp.appstore.entity.AppStoreClient;
import com.coship.sdp.sce.dp.appstore.service.AppStoreClientService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.sce.dp.util.ApkInfo;
import com.coship.sdp.sce.dp.util.ApkParserUtil;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 应用商店客户端版本管理action
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-13]
 * @since [产品/模块版本]
 */
@Controller
public class AppStoreClientAction extends BaseAction {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    /**
     * log日志打印前缀
     */
    private static final String MODULE_NAME = "AppStoreClientAction";

    /**
     * appStore客户端对象
     */
    private AppStoreClient appStoreClient;

    /**
     * 分页.
     */
    private Page<AppStoreClient> page;

    /**
     * id集合.
     */
    private String ids;

    /**
     * 客户端安装包
     */
    private File appStoreClientPackage;

    /**
     * 客户端安装包文件名称.
     */
    private String appStoreClientPackageFileName;

    /**
     * 安装包类型.
     */
    private String appStoreClientPackageContentType;

    /**
     * 终端系统类型
     */
    private List<DpType> androidDpTypeList;

    /**
     * 客户端服务类
     */
    @Autowired
    private AppStoreClientService appStoreClientService;

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
    public String doList() {
        page = new Page<AppStoreClient>();
        page.setPageSize(limit);
        page.setCurrentPage(start);
        try {
            String hql = "from AppStoreClient ac order by ac.createTime desc";
            appStoreClientService.listAppStoreClient(page, hql, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "doList";
    }

    /**
     * 跳转到appStore客户端新增页面
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
            exception_msg = "进入新增客户端页面失败.";
            return doList();
        }
        return "toEdit";
    }

    /**
     * 跳转到appStore客户端修改页面
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String toModify() {
        try {

            // 获取要修改的客户端对象
            AppStoreClient appStoreClientDbTemp = appStoreClientService
                    .findAppStoreClient(appStoreClient.getId());

            if (appStoreClientDbTemp == null) {
                exception_msg = "修改的记录已被删除.";
                return doList();
            }

            // 在线应用不许修改
            if (Constants.APP_STORE_ONLINE_STATUS.equals(appStoreClientDbTemp
                    .getStatus())) {
                exception_msg = "上架客户端不许修改";
                return doList();
            }
            appStoreClient = appStoreClientDbTemp;
            androidDpTypeList = dpTypeService
                    .findByParentTypeCode(DefaultTypeCodeConstants.ANDROID_TYPE_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            exception_msg = "进入修改客户端页面错误失败.";
            return doList();
        }
        return "toEdit";
    }

    /**
     * 保存appStore客户端新增
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doAdd() {
        try {
            User userSession = (User) request.getSession().getAttribute("user");

            if (appStoreClient != null && appStoreClientPackage != null) {

                String fileName = FileUtil
                        .resetFileName(this.appStoreClientPackageFileName);

                FileUtil.uploadFile(appStoreClientPackage,
                        Constants.APP_STORE_CLIENT_SAVE_PATH, fileName);

                String appStoreClientRealPath = Constants.APP_STORE_CLIENT_SAVE_PATH
                        + File.separator + fileName;

                ApkInfo apkInfo = processAppStoreClient(appStoreClientRealPath);

                if (apkInfo != null && apkInfo.getPublicKey() != null) {
                    appStoreClient.setVersionCode(apkInfo.getVersionCode());
                    boolean isExistVersion = appStoreClientService
                            .isExistVersion(appStoreClient.getVersionCode(),appStoreClient.getTeminalNum());
                    if (isExistVersion) {
                        // 删除上传的文件
                        File appStoreClientFile = new File(
                                appStoreClientRealPath);
                        if (appStoreClientFile.exists()) {
                            appStoreClientFile.delete();
                        }
                        exception_msg = "上传的版本已存在";
                        return ERROR;
                    }
                }

                appStoreClient.setStatus(Constants.APP_STORE_OFFLINE_STATUS);
                appStoreClient.setCreateTime(new Date());
                appStoreClient.setApkFileSavePath(fileName);
                appStoreClientService.saveAppStoreClient(appStoreClient);

                String logStr = "[" + userSession.getUserName() + "]新增客户端:["
                        + appStoreClient.getAppName() + "], 新增成功.";
                opLoggerService.info(
                        getText("sdp.sce.dp.admin.appstore.clientManage"),
                        logStr, getText(Constants.ADD));

            } else {
                Debug.logWarning("uploadInfo is null or upload is null",
                        MODULE_NAME);
                exception_msg = this
                        .getText("sdp.sce.dp.admin.appstore.add.apk.error");
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exception_msg = this.getText("sdp.sce.dp.admin.appstore.add.error");
            return ERROR;
        }
        return doList();
    }

    /**
     * @param appStoreClient
     * @param appStoreClientPackage [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private ApkInfo processAppStoreClient(String appStoreClientPackage) {
        File apk = new File(appStoreClientPackage);
        ApkInfo apkInfo = ApkParserUtil.parser(apk);
        return apkInfo;
    }

    /**
     * 保存appStore客户端修改
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    /**
     * <功能描述>
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doModify() {
        try {
            if (appStoreClient != null) {
                User userSession = (User) request.getSession().getAttribute(
                        "user");

                AppStoreClient appStoreClientDbTemp = appStoreClientService
                        .findAppStoreClient(appStoreClient.getId());

                if (appStoreClientDbTemp == null) {
                    exception_msg = "修改的记录已被删除.";
                    return doList();
                }

                // 在线应用不许修改
                if (Constants.APP_STORE_ONLINE_STATUS
                        .equals(appStoreClientDbTemp.getStatus())) {
                    exception_msg = "上架客户端不许修改";
                    return doList();
                }

                // 处理上传的文件
                if (appStoreClientPackage != null) {

                    String fileName = FileUtil
                            .resetFileName(this.appStoreClientPackageFileName);

                    FileUtil.uploadFile(appStoreClientPackage,
                            Constants.APP_STORE_CLIENT_SAVE_PATH, fileName);

                    String appStoreClientRealPath = Constants.APP_STORE_CLIENT_SAVE_PATH
                            + File.separator + fileName;

                    ApkInfo apkInfo = processAppStoreClient(appStoreClientRealPath);

                    boolean isExistVersion = appStoreClientService
                            .isExistVersion(apkInfo.getVersionCode(),appStoreClient.getTeminalNum());
                    if (isExistVersion) {
                        // 删除上传的文件
                        File appStoreClientFile = new File(
                                appStoreClientRealPath);
                        if (appStoreClientFile.exists()) {
                            appStoreClientFile.delete();
                        }
                        exception_msg = "上传的版本已存在";
                        return ERROR;
                    }

                    // 删除旧文件
                    File temp = new File(Constants.APP_STORE_CLIENT_SAVE_PATH
                            + File.separator
                            + appStoreClientDbTemp.getApkFileSavePath());
                    if (temp.exists()) {
                        temp.delete();
                    }

                    appStoreClient.setVersionCode(apkInfo.getVersionCode());
                    appStoreClient.setApkFileSavePath(fileName);
                }

                // 封装数据
                appStoreClientDbTemp.setAppName(appStoreClient.getAppName());
                appStoreClientDbTemp.setVersionCode(appStoreClient
                        .getVersionCode());
                appStoreClientDbTemp.setSystem(appStoreClient.getSystem());
                appStoreClientDbTemp.setTeminalNum(appStoreClient.getTeminalNum());
                appStoreClientDbTemp
                        .setStatus(Constants.APP_STORE_OFFLINE_STATUS);
                appStoreClientDbTemp.setCreateTime(new Date());

                if (appStoreClientPackage != null) {
                    appStoreClientDbTemp.setApkFileSavePath(appStoreClient
                            .getApkFileSavePath());
                }

                appStoreClientService
                        .updateAppStoreClient(appStoreClientDbTemp);

                String logStr = "[" + userSession.getUserName() + "]修改客户端:["
                        + appStoreClient.getAppName() + "], 修改成功.";
                opLoggerService.info(
                        getText("sdp.sce.dp.admin.appstore.clientManage"),
                        logStr, getText(Constants.MOD));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return doList();
    }

    /**
     * 应用客户端上下架操作
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doOnOrOffLine() {
        try {
            if (appStoreClient != null) {
                User userSession = (User) request.getSession().getAttribute(
                        "user");

                AppStoreClient appStoreClientDbTemp = appStoreClientService
                        .findAppStoreClient(appStoreClient.getId());

                if (appStoreClientDbTemp == null) {
                    exception_msg = "修改的记录已被删除.";
                    return ERROR;
                }

                AppStoreClient onlineClient = appStoreClientService
                        .getOnlineAppStoreClient(appStoreClientDbTemp.getTeminalNum());

                if (onlineClient != null
                        && ("1".equals(appStoreClient.getStatus()))
                        && (onlineClient.getVersionCode() > appStoreClientDbTemp
                                .getVersionCode())) {
                    exception_msg = "上架的版本低于当前已上架的版本,不允许上架.";
                    return ERROR;
                }

                if (onlineClient != null) {
                    onlineClient.setStatus("2");
                    appStoreClientService.onOrOffAppStoreClient(onlineClient);
                }
                appStoreClientDbTemp.setStatus(appStoreClient.getStatus());
                appStoreClientService.onOrOffAppStoreClient(appStoreClientDbTemp);
                String logStr = "[" + userSession.getUserName() + "]修改客户端:["
                        + appStoreClient.getAppName() + "], 修改成功.";
                opLoggerService.info(
                        getText("sdp.sce.dp.admin.appstore.clientManage"),
                        logStr, getText(Constants.MOD));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doList();
    }

    /**
     * 批量删除appStroe客户端
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doDelete() {
        try {
            User userSession = (User) request.getSession().getAttribute("user");
            if (StringUtils.isNotEmpty(ids)) {
                String[] idArr = ids.split(",");
                for (String idTemp : idArr) {
                    AppStoreClient appStoreClientTemp = appStoreClientService
                            .findAppStoreClient(idTemp.trim());
                    if (appStoreClientTemp != null
                            && Constants.APP_STORE_ONLINE_STATUS
                                    .equals(appStoreClientTemp.getStatus())) {
                        setResult("msg", "客户端已上线不许删除.");
                        write(JSONObject.fromObject(getResult()).toString());
                        return null;
                    } else {
                        appStoreClientService
                                .deleteAppStoreClient(appStoreClientTemp);

                        String appStoreClientRealPath = Constants.APP_STORE_CLIENT_SAVE_PATH
                                + File.separator
                                + appStoreClientTemp.getApkFileSavePath();

                        File appStoreClientFile = new File(
                                appStoreClientRealPath);
                        if (appStoreClientFile.exists()) {
                            appStoreClientFile.delete();
                        }

                        String logStr = "[" + userSession.getUserName()
                                + "]删除 客户端:[" + appStoreClientTemp.getAppName()
                                + "],删除成功.";
                        opLoggerService
                                .info(getText("sdp.sce.dp.admin.appstore.clientManage"),
                                        logStr, getText(Constants.DEL));
                    }
                }
            } else {
                setResult("msg", "请选择要端删除客户.");
                write(JSONObject.fromObject(getResult()).toString());
                exception_msg = getText("sdp.sce.dp.admin.common.data.delete.exception");
                return null;
            }
        } catch (Exception e) {
            setResult("msg", "客户端删除失败.");
            write(JSONObject.fromObject(getResult()).toString());
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            return null;
        }
        setResult("success", true);
        write(JSONObject.fromObject(getResult()).toString());
        Debug.logVerbose("AppStoreClientAction.doDelete() end...");
        return null;
    }

    /**
     * 查看详情
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doDetail() {
        try {
            appStoreClient = appStoreClientService
                    .findAppStoreClient(appStoreClient.getId());
        } catch (Exception e) {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
        }
        return "doDetail";
    }

    public AppStoreClient getAppStoreClient() {
        return appStoreClient;
    }

    public void setAppStoreClient(AppStoreClient appStoreClient) {
        this.appStoreClient = appStoreClient;
    }

    public Page<AppStoreClient> getPage() {
        return page;
    }

    public void setPage(Page<AppStoreClient> page) {
        this.page = page;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public File getAppStoreClientPackage() {
        return appStoreClientPackage;
    }

    public void setAppStoreClientPackage(File appStoreClientPackage) {
        this.appStoreClientPackage = appStoreClientPackage;
    }

    public String getAppStoreClientPackageFileName() {
        return appStoreClientPackageFileName;
    }

    public void setAppStoreClientPackageFileName(
            String appStoreClientPackageFileName) {
        this.appStoreClientPackageFileName = appStoreClientPackageFileName;
    }

    public String getAppStoreClientPackageContentType() {
        return appStoreClientPackageContentType;
    }

    public void setAppStoreClientPackageContentType(
            String appStoreClientPackageContentType) {
        this.appStoreClientPackageContentType = appStoreClientPackageContentType;
    }

    public List<DpType> getAndroidDpTypeList() {
        return androidDpTypeList;
    }

    public void setAndroidDpTypeList(List<DpType> androidDpTypeList) {
        this.androidDpTypeList = androidDpTypeList;
    }
}
