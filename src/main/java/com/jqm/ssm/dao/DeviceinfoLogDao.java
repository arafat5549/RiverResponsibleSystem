package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.DeviceinfoLog;
import com.jqm.ssm.entity.relate.DeviceinfoLogSearchModel;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DeviceinfoLogDao extends BaseMapper<DeviceinfoLog> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    DeviceinfoLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeviceinfoLog record);

    int updateByPrimaryKey(DeviceinfoLog record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<DeviceinfoLog> list);

    int batchInsertSelective(java.util.List<DeviceinfoLog> list, DeviceinfoLog.Column ... selective);

    Integer getDeviceinfoLogTotalBySearch(DeviceinfoLogSearchModel searchModel);

    List<DeviceinfoLog> getDeviceinfoLogListBySearch(DeviceinfoLogSearchModel searchModel);
}