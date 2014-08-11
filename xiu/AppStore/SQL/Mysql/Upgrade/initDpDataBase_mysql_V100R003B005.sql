-- V100R003B005. 
-- 增加类项表的字段. 
alter table t_item add column C_TYPE_IMG1 varchar(255) comment '获取焦点图标';
alter table t_item add column C_TYPE_IMG2 varchar(255) comment '失去焦点图标';
