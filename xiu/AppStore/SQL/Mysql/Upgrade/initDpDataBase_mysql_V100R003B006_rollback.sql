-- V100R003B006  rollback.  
delete from t_role_resource  where C_RES_ROLE_ID in ('2c9e999046423b7601464241a6a50002');
delete from t_role_resource  where C_RES_ROLE_ID in ('2c9e99904645b1bd014645c3da8b0006');
delete from t_role_resource  where C_RES_ROLE_ID in ('2c9e999046648d420146649a54110003');
delete from t_role_resource  where C_RES_ROLE_ID in ('2c9e99904664b6b5014664bea7fb00ad');




delete from t_resource  where c_id in ('2c9e999046423b7601464241a6a50002');
delete from t_resource  where c_id in ('2c9e99904645b1bd014645c3da8b0006');
delete from t_resource  where c_id in ('2c9e999046648d420146649a54110003');
delete from t_resource  where c_id in ('2c9e99904664b6b5014664bea7fb00ad');
commit;