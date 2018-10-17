package com.jqmkj.WaterResponseSystem.service;

import com.jqmkj.WaterResponseSystem.entity.SysPermission;
import com.jqmkj.WaterResponseSystem.pojo.PageTree;
import org.apache.shiro.subject.Subject;

import java.util.List;

public interface PermissionService extends BaseService<SysPermission>{

    List<PageTree> getAllTreeCheckByRole(Long id);

    List<PageTree> getAuthorizPermission();

    List<PageTree> getAllAuthorizChildrens(Object parentId,Subject subject);

    List<PageTree> getAllMenus(Object parentId);
}
