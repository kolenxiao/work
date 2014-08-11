-- B056 t_dp_type表增加字段. 
alter table t_dp_type add column c_sort_num int(4) DEFAULT 0 comment '分类排序';

-- 将t_dp_type中现有数据的c_sort_num字段的值全部设置为0. 
update t_dp_type set c_sort_num=0;

-- 初始化菜单和权限. 
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8ac89f4297588201429770b2c20002', '2013-11-27 10:43:34', 'admin', '分类排序', 'sortType', 3, 1, '分类排序', 0, '2013-11-27 10:43:34', 'dpType!doSort.action', '38');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8ac89f4297715f01429773ec05003f', '1', '8a8ac89f4297588201429770b2c20002');