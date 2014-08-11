/*
 * 文件名称：DpNewsServiceImplTest.java

 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Luhuanwen/905323
 * 创建时间：Sep 1, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.service.impl;

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
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.entity.DpStaff;
import com.coship.sdp.sce.dp.ap.service.DpStaffService;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.test.utils.DbUnitUtils;
import com.coship.sdp.test.utils.SpringTestCase;
import com.coship.sdp.utils.Page;

/**
 * <功能描述>.
 * @author Luhuanwen/905323
 * @version [版本号, Sep 1, 2011]
 * @since [产品/模块版本]
 */
@ContextConfiguration(locations =
{ "classpath:applicationContext-test.xml" })
@Transactional
public class DpStaffServicImplTest extends SpringTestCase
{

    private static final Logger logger = Logger
            .getLogger(DpStaffServicImplTest.class);

    /**
     * HQL 查询语句.
     */
    private static final String HQL = "from DpStaff where 1=1";

    /**
     * 服务层接口.
     */
    @Autowired
    private DpStaffService dpStaffService;

    /**
     * 数据源句柄.
     */
    private static DataSource dataSourceHolder = null;

    /**
     * 初始化数据.
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
     * @throws Exception
     */
    @AfterClass
    public static void cleanData() throws Exception
    {
        DbUnitUtils.removeData(dataSourceHolder, "/data/data.xml");
    }

    /**
     * 根据hql语句查询.
     * @throws Exception
     */
    @Test
    public void testFindByHQL() throws Exception
    {

        List<DpStaff> list = dpStaffService.findByHQL(HQL);
        Assert.assertNotNull(list);
    }

    /**
     * 根据id查询.
     * @throws Exception
     */
    @Test
    public void testFindDpStaff() throws Exception
    {

        DpStaff ds = dpStaffService.findDpStaff("1");
        Assert.assertEquals("admin", ds.getUserName());
        Assert.assertEquals("1003", ds.getStaffStatus());
    }

    /**
     * 根据用户名称查询
     * @throws Exception
     */
    @Test
    public void testFindDpStaffByName() throws Exception
    {

        DpStaff dpStaff = dpStaffService.findDpStaffByName("admin");
        Assert.assertNotNull(dpStaff);
    }

    /**
     * 根据用户名称和密码查询,存在的账户
     * @throws Exception
     */
    @Test
    public void testFindDpStaffByNameAndPwd() throws Exception
    {
        String userName = "admin";
        DpStaff dpStaff = dpStaffService.findDpStaff("admin",
                "0b9530c150355c9577aae21764f319c3");
        Assert.assertNotNull(dpStaff);
        Assert.assertEquals(dpStaff.getUserName(), userName);
    }

    /**
     * 根据用户名称和密码查询,不存在的账户
     * @throws Exception
     */
    @Test
    public void testFindDpStaffByNameAndPwdNull() throws Exception
    {

        DpStaff dpStaff = dpStaffService.findDpStaff("test", "123456");
        Assert.assertNull(dpStaff);
    }

    @Test
    public void testSearchDpStaff() throws Exception
    {

        Page<DpStaff> page = new Page<DpStaff>();
        DpStaff dp = new DpStaff();

        // 查询待审核状态的
        page = this.dpStaffService.searchDpStaff(page, dp, 1);
        Assert.assertEquals(page.getTotalCount(), 1);

        // 状态
        dp.setStaffStatus("1001");
        page = this.dpStaffService.searchDpStaff(page, dp, 0);
        Assert.assertEquals(page.getTotalCount(), 1);

        // 开始时间
        dp.setStaffStatus(null);
        dp.setBeginDpStaffCtime("2012-01-05");
        page = this.dpStaffService.searchDpStaff(page, dp, 0);
        Assert.assertEquals(page.getTotalCount(), 3);

        // 开始时间
        dp.setStaffStatus(null);
        dp.setBeginDpStaffCtime(null);
        dp.setEndDpStaffCtime("2012-01-10");
        page = this.dpStaffService.searchDpStaff(page, dp, 0);
        Assert.assertEquals(page.getTotalCount(), 3);

        // 结束时间
        dp.setBeginDpStaffCtime("2012-01-05");
        dp.setEndDpStaffCtime("2012-01-10");
        page = this.dpStaffService.searchDpStaff(page, dp, 0);
        Assert.assertEquals(page.getTotalCount(), 2);

        // 根据用户名查询
        dp.setStaffStatus(null);
        dp.setStaffStatus(null);
        dp.setBeginDpStaffCtime(null);
        dp.setUserName("admin");
        dp.setNickName("coship");
        page = this.dpStaffService.searchDpStaff(page, dp, 0);
        Assert.assertEquals(page.getTotalCount(), 1);

        // 根据用户名查询
        page = this.dpStaffService.searchDpStaff(page, dp, 0);
        Assert.assertEquals(page.getTotalCount(), 1);

    }

