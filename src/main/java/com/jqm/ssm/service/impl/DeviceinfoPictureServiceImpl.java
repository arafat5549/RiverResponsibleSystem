package com.jqm.ssm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jqm.ssm.service.IDeviceinfoPictureService;
import com.jqm.ssm.dao.DeviceinfoPictureDao;
import com.jqm.ssm.entity.DeviceinfoPicture;

import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.util.PageUtil;

import com.google.common.base.Strings;
import com.jqm.ssm.util.page.Page;
import com.jqm.ssm.misc.Constants;
import com.google.common.collect.Maps;
/**
 * 
 * IDeviceinfoPictureService 接口实现类
 *
 * @author wang
 */
@Service(value = "deviceinfoPictureService")
public class DeviceinfoPictureServiceImpl implements IDeviceinfoPictureService {

	private static final Logger LOG = LoggerFactory.getLogger(DeviceinfoPictureServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|DeviceinfoPicture|";
	private static final Class<DeviceinfoPicture> SELF_CLASS = DeviceinfoPicture.class;

	@Autowired
	private DeviceinfoPictureDao mapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public DeviceinfoPicture selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<DeviceinfoPicture> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public DeviceinfoPicture selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<DeviceinfoPicture> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<DeviceinfoPicture> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<DeviceinfoPicture> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
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
    public List<DeviceinfoPicture> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public DeviceinfoPicture selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			DeviceinfoPicture result_cache = redisCache.getCache(cache_key, SELF_CLASS);
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
	public int insert(DeviceinfoPicture record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(DeviceinfoPicture record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceinfoPicture record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DeviceinfoPicture record) {
		return mapper.updateByPrimaryKey(record);
	}


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	@Override
	public Integer createDeviceinfoPicture(DeviceinfoPicture deviceinfoPicture, User operator) {
		setDeviceinfoInsert(deviceinfoPicture,operator);
		return mapper.insertSelective(deviceinfoPicture);
	}

	private void setDeviceinfoInsert(DeviceinfoPicture deviceinfoPicture, User operator)
	{
		Date d = new Date();
		deviceinfoPicture.setCreatePerson(operator.getUsername());
		deviceinfoPicture.setUpdatePerson(operator.getUsername());
		deviceinfoPicture.setCreateDate(d);
		deviceinfoPicture.setUpdateDate(d);
	}
	private void setDepartmentUpdate(DeviceinfoPicture deviceinfoPicture,User operator)
	{
		Date d = new Date();
		deviceinfoPicture.setUpdatePerson(operator.getUsername());
		deviceinfoPicture.setUpdateDate(d);
	}

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
