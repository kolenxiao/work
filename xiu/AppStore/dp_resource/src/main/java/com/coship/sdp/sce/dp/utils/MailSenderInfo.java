package com.coship.sdp.sce.dp.utils;

/*
 * 文件名称：MailSenderInfo.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-17
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
import java.util.Properties;

import com.coship.sdp.sce.dp.common.SystemMailServerConfigConstants;

/**
 * 邮寄发送信息类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-17]
 * @since [产品/模块版本]
 */
public class MailSenderInfo
{
    /**
     * 发送邮件的服务器的IP
     */
    private String mailServerHost;

    /**
     * 发送邮件的服务器的端口
     */
    private String mailServerPort = "25";

    /**
     * 邮件发送者的地址
     */
    private String fromAddress;

    /**
     * 邮件接收者的地址
     */
    private String[] toAddress;

    /**
     * 登陆邮件发送服务器的用户名
     */
    private String userName;

    /**
     * 登陆邮件发送服务器的密码
     */
    private String password;

    /**
     * 是否需要身份验证
     */
    private boolean validate = false;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件的文本内容
     */
    private String content;

    /**
     * 邮件附件的文件名
     */
    private String[] attachFileNames;

    private long timeout;

    /**
     * 获取系统配置的邮件发送简单信息对象
     * @return [参数说明]
     * @return MailSenderInfo [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public static MailSenderInfo getSystemMailSenderInfo()
    {
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost(SystemMailServerConfigConstants.MAIL_SERVER_HOST);
        mailInfo.setMailServerPort(SystemMailServerConfigConstants.MAIL_SERVER_PORT);
        mailInfo.setValidate(SystemMailServerConfigConstants.MAIL_SMTP_AUTH);
        mailInfo.setTimeout(SystemMailServerConfigConstants.MAIL_SMTP_TIMEOUT);
        mailInfo.setUserName(SystemMailServerConfigConstants.MAIL_SERVER_USN);
        mailInfo.setPassword(SystemMailServerConfigConstants.MAIL_SERVER_PWD);
        mailInfo.setFromAddress(SystemMailServerConfigConstants.MAIL_SENDER);
        return mailInfo;
    }

    public Properties getProperties()
    {
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.stmp.timeout", this.timeout);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }

    public String getMailServerHost()
    {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost)
    {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort()
    {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort)
    {
        this.mailServerPort = mailServerPort;
    }

    public boolean isValidate()
    {
        return validate;
    }

    public void setValidate(boolean validate)
    {
        this.validate = validate;
    }

    public String[] getAttachFileNames()
    {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] fileNames)
    {
        this.attachFileNames = fileNames;
    }

    public String getFromAddress()
    {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress)
    {
        this.fromAddress = fromAddress;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String textContent)
    {
        this.content = textContent;
    }

    public String[] getToAddress()
    {
        return toAddress;
    }

    public void setToAddress(String[] toAddress)
    {
        this.toAddress = toAddress;
    }

    public long getTimeout()
    {
        return timeout;
    }

    public void setTimeout(long timeout)
    {
        this.timeout = timeout;
    }
}