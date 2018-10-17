package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.service.UserService;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class UserServiceImpl extends BaseServiceImpl<SysUser> implements UserService {

    @Autowired
    private UserMapper mapper;

    /**
     * 一般只需多表操作代码
     */

    @Override
    public SysUser findByUsername(String username) {
        return mapper.findByUsername(username);
    }

    @Override
    public Long[] getRoleIdsByUserId(Long userId){
        return mapper.getRoleIdsByUserId(userId);
    }

    @Override
    public void addUser_Role(Long userId,Long[] roleIds){
        mapper.deleteUser_RoleByUserId(userId);
        for(Long roleId : roleIds){
            mapper.addUser_Roles(userId,roleId);
        }
    }
}
