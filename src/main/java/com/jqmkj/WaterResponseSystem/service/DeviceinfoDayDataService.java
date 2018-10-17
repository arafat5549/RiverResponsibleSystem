package com.jqmkj.WaterResponseSystem.service;

import com.jqmkj.WaterResponseSystem.entity.DeviceinfoDayData;
import com.jqmkj.WaterResponseSystem.entity.other.DeviceinfoDataSearchModel;
import com.jqmkj.WaterResponseSystem.pojo.PageModel;

import java.util.List;

public interface DeviceinfoDayDataService extends BaseService<DeviceinfoDayData>{
    List<DeviceinfoDayData> getStatData(DeviceinfoDataSearchModel s);
    PageModel<DeviceinfoDayData> getStatDataPage(DeviceinfoDataSearchModel s);
    List<DeviceinfoDayData> getStatDataRange(DeviceinfoDataSearchModel s);
    PageModel<DeviceinfoDayData> getQueryDataPage(DeviceinfoDataSearchModel s);
    //String getReportData(DeviceinfoDataSearchModel searchModel);
}
