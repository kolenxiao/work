/*
 * 文件名称：AppCommentServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.comment.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFileEntity;
import com.coship.sdp.sce.dp.comment.dao.AppCommentDao;
import com.coship.sdp.sce.dp.comment.entity.AppCommentInfo;
import com.coship.sdp.sce.dp.comment.entity.AppScoreSummary;
import com.coship.sdp.sce.dp.comment.entity.AppScoreSummaryEntity;
import com.coship.sdp.sce.dp.comment.service.AppCommentService;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 应用的评论信息服务实现类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
@Service("appCommentService")
@Transactional
public class AppCommentServiceImpl implements AppCommentService
{
    /**
     * dao对象.
     */
    @Autowired
    private AppCommentDao appCommentDao;

    /**
     * 根据用户名、应用id查询应用对象.
     */
    private static final String FIND_UNIQUE_BY_UID_APPID = "from AppCommentInfo aci where aci.commentUserId =? and aci.dpAppInfo=?";
    private static final String FIND_UNIQUE_BY_UID_APPPACKAGENAME = "from AppCommentInfo aci where aci.commentUserId =? and aci.appPackageName=?";

    /**
     * 获得应用的平均评分.
     */
    private static final String SVG_SCORE_BY_APPID = "select avg(aci.score) from AppCommentInfo aci where aci.appPackageName=?";

    /**
     * 获得应用的几星评分人数.
     */
    private static final String COUNT_STAR_BY_APPID = "select aci.score,count(aci.score) from AppCommentInfo aci where aci.appPackageName=? group by aci.score";

    /**
     * 根据应用id删除评论信息.
     */
    private static final String DELETE_APP_COMMENT_INFO = "delete AppCommentInfo aci where aci.appPackageName = ?";

    /**
     * 根据应用的appid获取评论分页
     */
    private static final String QUERY_PAGE_BYAPPCODE_SQL = "select  appComment.*  from  T_APP_COMMENT_INFO appComment ,T_DP_APP_INFO appInfo"
            + " where  appInfo.C_APP_FILE_PACKAGE=? and appInfo.C_ID = appComment.C_APP_ID order by appComment.C_CREATE_DATE desc";

    /**
     * 根据应用的appid获取平均评分
     */
    private static final String AVG_SCORE_BYAPPCODE_SQL = "select avg(appComment.C_SCORE) from T_APP_COMMENT_INFO appComment ,T_DP_APP_INFO appInfo"
            + " where  appInfo.C_APP_FILE_PACKAGE=? and appInfo.C_ID = appComment.C_APP_ID";

    /**
     * 根据应用的appid获取评分人数
     */
    private static final String COUNT_STAR_BYAPPCODE_SQL = "select appComment.C_SCORE,count(appComment.C_SCORE) from T_APP_COMMENT_INFO appComment ,T_DP_APP_INFO appInfo"
            + " where appInfo.C_APP_FILE_PACKAGE=? and appInfo.C_ID = appComment.C_APP_ID group by appComment.C_SCORE";

    /**
     * 根据应用的包名获取评分人数
     */
    private static final String COUNT_STAR_BY_APPPACKAGENAME_SQL = "select appComment.C_SCORE,count(appComment.C_SCORE) from T_APP_COMMENT_INFO appComment ,T_DP_APP_INFO appInfo"
            + " where appInfo.C_APP_FILE_PACKAGE=? and appInfo.C_ID = appComment.C_APP_ID group by appComment.C_SCORE";

    @Override
    public void saveAppCommentInfo(AppCommentInfo appCommentInfo)
            throws Exception
    {
        appCommentDao.save(appCommentInfo);

    }

    @Override
    public void updateAppCommentInfo(AppCommentInfo appCommentInfo)
            throws Exception
    {
        appCommentDao.update(appCommentInfo);

    }

    @Override
    public Page<AppCommentInfo> listAppCommentInfo(Page<AppCommentInfo> page,
            String hql, Object[] values) throws Exception
    {
        return appCommentDao.queryPage(page, hql, values);

    }

    @Override
    public Page<AppCommentInfo> listAppCommentInfoByAppInfo(Page<AppCommentInfo> page,
            DpAppInfo app) throws Exception
    {
        String hql = "from AppCommentInfo aci where  aci.dpAppInfo.id=? order by  aci.createDate";
        return appCommentDao.queryPage(page, hql, new Object[]{ app.getId() });
    }

    @Override
    public AppCommentInfo findUnique(String userId, DpAppInfo dpAppInfo)
            throws Exception
    {

        AppCommentInfo appCommentInfo = appCommentDao.findUnique(
                FIND_UNIQUE_BY_UID_APPID, new Object[]
                { userId, dpAppInfo });

        return appCommentInfo;
    }
    
	@Override
	public AppCommentInfo findUnique(String userId, String appPackageName)
			throws Exception {
		AppCommentInfo appCommentInfo = appCommentDao.findUnique(
				FIND_UNIQUE_BY_UID_APPPACKAGENAME,
				new Object[] { userId, appPackageName });
		return appCommentInfo;
	}

    @Override
    public AppScoreSummary getAppScoreSummary(DpAppInfo dpAppInfo)
            throws Exception
    {
    	 AppScoreSummary appScoreSummary = new AppScoreSummary();
         appScoreSummary.setAppId(dpAppInfo.getId());
    	if(StringUtils.isNotBlank(dpAppInfo.getAppFilePackage())){
    		Object[] object = new Object[]{ dpAppInfo.getAppFilePackage() };
            Object avgScoreObj = appCommentDao.createQuery(SVG_SCORE_BY_APPID, object)
                    .uniqueResult();
            Iterator<?> it = appCommentDao.createQuery(COUNT_STAR_BY_APPID, object)
                    .list().iterator();

            // while begin
            while (it.hasNext())
            {
                Object[] scoreObjects = (Object[]) it.next();
                int scoreName = (Integer) scoreObjects[0];
                Long scoreCount = (Long) scoreObjects[1];

                // switch begin
                switch (scoreName)
                {
                    case 1:
                        // 1星人数
                        appScoreSummary.setStar1(scoreCount);
                        break;
                    case 2:
                        // 2星人数
                        appScoreSummary.setStar2(scoreCount);
                        break;
                    case 3:
                        // 3星人数
                        appScoreSummary.setStar3(scoreCount);
                        break;
                    case 4:
                        // 4星人数
                        appScoreSummary.setStar4(scoreCount);
                        break;
                    case 5:
                        // 5星人数
                        appScoreSummary.setStar5(scoreCount);
                        break;

                    default:
                        break;
                }
                // switch end
            }
            
            //真实的评分人数
            long scoreCount = appScoreSummary.getStar1() + appScoreSummary.getStar2()
                    + appScoreSummary.getStar3() + appScoreSummary.getStar4()
                    + appScoreSummary.getStar5();

    		// 真实的平均分
    		double avgScore = 0;
    		if (avgScoreObj != null) {
    			avgScore = AppCommentHelper.processAvgScore((Double) avgScoreObj);
    		}

    		//人加操作后的数据
    		double afterHandScoreSum = avgScore * scoreCount + dpAppInfo.getHandAvgScore() * dpAppInfo.getHandScoreCount();
    		long afterHandScoreCount = scoreCount + dpAppInfo.getHandScoreCount();
    		double afterHandAvgScore = avgScore;
    		if(0 != afterHandScoreCount){
    			afterHandAvgScore = afterHandScoreSum / afterHandScoreCount;
    			afterHandAvgScore = AppCommentHelper.processAvgScore(afterHandAvgScore);
    		}
    		
    		// 计算平均星值
    		double avgStar = AppCommentHelper.processAvgStar(afterHandAvgScore);
    		
    		//返回数据
    		appScoreSummary.setAvgScore(avgScore);
            appScoreSummary.setScoreCount(scoreCount);
            appScoreSummary.setAfterHandAvgScore(afterHandAvgScore);
            appScoreSummary.setAfterHandScoreCount(afterHandScoreCount);
            appScoreSummary.setAvgStar(avgStar);
    	}
        return appScoreSummary;
    }

	@Override
	public void deleteAppCommentInfo(DpAppInfo dpAppInfo) throws Exception {
		if(StringUtils.isNotBlank(dpAppInfo.getAppFilePackage())){
			appCommentDao.executeUpdate(DELETE_APP_COMMENT_INFO,
					new Object[] { dpAppInfo.getAppFilePackage() });
		}
	}

    @Override
    public void listAppCommentInfoByPacName(Page<AppCommentInfo> page,
            String pacName) throws Exception
    {
        appCommentDao.queryPageBySql(page, QUERY_PAGE_BYAPPCODE_SQL,
                new Object[]
                { pacName }, new Type[]
                { Hibernate.STRING });
    }

    @Override
    public AppScoreSummary getAppScoreSummaryByPacName(String pacName)
            throws Exception
    {
        AppScoreSummary appScoreSummary = new AppScoreSummary();

        Session session = appCommentDao.creatSqlSession();
        SQLQuery sqlQuery = session.createSQLQuery(AVG_SCORE_BYAPPCODE_SQL);

        sqlQuery.setParameters(new Object[]
        { pacName }, new Type[]
        { Hibernate.STRING });
        Object avgScoreObj = sqlQuery.uniqueResult();

        if (avgScoreObj != null)
        {
			//计算平均分
			double avgScore = AppCommentHelper.processAvgScore((Double) avgScoreObj);
			appScoreSummary.setAvgScore(avgScore);
			//计算平均星值
			double avgStar = AppCommentHelper.processAvgStar(avgScore);
			appScoreSummary.setAvgStar(avgStar);
        }

        SQLQuery sqlQueryIter = session
                .createSQLQuery(COUNT_STAR_BYAPPCODE_SQL);

        sqlQueryIter.setParameters(new Object[]
        { pacName }, new Type[]
        { Hibernate.STRING });

        Iterator<?> it = sqlQueryIter.list().iterator();

        // while begin
        while (it.hasNext())
        {
            Object[] scoreObjects = (Object[]) it.next();
            int scoreName = (Integer) scoreObjects[0];
            BigInteger tempScoreCount = (BigInteger) scoreObjects[1];
            Long scoreCount = tempScoreCount.longValue();

            // switch begin
            switch (scoreName)
            {
                case 1:
                    // 1星人数
                    appScoreSummary.setStar1(scoreCount);
                    break;
                case 2:
                    // 2星人数
                    appScoreSummary.setStar2(scoreCount);
                    break;
                case 3:
                    // 3星人数
                    appScoreSummary.setStar3(scoreCount);
                    break;
                case 4:
                    // 4星人数
                    appScoreSummary.setStar4(scoreCount);
                    break;
                case 5:
                    // 5星人数
                    appScoreSummary.setStar5(scoreCount);
                    break;

                default:
                    break;
            }
            // switch end

        }
        return appScoreSummary;
    }

    
    
    /**
     * 通过应用包名计算应用真实的平均评分和星值
     */
    public AppScoreSummaryEntity getAppScoreSummaryByPackageName(String packageName) throws Exception
    {
        AppScoreSummaryEntity appScoreSummaryEntity = new AppScoreSummaryEntity();

        Session session = appCommentDao.creatSqlSession();
        Connection conn = session.connection();

        ResultSet rs =null;
    	CallableStatement call = null;
    	try {
			call = conn.prepareCall("{Call COUNT_STAR_BY_PACKAGE(?)}");
			call.setString(1, packageName);
			rs = call.executeQuery();
			
			double scoreSum = 0;
			long num = 0;
			while (rs.next())
			{
				int scoreFlag = rs.getInt(1);//星值
				Long scoreCount = Long.valueOf(rs.getInt(2));//评该星级的次数
				scoreSum += scoreFlag * scoreCount;
				num += scoreCount;
				
	            switch (scoreFlag)
	            {
	                case 1:
	                    // 1星人数
	                    appScoreSummaryEntity.setStar1(scoreCount);
	                    break;
	                case 2:
	                    // 2星人数
	                    appScoreSummaryEntity.setStar2(scoreCount);
	                    break;
	                case 3:
	                    // 3星人数
	                    appScoreSummaryEntity.setStar3(scoreCount);
	                    break;
	                case 4:
	                    // 4星人数
	                    appScoreSummaryEntity.setStar4(scoreCount);
	                    break;
	                case 5:
	                    // 5星人数
	                    appScoreSummaryEntity.setStar5(scoreCount);
	                    break;

	                default:
	                    break;
	            }
	            // switch end
			}
			//评分和
			appScoreSummaryEntity.setScoreSum(scoreSum);
			
			// 计算评分人数
			appScoreSummaryEntity.setScoreCount(appScoreSummaryEntity.getStar1()
					+ appScoreSummaryEntity.getStar2()
					+ appScoreSummaryEntity.getStar3()
					+ appScoreSummaryEntity.getStar4()
					+ appScoreSummaryEntity.getStar5());
			
			if(num != 0){
				//计算平均分
				double avgScore = AppCommentHelper.processAvgScore(scoreSum/num);
				appScoreSummaryEntity.setAvgScore(avgScore);
				
				//计算平均星值
				double avgStar = AppCommentHelper.processAvgStar(avgScore);
				appScoreSummaryEntity.setAvgStar(avgStar);
			}
		} catch (SQLException e) {
			Debug.logError(e.getMessage());
		} finally {
			try {
				if (rs != null)
				{
					rs.close();
				}
				if (call != null)
				{
					call.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        return appScoreSummaryEntity;
    }

}
