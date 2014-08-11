/*
 * 文件名称：ApkInfo.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-14
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.utils;

import java.security.cert.X509Certificate;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-14]
 * @since [产品/模块版本]
 */
public class ApkInfo {

    /**
     * 数字证书(公钥)
     */
    private X509Certificate publicKey;

    /**
     * 版本编号
     */
    private int versionCode;

    /**
     * 版本名称
     */
    private String versionName;

    /**
     * 应用包名
     */
    private String packageName;

    /**
     * 应用运行最低sdk版本编码
     */
    private int minSdkVersion;

    public X509Certificate getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(X509Certificate publicKey) {
        this.publicKey = publicKey;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getMinSdkVersion() {
        return minSdkVersion;
    }

    public void setMinSdkVersion(int minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }
}
