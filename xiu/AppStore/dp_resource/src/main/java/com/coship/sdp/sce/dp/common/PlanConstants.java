package com.coship.sdp.sce.dp.common;


/**
 *
 * 定义方案常量.
 */
public class PlanConstants
{

    /**
     * 方案状态
     */
    public static final int PLAN_STATUS_DISABLED = 0;//未启用
    public static final int PLAN_STATUS_ENABLED = 1;//已启用
    public static final int PLAN_STATUS_DELETED = -1;//已删除
    
    /**
     * 类项Type
     */
    public static final int ITEM_TYPE_PANEL = 1;//首页
    public static final int ITEM_TYPE_SUBJECT = 2;//专题
    public static final int ITEM_TYPE_DPTYPE = 3;//固定分类
    public static final int ITEM_TYPE_SELFTYPE = 4;//自定义的分类

    /**
     * 条件.
     */
    public static final int CONDITION_STATUS_DISABLED = 0;// 禁用条件.
    public static final int CONDITION_STATUS_ENABLED = 1;// 启用条件.

}
