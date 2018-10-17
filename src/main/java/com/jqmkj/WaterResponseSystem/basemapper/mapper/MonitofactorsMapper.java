package com.jqmkj.WaterResponseSystem.basemapper.mapper;

import com.jqmkj.WaterResponseSystem.basemapper.BaseMapper;
import com.jqmkj.WaterResponseSystem.entity.Monitofactors;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheNamespace
public interface MonitofactorsMapper extends BaseMapper<Monitofactors> {

    /**
     * 一般只需多表操作
     */
    //List<Monitofactors> findFactorsBySiteId(Long siteId);
    List<Monitofactors> findFactorsByDeviceInfoIds(@Param("deviceInfoIds")List<Long> deviceInfoIds);
}
