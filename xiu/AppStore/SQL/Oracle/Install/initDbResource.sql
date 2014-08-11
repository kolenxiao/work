
-- ----------------------------
-- Records 
-- ----------------------------
/**
 * init T_DATA_DICT
 */
INSERT INTO T_DATA_DICT VALUES ('88', 'BANK_TYPE', '1', '招商银行', '1', '1', '招商银行');
INSERT INTO T_DATA_DICT VALUES ('89', 'BANK_TYPE', '2', '建设银行', '1', '2', '建设银行');
INSERT INTO T_DATA_DICT VALUES ('90', 'BANK_TYPE', '3', '工商银行', '1', '3', '工商银行');
INSERT INTO T_DATA_DICT VALUES ('91', 'BANK_TYPE', '4', '中国银行', '1', '4', '中国银行');
INSERT INTO T_DATA_DICT VALUES ('92', 'BANK_TYPE', '5', '农业银行', '1', '5', '农业银行');
INSERT INTO T_DATA_DICT VALUES ('93', 'BANK_TYPE', '6', '交通银行', '1', '6', '交通银行');
INSERT INTO T_DATA_DICT VALUES ('94', 'BANK_TYPE', '7', '广发银行', '1', '7', '广发银行');
INSERT INTO T_DATA_DICT VALUES ('95', 'BANK_TYPE', '8', '浦发银行', '1', '8', '浦发银行');
INSERT INTO T_DATA_DICT VALUES ('96', 'BANK_TYPE', '9', '兴业银行', '1', '9', '兴业银行');
INSERT INTO T_DATA_DICT VALUES ('97', 'BANK_TYPE', '10', '民生银行', '1', '10','民生银行');

-- ----------------------------
-- Records T_APP_TYPE
-- ----------------------------
INSERT INTO T_APP_TYPE VALUES ('1', sysdate, 'admin', '图书类型', 'app_sort_img_1.png', 'app_sort_img_on_1.png', '图书', sysdate);
INSERT INTO T_APP_TYPE VALUES ('2', sysdate, 'admin', '商业类型', 'app_sort_img_2.png', 'app_sort_img_on_2.png', '商业', sysdate);
INSERT INTO T_APP_TYPE VALUES ('3', sysdate, 'admin', '教育类型', 'app_sort_img_3.png', 'app_sort_img_on_3.png', '教育', sysdate);
INSERT INTO T_APP_TYPE VALUES ('4', sysdate, 'admin', '娱乐类型', 'app_sort_img_4.png', 'app_sort_img_on_4.png', '娱乐', sysdate);
INSERT INTO T_APP_TYPE VALUES ('5', sysdate, 'admin', '财经类型', 'app_sort_img_5.png', 'app_sort_img_on_5.png', '财经', sysdate);
INSERT INTO T_APP_TYPE VALUES ('6', sysdate, 'admin', '游戏类型', 'app_sort_img_6.png', 'app_sort_img_on_6.png', '游戏', sysdate);
INSERT INTO T_APP_TYPE VALUES ('7', sysdate, 'admin', '健康类型', 'app_sort_img_7.png', 'app_sort_img_on_7.png', '健康', sysdate);
INSERT INTO T_APP_TYPE VALUES ('8', sysdate, 'admin', '生活类型', 'app_sort_img_8.png', 'app_sort_img_on_7.png', '生活', sysdate);
INSERT INTO T_APP_TYPE VALUES ('9', sysdate, 'admin', '医疗类型', 'app_sort_img_9.png', 'app_sort_img_on_8.png', '医疗', sysdate);
INSERT INTO T_APP_TYPE VALUES ('10',sysdate, 'admin', '音乐类型', 'app_sort_img_10.png', 'app_sort_img_on_10.png', '音乐', sysdate);
INSERT INTO T_APP_TYPE VALUES ('11', sysdate, 'admin', '导航类型', 'app_sort_img_11.png', 'app_sort_img_on_11.png', '导航', sysdate);
INSERT INTO T_APP_TYPE VALUES ('12', sysdate, 'admin', '新闻类型', 'app_sort_img_12.png', 'app_sort_img_on_12.png', '新闻', sysdate);
INSERT INTO T_APP_TYPE VALUES ('13',sysdate, 'admin', '摄影类型', 'app_sort_img_13.png', 'app_sort_img_on_13.png', '摄影', sysdate);
INSERT INTO T_APP_TYPE VALUES ('14', sysdate, 'admin', '效率类型', 'app_sort_img_14.png', 'app_sort_img_on_14.png', '效率', sysdate);
INSERT INTO T_APP_TYPE VALUES ('15', sysdate, 'admin', '参考类型', 'app_sort_img_15.png', 'app_sort_img_on_15.png', '参考', sysdate);
INSERT INTO T_APP_TYPE VALUES ('16', sysdate, 'admin', '社交类型', 'app_sort_img_16.png', 'app_sort_img_on_16.png', '社交', sysdate);
INSERT INTO T_APP_TYPE VALUES ('17', sysdate, 'admin', '体育类型', 'app_sort_img_17.png', 'app_sort_img_on_17.png', '体育', sysdate);
INSERT INTO T_APP_TYPE VALUES ('18',sysdate, 'admin', '旅行类型', 'app_sort_img_18.png', 'app_sort_img_on_18.png', '旅行', sysdate);
INSERT INTO T_APP_TYPE VALUES ('19', sysdate, 'admin', '工具类型', 'app_sort_img_19.png', 'app_sort_img_on_19.png', '工具', sysdate);
INSERT INTO T_APP_TYPE VALUES ('20', sysdate, 'admin', '天气类型', 'app_sort_img_20.png', 'app_sort_img_on_20.png', '天气', sysdate);


