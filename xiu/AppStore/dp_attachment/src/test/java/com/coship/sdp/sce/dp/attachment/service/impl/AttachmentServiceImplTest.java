/*
 * 文 件 名：AttachmentServiceImplTest.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：Sunwengang/903820
 * 修改时间：2011-8-31
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.attachment.service.impl;


import java.io.File;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.coship.sdp.sce.dp.attachment.service.AttachmentService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * @author  Sunwengang/903820
 * @version  [版本号, 2011-8-31]
 * @since  [产品/模块版本]
 */
@ContextConfiguration(locations =
{ "classpath:applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = false)
public class AttachmentServiceImplTest   extends  AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private AttachmentService attachmentService;

    
    
    @Test
    public void testAddAttachmentFile()
    {
        File file = new File("F:\\wsdl\\weatherwebservice.wsdl");
        attachmentService.addAttachmentFile(null, "weatherwebservice.wsdl", "wsdl", file, null);
    
    }
    
    
    
    /**
     * @return 返回 attachmentService
     */
    public AttachmentService getAttachmentService()
    {
        return attachmentService;
    }
    /**
     * @param 对attachmentService进行赋值
     */
    public void setAttachmentService(AttachmentService attachmentService)
    {
        this.attachmentService = attachmentService;
    }
    
    

}
