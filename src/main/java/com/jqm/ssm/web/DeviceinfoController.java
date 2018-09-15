package com.jqm.ssm.web;

import com.jqm.ssm.dto.BaseRequestBody;
import com.jqm.ssm.dto.BaseResult;
import com.jqm.ssm.entity.*;
import com.jqm.ssm.entity.relate.DeviceinfoSearchModel;
import com.jqm.ssm.enums.ResultEnum;
import com.jqm.ssm.exception.BizException;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.*;
import com.jqm.ssm.util.*;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wangyaoyao.
 */
@Controller
@RequestMapping("/deviceinfo")
public class DeviceinfoController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IDeviceinfoService deviceinfoService;
    @Autowired
    SystemService systemService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IBrandService brandService;
    @Value("${inc.basepath}")
    private String incPath;
    private static final String local = "D:\\software\\install\\nginx-1.14.0\\html";


    /**
     * 列表
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> list(Integer offset, Integer limit) {
        List<Deviceinfo> list = null;
        try {

            offset = offset == null ? 0 : offset;
            limit = limit == null ? Constants.DEFALUT_LIMIT : limit;

            list = deviceinfoService.listPage(offset, limit,null);

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
    public BaseResult<Object> save(@Valid @RequestBody BaseRequestBody<Deviceinfo> body, BindingResult result, HttpServletRequest request) {
        User loginuser = SessionUtil.getSessionUser(request);
        Deviceinfo bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = deviceinfoService.insertSelective(bean);
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
    public BaseResult<Object> delete(@Valid @RequestBody BaseRequestBody<Deviceinfo> body, BindingResult result, HttpServletRequest request) {
        Deviceinfo bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = deviceinfoService.deleteByPrimaryKey(bean.getId());
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
    public BaseResult<Object> update(@Valid @RequestBody BaseRequestBody<Deviceinfo> body, BindingResult result, HttpServletRequest request){
        Deviceinfo bean = null;
        int resultCode = -1;
        try {
            bean = body.getData();
            resultCode = deviceinfoService.updateByPrimaryKeySelective(bean);
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
        String treedata = getDeviceinfoTree();
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
        List<Brand> brands = brandService.selectListByMap(null);
        List<Category> categorys = categoryService.getCategoryListForOption();
        map.put("brands", brands);
        map.put("categorys", categorys);
        map.put("user", user);
        map.put("menus", menus);
        return "deviceinfo/list.ftl";
    }

    @ResponseBody
    @RequestMapping("/getDeviceinfoDataTables")
    public String getDeviceinfoDataTables(DeviceinfoSearchModel searchModel, ModelMap map) {
        LOG.info(searchModel.getIDisplayStart()+","+searchModel.getIDisplayLength());
        return deviceinfoService.getDeviceinfoDataTables(searchModel);
    }

    @ResponseBody
    @RequestMapping("/export")
    public void exportToExcel(DeviceinfoSearchModel searchModel,HttpServletRequest request,HttpServletResponse response){
        List<Deviceinfo> deviceinfos = deviceinfoService.getDeviceinfoListBySearch(searchModel);
        ExcelUtils.getListToExcel(deviceinfos,"sheet","设备信息表",request,response,Deviceinfo.class);
    }


    @ResponseBody
    @RequestMapping("/getDeviceinfoDataRow")
    public String  getUserDataRow(@RequestParam("id") Integer id) throws Exception{
        return deviceinfoService.getDeviceinfoDataRow(id);
    }

    @ResponseBody
    @RequestMapping("/get")
    public String get(@RequestParam("id") Integer id) throws Exception{
        Deviceinfo deviceinfo = deviceinfoService.getDeviceinfoById(id);
        return JsonUtil.convertObj2json(deviceinfo).toString();
    }

    @RequestMapping("/addform")
    public String addform(ModelMap map) {
        List<Brand> brands = brandService.selectListByMap(null);
        List<Category> categorys = categoryService.getCategoryListForOption();
        map.put("brands", brands);
        map.put("categorys", categorys);
        return "deviceinfo/addform.ftl";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(@ModelAttribute("deviceinfo")Deviceinfo deviceinfo,HttpServletRequest request) {
        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);
        deviceinfoService.createDeviceinfo(deviceinfo, operator);
        return Constants.Success;
    }

    @RequestMapping("/updateform")
    public String updateform(Integer id,HttpServletRequest request,ModelMap map) {
        List<Brand> brands = brandService.selectListByMap(null);
       // String treeStr = categoryService.getCategoryTree();
        //JSONArray categorys = JSONArray.parseArray(treeStr);
        List<Category> categorys = categoryService.selectListByMap(null);
        Deviceinfo deviceinfo = deviceinfoService.getDeviceinfoById(id);
        map.put("brands", brands);
        map.put("categorys", categorys);
        map.put("deviceinfo", deviceinfo);
        return "deviceinfo/updateform.ftl";
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute("deviceinfo")  Deviceinfo deviceinfo,HttpServletRequest request) {
        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        deviceinfoService.updateDeviceinfoById(deviceinfo,operator);

        return Constants.Success;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Integer id,HttpServletRequest request){

        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);
        Deviceinfo deviceinfo = new Deviceinfo();
        deviceinfo.setId(id);
        deviceinfo.setDeleteFlag(true);
        deviceinfoService.updateDeviceinfoById(deviceinfo, operator);
        return Constants.Success;
    }

    @RequestMapping("/assignform")
    public String assignform(Integer id,ModelMap map) {

        Map<Object,Object> m = new HashMap<>();
        m.put("deviceinfoId",id);
        map.put("id",id);
        Integer count = deviceinfoPictureService.selectCountByMap(m);
        map.put("count", 3-count);
        return "deviceinfo/assignform.ftl";
    }

    @RequestMapping("/imageform")
    public String imageform(Integer id,ModelMap map) {

        Map<Object,Object> m = new HashMap<>();
        m.put("deviceinfoId",id);
        List<DeviceinfoPicture> deviceinfoPictures = deviceinfoPictureService.selectListByMap(m);
        deviceinfoPictures.forEach(d->d.setName(incPath+"/upload/photo/"+d.getName()));
        map.put("id", id);
        map.put("list",deviceinfoPictures);
        return "deviceinfo/imageform.ftl";
    }

    @ResponseBody
    @RequestMapping("/deleteImage")
    public String deleteImage(@RequestParam("id") Integer id,@RequestParam("src") String src,HttpServletRequest request){
       String url= src.replace(incPath,local);
       String smallurl= src.replace(incPath+"/upload/photo",local+"\\upload\\photo\\small");
        deviceinfoPictureService.deleteByPrimaryKey(id);
        DeleteFileUtil.deleteFile(url);
        DeleteFileUtil.deleteFile(smallurl);
        return Constants.Success;
    }

    @Autowired
    IDeviceinfoPictureService deviceinfoPictureService;

    @RequestMapping(value="/uploader",method = RequestMethod.POST)
    @ResponseBody
    public String load(@ModelAttribute("deviceinfoPicture") DeviceinfoPicture deviceinfoPicture, HttpServletRequest request){
        User operator = SessionUtil.getSessionUser(request);
        List<String> urls = UploadImageUtil.upload(request);
        for(String url : urls){
            deviceinfoPicture.setName(url);
            deviceinfoPictureService.createDeviceinfoPicture(deviceinfoPicture,operator);
        }
        return Constants.Success;
    }


    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//
        /**
     * 获取treedata树型结构
     */
    public String getDeviceinfoTree(){
        return "{}";
    }
}
