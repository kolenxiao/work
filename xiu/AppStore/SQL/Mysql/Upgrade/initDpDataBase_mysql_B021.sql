-- 2012-12-11 zhengxinlian/96976
-- 删除无用的字段
alter table T_DP_APP_INFO drop column C_APP_DOC;
alter table T_DP_APP_INFO drop column C_APP_FILE;
alter table T_DP_APP_INFO drop column C_APP_IMG1;
alter table T_DP_APP_INFO drop column C_APP_IMG2;
alter table T_DP_APP_INFO drop column C_APP_IMG3;
alter table T_DP_APP_INFO drop column C_APP_LOGO;
alter table T_DP_APP_INFO drop column C_APP_LOGO2;
alter table T_DP_APP_INFO drop column C_APP_LOGO3;
alter table T_DP_APP_INFO drop column C_SIZE;
alter table T_DP_APP_INFO drop column C_APP_POSTER;
alter table T_DP_APP_INFO drop column C_APP_ID;
alter table T_DP_APP_INFO drop column C_LEVEL;

alter table T_DP_APP_INFO ADD INDEX FK7D0237148C341343(C_APP_NAME);
alter table T_DP_APP_INFO ADD INDEX FK7D0237148C341344(C_APP_STATUS);

-- 2012-12-12 zhengxinlian/96976
-- 更改字段
alter table T_DP_APP_INFO change C_APP_TIME C_CREATE_TIME datetime;

alter table T_APP_SIGN
	add constraint FK7D0237148C341345
	foreign key (C_APP_ID)
	references T_DP_APP_INFO (C_ID);
alter table T_APP_CERTIFICATE drop column C_CERTIFICATE_NAME;
