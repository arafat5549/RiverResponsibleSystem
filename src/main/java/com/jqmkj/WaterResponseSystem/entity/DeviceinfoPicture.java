package com.jqmkj.WaterResponseSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="c_deviceinfo_picture")
public class DeviceinfoPicture extends BaseEntity {

    //图片名称,所属表字段为c_deviceinfo_picture.name
    @Column(name="name")
    private String name;

    //商品编号,所属表字段为c_deviceinfo_picture.deviceinfo_id
    @Column(name="deviceinfo_id")
    private Long deviceinfoId;

    //图片类型,所属表字段为c_deviceinfo_picture.picture_type
    @Column(name="picture_type")
    private Byte pictureType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDeviceinfoId() {
        return deviceinfoId;
    }

    public void setDeviceinfoId(Long deviceinfoId) {
        this.deviceinfoId = deviceinfoId;
    }

    public Byte getPictureType() {
        return pictureType;
    }

    public void setPictureType(Byte pictureType) {
        this.pictureType = pictureType;
    }

}
