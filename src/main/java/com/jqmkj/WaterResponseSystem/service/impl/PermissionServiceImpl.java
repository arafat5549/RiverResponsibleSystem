package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.entity.SysPermission;
import com.jqmkj.WaterResponseSystem.pojo.PageTree;
import com.jqmkj.WaterResponseSystem.service.PermissionService;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.PermissionMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<SysPermission> implements PermissionService {

    @Autowired
    private PermissionMapper mapper;


    /**
     * 一般只需多表操作代码
     */

    @Override
    public List<PageTree> getAllTreeCheckByRole(Long id) {
        List<Long> permissionIds = mapper.findPermissionIdByRoleId(id);
        List<PageTree> pageTrees = mapper.getAllTree(SysPermission.class);
        setAndCheckedTree(pageTrees, permissionIds);
        return pageTrees;
    }

    public void setAndCheckedTree(List<PageTree> pageTrees, List<Long> permissionIds) {
        for (PageTree pageTree : pageTrees) {
            for (Long id : permissionIds) {
                if (pageTree.getId().equals(id)) {
                    pageTree.setChecked(true);
                }
            }
            List<PageTree> childrens = mapper.getByParentId(SysPermission.class, pageTree.getId());
            pageTree.setChildren(childrens);
            setAndCheckedTree(childrens, permissionIds);
        }
    }

    @Override
    public List<PageTree> getAuthorizPermission() {//一次性获取整课已使授权树
        Subject subject = SecurityUtils.getSubject();
        List<PageTree> pageTrees = mapper.getAllTree(SysPermission.class)
                .stream().filter(p->subject.isPermitted(p.getText())).collect(Collectors.toList());
        setAuthorizTree(pageTrees,subject);
        return pageTrees;
    }

    public void setAuthorizTree(List<PageTree> pageTrees,Subject subject){
        for(PageTree pageTree : pageTrees){
            List<PageTree> childrens = mapper.getByParentId(SysPermission.class, pageTree.getId())
                    .stream().filter(p->subject.isPermitted(p.getText())).collect(Collectors.toList());
            pageTree.setChildren(childrens);
            setAuthorizTree(childrens,subject);
        }
    }

    @Override
    public List<PageTree> getAllAuthorizChildrens(Object parentId,Subject subject) {
        List<PageTree> pageTrees = mapper.getByParentId(SysPermission.class, parentId)
                   .stream().filter(p->subject.isPermitted(p.getText())).collect(Collectors.toList());
        setAuthorizTree(pageTrees,subject);
        return pageTrees;
    }

    @Override
    public List<PageTree> getAllMenus(Object parentId) {
        Subject subject = SecurityUtils.getSubject();
        List<PageTree> pageTrees = mapper.getByParentId(SysPermission.class, parentId)
                .stream().filter(p->"0".equals(p.getType()+"")).filter(p->subject.isPermitted(p.getText())).collect(Collectors.toList());
        setMenus(pageTrees,subject);
        return pageTrees;
    }

    public void setMenus(List<PageTree> pageTrees,Subject subject){
        for(PageTree pageTree : pageTrees){
            List<PageTree> childrens = mapper.getByParentId(SysPermission.class, pageTree.getId())
                    .stream().filter(p->"0".equals(p.getType()+"")).filter(p->subject.isPermitted(p.getText())).collect(Collectors.toList());
            pageTree.setChildren(childrens);
            setMenus(childrens,subject);
        }
    }


}