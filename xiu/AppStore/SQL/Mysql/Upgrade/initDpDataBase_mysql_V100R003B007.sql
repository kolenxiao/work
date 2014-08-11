-- V100R003B007. 应用商店DVB版本增加付费后显示应用功能 
-- 专题表新增productCode字段，用于记录DVB环境的BOSS系统中的产品编码，该值要与IUC中的productCode一致，表示同一个套餐.
-- 目前有的产品编码. fx_388(表示辽宁阜新388套餐), fx_588(表示辽宁阜新588套餐),fx_988(表示辽宁阜新988套餐)

ALTER TABLE T_APP_SUBJECT_TYPE ADD COLUMN C_PRODUCT_CODE VARCHAR(255) DEFAULT NULL AFTER C_SUBJECT_NAME;
INSERT INTO T_APP_SUBJECT_TYPE(C_ID,C_CREATE_DATE,C_CREATE_USER,C_SUBJECT_DESC,C_SUBJECT_IMG,C_SUBJECT_NAME,C_PRODUCT_CODE,C_UPDATE_DATE,C_VISIBLE_FLAG) 
	VALUES
	('2c9e997544d85e65014520140620001',now(),'admin','辽宁阜新合家欢套餐','','辽宁阜新合家欢套餐','fx_388',now(), 1),
	('2c9e997544d85e65014520140620002',now(),'admin','辽宁阜新高清互动Ⅰ套餐','','辽宁阜新高清互动Ⅰ套餐','fx_588',now(), 1),
	('2c9e997544d85e65014520140620003',now(),'admin','辽宁阜新高清互动 Ⅱ套餐','','辽宁阜新高清互动 Ⅱ套餐','fx_988',now(), 1);

commit;
