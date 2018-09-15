package com.jqm.ssm.service;

import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.Permission;
import com.jqm.ssm.entity.User;

/**
 * 
 *  IPermissionService 接口
 * 
 * @author wang
 *
 */
public interface IPermissionService {

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
	Permission selectObjByMap(Map<Object, Object> map);
	Permission selectObjByMap(Map<Object, Object> map,String cacheKey);
    List<Permission> selectListByMap(Map<Object, Object> map,String cacheKey);
	List<Permission> selectListByMap(Map<Object, Object> map);

    List<Permission> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey);
	List<Permission> listPage(int offset,int limit,Map<Object, Object> map);

	Permission selectByPrimaryKey(Integer id);
    
    //写部分

    int deleteByPrimaryKey(Integer id); //删除操作(注意会直接删除数据,物理删除)

	
	int insert(Permission record);

	int insertSelective(Permission record);

	int updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);
    
    

    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	Integer createResource(Permission resource, User user);//新增记录

	Integer updateResourceById(Permission resource, User user);//根据id修改一条记录

	Integer deleteResourceById(Integer id); //根据id删除一条resource

	Boolean isUsedByRole(Integer resourceId); //根据资源id判断是否被角色使用

	public String getPermissionTree(Integer roleId);//获取书结构

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
