--t_my_app和t_app_comment_info增加索引
alter table t_my_app change column c_app_package_name c_app_package_name varchar(128);
create index t_my_pname on t_my_app(c_app_package_name);
alter table t_app_comment_info change column c_app_package_name c_app_package_name varchar(128);
create index t_comment_pname on t_app_comment_info(c_app_package_name);