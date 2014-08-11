/*
 * 文件名称：AppSubjectTypeServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.subject.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.ap.service.DpAppInfoService;
import com.coship.sdp.sce.dp.subject.dao.SubjectAppinfoRelationDao;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.subject.entity.SubjectAppinfoRelation;
import com.coship.sdp.sce.dp.subject.service.SubjectAppinfoRelationService;
import com.coship.sdp.utils.Page;

/**
 * 应用专题服务实现类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Service("SubjectAppinfoRelationService")
@Transactional
public class SubjectAppinfoRelationServiceImpl implements SubjectAppinfoRelationService
{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1505755436663723461L;

    /**
     * 应用信息数据访问接口
     */
    @Autowired
    private SubjectAppinfoRelationDao subjectAppinfoRelationDao;

    /**
     * 应用管理服务接口
     */
    @Autowired
    private DpAppInfoService dpAppInfoService;

    /**
     * 根据应用专题分页查询应用列表
     * @param page 分页参数
     * @param appSubjectType 应用专题
     * @return [参数说明]应用列表
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void queryAppInfoPageBySubjectType(
            Page<SubjectAppinfoRelation> page, AppSubjectType appSubjectType)
            throws Exception
    {
        String hql = "from SubjectAppinfoRelation r where r.appSubjectType.id=:subId order by r.sort asc";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subId", appSubjectType.getId());

        page = subjectAppinfoRelationDao.queryPage(page, hql, map);
    }

    /**
     * 向主题中添加应用
     * @param appSubjectType 主题类型对象
     * @param idsStr [参数说明] 应用id
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void addAppToSubjectType(AppSubjectType appSubjectType,
            String[] idsStr) throws Exception
    {
        List<SubjectAppinfoRelation> list = getSubjectAppinfoRelationsBySubType(appSubjectType);
        SubjectAppinfoRelation firstRelation = null;
        if (CollectionUtils.isNotEmpty(list))
        {
            // 如果有值，则取出排行第一位的专题应用的实体对象
            firstRelation = list.get(0);

         // 重排序
            if (firstRelation.getSort() == null || firstRelation.getSort() == 0.0)
            {
                for (int i = 0; i < list.size(); i++)
                {
                    SubjectAppinfoRelation ur = list.get(i);
                    ur.setSort(i+1.0);
                    try
                    {
                        updateSubjectAppinfoRelationSort(ur);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        Double sort = 0.0;
        for (int k = 0; k < idsStr.length; k++)
        {
            String id = idsStr[k];
            SubjectAppinfoRelation relaction = new SubjectAppinfoRelation();
            relaction.setAppSubjectType(appSubjectType);
            DpAppInfo info = dpAppInfoService.findAppInfo(id.trim());
            relaction.setAppInfo(info);

            if (firstRelation == null)
            {
                sort = k+1.0;
            }
            else
            {
                firstRelation = getAppSubjectApp(firstRelation.getId());
                sort = getRandomSort(firstRelation);
            }
            relaction.setSort(sort);
            subjectAppinfoRelationDao.save(relaction);
        }
    }

    /**
     * 应用专题自定义序号
     * @param relation :应用专题信息
     * @return 应用排行随机序号
     */
    private Double getRandomSort(SubjectAppinfoRelation relation)
    {
        Double sort = Math.random()*relation.getSort();
        if (sort == 0)
        {
            getRandomSort(relation);
        }
        return sort;
    }

    @Override
    public void deleteAppSubjectRelationByIds(AppSubjectType appSubjectType,
            String[] idsStr) throws Exception
    {
        for (String id : idsStr)
        {
            SubjectAppinfoRelation re = getAppSubjectApp(id.trim());
            re.setAppInfo(null);
            re.setAppSubjectType(null);
            subjectAppinfoRelationDao.delete(re);
        }
    }

    /**
     * @param appSubjectType
     * @return
     */
    @Override
    public List<SubjectAppinfoRelation> getSubjectAppinfoRelationsBySubType(
            AppSubjectType appSubjectType)
    {
       String hql = "from SubjectAppinfoRelation ar where ar.appSubjectType.id=:subTypeId order by ar.sort asc";
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("subTypeId", appSubjectType.getId());
       List<SubjectAppinfoRelation> list = subjectAppinfoRelationDao.query(hql, map);
       return list;
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public SubjectAppinfoRelation getAppSubjectApp(String id)
            throws Exception
    {
        String hql = "from SubjectAppinfoRelation re where re.id=:id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        List<SubjectAppinfoRelation> list = subjectAppinfoRelationDao.query(hql, map);
        if (CollectionUtils.isEmpty(list))
        {
            return null;
        }
        return list.get(0);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public SubjectAppinfoRelation getAppSubjectAppById(String id)
            throws Exception
    {
        return subjectAppinfoRelationDao.get(id);
    }

    /**
     * @param rela
     * @throws Exception
     */
    @Override
    public void updateSubjectAppinfoRelation(SubjectAppinfoRelation rela)
            throws Exception
    {
        subjectAppinfoRelationDao.update(rela);
    }

    /**
     * @param rela
     * @throws Exception
     */
    @Override
    public void updateSubjectAppinfoRelationSort(SubjectAppinfoRelation rela)
            throws Exception
    {
        String sql = "update T_SUBJECT_APPINFO_RELATION set " +
        " C_SORT='"+rela.getSort()+"' " +
        " where c_id='" + rela.getId() + "'";
        subjectAppinfoRelationDao.updateSubjectAppinfoRelation(sql);
    }

    @Override
    public List<String> getSubjectAppPkgNameList(List<String> subjectTypeIdList) {
        if (subjectTypeIdList == null) {
            return null;
        }

        return subjectAppinfoRelationDao.getSubjectAppPkgNameList(subjectTypeIdList);
    }
}
