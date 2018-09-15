package com.jqm.ssm.service;

import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.DeviceinfoData;
import com.jqm.ssm.entity.relate.DeviceinfoDataSearchModel;

/**
 * 
 *  IDeviceinfoDataService 接口
 * 
 * @author wang
 *
 */
public interface IDeviceinfoDataService {

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
	DeviceinfoData selectObjByMap(Map<Object, Object> map);
	DeviceinfoData selectObjByMap(Map<Object, Object> map,String cacheKey);
    List<DeviceinfoData> selectListByMap(Map<Object, Object> map,String cacheKey);
	List<DeviceinfoData> selectListByMap(Map<Object, Object> map);

    List<DeviceinfoData> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey);
	List<DeviceinfoData> listPage(int offset,int limit,Map<Object, Object> map);

	DeviceinfoData selectByPrimaryKey(Integer id);
    
    //写部分

    int deleteByPrimaryKey(Integer id); //删除操作(注意会直接删除数据,物理删除)

	
	int insert(DeviceinfoData record);

	int insertSelective(DeviceinfoData record);

	int updateByPrimaryKeySelective(DeviceinfoData record);

	int updateByPrimaryKey(DeviceinfoData record);


	Integer getDeviceinfoDataTotalBySearch(DeviceinfoDataSearchModel searchModel);

	List<DeviceinfoData> getDeviceinfoDataListBySearch(DeviceinfoDataSearchModel searchModel);

	/**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/


	String getDeviceinfoDataDataTables(DeviceinfoDataSearchModel searchModel);

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
