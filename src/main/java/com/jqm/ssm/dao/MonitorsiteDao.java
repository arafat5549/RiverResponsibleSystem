package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.Monitorsite;

public interface MonitorsiteDao extends BaseMapper<Monitorsite> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    Monitorsite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Monitorsite record);

    int updateByPrimaryKey(Monitorsite record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<Monitorsite> list);

    int batchInsertSelective(java.util.List<Monitorsite> list, Monitorsite.Column ... selective);
}