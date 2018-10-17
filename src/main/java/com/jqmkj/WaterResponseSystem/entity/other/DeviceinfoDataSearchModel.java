package com.jqmkj.WaterResponseSystem.entity.other;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.jqmkj.WaterResponseSystem.entity.BaseDataEntity;
import com.jqmkj.WaterResponseSystem.entity.DeviceinfodataPollution;
import com.jqmkj.WaterResponseSystem.pojo.QueryInfo;
import com.jqmkj.WaterResponseSystem.util.Constants;
import com.jqmkj.WaterResponseSystem.util.DateUtils;

import java.util.List;
import java.util.Map;

public class DeviceinfoDataSearchModel extends QueryInfo {

    private String echartType;    //echartType
    private String dateType;//时间类别
    private String day;
    private String year;
    private String month;
    private String quarter;

    //
    private Long devicegpsId;
    private Long monitorSiteId;
    private String  factor;//监测因子

    private String beginDate;
    private String endDate;

    private List<String> factorList = Lists.newArrayList();

    public String getEchartType() {
        return echartType;
    }

    public void setEchartType(String echartType) {
        this.echartType = echartType;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public Long getDevicegpsId() {
        return devicegpsId;
    }

    public void setDevicegpsId(Long devicegpsId) {
        this.devicegpsId = devicegpsId;
    }

    public Long getMonitorSiteId() {
        return monitorSiteId;
    }

    public void setMonitorSiteId(Long monitorSiteId) {
        this.monitorSiteId = monitorSiteId;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<String> getFactorList() {
        return factorList;
    }

    public void setFactorList(List<String> factorList) {
        this.factorList = factorList;
    }

    //
    public String parseTitleBase(String pre){
        String ret = "";

        if(!Strings.isNullOrEmpty(beginDate)&&!Strings.isNullOrEmpty(endDate)){
            String plus = "["+beginDate+" 至 "+endDate+"]";
            String base = Constants.DateTypeEnum.cnname(dateType)+"数据";
            ret= "["+pre+"]"+base+plus;
            return ret;
        }

        if("hour".equals(dateType)){
            String plus = "-["+getYear()+"年-"+getMonth()+"月-"+getDay()+"]";
            String base = "小时值";
            ret= "["+pre+"]"+base+plus;
        }
        else if("day".equals(getDateType())){
            String plus = "-["+getYear()+"年-"+getMonth()+"月"+"]";
            String base = "日数据";
            ret= "["+pre+"]"+base+plus;
        }
        else if("week".equals(getDateType())){
            String plus = "-["+getYear()+"年"+"]";
            String base  = "周数据";
            ret= "["+pre+"]"+base+plus;
        }
        else if("month".equals(getDateType())){
            String plus = "-["+getYear()+"年"+"]";
            String base = "月数据";
            ret= "["+pre+"]"+base+plus;
        }
        else if("year".equals(getDateType())){
            String plus = "";
            String base = "年数据";
            ret= "["+pre+"]"+base+plus;
        }
        return ret;
    }
    public Integer parseArraySize(int years){
        Integer ret = 0;
        String year  = Constants.DateTypeEnum.year.value();
        String month = Constants.DateTypeEnum.month.value();
        String week  = Constants.DateTypeEnum.week.value();
        String day   = Constants.DateTypeEnum.day.value();
        String hour  = Constants.DateTypeEnum.hour.value();

        if(hour.equals(dateType)){
            ret = DateUtils.getHours();
        }
        else if(day.equals(getDateType())){
            ret = DateUtils.getDays(Ints.tryParse(getYear()),Ints.tryParse(getMonth()));
        }
        else if(week.equals(getDateType())){
            ret = DateUtils.getWeeks(Ints.tryParse(getYear()));
        }
        else if(month.equals(getDateType())){
            ret = DateUtils.getMonths();
        }
        else if(year.equals(getDateType())){
            ret = years;
        }
        return ret;
    }
    public String[] parseAxis(String beginDate,String endDate,String dateType){
        String month = Constants.DateTypeEnum.month.value();
        String day = Constants.DateTypeEnum.day.value();
        String hour = Constants.DateTypeEnum.hour.value();
        String minute = Constants.DateTypeEnum.minute.value();
        String realtime = Constants.DateTypeEnum.realtime.value();

        List<String> ret= Lists.newArrayList();
        int arrSize = 0;
        if(month.equals(dateType)){
            ret = DateUtils.getMonthBetween(beginDate,endDate,getRows());
        }
        else if(day.equals(dateType)){
            ret = DateUtils.getDayBetween(beginDate,endDate,getRows());
        }
        else if(hour.equals(dateType)){
            ret = DateUtils.getHourBetween(beginDate,endDate,getRows());
        }
        else if(minute.equals(dateType)){
            ret = DateUtils.getMinuteBetween(beginDate,endDate,getRows());
        }
        arrSize = ret.size();

        return (String[])ret.toArray(new String[arrSize]);
    }
    public Integer parseIndex(String beginDate,String endDate,BaseDataEntity d){
        int ret = -1;
        List<String> retlist= DateUtils.getMonthBetween(beginDate,endDate,0);
        for(int i=0;i<retlist.size();i++){
            String monthStr = d.getMonth() <10 ? "0"+d.getMonth() : d.getMonth().toString();
            if(retlist.get(i).equals(d.getYear()+"-"+monthStr)){
                return i;
            }
        }
        return ret;
    }


    public String[] parseAxis(Map<Integer,Integer> arrMaps){
        String year  = Constants.DateTypeEnum.year.value();
        String month = Constants.DateTypeEnum.month.value();
        String week  = Constants.DateTypeEnum.week.value();
        String day   = Constants.DateTypeEnum.day.value();
        String hour  = Constants.DateTypeEnum.hour.value();

        int arrSize = parseArraySize(arrMaps.keySet().size());
        String axis[]= new String[arrSize];
        if(hour.equals(dateType)){
            for(int i=0;i<arrSize;i++)
                axis[i] = i+"时";
        }
        else if(day.equals(getDateType())){
            for(int i=0;i<arrSize;i++)
                axis[i] = (i+1)+"天";
        }
        else if(week.equals(getDateType())){
            for(int i=0;i<arrSize;i++)
                axis[i] = (i+1)+"周";
        }
        else if(month.equals(getDateType())){
            for(int i=0;i<arrSize;i++)
                axis[i] = (i+1)+"月";
        }
        else if(year.equals(getDateType())){
            for(Integer y:arrMaps.keySet())
                axis[arrMaps.get(y)] = y+"年";
        }
        return axis;
    }
    //
    public Integer parseIndex(Map<Integer,Integer> arrMaps, BaseDataEntity d){
        String year  = Constants.DateTypeEnum.year.value();
        String month = Constants.DateTypeEnum.month.value();
        String week  = Constants.DateTypeEnum.week.value();
        String day   = Constants.DateTypeEnum.day.value();
        String hour  = Constants.DateTypeEnum.hour.value();

        int ret = -1;
        if(hour.equals(dateType)){
            ret = d.getHour();
        }
        else if(day.equals(getDateType())){
            ret = d.getDay()-1;
        }
        else if(week.equals(getDateType())){
            ret = d.getWeek();
        }
        else if(month.equals(getDateType())){
            ret = d.getMonth()-1;
        }
        else if(year.equals(getDateType())){
            ret = arrMaps.get(d.getYear());
        }
        return ret;
    }


    @Override
    public String toString() {
        return "DeviceinfoDataSearchModel{" +
                "Query=" +super.toString()+
                "echartType='" + echartType + '\'' +
                ", dateType='" + dateType + '\'' +
                ", day='" + day + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", quarter='" + quarter + '\'' +
                ", devicegpsId=" + devicegpsId +
                ", monitorSiteId=" + monitorSiteId +
                ", factor='" + factor + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", factorList=" + factorList +
                '}';
    }
}
