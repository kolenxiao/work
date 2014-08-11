/*
 * 文件名称：DpTypeServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd.
 *        Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-8-31
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.type.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.exception.ServiceException;
import com.coship.sdp.sce.dp.ap.dao.DpAppInfoDao;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.common.AppStatusConstants;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.type.dao.DpTypeDao;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 应用类型实现类.
 *
 * @author FuJian/906126
 * @version [版本号, 2011-8-31]
 * @since [产品/模块版本]
 */
@Repository("dpTypeService")
@Transactional
public class DpTypeServiceImpl implements DpTypeService
{

    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 应用信息表的column
     */
    private static final String APP_INFO_COLUMN = "appinfo.C_ID,appinfo.C_APP_DESC,"
            + "appinfo.C_APP_FILE_PACKAGE,appinfo.C_VERSION_CODE,"
            + "appinfo.C_APP_NAME,appinfo.C_APP_NAME_PINYIN,"
            + "appinfo.C_APP_STATUS,appinfo.C_CREATE_TIME,appinfo.C_AVERAGE_SCORE,appinfo.C_DEVELOPER,"
            + "appinfo.C_DP_STAFF_ID,appinfo.C_LANGUAGE,appinfo.C_PRICE,appinfo.C_SYSTEM,"
            + "appinfo.C_UPDATE_TIME,appinfo.C_VERSION,appinfo.C_TYPE_ID,appinfo.C_USER_ID";

    /**
     * 不排序
     */
    public static final String APP_ORDER_ALL = "0";

    /**
     * 最热排序(下载的次数)
     */
    public static final String APP_ORDER_HOT = "1";

    /**
     * 最新排序(更新的时间)
     */
    public static final String APP_ORDER_NEW = "2";

    /**
     * 好评排序(平均评分)
     */
    public static final String APP_ORDER_GOOD = "3";

    /**
     * 免费排序
     */
    public static final String APP_ORDER_FREE = "4";

    /**
     * 收费排序
     */
    public static final String APP_ORDER_PAY = "5";

    /**
     * 应用类型实体.
     */
    @Autowired
    private DpTypeDao dpTypeDao;

    /**
     * 应用信息数据访问接口
     */
    @Autowired
    private DpAppInfoDao dpAppInfoDao;

    /**
     * 按类型查询下载信息.
     */
    private static final String FIND_DOWNLOAD_COUNT_HQL = " from DpDownloadInfo dd where dd.dpType = ";

    /**
     * 按类型查询资讯信息.
     */
    private static final String FIND_NEWS_COUNT_HQL = " from DpNews dn where dn.dpType = ";

    /**
     * 按类型查询应用信息
     */
    private static final String FIND_APP_COUNT_HQL = "from DpAppInfo di where di.dpType = ";

    /**
     * 新增类型.
     * @param entity 类型实体
     * @throws Exception [参数说明]
     */
    @Override
    public void saveType(DpType entity) throws Exception
    {
    	entity.setSortNum(10000);
        this.dpTypeDao.save(entity);
    }

    /**
     * 更新类型.
     * @param entity 类型实体对象
     * @throws Exception [参数说明]
     */
    @Override
    public void updateType(DpType entity) throws Exception
    {
        this.dpTypeDao.update(entity);
    }

    /**
     * <删除类别>.
     * @param entity 类型实体对象
     * @throws Exception [参数说明]
     */
    @Override
    public void deleteType(DpType entity) throws Exception
    {
        this.dpTypeDao.delete(entity);
    }

    /**
     * 根据hql查询类别列表.
     * @param page 分页对象
     * @param hql hql语句
     * @param values 参数对象
     * @throws Exception [参数说明]
     * @return Page<DpType> [返回类型说明]
     */
    @Override
    public Page<DpType> listType(Page<DpType> page, String hql, Object[] values)
            throws Exception
    {
        this.dpTypeDao.queryPage(page, hql, values);
        List<DpType> list = page.getResultList();
        for (DpType temp : list)
        {
            String tempPCode = temp.getParentTypeCode();
            if (tempPCode != null)
            {
                DpType p = findByTypeCode(tempPCode);
                if (p != null)
                {
                    temp.setParentTypeName(p.getTypeName());
                }
            }
        }
        return page;
    }

