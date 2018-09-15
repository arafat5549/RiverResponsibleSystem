package com.jqm.ssm.entity;

import java.io.Serializable;
import java.util.Date;

public class DeviceinfodataPollution implements Serializable {
    //自增主键,所属表字段为d_deviceinfodata_pollution.id
    private Long id;

    //名称,所属表字段为d_deviceinfodata_pollution.name
    private String name;

    //悬浮物,所属表字段为d_deviceinfodata_pollution.suspension
    private Float suspension;

    //总汞,所属表字段为d_deviceinfodata_pollution.mercury
    private Float mercury;

    //ph值,所属表字段为d_deviceinfodata_pollution.ph
    private Float ph;

    //总有机碳,所属表字段为d_deviceinfodata_pollution.organic_carbon
    private Float organicCarbon;

    //总铅,所属表字段为d_deviceinfodata_pollution.lead
    private Float lead;

    //记录生成时间,所属表字段为d_deviceinfodata_pollution.create_date
    private Date createDate;

    //设备编号,所属表字段为d_deviceinfodata_pollution.devicegps_id
    private Integer devicegpsId;

    private static final long serialVersionUID = 1L;

    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    private Integer year;
    private Integer month;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getDevicegpsId() {
        return devicegpsId;
    }

    public void setDevicegpsId(Integer devicegpsId) {
        this.devicegpsId = devicegpsId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", suspension=").append(suspension);
        sb.append(", mercury=").append(mercury);
        sb.append(", ph=").append(ph);
        sb.append(", organicCarbon=").append(organicCarbon);
        sb.append(", lead=").append(lead);
        sb.append(", createDate=").append(createDate);
        sb.append(", devicegpsId=").append(devicegpsId);
        sb.append("]");
        return sb.toString();
    }

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
}