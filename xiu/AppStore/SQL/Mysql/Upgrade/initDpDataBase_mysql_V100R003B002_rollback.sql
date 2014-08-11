-- V100R003B002  rollback.  
alter table t_item drop column C_PARENT_TYPE_CODE;
-- 去掉 所有用户对 方案管理 的权限关联. 
delete from t_role_resource  where C_RES_ROLE_ID in ('2c9e998c4588c05f014588c1649d0002');
delete from t_role_resource  where C_RES_ROLE_ID in ('2c9e9975457442000145872d5de80014');
commit;
-- 去掉 方案管理 权限和菜单数据. 
delete from t_resource  where c_id in ('2c9e998c4588c05f014588c1649d0002');
delete from t_resource  where c_id in ('2c9e9975457442000145872d5de80014');
commit;
	

-- 删除存储过程 PRO_APPSTORE_COPY_PLAN. 
DROP PROCEDURE IF EXISTS PRO_APPSTORE_COPY_PLAN;