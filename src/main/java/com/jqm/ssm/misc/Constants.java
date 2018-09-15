package com.jqm.ssm.misc;

import com.jqm.ssm.util.SessionUtil;

/**
 * Created by wangyaoyao on 2018/8/14.
 */
public class Constants {
    public static boolean Debug(){return true;}
    public static final String SYS_DATABASE = "mysql";


    //###############################################################################################################//
    //                                      KEY索引字段区域
    //###############################################################################################################//
    /**   完整的redis缓存的key   **/
    public static final String CACHE_KEY_CURRENT_LOGINUSER = SessionUtil.SessionSystemLoginUserName;  //当前登录用户
    public static final String CACHE_KEY_ROLE_LIST          = "rolelist";
    public static final String CACHE_KEY_PERMISSION_LIST   = "permissionlist";

    /**   拼接的redis缓存的key   **/
    public static final String CACHE_KEY_LOGINNAME_ = "logname#";

    public static final String CACHE_KEY_LISTBYMAP_ALL_ = "all";
//    /**
//     * 获取缓存key
//     */
//    public static String getCachekey(String value){
//        return CACHE_KEY_LOGINNAME + value;
//    }


    public static  final Integer ECHART_MONTHS   = 0;
    public static  final Integer ECHART_YEARS    = 1;
    public static  final Integer ECHART_DAYS     = 2;
    //###############################################################################################################//
    //                                       私有字段区域
    //###############################################################################################################//
    public static final String Fail = "fail";
    public static final String Success = "success";
    public static final String Exists = "exists";
    public static final String DefaultMd5Password ="63a9f0ea7bb98050796b649e85481845";//123456
    public static final String EmptyJsonObject = "{}";
    public static final int DEFALUT_LIMIT = 20; //默认每页显示50条
    public static final String ENCODE_UTF8 = "UTF-8";
    public static final String HEADER_CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
}
