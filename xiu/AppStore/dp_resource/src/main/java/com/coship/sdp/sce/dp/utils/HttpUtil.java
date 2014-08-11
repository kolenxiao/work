package com.coship.sdp.sce.dp.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.coship.sdp.sce.dp.common.Constants;

public class HttpUtil {
	
	/**
	 * 更新应用评分action
	 */
	public static final String UPDATE_APPSCORE_ACTION = "/appstore_service/appStore!updateCacheAppScore";
	
	public static void updateAppScore(List<NameValuePair> params){
		HttpUtil.postForMethod(UPDATE_APPSCORE_ACTION, params);
	}

	public static HttpResponse doPost(String url, List<NameValuePair> params){
		HttpResponse httpResponse = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpParams httpParameters = new BasicHttpParams();   
			HttpConnectionParams.setConnectionTimeout(httpParameters, 10*1000);//设置请求超时10秒  
			HttpConnectionParams.setSoTimeout(httpParameters, 10*1000); //设置等待数据超时10秒 
			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			httpResponse = httpclient.execute(httpPost);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return httpResponse;
       
	}
	
	
	/**
	 * 获取接口服务器群的ip地址
	 * @return
	 */
	public static String[] getServerIps(){
		return StringUtils.split(Constants.APPSTORE_CACHE_IP, ";");
	}
	
	/**
	 * 调用指定的方法
	 * @param postForMethod
	 * @param params
	 */
	public static void postForMethod(String methodName, List<NameValuePair> params) {
		String[] ips = HttpUtil.getServerIps();
		for (String ip : ips) {
			String url = "http://" + ip + ":"
					+ Constants.APPSTORE_DPPORTAL_PORT + methodName;
			doPost(url, params);
		}
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("optType", "8"));
		params.add(new BasicNameValuePair("optContentId", "com.kugou.playerHD"));
		params.add(new BasicNameValuePair("deviceMac", "84:62:23:1d:47:79"));
		params.add(new BasicNameValuePair("optContent", URLEncoder.encode("酷狗音乐22", "UTF-8")));
		doPost("http://localhost:8921/appstore_service/appStore!submitUserOpera", params);
	}
	
	
}
