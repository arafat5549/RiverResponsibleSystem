package com.jqm.ssm.service;

import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.Category;
import com.jqm.ssm.entity.User;

/**
 * 
 *  ICategoryService 接口
 * 
 * @author wang
 *
 */
public interface ICategoryService {

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
	Category selectObjByMap(Map<Object, Object> map);
	Category selectObjByMap(Map<Object, Object> map,String cacheKey);
    List<Category> selectListByMap(Map<Object, Object> map,String cacheKey);
	List<Category> selectListByMap(Map<Object, Object> map);

    List<Category> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey);
	List<Category> listPage(int offset,int limit,Map<Object, Object> map);

	Category selectByPrimaryKey(Integer id);
    
    //写部分

    int deleteByPrimaryKey(Integer id); //删除操作(注意会直接删除数据,物理删除)

	
	int insert(Category record);

	int insertSelective(Category record);

	int updateByPrimaryKeySelective(Category record);

	int updateByPrimaryKey(Category record);

    String getCategoryTree();

	Integer createCategory(Category category, User user);

	Integer updateCategory(Category category, User user);

    List<Category> getCategoryListForOption();


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/


    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
