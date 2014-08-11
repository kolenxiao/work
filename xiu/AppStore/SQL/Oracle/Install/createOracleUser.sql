
-- ----------------------------
-- 创建用户SCE_COSHIP 密码SCE_COSHIP
-- ----------------------------
create user SCE_COSHIP identified by SCE_COSHIP;
grant connect to SCE_COSHIP;
grant dba to SCE_COSHIP;
grant RESOURCE to SCE_COSHIP;
grant unlimited tablespace to SCE_COSHIP;
Grant resource  to   SCE_COSHIP;
grant execute on ctx_ddl to SCE_COSHIP;
Grant ctxapp  to   SCE_COSHIP;
Grant create PUBLIC SYNONYM  to   SCE_COSHIP;
Grant create SYNONYM  to   SCE_COSHIP;
Grant create view  to   SCE_COSHIP;