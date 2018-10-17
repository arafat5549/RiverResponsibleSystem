package com.jqmkj.WaterResponseSystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.DeviceinfoDayDataMapper;
import com.jqmkj.WaterResponseSystem.entity.DeviceinfoDayData;
import com.jqmkj.WaterResponseSystem.entity.other.DeviceinfoDataSearchModel;
import com.jqmkj.WaterResponseSystem.pojo.PageModel;
import com.jqmkj.WaterResponseSystem.service.DeviceinfoDayDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceinfoDayDataServiceImpl extends BaseServiceImpl<DeviceinfoDayData> implements DeviceinfoDayDataService {
    @Autowired
    private DeviceinfoDayDataMapper mapper;

    @Override
    public List<DeviceinfoDayData> getStatData(DeviceinfoDataSearchModel s) {
        return mapper.getStatData(s);
    }

    @Override
    public PageModel<DeviceinfoDayData> getStatDataPage(DeviceinfoDataSearchModel s) {
        return null;
    }

    @Override
    public List<DeviceinfoDayData> getStatDataRange(DeviceinfoDataSearchModel s) {
        return mapper.getStatDataRange(s);
    }

    @Override
    public PageModel<DeviceinfoDayData> getQueryDataPage(DeviceinfoDataSearchModel s) {
        if(s.getRows()!=null && s.getRows()>0)
            PageHelper.startPage(s.getPage(), s.getRows());
        List<DeviceinfoDayData> list =  mapper.getQueryData(s);
        PageInfo<DeviceinfoDayData> pageInfo = new PageInfo<>(list);
        PageModel<DeviceinfoDayData> page = new PageModel<>();
        page.setRecordsTotal(pageInfo.getTotal());
        page.setRecordsFiltered(pageInfo.getTotal());
        page.setData(list);
        return page;
    }


    /**
     * 一般只需多表操作代码
     */
}
