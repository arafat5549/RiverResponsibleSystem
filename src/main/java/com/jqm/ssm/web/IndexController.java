package com.jqm.ssm.web;

import com.google.common.collect.Maps;
import com.jqm.ssm.dao.MenucateDao;
import com.jqm.ssm.dao.UserDao;
import com.jqm.ssm.entity.Menucate;
import com.jqm.ssm.entity.Permission;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.IMenucateService;
import com.jqm.ssm.service.IPermissionService;
import com.jqm.ssm.service.IUserService;
import com.jqm.ssm.service.SystemService;
import com.jqm.ssm.util.ConfigUtil;
import com.jqm.ssm.util.SessionUtil;
import com.jqm.ssm.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyaoyao on 2018/8/15.
 */
@Controller
@RequestMapping("/")
public class IndexController {

//    @Autowired
//    private IMenucateService menucateService;
    @Autowired
    private IUserService userService;
//    @Autowired
//    private IPermissionService permissionService;

    @Autowired
    private ConfigUtil configUtil;
    @Autowired
    private SystemService systemService;

    @RequestMapping("/main")
    public String main(String visitedModule,HttpServletRequest request,ModelMap map) {

        visitedModule = "p";

        //初始化用户、菜单
        User user = SessionUtil.getSessionUser(request);
        String menus = systemService.getModuleTree(user.getId(),visitedModule,"");
        map.put("user", user);
        map.put("menus", menus);

        int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        map.put("hours", hours);

        return "main.ftl";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception
    {
        SessionUtil.clearSession(request);
        //被拦截器拦截处理
        return "redirect:/main";//"redirect:" + configUtil.getCasServerUrl()+"/logout?service=" + configUtil.getCasServiceUrl();
    }

    @RequestMapping("/modifypasswordform")
    public String modifypasswordform(HttpServletRequest request) throws Exception
    {
        return "modifypasswordform.ftl";
    }

    @ResponseBody
    @RequestMapping("/modifypassword")
    public String modifypassword(String oldpassword,String password,HttpServletRequest request) throws Exception
    {
        if(StringUtil.isStrEmpty(oldpassword) || StringUtil.isStrEmpty(password))	return Constants.Fail;
        //初始化用户、菜单
        User user = SessionUtil.getSessionUser(request);
        if(!user.getPassword().equals(StringUtil.makeMD5(oldpassword))) return Constants.Fail;
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setPassword(StringUtil.makeMD5(password));
        newUser.setUpdateDate(new Date());
        newUser.setUpdatePerson(user.getUsername());
        //systemService.updateUserById(newUser);
        userService.updateByPrimaryKeySelective(newUser);

        //更新session
        user.setPassword(newUser.getPassword());
        request.getSession().setAttribute(SessionUtil.SessionSystemLoginUserName,user);

        return Constants.Success;
    }
    /*
    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {

        //初始化用户、菜单
        User user = SessionUtil.getSessionUser(request);
        if(user ==null)
            user = userService.selectByPrimaryKey(1);
        List<Menucate> menuCatelist = menucateService.selectListByMap(null);
        StringBuffer sb = new StringBuffer();
        for(Menucate m:menuCatelist)
        {
            sb.append("<li>");
            sb.append("<a href=\"javascript:;\">");
            sb.append("<i class=\"fa " + m.getIcon() + "\"></i>");
            sb.append("<span class=\"title\">");
            sb.append(" " + m.getName());
            sb.append("</span>");
            sb.append("<span class=\"selected\"></span>");
            sb.append("<span class=\"arrow open\"></span>");
            sb.append("</a>");
            String tree = buildPermissionTree(m);
            //System.out.println("tree="+tree);
            sb.append(tree);
            sb.append("</li>");
        }
        modelMap.put("user", user);
        modelMap.put("menus", sb.toString());
        int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        modelMap.put("hours", hours);

        return "main.ftl";
    }
    */
    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//

    /**
     * 结果带buildXXXXTree
     * 一般代表返回的是前台的html代码 一般ul树结构
     * @param menucate
     * @return

    private String buildPermissionTree(Menucate menucate){
        if(menucate ==null) return "";

        Map<Object,Object> map = Maps.newHashMap();
        //map.put("menucateid",menucate.getId());
        List<Permission> ps = permissionService.selectListByMap(map);

        if(ps == null || ps.size()==0)
        {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("<ul class=\"sub-menu\">");
        for(Permission r:ps)
        {
            //递归，找子级的树形结构
            //Menucate m2 = menucateService.selectByPrimaryKey(r.getMenucateid());
            String s = "";//buildPermissionTree(m2);
            boolean active = false;
            if(active)
            {
                sb.append("<li class=\"active\">");
            } else {
                sb.append("<li>");
            }
            if(s.equals(""))
            {
                sb.append("<a href=\"")
                        //.append(m.getUrl())
                        .append(r.getUrl())
                        .append("\">")
                ;
                sb.append("<i class=\"fa fa-leaf \"></i>&nbsp;&nbsp;");
                sb.append(r.getName());
                sb.append("</a>");
            }
            else
            {
                sb.append("<a href=\"javascript:;\">");
                sb.append("<i class=\"fa fa-folder \"></i>&nbsp;&nbsp;");
                sb.append(r.getName());
                if(active)
                {
                    sb.append("<span class=\"arrow open\"></span>");
                } else {
                    sb.append("<span class=\"arrow\"></span>");
                }
                sb.append("</a>");
            }
            sb.append(s);
            sb.append("</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }
     */

}
