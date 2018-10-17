package com.jqmkj.WaterResponseSystem.basemapper.mapper;

import com.jqmkj.WaterResponseSystem.basemapper.BaseMapper;
import com.jqmkj.WaterResponseSystem.entity.Brand;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

@Repository
@CacheNamespace
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 一般只需多表操作
     */

}
