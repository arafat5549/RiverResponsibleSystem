package com.jqm.ssm.web;

import com.jqm.ssm.dto.BaseRequestBody;
import com.jqm.ssm.dto.BaseResult;
import com.jqm.ssm.entity.Brand;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.BrandSearchModel;
import com.jqm.ssm.entity.relate.UserSearchModel;
import com.jqm.ssm.enums.ResultEnum;
import com.jqm.ssm.exception.BizException;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.IBrandService;
import com.jqm.ssm.service.SystemService;
import com.jqm.ssm.util.ExcelUtils;
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
import java.util.List;

/**
 * Created by wangyaoyao.
 */
@Controller
@RequestMapping("/brand")
public class BrandController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IBrandService brandService;
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
        List<Brand> list = null;
        try {

            offset = offset == null ? 0 : offset;
            limit = limit == null ? Constants.DEFALUT_LIMIT : limit;

            list = brandService.listPage(offset, limit,null);

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
    public BaseResult<Object> save(@Valid @RequestBody BaseRequestBody<Brand> body, BindingResult result, HttpServletRequest request) {
        User loginuser = SessionUtil.getSessionUser(request);
        Brand bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = brandService.insertSelective(bean);
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
    public BaseResult<Object> delete(@Valid @RequestBody BaseRequestBody<Brand> body, BindingResult result, HttpServletRequest request) {
        Brand bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = brandService.deleteByPrimaryKey(bean.getId());
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
    public BaseResult<Object> update(@Valid @RequestBody BaseRequestBody<Brand> body, BindingResult result, HttpServletRequest request){
        Brand bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = brandService.updateByPrimaryKeySelective(bean);
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
        String treedata = getBrandTree();
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
        return "brand/list.ftl";
    }

    @ResponseBody
    @RequestMapping("/getBrandDataTables")
    public String getBrandDataTables(BrandSearchModel searchModel,ModelMap map) {
        LOG.info(searchModel.getIDisplayStart()+","+searchModel.getIDisplayLength());
        return brandService.getBrandDataTables(searchModel);
    }

    @ResponseBody
    @RequestMapping("/export")
    public void exportToExcel(BrandSearchModel searchModel, HttpServletRequest request, HttpServletResponse response){
        List<Brand> brands = brandService.getBrandListBySearch(searchModel);
        ExcelUtils.getListToExcel(brands,"sheet","品牌明细表",request,response,Brand.class);
    }


    @RequestMapping("/addform")
    public String addform(ModelMap map) {
        //List<Brand> departments = brandService.getDepartmentListForOption();
      //  map.put("departments", departments);
        return "brand/addform.ftl";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(@ModelAttribute("brand")Brand brand, HttpServletRequest request) {
        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);
       brandService.createBrand(brand, operator);
        return Constants.Success;
    }

    @RequestMapping("/updateform")
    public String updateform(Integer id,HttpServletRequest request,ModelMap map) {
        Brand brand = brandService.getBrandById(id);
        map.put("brand", brand);
        return "brand/updateform.ftl";
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute("brand")  Brand brand,HttpServletRequest request) {
        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        brandService.updateBrandById(brand,operator);

        return Constants.Success;
    }

    @ResponseBody
    @RequestMapping("/getBrandDataRow")
    public String  getBrandDataRow(@RequestParam("id") Integer id) throws Exception{
        return brandService.getBrandDataRow(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Integer id,HttpServletRequest request){

        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        Brand brand =new Brand ();
        brand.setId(id);
        brand.setDeleteFlag(true);
        brandService.updateUserById(brand, operator);

        return Constants.Success;
    }


    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//
        /**
     * 获取treedata树型结构
     */
    public String getBrandTree(){
        return "{}";
    }
}
