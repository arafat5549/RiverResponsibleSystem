package com.jqmkj.WaterResponseSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "c_region")
public class Region extends BaseEntity {

    //,所属表字段为z_region.name
    @Column(name="name")
    private String name;

    //,所属表字段为z_region.parent_id
    @Column(name="parent_id")
    private Integer parentId;

    //,所属表字段为z_region.longitude
    @Column(name="longitude")
    private String longitude;

    //,所属表字段为z_region.latitude
    @Column(name="latitude")
    private String latitude;

    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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


    public enum EnumColumn {
        id("id"),
        createPerson("create_person"),
        createDate("create_date"),
        updatePerson("update_person"),
        updateDate("update_date"),
        name("name"),
        parentId("parent_id"),
        longitude("longitude"),
        latitude("latitude")
        ;
        private final String column;
        public String value() {return this.column;}
        public String getValue() {return this.column;}
        EnumColumn(String column) {this.column = column;}
    }

}