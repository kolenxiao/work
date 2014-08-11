-- 2012-11-29 906974/jiangjinfeng
alter table T_MY_APP add C_APP_PACKAGE_NAME longtext;

alter table T_APP_COMMENT_INFO add C_APP_PACKAGE_NAME longtext;

update T_MY_APP left join T_DP_APP_INFO
on T_MY_APP.C_APP_ID = T_DP_APP_INFO.C_ID
set T_MY_APP.C_APP_PACKAGE_NAME = T_DP_APP_INFO.C_APP_FILE_PACKAGE;

update T_APP_COMMENT_INFO left join T_DP_APP_INFO
on T_APP_COMMENT_INFO.C_APP_ID = T_DP_APP_INFO.C_ID
set T_APP_COMMENT_INFO.C_APP_PACKAGE_NAME = T_DP_APP_INFO.C_APP_FILE_PACKAGE;


update T_DP_APP_INFO set C_SYSTEM='1' where C_SYSTEM='1.0';
update T_DP_APP_INFO set C_SYSTEM='2' where C_SYSTEM='1.1';
update T_DP_APP_INFO set C_SYSTEM='3' where C_SYSTEM='1.5';
update T_DP_APP_INFO set C_SYSTEM='4' where C_SYSTEM='1.6';
update T_DP_APP_INFO set C_SYSTEM='5' where C_SYSTEM='2.0';
update T_DP_APP_INFO set C_SYSTEM='6' where C_SYSTEM='2.0.1';
update T_DP_APP_INFO set C_SYSTEM='7' where C_SYSTEM='2.1';
update T_DP_APP_INFO set C_SYSTEM='8' where C_SYSTEM='2.2';
update T_DP_APP_INFO set C_SYSTEM='9' where C_SYSTEM='2.3';
update T_DP_APP_INFO set C_SYSTEM='10' where C_SYSTEM='2.3.3';
update T_DP_APP_INFO set C_SYSTEM='11' where C_SYSTEM='3.0';
update T_DP_APP_INFO set C_SYSTEM='12' where C_SYSTEM='3.1';
update T_DP_APP_INFO set C_SYSTEM='13' where C_SYSTEM='3.2';
update T_DP_APP_INFO set C_SYSTEM='14' where C_SYSTEM='4.0';
update T_DP_APP_INFO set C_SYSTEM='15' where C_SYSTEM='4.0.3';
update T_DP_APP_INFO set C_SYSTEM='16' where C_SYSTEM='4.1';

update T_DP_APP_INFO set C_SYSTEM='1' where C_SYSTEM='android 1.0';
update T_DP_APP_INFO set C_SYSTEM='2' where C_SYSTEM='android 1.1';
update T_DP_APP_INFO set C_SYSTEM='3' where C_SYSTEM='android 1.5';
update T_DP_APP_INFO set C_SYSTEM='4' where C_SYSTEM='android 1.6';
update T_DP_APP_INFO set C_SYSTEM='5' where C_SYSTEM='android 2.0';
update T_DP_APP_INFO set C_SYSTEM='6' where C_SYSTEM='android 2.0.1';
update T_DP_APP_INFO set C_SYSTEM='7' where C_SYSTEM='android 2.1';
update T_DP_APP_INFO set C_SYSTEM='8' where C_SYSTEM='android 2.2';
update T_DP_APP_INFO set C_SYSTEM='9' where C_SYSTEM='android 2.3';
update T_DP_APP_INFO set C_SYSTEM='10' where C_SYSTEM='android 2.3.3';
update T_DP_APP_INFO set C_SYSTEM='11' where C_SYSTEM='android 3.0';
update T_DP_APP_INFO set C_SYSTEM='12' where C_SYSTEM='android 3.1';
update T_DP_APP_INFO set C_SYSTEM='13' where C_SYSTEM='android 3.2';
update T_DP_APP_INFO set C_SYSTEM='14' where C_SYSTEM='android 4.0';
update T_DP_APP_INFO set C_SYSTEM='15' where C_SYSTEM='android 4.0.3';
update T_DP_APP_INFO set C_SYSTEM='16' where C_SYSTEM='android 4.1';

