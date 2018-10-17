package com.jqmkj.WaterResponseSystem.basemapper.mapper;

import com.jqmkj.WaterResponseSystem.basemapper.BaseMapper;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary   //@Primary 为解决mapper冲突问题，设置有限调取
@CacheNamespace
public interface UserMapper extends BaseMapper<SysUser> {

    /**
     * 一般只需多表操作
     */

    SysUser findByUsername(String username);

    void addUser_Roles(@Param("userId") Long userId,@Param("roleId") Long roleId);

    Long[] getRoleIdsByUserId(Long userId);

    void deleteUser_RoleByUserId(Long userId);
}
