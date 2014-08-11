-- 2013-02-23 906976/zhengxinlian
-- 应用专题和应用分类增加显示隐藏标记字段
alter table T_DP_TYPE add C_VISIBLE_FLAG int default 1;
alter table T_APP_SUBJECT_TYPE add C_VISIBLE_FLAG int default 1;