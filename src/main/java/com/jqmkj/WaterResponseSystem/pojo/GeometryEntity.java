package com.jqmkj.WaterResponseSystem.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

/**
 * Created by wangyaoyao on 2018/8/31.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeometryEntity {
    String type;
    Float coordinates[] = new Float[2];

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Float[] coordinates) {
        this.coordinates = coordinates;
    }

    public GeometryEntity(Float[] coordinates){
        this.type = "Point";
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "GeometryEntity{" +
                "type='" + type + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
