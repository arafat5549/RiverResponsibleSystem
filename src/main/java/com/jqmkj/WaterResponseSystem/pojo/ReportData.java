package com.jqmkj.WaterResponseSystem.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

//报表
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportData implements Serializable {
    private String monitorsiteName;//站点名称
    private String data1;//小时值
    private String data2;//各监测因子小时均值
    private String data3;//日均值
    private String factorWaterType;//各单项指标对应的水质类别
    private String waterType; //水质类别
    private String topFactor;//首要污染物指标

    public String getMonitorsiteName() {
        return monitorsiteName;
    }

    public void setMonitorsiteName(String monitorsiteName) {
        this.monitorsiteName = monitorsiteName;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

    public String getFactorWaterType() {
        return factorWaterType;
    }

    public void setFactorWaterType(String factorWaterType) {
        this.factorWaterType = factorWaterType;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public String getTopFactor() {
        return topFactor;
    }

    public void setTopFactor(String topFactor) {
        this.topFactor = topFactor;
    }
}
