package com.jqmkj.WaterResponseSystem.basemapper.mapper;


import com.jqmkj.WaterResponseSystem.basemapper.BaseMapper;
import com.jqmkj.WaterResponseSystem.entity.DeviceinfodataPollution;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

@Repository
@CacheNamespace
public interface DeviceinfodataPollutionMapper extends BaseMapper<DeviceinfodataPollution> {

}
