package com.jqmkj.WaterResponseSystem.util;

import com.jqmkj.WaterResponseSystem.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {
    public static SysUser getCurrentSysUser(){
        Subject subject = SecurityUtils.getSubject();
        return (SysUser)subject.getPrincipal();
    }
}
