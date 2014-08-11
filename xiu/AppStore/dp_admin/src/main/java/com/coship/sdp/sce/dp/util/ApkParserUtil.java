/*
 * 文件名称：ApkParserUtil.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-14
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.security.cert.X509Certificate;
import java.util.Formatter;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import sun.security.pkcs.PKCS7;
import sun.security.pkcs.ParsingException;
import android.content.res.AXmlResourceParser;
import android.util.TypedValue;

/**
 * apk安装包解析工具类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-14]
 * @since [产品/模块版本]
 */
public class ApkParserUtil {

    private static final String CERT = "CERT.RSA";

    private static final String ANDROIDMANIFEST = "AndroidManifest.xml";

    private static final float RADIX_MULTS[] =
    { 0.00390625F, 3.051758E-005F, 1.192093E-007F, 4.656613E-010F };

    private static final String DIMENSION_UNITS[] =
    { "px", "dip", "sp", "pt", "in", "mm", "", "" };

    private static final String FRACTION_UNITS[] =
    { "%", "%p", "", "", "", "", "", "" };

    /**
     * 测试方法
     * @param args [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public static void main(String[] args) {
        File apkFile = new File("F:/TVappstore1.apk");
        parser(apkFile);
    }

    /**
     * 解析方法
     * @param apk
     * @return [参数说明]
     * @return ApkInfo [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public static ApkInfo parser(File apk) {
        ApkInfo apkInfo = new ApkInfo();
        ZipFile zf = null;
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(apk));
            zf = new ZipFile(apk);
            ZipInputStream zin = new ZipInputStream(in);
            ZipEntry ze;
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.isDirectory()) {
                } else {

                    String zeFileName = ze.getName();
                    int index = zeFileName.lastIndexOf("/") + 1;
                    index = (index == -1 ? 0 : index);
                    zeFileName = zeFileName.substring(index);

                    // 解析apk中CERT.RSA
                    if (CERT.equals(zeFileName)) {
                        InputStream inputEntry = zf.getInputStream(ze);
                        PKCS7 pkcs7 = new PKCS7(inputEntry);
                        X509Certificate publicKey = pkcs7.getCertificates()[0];

                        // 设置公钥
                        apkInfo.setPublicKey(publicKey);
                    }

                    // 解析apk中的AndroidManifest.xml文件，获取包名、版本编号、版本名称、运行最低sdk版本编码
                    if (ANDROIDMANIFEST.endsWith(zeFileName)) {
                        InputStream inputEntry = zf.getInputStream(ze);
                        AXmlResourceParser parser = new AXmlResourceParser();
                        parser.open(inputEntry);
                        StringBuffer xmlBuffer = new StringBuffer();

                        StringBuilder indent = new StringBuilder(10);
                        final String indentStep = "   ";
                        while (true) {
                            int type = parser.next();
                            if (type == XmlPullParser.END_DOCUMENT) {
                                break;
                            }
                            switch (type)
                            {
                                case XmlPullParser.START_DOCUMENT:
                                {
                                    log("<?xml version=\"1.0\" encoding=\"utf-8\"?>",
                                            xmlBuffer);
                                    break;
                                }
                                case XmlPullParser.START_TAG:
                                {
                                    log("%s<%s%s", xmlBuffer, indent,
                                            getNamespacePrefix(parser
                                                    .getPrefix()),
                                            parser.getName());
                                    indent.append(indentStep);

                                    int namespaceCountBefore = parser
                                            .getNamespaceCount(parser
                                                    .getDepth() - 1);
                                    int namespaceCount = parser
                                            .getNamespaceCount(parser
                                                    .getDepth());
                                    for (int i = namespaceCountBefore; i != namespaceCount; ++i) {
                                        log("%sxmlns:%s=\"%s\"", xmlBuffer,
                                                indent,
                                                parser.getNamespacePrefix(i),
                                                parser.getNamespaceUri(i));
                                    }

                                    for (int i = 0; i != parser
                                            .getAttributeCount(); ++i) {
                                        log("%s%s%s=\"%s\"",
                                                xmlBuffer,
                                                indent,
                                                getNamespacePrefix(parser
                                                        .getAttributePrefix(i)),
                                                parser.getAttributeName(i),
                                                getAttributeValue(parser, i));
                                    }
                                    log("%s>", xmlBuffer, indent);
                                    break;
                                }
                                case XmlPullParser.END_TAG:
                                {
                                    indent.setLength(indent.length()
                                            - indentStep.length());
                                    log("%s</%s%s>", xmlBuffer, indent,
                                            getNamespacePrefix(parser
                                                    .getPrefix()),
                                            parser.getName());
                                    break;
                                }
                                case XmlPullParser.TEXT:
                                {
                                    log("%s%s", xmlBuffer, indent,
                                            parser.getText());
                                    break;
                                }
                            }
                        }
                        parser.close();

                        String xmlStr = xmlBuffer.toString();
                        StringReader read = new StringReader(xmlStr);
                        InputSource source = new InputSource(read);
                        DocumentBuilderFactory buildFactory = DocumentBuilderFactory
                                .newInstance();
                        DocumentBuilder builder = buildFactory
                                .newDocumentBuilder();
                        Document document = builder.parse(source);

                        Element manifestElement = document.getDocumentElement();

                        String versionCodeStr = manifestElement
                                .getAttribute("android:versionCode");

                        String versionName = manifestElement
                                .getAttribute("android:versionName");

                        String packageName = manifestElement
                                .getAttribute("package");

                        System.out.println("versionCodeStr=" + versionCodeStr);
                        System.out.println("versionName=" + versionName);
                        System.out.println("packageName=" + packageName);

                        apkInfo.setVersionCode(Integer.valueOf(versionCodeStr));
                        apkInfo.setVersionName(versionName);
                        apkInfo.setPackageName(packageName);
                        NodeList usesSdk = manifestElement
                                .getElementsByTagName("uses-sdk");
                        if (usesSdk != null && usesSdk.getLength() > 0) {
                            Node usesSdkNode = usesSdk.item(0);
                            NamedNodeMap namedNodeMap = usesSdkNode
                                    .getAttributes();
                            Node minSdkVersionNode = namedNodeMap
                                    .getNamedItem("android:minSdkVersion");
                            System.out.println("minSdkVersionNode="
                                    + minSdkVersionNode.getNodeValue());
                            apkInfo.setMinSdkVersion(Integer
                                    .valueOf(minSdkVersionNode.getNodeValue()));
                        }

                        document = null;
                        source = null;
                        read = null;
                    }
                }
            }
            zin.closeEntry();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
        }
        return apkInfo;
    }

    private static String getNamespacePrefix(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return "";
        }
        return prefix + ":";
    }

    private static String getAttributeValue(AXmlResourceParser parser, int index) {
        int type = parser.getAttributeValueType(index);
        int data = parser.getAttributeValueData(index);
        if (type == TypedValue.TYPE_STRING) {
            return parser.getAttributeValue(index);
        }
        if (type == TypedValue.TYPE_ATTRIBUTE) {
            return String.format("?%s%08X", getPackage(data), data);
        }
        if (type == TypedValue.TYPE_REFERENCE) {
            return String.format("@%s%08X", getPackage(data), data);
        }
        if (type == TypedValue.TYPE_FLOAT) {
            return String.valueOf(Float.intBitsToFloat(data));
        }
        if (type == TypedValue.TYPE_INT_HEX) {
            return String.format("0x%08X", data);
        }
        if (type == TypedValue.TYPE_INT_BOOLEAN) {
            return data != 0 ? "true" : "false";
        }
        if (type == TypedValue.TYPE_DIMENSION) {
            return Float.toString(complexToFloat(data))
                    + DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type == TypedValue.TYPE_FRACTION) {
            return Float.toString(complexToFloat(data))
                    + FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
        }
        if (type >= TypedValue.TYPE_FIRST_COLOR_INT
                && type <= TypedValue.TYPE_LAST_COLOR_INT) {
            return String.format("#%08X", data);
        }
        if (type >= TypedValue.TYPE_FIRST_INT
                && type <= TypedValue.TYPE_LAST_INT) {
            return String.valueOf(data);
        }
        return String.format("<0x%X, type 0x%02X>", data, type);
    }

    private static String getPackage(int id) {
        if (id >>> 24 == 1) {
            return "android:";
        }
        return "";
    }

    private static void log(String format, StringBuffer xmlBuffer,
            Object... arguments) {
        Formatter formatter = new Formatter();
        formatter.format(Locale.getDefault(), format, arguments);
        xmlBuffer.append(formatter.toString());
    }

    public static float complexToFloat(int complex) {
        return (float) (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3];
    }
}
