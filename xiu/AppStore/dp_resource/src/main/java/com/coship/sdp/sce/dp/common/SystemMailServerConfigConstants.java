/*
 * 文件名称：SysteMailServerConfigConstants.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-17
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.common;

import org.apache.commons.lang.StringUtils;

import com.coship.sdp.utils.SystemConfig;

/**
 * 系统邮件服务器配置常量类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-17]
 * @since [产品/模块版本]
 */
public class SystemMailServerConfigConstants
{
    /**
     * 邮件服务器ip
     */
    public static final String MAIL_SERVER_HOST = (String) SystemConfig
            .getInstance().getProperty("mail.server.host");

    /**
     * 邮件服务器端口
     */
    public static final String MAIL_SERVER_PORT = (String) SystemConfig
            .getInstance().getProperty("mail.server.port");

    /**
     * 邮件服务器端口
     */
    public static final String MAIL_SERVER_USN = (String) SystemConfig
            .getInstance().getProperty("mail.server.username");

    /**
     * 邮件服务器端口
     */
    public static final String MAIL_SERVER_PWD = (String) SystemConfig
            .getInstance().getProperty("mail.server.password");

    /**
     * 邮件服务器是否需要授权
     */
    public static boolean MAIL_SMTP_AUTH = Boolean.valueOf(StringUtils
            .trim((String) SystemConfig.getInstance().getProperty(
                    "mail.smtp.auth")));

    /**
     * 邮件服务器发送超时时长
     */
    public static long MAIL_SMTP_TIMEOUT = Long.valueOf(StringUtils
            .trim((String) SystemConfig.getInstance().getProperty(
                    "mail.stmp.timeout")));

    /**
     * 邮件发送者地址
     */
    public static String MAIL_SENDER = (String) SystemConfig.getInstance()
            .getProperty("mail.sender");

}
