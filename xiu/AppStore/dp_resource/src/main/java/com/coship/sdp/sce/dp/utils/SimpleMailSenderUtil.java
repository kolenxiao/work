/*
 * 文件名称：SimpleMailSenderUtil.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-17
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.utils;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;

/**
 * 简单发送邮件工具类
 * @author JiangJinfeng/906974
 * @version [版本号, 2012-9-17]
 * @since [产品/模块版本]
 */
public class SimpleMailSenderUtil
{
    /**
     * 发送简单文本邮件
     * @param mailInfo
     * @throws MessagingException [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public static void sendTextMail(MailSenderInfo mailInfo)
            throws MessagingException
    {
        Message mailMessage = initCommonMessage(mailInfo);

        // 设置邮件消息的主要内容
        String mailContent = mailInfo.getContent();

        mailMessage.setText(mailContent);
        // 发送邮件
        Transport.send(mailMessage);
    }

    /**
     * 发送html内容，可以添加附件的邮件
     * @param mailInfo
     * @throws MessagingException [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public static void sendHtmlMail(MailSenderInfo mailInfo)
            throws MessagingException
    {
        Message mailMessage = initCommonMessage(mailInfo);

        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();

        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();

        // 设置HTML内容 建立第一部分： 文本正文
        html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");

        mainPart.addBodyPart(html);

        // 将MiniMultipart对象设置为邮件内容 建立第二部分：附件
        mailMessage.setContent(mainPart);

        String[] attachFiles = mailInfo.getAttachFileNames();
        if (attachFiles != null && attachFiles.length > 0)
        {
            for (int i = 0; i < attachFiles.length; i++)
            {
                if (StringUtils.isNotEmpty(mailInfo.getAttachFileNames()[i]))
                {
                    // 建立第二部分：附件
                    html = new MimeBodyPart();

                    // 获得附件
                    DataSource source = new FileDataSource(
                            mailInfo.getAttachFileNames()[i]);

                    // 设置附件的数据处理器
                    html.setDataHandler(new DataHandler(source));

                    // 设置附件文件名
                    html.setFileName(mailInfo.getAttachFileNames()[i]);

                    // 加入第二部分
                    mainPart.addBodyPart(html);
                }
            }
        }
        // 发送邮件
        Transport.send(mailMessage);
    }

    /**
     * 初始化通用消息对象
     * @param mailInfo
     * @return
     * @throws AddressException
     * @throws MessagingException [参数说明]
     * @return Message [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private static Message initCommonMessage(MailSenderInfo mailInfo)
            throws AddressException, MessagingException
    {
        // 判断是否需要身份认证
        CommonMailAuthenticator authenticator = null;

        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate())
        {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new CommonMailAuthenticator(mailInfo.getUserName(),
                    mailInfo.getPassword());
        }

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session
                .getDefaultInstance(pro, authenticator);

        // 根据session创建一个邮件消息
        Message mailMessage = new MimeMessage(sendMailSession);
        // 创建邮件发送者地址
        if (StringUtils.isNotEmpty(mailInfo.getFromAddress()))
        {
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
        }

        // 创建邮件的接收地址（数组）
        String[] to = mailInfo.getToAddress();
        InternetAddress[] sendTo = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++)
        {
            sendTo[i] = new InternetAddress(to[i]);
        }
        mailMessage.setRecipients(
                javax.mail.internet.MimeMessage.RecipientType.TO, sendTo);

        // 设置邮件消息的主题

        mailMessage.setSubject(mailInfo.getSubject());

        // 设置邮件消息发送的时间
        mailMessage.setSentDate(new Date());
        return mailMessage;
    }
}