/**
 * 管理员
 */
INSERT INTO t_user
   (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_EMAIL`, `C_PASSWORD`, `C_REAL_NAME`, `C_STATUS`, `C_TELEPHONE`, `C_UPDATED_DATE`, `C_USER_NAME`)
VALUES
   ('1', '2012-12-19 12:09:10', 'admin', 'admin@coship.com', '0b9530c150355c9577aae21764f319c3', '超级管理员', 'Y', '15967254621', '2012-12-19 12:09:10', 'admin');

/**
 * 角色
 */
INSERT INTO t_role
   (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_NAME`, `C_UPDATED_DATE`)
VALUES
   ('1', '2012-12-19 12:09:10', 'admin', '所有权限', '超级管理员', '2012-12-19 12:09:10');


/**
 * 管理员角色
 */
INSERT INTO t_user_role
   (`C_ID`, `C_USER_ROLE_ID`, `C_ROLE_USER_ID`)
VALUES
   ('1', '1', '1');


/**
 * 分类
 */
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('1', '2012-12-27 19:25:22', 'admin', null, 'ROOT_TYPE', '根分类描述', 'app_sort_img_1.png', 'app_sort_img_on_1.png', '根分类', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('10', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'FINANCE_APP_TYPE', '财经类型', 'app_sort_img_5.png', 'app_sort_img_on_5.png', '财经', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('1000', '2012-12-27 19:25:22', 'admin', 'NEWS_TYPE', '9', '常见FAQ', 'exchange.png', 'exchange.png', '常见FAQ', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('10068', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'PSBC_BANK_TYPE', '邮政银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '邮政银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('10069', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'CITIC_BANK_TYPE', '中信银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '中信银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('10070', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'CEB_BANK_TYPE', '光大银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '光大银行', '2012-12-27 19:25:22');
-- INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'GAME_APP_TYPE', '游戏类型', 'app_sort_img_6.png', 'app_sort_img_on_6.png', '游戏', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('12', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'HELTH_APP_TYPE', '健康类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '健康', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('13', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'LIFE_APP_TYPE', '生活类型', 'app_sort_img_8.png', 'app_sort_img_on_7.png', '生活', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('15', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'MUSIC_APP_TYPE', '音乐类型', 'app_sort_img_10.png', 'app_sort_img_on_10.png', '音乐', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('17', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'NEWS_APP_TYPE', '新闻类型', 'app_sort_img_12.png', 'app_sort_img_on_12.png', '新闻', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('2', '2012-12-27 19:25:22', 'admin', 'ROOT_TYPE', 'APP_TYPE', '分类描述', 'app_sort_img_1.png', 'app_sort_img_on_1.png', '应用分类', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('20', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'PHOTOGRAPHY_APP_TYPE', '书籍类型', 'app_sort_img_13.png', 'app_sort_img_on_13.png', '书籍', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('21', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'SOCIAL_APP_TYPE', '社交类型', 'app_sort_img_16.png', 'app_sort_img_on_16.png', '社交', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('22', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'SPORT_APP_TYPE', '体育类型', 'app_sort_img_17.png', 'app_sort_img_on_17.png', '体育', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('24', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'TOOL_APP_TYPE', '工具类型', 'app_sort_img_19.png', 'app_sort_img_on_19.png', '工具', '2012-12-27 19:25:22');

/**
 * 添加游戏分类
 */
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11000', '2013-06-07 19:25:22', 'admin', 'ROOT_TYPE', 'GAME_TYPE', '分类描述', 'app_sort_img_1.png', 'app_sort_img_on_1.png', '游戏分类', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11001', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'PUZZLE_GAME_TYPE', '益智类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '益智', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11002', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'RACING_GAME_TYPE', '赛车竞技类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '赛车竞技', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11003', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'ROLE_GAME_TYPE', '角色扮演类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '角色扮演', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11004', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'SPORTS_GAME_TYPE', '体育运动类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '体育运动', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11005', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'SHOOT_GAME_TYPE', '射击类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '射击', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11006', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'CHESS_GAME_TYPE', '棋牌类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '棋牌桌游', '2013-06-07 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('11007', '2013-06-07 19:25:22', 'admin', 'GAME_TYPE', 'ACTION_GAME_TYPE', '动作冒险类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '动作冒险', '2013-06-07 19:25:22');

INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('26', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'CMBC _BANK_TYPE', '招商银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '招商银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('27', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'CBC_BANK_TYPE', '建设银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '建设银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('28', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'ICBC_BANK_TYPE', '工商银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '工商银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('29', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'CB_BANK_TYPE', '中国银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '中国银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('3', '2012-12-27 19:25:22', 'admin', 'ROOT_TYPE', 'NEWS_TYPE', '分类描述', 'app_sort_img_1.png', 'app_sort_img_on_1.png', '资讯分类', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('30', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'ABC_BANK_TYPE', '农业银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '农业银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('31', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'HSBC', '交通银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '交通银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('32', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'GFB_BANK_TYPE', '广发银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '广发银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('33', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'SPDB', '浦发银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '浦发银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('34', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'CIB_BANK_TYPE', '兴业银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '兴业银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('35', '2012-12-27 19:25:22', 'admin', 'BANK_TYPE', 'CMSB_BANK_TYPE', '民生银行', 'app_sort_img_20.png', 'app_sort_img_20.png', '民生银行', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('36', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '1', 'Android版本类型', 'app_sort_img_20.png', 'app_sort_img_20.png', 'Android 1.0', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('37', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '3', 'Android版本类型', 'app_sort_img_20.png', 'app_sort_img_20.png', 'Android 1.5', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('38', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '4', 'Android版本类型', 'app_sort_img_20.png', 'app_sort_img_20.png', 'Android 1.6', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('39', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '5', 'Android版本类型', 'app_sort_img_20.png', 'app_sort_img_20.png', 'Android 2.0', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('4', '2012-12-27 19:25:22', 'admin', 'ROOT_TYPE', 'DOCUMENT_TYPE', '分类描述', 'app_sort_img_1.png', 'app_sort_img_on_1.png', '文档分类', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('40', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '7', 'Android版本类型', 'app_sort_img_20.png', 'app_sort_img_20.png', 'Android 2.1', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('41', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '8', 'Android版本类型', 'app_sort_img_20.png', 'app_sort_img_20.png', 'Android 2.2', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('42', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '9', 'Android版本类型', 'app_sort_img_20.png', 'app_sort_img_20.png', 'Android 2.3', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('43', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '11', 'Android版本类型', 'app_sort_img_20.png', 'app_sort_img_20.png', 'Android 3.0', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('44', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '14', 'Android版本类型', 'app_sort_img_20.png', 'app_sort_img_20.png', 'Android 4.0', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('45', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '16', 'Android版本类型', 'app_sort_img_20.png', 'app_sort_img_20.png', 'Android 4.1', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('46', '2012-12-27 19:25:22', 'admin', 'LANGUAGE_TYPE', 'zh', '语言类型', 'app_sort_img_20.png', 'app_sort_img_20.png', '中文', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('47', '2012-12-27 19:25:22', 'admin', 'LANGUAGE_TYPE', 'en', '语言类型', 'app_sort_img_20.png', 'app_sort_img_20.png', '英文', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('48', '2012-12-27 19:25:22', 'admin', 'LANGUAGE_TYPE', 'de', '语言类型', 'app_sort_img_20.png', 'app_sort_img_20.png', '德文', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('49', '2012-12-27 19:25:22', 'admin', 'LANGUAGE_TYPE', 'fr', '语言类型', 'app_sort_img_20.png', 'app_sort_img_20.png', '法文', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('5', '2012-12-27 19:25:22', 'admin', 'ROOT_TYPE', 'BANK_TYPE', '分类描述', 'app_sort_img_1.png', 'app_sort_img_on_1.png', '银行分类', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('50', '2012-12-27 19:25:22', 'admin', 'LANGUAGE_TYPE', 'ru', '语言类型', 'app_sort_img_20.png', 'app_sort_img_20.png', '俄文', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('500', '2012-12-27 19:25:22', 'admin', 'ROOT_TYPE', 'ANDROID_TYPE', '分类描述', 'app_sort_img_1.png', 'app_sort_img_on_1.png', 'Android版本分类', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('501', '2012-12-27 19:25:22', 'admin', 'ROOT_TYPE', 'LANGUAGE_TYPE', '分类描述', 'app_sort_img_1.png', 'app_sort_img_on_1.png', '语言分类', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('502', '2012-12-27 19:25:22', 'admin', 'ROOT_TYPE', 'PAY_TYPE', '分类描述', 'app_sort_img_1.png', 'app_sort_img_on_1.png', '支付平台分类', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('503', '2012-12-27 19:25:22', 'admin', 'ROOT_TYPE', 'CERT_TYPE', '分类描述', 'app_sort_img_1.png', 'app_sort_img_on_1.png', '证件分类', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('51', '2012-12-27 19:25:22', 'admin', 'LANGUAGE_TYPE', 'ar', '语言类型', 'app_sort_img_20.png', 'app_sort_img_20.png', '阿拉伯文', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('52', '2012-12-27 19:25:22', 'admin', 'LANGUAGE_TYPE', 'ja', '语言类型', 'app_sort_img_20.png', 'app_sort_img_20.png', '日文', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('53', '2012-12-27 19:25:22', 'admin', 'LANGUAGE_TYPE', 'it', '语言类型', 'app_sort_img_20.png', 'app_sort_img_20.png', '意大利文', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('54', '2012-12-27 19:25:22', 'admin', 'PAY_TYPE', 'ZFB_PAY_TYPE', '支付宝支付', '1app_sort_img_20.png', 'app_sort_img_20.png', '支付宝支付', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('55', '2012-12-27 19:25:22', 'admin', 'PAY_TYPE', 'BFB_PAY_TYPE', '百付宝支付', '1app_sort_img_20.png', 'app_sort_img_20.png', '百付宝支付', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('56', '2012-12-27 19:25:22', 'admin', 'PAY_TYPE', 'CFT_PAY_TYPE', '财付通支付', '1app_sort_img_20.png', 'app_sort_img_20.png', '财付通支付', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('57', '2012-12-27 19:25:22', 'admin', 'PAY_TYPE', 'KQ_AY_TYPE', '快钱支付', '1app_sort_img_20.png', 'app_sort_img_20.png', '快钱支付', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('58', '2012-12-27 19:25:22', 'admin', 'CERT_TYPE', 'ID_CERT_TYPE', '身份证', '1app_sort_img_20.png', 'app_sort_img_20.png', '身份证', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('62', '2012-12-27 19:25:22', 'admin', 'CERT_TYPE', 'YY_CERT_TYPE', '营业执照', '1app_sort_img_20.png', 'app_sort_img_20.png', '营业执照', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('63', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '6', 'Android版本类型', '1app_sort_img_20.png', 'app_sort_img_20.png', 'Android 2.0.1', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('64', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '10', 'Android版本类型', '1app_sort_img_20.png', 'app_sort_img_20.png', 'Android 2.3.3', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('65', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '12', 'Android版本类型', '1app_sort_img_20.png', 'app_sort_img_20.png', 'Android 3.1', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('66', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '13', 'Android版本类型', '1app_sort_img_20.png', 'app_sort_img_20.png', 'Android 3.2', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('67', '2012-12-27 19:25:22', 'admin', 'ANDROID_TYPE', '15', 'Android版本类型', '1app_sort_img_20.png', 'app_sort_img_20.png', 'Android 4.0.3', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('69', '2012-12-27 19:25:22', 'admin', 'NEWS_TYPE', '1', '热点资讯', 'hotspot.png', 'hotspot.png', '热点资讯', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('70', '2012-12-27 19:25:22', 'admin', 'NEWS_TYPE', '2', '平台公告', 'notice.png', 'notice.png', '平台公告', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('73', '2012-12-27 19:25:22', 'admin', 'NEWS_TYPE', '5', '传媒动态', 'movement.png', 'movement.png', '传媒动态', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('75', '2012-12-27 19:25:22', 'admin', 'NEWS_TYPE', '7', '数字电视', 'tv.png', 'tv.png', '数字电视', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('77', '2012-12-27 19:25:22', 'admin', 'NEWS_TYPE', '9', '技术交流', 'exchange.png', 'exchange.png', '技术交流', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('8', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'EDU_APP_TYPE', '教育类型', 'app_sort_img_3.png', 'app_sort_img_on_3.png', '教育', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('84', '2012-12-27 19:25:22', 'admin', 'DOCUMENT_TYPE', '10', '', 'document.png', 'document.png', 'Java', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('86', '2012-12-27 19:25:22', 'admin', 'DOCUMENT_TYPE', '12', 'android', 'document.png', 'document.png', 'Android', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('9', '2012-12-27 19:25:22', 'admin', 'APP_TYPE', 'FUN_APP_TYPE', '娱乐类型', 'app_sort_img_4.png', 'app_sort_img_on_4.png', '娱乐', '2012-12-27 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('504', '2013-12-10 19:25:22', 'admin', 'ROOT_TYPE', 'OP_MODE_TYPE', '分类描述', NULL, NULL, '操作类型分类', '2013-12-10 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('2c9e97a842da6b190142da6d598d0001', '2013-12-10 10:54:28', 'admin', 'OP_MODE_TYPE', '000000000001', '', NULL, NULL, '摸摸看', '2013-12-10 10:55:18');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('2c9e97a842da6b190142da6d9a8c0003', '2013-12-10 10:54:45', 'admin', 'OP_MODE_TYPE', '000000000010', '', NULL, NULL, '遥控器', '2013-12-10 10:55:26');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('2c9e97a842da6b190142da6dca360005', '2013-12-10 10:54:57', 'admin', 'OP_MODE_TYPE', '000000000100', '', NULL, NULL, '体感', '2013-12-10 10:55:30');

update T_DP_TYPE set C_CREATE_DATE = now();
update T_DP_TYPE set C_UPDATE_DATE = now();

/**
 * init T_RESOURCE
 */
/**
 * 开发者管理菜单和按钮资源
 */
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('19', '2012-9-3 18:23:41', 'admin', 'AP信息管理模块。', 'ApMsgManager', 1, 0, '开发者管理', 1, '2012-9-3 18:49:52', '', NULL);
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('35', '2012-9-3 18:23:41', 'admin', '', 'allApInfo', 2, 0, '开发者列表', 1, '2012-9-3 18:50:05', 'dpStaff!doList.action?flag=0', '19');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('34', '2012-9-3 18:23:41', 'admin', '', 'waitCheckApInfo', 2, 0, '开发者审核', 2, '2012-9-3 18:50:29', 'dpStaff!doList.action?flag=1', '19');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('48', '2012-9-3 18:23:41', 'admin', '', 'deleteAp', 3, 1, '删除AP信息', 0, '2012-9-3 18:23:41', 'dpStaff!doDelete.action', '35');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('49', '2012-9-3 18:23:41', 'admin', '', 'queryApInfo', 3, 1, '查看和审核AP信息跳转', 0, '2012-9-3 18:23:41', 'dpStaff!doDisplay.action', '35');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('79', '2012-9-3 18:23:41', 'admin', '', 'saveCheck', 3, 1, '保存AP审核信息', 0, '2012-9-3 18:23:41', 'dpStaff!doModify.action', '35');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('50', '2012-9-3 18:23:41', 'admin', '', 'PassWordReset', 3, 0, '重置AP密码', 0, '2012-9-3 18:23:41', 'dpStaff!passwordReset.action', '35');


/**
 * 我的应用菜单和按钮资源
 * 2012-11-01 by zhengxinlian
 */
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('400', '2012-9-3 18:23:41', 'admin', '管理员我的应用管理模块菜单。', 'AdminAppManager', 1, 0, '我的应用', 2, '2012-9-3 18:51:25', '', NULL);
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('401', '2012-9-3 18:23:41', 'admin', '创建app文档，上传我的应用', 'createAppDocument', 2, 0, '上传普通应用', 1, '2012-9-3 18:51:17', 'dpAppInfo!doApp.action?forward=Add', '400');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('402', '2012-9-3 18:23:41', 'admin', '我所有的应用列表', 'allMyAppList', 2, 0, '普通应用列表', 2, '2012-9-3 18:51:17', 'dpAppInfo!doSearchAdminAppList.action', '400');

/**
 * 应用管理菜单和按钮资源
 */
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('20', '2012-9-3 18:23:41', 'admin', 'AP应用管理模块菜单。', 'ApAppManager', 1, 0, '应用管理', 3, '2012-9-3 18:51:25', '', NULL);
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('54', '2012-9-3 18:23:41', 'admin', '全部AP应用', 'allAppList', 2, 0, '应用列表', 1, '2012-9-3 18:51:17', 'dpAppInfo!doSearchList.action', '20');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('47', '2012-9-3 18:23:41', 'admin', '', 'notAuditApp', 2, 0, '应用审核', 2, '2012-9-3 18:52:12', 'dpAppInfo!doSearchList.action?flags=1', '20');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('126', '2012-9-3 18:54:12', 'admin', '', 'appOnlineMgr', 2, 0, '应用上架', 3, '2012-9-3 18:55:13', 'dpAppInfo!doSearchOnOrOffList.action', '20');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('127', '2012-9-3 18:55:03', 'admin', '', 'appRecommendedMgr', 2, 0, '应用推荐', 4, '2012-9-3 18:55:03', 'appRecommend!doSearchRecommendList.action', '20');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('134', '2012-09-06 16:52:54', 'admin', '批量取消推荐', 'cancleRecommendApp', '3', '1', '取消推荐', '1', '2012-09-06 16:52:54', 'dpAppInfo!doBatchCommend.action', '127');

INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('135', '2012-09-07 17:01:46', 'admin', '应用专题', 'SubjectApp', '2', '0', '应用专题', '5', '2012-09-10 10:32:27', 'dpAppSubjectType!doSearchAppSubjectTypeList.action', '20');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('136', '2012-09-07 17:54:28', 'admin', '', 'deleteSubject', '3', '1', '删除应用专题', '1', '2012-09-07 17:54:28', 'dpAppSubjectType!deleteSubject.action', '135');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('137', '2012-09-10 10:27:10', 'admin', '进入专题编辑页面', 'toAppSubjectEdit', '3', '1', '进入专题编辑页面', '1', '2012-09-10 10:27:10', 'dpAppSubjectType!toEditSubject.action', '135');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('138', '2012-09-10 10:29:59', 'admin', '保存编辑应用专题数据', 'doSaveAppSubject', '3', '1', '保存编辑应用专题数据', '1', '2012-09-10 10:29:59', 'dpAppSubjectType!doSaveEidt.action', '135');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('139', '2012-09-11 11:19:30', 'admin', '移除专题关联的应用', 'removeAppToSubjectType', '3', '1', '移除专题应用', '1', '2012-09-11 11:26:37', 'dpAppSubjectType!doRemoveAppToSubjectType.action', '135');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('140', '2012-09-11 11:28:02', 'admin', '新增专题应用', 'addAppToSubjectType', '3', '1', '新增专题应用', '1', '2012-09-11 11:28:02', 'dpAppSubjectType!doAddAppToSubjectType.action', '135');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('141', '2012-09-11 11:44:03', 'admin', '进入添加专题应用页面', 'toAddAppToSubjectType', '3', '1', '进入添加专题应用页面', '1', '2012-09-11 11:44:03', 'dpAppInfo!toAddAppToSubjectType.action', '135');


INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('128', '2012-9-3 18:56:53', 'admin', '', 'appStatisticsMgr', 2, 0, '应用统计', 6, '2012-9-3 18:57:03', 'dpAppInfoStat!searchAppStatistic.action', '20');
-- INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('129', '2012-9-3 18:58:06', 'admin', '', 'appIncomeMgr', 2, 0, '收入明细', 7, '2012-9-3 18:58:06', '', '20');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('105', '2012-9-3 18:23:41', 'admin', '下载AP应用', 'doDownload', 3, 1, '下载AP应用', 0, '2012-9-3 18:23:41', 'dpAppInfo!doDownLoad.action', '54');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('107', '2012-9-3 18:23:41', 'admin', '审核Ap应用', 'doAuditDpAppInfo', 3, 1, '审核Ap应用', 0, '2012-9-3 18:23:41', 'dpAppInfo!doAudit.action', '54');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('110', '2012-9-3 18:23:41', 'admin', '上架', 'appOnline', 3, 1, '上架', 0, '2012-9-3 18:23:41', 'dpAppInfo!doOnline.action', '54');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('55', '2012-9-3 18:23:41', 'admin', '删除AP应用', 'deleteAppInfo', 3, 1, '删除AP应用', 0, '2012-9-3 18:23:41', 'dpAppInfo!doDelete.action', '54');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('56', '2012-9-3 18:23:41', 'admin', '按条件查询AP应用信息', 'findAppInfo', 3, 1, '查询AP应用', 0, '2012-9-3 18:23:41', 'dpAppInfo!doSearchList.action', '54');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('116', '2012-9-3 18:23:41', 'admin', '', 'doList', 3, 1, '应用展示', 0, '2012-9-3 18:23:41', 'dpAppInfo!doList.action', '54');

INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('142', '2012-9-3 18:23:41', 'admin', '', 'toStatisticsDetail', 3, 1, '应用统计详情', 0, '2012-9-3 18:23:41', 'dpAppInfoStat!viewDetailAppStatic.action', '128');

/**
 * 资讯管理菜单和按钮资源
 */
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('22', '2012-9-3 18:23:41', 'admin', '资讯管理模块菜单。', 'InformManager', 1, 0, '资讯管理', 4, '2012-9-3 18:58:52', '', NULL);
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('39', '2012-9-3 18:23:41', 'admin', '资讯列表菜单。', 'doNewsList', 2, 0, '资讯列表', 2, '2012-9-3 18:23:41', 'dpnews!doList.action', '22');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('40', '2012-9-3 18:23:41', 'admin', '新增资讯菜单', 'doNewsAdd', 2, 0, '新增资讯', 1, '2012-9-3 18:23:41', 'dpnews!doDisplay.action', '22');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('43', '2012-9-3 18:23:41', 'admin', '添加资讯按钮', 'doDisplayNews', 3, 1, '添加资讯', 1, '2012-9-3 18:23:41', 'dpnews!doDisplay.action', '39');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('44', '2012-9-3 18:23:41', 'admin', '删除资讯', 'doDeleteNews', 3, 1, '删除资讯', 2, '2012-9-3 18:23:41', 'dpnews!doDelete.action', '39');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('45', '2012-9-3 18:23:41', 'admin', '编辑资讯', 'doEditNews', 3, 1, '编辑资讯', 3, '2012-9-3 18:23:41', 'dpnews!doDisplay.action', '39');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('46', '2012-9-3 18:23:41', 'admin', '资讯上线', 'doDpNewsOnLine', 3, 1, '资讯上线', 4, '2012-9-3 18:23:41', 'dpnews!doOnLine.action', '39');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('100', '2012-9-3 18:23:41', 'admin', '保存资讯', 'doAddNew', 3, 2, '保存资讯', 4, '2012-9-3 18:23:41', 'dpnews!doAdd.action', '39');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('53', '2012-9-3 18:23:41', 'admin', '修改资讯', 'doEditNewsAction', 3, 1, '修改资讯', 2, '2012-9-3 18:23:41', 'dpnews!doEdit.action', '39');

/**
 * 开发文档管理菜单和按钮资源
 */
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('21', '2012-9-3 18:23:41', 'admin', '开发文档管理模块菜单。', 'downManager', 1, 0, '文档管理', 5, '2012-9-3 18:59:25', '', NULL);
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('41', '2012-9-3 18:23:41', 'admin', '开发文档列表界面', 'downloadinfo', 2, 0, '开发文档列表', 1, '2012-9-3 18:23:41', 'dpdownload!doList.action', '21');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('64', '2012-9-3 18:23:41', 'admin', '跳转到新增开发文档界面', 'toDownloadAdd', 2, 0, '新增开发文档', 0, '2012-9-3 18:23:41', 'dpdownload!doDisplay.action', '21');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('108', '2012-9-3 18:23:41', 'admin', '保存下载按钮', 'doDownloadAdd', 3, 1, '保存新增下载', 4, '2012-9-3 18:23:41', 'dpdownload!doAdd.action', '41');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('125', '2012-9-3 18:23:41', 'admin', '跳转到编辑下载界面', 'toDownloadEdit', 3, 1, '跳转到编辑下载界面', 2, '2012-9-3 18:23:41', 'dpdownload!doDisplay.action', '41');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('109', '2012-9-3 18:23:41', 'admin', '保存修改下载按钮', 'doDownloadEdit', 3, 1, '修改下载', 3, '2012-9-3 18:23:41', 'dpdownload!doEdit.action', '41');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('120', '2012-9-3 18:23:41', 'admin', '删除下载按钮', 'doDelDownInfo', 3, 1, '删除下载', 3, '2012-9-3 18:23:41', 'dpdownload!doDelete.action', '41');


-- 证书管理菜单
-- 2012-11-17 906055/wangchenbo
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af59c39e8c3640139e8c70ea90003', '2012-9-21 20:22:03', 'admin', '证书、私钥、吊销证书，应用批量签名', 'CertManager', 1, 0, '证书管理', 6, '2012-9-21 20:22:03', 'certificate!doList.action', NULL);
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af5af3af8b771013af8bb66ee0005', '2012-11-13 15:46:02', 'admin', '证书、私钥对列表', 'CertList', 2, 0, '证书列表', 1, '2012-11-13 16:24:48', 'certificate!doList.action', '8a8af59c39e8c3640139e8c70ea90003');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af5af3af8b771013af8bbe7660007', '2012-11-13 15:46:34', 'admin', '吊销列表', 'RevokedCertList', 2, 0, '吊销列表', 2, '2012-11-14 19:26:27', 'certificate!doRevokeList.action', '8a8af59c39e8c3640139e8c70ea90003');

INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af59c39e8c3640139e8c751ae0005', '2012-9-21 20:22:20', 'admin', '', 'addOrEdit', 3, 1, '新增或修改证书跳转页面', 1, '2012-9-21 20:22:20', 'certificate!doAddOrModify.action', '8a8af5af3af8b771013af8bb66ee0005');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af59c39e8c3640139e8cfadee0054', '2012-9-21 20:31:28', 'admin', '保存证书文件', 'addCertificate', 3, 1, '新增证书', 2, '2012-9-21 20:31:28', 'certificate!doAdd.action', '8a8af5af3af8b771013af8bb66ee0005');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af59c39f283140139f2901fa00002', '2012-9-23 17:58:15', 'admin', '', 'appSignMgr', 2, 0, '批量签名', 3, '2012-9-23 17:58:15', 'appsign!doList.action', '8a8af59c39e8c3640139e8c70ea90003');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af59c39f756670139f758ce180002', '2012-9-24 16:15:55', 'admin', '', 'addAppSign', 3, 1, '新增签名', 2, '2012-9-24 16:15:55', 'appsign!doAppSign.action', '8a8af59c39f283140139f2901fa00002');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af5d139fd8af6013a0026eb040004', '2012-9-26 09:18:01', 'admin', '修改证书', 'modifyCertificate', 3, 1, '修改证书', 2, '2012-9-26 09:18:01', 'certificate!doModify.action', '8a8af5af3af8b771013af8bb66ee0005');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af5d13a01592a013a015cb0500002', '2012-9-26 14:56:22', 'admin', '删除证书', 'deleteCertificate', 3, 1, '删除证书', 3, '2012-9-26 14:56:22', 'certificate!doDelete.action', '8a8af5af3af8b771013af8bb66ee0005');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af5af3afecb7a013afeea17870001', '2012-11-14 20:34:45', 'admin', '', 'addOrEditRevoke', 3, 1, '新增或修改吊销证书跳转页面', 1, '2012-11-14 20:34:45', 'certificate!doAddOrModifyRevoke.action', '8a8af5af3af8b771013af8bbe7660007');

-- 2013-05-07 906974/jiangjinfeng
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af5d139fd8af6013a0026eb041114', '2012-9-26 09:18:02', 'admin', '设置默认证书', 'setDefaultCertificate', 3, 1, '设置默认证书', 4, '2012-9-26 09:18:01', 'certificate!setDefault.action', '8a8af5af3af8b771013af8bb66ee0005');
/**
 * 客户端管理
 */
INSERT INTO `t_resource` VALUES ('8a8af5a53afc7b0b013afc7ecf860002', '2012-11-14 09:18:20', 'admin', '应用自动部署管理', 'AutoInstallAppMgr', 1, 0, '客户端管理', 7, '2012-11-14 09:18:20', '', null);
INSERT INTO `t_resource` VALUES ('8a8af5a53afc7b0b013afc81107a0004', '2012-11-14 09:20:47', 'admin', '客户端列表', 'appStoreClientList', 2, 0, '客户端列表', 1, '2012-11-14 09:20:47', 'appStoreClient!doList.action', '8a8af5a53afc7b0b013afc7ecf860002');
INSERT INTO `t_resource` VALUES ('8a8af5a53afc7b0b013afc8498230006', '2012-11-14 09:24:39', 'admin', '删除应用商店客户端', 'doDeleteAppStoreClient', 3, 1, '删除客户端', 1, '2012-11-14 09:24:39', 'appStoreClient!doDelete.action', '8a8af5a53afc7b0b013afc81107a0004');
INSERT INTO `t_resource` VALUES ('8a8af5a53afd852b013afd8b1d920002', '2012-11-14 14:11:23', 'admin', '保存新增客户端', 'doAddAppClient', 3, 1, '保存新增客户端', 1, '2012-11-14 14:11:23', 'appStoreClient!doAdd.action', '8a8af5a53afc7b0b013afc81107a0004');
INSERT INTO `t_resource` VALUES ('8a8af5a53afd8f4c013afd9119c20002', '2012-11-14 14:17:55', 'admin', '保存修改', 'doModifyAppClient', 3, 1, '保存修改客户端', 1, '2012-11-14 14:17:55', 'appStoreClient!doModify.action', '8a8af5a53afc7b0b013afc81107a0004');
INSERT INTO `t_resource` VALUES ('8a8af5a53b028887013b028ad42f0002', '2012-11-15 13:29:10', 'admin', '上下架应用客户端', 'doOnOrOffAppClient', 3, 1, '上下架应用客户端', 1, '2012-11-15 13:29:10', 'appStoreClient!doOnOrOffLine.action', '8a8af5a53afc7b0b013afc81107a0004');
INSERT INTO `t_resource` VALUES ('8a8af5a53afc7b0b013afc81107b0004', '2012-11-14 09:20:47', 'admin', '终端设备列表', 'deviceInfoList', 2, 0, '终端设备列表', 2, '2012-11-14 09:20:47', 'deviceInfo!doList.action', '8a8af5a53afc7b0b013afc7ecf860002');

-- add by zhengxinlian/906976
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a85a536158fc81107a0004', '2012-11-21 15:46:01', 'admin', '新增客户端', 'toAppStoreClient', 2, 0, '新增客户端', 1, '2012-11-14 09:20:47', 'appStoreClient!toAdd.action', '8a8af5a53afc7b0b013afc7ecf860002');

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

/**
 * 权限管理菜单和按钮资源
 */
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8', '2012-9-3 18:23:41', 'admin', '权限管理', 'permissionManager', 1, 0, '权限管理', 9, '2012-9-3 19:00:06', '', NULL);
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('9', '2012-9-3 18:23:41', 'admin', '用户管理', 'userManager', 2, 0, '用户管理', 1, '2012-9-3 18:23:41', 'user!searchUser.action', '8');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('10', '2012-9-3 18:23:41', 'admin', '角色管理', 'roleManager', 2, 0, '角色管理', 2, '2012-9-3 18:23:41', 'role!searchRole.action', '8');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('11', '2012-9-3 18:23:41', 'admin', '资源管理', 'resManager', 2, 0, '资源管理', 3, '2012-9-3 18:23:41', 'resource!searchRes.action', '8');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('29', '2012-9-3 18:23:41', 'admin', '添加用户', 'addUser', 3, 1, '添加用户', 0, '2012-9-3 18:23:41', 'user!addUser.action', '9');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('30', '2012-9-3 18:23:41', 'admin', '删除用户', 'deleteUser', 3, 1, '删除用户', 1, '2012-9-3 18:23:41', 'user!doDelete.action', '9');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('31', '2012-9-3 18:23:41', 'admin', '修改用户', 'modifyUser', 3, 1, '修改用户', 2, '2012-9-3 18:23:41', 'user!updateUser.action', '9');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('77', '2012-9-3 18:23:41', 'admin', '重置用户登录密码', 'passwordRet', 3, 1, '密码重置', 0, '2012-9-3 18:23:41', 'user!passwordReset.action', '9');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('15', '2012-9-3 18:23:41', 'admin', '添加新角色', 'addRole', 3, 1, '新增角色', 1, '2012-9-3 18:23:41', 'role!addRole.action', '10');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('16', '2012-9-3 18:23:41', 'admin', '删除角色信息', 'deleteRole', 3, 1, '角色删除', 2, '2012-9-3 18:23:41', 'role!deleteRole.action', '10');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('17', '2012-9-3 18:23:41', 'admin', '修改角色信息', 'modifyRole', 3, 1, '角色修改', 3, '2012-9-3 18:23:41', 'role!modifyRole.action', '10');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('18', '2012-9-3 18:23:41', 'admin', '给角色添加资源访问权限', 'rolePermission', 3, 1, '角色授权', 14, '2012-9-3 18:23:41', 'role!rolePermission.action', '10');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('12', '2012-9-3 18:23:41', 'admin', '添加新资源', 'addRes', 3, 1, '添加资源', 1, '2012-9-3 18:23:41', 'resource!addRes.action', '11');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('13', '2012-9-3 18:23:41', 'admin', '删除资源', 'deleteRes', 3, 1, '删除资源', 2, '2012-9-3 18:23:41', 'resource!deleteRes.action', '11');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('14', '2012-9-3 18:23:41', 'admin', '修改资源信息', 'modifyRes', 3, 1, '修改资源', 3, '2012-9-3 18:23:41', 'resource!modifyRes.action', '11');


/**
 * 系统管理菜单和按钮资源
 */
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('5', '2012-9-3 18:23:41', 'admin', '系统管理模块', 'SystemManage', 1, 0, '系统管理', 10, '2012-9-3 19:00:26', '', NULL);
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('24', '2012-9-3 18:23:41', 'admin', '日志管理模块菜单。', 'logManager', 2, 0, '日志管理', 1, '2012-9-3 19:04:58', 'oplogger!doList.action', '5');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('38', '2012-9-3 18:23:41', 'admin', '', 'newsManager', 2, 0, '分类管理', 2, '2012-9-3 19:05:06', 'dpType!list.action', '5');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('117', '2012-9-3 18:23:41', 'admin', '', 'doAdd', 3, 1, '增加分类', 0, '2012-9-3 18:23:41', 'dpType!doAdd.action', '38');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('57', '2012-9-3 18:23:41', 'admin', '新增编辑分类跳转', 'addType', 3, 1, '新增分类', 0, '2012-9-3 18:23:41', 'dpType!doDisplay.action', '38');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('124', '2012-9-3 18:23:41', 'admin', '编辑分类页面跳转', 'gotoEditType', 3, 1, '编辑分类页面跳转', 1, '2012-9-3 18:23:41', 'dpType!doDisplay.action', '38');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('118', '2012-9-3 18:23:41', 'admin', '', 'doEdit', 3, 1, '修改分类', 0, '2012-9-3 18:23:41', 'dpType!doEdit.action', '38');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('58', '2012-9-3 18:23:41', 'admin', '删除分类', 'deleteType', 3, 1, '删除分类', 0, '2012-9-3 18:23:41', 'dpType!doDelete.action', '38');
INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8ac89f4297588201429770b2c20002', '2013-11-27 10:43:34', 'admin', '分类排序', 'sortType', 3, 1, '分类排序', 0, '2013-11-27 10:43:34', 'dpType!doSort.action', '38');

/* 资源 */
update T_RESOURCE set C_CREATED_DATE = now();
update T_RESOURCE set C_UPDATED_DATE = now();


/**
 * 角色权限
 */
INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('1', '1', '57');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('10', '1', '48');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('11', '1', '107');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('12', '1', '38');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('13', '1', '14');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('14', '1', '50');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('15', '1', '17');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('16', '1', '21');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('17', '1', '22');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('18', '1', '56');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('19', '1', '49');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('2', '1', '20');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('20', '1', '8');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('21', '1', '100');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('22', '1', '127');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('23', '1', '43');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('24', '1', '41');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('25', '1', '77');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('26', '1', '47');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('27', '1', '30');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('28', '1', '124');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('29', '1', '13');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('3', '1', '11');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('30', '1', '35');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('31', '1', '19');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('32', '1', '39');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('33', '1', '18');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('34', '1', '125');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('35', '1', '12');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('36', '1', '128');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('37', '1', '40');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('38', '1', '54');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('39', '1', '10');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('4', '1', '126');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('40', '1', '24');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('42', '1', '5');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('43', '1', '16');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('44', '1', '110');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('45', '1', '79');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('46', '1', '53');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('47', '1', '9');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('49', '1', '15');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('5', '1', '29');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('50', '1', '108');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('51', '1', '64');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('52', '1', '58');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('53', '1', '34');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('55', '1', '46');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('56', '1', '44');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('57', '1', '55');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('58', '1', '31');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('59', '1', '105');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('6', '1', '117');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('62', '1', '45');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('63', '1', '134');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('64', '1', '135');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('65', '1', '136');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('66', '1', '137');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('67', '1', '138');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('68', '1', '139');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('69', '1', '140');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('7', '1', '118');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('70', '1', '141');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('71', '1', '120');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('72', '1', '142');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('74', '1', '400');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('75', '1', '401');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('76', '1', '402');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8', '1', '116');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af59c39e8c3640139e8c7a44d0013', '1', '8a8af59c39e8c3640139e8c70ea90003');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af59c39e8c3640139e8c7a7a40040', '1', '8a8af59c39e8c3640139e8c751ae0005');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af59c39e8c3640139e8d00b040098', '1', '8a8af59c39e8c3640139e8cfadee0054');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af59c39f771340139f77381f50060', '1', '8a8af59c39f756670139f758ce180002');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af59c39f771340139f77386670095', '1', '8a8af59c39f283140139f2901fa00002');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5a53b028887013b028b4e79001e', '1', '8a8af5a53afc7b0b013afc81107a0004');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5a53b028887013b028b4e79001f', '1', '8a8af5a53afc7b0b013afc81107b0004');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5a53b028887013b028b4eb70022', '1', '8a8af5a53b028887013b028ad42f0002');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5a53b028887013b028b4ec70024', '1', '8a8af5a53afd852b013afd8b1d920002');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5a53b028887013b028b51670049', '1', '8a8af5a53afd8f4c013afd9119c20002');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5a53b028887013b028b52030051', '1', '8a8af5a53afc7b0b013afc7ecf860002');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5a53b028887013b028b52510055', '1', '8a8af5a53afc7b0b013afc8498230006');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5a53b0557413b028b4ec7004', '1', '8a85a536158fc81107a0004');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5af3b0cbd89013b0d2bb55f001f', '1', '8a8af5af3afecb7a013afeea17870001');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5af3b0cbd89013b0d2bb80e0047', '1', '8a8af5af3af8b771013af8bbe7660007');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5af3b0cbd89013b0d2bb8ba0051', '1', '8a8af5af3af8b771013af8bb66ee0005');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5d13a01592a013a015d6a49002a', '1', '8a8af5d139fd8af6013a0026eb040004');

-- 906974/jiangjinfeng 2013-05-07
INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5d13a01592a013a015d6a49112a', '1', '8a8af5d139fd8af6013a0026eb041114');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5d13a01592a013a015d6cf8004c', '1', '8a8af5d13a01592a013a015cb0500002');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('9', '1', '109');
   
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8ac89f4297715f01429773ec05003f', '1', '8a8ac89f4297588201429770b2c20002');

-- 隐式应用关联角色
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004c', '1', '8a8af59c39aaa3640139e8c70ea90003');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004d', '1', '8a8af59c39aaa3640139e8c70ea90004');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004e', '1', '8a8af59c39aaa3640139e8c70ea90005');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004f', '1', '8a8af59c39aaa3640139e8c70ea90006');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004g', '1', '8a8af59c39aaa3640139e8c70ea90007');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8abbd13a01592a013a015d6cf8004h', '1', '8a8af59c39aaa3640139e8c70ea90008');


-- 常见FAQ添加
INSERT INTO t_dp_news
   (`C_ID`, `C_CREATE_USER`, `C_NEWS_CONTENT`, `C_NEWS_CREATE_TIME`, `C_NEWS_ORDER_ID`, `C_NEWS_SOURCE`, `C_NEWS_SOURCE_URL`, `C_NEWS_STSTUS`, `C_NEWS_SUMMARY`, `C_NEWS_TITLE`, `C_UPDATE_TIME`, `C_TYPE_ID`, `C_CLICK_TIME`)
VALUES
   ('8a8af50f3bdc1b6a013bdc3bd5e00002', 'admin', '<b>A：</b> 登录开发者门户，然后点击右上角的注册链接，或者直接点击登录，然后点击注册新用户。应注意以下事项：<br />\r\n1）注册表单页面中带*号的内容为必填内容；<br />\r\n2）注册用户名中不能含有不文明词汇、反动词汇、名人姓名等违反开发者社区规定的词语；<br />\r\n3）请填写详细、真实的注册信息，以保证信息可以审核通过并成为开发者；<br />\r\n4）请在注册时仔细阅读《开发者服务条款》。', '2012-12-27 20:00:07', 0, '', '', '1001', '登录开发者门户，然后点击右上角的注册链接...', 'Q：新用户注册', '2012-12-27 20:07:49', '1000', 0);

INSERT INTO t_dp_news
   (`C_ID`, `C_CREATE_USER`, `C_NEWS_CONTENT`, `C_NEWS_CREATE_TIME`, `C_NEWS_ORDER_ID`, `C_NEWS_SOURCE`, `C_NEWS_SOURCE_URL`, `C_NEWS_STSTUS`, `C_NEWS_SUMMARY`, `C_NEWS_TITLE`, `C_UPDATE_TIME`, `C_TYPE_ID`, `C_CLICK_TIME`)
VALUES
   ('8a8af50f3bdc1b6a013bdc3d77670004', 'admin', '<p><b>A:</b> </p>\r\n<p>1）您注册的信息不符合要求时，页面会出现提示框；</p>\r\n2）电脑或网络原因。请检查电脑，并刷新页面看是否成功；<br />\r\n<p>3）其他原因请联系我们（电话0755-26722666），我们会尽快给您解决。 </p>\r\n<p>4）查看上传的证据是否超过100Kb，系统规定证据大小不能超过100Kb</p>', '2012-12-27 20:01:54', 0, '', '', '1001', '您注册的信息不符合要求时，页面会出现提示框...', 'Q：没有注册成功', '2012-12-28 09:34:02', '1000', 0);

INSERT INTO t_dp_news
   (`C_ID`, `C_CREATE_USER`, `C_NEWS_CONTENT`, `C_NEWS_CREATE_TIME`, `C_NEWS_ORDER_ID`, `C_NEWS_SOURCE`, `C_NEWS_SOURCE_URL`, `C_NEWS_STSTUS`, `C_NEWS_SUMMARY`, `C_NEWS_TITLE`, `C_UPDATE_TIME`, `C_TYPE_ID`, `C_CLICK_TIME`)
VALUES
   ('8a8af50f3bdc1b6a013bdc3f0d460006', 'admin', '<b>A:</b> 不能重复填写表单注册，注册的用户名和邮箱必须保证唯一和可用，已保证后续账号或者密码出现问题时能够及时找回你的密码等信息', '2012-12-27 20:03:37', 0, '', '', '1001', '不能重复填写表单注册，注册的用户名和邮箱必须...', 'Q：出现“系统异常”的提醒', '2012-12-27 20:07:40', '1000', 0);

INSERT INTO t_dp_news
   (`C_ID`, `C_CREATE_USER`, `C_NEWS_CONTENT`, `C_NEWS_CREATE_TIME`, `C_NEWS_ORDER_ID`, `C_NEWS_SOURCE`, `C_NEWS_SOURCE_URL`, `C_NEWS_STSTUS`, `C_NEWS_SUMMARY`, `C_NEWS_TITLE`, `C_UPDATE_TIME`, `C_TYPE_ID`, `C_CLICK_TIME`)
VALUES
   ('8a8af50f3bdc1b6a013bdc416a3c0008', 'admin', '<p><b>A:</b></p>\r\n<p>\r\n									1）提供详细真实的公司、应用或者个人信息；</p>\r\n2）及时关注你的应用是否被审核通过，如果没有通过，请及时修改；<br />\r\n3）上传正确和合法的应用，以保障你的应用审核通过，并且可以和运营商合作，获取你的利益。<br />\r\n<br />', '2012-12-27 20:06:12', 0, '', '', '1001', '提供详细真实的公司、应用或者个人信息...', 'Q：开发者社区的开发者需要做什么？', '2012-12-27 20:07:36', '1000', 0);

INSERT INTO t_dp_news
   (`C_ID`, `C_CREATE_USER`, `C_NEWS_CONTENT`, `C_NEWS_CREATE_TIME`, `C_NEWS_ORDER_ID`, `C_NEWS_SOURCE`, `C_NEWS_SOURCE_URL`, `C_NEWS_STSTUS`, `C_NEWS_SUMMARY`, `C_NEWS_TITLE`, `C_UPDATE_TIME`, `C_TYPE_ID`, `C_CLICK_TIME`)
VALUES
   ('8a8af50f3bdf11ca013bdf1bfc280002', 'admin', '<b>A：</b> 应用审核通过后，可以对应用进行审核。但要注意一下事项<br />\r\n1）只有审核通过的应用才能升级<br />\r\n2）升级的应用的<b>包名</b>一定要和升级前的一致<br />\r\n3）升级的应用的版本<b>versionCode</b>一定要比升级前的要大，该字段是必填字段<br />\r\n4）应用<b>AndroidMantifest.xml</b>文件必须包含<b>uses-sdk</b>标签', '2012-12-28 09:24:11', 0, '', '', '1001', '应用审核通过后，可以对应用进行审核...', 'Q：什么情况可以对应用升级', '2012-12-28 09:24:11', '1000', 0);

INSERT INTO t_dp_news
   (`C_ID`, `C_CREATE_USER`, `C_NEWS_CONTENT`, `C_NEWS_CREATE_TIME`, `C_NEWS_ORDER_ID`, `C_NEWS_SOURCE`, `C_NEWS_SOURCE_URL`, `C_NEWS_STSTUS`, `C_NEWS_SUMMARY`, `C_NEWS_TITLE`, `C_UPDATE_TIME`, `C_TYPE_ID`, `C_CLICK_TIME`)
VALUES
   ('8a8af50f3bdf11ca013bdf1f489d0004', 'admin', '<b>A：</b> 上传的应用不能包含有反党、反政府、反社会，并且也不包含违法犯罪等问题，另外还需要遵循如下规范<br />\r\n1）应用必须包含有<b>包名</b><br />\r\n2）应用的<b>AndroidManifest.xml</b>文件必须包含<b>android:versionCode</b>属性<br />\r\n3）应用的<b>AndroidManifest.xml</b>文件必须包含<b>android:versionName</b><br />\r\n4）应用<b>AndroidMantifest.xml</b>文件必须包含<b>uses-sdk</b>标签', '2012-12-28 09:27:47', 0, '', '', '1001', '上传的应用不能包含有反党、反政府、反社会...', 'Q：上传的应用有什么规范要求', '2012-12-28 09:27:47', '1000', 0);

INSERT INTO t_dp_news
   (`C_ID`, `C_CREATE_USER`, `C_NEWS_CONTENT`, `C_NEWS_CREATE_TIME`, `C_NEWS_ORDER_ID`, `C_NEWS_SOURCE`, `C_NEWS_SOURCE_URL`, `C_NEWS_STSTUS`, `C_NEWS_SUMMARY`, `C_NEWS_TITLE`, `C_UPDATE_TIME`, `C_TYPE_ID`, `C_CLICK_TIME`)
VALUES
   ('8a8af50f3bdf11ca013bdf22984e0006', 'admin', '<b>A：</b> 已经审核通过的应用是不能修改的，只有在审核不通过，或者未审核的情况才可以对应用进行修改。<br />\r\n1）应用上传时如果没有完全上传完毕，则为草稿状态<br />\r\n2）应用上传完毕则为待审核状态<br />\r\n3）应用审核不通过则为审核不通过状态<br />\r\n<p>\r\n4）应用审核通过则为审核通过状态</p>\r\n<p>5）应用还包括上架状态，下架状态和版本更新状态</p>', '2012-12-28 09:31:24', 0, '', '', '1001', '已经审核通过的应用是不能修改的，只有在审核...', 'Q：审核通过的应用可以修改吗', '2012-12-28 09:31:24', '1000', 0);

INSERT INTO t_dp_news
   (`C_ID`, `C_CREATE_USER`, `C_NEWS_CONTENT`, `C_NEWS_CREATE_TIME`, `C_NEWS_ORDER_ID`, `C_NEWS_SOURCE`, `C_NEWS_SOURCE_URL`, `C_NEWS_STSTUS`, `C_NEWS_SUMMARY`, `C_NEWS_TITLE`, `C_UPDATE_TIME`, `C_TYPE_ID`, `C_CLICK_TIME`)
VALUES
   ('8a8af50f3bdf2b1e013bdf2efbb60002', 'admin', '<b>A：</b> 如果密码忘记了，那么可以点击登录页面的忘记密码页面，填写的用户名和注册邮箱一定要填写正确，这样系统会发送一份随机的15位随机密码到你的注册邮箱。有注意的地方：<br />\r\n1）注册的才审核，一定要保证注册邮箱正确，以保证能找回你的密码功能<br />\r\n<br />', '2012-12-27 20:00:08', 0, '', '', '1001', '如果密码忘记了，那么可以...', 'Q：如何找回密码', '2012-12-27 20:07:50', '1000', 0);

INSERT INTO t_dp_news
   (`C_ID`, `C_CREATE_USER`, `C_NEWS_CONTENT`, `C_NEWS_CREATE_TIME`, `C_NEWS_ORDER_ID`, `C_NEWS_SOURCE`, `C_NEWS_SOURCE_URL`, `C_NEWS_STSTUS`, `C_NEWS_SUMMARY`, `C_NEWS_TITLE`, `C_UPDATE_TIME`, `C_TYPE_ID`, `C_CLICK_TIME`)
VALUES
   ('8a8af7833be08177013be0d0bb6d000c', 'admin', '<b>A：</b>对开发者的图片大小是有要求的；系统要求开发者的图片大小不能超过100Kb，包括头像和证件信息<br />', '2012-12-28 17:21:14', 0, '', '', '1001', '对开发者的图片大小是有要求的...', 'Q：开发者的图片大小有要求吗', '2012-12-28 17:22:04', '1000', 0);

-- 2013-07-01 907632/wangzhenghui 
-- t_app_recommend 新增字段初始化默认值
update t_app_recommend s set c_type_id =(select c_type_id from t_dp_app_info tt where tt.c_id=s.c_app_id);
commit;

-- 2013-07-08 907632/wangzhenghui
-- 添加应用分类推荐
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb1cfe9bb0002','2013-07-06 10:29:20','admin','应用推荐分类','appTypeRecommendMgr','2','0','应用分类推荐','1','2013-07-06 10:29:56','dpType!doSearchRecommendList.action','20');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb1d4cbd7006a','2013-07-06 10:34:40','admin','取消应用推荐分类','cancleTypeRecommend','3','1','取消应用分类推荐','1','2013-07-06 10:34:40','dpType!doCommend.action?commendFlag=0','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb204f750006d','2013-07-06 11:27:17','admin','','addTypeRecommend','3','1','新增应用分类推荐','2','2013-07-06 11:27:30','dpType!doCommend.action?commendFlag=1','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb20724e900d3','2013-07-06 11:29:40','admin','新增应用推荐分类查询','queryUncludeTypeRecommed','3','1','新增应用分类推荐查询','1','2013-07-06 11:29:40','dpType!doSearchUncludeTypeRecommend.action','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb219e4f30344','2013-07-06 11:50:08','admin','应用推荐查询','searchAppTypeRecommendList','3','1','应用推荐查询','1','2013-07-06 11:52:48','appTypeRecommend!doSearchAppTypeRecommendList.action','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb21d5c9203af','2013-07-06 11:53:56','admin','新增应用推荐查询','searchAppInTypeRecommend','3','1','新增应用推荐查询','1','2013-07-06 11:53:56','dpAppInfo!toAddTypeRecommentList','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb21df20d03b1','2013-07-06 11:54:34','admin','','cancleAppInTypeRecommend','3','1','取消应用推荐','1','2013-07-06 11:56:31','appTypeRecommend!doBatchCommend.action','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb23e2241041f','2013-07-06 12:29:43','admin','新增应用推荐','addAppToTypeRecommend','3','1','新增应用推荐','1','2013-07-06 12:30:42','appTypeRecommend!doCommend.action','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_resource` (`C_ID`, `C_CREATED_DATE`, `C_CREATED_USER`, `C_DESCRIPTION`, `C_EN_NAME`, `C_LEVEL`, `C_MENU_BUTTON`, `C_NAME`, `C_ORDER_FIELD`, `C_UPDATED_DATE`, `C_URL`, `C_PARENT_ID`) values('8a8ac8123fb1cb9a013fb244c90304ba','2013-07-06 12:36:59','admin','','sortAppInTypeRecommend','3','1','应用排序','1','2013-07-06 13:57:40','appTypeRecommend!doRecommendSort.action','8a8ac8123fb1cb9a013fb1cfe9bb0002');
commit;

-- 2013-07-08 907632/wangzhenghui
-- 应用分类功能关联超级管理员角色
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28eeea5000e','1','8a8ac8123fb1cb9a013fb1cfe9bb0002');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28eeecf000f','1','8a8ac8123fb1cb9a013fb244c90304ba');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28eef9f001e','1','8a8ac8123fb1cb9a013fb1d4cbd7006a');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef0250028','1','8a8ac8123fb1cb9a013fb219e4f30344');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef0bb0036','1','8a8ac8123fb1cb9a013fb21d5c9203af');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef156003e','1','8a8ac8123fb1cb9a013fb21df20d03b1');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef180003f','1','8a8ac8123fb1cb9a013fb204f750006d');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef2750052','1','8a8ac8123fb1cb9a013fb23e2241041f');
insert into `t_role_resource` (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) values('8a8ac8123fb28cb3013fb28ef2d30057','1','8a8ac8123fb1cb9a013fb20724e900d3');
commit;

-- 2013-11-1. 
-- 增加版块资源. 
INSERT INTO t_resource VALUES ('8a8ac8ed422b81fc01422b85b57b0002', '2013-11-6 11:47:32', 'admin', 'MouUI2.0精选页管理', 'featuredManager', 1, 0, '精选页管理', 4, '2013-11-6 11:48:01', '', NULL);
INSERT INTO t_resource VALUES ('8a8ac80842300f8e01423016921c0002', '2013-11-7 09:04:14', 'admin', '', 'queryItem', 2, 0, '元素列表', 3, '2013-11-7 09:04:14', 'appRecommendPanelItem!doList.action', '8a8ac8ed422b81fc01422b85b57b0002');
INSERT INTO t_resource VALUES ('8a8ac80842301b890142301c60140002', '2013-11-7 09:10:35', 'admin', '', 'deleteItem', 3, 1, '删除元素', 1, '2013-11-7 09:10:35', 'appRecommendPanelItem!doDeleteItem.action', '8a8ac80842300f8e01423016921c0002');
INSERT INTO t_resource VALUES ('8a8ac80842301b890142301d8d2d0004', '2013-11-7 09:11:52', 'admin', '', 'doItemDetail', 3, 1, '查询元素详情', 2, '2013-11-7 09:11:52', 'appRecommendPanelItem!toDetail.action', '8a8ac80842300f8e01423016921c0002');
INSERT INTO t_resource VALUES ('8a8ac80842301b890142301e4de60006', '2013-11-7 09:12:41', 'admin', '', 'updateItem', 3, 1, '修改元素', 4, '2013-11-7 09:12:41', 'appRecommendPanelItem!doUpdateItem', '8a8ac80842300f8e01423016921c0002');
INSERT INTO t_resource VALUES ('8a8ac8084230215901423022f14d0002', '2013-11-7 09:17:45', 'admin', '', 'toSaveItem', 2, 0, '新增元素', 5, '2013-11-7 09:17:45', 'appRecommendPanelItem!toSaveItem.action', '8a8ac8ed422b81fc01422b85b57b0002');
INSERT INTO t_resource VALUES ('8a8ac8084230215901423023d1e90004', '2013-11-7 09:18:43', 'admin', '', 'saveItem', 3, 1, '新增元素信息', 2, '2013-11-7 09:18:43', 'appRecommendPanelItem!doSaveItem.action', '8a8ac8084230215901423022f14d0002');
INSERT INTO t_resource VALUES ('8a8ac808423021590142302a3f280086', '2013-11-7 09:25:44', 'admin', '', 'queryOnlineApp', 3, 1, '元素关联应用查询', 3, '2013-11-7 09:25:44', 'dpAppInfo!doAppList.action', '8a8ac8084230215901423022f14d0002');
INSERT INTO t_resource VALUES ('8a8ac808423021590142302e79c50108', '2013-11-7 09:30:21', 'admin', '', 'addSubjectToItem', 3, 1, '元素关联专题查询', 4, '2013-11-7 09:32:50', 'dpAppSubjectType!doSearchAppSubjectTypeListByItem.action', '8a8ac8084230215901423022f14d0002');
INSERT INTO t_resource VALUES ('8a8ac8ed422b81fc01422b88208f0074', '2013-11-6 11:50:10', 'admin', '', 'queryPanel', 2, 0, '版块列表', 1, '2013-11-6 11:50:51', 'appRecommendPanel!doList.action', '8a8ac8ed422b81fc01422b85b57b0002');
INSERT INTO t_resource VALUES ('8a8ac8ed422b81fc01422b8a308a0077', '2013-11-6 11:52:25', 'admin', '', 'toSavePanel', 2, 0, '新增版块', 2, '2013-11-6 13:41:53', 'appRecommendPanel!toSavePanel.action', '8a8ac8ed422b81fc01422b85b57b0002');
INSERT INTO t_resource VALUES ('8a8ac8ed422b81fc01422b8af8270079', '2013-11-6 11:53:17', 'admin', '', 'doPanelDetail', 3, 1, '查询版块详情', 3, '2013-11-6 13:47:12', 'appRecommendPanel!toDetail.action', '8a8ac8ed422b81fc01422b88208f0074');
INSERT INTO t_resource VALUES ('8a8ac8ed422b81fc01422b8c7582007c', '2013-11-6 11:54:54', 'admin', '', 'doSortPanel', 3, 1, '版块人工排序', 5, '2013-11-6 11:54:54', 'appRecommendPanel!doSort.action', '8a8ac8ed422b81fc01422b88208f0074');
INSERT INTO t_resource VALUES ('8a8ac8ed422b81fc01422b8d71d0007e', '2013-11-6 11:55:59', 'admin', '', 'doUpdatePanel', 3, 1, '修改版块信息', 6, '2013-11-6 11:55:59', 'appRecommendPanel!doUpdatePanel.action', '8a8ac8ed422b81fc01422b88208f0074');
INSERT INTO t_resource VALUES ('8a8ac8ed422b81fc01422beb5d860081', '2013-11-6 13:38:34', 'admin', '', 'doDeletePanel', 3, 1, '删除版块信息', 7, '2013-11-6 13:38:34', 'appRecommendPanel!doDeletePanel.action', '8a8ac8ed422b81fc01422b88208f0074');
INSERT INTO t_resource VALUES ('8a8ac8ed422b81fc01422befba1d00f8', '2013-11-6 13:43:20', 'admin', '', 'addPanel', 3, 1, '添加版块信息', 8, '2013-11-6 13:43:20', 'appRecommendPanel!doSavePanel.action', '8a8ac8ed422b81fc01422b8a308a0077');
INSERT INTO t_resource VALUES ('8a8ac8ed422c756c01422c7698f70002', '2013-11-6 16:10:39', 'admin', '', 'panelItemManager', 3, 1, '版块元素管理', 9, '2013-11-6 16:10:39', 'appRecommendPanelItem!doPanelItemList.action', '8a8ac8ed422b81fc01422b88208f0074');
INSERT INTO t_resource VALUES ('8a8ac8ed422c756c01422c7cd9890078', '2013-11-6 16:17:28', 'admin', '', 'deletePanelItem', 3, 1, '删除版块元素', 11, '2013-11-6 16:17:28', 'appRecommendPanelItem!doDeletePanelItem.action', '8a8ac8ed422b81fc01422b88208f0074');
INSERT INTO t_resource VALUES ('8a8ac8ed422c756c01422c7db57e007a', '2013-11-6 16:18:25', 'admin', '', 'queryItemUnRecommend', 3, 1, '新增版块元素查询', 12, '2013-11-6 18:15:21', 'appRecommendPanelItem!doUseFulItemList.action', '8a8ac8ed422b81fc01422b88208f0074');
INSERT INTO t_resource VALUES ('8a8ac8ed422c756c01422c7e9731007c', '2013-11-6 16:19:23', 'admin', '', 'sortPanelItem', 3, 1, '版块元素排序', 13, '2013-11-6 16:19:23', 'appRecommendPanelItem!doSort.action', '8a8ac8ed422b81fc01422b88208f0074');
INSERT INTO t_resource VALUES ('8a8ac8ed422c756c01422c7f906b007e', '2013-11-6 16:20:26', 'admin', '', 'addItemToPanel', 3, 1, '版块关联元素', 14, '2013-11-6 18:21:15', 'appRecommendPanelItem!doRecommendItem.action', '8a8ac8ed422b81fc01422b88208f0074');

commit;

INSERT INTO t_resource VALUES ('8a8ac808424b405901424b422e3c0002', '2013-11-12 15:41:37', 'admin', '', 'batchUpdate', 3, 1, '批量应用上下架', 1, '2013-11-12 15:41:37', 'dpAppInfo!doBatchOnOrDownline.action', '126');

INSERT INTO t_resource VALUES ('2c9e97a842d6463c0142d657262c0007', '2013-12-09 15:51:44', 'admin', '应用排序', 'sortApp', 3, 1, '应用排序', 2, '2013-12-09 15:51:44', 'dpAppInfo!doSort.action', '126');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('2c9e97a842d6463c0142d657c5600036', '1', '2c9e97a842d6463c0142d657262c0007');

INSERT INTO t_resource VALUES ('8a8a9f2b43802ba601438032b80e0002', '2014-01-11 15:27:24', 'admin', '缓存管理模块菜单', 'cacheManager', 2, 0, '缓存管理', 4, '2014-01-11 15:27:24', 'systemAdmin!cacheHome.action', '5');
INSERT INTO t_resource VALUES ('8a8a9f2b43802ba6014380352d330005', '2014-01-11 15:27:24', 'admin', '刷新缓存', 'refreshCache', 3, 1, '刷新缓存', 1, '2014-01-11 15:27:24', 'systemAdmin!refreshCache.action', '8a8a9f2b43802ba601438032b80e0002');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8a9f2b43802ba601438035a57d0026', '1', '8a8a9f2b43802ba601438032b80e0002');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8a9f2b43802ba601438035ab280080', '1', '8a8a9f2b43802ba6014380352d330005');


commit;

-- 2014-04-15 V100R003B001 方案管理 增加权限
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
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e998c4588c05f014588c1649d0002','2014-04-22 17:25:46','admin','','copyPlan',3,1,'复制方案',1,'2014-04-22 17:25:46','planManage!copy.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e9975457442000145872d5de80014','2014-04-22 17:25:46','admin','','modifyAppOnline',3,1,'上架状态修改应用',5,'2014-04-22 17:25:46','dpAppInfo!modifyAppOnline.action','126');
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
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f0a008c','1','8a8a901e4545bf84014545c427650003');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f190096','1','8a8a901e4526ab3b014526adcba00004');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f1c0098','1','8a8a901e451b6b5c01451b703b940004');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f21009b','1','2c9e997544d85e6501454982e0cf04e1');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('8a8a901e455ef82201455eff7f2c00a2','1','8a8a901e450687a20145068b0c3f0002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('2c9e998c4588c05f014588c1deb70053','1','2c9e998c4588c05f014588c1649d0002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('2c9e998e4592e1c5014592fbda50003f','1','2c9e9975457442000145872d5de80014');
commit;

-- V100R003B002. 
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e998c4588c05f014588c1649d0002','2014-04-22 17:25:46','admin','','copyPlan',3,1,'复制方案',1,'2014-04-22 17:25:46','planManage!copy.action','2c9e997544d85e6501450bf54a190018');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e9975457442000145872d5de80014','2014-04-22 17:25:46','admin','','modifyAppOnline',3,1,'上架状态修改应用',5,'2014-04-22 17:25:46','dpAppInfo!modifyAppOnline.action','126');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('2c9e998c4588c05f014588c1deb70053','1','2c9e998c4588c05f014588c1649d0002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('2c9e998e4592e1c5014592fbda50003f','1','2c9e9975457442000145872d5de80014');
delete from t_role_resource  where C_RES_ROLE_ID in ('8a8a901e4545bf84014545c5aee80005');
delete from t_resource  where c_id in ('8a8a901e4545bf84014545c5aee80005');


	-- 复制方案 存储过程. 
	DELIMITER $$  
		DROP PROCEDURE IF EXISTS PRO_APPSTORE_COPY_PLAN $$  
		CREATE  PROCEDURE PRO_APPSTORE_COPY_PLAN(
				in_newPlanId varchar(32),
				in_oldPlanId varchar(32)) 
		BEGIN  
		 -- 设置非自动提交. 
		SET autocommit=0;
		
		    -- 插入plan 数据. 
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
		
			-- 插入 planItem. 
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
		
			-- 插入 planItemApp. 
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
		
		-- 手动提交. 
		 commit;
		END $$  
	DELIMITER ; 
	
	
-- V100R003B006. 
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e999046423b7601464241a6a50002', '2014-5-28 17:55:37', 'admin', '', 'ParamManage', 2, 0, '参数管理', 1, '2014-5-28 17:55:37', 'systemParam!list.action', '5');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e99904664b6b5014664bea7fb00ad', '2014-6-4 10:39:14', 'admin', '', 'createOrModifyParam', 3, 1, '新增修改参数', 1, '2014-6-4 10:39:14', 'systemParam!createOrModify.action', '2c9e999046423b7601464241a6a50002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e999046648d420146649a54110003', '2014-6-4 09:59:34', 'admin', '', 'displayParam', 3, 1, '查看参数详情', 1, '2014-6-4 09:59:34', 'systemParam!display.action', '2c9e999046423b7601464241a6a50002');
INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e99904645b1bd014645c3da8b0006', '2014-5-29 10:16:41', 'admin', '', 'deleteParam', 3, 1, '删除参数', 1, '2014-5-29 10:16:41', 'systemParam!delete.action', '2c9e999046423b7601464241a6a50002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('2c9e99904664b6b5014664bef231013d','1','2c9e999046423b7601464241a6a50002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('2c9e99904664b6b5014664bef232013e', '1', '2c9e99904645b1bd014645c3da8b0006');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('2c9e99904664b6b5014664bef1d7010f', '1', '2c9e999046648d420146649a54110003');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('2c9e99904664b6b5014664bef25b0157', '1', '2c9e99904664b6b5014664bea7fb00ad');
commit;

-- V100R003B007. 
INSERT INTO T_APP_SUBJECT_TYPE(C_ID,C_CREATE_DATE,C_CREATE_USER,C_SUBJECT_DESC,C_SUBJECT_IMG,C_SUBJECT_NAME,C_PRODUCT_CODE,C_UPDATE_DATE,C_VISIBLE_FLAG) 
	VALUES
	('2c9e997544d85e65014520140620001',now(),'admin','辽宁阜新合家欢套餐','','辽宁阜新合家欢套餐','fx_388',now(), 1),
	('2c9e997544d85e65014520140620002',now(),'admin','辽宁阜新高清互动Ⅰ套餐','','辽宁阜新高清互动Ⅰ套餐','fx_588',now(), 1),
	('2c9e997544d85e65014520140620003',now(),'admin','辽宁阜新高清互动 Ⅱ套餐','','辽宁阜新高清互动 Ⅱ套餐','fx_988',now(), 1);

-- V100R003B008. 
INSERT INTO `t_system_param`(`C_ID`, `C_CODE`, `C_NAME`, `C_TYPE`, `C_VALUE`) VALUES ('2c9e9975469339110146b7ea8cee00d9', 'DVB_AUTH_CITYCODE', '需要鉴权的CityCode', '1', '0418001');
INSERT INTO `t_system_param`(`C_ID`, `C_CODE`, `C_NAME`, `C_TYPE`, `C_VALUE`) VALUES ('2c9e9990468f4bba014692f2ae450002', 'picsize_img', '应用截图尺寸', '1', '');
commit;