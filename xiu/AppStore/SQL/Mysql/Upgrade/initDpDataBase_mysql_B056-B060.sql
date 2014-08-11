-- B057 t_dp_app_info表增加字段. 
alter table t_dp_app_info add column c_sort_num int(6) DEFAULT 0 comment '排序序号';
alter table t_dp_app_info add column c_op_mode int(11) DEFAULT 1 comment '操作类型';

-- 初始化菜单和权限. 
INSERT INTO t_resource VALUES ('2c9e97a842d6463c0142d657262c0007', '2013-12-09 15:51:44', 'admin', '应用排序', 'sortApp', 3, 1, '应用排序', 2, '2013-12-09 15:51:44', 'dpAppInfo!doSort.action', '126');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('2c9e97a842d6463c0142d657c5600036', '1', '2c9e97a842d6463c0142d657262c0007');

-- 操作类型分类. 
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('504', '2013-12-10 19:25:22', 'admin', 'ROOT_TYPE', 'OP_MODE_TYPE', '分类描述', NULL, NULL, '操作类型分类', '2013-12-10 19:25:22');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('2c9e97a842da6b190142da6d598d0001', '2013-12-10 10:54:28', 'admin', 'OP_MODE_TYPE', '000000000001', '', NULL, NULL, '摸摸看', '2013-12-10 10:55:18');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('2c9e97a842da6b190142da6d9a8c0003', '2013-12-10 10:54:45', 'admin', 'OP_MODE_TYPE', '000000000010', '', NULL, NULL, '遥控器', '2013-12-10 10:55:26');
INSERT INTO t_dp_type (`C_ID`, `C_CREATE_DATE`, `C_CREATE_USER`, `C_PARENT_TYPE_CODE`, `C_TYPE_CODE`, `C_TYPE_DESC`, `C_TYPE_IMG1`, `C_TYPE_IMG2`, `C_TYPE_NAME`, `C_UPDATE_DATE`) VALUES ('2c9e97a842da6b190142da6dca360005', '2013-12-10 10:54:57', 'admin', 'OP_MODE_TYPE', '000000000100', '', NULL, NULL, '体感', '2013-12-10 10:55:30');

