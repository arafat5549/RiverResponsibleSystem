package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.Category;

public interface CategoryDao extends BaseMapper<Category> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<Category> list);

    int batchInsertSelective(java.util.List<Category> list, Category.Column ... selective);
}