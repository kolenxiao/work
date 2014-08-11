/*
 * 数据库创建语句. 
 */
    drop table if exists t_app_comment_info;
    drop table if exists t_app_recommend;
    drop table if exists t_app_subject_type;
    drop table if exists t_attachment_file;
    drop table if exists t_dp_app_info;
    drop table if exists t_dp_audit_record;
    drop table if exists t_dp_download_info;
    drop table if exists t_dp_news;
    drop table if exists t_dp_staff;
    drop table if exists t_dp_type;
    drop table if exists t_op_logger;
    drop table if exists t_resource;
    drop table if exists t_role;
    drop table if exists t_role_resource;
    drop table if exists t_subject_appinfo_relation;
    drop table if exists t_user;
    drop table if exists t_user_role;
	drop table if exists t_my_app;
	drop table if exists t_my_favorite;
	drop table if exists t_app_certificate;
	drop table if exists t_app_sign;
	drop table if exists t_app_store_client;
	drop table if exists t_implicit_app;
	drop table if exists t_device_info;
	drop table if exists t_app_type_recommend;
	drop table if exists t_device_info;
	drop table if exists t_user_operation;
	drop table if exists t_syn_command;
	drop table if exists t_user_feedback;
	drop table if exists t_app_recommend_panel;
	drop table if exists t_app_recommend_panel_item;
	drop table if exists t_tag_info;
	drop table if exists t_app_tag_relation;

    create table t_app_comment_info (
        c_id varchar(32) not null,
        c_comment_content longtext,
        c_comment_user_name varchar(255),
        c_comment_user_id varchar(255),
        c_create_date datetime,
        c_score integer not null,
        c_app_id varchar(32),
        c_app_package_name longtext,
        primary key (c_id)
    ) engine=innodb;

    create table t_app_recommend (
        c_id varchar(32) not null,
        c_app_recommendctime datetime,
        c_create_user varchar(255),
        c_app_id varchar(32),
        c_sort double default 0,
        c_type_id varchar(32),
        primary key (c_id)
    ) engine=innodb;

    create table t_app_subject_type (
        c_id varchar(32) not null,
        c_create_date datetime,
        c_create_user varchar(255),
        c_subject_desc longtext,
        c_subject_img varchar(255),
        c_subject_name varchar(255),
        c_update_date datetime,
        c_visible_flag int default 1,
        primary key (c_id)
    ) engine=innodb;

    create table t_attachment_file (
        c_id varchar(32) not null,
        c_create_date datetime,
        c_file_desc longtext,
        c_file_name varchar(128),
        c_file_save_name varchar(128),
        c_file_size bigint not null,
        c_file_type varchar(255),
        c_dpappinfo_id varchar(32),
        primary key (c_id)
    ) engine=innodb;

    create table t_dp_app_info (
        c_id varchar(32) not null,
        c_app_desc longtext,
        c_app_file_package longtext,
        c_app_name varchar(64),
        c_app_name_pinyin varchar(64),
        c_app_status varchar(4),
        c_create_time datetime,
        c_average_score double precision not null,
        c_developer varchar(255),
        c_dp_staff_id varchar(32),
        c_language varchar(255),
        c_price double precision not null,
        c_system varchar(255),
        c_update_time datetime,
        c_version varchar(255),
        c_type_id varchar(32),
        c_user_id varchar(32),
        c_version_code int default 0,
        primary key (c_id)
    ) engine=innodb;

    create table t_dp_audit_record (
        c_id varchar(32) not null,
        c_assessor varchar(32),
        c_audit_date datetime,
        c_audit_flag varchar(32),
        c_audit_option longtext,
        c_audit_record_id varchar(255),
        c_audit_result varchar(4),
        primary key (c_id)
    ) engine=innodb;

    create table t_dp_download_info (
        c_id varchar(32) not null,
        c_create_user varchar(64),
        c_ctime datetime,
        c_download_desc longtext,
        c_doc_source varchar(128),
        c_doc_source_url varchar(128),
        c_download_name varchar(200),
        c_show_index varchar(2),
        c_update_time datetime,
        c_attach_file_id varchar(32),
        c_type_id varchar(32),
        primary key (c_id)
    ) engine=innodb;

    create table t_dp_news (
        c_id varchar(32) not null,
        c_create_user varchar(64),
        c_news_content longtext,
        c_news_create_time datetime,
        c_news_order_id integer not null,
        c_news_source varchar(128),
        c_news_source_url varchar(64),
        c_news_ststus varchar(8),
        c_news_summary longtext,
        c_news_title varchar(128),
        c_update_time datetime,
        c_type_id varchar(32),
        c_click_time int(10) default '0',
        primary key (c_id)
    ) engine=innodb;

    create table t_dp_staff (
        c_id varchar(32) not null,
        c_address varchar(255),
        c_bank_account_name varchar(128),
        c_bank_card_num varchar(128),
        c_bank_name varchar(32),
        c_begin_dp_staff_time datetime,
        c_birthday datetime,
        c_biz_license varchar(255),
        c_brief_intro longtext,
        c_corp_name varchar(255),
        c_developer_type_code varchar(255),
        c_email varchar(255),
        c_head_icon varchar(64),
        c_identity_card varchar(30),
        c_identity_card_type varchar(255),
        c_identity_img varchar(64),
        c_mobile_num varchar(16),
        c_nick_name varchar(20),
        c_pass_word varchar(64) not null,
        c_pay_method varchar(255),
        c_phone_num varchar(255),
        c_post_code varchar(255),
        c_real_name varchar(40),
        c_sex varchar(255),
        c_staff_status varchar(4),
        c_update_dp_staff_time datetime,
        c_user_name varchar(20) not null,
        primary key (c_id)
    ) engine=innodb;

    create table t_dp_type (
        c_id varchar(32) not null,
        c_create_date datetime,
        c_create_user varchar(64),
        c_parent_type_code varchar(255),
        c_type_code varchar(255),
        c_type_desc varchar(64),
        c_type_img1 varchar(255),
        c_type_img2 varchar(255),
        c_type_name varchar(32),
        c_update_date datetime,
        c_visible_flag int default 1,
        primary key (c_id)
    ) engine=innodb;

    create table t_op_logger (
        c_id varchar(32) not null,
        c_business_name varchar(32),
        c_client_ip varchar(15),
        c_description longtext,
        c_operation_date datetime,
        c_operation_type varchar(8),
        c_operator_name varchar(16),
        primary key (c_id)
    ) engine=innodb;

    create table t_resource (
        c_id varchar(32) not null,
        c_created_date datetime,
        c_created_user varchar(64),
        c_description varchar(200),
        c_en_name varchar(50) not null unique,
        c_level integer,
        c_menu_button integer,
        c_name varchar(200),
        c_order_field integer,
        c_updated_date datetime,
        c_url varchar(200),
        c_parent_id varchar(32),
        primary key (c_id)
    ) engine=innodb;

    create table t_role (
        c_id varchar(32) not null,
        c_created_date datetime,
        c_created_user varchar(64),
        c_description varchar(200),
        c_name varchar(64),
        c_updated_date datetime,
        primary key (c_id)
    ) engine=innodb;

    create table t_role_resource (
        c_id varchar(32) not null,
        c_role_res_id varchar(32),
        c_res_role_id varchar(32),
        primary key (c_id)
    ) engine=innodb;

    create table t_subject_appinfo_relation (
    	c_id varchar(32) not null,
        c_subject_id varchar(32) not null,
        c_app_id varchar(32) not null,
        c_sort double default 0,
        primary key (c_id)
    ) engine=innodb;


    create table t_user (
        c_id varchar(32) not null,
        c_created_date datetime,
        c_created_user varchar(64),
        c_email varchar(64),
        c_password varchar(64),
        c_real_name varchar(64),
        c_status varchar(1),
        c_telephone varchar(20),
        c_updated_date datetime,
        c_user_name varchar(64) not null unique,
        primary key (c_id)
    ) engine=innodb;

    create table t_user_role (
        c_id varchar(32) not null,
        c_user_role_id varchar(32),
        c_role_user_id varchar(32),
        primary key (c_id)
    ) engine=innodb;


