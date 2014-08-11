/*
 * 文件名称：DpAuditRecordServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,
 *  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-9-5
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.audit.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.audit.dao.DpAuditRecordDao;
import com.coship.sdp.sce.dp.audit.entity.DpAuditRecord;
import com.coship.sdp.sce.dp.audit.service.DpAuditRecordService;
import com.coship.sdp.utils.Page;

/**
 * 应用审核记录 .
 * @author FuJian/906126
 * @version [版本号, 2011-9-5]
 * @since [产品/模块版本]
 */
@Service("dpAuditRecordService")
@Transactional
public class DpAuditRecordServiceImpl implements DpAuditRecordService
{

    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 应用审核记录DAO.
     */
    @Autowired
    private DpAuditRecordDao dpAuditRecordDao;

    /**
     * 保存审核记录信息.
     *
     * @param entity 审核实体对象
     * @throws Exception 异常处理
     */
    @Override
    public void saveAuditRecord(DpAuditRecord entity) throws Exception
    {
        this.dpAuditRecordDao.save(entity);

    }

    /**
     * 删除审核记录.
     *
     * @param entity 审核实体对象
     * @throws Exception 异常处理
     */
    @Override
    public void deleteAuditRecord(DpAuditRecord entity) throws Exception
    {
        this.dpAuditRecordDao.delete(entity);

    }

    /**
     * 根据被审核记录的ID和类型 查询最近的审核历史记录.
     *
     * @param recordId 审核id
     * @param auditFlag 审核记录标识
     * @return 审核信息列表
     * @throws Exception 异常类
     */
    @Override
    public List<DpAuditRecord> currentAuditRecord(String recordId,
            String auditFlag) throws Exception
    {
        String hql = "from DpAuditRecord T where T.auditDate=(SELECT   MAX(T1.auditDate)   FROM   DpAuditRecord T1 where T1.auditRecordId='"
                + recordId + "' and T1.auditFlag=" + auditFlag + " )";
        return this.dpAuditRecordDao.query(hql, new Object[0]);
    }

    /**
     * 根据被审核记录的ID和类型 查询 其审核历史记录.
     * @param recordId 审核id
     * @param auditFlag 审核标识（1、ap信息；2、ap应用）
     * @return List<DpAuditRecord>审核列表
     * @throws Exception 异常处理类
     */
    @Override
    public List<DpAuditRecord> listAuditRecord(String recordId, String auditFlag)
            throws Exception
    {
        String hql = "from DpAuditRecord where auditRecordId = '" + recordId
                + "' and auditFlag = " + auditFlag;
        return this.dpAuditRecordDao.query(hql, new Object[0]);
    }

    /**
     * 通过被审核记录的ID和类型 删除 其审核历史记录.
     * @param recordId 审核id
     * @param auditFlag 审核记录标识
     * @throws Exception 异常处理
     */
    @Override
    public void deleteAuditRecord(String recordId, String auditFlag)
            throws Exception
    {
        String hql = "delete DpAuditRecord where auditRecordId = '" + recordId
                + "' and auditFlag = " + auditFlag;
        this.dpAuditRecordDao.executeUpdate(hql, new Object[0]);
    }

    /**
     * @param page
     * @param recordId
     * @return
     * @throws Exception
     */
    @Override
    public Page<DpAuditRecord> findAuditRecordListByRecordId(
            Page<DpAuditRecord> page, String recordId) throws Exception
    {
        String hql = "from DpAuditRecord a where a.auditRecordId=? "
                + " or a.auditRecordId in (select p.id from DpAppInfo p where p.dpStaffId=?)"
                + " order by a.auditDate desc";

        String[] params = {recordId, recordId};
        page = this.dpAuditRecordDao.queryPage(page, hql, params);

        return page;
    }

}