    public Page<DpType> serchDpType(Page<DpType> page, DpType dpTypeQueryInfo)
    {
        String hql = "from DpType dt where 1=1";

        // 分类名称
        if (StringUtils.isNotEmpty(dpTypeQueryInfo.getTypeName()))
        {
            hql += " and dt.typeName like'%"
                    + SqlUtil.escapeSQLLike(dpTypeQueryInfo.getTypeName()
                            .trim()) + "%' escape'/'";
        }
        if (StringUtils.isNotEmpty(dpTypeQueryInfo.getParentTypeCode()))
        {
            hql += " and dt.parentTypeCode ='"
                    + dpTypeQueryInfo.getParentTypeCode() + "'";
        }

        if (dpTypeQueryInfo.getVisibleFlag() != 0)
        {
        	hql += " and dt.visibleFlag ="
                + dpTypeQueryInfo.getVisibleFlag();
        }

        hql += " and dt.typeCode !='" + DefaultTypeCodeConstants.ROOT_TYPE_CODE
                + "'";

        hql += " and dt.parentTypeCode !='"
                + DefaultTypeCodeConstants.ROOT_TYPE_CODE + "'";
        hql += " order by dt.parentTypeCode desc, dt.sortNum, dt.updateDate desc";

        
        page = dpTypeDao.queryPage(page, hql);

        List<DpType> list = page.getResultList();
        for (DpType temp : list)
        {
            String tempPCode = temp.getParentTypeCode();
            if (tempPCode != null)
            {
                DpType p = findByTypeCode(tempPCode);
                if (p != null)
                {
                    temp.setParentTypeName(p.getTypeName());
                }
            }
        }
        return page;
    }

    /**
     * 根据id删除类别.
     * @param id 类型id
     * @throws Exception [参数说明]
     */
    @Override
    public void deleteType(String id) throws Exception
    {
        DpType newType = dpTypeDao.get(id);
        this.dpTypeDao.delete(newType);
    }

    /**
     * 根据id查询类型.
     * @param id 类型id
     * @return
     * @throws Exception [参数说明]
     * @return DpType [返回类型说明]
     */
    @Override
    public DpType findType(String id) throws Exception
    {
        DpType temp = dpTypeDao.get(id);
        if (temp != null)
        {
            DpType pTemp = findByTypeCode(temp.getParentTypeCode());
            if (pTemp != null)
            {
                temp.setParentTypeName(pTemp.getTypeName());
            }
        }
        return temp;
    }

    /**
     * 根据hql查询类型列表.
     * @param hql hql语句
     * @throws Exception [参数说明]
     * @return List<DpType> [返回类型说明]
     */
    @Override
    public List<DpType> findByHQL(String hql) throws Exception
    {
        return this.dpTypeDao.query(hql);
    }

    /**
     * 根据父类型编码和自己的类型编码判断类型是否唯一
     * @param parentTypeCode类型编码
     * @param typeCode 自己的类型编码
     * @return true 表示唯一,false为不唯一
     * @throws Exception [参数说明]
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public boolean isUniqueType(String parentTypeCode, String typeCode)
            throws Exception
    {
        String hql = "from DpType dp where dp.parentTypeCode ='"
                + parentTypeCode + "'  and dp.typeCode ='" + typeCode + "'";
        List<DpType> typeList = this.dpTypeDao.query(hql);
        boolean isUniquene;
        if (typeList != null && !typeList.isEmpty())
        {
            isUniquene = false;
        }
        else
        {
            isUniquene = true;
        }
        return isUniquene;
    }

    /**
     * 根据ID组删除分类.如果分类被关联则不允许删除
     * @param ids id数组
     * @throws Exception [参数说明]
     */
    @Override
    @Transactional(rollbackFor =
    { ServiceException.class, Exception.class })
    public void deleteTypeByIds(String ids) throws Exception
    {
        // 传进来的id是以','拼接成的一串id组[76, 77, 84]
        String[] idsStr = ids.split(",");

        DpType entity = null;

        for (String id : idsStr)
        {
            entity = findType(id.trim());

            // 查询下载中是否绑定了类型
            String downloadHQL = FIND_DOWNLOAD_COUNT_HQL + "'" + id + "'";

            // 查询资讯中是否绑定了类型
            String newsHQL = FIND_NEWS_COUNT_HQL + "'" + id + "'";

            // 查询应用中是否绑定了类型
            String appHQL = FIND_APP_COUNT_HQL + "'" + id + "'";

            int countDownload = dpTypeDao.countHqlResult(downloadHQL);

            int countNews = dpTypeDao.countHqlResult(newsHQL);

            int countApps = dpAppInfoDao.countHqlResult(appHQL);
            if (countDownload != 0 || countNews != 0 || countApps != 0)
            {
                Debug.logWarning("dpTypeID[" + id + "] contain ["
                        + (countNews + countDownload)
                        + "]dpDownload or dpNews,don't remove DpType ", this
                        .getClass().getName());
                throw new ServiceException(
                        Constants.WARNING_DPTYPE_BIND_NEWSORDOWNLOAD);
            }
            this.dpTypeDao.delete(entity);
        }
    }

