package com.jqmkj.WaterResponseSystem.service;

import com.jqmkj.WaterResponseSystem.entity.DeviceinfoDayData;
import com.jqmkj.WaterResponseSystem.entity.DeviceinfoHourData;
import com.jqmkj.WaterResponseSystem.entity.other.DeviceinfoDataSearchModel;

import java.util.List;

public interface DeviceinfoHourDataService extends BaseService<DeviceinfoHourData>{
    List<DeviceinfoHourData> getQueryData(DeviceinfoDataSearchModel s);

}