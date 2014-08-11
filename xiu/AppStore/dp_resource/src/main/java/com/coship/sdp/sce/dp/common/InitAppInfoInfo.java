package com.coship.sdp.sce.dp.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 初始化应用信息并将其存储到内存中
 */
public class InitAppInfoInfo {
	
	private static final Logger logger = LoggerFactory.getLogger(InitAppInfoInfo.class);

	
	/**
	 * 执行初始化，将各种信息放入缓存
	 * @param appstoreIp
	 * @param port
	 */
	public static void executeInit(String appstoreIp, String port) {
		/**
		 * 获取应用的排行列表
		 */
		// 按热门排行
		getRankAppInfoDetailList(Constants.APP_ORDER_HOT, appstoreIp, port);
		// 按最新排行
		getRankAppInfoDetailList(Constants.APP_ORDER_NEW, appstoreIp, port);
		// 按免费排行
		getRankAppInfoDetailList(Constants.APP_ORDER_FREE, appstoreIp, port);
		// 按收费排行
		getRankAppInfoDetailList(Constants.APP_ORDER_PAY, appstoreIp, port);
		// 按好评排行
		getRankAppInfoDetailList(Constants.APP_ORDER_GOOD, appstoreIp, port);

		/**
		 * 获取专题下的应用排行列表
		 */
		// 按热门排行
		getSubAppInfoDetailList(Constants.APP_ORDER_HOT, appstoreIp, port);
		// 按最新排行
		getSubAppInfoDetailList(Constants.APP_ORDER_NEW, appstoreIp, port);
		// 按好评排行
		getSubAppInfoDetailList(Constants.APP_ORDER_GOOD, appstoreIp,  port);
		// 无指定排序
		getSubAppInfoDetailList(Constants.APP_ORDER_ALL, appstoreIp,  port);


		// 获取推荐应用列表
		getRecommendAppList(appstoreIp,  port);
		// 获取推荐分类列表
		getRecommendTypeList(appstoreIp,  port);
		// 获取推荐分类下关联的应用列表
		getRecommendAppTypeList(appstoreIp,  port);
		
		
		// 查询签名证书列表2013-08-23
		getEnableCertificate(appstoreIp,  port);
		// 查询吊销签名证书列表2013-08-23
		getRevokeCertificate(appstoreIp,  port);
		
        // 应用商店首页(精选页)
		getPlanAppRecommendPanelItemJsonMap(appstoreIp, port);
        // 初始化方案map.
        getPlanMap(appstoreIp, port);
        // 初始化条件方案map.
        getConditionPlanIdMap(appstoreIp, port);
        // 初始化方案分类map.
        getPlanDpTypeIdMap(appstoreIp, port);
        getPlanSelfTypeMap(appstoreIp, port);
        // 初始化方案分类下的应用map.
        getPlanDpTypeAppPackageNameMap(appstoreIp, port);
        // 初始化方案下的专题map.
        getPlanSubjectTypeMap(appstoreIp, port);
        // 初始化方案下的所有应用map.
        getPlanAppPackageNameMap(appstoreIp, port);
        
        //获取系统参数Map
        getSystemParamMap(appstoreIp, port);
        
        //获取缩略图信息
        getAppThumbnail(appstoreIp, port);
        
        clearOther(appstoreIp, port);
        
	}

	
	//获取推荐分类列表
	private static void getRecommendTypeList(String appstoreIp, String port)
	{
		long t1 = System.currentTimeMillis();
		String uri = "http://" + appstoreIp + ":" + port
			+ "/appstore_service/appStore!initRecommendTypeList.action";

		// http post请求
		httpRequest(uri, "");

		long t2 = System.currentTimeMillis();
		logger.info("------------execute initRecommendTypeList() time-------------" + (t2-t1) + "ms");
		
	}

	/**
	 * 获取排行应用
	 */
	private static void getRankAppInfoDetailList(String rankType, String appstoreIp, String port)
	{
		long t1 = System.currentTimeMillis();
		String uri = "http://" + appstoreIp + ":" + port
			+ "/appstore_service/appStore!initAppList.action";

		// http post请求
		httpRequest(uri, rankType);

		long t2 = System.currentTimeMillis();
		logger.info("------------execute getRankAppInfoDetailList() time-------------" + (t2-t1) + "ms");
	}


