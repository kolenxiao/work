-- zhengxinlian 2012-12-04
-- 客户端新增保存的权限操作
update T_RESOURCE set c_url='appStoreClient!doAdd.action' where c_id='8a85a536158fc81107a0004';

INSERT INTO T_DP_TYPE VALUES ('10068', '2012-12-04 12:56:42', 'admin' , 'BANK_TYPE', 'PSBC_BANK_TYPE', '邮政银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '邮政银行', '2012-12-04 12:56:47');
INSERT INTO T_DP_TYPE VALUES ('10069', '2012-12-04 12:56:42', 'admin' , 'BANK_TYPE', 'CITIC_BANK_TYPE', '中信银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '中信银行', '2012-12-04 12:56:47');
INSERT INTO T_DP_TYPE VALUES ('10070', '2012-12-04 12:56:42', 'admin' , 'BANK_TYPE', 'CEB_BANK_TYPE', '光大银行', 'app_sort_img_20.png', 'app_sort_img_20.png','光大银行', '2012-12-04 12:56:47');

-- zhengxinlian 2012-12-06
-- 注册资讯分类
INSERT INTO `t_dp_type` VALUES ('92', '2012-12-06 17:59:32', 'admin', 'NEWS_TYPE', 'REGISTER_TYPE', '注册FAQ', 'app_sort_img_1.png', 'app_sort_img_1.png', '注册FAQ', '2012-12-06 17:59:32');

