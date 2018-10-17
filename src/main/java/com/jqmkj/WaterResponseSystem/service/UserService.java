package com.jqmkj.WaterResponseSystem.service;

import com.jqmkj.WaterResponseSystem.entity.SysUser;

public interface UserService extends BaseService<SysUser>{

    SysUser findByUsername(String username);

    Long[] getRoleIdsByUserId(Long userId);

    void addUser_Role(Long userId, Long[] roleIds);
}
