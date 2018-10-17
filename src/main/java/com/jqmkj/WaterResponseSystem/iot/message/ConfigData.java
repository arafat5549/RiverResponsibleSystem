package com.jqmkj.WaterResponseSystem.iot.message;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.List;

//模拟程序配置文件（暂时无效）
public class ConfigData {
    private String title;
    private List<String> deviceIds = Lists.newArrayList();
    //private List<String> factorList = Lists.newArrayList();
    private Integer cycle;
    private Integer autoFullFactor = 1;
    private JSONObject factorStyle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }

//    public List<String> getFactorList() {
//        return factorList;
//    }
//
//    public void setFactorList(List<String> factorList) {
//        this.factorList = factorList;
//    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getAutoFullFactor() {
        return autoFullFactor;
    }

    public void setAutoFullFactor(Integer autoFullFactor) {
        this.autoFullFactor = autoFullFactor;
    }

    public JSONObject getFactorStyle() {
        return factorStyle;
    }

    public void setFactorStyle(JSONObject factorStyle) {
        this.factorStyle = factorStyle;
    }

    @Override
    public String toString() {
        return "ConfigData{" +
                "title='" + title + '\'' +
                ", deviceIds=" + deviceIds +
                ", factorList=" + factorStyle +
                ", cycle=" + cycle +
                ", autoFullFactor=" + autoFullFactor +
                '}';
    }
}
