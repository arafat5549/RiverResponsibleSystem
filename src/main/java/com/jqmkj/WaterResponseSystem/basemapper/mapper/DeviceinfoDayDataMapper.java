package com.jqmkj.WaterResponseSystem.basemapper.mapper;


import com.jqmkj.WaterResponseSystem.basemapper.BaseMapper;
import com.jqmkj.WaterResponseSystem.entity.DeviceinfoDayData;
import com.jqmkj.WaterResponseSystem.entity.other.DeviceinfoDataSearchModel;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheNamespace
public interface DeviceinfoDayDataMapper extends BaseMapper<DeviceinfoDayData> {

    List<DeviceinfoDayData> getStatData(DeviceinfoDataSearchModel s);
    List<DeviceinfoDayData> getStatDataRange(DeviceinfoDataSearchModel s);
    List<DeviceinfoDayData> getQueryData(DeviceinfoDataSearchModel s);
}
