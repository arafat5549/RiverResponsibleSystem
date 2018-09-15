/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/18 14:40:10                           */
/*==============================================================*/

SET FOREIGN_KEY_CHECKS=0;

drop table if exists water_Department;

drop table if exists water_DeviceGPS;

drop table if exists water_DeviceInfo;

drop table if exists water_MonitorSite;

drop table if exists water_MonitorSiteType;

drop table if exists water_MonitorSite_User;

drop table if exists water_MonitorVideo;

drop table if exists water_MonitorWarn;

drop table if exists water_MonitorWarnType;

drop table if exists water_Permission_Role;

drop table if exists water_Region;

drop table if exists water_River;

drop table if exists water_Role_User;

drop table if exists water_SendMessage;

drop table if exists water_Status;

drop table if exists water_User;

drop table if exists water_UserType;

drop table if exists water_DeviceData1;

drop table if exists water_DeviceData2;

drop table if exists water_Log;

drop table if exists water_LogType;

drop table if exists water_Permission;

drop table if exists water_Role;

drop table if exists water_MenuCate;
/*==============================================================*/
/* Table: water_Department                                      */
/*==============================================================*/
create table water_Department
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   parentX              int,
   parentY              int,
   level                smallint,
   isParent             bool,
   sort                 int,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_Department comment '部门表
';

/*==============================================================*/
/* Table: water_DeviceGPS                                       */
/*==============================================================*/
create table water_DeviceGPS
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   code                 char(32) ,
   position             varchar(100),
   longitude            float,
   latitude             float,
   ipAdress             varchar(64),
   port                 int,
   statusId             int,
   deviceInfoId         int,
   parentId             int,
   monitorSiteId        int,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_DeviceGPS comment '设备部署表';

/*==============================================================*/
/* Table: water_DeviceInfo                                      */
/*==============================================================*/
create table water_DeviceInfo
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   serialNO             varchar(100) not null,
   type                 int not null,
   protocol             varchar(100),
   producer             varchar(100),
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_DeviceInfo comment '设备信息表';

