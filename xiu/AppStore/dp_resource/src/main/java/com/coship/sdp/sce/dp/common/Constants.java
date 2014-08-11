/**
 * 文 件 名：Constants.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd.
 * Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：sunwengang/903820
 * 修改时间：2011-7-25
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.common;

import java.io.File;

import com.coship.sdp.utils.SystemConfig;

/**
 *
 * 常量类 定义系统常量.
 */
public class Constants
{

    /**
     * 编码方式.
     */
    public static final String ENCODING = "UTF-8";

    /**
     * 存放sesson中的用户变量.
     */
    public static final String LOGIN_SESSION_USER = "user";

    /**
     * 验证码sessoncode.
     */
    public static final String SESSION_AUTHEN_CODE = "session_picCode";

    /**
     * 资讯初稿状态.
     */
    public static final String NEWS_DRAFT_STATUS = "1001";

    /**
     * 资讯上线状态.
     */
    public static final String NEWS_ONLINE_STATUS = "1002";

    /**
     * 资讯类型标识. 在dpType实体里面，typeFlag=1表资讯类型；typeFlag=2表示下载类型
     */
    public static final int NEWS_TYPE_FLAG = 1;

    /**
     * 下载类型标识. 在dpType实体里面，typeFlag=1表资讯类型；typeFlag=2表示下载类型
     */
    public static final int DOWNLOAD_TYPE_FLAG = 2;

    /**
     * 跳转到编辑界面.
     */
    public static final String TO_EDIT_FORWARD = "edit";

    /**
     * 跳转到详情界面.
     */
    public static final String TO_DETAIL_FORWARD = "detail";

    /**
     * 跳转到审核界面.
     */
    public static final String TO_AUDIT_FORWARD = "audit";

    /**
     * web 类型的应用
     */
    public static final String APP_WEB_TYPE = "html";

    public final static String BINDING = "binding";

    public final static String BINDING_TYPE_HTTP = "http://schemas.xmlsoap.org/wsdl/http/";

    public final static String BINDING_TYPE_SOAP = "http://schemas.xmlsoap.org/wsdl/soap/";

    public final static String BINDING_TYPE_SOAP12 = "http://schemas.xmlsoap.org/wsdl/soap12/";

    public final static String INPUTPARA_DATATYPE_ENUM = "enum";

    /**
     * 从URL导入WSDL文件.
     */
    public static final String USP_REGISTRY_TYPE_REMOTE = "1";

    /**
     * 导入本地的WSDL文件.
     */
    public static final String USP_REGISTRY_TYPE_LOCAL = "2";

    /**
     * 服务类型-WebService.
     */
    public static final int USP_SERVICE_TYPE_WEBSERVICE = 1;

    /***
     * 服务类型-API.
     */
    public static final int USP_SERVICE_TYPE_API = 2;

    /***
     * 服务类型-ESB服务.
     */
    public static final int USP_SERVICE_TYPE_ESB = 3;

    /**
     * 参数标示-输入参数.
     */
    public static final int USP_PARAM_TYPE_INPUT = 1;

    /**
     * 参数标示-输入参数.
     */
    public static final int USP_PARAM_TYPE_INPUT_THREE = 3;

    /**
     * 参数标示-输出参数.
     */
    public static final int USP_PARAM_TYPE_OUTPUT = 2;

    /**
     * json 返回数据根对象.
     */
    public static final String JSON_RETURN_DATA = "data";

    /**
     * 附件上传文件大小.
     */
    public static final String ATTACHMENT_UPLOAD_MAXSIZE = "dp.attachment.upload.maxsize";

    /**
     * 本地WSDL上传目标地址.
     */
    public static final String WSDL_UPLOAD_DIR = "usp.wsdl.upload.dir";

    /**
     * 服务草稿.
     */
    public static final String USP_SERV_AUDIT_DRAFT = "1000";

    /**
     * 服务待审核.
     */
    public static final String USP_SERV_AUDIT_AUDITING = "1001";

    /**
     * 服务审核通过.
     */
    public static final String USP_SERV_AUDIT_AUDITED = "1002";

    /**
     * 服务审核拒绝.
     */
    public static final String USP_SERV_AUDIT_REJECT = "1003";

