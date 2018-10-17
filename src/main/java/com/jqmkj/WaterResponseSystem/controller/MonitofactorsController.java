package com.jqmkj.WaterResponseSystem.controller;

import com.jqmkj.WaterResponseSystem.entity.Monitofactors;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.pojo.EasyUIResult;
import com.jqmkj.WaterResponseSystem.pojo.MessageInfo;
import com.jqmkj.WaterResponseSystem.pojo.QueryInfo;
import com.jqmkj.WaterResponseSystem.service.MonitofactorsService;
import com.jqmkj.WaterResponseSystem.util.ExcelUtils;
import com.jqmkj.WaterResponseSystem.util.ShiroUtil;
import com.jqmkj.WaterResponseSystem.util.TkExampleUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MonitofactorsController {

    @Autowired
    private MonitofactorsService monitofactorsService;

    @PostMapping("/monitofactorss")
    public MessageInfo add(Monitofactors monitofactors) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            monitofactorsService.insertSelective(monitofactors);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("监测因子添加异常");
        }
        return result;
    }

    @PutMapping(value = "/monitofactorss")
    public MessageInfo update(Monitofactors monitofactors) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            monitofactorsService.updateByPrimaryKeySelective(monitofactors);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("监测因子更新异常");
        }
        return result;
    }

    @DeleteMapping(value = "/monitofactorss")
    public MessageInfo delete(Integer id) {
        MessageInfo result = MessageInfo.getInstance();
        SysUser operator =  ShiroUtil.getCurrentSysUser();
        try{
            //此处log记录
            monitofactorsService.deleteByPrimaryKey(id);
        }catch (Exception e){
            result.setResult(false);
            result.setMsg("监测因子删除异常");
        }
        return result;
    }

    @GetMapping("/monitofactorss")
    public EasyUIResult queryMonitofactorsList(QueryInfo queryInfo, HttpServletRequest request) {
       return monitofactorsService.queryListPage(queryInfo, TkExampleUtil2.setExample(request,Monitofactors.class));
    }

    @GetMapping("/monitofactorss/export")
    public void exportToExcel(HttpServletRequest request, HttpServletResponse response){
        List<Monitofactors> monitofactorss = monitofactorsService.selectByExample(TkExampleUtil2.setExample(request, Monitofactors.class));
        ExcelUtils.getListToExcel(monitofactorss, "sheet", "监测因子明细表", request, response, Monitofactors.class);
    }

    @GetMapping("/monitofactorss/findAll")
   public List<Monitofactors> queryMonitofactorsList(){
       return monitofactorsService.selectAll();
    }







}
