package com.jqmkj.WaterResponseSystem.controller;

import com.jqmkj.WaterResponseSystem.entity.SysDictionaryData;
import com.jqmkj.WaterResponseSystem.entity.SysDictionaryType;
import com.jqmkj.WaterResponseSystem.entity.SysUser;
import com.jqmkj.WaterResponseSystem.pojo.EasyUIResult;
import com.jqmkj.WaterResponseSystem.pojo.MessageInfo;
import com.jqmkj.WaterResponseSystem.pojo.QueryInfo;
import com.jqmkj.WaterResponseSystem.service.DictionaryDataService;
import com.jqmkj.WaterResponseSystem.service.DictionaryTypeService;
import com.jqmkj.WaterResponseSystem.util.ShiroUtil;
import com.jqmkj.WaterResponseSystem.util.TkExampleUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DictionaryTypeController {

    @Autowired
    private DictionaryTypeService dictionaryTypeService;
    @Autowired
    private DictionaryDataService dictionaryDataService;

    @PostMapping("/dictionaryTypes")
    public MessageInfo add(SysDictionaryType dictionaryType) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            dictionaryTypeService.insertSelective(dictionaryType);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("数据字典类型添加异常");
        }
        return result;
    }

    @PutMapping(value = "/dictionaryTypes")
    public MessageInfo update(SysDictionaryType dictionaryType) {
        MessageInfo result = MessageInfo.getInstance();
        try{
            dictionaryTypeService.updateByPrimaryKeySelective(dictionaryType);
        }catch (Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("数据字典类型更新异常");
        }
        return result;
    }

    @DeleteMapping(value = "/dictionaryTypes")
    public MessageInfo delete(Long id) {
        MessageInfo result = MessageInfo.getInstance();
        SysUser operator =  ShiroUtil.getCurrentSysUser();
        try{
            //此处log记录
            Example example = new Example(SysDictionaryData.class);
            example.createCriteria().andEqualTo("dictionaryTypeId",id);
            int count = dictionaryDataService.selectCountByExample(example);
            if(count>0){
                result.setResult(false);
                result.setMsg("无法删除类型");
            }else {
                dictionaryTypeService.deleteByPrimaryKey(id);
            }
        }catch (Exception e){
            result.setResult(false);
            result.setMsg("数据字典类型删除异常");
        }
        return result;
    }

    @GetMapping("/dictionaryTypes")
    public EasyUIResult queryDictionaryTypeList(QueryInfo queryInfo, HttpServletRequest request) {
       return dictionaryTypeService.queryListPage(queryInfo, TkExampleUtil2.setExample(request,SysDictionaryType.class));
    }
    

    @GetMapping("/dictionaryTypes/findAll")
   public List<SysDictionaryType> queryDictionaryTypeList(){
       return dictionaryTypeService.selectAll();
    }

}
