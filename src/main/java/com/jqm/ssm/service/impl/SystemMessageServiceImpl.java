package com.jqm.ssm.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.dao.SystemMessageDao;
import com.jqm.ssm.entity.SystemMessage;
import com.jqm.ssm.service.ISystemMessageService;
import com.jqm.ssm.util.PageUtil;
import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.util.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 
 * ISystemMessageService 接口实现类
 *
 * @author wang
 */
@Service(value = "systemMessageService")
public class SystemMessageServiceImpl implements ISystemMessageService {

	private static final Logger LOG = LoggerFactory.getLogger(SystemMessageServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|SystemMessage|";
	private static final Class<SystemMessage> SELF_CLASS = SystemMessage.class;

	@Autowired
	private SystemMessageDao mapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public SystemMessage selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<SystemMessage> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public SystemMessage selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<SystemMessage> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<SystemMessage> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<SystemMessage> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
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
    public List<SystemMessage> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public SystemMessage selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			SystemMessage result_cache = redisCache.getCache(cache_key, SELF_CLASS);
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
	public int insert(SystemMessage record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(SystemMessage record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SystemMessage record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SystemMessage record) {
		return mapper.updateByPrimaryKey(record);
	}


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/


    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
