package com.coship.sdp.sce.dp.utils;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.List;

import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.utils.Debug;

/**
 * 公用方法. <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-13]
 * @since [产品/模块版本]
 */
public class MethodsUtil
{

    private static final String MODULE = MethodsUtil.class.getName();

    /**
     * 拼装URL地址：http://ip+":"+port.
     * 
     * @return String
     */
    public static final String initURL(final String serverIp,
            final int serverPort)
    {
        // 网络ip地址
        String appStoreIp = null;
        // 端口地址
        String port;
        // url http://ip+":"+port.
        String url = null;

        // 获取当前服务器ip
        if (isNull(Constants.APPSTORE_UPLOAD_IP))
        {
            appStoreIp = serverIp;
        }
        else
        {
            appStoreIp = Constants.APPSTORE_UPLOAD_IP;
        }

        // 获取当然服务器端口号
        if (isNull(Constants.APPSTORE_UPLOAD_PORT))
        {
            port = String.valueOf(serverPort);
        }
        else
        {
            port = Constants.APPSTORE_UPLOAD_PORT;
        }

        url = "http://" + appStoreIp + ":" + port;

        return url;

    }
    
    /**
     * URL地址：http://ip+":"+port. (保存apk文件的路径)
     * 
     * @return String
     */
    public static final String initApkURL(final String serverIp, final int serverPort){
        // 网络ip地址
        String appStoreIp = null;
        // 端口地址
        String port;
        // url http://ip+":"+port.
        String url = null;

        // 获取当前服务器ip
        if (isNull(Constants.APPSTORE_APK_UPLOAD_IP)){
            appStoreIp = serverIp;
        } else{
            appStoreIp = Constants.APPSTORE_APK_UPLOAD_IP;
        }

        // 获取当然服务器端口号
        if (isNull(Constants.APPSTORE_APK_UPLOAD_PORT)) {
            port = String.valueOf(serverPort);
        }else{
            port = Constants.APPSTORE_APK_UPLOAD_PORT;
        }

        url = "http://" + appStoreIp + ":" + port;

        return url;
    }

    /**
     * 判断对象是否为空.
     * @param str 字符串
     * @return boolean 布尔值 true为空 ，false 不为空
     */
    public static final boolean isNull(final String str)
    {
        boolean nullFlag = false;

        if (null == str || str.isEmpty())
        {
            nullFlag = true;
        }

        return nullFlag;

    }

    /**
     * 判断对象是否为空.
     * @param list List
     * @return boolean 布尔值 true为空 ，false 不为空
     */
    public static final boolean isNull(final List<?> list)
    {
        boolean nullFlag = false;

        if (null == list || list.isEmpty())
        {
            nullFlag = true;
        }

        return nullFlag;

    }

    /**
     * 签名程序.
     * 
     * @param publicKeyName 签名证书
     * @param privateKeyName 签名证书私钥
     * @param sourceFileName 原始文件
     * @param targetFileName 签名后的
     * @return boolean 签名是否成功,true 成功，false不成功
     * @exception throws [违例类型] [违例说明]
     */
    public static boolean signed(String publicKeyName, String privateKeyName,
            String sourceFileName, String targetFileName)
    {

        boolean flag = true;
        // 删除文件注释标识.
        boolean retDeleteFileComment = false;
        // 签名信息.
        String retStringSignInfo = null;
        // 添加文件注释标识.
        boolean retAddFileComment = false;
        // 文件注释.
        String fileComment = null;

        if ((fileComment = SignAndCheckApk.getZipComment(sourceFileName)) != null)
        {

            // try -1 begin
            try
            {

                // if +1 begin
                if (retDeleteFileComment = SignAndCheckApk.deleteFileComment(
                        sourceFileName, fileComment, targetFileName))
                {

                    // try -2 begin
                    try
                    {
                        X509Certificate publicKey = SignAndCheckApk
                                .readPublicKey(new File(publicKeyName));
                        PrivateKey privateKey = SignAndCheckApk
                                .readPrivateKey(new File(privateKeyName));

                        // if +2 begin
                        if ((retStringSignInfo = SignAndCheckApk.getSignInfo(
                                targetFileName, publicKey, privateKey)) != null)
                        {
                            // if +3 begin
                            if ((retAddFileComment = SignAndCheckApk
                                    .addFileComment(targetFileName,
                                            retStringSignInfo)))
                            {
                                Debug.logInfo("addFileComment, successful");

                            }
                            else
                            {
                                Debug.logInfo("error: while call function addFileComment, return retAddFileComment="
                                        + retAddFileComment);
                                flag = false;

                            }
                            // if +3 end

                        }
                        else
                        {
                            Debug.logInfo("error: while call function getSignInfo, return retStringSignInfo="
                                    + retStringSignInfo);
                            flag = false;
                        }
                        // if +2 end

                    }
                    catch (GeneralSecurityException e)
                    {

                        Debug.logError(e, e.getMessage(), MODULE);
                    }
                    // try -2 end

                }
                else
                {
                    Debug.logInfo("error: while call function deleteFileComment, retDeleteFileComment="
                            + retDeleteFileComment);
                    flag = false;
                }
                // if +1 end
            }
            catch (IOException e)
            {

                Debug.logError(e, e.getMessage(), MODULE);
            }

            // try -1 end
        }
        else
        {
            Debug.logInfo("error: while call function getZipComment: fileComment="
                    + fileComment);
            flag = false;
        }

        return flag;
    }

    /**
     * 字符串转数组.
     * 
     * @param string 字符串
     * @param splitFlag 分隔符
     * @return String[] 数组
     * @exception throws [违例类型] [违例说明]
     */
    public static String[] stringToArray(String string, String splitFlag)
    {

        String[] strings = null;

        if (isNull(string))
        {
           
        }
        else
        {            
            strings = string.split(splitFlag);
        }

        return strings;
    }

    /**
     * 判断文件是否存在
     * 
     * @param filePath 文件路径   
     * @return boolean true 存在,flase不存在
     * @exception throws [违例类型] [违例说明]
     */
    public static boolean isFileExists(String filePath){
       
        boolean flag = false;
        
        File file = new File(filePath);
        
        if(file.exists()){
            
            flag = true;
            
        }
        return flag;
    }
}
