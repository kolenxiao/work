-- B054 t_dp_app_info表增加字段
alter table t_dp_app_info add column c_subject_poster varchar(128);
-- 新增精选页板块表.
CREATE TABLE `t_app_recommend_panel` (
  `C_ID` varchar(32) NOT NULL,
  `C_PANEL_NAME` varchar(255) NOT NULL DEFAULT '',
  `C_LAYOUT_TYPE` int(11) NOT NULL DEFAULT '1',
  `C_PANEL_DESC` varchar(255) DEFAULT NULL,
  `C_SORT_NUM` int(11) DEFAULT NULL,
  `C_STATUS` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 新增精选页板块元素表.
CREATE TABLE `t_app_recommend_panel_item` (
  `C_ID` varchar(32) NOT NULL,
  `C_ITEM_DESC` varchar(255) DEFAULT NULL,
  `C_ITEM_NAME` varchar(255) DEFAULT NULL,
  `C_ITEM_POSTER` varchar(255) DEFAULT NULL,
  `C_SORT_NUM` int(11) DEFAULT NULL,
  `C_STATUS` int(11) NOT NULL DEFAULT '1',
  `C_TYPE` int(11) NOT NULL DEFAULT '1',
  `C_TYPE_VALUE` varchar(255) NOT NULL DEFAULT '',
  `C_PANEL_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`C_ID`),
  KEY `FKA7B6151AD7E5A263` (`C_PANEL_ID`),
  CONSTRAINT `FKA7B6151AD7E5A263` FOREIGN KEY (`C_PANEL_ID`) REFERENCES `t_app_recommend_panel` (`C_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 精选页版块和元素
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

