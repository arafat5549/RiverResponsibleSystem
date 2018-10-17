package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.basemapper.mapper.RoleMapper;
import com.jqmkj.WaterResponseSystem.entity.SysRole;
import com.jqmkj.WaterResponseSystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class RoleServiceImpl extends BaseServiceImpl<SysRole> implements RoleService {

    @Autowired
    private RoleMapper mapper;


    /**
     * 一般只需多表操作代码
     */

    @Override
    public void addRole_Permssion(Long roleId, Long[] permissionIds) {
                mapper.deleteRole_PermissionByRoleId(roleId);
            for(Long permissionId : permissionIds) {
                mapper.addRole_Permssion(roleId, permissionId);
            }
    }


}