    /**
     * 正常流程添加保存一个用户.
     * @throws Exception
     */
    @Test
    public void testSaveDpStaffSucce() throws Exception
    {
        // DpStaff对象
        DpStaff dpStaff = new DpStaff();
        dpStaff.setAddress("深证市同洲电子股份有限公司");
        dpStaff.setBankAccountName("张三");
        dpStaff.setBankCardNum("622200440210");
        dpStaff.setBankName("发展银行");
        dpStaff.setEmail("lizhiwen@coship.com");
        dpStaff.setHeadIcon("images/photo_01.jpg");
        dpStaff.setIdentityCard("51100");
        dpStaff.setSaveBankName("发展银行");

        dpStaff.setIdentityImg("images/photo_01.jpg");
        dpStaff.setMobileNum("13456789045");
        dpStaff.setNickName("Sara");
        dpStaff.setPassWord("145677");
        dpStaff.setPostCode("518057");
        dpStaff.setRealName("李志文");
        dpStaff.setStaffStatus("1001");
        dpStaff.setUserName("张三");
        // 将用户保存到数据库中
        dpStaffService.saveDpStaff(dpStaff);
    }

    /**
     * 测试重名的
     * @throws Exception
     */
    @Test
    public void testSaveDpStaffEnique()
    {
        DpStaff dpStaff = new DpStaff();
        dpStaff.setUserName("test");
        dpStaff.setPassWord("0b9530c150355c9577aae21764f319c3");
        dpStaff.setNickName("这是个重复的dp账户");
        try
        {
            dpStaffService.saveDpStaff(dpStaff);

        }
        catch (Exception e)
        {
            Assert.assertEquals(e.getMessage(),
                    Constants.WARNING_USERNAME_ALREADY_EXIST);
        }
    }

    /**
     * 测试注册邮箱重复
     * @throws Exception
     */
    @Test
    public void testSaveDpStaffEmailEnique()
    {
        DpStaff dpStaff = new DpStaff();
        dpStaff.setUserName("test11");
        dpStaff.setPassWord("0b9530c150355c9577aae21764f319c3");
        dpStaff.setNickName("这是个重复的dp账户");
        dpStaff.setEmail("admin@coship.com");
        try
        {
            dpStaffService.saveDpStaff(dpStaff);

        }
        catch (Exception e)
        {
            Assert.assertEquals(e.getMessage(), "注册邮箱有重复");
        }
    }

    /**
     * 更新.
     * @throws Exception
     */
    @Test
    public void testUpdateDpStaff() throws Exception
    {
        DpStaff dpStaff = new DpStaff();
        dpStaff.setId("3");
        dpStaff.setNickName("lisi");
        dpStaff.setUserName("testUpdate");
        dpStaff.setPassWord("0b9530c150355c9577aae21764f319c3");
        dpStaff.setBeginDpStaffTime(new Date());
        dpStaff.setUpdateDpStaffTime(new Date());
        dpStaff.setSaveBankName("back");
        dpStaffService.updateDpStaff(dpStaff);
        Assert.assertEquals("testUpdate", dpStaff.getUserName());
        Assert.assertEquals("back", dpStaff.getSaveBankName());
        logger.info(dpStaff);
    }

    /**
     * 根据id更新状态.
     * @throws Exception
     */
    @Test
    public void testUpdateStaffStatus() throws Exception
    {
        String id = "3";
        String status = "1003";
        dpStaffService.updateStaffStatus(status, id);
        DpStaff ds = dpStaffService.findDpStaff("3");
        Assert.assertEquals("1003", ds.getStaffStatus());
    }

    /**
     * 正常情况删除dp账户.
     * @throws Exception
     */
    @Test
    public void testDeleteDpStaff() throws Exception
    {

        DpStaff df = new DpStaff();
        df.setId("3");
        df.setUserName("test1");
        df.setPassWord("0b9530c150355c9577aae21764f319c3");
        dpStaffService.deleteDpStaff(df);
    }

    /**
     * 根据id组删除.
     * @throws Exception
     */
    @Test
    public void testDeleteDpStaffByIds1() throws Exception
    {

        String ids = "2";
        dpStaffService.deleteDpStaffByIds(ids);
    }

    /**
     * 根据id组删除.绑定了应用
     * @throws Exception
     */
    @Test
    public void testDeleteDpStaffByIds2()
    {
        String ids = "1";
        try
        {
            dpStaffService.deleteDpStaffByIds(ids);
        }
        catch (Exception e)
        {
            Assert.assertEquals(e.getMessage(),
                    Constants.WARNING_DPSTAFF_BIND_APPINFO);
        }
    }
}
