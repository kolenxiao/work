package com.coship.sdp.sce.dp.recommend.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanelItem;
import com.coship.sdp.utils.Page;

@RunWith(value=Parameterized.class)
public class AppRecommendPanelItemServiceImplTest{
	@Autowired
	private static AppRecommendPanelItemService appRecommendPanelItemService;
    
	//分页对象
    private Page<AppRecommendPanelItem> page;
    
    //对象实体
    private AppRecommendPanelItem entity;
    
    //元素名称
    private String itemName;
    
    //元素类型
    private Integer type;
    
    //版块ID
    private String panelKey;
    
	
    public AppRecommendPanelItemServiceImplTest(AppRecommendPanelItem entity,
            String itemName, Integer type, String panelKey)
    {
        this.entity = entity;
        this.itemName = itemName;
        this.type = type;
        this.panelKey = panelKey;
    }
	
    @Parameters
    public static Collection<Object[]> getEntityCollections()
    {
        Object[][] collections = new Object[5][4];
        
        String[][] itemNames = new String[][]{{"愤怒的小鸟"},{"今日最热"},{"我是MT"},{"韩剧"},{"动漫专场"}};
        
        Integer[] types = new Integer[]{1,2,3,1,2};
        
        String[] typeValue = new String[]
        { "com.kunpeng.babydraw_1280x800", "40288a913f75a75e013f9ec2a6e40092",
                "abc", "com.sinyee.babybus.organized",
                "40288a913f9f50bc013fb2ce378a002e" };
        
        String[] panelKeys = new String[]
        { "8a8ac8ed422b81fc01422bf23c6f02c2",
                "8a8ac8ed422bffde01422c008f440002",
                "8a8ac8ed422bffde01422c00adbe0003", "8a8ac8ed422bffde01422c008f440002", "8a8ac8ed422b81fc01422bf23c6f02c2" };
        
        for (int i = 0; i < 5; i++)
        {
            AppRecommendPanelItem obj = new AppRecommendPanelItem();
            obj.setItemName("男人最爱"+i);
            obj.setType(types[i]);
            obj.setTypeValue(typeValue[i]);
            obj.setItemPoster("cc.jpg");
            obj.setStatus(1);
            collections[i][0] = obj;
            collections[i][1] = itemNames[i][0];
            collections[i][2] =  types[i];
            collections[i][3] = panelKeys[i];
        }

        return Arrays.asList(collections);
    }
    
    @BeforeClass
    public static void init()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-test.xml");
        appRecommendPanelItemService = (AppRecommendPanelItemService)context.getBean("appRecommendPanelItemService");
    }
    
    @Before
    public void setUp()
    {
        page = new Page<AppRecommendPanelItem>();
    }
	
	@Test
	@Ignore
	public void testGetAllAppRecommendPanelItemList() {
		List<AppRecommendPanelItem> result = appRecommendPanelItemService.getAllAppRecommendPanelItemList();

		assertNotNull(result);
		assertTrue(result.size() > 0);
	}
	
	/**
	 * 新增元素
	 */
    @Test
    public void testSaveAppRecommendPanelItem()
    {
        entity.setItemName(itemName);
        appRecommendPanelItemService.saveAppRecommendPanelItem(entity);
    }
    
    /**
     * 修改元素
     */
    @Test
    public void testUpdateAppRecoomendPanelItem()
    {
        appRecommendPanelItemService.updateAppRecoomendPanelItem(entity);
    }
    
    /**
     * 查询可用元素
     */
    @Test
    public void testQueryAppRecommendPanelItemList()
    {
        appRecommendPanelItemService.queryAppRecommendPanelItemList(page, itemName, type);
        assertEquals(1,page.getResultList().size());
    }
    
    /**
     * 根据ID获取元素
     */
    @Test
    public void testGetAppRecoomendPanelItemById()
    {
        Object obj = appRecommendPanelItemService.getAppRecoomendPanelItemById(entity.getId());
        assertNotNull(obj);
    }
    
    /**
     * 版块关联元素
     */
    @Test
    public void testDoRecommendItem()
    {
        appRecommendPanelItemService.doRecommendItem(new String[]{entity.getId()}, panelKey);
    }
    
    /**
     * 版块下的元素进行排序
     */
    @Test
    public void testDoSortAppRecoomendPanelItem()
    {
        appRecommendPanelItemService.doSortAppRecoomendPanelItem(entity.getId(), new Random().nextInt(15)+"");
        assertNotSame(0,appRecommendPanelItemService.getAppRecoomendPanelItemById(entity.getId()).getSortNum());
    }
    
    /**
     * 查询版块下的元素
     */
    @Test
    public void testQueryItemListByPanelId()
    {
        appRecommendPanelItemService.queryItemListByPanelId(page, itemName, type, panelKey);
        assertEquals(1, page.getResultList().size());
    }
    
    /**
     * 查询可关联版块的元素
     */
    @Test
    public void testQueryUnRecommendItemList()
    {
        appRecommendPanelItemService.queryUnRecommendItemList(page, null, null);
        assertEquals("异常啦", 0, page.getResultList().size());
    }
    
    /**
     * 取消版块和元素之间的关联
     */
    @Test
    public void testUnRecommendItemToPanel()
    {
        List<String> list = new ArrayList<String>();
        list.add(entity.getId());
        int result = appRecommendPanelItemService.unRecommendItemToPanel(list);
        assertEquals(1, result);
    }
    
    /**
     * 删除元素
     */
    @Test
    public void testDeleteItem(){
        List<String> list = new ArrayList<String>();
        list.add(entity.getId());
        int result = appRecommendPanelItemService.deleteItem(list);
        assertEquals(String.format("请求删除%d条,实际删除%d条", 1, result), 1, result);
    }
}
