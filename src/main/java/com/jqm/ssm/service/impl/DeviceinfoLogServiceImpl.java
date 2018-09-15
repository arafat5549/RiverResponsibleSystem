package com.jqm.ssm.service.impl;

import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.relate.DeviceinfoLogSearchModel;
import com.jqm.ssm.util.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jqm.ssm.service.IDeviceinfoLogService;
import com.jqm.ssm.dao.DeviceinfoLogDao;
import com.jqm.ssm.entity.DeviceinfoLog;

import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.util.PageUtil;

import com.google.common.base.Strings;
import com.jqm.ssm.util.page.Page;
import com.jqm.ssm.misc.Constants;
import com.google.common.collect.Maps;
/**
 * 
 * IDeviceinfoLogService 接口实现类
 *
 * @author wang
 */
@Service(value = "deviceinfoLogService")
public class DeviceinfoLogServiceImpl implements IDeviceinfoLogService {

	private static final Logger LOG = LoggerFactory.getLogger(DeviceinfoLogServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|DeviceinfoLog|";
	private static final Class<DeviceinfoLog> SELF_CLASS = DeviceinfoLog.class;

	@Autowired
	private DeviceinfoLogDao mapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public DeviceinfoLog selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<DeviceinfoLog> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public DeviceinfoLog selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<DeviceinfoLog> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<DeviceinfoLog> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<DeviceinfoLog> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
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
    public List<DeviceinfoLog> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public DeviceinfoLog selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			DeviceinfoLog result_cache = redisCache.getCache(cache_key, SELF_CLASS);
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
	public int insert(DeviceinfoLog record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(DeviceinfoLog record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceinfoLog record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DeviceinfoLog record) {
		return mapper.updateByPrimaryKey(record);
	}


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	@Override
	public Integer getDeviceinfoLogTotalBySearch(DeviceinfoLogSearchModel searchModel) {
		return mapper.getDeviceinfoLogTotalBySearch(searchModel);
	}

	@Override
	public List<DeviceinfoLog> getDeviceinfoLogListBySearch(DeviceinfoLogSearchModel searchModel) {
		return mapper.getDeviceinfoLogListBySearch(searchModel);
	}

	@Override
	public String getDeviceinfoLogDataTables(DeviceinfoLogSearchModel searchModel) {
		Integer total = getDeviceinfoLogTotalBySearch(searchModel);
		List<DeviceinfoLog> deviceinfoLogList = getDeviceinfoLogListBySearch(searchModel);
		if(deviceinfoLogList==null || deviceinfoLogList.size()==0)
		{
			return "{\"iTotalRecords\":0,\"iTotalDisplayRecords\":0,\"aaData\":[]}";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("{\"iTotalRecords\":%d,\"iTotalDisplayRecords\":%d,\"aaData\":[",total,total));
		int i= 0;
		for(DeviceinfoLog u:deviceinfoLogList)
		{
			if(i != 0) sb.append(",");
			addDataRow(sb,u);
			i++;
		}
		sb.append("]}");
		return sb.toString();
	}

	private void addDataRow(StringBuilder sb,DeviceinfoLog b) {
		sb.append("[");
		sb.append("\"<input type=\\\"checkbox\\\" name=\\\"id[]\\\" value=\\\"").append(b.getId()).append("\\\">\"");
		sb.append(",").append(b.getId());
		sb.append(",\"").append(b.getDeviceinfoId()).append("\"");
		sb.append(",\"").append(b.getLogTypeId()).append("\"");
		sb.append(",\"").append(b.getRemark()).append("\"");
		sb.append(",\"").append(b.getCreatePerson()).append("\"");
		sb.append(",\"").append(StringUtil.formatDate(b.getCreateDate(), "yyyy-MM-dd HH:mm:ss")).append("\"");
		sb.append(",\"")
				.append("<a href=\\\"javascript:DeviceinfoLog.update_click('").append(b.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-edit\\\"></i> 修改</a>")
				//.append("&nbsp;&nbsp;<a href=\\\"javascript:").append(u.getIsLock() ? "User.unlock('" : "User.lock('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-").append(u.getIsLock() ? "un" : "").append("lock\\\"></i> ").append(u.getIsLock() ? "解锁" : "锁定").append("</a>")
				.append("&nbsp;&nbsp;<a href=\\\"javascript:DeviceinfoLog.remove('").append(b.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-times\\\"></i> 删除</a>")
				//.append("&nbsp;&nbsp;<a href=\\\"javascript:User.assign_click('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-key\\\"></i> 分配角色</a>")
				.append("\"");
		sb.append("]");

	}

	/**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
