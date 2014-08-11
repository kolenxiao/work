package com.coship.sdp.sce.dp.permission.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.dp.utils.Page;
import com.coship.sdp.sce.dp.permission.dao.ResourceDao;
import com.coship.sdp.sce.dp.permission.entity.Resource;
import com.coship.sdp.sce.dp.permission.entity.RoleResource;
import com.coship.sdp.sce.dp.permission.service.ResourceService;

@Service("resService")
public class ResourceServiceImpl implements ResourceService
{

    @Autowired
    private ResourceDao<Resource, Long> resDao;
    
    private List<Resource> allRes;
    
    public final static String RESOURCE_STR1 = "该资源已被授权给<";
    
    public final static String RESOURCE_STR2 = ">角色，请先删除该授权或删除该角色！";

    public final static String RESOURCE_STR3 = "您不能直接删除该资源，请先删除其子资源！";
    
    /**
     * 修改资源
     */
    @Transactional
    public void modifyResource(Resource resource, long parentId)
            throws Exception
    {
        Resource parentRes = findResource(parentId);
        resource.setParentRes(parentRes);
        resDao.update(resource);
    }

    /**
     * 删除资源 如果其有子资源
     */
    @Transactional
    public String deleteResource(long resourceId) throws Exception
    {
        String hql = "from RoleResource a where a.resource.id = ?";
        List<RoleResource> roleRes = resDao.query(hql, resourceId);
        if (roleRes != null && roleRes.size() > 0)
        {
//            String roleName = "";
//            for (RoleResource rr : roleRes)
//            {
//                roleName = roleName + rr.getResRole().getName() + ",";
//            }
//            return  "该资源已被授权给<" + roleName + ">角色，请先删除该授权或删除该角色！";
        	StringBuffer roleName = new StringBuffer();
        	for (RoleResource rr : roleRes)
        	{
        		roleName.append(rr.getResRole().getName()).append(",");
        	}
        	return RESOURCE_STR1 + roleName.toString() + RESOURCE_STR2;
        	
        }
        List<Resource> res = resDao.query(
                "from Resource a where a.parentRes.id = ?", resourceId);
        if (res != null && res.size() > 0)
        {
            return RESOURCE_STR3;
        }
        resDao.delete(resDao.get(resourceId));
        return "SUCCESS";
    }

    /**
     * 根据资源ID查询资源信息
     */
    public Resource findResource(long resId) throws Exception
    {
        return resDao.get(resId);
    }

    /**
     * 根据菜单资源ID 查询其名下按钮的权限
     */
    public List<Resource> searchChildResource(String enName, String userName)
            throws Exception
    {
        // List<Resource> li = resDao.query("select a from Resource a, Resource b where a in elements(b.res) and b.enName = ?", enName);
        List<Resource> li = resDao
                .query("select a from Resource a where a.menuButton = 1 and a.parentRes.enName = ?",
                        enName);
        //System.out.println("li: = " + li.size() + enName);
        if (li != null && li.size() > 0)
        {
            /*
             * for(int i=0; i<li.size(); i++) { Resource res = (Resource)li.get(0); System.out.println("====" + res.getId() + "====" ); if(!checkResPermission(res.getId(), userName)) { li.remove(i); }
             * 
             * }
             */
            Iterator<Resource> iter = li.iterator();
            Resource res ;
            while (iter.hasNext())
            {
                //System.out.println(iter.hashCode());
                res = iter.next();
                
                if (res == null)
                {
                	continue;
                }
                
                //System.out.println("====" + res.getId() + "====");
                if (!checkResPermission(res.getId(), userName))
                {
                    iter.remove();
                }
            }
        }
        return li;
    }

    /**
     * 分页查询资源列表信息
     */
    public Page<Resource> listResource(Page<Resource> page, String hql, Object[] values) throws Exception
    {
        return this.resDao.queryPage(page, hql, values);
    }

    /**
     * 检查用户是否有某资源的权限
     */
    public boolean checkResPermission(long resId, String userName)
    {

        String hql = "select e  from User a, UserRole b, Role c, RoleResource d, Resource e "
                + "where b.user = a and b.userRole = c and d.resRole = c and d.resource = e "
                + "and e.id = ? and a.userName = ?";
        List<Resource> li = resDao.query(hql, new Object[]
        { resId, userName });
        if (li != null && li.size() > 0)
        {
            return true;
        }
        return false;
    }

    /**
     * 根据传入父资源ID 添加新资源
     */
    public void addRes(Resource res, long parentId)
    {

        if (parentId > 0)
        {
            Resource r = resDao.get(parentId);
            res.setLevel(r.getLevel() + 1);
            res.setParentRes(r);
        }
        else
        {
            res.setLevel(1);
        }
        resDao.save(res);
    }

    /**
     * 查询所有资源信息并返回
     */
    @Override
    public List<Resource> searchResource() throws Exception
    {

        // List<Resource> res = resDao.query("from Resource where level = ?", 1);
        // if(res.size() >0) {
        // for(Resource r : res) {
        // List<Resource> child = resDao.query("from Resource where level = ?", r.getLevel() + 1);
        // if(child.size() >0) {
        // //for()
        // List<Resource> childRes = resDao.query("from Resource where level = ?", r.getLevel() + 1);
        //
        // }
        // r.setRes(new HashSet<Resource>(child));
        // }
        // }
        //
        // return resDao.query("from Resource", "");
        allRes = resDao.query("from Resource where level = ?", 1);
        getAllRes(allRes);
        return allRes;
    }

 

    /**
     * 递归查询所有资源
     * @param res
     */
    public void getAllRes(List<Resource> res)
    {
        if (res.size() > 0)
        {
            for (Resource r : res)
            {
                List<Resource> child = resDao
                        .query("from Resource a where a.level = ? and a.parentRes.id = ?",
                                r.getLevel() + 1, r.getId());
                r.setRes(new HashSet<Resource>(child));
                getAllRes(child);

            }
        }
    }

    /**
     * 根据enName查询资源信息
     */
    @Override
    public Resource findResByEnName(String enName)
    {
        List<Resource> res = resDao.query("from Resource where enName = ?",
                enName);
        if (res != null && res.size() > 0)
        {
            return res.get(0);
        }
        return null;
    }

    public List<Resource> findByHQL(String HQL)
    {
        return resDao.query(HQL);
    }

	public ResourceDao<Resource, Long> getResDao() {
		return resDao;
	}

	public void setResDao(ResourceDao<Resource, Long> resDao) {
		this.resDao = resDao;
	}

}
