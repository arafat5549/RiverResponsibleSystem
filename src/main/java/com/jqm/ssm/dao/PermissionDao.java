package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionDao extends BaseMapper<Permission> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    /**
     * 根据所属模块获取资源
     * @return
     */
    List<Permission> getPermissionListByModuleFlag(@Param("moduleFlags") List<String> moduleFlags, @Param("userId") Integer userId);

    /**
     * 根据资源id判断是否被角色使用
     * @param resourceId
     * @return
     */
    Boolean isUsedByRole(@Param("resourceId") Integer resourceId);

    List<Permission> getPermissionListByRoleId(Integer resourceId);
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<Permission> list);

    int batchInsertSelective(java.util.List<Permission> list, Permission.Column ... selective);
}