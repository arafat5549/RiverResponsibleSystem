package com.jqmkj.WaterResponseSystem.controller;

import com.jqmkj.WaterResponseSystem.entity.SysDepartment;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.pojo.MessageInfo;
import com.jqmkj.WaterResponseSystem.pojo.PageTree;
import com.jqmkj.WaterResponseSystem.service.DepartmentService;
import com.jqmkj.WaterResponseSystem.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value = "/departments")
    public MessageInfo add(SysDepartment department){
        MessageInfo result = MessageInfo.getInstance();
        try{
            departmentService.insertSelective(department);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("部门添加异常");
        }
        return result;
    }
    @PutMapping(value = "/departments")
    public MessageInfo update(SysDepartment department) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            departmentService.updateByPrimaryKeySelective(department);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("部门更新异常");
        }
        return result;
    }

    @DeleteMapping(value = "/departments")
    public MessageInfo delete(Integer id) {
        MessageInfo result = MessageInfo.getInstance();
        SysUser operator =  ShiroUtil.getCurrentSysUser();
        try{
            //此处log记录
            departmentService.deleteByPrimaryKey(id);
        }catch (Exception e){
            result.setResult(false);
            result.setMsg("部门删除异常");
        }
        return result;
    }

    @GetMapping("/departments")
    public List<PageTree> getAllTree() {//加载所有节点
        return departmentService.getAllTree(SysDepartment.class);
    }

    @GetMapping(value = "/departments/getAllChildrensByParent")
    public List<PageTree> getAllChildrensByParent(Integer parentId) {//获取某个根节点下所有子节点
        if (StringUtils.isEmpty(parentId))
            return departmentService.getRootNode(SysDepartment.class);
        return departmentService.getAllChildrens(SysDepartment.class, parentId);
    }



}
