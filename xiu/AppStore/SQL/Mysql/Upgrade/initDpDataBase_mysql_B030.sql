-- 2013-02-26 906976/zhengxinlian
-- 隐式应用表新增
create table T_IMPLICIT_APP (
    C_ID varchar(32) not null,
    C_APP_NAME varchar(255),
    C_VERSION_CODE int,
    C_FILE_NAME varchar(255),
    C_APK_FILE_SAVE_PATH varchar(255),
    C_SYSTEM varchar(255),
    C_STATUS varchar(255),
    C_CREATE_TIME datetime,
    C_DEPLOY_TIME datetime,
    C_SYSTEM_FLAG varchar(255),
    C_APP_FILE_PACKAGE varchar(255),
    C_TEMINAL_NUM varchar(255),
    C_IMPLICIT_TYPE varchar(255),
    C_DESCRIPTION varchar(255),
    primary key (C_ID)
) type=InnoDB;

DELETE FROM T_RESOURCE WHERE C_ID = '403';
DELETE FROM T_RESOURCE WHERE C_ID = '404';

/**
 * zhengxinlian/906976 2013-3-1
 * 隐式应用管理
 */
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af59c39aaa3640139e8c70ea90003', '2013-3-1 18:23:41', 'admin', '隐式应用模块', 'ImplicitAppManage', 1, 0, '隐式应用管理', 8, '2013-3-1 18:23:41', '', NULL);
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af59c39aaa3640139e8c70ea90004', '2012-3-1 18:23:41', 'admin', '上传隐式应用', 'implicitAppAdd', 2, 0, '上传隐式应用', 1, '2013-3-1 18:23:41', 'implicit!toAdd.action', '8a8af59c39aaa3640139e8c70ea90003');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af59c39aaa3640139e8c70ea90005', '2012-3-1 18:23:41', 'admin', '隐式应用列表', 'implicitAppList', 2, 0, '隐式应用列表', 2, '2013-3-1 18:23:41', 'implicit!list.action', '8a8af59c39aaa3640139e8c70ea90003');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af59c39aaa3640139e8c70ea90006', '2012-3-1 18:23:41', 'admin', '删除隐式应用', 'deleteImplicitApp', 3, 1, '删除隐式应用', 1, '2013-3-1 18:23:41', 'implicit!doDelete.action', '8a8af59c39aaa3640139e8c70ea90005');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af59c39aaa3640139e8c70ea90007', '2012-3-1 18:23:41', 'admin', '卸载隐式应用', 'uninstallImplicitApp', 3, 2, '卸载隐式应用', 2, '2013-3-1 18:23:41', 'implicit!doUninstall.action', '8a8af59c39aaa3640139e8c70ea90005');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af59c39aaa3640139e8c70ea90008', '2012-3-1 18:23:41', 'admin', '隐式应用启用禁用', 'doOnOrOffLineImplicit', 3, 3, '卸载隐式应用', 2, '2013-3-1 18:23:41', 'implicit!doOnOrOffLine.action', '8a8af59c39aaa3640139e8c70ea90005');


-- 隐式应用关联角色
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004c', '1', '8a8af59c39aaa3640139e8c70ea90003');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004d', '1', '8a8af59c39aaa3640139e8c70ea90004');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004e', '1', '8a8af59c39aaa3640139e8c70ea90005');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004f', '1', '8a8af59c39aaa3640139e8c70ea90006');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004g', '1', '8a8af59c39aaa3640139e8c70ea90007');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004h', '1', '8a8af59c39aaa3640139e8c70ea90008');


-- 更新菜单顺序
UPDATE T_RESOURCE SET C_ORDER_FIELD=8 WHERE C_ID='8a8af59c39aaa3640139e8c70ea90003';
UPDATE T_RESOURCE SET C_ORDER_FIELD=9 WHERE C_ID='8';
UPDATE T_RESOURCE SET C_ORDER_FIELD=10 WHERE C_ID='5';
