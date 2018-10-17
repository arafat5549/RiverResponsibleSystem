package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.basemapper.mapper.DeviceInfoRealTimeDataMapper;
import com.jqmkj.WaterResponseSystem.entity.DeviceInfoRealTimeData;
import com.jqmkj.WaterResponseSystem.service.DeviceInfoRealTimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceInfoRealTimeDataServiceImpl extends BaseServiceImpl<DeviceInfoRealTimeData> implements DeviceInfoRealTimeDataService {
    @Autowired
    private DeviceInfoRealTimeDataMapper mapper;


    /**
     * 一般只需多表操作代码
     */
}