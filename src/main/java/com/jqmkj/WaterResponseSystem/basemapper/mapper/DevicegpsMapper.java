package com.jqmkj.WaterResponseSystem.basemapper.mapper;

import com.jqmkj.WaterResponseSystem.basemapper.BaseMapper;
import com.jqmkj.WaterResponseSystem.entity.Devicegps;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@CacheNamespace
public interface DevicegpsMapper extends BaseMapper<Devicegps> {
    List<Devicegps> findDevicesByType(Integer type);
    List<Devicegps> findDevicesByArea(Integer area);
}
