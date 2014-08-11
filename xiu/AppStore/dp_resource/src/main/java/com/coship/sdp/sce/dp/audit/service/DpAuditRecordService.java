/*
 * 文件名称：DpAuditRecordService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-9-5
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.audit.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.audit.entity.DpAuditRecord;
import com.coship.sdp.utils.Page;

/**
 * <功能描述>.
 * @author  FuJian/906126
 * @version  [版本号, 2011-9-5]
 * @since  [产品/模块版本]
 */
public interface DpAuditRecordService extends Serializable {
    /**
     * 保存审核信息.
     * @param entity 审核实体对象
     * @throws Exception 异常处理类
     */
    void saveAuditRecord(DpAuditRecord entity) throws Exception;
    /**
     *
     *删除审核信息.
     * @param entity 审核实体对象
     * @throws Exception [参数说明]
     */
    void deleteAuditRecord(DpAuditRecord entity) throws Exception;
    /**
     * 根据审核记录id和标志查询审核记录列表.
     * @param recordId 审核记录id
     * @param auditFlag 标志，（1、ap信息；2、ap应用）
     * @throws Exception [参数说明]
     * @return List<DpAuditRecord> 审核列表
     */
    List<DpAuditRecord> listAuditRecord(String recordId, String auditFlag)
    throws Exception;
    /**
     *根据审核记录id和审核标识删除审核记录.
     * @param recordId 审核记录id
     * @param auditFlag 标识（1、ap信息；2、ap应用）
     * @throws Exception [参数说明]
     */
    void deleteAuditRecord(String recordId, String auditFlag) throws Exception;
    /**
     * 根据审核记录id和标志查询最新的审核记录.
     * @param recordId 审核记录id
     * @param auditFlag 标识
     * @throws Exception [参数说明]
     * @return List<DpAuditRecord> [返回类型说明]
     */
    List<DpAuditRecord> currentAuditRecord(String recordId, String auditFlag)
    throws Exception;
    /**
     * 根据开发者Id查询所有的评审记录
     * @param recordId 审核记录id
     * @param auditFlag 标识
     * @throws Exception [参数说明]
     * @return Page<DpAuditRecord> [返回审核记录分页列表]
     */
    Page<DpAuditRecord> findAuditRecordListByRecordId(Page<DpAuditRecord> page, String recordId)
    throws Exception;
}
