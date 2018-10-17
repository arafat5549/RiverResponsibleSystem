package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.entity.SysDictionaryType;
import com.jqmkj.WaterResponseSystem.service.DictionaryTypeService;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.DictionaryTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class DictionaryTypeServiceImpl extends BaseServiceImpl<SysDictionaryType> implements DictionaryTypeService {

    @Autowired
    private DictionaryTypeMapper mapper;


    /**
     * 一般只需多表操作代码
     */

}
