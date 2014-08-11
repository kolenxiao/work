/*
 * 文件名称：MyAppServiceImplTest.java
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
import java.util.List;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.coship.sdp.sce.dp.appstore.entity.MyApp;
import com.coship.sdp.sce.dp.appstore.service.MyAppService;
import com.coship.sdp.test.utils.DbUnitUtils;
import com.coship.sdp.test.utils.SpringTestCase;

/**
 * <功能描述>
 *
 * @author Huangliufei/905735
 * @version [版本号, Oct 19, 2011]
 * @since [产品/模块版本]
 */
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
// @TransactionConfiguration(defaultRollback = true)
public class MyAppServiceImplTest extends SpringTestCase {

	/**
	 * 我的应用接口服务层
	 */
	@Autowired
	private MyAppService myAppService;
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
	 * Test method for
	 * {@link com.coship.sdp.sce.dp.appstore.service.impl.MyAppServiceImpl#saveMyApp(com.coship.sdp.sce.dp.appstore.entity.MyApp)}
	 * .
	 *
	 * @throws Exception
	 */
	@Test
	public void testSaveMyApp() throws Exception {
		MyApp myApp = new MyApp();
		myApp.setAppId("1");
		myApp.setUid("10000");
		this.myAppService.saveMyApp(myApp);
	}

	/**
	 * Test method for
	 * {@link com.coship.sdp.sce.dp.appstore.service.impl.MyAppServiceImpl#deleteMyApp(com.coship.sdp.sce.dp.appstore.entity.MyApp)}
	 * .
	 *
	 * @throws Exception
	 */
	@Test
	public void testDeleteMyApp() throws Exception {

		List<MyApp> myAppList =this.myAppService.findMyApp("1000", "2");
		if (null != myAppList && myAppList.size() > 0) {
			for (MyApp myApp : myAppList) {
				this.myAppService.deleteMyApp(myApp);
			}
		}

	}

	/**
	 * Test method for
	 * {@link com.coship.sdp.sce.dp.appstore.service.impl.MyAppServiceImpl#findByUid(java.lang.String)}
	 * . 根据正常情况查找数据库中存在的
	 *
	 * @throws Exception
	 */
	@Test
	public void testFindByUid() throws Exception {
		List<String> appId = null;

		appId = this.myAppService.findByUid("1000");
		Assert.assertNotNull(appId);
	}
	/**
	 * 根据机顶盒id查询不存在的
	 * @throws Exception
	 */
	@Test
	public void testFindByUidNull() throws Exception {
		List<String> appId = null;

		appId = this.myAppService.findByUid("1001");
		Assert.assertEquals(0,appId.size());
	}
	/**
	 * Test method for {@link
	 * com.coship.sdp.sce.dp.appstore.service.impl.MyAppServiceImpl.
	 * #findMyApp(java.lang.String, java.lang.Long)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void testFindMyApp() throws Exception {
		List<MyApp> myAppList = null;
		myAppList = this.myAppService.findMyApp("1000", "1");
		Assert.assertNotNull(myAppList);
		Assert.assertEquals(1,myAppList.size());

	}

	/**
	 * 测试不存在的应用
	 *
	 * @throws Exception
	 */
	@Test
	public void testFindMyAppNull() throws Exception {
		List<MyApp> myAppList = null;
		myAppList = this.myAppService.findMyApp("10000", "1");
		Assert.assertNull(myAppList);
	}

}
