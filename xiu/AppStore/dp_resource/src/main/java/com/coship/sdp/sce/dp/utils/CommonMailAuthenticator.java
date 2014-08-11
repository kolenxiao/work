/*
 * 文件名称：CommonAuthenticator.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-17
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮件发送普通(用户名/密码)认证类
 * @author JiangJinfeng/906974
 * @version [版本号, 2012-9-17]
 * @since [产品/模块版本]
 */
public class CommonMailAuthenticator extends Authenticator
{
    /**
     * 用户名
     */
    private String userName = null;

    /**
     * 密码
     */
    private String password = null;

    public CommonMailAuthenticator()
    {
    }

    public CommonMailAuthenticator(String username, String password)
    {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(userName, password);
    }
}