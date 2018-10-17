package com.jqmkj.WaterResponseSystem.service;

import com.jqmkj.WaterResponseSystem.entity.Devicegps;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DevicegpsService extends BaseService<Devicegps>{
    List<Devicegps> findDevicesByType(Integer type);
    List<Devicegps> findDevicesByArea(Integer area);
}
