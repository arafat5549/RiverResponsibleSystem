/*================================================================================================================================================*/
/*                                      设备管理部分                            			*/
/*================================================================================================================================================*/
#设备表
DROP TABLE IF EXISTS `c_deviceinfo`;
CREATE TABLE `c_deviceinfo` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`sno`  varchar(50) NOT NULL COMMENT '设备序列号' ,
`name`  varchar(200) NOT NULL COMMENT '设备名称' ,
`protocol` varchar(200) NOT NULL COMMENT '协议' ,
`brand_id`  int(11) UNSIGNED NOT NULL COMMENT '所属品牌ID' ,
`category_id`  int(11) UNSIGNED NOT NULL COMMENT '所属分类ID' ,
`supplier` varchar(200) NOT NULL COMMENT '供应商名称' ,
`status`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态' ,
`is_picture_finish`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '图片是否上传完整，1是0否' ,
`delete_flag`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除：1是0否' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '更新人' ,
`update_date`  datetime NOT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`) ,
INDEX `idx_deviceinfo_brand_id` (`brand_id`) USING BTREE ,
INDEX `idx_deviceinfo_category_id` (`category_id`) USING BTREE 
)
COMMENT='设备表'
;
#设备部署表
DROP TABLE IF EXISTS `c_devicegps`;
CREATE TABLE `c_devicegps` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`monitorsite_id` int(11) UNSIGNED NOT NULL COMMENT '设备编号' ,
`deviceinfo_id`  int(11) UNSIGNED NOT NULL COMMENT '设备编号' ,
`parent_id`      int(11) UNSIGNED NOT NULL COMMENT '父级id' ,
`structure`  varchar(20) NOT NULL DEFAULT '' COMMENT '层级结构' ,
`longitude`      varchar(30) NOT NULL COMMENT '设备所在经度' ,
`latitude`      varchar(30) NOT NULL COMMENT '设备所在维度' ,
`ip_address`    varchar(30)  COMMENT '设备的ip地址' ,
`port`			 int(11)   COMMENT '设备的端口地址' ,
`url`			 varchar(100)   COMMENT '视频流地址' ,
`status`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态' ,
`delete_flag`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除：1是0否' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '更新人' ,
`update_date`  datetime NOT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`) 
)
COMMENT='设备部署表'
;
#监测站表 类似部门
DROP TABLE IF EXISTS `c_monitorsite`;
CREATE TABLE `c_monitorsite` (
`id`    int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`name`  varchar(200) NOT NULL COMMENT '名称' ,
`user_id`      int(11) UNSIGNED NOT NULL COMMENT '负责人id' ,
`parent_id`      int(11) UNSIGNED NOT NULL COMMENT '父级id' ,
`structure`  varchar(20) NOT NULL DEFAULT '' COMMENT '层级结构' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '更新人' ,
`update_date`  datetime NOT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`) 
)
COMMENT='监测站表'
;
#设备图片表
DROP TABLE IF EXISTS `c_deviceinfo_picture`;
CREATE TABLE `c_deviceinfo_picture` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`name`  varchar(20) NOT NULL COMMENT '图片名称' ,
`deviceinfo_id`  int(11) UNSIGNED NOT NULL COMMENT '设备编号' ,
`picture_type`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '图片类型' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '更新人' ,
`update_date`  datetime NOT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`) ,
INDEX `idx_deviceinfo_picture_deviceinfo_id` (`deviceinfo_id`) USING BTREE 
)
COMMENT='设备图片表'
;

