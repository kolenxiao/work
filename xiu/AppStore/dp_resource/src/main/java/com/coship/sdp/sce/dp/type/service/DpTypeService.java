/*
 * 文件名称：DpTypeService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-4
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.type.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.utils.Page;

/**
 * 分类管理服务类
 * @author JiangJinfeng/906974
 * @version [版本号, 2012-9-4]
 * @since [产品/模块版本]
 */
public interface DpTypeService extends Serializable
{
    /**
     * 新增类型.
     * @param entity 类型实体
     * @throws Exception [参数说明]
     */
    void saveType(DpType entity) throws Exception;

    /**
     * 更新类型.
     * @param entity 类型实体对象
     * @throws Exception [参数说明]
     */
    void updateType(DpType entity) throws Exception;

    /**
     * <删除类别>.
     * @param entity 类型实体对象
     * @throws Exception [参数说明]
     */
    void deleteType(DpType entity) throws Exception;

    /**
     * 根据hql查询类别列表.
     * @param page 分页对象
     * @param hql hql语句
     * @param values 参数对象
     * @throws Exception [参数说明]
     * @return Page<DpType> [返回类型说明]
     */
    Page<DpType> listType(Page<DpType> page, String hql, Object[] values)
            throws Exception;

    /**
     * 根据id删除类别.
     * @param id 类型id
     * @throws Exception [参数说明]
     */
    void deleteType(String id) throws Exception;

    /**
     * 根据id查询类型.
     * @param id 类型id
     * @return
     * @throws Exception [参数说明]
     * @return DpType [返回类型说明]
     */
    DpType findType(String id) throws Exception;

    /**
     * 根据hql查询类型列表.
     * @param hql hql语句
     * @throws Exception [参数说明]
     * @return List<DpType> [返回类型说明]
     */
    List<DpType> findByHQL(String hql) throws Exception;

    /**
     * 根据父类型编码和自己的类型编码判断类型是否唯一
     * @param parentTypeCode类型编码
     * @param typeCode 自己的类型编码
     * @return true 表示唯一,false为不唯一
     * @throws Exception [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    boolean isUniqueType(String parentTypeCode, String typeCode)
            throws Exception;

    /**
     * 根据ID组删除分类.
     * @param ids id数组
     * @throws Exception [参数说明]
     */
    void deleteTypeByIds(String ids) throws Exception;

    /**
     * 根据父分类编码获取子分类列表
     * @param parentTypeCode 父分类编码
     * @return 分类列表
     * @throws Exception
     * @return List<DpType> 分类列表对象
     * @exception throws [违例类型] [违例说明]
     */
    List<DpType> findByParentTypeCode(String parentTypeCode) throws Exception;


    /**
     * 根据父分类编码获取子分类列表
     * @param parentTypeCode 父分类编码
     * @return 分类列表
     * @throws Exception
     * @return List<DpType> 分类列表对象
     * @exception throws [违例类型] [违例说明]
     */
    List<DpType> findVisibleTypeByParentTypeCode(String parentTypeCode) throws Exception;

    /**
     * 分页条件查询
     * @param page 分页数据
     * @param dpTypeQueryInfo查询条件数据
     * @return [参数说明]
     * @return Page<DpType> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    Page<DpType> serchDpType(Page<DpType> page, DpType dpTypeQueryInfo);

    /**
     * 获取分类下的应用列表
     * @param page
     * @param typeId
     * @param orderBy [参数说明]
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    void getTypeAppInfoList(Page<DpAppInfo> page, String typeId, String orderBy)
            throws Exception;

    /**
     * 获取分类下的排行
     * @param page
     * @param typeId 分类id
     * @param rankType 排行标识
     * @return [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    void getRankTypeAppInfoList(Page<DpAppInfo> page, String typeId,
            String rankType) throws Exception;
    /**
     * 根据分类编码查询分类信息
     * @param typeCode
     * @return [参数说明]
     * @return DpType [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public DpType findByTypeCode(String typeCode) throws Exception;

    public List<DpType> getGameAndAppTypeList() throws Exception;

    /**
     * 获取推荐分类列表
     *
     * @return List<DpType> 返回推荐分类列表
     * @exception throws [违例类型] [违例说明]
     */
    public List<DpType> getRecommentTypeList() throws Exception;
    
    /**
     * <p>位置：开发者后台-》应用管理-》应用推荐</p>
     * 查询应用下的推荐分类，不包含根分类,该方法会改变Page对象的引用 
     * @param page 查询分页对象
     * @param queryobject 查询条件对象
     * @param isInclude true则查询t_app_recommend表中唯一记录,false则查询在t_dp_type 且 不在t_app_recommend中的记录
     * @throws Exception
     */
    public void searchRecomentDpTypeList(Page<DpType> page,DpType queryobject,boolean isInclude)throws Exception;
    
    /**
     * 根据分类编码，查询显示的分类
     * @param parentTypeCodes
     * @return
     */
    public List<DpType> findVisibleTypeByParentCodeArr(String...parentTypeCodes)throws Exception;
    
    /**
     * 获取应用分类下的应用数
     * @param typeId
     * @return
     * @throws Exception
     */
    public int getAppTotalByTypeId(String typeId) throws Exception;
    
    /**
     * 根据id得到分类
     * @param typeId
     * @return
     */
    public DpType getDpTypeById(String typeId);
}
