package com.jqmkj.WaterResponseSystem.controller;

import com.jqmkj.WaterResponseSystem.entity.Brand;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.pojo.EasyUIResult;
import com.jqmkj.WaterResponseSystem.pojo.MessageInfo;
import com.jqmkj.WaterResponseSystem.pojo.QueryInfo;
import com.jqmkj.WaterResponseSystem.service.BrandService;
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
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/brands")
    public MessageInfo add(Brand brand) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            brandService.insertSelective(brand);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("品牌添加异常");
        }
        return result;
    }

    @PutMapping(value = "/brands")
    public MessageInfo update(Brand brand) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            brandService.updateByPrimaryKeySelective(brand);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("品牌更新异常");
        }
        return result;
    }

    @DeleteMapping(value = "/brands")
    public MessageInfo delete(Integer id) {
        MessageInfo result = MessageInfo.getInstance();
        SysUser operator =  ShiroUtil.getCurrentSysUser();
        try{
            //此处log记录
            brandService.deleteByPrimaryKey(id);
        }catch (Exception e){
            result.setResult(false);
            result.setMsg("品牌删除异常");
        }
        return result;
    }

    @GetMapping("/brands")
    public EasyUIResult queryBrandList(QueryInfo queryInfo, HttpServletRequest request) {
       return brandService.queryListPage(queryInfo, TkExampleUtil2.setExample(request,Brand.class));
    }

    @GetMapping("/brands/export")
    public void exportToExcel(HttpServletRequest request, HttpServletResponse response){
        List<Brand> brands = brandService.selectByExample(TkExampleUtil2.setExample(request, Brand.class));
        ExcelUtils.getListToExcel(brands, "sheet", "品牌明细表", request, response, Brand.class);
    }

    @GetMapping("/brands/findAll")
   public List<Brand> queryBrandList(){
       return brandService.selectAll();
    }







}
