package com.jqmkj.WaterResponseSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "${tableName}",
        indexes = {  @Index(name = "my_index_DataTime", columnList="data_time",     unique = false)})
public class ${entityName} extends BaseEntity{

    @Column(name="monitorsite_id")
    private Long monitorsiteId;//监测站ID
    @Column(name="data_time")
    private String DataTime;

    <#if datas??>
    <#list datas as m>
    @Column(name="${m.name}")
    private String ${m.code};
    </#list>
    </#if>


}