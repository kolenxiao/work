
-- b059.  
alter table t_dp_app_info drop column c_hand_down_count;
alter table t_dp_app_info drop column c_hand_avg_score;
alter table `t_app_tag_relation` drop column `C_APP_PACKAGE_NAME`;

delete a.* from  t_role_resource a where a.C_ID='8a8a9f2b43802ba601438035a57d0026';
delete a.* from  t_role_resource a where a.C_ID='8a8a9f2b43802ba601438035ab280080';

delete a.* from  t_resource a where a.C_ID='8a8a9f2b43802ba6014380352d330005';
delete a.* from  t_resource a where a.C_ID='8a8a9f2b43802ba601438032b80e0002';


-- b058 删除表. 
drop table t_app_tag_relation;
drop table t_tag_info;

-- B057 t_dp_app_info表删除字段. 
alter table t_dp_app_info drop column c_sort_num;
alter table t_dp_app_info drop column c_op_mode;

DELETE a.* from t_role_resource a WHERE a.C_ID = '2c9e97a842d6463c0142d657c5600036';
DELETE a.* from t_resource a WHERE a.C_ID = '2c9e97a842d6463c0142d657262c0007';

DELETE a.* from t_dp_type a WHERE a.C_ID = '2c9e97a842da6b190142da6d598d0001';
DELETE a.* from t_dp_type a WHERE a.C_ID = '2c9e97a842da6b190142da6d9a8c0003';
DELETE a.* from t_dp_type a WHERE a.C_ID = '2c9e97a842da6b190142da6dca360005';
DELETE a.* from t_dp_type a WHERE a.C_ID = '504';