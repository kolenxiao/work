/*
 * 文件名称：UserServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：chenjiawei/903903
 * 创建时间：2011-7-5
 * 
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.permission.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.dp.utils.Page;
import com.coship.sdp.sce.dp.permission.common.QueryUserCondition;
import com.coship.sdp.sce.dp.permission.dao.RoleDao;
import com.coship.sdp.sce.dp.permission.dao.UserDao;
import com.coship.sdp.sce.dp.permission.dao.UserRoleDao;
import com.coship.sdp.sce.dp.permission.entity.Resource;
import com.coship.sdp.sce.dp.permission.entity.Role;
import com.coship.sdp.sce.dp.permission.entity.User;
import com.coship.sdp.sce.dp.permission.entity.UserRole;
import com.coship.sdp.sce.dp.permission.service.UserService;

/**
 * <功能描述>
 * @author  chenjiawei/903903
 * @version  [版本号, 2011-7-5]
 * @since  [产品/模块版本]
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao<User, Long> userDao;

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RoleDao<Role, ?> roleDao;

	public RoleDao<Role, ?> getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao<Role, ?> roleDao) {
		this.roleDao = roleDao;
	}

	public UserDao<User, Long> getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao<User, Long> userDao) {
		this.userDao = userDao;
	}

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	/**
	 * 根据用户查询菜单
	 */
	public List<Resource> searchUserMenu(long userId) {
		
		List<Resource> menu = this.searchMenuByParentRes(userId, 0, 1);
		if(menu.size() > 0 ) {
			List<Resource> childMenu;
			for(Resource r : menu) {
				childMenu = this.searchMenuByParentRes(userId, r.getId(), r.getLevel()+1);
//				for(Resource cr : childMenu) {
//					String url = cr.getUrl();
//					if(url != null) {
//						cr.setUrl(url.indexOf("?") > 0 ? url+ "&enName=" + cr.getEnName() : url + "?enName=" + cr.getEnName());
//					}
//				}
				r.setRes(new HashSet<Resource>(childMenu));
			}
		}
		return menu;
	}
	
	/**
	 * 根据用户和父资源查询子资源列表
	 */
	public List<Resource> searchMenuByParentRes(long userId, long parentId, int level) {
	
		String hql = "select distinct e  from User a, UserRole b, Role c, RoleResource d, Resource e " +
				"where b.user = a and b.userRole = c and d.resRole = c and d.resource = e " +
				"and e.menuButton = 0 and a.id = ? and e.level = ? ";
		if(parentId >0) {
			hql = hql + "and e.parentRes.id = ? order by e.orderField";
			return userDao.query(hql, userId, level, parentId); 
		} else {
			hql = hql + "order by e.orderField";
			return userDao.query(hql, userId, level);
		}
	}
	
	public Map<String, Resource> searchResByUser(long userId) {
		Map<String, Resource> userResMap = new HashMap<String, Resource>();
		String hql = "select c from UserRole a, RoleResource b, Resource c where a.userRole = b.resRole " +
				"and b.resource = c and a.user.id = ?";
		List<Resource> res = userDao.query(hql, userId);
		for(Resource r : res) {
			if (r == null)
			{
				continue;
			}
			
			if(r.getUrl() != null) {
				userResMap.put(r.getEnName(), r);
			}
		}
		return userResMap;
	}
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	public User getUser(String userName, String password) {
		StringBuffer HQL = new StringBuffer("from User where userName = '");
		HQL.append(userName);
		HQL.append("' and password = '");
		HQL.append(password);
		HQL.append("'");
		
		//System.out.println("HQL:"+HQL);
		List<User> result =  userDao.query(HQL.toString());
		
		if(result!=null&&result.size()==1){
			return result.get(0);
		}else{
			return null;
		}	
	}

	/**
	 * @param user
	 */
	@Transactional
	public void updateUser(User user) {
		userDao.update(user);
	}

	/**
	 * @param id
	 * @return
	 */
	public User getUser(Long id) {
		return userDao.get(id);
	}

	public List<Resource> searchUserMenu33(long id) {
//		List<Resource> list = userDao.query("select c from User a, Role b, Resource c " +
//				"where a.id = ? and c.level = 1 and b in elements(a.roles) and c in elements(b.res)" +
//				" ", id);
		List<Resource> list = userDao.query("select c from User a, Role b, Resource c " +
				"where a.id = ? and b in elements(a.roles) and c in elements(b.res)" +
				" ", id);
		//return list;
		List<Resource> menu = new ArrayList<Resource>();
		String url ;
		//System.out.println("list.size(): " + list.size());
		for(Resource  r : list) {
			
			if (r == null)
			{
				continue;
			}
			
			if(r.getLevel() == 1) {
				for(Resource  child : r.getRes()) {
					
					if (child == null)
					{
						continue;
					}
					url = child.getUrl();
					if(url != null) {
						int a = url.indexOf("enName");
						if(a<0) {
							child.setUrl(url.indexOf("?") > 0 ? url + "&enName=" + child.getEnName() : url + "?enName=" + child.getEnName());
							//System.out.println(child.getId());
						}
					}
//					if(child.getRoles().size() > 0) {
//						r.setRoles(new ArrayList());
//					} else {
//						r.setRoles(null);
//					}
				}
				menu.add(r);
//				System.out.println("r: " + r.getRoles());
//				System.out.println("list: " + r.getRoles());
			}
		}
		//userDao.query("", );
		return menu;
	}

	@Override
	public Page<User> listUser(Page<User> page, String hql, Object[] values)
			throws Exception {
		return userDao.queryPage(page, hql, values);
	}

	@Override
	public void savaUser(User user) {
		userDao.save(user);
	}


	@Transactional
	public void deleteUser(long userId) throws Exception {
		// 首先删除掉关联表中t_user_role中的数据，然后再删除t_user表中的数据
		
		deleteUserRole(userId);
		userDao.delete(userDao.get(userId));
	}

	@Transactional
	public void deleteUserRole(Long userId) throws Exception{

		//System.out.println("deleteRole userID is "+userId);
		String hql = "from UserRole u where u.user.id = ? ";
		
		List<UserRole> userRoleList = userRoleDao.query(hql, userId);
		
		if (userRoleList == null ||userRoleList.size()==0)
		{
			//System.out.println("deleteRole userRoleList is null");
			return;
		}
		
		for (UserRole userRole:userRoleList)
		{
			userRoleDao.delete(userRole);
		}
		
	}
	
	@Override
	public void saveUserRole(UserRole userRole) {
		userRoleDao.save(userRole);
	}
	
	/*
	 * 
	 * 
	 * 根据用户名查询用户
	 * 
	 * @see
	 * com.coship.sdp.iag.usp.permission.service.UserService#getUserByName(java
	 * .lang.String)
	 */
	@Override
	public List<User> getUserByName(String userName) throws Exception {
		String hql = "from User u where u.userName=?";

		return userDao.query(hql, userName);
	}

	/*
	 * 从数据库中拿到没有用户所对应的角色 (non-Javadoc)
	 * 
	 * @see com.coship.sdp.iag.usp.permission.service.UserService#getUserRole()
	 */
	@Override
	public Map<Long, String> getUserRole() throws Exception {
		Map<Long, String> usrRoleMap = new HashMap<Long, String>();

		List<User> usrList = userDao.query("from User", "");

		if (usrList == null || usrList.size() == 0) {
			//System.out.println("usrList is null");
			return usrRoleMap;
		}

		// 根据用户id查找出用户所拥有的角色
		String hql = "select r from Role r,UserRole ur where ur.user.id = ? and ur.userRole.id = r.id";
		StringBuffer sb = null;

		for (User user : usrList) {
			if (user == null) {
				continue;
			}
			List<Role> roleList = roleDao.query(hql, user.getId());

			if (roleList == null || roleList.size() == 0) {
				continue;
			}
			sb = new StringBuffer();
			for (Role r : roleList) {
				if (r == null) {
					continue;
				}

				sb.append(r.getName()).append(" ");
			}
			if (sb.toString() != null) {
				usrRoleMap.put(user.getId(), sb.toString());
			}

		}

		return usrRoleMap;
	}

	/*
	 * 根据条件查询用户 (non-Javadoc)
	 * 
	 * @see
	 * com.coship.sdp.iag.usp.permission.service.UserService#searchUser(com.
	 * coship.sdp.dp.utils.Page,
	 * com.coship.sdp.iag.usp.permission.service.impl.querycondition
	 * .QueryUserCondition)
	 */
	@Override
	public Page<User> searchUser(Page<User> page, QueryUserCondition querycon)
			throws Exception {
		
		StringBuffer hql = new StringBuffer();
		if(querycon.getRoleId()==null)
		{
			hql.append("select user from User user where 1=1 ");
		}
		else
		{
			hql.append("select user from User user ,UserRole ur where 1=1 ");
		}
		
		
		// 用户名称
		if (StringUtils.isNotEmpty(querycon.getUserName())) {
			hql.append("and user.userName like'%")
					.append(querycon.getUserName()).append("%'");
		}
		// 创建时间段
//		if (StringUtils.isNotEmpty(querycon.getStartDate())
//				&& StringUtils.isNotEmpty(querycon.getEndDate())) {
//			if (StringUtils.isNotEmpty(querycon.getUserName())) {
//				hql.append(" and user.createdDate >'")
//						.append(querycon.getStartDate())
//						.append("'and user.createdDate <'")
//						.append(querycon.getEndDate()).append("'");
//			} else {
//				hql.append("where user.createdDate >'")
//						.append(querycon.getStartDate())
//						.append("'and user.createdDate <'")
//						.append(querycon.getEndDate()).append("'");
//			}
//
//		}
		// 创建时间段
        if (StringUtils.isNotEmpty(querycon.getStartDate())
                || StringUtils.isNotEmpty(querycon.getEndDate()))
        {
            if (StringUtils.isNotEmpty(querycon.getStartDate())
                    && StringUtils.isEmpty(querycon.getEndDate()))
            {                
                hql.append(" and user.createdDate >='")
				.append(querycon.getStartDate())
				.append(" 00:00:00")
				.append("'");
            }
            else if (StringUtils.isEmpty(querycon.getStartDate())
                    && StringUtils.isNotEmpty(querycon.getEndDate()))
            {
                
                hql.append(" and user.createdDate <='")
				.append(querycon.getEndDate())
				.append(" 59:59:59")
				.append("'");
            }
            else 
            {                
                hql.append("and user.createdDate >='")
				.append(querycon.getStartDate())
				.append(" 00:00:00").append("'")
				.append(" and user.createdDate <='")
				.append(querycon.getEndDate())
				.append(" 59:59:59")
				.append("'");
            }
        }
		
		// 角色id
		if (querycon.getRoleId()!=null)
		{
			hql.append(" and user.id = ur.user.id and ur.userRole.id = '")
						.append(querycon.getRoleId()).append("'");			
		}
		

		page = userDao.queryPage(page, hql.toString(), new Object[0]);
		return page;
	}

}
