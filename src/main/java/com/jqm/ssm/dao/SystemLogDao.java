package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.SystemLog;
import com.jqm.ssm.entity.relate.SystemLogSearchModel;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SystemLogDao extends BaseMapper<SystemLog> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    SystemLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<SystemLog> list);

    int batchInsertSelective(java.util.List<SystemLog> list, SystemLog.Column ... selective);

    Integer getSystemLogTotalBySearch(SystemLogSearchModel searchModel);

    List<SystemLog> getSystemLogListBySearch(SystemLogSearchModel searchModel);
}