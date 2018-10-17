package com.jqmkj.WaterResponseSystem.entity;

import com.google.common.base.Strings;
import com.google.common.primitives.Doubles;

import javax.persistence.Transient;
import java.util.List;

public abstract class BaseDataEntity extends  BaseEntity {

    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    @Transient
    private Integer year;
    @Transient
    private Integer quarter;
    @Transient
    private Integer month;
    @Transient
    private Integer week;
    @Transient
    private Integer day;
    @Transient
    private Integer hour;
    @Transient
    private String  monitorsiteName;
    @Transient
    private Long devicegpsId;

    @Transient
    private String sourcepointId;
    @Transient
    private String deviceId;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public String getMonitorsiteName() {
        return monitorsiteName;
    }

    public void setMonitorsiteName(String monitorsiteName) {
        this.monitorsiteName = monitorsiteName;
    }

    public Long getDevicegpsId() {
        return devicegpsId;
    }

    public void setDevicegpsId(Long devicegpsId) {
        this.devicegpsId = devicegpsId;
    }


    public String getSourcepointId() {
        return sourcepointId;
    }

    public void setSourcepointId(String sourcepointId) {
        this.sourcepointId = sourcepointId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public abstract void setDataTime(String dataTime);
}
