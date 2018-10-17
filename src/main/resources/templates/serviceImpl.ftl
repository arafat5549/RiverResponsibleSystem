package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.basemapper.mapper.${entityName}Mapper;
import com.jqmkj.WaterResponseSystem.entity.${entityName};
import com.jqmkj.WaterResponseSystem.service.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${entityName}ServiceImpl extends BaseServiceImpl<${entityName}> implements ${entityName}Service {
    @Autowired
    private ${entityName}Mapper mapper;


    /**
     * 一般只需多表操作代码
     */
}