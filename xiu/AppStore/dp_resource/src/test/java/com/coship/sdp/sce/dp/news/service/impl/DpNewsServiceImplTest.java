/*
 * 文件名称：DpNewsServiceImplTest.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Sep 1, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.news.service.impl;

import java.util.List;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.coship.sdp.sce.dp.news.entity.DpNews;
import com.coship.sdp.sce.dp.news.service.DpNewsService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.test.utils.DbUnitUtils;
import com.coship.sdp.test.utils.SpringTestCase;
import com.coship.sdp.utils.Page;

/**
 * <功能描述>.
 * @author Huangliufei/905735
 * @version [版本号, Sep 1, 2011]
 * @since [产品/模块版本]
 */
@ContextConfiguration(locations =
{ "classpath:applicationContext-test.xml" })
public class DpNewsServiceImplTest extends SpringTestCase
{

    @Autowired
    private DpNewsService dpNewsService;

    @Autowired
    private DpTypeService dpTypeService;

    private static DataSource dataSourceHolder = null;

    /**
     * 初始化数据
     * 
     * @throws java.lang.Exception [参数说明]
     * @exception 抛出异常
     */
    @Before
    public void setUp() throws Exception
    {
        // 批量向数据库中插入数据
        if (dataSourceHolder == null)
        {
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
    public static void cleanData() throws Exception
    {
        DbUnitUtils.removeData(dataSourceHolder, "/data/data.xml");
    }

    /**
     * Test method for {@link com.coship.sdp.sce.dp.news.service.impl.DpNewsServiceImpl#updateDpNews(com.coship.sdp.sce.dp.news.entity.DpNews)} .
     * 
     * @throws Exception
     */
    @Test
    public void testUpdateDpNews() throws Exception
    {

        DpNews dn = new DpNews();
        dn.setId("1");
        String newsTitle = "iOS";
        dn.setNewsTitle(newsTitle);
        this.dpNewsService.updateDpNews(dn);
        Assert.assertEquals(newsTitle, dn.getNewsTitle());

    }

    /**
     * Test method for {@link com.coship.sdp.sce.dp.news.service.impl.DpNewsServiceImpl#deleteDpNews(com.coship.sdp.sce.dp.news.entity.DpNews)} .
     */
    @Test
    public void testDeleteDpNews()
    {
        DpNews dpNews = new DpNews();
        dpNews.setId("3");
        try
        {
            this.dpNewsService.deleteDpNews(dpNews);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.coship.sdp.sce.dp.news.service.impl.DpNewsServiceImpl#findDpNews(java.lang.Long)} .
     */
    @Test
    public void testFindDpNews()
    {

        DpNews dn = this.dpNewsService.findDpNews("1");
        Assert.assertEquals("new1", dn.getNewsTitle());

    }

    /**
     * Test method for {@link com.coship.sdp.sce.dp.news.service.impl.DpNewsServiceImpl#findByHQL(java.lang.String)} .
     * 
     * @throws Exception
     */
    @Test
    public void testFindByHQL() throws Exception
    {
        String hql = "from DpNews where 1=1";

        List<DpNews> list = this.dpNewsService.findByHQL(hql);
        Assert.assertNotNull(list);
        Assert.assertEquals(5, list.size());

    }

    /**
     * 根据字段查询是否唯一,不唯一返回FALSE，否则返回TRUE，这里测试返回TRUE的结果
     * @throws Exception
     */
    @Test
    public void testUniqueneByPropertyNameTrue() throws Exception
    {
        // 标题
        String propertyName = "newsTitle";
        // 值
        String value = "test";
        boolean uniqueFlag = this.dpNewsService.isUniqueneByPropertyName(
                propertyName, value);
        // 如果存在该数据，则返回FALSE；
        Assert.assertTrue(uniqueFlag);

    }

    /**
     * 根据字段查询是否唯一,不唯一返回FALSE，否则返回TRUE； 这里测试返回false的结果
     * @throws Exception
     */
    @Test
    public void testUniqueneByPropertyNameFalse() throws Exception
    {
        // 标题
        String propertyName = "newsTitle";
        // 值
        String value = "newTest1";
        boolean uniqueFlag = this.dpNewsService.isUniqueneByPropertyName(
                propertyName, value);
        // 如果存在该数据，则返回FALSE；
        Assert.assertFalse(uniqueFlag);

    }

    /**
     * 分页查询
     * @param firstResult
     * 
     * @throws Exception
     */
    @Test
    public void testfindDpNewsByLimit()
    {
        int firstResult = 0;
        int maxResults = 10;
        List<DpNews> list = dpNewsService.findDpNewsByLimit(firstResult,
                maxResults);
        Assert.assertNotNull(list);
        Assert.assertEquals(5, list.size());

    }

    /**
     * 按条件查询
     */
    @Test
    public void testListDpNewsPageOfDpNewsDpNews() throws Exception
    {
        DpType dpType = new DpType();
        DpNews dn = new DpNews();

        Page<DpNews> page = new Page<DpNews>();
        page.setPageSize(10);
        page.setCurrentPage(1);

        dn.setDpType(dpType);
        page = this.dpNewsService.listDpNews(page, dn);
        Assert.assertNotNull(page);

        dn.setNewsTitle("test");
        page = this.dpNewsService.listDpNews(page, dn);
        Assert.assertNotNull(page);

        dn.setNewsSource("coship");
        page = this.dpNewsService.listDpNews(page, dn);
        Assert.assertNotNull(page);

        dn.setBeginNewsCtime("2011-12-01");
        page = this.dpNewsService.listDpNews(page, dn);
        Assert.assertNotNull(page);

        dn.setBeginNewsCtime(null);
        dn.setEndNewsCtime("2012-01-20");
        page = this.dpNewsService.listDpNews(page, dn);
        Assert.assertNotNull(page);

        dn.setBeginNewsCtime("2011-12-01");
        dn.setEndNewsCtime("2012-01-20");
        page = this.dpNewsService.listDpNews(page, dn);
        Assert.assertNotNull(page);
    }

    /**
     * 删除多条数据测试.
     */
    @Test
    public void testdeleteDpNewsList()
    {
        String[] idArray =
        { "4", "5" };
        try
        {
            dpNewsService.deleteDpNewsList(idArray);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }
}
