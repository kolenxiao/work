-- 2013-09-5 907632/wangzhenghui
-- 数据同步到搜索平台,增加同步下发表
create table t_syn_command (c_id varchar(32) primary key not null comment '主键',
c_app_id varchar(32) comment '应用app主键',
c_init_time datetime comment '初始入库时间',
c_last_time datetime comment '上次同步时间',
c_fail_count int comment '失败次数',
c_status int comment '同步状态:1 待同步 2 已同步 3 待删除 4 已删除',
c_param varchar(50) comment '扩展字段,1: 此次发送 ；其它： 不发送',
KEY `idx_app_id` (`c_app_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

