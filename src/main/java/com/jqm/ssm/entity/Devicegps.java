package com.jqm.ssm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Devicegps implements Serializable {
    //自增主键,所属表字段为c_devicegps.id
    private Integer id;

    //设备编号,所属表字段为c_devicegps.monitorsite_id
    private Integer monitorsiteId;

    //设备编号,所属表字段为c_devicegps.deviceinfo_id
    private Integer deviceinfoId;

    //父级id,所属表字段为c_devicegps.parent_id
    private Integer parentId;

    //层级结构,所属表字段为c_devicegps.structure
    private String structure;

    //设备所在经度,所属表字段为c_devicegps.longitude
    private String longitude;

    //设备所在维度,所属表字段为c_devicegps.latitude
    private String latitude;

    //设备的ip地址,所属表字段为c_devicegps.ip_address
    private String ipAddress;

    //设备的端口地址,所属表字段为c_devicegps.port
    private Integer port;

    //视频流地址,所属表字段为c_devicegps.url
    private String url;

    //状态,所属表字段为c_devicegps.status
    private Byte status;

    //是否删除：1是0否,所属表字段为c_devicegps.delete_flag
    @JsonIgnore
    private Boolean deleteFlag;

    //记录生成人,所属表字段为c_devicegps.create_person
    @JsonIgnore
    private String createPerson;

    //记录生成时间,所属表字段为c_devicegps.create_date
    @JsonIgnore
    private Date createDate;

    //更新人,所属表字段为c_devicegps.update_person
    @JsonIgnore
    private String updatePerson;

    //更新时间,所属表字段为c_devicegps.update_date
    @JsonIgnore
    private Date updateDate;

    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    private String deviceinfoName;
    private String monitorsiteName;
    private Integer categoryId;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonitorsiteId() {
        return monitorsiteId;
    }

    public void setMonitorsiteId(Integer monitorsiteId) {
        this.monitorsiteId = monitorsiteId;
    }

    public Integer getDeviceinfoId() {
        return deviceinfoId;
    }

    public void setDeviceinfoId(Integer deviceinfoId) {
        this.deviceinfoId = deviceinfoId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
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

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", monitorsiteId=").append(monitorsiteId);
        sb.append(", deviceinfoId=").append(deviceinfoId);
        sb.append(", parentId=").append(parentId);
        sb.append(", structure=").append(structure);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", port=").append(port);
        sb.append(", url=").append(url);
        sb.append(", status=").append(status);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createDate=").append(createDate);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id"),
        monitorsiteId("monitorsite_id"),
        deviceinfoId("deviceinfo_id"),
        parentId("parent_id"),
        structure("structure"),
        longitude("longitude"),
        latitude("latitude"),
        ipAddress("ip_address"),
        port("port"),
        url("url"),
        status("status"),
        deleteFlag("delete_flag"),
        createPerson("create_person"),
        createDate("create_date"),
        updatePerson("update_person"),
        updateDate("update_date");

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