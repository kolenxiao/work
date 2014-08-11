package com.coship.sdp.sce.dp.permission.service;

import java.util.List;
import java.util.Map;

import com.coship.sdp.dp.utils.Page;
import com.coship.sdp.sce.dp.permission.entity.Role;

public interface RoleService {

	void addRole(Role role) throws Exception;
	
	/**
	 * 修改角色信息
	 * @param role 待修改的角色信息
	 * @throws Exception
	 */
	void modifyRole(Role role) throws Exception;
	
	/**
	 * 删除角色  同时删除本角色与资源的关系   如果有用户
	 * @param roleId  角色ID
	 * @return 返回删除结果及信息   SUCCESS:表示成功
	 * @throws Exception
	 */
	String deleteRole(List<Long> roleId) throws Exception;
	
	/**
	 * 根据角色ID查询角色
	 * @param roleId  角色ID
	 * @return  一个对应的角色实体
	 * @throws Exception
	 */
	Role findRole(long roleId) throws Exception;
	
	/**
	 * 分页查询角色列表
	 * @param page     分页数据
	 * @param role      角色对象
	 * @param values   查询条件
	 * @return  分页数据
	 * @throws Exception
	 */
	Page<Role> listRole(Page<Role> page, Role role, Object[] values) throws Exception;
	
	/**
	 * 根据角色查询     角色有操作权限的资源
	 * @param roleId  角色ID
	 * @return  角色有操作权限的资源
	 */
	Map<String, Boolean> searchRoleRes(long roleId) throws Exception;
	
	/**
	 * 为角色添加资源访问权限
	 * @param rolePerMap 角色是否有资源的访问权限
	 * @param roleId  角色ID
	 */
	void addPermission(Map<String, Boolean> rolePerMap, long roleId) throws Exception;
	
	/**
	 * 根据用户id查询出该用户所有的角色
	 * @param page
	 * @param userID
	 * @return
	 */
	List<Role> listRole(List<Role> page, Long userID) throws Exception;
	
	void getUserSelectRole(Map<Long,Boolean> userSelectedRoleMap,Long userId) throws Exception;
	
	List<Role> listRole() throws Exception;;
	
}
