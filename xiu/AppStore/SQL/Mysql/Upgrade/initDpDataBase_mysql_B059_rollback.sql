-- b059.  
alter table t_dp_app_info drop column c_hand_down_count;
alter table t_dp_app_info drop column c_hand_avg_score;


ALTER TABLE `t_app_tag_relation` drop COLUMN `C_APP_PACKAGE_NAME`;


delete a.* from  t_role_resource a where a.C_ID='8a8a9f2b43802ba601438035a57d0026';
delete a.* from  t_role_resource a where a.C_ID='8a8a9f2b43802ba601438035ab280080';

delete a.* from  t_resource a where a.C_ID='8a8a9f2b43802ba6014380352d330005';
delete a.* from  t_resource a where a.C_ID='8a8a9f2b43802ba601438032b80e0002';
