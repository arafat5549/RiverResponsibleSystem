package com.jqm.ssm.util.test;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by wangyaoyao on 2018/8/15.
 */
public class MiscUtil {
    public static String mapToString(Map<Object, Object> map){
        StringBuffer sb = new StringBuffer();
        for (Object key: map.keySet())
            sb.append(key +"-");
        if(sb.toString().length() > 0) sb.deleteCharAt(sb.toString().length() -1);

        return sb.toString();
    }

    public static void main(String[] args) {
        Map<Object,Object> map = Maps.newHashMap();
        map.put("logname","logname");
        map.put("password","password");
        String ret = mapToString(map);
        System.out.println(ret);
    }
}
