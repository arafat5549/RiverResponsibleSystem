package com.jqm.ssm.dao;

import com.jqm.ssm.base.BaseMapper;
import com.jqm.ssm.entity.Brand;
import com.jqm.ssm.entity.relate.BrandSearchModel;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BrandDao extends BaseMapper<Brand> {
    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

    /**
     * 根据条件查询用户总数
     * @param searchModel
     * @return
     */
    Integer getBrandTotalBySearch(BrandSearchModel searchModel);

    /**
     * 根据条件查询用户List
     * @param searchModel
     * @return
     */
    List<Brand> getBrandListBySearch(BrandSearchModel searchModel);
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    int deleteByPrimaryKey(Integer id);

    Brand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    int deleteLogicByIds(int deleteFlag, Integer[] ids);

    int batchInsert(java.util.List<Brand> list);

    int batchInsertSelective(java.util.List<Brand> list, Brand.Column ... selective);
}