package com.jqmkj.WaterResponseSystem.basemapper.mapper;

import com.jqmkj.WaterResponseSystem.basemapper.BaseMapper;
import com.jqmkj.WaterResponseSystem.entity.SysPermission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheNamespace
public interface PermissionMapper extends BaseMapper<SysPermission> {

    List<Long> findPermissionIdByRoleId(Long id);


}