/**
 * init T_RESOURCE
 */

INSERT INTO T_RESOURCE VALUES ('5', sysdate, 'admin', '系统管理模块', 'SystemManage', '1', '0', '系统管理', '5', sysdate, '', null);
INSERT INTO T_RESOURCE VALUES ('8', sysdate, 'admin', '权限管理', 'permissionManager', '1', '0', '权限管理', '6', sysdate, '', null);
INSERT INTO T_RESOURCE VALUES ('9', sysdate, 'admin', '用户管理', 'userManager', '2', '0', '用户管理', '1', sysdate, 'user!searchUser.action', '8');
INSERT INTO T_RESOURCE VALUES ('10', sysdate, 'admin', '角色管理', 'roleManager', '2', '0', '角色管理', '2', sysdate, 'role!searchRole.action', '8');
INSERT INTO T_RESOURCE VALUES ('11', sysdate, 'admin', '资源管理', 'resManager', '2', '0', '资源管理', '3', sysdate, 'resource!searchRes.action', '8');
INSERT INTO T_RESOURCE VALUES ('12', sysdate, 'admin', '添加新资源', 'addRes', '3', '1', '添加资源', '1', sysdate, 'resource!addRes.action', '11');
INSERT INTO T_RESOURCE VALUES ('13', sysdate, 'admin', '删除资源', 'deleteRes', '3', '1', '删除资源', '2', sysdate, 'resource!deleteRes.action', '11');
INSERT INTO T_RESOURCE VALUES ('14', sysdate, 'admin', '修改资源信息', 'modifyRes', '3', '1', '修改资源', '3', sysdate, 'resource!modifyRes.action', '11');
INSERT INTO T_RESOURCE VALUES ('15', sysdate, 'admin', '添加新角色', 'addRole', '3', '1', '新增角色', '1', sysdate, 'role!addRole.action', '10');
INSERT INTO T_RESOURCE VALUES ('16', sysdate, 'admin', '删除角色信息', 'deleteRole', '3', '1', '角色删除', '2', sysdate, 'role!deleteRole.action', '10');
INSERT INTO T_RESOURCE VALUES ('17', sysdate, 'admin', '修改角色信息', 'modifyRole', '3', '1', '角色修改', '3', sysdate, 'role!modifyRole.action', '10');