-- 初始化现有app的操作类型. 
update t_dp_app_info set c_op_mode = 2 where c_app_name = '火柴人足球';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '极速狂飙 Fast Racing';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '小飞侠';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '新石器时代';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '公路冲锋';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '疯狂漂移';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '重力小子';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '战神无双暗黑之怒';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '玩具轰炸机';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '极限方程式赛车';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '弹弓赛车';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '帮助我飞';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '终极极速赛车';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '雷霆赛车2';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '越狱';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '萌军敢死队';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'GP赛艇';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '贪玩的无尾熊';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝贝算算术';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝识自然现象';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '儿歌动画精选';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝幼儿园';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '超音速飞行';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '8684地铁';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '酷狗音乐';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '8684公交';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '毛毛飞行棋';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '我的猫呢';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '10天记忆1000单词第一辑';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '3D桌球';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '跳动的蜘蛛';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '京东商城tv版';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '极速狂飙';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '烈日赛艇';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '沙滩车闪电战';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '重力滚珠';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'Velox特技竞速';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '艾诺迪亚3';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '地下城之暗黑勇士';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '蝴蝶流星剑';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '龙珠祖玛';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '小小推理家';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '瞎子忍者';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '大鱼吃小鱼';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '人鱼球球';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '重力感应球';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '酷我K歌';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '飞行小松鼠';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '顶级卡车';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '3D隐形战斗直升机';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '3D光速赛车';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '大脚车越野赛';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '咔布咔谁偷了我的内裤';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '猎魔勇士2';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '泽诺尼亚传奇4';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '尖峰滑雪';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '赛车大战僵尸';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '极限摩托3';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '短程极速赛车2';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '滑雪大冒险';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝成长乐园';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'VIVA畅读';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '谁被吃了';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '三及第';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '熊猫快跑';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '数学农场';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '艾尔的旅行';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝贝拼拼看';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝学交通工具';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝认时钟';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝生日派对';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝贝画画看';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝来找茬';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝学反义词';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'OfficeSuite';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'FlashPlayer';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'MyScript手写计算器';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '3D过山车';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '神奇的阿力';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '3D跑酷';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '高速骑士';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '3D终极狂飙2';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '黄蜂防御';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '天朝小将';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '乱斗堂';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '滑板少年';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'QQ降龙';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '勇者使命';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '削积木';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '祭亡灵杀手';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '艾诺迪亚4';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '地下城勇士与魔女';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '地下城与剑灵觉醒';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'Real football 2013';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '捣蛋猪';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'KungFu';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '捕鱼达人';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '找你妹';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '漂移都市';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '英雄战魂';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '新浪微博';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '腾讯微博';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '数学乐园4';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '数学乐园3';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '数学乐园2';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '数学乐园1';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '蜘蛛纸牌';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '挖财';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝石矿工';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '水果忍者';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '极品钢琴';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '愤怒的小鸟_星球大战';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '青蛙跳水';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '欢乐推箱子';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '三国杀';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'QQ斗牛';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '要塞围城';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '超音速';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '天堂岛';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '战争高塔';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '号百商旅';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '口袋购宝';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '故事口袋 听听';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '摩卡时光';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '高德地图';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '易图';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'WPS Office';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '网易云阅读';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '百度相册';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'QQ音乐';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '豆瓣FM';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '黏黏世界';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '职业桌球';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '瓷砖谜题';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '云狐迪士尼乐园';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '疯狂喷气机';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '超级玛丽';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '极限摩托2';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '滑雪少年';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '奔跑小孩';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '保卫萝卜';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '网易公开课';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '当当网';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '僵尸飞翔';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '僵尸快跑';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '和风物语';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '坚守阵地';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '发条骑士';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '贪婪的蜘蛛';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '等分达人';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '极限摩托';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '雷霆赛车';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '武士复仇2';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '航海时代';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '明珠三国';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '三国大富翁';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '21点';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝石仙境';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '涂鸦泡泡';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '喜羊羊益智园之太鼓律动';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '刀锋战士';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '黄金矿工';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '大富翁';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '愤怒的小鸟';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '阿帕奇直升机战斗';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '战地英雄';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '在线K歌';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '星座运势';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝童谣';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝童谣2';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝过春节';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝贴贴纸';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝服装秀';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝爱卫生';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝爱整理';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝家庭树';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝认鞋子';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝学数字';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝学蔬菜';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝学身体部位';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝学日用品';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝学家具电器';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝宝涂鸦';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'QQ鱼';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝贝听听';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '白雪公主';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '青蛙王子';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '宝贝涂涂看';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '智能家居';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '义方电视课堂';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '家庭ktv';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '开心网';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '鲜果联播';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '国学宝典';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '谜语大全';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '大麦票务';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '邮乐tv版';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '支付宝';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '乐视影视';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '腾讯视频';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '搜狐视频';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '看客影视';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '悦视频';
update t_dp_app_info set c_op_mode = 2 where c_app_name = '爱奇艺';
update t_dp_app_info set c_op_mode = 2 where c_app_name = 'PPS';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '棋乐诈金花';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '棋乐大众斗地主';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '棋乐血战麻将';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '棋乐牛牛';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '棋乐马股';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '棋乐黄金矿工';
update t_dp_app_info set c_op_mode = 1 where c_app_name = 'ZAKER';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '万花筒相册';
update t_dp_app_info set c_op_mode = 1 where c_app_name = 'K客';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '大智慧';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '爱看动漫乐园';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '汽车之家';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '会计网校';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '儿童音乐台';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '航班管家';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '跟我背单词';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '宝宝音乐会';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '智能航班火车中转';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '美食杰';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '气质测试';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '郑多燕减肥操';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '旅游攻略';
update t_dp_app_info set c_op_mode = 1 where c_app_name = 'X计划';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '植物大战僵尸';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '瑜伽达人';
update t_dp_app_info set c_op_mode = 1 where c_app_name = 'ES文件浏览器';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '美食风情';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '皇帝';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '乐健康';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '世界旅游';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '疯狂牛仔';
update t_dp_app_info set c_op_mode = 1 where c_app_name = 'K词达人';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '食游记';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '乐1网';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '好豆菜谱';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '美食天下';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '人人网';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '炸金花';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '边锋杭州麻将';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '棋类大师';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '小奥棋牌城';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '快乐斗地主';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '联众斗地主';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '淘宝购物';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '同花顺';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '移动亲子园';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '苏宁易购tv版';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '家庭常备药箱';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '豆果美食tv版';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '万年历';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '操盘手';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '亲宝儿歌';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '悟空识字';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '有道词典';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '家庭用药';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '百度地图';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '多米音乐';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '义方电视学堂';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '摩天大楼';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '华容道';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '江湖谋生记';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '扫雷';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '美少女麻将馆';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '单机欢乐斗地主';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '百度电视云';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '泰捷视频';
update t_dp_app_info set c_op_mode = 1 where c_app_name = 'PPTV';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '球迷TV';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '电视猫';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '快手看片';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '优酷TV';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '虾米音乐';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '兔子视频TV版';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '新浪新闻';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '腾讯新闻';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '人民日报';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '欧朋浏览器';
update t_dp_app_info set c_op_mode = 1 where c_app_name = '遨游傲游浏览器TV';





