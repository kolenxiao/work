-- B057 t_dp_app_info表删除字段. 
alter table t_dp_app_info drop column c_sort_num;
alter table t_dp_app_info drop column c_op_mode;

DELETE a.* from t_role_resource a WHERE a.C_ID = '2c9e97a842d6463c0142d657c5600036';
DELETE a.* from t_resource a WHERE a.C_ID = '2c9e97a842d6463c0142d657262c0007';


DELETE a.* from t_dp_type a WHERE a.C_ID = '2c9e97a842da6b190142da6d598d0001';
DELETE a.* from t_dp_type a WHERE a.C_ID = '2c9e97a842da6b190142da6d9a8c0003';
DELETE a.* from t_dp_type a WHERE a.C_ID = '2c9e97a842da6b190142da6dca360005';
DELETE a.* from t_dp_type a WHERE a.C_ID = '504';