package com.jqm.ssm.web;

import com.google.common.collect.Maps;
import com.jqm.ssm.dto.BaseRequestBody;
import com.jqm.ssm.dto.BaseResult;
import com.jqm.ssm.entity.Role;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.RoleSearchModel;
import com.jqm.ssm.entity.relate.UserSearchModel;
import com.jqm.ssm.enums.ResultEnum;
import com.jqm.ssm.exception.BizException;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.IRoleService;
import com.jqm.ssm.service.SystemService;
import com.jqm.ssm.util.ExcelUtils;
import com.jqm.ssm.util.JsonUtil;
import com.jqm.ssm.util.SessionUtil;
import com.jqm.ssm.util.StringUtil;
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
import java.util.List;
import java.util.Map;

/**
 * Created by wangyaoyao.
 */
@Controller
@RequestMapping(value = {"/role"})
public class RoleController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IRoleService roleService;
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
        List<Role> list = null;
        try {

            offset = offset == null ? 0 : offset;
            limit = limit == null ? Constants.DEFALUT_LIMIT : limit;

            list = roleService.listPage(offset, limit,null);

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
//        Role bean = null;
//        try {
//            bean = roleService.selectObjByMap(map);
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
    public BaseResult<Object> save(@Valid @RequestBody BaseRequestBody<Role> body, BindingResult result, HttpServletRequest request) {
        User loginuser = SessionUtil.getSessionUser(request);
        Role bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = roleService.insertSelective(bean);
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
    public BaseResult<Object> delete(@Valid @RequestBody BaseRequestBody<Role> body, BindingResult result, HttpServletRequest request) {
        Role bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = roleService.deleteByPrimaryKey(bean.getId());
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
    public BaseResult<Object> update(@Valid @RequestBody BaseRequestBody<Role> body, BindingResult result, HttpServletRequest request){
        Role bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = roleService.updateByPrimaryKeySelective(bean);
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
        String treedata = getRoleTree();
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

        return "role/list.ftl";
    }

    @ResponseBody
    @RequestMapping("/getRoleDataTables")
    public String getRoleDataTables(RoleSearchModel searchModel, ModelMap map) {
        LOG.info(searchModel.getIDisplayStart()+","+searchModel.getIDisplayLength());
        return roleService.getRoleDataTables(searchModel);
    }

    @ResponseBody
    @RequestMapping("/export")
    public void exportToExcel(RoleSearchModel searchModel, HttpServletRequest request, HttpServletResponse response){
        List<Role> roles = roleService.getRoleListBySearch(searchModel);
        ExcelUtils.getListToExcel(roles,"sheet","角色明细表",request,response,Role.class);
    }

    @ResponseBody
    @RequestMapping("/getRoleDataRow")
    public String  getRoleDataRow(@RequestParam("id") Integer id) throws Exception{
        return roleService.getRoleDataRow(id);
    }

    @ResponseBody
    @RequestMapping("/get")
    public String  get(@RequestParam("id") Integer id) throws Exception{
        Role role = roleService.selectByPrimaryKey(id);
        return JsonUtil.convertObj2json(role).toString();
    }

    @RequestMapping("/addform")
    public String addform() {
        return "role/addform.ftl";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(@ModelAttribute("role")Role role,HttpServletRequest request) {
        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);
        //生成节点角色
        if(role.getRemark() == null)
        {
            role.setRemark("");
        }
        roleService.createRole(role,user);
        return Constants.Success;
    }

    @RequestMapping("/updateform")
    public String updateform(Integer id,HttpServletRequest request,ModelMap map) {
        Role role = roleService.selectByPrimaryKey(id);
        map.put("role", role);
        return "role/updateform.ftl";
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute("role")  Role role,HttpServletRequest request) {
        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);
        //生成节点角色
        roleService.updateRoleById(role,user);

        return Constants.Success;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String  delete(@RequestParam("id") Integer id) {

        //判断节点是否被用户关联
        if(roleService.isUsedByUser(id))
        {
            return Constants.Fail;
        }

        roleService.deleteRoleById(id);

        return Constants.Success;
    }

    @RequestMapping("/assignform")
    public String assignform(String id,ModelMap map)
    {
        map.put("id", id);
        return "role/assignform.ftl";
    }

    @ResponseBody
    @RequestMapping("/assign")
    public String assign(Integer id,String checkedStr)
    {
        if(id == null || StringUtil.isStrEmpty(id.toString()) || StringUtil.isStrEmpty(checkedStr))
        {
            return Constants.Fail;
        }
        roleService.assignModuleAndResource(id, checkedStr);

        return Constants.Success;
    }

    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//
    /**
     * 获取treedata树型结构
     */
    public String getRoleTree(){
        return "{}";
    }
}
