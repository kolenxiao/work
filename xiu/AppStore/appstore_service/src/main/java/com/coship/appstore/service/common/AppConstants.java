/*
 * 文 件 名：Constants.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：wangchenbo/906055
 * 修改时间：2012-9-6
 * 修改内容：常量类
 */
package com.coship.appstore.service.common;

/**
 * 应用商店服务端常量类.
 *
 * @author wangchenbo/906055
 * @version [版本号, 2012-9-6]
 * @since [应用商店/V100R001B010]
 */
public class AppConstants
{
    /**
     * 时间转换格式.
     */
    public static final String TIMEF_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 数字零.
     */
    public static final int ZERO = 0;

    /**
     * 成功信息.
     */
    public static final String MSG_SUCCESS = "Success";

    /**
     * 空值.
     */
    public static final String MSG_NULL = "Null values";

    /**
     * 数字错误.
     */
    public static final String MSG_WRONG_NUMBER = "Wrong number";

    /**
     * 系统异常错误.
     */
    public static final String MSG_SYS_ERROR = "System error";

    /**
     * 已经存在.
     */
    public static final String MSG_EXISTS = "Already exists";

    /**
     * 不存在.
     */
    public static final String MSG_NOT_EXISTS = "App Not exists";

    /**
     * 接口必填参数为空
     */
    public static final String MUST_PARAM_NULL = "Request error, must request parameter is null";

    /**
     * 成功响应
     */
    public static final String RESPOSE_STATUS_SUCESS = "0";

    /**
     * 错误响应
     */
    public static final String RESPOSE_STATUS_ERROR = "1";

    /**
     * 按时间降序查询新品推荐应用列表.
     */
    public static final String GET_NEW_APP_HQL = "from DpAppInfo da where   da.appStatus=?  order by da.updateTime desc  ";

    /**
     * 获取热门应用列表(按照评分点击率来排序).
     */
    // TODO level字段已经没有用
    public static final String GET_HOT_APP_HQL = "from DpAppInfo da where   da.appStatus=?  order by da.level desc  ";

    /**
     * 获取免费应用列表.
     */
    public static final String GET_FREE_APP_HQL = "from DpAppInfo da where da.price=0 and  da.appStatus=? order by da.updateTime desc  ";

    /**
     * 获取收费应用列表.
     */
    public static final String GET_CHARGE_APP_HQL = "from DpAppInfo da where da.price>0 and  da.appStatus= ? order by da.updateTime desc  ";

    /**
     * 获取所有上架应用列表.
     */
    public static final String GET_ALL_APP_HQL = "from DpAppInfo da where  da.appStatus=? order by da.updateTime desc  ";

    /**
     * 按类别查询应用列表.
     */
    public static final String GET_APP_BY_TYPE_HQL = "from DpAppInfo da where ( da.appStatus =? and da.dpType =? ) order by da.updateTime desc";

    /**
     * 根据应用id查询评论列表.
     */
    public static final String GET_APP_COMMENT_HQL = "from AppCommentInfo aci where  aci.dpAppInfo=? order by  aci.createDate";

    /**
     * 获取应用专题列表
     */
    public static final String GET_APP_SUBJECT_HQL = "from AppSubjectType ast where ast.visibleFlag=1 order by ast.updateDate desc";

    /**
     * 获取更新的应用
     */
    public static final String GET_UPDATEAPP_LIST_HQL = "from DpAppInfo da where da.appStatus ='1004' and da.id !=? and da.appFilePackage=?";

    /**
     * 根据证书类型和签名状态获得证书列表
     */
    public static final String CERTIFICATE_TYPE_REVOKEFLAG_HQL = "from AppCertificate apc where apc.certificateType = ? and apc.revokeFlag = ? order by apc.updatedDate desc";

    /**
     * 应用基本信息查询，包含应用分类，应用签名，应用下载次数，应用评论数，应用平均评分
     */
    public static final String BASE_QUERY_APP_SQL = "select appInfo.c_id id,appInfo.c_app_name appName,"
            + " appInfo.c_app_name_pinyin appNamePinyin, appInfo.c_app_desc appDesc,"
            + " appInfo.c_app_file_package appFilePackage, appInfo.c_update_time updateTime,"
            + " appInfo.c_create_time appTime, appInfo.c_price price, appInfo.c_version version,appInfo.c_version_code versionCode,"
            + " appInfo.c_language language, appInfo.c_system system, appInfo.c_developer developer,"
            + " appInfo.c_app_status appStatus, appInfo.c_type_id typeId, appType.c_type_name typeName,"
            + " sign.c_app_cert_id appCertId, t_my_apps.downloadCount downloadCount,"
            + " t_comments.commentNum commentNum, t_comments.avgScore avgScore"
            + " from t_dp_app_info appInfo left join t_dp_type appType on appInfo.c_type_id = appType.c_id"
            + " left join t_app_sign sign on appInfo.C_ID = sign.c_app_id"
            + " left join  (select  count(*) downloadCount, c_app_package_name  from t_my_app group by c_app_package_name)t_my_apps"
            + " on appInfo.c_app_file_package = t_my_apps.c_app_package_name"
            + " left join (select  count(*) commentNum,avg(c_score) avgScore, c_app_package_name  "
            + " from t_app_comment_info group by c_app_package_name) t_comments"
            + " on appInfo.c_app_file_package = t_comments.c_app_package_name";

	public static final String DEVICE_MSTAR = "mstar";
	public static final String DEVICE_HISI = "hisi";
	public static final String DEVICE_MTK = "mt";

    /**
     * DVB鉴权系统参数的编码。目前用于辽宁阜新DVB项目.
     */
    public static final String DVB_AUTH = "DVB_AUTH_CITYCODE";
}