create table t_my_app (
  c_id varchar(32) not null,
  c_add_time datetime,
  c_app_id varchar(32),
  c_app_package_name longtext,
  c_uid varchar(32),
  primary key (c_id)
) engine=innodb;


create table t_my_favorite (
  c_id varchar(32) not null,
  c_app_id varchar(32),
  c_favorite_time datetime,
  c_uid varchar(32),
   primary key (c_id)
) engine=innodb;

 create table t_app_certificate (
        c_id varchar(32) not null,
		c_certificate_name varchar(128),
		c_certificate_src_name varchar(128),
		c_certificate_save_name varchar(128),
		c_private_key_src_name varchar(128),
		c_private_key_save_name varchar(128),
		c_revoke_src_name varchar(128),
		c_revoke_save_name varchar(128),
		c_certificate_type varchar(4),
		c_certificate_desc longtext,
		c_revoke_flag varchar(4),
		c_serial_number varchar(2000),
		c_not_before datetime,
		c_not_after datetime,
		c_this_update datetime,
		c_next_update datetime,
		c_created_date datetime,
		c_updated_date datetime,
        c_created_user varchar(64),
        c_is_default  int default 0,
        primary key (c_id)
    ) engine=innodb;

create table t_app_sign (
  c_id varchar(32) not null,
  c_app_id varchar(32),
  c_app_cert_id varchar(32),
  c_create_time datetime,
  primary key (c_id)
) engine=innodb;

