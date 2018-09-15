package com.jqm.ssm.web;

import com.google.common.collect.Maps;
import com.jqm.ssm.dto.BaseRequestBody;
import com.jqm.ssm.dto.BaseResult;
import com.jqm.ssm.entity.Module;
import com.jqm.ssm.entity.Permission;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.enums.ResultEnum;
import com.jqm.ssm.exception.BizException;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.IModuleService;
import com.jqm.ssm.service.IPermissionService;
import com.jqm.ssm.service.SystemService;
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
@RequestMapping(value ={"/permission","/resource"})
public class PermissionController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IPermissionService permissionService;
    @Autowired
    IModuleService moduleService;
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
        List<Permission> list = null;
        try {

            offset = offset == null ? 0 : offset;
            limit = limit == null ? Constants.DEFALUT_LIMIT : limit;

            list = permissionService.listPage(offset, limit,null);

        } catch (BizException e) {
            return new BaseResult<Object>(false, e.getMessage());
        } catch (Exception e) {
            if(Constants.Debug()) e.printStackTrace();
            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
        }
        return new BaseResult<Object>(true, list);
    }

    /**
     * 获取单个对象
     */
    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public BaseResult<Object> get(Integer id) {
        if(id <= 0) return new BaseResult<Object>(false,  ResultEnum.INVALID_DEPARTMENT.getMsg());

        Map<Object, Object> map = null;
        if(id !=null && id > 0){
            map = Maps.newHashMap();
            map.put("id",id);
        }
        Permission bean = null;
        try {
            bean = permissionService.selectObjByMap(map);
        } catch (BizException e) {
            return new BaseResult<Object>(false, e.getMessage());
        } catch (Exception e) {
            if(Constants.Debug()) e.printStackTrace();
            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
        }
        if(bean == null) return new BaseResult<Object>(false,  ResultEnum.INVALID_DEPARTMENT.getMsg());
        return new BaseResult<Object>(true, bean);
    }
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST,produces = {Constants.HEADER_CONTENT_TYPE_JSON})
    @ResponseBody
    public BaseResult<Object> save(@Valid @RequestBody BaseRequestBody<Permission> body, BindingResult result, HttpServletRequest request) {
        User loginuser = SessionUtil.getSessionUser(request);
        Permission bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = permissionService.insertSelective(bean);
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
    public BaseResult<Object> delete(@Valid @RequestBody BaseRequestBody<Permission> body, BindingResult result, HttpServletRequest request) {
        Permission bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = permissionService.deleteByPrimaryKey(bean.getId());
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
    public BaseResult<Object> update(@Valid @RequestBody BaseRequestBody<Permission> body, BindingResult result, HttpServletRequest request){
        Permission bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = permissionService.updateByPrimaryKeySelective(bean);
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
        String treedata = permissionService.getPermissionTree(0);
        return new BaseResult<Object>(true,null, treedata);
    }
    //###############################################################################################################//
    //                                             自定义controller的方法
    //###############################################################################################################//

    @RequestMapping("/listView")
    public String main(String visitedModule, String visitedResource, HttpServletRequest request, ModelMap map) {

        //初始化用户、菜单
        User user = SessionUtil.getSessionUser(request);
        String menus = systemService.getModuleTree(user.getId(),visitedModule,visitedResource);
        map.put("user", user);
        map.put("menus", menus);

        return "resource/list.ftl";
    }

    @ResponseBody
    @RequestMapping("/getResourceTree")
    public String getResourceTree(HttpServletRequest request,HttpServletResponse response,ModelMap map) {

        //这是为了jstree插件使用，这个插件只对Content-Type为json和html的内容进行处理
        response.setContentType("application/json;charset=UTF-8");

        return permissionService.getPermissionTree(0);
    }

    @ResponseBody
    @RequestMapping("/getResourceTreeWithChecked")
    public String getResourceTreeWithChecked(Integer roleId,HttpServletRequest request,HttpServletResponse response,ModelMap map) {

        //这是为了jstree插件使用，这个插件只对Content-Type为json和html的内容进行处理
        response.setContentType("application/json;charset=UTF-8");

        return permissionService.getPermissionTree(roleId);
    }

    @ResponseBody
    @RequestMapping("/get")
    public String get(Integer id,ModelMap map) {

        Permission resource = permissionService.selectByPrimaryKey(id);
        return JsonUtil.convertObj2json(resource).toString();
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(@ModelAttribute("resource")Permission resource, HttpServletRequest request) {

        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);
        //生成节点
        permissionService.createResource(resource, user);
        return JsonUtil.convertObj2json(resource).toString();
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute("resource") Permission resource, HttpServletRequest request) {
        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);

        //生成节点积累
        permissionService.updateResourceById(resource, user);

        return JsonUtil.convertObj2json(resource).toString();
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String  delete(@RequestParam("id") Integer id) throws Exception{

        //判断节点是否被用户关联
        if(permissionService.isUsedByRole(id))
        {
            return Constants.Fail;
        }

        permissionService.deleteResourceById(id);

        return Constants.Success;
    }

    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//

}
