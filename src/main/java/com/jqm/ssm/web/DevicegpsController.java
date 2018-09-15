package com.jqm.ssm.web;

import com.google.common.collect.Maps;
import com.jqm.ssm.dto.BaseRequestBody;
import com.jqm.ssm.dto.BaseResult;
import com.jqm.ssm.entity.Devicegps;
import com.jqm.ssm.entity.Deviceinfo;
import com.jqm.ssm.entity.Monitorsite;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.enums.ResultEnum;
import com.jqm.ssm.exception.BizException;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.IDevicegpsService;
import com.jqm.ssm.service.IDeviceinfoService;
import com.jqm.ssm.service.IMonitorsiteService;
import com.jqm.ssm.service.SystemService;
import com.jqm.ssm.util.ConfigUtil;
import com.jqm.ssm.util.EChartUtil;
import com.jqm.ssm.util.JsonUtil;
import com.jqm.ssm.util.SessionUtil;
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
@RequestMapping("/devicegps")
public class DevicegpsController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IDevicegpsService devicegpsService;
    @Autowired
    SystemService systemService;
    @Autowired
    ConfigUtil configUtil;

    /**
     * 列表
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> list(Integer offset, Integer limit) {
        List<Devicegps> list = null;
        try {

            offset = offset == null ? 0 : offset;
            limit = limit == null ? Constants.DEFALUT_LIMIT : limit;

            list = devicegpsService.listPage(offset, limit,null);

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
    public BaseResult<Object> save(@Valid @RequestBody BaseRequestBody<Devicegps> body, BindingResult result, HttpServletRequest request) {
        User loginuser = SessionUtil.getSessionUser(request);
        Devicegps bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = devicegpsService.insertSelective(bean);
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
    public BaseResult<Object> delete(@Valid @RequestBody BaseRequestBody<Devicegps> body, BindingResult result, HttpServletRequest request) {
        Devicegps bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = devicegpsService.deleteByPrimaryKey(bean.getId());
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
    public BaseResult<Object> update(@Valid @RequestBody BaseRequestBody<Devicegps> body, BindingResult result, HttpServletRequest request){
        Devicegps bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = devicegpsService.updateByPrimaryKeySelective(bean);
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
        String treedata = getDevicegpsTree();
        return new BaseResult<Object>(true,null, treedata);
    }
    //###############################################################################################################//
    //                                             自定义controller的方法
    //###############################################################################################################//

    @Autowired
    IMonitorsiteService monitorsiteService;
    @Autowired
    IDeviceinfoService deviceinfoService;

    //TODO HERE
    @RequestMapping(value="/listView", method = RequestMethod.GET)
    public String main(String visitedModule, String visitedResource, HttpServletRequest request, ModelMap map) {
        LOG.info("visitedModule="+visitedModule+",visitedResource="+visitedResource);
        //初始化用户、菜单
        User user = SessionUtil.getSessionUser(request);
        String menus = systemService.getModuleTree(user.getId(),visitedModule,visitedResource);
        List<Deviceinfo> deviceinfos = deviceinfoService.selectListByMap(null);
        List<Monitorsite> monitorsites = monitorsiteService.getMonitorsiteListForOption();
        map.put("user", user);
        map.put("menus", menus);
        map.put("deviceinfos", deviceinfos);
        map.put("monitorsites", monitorsites);
        return "devicegps/list.ftl";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(@ModelAttribute("devicegps")Devicegps devicegps, HttpServletRequest request) {
        LOG.info("add="+devicegps.toString());
        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);
        //生成节点
        devicegpsService.createDevicegps(devicegps,user);
        return JsonUtil.convertObj2json(devicegps).toString();
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String  delete(@RequestParam("id") Integer id){
        LOG.info("delete="+id);
        devicegpsService.deleteByPrimaryKey(id);
        return Constants.Success;
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute("department")  Devicegps devicegps,HttpServletRequest request) {
        LOG.info("update="+devicegps.toString());
        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);
        //生成节点积累
        devicegpsService.updateDevicegps(devicegps,user);
        return JsonUtil.convertObj2json(devicegps).toString();
    }
    //
    @ResponseBody
    @RequestMapping("/getEcharMapData")
    public String getEcharMapData(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String data = Constants.EmptyJsonObject;

        List<Devicegps> list =  devicegpsService.getDeviceinfoList();
        data = EChartUtil.generateMapData(list,configUtil.getBasePath());
        //System.out.println(data);
        return data;
    }


    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//


    @ResponseBody
    @RequestMapping(value = "/get")
    public String get(Integer id) {
        Devicegps devicegps = null;
        if(id==null || id <= 0)
            devicegps = null;
        else
            devicegps = devicegpsService.selectByPrimaryKey(id);
        String resp = JsonUtil.objectToJson(devicegps).toString();
        LOG.info("get="+devicegps);
        return resp;
    }

    @RequestMapping(value = "/getDevicegpsTree")
    @ResponseBody
    public String getDevicegpsTree(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String treedata = devicegpsService.getDevicegpsTree();
        return treedata;
    }

        /**
     * 获取treedata树型结构
     */
    public String getDevicegpsTree(){
        return "{}";
    }
}
