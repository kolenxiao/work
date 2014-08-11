/*
 * 文件名称：AppStoreActionTest.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-10-10
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.appstore.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.springframework.util.Assert;

import com.coship.appstore.service.common.AppConstants;
import com.coship.appstore.service.dto.ResponseDTO;
import com.coship.appstore.service.utils.HttpTestUtil;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-10-10]
 * @since [产品/模块版本]
 */
public class AppStoreActionTest
{

    public static final String SERVER_URL = "http://10.10.119.3:8280/appstore_service/appStore!";

    /**
     * 获取应用分类
     */
    @Test
    public void testGetAppTypeList()
    {
        String url = SERVER_URL + "getAppTypeList.action";
        String body = HttpTestUtil.doGet(url, null);
        Assert.notNull(body);
        JSONObject jsonObject = JSONObject.fromObject(body);

        ResponseDTO responseDTO = (ResponseDTO) JSONObject.toBean(jsonObject,
                ResponseDTO.class);
        Assert.notNull(responseDTO);
        Assert.notNull(responseDTO.getRespseStatus());
    }

    /**
     * 获取应用分类下的应用列表
     */
    @Test
    public void testGetTypeAppInfoList()
    {
        String url = SERVER_URL + "getTypeAppInfoList.action";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("typeId", "10"));
        params.add(new BasicNameValuePair("orderBy", "1"));
        params.add(new BasicNameValuePair("start", "1"));
        params.add(new BasicNameValuePair("limit", "10"));
        String body = HttpTestUtil.doGet(url, params);
        Assert.notNull(body);
        JSONObject jsonObject = JSONObject.fromObject(body);

        ResponseDTO responseDTO = (ResponseDTO) JSONObject.toBean(jsonObject,
                ResponseDTO.class);
        Assert.notNull(responseDTO);
    }

    /**
     * 获取我的应用列表
     * @param <NameValuePair>
     */
    @Test
    public void testGetMyAppList()
    {
        String url = SERVER_URL + "getMyAppList.action";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("userId", "xxxxx"));
        String body = HttpTestUtil.doGet(url, params);
        Assert.notNull(body);
        JSONObject jsonObject = JSONObject.fromObject(body);

        ResponseDTO responseDTO = (ResponseDTO) JSONObject.toBean(jsonObject,
                ResponseDTO.class);
        Assert.notNull(responseDTO);
    }

    /**
     * 添加我的应用
     */
    @Test
    public void testAddMyApp()
    {
        // TODO
    }

    /**
     * 删除应用的评论
     */
    @Test
    public void testDelMyApp()
    {
        // TODO
    }

    /**
     * 获取应用的评论
     */
    @Test
    public void testGetAppComment()
    {
        String url = SERVER_URL + "getAppComment.action";

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("appCode", "xxxxx"));
        params.add(new BasicNameValuePair("start", "1"));
        params.add(new BasicNameValuePair("limit", "10"));
        String body = HttpTestUtil.doGet(url, params);
        Assert.notNull(body);
        JSONObject jsonObject = JSONObject.fromObject(body);

        ResponseDTO responseDTO = (ResponseDTO) JSONObject.toBean(jsonObject,
                ResponseDTO.class);
        Assert.notNull(responseDTO);
    }

    /**
     * 添加评论
     */
    @Test
    public void testAddAppComment()
    {
        // TODO
    }

    /**
     * 获取专题列表
     */
    @Test
    public void testGetAppSubjectList()
    {
        String url = SERVER_URL + "getAppSubjectList.action";
        String body = HttpTestUtil.doGet(url, null);
        Assert.notNull(body);
        JSONObject jsonObject = JSONObject.fromObject(body);
        ResponseDTO responseDTO = (ResponseDTO) JSONObject.toBean(jsonObject,
                ResponseDTO.class);
        Assert.notNull(responseDTO);
    }

    /**
     * 专题应用列表
     */
    @Test
    public void testGetSubjectAppInfoList()
    {
        String url = SERVER_URL + "getSubjectAppInfoList.action";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("subjectId", "xxxxx"));
        params.add(new BasicNameValuePair("orderBy", "1"));
        params.add(new BasicNameValuePair("start", "1"));
        params.add(new BasicNameValuePair("limit", "10"));
        String body = HttpTestUtil.doGet(url, params);
        Assert.notNull(body);
        JSONObject jsonObject = JSONObject.fromObject(body);
        ResponseDTO responseDTO = (ResponseDTO) JSONObject.toBean(jsonObject,
                ResponseDTO.class);
        Assert.notNull(responseDTO);
    }

    /**
     * 获取推荐应用列表
     */
    @Test
    public void testGetRecommendAppList()
    {
        String url = SERVER_URL + "getRecommendAppList.action";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("limit", "20"));
        String body = HttpTestUtil.doGet(url, params);
        Assert.notNull(body);
        JSONObject jsonObject = JSONObject.fromObject(body);
        ResponseDTO responseDTO = (ResponseDTO) JSONObject.toBean(jsonObject,
                ResponseDTO.class);
        Assert.notNull(responseDTO);
    }

    /**
     * 获取排行应用分页列表
     */
    @Test
    public void testGetRankAppList()
    {
        String url = SERVER_URL + "getRankAppList.action";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("start", "1"));
        params.add(new BasicNameValuePair("limit", "10"));
        params.add(new BasicNameValuePair("rankType", "1"));
        params.add(new BasicNameValuePair("typeId", "10"));
        String body = HttpTestUtil.doGet(url, params);

        Assert.notNull(body);
        JSONObject jsonObject = JSONObject.fromObject(body);
        ResponseDTO responseDTO = (ResponseDTO) JSONObject.toBean(jsonObject,
                ResponseDTO.class);
        Assert.notNull(responseDTO);
    }

    /**
     * 获取可更新的应用列表
     */
    @Test
    public void testGetUpdateAppList()
    {
        String url = SERVER_URL + "getAppTypeList.action";
        String body = HttpTestUtil.doGet(url, null);
        Assert.notNull(body);
        JSONObject jsonObject = JSONObject.fromObject(body);
        String respseDesc = jsonObject.getString("respseDesc");
        Assert.notNull(respseDesc);
        String respseStatus = jsonObject.getString("respseStatus");
        Assert.notNull(respseStatus);
        Assert.isTrue(StringUtils.equals(respseStatus,
                AppConstants.RESPOSE_STATUS_SUCESS));
    }
}
