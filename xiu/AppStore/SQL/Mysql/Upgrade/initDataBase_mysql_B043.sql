/**
 * 2013-06-09 Alan Zheng
 * 添加游戏分类
 */
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11000', '2013-06-07 19:25:22', 'admin', 'ROOT_TYPE', 'GAME_TYPE', '分类描述', 'app_sort_img_1.png', 'app_sort_img_on_1.png', '游戏分类', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11001', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'PUZZLE_GAME_TYPE', '益智类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '益智', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11002', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'RACING_GAME_TYPE', '赛车竞技类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '赛车竞技', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11003', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'ROLE_GAME_TYPE', '角色扮演类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '角色扮演', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11004', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'SPORTS_GAME_TYPE', '体育运动类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '体育运动', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11005', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'SHOOT_GAME_TYPE', '射击类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '射击', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11006', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'CHESS_GAME_TYPE', '棋牌类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '棋牌桌游', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11007', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'ACTION_GAME_TYPE', '动作冒险类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '动作冒险', '2013-06-07 19:25:22');

/**
 * 2013-06-27 By Alan Zheng
 * 添加推荐分裂脚本，给推荐分类增加一个和分类关联的字段c_type_id
 */
alter table T_APP_RECOMMEND
		add C_TYPE_ID varchar(32),
		add index FK7D0000148C341342 (C_TYPE_ID),
        add constraint FK7D0000148C341342
        foreign key (C_TYPE_ID)
        references T_DP_TYPE (C_ID);
        
        

-- 2013-07-01 907632/wangzhenghui
-- t_app_recommend 新增字段初始化默认值
update t_app_recommend s set c_type_id =(select c_type_id from t_dp_app_info tt where tt.c_id=s.c_app_id);
commit;

-- 2013-07-08 907632/wangzhenghui
-- 添加应用分类推荐
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb1cfe9bb0002','2013-07-06 10:29:20','admin','应用推荐分类','appTypeRecommendMgr','2','0','应用分类推荐','1','2013-07-06 10:29:56','dpType!doSearchRecommendList.action','20');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb1d4cbd7006a','2013-07-06 10:34:40','admin','取消应用推荐分类','cancleTypeRecommend','3','1','取消应用分类推荐','1','2013-07-06 10:34:40','dpType!doCommend.action?commendFlag=0','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb204f750006d','2013-07-06 11:27:17','admin','','addTypeRecommend','3','1','新增应用分类推荐','2','2013-07-06 11:27:30','dpType!doCommend.action?commendFlag=1','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb20724e900d3','2013-07-06 11:29:40','admin','新增应用推荐分类查询','queryUncludeTypeRecommed','3','1','新增应用分类推荐查询','1','2013-07-06 11:29:40','dpType!doSearchUncludeTypeRecommend.action','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb219e4f30344','2013-07-06 11:50:08','admin','应用推荐查询','searchAppTypeRecommendList','3','1','应用推荐查询','1','2013-07-06 11:52:48','appTypeRecommend!doSearchAppTypeRecommendList.action','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb21d5c9203af','2013-07-06 11:53:56','admin','新增应用推荐查询','searchAppInTypeRecommend','3','1','新增应用推荐查询','1','2013-07-06 11:53:56','dpAppInfo!toAddTypeRecommentList','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb21df20d03b1','2013-07-06 11:54:34','admin','','cancleAppInTypeRecommend','3','1','取消应用推荐','1','2013-07-06 11:56:31','appTypeRecommend!doBatchCommend.action','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb23e2241041f','2013-07-06 12:29:43','admin','新增应用推荐','addAppToTypeRecommend','3','1','新增应用推荐','1','2013-07-06 12:30:42','appTypeRecommend!doCommend.action','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb244c90304ba','2013-07-06 12:36:59','admin','','sortAppInTypeRecommend','3','1','应用排序','1','2013-07-06 13:57:40','appTypeRecommend!doRecommendSort.action','8a8ac8123fb1cb9a013fb1cfe9bb0002');
commit;

-- 2013-07-08 907632/wangzhenghui
-- 应用分类功能关联超级管理员角色
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28eeea5000e','1','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28eeecf000f','1','8a8ac8123fb1cb9a013fb244c90304ba');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28eef9f001e','1','8a8ac8123fb1cb9a013fb1d4cbd7006a');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef0250028','1','8a8ac8123fb1cb9a013fb219e4f30344');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef0bb0036','1','8a8ac8123fb1cb9a013fb21d5c9203af');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef156003e','1','8a8ac8123fb1cb9a013fb21df20d03b1');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef180003f','1','8a8ac8123fb1cb9a013fb204f750006d');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef2750052','1','8a8ac8123fb1cb9a013fb23e2241041f');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef2d30057','1','8a8ac8123fb1cb9a013fb20724e900d3');
commit;

-- 2013-07-08 907632/wangzhenghui
-- 应用分类推荐表
CREATE TABLE `T_APP_TYPE_RECOMMEND` (
  `C_ID` varchar(32) NOT NULL,
  `C_APP_RECOMMENDCTIME` datetime default NULL,
  `C_CREATE_USER` varchar(255) default NULL,
  `C_APP_ID` varchar(32) default NULL,
  `C_TYPE_ID` varchar(32) default NULL,
  `C_SORT` double default '0',
  PRIMARY KEY  (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
