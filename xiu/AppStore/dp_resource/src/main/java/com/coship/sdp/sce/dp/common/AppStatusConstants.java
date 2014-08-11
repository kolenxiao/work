/*
 * 文件名称：AppStatusConstants.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-5
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.common;

/**
 * 应用状态常量类
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-5]
 * @since [产品/模块版本]
 */
public class AppStatusConstants
{

    /**
     * 草稿状态
     */
    public static final String APP_STATUS_DRAFT = "1000";

    /**
     * 待审核状态
     */
    public static final String APP_STATUS_PENDING_AUDITE = "1001";

    /**
     * 审核不通过状态
     */
    public static final String APP_STATUS_NOPASS_AUDITED = "1002";

    /**
     * 审核通过
     */
    public static final String APP_STATUS_PASS_AUDITED = "1003";

    /**
     * 已上架
     */
    public static final String APP_STATUS_ONLINE = "1004";

    /**
     * 已下架
     */
    public static final String APP_STATUS_OFFLINE = "1005";

    /**
     * 更新版本
     */
    public static final String APP_STATUS_UPDATE = "1006";
}
