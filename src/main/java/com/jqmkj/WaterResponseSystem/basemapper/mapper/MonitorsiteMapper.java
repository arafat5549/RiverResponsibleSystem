package com.jqmkj.WaterResponseSystem.basemapper.mapper;

import com.jqmkj.WaterResponseSystem.basemapper.BaseMapper;
import com.jqmkj.WaterResponseSystem.pojo.MonitorSiteTree;
import com.jqmkj.WaterResponseSystem.entity.Monitorsite;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheNamespace
public interface MonitorsiteMapper extends BaseMapper<Monitorsite> {

    /**
     * 一般只需多表操作
     */

    @Select("SELECT m.*,u.username from c_monitorsite m left join water_user u on m.user_id = u.id where m.parent_id=0 or m.parent_id is null")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "text", column = "name"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "iconCls", column = "icon_cls"),
            @Result(property = "sectionNo", column = "section_no"),
            @Result(property = "sectionName", column = "section_name"),
            @Result(property = "sectionName", column = "section_name"),
            @Result(property = "functionType", column = "function_type"),
            @Result(property = "drainageBasin", column = "drainage_basin"),
            @Result(property = "riverSystem", column = "river_system"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "children", column = "id",
                     many=@Many(select = "getRootMonitorSiteTreeByParentId"))
    })
    List<MonitorSiteTree> getRootMonitorSiteTree();

    @Select("SELECT m.*,u.username from c_monitorsite m left join water_user u on m.user_id = u.id where m.parent_id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "text", column = "name"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "iconCls", column = "icon_cls"),
            @Result(property = "sectionNo", column = "section_no"),
            @Result(property = "sectionName", column = "section_name"),
            @Result(property = "sectionName", column = "section_name"),
            @Result(property = "functionType", column = "function_type"),
            @Result(property = "drainageBasin", column = "drainage_basin"),
            @Result(property = "riverSystem", column = "river_system"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "children", column = "id",
                    many=@Many(select = "getRootMonitorSiteTreeByParentId"))
    })
    List<MonitorSiteTree> getRootMonitorSiteTreeByParentId(@Param("id") Long id);

}
