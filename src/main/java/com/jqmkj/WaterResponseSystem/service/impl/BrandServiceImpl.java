package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.entity.Brand;
import com.jqmkj.WaterResponseSystem.service.BrandService;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class BrandServiceImpl extends BaseServiceImpl<Brand> implements BrandService {

    @Autowired
    private BrandMapper mapper;


    /**
     * 一般只需多表操作代码
     */

}
