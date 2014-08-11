/*
 * 文件名称：MyFavoriteServiceImplTest.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Oct 19, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.appstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.coship.sdp.sce.dp.appstore.entity.MyFavorite;
import com.coship.sdp.sce.dp.appstore.service.MyFavoriteService;
import com.coship.sdp.test.utils.DbUnitUtils;
import com.coship.sdp.test.utils.SpringTestCase;

/**
 * <功能描述>
 * @author  Huangliufei/905735
 * @version  [版本号, Oct 19, 2011]
 * @since  [产品/模块版本]
 */
@ContextConfiguration(locations =
{ "classpath:applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = true)
public class MyFavoriteServiceImplTest extends SpringTestCase
{
    /**
     * 我的收藏接口服务层
     */
    @Autowired
    private MyFavoriteService myFavoriteService;
	private static DataSource dataSourceHolder = null;

	/**
	 * 初始化数据
	 *
	 * @throws java.lang.Exception
	 *             [参数说明]
	 * @exception 抛出异常
	 */
	@Before
	public void setUp() throws Exception {
		// 批量向数据库中插入数据
		if (dataSourceHolder == null) {
			DbUnitUtils.loadData(dataSource, "/data/data.xml");
			dataSourceHolder = dataSource;
		}

	}

	/**
	 * 清除数据.
	 *
	 * @throws Exception
	 */
	@AfterClass
	public static void cleanData() throws Exception {
		DbUnitUtils.removeData(dataSourceHolder, "/data/data.xml");
	}

    /**
     * Test method for {@link com.coship.sdp.sce.dp.appstore.service.impl.MyFavoriteServiceImpl#saveMyFavorite(com.coship.sdp.sce.dp.appstore.entity.MyFavorite)}.
     * @throws Exception
     */
    @Test
    public void testSaveMyFavorite() throws Exception
    {
    	MyFavorite myFavorite=new MyFavorite();
    	myFavorite.setAppId("2");
    	myFavorite.setFavoriteTime(new Date());
    	myFavorite.setUid("10000");
        this.myFavoriteService.saveMyFavorite(myFavorite);
    }

    /**
     * Test method for {@link com.coship.sdp.sce.dp.appstore.service.impl.MyFavoriteServiceImpl#deleteMyFavorite(com.coship.sdp.sce.dp.appstore.entity.MyFavorite)}.
     * @throws Exception
     */
    @Test
    public void testDeleteMyFavorite() throws Exception
    {

    	    List<MyFavorite> myFavoriteList= this.myFavoriteService.findMyFavorite("1000","2");
    	    if(null != myFavoriteList && myFavoriteList.size()>0){
    	        for(MyFavorite myFavorite:myFavoriteList){
    	            this.myFavoriteService.deleteMyFavorite(myFavorite);
    	        }
    	    }
    }

    /**
     * Test method for {@link com.coship.sdp.sce.dp.appstore.service.impl.MyFavoriteServiceImpl#findByUid(java.lang.String)}.
     * @throws Exception
     */
    @Test
    public void testFindByUidNull() throws Exception
    {
    	List<String> appId = null;
    	appId = this.myFavoriteService.findByUid("1001");
    	Assert.assertEquals(0,appId.size());
    }
    /**
     * 根据正常流程
     * Test method for {@link com.coship.sdp.sce.dp.appstore.service.impl.MyFavoriteServiceImpl#findByUid(java.lang.String)}.
     * @throws Exception
     */
    @Test
    public void testFindByUidNotNull() throws Exception
    {
    	List<String> appId = null;
    	appId = this.myFavoriteService.findByUid("1000");
    	Assert.assertNotNull(appId);
    }
    /**
     * Test method for {@link com.coship.sdp.sce.dp.appstore.service.impl.MyFavoriteServiceImpl#findMyFavorite(java.lang.String, java.lang.Long)}.
     * @throws Exception
     */
    @Test
    public void testFindMyFavorite() throws Exception
    {
    	List<MyFavorite> myAppList= this.myFavoriteService.findMyFavorite("1000","1");
    	Assert.assertNotNull(myAppList);
    }

}
