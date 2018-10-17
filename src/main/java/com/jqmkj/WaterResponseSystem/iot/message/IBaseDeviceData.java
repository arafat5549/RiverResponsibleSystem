package com.jqmkj.WaterResponseSystem.iot.message;

import com.alibaba.fastjson.JSONObject;

//
public interface IBaseDeviceData {

    String  getServer();
    Integer  getPort();
    String  getDeviceId();
    String  getMessage();
    String  getCode();
    Integer getDataType();
    JSONObject getFactorStyle();
    boolean isFullFactor();


    void setDeviceId(String deviceId);
    void setFactorStyle(JSONObject jo);
    void setFullFactor(boolean b);

    //String generateMessage();//生成随机数据
}
