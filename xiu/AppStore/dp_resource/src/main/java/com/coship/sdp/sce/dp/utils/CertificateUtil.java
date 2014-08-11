/*
 * 文 件 名：CertificateUtil.java
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：证书和吊销列表操作工具类
 * 修 改 人：wangchenbo/906055
 * 修改时间：2012-9-26
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 证书和吊销列表操作工具类.
 * @author 906055
 *
 */
public class CertificateUtil
{

    /**
     * 此类定义了用于从相关的编码中生成证书、证书路径 (CertPath) 和证书撤消列表 (CRL) 对象的 CertificateFactory 功能.
     */
    private static CertificateFactory certificateFactory = null;

    /**
     * 16进制.
     */
    public static final int HEXADECIMAL = 16;

    /**
     * 读取证书.
     * 
     * @param caName
     * @return
     * @throws IOException
     * @throws CertificateException [参数说明]
     * @return X509Certificate [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public static X509Certificate readCa(String caName) throws IOException,
            CertificateException
    {

        FileInputStream fileIn = null;
        X509Certificate x509certificate = null;
        try
        {
            if (null == certificateFactory)
            {
                certificateFactory = CertificateFactory.getInstance("X.509");
            }

            fileIn = new FileInputStream(caName);

            x509certificate = (X509Certificate) certificateFactory
                    .generateCertificate(fileIn);
        }

        finally
        {

            if (null != fileIn)
            {
                fileIn.close();

            }
        }
        return x509certificate;
    }

    /**
     * 读取证书吊销列表.
     * 
     * @param caName
     * @return
     * @throws CertificateException
     * @throws CRLException
     * @throws IOException [参数说明]
     * @return X509CRL [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public static X509CRL readCrl(String caName) throws CertificateException,
            CRLException, IOException
    {

        FileInputStream fileIn = null;
        X509CRL x509crl;
        try
        {
            if (null == certificateFactory)
            {
                certificateFactory = CertificateFactory.getInstance("X.509");
            }
            fileIn = new FileInputStream(caName);
            x509crl = (X509CRL) certificateFactory.generateCRL(fileIn);
        }

        finally
        {

            if (null != fileIn)
            {
                fileIn.close();

            }
        }

        return x509crl;
    }

    /**
     * 获得证书吊销列表所有序列号.
     * 
     * @param crl 证书吊销列表
     * @return List<String> 所有序列号
     * @exception throws [违例类型] [违例说明]
     */
    public static List<String> getCrlList(X509CRL crl)
    {
        Set<?> revokedSet = null;
        Iterator<?> revokedIterator = null;
        List<String> crlList = null;
        // if +1 begin
        if (null != crl)
        {
            revokedSet = crl.getRevokedCertificates();

            // if +2 begin
            if (null != revokedSet)
            {
                revokedIterator = revokedSet.iterator();

                crlList = new ArrayList<String>();
                String serialNumber = null;
                // while +1 begin
                while (revokedIterator.hasNext())
                {
                    X509CRLEntry tEntry = (X509CRLEntry) revokedIterator.next();
                    serialNumber = tEntry.getSerialNumber().toString(
                            HEXADECIMAL);
                    crlList.add(serialNumber);

                }
                // while +1 end
            }
            // if +2 end
        }
        // if +1 end

        return crlList;
    }

}
