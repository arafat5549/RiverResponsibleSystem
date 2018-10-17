package com.jqmkj.WaterResponseSystem.iot.message;

import com.alibaba.fastjson.JSONObject;
import com.jqmkj.WaterResponseSystem.util.Constants;

//设备传过来的数据
public class WaterDeviceData implements  IBaseDeviceData {
    private String deviceId;
    private String server;
    private Integer port;
    private String message;
    //传输指定的监测因子
    private JSONObject factorStyle;
    //传输全部监测因子
    private boolean fullFactor;
    //
    private String code;
    private Integer dataType;
    public WaterDeviceData(){
        this.code =  Constants.DeviceDataTypeEnum.WATER_NA212.code();
        this.dataType   = Constants.DeviceDataTypeEnum.WATER_NA212.dataType();
    }
    public WaterDeviceData(String server, Integer port) {
        this();
        this.server = server;
        this.port = port;
    }

    @Override
    public String getServer() {
        return server;
    }

    @Override
    public Integer getPort() {
        return port;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Integer getDataType() {
        return dataType;
    }

    @Override
    public JSONObject getFactorStyle() {
        return factorStyle;
    }

    @Override
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    @Override
    public void setFactorStyle(JSONObject jo) {
        factorStyle = jo;
    }

    public boolean isFullFactor() {
        return fullFactor;
    }

    public void setFullFactor(boolean fullFactor) {
        this.fullFactor = fullFactor;
    }
}
