-- V100R003B001

-- 创建 方案表
CREATE TABLE `t_plan` (
  `C_ID` varchar(32) NOT NULL,
  `C_DEFAULTED` bit(1) DEFAULT NULL,
  `C_DESCRIPTION` varchar(255) DEFAULT NULL,
  `C_END_TIME` datetime DEFAULT NULL,
  `C_NAME` varchar(255) DEFAULT NULL,
  `C_RFC_CALENDAR` varchar(255) DEFAULT NULL,
  `C_START_TIME` datetime DEFAULT NULL,
  `C_STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '状态，-1=已删除；0=未启用；1=已启用',
  `C_REGULATION` varchar(255) DEFAULT NULL,
  `C_CREATE_TIME` datetime NOT NULL,
  `C_UPDATE_TIME` datetime NOT NULL,
  `C_SORT_NUM` int(11) DEFAULT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建 条件表
CREATE TABLE `t_condition` (
  `C_ID` varchar(32) NOT NULL,
  `C_CODE` varchar(100) NOT NULL,
  `C_DESCRIPTION` varchar(256) DEFAULT NULL,
  `C_NAME` varchar(100) NOT NULL,
  `C_STATUS` int(11) NOT NULL DEFAULT '1' COMMENT '状态，-1=已删除；0=禁用；1=有效',
  `C_VALUE` varchar(500) DEFAULT NULL,
  `C_CREATE_TIME` datetime NOT NULL,
  `C_UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建类项表
CREATE TABLE `t_item` (
  `C_ID` varchar(32) NOT NULL,
  `C_NAME` varchar(100) NOT NULL COMMENT '方案类项名称。',
  `C_ITEM_TYPE` int(11) NOT NULL DEFAULT '4' COMMENT '方案类项类型，1=首页类项；2=专题类项；3=分类类项；4=自定义类项',
  `C_STATUS` int(11) NOT NULL DEFAULT '1' COMMENT '状态，-1=已删除；0=禁用；1=有效',
  `C_DESCRIPTION` varchar(256) DEFAULT NULL,
  `C_CREATE_TIME` datetime NOT NULL,
  `C_UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建 方案-条件 关联表
CREATE TABLE `t_plan_condition` (
  `C_ID` varchar(32) NOT NULL,
  `C_DEFAULTED` tinyint(1) NOT NULL DEFAULT '0',
  `C_SORT_NUM` int(11) NOT NULL DEFAULT '10000',
  `C_STATUS` int(11) NOT NULL DEFAULT '1',
  `C_CONDITION_ID` varchar(32) NOT NULL,
  `C_PLAN_ID` varchar(32) NOT NULL,
  `C_CREATE_TIME` datetime NOT NULL,
  `C_UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`C_ID`),
  KEY `FK2C4A16B05FBD022F1` (`C_CONDITION_ID`),
  KEY `FK2C4A16B018E3E9A5` (`C_PLAN_ID`),
  CONSTRAINT `FK2C4A16B018E3E9A5` FOREIGN KEY (`C_PLAN_ID`) REFERENCES `t_plan` (`C_ID`),
  CONSTRAINT `FK2C4A16B05FBD022F1` FOREIGN KEY (`C_CONDITION_ID`) REFERENCES `t_condition` (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建 方案-类项 关联表
CREATE TABLE `t_plan_item` (
  `C_ID` varchar(32) NOT NULL,
  `C_DESCRIPTION` varchar(255) DEFAULT NULL,
  `C_ITEM_TYPE` int(11) DEFAULT NULL,
  `C_SORT_NUM` int(11) DEFAULT NULL,
  `C_ITEM_ID` varchar(32) DEFAULT NULL,
  `C_PLAN_ID` varchar(32) DEFAULT NULL,
  `C_CREATE_TIME` datetime NOT NULL,
  `C_UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`C_ID`),
  KEY `FKA773DC3EB473E965` (`C_ITEM_ID`),
  KEY `FKA773DC3E18E3E9A5` (`C_PLAN_ID`),
  CONSTRAINT `FKA773DC3E18E3E9A5` FOREIGN KEY (`C_PLAN_ID`) REFERENCES `t_plan` (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建 方案-类项-应用 关联表
CREATE TABLE `t_plan_item_app` (
  `C_ID` varchar(32) NOT NULL,
  `C_APP_PKG_NAME` varchar(255) DEFAULT NULL,
  `C_ITEM_TYPE` int(11) DEFAULT NULL,
  `C_SORT_NUM` int(11) DEFAULT NULL,
  `C_ITEM_ID` varchar(32) DEFAULT NULL,
  `C_PLAN_ID` varchar(32) DEFAULT NULL,
  `C_CREATE_TIME` datetime NOT NULL,
  `C_UPDATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`C_ID`),
  KEY `FKD5BBBA80B473E965` (`C_ITEM_ID`),
  KEY `FKD5BBBA8018E3E9A5` (`C_PLAN_ID`),
  CONSTRAINT `FKD5BBBA8018E3E9A5` FOREIGN KEY (`C_PLAN_ID`) REFERENCES `t_plan` (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 增加 设备信息表 渠道id 和 区域编码 字段
alter table t_device_info add C_CHANNEL_ID varchar(32) comment '渠道id';
alter table t_device_info add C_CITY_CODE varchar(32) comment '区域编码';

-- 增加 用户操作信息表 渠道id 和 区域编码 字段
alter table t_user_operation add C_CHANNEL_ID varchar(32) comment '渠道id';
alter table t_user_operation add C_CITY_CODE varchar(32) comment '区域编码';

-- 方案管理 增加权限
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e450687a20145068b0c3f0002','2014-03-28 10:35:47','admin','','planManage',1,0,'方案管理',1,'2014-03-28 10:36:35','',NULL);
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e997544d85e6501450bf54a190018','2014-03-29 11:49:55','admin','','planList',2,0,'方案列表',2,'2014-04-09 16:31:50','planManage!goList.action','8a8a901e450687a20145068b0c3f0002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e450687a20145068cfb3c0007','2014-03-28 10:37:53','admin','','conditionManage',2,0,'条件管理',1,'2014-03-28 10:37:53','condition!list.action','8a8a901e450687a20145068b0c3f0002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e451b6b5c01451b6dad580002','2014-04-01 11:55:43','admin','','itemManage',2,0,'类项管理',1,'2014-04-01 11:55:43','item!list.action','8a8a901e450687a20145068b0c3f0002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e997544d85e65014516f93f1c00b2','2014-03-31 15:10:04','admin','','deletePlan',3,1,'删除方案',5,'2014-04-10 10:26:18','planManage!deletePlan.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e997544d85e6501451aec5bba0148','2014-04-01 09:34:28','admin','','modifyPlan',3,1,'修改方案',2,'2014-04-10 10:25:44','planManage!modifyPlan.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e997544d85e6501451aee6165014b','2014-04-01 09:36:41','admin','','enabledPlan',3,1,'启用方案',3,'2014-04-10 10:24:18','planManage!enabledPlan.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e997544d85e6501451aefd096014e','2014-04-01 09:38:15','admin','','disablePlan',3,1,'禁用方案',4,'2014-04-10 10:24:02','planManage!disablePlan.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e997544d85e6501451af61bc301e5','2014-04-01 09:45:07','admin','','defaultPlan',3,1,'设置默认方案',6,'2014-04-10 10:23:46','planManage!defaultPlan.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e997544d85e6501451af7b3f60311','2014-04-01 09:46:52','admin','','createPlan',3,1,'创建方案',1,'2014-04-10 10:22:52','planManage!createPlan.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e997544d85e650145404d4d87040e','2014-04-08 15:46:19','admin','','createPlanItemApp',3,1,'新增类型关联应用',1,'2014-04-10 11:15:12','planItemAppManage!addPlanItemApp.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e997544d85e650145497a88c804de','2014-04-10 10:32:18','admin','','addPlanItem',3,1,'新增方案关联类项',1,'2014-04-10 10:40:41','planItemManage!addPlanItem.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e997544d85e6501454982e0cf04e1','2014-04-10 10:41:25','admin','','deletePlanItem',3,1,'取消方案关联类项',1,'2014-04-10 10:41:25','planItemManage!deletePlanItem.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e997544d85e6501454990e7b4058d','2014-04-10 10:56:44','admin','','deletePlanItemApp',3,1,'取消类项关联应用',1,'2014-04-10 11:13:31','planItemAppManage!deletePlanItemApp.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e450c0c9401450c357e370003','2014-03-29 13:00:03','admin','','conditionDelete',3,1,'删除条件',1,'2014-04-01 11:59:13','condition!delete.action','8a8a901e450687a20145068cfb3c0007');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e450ceed201450cf020a60002','2014-03-29 16:23:54','admin','','conditionEnable',3,1,'启用条件',1,'2014-03-29 16:23:54','condition!enable.action','8a8a901e450687a20145068cfb3c0007');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e450ceed201450cf0b1870004','2014-03-29 16:24:31','admin','','conditionDisable',3,1,'禁用条件',1,'2014-03-29 16:24:31','condition!disable.action','8a8a901e450687a20145068cfb3c0007');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e450d3a5e01450d3bda7e0002','2014-03-29 17:46:37','admin','','conditionToAdd',3,1,'进入新增页面',1,'2014-03-29 17:46:37','condition!toAdd.action','8a8a901e450687a20145068cfb3c0007');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e450d3a5e01450d3c57850004','2014-03-29 17:47:09','admin','','conditionToEdit',3,1,'进入编辑页面',1,'2014-03-29 17:47:09','condition!toEdit.action','8a8a901e450687a20145068cfb3c0007');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e450d3a5e01450d3daff20006','2014-03-29 17:48:37','admin','','conditionSave',3,1,'新增条件',1,'2014-03-31 14:54:45','condition!save.action','8a8a901e450687a20145068cfb3c0007');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e4516e9e6014516ebe2ca0003','2014-03-31 14:55:28','admin','','conditionUpdate',3,1,'修改条件',1,'2014-03-31 14:55:28','condition!update.action','8a8a901e450687a20145068cfb3c0007');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e451b6b5c01451b703b940004','2014-04-01 11:58:31','admin','','itemDelete',3,1,'删除类项',1,'2014-04-01 11:59:30','item!delete.action','8a8a901e451b6b5c01451b6dad580002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e451b6b5c01451b71c6470008','2014-04-01 12:00:12','admin','','itemEnable',3,1,'启用类项',1,'2014-04-01 12:00:12','item!enable.action','8a8a901e451b6b5c01451b6dad580002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e451b6b5c01451b724d57000a','2014-04-01 12:00:46','admin','','itemDisable',3,1,'禁用类项',1,'2014-04-01 12:00:46','item!disable.action','8a8a901e451b6b5c01451b6dad580002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e451be28001451be6c82e0002','2014-04-01 14:08:00','admin','','itemToEdit',3,1,'进入编辑页面',1,'2014-04-01 14:08:00','item!toEdit.action','8a8a901e451b6b5c01451b6dad580002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e451be28001451be79b660004','2014-04-01 14:08:54','admin','','itemToAdd',3,1,'进入新增页面',1,'2014-04-01 14:08:54','item!toAdd.action','8a8a901e451b6b5c01451b6dad580002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e451be28001451be876fb0006','2014-04-01 14:09:50','admin','','itemSave',3,1,'新增类项',1,'2014-04-01 14:10:31','item!save.action','8a8a901e451b6b5c01451b6dad580002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e451be28001451be9b89d0009','2014-04-01 14:11:13','admin','','itemEdit',3,1,'修改类项',1,'2014-04-01 14:58:15','item!update.action','8a8a901e451b6b5c01451b6dad580002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e45254bdd0145254e30670002','2014-04-03 09:57:32','admin','','toPlanCondition',3,1,'进入条件关联页面',1,'2014-04-03 09:57:32','condition!listForPlan','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e4526ab3b014526ace6a00002','2014-04-03 16:20:36','admin','','planConditionAdd',3,1,'添加关联条件',1,'2014-04-03 16:20:36','condition!addPlanCondition','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e4526ab3b014526adcba00004','2014-04-03 16:21:35','admin','','planConditionDelete',3,1,'删除关联条件',1,'2014-04-03 16:21:35','condition!deletePlanCondition','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e45448d480145449076820002','2014-04-09 11:38:09','admin','','appToPlan',3,1,'进入应用关联方案页面',1,'2014-04-09 17:16:18','planManage!listForApp.action','126');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e4545bf84014545c427650003','2014-04-09 17:14:14','admin','','appAddPlan',3,1,'app添加关联方案',1,'2014-04-09 17:17:43','planItemAppManage!appAddPlan.action','126');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('8a8a901e4545bf84014545c5aee80005','2014-04-09 17:15:54','admin','','appDeletePlan',3,1,'app取消关联方案',1,'2014-04-09 17:15:54','planItemAppManage!appDeletePlan.action','126');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e998c4588c05f014588c1649d0002','2014-04-22 17:25:46','admin','','copyPlan',3,1,'复制方案',1,'2014-04-22 17:25:46','planManage!copy.action','2c9e997544d85e6501450bf54a190018');
commit;

