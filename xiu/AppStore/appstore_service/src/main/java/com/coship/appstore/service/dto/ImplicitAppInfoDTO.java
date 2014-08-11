package com.coship.appstore.service.dto;

/**
 * 隐式应用信息
 *
 * @author 906974
 */
public class ImplicitAppInfoDTO
{

    /**
     * 应用包名
     */
    private String packageName;

    /**
     * 应用的版本编号
     */
    private int versionCode;

    /**
     * 隐式应用的执行类型 1安装、2升级、3卸载
     */
    private int executeAction;

    /**
     * 隐式应用的下载url
     */
    private String downloadUrl;

    /**
     * 应用的签名证书id
     */
    private String certificateId;

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public int getVersionCode()
    {
        return versionCode;
    }

    public void setVersionCode(int versionCode)
    {
        this.versionCode = versionCode;
    }

    public int getExecuteAction()
    {
        return executeAction;
    }

    public void setExecuteAction(int executeAction)
    {
        this.executeAction = executeAction;
    }

    public String getDownloadUrl()
    {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl)
    {
        this.downloadUrl = downloadUrl;
    }

	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

}