create table t_implicit_app (
    c_id varchar(32) not null,
    c_app_name varchar(255),
    c_version_code int,
    c_file_name varchar(255),
    c_apk_file_save_path varchar(255),
    c_system varchar(255),
    c_status varchar(255),
    c_create_time datetime,
    c_deploy_time datetime,
    c_system_flag varchar(255),
    c_app_file_package varchar(255),
    c_teminal_num varchar(255),
    c_implicit_type varchar(255),
    c_description varchar(255),
    c_app_cert_id varchar(32),
    primary key (c_id)
) engine=innodb;

	create table t_app_store_client (
	    c_id varchar(32) not null,
	    c_apk_file_save_path varchar(255),
	    c_app_name varchar(255),
	    c_create_time datetime,
	    c_status varchar(255),
	    c_system int,
	    c_version_code int,
	    c_teminal_num varchar(255),
	    primary key (c_id)
	) engine=innodb;
	
   create table `t_app_type_recommend` (
	  `c_id` varchar(32) not null,
	  `c_app_recommendctime` datetime default null,
	  `c_create_user` varchar(255) default null,
	  `c_app_id` varchar(32) default null,
	  `c_type_id` varchar(32) default null,
	  `c_sort` double default '0',
	  primary key  (`c_id`)
	) engine=innodb default charset=utf8;
	
	create table t_device_info (
        c_id varchar(32) not null,
        c_app_store_client_version varchar(255),
        c_app_store_client_install_date datetime,
        c_device_ip varchar(255),
        c_device_mac varchar(255),
        c_device_serial_no varchar(255),
        c_device_type varchar(255),
        c_os_version integer not null,
        primary key (c_id)
    ) engine=innodb;

   -- 2013-08-28 907632/wangzhenghui
   -- 增加日志记录表记录应用商店客户端操作记录
   create table t_user_operation (
	c_id varchar (32) primary key not null comment '主键',
	c_opt_type int comment '操作类型：1、启动  2、退出 3、专题
                                    4、分类、5、推荐 6、应用详情浏览  
                                    7、浏览其它 8、安装 9、卸载  
                                    10、下载 ',
	c_opt_time datetime comment '操作时间',
	c_opt_content varchar (255) comment '记录用户访问的情况：点击菜单则记录菜单的名称。其它则记录相应的唯一标识，如应用名称',
	c_opt_content_id varchar (255) comment '应用包名/专题id/分类id/推荐id ',
	c_device_mac varchar (255) comment '设备mac地址',
	c_param1 varchar (255) comment '扩展参数',
	c_param2 varchar (255) comment '扩展参数',
	c_param3 varchar (255) comment '扩展参数',
	key `idx_user_optime` (`c_opt_time`)
   )engine=innodb default charset=utf8;
  
    -- 2013-09-5 907632/wangzhenghui
    -- 数据同步到搜索平台,增加同步下发表
	create table t_syn_command (c_id varchar(32) primary key not null comment '主键',
	c_app_id varchar(32) comment '应用app主键',
	c_init_time datetime comment '初始入库时间',
	c_last_time datetime comment '上次同步时间',
	c_fail_count int comment '失败次数',
	c_status int comment '同步状态:1 待同步 2 已同步 3 待删除 4 已删除',
	c_param varchar(50) comment '扩展字段,1: 此次发送 ；其它： 不发送',
	key `idx_app_id` (`c_app_id`)
	)engine=innodb default charset=utf8;
   
	-- 2013-10-09 907632/wangzhenghui
    -- 爱摸客用户反馈信息表
    create table t_user_feedback (
	c_id varchar (32) not null primary key comment '主键',
	c_user_email varchar (30) comment '用户邮箱',
	c_feedback_time datetime comment '反馈时间',
	c_context varchar (300) not null comment '用户反馈信息'
    )engine=innodb default charset=utf8;
	
    alter table t_app_comment_info
        add index fk9cb9b4b72ac9f70b (c_app_id),
        add constraint fk9cb9b4b72ac9f70b
        foreign key (c_app_id)
        references t_dp_app_info (c_id);

    alter table t_app_recommend
        add index fk53dbfb332ac9f70b (c_app_id),
        add constraint fk53dbfb332ac9f70b
        foreign key (c_app_id)
        references t_dp_app_info (c_id);

    alter table t_app_recommend
		add index fk7d0000148c341342 (c_type_id),
        add constraint fk7d0000148c341342
        foreign key (c_type_id)
        references t_dp_type (c_id);

    alter table t_attachment_file
        add index fk49154d2d674cca9 (c_dpappinfo_id),
        add constraint fk49154d2d674cca9
        foreign key (c_dpappinfo_id)
        references t_dp_app_info (c_id);

    alter table t_dp_app_info
        add index fk7d0237148c341342 (c_type_id),
        add constraint fk7d0237148c341342
        foreign key (c_type_id)
        references t_dp_type (c_id);

    -- 2012-12-12 zhengxinlian/906976
    alter table t_dp_app_info
        add index fk7d0237148c341343 (c_app_name);

    alter table t_dp_app_info
    	add index fk7d0237148c341344 (c_app_status);

   	alter table t_app_sign
		add constraint fk7d0237148c341345
		foreign key (c_app_id)
		references t_dp_app_info (c_id);

    alter table t_dp_download_info
        add index fkfce7fc1d8c341342 (c_type_id),
        add constraint fkfce7fc1d8c341342
        foreign key (c_type_id)
        references t_dp_type (c_id);

    alter table t_dp_news
        add index fkce0ebdbb8c341342 (c_type_id),
        add constraint fkce0ebdbb8c341342
        foreign key (c_type_id)
        references t_dp_type (c_id);

    alter table t_resource
        add index fk47d00399b6e20216 (c_parent_id),
        add constraint fk47d00399b6e20216
        foreign key (c_parent_id)
        references t_resource (c_id);

    alter table t_role_resource
        add index fk3e7e410cc1219fb1 (c_role_res_id),
        add constraint fk3e7e410cc1219fb1
        foreign key (c_role_res_id)
        references t_role (c_id);

    alter table t_role_resource
        add index fk3e7e410c3f916d2b (c_res_role_id),
        add constraint fk3e7e410c3f916d2b
        foreign key (c_res_role_id)
        references t_resource (c_id);

    alter table t_subject_appinfo_relation
        add index fk80e41d82ac9f70b (c_app_id),
        add constraint fk80e41d82ac9f70b
        foreign key (c_app_id)
        references t_dp_app_info (c_id);

    alter table t_subject_appinfo_relation
        add index fk80e41d8d6028b97 (c_subject_id),
        add constraint fk80e41d8d6028b97
        foreign key (c_subject_id)
        references t_app_subject_type (c_id);

    alter table t_user_role
        add index fk3e62963f5ce174b (c_user_role_id),
        add constraint fk3e62963f5ce174b
        foreign key (c_user_role_id)
        references t_user (c_id);

    alter table t_user_role
        add index fk3e62963f66d6be2c (c_role_user_id),
        add constraint fk3e62963f66d6be2c
        foreign key (c_role_user_id)
        references t_role (c_id);

    -- 2013-05-08 906976/zhengxinlian
	-- 新增存储过程，根据id查找对应的附件，和根据包名分类对应的星数  .
	delimiter ;; ;
	create procedure `query_atta_file`(in id varchar(32))
	begin
		select c_file_save_name,c_file_size,c_file_desc from t_attachment_file where c_dpappinfo_id =id;
	end;;

	create procedure `count_star_by_package`(in package varchar(200))
	begin
	    select appcomment.c_score,count(appcomment.c_score) from t_app_comment_info appcomment ,t_dp_app_info appinfo  where appinfo.c_app_file_package=package and appinfo.c_id = appcomment.c_app_id group by appcomment.c_score;
	end;;
	delimiter ; ;;
	
	-- t_my_app和t_app_comment_info增加索引 .   
	alter table t_my_app change column c_app_package_name c_app_package_name varchar(128);
    create index t_my_pname on t_my_app(c_app_package_name);
    alter table t_app_comment_info change column c_app_package_name c_app_package_name varchar(128);
    create index t_comment_pname on t_app_comment_info(c_app_package_name);
    
    -- b054 t_dp_app_info表增加字段     .
    alter table t_dp_app_info add column c_subject_poster varchar(128);
    
