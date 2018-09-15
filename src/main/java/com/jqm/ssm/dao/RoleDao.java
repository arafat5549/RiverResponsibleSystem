package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.Role;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.RoleSearchModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RoleDao extends BaseMapper<Role> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

    /**
     * 根据id删除角色模块关联关系
     * @param roleId
     * @return
     */
    Integer deleteRoleModuleById(Integer roleId);

    /**
     * 根据id删除角色资源关联关系
     * @param roleId
     * @return
     */
    Integer deleteRoleResourceById(Integer roleId);
    /**
     * 根据条件查询角色列表总数
     * @param searchModel
     * @return
     */
    Integer getRoleTotalBySearch(RoleSearchModel searchModel);

    /**
     * 根据条件查询用户List
     * @param searchModel
     * @return
     */
    List<Role> getRoleListBySearch(RoleSearchModel searchModel);

    /**
     * 角色是否被用户使用
     * @param roleId
     * @return
     */
    Boolean isUsedByUser(Integer roleId);

    /**
     * 分配角色关联模块
     * @param moduleIds
     * @param roleId
     */
    void assignModules(@Param("moduleIds") List<Integer> moduleIds, @Param("roleId") Integer roleId);


    /**
     * 分配角色关联资源
     * @param resourceIds
     * @param roleId
     */
    void assignResources(@Param("resourceIds") List<Integer> resourceIds, @Param("roleId") Integer roleId);
    /**
     * 根据选择的字符串(,分割)，对角色赋予模块和资源
     * @param roleId
     * @param checkedStr
     */
    String assignModuleAndResource(Integer roleId, String checkedStr);

    /**
     * 根据userId获取关联的权限列表
     * @param userId
     * @return
     */
    List<Role> getRoleListByUserId(Integer userId);
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<Role> list);

    int batchInsertSelective(java.util.List<Role> list, Role.Column ... selective);
}