-- 授予管理员 方案管理全部权限
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7dac0007','1','8a8a901e4526ab3b014526ace6a00002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7dc3000b','1','8a8a901e451b6b5c01451b724d57000a');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7dd9000f','1','8a8a901e451b6b5c01451b6dad580002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7ddf0010','1','2c9e997544d85e650145404d4d87040e');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e24001f','1','8a8a901e450687a20145068cfb3c0007');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e310022','1','2c9e997544d85e65014516f93f1c00b2');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e350023','1','8a8a901e451b6b5c01451b71c6470008');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e390024','1','8a8a901e451be28001451be876fb0006');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e3d0025','1','8a8a901e45448d480145449076820002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e4a002a','1','8a8a901e450d3a5e01450d3bda7e0002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e57002f','1','8a8a901e450c0c9401450c357e370003');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e600033','1','8a8a901e451be28001451be6c82e0002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e630034','1','8a8a901e451be28001451be79b660004');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e7e003e','1','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e890043','1','8a8a901e450ceed201450cf020a60002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e8d0045','1','2c9e997544d85e6501451aefd096014e');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e910048','1','2c9e997544d85e6501454990e7b4058d');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e930049','1','8a8a901e45254bdd0145254e30670002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7e9c004f','1','8a8a901e4516e9e6014516ebe2ca0003');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7ea80057','1','8a8a901e451be28001451be9b89d0009');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7ea90058','1','2c9e997544d85e6501451aec5bba0148');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7eb3005f','1','8a8a901e450d3a5e01450d3c57850004');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7ebe0062','1','2c9e997544d85e6501451af61bc301e5');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7ebf0063','1','8a8a901e450d3a5e01450d3daff20006');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7ec40066','1','2c9e997544d85e6501451aee6165014b');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7ed7006f','1','2c9e997544d85e6501451af7b3f60311');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7ee30072','1','8a8a901e450ceed201450cf0b1870004');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f000085','1','2c9e997544d85e650145497a88c804de');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f07008a','1','8a8a901e4545bf84014545c5aee80005');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f0a008c','1','8a8a901e4545bf84014545c427650003');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f190096','1','8a8a901e4526ab3b014526adcba00004');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f1c0098','1','8a8a901e451b6b5c01451b703b940004');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f21009b','1','2c9e997544d85e6501454982e0cf04e1');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f2c00a2','1','8a8a901e450687a20145068b0c3f0002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('2c9e998c4588c05f014588c1deb70053','1','2c9e998c4588c05f014588c1649d0002');
commit;
-- 初始化默认方案数据
INSERT INTO `t_plan` (`C_ID`,`C_DEFAULTED`,`C_DESCRIPTION`,`C_END_TIME`,`C_NAME`,`C_RFC_CALENDAR`,`C_START_TIME`,`C_STATUS`,`C_REGULATION`,`C_CREATE_TIME`,`C_UPDATE_TIME`,`C_SORT_NUM`) VALUES ('1',1,'默认方案_ 请慎重修改','2050-04-01 00:00:00','默认方案',NULL,'2014-04-01 00:00:00',1,NULL,'2014-04-01 00:00:00','2014-04-01 00:00:00',100001);
commit;
 
