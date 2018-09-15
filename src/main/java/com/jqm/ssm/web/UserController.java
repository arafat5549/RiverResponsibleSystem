package com.jqm.ssm.web;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.collect.Maps;
import com.jqm.ssm.dto.BaseRequestBody;
import com.jqm.ssm.dto.BaseResult;
import com.jqm.ssm.entity.Department;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.RoleSearchModel;
import com.jqm.ssm.entity.relate.UserSearchModel;
import com.jqm.ssm.enums.ResultEnum;
import com.jqm.ssm.exception.BizException;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.IDepartmentService;
import com.jqm.ssm.service.IRoleService;
import com.jqm.ssm.service.IUserService;
import com.jqm.ssm.service.SystemService;
import com.jqm.ssm.util.*;
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
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyaoyao.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IUserService userService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private SystemService systemService;

    /**
     * 列表
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> list(Integer offset, Integer limit) {
        List<User> list = null;
        try {

            offset = offset == null ? 0 : offset;
            limit = limit == null ? Constants.DEFALUT_LIMIT : limit;

            list = userService.listPage(offset, limit,null);

        } catch (BizException e) {
            return new BaseResult<Object>(false, e.getMessage());
        } catch (Exception e) {
            if(Constants.Debug()) e.printStackTrace();
            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
        }
        return new BaseResult<Object>(true, list);
    }

//    /**
//     * 获取单个对象
//     */
//    @ResponseBody
//    @RequestMapping(value = "/get",method = RequestMethod.GET)
//    public BaseResult<Object> get(Integer id) {
//        if(id <= 0) return new BaseResult<Object>(false,  ResultEnum.INVALID_DEPARTMENT.getMsg());
//
//        Map<Object, Object> map = null;
//        if(id !=null && id > 0){
//            map = Maps.newHashMap();
//            map.put("id",id);
//        }
//        User bean = null;
//        try {
//            bean = userService.selectObjByMap(map);
//        } catch (BizException e) {
//            return new BaseResult<Object>(false, e.getMessage());
//        } catch (Exception e) {
//            if(Constants.Debug()) e.printStackTrace();
//            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
//        }
//        if(bean == null) return new BaseResult<Object>(false,  ResultEnum.INVALID_DEPARTMENT.getMsg());
//        return new BaseResult<Object>(true, bean);
//    }
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST,produces = {Constants.HEADER_CONTENT_TYPE_JSON})
    @ResponseBody
    public BaseResult<Object> save(@Valid @RequestBody BaseRequestBody<User> body, BindingResult result, HttpServletRequest request) {
        User loginuser = SessionUtil.getSessionUser(request);
        User bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = userService.insertSelective(bean);
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
    public BaseResult<Object> delete(@Valid @RequestBody BaseRequestBody<User> body, BindingResult result, HttpServletRequest request) {
        User bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = userService.deleteByPrimaryKey(bean.getId());
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
    public BaseResult<Object> update(@Valid @RequestBody BaseRequestBody<User> body, BindingResult result, HttpServletRequest request){
        User bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = userService.updateByPrimaryKeySelective(bean);
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
        String treedata = getUserTree();
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
        //部门下拉框
        List<Department> departments = departmentService.getDepartmentListForOption();
        map.put("departments", departments);
        map.put("user", user);
        map.put("menus", menus);

        return "user/list.ftl";
    }

    @ResponseBody
    @RequestMapping(value = "/getUserDataTables")
    public String getUserDataTables(UserSearchModel searchModel, ModelMap map) {
        LOG.info(searchModel.getIDisplayStart()+","+searchModel.getIDisplayLength());
        return userService.getUserDataTables(searchModel);
    }

    @ResponseBody
    @RequestMapping("/export")
    public void exportToExcel(UserSearchModel searchModel,HttpServletRequest request,HttpServletResponse response){
        List<User> users = userService.getUserListBySearch(searchModel);
        ExcelUtils.getListToExcel(users,"sheet","用户明细表",request,response,User.class);
    }

    @ResponseBody
    @RequestMapping("/getUserDataRow")
    public String  getUserDataRow(@RequestParam("id") Integer id) throws Exception{
        return userService.getUserDataRow(id);
    }

    @ResponseBody
    @RequestMapping("/get")
    public String get(@RequestParam("id") Integer id) throws Exception{
        User user = userService.getUserById(id);
        return JsonUtil.convertObj2json(user).toString();
    }

    @RequestMapping("/addform")
    public String addform(ModelMap map) {
        List<Department> departments = departmentService.getDepartmentListForOption();
        map.put("departments", departments);
        return "user/addform.ftl";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(User user,HttpServletRequest request) {
        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);
        userService.createUser(user, operator);
        return Constants.Success;
    }

    @RequestMapping("/updateform")
    public String updateform(Integer id,HttpServletRequest request,ModelMap map) {
        List<Department> departments = departmentService.getDepartmentListForOption();
        User user = userService.getUserById(id);
        map.put("departments", departments);
        map.put("user", user);
        return "user/updateform.ftl";
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute("user")  User user,HttpServletRequest request) {
        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        userService.updateUserById(user,operator);

        return Constants.Success;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Integer id,HttpServletRequest request){

        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        User user = new User();
        user.setId(id);
        user.setDeleteFlag(true);
        userService.updateUserById(user, operator);

        return Constants.Success;
    }

    @ResponseBody
    @RequestMapping("/resetpass")
    public String resetpass(@RequestParam("id") Integer id,HttpServletRequest request){

        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        User user = new User();
        user.setId(id);
        user.setPassword(Constants.DefaultMd5Password);

        userService.updateUserById(user, operator);

        return Constants.Success;
    }

    @ResponseBody
    @RequestMapping("/lock")
    public String lock(@RequestParam("id") Integer id,HttpServletRequest request){

        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        User user = new User();
        user.setId(id);
        user.setIsLock(true);

        userService.updateUserById(user, operator);

        return Constants.Success;
    }

    @ResponseBody
    @RequestMapping("/unlock")
    public String unlock(@RequestParam("id") Integer id,HttpServletRequest request){

        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        User user = new User();
        user.setId(id);
        user.setIsLock(false);

        userService.updateUserById(user, operator);

        return Constants.Success;
    }

    @RequestMapping("/assignform")
    public String assignform(Integer id,ModelMap map) {

        map.put("options", roleService.getRoleForOptions(id));
        map.put("id", id);

        return "user/assignform.ftl";
    }

    @ResponseBody
    @RequestMapping("/assign")
    public String assign(Integer id,String selectedStr)
    {
        if(id==null || StringUtil.isStrEmpty(id.toString()) || StringUtil.isStrEmpty(selectedStr))
        {
            return Constants.Fail;
        }
        userService.assignRole(id, selectedStr);

        return Constants.Success;
    }


    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//
    /**
     * 获取treedata树型结构
     */
    public String getUserTree(){
        return "{}";
    }
}
