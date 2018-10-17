package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.entity.SysDepartment;
import com.jqmkj.WaterResponseSystem.service.DepartmentService;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class DepartmentServiceImpl extends BaseServiceImpl<SysDepartment> implements DepartmentService {

    @Autowired
    private DepartmentMapper mapper;


    /**
     * 一般只需多表操作代码
     */

}
