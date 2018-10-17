package com.jqmkj.WaterResponseSystem.service;

import com.jqmkj.WaterResponseSystem.entity.Monitorsite;
import com.jqmkj.WaterResponseSystem.pojo.MonitorSiteTree;

import java.util.List;

public interface MonitorsiteService extends BaseService<Monitorsite>{

    List<MonitorSiteTree> getAllMonitorsiteTree();
    //List<Monitorsite> getMonitorsiteListForOption();
}
