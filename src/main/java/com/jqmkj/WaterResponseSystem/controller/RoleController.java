package com.jqmkj.WaterResponseSystem.controller;

import com.jqmkj.WaterResponseSystem.entity.SysRole;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.pojo.EasyUIResult;
import com.jqmkj.WaterResponseSystem.pojo.MessageInfo;
import com.jqmkj.WaterResponseSystem.pojo.PageTree;
import com.jqmkj.WaterResponseSystem.pojo.QueryInfo;
import com.jqmkj.WaterResponseSystem.service.PermissionService;
import com.jqmkj.WaterResponseSystem.service.RoleService;
import com.jqmkj.WaterResponseSystem.util.ShiroUtil;
import com.jqmkj.WaterResponseSystem.util.TkExampleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/roles")
    public MessageInfo add(SysRole role) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            roleService.insertSelective(role);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("角色添加异常");
        }
        return result;
    }

    @PutMapping(value = "/roles")
    public MessageInfo update(SysRole role) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            roleService.updateByPrimaryKeySelective(role);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("角色更新异常");
        }
        return result;
    }

    @DeleteMapping(value = "/roles")
    public MessageInfo delete(Integer id) {
        MessageInfo result = MessageInfo.getInstance();
        SysUser operator =  ShiroUtil.getCurrentSysUser();
        try{
            //此处log记录
            roleService.deleteByPrimaryKey(id);
        }catch (Exception e){
            result.setResult(false);
            result.setMsg("角色删除异常");
        }
        return result;
    }

    @GetMapping("/roles")
    public EasyUIResult querySysRoleList(QueryInfo queryInfo, HttpServletRequest request) {
       return roleService.queryListPage(queryInfo, TkExampleUtil.setExample(request,SysRole.class));
    }
    @GetMapping("/roles/findAll")
   public List<SysRole> querySysRoleList(){
       return roleService.selectAll();
    }

    @GetMapping("/roles/{id}/permissions")
    public List<PageTree> getPermissionsById(@PathVariable Long id){
        return  permissionService.getAllTreeCheckByRole(id);
    }


    @PostMapping("/roles/role_permission")
    public MessageInfo addRole_Permission(Long roleId,Long[] permissionIds){
        MessageInfo result = MessageInfo.getInstance();
        try {
            roleService.addRole_Permssion(roleId,permissionIds);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("授权失败");
        }
        return result;
    }



}
