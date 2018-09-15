package com.jqm.ssm.misc.es;

import com.jqm.ssm.util.ReflectionUtil;

/**
 * Created by Administrator on 2018/7/27.
 * 测试传感器数据
 */
public class TestTrackingData {

    public static void main(String[] args) {
        String rawdata = "deviceSN:1123,latitude:123.333";
        TestTrackingData data =  ReflectionUtil.parseTackingDate(rawdata,TestTrackingData.class);
        System.out.println(data);

    }


    public String deviceSN = null;
    public String latitude = null;
    public String longitude = null;
    public String temperature = null;
    public String humidity = null;
    public String timestamp = null;


    public String getDeviceSN() {
        return deviceSN;
    }

    public void setDeviceSN(String deviceSN) {
        this.deviceSN = deviceSN;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "TestTrackingData{" +
                "deviceSN='" + deviceSN + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
