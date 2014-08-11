package com.coship.sdp.sce.dp.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.coship.sdp.utils.Debug;

public class HttpUtil
{
    public static void getAppRecommendPanelItemJson() {
        long start = System.currentTimeMillis();
        String uri = "http://" + Constants.APPSTORE_DPPORTAL_IP + ":" + Constants.APPSTORE_DPPORTAL_PORT
                + "/appstore_service/appStore!initPlanAppRecommendPanelItemJsonMap.action";

        // http post请求
        httpRequest(uri, "");

        long end = System.currentTimeMillis();
        Debug.logVerbose("------------execute getAppRecommendPanelItemJson() time-------------" + (end - start) + "ms");
    }
    
    private static void httpRequest(String uri, String rankType)
    {
        HttpPost httpPost = new HttpPost(uri);
        try
        {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("rankType", rankType));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpClient httpclient = new DefaultHttpClient();
            httpclient.execute(httpPost);
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
	/**
	 * 拼装服务器端资源访问路径.
	 * 
	 * @return String
	 */
	public static final String initURL(final String serverIp,
			final int serverPort) {
		// 访问协议
		String protocol = null;
		// 网络ip地址
		String appStoreIp = null;
		// 端口地址
		String port;
		String url = null;

		// 获取当然服务器端协议
		if (StringUtils.isBlank(Constants.APPSTORE_UPLOAD_PROTOCOL)) {
			protocol = "http://";
		} else {
			protocol = Constants.APPSTORE_UPLOAD_PROTOCOL;
		}

		// 获取当前服务器ip
		if (StringUtils.isBlank(Constants.APPSTORE_UPLOAD_IP)) {
			appStoreIp = serverIp;
		} else {
			appStoreIp = Constants.APPSTORE_UPLOAD_IP;
		}

		// 获取当然服务器端口号
		if (StringUtils.isBlank(Constants.APPSTORE_UPLOAD_PORT)) {
			port = String.valueOf(serverPort);
		} else {
			port = Constants.APPSTORE_UPLOAD_PORT;
		}
		url = protocol + appStoreIp + ":" + port;
		return url;
	}
	
	/**
	 * 拼装服务器端资源访问路径. (保存apk文件的路径)
	 * 
	 * @return String
	 */
	public static final String initApkURL(final String serverIp,
			final int serverPort) {
		// 访问协议
		String protocol = null;
		// 网络ip地址
		String appStoreIp = null;
		// 端口地址
		String port;
		String url = null;

		// 获取当然服务器端协议
		if (StringUtils.isBlank(Constants.APPSTORE_UPLOAD_PROTOCOL)) {
			protocol = "http://";
		} else {
			protocol = Constants.APPSTORE_UPLOAD_PROTOCOL;
		}

		// 获取当前服务器ip
		if (StringUtils.isBlank(Constants.APPSTORE_APK_UPLOAD_IP)) {
			appStoreIp = serverIp;
		} else {
			appStoreIp = Constants.APPSTORE_APK_UPLOAD_IP;
		}

		// 获取当然服务器端口号
		if (StringUtils.isBlank(Constants.APPSTORE_APK_UPLOAD_PORT)) {
			port = String.valueOf(serverPort);
		} else {
			port = Constants.APPSTORE_APK_UPLOAD_PORT;
		}
		url = protocol + appStoreIp + ":" + port;
		return url;
	}
}