    /**
     * AP信息审核不通过状态.
     */
    public static final String AP_AUDIT_NOT_PASS = "1001";

    /**
     * AP信息待审核状态.
     */
    public static final String AP_AUDIT_TO_WAIT = "1002";

    /**
     * AP信息审核通过状态.
     */
    public static final String AP_AUDIT_PASS = "1003";

    /**
     * AP信息草稿状态.
     */
    public static final String AP_DRAFT = "1004";

    /**
     * AP应用待审核状态.
     */
    public static final String APPINFO_AUDIT_TO_WAIT = "1001";

    /**
     * AP应用审核不通过状态.
     */
    public static final String APPINFO_AUDIT_NOT_PASS = "1002";

    /**
     * AP应用审核通过状态.
     */
    public static final String APPINFO_AUDIT_PASS = "1003";

    /**
     * 服务审核结果 通过.
     */
    public static final int USP_SERV_AUDIT_PASS = 1;

    /**
     * 服务审核结果 不通过.
     */
    public static final int USP_SERV_AUDIT_NO = 2;

    /**
     * dp信息审核标识 （在审核表里面用到）.
     */
    public static final String DP_AUDIT_FLAG = "3";

    /**
     * app应用审核标识（在审核表里面用到）.
     */
    public static final String APP_AUDIT_FLAG = "2";

    /**
     * 新增.
     */
    public static final String ADD = "sdp.sce.dp.admin.log.add.operate";

    /**
     * 删除.
     */
    public static final String DEL = "sdp.sce.dp.admin.log.delete.operate";

    /**
     * 修改.
     */
    public static final String MOD = "sdp.sce.dp.admin.log.update.operate";

    /**
     * 排序.
     */
    public static final String SORT = "sdp.sce.dp.admin.log.sort.operate";

    /**
     * 审核.
     */
    public static final String CHECK = "sdp.sce.dp.admin.log.audit.operate";

    /**
     * 其它.
     */
    public static final String OTHER = "sdp.sce.dp.admin.log.other.operate";

    /**
     * 登录 日志操作类型
     */
    public static final String LOGIN = "sdp.sce.dp.admin.global.login";

    /**
     * 附件上传目标地址.
     */
    public static final String ATTACHMENT_UPLOAD_DIR = "dp.attachment.upload.dir";

    /**
     * 发布到 SEE的应用名称.
     */
    public static final String DEPLOY_PROJECT_PATH = (String) SystemConfig
            .getInstance().getProperty("deploy.project.path");

    /**
     * 读超时时间.
     */
    public static final int READ_TIMEOUT = 5000;

    /**
     * 连接超时时间.
     */
    public static final int CONNECT_TIMEOUT = 10000;

    /**
     * appstore访问dp_portal 服务器protocol：由system.properties文件中的appstore.server.protocol值.
     */
    public static final String APPSTORE_RESOURCE_PROTOCOL = (String) SystemConfig
            .getInstance().getProperty("appstore.server.protocol");

    /**
     * 终端访问appstore_service 服务器ip：由system.properties文件中的appstore.server.ip值.
     */
    public static final String APPSTORE_DPPORTAL_IP = (String) SystemConfig
            .getInstance().getProperty("appstore.server.ip");

    /**
     * 终端访问appstore_service 服务器端口号：由system.properties文件中的appstore.server.port值.
     */
    public static final String APPSTORE_DPPORTAL_PORT = (String) SystemConfig
            .getInstance().getProperty("appstore.server.port");
    
    /**
     * appstore 缓存服务器ip：由system.properties文件中的appstore.cache.ip值.
     */
    public static final String APPSTORE_CACHE_IP = (String) SystemConfig
            .getInstance().getProperty("appstore.cache.ip");
    
    /**
     * appstore 版本升级服务器host
     */
    public static final String UPGRADE_SERVER_IP = (String) SystemConfig
            .getInstance().getProperty("upgrade.server.ip");
    
    public static final String UPGRADE_SERVER_PORT = (String) SystemConfig
            .getInstance().getProperty("upgrade.server.port");
    
    /**
     * FUC 服务器地址
     */
    public static final String FUC_SERVER_URL = (String) SystemConfig
            .getInstance().getProperty("fuc.server.url");
    
