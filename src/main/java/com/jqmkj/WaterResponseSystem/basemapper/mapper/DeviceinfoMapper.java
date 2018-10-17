package com.jqmkj.WaterResponseSystem.basemapper.mapper;

import com.jqmkj.WaterResponseSystem.basemapper.BaseMapper;
import com.jqmkj.WaterResponseSystem.entity.Deviceinfo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheNamespace
public interface DeviceinfoMapper extends BaseMapper<Deviceinfo> {

    /**
     * 一般只需多表操作
     */
    public List<Deviceinfo> findDeviceinfosBySiteIds(@Param("siteIds") List<Long> siteIds);
}
