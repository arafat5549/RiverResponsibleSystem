package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.entity.DeviceinfoPicture;
import com.jqmkj.WaterResponseSystem.service.DeviceinfoPictureService;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.DeviceinfoPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class DeviceinfoPictureServiceImpl extends BaseServiceImpl<DeviceinfoPicture> implements DeviceinfoPictureService {

    @Autowired
    private DeviceinfoPictureMapper mapper;


    /**
     * 一般只需多表操作代码
     */

}
