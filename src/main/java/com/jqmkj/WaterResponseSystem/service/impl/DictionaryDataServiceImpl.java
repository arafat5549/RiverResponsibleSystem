package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.entity.SysDictionaryData;
import com.jqmkj.WaterResponseSystem.service.DictionaryDataService;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.DictionaryDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class DictionaryDataServiceImpl extends BaseServiceImpl<SysDictionaryData> implements DictionaryDataService {

    @Autowired
    private DictionaryDataMapper mapper;


    /**
     * 一般只需多表操作代码
     */

}
