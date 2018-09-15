package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.DeviceinfodataPollution;

import java.util.List;

public interface DeviceinfodataPollutionDao extends BaseMapper<DeviceinfodataPollution> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    List<DeviceinfodataPollution> getData(int type);
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Long id);

    DeviceinfodataPollution selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceinfodataPollution record);

    int updateByPrimaryKey(DeviceinfodataPollution record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<DeviceinfodataPollution> list);

    int batchInsertSelective(java.util.List<DeviceinfodataPollution> list, DeviceinfodataPollution.Column ... selective);
}