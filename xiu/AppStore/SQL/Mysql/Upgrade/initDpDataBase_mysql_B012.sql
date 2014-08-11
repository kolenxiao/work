-- 2012-11-01 906976/zhengxinlian
alter table T_DP_APP_INFO add C_USER_ID varchar(32);

-- 应用管理菜单和按钮资源
-- 2012-11-01 by zhengxinlian

INSERT INTO T_RESOURCE VALUES ('400', '2012-9-3 18:23:41', 'admin', '管理员我的应用管理模块菜单。', 'AdminAppManager', 1, 0, '我的应用', 2, '2012-9-3 18:51:25', '', NULL);
INSERT INTO T_RESOURCE VALUES ('401', '2012-9-3 18:23:41', 'admin', '创建app文档，上传我的应用', 'createAppDocument', 2, 0, '上传普通应用', 1, '2012-9-3 18:51:17', 'dpAppInfo!doApp.action?forward=Add', '400');
INSERT INTO T_RESOURCE VALUES ('402', '2012-9-3 18:23:41', 'admin', '我所有的应用列表', 'allMyAppList', 2, 0, '普通应用列表', 2, '2012-9-3 18:51:17', 'dpAppInfo!doSearchAdminAppList.action', '400');
INSERT INTO T_RESOURCE VALUES ('403', '2012-9-3 18:23:41', 'admin', '隐式应用上传', 'implicationApp', 2, 0, '上传隐式应用', 3, '2012-9-3 18:51:17', '#', '400');
INSERT INTO T_RESOURCE VALUES ('404', '2012-9-3 18:23:41', 'admin', '我所有的应用列表', 'implicationAppList', 2, 0, '隐式应用列表', 4, '2012-9-3 18:51:17', '#', '400');

-- 设置我的应用的权限给管理员
INSERT INTO `t_role_resource` VALUES ('88af5a53b028887013b02484b520051', '1', '400');
INSERT INTO `t_role_resource` VALUES ('8aaf5a53b028887013b04248b79001e', '1', '401');
INSERT INTO `t_role_resource` VALUES ('8a8f5a53b02888701344b0252510055', '1', '402');
INSERT INTO `t_role_resource` VALUES ('88af5a53b02888705513b8b4ec70024', '1', '403');
INSERT INTO `t_role_resource` VALUES ('88af5a53b02888551028b5164d70049', '1', '404');


-- 2012-11-05 906976/zhengxinlian
update T_RESOURCE set C_URL='appRecommend!doSearchRecommendList.action' where C_ID='127';

-- 2012-11-13 906055/wangchenbo
alter table T_APP_CERTIFICATE add C_PRIVATE_KEY_SRC_NAME varchar(128);
alter table T_APP_CERTIFICATE add C_PRIVATE_KEY_SAVE_NAME varchar(128);
alter table T_APP_CERTIFICATE add C_REVOKE_SRC_NAME varchar(128);
alter table T_APP_CERTIFICATE add C_REVOKE_SAVE_NAME varchar(128);
alter table T_APP_CERTIFICATE add C_THIS_UPDATE datetime;
alter table T_APP_CERTIFICATE add C_NEXT_UPDATE datetime;
alter table T_APP_CERTIFICATE drop C_SIGNED_PRIVATEKEY_ID;

alter table T_APP_SIGN change C_PUBLIC_KEY C_APP_CERT_ID varchar(32);
alter table T_APP_SIGN drop C_PRIVATE_KEY;

-- 证书列表
UPDATE t_resource SET C_DESCRIPTION='证书、私钥、吊销证书，应用批量签名' ,C_EN_NAME='CertManager',C_LEVEL=1,C_PARENT_ID=NULL where C_ID='8a8af59c39e8c3640139e8c70ea90003';
-- 证书列表
INSERT INTO t_resource VALUES ('8a8af5af3af8b771013af8bb66ee0005', '2012-11-13 15:46:02', 'admin', '证书、私钥对列表', 'CertList', 2, 0, '证书列表', 1, '2012-11-13 16:24:48', 'certificate!doList.action', '8a8af59c39e8c3640139e8c70ea90003');
UPDATE t_resource SET C_PARENT_ID='8a8af5af3af8b771013af8bb66ee0005' where C_ID='8a8af59c39e8c3640139e8c751ae0005';
UPDATE t_resource SET C_PARENT_ID='8a8af5af3af8b771013af8bb66ee0005' where C_ID='8a8af5d13a01592a013a015cb0500002';
UPDATE t_resource SET C_PARENT_ID='8a8af5af3af8b771013af8bb66ee0005' where C_ID='8a8af5d139fd8af6013a0026eb040004';
UPDATE t_resource SET C_PARENT_ID='8a8af5af3af8b771013af8bb66ee0005' where C_ID='8a8af59c39e8c3640139e8cfadee0054';
-- 批量签名
UPDATE t_resource SET C_NAME='批量签名',C_ORDER_FIELD=3,C_PARENT_ID='8a8af59c39e8c3640139e8c70ea90003' where C_ID='8a8af59c39f283140139f2901fa00002';
UPDATE t_resource SET C_PARENT_ID='8a8af59c39f283140139f2901fa00002' where C_ID='8a8af59c39f756670139f758ce180002';
-- 吊销列表
INSERT INTO t_resource VALUES ('8a8af5af3af8b771013af8bbe7660007', '2012-11-13 15:46:34', 'admin', '吊销列表', 'RevokedCertList', 2, 0, '吊销证书列表', 2, '2012-11-14 19:26:27', 'certificate!doRevokeList.action', '8a8af59c39e8c3640139e8c70ea90003');
INSERT INTO t_resource VALUES ('8a8af5af3afecb7a013afeea17870001', '2012-11-14 20:34:45', 'admin', '', 'addOrEditRevoke', 3, 1, '新增或修改吊销证书列表跳转页面', 1, '2012-11-14 20:34:45', 'certificate!doAddOrModifyRevoke.action', '8a8af5af3af8b771013af8bbe7660007');

INSERT INTO t_role_resource VALUES ('8a8af5af3b0cbd89013b0d2bb8ba0051', '1', '8a8af5af3af8b771013af8bb66ee0005');
INSERT INTO t_role_resource VALUES ('8a8af5af3b0cbd89013b0d2bb80e0047', '1', '8a8af5af3af8b771013af8bbe7660007');
INSERT INTO t_role_resource VALUES ('8a8af5af3b0cbd89013b0d2bb55f001f', '1', '8a8af5af3afecb7a013afeea17870001');

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