-- v100r001b054新增.
-- 新增精选页板块表.
create table `t_app_recommend_panel` (
  `c_id` varchar(32) not null,
  `c_panel_name` varchar(255) not null default '',
  `c_layout_type` int(11) not null default '1',
  `c_panel_desc` varchar(255) default null,
  `c_sort_num` int(11) default null,
  `c_status` int(11) not null default '1',
  primary key (`c_id`)
) engine=innodb default charset=utf8;

-- 新增精选页板块元素表.
create table `t_app_recommend_panel_item` (
  `c_id` varchar(32) not null,
  `c_item_desc` varchar(255) default null,
  `c_item_name` varchar(255) default null,
  `c_item_poster` varchar(255) default null,
  `c_sort_num` int(11) default null,
  `c_status` int(11) not null default '1',
  `c_type` int(11) not null default '1',
  `c_type_value` varchar(255) not null default '',
  `c_panel_id` varchar(32) default null,
  primary key (`c_id`),
  key `fka7b6151ad7e5a263` (`c_panel_id`),
  constraint `fka7b6151ad7e5a263` foreign key (`c_panel_id`) references `t_app_recommend_panel` (`c_id`)
) engine=innodb default charset=utf8;

	-- 2013-11-27 908618/xiaoyingping. 
    -- b056 t_dp_type表增加字段. 
    alter table t_dp_type add column c_sort_num int(4) default 0 comment '分类排序';
	
	-- 2013-12-17 908618/xiaoyingping. 
    -- b057 t_dp_app_info表增加字段. 
	alter table t_dp_app_info add column c_sort_num int(6) default 0 comment '排序序号';
	alter table t_dp_app_info add column c_op_mode int(11) default 1 comment '操作类型';
	
	-- 2013-12-31 908618/xiaoyingping. 
	-- b058 新增标签信息表. 
	create table `t_tag_info` (
	  `c_id` varchar(32) not null,
	  `c_name` varchar(16) not null,
	  `c_status` int(11) not null default '1',
	  `c_create_time` datetime,
	  primary key (`c_id`)
	) engine=innodb default charset=utf8;

	-- 新增应用标签关系表. 
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
	
	-- 2014-01-13 908618/xiaoyingping.
	-- b059 增加应用信息表的字段. 
	alter table t_dp_app_info add column c_hand_down_count int default 0 comment '人工增加下载次数';
	alter table t_dp_app_info add column c_hand_avg_score double default 0 comment '人工增加平均评分';

	-- 修改应用标签关联表的字段. 
	alter table `t_app_tag_relation`
	modify column `c_app_id`  varchar(32) character set utf8 collate utf8_general_ci null after `c_id`,
	add column `c_app_package_name`  varchar(255) null after `c_sort_num`;
		
	-- 2014-03-12 908618/xiaoyingping.
	-- b061 增加应用信息表的字段. 
	alter table t_dp_app_info add column c_hand_score_count int default 0 comment '人工增加评分人数';

	-- 2014-04-15 V100R003B001 方案管理. 
	-- 创建方案表. 
	CREATE TABLE `t_plan` (
	  `C_ID` varchar(32) NOT NULL,
	  `C_DEFAULTED` bit(1) DEFAULT NULL,
	  `C_DESCRIPTION` varchar(255) DEFAULT NULL,
	  `C_END_TIME` datetime DEFAULT NULL,
	  `C_NAME` varchar(255) DEFAULT NULL,
	  `C_RFC_CALENDAR` varchar(255) DEFAULT NULL,
	  `C_START_TIME` datetime DEFAULT NULL,
	  `C_STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '状态，-1=已删除；0=未启用；1=已启用',
	  `C_REGULATION` varchar(255) DEFAULT NULL,
	  `C_CREATE_TIME` datetime NOT NULL,
	  `C_UPDATE_TIME` datetime NOT NULL,
	  `C_SORT_NUM` int(11) DEFAULT NULL,
	  PRIMARY KEY (`C_ID`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	-- 创建条件表. 
	CREATE TABLE `t_condition` (
	  `C_ID` varchar(32) NOT NULL,
	  `C_CODE` varchar(100) NOT NULL,
	  `C_DESCRIPTION` varchar(256) DEFAULT NULL,
	  `C_NAME` varchar(100) NOT NULL,
	  `C_STATUS` int(11) NOT NULL DEFAULT '1' COMMENT '状态，-1=已删除；0=禁用；1=有效',
	  `C_VALUE` varchar(500) DEFAULT NULL,
	  `C_CREATE_TIME` datetime NOT NULL,
	  `C_UPDATE_TIME` datetime NOT NULL,
	  PRIMARY KEY (`C_ID`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	-- 创建类项表. 
	CREATE TABLE `t_item` (
	  `C_ID` varchar(32) NOT NULL,
	  `C_NAME` varchar(100) NOT NULL COMMENT '方案类项名称。',
	  `C_ITEM_TYPE` int(11) NOT NULL DEFAULT '4' COMMENT '方案类项类型，1=首页类项；2=专题类项；3=分类类项；4=自定义类项',
	  `C_STATUS` int(11) NOT NULL DEFAULT '1' COMMENT '状态，-1=已删除；0=禁用；1=有效',
	  `C_DESCRIPTION` varchar(256) DEFAULT NULL,
	  `C_CREATE_TIME` datetime NOT NULL,
	  `C_UPDATE_TIME` datetime NOT NULL,
	  PRIMARY KEY (`C_ID`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	-- 创建 方案-条件 关联表. 
	CREATE TABLE `t_plan_condition` (
	  `C_ID` varchar(32) NOT NULL,
	  `C_DEFAULTED` tinyint(1) NOT NULL DEFAULT '0',
	  `C_SORT_NUM` int(11) NOT NULL DEFAULT '10000',
	  `C_STATUS` int(11) NOT NULL DEFAULT '1',
	  `C_CONDITION_ID` varchar(32) NOT NULL,
	  `C_PLAN_ID` varchar(32) NOT NULL,
	  `C_CREATE_TIME` datetime NOT NULL,
	  `C_UPDATE_TIME` datetime NOT NULL,
	  PRIMARY KEY (`C_ID`),
	  KEY `FK2C4A16B05FBD022F1` (`C_CONDITION_ID`),
	  KEY `FK2C4A16B018E3E9A5` (`C_PLAN_ID`),
	  CONSTRAINT `FK2C4A16B018E3E9A5` FOREIGN KEY (`C_PLAN_ID`) REFERENCES `t_plan` (`C_ID`),
	  CONSTRAINT `FK2C4A16B05FBD022F1` FOREIGN KEY (`C_CONDITION_ID`) REFERENCES `t_condition` (`C_ID`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	-- 创建 方案-类项 关联表. 
	CREATE TABLE `t_plan_item` (
	  `C_ID` varchar(32) NOT NULL,
	  `C_DESCRIPTION` varchar(255) DEFAULT NULL,
	  `C_ITEM_TYPE` int(11) DEFAULT NULL,
	  `C_SORT_NUM` int(11) DEFAULT NULL,
	  `C_ITEM_ID` varchar(32) DEFAULT NULL,
	  `C_PLAN_ID` varchar(32) DEFAULT NULL,
	  `C_CREATE_TIME` datetime NOT NULL,
	  `C_UPDATE_TIME` datetime NOT NULL,
	  PRIMARY KEY (`C_ID`),
	  KEY `FKA773DC3EB473E965` (`C_ITEM_ID`),
	  KEY `FKA773DC3E18E3E9A5` (`C_PLAN_ID`),
	  CONSTRAINT `FKA773DC3E18E3E9A5` FOREIGN KEY (`C_PLAN_ID`) REFERENCES `t_plan` (`C_ID`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	-- 创建 方案-类项-应用 关联表. 
	CREATE TABLE `t_plan_item_app` (
	  `C_ID` varchar(32) NOT NULL,
	  `C_APP_PKG_NAME` varchar(255) DEFAULT NULL,
	  `C_ITEM_TYPE` int(11) DEFAULT NULL,
	  `C_SORT_NUM` int(11) DEFAULT NULL,
	  `C_ITEM_ID` varchar(32) DEFAULT NULL,
	  `C_PLAN_ID` varchar(32) DEFAULT NULL,
	  `C_CREATE_TIME` datetime NOT NULL,
	  `C_UPDATE_TIME` datetime NOT NULL,
	  PRIMARY KEY (`C_ID`),
	  KEY `FKD5BBBA80B473E965` (`C_ITEM_ID`),
	  KEY `FKD5BBBA8018E3E9A5` (`C_PLAN_ID`),
	  CONSTRAINT `FKD5BBBA8018E3E9A5` FOREIGN KEY (`C_PLAN_ID`) REFERENCES `t_plan` (`C_ID`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	-- 增加 设备信息表 渠道id 和 区域编码 字段. 
	alter table t_device_info add C_CHANNEL_ID varchar(32) comment '渠道id';
	alter table t_device_info add C_CITY_CODE varchar(32) comment '区域编码';
	
	-- 增加 用户操作信息表 渠道id 和 区域编码 字段. 
	alter table t_user_operation add C_CHANNEL_ID varchar(32) comment '渠道id';
	alter table t_user_operation add C_CITY_CODE varchar(32) comment '区域编码';
	
	-- 复制方案 存储过程. 
	DELIMITER $$  
		DROP PROCEDURE IF EXISTS PRO_APPSTORE_COPY_PLAN $$  
		CREATE  PROCEDURE PRO_APPSTORE_COPY_PLAN(
				in_newPlanId varchar(32),
				in_oldPlanId varchar(32)) 
		BEGIN  
		 -- 设置非自动提交
		SET autocommit=0;
		
		    -- 插入plan 数据
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
		
			-- 插入 planItem
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
		
			-- 插入 planItemApp
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
		
		-- 手动提交
		 commit;
		END $$  
	DELIMITER ; 
	
		
	-- 2014-04-24 908618/xiaoyingping.
	-- V100R003B002. 
	-- 增加类项表的字段. 
	alter table t_item add column C_PARENT_TYPE_CODE varchar(12) default '' comment '上级分类编码';
	
	-- 2014-05-23 908618/xiaoyingping. 
	-- V100R003B005. 
	-- 增加类项表的字段. 
	alter table t_item add column C_TYPE_IMG1 varchar(255) comment '获取焦点图标';
	alter table t_item add column C_TYPE_IMG2 varchar(255) comment '失去焦点图标';
	
	-- V100R003B006. 
	-- 增加系统参数表. 
	DROP TABLE IF EXISTS `t_system_param`;
	CREATE TABLE `t_system_param` (
	  `C_ID` varchar(32) NOT NULL,
	  `C_CODE` varchar(255) DEFAULT NULL COMMENT '参数编码',
	  `C_NAME` varchar(255) DEFAULT NULL COMMENT '参数名',
	  `C_TYPE` int(11) DEFAULT NULL COMMENT '参数类型 1:文字; 2:图片',
	  `C_VALUE` varchar(255) DEFAULT NULL COMMENT '参数值',
	  PRIMARY KEY (`C_ID`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	-- 增加缩略图信息表. 
	DROP TABLE IF EXISTS `t_app_thumbnail`;
	CREATE TABLE `t_app_thumbnail` (
	  `C_ID` varchar(32) NOT NULL,
	  `C_SRC_IMG` varchar(255) DEFAULT NULL COMMENT '源图',
	  `C_SRC_TYPE` varchar(255) DEFAULT NULL COMMENT '源图来源类型',
	  `C_DIMENSION` varchar(255) DEFAULT NULL COMMENT '缩略尺寸',
	  `C_THUMB_IMG` varchar(255) DEFAULT NULL COMMENT '通过成的缩略图',
	  `C_STATUS` int(11) DEFAULT NULL COMMENT '状态',
	  `C_FAIL_COUNT` int(11) DEFAULT NULL COMMENT '失败次数',
	  PRIMARY KEY (`C_ID`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	-- V100R003B007. 
	--专题表新增productCode字段，用于记录DVB环境的BOSS系统中的产品编码，该值要与IUC中的productCode一致，表示同一个套餐. 
	ALTER TABLE T_APP_SUBJECT_TYPE ADD COLUMN C_PRODUCT_CODE VARCHAR(255) DEFAULT NULL AFTER C_SUBJECT_NAME;
	
	-- V100R003B008. 
	-- 新增表'人工指定猜你喜欢'. 
	DROP TABLE IF EXISTS `t_hand_app_relate`;
	CREATE TABLE `t_hand_app_relate` (
	  `C_ID` varchar(32) NOT NULL,
	  `C_APP_PKG_NAME` varchar(255) NOT NULL,
	  `C_PLAN_ID` varchar(255) NOT NULL,
	  `C_RELATE_APP_PKG_NAME` varchar(255) NOT NULL,
	  `C_SORT_NUM` int(11) NOT NULL,
	  PRIMARY KEY (`C_ID`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
		
	-- V100R003B009. 
	-- 新增表'登录用户拥有的应用信息'. 
	DROP TABLE IF EXISTS `t_user_app`;
	CREATE TABLE `t_user_app` (
	  `C_ID` varchar(32) NOT NULL,
	  `C_USER_CODE` varchar(255) NOT NULL,
	  `C_APP_PACKAGE_NAME` varchar(255) NOT NULL,
	  `C_CREATE_TIME` datetime NOT NULL,
	  PRIMARY KEY (`C_ID`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	ALTER TABLE T_USER_OPERATION ADD COLUMN C_USER_CODE VARCHAR(255) DEFAULT NULL AFTER C_CITY_CODE;
	
	