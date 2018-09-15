package com.jqm.ssm.entity;

import java.io.Serializable;
import java.util.Date;

public class DeviceinfoData implements Serializable {
    //自增主键,所属表字段为d_deviceinfo_data.id
    private Integer id;

    //设备编号,所属表字段为d_deviceinfo_data.devicegps_id
    private Integer devicegpsId;

    //传输过来的数据,所属表字段为d_deviceinfo_data.data
    private String data;

    //数据类型用于不同解析 比如a#b;c等不同的数据有不同的分割类型,所属表字段为d_deviceinfo_data.data_type_id
    private Byte dataTypeId;

    //记录生成人,所属表字段为d_deviceinfo_data.create_person
    private String createPerson;

    //记录生成时间,所属表字段为d_deviceinfo_data.create_date
    private Date createDate;

    private static final long serialVersionUID = 1L;

    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDevicegpsId() {
        return devicegpsId;
    }

    public void setDevicegpsId(Integer devicegpsId) {
        this.devicegpsId = devicegpsId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Byte getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Byte dataTypeId) {
        this.dataTypeId = dataTypeId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", devicegpsId=").append(devicegpsId);
        sb.append(", data=").append(data);
        sb.append(", dataTypeId=").append(dataTypeId);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createDate=").append(createDate);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id"),
        devicegpsId("devicegps_id"),
        data("data"),
        dataTypeId("data_type_id"),
        createPerson("create_person"),
        createDate("create_date");

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