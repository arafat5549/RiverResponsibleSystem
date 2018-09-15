package com.jqm.ssm.entity;

import java.io.Serializable;
import java.util.Date;

public class DeviceinfoPicture implements Serializable {
    //自增主键,所属表字段为c_deviceinfo_picture.id
    private Integer id;

    //图片名称,所属表字段为c_deviceinfo_picture.name
    private String name;


    //商品编号,所属表字段为c_deviceinfo_picture.deviceinfo_id
    private Integer deviceinfoId;

    //图片类型,所属表字段为c_deviceinfo_picture.picture_type
    private Byte pictureType;

    //记录生成人,所属表字段为c_deviceinfo_picture.create_person
    private String createPerson;

    //记录生成时间,所属表字段为c_deviceinfo_picture.create_date
    private Date createDate;

    //更新人,所属表字段为c_deviceinfo_picture.update_person
    private String updatePerson;

    //更新时间,所属表字段为c_deviceinfo_picture.update_date
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeviceinfoId() {
        return deviceinfoId;
    }

    public void setDeviceinfoId(Integer deviceinfoId) {
        this.deviceinfoId = deviceinfoId;
    }

    public Byte getPictureType() {
        return pictureType;
    }

    public void setPictureType(Byte pictureType) {
        this.pictureType = pictureType;
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
        sb.append(", name=").append(name);
        sb.append(", deviceinfoId=").append(deviceinfoId);
        sb.append(", pictureType=").append(pictureType);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createDate=").append(createDate);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id"),
        name("name"),
        deviceinfoId("deviceinfo_id"),
        pictureType("picture_type"),
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