INSERT INTO T_RESOURCE VALUES ('18', sysdate, 'admin', '给角色添加资源访问权限', 'rolePermission', '3', '1', '角色授权', '14', sysdate, 'role!rolePermission.action', '10');
INSERT INTO T_RESOURCE VALUES ('19', sysdate, 'admin', 'AP信息管理模块。', 'ApMsgManager', '1', '0', 'AP管理', '0', sysdate, '', null);
INSERT INTO T_RESOURCE VALUES ('20', sysdate, 'admin', 'AP应用管理模块菜单。', 'ApAppManager', '1', '0', '应用管理', '1', sysdate, '', null);
INSERT INTO T_RESOURCE VALUES ('21', sysdate, 'admin', '下载管理模块菜单。', 'downManager', '1', '0', '下载管理', '4', sysdate, '', null);
INSERT INTO T_RESOURCE VALUES ('22', sysdate, 'admin', '资讯管理模块菜单。', 'InformManager', '1', '0', '资讯管理', '3', sysdate, '', null);
INSERT INTO T_RESOURCE VALUES ('23', sysdate, 'admin', '服务管理模块菜单。', 'serviceManager', '1', '0', '服务管理', '2', sysdate, '', null);
INSERT INTO T_RESOURCE VALUES ('24', sysdate, 'admin', '日志管理模块菜单。', 'logManager', '2', '0', '日志管理', '0', sysdate, 'oplogger!doList.action', '5');
INSERT INTO T_RESOURCE VALUES ('26', sysdate, 'admin', '', 'downloadinfo', '2', '0', '下载列表', '1', sysdate, 'dpdownload!doList.action', '21');

INSERT INTO T_RESOURCE VALUES ('29', sysdate, 'admin', '添加用户', 'addUser', '3', '1', '添加用户', '0', sysdate, 'user!addUser.action', '9');
INSERT INTO T_RESOURCE VALUES ('30', sysdate, 'admin', '删除用户', 'deleteUser', '3', '1', '删除用户', '1', sysdate, 'user!doDelete.action', '9');
INSERT INTO T_RESOURCE VALUES ('31', sysdate, 'admin', '修改用户', 'modifyUser', '3', '1', '修改用户', '2', sysdate, 'user!updateUser.action', '9');
INSERT INTO T_RESOURCE VALUES ('34', sysdate, 'admin', '', 'waitCheckApInfo', '2', '0', '待审核AP列表', '0', sysdate, 'dpStaff!doList.action?flag=1', '19');
INSERT INTO T_RESOURCE VALUES ('35', sysdate, 'admin', '', 'allApInfo', '2', '0', 'AP列表', '0', sysdate, 'dpStaff!doList.action?flag=0', '19');
INSERT INTO T_RESOURCE VALUES ('38', sysdate, 'admin', '', 'newsManager', '2', '0', '分类管理', '0', sysdate, 'dpType!list.action', '5');
INSERT INTO T_RESOURCE VALUES ('39', sysdate, 'admin', '资讯列表菜单。', 'doNewsList', '2', '0', '资讯列表', '0', sysdate, 'dpnews!doList.action', '22');
INSERT INTO T_RESOURCE VALUES ('40', sysdate, 'admin', '新增资讯菜单', 'doNewsAdd', '2', '0', '新增资讯', '1', sysdate, 'dpnews!doDisplay.action', '22');

INSERT INTO T_RESOURCE VALUES ('41', sysdate, 'admin', '删除下载', 'doDelDownInfo', '3', '1', '删除下载', '0', sysdate, 'dpdownload!doDelete.action', '26');

