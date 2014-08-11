-- 2013-08-28 907632/wangzhenghui
-- 增加日志记录表记录应用商店客户端操作记录
CREATE TABLE t_user_operation (
	c_id VARCHAR (32) PRIMARY KEY NOT NULL COMMENT '主键',
	c_opt_type INT COMMENT '操作类型：1、启动  2、退出 3、专题
                                    4、分类、5、推荐 6、应用详情浏览  
                                    7、浏览其它 8、安装 9、卸载  
                                    10、下载 ',
	c_opt_time datetime COMMENT '操作时间',
	c_opt_content VARCHAR (255) COMMENT '记录用户访问的情况：点击菜单则记录菜单的名称。其它则记录相应的唯一标识，如应用名称',
	c_opt_content_id VARCHAR (255) COMMENT '应用包名/专题id/分类id/推荐id ',
	c_device_mac VARCHAR (255) COMMENT '设备mac地址',
	c_param1 VARCHAR (255) COMMENT '扩展参数',
	c_param2 VARCHAR (255) COMMENT '扩展参数',
	c_param3 VARCHAR (255) COMMENT '扩展参数',
	KEY `idx_user_optime` (`c_opt_time`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

