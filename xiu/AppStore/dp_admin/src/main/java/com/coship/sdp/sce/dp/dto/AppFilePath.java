package com.coship.sdp.sce.dp.dto;

import javax.servlet.http.HttpServletRequest;

import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.utils.MethodsUtil;

/**
 * 应用文件路径对象.
 * <功能描述>
 * @author  wangchenbo/906055
 * @version  [版本号, 2012-9-13]
 * @since  [产品/模块版本]
 */
public class AppFilePath {

    /**
     * 应用logo路径.
     */
    private String logoPath;
    /**
     * 应用截图\海报路径.
     */
    private String imgPath;   
    /**
     * 应用apk路径.
     */
    private String appPath;
    /**
     * 系统别的图片路径.
     */
    private String systemPath;
    /**
     * 开发者头像路径
     */
    private String headIcon;
    
    private static AppFilePath appFilePath;
    
    private AppFilePath(){
    	
    }
    
    public static AppFilePath getAppFilePath(HttpServletRequest request){
    	if(null == appFilePath){
    		appFilePath = new AppFilePath(); 
    		appFilePath.initAppFilePath(request); 
    	}
    	return appFilePath;
    }
    
    /**
     * 初始化logo 截图 应用apk访问的路径前缀.
     *
     * @return void
     */
    public void initAppFilePath(HttpServletRequest request)
    {
       
        // 获取URL地址
        String url = MethodsUtil.initURL(request.getLocalAddr(), request.getServerPort());
        String strApkUrl = MethodsUtil.initApkURL(request.getLocalAddr(),request.getServerPort());
        setLogoPath(url + Constants.APP_LOGO_MAPPE_PATH);
        setImgPath(url + Constants.APP_IMAGES_MAPPE_PATH);
        setSystemPath(url + Constants.SYSTEM_IMAGES_MAPPE_PATH);
        setAppPath(strApkUrl + Constants.APP_FILE_MAPPE_PATH);
        setHeadIcon(url +Constants.APP_DPSTAFF_MAPPE_PATH);
    }
    
    public String getLogoPath()
    {
        return logoPath;
    }
    public void setLogoPath(String logoPath)
    {
        this.logoPath = logoPath;
    }
    public String getImgPath()
    {
        return imgPath;
    }
    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }
   
    public String getAppPath()
    {
        return appPath;
    }
    public void setAppPath(String appPath)
    {
        this.appPath = appPath;
    }

    public String getHeadIcon()
    {
        return headIcon;
    }

    public void setHeadIcon(String headIcon)
    {
        this.headIcon = headIcon;
    }

	public String getSystemPath() {
		return systemPath;
	}

	public void setSystemPath(String systemPath) {
		this.systemPath = systemPath;
	}

   



}
