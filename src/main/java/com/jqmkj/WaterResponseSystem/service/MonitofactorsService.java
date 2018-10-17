package com.jqmkj.WaterResponseSystem.service;

import com.jqmkj.WaterResponseSystem.entity.Monitofactors;

import java.util.List;

public interface MonitofactorsService extends BaseService<Monitofactors>{

    List<Monitofactors> findFactorsBySiteId(Long siteId);
    List<Monitofactors> findFactorsByDeviceInfoIds(List<Long> deviceInfoIds);
}
