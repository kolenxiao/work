package com.coship.sdp.sce.dp.system.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.coship.sdp.sce.dp.system.entity.SystemParam;
import com.coship.sdp.utils.Page;

public interface SystemParamService extends Serializable {
	
	/**
	 * 新增
	 * @param systemParam
	 */
	public void create(SystemParam systemParam);
	
	/**
	 * 修改
	 * @param systemParam
	 */
	public void update(SystemParam systemParam);
	
	/**
	 * 删除
	 * @param idArr
	 */
	public void delete(String[] idArr);
	
	/**
	 * 查询
	 * @param systemParamQuery
	 * @return
	 */
	public Page<SystemParam> search(Page<SystemParam> page, SystemParam systemParam, Map<String, Object> params);
	
	public SystemParam getById(String id);
	
	public SystemParam getByCode(String code);
	
	public List<SystemParam> getAll();

}
