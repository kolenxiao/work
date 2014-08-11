-- 2012-10-10 906055/wangchenbo
alter table T_DP_STAFF modify C_ADDRESS varchar(255);
alter table T_DP_STAFF modify C_BANK_CARD_NUM varchar(128);
alter table T_DP_STAFF modify C_BANK_ACCOUNT_NAME varchar(128);

-- 2012-10-13 906974/jiangjinfeng
alter table T_ATTACHMENT_FILE modify C_FILE_TYPE varchar(255);

-- 2012-10-19 906976/zhengxinlian
alter table T_DP_NEWS modify C_NEWS_SOURCE_URL varchar(128);

--2012-11-01 906976/zhengxinlian
alter table T_DP_APP_INFO add C_USER_ID varchar(32);

-- 应用管理菜单和按钮资源
-- 2012-11-01 by zhengxinlian

INSERT INTO T_RESOURCE VALUES ('400', '2012-9-3 18:23:41', 'admin', '管理员我的应用管理模块菜单。', 'AdminAppManager', 1, 0, '我的应用', 2, '2012-9-3 18:51:25', '', NULL);
INSERT INTO T_RESOURCE VALUES ('401', '2012-9-3 18:23:41', 'admin', '创建app文档，上传我的应用', 'createAppDocument', 2, 0, '上传普通应用', 1, '2012-9-3 18:51:17', 'dpAppInfo!doApp.action?forward=Add', '400');
INSERT INTO T_RESOURCE VALUES ('402', '2012-9-3 18:23:41', 'admin', '我所有的应用列表', 'allMyAppList', 2, 0, '普通应用列表', 2, '2012-9-3 18:51:17', 'dpAppInfo!doSearchAdminAppList.action', '400');
INSERT INTO T_RESOURCE VALUES ('403', '2012-9-3 18:23:41', 'admin', '隐式应用上传', 'implicationApp', 2, 0, '上传隐式应用', 3, '2012-9-3 18:51:17', '#', '400');
INSERT INTO T_RESOURCE VALUES ('404', '2012-9-3 18:23:41', 'admin', '我所有的应用列表', 'implicationAppList', 2, 0, '隐式应用列表', 4, '2012-9-3 18:51:17', '#', '400');

-- 2012-11-05 906976/zhengxinlian
update T_RESOURCE set C_URL='appRecommend!doSearchRecommendList.action' where C_ID='127';

-- 2012-11-15 906974/jiangjinfeng
create table T_APP_STORE_CLIENT (
    C_ID varchar(32) not null,
    C_APK_FILE_SAVE_PATH varchar(255),
    C_APP_NAME varchar(255),
    C_CREATE_TIME datetime,
    C_STATUS varchar(255),
    C_SYSTEM int,
    C_VERSION_CODE int,
    primary key (C_ID)
) type=InnoDB;

INSERT INTO `t_resource` VALUES ('8a8af5a53afc7b0b013afc7ecf860002', '2012-11-14 09:18:20', 'admin', '应用自动部署管理', 'AutoInstallAppMgr', '1', '0', '自动部署管理', '8', '2012-11-14 09:18:20', '', null);
INSERT INTO `t_resource` VALUES ('8a8af5a53afc7b0b013afc81107a0004', '2012-11-14 09:20:47', 'admin', '客户端列表', 'appStoreClientList', '2', '0', '客户端列表', '1', '2012-11-14 09:20:47', 'appStoreClient!doList.action', '8a8af5a53afc7b0b013afc7ecf860002');
INSERT INTO `t_resource` VALUES ('8a8af5a53afc7b0b013afc8498230006', '2012-11-14 09:24:39', 'admin', '删除应用商店客户端', 'doDeleteAppStoreClient', '3', '1', '删除客户端', '1', '2012-11-14 09:24:39', 'appStoreClient!doDelete.action', '8a8af5a53afc7b0b013afc81107a0004');
INSERT INTO `t_resource` VALUES ('8a8af5a53afd852b013afd8b1d920002', '2012-11-14 14:11:23', 'admin', '保存新增客户端', 'doAddAppClient', '3', '1', '保存新增客户端', '1', '2012-11-14 14:11:23', 'appStoreClient!doAdd.action', '8a8af5a53afc7b0b013afc81107a0004');
INSERT INTO `t_resource` VALUES ('8a8af5a53afd8f4c013afd9119c20002', '2012-11-14 14:17:55', 'admin', '保存修改', 'doModifyAppClient', '3', '1', '保存修改客户端', '1', '2012-11-14 14:17:55', 'appStoreClient!doModify.action', '8a8af5a53afc7b0b013afc81107a0004');
INSERT INTO `t_resource` VALUES ('8a8af5a53b028887013b028ad42f0002', '2012-11-15 13:29:10', 'admin', '上下架应用客户端', 'doOnOrOffAppClient', '3', '1', '上下架应用客户端', '1', '2012-11-15 13:29:10', 'appStoreClient!doOnOrOffLine.action', '8a8af5a53afc7b0b013afc81107a0004');

