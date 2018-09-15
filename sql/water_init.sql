/*==============================================================*/
/* DBMS name:      MySQL 5.6                                    */
/* Created on:     2018/7/18 14:40:10                           */
/*==============================================================*/


/*================================================================================================================================================*/
/*                      基础权限部分 */
/*================================================================================================================================================*/
/* 基础部分涉及到权限和部门管理 */
drop table if exists `water_module`;
drop table if exists `water_role_module`;
drop table if exists `water_user`;
drop table if exists `water_userType`;
drop table if exists `water_department`;
drop table if exists `water_permission`;
drop table if exists `water_role`;
drop table if exists `water_role_permission`;
drop table if exists `water_user_role`;
drop table if exists `water_menucate`;

#系统模块表
DROP TABLE IF EXISTS `water_module`;
CREATE TABLE `water_module` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`name`  varchar(50) NOT NULL DEFAULT '' COMMENT '系统模块名称' ,
`flag`  varchar(20) NOT NULL DEFAULT '' COMMENT '系统模块标记' ,
`url`  varchar(300) NOT NULL DEFAULT '' COMMENT '系统访问URL' ,
`sort_no`  int(11) NOT NULL DEFAULT 0 COMMENT '排序号' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '最后更新人' ,
`update_date`  datetime NOT NULL COMMENT '最后更新时间' ,
PRIMARY KEY (`id`)
)
COMMENT='系统模块表'
;

#角色系统模块关系表
DROP TABLE IF EXISTS `water_role_module`;
CREATE TABLE `water_role_module` (
`role_id`  int(11) NOT NULL COMMENT '角色ID' ,
`module_id`  int(11) NOT NULL COMMENT '模块ID' ,
INDEX `idx_role_id` (`role_id`) USING BTREE ,
INDEX `idx_module_id` (`module_id`) USING BTREE 
)
COMMENT='角色系统模块关系表'
;


#用户表
create table `water_user`
(
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID主键' ,
`username`  varchar(30) NOT NULL DEFAULT '' COMMENT '登录用户名' ,
`password`  varchar(50) NOT NULL DEFAULT '' COMMENT '登录密码' ,
`fullname`  varchar(30) NOT NULL DEFAULT '' COMMENT '姓名' ,
`gender`  tinyint(1) NOT NULL DEFAULT 1 COMMENT '性别：1男0女' ,
`is_admin`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否管理员：1是0否' ,
`department_id`  int(11) NOT NULL DEFAULT 0 COMMENT '外键，所属部门Id' ,
`is_lock`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否锁定：1是0否' ,
`delete_flag`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除：1是0否' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '最后更新人' ,
`update_date`  datetime NOT NULL COMMENT '最后更新时间' ,
PRIMARY KEY (`id`),
INDEX `idx_username` (`username`) USING BTREE
)
Engine = InnoDB
auto_increment = 10
charset = UTF8
COMMENT = '用户表'
;


