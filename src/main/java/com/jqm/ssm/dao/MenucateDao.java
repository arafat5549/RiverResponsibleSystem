package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.Menucate;

public interface MenucateDao extends BaseMapper<Menucate> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    Menucate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menucate record);

    int updateByPrimaryKey(Menucate record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<Menucate> list);

    int batchInsertSelective(java.util.List<Menucate> list, Menucate.Column ... selective);
}