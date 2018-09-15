package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.Deviceinfo;
import com.jqm.ssm.entity.relate.DeviceinfoSearchModel;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DeviceinfoDao extends BaseMapper<Deviceinfo> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

    Integer getDeviceinfoTotalBySearch(DeviceinfoSearchModel searchModel);

    List<Deviceinfo> getDeviceinfoListBySearch(DeviceinfoSearchModel searchModel);
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    Deviceinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deviceinfo record);

    int updateByPrimaryKey(Deviceinfo record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<Deviceinfo> list);

    int batchInsertSelective(java.util.List<Deviceinfo> list, Deviceinfo.Column ... selective);


}