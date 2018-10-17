package com.jqmkj.WaterResponseSystem.controller;

import com.jqmkj.WaterResponseSystem.entity.Monitorsite;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.pojo.MessageInfo;
import com.jqmkj.WaterResponseSystem.pojo.MonitorSiteTree;
import com.jqmkj.WaterResponseSystem.service.MonitorsiteService;
import com.jqmkj.WaterResponseSystem.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MonitorsiteController {

    @Autowired
    private MonitorsiteService monitorsiteService;

    @PostMapping(value = "/monitorsites")
    public MessageInfo add(Monitorsite monitorsite){
        MessageInfo result = MessageInfo.getInstance();
        try{
            monitorsiteService.insertSelective(monitorsite);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("监测站添加异常");
        }
        return result;
    }
    @PutMapping(value = "/monitorsites")
    public MessageInfo update(Monitorsite monitorsite) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            monitorsiteService.updateByPrimaryKeySelective(monitorsite);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("监测站更新异常");
        }
        return result;
    }

    @DeleteMapping(value = "/monitorsites")
    public MessageInfo delete(Integer id) {
        MessageInfo result = MessageInfo.getInstance();
        SysUser operator =  ShiroUtil.getCurrentSysUser();
        try{
            //此处log记录
            monitorsiteService.deleteByPrimaryKey(id);
        }catch (Exception e){
            result.setResult(false);
            result.setMsg("监测站删除异常");
        }
        return result;
    }

    @GetMapping("/monitorsites")
    public List<MonitorSiteTree> getAllTree() {//加载所有节点
        return monitorsiteService.getAllMonitorsiteTree();
    }



}