INSERT INTO T_RESOURCE VALUES ('43', sysdate, 'admin', '添加资讯按钮', 'doDisplayNews', '3', '1', '添加资讯', '1', sysdate, 'dpnews!doDisplay.action', '39');
INSERT INTO T_RESOURCE VALUES ('44', sysdate, 'admin', '删除资讯', 'doDeleteNews', '3', '1', '删除资讯', '2', sysdate, 'dpnews!doDelete.action', '39');
INSERT INTO T_RESOURCE VALUES ('45', sysdate, 'admin', '编辑资讯', 'doEditNews', '3', '1', '编辑资讯', '3', sysdate, 'dpnews!doDisplay.action', '39');
INSERT INTO T_RESOURCE VALUES ('46', sysdate, 'admin', '资讯上线', 'doDpNewsOnLine', '3', '1', '资讯上线', '4',sysdate, 'dpnews!doOnLine.action', '39');
INSERT INTO T_RESOURCE VALUES ('47', sysdate, 'admin', '', 'notAuditApp', '2', '0', '待审核应用列表', '0',sysdate, 'dpAppInfo!doSearchList.action?flags=1', '20');
INSERT INTO T_RESOURCE VALUES ('48',sysdate, 'admin', '', 'deleteAp', '3', '1', '删除AP信息', '0', sysdate, 'dpStaff!doDelete.action', '35');
INSERT INTO T_RESOURCE VALUES ('49', sysdate, 'admin', '', 'queryApInfo', '3', '1', '查看和审核AP信息跳转', '0',sysdate, 'dpStaff!doDisplay.action', '35');
INSERT INTO T_RESOURCE VALUES ('50', sysdate, 'admin', '', 'PassWordReset', '3', '0', '重置AP密码', '0', sysdate, 'dpStaff!passwordReset.action', '35');
INSERT INTO T_RESOURCE VALUES ('53', sysdate, 'admin', '修改资讯', 'doEditNewsAction', '3', '1', '修改资讯', '2', sysdate, 'dpnews!doEdit.action', '39');
INSERT INTO T_RESOURCE VALUES ('54', sysdate, 'admin', '全部AP应用', 'allAppList', '2', '0', '应用列表', '0', sysdate, 'dpAppInfo!doSearchList.action', '20');
INSERT INTO T_RESOURCE VALUES ('55', sysdate, 'admin', '删除AP应用', 'deleteAppInfo', '3', '1', '删除AP应用', '0', sysdate, 'dpAppInfo!doDelete.action', '54');
INSERT INTO T_RESOURCE VALUES ('56', sysdate, 'admin', '按条件查询AP应用信息', 'findAppInfo', '3', '1', '查询AP应用', '0', sysdate, 'dpAppInfo!doSearchList.action', '54');
INSERT INTO T_RESOURCE VALUES ('57',sysdate, 'admin', '新增编辑分类跳转', 'addType', '3', '1', '新增分类', '0', sysdate, 'dpType!doDisplay.action', '38');
INSERT INTO T_RESOURCE VALUES ('58', sysdate, 'admin', '删除分类', 'deleteType', '3', '1', '删除分类', '0', sysdate, 'dpType!doDelete.action', '38');
INSERT INTO T_RESOURCE VALUES ('64', sysdate, 'admin', '跳转到新增下载界面', 'toDownloadAdd', '2', '0', '新增下载', '0', sysdate, 'dpdownload!doDisplay.action', '21');
INSERT INTO T_RESOURCE VALUES ('65', sysdate, 'admin', 'JS服务列表', 'jsServiceList', '2', '0', 'JS服务列表', '0',sysdate, 'service!doList.action?uspService.uspServType=2', '23');
INSERT INTO T_RESOURCE VALUES ('137', sysdate, 'admin', 'API服务列表', 'apiServiceList', '2', '0', 'API服务列表', '0',sysdate, 'service!doList.action?uspService.uspServType=4', '23');

