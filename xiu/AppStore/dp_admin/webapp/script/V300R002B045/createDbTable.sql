/*
MySQL Data Transfer
Source Host: 10.10.91.20
Source Database: uspdb
Target Host: 10.10.91.20
Target Database: uspdb
Date: 2011-8-1 9:43:36
*/

-- ----------------------------
-- drop tables
-- ----------------------------

drop table if exists T_ATTACHMENT_FILE;

drop table if exists T_DATA_DICT;

drop table if exists T_DP_APP_INFO;

drop table if exists T_DP_AUDIT_RECORD;

drop table if exists T_DP_DOWNLOAD_INFO;

drop table if exists T_DP_NEWS;

drop table if exists T_DP_STAFF;

drop table if exists T_DP_TYPE;

drop table if exists T_APP_TYPE;

drop table if exists T_MY_APP;

drop table if exists T_MY_FAVORITE;

drop table if exists T_OP_LOGGER;

drop table if exists T_RESOURCE;

drop table if exists T_ROLE;

drop table if exists T_ROLE_RESOURCE;

drop table if exists T_USER;

drop table if exists T_USER_ROLE;

drop table if exists T_USP_SERV_AUDIT;

drop table if exists T_USP_SERV_BIND;

drop table if exists T_USP_SERV_GROUP;

drop table if exists T_USP_SERV_OPERATION;

drop table if exists T_USP_SERV_PARAMETERS;

drop table if exists T_USP_SERVICE;


SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_app_type
-- ----------------------------
CREATE TABLE `T_APP_TYPE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_USER` varchar(64) DEFAULT NULL,
  `TYPE_DESC` varchar(64) DEFAULT NULL,
  `TYPE_IMG1` varchar(255) DEFAULT NULL,
  `TYPE_IMG2` varchar(255) DEFAULT NULL,
  `TYPE_NAME` varchar(32) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_ATTACHMENT_FILE
-- ----------------------------
CREATE TABLE `T_ATTACHMENT_FILE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` datetime DEFAULT NULL,
  `FILE_DESC` longtext,
  `FILE_NAME` varchar(128) DEFAULT NULL,
  `FILE_SAVE_NAME` varchar(128) DEFAULT NULL,
  `FILE_SIZE` bigint(20) NOT NULL,
  `FILE_TYPE` varchar(32) DEFAULT NULL,
  `ATTACH_FILE_STATUS` varchar(12) DEFAULT NULL,
  `DP_APP_INFO_ID` bigint(20) DEFAULT NULL,
  `USP_SERV_ID` bigint(20) DEFAULT NULL,
  `DP_STAFF_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK49154D2D4980F6B1` (`DP_APP_INFO_ID`),
  KEY `FK49154D2D2DCA95CB` (`USP_SERV_ID`),
  KEY `FK49154D2DED6DBB14` (`DP_STAFF_ID`),
  CONSTRAINT `FK49154D2D2DCA95CB` FOREIGN KEY (`USP_SERV_ID`) REFERENCES `T_USP_SERVICE` (`ID`),
  CONSTRAINT `FK49154D2D4980F6B1` FOREIGN KEY (`DP_APP_INFO_ID`) REFERENCES `T_DP_APP_INFO` (`ID`),
  CONSTRAINT `FK49154D2DED6DBB14` FOREIGN KEY (`DP_STAFF_ID`) REFERENCES `T_DP_STAFF` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5962 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_DATA_DICT
-- ----------------------------
CREATE TABLE `T_DATA_DICT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CATEGORY` varchar(128) DEFAULT NULL,
  `DATA_KEY` varchar(64) DEFAULT NULL,
  `DATA_VALUE` varchar(128) DEFAULT NULL,
  `ENABLE` int(11) DEFAULT NULL,
  `ORDER_ID` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_DP_APP_INFO
