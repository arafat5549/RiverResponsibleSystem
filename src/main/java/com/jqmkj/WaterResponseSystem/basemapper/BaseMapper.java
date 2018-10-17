package com.jqmkj.WaterResponseSystem.basemapper;

import com.jqmkj.WaterResponseSystem.basemapper.mymapper.TreeMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface BaseMapper<T> extends Mapper<T> ,MySqlMapper<T> ,IdsMapper<T> ,TreeMapper<T> {


}
