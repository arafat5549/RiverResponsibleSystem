package com.jqm.ssm.service;

import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.UserSearchModel;

/**
 * 
 *  IUserService 接口
 * 
 * @author wang
 *
 */
public interface IUserService {

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
	User selectObjByMap(Map<Object, Object> map);
	User selectObjByMap(Map<Object, Object> map,String cacheKey);
    List<User> selectListByMap(Map<Object, Object> map,String cacheKey);
	List<User> selectListByMap(Map<Object, Object> map);

    List<User> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey);
	List<User> listPage(int offset,int limit,Map<Object, Object> map);

	User selectByPrimaryKey(Integer id);
    
    //写部分

    int deleteByPrimaryKey(Integer id); //删除操作(注意会直接删除数据,物理删除)

	
	int insert(User record);

	int insertSelective(User record);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
    
    

    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);

	/**
	 * 根据条件查询用户总数
	 * @param searchModel
	 * @return
	 */
	Integer getUserTotalBySearch(UserSearchModel searchModel);

	/**
	 * 根据条件查询用户List
	 * @param searchModel
	 * @return
	 */
	List<User> getUserListBySearch(UserSearchModel searchModel);

	/**
	 * 新增用户
	 * @param user
	 * @param operator
	 * @return
	 */
	Integer createUser(User user, User operator);

	/**
	 * 根据id更新用户
	 * @param user
	 * @param operator
	 * @return
	 */
	Integer updateUserById(User user, User operator);

	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	Integer deleteUserById(Integer id, User operator);

	/**
	 * 用户赋予角色
	 * @param id
	 * @param selectedStr
	 * @return
	 */
	String assignRole(Integer id, String selectedStr);

	/**
	 * 根据查询条件，返回DataTables控件需要的Json数据格式
	 * @param searchModel
	 * @return
	 */
	String getUserDataTables(UserSearchModel searchModel);

	/**
	 * 返回DataTables控件需要的一行Json数据格式
	 * @param id
	 * @return
	 */
	String getUserDataRow(Integer id);

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
