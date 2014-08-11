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