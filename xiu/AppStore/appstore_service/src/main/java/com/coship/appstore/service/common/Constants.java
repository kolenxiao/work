/**
 * 文 件 名：Constants.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd.
 * Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：sunwengang/903820
 * 修改时间：2011-7-25
 * 修改内容：<修改内容>
 */
package com.coship.appstore.service.common;

import com.coship.sdp.utils.SystemConfig;

/**
 *
 * 常量类 定义系统常量.
 */
public class Constants
{
    /**
     * 每隔多少分钟初始化查询接口一次
     */
    public static final Integer INIT_MINUTE = Integer.valueOf(((String) SystemConfig
            .getInstance().getProperty("init.minute")));

}
