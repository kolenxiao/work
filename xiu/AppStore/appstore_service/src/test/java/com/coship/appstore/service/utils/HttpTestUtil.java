/*
 * 文件名称：HttpTestUtil.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-10-10
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.appstore.service.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-10-10]
 * @since [产品/模块版本]
 */
public class HttpTestUtil
{

    private static HttpClient httpClient = new DefaultHttpClient();

    /**
     * 发送get请求返回字符串
     * @param url 请求的url
     * @param params 请求的参数
     * @return String json字符串
     */
    public static String doGet(String url, List<NameValuePair> params)
    {
        String body = null;
        try
        {
            // Get请求
            HttpGet httpget = new HttpGet(url);
            // 设置参数
            String str = "";
            if (params != null)
            {
                str = EntityUtils.toString(new UrlEncodedFormEntity(params));
            }
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            // 发送请求
            HttpResponse httpresponse = httpClient.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            if (entity != null)
            {
                entity.consumeContent();
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * 发送post请求返回字符串
     * @param url 请求的url
     * @param params 请求的参数
     * @return 返回json字符串
     */
    public static String doPost(String url, List<NameValuePair> params)
    {
        String body = null;
        try
        {
            // Post请求
            HttpPost httppost = new HttpPost(url);
            if (params != null)
            {
                httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            }
            // 设置参数
            // 发送请求
            HttpResponse httpresponse = httpClient.execute(httppost);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            if (entity != null)
            {
                entity.consumeContent();
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return body;
    }
}