-- 插入 t_plan_item 初始化数据
insert into t_plan_item  select t.* from (
-- 生成 t_dp_type 应用类型数据
select 
    replace(uuid(), '-', '') as c_id,
    dt.C_TYPE_NAME as c_description,
    3 as c_item_type,
    dt.c_sort_num,
    dt.c_id as c_item_id,
    '1' as c_plan_id,
    now() as c_create_time,
    now() as c_update_time
from t_dp_type dt
where dt.C_VISIBLE_FLAG = 1
        and dt.C_PARENT_TYPE_CODE in ('GAME_TYPE' , 'APP_TYPE')

union all
-- 生成 t_app_subject_type  应用专题数据
select 
    replace(uuid(), '-', '') as c_id,
    st.C_SUBJECT_NAME as c_description,
    2 as c_item_type,
    10000 c_sort_num,
    st.c_id as c_item_id,
    '1' as c_plan_id,
    now() as c_create_time,
    now() as c_update_time
 from t_app_subject_type st
where st.C_VISIBLE_FLAG = 1

union all
-- 生成 t_app_subject_type  精选页-板块 数据
select 
    replace(uuid(), '-', '') as c_id,
    arp.C_PANEL_NAME as c_description,
    1 as c_item_type,
    arp.C_SORT_NUM,
    arp.c_id as c_item_id,
    '1' as c_plan_id,
    now() as c_create_time,
    now() as c_update_time
 from t_app_recommend_panel arp
where arp.C_STATUS = 1 ) t;
commit;

