package com.jqm.ssm.web;

import com.jqm.ssm.dto.BaseRequestBody;
import com.jqm.ssm.dto.BaseResult;
import com.jqm.ssm.entity.Department;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.enums.ResultEnum;
import com.jqm.ssm.exception.BizException;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.IDepartmentService;
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
 * Created by wangyaoyao on 2018/8/15.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IDepartmentService departmentService;
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
        List<Department> list = null;
        try {

            offset = offset == null ? 0 : offset;
            limit = limit == null ? Constants.DEFALUT_LIMIT : limit;

            list = departmentService.listPage(offset, limit,null);

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
    public BaseResult<Object> save(@Valid @RequestBody BaseRequestBody<Department> body, BindingResult result, HttpServletRequest request) {
        User loginuser = SessionUtil.getSessionUser(request);
        Department bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = departmentService.insertSelective(bean);
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
    public BaseResult<Object> delete(@Valid @RequestBody BaseRequestBody<Department> body, BindingResult result, HttpServletRequest request) {
        Department bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = departmentService.deleteByPrimaryKey(bean.getId());
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
    public BaseResult<Object> update(@Valid @RequestBody BaseRequestBody<Department> body, BindingResult result, HttpServletRequest request){
        Department bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = departmentService.updateByPrimaryKeySelective(bean);
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
        String treedata = departmentService.getDepartmentTree();
        return new BaseResult<Object>(true,null, treedata);
    }

    //###############################################################################################################//
    //                                            跳转到页面的方法
    //###############################################################################################################//
    @RequestMapping(value="/listView", method = RequestMethod.GET)
    public String main(String visitedModule, String visitedResource, HttpServletRequest request, ModelMap map) {
         LOG.info("visitedModule="+visitedModule+",visitedResource="+visitedResource);
        //初始化用户、菜单
        User user = SessionUtil.getSessionUser(request);
        String menus = systemService.getModuleTree(user.getId(),visitedModule,visitedResource);
        map.put("user", user);
        map.put("menus", menus);
        return "department/list.ftl";
    }

    //###############################################################################################################//
    //                                             自定义controller的方法,不返回对象
    //###############################################################################################################//


    /**
     * 获取单个对象
     */
    @ResponseBody
    @RequestMapping(value = "/get")
    public String get(Integer id) {
        Department department = null;
        if(id==null || id <= 0)
            department = null;
        else
            department = departmentService.selectByPrimaryKey(id);
        String resp = JsonUtil.convertObj2json(department).toString();
        LOG.info("get="+id);
        return resp;
    }

    @RequestMapping(value = "/getDepartmentTree")
    @ResponseBody
    public String getDepartmentTree(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String treedata = departmentService.getDepartmentTree();
        return treedata;
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(@ModelAttribute("department")Department department, HttpServletRequest request) {
        LOG.info("add="+department.toString());
        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);
        //生成节点
        departmentService.createDepartment(department,user);
        return JsonUtil.convertObj2json(department).toString();
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String  delete(@RequestParam("id") Integer id){
        LOG.info("delete="+id);
        //判断节点是否被用户关联
        if(departmentService.isUsedByUser(id))
        {
            return Constants.Fail;
        }
        departmentService.deleteByPrimaryKey(id);
        return Constants.Success;
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute("department")  Department department,HttpServletRequest request) {
        LOG.info("update="+department.toString());
        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);
        //生成节点积累
        departmentService.updateDepartment(department,user);
        return JsonUtil.convertObj2json(department).toString();
    }

    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//


}
