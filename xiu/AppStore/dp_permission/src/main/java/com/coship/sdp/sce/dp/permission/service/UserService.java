/*
 * 文件名称：UserService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：chenjiawei/903903
 * 创建时间：2011-7-5
 * 
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.permission.service;

import java.util.List;
import java.util.Map;

import com.coship.sdp.dp.utils.Page;
import com.coship.sdp.sce.dp.permission.common.QueryUserCondition;
import com.coship.sdp.sce.dp.permission.entity.Resource;
import com.coship.sdp.sce.dp.permission.entity.User;
import com.coship.sdp.sce.dp.permission.entity.UserRole;

/**
 * <功能描述>
 * @author  chenjiawei/903903
 * @version  [版本号, 2011-7-5]
 * @since  [产品/模块版本]
 */
public interface UserService {

	User getUser(Long id) ;
	User getUser(String userName, String password);
	void updateUser(User user);
	void savaUser(User user);
	
	/**
	 * 根据用户查询页面显示的菜单
	 * @param userId 用户ID
	 * @return 菜单资源实体的列表
	 */
	List<Resource> searchUserMenu(long userId);
	Page<User> listUser(Page<User> page, String hql, Object[] values)
			throws Exception;
	/**
	 * 根据输入条件查询用户
	 * 
	 * @param page
	 * @param querycon
	 * @return
	 * @throws Exception
	 */
	Page<User> searchUser(Page<User> page, QueryUserCondition querycon)
			throws Exception;
	void deleteUser(long userId) throws Exception;
	
	void saveUserRole(UserRole userRole);
	
	void deleteUserRole(Long userId) throws Exception;
	
	/**
	 * 根据登录用户查询其有操作权限的资源集合
	 * @param userId  用户ID
	 * @return 以资源名为KEY，资源实体为VALUE的集合
	 */
	Map<String, Resource> searchResByUser(long userId);
	
	/**
	 * 根据用户名获得用户信息
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	List<User> getUserByName(String userName) throws Exception;

	/**
	 * 从数据库中拿到每个用户所拥有的角色
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<Long, String> getUserRole() throws Exception;


}
