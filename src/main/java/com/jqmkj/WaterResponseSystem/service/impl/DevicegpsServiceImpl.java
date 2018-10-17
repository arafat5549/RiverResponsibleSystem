package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.basemapper.mapper.DevicegpsMapper;
import com.jqmkj.WaterResponseSystem.entity.Devicegps;
import com.jqmkj.WaterResponseSystem.service.DevicegpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevicegpsServiceImpl extends BaseServiceImpl<Devicegps> implements DevicegpsService {

    @Autowired
    private DevicegpsMapper mapper;

    @Override
    public List<Devicegps> findDevicesByType(Integer type) {
        return mapper.findDevicesByType(type);
    }

    @Override
    public List<Devicegps> findDevicesByArea(Integer area) {
        return mapper.findDevicesByArea(area);
    }
}