#部门表
create table `water_department`
(
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID主键' ,
`name`  varchar(30) NOT NULL DEFAULT '' COMMENT '部门名称' ,
`remark`  varchar(500) NOT NULL DEFAULT '' COMMENT '部门描述' ,
`parent_id`  int(11) NOT NULL DEFAULT 0 COMMENT '父级id' ,
`structure`  varchar(20) NOT NULL DEFAULT '' COMMENT '部门的层级结构' ,
`sort_no`  int(11) NOT NULL DEFAULT 0  COMMENT '排序号' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '最后更新人' ,
`update_date`  datetime NOT NULL COMMENT '最后更新时间' ,
PRIMARY KEY (`id`)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8
comment = '部门表'
;

#权限表
create table `water_permission`
(
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`name`  varchar(50) NOT NULL DEFAULT '' COMMENT '菜单资源名称' ,
`url`  varchar(300) NOT NULL DEFAULT '' COMMENT '菜单资源URL' ,
`remark`  varchar(500) NOT NULL DEFAULT '' COMMENT '菜单资源简要描述' ,
`parent_id`  int(11) NOT NULL DEFAULT 0 COMMENT '父级id' ,
`structure`  varchar(20) NOT NULL DEFAULT '' COMMENT '菜单的层级结构' ,
`sort_no`  int(11) NOT NULL DEFAULT 0  COMMENT '排序号' ,
`module_flag`  varchar(20) NOT NULL DEFAULT '' COMMENT '所属系统模块的标记' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '最后更新人' ,
`update_date`  datetime NOT NULL COMMENT '最后更新时间' ,
PRIMARY KEY (`id`),
INDEX `idx_structure` (`structure`) USING BTREE
)
Engine = InnoDB
auto_increment = 10
charset = UTF8
comment = '权限表';


#角色表
create table `water_role`
(
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID主键' ,
`name`  varchar(30) NOT NULL DEFAULT '' COMMENT '角色名称' ,
`remark`  varchar(500) NOT NULL DEFAULT '' COMMENT '角色描述' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '最后更新人' ,
`update_date`  datetime NOT NULL COMMENT '最后更新时间' ,
PRIMARY KEY (`id`)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8
comment = '角色表'
;


#权限角色关联表
create table `water_role_permission`
(
`role_id`  int(11) NOT NULL COMMENT '角色ID' ,
`permission_id`  int(11) NOT NULL COMMENT '资源ID' ,
INDEX `idx_role_id` (`role_id`) USING BTREE ,
INDEX `idx_permission_id` (`permission_id`) USING BTREE ,
primary key (`role_id`, `permission_id`)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8
comment = '权限角色关联表';

#角色用户关联表
create table `water_user_role`
(
`user_id`  int(11) NOT NULL COMMENT '用户ID' ,
`role_id`  int(11) NOT NULL COMMENT '角色ID' ,
INDEX `idx_user_id` (`user_id`) USING BTREE ,
INDEX `idx_role_id` (`role_id`) USING BTREE ,
primary key (`user_id`, `role_id`)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8
comment = '角色用户关联表';


#菜单分类表
create table `water_menucate`
(
`id`        int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`name`      varchar(50) NOT NULL DEFAULT '' COMMENT '菜单资源名称' ,
`sort_no`   int(11) NOT NULL DEFAULT 0  COMMENT '排序号' ,
`is_lock`   tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否锁定：1是0否' ,
`parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父级id' ,
`icon`      varchar(100) DEFAULT "" COMMENT '图标',
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '最后更新人' ,
`update_date`  datetime NOT NULL COMMENT '最后更新时间' ,
primary key (`id`)
)
Engine = InnoDB
auto_increment = 10
charset = UTF8 
comment = '菜单分类表';


/*================================================================================================================================================*/
/*                      				基础权限导入数据 				*/
/*================================================================================================================================================*/

/* water_module  */
INSERT INTO `water_module`(id,name,flag,url,sort_no,create_person,create_date,update_person,update_date) 
VALUES
(1,'配置中心','p','http://127.0.0.1:8080',10,'system',NOW(),'system',NOW()),
(3,'设备中心','c','http://127.0.0.1:8080',30,'system',NOW(),'system',NOW()),
(4,'数据中心','d','http://127.0.0.1:8080',30,'system',NOW(),'system',NOW())
;
/* water_User is_admin会自动拥有全部权限  */
INSERT INTO `water_user`(id,username,password,fullname,gender,is_admin,department_id,is_lock,delete_flag,create_person,create_date,update_person,update_date) 
VALUES 
(1, 'system' , '63a9f0ea7bb98050796b649e85481845', '超级管理员'        , 1, 1, 0, 0, 0, 'system',  NOW(), 'system',  NOW()),
(2, 'root'   , '63a9f0ea7bb98050796b649e85481845', '管理员'             , 1, 0, 1, 0, 0, 'system',  NOW(), 'system',  NOW()),
(3, 'user' , '63a9f0ea7bb98050796b649e85481845', '普通用户'             , 1, 0, 1, 0, 0, 'system',  NOW(), 'system',  NOW()),
(4, 'user_p' , '63a9f0ea7bb98050796b649e85481845', '配置中心用户'       , 1, 0, 2, 0, 0, 'system',  NOW(), 'system',  NOW()),
(5, 'user_device' , '63a9f0ea7bb98050796b649e85481845', '设备中心用户' , 1, 0, 3, 0, 0, 'system',  NOW(), 'system',  NOW()),
(6, 'user_data' , '63a9f0ea7bb98050796b649e85481845', '数据中心用户'   , 1, 0, 4, 0, 0, 'system',  NOW(), 'system',  NOW()),
(99, 'guest', '63a9f0ea7bb98050796b649e85481845', '客人'                 , 1, 0, 5, 0, 0, 'system', NOW(), 'system', NOW())
;

/* water_UserType  */
/* water_Department  */
INSERT INTO `water_department`(id,name,remark,parent_id,structure,sort_no,create_person,create_date,update_person,update_date) 
VALUES 
(1, '总部', '', 0, 's-1', '1', 'system', '2018-10-21 01:04:50', 'system', '2018-10-23 01:03:39'),

(2, '模块中心', '', 1, 's-1-1', '2', 'system',NOW(), 'system', NOW()),
(3, '设备中心', '', 1, 's-1-2', '3', 'system', NOW(), 'system',NOW()),
(4, '数据中心', '', 1, 's-1-3', '1', 'system', NOW(), 'system',NOW()),

(5, '总裁办', '', 4, 's-1-3-1', '1', 'system',NOW(), 'system',NOW()),
(6, '财务部', '', 4, 's-1-3-2', '2', 'system',NOW(), 'system', NOW()),
(7, '人力资源部', '', 4, 's-1-3-3', '3', 'system',NOW(), 'system', NOW()),

(8, '开发组', '', 3, 's-1-2-1', '1', 'system', NOW(), 'system', NOW()),
(9, '测试组', '', 3, 's-1-2-2', '2', 'system', NOW(), 'system', NOW()),
(10, '运维组', '', 3, 's-1-2-3', '3', 'system',NOW(), 'system',NOW()),

(11, '视觉形象部', '', 2, 's-1-1-3', '3', 'system', NOW(), 'system',NOW()),
(12, '女装部', '', 11, 's-1-1-1', '1', 'system',NOW(), 'system',NOW()),
(13, '男装部', '', 11, 's-1-1-2', '2', 'system',NOW(), 'system',NOW())
;
/* water_Permission  */
INSERT INTO `water_Permission`(id,name,url,remark,parent_id,structure,sort_no,module_flag,create_person,create_date,update_person,update_date) 
VALUES
(1,'部门管理','/department/listView.do','',0,'s-1',1,'p','system',NOW(),'system',NOW()),
(2,'权限管理','/permission/listView.do','',0,'s-2',2,'p','system',NOW(),'system',NOW()),
(3,'角色管理','/role/listView.do','',0,'s-3',3,'p','system',NOW(),'system',NOW()),
(4,'帐户管理','/user/listView.do','',0,'s-4',4,'p','system',NOW(),'system',NOW())
;

INSERT INTO `water_role_module` VALUES ('1', '1');
INSERT INTO `water_role_module` VALUES ('2', '1');
INSERT INTO `water_role_module` VALUES ('3', '3');
INSERT INTO `water_role_module` VALUES ('4', '4');
INSERT INTO `water_role_module` VALUES ('1', '3');
INSERT INTO `water_role_module` VALUES ('1', '4');

INSERT INTO `water_user_role` VALUES ('2', '1');
INSERT INTO `water_user_role` VALUES ('3', '2');
INSERT INTO `water_user_role` VALUES ('3', '3');
INSERT INTO `water_user_role` VALUES ('3', '4');
INSERT INTO `water_user_role` VALUES ('4', '2');
INSERT INTO `water_user_role` VALUES ('5', '3');
INSERT INTO `water_user_role` VALUES ('6', '4');

INSERT INTO `water_role` VALUES (1, '管理员', '管理员', 'system', NOW(), 'system', NOW());
INSERT INTO `water_role` VALUES (2, '配置中心主管', '配置中心主管', 'system', NOW(), 'system', NOW());
INSERT INTO `water_role` VALUES (3, '设备中心主管', '设备中心主管', 'system', NOW(), 'system', NOW());
INSERT INTO `water_role` VALUES (4, '数据中心主管', '数据中心主管', 'system', NOW(), 'system', NOW());

INSERT INTO `water_role_permission` VALUES ('1', '1');
INSERT INTO `water_role_permission` VALUES ('1', '2');
INSERT INTO `water_role_permission` VALUES ('1', '3');
INSERT INTO `water_role_permission` VALUES ('1', '4');
INSERT INTO `water_role_permission` VALUES ('1', '50');
INSERT INTO `water_role_permission` VALUES ('1', '51');
INSERT INTO `water_role_permission` VALUES ('1', '52');
INSERT INTO `water_role_permission` VALUES ('1', '60');
INSERT INTO `water_role_permission` VALUES ('1', '61');
INSERT INTO `water_role_permission` VALUES ('1', '62');
INSERT INTO `water_role_permission` VALUES ('1', '63');
INSERT INTO `water_role_permission` VALUES ('1', '100');
INSERT INTO `water_role_permission` VALUES ('1', '101');
INSERT INTO `water_role_permission` VALUES ('1', '102');
INSERT INTO `water_role_permission` VALUES ('1', '103');
INSERT INTO `water_role_permission` VALUES ('1', '111');
INSERT INTO `water_role_permission` VALUES ('1', '112');
INSERT INTO `water_role_permission` VALUES ('1', '141');
INSERT INTO `water_role_permission` VALUES ('1', '142');
INSERT INTO `water_role_permission` VALUES ('2', '1');
INSERT INTO `water_role_permission` VALUES ('2', '2');
INSERT INTO `water_role_permission` VALUES ('2', '3');
INSERT INTO `water_role_permission` VALUES ('2', '4');
INSERT INTO `water_role_permission` VALUES ('3', '50');
INSERT INTO `water_role_permission` VALUES ('3', '51');
INSERT INTO `water_role_permission` VALUES ('3', '52');
INSERT INTO `water_role_permission` VALUES ('3', '60');
INSERT INTO `water_role_permission` VALUES ('3', '61');
INSERT INTO `water_role_permission` VALUES ('3', '62');
INSERT INTO `water_role_permission` VALUES ('3', '63');
INSERT INTO `water_role_permission` VALUES ('4', '100');
INSERT INTO `water_role_permission` VALUES ('4', '101');
INSERT INTO `water_role_permission` VALUES ('4', '102');
INSERT INTO `water_role_permission` VALUES ('4', '103');
INSERT INTO `water_role_permission` VALUES ('4', '111');
INSERT INTO `water_role_permission` VALUES ('4', '112');
INSERT INTO `water_role_permission` VALUES ('4', '141');
INSERT INTO `water_role_permission` VALUES ('4', '142');

/* water_MenuCate  */
INSERT INTO `water_menucate`(id,name,sort_no,is_lock,parent_id,icon,create_person,create_date,update_person,update_date) 
VALUES 
(1,  '保护管理', 2  ,1,0,'','system', NOW(), 'system',NOW()),
(3,  '履职尽责', 3  ,1,0,'','system', NOW(), 'system',NOW()),
(4,  '问题整治', 5  ,1,0,'','system', NOW(), 'system',NOW()),
(5,  '社会力量', 6  ,1,0,'','system', NOW(), 'system',NOW()),
(7,  '系统配置', 9  ,1,0,'','system', NOW(), 'system',NOW()),
(8,  '考核问责', 8  ,1,0,'','system', NOW(), 'system',NOW()),
(9,  '组织体系', 1  ,1,0,'','system', NOW(), 'system',NOW()),
(12, '日志管理', 11 ,1,0,'','system', NOW(), 'system',NOW())
;