

/*================================================================================================================================================*/
/*                                      设备管理部分                            */
/*================================================================================================================================================*/
#数据初始化-商品字典表
insert into `c_dictionary`(`group`,`code`,`name`,sort_no,create_person,create_date)
values
('deviceinfo_status',0,'新建',1,'system',now()),
('deviceinfo_status',1,'运行中',2,'system',now()),
('deviceinfo_status',2,'维护中',3,'system',now()),
('deviceinfo_status',3,'中止',4,'system',now()),

('deviceinfo_log',1,'新增商品',10,'system',now()),
('deviceinfo_log',2,'修改商品',20,'system',now()),
('deviceinfo_log',3,'图片上传完整',30,'system',now()),
('deviceinfo_log',4,'图片删除',40,'system',now()),
('deviceinfo_log',5,'图片不再完整',50,'system',now()),
('deviceinfo_log',6,'商品销售',60,'system',now()),
('deviceinfo_log',7,'商品停售',70,'system',now()),
('deviceinfo_log',8,'修改价格',80,'system',now()),
('deviceinfo_log',9,'删除商品',90,'system',now()),

('deviceinfo_pictype',1,'原图-o',1,'system',now()),	#o
('deviceinfo_pictype',2,'大图-l',2,'system',now()),	#l
('deviceinfo_pictype',3,'中图-m',3,'system',now()),	#m
('deviceinfo_pictype',4,'小图-s',4,'system',now()),	#s
('deviceinfo_pictype',5,'微图-t',5,'system',now()),	#t
('deviceinfo_pictype',6,'宝贝描述图-b',6,'system',now()),	#b

('test',1,'测试数据',1,'system',now())
;

#数据初始化-设备表
truncate table c_deviceinfo;
insert into c_deviceinfo(id,sno,name,protocol,brand_id,category_id,supplier,status,is_picture_finish,delete_flag,create_person,create_date,update_person,update_date)
values
(99913497,'BBL3PQF4DU1CM3','BELLE/百丽啡色小牛皮3PQF4CM3秋季女单鞋','https',1,1,"金钱猫",1,1,0,'system',NOW(),'system',NOW())
;

#数据初始化-品牌表
truncate table c_brand;
insert into c_brand(id,name,ename,website,pic_large,pic_middle,pic_small,letter,sort_no,delete_flag,create_person,create_date,update_person,update_date)
values
(1,'百丽','belle','http://www.belle.com','brand_1_l.jpg','brand_1_m.jpg','brand_1_s.jpg',6,1,0,'system',NOW(),'system',NOW()),
(2,'天美意','teenmix','http://www.teenmix.com.cn','brand_2_l.jpg','brand_2_m.jpg','brand_2_s.jpg',5,2,0,'system',NOW(),'system',NOW()),
(3,'他她','tata','http://www.tatashoes.com.cn','brand_3_l.jpg','brand_3_m.jpg','brand_3_s.jpg',5,3,0,'system',NOW(),'system',NOW()),
(4,'思加图','staccato','http://www.staccato.com','brand_4_l.jpg','brand_4_m.jpg','brand_4_s.jpg',4,4,0,'system',NOW(),'system',NOW()),
(5,'百思图','basto','http://www.basto.com.cn','brand_5_l.jpg','brand_5_m.jpg','brand_5_s.jpg',5,5,0,'system',NOW(),'system',NOW()),
(6,'茵奈儿','innet','','brand_6_l.jpg','brand_6_m.jpg','brand_6_s.jpg',5,6,0,'system',NOW(),'system',NOW()),
(7,'真美诗','joypeace','http://www.joypeace.com','brand_7_l.jpg','brand_7_m.jpg','brand_7_s.jpg',5,7,0,'system',NOW(),'system',NOW()),
(8,'耐克','nike','','brand_8_l.jpg','brand_8_m.jpg','brand_8_s.jpg',5,8,0,'system',NOW(),'system',NOW()),
(9,'阿迪达斯','adidas','','brand_9_l.jpg','brand_9_m.jpg','brand_9_s.jpg',5,9,0,'system',NOW(),'system',NOW()),
(10,'探路者','toread','','brand_10_l.jpg','brand_10_m.jpg','brand_10_s.jpg',5,10,0,'system',NOW(),'system',NOW())
;

#数据初始化-分类表
truncate table c_category;
insert into c_category(id,name,struct_name,level,parent_id,sort_no,delete_flag,create_person,create_date,update_person,update_date)
values
(1,'检测设备','检测设备',1,0,1,0,'system',NOW(),'system',NOW()),
(2,'监控设备','监控设备',1,0,2,0,'system',NOW(),'system',NOW()),
--(3,'女装','女装',1,0,3,0,'system',NOW(),'system',NOW()),
--(4,'男装','男装',1,0,4,0,'system',NOW(),'system',NOW()),
(5,'水质检测','检测设备-水质检测',2,1,1,0,'system',NOW(),'system',NOW()),
(6,'空气检测','检测设备-空气检测',2,1,2,0,'system',NOW(),'system',NOW()),
(7,'视频','监控设备-视频',2,2,1,0,'system',NOW(),'system',NOW()),
(8,'无人机','监控设备-无人机',2,2,2,0,'system',NOW(),'system',NOW()),
-- (101,'中空凉鞋','女鞋-凉鞋-中空凉鞋',3,5,1,0,'system',NOW(),'system',NOW()),
-- (102,'后空凉鞋','女鞋-凉鞋-后空凉鞋',3,5,2,0,'system',NOW(),'system',NOW()),
-- (103,'长靴','女鞋-靴子-长靴',3,6,1,0,'system',NOW(),'system',NOW()),
-- (104,'短靴','女鞋-靴子-短靴',3,6,1,0,'system',NOW(),'system',NOW()),
(105,'手机视频','监控设备-视频-手机视频',3,7,1,0,'system',NOW(),'system',NOW()),
(106,'摄像头','监控设备-视频-摄像头',3,7,2,0,'system',NOW(),'system',NOW()),
--(107,'篮球鞋','男鞋-运动鞋-篮球鞋',3,8,1,0,'system',NOW(),'system',NOW()),
-- (108,'跑步鞋','男鞋-运动鞋-跑步鞋',3,8,2,0,'system',NOW(),'system',NOW()),
-- (109,'网球鞋','男鞋-运动鞋-网球鞋',3,8,2,0,'system',NOW(),'system',NOW()),
-- (110,'登山鞋','男鞋-运动鞋-登山鞋',3,8,2,0,'system',NOW(),'system',NOW())
(9999,'测试分类','测试分类',1,0,1,0,'system',NOW(),'system',NOW())
;
#数据初始化-商品图片表
truncate table c_deviceinfo_picture;
insert into c_deviceinfo_picture(id,name,deviceinfo_id,picture_type,create_person,create_date,update_person,update_date)
values
(1,'99913497_01_b.jpg',99913497,6,'system',NOW(),'system',NOW()),
(2,'99913497_01_b.jpg',99913497,6,'system',NOW(),'system',NOW())
;
