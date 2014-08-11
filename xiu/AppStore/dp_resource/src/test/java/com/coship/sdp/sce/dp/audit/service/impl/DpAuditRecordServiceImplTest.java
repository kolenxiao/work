/*
 * 文件名称：DpAuditRecordServiceImplTest.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.audit.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.coship.sdp.sce.dp.audit.dao.DpAuditRecordDao;
import com.coship.sdp.sce.dp.audit.dao.impl.DpAuditRecordDaoImpl;
import com.coship.sdp.sce.dp.audit.entity.DpAuditRecord;
import com.coship.sdp.sce.dp.audit.service.DpAuditRecordService;

/**
 * <功能描述>.
 *
 * @author FuJian/906126
 * @version [版本号, 2011-9-7]
 * @since [产品/模块版本]
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
public class DpAuditRecordServiceImplTest extends
    AbstractTransactionalJUnit4SpringContextTests {
    /**
     * 服务对象.
     */
	@Autowired
	private DpAuditRecordService dpAuditRecordService;
	/**
	 * 审核对象.
	 */
	private DpAuditRecordServiceImpl dp =  new DpAuditRecordServiceImpl();

	/**
	 * Test method for.
	 *
	 * @throws Exception 异常
	 */
	@Test
	public void testSaveAuditRecord() throws Exception {
		DpAuditRecord auditRecord = new DpAuditRecord();
		auditRecord.setAuditDate(new Date());
		auditRecord.setAuditFlag("2");
		auditRecord.setAuditOption("one ok");
		auditRecord.setAuditRecordId("1");
		auditRecord.setAuditResult("1");

		this.dpAuditRecordService.saveAuditRecord(auditRecord);

	}




	/**
	 * Test method for
	 * .
	 *
	 * @throws Exception 异常
	 */
	@Test
	public void testListAuditRecord() throws Exception {
		this.dpAuditRecordService.listAuditRecord("1", "2");

	}

	/**
     * Test method for
     * .
     *
     * @throws Exception 异常
     */
	@Test
	public void testCurrentAuditRecord() throws Exception {
		String recordId = "1";
		String auditFlag = "1002";

		this.dpAuditRecordService
				.currentAuditRecord(recordId, auditFlag);

	}
//	/**
//	 * set方法.
//	 */
//	@Test
//	public void testSetDpAuditRecordDao() {
//		DpAuditRecordDao dpAuditRecordDao = new DpAuditRecordDaoImpl();
//		dp.setDpAuditRecordDao(dpAuditRecordDao);
//	}
//	/**
//	 * get方法.
//	 */
//	@Test
//	public void testGetDpAuditRecordDao() {
//		dp.getDpAuditRecordDao();
//	}


	/**
	 * Test method for
	 * .
	 *
	 * @throws Exception 异常
	 */
	@Test
	public void testDeleteAuditRecordDpAuditRecord() throws Exception {

		List<DpAuditRecord> auditRecordList = this.dpAuditRecordService
				.currentAuditRecord("172", "2");

		for (DpAuditRecord dpRecord : auditRecordList) {

			this.dpAuditRecordService.deleteAuditRecord(dpRecord);
		}

	}
	/**
     * Test method for
     * .
     *
     * @throws Exception 异常
     */
	@Test
	public void testDeleteAuditRecordByHQL() throws Exception {

		this.dpAuditRecordService.deleteAuditRecord("1", "2");

	}

}
