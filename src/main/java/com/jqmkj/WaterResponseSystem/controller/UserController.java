package com.jqmkj.WaterResponseSystem.controller;

import com.jqmkj.WaterResponseSystem.entity.SysDepartment;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.pojo.EasyUIResult;
import com.jqmkj.WaterResponseSystem.pojo.MessageInfo;
import com.jqmkj.WaterResponseSystem.pojo.QueryInfo;
import com.jqmkj.WaterResponseSystem.service.DepartmentService;
import com.jqmkj.WaterResponseSystem.service.UserService;
import com.jqmkj.WaterResponseSystem.util.ExcelUtils;
import com.jqmkj.WaterResponseSystem.util.ShiroUtil;
import com.jqmkj.WaterResponseSystem.util.TkExampleUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/users")
    public MessageInfo add(SysUser user) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            userService.insertSelective(user);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("账户添加异常");
        }
        return result;
    }

    @PutMapping(value = "/users")
    public MessageInfo update(SysUser user) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            userService.updateByPrimaryKeySelective(user);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("账户更新异常");
        }
        return result;
    }

    @DeleteMapping(value = "/users")
    public MessageInfo delete(Integer id) {
        MessageInfo result = MessageInfo.getInstance();
        SysUser operator =  ShiroUtil.getCurrentSysUser();
        try{
            //此处log记录
            userService.deleteByPrimaryKey(id);
        }catch (Exception e){
            result.setResult(false);
            result.setMsg("资源删除异常");
        }
        return result;
    }

    @GetMapping("/users")
    public EasyUIResult querySysUserList(QueryInfo queryInfo, HttpServletRequest request) {
        List<SysDepartment> departments = departmentService.selectAll();
        EasyUIResult result = userService.queryListPage(queryInfo, TkExampleUtil2.setExample(request,SysUser.class));
        List<SysUser> users = (List<SysUser>) result.getRows();
        departments.forEach(d->{
            users.forEach(u->{
                if (d.getId().equals(u.getDepartmentId())) u.setDepartmentName(d.getName());
            });
        });
        result.setRows(users);
        return result;
    }

    @GetMapping("/users/export")
    public void exportToExcel(HttpServletRequest request, HttpServletResponse response){
            List<SysUser> users = userService.selectByExample(TkExampleUtil2.setExample(request, SysUser.class));
            ExcelUtils.getListToExcel(users, "sheet", "用户明细表", request, response, SysUser.class);
    }

    @PostMapping("users/user_roles")
    public MessageInfo addUser_Role(Long userId,Long[] roleIds){
        MessageInfo result = MessageInfo.getInstance();
        try {
            userService.addUser_Role(userId, roleIds);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("角色授予失败");
        }
        return result;
    }

    @GetMapping("/users/{userId}/roleIds")
    public Long[] getRoleIdsByUserId(@PathVariable Long userId){
        return userService.getRoleIdsByUserId(userId);
    }

    @GetMapping("/users/{id}")
    public SysUser get(@PathVariable Long id){
            return userService.selectByPrimaryKey(id);
    }




}
