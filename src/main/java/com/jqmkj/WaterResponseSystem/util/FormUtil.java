package com.jqmkj.WaterResponseSystem.util;


import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class FormUtil {

    public static Map<String, Object> getFormForMap(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (request != null) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (String key : parameterMap.keySet()) {
                String[] value = parameterMap.get(key);
                if (value.length > 0) {
                    if (key.endsWith("[]")) {
                        result.put(key.substring(0, key.length() - 2), value);
                    } else if (StringUtils.hasText(value[0])) {
                        result.put(key, value[0].trim());
                    }
                }
            }
        }
        return result;
    }

    public static JSONObject getFormForJson(HttpServletRequest request){
        return JSONObject.parseObject(getFormForMap(request)+"");
    }

    public static <T> T getFormForBean(HttpServletRequest request,Class<T> clazz){
        return JSONObject.parseObject(getFormForMap(request)+"",clazz);
    }

}