    /**
     * 终端访问文件服务器protocol：由system.properties文件中的appstore.server.protocol值.
     */
    public static final String APPSTORE_UPLOAD_PROTOCOL = (String) SystemConfig
            .getInstance().getProperty("appstore.upload.protocol");
    
    /**
     * 终端访问文件服务器ip：由system.properties文件中的appstore.upload.ip值.
     */
    public static final String APPSTORE_UPLOAD_IP = (String) SystemConfig
            .getInstance().getProperty("appstore.upload.ip");

    /**
     * 终端访问文件 服务器端口号：由system.properties文件中的appstore.upload.port值.
     */
    public static final String APPSTORE_UPLOAD_PORT = (String) SystemConfig
            .getInstance().getProperty("appstore.upload.port");
    
    /**
     * 终端访问文件服务器ip：由system.properties文件中的appstore.apk.upload.ip值. (存放apk文件专用)
     */
    public static final String APPSTORE_APK_UPLOAD_IP = (String) SystemConfig
            .getInstance().getProperty("appstore.apk.upload.ip");

    /**
     * 终端访问文件 服务器端口号：由system.properties文件中的appstore.apk.upload.port值. (存放apk文件专用)
     */
    public static final String APPSTORE_APK_UPLOAD_PORT = (String) SystemConfig
            .getInstance().getProperty("appstore.apk.upload.port");
    
    /**
     * 系统中保存文件的根目录：这个会由system.properties文件中的dp.attachment.upload.dir值决定. 如D:/opt/save/dp/upload.war/它的下一层会分别有保存文档（doc）、图片（images）、文件（file）、logo（logo）等对应的目录
     */
    public static final String ROOT_SAVE_PATH = (String) SystemConfig
            .getInstance().getProperty("dp.attachment.upload.dir");

    /**
     * 系统保存应用文档的路径。如D:/opt/save/dp/upload.war/appdoc.
     */
    public static final String APP_DOC_SAVE_PATH = ROOT_SAVE_PATH
            + File.separator + "appdoc";

    /**
     * 系统保存应用文件的路径。如D:/opt/save/dp/upload.war/appfile.
     */
    public static final String APP_FILE_SAVE_PATH = ROOT_SAVE_PATH
            + File.separator + "appfile";

    /**
     * 系统保存应用logo图片的路径。如D:/opt/save/dp/upload.war/applogo.
     */
    public static final String APP_LOGO_SAVE_PATH = ROOT_SAVE_PATH
            + File.separator + "applogo";

    /**
     * 系统保存应用图片的路径。如D:/opt/save/dp/upload.war/appimages.
     */
    public static final String APP_IMAGES_SAVE_PATH = ROOT_SAVE_PATH
            + File.separator + "appimages";
    
    /**
     * 保存系统其它的图片，比如系统参数里的图片
     */
    public static final String SYSTEM_IMAGES_SAVE_PATH = ROOT_SAVE_PATH
            + File.separator + "system";

    /**
     * 系统保存下载附件的路径。如D:/opt/save/dp/upload.war/download.
     */
    public static final String DOWNLOAD_IMG_SAVE_PATH = ROOT_SAVE_PATH
            + File.separator + "download";

    /**
     * 系统保存dp账户头像和扫描件的路径。如D:/opt/save/dp/upload.war/dpstaff.
     */
    public static final String DPSTAFF_IMG_SAVE_PATH = ROOT_SAVE_PATH
            + File.separator + "dpstaff";

    /**
     * 系统保存新闻信息的路径。如D:/opt/save/dp/upload.war/news.
     */
    public static final String NEWS_IMG_SAVE_PATH = ROOT_SAVE_PATH
            + File.separator + "news";

    /**
     * 系统保存证书文件的路径。如D:/opt/save/dp/upload.war/certificate.
     */
    public static final String CERTIFICATE_SAVE_PATH = ROOT_SAVE_PATH
            + File.separator + "certificate";

    /**
     * 系统保存应用签名后的文件的路径。如D:/opt/save/dp/upload.war/signedapp.
     */
    public static final String SIGNED_APP_SAVE_PATH = ROOT_SAVE_PATH
            + File.separator + "signedapp";

    public static final String APP_STORE_CLIENT_SAVE_PATH = ROOT_SAVE_PATH
            + File.separator + "appstore";