	/**
	 * 获取专题排行应用
	 */
	private static void getSubAppInfoDetailList(String rankType, String appstoreIp, String port)
	{
		long t1 = System.currentTimeMillis();
		String uri = "http://" + appstoreIp + ":" + port
			+ "/appstore_service/appStore!initSubjectAppList.action";

		// http post请求
		httpRequest(uri, rankType);

		long t2 = System.currentTimeMillis();
		logger.info("------------execute getSubAppInfoDetailList() time-------------" + (t2-t1) + "ms");
	}

	/**
	 * 获取推荐应用列表
	 */
	private static void getRecommendAppList(String appstoreIp, String port)
	{
		long t1 = System.currentTimeMillis();
		String uri = "http://" + appstoreIp + ":" + port
			+ "/appstore_service/appStore!initRecommendAppList.action";

		// http post请求
		httpRequest(uri, "");

		long t2 = System.currentTimeMillis();
		logger.info("------------execute getRecommendAppList() time-------------" + (t2-t1) + "ms");
	}

	/**
	 * HTTP POST请求
	 */
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
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取推荐分类下关联的应用列表
	 */
	private static void getRecommendAppTypeList(String appstoreIp, String port)
	{
		long t1 = System.currentTimeMillis();
		String uri = "http://" + appstoreIp + ":" + port
			+ "/appstore_service/appStore!initRecommendAppTypeList.action";

		// http post请求
		httpRequest(uri, "");

		long t2 = System.currentTimeMillis();
		logger.info("------------execute getRecommendAppTypeList() time-------------" + (t2-t1) + "ms");
	}
	
	/**
	 * 获取证书列表
	 */
	private static void getEnableCertificate(String appstoreIp, String port)
	{
		long t1 = System.currentTimeMillis();
		String uri = "http://" + appstoreIp + ":" + port
			+ "/appstore_service/appStore!initCertificate.action";

		// http post请求
		httpRequest(uri, "");

		long t2 = System.currentTimeMillis();
		logger.info("------------execute getEnableCertificate() time-------------" + (t2-t1) + "ms");
	}
	//
	
	/**
	 * 获取吊销证书列表
	 */
	private static void getRevokeCertificate(String appstoreIp, String port)
	{
		long t1 = System.currentTimeMillis();
		String uri = "http://" + appstoreIp + ":" + port
			+ "/appstore_service/appStore!initRevokeCertificate.action";

		// http post请求
		httpRequest(uri, "");

		long t2 = System.currentTimeMillis();
		logger.info("------------execute getRevokeCertificate() time-------------" + (t2-t1) + "ms");
	}

	/**
	 * 获取精选页Json数据.
	 * 
	 * @param appstoreIp
	 * @param port
	 */
	private static void getPlanAppRecommendPanelItemJsonMap(String appstoreIp, String port) {
		long start = System.currentTimeMillis();
        String uri = "http://" + appstoreIp + ":" + port
                + "/appstore_service/appStore!initPlanAppRecommendPanelItemJsonMap";

		// http post请求
		httpRequest(uri, "");

		long end = System.currentTimeMillis();
        logger.info("------------execute getPlanAppRecommendPanelItemJsonMap() time-------------" + (end - start)
                + "ms");
	}

    /**
     * 初始化方案Map.
     * 
     * @param appstoreIp
     * @param port
     */
    private static void getPlanMap(String appstoreIp, String port) {
        long start = System.currentTimeMillis();
        String uri = "http://" + appstoreIp + ":" + port + "/appstore_service/appStore!initPlanMap";

        // http post请求
        httpRequest(uri, "");

        long end = System.currentTimeMillis();
        logger.info("------------execute getPlanMap() time-------------" + (end - start) + "ms");
    }

    /**
     * 
     * @param appstoreIp
     * @param port
     */
    private static void getConditionPlanIdMap(String appstoreIp, String port) {
        long start = System.currentTimeMillis();
        String uri = "http://" + appstoreIp + ":" + port + "/appstore_service/appStore!initConditionPlanIdMap";

        // http post请求
        httpRequest(uri, "");

        long end = System.currentTimeMillis();
        logger.info("------------execute getConditionPlanIdMap() time-------------" + (end - start) + "ms");
    }

