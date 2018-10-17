package com.jqmkj.WaterResponseSystem.basemapper.mapper;


import com.jqmkj.WaterResponseSystem.basemapper.BaseMapper;
import com.jqmkj.WaterResponseSystem.entity.${entityName};
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheNamespace
public interface ${entityName}Mapper extends BaseMapper<${entityName}> {

}