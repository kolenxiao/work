/*
 * 文件名称：PermissionInterceptor.java
 * 版    权：Shenzhen Coship Electronics
 * Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：liuyongjun
 * 创建时间：Sep 2, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.interceptor;

import java.util.Map;
import java.util.Set;

import com.coship.sdp.permission.entity.Resource;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.utils.Debug;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 操作权限拦截器.
 *
 * @author 905950 对没有登录、登录超时和没有权限进行拦截，对不合法请求，统一返回到登录页面。
 */
public class PermissionInterceptor extends AbstractInterceptor {

    /**
     * 序列号id.
     */
    private static final long serialVersionUID = -5391401690461765490L;

    /**
     * ClassName.
     */
    private static final String MODULE = PermissionInterceptor.class.getName();

    /**
     * 记录异常信息.
     */
    public String exception_msg = "";

    /**
     * 拦截action.
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Debug.logVerbose("PermissionInterceptor.intercept() start...", MODULE);
        // 不需要拦截的地址集
        String[] noNeedInterceptUrls =
        { "login", "logout", "user!gotoEditPassword",
                "user!isOldPasswordRight", "user!isUserNameSame",
                "user!gotoUserEdit", "user!searchUser",
                "user!searchUserByCondition", "user!showAdd",
                "user!userDetail", "user!viewUserDetail",
                "user!gotoUserselfEdit", "user!editPassword",
                "user!updateUserselfInformation", "user!editUser",
                "resource!searchRes", "resource!showAddRes",
                "resource!showModify", "resource!isResourceNameSame",
                "role!searchRole", "role!rolePermission", "role!showAdd",
                "role!showModify", "role!showRoleDetail",
                "role!isRoleNameSame", "oplogger!doList", "oplogger!exportLog",
                "dpStaff!doShow.action", "dpStaff!doList.action",
                "dpAppInfo!doSearchNotAuditList.action",
                "dpAppInfo!doApp.action", "dpAppInfo!doSearchList.action",
                "dpAppInfo!doDisplay.action", "dpAppInfo!doFind.action",
                "dpAppInfo!doCancelCommend.action",
                "appRecommend!doCommend.action","appRecommend!addTypeToRecomment.action",
                "appRecommend!doBatchCommend.action",
                "dpAppInfo!doOnOrDownline.action",
                "dpAppInfo!doOnlinePre.action", "dpdownload!doList.action",
                "dpdownload!doDownLoad.action", "dpdownload!doDetail.action",
                "dpdownload!doDisplay.action", "dpdownload!doDelete.action",
                "dpnews!doList.action", "dpnews!doDetail.action",
                "dpType!doList.action", "dpType!doDisplay.action",
                "dpType!doChangeVisibleStateType.action",
                "dpAppSubjectType!doDisplay.action","dpAppSubjectType!doChangeVisibleStateType.action",
                "dpAppInfo!doSubmitApp.action",
                "subjectAppinfoRelationAction!toEditSubject.action",
                "subjectAppinfoRelationAction!doSubjectAppSort.action",
                "appRecommend!doRecommendSort.action",
                "dpAppInfo!doAddApp.action",
                "dpAppInfo!doUniqueAppName.action",
                "dpAppInfo!doModifyApp.action",
                "dpAppInfo!doUpgradeApp.action",
                "dpAppInfo!doDeleteAttachment.action",
                "dpAppInfo!doValidateAttachment.action",
                "dpAppInfo!toAddRecommentList.action",
                "dpAppInfo!toAddAppToSubjectType.action",
                "dpAppInfo!getAppRelateByPlanIdAndPkgName.action",
                "dpAppInfo!getAppListForReplace.action",
                "dpType!isTypeNameUnique.action",
                "appStoreClient!toAdd.action",
                "appStoreClient!toModify.action",
                "appStoreClient!doDetail.action",
                "implicit!doAdd.action", "implicit!toModify.action",
                "implicit!doModify.action", "implicit!doUninstall.action",
                "implicit!doDetail.action","implicit!doList.action",
                "deviceInfo!doList.action","deviceInfo!viewDeviceDetail.action",
                "upload!doUpload.action",
                "planManage!goList","planManage!list","planManage!goCreatePlan","planManage!doDisplay","doSort",
                "planItemManage!listSelected","planItemManage!listUnSelectedPanel","planItemManage!listUnSelectedSubject","planItemManage!listUnSelectedDpType",
                "planItemManage!listUnSelectedSelf","planItemAppManage!listSelectedApp","planItemAppManage!listUnSelectedApp"};
        // 获得请求的URL
        String requestUrl = "";
        Action action = (Action) invocation.getAction();

        if (action instanceof BaseAction) {
            BaseAction as = (BaseAction) action;
            requestUrl = as.getRequest().getRequestURI();
            Debug.logVerbose("PermissionInterceptor intercept requestUrl: "
                    + requestUrl, MODULE);
        }
        // 如果是登录不进行拦截
        if (requestUrl.indexOf(noNeedInterceptUrls[0]) >= 0) {
            invocation.invoke();
            return null;
        }

        // 没有登录或session过期拦截
        ActionContext act = invocation.getInvocationContext();
        Map session = act.getSession();
        User user = (User) session.get("user");
        if (user == null) {
            return "gotologin";
        }
        for (String url : noNeedInterceptUrls) {
            if (requestUrl.indexOf(url) >= 0) {

                invocation.invoke();
                return null;
            }
        }
        // 修改结束

        // 操作请求权限拦截
        Map<String, Resource> m = (Map) session.get("userResMap");
        Set<String> keys = m.keySet();
        for (String key : keys) {
            Resource resource = m.get(key);

            // findbugs: modify by wangjiangliu 2012-06-29 PM
            // Set<Entry<String, Resource>> entrys = m.entrySet();
            // for (Entry<String, Resource> entry : entrys) {
            // Resource resource = entry.getValue();
            String realUrl = resource.getUrl(); // 用户拥有操作权限的URL
            int findIndex = realUrl.indexOf("?");
            if (findIndex >= 0) {
                realUrl = realUrl.substring(0, findIndex);
            }
            if (realUrl != null && realUrl.trim().length() > 0) {
                // System.out.println("realUrl: " + realUrl);
                if (requestUrl.indexOf(realUrl) >= 0) {
                    invocation.invoke();
                    return null;
                }
            }
        }

        exception_msg = "You do not have permission to perform this step";
        session.put("error", exception_msg);
        return "interceptorError";
    }

}