    /**
     * 
     * @param appstoreIp
     * @param port
     */
    private static void getPlanDpTypeIdMap(String appstoreIp, String port) {
        long start = System.currentTimeMillis();
        String uri = "http://" + appstoreIp + ":" + port + "/appstore_service/appStore!initPlanDpTypeIdMap";

        // http post请求
        httpRequest(uri, "");

        long end = System.currentTimeMillis();
        logger.info("------------execute getPlanDpTypeIdMap() time-------------" + (end - start) + "ms");
    }
    
    private static void getPlanSelfTypeMap(String appstoreIp, String port) {
        long start = System.currentTimeMillis();
        String uri = "http://" + appstoreIp + ":" + port + "/appstore_service/appStore!initPlanSelfTypeMap";

        // http post请求
        httpRequest(uri, "");

        long end = System.currentTimeMillis();
        logger.info("------------execute getPlanSelfTypeMap() time-------------" + (end - start) + "ms");
    }

    /**
     * 
     * @param appstoreIp
     * @param port
     */
    private static void getPlanDpTypeAppPackageNameMap(String appstoreIp, String port) {
        long start = System.currentTimeMillis();
        String uri = "http://" + appstoreIp + ":" + port + "/appstore_service/appStore!initPlanDpTypeAppPackageNameMap";

        // http post请求
        httpRequest(uri, "");

        long end = System.currentTimeMillis();
        logger.info("------------execute getPlanDpTypeAppPackageNameMap() time-------------" + (end - start) + "ms");
    }

    /**
     * 
     * @param appstoreIp
     * @param port
     */
    private static void getPlanAppPackageNameMap(String appstoreIp, String port) {
        long start = System.currentTimeMillis();
        String uri = "http://" + appstoreIp + ":" + port + "/appstore_service/appStore!initPlanAppPackageNameMap";

        // http post请求
        httpRequest(uri, "");

        long end = System.currentTimeMillis();
        logger.info("------------execute getPlanAppPackageNameMap() time-------------" + (end - start) + "ms");
    }

    /**
     * 
     * @param appstoreIp
     * @param port
     */
    private static void getPlanSubjectTypeMap(String appstoreIp, String port) {
        long start = System.currentTimeMillis();
        String uri = "http://" + appstoreIp + ":" + port + "/appstore_service/appStore!initPlanSubjectTypeMap";

        // http post请求
        httpRequest(uri, "");

        long end = System.currentTimeMillis();
        logger.info("------------execute getPlanSubjectTypeMap() time-------------" + (end - start) + "ms");
    }
    
	
	/**
	 * 获取系统参数
	 */
	private static void getSystemParamMap(String appstoreIp, String port)
	{
		long t1 = System.currentTimeMillis();
		String uri = "http://" + appstoreIp + ":" + port
			+ "/appstore_service/appStore!initSystemParamMap.action";

		// http post请求
		httpRequest(uri, "");

		long t2 = System.currentTimeMillis();
		logger.info("------------execute getSystemParamMap() time-------------" + (t2-t1) + "ms");
	}
	
    /**
     * 
     * @param appstoreIp
     * @param port
     */
    private static void getAppThumbnail(String appstoreIp, String port) {
        long start = System.currentTimeMillis();
        String uri = "http://" + appstoreIp + ":" + port + "/appstore_service/appStore!initAppThumbnail";

        // http post请求
        httpRequest(uri, "");

        long end = System.currentTimeMillis();
        logger.info("------------execute getAppThumbnail() time-------------" + (end - start) + "ms");
    }
    
	
    /**
     * 
     * @param appstoreIp
     * @param port
     */
    private static void clearOther(String appstoreIp, String port) {
        long start = System.currentTimeMillis();
        String uri = "http://" + appstoreIp + ":" + port + "/appstore_service/appStore!clearOther";

        // http post请求
        httpRequest(uri, "");

        long end = System.currentTimeMillis();
        logger.info("------------execute clearOther() time-------------" + (end - start) + "ms");
    }
    
    
	
	
	
}
