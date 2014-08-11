/*
 * 文件名称：AttachmentFileServiceImplTest.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人： Huangliufei/905735
 * 创建时间：Oct 24, 2011
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.atachmentfile.service.impl;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.coship.sdp.sce.dp.atachmentfile.dao.AttachmentFileDao;
import com.coship.sdp.sce.dp.atachmentfile.dao.impl.AttachmentFileDaoImpl;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;
import com.coship.sdp.sce.dp.atachmentfile.service.AttachmentFileService;
import com.coship.sdp.test.utils.SpringTestCase;

/**
 * <功能描述>.
 * @author Huangliufei/905735
 * @version [版本号, Oct 24, 2011]
 * @since [产品/模块版本]
 */
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = false)
public class AttachmentFileServiceImplTest extends SpringTestCase {

	/**
	 * 附件操作接口.
	 */
	@Autowired
	private AttachmentFileService attachmentFileService;

	@Test
	public void testSaveAttachmentFile() throws Exception {
		AttachmentFile file = new AttachmentFile();
		file.setFileName("Test");
		file.setFileSaveName("Test");
		file.setFileDesc("Test");
		file.setCreateDate(new Date());
		file.setFileType("application/octet-stream");
		file.setFileSize((35600L));
		file.setIscanDown("0");
		this.attachmentFileService.saveAttachmentFile(file);
		file.getIscanDown();
	}

	@Test
	public void testFindAttachmentFile() throws Exception {
		this.attachmentFileService.findAttachmentFile("Test");
		this.attachmentFileService.findAttachmentFile("Test22343434");
	}

	@Test
	public void testUpdateAttachmentFile() throws Exception {

		AttachmentFile file =this.attachmentFileService.findAttachmentFile("Test");
		file.setFileName("Test3");
		file.setFileDesc("test modif");
		file.setIscanDown("0");
		this.attachmentFileService.updateAttachmentFil(file);

	}

	@Test
	public void testFindAttachmentFileById() throws Exception {
		AttachmentFile file = this.attachmentFileService.findAttachmentFile("Test3");
		this.attachmentFileService.findAttachmentFileById(file.getId());
	}

	/*@Test
	public void testSetAttachmentFileDao() {
		AttachmentFileDao attFileDao = new AttachmentFileDaoImpl();
		AttachmentFileServiceImpl attachmentFileServiceImpl = new AttachmentFileServiceImpl();
//		attachmentFileServiceImpl.setAttachmentFileDao(attFileDao);
	}*/

	@Test
	public void testDeleteAttachmentFile() throws Exception {
		AttachmentFile file = this.attachmentFileService.findAttachmentFile("Test3");

		this.attachmentFileService.deleteAttachmentFile(file);

	}

}