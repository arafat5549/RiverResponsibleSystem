package com.jqmkj.WaterResponseSystem.service;

import com.jqmkj.WaterResponseSystem.entity.SysRole;

public interface RoleService extends BaseService<SysRole>{

    void addRole_Permssion(Long roleId,Long[] permissionIds);
}
