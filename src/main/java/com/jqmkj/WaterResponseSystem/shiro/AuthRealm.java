package com.jqmkj.WaterResponseSystem.shiro;

import com.jqmkj.WaterResponseSystem.entity.SysPermission;
import com.jqmkj.WaterResponseSystem.entity.SysRole;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        SysUser user = userService.findByUsername(username);
        if(user==null){
            throw new UnknownAccountException();//没找到帐号
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SysUser user=(SysUser) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        //String username=(String) principal.getPrimaryPrincipal();
        List<String> permissions=new ArrayList<>();
        Set<SysRole> roles = user.getRoles();
        if(roles.size()>0) {
            for(SysRole role : roles) {
                Set<SysPermission> modules = role.getPermissions();
                if(modules.size()>0) {
                    for(SysPermission module : modules) {
                        permissions.add(module.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }

}
