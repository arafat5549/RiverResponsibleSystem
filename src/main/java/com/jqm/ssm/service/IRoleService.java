package com.jqm.ssm.service;

import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.Role;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.RoleSearchModel;

/**
 * 
 *  IRoleService 接口
 * 
 * @author wang
 *
 */
public interface IRoleService {

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
	Role selectObjByMap(Map<Object, Object> map);
	Role selectObjByMap(Map<Object, Object> map,String cacheKey);
    List<Role> selectListByMap(Map<Object, Object> map,String cacheKey);
	List<Role> selectListByMap(Map<Object, Object> map);

    List<Role> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey);
	List<Role> listPage(int offset,int limit,Map<Object, Object> map);

	Role selectByPrimaryKey(Integer id);
    
    //写部分

    int deleteByPrimaryKey(Integer id); //删除操作(注意会直接删除数据,物理删除)

	
	int insert(Role record);

	int insertSelective(Role record);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);
    
    

    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	Integer createRole(Role role, User user);
	Integer updateRoleById(Role role, User user);//根据id修改一条记录
	Integer deleteRoleById(Integer id); //根据id删除角色
	Integer getRoleTotalBySearch(RoleSearchModel searchModel);//根据条件查询角色列表总数
	List<Role> getRoleListBySearch(RoleSearchModel searchModel);//根据条件查询角色列表
	Boolean isUsedByUser(Integer roleId);//角色是否被用户使用
	String assignModuleAndResource(Integer roleId, String checkedStr);
	List<Role> getRoleListByUserId(Integer id);

	String getRoleDataTables(RoleSearchModel searchModel); //根据查询条件，返回DataTables控件需要的Json数据格式

	String getRoleDataRow(Integer id); //返回DataTables控件需要的一行Json数据格式

	String getRoleForOptions(Integer userId);//返回jquery-multi-select需要的options数据

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
