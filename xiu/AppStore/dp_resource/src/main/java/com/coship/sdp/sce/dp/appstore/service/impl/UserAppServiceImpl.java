package com.coship.sdp.sce.dp.appstore.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.dao.DpAppInfoDao;
import com.coship.sdp.sce.dp.ap.service.DpAppInfoService;
import com.coship.sdp.sce.dp.appstore.dao.UserAppDao;
import com.coship.sdp.sce.dp.appstore.entity.UserApp;
import com.coship.sdp.sce.dp.appstore.service.UserAppService;

/**
 * "用户拥有的应用"实现类
 * 
 * @author 908618
 * 
 */
@Service("userAppService")
@Transactional
public class UserAppServiceImpl implements UserAppService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3480348867485984755L;

	@Autowired
	private UserAppDao userAppDao;

	@Autowired
	private DpAppInfoService dpAppInfoService;

	@Autowired
	private DpAppInfoDao dpAppInfoDao;

	@Override
	public void save(String userCode, String appPackageName) {
		// 首先查询是否存在
		UserApp userApp = this.search(userCode, appPackageName);
		if (null == userApp) {
			// 新增
			userApp = new UserApp();
			userApp.setUserCode(userCode);
			userApp.setAppPackageName(appPackageName);
			userApp.setCreateTime(new Date());
			userAppDao.save(userApp);
		}
	}

	@Override
	public void delete(String userCode, String appPackageName) {
		// 首先查询是否存在
		UserApp userApp = this.search(userCode, appPackageName);
		if (null != userApp) {
			// 删除
			userAppDao.delete(userApp);
		}
	}

	@Override
	public UserApp search(String userCode, String appPackageName) {
		StringBuffer sbf = new StringBuffer("from UserApp where userCode = :userCode ");
		sbf.append(" and appPackageName = :appPackageName");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userCode", userCode);
		params.put("appPackageName", appPackageName);

		String hql = sbf.toString();
		UserApp userApp = userAppDao.findUnique(hql, params);
		return userApp;
	}

	@Override
	public Map<String, UserApp> search(String userCode) {
		StringBuffer sbf = new StringBuffer("from UserApp where userCode = :userCode ");
		sbf.append(" order by createTime desc");
		String hql = sbf.toString();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userCode", userCode);
		List<UserApp> userAppList = userAppDao.query(hql, params);
		
		Map<String, UserApp> map = new HashMap<String, UserApp>();
		if (null != userAppList) {
			for (UserApp userApp : userAppList) {
				map.put(userApp.getAppPackageName(), userApp);
			}
		}
		return map;
	}

}
