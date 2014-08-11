
    alter table T_ATTACHMENT_FILE 
        drop 
        foreign key FK49154D2D456277EF;

    alter table T_ATTACHMENT_FILE 
        drop 
        foreign key FK49154D2D674CCA9;

    alter table T_DP_APP_INFO 
        drop 
        foreign key FK7D0237145C5D291D;

    alter table T_DP_APP_INFO 
        drop 
        foreign key FK7D023714B7C1E367;

    alter table T_DP_DOWNLOAD_INFO 
        drop 
        foreign key FKFCE7FC1D6B053808;

    alter table T_DP_DOWNLOAD_INFO 
        drop 
        foreign key FKFCE7FC1D8C341342;

    alter table T_DP_NEWS 
        drop 
        foreign key FKCE0EBDBB8C341342;

    alter table T_RESOURCE 
        drop 
        foreign key FK47D00399B6E20216;

    alter table T_ROLE_RESOURCE 
        drop 
        foreign key FK3E7E410CC1219FB1;

    alter table T_ROLE_RESOURCE 
        drop 
        foreign key FK3E7E410C3F916D2B;

    alter table T_USER_ROLE 
        drop 
        foreign key FK3E62963F5CE174B;

    alter table T_USER_ROLE 
        drop 
        foreign key FK3E62963F66D6BE2C;

    alter table T_USP_SERVICE 
        drop 
        foreign key FK44AB315DF3C9746C;

    alter table T_USP_SERV_BIND 
        drop 
        foreign key FKC7D9F78E456277EF;

    alter table T_USP_SERV_OPERATION 
        drop 
        foreign key FK45417496456277EF;

    alter table T_USP_SERV_PARAMETERS 
        drop 
        foreign key FK7E05087B6EF79A54;

    alter table T_USP_SERV_PARAMETERS 
        drop 
        foreign key FK7E05087B6D730AB3;

    drop table if exists T_APP_TYPE;

    drop table if exists T_ATTACHMENT_FILE;

    drop table if exists T_DATA_DICT;

    drop table if exists T_DP_APP_INFO;

    drop table if exists T_DP_AUDIT_RECORD;

    drop table if exists T_DP_DOWNLOAD_INFO;

    drop table if exists T_DP_NEWS;

    drop table if exists T_DP_STAFF;

    drop table if exists T_DP_TYPE;

    drop table if exists T_MY_APP;

    drop table if exists T_MY_FAVORITE;

    drop table if exists T_RESOURCE;

    drop table if exists T_ROLE;

    drop table if exists T_ROLE_RESOURCE;

    drop table if exists T_USER;

    drop table if exists T_USER_ROLE;

    drop table if exists T_USP_SERVICE;

    drop table if exists T_USP_SERV_AUDIT;

    drop table if exists T_USP_SERV_BIND;

    drop table if exists T_USP_SERV_GROUP;

    drop table if exists T_USP_SERV_OPERATION;

    drop table if exists T_USP_SERV_PARAMETERS;
    
    drop table if exists T_OP_LOGGER;

    create table T_APP_TYPE (
        C_ID varchar(32) not null,
        C_CREATE_DATE datetime,
        C_CREATE_USER varchar(64),
        C_TYPE_DESC varchar(64),
        C_TYPE_IMG1 varchar(255),
        C_TYPE_IMG2 varchar(255),
        C_TYPE_NAME varchar(32),
        C_UPDATE_DATE datetime,
        primary key (C_ID)
    ) type=InnoDB;

    create table T_ATTACHMENT_FILE (
        C_ID varchar(32) not null,
        C_CREATE_DATE datetime,
        C_FILE_DESC longtext,
        C_FILE_NAME varchar(128),
        C_FILE_SAVE_NAME varchar(128),
        C_FILE_SIZE bigint not null,
        C_FILE_TYPE varchar(32),
        C_USP_SERV_ID varchar(64),
        C_DPAPPINFO_ID varchar(32),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_DATA_DICT (
        C_ID varchar(32) not null,
        C_CATEGORY varchar(128),
        C_DATA_KEY varchar(64),
        C_DATA_VALUE varchar(128),
        C_ENABLE integer,
        C_ORDER_ID integer,
        C_REMARK varchar(255),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_DP_APP_INFO (
        C_ID varchar(32) not null,
        C_APP_DESC longtext,
        C_APP_DOC longtext,
        C_APP_FILE longtext,
        C_APP_ID varchar(255),
        C_APP_IMG1 longtext,
        C_APP_IMG2 longtext,
        C_APP_IMG3 longtext,
        C_APP_LOGO longtext,
        C_APP_LOGO2 longtext,
        C_APP_LOGO3 longtext,
        C_APP_NAME varchar(64),
        C_APP_ON_LINE_STATUS integer,
        C_APP_ORDER integer,
        C_APP_STATUS varchar(4),
        C_APP_TIME datetime,
        C_DEVELOPER varchar(255),
        C_LANGUAGE varchar(255),
        C_LEVEL integer not null,
        C_PRICE double precision not null,
        C_SIZE bigint not null,
        C_SYSTEM varchar(255),
        C_TYPE varchar(255),
        C_UPDATE_TIME datetime,
        C_VERSION varchar(255),
        C_TYPE_ID varchar(32),
        C_STAFF_ID varchar(32),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_DP_AUDIT_RECORD (
        C_ID varchar(32) not null,
        C_ASSESSOR varchar(32),
        C_AUDIT_DATE datetime,
        C_AUDIT_FLAG varchar(32),
        C_AUDIT_OPTION longtext,
        C_AUDIT_RECORD_ID varchar(255),
        C_AUDIT_RESULT varchar(4),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_DP_DOWNLOAD_INFO (
        C_ID varchar(32) not null,
        C_CREATE_USER varchar(64),
        C_CTIME datetime,
        C_DOWNLOAD_DESC longtext,
        C_DOWNLOAD_NAME varchar(200),
        C_SHOW_INDEX varchar(2),
        C_UPDATE_TIME datetime,
        C_ATTACH_FILE_ID varchar(32),
        C_TYPE_ID varchar(32),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_DP_NEWS (
        C_ID varchar(32) not null,
        C_CREATE_USER varchar(64),
        C_NEWS_CONTENT longtext,
        C_NEWS_CREATE_TIME datetime,
        C_NEWS_ORDER_ID integer not null,
        C_NEWS_SOURCE varchar(128),
        C_NEWS_SOURCE_URL varchar(64),
        C_NEWS_STSTUS varchar(8),
        C_NEWS_SUMMARY longtext,
        C_NEWS_TITLE varchar(128),
        C_UPDATE_TIME datetime,
        C_TYPE_ID varchar(32),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_DP_STAFF (
        C_ID varchar(32) not null,
        C_ADDRESS varchar(64),
        C_BANK_ACCOUNT_NAME varchar(20),
        C_BANK_CARD_NUM varchar(20),
        C_BANK_NAME varchar(32),
        C_BEGIN_DP_STAFF_TIME datetime,
        C_EMAIL varchar(64),
        C_HEAD_ICON varchar(64),
        C_IDENTITY_CARD varchar(30),
        C_IDENTITY_IMG varchar(64),
        C_LOCAL_ADD varchar(64),
        C_MOBILE_NUM varchar(16),
        C_NICK_NAME varchar(20),
        C_PASS_WORD varchar(64) not null,
        C_PORSTAL_CODE varchar(6),
        C_REAL_NAME varchar(40),
        C_STAFF_STATUS varchar(4),
        C_UPDATE_DP_STAFF_TIME datetime,
        C_USER_NAME varchar(20) not null,
        primary key (C_ID)
    ) type=InnoDB;

    create table T_DP_TYPE (
        C_ID varchar(32) not null,
        C_CREATE_DATE datetime,
        C_CREATE_USER varchar(64),
        C_TYPE_DESC varchar(64),
        C_TYPE_FLAG integer,
        C_TYPE_NAME varchar(32),
        C_UPDATE_DATE datetime,
        primary key (C_ID)
    ) type=InnoDB;

    create table T_MY_APP (
        C_ID varchar(32) not null,
        C_ADD_TIME datetime,
        C_APP_ID varchar(255),
        C_UID varchar(255),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_MY_FAVORITE (
        C_ID varchar(32) not null,
        C_APP_ID varchar(255),
        C_FAVORITE_TIME datetime,
        C_UID varchar(255),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_RESOURCE (
        C_ID varchar(32) not null,
        C_CREATED_DATE datetime,
        C_CREATED_USER varchar(64),
        C_DESCRIPTION varchar(200),
        C_EN_NAME varchar(50) not null unique,
        C_LEVEL integer,
        C_MENU_BUTTON integer,
        C_NAME varchar(200),
        C_ORDER_FIELD integer,
        C_UPDATED_DATE datetime,
        C_URL varchar(200),
        C_PARENT_ID varchar(32),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_ROLE (
        C_ID varchar(32) not null,
        C_CREATED_DATE datetime,
        C_CREATED_USER varchar(64),
        C_DESCRIPTION varchar(200),
        C_NAME varchar(64),
        C_UPDATED_DATE datetime,
        primary key (C_ID)
    ) type=InnoDB;

    create table T_ROLE_RESOURCE (
        C_ID varchar(32) not null,
        C_ROLE_RES_ID varchar(32),
        C_RES_ROLE_ID varchar(32),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_USER (
        C_ID varchar(32) not null,
        C_CREATED_DATE datetime,
        C_CREATED_USER varchar(64),
        C_EMAIL varchar(64),
        C_PASSWORD varchar(64),
        C_REAL_NAME varchar(64),
        C_STATUS varchar(1),
        C_TELEPHONE varchar(20),
        C_UPDATED_DATE datetime,
        C_USER_NAME varchar(64) not null unique,
        primary key (C_ID)
    ) type=InnoDB;

    create table T_USER_ROLE (
        C_ID varchar(32) not null,
        C_USER_ROLE_ID varchar(32),
        C_ROLE_USER_ID varchar(32),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_USP_SERVICE (
        C_ID varchar(64) not null,
        C_USP_NAME_SPACE varchar(128),
        C_USP_SERV_ADDR varchar(128),
        C_USP_SERV_CTIME datetime,
        C_USP_SERV_DESC longtext,
        C_USP_SERV_MTIME datetime,
        C_USP_SERV_NAME varchar(64),
        C_USP_SERV_STATUS varchar(4),
        C_USP_SERV_TYPE integer not null,
        C_USP_GROUP_ID varchar(255),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_USP_SERV_AUDIT (
        C_ID varchar(64) not null,
        C_SERV_AUDIT_DATE datetime,
        C_SERV_AUDIT_OPINION varchar(128),
        C_SERV_AUDIT_RESULT integer not null,
        primary key (C_ID)
    ) type=InnoDB;

    create table T_USP_SERV_BIND (
        C_ID varchar(32) not null,
        C_USP_SERV_BIND_NAME varchar(64),
        C_USP_SERV_BIND_PORTTYPE varchar(64),
        C_USP_SERV_BIND_TRANSPORT varchar(128),
        C_USP_SERV_ID varchar(64),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_USP_SERV_GROUP (
        C_ID varchar(255) not null,
        C_CREATE_DATE datetime,
        C_CREATE_USER varchar(64),
        C_UPDATE_DATE datetime,
        C_USP_GROUP_DESC longtext,
        C_USP_GROUP_NAME varchar(64),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_USP_SERV_OPERATION (
        C_ID varchar(64) not null,
        C_USP_OPER_DESC longtext,
        C_USP_OPER_NAME varchar(128),
        C_USP_SERV_ID varchar(64),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_USP_SERV_PARAMETERS (
        C_ID varchar(64) not null,
        C_USP_ORDER_FIELD integer not null,
        C_USP_PRM_DESC varchar(128),
        C_USP_PRM_FLAG integer not null,
        C_USP_PRM_NAME varchar(32),
        C_USP_PRM_TYPE varchar(32),
        C_USP_PRM_PARENT_ID varchar(64),
        C_USP_OPER_ID varchar(64),
        primary key (C_ID)
    ) type=InnoDB;

    create table T_OP_LOGGER (
        C_ID varchar(64) not null,
        C_BUSINESS_NAME varchar(64),
        C_CLIENT_IP varchar(30),
        C_DESCRIPTION varchar(2048),
        C_OPERATION_DATE datetime,
        C_OPERATION_TYPE varchar(16),
        C_OPERATOR_NAME varchar(32),
        primary key (C_ID)
    )type=InnoDB;

    
    alter table T_ATTACHMENT_FILE 
        add index FK49154D2D456277EF (C_USP_SERV_ID), 
        add constraint FK49154D2D456277EF 
        foreign key (C_USP_SERV_ID) 
        references T_USP_SERVICE (C_ID);

    alter table T_ATTACHMENT_FILE 
        add index FK49154D2D674CCA9 (C_DPAPPINFO_ID), 
        add constraint FK49154D2D674CCA9 
        foreign key (C_DPAPPINFO_ID) 
        references T_DP_APP_INFO (C_ID);

    alter table T_DP_APP_INFO 
        add index FK7D0237145C5D291D (C_STAFF_ID), 
        add constraint FK7D0237145C5D291D 
        foreign key (C_STAFF_ID) 
        references T_DP_STAFF (C_ID);

    alter table T_DP_APP_INFO 
        add index FK7D023714B7C1E367 (C_TYPE_ID), 
        add constraint FK7D023714B7C1E367 
        foreign key (C_TYPE_ID) 
        references T_APP_TYPE (C_ID);

    alter table T_DP_DOWNLOAD_INFO 
        add index FKFCE7FC1D6B053808 (C_ATTACH_FILE_ID), 
        add constraint FKFCE7FC1D6B053808 
        foreign key (C_ATTACH_FILE_ID) 
        references T_ATTACHMENT_FILE (C_ID);

    alter table T_DP_DOWNLOAD_INFO 
        add index FKFCE7FC1D8C341342 (C_TYPE_ID), 
        add constraint FKFCE7FC1D8C341342 
        foreign key (C_TYPE_ID) 
        references T_DP_TYPE (C_ID);

    alter table T_DP_NEWS 
        add index FKCE0EBDBB8C341342 (C_TYPE_ID), 
        add constraint FKCE0EBDBB8C341342 
        foreign key (C_TYPE_ID) 
        references T_DP_TYPE (C_ID);

    alter table T_RESOURCE 
        add index FK47D00399B6E20216 (C_PARENT_ID), 
        add constraint FK47D00399B6E20216 
        foreign key (C_PARENT_ID) 
        references T_RESOURCE (C_ID);

    alter table T_ROLE_RESOURCE 
        add index FK3E7E410CC1219FB1 (C_ROLE_RES_ID), 
        add constraint FK3E7E410CC1219FB1 
        foreign key (C_ROLE_RES_ID) 
        references T_ROLE (C_ID);

    alter table T_ROLE_RESOURCE 
        add index FK3E7E410C3F916D2B (C_RES_ROLE_ID), 
        add constraint FK3E7E410C3F916D2B 
        foreign key (C_RES_ROLE_ID) 
        references T_RESOURCE (C_ID);

    alter table T_USER_ROLE 
        add index FK3E62963F5CE174B (C_USER_ROLE_ID), 
        add constraint FK3E62963F5CE174B 
        foreign key (C_USER_ROLE_ID) 
        references T_USER (C_ID);

    alter table T_USER_ROLE 
        add index FK3E62963F66D6BE2C (C_ROLE_USER_ID), 
        add constraint FK3E62963F66D6BE2C 
        foreign key (C_ROLE_USER_ID) 
        references T_ROLE (C_ID);

    alter table T_USP_SERVICE 
        add index FK44AB315DF3C9746C (C_USP_GROUP_ID), 
        add constraint FK44AB315DF3C9746C 
        foreign key (C_USP_GROUP_ID) 
        references T_USP_SERV_GROUP (C_ID);

    alter table T_USP_SERV_BIND 
        add index FKC7D9F78E456277EF (C_USP_SERV_ID), 
        add constraint FKC7D9F78E456277EF 
        foreign key (C_USP_SERV_ID) 
        references T_USP_SERVICE (C_ID);

    alter table T_USP_SERV_OPERATION 
        add index FK45417496456277EF (C_USP_SERV_ID), 
        add constraint FK45417496456277EF 
        foreign key (C_USP_SERV_ID) 
        references T_USP_SERVICE (C_ID);

    alter table T_USP_SERV_PARAMETERS 
        add index FK7E05087B6EF79A54 (C_USP_PRM_PARENT_ID), 
        add constraint FK7E05087B6EF79A54 
        foreign key (C_USP_PRM_PARENT_ID) 
        references T_USP_SERV_PARAMETERS (C_ID);

    alter table T_USP_SERV_PARAMETERS 
        add index FK7E05087B6D730AB3 (C_USP_OPER_ID), 
        add constraint FK7E05087B6D730AB3 
        foreign key (C_USP_OPER_ID) 
        references T_USP_SERV_OPERATION (C_ID);