INSERT INTO T_RESOURCE VALUES ('67', sysdate, 'admin', 'ESB服务', 'esbServiceList', '2', '0', 'ESB服务列表', '0', sysdate, 'service!doList.action?uspService.uspServType=3', '23');
INSERT INTO T_RESOURCE VALUES ('66', sysdate, 'admin', 'ws服务', 'serviceAction', '2', '0', 'WS服务列表', '0',sysdate, 'service!doList.action?uspService.uspServType=1', '23');
INSERT INTO T_RESOURCE VALUES ('77', sysdate, 'admin', '重置用户登录密码', 'passwordRet', '3', '1', '密码重置', '0', sysdate, 'user!passwordReset.action', '9');
INSERT INTO T_RESOURCE VALUES ('79', sysdate, 'admin', '', 'saveCheck', '3', '1', '保存AP审核信息', '0', sysdate, 'dpStaff!doModify.action', '35');
INSERT INTO T_RESOURCE VALUES ('100',sysdate, 'admin', '保存资讯', 'doAddNew', '3', '2', '保存资讯', '4', sysdate, 'dpnews!doAdd.action', '39');
INSERT INTO T_RESOURCE VALUES ('102',sysdate, 'admin', '查询WS服务', 'doSerach', '3', '1', '查询WS服务', '0', sysdate, 'service!doList.action', '66');
INSERT INTO T_RESOURCE VALUES ('103', sysdate, 'admin', '查询JS服务', 'doSearch', '3', '1', '查询JS服务', '0', sysdate, 'service!doList.action', '65');
INSERT INTO T_RESOURCE VALUES ('105', sysdate, 'admin', '下载AP应用', 'doDownload', '3', '1', '下载AP应用', '0', sysdate, 'dpAppInfo!doDownLoad.action', '54');
INSERT INTO T_RESOURCE VALUES ('107', sysdate, 'admin', '审核Ap应用', 'doAuditDpAppInfo', '3', '1', '审核Ap应用', '0', sysdate, 'dpAppInfo!doAudit.action', '54');

INSERT INTO T_RESOURCE VALUES ('108', sysdate, 'admin', '保存下载按钮', 'doDownloadAdd', '3', '1', '保存新增下载', '4',sysdate, 'dpdownload!doAdd.action', '26');
INSERT INTO T_RESOURCE VALUES ('109', sysdate, 'admin', '保存修改下载按钮', 'doDownloadEdit', '3', '1', '修改下载', '3', sysdate, 'dpdownload!doEdit.action', '26');

INSERT INTO T_RESOURCE VALUES ('110',sysdate, 'admin', '上架', 'appOnline', '3', '1', '上架', '0', sysdate, 'dpAppInfo!doOnline.action', '54');
INSERT INTO T_RESOURCE VALUES ('114',sysdate, 'admin', '查看api服务信息', 'doDetailApi', '3', '1', '查看api信息', '0', sysdate, 'service!doDetail.action?uspService.uspServType=4', '137');
INSERT INTO T_RESOURCE VALUES ('119', sysdate, 'admin', '查看ESB服务信息', 'doDetailESB', '3', '1', '查看esb信息', '0', sysdate, 'service!doDetail.action?uspService.uspServType=3', '67');

INSERT INTO T_RESOURCE VALUES ('115', sysdate, 'admin', '查看WS服务信息', 'doDetailWS', '3', '1', '查看WS服务信息', '0',sysdate, 'service!doDetail.action?uspService.uspServType=1', '66');
INSERT INTO T_RESOURCE VALUES ('116', sysdate, 'admin', '', 'doList', '3', '1', '应用展示', '0', sysdate, 'dpAppInfo!doList.action', '54');
INSERT INTO T_RESOURCE VALUES ('117', sysdate, 'admin', '', 'doAdd', '3', '1', '增加分类', '0', sysdate, 'dpType!doAdd.action', '38');
INSERT INTO T_RESOURCE VALUES ('118', sysdate, 'admin', '', 'doEdit', '3', '1', '修改分类', '0', sysdate, 'dpType!doEdit.action', '38');
INSERT INTO T_RESOURCE VALUES ('124', sysdate, 'admin', '编辑分类页面跳转', 'gotoEditType', '3', '1', '编辑分类页面跳转', '1',sysdate, 'dpType!doDisplay.action', '38');

