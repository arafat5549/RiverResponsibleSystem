package com.jqmkj.WaterResponseSystem.controller;

import com.jqmkj.WaterResponseSystem.entity.SysPermission;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.pojo.MessageInfo;
import com.jqmkj.WaterResponseSystem.pojo.PageTree;
import com.jqmkj.WaterResponseSystem.service.PermissionService;
import com.jqmkj.WaterResponseSystem.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping(value = "/permissions")
    public MessageInfo add(SysPermission permission) {
        MessageInfo result = MessageInfo.getInstance();
        try {
            permissionService.insertSelective(permission);
        } catch (Exception e) {
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("资源添加异常");
        }
        return result;
    }

    @PutMapping(value = "/permissions")
    public MessageInfo update(SysPermission permission) {
        MessageInfo result = MessageInfo.getInstance();
        try {
            permissionService.updateByPrimaryKeySelective(permission);
        } catch (Exception e) {
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("资源更新异常");
        }
        return result;
    }

    @DeleteMapping(value = "/permissions")
    public MessageInfo delete(Integer id) {
        MessageInfo result = MessageInfo.getInstance();
        SysUser operator =  ShiroUtil.getCurrentSysUser();
        try {
            //此处log记录
            permissionService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            result.setResult(false);
            result.setMsg("资源删除异常");
        }
        return result;
    }

    @GetMapping("/permissions")
    public List<PageTree> getAllTree(Integer parentId) {//加载所有节点
        if (StringUtils.isEmpty(parentId))
            return permissionService.getAllTree(SysPermission.class);
        return permissionService.getAllChildrens(SysPermission.class, parentId);
    }

    @GetMapping(value = "/permissions/getAllChildrensByParent")
    public List<PageTree> getAllChildrensByParent(Integer parentId) {//获取某个根节点下所有子节点
        if (StringUtils.isEmpty(parentId))
            return permissionService.getRootNode(SysPermission.class);
        return permissionService.getAllChildrens(SysPermission.class, parentId);
    }

    @GetMapping(value = "/permissions/getAllMenus")
    public List<PageTree> getAllMenus(Integer parentId) {//获取某个根节点下所有子节点
        if (StringUtils.isEmpty(parentId))
            return permissionService.getRootNode(SysPermission.class).stream().filter(pageTree -> pageTree.getType().equals(0)).collect(Collectors.toList());
        return permissionService.getAllMenus(parentId);
    }

    @GetMapping(value = "/permissions/getAuthorizPermissions")
    public List<PageTree> getAuthorizPermissions(Long parentId) {
        Subject subject = SecurityUtils.getSubject();
        if (StringUtils.isEmpty(parentId))
            return permissionService.getRootNode(SysPermission.class)
                    .stream().filter(p -> subject.isPermitted(p.getText())).collect(Collectors.toList());
        ;
        return permissionService.getAllAuthorizChildrens(parentId, subject);
    }


}