    /**
     * 根据父分类编码获取子分类列表
     * @param parentTypeCode 父分类编码
     * @return 分类列表
     * @throws Exception
     * @return List<DpType> 分类列表对象
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public List<DpType> findByParentTypeCode(String parentTypeCode)
            throws Exception
    {
        String hql = "from DpType where parentTypeCode ='" + parentTypeCode
                + "'";
        return this.dpTypeDao.query(hql);
    }

    /**
     * 根据父分类编码获取可见的子分类列表
     * @param parentTypeCode 父分类编码
     * @return 分类列表
     * @throws Exception
     * @return List<DpType> 分类列表对象
     * @exception throws [违例类型] [违例说明]
     */
	@Override
	public List<DpType> findVisibleTypeByParentTypeCode(String parentTypeCode)
			throws Exception {
		 String hql = "from DpType where parentTypeCode ='" + parentTypeCode
         + "' and visibleFlag=1 order by sortNum, updateDate desc";
		 return this.dpTypeDao.query(hql);
	}

    /**
     * 根据分类编码查询分类信息
     * @param typeCode
     * @return [参数说明]
     * @return DpType [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public DpType findByTypeCode(String typeCode)
    {
        String hql = "from DpType where typeCode ='" + typeCode + "'";

        List<DpType> list = this.dpTypeDao.query(hql);
        if (list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void getTypeAppInfoList(Page<DpAppInfo> page, String typeId,
            String orderByFlag) throws Exception
    {

        String sql = getOrderByNewSql(typeId);
        if (StringUtils.equals(orderByFlag, APP_ORDER_HOT))
        {
            sql = getOrderByHotSql(typeId);
        }
        else if (StringUtils.equals(orderByFlag, APP_ORDER_GOOD))
        {
            sql = getOrderByGoodSql(typeId);
        }
        dpAppInfoDao.queryForPageBySql(page, sql);
    }

    @Override
    public void getRankTypeAppInfoList(Page<DpAppInfo> page, String typeId,
            String rankType) throws Exception
    {
        String sql = getOrderByNewSql(typeId);
        if (StringUtils.equals(rankType, APP_ORDER_HOT))
        {
            sql = getOrderByHotSql(typeId);
        }
        else if (StringUtils.equals(rankType, APP_ORDER_GOOD))
        {
            sql = getOrderByGoodSql(typeId);
        }
        else if (StringUtils.equals(rankType, APP_ORDER_FREE))
        {
            sql = getOrderByFreeSql(typeId);
        }
        else if (StringUtils.equals(rankType, APP_ORDER_PAY))
        {
            sql = getOrderByPaySql(typeId);
        }
        dpAppInfoDao.queryForPageBySql(page, sql);
    }

    /**
     * 收费排序sql构建
     * @param typeId
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private String getOrderByPaySql(String typeId)
    {
        String sql = "select dp.* from T_DP_APP_INFO dp where dp.C_APP_STATUS='1004'";
        if (StringUtils.isNotEmpty(typeId))
        {
            sql += " and dp.C_TYPE_ID='" + typeId + "'";
        }
        sql += " and dp.C_PRICE>0 order by dp.C_PRICE desc";
        return sql;
    }

    /**
     * 免费排序sql构建
     * @param typeId
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private String getOrderByFreeSql(String typeId)
    {
        String sql = "select dp.* from T_DP_APP_INFO dp where dp.C_APP_STATUS='1004'";
        if (StringUtils.isNotEmpty(typeId))
        {
            sql += " and dp.C_TYPE_ID='" + typeId + "'";
        }
        sql += " and dp.C_PRICE =0 order by dp.C_UPDATE_TIME desc";
        return sql;
    }

    /**
     * 根据最新排序sql构建
     * @param typeId
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private String getOrderByNewSql(String typeId)
    {
        String sql = "select dp.* from T_DP_APP_INFO dp where dp.C_APP_STATUS='1004'";
        if (StringUtils.isNotEmpty(typeId))
        {
            sql += " and dp.C_TYPE_ID='" + typeId + "'";
        }
        sql += " order by dp.C_UPDATE_TIME desc";
        return sql;
    }

    /**
     * 根据下载量排序sql构建
     * @param subjectId
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private String getOrderByHotSql(String typeId)
    {
        String hotSql = "select "
                + APP_INFO_COLUMN
                + " ,downloadCount from  (select appinfoTemp.* , count(myapp.C_ID) downloadCount"
                + " from T_DP_APP_INFO appinfoTemp left join T_MY_APP myapp on appinfoTemp.C_ID = myapp.C_APP_ID"
                + " group by (appinfoTemp.C_ID) having appinfoTemp.C_APP_STATUS='1004') appinfo";
        if (StringUtils.isNotEmpty(typeId))
        {
            hotSql += " where appinfo.C_TYPE_ID='" + typeId + "'";
        }
        hotSql += " order by appinfo.downloadCount desc";
        return hotSql;
    }

    /**
     * 根据平均评分排序sql构建
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private String getOrderByGoodSql(String typeId)
    {
        String goodSql = "select "
                + APP_INFO_COLUMN
                + " from (select appinfoTemp.*, avg(appComment.C_SCORE) commentAvgScore"
                + " from T_DP_APP_INFO appinfoTemp left join T_APP_COMMENT_INFO appComment "
                + " on appinfoTemp.C_ID = appComment.C_APP_ID group by (appinfoTemp.C_ID) having "
                + " appinfoTemp.C_APP_STATUS='1004') appinfo";

        if (StringUtils.isNotEmpty(typeId))
        {
            goodSql += " where appinfo.C_TYPE_ID='" + typeId + "'";
        }
        goodSql += " order by appinfo.commentAvgScore desc";
        return goodSql;
    }

	@Override
	public List<DpType> getGameAndAppTypeList() throws Exception {
		String hql = "from DpType where parentTypeCode in('"+DefaultTypeCodeConstants.APP_TYPE_CODE
        	+"', '"+DefaultTypeCodeConstants.GAME_TYPE_CODE+"')  and visibleFlag=1 order by parentTypeCode desc, sortNum, updateDate desc ";

		List<DpType> list =this.dpTypeDao.query(hql);
		if(null == list){
			list = new ArrayList<DpType>();
		}
		return list;
	}

	@Override
	public List<DpType> getRecommentTypeList() throws Exception {
		String hql = "from DpType type where type.id "
			+ "in(select distinct ar.dpType.id from AppTypeRecommend ar) and type.visibleFlag = 1";

		List<DpType> recommentTypeList = dpTypeDao.query(hql);
		if (CollectionUtils.isEmpty(recommentTypeList))
		{
			recommentTypeList = new ArrayList<DpType>();
		}
		return recommentTypeList;
	}

	@Override
	public void searchRecomentDpTypeList(Page<DpType> page, DpType queryobject,
			boolean isInclude) throws Exception {

		String hql;// 查询语句

		if (isInclude) {// t_app_recommend中唯一记录
			hql = " from DpType recType where recType  in (select ar.dpType from AppTypeRecommend ar) ";
		} else {
			hql = " from DpType recType where recType not in(select ar.dpType from AppTypeRecommend ar)";
		}
		
		if(null == queryobject){
			queryobject = new DpType();//防止空指针
		}
		
		if (StringUtils.isNotEmpty(queryobject.getTypeName())) {// 分类名称
			hql += " and recType.typeName like'%"
					+ SqlUtil.escapeSQLLike(queryobject.getTypeName().trim())
					+ "%' escape'/'";
		}

		if (StringUtils.isNotEmpty(queryobject.getParentTypeCode())) {// 应用类型
			hql += " and recType.parentTypeCode ='"
					+ queryobject.getParentTypeCode() + "'";
		}

		if (queryobject.getVisibleFlag() != 0) {// 隐藏还是显示
			hql += " and recType.visibleFlag =" + queryobject.getVisibleFlag();
		}

		hql +=  "and recType.parentTypeCode in('"
				+DefaultTypeCodeConstants.GAME_TYPE_CODE+"','"+DefaultTypeCodeConstants.APP_TYPE_CODE+"')";
		
		dpTypeDao.queryPage(page, hql, new Object[0]);

	}
	
	
	/**
	 * 
	 * @param parentTypeCode
	 * @return
	 * @throws Exception
	 */
	public List<DpType> findVisibleTypeByParentCodeArr(String...parentTypeCodes)
			throws Exception {
		
		String hql = "from DpType where visibleFlag=1 ";
		
		if( null != parentTypeCodes && parentTypeCodes.length > 0)
		{	
			hql += "and  typeCode in ('";
			int index = 0 ;
			for(String code : parentTypeCodes){
				
				if(index != parentTypeCodes.length-1){
					hql += code+"','";
				}else{
					hql += code+"'";
				}
				
				index++; 
			}
			
			hql += ')';
		}
		
		return this.dpTypeDao.query(hql);
	}
	
	public int getAppTotalByTypeId(String typeId) throws Exception {
		String hql = "from DpAppInfo ar where ar.dpType.id=? and ar.appStatus=?";
		int count = dpTypeDao.countHqlResult(hql,
				new Object[] { typeId,AppStatusConstants.APP_STATUS_ONLINE});
		return count;
	}
	
	public DpType getDpTypeById(String typeId){
		return dpTypeDao.get(typeId);
	}
}
