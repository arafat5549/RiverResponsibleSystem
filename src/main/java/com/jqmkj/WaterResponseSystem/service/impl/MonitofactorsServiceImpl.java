package com.jqmkj.WaterResponseSystem.service.impl;

import com.google.common.collect.Lists;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.DeviceinfoMapper;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.MonitorsiteMapper;
import com.jqmkj.WaterResponseSystem.entity.Deviceinfo;
import com.jqmkj.WaterResponseSystem.entity.Monitofactors;
import com.jqmkj.WaterResponseSystem.service.MonitofactorsService;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.MonitofactorsMapper;
import com.jqmkj.WaterResponseSystem.service.MonitorsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class MonitofactorsServiceImpl extends BaseServiceImpl<Monitofactors> implements MonitofactorsService {

    @Autowired
    private MonitofactorsMapper mapper;
    //@Autowired
    //private MonitorsiteMapper monitorsiteMapper;
    @Autowired
    private DeviceinfoMapper deviceinfoMapper;
    /**
     * 一般只需多表操作代码
     */
    @Override
    public List<Monitofactors> findFactorsBySiteId(Long siteId) {
        if(siteId==null || siteId<=0){
            return mapper.selectAll();
        }
        List<Deviceinfo>  dlist =  deviceinfoMapper.findDeviceinfosBySiteIds(Lists.newArrayList(siteId));
        List<Long> deviceInfoIds = Lists.newArrayList();
        for(Deviceinfo d:dlist){
            deviceInfoIds.add(d.getId());
        }
        return findFactorsByDeviceInfoIds(deviceInfoIds);
    }

    @Override
    public List<Monitofactors> findFactorsByDeviceInfoIds(List<Long> deviceInfoIds) {
        if(deviceInfoIds==null || deviceInfoIds.size()<=0){
            return mapper.selectAll();
        }
        return mapper.findFactorsByDeviceInfoIds(deviceInfoIds);
    }




}
