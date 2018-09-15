package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.DeviceinfoPicture;

public interface DeviceinfoPictureDao extends BaseMapper<DeviceinfoPicture> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    DeviceinfoPicture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeviceinfoPicture record);

    int updateByPrimaryKey(DeviceinfoPicture record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<DeviceinfoPicture> list);

    int batchInsertSelective(java.util.List<DeviceinfoPicture> list, DeviceinfoPicture.Column ... selective);
}