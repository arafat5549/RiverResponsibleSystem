package com.jqm.ssm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.DictionarySearchModel;
import com.jqm.ssm.util.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jqm.ssm.service.IDictionaryService;
import com.jqm.ssm.dao.DictionaryDao;
import com.jqm.ssm.entity.Dictionary;

import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.util.PageUtil;

import com.google.common.base.Strings;
import com.jqm.ssm.util.page.Page;
import com.jqm.ssm.misc.Constants;
import com.google.common.collect.Maps;
/**
 * 
 * IDictionaryService 接口实现类
 *
 * @author wang
 */
@Service(value = "dictionaryService")
public class DictionaryServiceImpl implements IDictionaryService {

	private static final Logger LOG = LoggerFactory.getLogger(DictionaryServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|Dictionary|";
	private static final Class<Dictionary> SELF_CLASS = Dictionary.class;

	@Autowired
	private DictionaryDao mapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public Dictionary selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<Dictionary> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public Dictionary selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<Dictionary> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<Dictionary> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<Dictionary> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
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
    public List<Dictionary> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public Dictionary selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			Dictionary result_cache = redisCache.getCache(cache_key, SELF_CLASS);
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
	public int insert(Dictionary record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Dictionary record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Dictionary record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Dictionary record) {
		return mapper.updateByPrimaryKey(record);
	}


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	@Override
	public Integer getDictionaryTotalBySearch(DictionarySearchModel searchModel) {
		return mapper.getDictionaryTotalBySearch(searchModel);
	}

	@Override
	public List<Dictionary> getDictionaryListBySearch(DictionarySearchModel searchModel) {
		return mapper.getDictionaryListBySearch(searchModel);
	}

	@Override
	public String getDictionaryDataTables(DictionarySearchModel searchModel) {
		Integer total = getDictionaryTotalBySearch(searchModel);
		List<Dictionary> systemLogList = getDictionaryListBySearch(searchModel);
		if(systemLogList==null || systemLogList.size()==0)
		{
			return "{\"iTotalRecords\":0,\"iTotalDisplayRecords\":0,\"aaData\":[]}";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("{\"iTotalRecords\":%d,\"iTotalDisplayRecords\":%d,\"aaData\":[",total,total));
		int i= 0;
		for(Dictionary u:systemLogList)
		{
			if(i != 0) sb.append(",");
			addDataRow(sb,u);
			i++;
		}
		sb.append("]}");
		return sb.toString();
	}

	@Override
	public Integer createDictionary(Dictionary d, User operator) {
		setDictionaryInsert(d,operator);
		return mapper.insertSelective(d);
	}


	public void setDictionaryInsert(Dictionary b, User operator) {
		Date d = new Date();
		b.setCreatePerson(operator.getUsername());
		b.setCreateDate(d);
		//Map map = Maps.newHashMap();
		//map.put("group",b.getGroup());
		//byte count = (byte) mapper.selectCountByMap(map);
		byte c = 1;
		b.setCode(c);
	}

	private void addDataRow(StringBuilder sb,Dictionary b) {
		sb.append("[");
		sb.append("\"<input type=\\\"checkbox\\\" name=\\\"id[]\\\" value=\\\"").append(b.getId()).append("\\\">\"");
		sb.append(",").append(b.getId());
		sb.append(",\"").append(b.getGroup()).append("\"");
		sb.append(",\"").append(b.getCode()).append("\"");
		sb.append(",\"").append(b.getName()).append("\"");
		sb.append(",\"").append(b.getSortNo()).append("\"");
		sb.append(",\"").append(b.getCreatePerson()).append("\"");
		sb.append(",\"").append(StringUtil.formatDate(b.getCreateDate(), "yyyy-MM-dd HH:mm:ss")).append("\"");
		sb.append(",\"")
				.append("<a href=\\\"javascript:Dictionary.update_click('").append(b.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-edit\\\"></i> 修改</a>")
				//.append("&nbsp;&nbsp;<a href=\\\"javascript:").append(u.getIsLock() ? "User.unlock('" : "User.lock('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-").append(u.getIsLock() ? "un" : "").append("lock\\\"></i> ").append(u.getIsLock() ? "解锁" : "锁定").append("</a>")
				.append("&nbsp;&nbsp;<a href=\\\"javascript:Dictionary.remove('").append(b.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-times\\\"></i> 删除</a>")
				//.append("&nbsp;&nbsp;<a href=\\\"javascript:User.assign_click('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-key\\\"></i> 分配角色</a>")
				.append("\"");
		sb.append("]");

	}

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
