package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.Module;

import java.util.List;

public interface ModuleDao extends BaseMapper<Module> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

    /**
     * 查询roleId具有权限的模块列表
     * @param roleId
     * @return
     */
    List<Module> getModuleListByRoleId(Integer roleId);

    /**
     * 查询userId具有权限的模块列表
     * @param userId
     * @return
     */
    List<Module> getModuleListByUserId(Integer userId);

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    Module selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<Module> list);

    int batchInsertSelective(java.util.List<Module> list, Module.Column ... selective);
}