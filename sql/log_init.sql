/*================================================================================================================================================*/
/*                                      日记和预警部门以及一些其他的支持功能【数据管理】                            			*/
/*================================================================================================================================================*/

#设备日志表 - 设备日记表(跟device_info decivegps相关的)
DROP TABLE IF EXISTS `d_deviceinfo_log`;
CREATE TABLE `d_deviceinfo_log` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`deviceinfo_id`  int(11) UNSIGNED NOT NULL COMMENT '设备编号' ,
`log_type_id`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '日志类型ID' ,
`remark`  varchar(500) NOT NULL DEFAULT '' COMMENT '日志描述' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
PRIMARY KEY (`id`) ,
INDEX `idx_deviceinfo_log_deviceinfo_id` (`deviceinfo_id`) USING BTREE 
)
COMMENT='设备日志表'
;

#设备数据,设备传输过来的数据,临时设计到时候觉得是不是要一个设备独立设计一张表
DROP TABLE IF EXISTS `d_deviceinfo_data`;
CREATE TABLE `d_deviceinfo_data`(
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`devicegps_id` int(11) UNSIGNED NOT NULL COMMENT '设备编号' ,
`data`         varchar(1024) NOT NULL DEFAULT '' COMMENT '传输过来的数据' ,
`data_type_id`    tinyint(4)  NOT NULL DEFAULT 0 COMMENT '数据类型用于不同解析 比如a#b;c等不同的数据有不同的分割类型',
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
PRIMARY KEY (`id`) 
)
COMMENT='设备数据表'
;

#系统日记表
DROP TABLE IF EXISTS `d_system_log`;
CREATE TABLE `d_system_log`(
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`log_type_id`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '日志类型ID 1' ,
`remark`  varchar(500) NOT NULL DEFAULT '' COMMENT '日志描述' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
PRIMARY KEY (`id`)
)
COMMENT='系统日记表'
;

#系统通知表
DROP TABLE IF EXISTS `d_system_message`;
CREATE TABLE `d_system_message`(
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`message_type`   tinyint(4) UNSIGNED NOT NULL COMMENT '通知类型：1系统内部消息 2消息推送 4公告通知 5警报 6' ,
`message_method` tinyint(4) UNSIGNED NOT NULL COMMENT '通知手段：1短信 2邮件' ,
`title`        varchar(500) NOT NULL DEFAULT '' COMMENT '标题' ,
`content`      varchar(2048) NOT NULL DEFAULT '' COMMENT '通知内容' ,
`status`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
PRIMARY KEY (`id`) 
)
COMMENT='设备数据表'
;
#类型状态字典表
DROP TABLE IF EXISTS `c_dictionary`;    -- 删除旧的数据表 以后这行删掉
DROP TABLE IF EXISTS `d_dictionary`;
CREATE TABLE `d_dictionary` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`group`  varchar(50) NOT NULL COMMENT '状态分组' ,
`code`  tinyint(4) NOT NULL COMMENT '状态代码' ,
`name`  varchar(100) NOT NULL COMMENT '状态名称' ,
`sort_no`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '排序号' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
PRIMARY KEY (`id`) ,
INDEX `idx_dictionary_group` (`group`) USING BTREE
)
COMMENT='类型状态字典表'
;

/*================================================================================================================================================*/
/*                                       基础权限导入数据【数据管理】                            */
/*================================================================================================================================================*/
delete from `water_module` where name='数据中心';
INSERT INTO `water_module`(id,name,flag,url,sort_no,create_person,create_date,update_person,update_date) 
VALUES
(4,'数据中心','d','http://127.0.0.1:8080',30,'system',NOW(),'system',NOW())
;

delete from `water_permission` where module_flag='d';
INSERT INTO `water_permission` VALUES ('100', '数据管理', '', '', '0', 's-1', '1', 'd', 'system', '2018-08-29 11:53:52', 'system', '2018-08-29 11:53:52');
INSERT INTO `water_permission` VALUES ('101', '日记管理', '', '', '0', 's-2', '2', 'd', 'system', '2018-08-29 11:53:52', 'system', '2018-08-29 11:53:52');
INSERT INTO `water_permission` VALUES ('102', '数据字典', '/dictionary/listView.do', '', '0', 's-3', '3', 'd', 'system', '2018-08-29 11:53:52', 'system', '2018-08-29 11:53:52');
INSERT INTO `water_permission` VALUES ('103', '数据统计', '', '', '0', 's-4', '4', 'd', 'system', '2018-08-29 11:53:52', 'system', '2018-08-29 11:53:52');
INSERT INTO `water_permission` VALUES ('111', '设备日记', '/deviceinfoLog/listView.do', '', '101', 's-2-1', '1', 'd', 'system', '2018-08-29 11:53:52', 'system', '2018-08-29 11:53:52');
INSERT INTO `water_permission` VALUES ('112', '系统日记', '/systemLog/listView.do', '', '101', 's-2-2', '2', 'd', 'system', '2018-08-29 11:53:52', 'system', '2018-08-29 11:53:52');
INSERT INTO `water_permission` VALUES ('141', '设备数据统计', '/deviceinfoData/listView.do', '', '103', 's-4-1', '1', 'd', 'system', '2018-08-29 11:53:52', 'system', '2018-08-29 11:53:52');
INSERT INTO `water_permission` VALUES ('142', '操作数据统计', '', '', '103', 's-4-2', '2', 'd', 'system', '2018-08-29 11:53:52', 'system', '2018-08-29 11:53:52');

