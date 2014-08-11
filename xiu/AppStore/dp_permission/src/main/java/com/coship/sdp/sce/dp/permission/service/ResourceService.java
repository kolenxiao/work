package com.coship.sdp.sce.dp.permission.service;

import java.util.List;

import com.coship.sdp.dp.utils.Page;
import com.coship.sdp.sce.dp.permission.entity.Resource;

public interface ResourceService {
	
	public void modifyResource(Resource resource, long parentId) throws Exception;
	
	/**
	 * 删除资源信息    有子资源允许删除
	 * @param resourceId 资源ID
	 * @return 返回删除结果及信息   SUCCESS:表示成功
	 * @throws Exception
	 */
	public String deleteResource(long resourceId) throws Exception;
	
	public Resource findResource(long resId) throws Exception;
	
	public List<Resource> searchChildResource(String enName, String userName) throws Exception;
	
	public Page<Resource> listResource(Page<Resource> page,String hql,Object[] values) throws Exception;

	public boolean checkResPermission(long resId, String userName) throws Exception;
	
	/**
	 * 根据传入父资源ID  添加新资源
	 * @param res 新资源
	 * @param parentId 父资源ID
	 */
	public void addRes(Resource res, long parentId) throws Exception;

	/**
	 * 查询所有资源列表
	 * @return  资源列表
	 * @throws Exception
	 */
	public List<Resource> searchResource() throws Exception;
	
	public Resource findResByEnName(String enName) throws Exception;
	
	public List<Resource> findByHQL(String HQL) throws Exception;
}