-- ----------------------------
CREATE TABLE `T_DP_APP_INFO` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `APP_DESC` longtext,
  `APP_DOC` longtext,
  `APP_FILE` longtext,
  `APP_IMG1` longtext,
  `APP_IMG2` longtext,
  `APP_IMG3` longtext,
  `APP_LOGO` longtext,
  `APP_NAME` varchar(64) DEFAULT NULL,
  `APP_ORDER` int(11) DEFAULT NULL,
  `APP_STATUS` varchar(4) DEFAULT NULL,
  `APP_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `STAFF_ID` bigint(20) DEFAULT NULL,
  `ATTACH_GROUP_ID` bigint(20) DEFAULT NULL,
  `APP_ON_LINE_STATUS` int(11) DEFAULT NULL,
  `APP_ID` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `DEVELOPER` varchar(255) DEFAULT NULL,
  `LANGUAGE` varchar(255) DEFAULT NULL,
  `LEVEL` int(11) NOT NULL,
  `PRICE` double NOT NULL,
  `SIZE` bigint(20) NOT NULL,
  `SYSTEM` varchar(255) DEFAULT NULL,
  `VERSION` varchar(255) DEFAULT NULL,
  `TYPE_ID` bigint(20) DEFAULT NULL,
  `APP_LOGO2` longtext,
  `APP_LOGO3` longtext,
  `APP_PATH` tinyblob,
  PRIMARY KEY (`ID`),
  KEY `FK7D023714DE9138C1` (`STAFF_ID`),
  KEY `FK7D023714A92D7F1E` (`TYPE_ID`),
  CONSTRAINT `FK7D023714A92D7F1E` FOREIGN KEY (`TYPE_ID`) REFERENCES `T_APP_TYPE` (`ID`),
  CONSTRAINT `FK7D023714DE9138C1` FOREIGN KEY (`STAFF_ID`) REFERENCES `T_DP_STAFF` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=629 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_DP_AUDIT_RECORD
-- ----------------------------
CREATE TABLE `T_DP_AUDIT_RECORD` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AUDIT_DATE` datetime DEFAULT NULL,
  `AUDIT_FLAG` varchar(32) DEFAULT NULL,
  `AUDIT_OPTION` longtext,
  `AUDIT_RECORD_ID` int(11) DEFAULT NULL,
  `AUDIT_RESULT` varchar(4) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKF950F43D2C5320C9` (`USER_ID`),
  CONSTRAINT `FKF950F43D2C5320C9` FOREIGN KEY (`USER_ID`) REFERENCES `T_USER` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=453 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_DP_DOWNLOAD_INFO
-- ----------------------------
CREATE TABLE `T_DP_DOWNLOAD_INFO` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CTIME` datetime DEFAULT NULL,
  `DOWNLOAD_DESC` longtext,
  `DOWNLOAD_NAME` varchar(200) DEFAULT NULL,
  `ATTACH_GROUP_ID` bigint(20) DEFAULT NULL,
  `TYPE_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `CREATE_USER` varchar(64) DEFAULT NULL,
  `SHOW_INDEX` varchar(2) DEFAULT NULL,
  `ATTACH_FILE_ID` bigint(20) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKFCE7FC1DA06A6970` (`ATTACH_GROUP_ID`),
  KEY `FKFCE7FC1D2C5320C9` (`USER_ID`),
  KEY `FKFCE7FC1DA92D7F1E` (`TYPE_ID`),
  KEY `FKFCE7FC1DD72FFCAC` (`ATTACH_FILE_ID`),
  CONSTRAINT `FKFCE7FC1DA92D7F1E` FOREIGN KEY (`TYPE_ID`) REFERENCES `T_DP_TYPE` (`ID`),
  CONSTRAINT `FKFCE7FC1DD72FFCAC` FOREIGN KEY (`ATTACH_FILE_ID`) REFERENCES `T_ATTACHMENT_FILE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_DP_NEWS
-- ----------------------------
CREATE TABLE `T_DP_NEWS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_USER` varchar(64) DEFAULT NULL,
  `NEWS_CONTENT` longtext,
  `NEWS_CREATE_TIME` datetime DEFAULT NULL,
  `NEWS_ORDER_ID` int(11) NOT NULL,
  `NEWS_SOURCE` varchar(128) DEFAULT NULL,
  `NEWS_SOURCE_URL` varchar(64) DEFAULT NULL,
  `NEWS_STSTUS` varchar(8) DEFAULT NULL,
  `NEWS_SUMMARY` longtext,
  `NEWS_TITLE` varchar(128) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `TYPE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKCE0EBDBBA92D7F1E` (`TYPE_ID`),
  CONSTRAINT `FKCE0EBDBBA92D7F1E` FOREIGN KEY (`TYPE_ID`) REFERENCES `T_DP_TYPE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_dp_staff
