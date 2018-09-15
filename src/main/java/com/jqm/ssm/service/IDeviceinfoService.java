package com.jqm.ssm.service;

import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.Deviceinfo;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.DeviceinfoSearchModel;

/**
 * 
 *  IDeviceinfoService 接口
 * 
 * @author wang
 *
 */
public interface IDeviceinfoService {

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
	Deviceinfo selectObjByMap(Map<Object, Object> map);
	Deviceinfo selectObjByMap(Map<Object, Object> map,String cacheKey);
    List<Deviceinfo> selectListByMap(Map<Object, Object> map,String cacheKey);
	List<Deviceinfo> selectListByMap(Map<Object, Object> map);

    List<Deviceinfo> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey);
	List<Deviceinfo> listPage(int offset,int limit,Map<Object, Object> map);

	Deviceinfo selectByPrimaryKey(Integer id);
    
    //写部分

    int deleteByPrimaryKey(Integer id); //删除操作(注意会直接删除数据,物理删除)

	
	int insert(Deviceinfo record);

	int insertSelective(Deviceinfo record);

	int updateByPrimaryKeySelective(Deviceinfo record);

	int updateByPrimaryKey(Deviceinfo record);


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	String getDeviceinfoDataTables(DeviceinfoSearchModel searchModel);

	List<Deviceinfo> getDeviceinfoListBySearch(DeviceinfoSearchModel searchModel);

	Integer getDeviceinfoTotalBySearch(DeviceinfoSearchModel searchModel);

    Deviceinfo getDeviceinfoById(Integer id);

	Integer createDeviceinfo(Deviceinfo deviceinfo, User operator);

	Integer updateDeviceinfoById(Deviceinfo deviceinfo, User operator);

    String getDeviceinfoDataRow(Integer id);


    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
