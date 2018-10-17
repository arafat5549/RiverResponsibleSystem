package com.jqmkj.WaterResponseSystem.controller;

import com.jqmkj.WaterResponseSystem.entity.Category;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.pojo.MessageInfo;
import com.jqmkj.WaterResponseSystem.pojo.PageTree;
import com.jqmkj.WaterResponseSystem.service.CategoryService;
import com.jqmkj.WaterResponseSystem.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/categorys")
    public MessageInfo add(Category category){
        MessageInfo result = MessageInfo.getInstance();
        try{
            categoryService.insertSelective(category);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("部门添加异常");
        }
        return result;
    }
    @PutMapping(value = "/categorys")
    public MessageInfo update(Category category) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            categoryService.updateByPrimaryKeySelective(category);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("部门更新异常");
        }
        return result;
    }

    @DeleteMapping(value = "/categorys")
    public MessageInfo delete(Integer id) {
        MessageInfo result = MessageInfo.getInstance();
        SysUser operator =  ShiroUtil.getCurrentSysUser();
        try{
            //此处log记录
            categoryService.deleteByPrimaryKey(id);
        }catch (Exception e){
            result.setResult(false);
            result.setMsg("部门删除异常");
        }
        return result;
    }

    @GetMapping("/categorys")
    public List<PageTree> getAllTree() {//加载所有节点
        return categoryService.getAllTree(Category.class);
    }

    @GetMapping(value = "/categorys/getAllChildrensByParent")
    public List<PageTree> getAllChildrensByParent(Integer parentId) {//获取某个根节点下所有子节点
        if (StringUtils.isEmpty(parentId))
            return categoryService.getRootNode(Category.class);
        return categoryService.getAllChildrens(Category.class, parentId);
    }



}
