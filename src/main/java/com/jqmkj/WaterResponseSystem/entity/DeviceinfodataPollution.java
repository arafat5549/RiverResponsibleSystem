package com.jqmkj.WaterResponseSystem.entity;

import com.google.common.base.Strings;
import com.google.common.primitives.Doubles;
import com.jqmkj.WaterResponseSystem.config.ExcelAttribute;
import com.jqmkj.WaterResponseSystem.util.FormSearchCondition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="d_deviceinfodata_pollution")
public class DeviceinfodataPollution extends BaseEntity  {

    //自增主键,所属表字段为d_deviceinfodata_pollution.id
    //private Long id;

    //名称,所属表字段为d_deviceinfodata_pollution.name
    @ExcelAttribute(name = "名称")
    @Column(name="name")
    @FormSearchCondition(like = "keyword")
    private String name;

    //悬浮物,所属表字段为d_deviceinfodata_pollution.suspension
    @ExcelAttribute(name = "悬浮物")
    @Column(name="suspension")
    @FormSearchCondition(like = "keyword")
    private Float suspension;

    //总汞,所属表字段为d_deviceinfodata_pollution.mercury
    @ExcelAttribute(name = "总汞")
    @Column(name="mercury")
    @FormSearchCondition(like = "keyword")
    private Float mercury;

    //ph值,所属表字段为d_deviceinfodata_pollution.ph
    @ExcelAttribute(name = "ph值")
    @Column(name="ph")
    @FormSearchCondition(like = "keyword")
    private Float ph;

    //总有机碳,所属表字段为d_deviceinfodata_pollution.organic_carbon
    @ExcelAttribute(name = "总有机碳")
    @Column(name="organic_carbon")
    @FormSearchCondition(like = "keyword")
    private Float organicCarbon;

    //总铅,所属表字段为d_deviceinfodata_pollution.lead
    @ExcelAttribute(name = "总铅")
    @Column(name="`lead`")
    @FormSearchCondition(like = "keyword")
    private Float lead;

    //设备编号,所属表字段为d_deviceinfodata_pollution.devicegps_id
    @ExcelAttribute(name = "设备编号")
    @Column(name="devicegps_id")
    @FormSearchCondition(like = "keyword")
    private Long devicegpsId;

    
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


    public  String getPollutionData(int factorId){
        String ret =null;
        switch (factorId){
            case 1: ret = getSuspension().toString(); break;
            case 2: ret = getMercury().toString(); break;
            case 3: ret = getPh().toString(); break;
            case 4: ret = getOrganicCarbon().toString(); break;
            case 5: ret = getLead().toString(); break;

        }
        return ret;
    }
    public String getAllPollutionData(List<DictionaryPollution> dictlist){
        //"悬浮物-"+b.getSuspension()+","+"ph值-"+b.getPh();
        StringBuffer sb = new StringBuffer();
        for(DictionaryPollution d:dictlist){
            String data = getPollutionData(d.getId());
            Double value = Doubles.tryParse(data);
            if(!Strings.isNullOrEmpty(d.getRangeMin()) && value > Doubles.tryParse(d.getRangeMin())){
                data = "<span class=\'color_red\'>"+data+"</span>";
            }
            //System.out.println(d);;
            if(!Strings.isNullOrEmpty(d.getAvaliableMin()) && !Strings.isNullOrEmpty(d.getAvaliableMax()) &&
                    value < Doubles.tryParse(d.getAvaliableMin()) ){
                data = "<span class=\'color_blue\'>"+data+"</span>";
            }
            sb.append(d.getName()+"="+data+",");
        }
        return sb.toString();
    }

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSuspension() {
        return suspension;
    }

    public void setSuspension(Float suspension) {
        this.suspension = suspension;
    }

    public Float getMercury() {
        return mercury;
    }

    public void setMercury(Float mercury) {
        this.mercury = mercury;
    }

    public Float getPh() {
        return ph;
    }

    public void setPh(Float ph) {
        this.ph = ph;
    }

    public Float getOrganicCarbon() {
        return organicCarbon;
    }

    public void setOrganicCarbon(Float organicCarbon) {
        this.organicCarbon = organicCarbon;
    }

    public Float getLead() {
        return lead;
    }

    public void setLead(Float lead) {
        this.lead = lead;
    }

    public Long getDevicegpsId() {
        return devicegpsId;
    }

    public void setDevicegpsId(Long devicegpsId) {
        this.devicegpsId = devicegpsId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", suspension=").append(suspension);
        sb.append(", mercury=").append(mercury);
        sb.append(", ph=").append(ph);
        sb.append(", organicCarbon=").append(organicCarbon);
        sb.append(", lead=").append(lead);
        sb.append(", devicegpsId=").append(devicegpsId);
        sb.append(", year=").append(year+"-"+month+"-"+week+"-"+day);

        sb.append("]");
        return sb.toString();
    }
    /*
    public enum Column {
        id("id"),
        name("name"),
        suspension("suspension"),
        mercury("mercury"),
        ph("ph"),
        organicCarbon("organic_carbon"),
        lead("lead"),
        createDate("create_date"),
        devicegpsId("devicegps_id");

        private final String column;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        Column(String column) {
            this.column = column;
        }

        public String desc() {
            return this.column + " DESC";
        }

        public String asc() {
            return this.column + " ASC";
        }
    }
    */
}