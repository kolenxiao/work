-- V100R003B001  rollback

--删除 方案管理 相关表
drop table t_plan_item_app;
drop table t_plan_item;
drop table t_plan_condition;
drop table t_item;
drop table t_condition;
drop table t_plan;

-- 去掉 设备信息表 渠道id 和 区域编码 字段
alter table t_device_info drop column C_CHANNEL_ID;
alter table t_device_info drop column C_CITY_CODE ;

-- 去掉 用户操作信息表 渠道id 和 区域编码 字段
alter table t_user_operation drop column C_CHANNEL_ID ;
alter table t_user_operation drop column C_CITY_CODE ;

--去掉 所有用户对 方案管理 的权限关联
delete from t_role_resource  where C_RES_ROLE_ID in ('2c9e997544d85e6501450bf54a190018', 
	'2c9e997544d85e65014516f93f1c00b2',
    '2c9e997544d85e6501451aec5bba0148',
    '2c9e997544d85e6501451aee6165014b',
    '2c9e997544d85e6501451aefd096014e',
    '2c9e997544d85e6501451af61bc301e5',
    '2c9e997544d85e6501451af7b3f60311',
    '2c9e997544d85e650145404d4d87040e',
    '2c9e997544d85e650145497a88c804de',
    '2c9e997544d85e6501454982e0cf04e1',
    '2c9e997544d85e6501454990e7b4058d',
    '8a8a901e450687a20145068b0c3f0002',
    '8a8a901e450687a20145068cfb3c0007',
    '8a8a901e450c0c9401450c357e370003',
    '8a8a901e450ceed201450cf020a60002',
    '8a8a901e450ceed201450cf0b1870004',
    '8a8a901e450d3a5e01450d3bda7e0002',
    '8a8a901e450d3a5e01450d3c57850004',
    '8a8a901e450d3a5e01450d3daff20006',
    '8a8a901e4516e9e6014516ebe2ca0003',
    '8a8a901e451b6b5c01451b6dad580002',
    '8a8a901e451b6b5c01451b703b940004',
    '8a8a901e451b6b5c01451b71c6470008',
    '8a8a901e451b6b5c01451b724d57000a',
    '8a8a901e451be28001451be6c82e0002',
    '8a8a901e451be28001451be79b660004',
    '8a8a901e451be28001451be876fb0006',
    '8a8a901e451be28001451be9b89d0009',
    '8a8a901e45254bdd0145254e30670002',
    '8a8a901e4526ab3b014526ace6a00002',
    '8a8a901e4526ab3b014526adcba00004',
    '8a8a901e45448d480145449076820002',
    '8a8a901e4545bf84014545c427650003',
    '8a8a901e4545bf84014545c5aee80005',
    '2c9e998c4588c05f014588c1649d0002');
commit;
--去掉 方案管理 权限和菜单数据
 delete from t_resource  where c_id in ('2c9e997544d85e6501450bf54a190018', 
	'2c9e997544d85e65014516f93f1c00b2',
    '2c9e997544d85e6501451aec5bba0148',
    '2c9e997544d85e6501451aee6165014b',
    '2c9e997544d85e6501451aefd096014e',
    '2c9e997544d85e6501451af61bc301e5',
    '2c9e997544d85e6501451af7b3f60311',
    '2c9e997544d85e650145404d4d87040e',
    '2c9e997544d85e650145497a88c804de',
    '2c9e997544d85e6501454982e0cf04e1',
    '2c9e997544d85e6501454990e7b4058d',
    '8a8a901e450687a20145068b0c3f0002',
    '8a8a901e450687a20145068cfb3c0007',
    '8a8a901e450c0c9401450c357e370003',
    '8a8a901e450ceed201450cf020a60002',
    '8a8a901e450ceed201450cf0b1870004',
    '8a8a901e450d3a5e01450d3bda7e0002',
    '8a8a901e450d3a5e01450d3c57850004',
    '8a8a901e450d3a5e01450d3daff20006',
    '8a8a901e4516e9e6014516ebe2ca0003',
    '8a8a901e451b6b5c01451b6dad580002',
    '8a8a901e451b6b5c01451b703b940004',
    '8a8a901e451b6b5c01451b71c6470008',
    '8a8a901e451b6b5c01451b724d57000a',
    '8a8a901e451be28001451be6c82e0002',
    '8a8a901e451be28001451be79b660004',
    '8a8a901e451be28001451be876fb0006',
    '8a8a901e451be28001451be9b89d0009',
    '8a8a901e45254bdd0145254e30670002',
    '8a8a901e4526ab3b014526ace6a00002',
    '8a8a901e4526ab3b014526adcba00004',
    '8a8a901e45448d480145449076820002',
    '8a8a901e4545bf84014545c427650003',
    '8a8a901e4545bf84014545c5aee80005',
    '2c9e998c4588c05f014588c1649d0002');
	commit;
	
-- 删除 初始化默认方案数据	
delete from t_plan_item_app a
where a.c_plan_id = 1;
commit;

delete from t_plan_item i
where i.c_plan_id = 1;
commit;

delete from t_plan p
where p.c_id = 1;
commit;

-- 删除存储过程 PRO_APPSTORE_COPY_PLAN
DROP PROCEDURE IF EXISTS PRO_APPSTORE_COPY_PLAN;