package com.jqm.ssm.web;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.jqm.ssm.dao.DeviceinfodataPollutionDao;
import com.jqm.ssm.dto.BaseRequestBody;
import com.jqm.ssm.dto.BaseResult;
import com.jqm.ssm.dto.Option;
import com.jqm.ssm.entity.*;
import com.jqm.ssm.entity.Dictionary;
import com.jqm.ssm.entity.relate.DeviceinfoDataSearchModel;
import com.jqm.ssm.enums.ResultEnum;
import com.jqm.ssm.exception.BizException;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.IDeviceinfoDataService;
import com.jqm.ssm.service.IDictionaryService;
import com.jqm.ssm.service.SystemService;
import com.jqm.ssm.util.DateUtils;
import com.jqm.ssm.util.EChartUtil;
import com.jqm.ssm.util.SessionUtil;
import com.jqm.ssm.util.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by wangyaoyao.
 */
@Controller
@RequestMapping("/deviceinfoData")
public class DeviceinfoDataController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IDeviceinfoDataService deviceinfoDataService;
    @Autowired
    IDictionaryService dictionaryService;
    @Autowired
    SystemService systemService;
    @Autowired
    DeviceinfodataPollutionDao deviceinfodataPollutionDao;

    /**
     * 列表
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> list(Integer offset, Integer limit) {
        List<DeviceinfoData> list = null;
        try {

            offset = offset == null ? 0 : offset;
            limit = limit == null ? Constants.DEFALUT_LIMIT : limit;

            list = deviceinfoDataService.listPage(offset, limit,null);

        } catch (BizException e) {
            return new BaseResult<Object>(false, e.getMessage());
        } catch (Exception e) {
            if(Constants.Debug()) e.printStackTrace();
            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
        }
        return new BaseResult<Object>(true, list);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST,produces = {Constants.HEADER_CONTENT_TYPE_JSON})
    @ResponseBody
    public BaseResult<Object> save(@Valid @RequestBody BaseRequestBody<DeviceinfoData> body, BindingResult result, HttpServletRequest request) {
        User loginuser = SessionUtil.getSessionUser(request);
        DeviceinfoData bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = deviceinfoDataService.insertSelective(bean);
            systemService.log(loginuser,request.getRequestURI());
        } catch (BizException e) {
            return new BaseResult<Object>(false, e.getMessage());
        } catch (Exception e) {
            if(Constants.Debug()) e.printStackTrace();
            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
        }
        if(resultCode <= 0) return new BaseResult<Object>(false,  ResultEnum.DEPT_SAVE_ERROR.getMsg());
        return new BaseResult<Object>(true, bean);
    }
    /**
     * 删除
     * @param body
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST,produces = {Constants.HEADER_CONTENT_TYPE_JSON})
    @ResponseBody
    public BaseResult<Object> delete(@Valid @RequestBody BaseRequestBody<DeviceinfoData> body, BindingResult result, HttpServletRequest request) {
        DeviceinfoData bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = deviceinfoDataService.deleteByPrimaryKey(bean.getId());
            LOG.info("resultCode="+resultCode);
        } catch (BizException e) {
            return new BaseResult<Object>(false, e.getMessage());
        } catch (Exception e) {
            if(Constants.Debug()) e.printStackTrace();
            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
        }
        if(resultCode <= 0) return new BaseResult<Object>(false,  ResultEnum.DEPT_DELETE_ERROR.getMsg());
        return new BaseResult<Object>(true, bean);
    }
    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = {Constants.HEADER_CONTENT_TYPE_JSON})
    @ResponseBody
    public BaseResult<Object> update(@Valid @RequestBody BaseRequestBody<DeviceinfoData> body, BindingResult result, HttpServletRequest request){
        DeviceinfoData bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = deviceinfoDataService.updateByPrimaryKeySelective(bean);
        } catch (BizException e) {
            return new BaseResult<Object>(false, e.getMessage());
        } catch (Exception e) {
            if(Constants.Debug()) e.printStackTrace();
            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
        }
        if(resultCode <= 0) return new BaseResult<Object>(false,  ResultEnum.DEPT_UPDATE_ERROR.getMsg());
        return new BaseResult<Object>(true, bean);
    }
    /**
     * Treedata返回JSON结构
     */
    @RequestMapping(value = "/treedata", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> treedata(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String treedata = getDeviceinfoDataTree();
        return new BaseResult<Object>(true,null, treedata);
    }
    //###############################################################################################################//
    //                                             自定义controller的方法
    //###############################################################################################################//

    //TODO HERE

    @RequestMapping("/listView")
    public String main(String visitedModule, String visitedResource, HttpServletRequest request, ModelMap map) {

        //初始化用户、菜单
        User user = SessionUtil.getSessionUser(request);
        String menus = systemService.getModuleTree(user.getId(),visitedModule,visitedResource);
        map.put("user", user);
        map.put("menus", menus);
        //
        Map<Object,Object> dictmap = Maps.newHashMap();
        dictmap.put("group","echart_months");
        List<Dictionary>  months = dictionaryService.selectListByMap(dictmap);

        map.put("months", Lists.reverse(months));
        System.out.println(months);
        return "deviceinfodata/list.ftl";
    }

    @ResponseBody
    @RequestMapping("/getDeviceinfoDataDataTables")
    public String getBrandDataTables(DeviceinfoDataSearchModel searchModel, ModelMap map) {
        LOG.info(searchModel.getIDisplayStart()+","+searchModel.getIDisplayLength());
        return deviceinfoDataService.getDeviceinfoDataDataTables(searchModel);
    }

    @ResponseBody
    @RequestMapping("/getEchartDataOption")
    public String getEchartDataOption(@RequestParam("type")String type,@RequestParam("dateType")String dateType, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String data = Constants.EmptyJsonObject;
//        Map<Object,Object> map = Maps.newHashMap();
//        int offset = 0;
//        int limit  = 200;
//        if(searchModel !=null)
//        {
//            offset = searchModel.getIDisplayStart();
//            limit = searchModel.getIDisplayLength();
//        }
//        Page page = new Page(offset,limit );
//        map.put("page",page);
//        map.put("databaseId","mysql");
//        List<DeviceinfodataPollution> list = deviceinfodataPollutionDao.selectListByMap(map);
         if("month".equals(dateType)){
             data = getDate_month(type,true);
         }else if("year".equals(dateType)){

         }

        return  data;
    }

    private String getDate_month(String type,boolean order){
        String data = Constants.EmptyJsonObject;
        List<DeviceinfodataPollution> list = deviceinfodataPollutionDao.getData(Constants.ECHART_MONTHS);
        String title   = "月数据";
        String []axis = EChartUtil.AXIS_NAME_MONTH;
        Map<String,Object> datas = Maps.newHashMap();
        Object months1[] = new Object[12];
        Object months2[] = new Object[12];
        Object months3[] = new Object[12];
        Object months4[] = new Object[12];
        Object months5[] = new Object[12];
        for (DeviceinfodataPollution d:list){
            int month = d.getMonth()-1;
            if(month>=0 && month <12)
            {
                months1[month] = d.getSuspension();
                months2[month] = d.getPh();
                months3[month] = d.getMercury();
                months4[month] = d.getOrganicCarbon();
                months5[month] = d.getLead();
            }
        }
        datas.put("悬浮物",months1);
        datas.put("PH值",months2);
        datas.put("总汞",months3);
        datas.put("有机碳",months4);
        datas.put("总铅",months5);
        data= EChartUtil.generateEchartOption(type,title,"平均值",datas,axis,order);
       // System.out.println(data);
        return data;
    }

    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//
        /**
     * 获取treedata树型结构
     */
    public String getDeviceinfoDataTree(){
        return "{}";
    }
}