    public static final String IMPLICIT_APP_SAVE_PATH = ROOT_SAVE_PATH
    		+ File.separator + "implicitapp";

    /**
     * 资讯查询错误信息.
     */
    public static final String NEWS_QUERY_ERROR_MSG = "sdp.sce.dp.admin.news.query.news.error";

    /**
     * 资讯管理.
     */
    public static final String NEWS_MANAGEMENT = "sdp.sce.dp.admin.news";

    /**
     * 服务管理
     */
    public static final String SERVICE_MANAGEMENT = "sdp.sce.dp.admin.service";

    /**
     * 下载管理.
     */
    public static final String DOWNLOAD_MANAGEMENT = "sdp.sce.dp.admin.download";

    /**
     * AP信息管理.
     */
    public static final String APINFOMATION_MANAGEMENT = "sdp.sce.dp.admin.dpstaff";

    /**
     * 警告 分类已经绑定了下载或者资讯.
     */
    public static final String WARNING_DPTYPE_BIND_NEWSORDOWNLOAD = "sdp.sce.dp.admin.dptype.type.bind.data";

    /**
     * 警告 DP账户已经绑定了应用.
     */
    public static final String WARNING_DPSTAFF_BIND_APPINFO = "sdp.sce.dp.admin.dpstaff.dpstaff.bind.dpappinfo";

    /**
     * 应用图片映射路径，其中upload = jboss服务器中配置的映射到保存文件的路径.
     */
    public static final String APP_IMAGES_MAPPE_PATH = "/upload/appimages/";
    public static final String SYSTEM_IMAGES_MAPPE_PATH = "/upload/system/";

    /**
     * 应用logo映射路径，其中upload = jboss服务器中配置的映射到保存文件的路径.
     */
    public static final String APP_LOGO_MAPPE_PATH = "/upload/applogo/";

    /**
     * 应用文件映射路径，其中upload = jboss服务器中配置的映射到保存文件的路径.
     */
    public static final String APP_FILE_MAPPE_PATH = "/upload/signedapp/";
    /**
     * 开发者头像文件映射路径，其中upload = jboss服务器中配置的映射到保存文件的路径.
     */
    public static final String APP_DPSTAFF_MAPPE_PATH = "/upload/dpstaff/";
    
    /**
     * 文档路径，其中upload = jboss服务器中配置的映射到保存文件的路径.
     */
    public static final String APP_DOWLOADDOC_MAPPE_PATH = "/upload/download/";
    
    /**
	 * 精选页海报映射路径，其中upload = 服务器中配置的映射到保存文件的路径.
	 */
	public static final String APP_POSTER_MAPPE_PATH = "/upload/appposter/";

	/**
	 * 新闻资讯路径，其中upload = jboss服务器中配置的映射到保存文件的路径.
	 */
    public static final String APP_NEWS_MAPPE_PATH = "/upload/news/";

    /**
     * 证书文件映射路径
     */
    public static final String CERTIFICATE_MAPPE_PATH = "/upload/certificate/";

    /**
     * 应用商店客户端文件映射路径
     */
    public static final String APP_STORE_CLIENT_MAPPE_PATH = "/upload/appstore/";

    /**
     * 开发者论坛地址.
     */
    public static final String FORUMS_URL = (String) SystemConfig.getInstance()
            .getProperty("dp.forums.url");

    /**
     * dp 账号名称有重复.
     */
    public static final String WARNING_USERNAME_ALREADY_EXIST = "dp.resource.warning.username.already.exist";

    /**
     * 警告 服务同步地址名称已经存在.
     */
    public static final String WARNING_USPADDRESSNAME_ALREADY_EXIST = "usp.uddi.warning.uspaddressname.already.exist";

    /**
     * 警告 服务同步地址名称已经存在.
     */
    public static final String WARNING_USPADDRESSURL_ALREADY_EXIST = "usp.uddi.warning.uspaddressurl.already.exist";

    /**
     * 应用logo1.
     */
    public static final String LOGO = "logo";

    /**
     * 应用logo2.
     */
    public static final String LOGO2 = "logo2";

    /**
     * 应用logo3.
     */
    public static final String LOGO3 = "logo3";

    /**
     * 应用截图.
     */
    public static final String IMG = "img";

