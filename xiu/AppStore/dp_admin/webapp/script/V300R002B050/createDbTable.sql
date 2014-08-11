
    drop table T_APP_TYPE cascade constraints;

    drop table T_ATTACHMENT_FILE cascade constraints;

    drop table T_DATA_DICT cascade constraints;

    drop table T_DP_APP_INFO cascade constraints;

    drop table T_DP_AUDIT_RECORD cascade constraints;

    drop table T_DP_DOWNLOAD_INFO cascade constraints;

    drop table T_DP_NEWS cascade constraints;

    drop table T_DP_STAFF cascade constraints;

    drop table T_DP_TYPE cascade constraints;

    drop table T_MY_APP cascade constraints;

    drop table T_MY_FAVORITE cascade constraints;

    drop table T_OP_LOGGER cascade constraints;

    drop table T_RESOURCE cascade constraints;

    drop table T_ROLE cascade constraints;

    drop table T_ROLE_RESOURCE cascade constraints;

    drop table T_USER cascade constraints;

    drop table T_USER_ROLE cascade constraints;

    drop table T_USP_SERVICE cascade constraints;

    drop table T_USP_SERV_AUDIT cascade constraints;

    drop table T_USP_SERV_BIND cascade constraints;

    drop table T_USP_SERV_GROUP cascade constraints;

    drop table T_USP_SERV_OPERATION cascade constraints;

    drop table T_USP_SERV_PARAMETERS cascade constraints;

    create table T_APP_TYPE (
        C_ID varchar2(32 char) not null,
        C_CREATE_DATE timestamp,
        C_CREATE_USER varchar2(64 char),
        C_TYPE_DESC varchar2(64 char),
        C_TYPE_IMG1 varchar2(255 char),
        C_TYPE_IMG2 varchar2(255 char),
        C_TYPE_NAME varchar2(32 char),
        C_UPDATE_DATE timestamp,
        primary key (C_ID)
    );

    create table T_ATTACHMENT_FILE (
        C_ID varchar2(32 char) not null,
        C_CREATE_DATE timestamp,
        C_FILE_DESC varchar2(512 char),
        C_FILE_NAME varchar2(128 char),
        C_FILE_SAVE_NAME varchar2(128 char),
        C_FILE_SIZE number(19,0) not null,
        C_FILE_TYPE varchar2(32 char),
        C_USP_SERV_ID varchar2(64 char),
        C_DPAPPINFO_ID varchar2(32 char),
        primary key (C_ID)
    );

    create table T_DATA_DICT (
        C_ID varchar2(32 char) not null,
        C_CATEGORY varchar2(128 char),
        C_DATA_KEY varchar2(64 char),
        C_DATA_VALUE varchar2(128 char),
        C_ENABLE number(10,0),
        C_ORDER_ID number(10,0),
        C_REMARK varchar2(255 char),
        primary key (C_ID)
    );

    create table T_DP_APP_INFO (
        C_ID varchar2(32 char) not null,
        C_APP_DESC varchar2(512 char),
        C_APP_DOC varchar2(256 char),
        C_APP_FILE varchar2(256 char),
        C_APP_FILE_PACKAGE varchar2(256 char),
        C_APP_ID varchar2(255 char),
        C_APP_IMG1 varchar2(256 char),
        C_APP_IMG2 varchar2(256 char),
        C_APP_IMG3 varchar2(256 char),
        C_APP_LOGO varchar2(256 char),
        C_APP_LOGO2 varchar2(256 char),
        C_APP_LOGO3 varchar2(256 char),
        C_APP_NAME varchar2(64 char),
        C_APP_NAME_PINYIN varchar2(64 char),
        C_APP_ON_LINE_STATUS number(10,0),
        C_APP_ORDER number(10,0),
        C_APP_STATUS varchar2(4 char),
        C_APP_TIME timestamp,
        C_DEVELOPER varchar2(255 char),
        C_LANGUAGE varchar2(255 char),
        C_LEVEL number(10,0) not null,
        C_PRICE double precision not null,
        C_SIZE number(19,0) not null,
        C_SYSTEM varchar2(255 char),
        C_TYPE varchar2(255 char),
        C_UPDATE_TIME timestamp,
        C_VERSION varchar2(255 char),
        C_TYPE_ID varchar2(32 char),
        C_STAFF_ID varchar2(32 char),
        primary key (C_ID)
    );

    create table T_DP_AUDIT_RECORD (
        C_ID varchar2(32 char) not null,
        C_ASSESSOR varchar2(32 char),
        C_AUDIT_DATE timestamp,
        C_AUDIT_FLAG varchar2(32 char),
        C_AUDIT_OPTION varchar2(256 char),
        C_AUDIT_RECORD_ID varchar2(255 char),
        C_AUDIT_RESULT varchar2(4 char),
        primary key (C_ID)
    );

    create table T_DP_DOWNLOAD_INFO (
        C_ID varchar2(32 char) not null,
        C_CREATE_USER varchar2(64 char),
        C_CTIME timestamp,
        C_DOWNLOAD_DESC varchar2(512 char),
        C_DOWNLOAD_NAME varchar2(200 char),
        C_SHOW_INDEX varchar2(2 char),
        C_UPDATE_TIME timestamp,
        C_ATTACH_FILE_ID varchar2(32 char),
        C_TYPE_ID varchar2(32 char),
        primary key (C_ID)
    );

    create table T_DP_NEWS (
        C_ID varchar2(32 char) not null,
        C_CREATE_USER varchar2(64 char),
        C_NEWS_CONTENT long,
        C_NEWS_CREATE_TIME timestamp,
        C_NEWS_ORDER_ID number(10,0) not null,
        C_NEWS_SOURCE varchar2(128 char),
        C_NEWS_SOURCE_URL varchar2(64 char),
        C_NEWS_STSTUS varchar2(8 char),
        C_NEWS_SUMMARY varchar2(512 char),
        C_NEWS_TITLE varchar2(128 char),
        C_UPDATE_TIME timestamp,
        C_TYPE_ID varchar2(32 char),
        primary key (C_ID)
    );

    create table T_DP_STAFF (
        C_ID varchar2(32 char) not null,
        C_ADDRESS varchar2(64 char),
        C_BANK_ACCOUNT_NAME varchar2(20 char),
        C_BANK_CARD_NUM varchar2(20 char),
        C_BANK_NAME varchar2(32 char),
        C_BEGIN_DP_STAFF_TIME timestamp,
        C_EMAIL varchar2(64 char),
        C_HEAD_ICON varchar2(64 char),
        C_IDENTITY_CARD varchar2(30 char),
        C_IDENTITY_IMG varchar2(64 char),
        C_LOCAL_ADD varchar2(64 char),
        C_MOBILE_NUM varchar2(16 char),
        C_NICK_NAME varchar2(20 char),
        C_PASS_WORD varchar2(64 char) not null,
        C_PORSTAL_CODE varchar2(6 char),
        C_REAL_NAME varchar2(40 char),
        C_STAFF_STATUS varchar2(4 char),
        C_UPDATE_DP_STAFF_TIME timestamp,
        C_USER_NAME varchar2(20 char) not null,
        primary key (C_ID)
    );

    create table T_DP_TYPE (
        C_ID varchar2(32 char) not null,
        C_CREATE_DATE timestamp,
        C_CREATE_USER varchar2(64 char),
        C_TYPE_DESC varchar2(64 char),
        C_TYPE_FLAG number(10,0),
        C_TYPE_NAME varchar2(32 char),
        C_UPDATE_DATE timestamp,
        primary key (C_ID)
    );

    create table T_MY_APP (
        C_ID varchar2(32 char) not null,
        C_ADD_TIME timestamp,
        C_APP_ID varchar2(255 char),
        C_UID varchar2(255 char),
        primary key (C_ID)
    );

    create table T_MY_FAVORITE (
        C_ID varchar2(32 char) not null,
        C_APP_ID varchar2(255 char),
        C_FAVORITE_TIME timestamp,
        C_UID varchar2(255 char),
        primary key (C_ID)
    );

    create table T_OP_LOGGER (
        C_ID varchar2(32 char) not null,
        C_BUSINESS_NAME varchar2(32 char),
        C_CLIENT_IP varchar2(15 char),
        C_DESCRIPTION varchar2(1024 char),
        C_OPERATION_DATE timestamp,
        C_OPERATION_TYPE varchar2(8 char),
        C_OPERATOR_NAME varchar2(16 char),
        primary key (C_ID)
    );

    create table T_RESOURCE (
        C_ID varchar2(32 char) not null,
        C_CREATED_DATE timestamp,
        C_CREATED_USER varchar2(64 char),
        C_DESCRIPTION varchar2(200 char),
        C_EN_NAME varchar2(50 char) not null unique,
        C_LEVEL number(10,0),
        C_MENU_BUTTON number(10,0),
        C_NAME varchar2(200 char),
        C_ORDER_FIELD number(10,0),
        C_UPDATED_DATE timestamp,
        C_URL varchar2(200 char),
        C_PARENT_ID varchar2(32 char),
        primary key (C_ID)
    );

    create table T_ROLE (
        C_ID varchar2(32 char) not null,
        C_CREATED_DATE timestamp,
        C_CREATED_USER varchar2(64 char),
        C_DESCRIPTION varchar2(200 char),
        C_NAME varchar2(64 char),
        C_UPDATED_DATE timestamp,
        primary key (C_ID)
    );

    create table T_ROLE_RESOURCE (
        C_ID varchar2(32 char) not null,
        C_ROLE_RES_ID varchar2(32 char),
        C_RES_ROLE_ID varchar2(32 char),
        primary key (C_ID)
    );

    create table T_USER (
        C_ID varchar2(32 char) not null,
        C_CREATED_DATE timestamp,
        C_CREATED_USER varchar2(64 char),
        C_EMAIL varchar2(64 char),
        C_PASSWORD varchar2(64 char),
        C_REAL_NAME varchar2(64 char),
        C_STATUS varchar2(1 char),
        C_TELEPHONE varchar2(20 char),
        C_UPDATED_DATE timestamp,
        C_USER_NAME varchar2(64 char) not null unique,
        primary key (C_ID)
    );

    create table T_USER_ROLE (
        C_ID varchar2(32 char) not null,
        C_USER_ROLE_ID varchar2(32 char),
        C_ROLE_USER_ID varchar2(32 char),
        primary key (C_ID)
    );

    create table T_USP_SERVICE (
        C_ID varchar2(64 char) not null,
        C_USP_NAME_SPACE varchar2(128 char),
        C_USP_SERV_ADDR varchar2(128 char),
        C_USP_SERV_CTIME timestamp,
        C_USP_SERV_DESC varchar2(512 char),
        C_USP_SERV_MTIME timestamp,
        C_USP_SERV_NAME varchar2(64 char),
        C_USP_SERV_STATUS varchar2(4 char),
        C_USP_SERV_TYPE number(10,0) not null,
        C_USP_GROUP_ID varchar2(255 char),
        primary key (C_ID)
    );

    create table T_USP_SERV_AUDIT (
        C_ID varchar2(64 char) not null,
        C_SERV_AUDIT_DATE timestamp,
        C_SERV_AUDIT_OPINION varchar2(128 char),
        C_SERV_AUDIT_RESULT number(10,0) not null,
        primary key (C_ID)
    );

    create table T_USP_SERV_BIND (
        C_ID varchar2(32 char) not null,
        C_USP_SERV_BIND_NAME varchar2(64 char),
        C_USP_SERV_BIND_PORTTYPE varchar2(64 char),
        C_USP_SERV_BIND_TRANSPORT varchar2(128 char),
        C_USP_SERV_ID varchar2(64 char),
        primary key (C_ID)
    );

    create table T_USP_SERV_GROUP (
        C_ID varchar2(255 char) not null,
        C_CREATE_DATE timestamp,
        C_CREATE_USER varchar2(64 char),
        C_UPDATE_DATE timestamp,
        C_USP_GROUP_DESC varchar2(512 char),
        C_USP_GROUP_NAME varchar2(64 char),
        primary key (C_ID)
    );

    create table T_USP_SERV_OPERATION (
        C_ID varchar2(64 char) not null,
        C_USP_OPER_DESC varchar2(1024 char),
        C_USP_OPER_NAME varchar2(128 char),
        C_USP_SERV_ID varchar2(64 char),
        primary key (C_ID)
    );

    create table T_USP_SERV_PARAMETERS (
        C_ID varchar2(64 char) not null,
        C_USP_ORDER_FIELD number(10,0) not null,
        C_USP_PRM_DESC varchar2(128 char),
        C_USP_PRM_FLAG number(10,0) not null,
        C_USP_PRM_NAME varchar2(32 char),
        C_USP_PRM_TYPE varchar2(32 char),
        C_USP_PRM_PARENT_ID varchar2(64 char),
        C_USP_OPER_ID varchar2(64 char),
        primary key (C_ID)
    );

    alter table T_ATTACHMENT_FILE 
        add constraint FK49154D2D456277EF 
        foreign key (C_USP_SERV_ID) 
        references T_USP_SERVICE;

    alter table T_ATTACHMENT_FILE 
        add constraint FK49154D2D674CCA9 
        foreign key (C_DPAPPINFO_ID) 
        references T_DP_APP_INFO;

    alter table T_DP_APP_INFO 
        add constraint FK7D0237145C5D291D 
        foreign key (C_STAFF_ID) 
        references T_DP_STAFF;

    alter table T_DP_APP_INFO 
        add constraint FK7D023714B7C1E367 
        foreign key (C_TYPE_ID) 
        references T_APP_TYPE;

    alter table T_DP_DOWNLOAD_INFO 
        add constraint FKFCE7FC1D6B053808 
        foreign key (C_ATTACH_FILE_ID) 
        references T_ATTACHMENT_FILE;

    alter table T_DP_DOWNLOAD_INFO 
        add constraint FKFCE7FC1D8C341342 
        foreign key (C_TYPE_ID) 
        references T_DP_TYPE;

    alter table T_DP_NEWS 
        add constraint FKCE0EBDBB8C341342 
        foreign key (C_TYPE_ID) 
        references T_DP_TYPE;

    alter table T_RESOURCE 
        add constraint FK47D00399B6E20216 
        foreign key (C_PARENT_ID) 
        references T_RESOURCE;

    alter table T_ROLE_RESOURCE 
        add constraint FK3E7E410CC1219FB1 
        foreign key (C_ROLE_RES_ID) 
        references T_ROLE;

    alter table T_ROLE_RESOURCE 
        add constraint FK3E7E410C3F916D2B 
        foreign key (C_RES_ROLE_ID) 
        references T_RESOURCE;

    alter table T_USER_ROLE 
        add constraint FK3E62963F5CE174B 
        foreign key (C_USER_ROLE_ID) 
        references T_USER;

    alter table T_USER_ROLE 
        add constraint FK3E62963F66D6BE2C 
        foreign key (C_ROLE_USER_ID) 
        references T_ROLE;

    alter table T_USP_SERVICE 
        add constraint FK44AB315DF3C9746C 
        foreign key (C_USP_GROUP_ID) 
        references T_USP_SERV_GROUP;

    alter table T_USP_SERV_BIND 
        add constraint FKC7D9F78E456277EF 
        foreign key (C_USP_SERV_ID) 
        references T_USP_SERVICE;

    alter table T_USP_SERV_OPERATION 
        add constraint FK45417496456277EF 
        foreign key (C_USP_SERV_ID) 
        references T_USP_SERVICE;

    alter table T_USP_SERV_PARAMETERS 
        add constraint FK7E05087B6EF79A54 
        foreign key (C_USP_PRM_PARENT_ID) 
        references T_USP_SERV_PARAMETERS;

    alter table T_USP_SERV_PARAMETERS 
        add constraint FK7E05087B6D730AB3 
        foreign key (C_USP_OPER_ID) 
        references T_USP_SERV_OPERATION;
