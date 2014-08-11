package com.coship.sdp.sce.dp.implicit.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.appstore.entity.AppStoreClient;
import com.coship.sdp.sce.dp.implicit.dao.ImplicitAppDao;
import com.coship.sdp.sce.dp.implicit.entity.ImplicitApp;
import com.coship.sdp.sce.dp.implicit.service.ImplicitAppService;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Page;

@Service("implicitAppService")
@Transactional
public class ImplicitAppServiceImpl implements ImplicitAppService {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


    @Autowired
    private ImplicitAppDao implicitAppDao;


	@Override
	public Page<ImplicitApp> getImplicitAppList(Page<ImplicitApp> page,
			ImplicitApp implicitApp) {

		Map<String, Object> map = new HashMap<String, Object>();

		String hql = "from ImplicitApp ipa where 1 = 1 ";

		if (StringUtils.isNotEmpty(implicitApp.getAppName()))
		{
			hql += " and ipa.appName like'%"
                + SqlUtil.escapeSQLLike(implicitApp.getAppName().trim())
                + "%' escape'/'";
		}

		if (StringUtils.isNotEmpty(implicitApp.getTeminalNum()))
		{
			 hql += " and ipa.teminalNum = :teminalNum or ipa.teminalNum='0'";
             map.put("teminalNum", implicitApp.getTeminalNum());
		}

		if (StringUtils.isNotEmpty(implicitApp.getImplicitType()))
		{
			hql += " and ipa.implicitType = :implicitType";
            map.put("implicitType", implicitApp.getImplicitType());
		}

		if (StringUtils.isNotEmpty(implicitApp.getStatus()))
		{
			hql += " and ipa.status = :status";
            map.put("status", implicitApp.getStatus());
		}

		page = this.implicitAppDao.queryPage(page, hql, map);

		return page;
	}

    @Override
    public boolean isApkPackageExist(String packageName,String teminalNum)
    {
    	boolean ret = false;
    	String hql = "from ImplicitApp ipa where ipa.appFilePackage=? and ipa.teminalNum=?";

    	List<ImplicitApp> retList = implicitAppDao.query(hql,
                new Object[]
                { packageName,teminalNum});
        if (retList != null && retList.size() > 0)
        {
            ret = true;
        }
        return ret;
    }


    @Override
    public boolean isApkExistByPackageAndImpType(String packageName, String impType, String id)
    {
    	boolean ret = false;
    	String hql = "from ImplicitApp ipa where ipa.appFilePackage=? "
    		+ " and ipa.implicitType=? and ipa.id not in(?)";

    	List<AppStoreClient> retList = implicitAppDao.query(hql,
                new Object[]
                { packageName, impType, id});
        if (retList != null && retList.size() > 0)
        {
            ret = true;
        }
        return ret;
    }

	@Override
	public ImplicitApp findImplicitAppById(String id) {
		return implicitAppDao.get(id);
	}


	@Override
	public void saveImplicitApp(ImplicitApp entity) throws Exception {
		implicitAppDao.save(entity);
	}

	@Override
	public void updateImplicitApp(ImplicitApp entity) throws Exception {
		implicitAppDao.update(entity);
	}

	@Override
	public void onOrOffImplicitApp(ImplicitApp entity) throws Exception {
		String hql = "update ImplicitApp ipa set ipa.status = ?,ipa.deployTime=?"
			+ " where ipa.id=?";
		implicitAppDao.executeUpdate(hql,
				new Object[]{entity.getStatus(), new Date(), entity.getId()});
	}

	@Override
	public void deleteImplicitApp(ImplicitApp entity) throws Exception {
		implicitAppDao.delete(entity);
	}

	@Override
	public void offImplicitApp(String packageName,String teminalNum) throws Exception {
		String hql = "from ImplicitApp ipa where ipa.appFilePackage = ? and ipa.status = '0' and ipa.teminalNum=?";
		List<ImplicitApp> list = implicitAppDao.query(hql,
				new Object[]{packageName,teminalNum});

		if (CollectionUtils.isNotEmpty(list))
		{
			ImplicitApp impApp = list.get(0);
			impApp.setStatus("1");
			implicitAppDao.update(impApp);
		}
	}

	@Override
	public List<ImplicitApp> getImplicitAppListByPackageName(String packageName, String teminalNum) throws Exception {
		String hql = "from ImplicitApp ipa where ipa.appFilePackage = ? and ipa.teminalNum=?";
		return implicitAppDao.query(hql, new Object[]{packageName,teminalNum});
	}

	@Override
	public List<ImplicitApp> getImplicitAppListByTeminalNumAndOsVersion(
			String teminalNum, int osVersion) throws Exception {
		String hql = "from ImplicitApp ipa where ipa.status='0' "
				+ " and ((CAST(ipa.system as int) <= ?  "
				+ " or ipa.implicitType='3') and (ipa.teminalNum = ? or ipa.teminalNum='0'))";
		return implicitAppDao.query(hql,
				new Object[]{osVersion, teminalNum});
	}

	@Override
	public boolean isApkExistByPackageNameAndSavePath(ImplicitApp impApp)
			throws Exception {
		String hql = "from ImplicitApp ipa where ipa.appFilePackage=? "
			+ " and ipa.apkFileSavePath=? and ipa.id not in(?)";

		List<ImplicitApp> impList = implicitAppDao.query(hql,
				new Object[]{impApp.getAppFilePackage()
					, impApp.getApkFileSavePath(), impApp.getId()});

		if (CollectionUtils.isEmpty(impList))
		{
			// 表明应用不存在
			return false;
		}

		return true;
	}

	@Override
	public List<ImplicitApp> getAllImplicitAppList() {
		String hql = "from ImplicitApp ipa where ipa.status=?";
		return implicitAppDao.query(hql,
				new Object[]{"0"});
	}
}
