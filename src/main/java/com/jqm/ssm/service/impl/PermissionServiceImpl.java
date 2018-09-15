package com.jqm.ssm.service.impl;

import java.util.*;

import com.jqm.ssm.dao.ModuleDao;
import com.jqm.ssm.entity.Permission;
import com.jqm.ssm.entity.Module;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jqm.ssm.service.IPermissionService;
import com.jqm.ssm.dao.PermissionDao;
import com.jqm.ssm.entity.Permission;

import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.util.PageUtil;

import com.google.common.base.Strings;
import com.jqm.ssm.util.page.Page;
import com.jqm.ssm.misc.Constants;
import com.google.common.collect.Maps;
/**
 * 
 * IPermissionService 接口实现类
 *
 * @author wang
 */
@Service(value = "permissionService")
public class PermissionServiceImpl implements IPermissionService {

	private static final Logger LOG = LoggerFactory.getLogger(PermissionServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|Permission|";
	private static final Class<Permission> SELF_CLASS = Permission.class;

	@Autowired
	private PermissionDao mapper;
	@Autowired
	private ModuleDao moduleDao;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public Permission selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<Permission> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public Permission selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<Permission> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<Permission> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<Permission> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
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
    public List<Permission> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public Permission selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			Permission result_cache = redisCache.getCache(cache_key, SELF_CLASS);
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
	public int insert(Permission record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Permission record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Permission record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Permission record) {
		return mapper.updateByPrimaryKey(record);
	}


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	private void setResourceInsert(Permission resource, User operator)
	{
		Date d = new Date();
		resource.setCreatePerson(operator.getUsername());
		resource.setUpdatePerson(operator.getUsername());
		resource.setCreateDate(d);
		resource.setUpdateDate(d);
	}
	private void setResourceUpdate(Permission resource,User operator)
	{
		Date d = new Date();
		resource.setUpdatePerson(operator.getUsername());
		resource.setUpdateDate(d);
	}

	@Override
	public Integer createResource(Permission resource, User user)
	{
		Permission resourceParent = mapper.selectByPrimaryKey(resource.getParentId());
		StringBuffer sb = new StringBuffer("");
		if (resourceParent != null) {
			//String structNameP = resourceParent.getStructName();
			sb.append(resourceParent.getStructure())
					.append("-");
		}else{
			sb.append("s").append("-");
		}
		setResourceInsert(resource,user);
		int result = mapper.insertSelective(resource);
		sb.append(resource.getId());
		resource.setStructure(sb.toString());
		updateByPrimaryKeySelective(resource);
		return result;
		//生成structure
		/*String structure = "1";
		Permission parentResource = mapper.selectByPrimaryKey(resource.getParentId());
		Map<Object,Object> map = Maps.newHashMap();
		map.put("parentId",resource.getParentId());
		List<Permission> resources = mapper.selectListByMap(map);
		if(resources==null || resources.size()==0)
		{
			structure = parentResource.getStructure()+"-1";
		} else {
			Integer parentLevel = parentResource.getStructure().split("-").length;

			for(Permission r:resources)
			{
				String[] structures = r.getStructure().split("-");
				if(structures.length == parentLevel + 1)
				{
					if(StringUtil.isNumber(structures[structures.length-1])&& StringUtil.compareTo(structures[structures.length-1], structure)>0)
					{
						structure = structures[structures.length-1];
					}
				}
			}
			structure = String.valueOf(Integer.parseInt(structure) + 1);
			structure = parentResource.getStructure()+"-" + structure;
		}

		resource.setStructure(structure);

		setResourceInsert(resource,user);

		return mapper.insertSelective(resource);*/

	}

	@Override
	public Integer updateResourceById(Permission resource,User user)
	{
		setResourceUpdate(resource,user);
		return mapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public Integer deleteResourceById(Integer id)
	{
		return mapper.deleteByPrimaryKey(id);
	}


	@Override
	public Boolean isUsedByRole(Integer resourceId) {
		return mapper.isUsedByRole(resourceId);
	}

	/**
	 * 获取treedata树型结构
	 */
	@Override
	public String getPermissionTree(Integer roleId){

		Set<Integer> setResource = new HashSet<Integer>();

		if(roleId != null && roleId >0)
		{
			List<Permission> tempResourceList = mapper.getPermissionListByRoleId(roleId);
			if(tempResourceList!=null && tempResourceList.size()>0)
			{
				for(Permission r:tempResourceList)
				{
					setResource.add(r.getId());
				}
			}
		}

		List<Module> moduleList = moduleDao.selectListByMap(null);
		List<Permission> resourceList = mapper.selectListByMap(null);
		Collections.sort(moduleList,new ComparatorModule());
		Collections.sort(resourceList,new ComparatorResource());

		Set<Integer> setParent = new HashSet<Integer>();
		for(Permission r:resourceList)
		{
			setParent.add(r.getParentId());
		}

		StringBuilder sb = new StringBuilder();
		Map<String,Integer> mapModule = new HashMap<String,Integer>();
		sb.append("[");
		int i = 0;
		for(Module m:moduleList)
		{
			mapModule.put(m.getFlag(), m.getId());
			if(i!=0)
			{
				sb.append(",");
			}
			i++;
			sb.append("{")
					.append("\"id\":\"").append("m_").append(m.getId()).append("\"")
					.append(",\"parent\":\"").append("#\"")
					.append(",\"text\":\"").append(m.getName()).append("\"")
					.append(",\"li_attr\":{\"flag\":\"").append(m.getFlag()).append("\"}");
			//前两个级别默认打开
			sb.append(",\"state\":{");
			sb.append("\"opened\":true");
			sb.append("}");
			sb.append("}");
		}
		i = 0;
		for(Permission r:resourceList)
		{
			int level = r.getStructure().split("-").length;

			sb.append(",");

			i++;
			sb.append("{")
					.append("\"id\":\"").append(r.getId()).append("\"")
					.append(",\"parent\":\"").append(r.getParentId()==0?("m_"+mapModule.get(r.getModuleFlag())):r.getParentId()).append("\"")
					.append(",\"text\":\"").append(r.getName()).append("\"")
					.append(",\"li_attr\":{\"flag\":\"").append(r.getModuleFlag())
					.append("\",\"sortNo\":").append(r.getSortNo()).append("}");
			//前两个级别默认打开
			if(level <=2)
			{
				sb.append(",\"state\":{\"opened\":true");
				if(setResource.contains(r.getId()))
				{
					sb.append(",\"selected\":true");
				}
				sb.append("}");
			} else {

				if(setResource.contains(r.getId()))
				{
					sb.append(",\"state\":{\"opened\":true}");
				}
			}
			//最后一个级别换个绿色图标
			if(!setParent.contains(r.getId()))
			{
				sb.append(", \"icon\": \"fa fa-briefcase icon-success\"");
			}
			sb.append("}");
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Module排序器，保证jsTree可以按照SortNo字段显示
	 */
	class ComparatorModule implements Comparator<Module> {
		public int compare(Module r1, Module r2) {
			return r1.getSortNo().compareTo(r2.getSortNo());
		}
	}

	/**
	 * Resource排序器，保证jsTree可以按照SortNo字段显示
	 */
	class ComparatorResource implements Comparator<Permission> {
		public int compare(Permission r1, Permission r2) {
			int l1 = r1.getStructure().length();
			int l2 = r2.getStructure().length();
			if(l1 == l2 )
			{
				return r1.getSortNo().compareTo(r2.getSortNo());
			}
			return l1>l2?1:-1;
		}
	}

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
