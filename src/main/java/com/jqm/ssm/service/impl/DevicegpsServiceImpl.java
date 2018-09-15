package com.jqm.ssm.service.impl;

import java.util.*;

import com.jqm.ssm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jqm.ssm.service.IDevicegpsService;
import com.jqm.ssm.dao.DevicegpsDao;
import com.jqm.ssm.entity.Devicegps;

import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.util.PageUtil;

import com.google.common.base.Strings;
import com.jqm.ssm.util.page.Page;
import com.jqm.ssm.misc.Constants;
import com.google.common.collect.Maps;
/**
 * 
 * IDevicegpsService 接口实现类
 *
 * @author wang
 */
@Service(value = "devicegpsService")
public class DevicegpsServiceImpl implements IDevicegpsService {

	private static final Logger LOG = LoggerFactory.getLogger(DevicegpsServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|Devicegps|";
	private static final Class<Devicegps> SELF_CLASS = Devicegps.class;

	@Autowired
	private DevicegpsDao mapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public Devicegps selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<Devicegps> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public Devicegps selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<Devicegps> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<Devicegps> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<Devicegps> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
	{
		String cache_key = PREFIX_CAHCE + "listPage|" + offset +"|"+ limit;
		if(map == null) map = Maps.newHashMap();
		if(offset>=0 && limit>=0) {
			Page page = new Page(offset, limit);
			map.put("page",page);
		}
		if(springContextHolder.getRedisAcaliable()&& !Strings.isNullOrEmpty(cacheKey))
		{
    		cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, PageUtil.getPageParamMap(offset, limit,map), mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
    @Override
    public List<Devicegps> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public Devicegps selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			Devicegps result_cache = redisCache.getCache(cache_key, SELF_CLASS);
			if (result_cache != null) {
				LOG.info("get cache with key:" + cache_key);
			} else {
				result_cache = mapper.selectByPrimaryKey(id);
				if(result_cache!=null){
					redisCache.putCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
					LOG.info("put cache with key:" + cache_key);
				}
				else{
					LOG.error(cache_key+":获取的是空的对象");
				}
				return result_cache;
			}
			return result_cache;
		}
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	

	@Override
	public int insert(Devicegps record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Devicegps record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Devicegps record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Devicegps record) {
		return mapper.updateByPrimaryKey(record);
	}


	/**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	@Override
	public Integer createDevicegps(Devicegps devicegps, User user) {

		Devicegps devicegpsParent = mapper.selectByPrimaryKey(devicegps.getParentId());
		StringBuffer sb = new StringBuffer("");
		if (devicegpsParent != null) {
			//String structNameP = devicegpsParent.getStructName();
			sb.append(devicegpsParent.getStructure())
					.append("-");
		}else{
			sb.append("s").append("-");
		}
		setDevicegpsInsert(devicegps,user);
		int result = mapper.insertSelective(devicegps);
		sb.append(devicegps.getId());
		devicegps.setStructure(sb.toString());
		updateByPrimaryKeySelective(devicegps);
		return result;
	}

	@Override
	public Integer updateDevicegps(Devicegps devicegps, User user) {
		setDevicegpsUpdate(devicegps,user);
		return mapper.updateByPrimaryKeySelective(devicegps);
	}

	@Override
	public String getDevicegpsTree() {
		List<Devicegps> categoryList = mapper.selectListByMap(null);
		if(categoryList==null || categoryList.size()==0) return Constants.EmptyJsonObject;

		//Collections.sort(categoryList,new ComparatorDevicegps());

		Set<Integer> setParent = new HashSet<Integer>();
		for(Devicegps d:categoryList)
		{
			setParent.add(d.getParentId());
		}

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int i = 0;
		for(Devicegps d:categoryList)
		{
			int level = d.getStructure().split("-").length;
			if(i!=0)
			{
				sb.append(",");
			}
			i++;
			sb.append("{")
					.append("\"id\":\"").append(d.getId()).append("\"")
					.append(",\"parent\":\"").append(d.getParentId()==0?"#":d.getParentId()).append("\"")
					.append(",\"text\":\"").append(d.getId()).append("\"");
					//.append(",\"li_attr\":{\"sortNo\":").append(d.getSortNo()).append("}");;
			//前两个级别默认打开
			if(level <=3)
			{
				sb.append(",\"state\":{\"opened\":true}");
			}
			//最后一个级别换个绿色图标
			if(!setParent.contains(d.getId()))
			{
				sb.append(", \"icon\": \"fa fa-briefcase icon-success\"");
			}
			sb.append("}");
		}
		sb.append("]");
		return sb.toString();
	}
	@Override
	public List<Devicegps> getDeviceinfoList(){
		return mapper.getDeviceinfoList();
	}


	private void setDevicegpsInsert(Devicegps devicegps, User operator)
	{
		Date d = new Date();
		devicegps.setCreatePerson(operator.getUsername());
		devicegps.setUpdatePerson(operator.getUsername());
		devicegps.setCreateDate(d);
		devicegps.setUpdateDate(d);
	}
	private void setDevicegpsUpdate(Devicegps devicegps,User operator)
	{
		Date d = new Date();
		devicegps.setUpdatePerson(operator.getUsername());
		devicegps.setUpdateDate(d);
	}


    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
