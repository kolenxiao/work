/*
 * 文件名称：AndroidVersionUtil.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-29
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.utils;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-29]
 * @since [产品/模块版本]
 */
public class AndroidVersionUtil
{

    public static String getVersionNameBySdkLevel(int sdkLevel)
    {
        String androidVersionName = "";
        switch (sdkLevel)
        {
            case 1:
                androidVersionName = "android 1.0";
                break;
            case 2:
                androidVersionName = "android 1.1";
                break;
            case 3:
                androidVersionName = "android 1.5";
                break;
            case 4:
                androidVersionName = "android 1.6";
                break;
            case 5:
                androidVersionName = "android 2.0";
                break;
            case 6:
                androidVersionName = "android 2.0.1";
                break;
            case 7:
                androidVersionName = "android 2.1";
                break;
            case 8:
                androidVersionName = "android 2.2";
                break;
            case 9:
                androidVersionName = "android 2.3";
                break;
            case 10:
                androidVersionName = "android 2.3.3";
                break;
            case 11:
                androidVersionName = "android 3.0";
                break;
            case 12:
                androidVersionName = "android 3.1";
                break;
            case 13:
                androidVersionName = "android 3.2";
                break;
            case 14:
                androidVersionName = "android 4.0";
                break;
            case 15:
                androidVersionName = "android 4.0.3";
            case 16:
                androidVersionName = "android 4.1";
                break;
        }
        return androidVersionName;

    }
}
