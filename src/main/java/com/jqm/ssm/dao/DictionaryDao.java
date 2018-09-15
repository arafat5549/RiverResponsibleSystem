package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.Dictionary;
import com.jqm.ssm.entity.relate.DictionarySearchModel;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DictionaryDao extends BaseMapper<Dictionary> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    Dictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<Dictionary> list);

    int batchInsertSelective(java.util.List<Dictionary> list, Dictionary.Column ... selective);

    Integer getDictionaryTotalBySearch(DictionarySearchModel searchModel);

    List<Dictionary> getDictionaryListBySearch(DictionarySearchModel searchModel);
}