    /**
     * 应用截图1.
     */
    public static final String IMG1 = "img1";

    /**
     * 应用截图2.
     */
    public static final String IMG2 = "img2";

    /**
     * 应用截图3.
     */
    public static final String IMG3 = "img3";

    /**
     * 应用海报.
     */
    public static final String POSTER = "poster";

    /**
     * 游戏logo.
     */
    public static final String GAME_LOGO = "gameLogo";

    /**
     * 应用发布包.
     */
    public static final String APPFILE = "appfile";

    /**
     * 审核表，开发者审核标识：1
     */
    public static final String AUDIT_DEV_RECORD_FLAG = "1";

    /**
     * 审核表，应用审核标识：2
     */
    public static final String AUDIT_APP_RECORD_FLAG = "2";

    /**
     * 1001 签名证书和私钥.
     */
    public static final String CERTIFICATE_SIGNED_FLAG = "1001";

    /**
     * 1003 吊销证书列表.
     */
    public static final String CERTIFICATE_REVOKE_FLAG = "1003";

    /**
     * 证书正常标记.
     */
    public static final String CERTIFICATE_STATUS_NORMAL = "1000";

    /**
     * 证书吊销标记.
     */
    public static final String CERTIFICATE_STATUS_REVOKE = "1001";

    /**
     * 证书过期标记.
     */
    public static final String CERTIFICATE_STATUS_EXPIRED = "1002";

    /**
     * 一个星期标记
     */
    public static final int ONE_WEEK_FLAG = 0;

    /**
     * 一个月标记
     */
    public static final int ONE_MONTH_FLAG = 1;

    /**
     * 跳转到开发者平台资讯列表页面.
     */
    public static final String TO_NEWS_LIST_FORWARD = "list";

    /**
     * 跳转到开发者平台资讯列表页面.
     */
    public static final String TO_NEWS_SEARCH_LIST_FORWARD = "search";

    /**
     * 分隔符.
     */
    public static final String SPLIT_FLAG = ",";

    /**
     * 自动登陆标记
     */
    public static final String AUTO_LOGIN = "on";

    /**
     * 中文语言英文简写
     */
    public static final String ZH = "zh";

    /**
     * 英文语言英文简写
     */
    public static final String EN = "en";

    /**
     * 德文语言英文简写
     */
    public static final String DE = "de";

    /**
     * 法文语言英文简写
     */
    public static final String FR = "fr";

    /**
     * 俄文语言英文简写
     */
    public static final String RU = "ru";

    /**
     * 阿拉伯语言英文简写
     */
    public static final String AR = "ar";

    /**
     * 日文语言英文简写
     */
    public static final String JA = "ja";

    /**
     * 意大利语言简写
     */
    public static final String IT = "it";

    /**
     * 中文
     */
    public static final String CHZH = "中文";

    /**
     * 英文
     */
    public static final String CHEN = "英文";

    /**
     * 德文
     */
    public static final String CHDE = "德文";

    /**
     * 法文
     */
    public static final String CHFR = "法文";

    /**
     * 俄文
     */
    public static final String CHRU = "俄文";

    /**
     * 阿拉伯文
     */
    public static final String CHAR = "阿拉伯文";

    /**
     * 日文
     */
    public static final String CHJA = "日文";

    /**
     * 意大利文
     */
    public static final String CHIT = "意大利文";

    /**
     * 新增操作.
     */
    public static final String OPERATE_ADD = "Add";

    /**
     * 修改操作.
     */
    public static final String OPERATE_MODIFY = "Modify";

    /**
     * 升级操作.
     */
    public static final String OPERATE_UPGRADE = "Upgrade";

    /**
     * 表示后台管理应用上传时附件有修改
     */
    public static final String IS_MODIFY = "1";

    /**
     * 应用商店状态在线
     */
    public static final String APP_STORE_ONLINE_STATUS = "1";

    /**
     * 应用商店状态下线
     */
    public static final String APP_STORE_OFFLINE_STATUS = "2";

    /**
     * 草稿状态
     */
    public static final String APP_STATUS_DRAFT = "1000";

    /**
     * 待审核状态
     */
    public static final String APP_STATUS_PENDING_AUDITE = "1001";

