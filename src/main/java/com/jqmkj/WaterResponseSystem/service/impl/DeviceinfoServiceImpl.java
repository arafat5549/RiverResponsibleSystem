package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.entity.Deviceinfo;
import com.jqmkj.WaterResponseSystem.service.DeviceinfoService;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.DeviceinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class DeviceinfoServiceImpl extends BaseServiceImpl<Deviceinfo> implements DeviceinfoService {

    @Autowired
    private DeviceinfoMapper mapper;


    /**
     * 一般只需多表操作代码
     */

}
