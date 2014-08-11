-- V100R003B008. 
-- 新增表'人工指定猜你喜欢'. 
DROP TABLE IF EXISTS `t_hand_app_relate`;
CREATE TABLE `t_hand_app_relate` (
  `C_ID` varchar(32) NOT NULL,
  `C_APP_PKG_NAME` varchar(255) NOT NULL,
  `C_PLAN_ID` varchar(255) NOT NULL,
  `C_RELATE_APP_PKG_NAME` varchar(255) NOT NULL,
  `C_SORT_NUM` int(11) NOT NULL,
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `t_system_param`(`C_ID`, `C_CODE`, `C_NAME`, `C_TYPE`, `C_VALUE`) VALUES ('2c9e9975469339110146b7ea8cee00d9', 'DVB_AUTH_CITYCODE', '需要鉴权的CityCode', '1', '0418001');
INSERT INTO `t_system_param`(`C_ID`, `C_CODE`, `C_NAME`, `C_TYPE`, `C_VALUE`) VALUES ('2c9e9990468f4bba014692f2ae450002', 'picsize_img', '应用截图尺寸', '1', '');
