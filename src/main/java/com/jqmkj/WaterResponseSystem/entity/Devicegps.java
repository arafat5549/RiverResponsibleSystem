package com.jqmkj.WaterResponseSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "c_devicegps")
public class Devicegps extends BaseEntity {

    //设备编号,所属表字段为c_devicegps.monitorsite_id
    @Column(name="monitorsite_id")
    private Long monitorsiteId;

    //设备编号,所属表字段为c_devicegps.deviceinfo_id
    @Column(name="deviceinfo_id")
    private Long deviceinfoId;

    //父级id,所属表字段为c_devicegps.parent_id
    @Column(name="parent_id")
    private Long parentId;

    //层级结构,所属表字段为c_devicegps.structure
    @Column(name="structure")
    private String structure;

    //设备所在经度,所属表字段为c_devicegps.longitude
    @Column(name="longitude")
    private String longitude;

    //设备所在维度,所属表字段为c_devicegps.latitude
    @Column(name="latitude")
    private String latitude;

    //设备的ip地址,所属表字段为c_devicegps.ip_address
    @Column(name="ip_address")
    private String ipAddress;

    //设备的端口地址,所属表字段为c_devicegps.port
    @Column(name="port")
    private Integer port;

    //视频流地址,所属表字段为c_devicegps.url
    @Column(name="url")
    private String url;

    //状态,所属表字段为c_devicegps.status
    @Column(name="status")
    private Byte status;

    //是否删除：1是0否,所属表字段为c_devicegps.delete_flag
    @Column(name="delete_flag")
    private Boolean deleteFlag;

    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    @Transient
    private String deviceinfoName;
    @Transient
    private String monitorsiteName;
    @Transient
    private Integer gpsType;

    public String getDeviceinfoName() {
        return deviceinfoName;
    }
    public void setDeviceinfoName(String deviceinfoName) {
        this.deviceinfoName = deviceinfoName;
    }
    public String getMonitorsiteName() {
        return monitorsiteName;
    }
    public void setMonitorsiteName(String monitorsiteName) {
        this.monitorsiteName = monitorsiteName;
    }

    public Integer getGpsType() {
        return gpsType;
    }
    public void setGpsType(Integer gpsType) {
        this.gpsType = gpsType;
    }

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/


    public Long getMonitorsiteId() {
        return monitorsiteId;
    }

    public void setMonitorsiteId(Long monitorsiteId) {
        this.monitorsiteId = monitorsiteId;
    }

    public Long getDeviceinfoId() {
        return deviceinfoId;
    }

    public void setDeviceinfoId(Long deviceinfoId) {
        this.deviceinfoId = deviceinfoId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "Devicegps{" +
                "monitorsiteId=" + monitorsiteId +
                ", deviceinfoId=" + deviceinfoId +
                ", parentId=" + parentId +
                ", structure='" + structure + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", deleteFlag=" + deleteFlag +
                ", deviceinfoName='" + deviceinfoName + '\'' +
                ", monitorsiteName='" + monitorsiteName + '\'' +
                ", gpsType=" + gpsType +
                '}';
    }
}