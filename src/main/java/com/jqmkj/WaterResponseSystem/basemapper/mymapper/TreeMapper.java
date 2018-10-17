package com.jqmkj.WaterResponseSystem.basemapper.mymapper;

import com.jqmkj.WaterResponseSystem.pojo.PageTree;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface TreeMapper<T> {

    @SelectProvider(type = TreeProvider.class,method = "getAllTree")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "text", column = "name"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "iconCls", column = "icon_cls")
    })
    List<PageTree> getAllTree(Class<T> t);



    @SelectProvider(type = TreeProvider.class,method = "getByParentId")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "text", column = "name"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "iconCls", column = "icon_cls")
    })
    List<PageTree> getByParentId(Class<T> t,@Param("id") Object id);


}
