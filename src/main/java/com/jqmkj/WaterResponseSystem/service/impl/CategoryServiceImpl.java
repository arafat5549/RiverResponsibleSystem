package com.jqmkj.WaterResponseSystem.service.impl;

import com.jqmkj.WaterResponseSystem.entity.Category;
import com.jqmkj.WaterResponseSystem.service.CategoryService;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    @Autowired
    private CategoryMapper mapper;


    /**
     * 一般只需多表操作代码
     */

}
