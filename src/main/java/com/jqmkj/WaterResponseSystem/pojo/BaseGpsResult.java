package com.jqmkj.WaterResponseSystem.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangyaoyao on 2018/8/31.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseGpsResult<T> implements Serializable {
    private String type;
    List<GpsFeature<T>> features = Lists.newArrayList();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GpsFeature<T>> getFeatures() {
        return features;
    }

    public void setFeatures(List<GpsFeature<T>> features) {
        this.features = features;
    }

    public BaseGpsResult(List<GpsFeature<T>> features) {
        this.type = "FeatureCollection";
        this.features = features;
    }
}