INSERT INTO `t_role_resource` VALUES ('8a8af5a53b028887013b028b52030051', '1', '8a8af5a53afc7b0b013afc7ecf860002');
INSERT INTO `t_role_resource` VALUES ('8a8af5a53b028887013b028b4e79001e', '1', '8a8af5a53afc7b0b013afc81107a0004');
INSERT INTO `t_role_resource` VALUES ('8a8af5a53b028887013b028b52510055', '1', '8a8af5a53afc7b0b013afc8498230006');
INSERT INTO `t_role_resource` VALUES ('8a8af5a53b028887013b028b4ec70024', '1', '8a8af5a53afd852b013afd8b1d920002');
INSERT INTO `t_role_resource` VALUES ('8a8af5a53b028887013b028b51670049', '1', '8a8af5a53afd8f4c013afd9119c20002');
INSERT INTO `t_role_resource` VALUES ('8a8af5a53b028887013b028b4eb70022', '1', '8a8af5a53b028887013b028ad42f0002');

UPDATE T_DP_TYPE SET C_TYPE_CODE='1' where C_ID='36';
UPDATE T_DP_TYPE SET C_TYPE_CODE='3' where C_ID='37';
UPDATE T_DP_TYPE SET C_TYPE_CODE='4' where C_ID='38';
UPDATE T_DP_TYPE SET C_TYPE_CODE='5' where C_ID='39';
UPDATE T_DP_TYPE SET C_TYPE_CODE='7' where C_ID='40';
UPDATE T_DP_TYPE SET C_TYPE_CODE='8' where C_ID='41';
UPDATE T_DP_TYPE SET C_TYPE_CODE='9' where C_ID='42';
UPDATE T_DP_TYPE SET C_TYPE_CODE='11' where C_ID='43';
UPDATE T_DP_TYPE SET C_TYPE_CODE='14' where C_ID='44';
UPDATE T_DP_TYPE SET C_TYPE_CODE='16' where C_ID='45';


INSERT INTO T_DP_TYPE VALUES ('63', '2011-10-14 12:56:42', 'admin' , 'ANDROID_TYPE', '6', 'Android版本类型', '1app_sort_img_20.png', 'app_sort_img_20.png','Android 2.0.1', '2011-10-14 12:56:47');
INSERT INTO T_DP_TYPE VALUES ('64', '2011-10-14 12:56:42', 'admin' , 'ANDROID_TYPE', '10', 'Android版本类型', '1app_sort_img_20.png', 'app_sort_img_20.png','Android 2.3.3', '2011-10-14 12:56:47');
INSERT INTO T_DP_TYPE VALUES ('65', '2011-10-14 12:56:42', 'admin' , 'ANDROID_TYPE', '12', 'Android版本类型', '1app_sort_img_20.png', 'app_sort_img_20.png','Android 3.1', '2011-10-14 12:56:47');
INSERT INTO T_DP_TYPE VALUES ('66', '2011-10-14 12:56:42', 'admin' , 'ANDROID_TYPE', '13', 'Android版本类型', '1app_sort_img_20.png', 'app_sort_img_20.png','Android 3.2', '2011-10-14 12:56:47');
INSERT INTO T_DP_TYPE VALUES ('67', '2011-10-14 12:56:42', 'admin' , 'ANDROID_TYPE', '15', 'Android版本类型', '1app_sort_img_20.png', 'app_sort_img_20.png','Android 4.0.3', '2011-10-14 12:56:47');

-- 2012-11-15 906976/zhengxinlian
-- 应用推荐增加排序字段
alter table T_APP_RECOMMEND add C_SORT double default 0;

-- 应用信息和应用专题关联的第三张表
CREATE TABLE `t_subject_appinfo_relation` (
  `C_ID` varchar(32) NOT NULL,
  `C_SORT` double default NULL,
  `C_APP_ID` varchar(32) default NULL,
  `C_SUBJECT_ID` varchar(32) default '0',
  PRIMARY KEY  (`C_ID`),
  KEY `C_APP_ID` (`C_APP_ID`),
  KEY `C_SUBJECT_ID` (`C_SUBJECT_ID`),
  CONSTRAINT `t_subject_appinfo_relation_ibfk_1` FOREIGN KEY (`C_APP_ID`) REFERENCES `t_dp_app_info` (`C_ID`),
  CONSTRAINT `t_subject_appinfo_relation_ibfk_2` FOREIGN KEY (`C_SUBJECT_ID`) REFERENCES `t_app_subject_type` (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