#品牌表
DROP TABLE IF EXISTS `c_brand`;
CREATE TABLE `c_brand` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`name`  varchar(50) NOT NULL DEFAULT '' COMMENT '品牌名称' ,
`ename`  varchar(50) NOT NULL DEFAULT '' COMMENT '英文名称' ,
`website`  varchar(200) NOT NULL DEFAULT '' COMMENT '品牌网址' ,
`pic_large`  varchar(100) NOT NULL DEFAULT '' COMMENT '品牌大图(140*120)' ,
`pic_middle`  varchar(100) NOT NULL DEFAULT '' COMMENT '品牌中图(110*50)' ,
`pic_small`  varchar(100) NOT NULL DEFAULT '' COMMENT '品牌小图(85*40)' ,
`letter`  char(1) NOT NULL DEFAULT '' COMMENT '归属哪个字母：A-Z' ,
`sort_no`  int(11) NOT NULL DEFAULT 0 COMMENT '排序号' ,
`delete_flag`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除：1是0否' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '更新人' ,
`update_date`  datetime NOT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`)
)
COMMENT='品牌表'
;

#分类表
DROP TABLE IF EXISTS `c_category`;
CREATE TABLE `c_category` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`name`  varchar(50) NOT NULL DEFAULT '' COMMENT '分类名称' ,
`struct_name`  varchar(200) NOT NULL DEFAULT '' COMMENT '中文名称的分类结构' ,
`level`  tinyint(4) NOT NULL DEFAULT 1 COMMENT '层级：1,2,3' ,
`parent_id`  int(11) UNSIGNED NOT NULL COMMENT '父级id' ,
`sort_no`  int(11) NOT NULL DEFAULT 0 COMMENT '排序号' ,
`delete_flag`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除：1是0否' ,
`create_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '记录生成人' ,
`create_date`  datetime NOT NULL COMMENT '记录生成时间' ,
`update_person`  varchar(30) NOT NULL DEFAULT '' COMMENT '更新人' ,
`update_date`  datetime NOT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`),
INDEX `idx_struct_name` (`struct_name`) USING BTREE 
)
COMMENT='分类表'
;

/*================================================================================================================================================*/
/*                                       基础权限导入数据                            */
/*================================================================================================================================================*/
delete from `water_module` where name='设备中心';
INSERT INTO `water_module`(id,name,flag,url,sort_no,create_person,create_date,update_person,update_date) 
VALUES
(3,'设备中心','c','http://127.0.0.1:8080',30,'system',NOW(),'system',NOW())
;

delete from `water_permission` where module_flag='c';
INSERT INTO `water_permission`(id,name,url,remark,parent_id,structure,sort_no,module_flag,create_person,create_date,update_person,update_date) 
VALUES
(50,'基础管理','','',0,'s-1',1,'c','system',NOW(),'system',NOW()),
(51,'设备管理','','',0,'s-2',2,'c','system',NOW(),'system',NOW()),
(52,'监测站管理','/monitorsite/listView.do','',0,'s-3',3,'c','system',NOW(),'system',NOW()),
(60,'分类管理','/category/listView.do'  ,'',50,'s-1-1',1,'c','system',NOW(),'system',NOW()),
(61,'品牌管理','/brand/listView.do'     ,'',50,'s-1-2',2,'c','system',NOW(),'system',NOW()),
(62,'设备管理','/deviceinfo/listView.do','',51,'s-2-2',2,'c','system',NOW(),'system',NOW()),
(63,'设备部署','/devicegps/listView.do'  ,'',51,'s-2-4',4,'c','system',NOW(),'system',NOW())
;
/*================================================================================================================================================*/
/*                                      设备管理导入数据                            */
/*================================================================================================================================================*/


#数据初始化-设备表
truncate table c_deviceinfo;
insert into c_deviceinfo(id,sno,name,protocol,brand_id,category_id,supplier,status,is_picture_finish,delete_flag ,create_person,create_date,update_person,update_date)
values

(88888881,'BBL3PQF4DU1CS1','水质检测设备1','https',1,1,"金钱猫",1,1,0,'system',NOW(),'system',NOW()),
(88888882,'BBL3PQF4DU1CS2','水质检测设备2','https',1,1,"金钱猫",1,1,0,'system',NOW(),'system',NOW()),
(88888883,'BBL3PQF4DU1CS3','水质检测设备3','https',1,1,"金钱猫",1,1,0,'system',NOW(),'system',NOW()),
(88888884,'BBL3PQF4DU1CS4','水质检测设备4','https',1,1,"金钱猫",1,1,0,'system',NOW(),'system',NOW()),
(88888885,'BBL3PQF4DU1CS5','水质检测设备5','https',1,1,"金钱猫",1,1,0,'system',NOW(),'system',NOW()),

(87888881,'BBM3PQF4DU1CS1','视频监控1','rtmp',1,7,"金钱猫",1,1,0,'system',NOW(),'system',NOW()),
(87888882,'BBM3PQF4DU1CS2','视频监控2','rtmp',1,7,"金钱猫",1,1,0,'system',NOW(),'system',NOW()),
(87888883,'BBM3PQF4DU1CS3','视频监控3','rtmp',1,7,"金钱猫",1,1,0,'system',NOW(),'system',NOW()),
(87888884,'BBM3PQF4DU1CS4','视频监控4','rtmp',1,7,"金钱猫",1,1,0,'system',NOW(),'system',NOW()),
(87888885,'BBM3PQF4DU1CS5','视频监控5','rtmp',1,7,"金钱猫",1,1,0,'system',NOW(),'system',NOW()),

(99999991,'BBL3PQZ5DU1CS1','空气检测设备1','tcp',1,1,"华为",1,1,0,'system',NOW(),'system',NOW()),
(99999992,'BBL3PQZ5DU1CS2','空气检测设备2','tcp',1,1,"华为",1,1,0,'system',NOW(),'system',NOW()),
(99999993,'BBL3PQZ5DU1CS3','空气检测设备3','tcp',1,1,"华为",1,1,0,'system',NOW(),'system',NOW()),
(99999994,'BBL3PQZ5DU1CS4','空气检测设备4','tcp',1,1,"华为",1,1,0,'system',NOW(),'system',NOW()),
(99999995,'BBL3PQZ5DU1CS5','空气检测设备5','tcp',1,1,"华为",1,1,0,'system',NOW(),'system',NOW())
;

#检测站信息
truncate table c_monitorsite;
insert into c_monitorsite(id,name,user_id,parent_id,structure,create_person,create_date,update_person,update_date)
values
(1,'三明市站点',1,0,'s-1' , 'system',NOW(),'system',NOW()),
(2,'三明市梅列区站点',1,1,'s-1-1' , 'system',NOW(),'system',NOW()),
(3,'三明市大田乡站点',1,2,'s-1-2' , 'system',NOW(),'system',NOW()),
(4,'三明市石壁镇站点',1,3,'s-1-3' , 'system',NOW(),'system',NOW()),

(101,'福州市站点',2,0,'s-101', 'system',NOW(),'system',NOW()),
(102,'福清市站点',2,101,'s-101-102', 'system',NOW(),'system',NOW()),
(103,'闽侯市站点',2,101,'s-101-103', 'system',NOW(),'system',NOW()),
(104,'高山镇站点',2,102,'s-101-104', 'system',NOW(),'system',NOW())
;

# 设备部署信息 c_devicegps
truncate table c_devicegps;
insert into c_devicegps(id,monitorsite_id,deviceinfo_id,parent_id,structure,longitude,latitude,ip_address,port,url,status,delete_flag ,create_person,create_date,update_person,update_date)
values
(1,1,88888881,0,'s-1',117.51812339,26.77153804,'127.0.0.1',8080,'',1,0, 'system',NOW(),'system',NOW()),
(2,1,88888882,0,'s-2',117.56049444,26.29862222,'127.0.0.1',8080,'',1,0, 'system',NOW(),'system',NOW()),
(3,1,88888883,0,'s-3',117.04944444,26.54944444,'127.0.0.1',8080,'',1,0, 'system',NOW(),'system',NOW()),

(4,1,87888881,0,'s-4',117.77735,26.383066 ,'127.0.0.1',8080,'rtmp:\/\/183.250.15.3:10035\/live\/qingzhouqiao',1,0, 'system',NOW(),'system',NOW()),
(5,1,87888882,0,'s-5',118.026623,26.360079,'127.0.0.1',8080,'rtmp:\/\/183.250.15.3:10035\/live\/zhenghucun',1,0, 'system',NOW(),'system',NOW()),
(6,1,87888883,0,'s-6',117.941284,26.347635,'127.0.0.1',8080,'rtmp:\/\/183.250.15.3:10035\/live\/gaoqiao',1,0, 'system',NOW(),'system',NOW())
;




#数据初始化-品牌表
truncate table c_brand;
insert into c_brand(id,name,ename,website,pic_large,pic_middle,pic_small,letter,sort_no,delete_flag,create_person,create_date,update_person,update_date)
values


(1001,'金钱猫','jinqianmao','','brand_10_l.jpg','brand_10_m.jpg','brand_10_s.jpg',5,11,0,'system',NOW(),'system',NOW()),
(1002,'华为','huawei','','brand_10_l.jpg','brand_10_m.jpg','brand_10_s.jpg',5,12,0,'system',NOW(),'system',NOW())
;

#数据初始化-分类表
truncate table c_category;
insert into c_category(id,name,struct_name,level,parent_id,sort_no,delete_flag,create_person,create_date,update_person,update_date)
values
(1,'检测设备','检测设备',1,0,1,0,'system',NOW(),'system',NOW()),
(2,'监控设备','监控设备',1,0,2,0,'system',NOW(),'system',NOW()),
(5,'水质检测','检测设备-水质检测',2,1,1,0,'system',NOW(),'system',NOW()),
(6,'空气检测','检测设备-空气检测',2,1,2,0,'system',NOW(),'system',NOW()),
(7,'视频','监控设备-视频',2,2,1,0,'system',NOW(),'system',NOW()),
(8,'无人机','监控设备-无人机',2,2,2,0,'system',NOW(),'system',NOW()),
(105,'手机视频','监控设备-视频-手机视频',3,7,1,0,'system',NOW(),'system',NOW()),
(106,'摄像头','监控设备-视频-摄像头',3,7,2,0,'system',NOW(),'system',NOW()),
(9999,'测试分类','测试分类',1,0,1,0,'system',NOW(),'system',NOW())
;
#数据初始化-设备图片表
truncate table c_deviceinfo_picture;
insert into c_deviceinfo_picture(id,name,deviceinfo_id,picture_type,create_person,create_date,update_person,update_date)
values
(1,'99913497_01_b.jpg',88888881,6,'system',NOW(),'system',NOW()),
(2,'99913497_01_b.jpg',88888882,6,'system',NOW(),'system',NOW())
;



