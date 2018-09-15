package com.jqm.ssm.service;

import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.SystemLog;
import com.jqm.ssm.entity.relate.SystemLogSearchModel;

/**
 * 
 *  ISystemLogService 接口
 * 
 * @author wang
 *
 */
public interface ISystemLogService {

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
	SystemLog selectObjByMap(Map<Object, Object> map);
	SystemLog selectObjByMap(Map<Object, Object> map,String cacheKey);
    List<SystemLog> selectListByMap(Map<Object, Object> map,String cacheKey);
	List<SystemLog> selectListByMap(Map<Object, Object> map);

    List<SystemLog> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey);
	List<SystemLog> listPage(int offset,int limit,Map<Object, Object> map);

	SystemLog selectByPrimaryKey(Integer id);
    
    //写部分

    int deleteByPrimaryKey(Integer id); //删除操作(注意会直接删除数据,物理删除)

	
	int insert(SystemLog record);

	int insertSelective(SystemLog record);

	int updateByPrimaryKeySelective(SystemLog record);

	int updateByPrimaryKey(SystemLog record);


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

    Integer getSystemLogTotalBySearch(SystemLogSearchModel searchModel);

	List<SystemLog> getSystemLogListBySearch(SystemLogSearchModel searchModel);

	String getSystemLogDataTables(SystemLogSearchModel searchModel);

	/**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
