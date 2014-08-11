/*
 * 文 件 名：ApkUtil.java
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：android应用apk文件操作工具类
 * 修 改 人：wangchenbo/906055
 * 修改时间：2012-9-26
 * 修改内容：<修改内容>
 */

package com.coship.sdp.sce.dp.utils;

import java.io.File;
import java.util.Set;

import oracle.net.aso.n;

import brut.androlib.AndrolibException;
import brut.androlib.ApkDecoder;
import brut.androlib.res.data.ResPackage;

import com.coship.sdp.utils.Debug;

/**
 * android应用apk文件操作工具类.
 *
 * @author 906055
 *
 */
public class ApkUtil
{

    /**
     * 类名称.
     */
    private static final String MODULE = ApkUtil.class.getName();

    /**
     * 获得apk文件的包名.
     *
     * @param apk android应用
     * @return apk文件的包名
     */
    public static String getApkPkgName(String apk)
    {

        String apkPkgName = null;

        ApkDecoder apkDecoder = new ApkDecoder();

        File file = new File(apk);

        apkDecoder.setApkFile(file);

        try
        {

            Set<ResPackage> p = apkDecoder.getResTable().listMainPackages();

            for (ResPackage r : p)
            {

                // 这里set最大只会等于1打印出包名
                apkPkgName = r.getName();

                if (!MethodsUtil.isNull(apkPkgName))
                {
                    break;
                }

            }
        }
        catch (AndrolibException e)
        {

            Debug.logError(e, e.getMessage(), MODULE);
        }
        finally
        {

        }
        return apkPkgName;
    }

    /**
     * 获得apk信息:数字证书(公钥)、版本编号、版本名称、应用包名、应用运行最低sdk版本编码
     *
     * @param apkPath apk路径
     * @return ApkInfo 获得apk信息
     *
     */
    public static ApkInfo getApkInfo(String apkPath)
    {
        File apk = new File(apkPath);
        ApkInfo apkInfo = ApkParserUtil.parser(apk);
        return apkInfo;

    }

    public static void main(String[] args) {
        String str = "<a href='#'>ddddddd,</a><span style='display:none;' mce_style='display:none;''>This is test</span><img src=''></img><strong></strong><br/>";
        String s1=str.replaceAll("</?[^>]+>", "");
        System.out.println(s1);
    }

}
