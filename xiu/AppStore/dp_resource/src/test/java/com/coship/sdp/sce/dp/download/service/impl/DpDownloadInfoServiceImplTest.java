/*
 * 文件名称：DpDownloadInfoServiceImplTest.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Huangliufei/905735
 * 创建时间：Oct 18, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.download.service.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.coship.sdp.sce.dp.download.entity.DpDownloadInfo;
import com.coship.sdp.sce.dp.download.service.DpDownloadInfoService;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.test.utils.DbUnitUtils;
import com.coship.sdp.test.utils.SpringTestCase;
import com.coship.sdp.utils.Page;

/**
 * <功能描述>.
 * @author Huangliufei/905735
 * @version [版本号, Oct 18, 2011]
 * @since [产品/模块版本]
 */
@ContextConfiguration(locations =
{ "classpath:applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = false)
public class DpDownloadInfoServiceImplTest extends SpringTestCase
{
    private static final Logger logger = Logger
            .getLogger(DpDownloadInfoServiceImplTest.class);

    /**
     * 下载信息服务接口
     */
    @Autowired
    private DpDownloadInfoService dpDownloadInfoService;

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
     * Test method for {@link com.coship.sdp.sce.dp.download.service.impl.DpDownloadInfoServiceImpl#saveDpDownloadInfo(com.coship.sdp.sce.dp.download.entity.DpDownloadInfo)} .
     * @throws Exception
     */
    @Test
    public void testSaveDpDownloadInfo() throws Exception
    {

    }

    /**
     * Test method for {@link com.coship.sdp.sce.dp.download.service.impl.DpDownloadInfoServiceImpl#updateDpDownloadInfo(com.coship.sdp.sce.dp.download.entity.DpDownloadInfo)} .
     * 
     * @throws Exception
     */
    @Test
    public void testUpdateDpDownloadInfo() throws Exception
    {
        Date date = new Date();
        List<DpDownloadInfo> list = dpDownloadInfoService.findAll();
        String downID = list.get(0).getId();
        DpDownloadInfo downloadInfo = this.dpDownloadInfoService
                .findDpDownloadInfo(downID);
        downloadInfo.setDownloadName("test1");
        downloadInfo.setDownloadDesc("下载测试");
        downloadInfo.setCtime(date);
        downloadInfo.setCtimeStart("2012-01-10 20:10:23");
        downloadInfo.setCtimeEnd("2012-01-10 20:10:23");
        this.dpDownloadInfoService.updateDpDownloadInfo(downloadInfo);

    }

    @Test
    public void testFindDpDownloadInfo() throws Exception
    {
        List<DpDownloadInfo> list = dpDownloadInfoService.findAll();
        String downID = list.get(0).getId();
        DpDownloadInfo downloadInfo = this.dpDownloadInfoService
                .findDpDownloadInfo(downID);
        logger.debug(downloadInfo);

    }

    /**
     * Test method for {@link com.coship.sdp.sce.dp.download.service.impl.DpDownloadInfoServiceImpl#ListDpDownloadInfo(com.coship.sdp.utils.Page, java.lang.String, java.lang.Object[])} .
     * 
     * @throws Exception
     */
    @Test
    public void testListDpDownloadInfoPageOfDpDownloadInfoStringObjectArray()
            throws Exception
    {
        Page<DpDownloadInfo> page = new Page<DpDownloadInfo>();
        String hql = "from DpDownloadInfo";

        page = this.dpDownloadInfoService.listDpDownloadInfo(page, hql,
                new Object[0]);
        logger.debug(page.getTotalCount());

    }

    /**
     * Test method for
     * {@link com.coship.sdp.sce.dp.download.service.impl.DpDownloadInfoServiceImpl#ListDpDownloadInfo(com.coship.sdp.utils.Page, com.coship.sdp.sce.dp.download.entity.DpDownloadInfo, com.coship.sdp.sce.dp.type.entity.DpType)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public void testListDpDownloadInfoPageOfDpDownloadInfoDpDownloadInfoDpType()
            throws Exception
    {
        Page<DpDownloadInfo> page = new Page<DpDownloadInfo>();
        DpDownloadInfo downloadInfo = new DpDownloadInfo();
        downloadInfo.setDownloadName("download");
        page = this.dpDownloadInfoService
                .listDpDownloadInfo(page, downloadInfo);

        downloadInfo.setCreateUser("admin");
        page = this.dpDownloadInfoService
                .listDpDownloadInfo(page, downloadInfo);
        Assert.assertNotNull(page);

        downloadInfo.setCtimeStart("2012-01-10");
        page = this.dpDownloadInfoService
                .listDpDownloadInfo(page, downloadInfo);
        Assert.assertNotNull(page);

        downloadInfo.setCtimeStart(null);
        downloadInfo.setCtimeEnd("2011-01-11");
        page = this.dpDownloadInfoService
                .listDpDownloadInfo(page, downloadInfo);
        Assert.assertNotNull(page);

        downloadInfo.setCtimeStart("2012-01-10");
        downloadInfo.setCtimeEnd("2011-01-11");
        page = this.dpDownloadInfoService
                .listDpDownloadInfo(page, downloadInfo);
        Assert.assertNotNull(page);

        DpType dpType = new DpType();
        dpType.setId("87");
        downloadInfo.setDpType(dpType);

        page = this.dpDownloadInfoService
                .listDpDownloadInfo(page, downloadInfo);
        logger.debug(page.getTotalCount());

    }

    /**
     * Test method for {@link com.coship.sdp.sce.dp.download.service.impl.DpDownloadInfoServiceImpl#findByHQL(java.lang.String)} .
     * 
     * @throws Exception
     */
    @Test
    public void testFindByHQL() throws Exception
    {

        List<DpDownloadInfo> list = this.dpDownloadInfoService
                .findByHQL("from DpDownloadInfo");
        logger.debug(list.size());

    }

    @Test
    public void testFindAll() throws Exception
    {

        List<DpDownloadInfo> list = this.dpDownloadInfoService.findAll();
        logger.debug(list.size());

    }

    @Test
    public void testIsUniqueneByPropertyName() throws Exception
    {
        String propertyName = "downloadName";
        String value = "Eclipse_SDK_2.3";

        boolean flag = this.dpDownloadInfoService.isUniqueneByPropertyName(
                propertyName, value);
        logger.debug(flag);

    }

    /**
     * 判断是否重名测试： 重名测试,重名的返回FALSE
     * @throws Exception
     */
    @Test
    public void testIsUniqueneByPropertyNameTrue() throws Exception
    {
        String propertyName = "downloadName";
        String value = "test1";

        boolean flag = this.dpDownloadInfoService.isUniqueneByPropertyName(
                propertyName, value);
        logger.debug(flag);
        Assert.assertEquals(false, flag);

    }

    /**
     * Test method for {@link com.coship.sdp.sce.dp.download.service.impl. DpDownloadInfoServiceImpl#deleteDpDownloadInfo (com.coship.sdp.sce.dp.download.entity.DpDownloadInfo)}
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteDpDownloadInfo() throws Exception
    {

        // List<DpDownloadInfo> list = dpDownloadInfoService.findAll();
        // DpDownloadInfo downloadInfo = new DpDownloadInfo();
        String downID = "1";
        DpDownloadInfo downloadInfo = this.dpDownloadInfoService
                .findDpDownloadInfo(downID);

        this.dpDownloadInfoService.deleteDpDownloadInfo(downloadInfo);

    }

    /**
     * Test method for 根据id组删除. {@link com.coship.sdp.sce.dp.download.service.impl. DpDownloadInfoServiceImpl#deleteDownloadInfoByIds (com.coship.sdp.sce.dp.download.entity.DpDownloadInfo)}
     * @throws Exception
     */
    @Test
    public void testDeleteDownloadInfoByIds() throws Exception
    {

        String[] idArray =
        { "2", "3" };
        this.dpDownloadInfoService.deleteDownloadInfoByIds(idArray);

    }

}
