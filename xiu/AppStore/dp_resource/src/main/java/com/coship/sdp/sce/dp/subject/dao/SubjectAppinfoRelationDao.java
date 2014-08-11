/*
 * 文件名称：AppSubjectTypeDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.subject.dao;

import java.sql.SQLException;
import java.util.List;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.subject.entity.SubjectAppinfoRelation;
import com.coship.sdp.utils.Page;

/**
 * 应用专题类型数据访问接口
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
public interface SubjectAppinfoRelationDao extends IGenericDao<SubjectAppinfoRelation, String>
{

    /**
     * 使用原生sql根据应用专题分页查询应用信息列表
     * @param page
     * @param sql
     * @return [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    List<SubjectAppinfoRelation> queryForPageBySql(Page<SubjectAppinfoRelation> page, String hql);

    /**
     * <功能描述>
     * @param sql [参数说明]
     * @return void [返回类型说明]
     * @throws SQLException
     * @exception throws [违例类型] [违例说明]
     */
    void executeNativeUpdateSql(String sql) throws SQLException;


    /**
     * <功能描述>
     * @param sql [参数说明]
     * @return void [返回类型说明]
     * @throws SQLException
     * @exception throws [违例类型] [违例说明]
     */
    void updateSubjectAppinfoRelation(String sql) throws SQLException;

    /**
     * 根据专题列表获得专题下的所有应用包名列表.
     * 
     * @param subjectTypeIdList
     * @return
     */
    List<String> getSubjectAppPkgNameList(List<String> subjectTypeIdList);
}
