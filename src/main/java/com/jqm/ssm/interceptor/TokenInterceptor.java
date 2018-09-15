package com.jqm.ssm.interceptor;

import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.dao.UserDao;
import com.jqm.ssm.dto.BaseResult;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.enums.ResultEnum;
import com.jqm.ssm.misc.Constants;
import com.jqm.ssm.service.SystemService;
import com.jqm.ssm.util.ConfigUtil;
import com.jqm.ssm.util.JsonUtil;
import com.jqm.ssm.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 拦截指定path，进行权限验证，及用户的本地session过期后，重新进行赋值
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemService systemService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //处理跨域攻击
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("X-Powered-By", "3.2.1");

        LOG.info("------------------"+request.getRequestURL()+","+configUtil.getProjectType());

        if("Dev".equals(configUtil.getProjectType())){
            User user = userDao.selectByPrimaryKey(1);
            request.getSession().setAttribute(SessionUtil.SessionSystemLoginUserName,user);
            return true;
        }

        User user = SessionUtil.getSessionUser(request);
        if(user == null)
        {
            //存储Session:用户登录名
            user = redisCache.getCache(Constants.CACHE_KEY_CURRENT_LOGINUSER,User.class);
            request.getSession().setAttribute(SessionUtil.SessionSystemLoginUserName,user);
            LOG.info(user+","+ request.getSession().getAttribute(SessionUtil.SessionSystemLoginUserName));
        }

        //检查登录用户
        if(user ==null){
            sendJson(response,ResultEnum.UNLOGIN_USER.getMsg());
            return false;
        }
        //TODO 判断权限，没有权限，进入没有权限页面
        //TODO 检查token
//        String token = request.getHeader("token");
//        String error = TokenUtil.checkToken(token);
//        if(!Strings.isNullOrEmpty(error)){
//            sendJson(response,error);
//            return false;
//        }
        //LOG.error(request.getRequestURI()+","+flag+","+handler);
        return true;
    }

    private boolean sendJson(HttpServletResponse response,String msg) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            BaseResult res = new BaseResult(false, msg);
            out = response.getWriter();
            out.append(JsonUtil.objectToJson(res).toString());
        } catch (IOException e) {
            //e.printStackTrace();
            //response.sendError(500);
            BaseResult res = new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
            out.append(JsonUtil.objectToJson(res).toString());
            return false;
        }
        return false;
    }
    //
}
