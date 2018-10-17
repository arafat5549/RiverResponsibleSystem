package com.jqmkj.WaterResponseSystem.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jqmkj.WaterResponseSystem.util.ReflectionUtil;

/**
 * Created by wangyaoyao on 2018/8/31.
 * 往前台返回地图数据
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GpsFeature<T>  {
   // private static final long serialVersionUID = -4185151304730685014L;

     String type;
     GeometryEntity geometry;
     T properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeometryEntity getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryEntity geometry) {
        this.geometry = geometry;
    }

    public T getProperties() {
        return properties;
    }

    public void setProperties(T properties) {
        this.properties = properties;
    }

    //public GpsFeature(){}
    public GpsFeature(T data) {
        this.type = "Feature";
        String longitude = (String)ReflectionUtil.getFieldValue(data,"longitude");
        String latitude = (String) ReflectionUtil.getFieldValue(data,"latitude");
        Float coordinates[] = new Float[2];
        coordinates[0] = Float.parseFloat(longitude);
        coordinates[1] = Float.parseFloat(latitude);
        this.geometry = new GeometryEntity(coordinates);
        this.properties = data;
    }


    @Override
    public String toString() {
        return "GpsFeature{" +
                "type='" + type + '\'' +
                ", geometry=" + geometry.toString() +
                ", data=" + properties +
                '}';
    }
    //    {"type":"FeatureCollection","features":
//            [
//        {"type":"Feature","geometry":{"type":"Point","coordinates":[117.04944444,26.54944444]},"properties":{"id":1,"imgUrl":"",
//                "name":"\u6c34\u53e3\u6c34\u7535\u7ad9","regCode":"3504211001","idOrganization":191,
//                "organization":"\u76d6\u6d0b\u9547","idStationType":1,"stationType":"\u575d\u540e\u5f0f",
//                "createTime":"1990\u5e749\u6708","volume":"5000","minflow":"0",
//                "unit":"\u798f\u5efa\u5b8f\u6e90\u7535\u4e1a\u80a1\u4efd\u6709\u9650\u516c\u53f8"}},
//
//        {"type":"Feature","geometry":{"type":"Point","coordinates":[117.4,26.37777778]},"properties":{"id":2,"imgUrl":"","name":"\u6893\u53e3\u574a\u6c34\u7535\u7ad9","regCode":"3504211002","idOrganization":199,"organization":"\u6c99\u6eaa\u4e61","idStationType":2,"stationType":"\u5f15\u6c34\u5f0f","createTime":"1973\u5e748\u6708","volume":"1890","minflow":"0.33","unit":"\u798f\u5efa\u5b8f\u6e90\u7535\u4e1a\u80a1\u4efd\u6709\u9650\u516c\u53f8"}},
//        {"type":"Feature","geometry":{"type":"Point","coordinates":[117.25,26.15972222]},"properties":{"id":3,"imgUrl":"","name":"\u5927\u6eaa\u5cf0\u4e00\u7ea7\u7ad9","regCode":"3504211003","idOrganization":101,"organization":"\u534e\u5174\u9547","idStationType":1,"stationType":"\u575d\u540e\u5f0f","createTime":"1984\u5e741\u6708","volume":"800","minflow":"0","unit":"\u798f\u5efa\u5b8f\u6e90\u7535\u4e1a\u80a1\u4efd\u6709\u9650\u516c\u53f8"}},
//        {"type":"Feature","geometry":{"type":"Point","coordinates":[117.26666667,26.16111111]},"properties":{"id":4,"imgUrl":"","name":"\u5927\u6eaa\u5cf0\u4e8c\u7ea7\u7ad9","regCode":"3504211004","idOrganization":196,"organization":"\u80e1\u574a\u9547","idStationType":2,"stationType":"\u5f15\u6c34\u5f0f","createTime":"1983\u5e743\u6708","volume":"3000","minflow":"0.36","unit":"\u798f\u5efa\u5b8f\u6e90\u7535\u4e1a\u80a1\u4efd\u6709\u9650\u516c\u53f8"}},
//        {"type":"Feature","geometry":{"type":"Point","coordinates":[116.99583333,26.53277778]},"properties":{"id":5,"imgUrl":"","name":"\u8499\u5dde\u6c34\u7535\u7ad9","regCode":"3504211005","idOrganization":198,"organization":"\u590f\u574a\u4e61","idStationType":1,"stationType":"\u575d\u540e\u5f0f","createTime":"1997\u5e748\u6708","volume":"5000","minflow":"0.62","unit":"\u8499\u5dde\u6c34\u7535\u5f00\u53d1\u6709\u9650\u516c\u53f8"}},
//        {"type":"Feature","geometry":{"type":"Point","coordinates":[117.37083333,26.40722222]},"properties":{"id":6,"imgUrl":"","name":"\u9648\u574a\u6c34\u7535\u7ad9","regCode":"3504211006","idOrganization":192,"organization":"\u590f\u9633\u4e61","idStationType":2,"stationType":"\u5f15\u6c34\u5f0f","createTime":"2003\u5e746\u6708","volume":"1000","minflow":"0.2","unit":"\u798f\u5efa\u7701\u6c38\u6995\u7535\u529b\u96c6\u56e2"}},
//        {"type":"Feature","geometry":{"type":"Point","coordinates":[117.31111111,26.3375]},"properties":{"id":7,"imgUrl":"","name":"\u6e14\u5858\u6eaa\u4e00\u7ea7\u7ad9","regCode":"3504211007","idOrganization":199,"organization":"\u6c99\u6eaa\u4e61","idStationType":2,"stationType":"\u5f15\u6c34\u5f0f","createTime":"2002\u5e743\u6708","volume":"1890","minflow":"0.63","unit":"\u798f\u5efa\u7701\u6c38\u6995\u7535\u529b\u96c6\u56e2"}},
//        {"type":"Feature","geometry":{"type":"Point","coordinates":[117.31611111,26.33888889]},"properties":{"id":8,"imgUrl":"","name":"\u6e14\u5858\u6eaa\u4e8c\u7ea7\u7ad9","regCode":"3504211008","idOrganization":199,"organization":"\u6c99\u6eaa\u4e61","idStationType":2,"stationType":"\u5f15\u6c34\u5f0f","createTime":"2002\u5e746\u6708","volume":"1200","minflow":"0.89","unit":"\u798f\u5efa\u7701\u6c38\u6995\u7535\u529b\u96c6\u56e2"}},
//        {"type":"Feature","geometry":{"type":"Point","coordinates":[117.33333333,26.34166667]},"properties":{"id":9,"imgUrl":"","name":"\u6e14\u5858\u6eaa\u4e09\u7ea7\u7ad9","regCode":"3504211009","idOrganization":199,"organization":"\u6c99\u6eaa\u4e61","idStationType":2,"stationType":"\u5f15\u6c34\u5f0f","createTime":"2002\u5e748\u6708","volume":"1200","minflow":"0.86","unit":"\u798f\u5efa\u7701\u6c38\u6995\u7535\u529b\u96c6\u56e2"}},
//        {"type":"Feature","geometry":{"type":"Point","coordinates":[116.98944444,26.58333333]},"properties":{"id":10,"imgUrl":"","name":"\u9645\u6d1e\u4e0a\u6c34\u7535\u7ad9","regCode":"3504211010","idOrganization":191,"organization":"\u76d6\u6d0b\u9547","idStationType":2,"stationType":"\u5f15\u6c34\u5f0f","createTime":"1986\u5e749\u6708","volume":"150","minflow":"0.02","unit":"\u660e\u6eaa\u53bf\u5b8f\u76db\u6c34\u7535\u5de5\u7a0b\u6709\u9650\u516c\u53f8"}}]}
}
