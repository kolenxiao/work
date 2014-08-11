/*
 * 文件名称：DpTypeServiceImplTest.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-8-31
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.type.service.impl;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.utils.Page;

/**
 * <功能描述>.
 * 
 * @author FuJian/906126
 * @version [版本号, 2011-8-31]
 * @since [产品/模块版本]
 */
@ContextConfiguration(locations =
{ "classpath:applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = false)
public class DpTypeServiceImplTest extends
        AbstractTransactionalJUnit4SpringContextTests
{

    /**
     * 分类实现类.
     */
    @Autowired
    private DpTypeService dpTypeService;

    /**
     * 初始化
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception
    {

    }

    /**
     * 清除数据.
     * @throws Exception
     */
    @AfterClass
    public static void cleanData() throws Exception
    {

    }

    /**
     * <功能描述>测试分页类型
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    @Test
    public void testPageListType() throws Exception
    {
        Page<DpType> page = new Page<DpType>();
        String hql = "from DpType dt where dt.parentTypeCode=?";
        Object[] paramMap = new Object[]
        { DefaultTypeCodeConstants.ROOT_TYPE_CODE };
        page = dpTypeService.listType(page, hql, paramMap);
        Assert.assertEquals(page.getTotalCount(), 4);
    }

    /**
     * 测试基本的增删改查
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Test
    public void testSaveDelFindType() throws Exception
    {
        DpType dpType = new DpType();

        dpType.setCreateDate(new Date());
        dpType.setTypeCode("xxxx");
        dpType.setParentTypeCode("pxxxx");
        dpType.setTypeName("x的范德萨");
        dpTypeService.saveType(dpType);

        String id = dpType.getId();
        Assert.assertNotNull(id);

        dpTypeService.deleteType(id);

        DpType dpType2 = dpTypeService.findType(id);
        Assert.assertNull(dpType2);
    }

    /**
     * <功能描述> 根据父类别编码和自己的编码判断是否唯一
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    @Test
    public void testisUniqueType() throws Exception
    {
        boolean rst = dpTypeService.isUniqueType(
                DefaultTypeCodeConstants.ROOT_TYPE_CODE,
                DefaultTypeCodeConstants.APP_TYPE_CODE);
        Assert.assertFalse(rst);
    }

    /**
     * <功能描述>
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Test
    public void testFindByParentTypeCode() throws Exception
    {
        List<DpType> list = dpTypeService
                .findByParentTypeCode(DefaultTypeCodeConstants.APP_TYPE_CODE);
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 20);
    }
}
