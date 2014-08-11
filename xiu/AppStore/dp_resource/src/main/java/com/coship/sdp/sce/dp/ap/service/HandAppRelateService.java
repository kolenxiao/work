package com.coship.sdp.sce.dp.ap.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.ap.entity.HandAppRelate;
import com.coship.sdp.utils.Page;

/**
 * "人工指定的关联推荐应用信息" 服务层接口
 * 
 * @author 908618
 * 
 */
public interface HandAppRelateService extends Serializable {

	/**
	 * 保存
	 * 
	 * @param planId
	 * @param appPkgName
	 * @param relateAppPkgNames
	 */
	public void save(String planId, String appPkgName, String[] relateAppPkgNames);

	/**
	 * 取消
	 * 
	 * @param planId
	 * @param appPkgName
	 */
	public void deleteByAppPkgName(String planId, String appPkgName);
	public void deleteByRelateAppPkgName(String planId, String relateAppPkgName);
	
	/**
	 * 查询列表
	 * @param planId
	 * @param appPkgName
	 * @return
	 */
	public List<HandAppRelate> list(String planId, String appPkgName);

	/**
	 * 查询猜你喜欢的应用
	 * @param planId
	 * @param appPkgName
	 * @param countMax
	 * @return
	 */
	public List<DpAppInfo> getAppRelateList(String planId, String appPkgName);

	public List<DpAppInfo> getAppRelateAppListBySystem(String planId,
			String appPkgName);

	public List<DpAppInfo> getAppRelateAppListByHand(String planId,
			String appPkgName);
	
	/**
	 * 查询能够替换的关联应用列表
	 * @param page
	 * @param specialParams
	 * @return
	 */
	public Page<DpAppInfo> getAppListForReplace(Page<DpAppInfo> page, Map<String, Object> specialParams);

}
