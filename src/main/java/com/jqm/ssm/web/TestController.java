package com.jqm.ssm.web;


import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.dao.DevicegpsDao;
import com.jqm.ssm.dto.BaseGpsResult;
import com.jqm.ssm.dto.BaseRequestBody;
import com.jqm.ssm.dto.BaseResult;
import com.jqm.ssm.dto.GpsFeature;
import com.jqm.ssm.entity.Devicegps;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.UserSearchModel;
import com.jqm.ssm.enums.ResultEnum;
import com.jqm.ssm.exception.BizException;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.IDevicegpsService;
import com.jqm.ssm.service.IUserService;
import com.jqm.ssm.service.SystemService;
import com.jqm.ssm.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

//: object/UserController
/** 这是一个Javadoc测试程序
 *  @author wangyao
 *  @version 1.0
 *  @since 1.5
 * */
@Controller
@RequestMapping("/test")
public class TestController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IUserService userService;
    @Autowired
    SystemService systemService;
    @Autowired
    RedisCache redisCache;
    @Autowired
    IDevicegpsService devicegpsService;
    @Autowired
    DevicegpsDao devicegpsDao;
    @Autowired
    ConfigUtil configUtil;

//    @RequestMapping(value = "/gps", method = RequestMethod.GET)
//    @ResponseBody
//    public BaseGpsResult<Object> togps(Integer offset, Integer limit){
//        List<Devicegps> list =  devicegpsService.selectListByMap(null);
//        return new BaseGpsResult(list);
//    }

    /**
     * 返回前台页面所需要的数据
     * @param offset
     * @param limit
     * @param response
     * @return
     */
    @RequestMapping(value = "/gps2", method = RequestMethod.GET)
    @ResponseBody
    public String togps2(Integer offset, Integer limit, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);

        List<Devicegps> list =  devicegpsService.selectListByMap(null);
        List<GpsFeature> glist = Lists.newArrayList();
        for (Devicegps devicegps:list){
            GpsFeature<Devicegps> g = new GpsFeature<Devicegps>(devicegps);
            glist.add(g);
        }
        return JsonUtil.objectToJson(new BaseGpsResult(glist));
        //return new BaseGpsResult(list);
    }



    /**
     * 返回用户列表 可分页s
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> list(Integer offset, Integer limit) {
        LOG.info("invoke----------/user/list");
        offset = offset == null ? 0 : offset;//默认便宜0
        limit = limit == null ? Constants.DEFALUT_LIMIT : limit;//默认展示50条
        LOG.info("offset="+offset+",limit="+limit);
        List<User> userlist = userService.listPage(offset, limit,null);
        return new BaseResult<Object>(true, userlist);
    }

    @RequestMapping("/listView")
    public String main(String visitedModule, String visitedResource, HttpServletRequest request, ModelMap map) {
        //初始化用户、菜单
        User user = SessionUtil.getSessionUser(request);
        String menus = systemService.getModuleTree(user.getId(),visitedModule,visitedResource);
        map.put("user", user);
        map.put("menus", menus);
        return "test/list.ftl";
    }
    @ResponseBody
    @RequestMapping("/getUserDataTables")
    public String getUserDataTables(UserSearchModel searchModel, ModelMap map) {
        return userService.getUserDataTables(searchModel);
    }
    @ResponseBody
    @RequestMapping("/getUserDataRow")
    public String  getUserDataRow(@RequestParam("id") Integer id) throws Exception{
        return userService.getUserDataRow(id);
    }
    @ResponseBody
    @RequestMapping("/getEchartDataOption")
    public String getEchartDataOption(@RequestParam("type")String type,HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String data = Constants.EmptyJsonObject;
        if("pie".equals(type))
            data=EChartUtil.generatePie();
        else if("bar".equals(type))
            data= EChartUtil.generateBar();
        else if("line".equals(type))
            data = EChartUtil.generateLine();
        return  data;
    }

    @ResponseBody
    @RequestMapping("/getEcharMapData")
    public String getEcharMapData(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String data = Constants.EmptyJsonObject;

        List<Devicegps> list =  devicegpsDao.getDeviceinfoList();
        data = EChartUtil.generateMapData(list,configUtil.getBasePath());
        System.out.println(data);
        return data;
    }





    //###############################################################################################################//
    //                                             自定义controller的方法
    //###############################################################################################################//
    /**
     * 根据用户id返回个人信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = {Constants.HEADER_CONTENT_TYPE_JSON})
    @ResponseBody
    public BaseResult<User> buy(@PathVariable("userId") Integer userId) {
        if (userId <= 0 ) {
            return new BaseResult<User>(false, ResultEnum.INVALID_USER.getMsg());
        }
        //Valid 参数验证(这里注释掉，采用AOP的方式验证,见BindingResultAop.java)
        //if (result.hasErrors()) {
        //    String errorInfo = "[" + result.getFieldError().getField() + "]" + result.getFieldError().getDefaultMessage();
        //    return new BaseResult<Object>(false, errorInfo);
        //}
        User user = null;
        try {
            user = userService.selectByPrimaryKey(userId);
        } catch (BizException e) {
            return new BaseResult<User>(false, e.getMessage());
        } catch (Exception e) {
            return new BaseResult<User>(false, ResultEnum.INNER_ERROR.getMsg());
        }
        if(user == null){
            return new BaseResult<User>(false, ResultEnum.INVALID_USER.getMsg());
        }
        return new BaseResult<User>(true, user);
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {Constants.HEADER_CONTENT_TYPE_JSON})
    @ResponseBody
    public BaseResult<Object> login(@Valid @RequestBody BaseRequestBody<User> body, BindingResult result, HttpServletRequest request){
//        String error = TokenUtil.checkToken(body.getToken());
//        if(error!=""){
//            return new BaseResult<Object>(false, error);
//        }
        User user = body.getData();
        User loginuser = null;
        try {
            Map<Object,Object> map = Maps.newHashMap();
            map.put(User.Column.username,user.getUsername());
            map.put(User.Column.password,user.getPassword());
            String cachekey = Constants.CACHE_KEY_LOGINNAME_ + user.getUsername();
            loginuser = userService.selectObjByMap(map,cachekey);
        } catch (BizException e) {
            return new BaseResult<Object>(false, e.getMessage());
        } catch (Exception e) {
            if(Constants.Debug()) e.printStackTrace();
            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
        }
        if(loginuser ==null)   return new BaseResult<Object>(false, ResultEnum.INVALID_USER.getMsg());

        //放入缓存 放入redis缓存 注意不过期
        request.getSession().setAttribute(SessionUtil.SessionSystemLoginUserName, loginuser);
        redisCache.putCache(Constants.CACHE_KEY_CURRENT_LOGINUSER ,loginuser);
        return new BaseResult<Object>(true, loginuser);
    }

    /**
     *  注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = {Constants.HEADER_CONTENT_TYPE_JSON})
    @ResponseBody
    public BaseResult<Object> register(@Valid @RequestBody BaseRequestBody<User> body, BindingResult result){
        String error = TokenUtil.checkToken(body.getToken());
        if(error!=""){
            return new BaseResult<Object>(false, error);
        }
        User user = body.getData();
        if (!(checkLoginName(user.getUsername()))){

        }
        return null;
    }


    /**
     * 修改个人用户密码
     * @param oldPassword
     * @param newPassword
     * @param model
     * @return
     */
    @RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> modifyPwd(String oldPassword, String newPassword, Model model) {
//        User user = UserUtils.getUser();
//        if (!Strings.isNullOrEmpty(oldPassword) && !Strings.isNullOrEmpty(newPassword)){
//            if (SystemService.validatePassword(oldPassword, user.getPassword())){
//                systemService.updatePasswordById(user.getId(), user.getLogname(), newPassword);
//                model.addAttribute("message", "修改密码成功");
//            }else{
//                model.addAttribute("message", "修改密码失败，旧密码错误");
//            }
//        }
//        model.addAttribute("user", user);
//        return "modules/sys/userModifyPwd";
        return null;
    }

    /**
     * 返回用户的角色列表
     */
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> roleList(){
        return null;
    }
    /**
     * 返回用户的权限列表
     */
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> permissionList(){
        return null;
    }
    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//
    /**
     * 验证登录名是否有效
     * @param loginName
     * @return
     */
    private boolean checkLoginName( String loginName) {
        if (!Strings.isNullOrEmpty(loginName)) {
            Map<Object,Object> map = Maps.newHashMap();
            map.put(User.Column.username , loginName);
            String cachekey = Constants.CACHE_KEY_LOGINNAME_ + loginName;
            if(userService.selectObjByMap(map,cachekey)==null)
                return true;
        }
        return false;
    }
}///:~

