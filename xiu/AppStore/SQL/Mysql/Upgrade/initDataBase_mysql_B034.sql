-- 2013-05-02 906974/jiangjinfeng
-- 添加应用商店客户端升级包适用的平台编码
alter table T_APP_STORE_CLIENT add C_TEMINAL_NUM varchar(255);

-- 添加证书是否为默认证书标识
alter table T_APP_CERTIFICATE add C_IS_DEFAULT  int default 0;

-- 添加隐式应用的签名证书id
alter table T_IMPLICIT_APP add C_APP_CERT_ID varchar(32);

INSERT INTO t_resource (C_ID, C_CREATED_DATE, C_CREATED_USER, C_DESCRIPTION, C_EN_NAME, C_LEVEL, C_MENU_BUTTON, C_NAME, C_ORDER_FIELD, C_UPDATED_DATE, C_URL, C_PARENT_ID)  VALUES ('8a8af5d139fd8af6013a0026eb041114', '2012-9-26 09:18:02', 'admin', '设置默认证书', 'setDefaultCertificate', 3, 1, '设置默认证书', 4, '2012-9-26 09:18:01', 'certificate!setDefault.action', '8a8af5af3af8b771013af8bb66ee0005');

INSERT INTO t_role_resource
   (`C_ID`, `C_ROLE_RES_ID`, `C_RES_ROLE_ID`)
VALUES
   ('8a8af5d13a01592a013a015d6a49112a', '1', '8a8af5d139fd8af6013a0026eb041114');