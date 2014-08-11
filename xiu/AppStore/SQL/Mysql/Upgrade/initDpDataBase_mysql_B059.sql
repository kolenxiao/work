-- b059. 
-- 增加应用信息表的字段. 
alter table t_dp_app_info add column c_hand_down_count int default 0 comment '人工增加下载次数';
alter table t_dp_app_info add column c_hand_avg_score double default 0 comment '人工增加平均评分';

-- 修改应用标签关联表的字段. 
alter table `t_app_tag_relation`
modify column `c_app_id`  varchar(32) character set utf8 collate utf8_general_ci null after `c_id`,
add column `c_app_package_name`  varchar(255) null after `c_sort_num`;

-- 增加菜单和权限. 
INSERT INTO t_resource VALUES ('8a8a9f2b43802ba601438032b80e0002', '2014-01-11 15:27:24', 'admin', '缓存管理模块菜单', 'cacheManager', 2, 0, '缓存管理', 4, '2014-01-11 15:27:24', 'systemAdmin!cacheHome.action', '5');
INSERT INTO t_resource VALUES ('8a8a9f2b43802ba6014380352d330005', '2014-01-11 15:27:24', 'admin', '刷新缓存', 'refreshCache', 3, 1, '刷新缓存', 1, '2014-01-11 15:27:24', 'systemAdmin!refreshCache.action', '8a8a9f2b43802ba601438032b80e0002');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8a9f2b43802ba601438035a57d0026', '1', '8a8a9f2b43802ba601438032b80e0002');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8a9f2b43802ba601438035ab280080', '1', '8a8a9f2b43802ba6014380352d330005');