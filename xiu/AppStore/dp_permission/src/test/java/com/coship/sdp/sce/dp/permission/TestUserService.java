package com.coship.sdp.sce.dp.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.coship.sdp.sce.dp.permission.entity.Resource;
import com.coship.sdp.sce.dp.permission.service.ResourceService;
import com.coship.sdp.sce.dp.permission.service.RoleService;
import com.coship.sdp.sce.dp.permission.service.UserService;
import com.opensymphony.xwork2.interceptor.annotations.Before;

@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = false)
public class TestUserService extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceService resService;
	
	@Autowired
	private RoleService roleService;
	
	@Before
	public void setUp() throws Exception {
	}
	
    //按照hql查询
    @Test
    public void findByHQLTest()
    {
        try
        {
            List<Resource> list = this.userService.searchUserMenu(1);
//        	Page<Resource> page = new Page<Resource>();
//            page = this.resService.listResource(page, "from Resource", null);
//          System.out.println(page.getResultList());
            
        	System.out.println(list.size());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
      
    }

    @Test
    public void findChildTest()
    {
        
        try
        {
            //List<Resource> list = this.resService.searchChildResource("resManager", "admin");
        	List<Resource> list = this.resService.searchResource();
        	System.out.println(list.size());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
      
    }

    @Test
    public void checkPermissionTest()
    {
        
        try
        {
            boolean b = resService.checkResPermission(1L, "admin");
        	System.out.println(b);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
      
    }    
    
    @Test
    public void savaResTest()
    {
        
        try
        {
            Resource res = new Resource();
            res.setName("test=++++===");
            resService.addRes(res, 7);
        	
        	System.out.println("=====");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
      
    }    
  
    @Test
    public void searchRoleResTest()
    {
        
        try
        {
        	//List<Resource> menu = roleService.searchRoleRes(1);
            
        	//System.out.println(menu.size());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
      
    }   
    
    @Test
    public void sqlTest()
    {
        
        try
        {
        	Map<String, Boolean> m = new HashMap<String, Boolean>();
        	m.put("userManager", true);
        	m.put("permissionManager", true);
        	m.put("roleManager", true);
        	roleService.addPermission(m, 2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
      
    }   
    
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//按照hql查询
	    @Test
	    public void addResourceTest()
	    {
	        String hql = "from UsrService us where us.usrServType='1' and us.usrServCtime >'2011-06-28'and us.usrServCtime <'2011-07-08' and us.usrServGroup ='1'";
	        try
	        {
	        	Resource res = new Resource();
	        	res.setName("3333333333");
	        	res.setUrl("dfdfsf");
	            resService.addRes(res, 42);
	//        	Page<Resource> page = new Page<Resource>();
	//            page = this.resService.listResource(page, "from Resource", null);
	        	//System.out.println(page.getResultList());
	        	//System.out.println(list.size());
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	      
	    }

}