    /**
     * 审核不通过状态
     */
    public static final String APP_STATUS_NOPASS_AUDITED = "1002";

    /**
     * 审核通过
     */
    public static final String APP_STATUS_PASS_AUDITED = "1003";

    /**
     * 已上架
     */
    public static final String APP_STATUS_ONLINE = "1004";

    /**
     * 已下架
     */
    public static final String APP_STATUS_OFFLINE = "1005";

    /**
     * 更新版本
     */
    public static final String APP_STATUS_UPDATE = "1006";

    /**
     * 隐式应用启用
     */
    public static final String IMPLICIT_APP_ACTIVATE_STATUS = "0";

    /**
     * 隐式应用禁用
     */
    public static final String IMPLICIT_APP_FORBID_STATUS = "1";

    /**
     * 安装类型的隐式应用
     */
    public static final String IMPLICIT_INSTALL = "1";

    /**
     * 升级类型的隐式应用
     */
    public static final String IMPLICIT_UPGRADE = "2";

    /**
     * 卸载类型的隐式应用
     */
    public static final String IMPLICIT_UNINSTALL = "3";

    /**
     * 隐式应用为终端类型为mstar和hisi类型
     */
    public static final String TEMINALNUM_MSTAR_HISI = "1, 2, 3";

    /**
     * 隐式应用为终端类型为mstar类型
     */
    public static final String TEMINALNUM_MSTAR = "1";

    /**
     * 隐式应用为终端类型为hisi类型
     */
    public static final String TEMINALNUM_HISI = "2";

    /**
     * 隐式应用为终端类型为MTK类型
     */
    public static final String TEMINALNUM_MTK = "3";

    /**
     * 隐式应用为终端类型为mstar和hisi类型
     */
    public static final String TEMINALNUM_MSTAR_AND_HISI = "0";

    /**
     * 不排序
     */
    public static final String APP_ORDER_ALL = "0";

    /**
     * 最热排序(下载的次数)
     */
    public static final String APP_ORDER_HOT = "1";

    /**
     * 最新排序(更新的时间)
     */
    public static final String APP_ORDER_NEW = "2";

    /**
     * 好评排序(平均评分)
     */
    public static final String APP_ORDER_GOOD = "3";

    /**
     * 免费排序
     */
    public static final String APP_ORDER_FREE = "4";

    /**
     * 收费排序
     */
    public static final String APP_ORDER_PAY = "5";

    /**
     * 论坛开启
     */
    public static final String DISCUZ_OFF = "off";

    /**
     * 论坛关闭
     */
    public static final String DISCUZ_ON = "on";

    /**
     * 论坛开关
     */
    public static final String DISCUZ_SWITCH = (String) SystemConfig
    		.getInstance().getProperty("discuz.forum.switch");

    /**
     *  论坛地址
     */
    public static final String DISCUZ_FORUM_ADDR = (String) SystemConfig
			.getInstance().getProperty("dp.forums.url");


	/**
	 * 精选页板块元素的应用类型.
	 */
	public static final int APP_RECOMMEND_PANEL_ITEM_APP_TYPE = 1;

	/**
	 * 精选页板块元素的专题类型.
	 */
	public static final int APP_RECOMMEND_PANEL_ITEM_APP_SUBJECT_TYPE = 2;

    /**
     * 专题状态. 1=显示
     */
    public static final int APP_SUBJECT_VISIBLE = 1;

    /**
     * 专题状态. 2=隐藏
     */
    public static final int APP_SUBJECT_HIDE = 2;
    
    /**
     * 参数类型1:文字；2:图片
     */
    public static final Integer PARAM_TYPE_TEXT = 1;
    public static final Integer PARAM_TYPE_PHOTO = 2;
    
    /**
     * 缩略图状态0:失败；1:成功
     */
    public static final Integer THUMBNAIL_FAIL = 0;
    public static final Integer THUMBNAIL_SUCCESS = 1;
    
    /**
     * 猜你喜欢 0:系统自动计算;1:手动指定
     */
    public static final String APP_RELATE_TYPE_SYSTEM = "0";
    public static final String APP_RELATE_TYPE_HAND = "1";
    
    /**
     * 猜你喜欢推荐个数
     */
    public static final int APP_RELATE_MAXCOUNT = 5;
}
