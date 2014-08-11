package com.coship.sdp.sce.dp.command.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.command.dao.SynCommandDao;
import com.coship.sdp.sce.dp.command.entity.SynCommand;
import com.coship.sdp.sce.dp.common.Constants;

@Repository("synCommandDao")
public class SynCommandDaoImpl extends GenericDaoImpl<SynCommand, String>
		implements SynCommandDao
{
    public static final String BASE_QUERY_APP_SQL = "select appInfo.c_id id,appInfo.c_app_name appName,"
        + " appInfo.c_app_name_pinyin appNamePinyin, appInfo.c_app_desc appDesc,"
        + " appInfo.c_app_file_package appFilePackage, appInfo.c_update_time updateTime,"
        + " appInfo.c_create_time appTime, appInfo.c_price price, appInfo.c_version version,appInfo.c_version_code versionCode,"
        + " appInfo.c_language language, appInfo.c_system system, appInfo.c_developer developer,"
        + " appInfo.c_app_status appStatus, appInfo.c_type_id typeId, appType.c_type_name typeName,"
        + " sign.c_app_cert_id appCertId, t_my_apps.downloadCount downloadCount,"
        + " t_comments.commentNum commentNum, t_comments.avgScore avgScore"
        + " from t_dp_app_info appInfo inner join t_syn_command sy on appInfo.c_id = sy.c_app_id  left join t_dp_type appType on appInfo.c_type_id = appType.c_id"
        + " left join t_app_sign sign on appInfo.C_ID = sign.c_app_id"
        + " left join  (select  count(1) downloadCount, c_app_package_name  from t_my_app group by c_app_package_name)t_my_apps"
        + " on appInfo.c_app_file_package = t_my_apps.c_app_package_name"
        + " left join (select  count(1) commentNum,avg(c_score) avgScore, c_app_package_name  "
        + " from t_app_comment_info group by c_app_package_name) t_comments"
        + " on appInfo.c_app_file_package = t_comments.c_app_package_name where sy.c_status = 1 and sy.c_param =1";
    
	private static final long serialVersionUID = -7797659736472575371L;

	public int updateToBeIssued()
	{
		int result = 0;
		String sql = "update t_syn_command set c_status = 1,c_param=0 where c_status in (3,4) and "
				+ "c_app_id = (select c_id from t_dp_app_info ap where ap.c_id = c_app_id and ap.C_APP_STATUS = '"
				+ Constants.APP_STATUS_ONLINE + "') ";
		SQLQuery queryList = this.getSession().createSQLQuery(sql);
		result = queryList.executeUpdate();
		return result;
	}
	
	public int updateToBeDelete()
	{
		int result = 0;
		String sql = "update t_syn_command set c_status = 3,c_param=0 where c_status in (1,2) and "
				+ "c_app_id = (select c_id from t_dp_app_info ap where ap.c_id = c_app_id and ap.C_APP_STATUS <> '"
				+ Constants.APP_STATUS_ONLINE + "') ";
		SQLQuery queryList = this.getSession().createSQLQuery(sql);
		result = queryList.executeUpdate();
		return result;
	}
	
	public List<String> queryIssuedCommand()
	{
		String sql = "select ap.c_id from t_dp_app_info ap left join t_syn_command sy on ap.c_id = sy.c_app_id where sy.c_app_id is null and ap.C_APP_STATUS = '"
				+ Constants.APP_STATUS_ONLINE + "'";
		SQLQuery queryList = this.getSession().createSQLQuery(sql);
		List<String> list = queryList.list();
		return list;
	}
    
	public List<String> queryUnIssuedCommand(String appType)
	{
		String sql = "select ap.c_id from t_dp_app_info ap "
				+ "          LEFT JOIN t_syn_command sy on ap.c_id = sy.c_app_id "
				+ "          LEFT JOIN t_dp_type dt on ap.C_TYPE_ID = dt.C_ID "
				+ "          where sy.c_app_id is not null "
				+ "          and ap.C_APP_STATUS <> '" + Constants.APP_STATUS_ONLINE + "' "
				+ "          and dt.C_PARENT_TYPE_CODE = '" + appType + "'";
		SQLQuery queryList = this.getSession().createSQLQuery(sql);
		List<String> list = queryList.list();
		return list;
	}
	
	
    @Override
    public List<AppInfoDetail> getSynAppInfos()
    {
        String sql = "select appDetail.* from (" + BASE_QUERY_APP_SQL+ ")appDetail ";
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
   
	public int updateWhichIssued(int synCount, String flag)
	{
		int result = 0;
		String sql = "update t_syn_command set c_param= '"+flag+"',c_last_time= now() where c_status in (1,3) limit "+synCount;
		SQLQuery queryList = this.getSession().createSQLQuery(sql);
		result = queryList.executeUpdate(); 
		return result;
	}
	
	public int updateStatusAfterSend(final int status, final String flag,final boolean isFail)
	{
		int result = 0;
		String sql = "update t_syn_command set c_status='"+status+"', c_param='0'";
		
		if (isFail)
		{
            sql += " ,c_fail_count = c_fail_count+1";
		}
		
		sql += " where c_param = '"+flag+"'";
		
		SQLQuery query = this.getSession().createSQLQuery(sql);
		result = query.executeUpdate(); 
		
		return result;
	}
    
    /**
     * <功能描述>
     * @param props
     * @return [参数说明]
     * @return AppInfoDetail [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private AppInfoDetail toAppInfoDetail(Object props)
    {
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

        if (propArray[17] != null)
        {
            BigInteger tempDownloadCount = (BigInteger) propArray[17];
            appInfoDetail.setDownloadCount(tempDownloadCount.longValue());
        }
        else
        {
            appInfoDetail.setDownloadCount(0);
        }

        if (propArray[18] != null)
        {
            BigInteger tempCommentCount = (BigInteger) propArray[18];
            appInfoDetail.setCommentCount(tempCommentCount.longValue());
        }
        else
        {
            appInfoDetail.setCommentCount(0);
        }
        if (propArray[19] != null)
        {
            BigDecimal tempCommentCount = (BigDecimal) propArray[19];
            appInfoDetail.setAvgScore(tempCommentCount.doubleValue());
        }
        else
        {
            appInfoDetail.setAvgScore(new Double(0));
        }
        return appInfoDetail;
    }

}
