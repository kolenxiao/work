/*
 * 文件名称：AppInfoDetailDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-11-27
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.ap.dao.AppInfoDetailDao;
import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.utils.Page;

/**
 * <功能描述>
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-11-27]
 * @since [产品/模块版本]
 */
@Repository("appInfoDetailDao")
public class AppInfoDetailDaoImpl extends GenericDaoImpl<AppInfoDetail, String>
        implements AppInfoDetailDao
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    @Override
    public Page<AppInfoDetail> queryPageBySql(Page<AppInfoDetail> page,
            String sql, Object[] values, Type[] types)
    {
        SQLQuery queryList = this.getSession().createSQLQuery(sql);

        if (values != null && types != null)
        {
            queryList.setParameters(values, types);
        }
        queryList.setFirstResult(page.getBeginCount());
        queryList.setMaxResults(page.getPageSize());

        List resultList = queryList.list();
        List<AppInfoDetail> retList = new ArrayList<AppInfoDetail>();

        for (Object props : resultList)
        {
            AppInfoDetail appInfoDetail = toAppInfoDetail(props);
            retList.add(appInfoDetail);
        }
        page.setResultList(retList);

        SQLQuery queryCount = this.getSession().createSQLQuery(
                "select count(1) from (" + sql + ") tempTable");
        if (values != null && types != null)
        {
            queryCount.setParameters(values, types);
        }

        BigInteger count = (BigInteger) queryCount.uniqueResult();
        page.setTotalCount(count.intValue());
        return page;
    }

    @Override
    public List<AppInfoDetail> queryListBySql(String sql, Object[] values,
    		Type[] types)
    {
    	SQLQuery queryList = this.getSession().createSQLQuery(sql);

        if (values != null && types != null)
        {
            queryList.setParameters(values, types);
        }

        List resultList = queryList.list();
        List<AppInfoDetail> retList = new ArrayList<AppInfoDetail>();

        for (Object props : resultList)
        {
            AppInfoDetail appInfoDetail = toAppInfoDetail(props);
            retList.add(appInfoDetail);
        }

        return retList;
    }
    
	/**
	 * <功能描述>
	 * 
	 * @param props
	 * @return [参数说明]
	 * @return AppInfoDetail [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	private AppInfoDetail toAppInfoSubDetail(Object props) {
		AppInfoDetail appInfoDetail = new AppInfoDetail();
		Object[] propArray = (Object[]) props;
		appInfoDetail.setId((String) propArray[0]);
		appInfoDetail.setAppName((String) propArray[1]);
		appInfoDetail.setAppNamePinyin((String) propArray[2]);
		appInfoDetail.setAppDesc((String) propArray[3]);
		appInfoDetail.setAppFilePackage((String) propArray[4]);
		appInfoDetail.setUpdateTime((Date) propArray[5]);
		appInfoDetail.setAppTime((Date) propArray[6]);
		appInfoDetail.setPrice((Double) propArray[7]);
		appInfoDetail.setVersion((String) propArray[8]);
		appInfoDetail.setVersionCode(String.valueOf(propArray[9]));
		appInfoDetail.setLanguage((String) propArray[10]);
		appInfoDetail.setSystem((String) propArray[11]);
		appInfoDetail.setDeveloper((String) propArray[12]);
		appInfoDetail.setAppStatus((String) propArray[13]);
		appInfoDetail.setTypeId((String) propArray[14]);
		appInfoDetail.setTypeName((String) propArray[15]);
		appInfoDetail.setAppCertId((String) propArray[16]);

		if (propArray[17] != null) {
			BigInteger tempDownloadCount = (BigInteger) propArray[17];
			appInfoDetail.setDownloadCount(tempDownloadCount.longValue());
		} else {
			appInfoDetail.setDownloadCount(0);
		}
		
		if (propArray[18] != null) {
			BigInteger tempCommentCount = (BigInteger) propArray[18];
			appInfoDetail.setCommentCount(tempCommentCount.longValue());
		} else {
			appInfoDetail.setCommentCount(0);
		}
		
		if (propArray[19] != null) {
			BigDecimal tempCommentCount = (BigDecimal) propArray[19];
			appInfoDetail.setAvgScore(tempCommentCount.doubleValue());
		} else {
			appInfoDetail.setAvgScore(new Double(0));
		}		
		appInfoDetail.setSubjectPoster((String) propArray[20]);
		appInfoDetail.setOpMode((Integer) propArray[21]);
		appInfoDetail.setHandDownCount((Integer) propArray[23]);
		if (propArray[24] != null) {
			appInfoDetail.setHandAvgScore((Double)propArray[24]);
		} else {
			appInfoDetail.setHandAvgScore(new Double(0));
		}
		appInfoDetail.setHandScoreCount((Integer) propArray[25]);
		appInfoDetail.setSubjectId((String) ((Object[]) props)[26]);

		return appInfoDetail;
	}

	/**
	 * <功能描述>
	 * 
	 * @param props
	 * @return [参数说明]
	 * @return AppInfoDetail [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	private AppInfoDetail toAppInfoDetail(Object props) {
		AppInfoDetail appInfoDetail = new AppInfoDetail();
		Object[] propArray = (Object[]) props;
		appInfoDetail.setId((String) propArray[0]);
		appInfoDetail.setAppName((String) propArray[1]);
		appInfoDetail.setAppNamePinyin((String) propArray[2]);
		appInfoDetail.setAppDesc((String) propArray[3]);
		appInfoDetail.setAppFilePackage((String) propArray[4]);
		appInfoDetail.setUpdateTime((Date) propArray[5]);
		appInfoDetail.setAppTime((Date) propArray[6]);
		appInfoDetail.setPrice((Double) propArray[7]);
		appInfoDetail.setVersion((String) propArray[8]);
		appInfoDetail.setVersionCode(String.valueOf(propArray[9]));
		appInfoDetail.setLanguage((String) propArray[10]);
		appInfoDetail.setSystem((String) propArray[11]);
		appInfoDetail.setDeveloper((String) propArray[12]);
		appInfoDetail.setAppStatus((String) propArray[13]);
		appInfoDetail.setTypeId((String) propArray[14]);
		appInfoDetail.setTypeName((String) propArray[15]);
		appInfoDetail.setAppCertId((String) propArray[16]);

		if (propArray[17] != null) {
			BigInteger tempDownloadCount = (BigInteger) propArray[17];
			appInfoDetail.setDownloadCount(tempDownloadCount.longValue());
		} else {
			appInfoDetail.setDownloadCount(0);
		}

		if (propArray[18] != null) {
			BigInteger tempCommentCount = (BigInteger) propArray[18];
			appInfoDetail.setCommentCount(tempCommentCount.longValue());
		} else {
			appInfoDetail.setCommentCount(0);
		}
		if (propArray[19] != null) {
			BigDecimal tempCommentCount = (BigDecimal) propArray[19];
			appInfoDetail.setAvgScore(tempCommentCount.doubleValue());
		} else {
			appInfoDetail.setAvgScore(new Double(0));
		}

		appInfoDetail.setSubjectPoster((String) propArray[20]);
		appInfoDetail.setOpMode((Integer) propArray[21]);
		appInfoDetail.setHandDownCount((Integer) propArray[23]);
		if (propArray[24] != null) {
			appInfoDetail.setHandAvgScore((Double)propArray[24]);
		} else {
			appInfoDetail.setHandAvgScore(new Double(0));
		}
		appInfoDetail.setHandScoreCount((Integer) propArray[25]);

		return appInfoDetail;
	}

    @Override
    public List<AppInfoDetail> queryListBySql(String sql)
    {
        SQLQuery queryList = this.getSession().createSQLQuery(sql);

        List resultList = queryList.list();
        List<AppInfoDetail> retList = new ArrayList<AppInfoDetail>();

        for (Object props : resultList)
        {
            AppInfoDetail appInfoDetail = toAppInfoDetail(props);
            retList.add(appInfoDetail);
        }
        return retList;
    }


    @Override
    public List<AppInfoDetail> querySubjectListBySql(String sql)
    {
        SQLQuery queryList = this.getSession().createSQLQuery(sql);

        List resultList = queryList.list();
        List<AppInfoDetail> retList = new ArrayList<AppInfoDetail>();

        for (Object props : resultList)
        {
        	AppInfoDetail subDetail = toAppInfoSubDetail(props);
            retList.add(subDetail);
        }
        return retList;
    }




    @Override
    public AppInfoDetail findUniqueByIdSql(String sql)
    {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        Object props = query.uniqueResult();
        if (props == null)
        {
            return null;
        }
        return toAppInfoDetail(props);
    }

}
