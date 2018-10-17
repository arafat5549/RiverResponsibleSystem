package com.jqmkj.WaterResponseSystem.controller;

import com.jqmkj.WaterResponseSystem.entity.SysDictionaryData;
import com.jqmkj.WaterResponseSystem.entity.SysDictionaryType;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.pojo.EasyUIResult;
import com.jqmkj.WaterResponseSystem.pojo.MessageInfo;
import com.jqmkj.WaterResponseSystem.pojo.QueryInfo;
import com.jqmkj.WaterResponseSystem.service.DictionaryDataService;
import com.jqmkj.WaterResponseSystem.service.DictionaryTypeService;
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
public class DictionaryDataController {

    @Autowired
    private DictionaryDataService dictionaryDataService;
    @Autowired
    private DictionaryTypeService dictionaryTypeService;

    @PostMapping("/dictionaryDatas")
    public MessageInfo add(SysDictionaryData dictionaryData) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            dictionaryDataService.insertSelective(dictionaryData);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("字典数据添加异常");
        }
        return result;
    }

    @PutMapping(value = "/dictionaryDatas")
    public MessageInfo update(SysDictionaryData dictionaryData) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            dictionaryDataService.updateByPrimaryKeySelective(dictionaryData);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("字典数据更新异常");
        }
        return result;
    }

    @DeleteMapping(value = "/dictionaryDatas")
    public MessageInfo delete(Integer id) {
        MessageInfo result = MessageInfo.getInstance();
        SysUser operator =  ShiroUtil.getCurrentSysUser();
        try{
            //此处log记录
            dictionaryDataService.deleteByPrimaryKey(id);
        }catch (Exception e){
            result.setResult(false);
            result.setMsg("字典数据删除异常");
        }
        return result;
    }

    @GetMapping("/dictionaryDatas")
    public EasyUIResult queryDictionaryDataList(QueryInfo queryInfo, HttpServletRequest request) {
        List<SysDictionaryType> dictionaryTypes = dictionaryTypeService.selectAll();
        EasyUIResult result = dictionaryDataService.queryListPage(queryInfo, TkExampleUtil2.setExample(request,SysDictionaryData.class));
        List<SysDictionaryData> dictionaryDatas = (List<SysDictionaryData>) result.getRows();
        dictionaryDatas.forEach(d->{
            dictionaryTypes.forEach(t->{
                if(t.getId().equals(d.getDictionaryTypeId())) d.setDictionaryType(t);
            });
        });
        result.setRows(dictionaryDatas);
       return result;
    }

    @GetMapping("/dictionaryDatas/export")
    public void exportToExcel(HttpServletRequest request, HttpServletResponse response){
        List<SysDictionaryData> dictionaryDatas = dictionaryDataService.selectByExample(TkExampleUtil2.setExample(request, SysDictionaryData.class));
        ExcelUtils.getListToExcel(dictionaryDatas, "sheet", "字典数据明细表", request, response, SysDictionaryData.class);
    }

    @GetMapping("/dictionaryDatas/findAll")
   public List<SysDictionaryData> queryDictionaryDataList(){
       return dictionaryDataService.selectAll();
    }







}
