-- 2012-11-21 906976/zhengxinlian
update T_RESOURCE set C_NAME='文档管理' where c_id='21';
update T_RESOURCE set C_URL='appStoreClient!toAdd.action' where c_id='8a8af5a53afd852b013afd8b1d920002';
update T_RESOURCE set C_ORDER_FIELD='2' where C_ID='8a8af5a53afc7b0b013afc81107a0004';
update T_RESOURCE set C_NAME='吊销列表' where C_ID='8a8af5af3af8b771013af8bbe7660007';
update T_RESOURCE set C_NAME='客户端管理' where C_ID='8a8af5a53afc7b0b013afc7ecf860002';
INSERT INTO T_RESOURCE VALUES ('8a85a536158fc81107a0004', '2012-11-21 15:46:01', 'admin', '新增客户端', 'toAppStoreClient', '2', '0', '新增客户端', '1', '2012-11-14 09:20:47', 'appStoreClient!toAdd.action', '8a8af5a53afc7b0b013afc7ecf860002');
INSERT INTO `t_role_resource` VALUES ('8a8af5a53b0557413b028b4ec7004', '1', '8a85a536158fc81107a0004');

-- 2012-11-23 906976/zhengxinlian
update T_RESOURCE set C_URL='dpAppInfo!toAddAppToSubjectType.action' where c_id='141';

update T_DP_APP_INFO set C_SYSTEM='android 1.0' where C_SYSTEM='1.0';
update T_DP_APP_INFO set C_SYSTEM='android 1.5' where C_SYSTEM='1.5';
update T_DP_APP_INFO set C_SYSTEM='android 1.6' where C_SYSTEM='1.6';
update T_DP_APP_INFO set C_SYSTEM='android 2.0' where C_SYSTEM='2.0';
update T_DP_APP_INFO set C_SYSTEM='android 2.1' where C_SYSTEM='2.1';
update T_DP_APP_INFO set C_SYSTEM='android 2.2' where C_SYSTEM='2.2';
update T_DP_APP_INFO set C_SYSTEM='android 2.3' where C_SYSTEM='2.3';
update T_DP_APP_INFO set C_SYSTEM='android 3.0' where C_SYSTEM='3.0';
update T_DP_APP_INFO set C_SYSTEM='android 4.0' where C_SYSTEM='4.0';
update T_DP_APP_INFO set C_SYSTEM='android 4.1' where C_SYSTEM='4.1';
update T_DP_APP_INFO set C_SYSTEM='android 1.0' where C_SYSTEM='1';
update T_DP_APP_INFO set C_SYSTEM='android 1.5' where C_SYSTEM='3';
update T_DP_APP_INFO set C_SYSTEM='android 1.6' where C_SYSTEM='4';
update T_DP_APP_INFO set C_SYSTEM='android 2.0' where C_SYSTEM='5';
update T_DP_APP_INFO set C_SYSTEM='android 2.1' where C_SYSTEM='7';
update T_DP_APP_INFO set C_SYSTEM='android 2.2' where C_SYSTEM='8';
update T_DP_APP_INFO set C_SYSTEM='android 2.3' where C_SYSTEM='9';
update T_DP_APP_INFO set C_SYSTEM='android 3.0' where C_SYSTEM='11';
update T_DP_APP_INFO set C_SYSTEM='android 4.0' where C_SYSTEM='14';
update T_DP_APP_INFO set C_SYSTEM='android 4.1' where C_SYSTEM='16';
update T_DP_APP_INFO set C_SYSTEM='android 2.0.1' where C_SYSTEM='6';
update T_DP_APP_INFO set C_SYSTEM='android 2.3.3' where C_SYSTEM='10';
update T_DP_APP_INFO set C_SYSTEM='android 3.1' where C_SYSTEM='12';
update T_DP_APP_INFO set C_SYSTEM='android 3.2' where C_SYSTEM='13';
update T_DP_APP_INFO set C_SYSTEM='android 4.0.3' where C_SYSTEM='15';