-- ----------------------------
CREATE TABLE `T_DP_STAFF` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(64) DEFAULT NULL,
  `BANK_ACCOUNT_NAME` varchar(32) DEFAULT NULL,
  `BANK_CARD_NUM` varchar(19) DEFAULT NULL,
  `BANK_NAME` varchar(32) DEFAULT NULL,
  `BEGIN_DP_STAFF_TIME` datetime DEFAULT NULL,
  `EMAIL` varchar(64) DEFAULT NULL,
  `HEAD_ICON` varchar(64) DEFAULT NULL,
  `IDENTITY_CARD` varchar(30) DEFAULT NULL,
  `IDENTITY_IMG` varchar(128) DEFAULT NULL,
  `LOCAL_ADD` varchar(64) DEFAULT NULL,
  `MOBILE_NUM` varchar(11) DEFAULT NULL,
  `NICK_NAME` varchar(20) DEFAULT NULL,
  `PASS_WORD` varchar(64) NOT NULL,
  `PORSTAL_CODE` varchar(6) DEFAULT NULL,
  `REAL_NAME` varchar(40) DEFAULT NULL,
  `STAFF_STATUS` varchar(4) DEFAULT NULL,
  `UPDATE_DP_STAFF_TIME` datetime DEFAULT NULL,
  `USER_NAME` varchar(20) NOT NULL,
  `IDENTITY_TYPE` varchar(16) DEFAULT NULL,
  `TEL_NUM` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_dp_type
-- ----------------------------
CREATE TABLE `T_DP_TYPE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_USER` varchar(64) DEFAULT NULL,
  `TYPE_DESC` varchar(64) DEFAULT NULL,
  `TYPE_FLAG` int(11) DEFAULT NULL,
  `TYPE_NAME` varchar(32) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_my_app
-- ----------------------------
CREATE TABLE `T_MY_APP` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADD_TIME` datetime DEFAULT NULL,
  `APP_ID` bigint(20) DEFAULT NULL,
  `UID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_my_favorite
-- ----------------------------
CREATE TABLE `T_MY_FAVORITE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `APP_ID` bigint(20) DEFAULT NULL,
  `FAVORITE_TIME` datetime DEFAULT NULL,
  `UID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_op_logger
-- ----------------------------
CREATE TABLE `T_OP_LOGGER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BUSINESS_NAME` varchar(32) DEFAULT NULL,
  `CLIENT_IP` varchar(15) DEFAULT NULL,
  `DESCRIPTION` varchar(128) DEFAULT NULL,
  `OPERATION_DATE` datetime DEFAULT NULL,
  `OPERATOR_NAME` varchar(16) DEFAULT NULL,
  `OPERATION_TYPE` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8493 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
CREATE TABLE `T_RESOURCE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_USER` varchar(64) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `EN_NAME` varchar(50) NOT NULL,
  `LEVEL` int(11) DEFAULT NULL,
  `MENU_BUTTON` int(11) DEFAULT NULL,
  `NAME` varchar(200) DEFAULT NULL,
  `ORDER_FIELD` int(11) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EN_NAME` (`EN_NAME`),
  KEY `FK47D003992B08AC4D` (`PARENT_ID`),
  CONSTRAINT `FK47D003992B08AC4D` FOREIGN KEY (`PARENT_ID`) REFERENCES `T_RESOURCE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
