package com.jqm.ssm.service;

import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.Brand;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.BrandSearchModel;
import com.jqm.ssm.entity.relate.UserSearchModel;

/**
 * 
 *  IBrandService 接口
 * 
 * @author wang
 *
 */
public interface IBrandService {

    //读取部分
    
	int selectCountByMap(Map<Object, Object> map);
	/**
	 * 根据参数过滤结果集<p>
	 * 
	 *  用法例子(分页效果):<br>
	 *  Map<Object,Object> map = new HashMap<Object, Object>();<br>
	 * 	Page page = new Page(0, 10);<br>
	 * 	map.put("page", page);<br>
	 * 	map.put("databaseId","mysql");//分页时候记得指定数据库集合<br>
	 * 
	 * @param map
	 * @return
	 */
	Brand selectObjByMap(Map<Object, Object> map);
	Brand selectObjByMap(Map<Object, Object> map,String cacheKey);
    List<Brand> selectListByMap(Map<Object, Object> map,String cacheKey);
	List<Brand> selectListByMap(Map<Object, Object> map);

    List<Brand> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey);
	List<Brand> listPage(int offset,int limit,Map<Object, Object> map);

	Brand selectByPrimaryKey(Integer id);
    
    //写部分

    int deleteByPrimaryKey(Integer id); //删除操作(注意会直接删除数据,物理删除)

	
	int insert(Brand record);

	int insertSelective(Brand record);

	int updateByPrimaryKeySelective(Brand record);

	int updateByPrimaryKey(Brand record);
    
    

    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	/**
	 * 根据条件查询总数
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

	/**
	 * 根据查询条件，返回DataTables控件需要的Json数据格式
	 * @param searchModel
	 * @return
	 */
	String getBrandDataTables(BrandSearchModel searchModel);

    Integer createBrand(Brand b, User operator);

	Brand getBrandById(Integer id);

    Integer updateBrandById(Brand brand, User operator);

	String getBrandDataRow(Integer id);

	Integer updateUserById(Brand brand, User operator);


	/**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
