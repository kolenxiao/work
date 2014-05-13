package com.xiu.uuc.web.util;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class BeanLocator
{

    public static BeanLocator getInstance()
    {
        if(instance == null)
            synchronized(logger)
            {
                if(instance == null)
                    instance = new BeanLocator();
            }
        return instance;
    }

    private BeanLocator()
    {
        try
        {
            applicationContext = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
        }
        catch(Exception e)
        {
            logger.error((new StringBuilder("加载spring配置文件applicationContext.xml失败:")).append(e.getMessage()).toString(), e);
            throw new RuntimeException((new StringBuilder("加载spring配置文件applicationContext.xml失败::")).append(e.getMessage()).toString(), e);
        }
    }

    public Object getBean(String beanName)
    {
        if(applicationContext == null)
            getInstance();
        return applicationContext.getBean(beanName);
    }

    public static Object getBeanInstance(String beanName)
    {
        getInstance();
        return applicationContext.getBean(beanName);
    }

    public ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    private static final Logger logger = Logger.getLogger(BeanLocator.class);
    private static BeanLocator instance = null;
    private static ApplicationContext applicationContext = null;
}