package com.jqm.ssm.util;

import com.jqm.ssm.enums.ResultEnum;

/**
 * Created by wangyaoyao on 2018/8/14.
 */
public class TokenUtil {

    public static String checkToken(String token){
        if(token!=null && !"".equals(token))
            return "";
        return ResultEnum.INVALID_TOKEN.getMsg();
    }
}
