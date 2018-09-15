package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.DeviceinfoData;
import com.jqm.ssm.entity.relate.DeviceinfoDataSearchModel;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DeviceinfoDataDao extends BaseMapper<DeviceinfoData> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    DeviceinfoData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeviceinfoData record);

    int updateByPrimaryKey(DeviceinfoData record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<DeviceinfoData> list);

    int batchInsertSelective(java.util.List<DeviceinfoData> list, DeviceinfoData.Column ... selective);

    Integer getDeviceinfoDataTotalBySearch(DeviceinfoDataSearchModel searchModel);

    List<DeviceinfoData> getDeviceinfoDataListBySearch(DeviceinfoDataSearchModel searchModel);
}