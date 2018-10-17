
#监测因子监测设备关系表
DROP TABLE IF EXISTS `c_monitofactors_deviceinfo`;
CREATE TABLE `c_monitofactors_deviceinfo` (
`factor_id`  BIGINT NOT NULL COMMENT '角色ID' ,
`deviceinfo_id`  BIGINT NOT NULL COMMENT '模块ID' ,
INDEX `idx_factor_id` (`factor_id`) USING BTREE ,
INDEX `idx_deviceinfo_id` (`deviceinfo_id`) USING BTREE
)
COMMENT='监测因子监测设备关系表'
;