
-- 2013-05-08 906976/zhengxinlian
-- 新增存储过程，根据ID查找对应的附件，和根据包名分类对应的星数
CREATE PROCEDURE `QUERY_ATTA_FILE`(IN ID VARCHAR(32))
BEGIN
	SELECT C_FILE_SAVE_NAME,C_FILE_SIZE,C_FILE_DESC FROM t_attachment_file WHERE C_DPAPPINFO_ID =ID;
END;

CREATE PROCEDURE `COUNT_STAR_BY_PACKAGE`(IN PACKAGE VARCHAR(200))
BEGIN
    select appComment.C_SCORE,count(appComment.C_SCORE) from T_APP_COMMENT_INFO appComment ,T_DP_APP_INFO appInfo  where appInfo.C_APP_FILE_PACKAGE=PACKAGE and appInfo.C_ID = appComment.C_APP_ID group by appComment.C_SCORE;
END;

-- 2013-05-20 906974/jiangjinfeng
create table T_DEVICE_INFO (
    C_ID varchar(32) not null,
    C_APP_STORE_CLIENT_VERSION varchar(255),
    C_APP_STORE_CLIENT_INSTALL_DATE datetime,
    C_DEVICE_IP varchar(255),
    C_DEVICE_MAC varchar(255),
    C_DEVICE_SERIAL_NO varchar(255),
    C_DEVICE_TYPE varchar(255),
    C_OS_VERSION integer not null,
    primary key (C_ID)
) type=InnoDB;

INSERT INTO `t_resource` VALUES ('8a8af5a53afc7b0b013afc81107b0004', '2012-11-14 09:20:47', 'admin', '终端设备列表', 'deviceInfoList', 2, 0, '终端设备列表', 2, '2012-11-14 09:20:47', 'deviceInfo!doList.action', '8a8af5a53afc7b0b013afc7ecf860002');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5a53b028887013b028b4e79001f', '1', '8a8af5a53afc7b0b013afc81107b0004');

UPDATE T_RESOURCE SET C_PARENT_ID='8a8af5a53afc7b0b013afc81107a0004' WHERE C_ID='8a8af5a53afd852b013afd8b1d920002';
UPDATE T_RESOURCE SET C_ORDER_FIELD='1' WHERE C_ID='8a8af5a53afc7b0b013afc81107a0004';
