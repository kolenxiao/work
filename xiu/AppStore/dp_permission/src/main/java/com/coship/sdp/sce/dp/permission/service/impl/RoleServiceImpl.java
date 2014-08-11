package com.coship.sdp.sce.dp.permission.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.dp.utils.Page;
import com.coship.sdp.sce.dp.permission.dao.RoleDao;
import com.coship.sdp.sce.dp.permission.dao.RoleResourceDao;
import com.coship.sdp.sce.dp.permission.entity.Resource;
import com.coship.sdp.sce.dp.permission.entity.Role;
import com.coship.sdp.sce.dp.permission.entity.RoleResource;
import com.coship.sdp.sce.dp.permission.entity.UserRole;
import com.coship.sdp.sce.dp.permission.service.ResourceService;
import com.coship.sdp.sce.dp.permission.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao<Role, ?> roleDao;

	@Autowired
	private RoleResourceDao<RoleResource, ?> roleResDao;
	
	public static final String ROLE_STR1 = "角色正被<";
	
	public static final String ROLE_STR2 = ">用户使用，请先删除这些用户名下此角色！";
	
	public RoleResourceDao<RoleResource, ?> getRoleResDao() {
		return roleResDao;
	}

	public void setRoleResDao(RoleResourceDao<RoleResource, ?> roleResDao) {
		this.roleResDao = roleResDao;
	}

	@Autowired
	private ResourceService resService;
	

	public RoleDao<Role, ?> getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao<Role, ?> roleDao) {
		this.roleDao = roleDao;
	}

	/**
	 * 新增角色信息
	 */
	@Transactional
	public void addRole(Role role) throws Exception {
		//Role role = roleDao.get(roleId);
		roleDao.save(role);
	}

	/**
	 * 修改角色信息
	 */
	@Transactional
	public void modifyRole(Role role) throws Exception {
		roleDao.update(role);
	}

	/**
	 * 删除角色信息
	 */
	@Transactional
	public String deleteRole(List<Long> roleId) throws Exception {
		String hql = "from UserRole a where a.userRole.id = ?";
		for(long id : roleId) {
			Role role = roleDao.get(id);
			List<UserRole> urList = roleDao.query(hql, id);
			if(urList != null && urList.size() > 0) {
//				String useres = "";
//				for(UserRole ur : urList) {
//					useres += ur.getUser().getUserName() + ";";
//				}
//				return role.getName() + "角色正被<" + useres + ">用户使用，请先删除这些用户名下此角色！";
				StringBuffer useres = new StringBuffer();
				for (UserRole ur : urList)
				{
					if (ur == null)
					{
						continue;
					}
					useres.append(ur.getUser().getUserName()).append(";");					
				}
				return role.getName() + ROLE_STR1 + useres.toString() + ROLE_STR2;
			}
			this.deleteRoleRes(id);
			roleDao.delete(role);
		}
		return "SUCCESS";
	}

	/**
	 * 根据ID查找角色信息
	 */
	public Role findRole(long roleId) throws Exception {
		return (Role) roleDao.get(roleId);
	}

	/**
	 * 分页查找角色列表
	 */
	public Page<Role> listRole(Page<Role> page, Role role, Object[] values)
			throws Exception {
		String hql = "from Role a ";
		if(role == null) {
			return roleDao.queryPage(page, hql, values);
		}
		// 角色名
        if (StringUtils.isNotEmpty(role.getName()))
        {
            hql += " where a.name like'%" + role.getName()
                    + "%' ";
        } 
        // 创建时间段
        if (StringUtils.isNotEmpty(role.getBeginDate())
                || StringUtils.isNotEmpty(role.getEndDate()))
        {
        	if (StringUtils.isNotEmpty(role.getName())){
        		hql += " and ";
        	} else {
        		hql += " where ";
        	}
            if (StringUtils.isNotEmpty(role.getBeginDate())
                    && StringUtils.isEmpty(role.getEndDate()))
            {
                hql += " a.createdDate >='"
                        + role.getBeginDate() + " 00:00:00" + "'";
            }
            else if (StringUtils.isEmpty(role.getBeginDate())
                    && StringUtils.isNotEmpty(role.getEndDate()))
            {
                hql += " a.createdDate <='"
                        + role.getEndDate() + " 59:59:59" + "'";
            }
            else
            {
                hql += " a.createdDate >='"
                        + role.getBeginDate() + " 00:00:00" + "'"
                        + "and a.createdDate <='"
                        + role.getEndDate() + " 59:59:59" + "'";
            }
        }
		return roleDao.queryPage(page, hql, values);
	}
	
	

	/**
	 * 根据角色查询其与资源对应的关系
	 */
	@Override
	public Map<String, Boolean> searchRoleRes(long roleId) throws Exception{
		Map<String, Boolean> rolePerMap = new HashMap<String, Boolean>();
		String hql = "from RoleResource a where a.resRole.id = ?";
		List<RoleResource> roleReses = roleDao.query(hql, roleId);
		RoleResource rolrRes;
		for(Iterator<RoleResource> iter = roleReses.iterator(); iter.hasNext();) {
			rolrRes = iter.next();
			if (rolrRes == null)
			{
				continue;
			}
			rolePerMap.put(rolrRes.getResource().getEnName(), true);
		}
		//System.out.println(rolePerMap);
		return rolePerMap;
	}

	/**
	 * 为角色添加资源访问权限
	 */
	@Override
	@Transactional
	public void addPermission(Map<String, Boolean> rolePerMap, long roleId) throws Exception{
		Role role = (Role)roleDao.get(roleId);
		this.deleteRoleRes(roleId);
		
		Set<String> key = rolePerMap.keySet();
		String enName;
		for(Iterator<String> iter = key.iterator(); iter.hasNext();) {
			enName = iter.next();
			if(rolePerMap.get(enName)) {
				Resource res = resService.findResByEnName(enName);
				RoleResource rr = new RoleResource();
				rr.setResRole(role);
				rr.setResource(res);
				roleResDao.save(rr);
			}
		}

	}

	/**
	 * 删除角色与资源关系
	 * @param roleId  角色ID
	 */
	private void deleteRoleRes(long roleId) throws Exception {
		List<RoleResource> roleReses = roleDao.query("from RoleResource a where a.resRole.id = ?", roleId);
		if(roleReses != null && roleReses.size() > 0) {
			for(RoleResource roleRes : roleReses) {
				roleResDao.delete(roleRes);
			}
		}
	}
	
	public ResourceService getResService() {
		return resService;
	}

	public void setResService(ResourceService resService) {
		this.resService = resService;
	}

	
	/**
	 * 根据用户id查询出该用户所有的角色
	 * @param page
	 * @param userID
	 * @return
	 */
	public Page<Role> listRole(Page<Role> page, Long userID) throws Exception
	{
		if (userID==null||page ==null)
		{
			//System.out.println( "listRole error");
			return null;
		}
		//System.out.println("listRole userID is "+userID);
		String hql = " select r from Role r,UserRole ur where ur.user.id = ? and r.id = ur.userRole.id";
		
		
		
		return roleDao.queryPage(page, hql, userID);
	}

	/**
	 * 根据用户id获取用户的角色
	 * @param userSelectedRoleMap
	 * @param userId
	 */
	public void getUserSelectRole(Map<Long,Boolean> userSelectedRoleMap,Long userId) throws Exception
	{
		String hql ="select r from Role r,UserRole ur where ur.user.id = ? and ur.userRole.id = r.id";
		List<Role> roleList = roleDao.query(hql, userId);
		
		if (userSelectedRoleMap!=null)
		{
			for (Role role :roleList)
			{
				if (role==null)
				{
					continue;
				}
				userSelectedRoleMap.put(role.getId(), true);
			}
			
		}
	}

	@Override
	public List<Role> listRole(List<Role> roleList, Long userID) throws Exception{
		if (userID==null||roleList ==null)
		{
			//System.out.println( "listRole error");
			return null;
		}
		//System.out.println("listRole userID is "+userID);
		String hql = " select r from Role r,UserRole ur where ur.user.id = ? and r.id = ur.userRole.id";
		return roleDao.query(hql, userID);
	}

	@Override
	public List<Role> listRole() throws Exception {
		String hql = "from Role";
		List<Role> roleList = roleDao.query(hql);
		if (roleList==null ||roleList.size()==0)
		{
			//System.out.println("roleList is null");
			return null;
		}
		
		return roleList;

	}

	
	
}
