/*
 * 文 件 名：OpLoggerAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：huangliufei/905735
 * 修改时间：2011-8-6
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.common;

import java.util.List;

import com.coship.sdp.permission.OperationType;

/**
 *
 * <日志操作类型数据初始化，继承原来数据的基础上再添加新的类型>
 *
 * @author Huangliufei/905735
 * @version [版本号, Oct 19, 2011]
 * @since [产品/模块版本]
 */
public class LogoOperationType extends OperationType
{

    private static String[] opertTypes = new String[]
    { "上传", "上架", "下架", "首页推荐", "取消首页推荐", "密码重置" };

    private static List<String> logOperTypeList = getOperTypeList();

    static
    {
        for (String operType : opertTypes)
        {
            logOperTypeList.add(operType);
        }
    }

    public static List<String> getLogOperTypeList()
    {
        return logOperTypeList;
    }

}