INSERT INTO T_RESOURCE VALUES ('125', sysdate, 'admin', '跳转到编辑下载界面', 'toDownloadEdit', '3', '1', '跳转到编辑下载界面', '2',sysdate, 'dpdownload!doDisplay.action', '26');
INSERT INTO T_RESOURCE VALUES ('136', sysdate, 'admin', '服务附件下载', 'doServiceDownload', '3', '1', '服务附件下载', '0',sysdate, 'service!doDownLoad.action', '66');
INSERT INTO T_RESOURCE VALUES ('138',sysdate, 'admin', '查看JS服务信息', 'doDetailJS', '3', '1', '查看JS信息', '0', sysdate, 'service!doDetail.action?uspService.uspServType=2', '65');
/* 资源 */


/**
 * init T_USER
 */
INSERT INTO T_USER VALUES ('1',sysdate, 'admin', 'admin@coship.com', '0b9530c150355c9577aae21764f319c3', '超级管理员', 'Y', '15967254621', sysdate, 'admin');
/**
 * init T_ROLE
 */
INSERT INTO T_ROLE VALUES ('1',null, 'admin', '所有权限', '超级管理员', sysdate);
/**
 * init T_USER_ROLE
 */
INSERT INTO T_USER_ROLE VALUES ('1', '1', '1');

/**
 *init T_ROLE_RESOURCE
 */