/*==============================================================*/
/* Table: water_MonitorSite                                     */
/*==============================================================*/
create table water_MonitorSite
(
   id                   int not null auto_increment,
   code                 char(32),
   name                 varchar(100) not null,
   monitorSiteTypeId    int,
   riverId              int,
   regionId             int,
   longitude            float,
   latitude             float,
   parentId             int,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_MonitorSite comment '监测站点表';

/*==============================================================*/
/* Table: water_MonitorSiteType                                 */
/*==============================================================*/
create table water_MonitorSiteType
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_MonitorSiteType comment '监测点类型表';

/*==============================================================*/
/* Table: water_MonitorSite_User                                */
/*==============================================================*/
create table water_MonitorSite_User
(
   userId               int not null,
   monitorSiteId        int not null,
   primary key (userId, monitorSiteId)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_MonitorSite_User comment '监测站的负责人关联表';

/*==============================================================*/
/* Table: water_MonitorVideo                                    */
/*==============================================================*/
create table water_MonitorVideo
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   code                 char(32),
   position             varchar(32),
   longitude            float,
   latitude             float,
   ipAdress             varchar(64),
   port                 int,
   statusId             int,
   monitorSiteId        int,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_MonitorVideo comment '视频监控表';

/*==============================================================*/
/* Table: water_MonitorWarn                                     */
/*==============================================================*/
create table water_MonitorWarn
(
   id                   int not null auto_increment,
   deviceGPSId          int,
   monitorSiteId        int,
   warnTypeId           int,
   level                int,
   sender               varchar(100),
   content              varchar(1000),
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_MonitorWarn comment '监控预警';

/*==============================================================*/
/* Table: water_MonitorWarnType                                 */
/*==============================================================*/
create table water_MonitorWarnType
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   noticeType           int,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_MonitorWarnType comment '监控预警类别表';

/*==============================================================*/
/* Table: water_Permission_Role                                 */
/*==============================================================*/
create table water_Permission_Role
(
   permissionId         int not null,
   roleId               int not null,
   primary key (permissionId, roleId)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

/*==============================================================*/
/* Table: water_Region                                          */
/*==============================================================*/
create table water_Region
(
   id                   int not null auto_increment,
   code                 char(32),
   name                 varchar(100) not null,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_Region comment '行政区划表';

/*==============================================================*/
/* Table: water_River                                           */
/*==============================================================*/
create table water_River
(
   id                   int not null auto_increment,
   code                 char(32),
   name                 varchar(100) not null,
   level                smallint,
   length               float,
   parentId             int,
   longitude            float,
   latitude             float,
   area                 float,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_River comment '流域表';

/*==============================================================*/
/* Table: water_Role_User                                       */
/*==============================================================*/
create table water_Role_User
(
   roleId               int not null,
   userId               int not null,
   primary key (roleId, userId)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

/*==============================================================*/
/* Table: water_SendMessage                                     */
/*==============================================================*/
create table water_SendMessage
(
   id                   int not null auto_increment,
   titile               varchar(255),
   content              longtext,
   sender               varchar(100),
   receiver             varchar(100),
   mobile               varchar(20),
   createTime           timestamp not null DEFAULT CURRENT_TIMESTAMP, 
   isReceived           bool,
   type                 smallint,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_SendMessage comment '发送消息表';

/*==============================================================*/
/* Table: water_Status                                          */
/*==============================================================*/
create table water_Status
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   keyword              varchar(100) not null,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_Status comment '状态表';

/*==============================================================*/
/* Table: water_User                                            */
/*==============================================================*/
create table water_User
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   logname              varchar(100) not null,
   password             varchar(100) not null,
   gender               smallint,
   lastLoginDT          datetime,
   signRange            longtext,
   createTime           timestamp not null DEFAULT CURRENT_TIMESTAMP,
   responseTypeId       int,
   userTypeId           int,
   deptId               int,
   primary key (id),
   UNIQUE KEY `key_uniq_logname` (`logname`) USING BTREE
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_User comment '用户表';

/*==============================================================*/
/* Table: water_UserType                                        */
/*==============================================================*/
create table water_UserType
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_UserType comment '用户类别表';

/*==============================================================*/
/* Table: water_DeviceData1                                   */
/*==============================================================*/
create table water_DeviceData1
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   deviceGPSId          int,
   createTime           timestamp not null DEFAULT CURRENT_TIMESTAMP,
   isReceived           bool,
   data1                varchar(255),
   data2                varchar(255),
   data3                varchar(255),
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_DeviceData1 comment '设备数据表1';

/*==============================================================*/
/* Table: water_DeviceData2                                   */
/*==============================================================*/
create table water_DeviceData2
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   deviceGPSId          int,
   createTime           timestamp not null DEFAULT CURRENT_TIMESTAMP,
   isReceived           bool,
   data1                varchar(255),
   data2                varchar(255),
   data3                varchar(255),
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_DeviceData2 comment '设备数据表2';

/*==============================================================*/
/* Table: water_Log                                         */
/*==============================================================*/
create table water_Log
(
   id                   int not null auto_increment,
   logTypeId            int,
   content              varchar(1000),
   options              varchar(100),
   urlDetail            varchar(100),
   createTime           timestamp not null DEFAULT CURRENT_TIMESTAMP,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_Log comment '系统操作日记';

/*==============================================================*/
/* Table: water_LogType                                     */
/*==============================================================*/
create table water_LogType
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_LogType comment '系统g系统操作日记类别表';

/*==============================================================*/
/* Table: water_Permission                                  */
/*==============================================================*/
create table water_Permission
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   href                 varchar(100) not null,
   isActive             bool,
   menuCateId           int,
   parentId              int,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_Permission comment '权限表';

/*==============================================================*/
/* Table: water_Role                                        */
/*==============================================================*/
create table water_Role
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   ename                varchar(100) not null,
   isActive             bool,
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;

alter table water_Role comment '角色表';

create table water_MenuCate
(
   id                  int not null auto_increment,
   name                varchar(100) not null,
   sort                int,
   IsActive            bool,
   parentId            int,
   icon                varchar(100),
   primary key (id)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8;
alter table water_MenuCate comment '菜单分类表';

-- alter table water_DeviceGPS add constraint FK_re_devicegps_deviceinfo foreign key (deviceInfoId)
--       references water_DeviceInfo (id) on delete restrict on update restrict;
--
-- alter table water_DeviceGPS add constraint FK_re_devicegps_site foreign key (monitorSiteId)
--       references water_MonitorSite (id) on delete restrict on update restrict;
--
-- alter table water_DeviceGPS add constraint FK_re_devicegps_status foreign key (statusId)
--       references water_Status (id) on delete restrict on update restrict;
--
-- alter table water_MonitorSite add constraint FK_re_site_region foreign key (regionId)
--       references water_Region (id) on delete restrict on update restrict;
--
-- alter table water_MonitorSite add constraint FK_re_site_river foreign key (riverId)
--       references water_River (id) on delete restrict on update restrict;
--
-- alter table water_MonitorSite add constraint FK_re_site_type foreign key (monitorSiteTypeId)
--       references water_MonitorSiteType (id) on delete restrict on update restrict;
--
-- alter table water_MonitorSite_User add constraint FK_re_siteuser_site foreign key (monitorSiteId)
--       references water_MonitorSite (id) on delete restrict on update restrict;
--
-- alter table water_MonitorSite_User add constraint FK_re_siteuser_user foreign key (userId)
--       references water_User (id) on delete restrict on update restrict;
--
-- alter table water_MonitorVideo add constraint FK_re_video_site foreign key (monitorSiteId)
--       references water_MonitorSite (id) on delete restrict on update restrict;
--
-- alter table water_MonitorVideo add constraint FK_re_video_status foreign key (statusId)
--       references water_Status (id) on delete restrict on update restrict;
--
-- alter table water_Permission_Role add constraint FK_re_permissionrole_permission foreign key (permissionId)
--       references water_Permission (id) on delete restrict on update restrict;
--
-- alter table water_Permission_Role add constraint FK_re_permissionrole_role foreign key (roleId)
--       references water_Role (id) on delete restrict on update restrict;
--
-- alter table water_Role_User add constraint FK_re_roleuser_role foreign key (roleId)
--       references water_Role (id) on delete restrict on update restrict;
--
-- alter table water_Role_User add constraint FK_re_roleuser_user foreign key (userId)
--       references water_User (id) on delete restrict on update restrict;
--
-- alter table water_User add constraint FK_re_user_dept foreign key (deptId)
--       references water_Department (id) on delete restrict on update restrict;
--
-- alter table water_User add constraint FK_re_user_type foreign key (userTypeId)
--       references water_UserType (id) on delete restrict on update restrict;
--
-- alter table water_DeviceData1 add constraint FK_re_devicedata1_devicegps foreign key (deviceGPSId)
--       references water_DeviceGPS (id) on delete restrict on update restrict;
--
-- alter table water_DeviceData2 add constraint FK_re_devicedata2_devicegps foreign key (deviceGPSId)
--       references water_DeviceGPS (id) on delete restrict on update restrict;
--
-- alter table water_Log add constraint FK_re_log_type foreign key (logTypeId)
--       references water_LogType (id) on delete restrict on update restrict;