CREATE TABLE `T_ROLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_USER` varchar(64) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `NAME` varchar(64) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role_resource
-- ----------------------------
CREATE TABLE `T_ROLE_RESOURCE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_RES_ID` bigint(20) DEFAULT NULL,
  `RES_ROLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK3E7E410CD7D25062` (`RES_ROLE_ID`),
  KEY `FK3E7E410C61396868` (`ROLE_RES_ID`),
  CONSTRAINT `FK3E7E410C61396868` FOREIGN KEY (`ROLE_RES_ID`) REFERENCES `T_ROLE` (`ID`),
  CONSTRAINT `FK3E7E410CD7D25062` FOREIGN KEY (`RES_ROLE_ID`) REFERENCES `T_RESOURCE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2761 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `T_USER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime DEFAULT NULL,
  `CREATED_USER` varchar(64) DEFAULT NULL,
  `EMAIL` varchar(64) DEFAULT NULL,
  `PASSWORD` varchar(64) DEFAULT NULL,
  `REAL_NAME` varchar(64) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT NULL,
  `TELEPHONE` varchar(20) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `USER_NAME` varchar(64) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_NAME` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
CREATE TABLE `T_USER_ROLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ROLE_ID` bigint(20) DEFAULT NULL,
  `ROLE_USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK3E62963FE2195FCA` (`USER_ROLE_ID`),
  KEY `FK3E62963F432206AB` (`ROLE_USER_ID`),
  CONSTRAINT `FK3E62963F432206AB` FOREIGN KEY (`ROLE_USER_ID`) REFERENCES `T_ROLE` (`ID`),
  CONSTRAINT `FK3E62963FE2195FCA` FOREIGN KEY (`USER_ROLE_ID`) REFERENCES `T_USER` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_usp_serv_audit
-- ----------------------------
CREATE TABLE `T_USP_SERV_AUDIT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SERV_AUDIT_DATE` datetime DEFAULT NULL,
  `SERV_AUDIT_OPINION` varchar(128) DEFAULT NULL,
  `SERV_AUDIT_RESULT` int(11) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `USP_SERV_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK335C328ACE417BC2` (`USER_ID`),
  KEY `FK335C328A320E80E2` (`USP_SERV_ID`),
  KEY `FK335C328A2C5320C9` (`USER_ID`),
  CONSTRAINT `FK335C328A2C5320C9` FOREIGN KEY (`USER_ID`) REFERENCES `T_USER` (`ID`),
  CONSTRAINT `FK335C328A320E80E2` FOREIGN KEY (`USP_SERV_ID`) REFERENCES `T_USP_SERVICE` (`ID`),
  CONSTRAINT `FK335C328ACE417BC2` FOREIGN KEY (`USER_ID`) REFERENCES `T_USER` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_USP_SERV_BIND
-- ----------------------------
CREATE TABLE `T_USP_SERV_BIND` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USP_SERV_BIND_NAME` varchar(64) DEFAULT NULL,
  `USP_SERV_BIND_PORTTYPE` varchar(64) DEFAULT NULL,
  `USP_SERV_BIND_TRANSPORT` varchar(128) DEFAULT NULL,
  `USP_SERV_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKC7D9F78E320E80E2` (`USP_SERV_ID`),
  CONSTRAINT `FKC7D9F78E320E80E2` FOREIGN KEY (`USP_SERV_ID`) REFERENCES `T_USP_SERVICE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_USP_SERV_GROUP
-- ----------------------------
CREATE TABLE `T_USP_SERV_GROUP` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USP_GROUP_DESC` longtext,
  `USP_GROUP_NAME` varchar(64) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_USER` varchar(64) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_USP_SERV_OPERATION
-- ----------------------------
CREATE TABLE `T_USP_SERV_OPERATION` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USP_OPER_DESC` longtext,
  `USP_OPER_NAME` varchar(128) DEFAULT NULL,
  `USP_SERV_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK45417496320E80E2` (`USP_SERV_ID`),
  CONSTRAINT `FK45417496320E80E2` FOREIGN KEY (`USP_SERV_ID`) REFERENCES `T_USP_SERVICE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_USP_SERV_PARAMETERS
-- ----------------------------
CREATE TABLE `T_USP_SERV_PARAMETERS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USP_PRM_DESC` varchar(128) DEFAULT NULL,
  `USP_PRM_FLAG` int(11) NOT NULL,
  `USP_PRM_NAME` varchar(32) DEFAULT NULL,
  `USP_PRM_TYPE` varchar(32) DEFAULT NULL,
  `USP_PRM_PARENT_ID` bigint(20) DEFAULT NULL,
  `USP_OPER_ID` bigint(20) DEFAULT NULL,
  `USP_ORDER_FIELD` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK7E05087BF4F22666` (`USP_OPER_ID`),
  KEY `FK7E05087B37B9A039` (`USP_PRM_PARENT_ID`),
  CONSTRAINT `FK7E05087B37B9A039` FOREIGN KEY (`USP_PRM_PARENT_ID`) REFERENCES `T_USP_SERV_PARAMETERS` (`ID`),
  CONSTRAINT `FK7E05087BF4F22666` FOREIGN KEY (`USP_OPER_ID`) REFERENCES `T_USP_SERV_OPERATION` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=629 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for T_USP_SERVICE
-- ----------------------------
CREATE TABLE `T_USP_SERVICE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USP_NAME_SPACE` varchar(128) DEFAULT NULL,
  `USP_SERV_ADDR` varchar(128) DEFAULT NULL,
  `USP_SERV_CTIME` datetime DEFAULT NULL,
  `USP_SERV_DESC` longtext,
  `USP_SERV_MTIME` datetime DEFAULT NULL,
  `USP_SERV_NAME` varchar(64) DEFAULT NULL,
  `USP_SERV_STATUS` varchar(4) DEFAULT NULL,
  `USP_SERV_TYPE` int(11) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `USP_GROUP_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK44AB315D1B5A9367` (`USP_GROUP_ID`),
  KEY `FK44AB315D2C5320C9` (`USER_ID`),
  CONSTRAINT `FK44AB315D1B5A9367` FOREIGN KEY (`USP_GROUP_ID`) REFERENCES `T_USP_SERV_GROUP` (`ID`),
  CONSTRAINT `FK44AB315D2C5320C9` FOREIGN KEY (`USER_ID`) REFERENCES `T_USER` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
