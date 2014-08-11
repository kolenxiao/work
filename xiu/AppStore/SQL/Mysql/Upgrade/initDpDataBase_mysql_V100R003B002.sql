-- V100R003B002. 
-- 增加类项表的字段. 
alter table t_item add column C_PARENT_TYPE_CODE varchar(12) default '' comment '上级分类编码';

INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e998c4588c05f014588c1649d0002','2014-04-22 17:25:46','admin','','copyPlan',3,1,'复制方案',1,'2014-04-22 17:25:46','planManage!copy.action','2c9e997544d85e6501450bf54a190018');

INSERT INTO `t_resource` (`C_ID`,`C_CREATED_DATE`,`C_CREATED_USER`,`C_DESCRIPTION`,`C_EN_NAME`,`C_LEVEL`,`C_MENU_BUTTON`,`C_NAME`,`C_ORDER_FIELD`,`C_UPDATED_DATE`,`C_URL`,`C_PARENT_ID`) VALUES ('2c9e9975457442000145872d5de80014','2014-04-22 17:25:46','admin','','modifyAppOnline',3,1,'上架状态修改应用',5,'2014-04-22 17:25:46','dpAppInfo!modifyAppOnline.action','126');
commit;

-- 授予管理员 方案管理全部权限. 
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('2c9e998c4588c05f014588c1deb70053','1','2c9e998c4588c05f014588c1649d0002');
INSERT INTO `t_role_resource` (`C_ID`,`C_ROLE_RES_ID`,`C_RES_ROLE_ID`) VALUES ('2c9e998e4592e1c5014592fbda50003f','1','2c9e9975457442000145872d5de80014');
commit;

-- 去掉 所有用户对 "应用上架-取消关联方案" 的权限关联. 
delete from t_role_resource  where C_RES_ROLE_ID in ('8a8a901e4545bf84014545c5aee80005');
delete from t_resource  where c_id in ('8a8a901e4545bf84014545c5aee80005');
commit;

	-- 复制方案 存储过程. 
	DELIMITER $$  
		DROP PROCEDURE IF EXISTS PRO_APPSTORE_COPY_PLAN $$  
		CREATE  PROCEDURE PRO_APPSTORE_COPY_PLAN(
				in_newPlanId varchar(32),
				in_oldPlanId varchar(32)) 
		BEGIN  
		 -- 设置非自动提交. 
		SET autocommit=0;
		
		    -- 插入plan 数据. 
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
		
			-- 插入 planItem. 
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
		
			-- 插入 planItemApp. 
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
		
		-- 手动提交. 
		 commit;
		END $$  
	DELIMITER ; 