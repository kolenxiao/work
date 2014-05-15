package com.coship.sdp.sce.dp.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 利用HttpComponents组件发送请求
 * 
 * @author kolenxiao
 * 
 */
public class HttpUtils {

	private static int TIME_OUT_CONNECTION = 10 * 1000; // 设置连接超时时间(10秒)
	private static int TIME_OUT_SOTIMEOUT = 20 * 1000; // 设置等待数据超时时间(20秒)

	/**
	 * get请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @return 服务器响应字符串
	 * @throws Exception
	 */
	public static String doGet(String url) throws Exception {
		// 创建HttpGet对象。
		HttpGet httpGet = new HttpGet(url);

		// 发送GET请求
		HttpClient httpClient = getDefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpGet);

		// 返回响应
		return getResponseResult(httpResponse);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param rawParams
	 *            请求参数
	 * @return 服务器响应字符串
	 * @throws Exception
	 */
	public static String doPost(String url, Map<String, String> rawParams)
			throws Exception {
		// 创建HttpPost对象
		HttpPost httpPost = new HttpPost(url);

		// 封装、设置请求参数
		List<NameValuePair> httpParams = new ArrayList<NameValuePair>();
		for (String key : rawParams.keySet()) {
			httpParams.add(new BasicNameValuePair(key, rawParams.get(key)));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(httpParams, HTTP.UTF_8));
		// System.out.println("executing request " + httpPost.getURI());

		// 发送POST请求
		HttpClient httpClient = getDefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// 返回响应
		return getResponseResult(httpResponse);
	}

	// 获取HttpClient
	private static HttpClient getDefaultHttpClient() {
		// 创建HttpClient并设置连结属性
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				TIME_OUT_CONNECTION);
		HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT_SOTIMEOUT);
		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		return httpClient;
	}

	// 获取服务器响应字符串
	private static String getResponseResult(HttpResponse httpResponse)
			throws ParseException, IOException {
		// 如果服务器成功地返回响应
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			// 获取服务器响应字符串
			HttpEntity entity = httpResponse.getEntity();
			return EntityUtils.toString(entity, HTTP.UTF_8);
		}
		return null;
	}

	public static void main(String[] args) {
		String url = "http://localhost:8921/appstore_service/appStore!submitUserOpera";

		String response = "";
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("optType", "8");
			params.put("optContentId", "com.kugou.playerHD");
			params.put("deviceMac", "84:62:23:1d:47:79");
			params.put("optContent", URLEncoder.encode("酷狗音乐22", "UTF-8"));

			response = doPost(url, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("response: " + response);
	}

}