-- b058 新增标签信息表. 
create table `t_tag_info` (
  `c_id` varchar(32) not null,
  `c_name` varchar(16) not null,
  `c_status` int(11) not null default '1',
  `c_create_time` datetime,
  primary key (`c_id`)
) engine=innodb default charset=utf8;

-- b058 新增应用标签关系表. 
create table `t_app_tag_relation` (
  `c_id` varchar(32) not null,
  `c_app_id` varchar(32) not null,
  `c_tag_id` varchar(32) not null,
  `c_sort_num` int(4) not null,
  primary key (`c_id`),
  key `fk_r_app_tag_appid` (`c_app_id`),
  constraint `fk_r_app_tag_appid` foreign key (`c_app_id`) references `t_dp_app_info` (`c_id`),
  key `fk_r_app_tag_tagid` (`c_tag_id`),
  constraint `fk_r_app_tag_tagid` foreign key (`c_tag_id`) references `t_tag_info` (`c_id`)
) engine=innodb default charset=utf8;





-- b059. 
-- 增加应用信息表的字段. 
alter table t_dp_app_info add column c_hand_down_count int default 0 comment '人工增加下载次数';
alter table t_dp_app_info add column c_hand_avg_score double default 0 comment '人工增加平均评分';

-- 修改应用标签关联表的字段. 
alter table `t_app_tag_relation`
modify column `c_app_id`  varchar(32) character set utf8 collate utf8_general_ci null after `c_id`,
add column `c_app_package_name`  varchar(255) null after `c_sort_num`;

-- 增加菜单和权限. 
INSERT INTO t_resource VALUES ('8a8a9f2b43802ba601438032b80e0002', '2014-01-11 15:27:24', 'admin', '缓存管理模块菜单', 'cacheManager', 2, 0, '缓存管理', 4, '2014-01-11 15:27:24', 'systemAdmin!cacheHome.action', '5');
INSERT INTO t_resource VALUES ('8a8a9f2b43802ba6014380352d330005', '2014-01-11 15:27:24', 'admin', '刷新缓存', 'refreshCache', 3, 1, '刷新缓存', 1, '2014-01-11 15:27:24', 'systemAdmin!refreshCache.action', '8a8a9f2b43802ba601438032b80e0002');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8a9f2b43802ba601438035a57d0026', '1', '8a8a9f2b43802ba601438032b80e0002');
INSERT INTO t_role_resource (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`) VALUES ('8a8a9f2b43802ba601438035ab280080', '1', '8a8a9f2b43802ba6014380352d330005');