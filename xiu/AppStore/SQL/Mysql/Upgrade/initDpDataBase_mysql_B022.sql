-- 2012-12-18 zhengxinlian/96976
-- 去除隐式应用的权限
delete from T_ROLE_RESOURCE where C_ID='78';
delete from T_ROLE_RESOURCE where C_ID='77';

-- 2012-12-25 zhengxinlian/96976
-- 调整客户端管理后台菜单的顺序
update T_RESOURCE set C_ORDER_FIELD=3 where C_ID='20';
update T_RESOURCE set C_ORDER_FIELD=4 where C_ID='22';
update T_RESOURCE set C_ORDER_FIELD=5 where C_ID='21';
update T_RESOURCE set C_ORDER_FIELD=6 where C_ID='8a8af59c39e8c3640139e8c70ea90003';
update T_RESOURCE set C_ORDER_FIELD=7 where C_ID='8a8af5a53afc7b0b013afc7ecf860002';
update T_RESOURCE set C_ORDER_FIELD=8 where C_ID='8';
update T_RESOURCE set C_ORDER_FIELD=9 where C_ID='5';

-- 2012-12-25 zhengxinlian/96976
-- 增加资讯和文档的基本脚本
INSERT INTO t_dp_type
   (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`)
VALUES
   ('69', '2011-10-14 11:24:28', 'admin', 'NEWS_TYPE', '1', '热点资讯', 'hotspot.png', 'hotspot.png', '热点资讯', '2011-10-14 11:24:28');

INSERT INTO t_dp_type
   (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`)
VALUES
   ('70', '2011-10-14 11:25:58', 'admin', 'NEWS_TYPE', '2', '平台公告', 'notice.png', 'notice.png', '平台公告', '2011-10-14 11:25:58');

INSERT INTO t_dp_type
   (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`)
VALUES
   ('73', '2011-10-14 11:26:41', 'admin', 'NEWS_TYPE', '5', '传媒动态', 'movement.png', 'movement.png', '传媒动态', '2011-10-14 11:26:41');

INSERT INTO t_dp_type
   (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`)
VALUES
   ('75', '2011-10-14 11:27:03', 'admin', 'NEWS_TYPE', '7', '数字电视', 'tv.png', 'tv.png', '数字电视', '2011-10-14 11:27:03');

INSERT INTO t_dp_type
   (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`)
VALUES
   ('77', '2011-10-14 11:27:33', 'admin', 'NEWS_TYPE', '9', '技术交流', 'exchange.png', 'exchange.png', '技术交流', '2011-10-14 11:27:33');

INSERT INTO t_dp_type
   (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`)
VALUES ('84', '2011-10-15 15:16:03', 'admin', 'DOCUMENT_TYPE', '10', 'Java', 'document.png', 'document.png', 'Java', '2011-10-15 15:16:03');

INSERT INTO t_dp_type
   (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`)
VALUES ('86', '2011-10-15 16:52:56', 'admin', 'DOCUMENT_TYPE', '12', 'Android', 'document.png', 'document.png', 'Android', '2011-10-15 16:52:56');

-- 2012-12-25 zhengxinlian/96976
-- 删除下载的外键索引
alter table T_DP_DOWNLOAD_INFO drop foreign key FKFCE7FC1D6B053808;
alter table t_dp_download_info drop index FKFCE7FC1D6B053808 ;

alter table T_DP_APP_INFO add C_VERSION_CODE int default 0;

-- 2012-12-27 zhengxinlian/96976
-- 注册FAQ的资讯类型
INSERT INTO t_dp_type
   (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`)
VALUES
   ('1000', '2011-10-14 11:27:33', 'admin', 'NEWS_TYPE', '9', '常见FAQ', 'exchange.png', 'exchange.png', '常见FAQ', '2011-10-14 11:27:33');

update T_DP_TYPE set C_CREATE_DATE = now();
update T_DP_TYPE set C_UPDATE_DATE = now();


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


