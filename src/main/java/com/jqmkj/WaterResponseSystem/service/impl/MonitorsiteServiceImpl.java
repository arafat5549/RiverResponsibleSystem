package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.entity.Monitorsite;
import com.jqmkj.WaterResponseSystem.pojo.MonitorSiteTree;
import com.jqmkj.WaterResponseSystem.service.MonitorsiteService;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.MonitorsiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public  class MonitorsiteServiceImpl extends BaseServiceImpl<Monitorsite> implements MonitorsiteService {

    @Autowired
    private MonitorsiteMapper mapper;


    /**
     * 一般只需多表操作代码
     */

    @Override
    public  List<MonitorSiteTree> getAllMonitorsiteTree(){
        return mapper.getRootMonitorSiteTree();
    }


}