-- ----------------------------
INSERT INTO T_ROLE_RESOURCE VALUES ('1', '1', '57');
INSERT INTO T_ROLE_RESOURCE VALUES ('2', '1', '20');
INSERT INTO T_ROLE_RESOURCE VALUES ('3', '1', '103');
INSERT INTO T_ROLE_RESOURCE VALUES ('4', '1', '11');
INSERT INTO T_ROLE_RESOURCE VALUES ('5', '1', '117');
INSERT INTO T_ROLE_RESOURCE VALUES ('6', '1', '29');
INSERT INTO T_ROLE_RESOURCE VALUES ('7', '1', '118');
INSERT INTO T_ROLE_RESOURCE VALUES ('8', '1', '116');
INSERT INTO T_ROLE_RESOURCE VALUES ('9', '1', '109');
INSERT INTO T_ROLE_RESOURCE VALUES ('10', '1', '48');
INSERT INTO T_ROLE_RESOURCE VALUES ('11', '1', '107');
INSERT INTO T_ROLE_RESOURCE VALUES ('12', '1', '14');
INSERT INTO T_ROLE_RESOURCE VALUES ('13', '1', '38');
INSERT INTO T_ROLE_RESOURCE VALUES ('14', '1', '50');
INSERT INTO T_ROLE_RESOURCE VALUES ('15', '1', '17');
INSERT INTO T_ROLE_RESOURCE VALUES ('16', '1', '21');
INSERT INTO T_ROLE_RESOURCE VALUES ('17', '1', '22');
INSERT INTO T_ROLE_RESOURCE VALUES ('18', '1', '56');
INSERT INTO T_ROLE_RESOURCE VALUES ('19', '1', '49');
INSERT INTO T_ROLE_RESOURCE VALUES ('20', '1', '8');
INSERT INTO T_ROLE_RESOURCE VALUES ('21', '1', '100');
INSERT INTO T_ROLE_RESOURCE VALUES ('22', '1', '66');
INSERT INTO T_ROLE_RESOURCE VALUES ('23', '1', '23');
INSERT INTO T_ROLE_RESOURCE VALUES ('24', '1', '43');
INSERT INTO T_ROLE_RESOURCE VALUES ('25', '1', '65');
INSERT INTO T_ROLE_RESOURCE VALUES ('26', '1', '41');
INSERT INTO T_ROLE_RESOURCE VALUES ('27', '1', '114');
INSERT INTO T_ROLE_RESOURCE VALUES ('28', '1', '77');
INSERT INTO T_ROLE_RESOURCE VALUES ('29', '1', '47');
INSERT INTO T_ROLE_RESOURCE VALUES ('30', '1', '30');
INSERT INTO T_ROLE_RESOURCE VALUES ('31', '1', '124');
INSERT INTO T_ROLE_RESOURCE VALUES ('32', '1', '13');
INSERT INTO T_ROLE_RESOURCE VALUES ('33', '1', '35');
INSERT INTO T_ROLE_RESOURCE VALUES ('34', '1', '19');
INSERT INTO T_ROLE_RESOURCE VALUES ('35', '1', '39');
INSERT INTO T_ROLE_RESOURCE VALUES ('36', '1', '18');
INSERT INTO T_ROLE_RESOURCE VALUES ('37', '1', '125');
INSERT INTO T_ROLE_RESOURCE VALUES ('38', '1', '12');
INSERT INTO T_ROLE_RESOURCE VALUES ('39', '1', '40');
INSERT INTO T_ROLE_RESOURCE VALUES ('40', '1', '54');
INSERT INTO T_ROLE_RESOURCE VALUES ('41', '1', '10');
INSERT INTO T_ROLE_RESOURCE VALUES ('42', '1', '24');
INSERT INTO T_ROLE_RESOURCE VALUES ('43', '1', '115');
INSERT INTO T_ROLE_RESOURCE VALUES ('44', '1', '5');
INSERT INTO T_ROLE_RESOURCE VALUES ('45', '1', '16');
INSERT INTO T_ROLE_RESOURCE VALUES ('46', '1', '102');
INSERT INTO T_ROLE_RESOURCE VALUES ('47', '1', '110');
INSERT INTO T_ROLE_RESOURCE VALUES ('48', '1', '79');
INSERT INTO T_ROLE_RESOURCE VALUES ('49', '1', '26');
INSERT INTO T_ROLE_RESOURCE VALUES ('50', '1', '53');
INSERT INTO T_ROLE_RESOURCE VALUES ('51', '1', '9');
INSERT INTO T_ROLE_RESOURCE VALUES ('52', '1', '15');
INSERT INTO T_ROLE_RESOURCE VALUES ('53', '1', '64');
INSERT INTO T_ROLE_RESOURCE VALUES ('54', '1', '58');
INSERT INTO T_ROLE_RESOURCE VALUES ('55', '1', '34');
INSERT INTO T_ROLE_RESOURCE VALUES ('56', '1', '46');
INSERT INTO T_ROLE_RESOURCE VALUES ('57', '1', '44');
INSERT INTO T_ROLE_RESOURCE VALUES ('58', '1', '55');
INSERT INTO T_ROLE_RESOURCE VALUES ('59', '1', '31');
INSERT INTO T_ROLE_RESOURCE VALUES ('60', '1', '105');
INSERT INTO T_ROLE_RESOURCE VALUES ('61', '1', '45');
INSERT INTO T_ROLE_RESOURCE VALUES ('62', '1', '119');
INSERT INTO T_ROLE_RESOURCE VALUES ('63', '1', '67');
INSERT INTO T_ROLE_RESOURCE VALUES ('64', '1', '108');
INSERT INTO T_ROLE_RESOURCE VALUES ('65', '1', '136');
INSERT INTO T_ROLE_RESOURCE VALUES ('66', '1', '137');
INSERT INTO T_ROLE_RESOURCE VALUES ('67', '1', '138');

commit;

