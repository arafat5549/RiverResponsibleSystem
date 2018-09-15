package com.jqm.ssm.web;

import com.google.common.collect.Maps;
import com.jqm.ssm.dto.BaseRequestBody;
import com.jqm.ssm.dto.BaseResult;
import com.jqm.ssm.entity.Deviceinfo;
import com.jqm.ssm.entity.DeviceinfoLog;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.DeviceinfoDataSearchModel;
import com.jqm.ssm.entity.relate.DeviceinfoLogSearchModel;
import com.jqm.ssm.enums.ResultEnum;
import com.jqm.ssm.exception.BizException;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.IDeviceinfoLogService;
import com.jqm.ssm.service.IDeviceinfoService;
import com.jqm.ssm.service.SystemService;
import com.jqm.ssm.util.ExcelUtils;
import com.jqm.ssm.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by wangyaoyao.
 */
@Controller
@RequestMapping("/deviceinfoLog")
public class DeviceinfoLogController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IDeviceinfoLogService deviceinfoLogService;
    @Autowired
    SystemService systemService;

    /**
     * 列表
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> list(Integer offset, Integer limit) {
        List<DeviceinfoLog> list = null;
        try {

            offset = offset == null ? 0 : offset;
            limit = limit == null ? Constants.DEFALUT_LIMIT : limit;

            list = deviceinfoLogService.listPage(offset, limit,null);

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
    public BaseResult<Object> save(@Valid @RequestBody BaseRequestBody<DeviceinfoLog> body, BindingResult result, HttpServletRequest request) {
        User loginuser = SessionUtil.getSessionUser(request);
        DeviceinfoLog bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = deviceinfoLogService.insertSelective(bean);
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
    public BaseResult<Object> delete(@Valid @RequestBody BaseRequestBody<DeviceinfoLog> body, BindingResult result, HttpServletRequest request) {
        DeviceinfoLog bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = deviceinfoLogService.deleteByPrimaryKey(bean.getId());
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
    public BaseResult<Object> update(@Valid @RequestBody BaseRequestBody<DeviceinfoLog> body, BindingResult result, HttpServletRequest request){
        DeviceinfoLog bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = deviceinfoLogService.updateByPrimaryKeySelective(bean);
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
        String treedata = getDeviceinfoLogTree();
        return new BaseResult<Object>(true,null, treedata);
    }
    //###############################################################################################################//
    //                                             自定义controller的方法
    //###############################################################################################################//

    //TODO HERE

    @Autowired
    IDeviceinfoService deviceinfoService;

    @RequestMapping("/listView")
    public String main(String visitedModule, String visitedResource, HttpServletRequest request, ModelMap map) {

        //初始化用户、菜单
        User user = SessionUtil.getSessionUser(request);
        String menus = systemService.getModuleTree(user.getId(),visitedModule,visitedResource);
        List<Deviceinfo> deviceinfos = deviceinfoService.selectListByMap(null);
        map.put("deviceinfos", deviceinfos);
        map.put("user", user);
        map.put("menus", menus);
        return "deviceinfolog/list.ftl";
    }

    @ResponseBody
    @RequestMapping("/getDeviceinfoLogDataTables")
    public String getDeviceinfoLogDataTables(DeviceinfoLogSearchModel searchModel, ModelMap map) {
        LOG.info(searchModel.getIDisplayStart()+","+searchModel.getIDisplayLength());
        return deviceinfoLogService.getDeviceinfoLogDataTables(searchModel);
    }

    @ResponseBody
    @RequestMapping("/export")
    public void exportToExcel(DeviceinfoLogSearchModel searchModel,HttpServletRequest request,HttpServletResponse response){
        List<DeviceinfoLog> deviceinfoLogs = deviceinfoLogService.getDeviceinfoLogListBySearch(searchModel);
        ExcelUtils.getListToExcel(deviceinfoLogs,"sheet","设备日志表",request,response,DeviceinfoLog.class);
    }

    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//
        /**
     * 获取treedata树型结构
     */
    public String getDeviceinfoLogTree(){
        return "{}";
    }
}