-- 数据初始化-设备字典表
INSERT INTO `d_dictionary`
VALUES
(1,'deviceinfo_status',0,'新建'  ,1,'system',now()),
(2,'deviceinfo_status',1,'运行中',2,'system',now()),
(3,'deviceinfo_status',2,'维护中',3,'system',now()),
(4,'deviceinfo_status',3,'中止'  ,4,'system',now()),

(11,'deviceinfo_log',1,'新增设备',10,'system',now()),
(12,'deviceinfo_log',2,'修改设备',20,'system',now()),
(13,'deviceinfo_log',3,'图片上传完整',30,'system',now()),
(14,'deviceinfo_log',4,'图片删除',40,'system',now()),
(15,'deviceinfo_log',5,'图片不再完整',50,'system',now()),
(16,'deviceinfo_log',6,'设备部署',60,'system',now()),
(17,'deviceinfo_log',7,'设备下架',70,'system',now()),
(18,'deviceinfo_log',8,'修改价格',80,'system',now()),
(19,'deviceinfo_log',9,'删除设备',90,'system',now()),

(21,'deviceinfo_pictype',1,'原图-o',1,'system',now()),	#o
(22,'deviceinfo_pictype',2,'大图-l',2,'system',now()),	#l
(23,'deviceinfo_pictype',3,'中图-m',3,'system',now()),	#m
(24,'deviceinfo_pictype',4,'小图-s',4,'system',now()),	#s
(25,'deviceinfo_pictype',5,'微图-t',5,'system',now()),	#t
(26,'deviceinfo_pictype',6,'宝贝描述图-b',6,'system',now())	#b
;

-- 数据初始化-数据字典表
INSERT INTO `d_dictionary`
VALUES
(31,'system_log',0,'登陆',1,'system',now()),
(32,'system_log',1,'注册',2,'system',now()),
(33,'system_log',2,'权限操作',3,'system',now()),
(34,'system_log',3,'品牌管理',3,'system',now()),
(35,'system_log',4,'分类管理',3,'system',now()),

(41,'system_message_method',0,'短信' ,1,'system',now()),
(42,'system_message_method',1,'邮件' ,2,'system',now()),
(43,'system_message_method',2,'其他' ,3,'system',now()),

(51,'system_message_type',0,'系统内部'  ,1,'system',now()),
(52,'system_message_type',1,'消息推送'     ,2,'system',now()),
(53,'system_message_type',4,'公告通知'     ,4,'system',now()),
(54,'system_message_type',5,'警报'     ,5,'system',now()),
(55,'deviceinfo_data_type',0,'A#1',  1,'system',now())
;

INSERT INTO `d_dictionary`(`group`,`code`,`name`,sort_no,create_person,create_date)
VALUES
  ('echart_months',0,'当月',1,'system',now()),
  ('echart_months',1,'一月',2,'system',now()),
  ('echart_months',2,'二月',3,'system',now()),
  ('echart_months',3,'三月',4,'system',now()),
  ('echart_months',4,'四月',5,'system',now()),
  ('echart_months',5,'五月',6,'system',now()),
  ('echart_months',6,'六月',7,'system',now()),
  ('echart_months',7,'七月',8,'system',now()),
  ('echart_months',8,'八月',9,'system',now()),
  ('echart_months',9,'九月',10,'system',now()),
  ('echart_months',10,'十月',11,'system',now()),
  ('echart_months',11,'十一月',12,'system',now()),
  ('echart_months',12,'十二月',13,'system',now())
;
/*================================================================================================================================================*/
/*                                      日记和预警部门以及一些其他的支持功能-数据导入【数据管理】                            			*/
/*================================================================================================================================================*/
