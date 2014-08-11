/*
 * 文件名称：DpStaffService.java.
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：luhuanwen/905323
 * 创建时间：2011-9-21
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.ap.entity.DpStaff;
import com.coship.sdp.sce.dp.audit.entity.DpAuditRecord;
import com.coship.sdp.utils.Page;

/**
 * ap信息服务层接口. <功能描述>
 * @author Huangliufei/905735
 * @version [版本号, 2012-2-4]
 * @since [产品/模块版本]
 */
public interface DpStaffService extends Serializable
{
    /**
     * 根据AP的ID删除AP信息.
     * @param dpStaff 对象
     * @throws Exception 异常
     */
    void deleteDpStaff(DpStaff dpStaff) throws Exception;

    /**
     * <根据hql语句查询>.
     * @param hql 参数
     * @throws Exception [参数说明]
     * @return List<DpStaff> ap列表
     */
    List<DpStaff> findByHQL(String hql) throws Exception;

    /**
     * <功能描述>.
     * @param page 分页对象
     * @param dpStaff ap实体
     * @param flag 查询标识：表示是否是查询未审核的
     * @throws Exception 异常类
     * @return Page<DpStaff> 分页列表
     */
    Page<DpStaff> searchDpStaff(Page<DpStaff> page, DpStaff dpStaff, int flag)
            throws Exception;

    /**
     * 更新ap信息.
     * @param dpStaff 实体对象
     * @throws Exception 异常处理类
     */
    void updateDpStaff(DpStaff dpStaff) throws Exception;

    /**
     * 获取特定ID的AP.
     * @param id apid
     * @return DpStaff对象
     * @throws Exception 异常实体
     */
    DpStaff findDpStaff(String id) throws Exception;

    /**
     * 更新AP的审核状态.
     * @param s 审核状态s
     * @param id AP的ID
     * @throws Exception 异常实体
     */
    void updateStaffStatus(String s, String id) throws Exception;

    /**
     * 根据用户名获得用户信息.
     * @param userName 用户名
     * @return <DpStaff> 用户对象
     * @throws Exception 异常类
     */
    DpStaff findDpStaffByName(String userName) throws Exception;

    /**
     * 新增AP信息.
     * @param dpStaff ap实体
     * @throws Exception 异常
     */
    void saveDpStaff(DpStaff dpStaff) throws Exception;

    /**
     * 根据用户名和密码查询ap信息.
     * @param userName 用户名
     * @param passWord 密码
     * @throws Exception [参数说明]
     * @return DpStaff 实体对象
     */
    DpStaff findDpStaff(String userName, String passWord) throws Exception;

    /**
     * 根据ID组删除分类.
     * @param ids id组
     * @return String 用户名字符串
     * @throws Exception 异常类
     */
    String deleteDpStaffByIds(String ids) throws Exception;

    /**
     * 审核开发者信息
     * @param dpStaff 开发者实体对象
     * @param dpAuditRecord 审核记录实体对象
     * @throws Exception 异常类
     * @return void
     * @exception throws [违例类型] [违例说明]
     */
    void auditDpStaffAndSaveRecore(DpStaff dpStaff, DpAuditRecord dpAuditRecord)
            throws Exception;

    /**
     * 重置开发者密码
     * @param dpStaff 开发者信息实体对象
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void resetDpStaffPwd(DpStaff dpStaff) throws Exception;

    /**
     * 根据注册邮箱查询.
     * @param email 注册邮箱
     * @return List<DpStaff> 列表
     * @throws Exception 异常处理
     */
    List<DpStaff> findDpStaffByEmail(String email) throws Exception;
    

    /**
     * 根据用户名和邮箱查询记录
     * @param email 注册邮箱
     * @param userName 用户账户
     * @return List<DpStaff> 列表
     * @throws Exception 异常处理
     */
    List<DpStaff> findDpStaffByEmailAndUserName(String email, String userName) throws Exception;
    
    /**
     * 根据营业执照查询记录
     * @param bizLicense 营业执照
     * @return List<DpStaff>列表
     * @throws Exception
     */
    public List<DpStaff> findDpStaffByBizLicense(String bizLicense) throws Exception;


}
