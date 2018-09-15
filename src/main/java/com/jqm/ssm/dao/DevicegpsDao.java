package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.Devicegps;

import java.util.List;

public interface DevicegpsDao extends BaseMapper<Devicegps> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

    List<Devicegps> getDeviceinfoListByCid(Integer categoryId);
    List<Devicegps> getDeviceinfoList();
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    Devicegps selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Devicegps record);

    int updateByPrimaryKey(Devicegps record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<Devicegps> list);

    int batchInsertSelective(java.util.List<Devicegps> list, Devicegps.Column ... selective);
}