-- 插入初始化数据
insert into  t_plan_item_app select t.* from (
 
-- 生成 t_dp_type 包含的应用数据
select 
    replace(uuid(), '-', '') as c_id,
	dai.C_APP_FILE_PACKAGE as c_app_pkg_name,
	pi.C_ITEM_TYPE,
	10000 as c_sort_num,
	pi.C_ITEM_ID,
	'1' as c_plan_id,
	now() as c_create_time,
    now() as c_update_time
from t_plan_item pi,t_dp_app_info dai
where pi.C_ITEM_ID = dai.C_TYPE_ID 
and pi.C_ITEM_TYPE = 3
and dai.C_APP_STATUS = 1004
) t;
commit;

	-- 复制方案 存储过程
	DELIMITER $$  
		DROP PROCEDURE IF EXISTS PRO_APPSTORE_COPY_PLAN $$  
		CREATE  PROCEDURE PRO_APPSTORE_COPY_PLAN(
				in_newPlanId varchar(32),
				in_oldPlanId varchar(32)) 
		BEGIN  
		 -- 设置非自动提交
		SET autocommit=0;
		
		    -- 插入plan 数据
			insert into t_plan 
				select 
					in_newPlanId,
					0,
					p.c_description,
					p.c_end_time,
					concat(p.c_name, '_复制方案'),
					p.c_rfc_calendar,
					p.c_start_time,
					0,
					p.c_regulation,
					now(),
					now(),
					p.c_sort_num
				from
					t_plan p
				where
					p.c_id = in_oldPlanId;	
		
			-- 插入 planItem
		insert into t_plan_item
			select 
				replace(uuid(), '-', '') as c_id,
				pi.c_description,
				pi.c_item_type,
				pi.c_sort_num,
				pi.c_item_id,
				in_newPlanId,
				now(),
				now()
			from
				t_plan_item pi
			where
				pi.C_PLAN_ID = in_oldPlanId;
		
			-- 插入 planItemApp
		insert into t_plan_item_app
			select 
				replace(uuid(), '-', '') as c_id,
		    pia.c_app_pkg_name,
		    pia.c_item_type,
		    pia.c_sort_num,
		    pia.c_item_id,
		    in_newPlanId,
		    now(),
		    now()
		from
		    t_plan_item_app pia
		where
		    pia.C_PLAN_ID = in_oldPlanId;
		
		-- 手动提交
		 commit;
		END $$  
	DELIMITER ; 