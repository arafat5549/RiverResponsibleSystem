package com.jqmkj.WaterResponseSystem.basemapper.mapper;

import com.jqmkj.WaterResponseSystem.basemapper.BaseMapper;
import com.jqmkj.WaterResponseSystem.entity.SysRole;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheNamespace
public interface RoleMapper extends BaseMapper<SysRole> {

    void addRole_Permssion(@Param("roleId") Long roleId , @Param("permissionId") Long  permissionId);

    List<Long> getpermissionIdByroleId(Long roleId);

    void deleteRole_PermissionByRoleId(Long roleId);
}
