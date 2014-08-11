package com.coship.sdp.sce.dp.recommend.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.sce.dp.recommend.service.AppRecommendPanelService;
import com.coship.sdp.test.utils.SpringTestCase;
import com.coship.sdp.utils.Page;
import static org.junit.Assert.*;
@RunWith(value=Parameterized.class)
public class AppRecommendPanelTest
{
    @Autowired
    private static AppRecommendPanelService appRecommendPanelService;
    
    private Page<AppRecommendPanel> page;
    
    private AppRecommendPanel entity;
    
    private String newName;
    
    public AppRecommendPanelTest(AppRecommendPanel entity, String newName){
        this.entity = entity;
        this.newName = newName;
    }
    
    
    @Parameters
    public static Collection<Object[]> getEntityCollections()
    {
        Object[][] collections = new Object[5][2];
        String[][] names = new String[][]{{"海贼王"},{"柯南"},{"火影忍者"},{"圣斗士"},{"北斗神拳"}};
        for (int i = 0; i < 5; i++)
        {
            AppRecommendPanel obj = new AppRecommendPanel();
            obj.setPanelName("男人最爱"+i);
            obj.setLayoutType(new Random().nextInt(2)+1);
            obj.setPanelDesc("测试"+i);
            obj.setStatus(1);
            collections[i][0] = obj;
            collections[i][1] = names[i][0];
        }

        return Arrays.asList(collections);
    }
    
    @BeforeClass
    public static void init()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-test.xml");
        appRecommendPanelService = (AppRecommendPanelService)context.getBean("appRecommendPanelService");
    }
    
    @Before
    public void setUp()
    {
        page = new Page<AppRecommendPanel>();
    }
    
    
    /**
     * 添加版块信息
     */
    @Test
    public void testSaveRecommendPanel()
    {
        appRecommendPanelService.saveAppRecommendPanel(entity);
    }
    
    /**
     * 测试修改版块信息
     */
    @Test
    public void testUpdateAppRecoomendPanel()
    {
        entity.setPanelName(newName);
        appRecommendPanelService.updateAppRecoomendPanel(entity);
        assertEquals(entity.getPanelName(), newName);
    }
    
    /**
     * 根据条件查询版块信息
     */
    @Test
    public void testqueryAppRecommendPanelList()
    {
        appRecommendPanelService.queryAppRecommendPanelList(page, newName);
        assertEquals(1, page.getResultList().size());
    }
    
    /**
     * 测试版块排序
     */
    @Test
    public void doSortAppRecoomendPanel(){
        int sort = new Random().nextInt(15);
        appRecommendPanelService.doSortAppRecoomendPanel(entity.getId(), sort+"");
    }
    
    /**
     * 测试根据ID获取版块信息
     */
    @Test
    public void testGetAppRecoomendPanelById(){
        assertNotNull(appRecommendPanelService.getAppRecoomendPanelById(entity.getId()));
    }
    
    /**
     * 删除版块信息
     */
    @Test
    public void testDeleteAppRecoomendPanelStatus()
    {
        appRecommendPanelService.queryAppRecommendPanelList(page, null);
        List<String> listKeys = new ArrayList<String>();
        for (AppRecommendPanel obj : page.getResultList())
        {
            listKeys.add(obj.getId());
        }
        int actual = appRecommendPanelService.deleteAppRecoomendPanel(listKeys);
        assertEquals(String.format("请求%d条,实际删除%d条", listKeys.size(), actual),
                listKeys.size(), actual);
    }
    
    @AfterClass
    public static void destory(){
        appRecommendPanelService = null;
    }
   
}
