package com.jqm.ssm.service.impl;

import java.util.*;

import com.jqm.ssm.entity.User;
import com.jqm.ssm.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jqm.ssm.service.IMonitorsiteService;
import com.jqm.ssm.dao.MonitorsiteDao;
import com.jqm.ssm.entity.Monitorsite;

import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.util.PageUtil;

import com.google.common.base.Strings;
import com.jqm.ssm.util.page.Page;
import com.jqm.ssm.misc.Constants;
import com.google.common.collect.Maps;
/**
 * 
 * IMonitorsiteService 接口实现类
 *
 * @author wang
 */
@Service(value = "monitorsiteService")
public class MonitorsiteServiceImpl implements IMonitorsiteService {

	private static final Logger LOG = LoggerFactory.getLogger(MonitorsiteServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|Monitorsite|";
	private static final Class<Monitorsite> SELF_CLASS = Monitorsite.class;

	@Autowired
	private MonitorsiteDao mapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public Monitorsite selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<Monitorsite> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public Monitorsite selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<Monitorsite> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<Monitorsite> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<Monitorsite> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
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
    public List<Monitorsite> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public Monitorsite selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			Monitorsite result_cache = redisCache.getCache(cache_key, SELF_CLASS);
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
	public int insert(Monitorsite record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Monitorsite record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Monitorsite record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Monitorsite record) {
		return mapper.updateByPrimaryKey(record);
	}


    @Override
    public Integer createMonitorsite(Monitorsite monitorsite, User user) {
        Monitorsite monitorsiteParent = mapper.selectByPrimaryKey(monitorsite.getParentId());
        StringBuffer sb = new StringBuffer("");
        if (monitorsiteParent != null) {
            //String structNameP = monitorsiteParent.getStructName();
            sb.append(monitorsiteParent.getStructure())
                    .append("-");
        }else{
			sb.append("s").append("-");
		}
		setMonitorsiteInsert(monitorsite,user);
		int result = mapper.insertSelective(monitorsite);
		sb.append(monitorsite.getId());
		monitorsite.setStructure(sb.toString());
		updateByPrimaryKeySelective(monitorsite);
        return result;
    }

    @Override
    public Integer updateMonitorsite(Monitorsite monitorsite, User user) {
        setMonitorsiteUpdate(monitorsite,user);
        return mapper.updateByPrimaryKeySelective(monitorsite);
    }


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

    private void setMonitorsiteInsert(Monitorsite monitorsite, User operator)
    {
        Date d = new Date();
        monitorsite.setCreatePerson(operator.getUsername());
        monitorsite.setUpdatePerson(operator.getUsername());
        monitorsite.setCreateDate(d);
        monitorsite.setUpdateDate(d);
    }
    private void setMonitorsiteUpdate(Monitorsite monitorsite,User operator)
    {
        Date d = new Date();
        monitorsite.setUpdatePerson(operator.getUsername());
        monitorsite.setUpdateDate(d);
    }

	@Override
	public String getMonitorsiteTree() {
		List<Monitorsite> monitorsiteList = mapper.selectListByMap(null);
		if(monitorsiteList==null || monitorsiteList.size()==0) return Constants.EmptyJsonObject;

		//Collections.sort(monitorsiteList,new ComparatorMonitorsite());

		Set<Integer> setParent = new HashSet<Integer>();
		for(Monitorsite d:monitorsiteList)
		{
			setParent.add(d.getParentId());
		}

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int i = 0;
		for(Monitorsite d:monitorsiteList)
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
					.append(",\"text\":\"").append(d.getName()).append("\"");
					//.append(",\"li_attr\":{\"sortNo\":").append(d.getSortNo()).append("}");
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
	public List<Monitorsite> getMonitorsiteListForOption() {
			List<Monitorsite> monitorsites = selectListByMap(null);
			//monitorsites.forEach(c->c.setStructure("s-"+c.getStructure()));
			if(monitorsites==null || monitorsites.size()==0)
			{
				return null;
			}

			List<Monitorsite> tempImmediateMonitorsites = new ArrayList<Monitorsite>();
			for(Monitorsite d:monitorsites)
			{
				if(d.getParentId()==0)
				{
					//一级子节点
					tempImmediateMonitorsites.add(d);
				}
			}

			return buildMonitorsiteListForOption(monitorsites,tempImmediateMonitorsites,"s");
	}

	private List<Monitorsite> buildMonitorsiteListForOption(List<Monitorsite> descendantMonitorsites,List<Monitorsite> immediateMonitorsites,String structure)
	{
		if(descendantMonitorsites == null || descendantMonitorsites.size()==0
				||immediateMonitorsites == null || immediateMonitorsites.size()==0)
		{
			return null;
		}

		//Collections.sort(immediateMonitorsites,new ComparatorMonitorsite());

		List<Monitorsite> result = new ArrayList<Monitorsite>();

		Integer index = 0;
		Integer level = structure.split("-").length;
		String prefix = "";
		for(int i=0;i<level-1;i++)
		{
			prefix += "&nbsp;&nbsp;&nbsp;";
		}
		for(Monitorsite department:immediateMonitorsites)
		{
			if(department.getStructure().split("-").length != level+1
					|| !department.getStructure().startsWith(structure+"-")
					)
			{
				continue;
			}
			department.setName(prefix + department.getName());
			result.add(department);

			List<Monitorsite> tempDescendantMonitorsite = new ArrayList<Monitorsite>();
			List<Monitorsite> tempImmediateMonitorsite = new ArrayList<Monitorsite>();
			for(Monitorsite r:descendantMonitorsites)
			{
				if(r.getStructure().startsWith(department.getStructure()+"-"))
				{
					if(r.getStructure().split("-").length == level + 2 )
					{
						//第一级子节点
						tempImmediateMonitorsite.add(r);
					}
					//所有子节点
					tempDescendantMonitorsite.add(r);
				}
			}
			if(tempDescendantMonitorsite!=null && tempDescendantMonitorsite.size()>0
					&& tempImmediateMonitorsite!=null && tempImmediateMonitorsite.size()>0)
			{
				List<Monitorsite> sub = buildMonitorsiteListForOption(tempDescendantMonitorsite,tempImmediateMonitorsite, department.getStructure());

				if(sub!=null && sub.size()>0)
				{
					result.addAll(sub);
				}
			}
			index++;
		}
		return result;
	}

	/**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
