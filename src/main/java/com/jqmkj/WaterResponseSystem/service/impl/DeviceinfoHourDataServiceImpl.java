package com.jqmkj.WaterResponseSystem.service.impl;	
	
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.DeviceinfoHourDataMapper;
import com.jqmkj.WaterResponseSystem.entity.DeviceinfoDayData;
import com.jqmkj.WaterResponseSystem.entity.DeviceinfoHourData;
import com.jqmkj.WaterResponseSystem.entity.other.DeviceinfoDataSearchModel;
import com.jqmkj.WaterResponseSystem.service.DeviceinfoHourDataService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;	
	
import java.util.List;	
	
@Service	
public class DeviceinfoHourDataServiceImpl extends BaseServiceImpl<DeviceinfoHourData> implements DeviceinfoHourDataService {	
    @Autowired	
    private DeviceinfoHourDataMapper mapper;

    @Override
    public List<DeviceinfoHourData> getQueryData(DeviceinfoDataSearchModel s) {
        PageHelper.startPage(s.getPage(), s.getRows());
        List<DeviceinfoHourData> list =  mapper.getQueryData(s);
        PageInfo<DeviceinfoHourData> pageInfo = new PageInfo<DeviceinfoHourData>(list);
        System.out.println(pageInfo);
        return list;
    }


    /**	
     * 一般只需多表操作代码	
     */	

}