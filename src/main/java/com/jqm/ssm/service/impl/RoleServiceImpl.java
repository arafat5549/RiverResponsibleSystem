package com.jqm.ssm.service.impl;

import java.util.*;

import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.RoleSearchModel;
import com.jqm.ssm.util.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jqm.ssm.service.IRoleService;
import com.jqm.ssm.dao.RoleDao;
import com.jqm.ssm.entity.Role;

import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.util.PageUtil;

import com.google.common.base.Strings;
import com.jqm.ssm.util.page.Page;
import com.jqm.ssm.misc.Constants;
import com.google.common.collect.Maps;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * IRoleService 接口实现类
 *
 * @author wang
 */
@Service(value = "roleService")
public class RoleServiceImpl implements IRoleService {

	private static final Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|Role|";
	private static final Class<Role> SELF_CLASS = Role.class;

	@Autowired
	private RoleDao mapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public Role selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<Role> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public Role selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<Role> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<Role> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<Role> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
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
    public List<Role> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public Role selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			Role result_cache = redisCache.getCache(cache_key, SELF_CLASS);
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
	public int insert(Role record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Role record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		return mapper.updateByPrimaryKey(record);
	}


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	private void setPersonInsert(Role role, User user)
	{
		Date d = new Date();
		role.setCreatePerson(user.getUsername());
		role.setUpdatePerson(user.getUsername());
		role.setCreateDate(d);
		role.setUpdateDate(d);
	}
	private void setPersonUpdate(Role role,User user)
	{
		Date d = new Date();
		role.setUpdatePerson(user.getUsername());
		role.setUpdateDate(d);
	}

	@Override
	public Integer createRole(Role role, User user) {

		setPersonInsert(role,user);
		return mapper.insertSelective(role);
	}

	@Override
	public Integer updateRoleById(Role role,User user) {
		setPersonUpdate(role,user);
		return mapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public Integer deleteRoleById(Integer id)
	{
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer getRoleTotalBySearch(RoleSearchModel searchModel)
	{
		return mapper.getRoleTotalBySearch(searchModel);
	}

	@Override
	public List<Role> getRoleListBySearch(RoleSearchModel searchModel)
	{
		return mapper.getRoleListBySearch(searchModel);
	}

	@Override
	public Boolean isUsedByUser(Integer roleId)
	{
		return mapper.isUsedByUser(roleId);
	}
	@Override
	public List<Role> getRoleListByUserId(Integer userId){
		return mapper.getRoleListByUserId(userId);
	}

	@Override
	@Transactional
	public String assignModuleAndResource(Integer roleId,String checkedStr)
	{
		String[] checkedArr = checkedStr.split(",");
		List<Integer> moduleIds = new ArrayList<Integer>();
		List<Integer> resourceIds = new ArrayList<Integer>();
		for(String s:checkedArr)
		{
			if(s.startsWith("m_"))
			{
				moduleIds.add(Integer.parseInt(s.replace("m_", "")));
			} else {
				resourceIds.add(Integer.parseInt(s));
			}
		}

		mapper.deleteRoleModuleById(roleId);
		if(moduleIds.size()>0)
		{
			mapper.assignModules(moduleIds, roleId);
		}

		mapper.deleteRoleResourceById(roleId);
		if(resourceIds.size()>0)
		{
			mapper.assignResources(resourceIds, roleId);
		}

		return Constants.Success;
	}

	//
	@Override
	public String getRoleDataTables(RoleSearchModel searchModel)
	{
		Integer total = getRoleTotalBySearch(searchModel);
		List<Role> roleList = getRoleListBySearch(searchModel);
		if(roleList==null || roleList.size()==0)
		{
			return "{\"iTotalRecords\":0,\"iTotalDisplayRecords\":0,\"aaData\":[]}";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("{\"iTotalRecords\":%d,\"iTotalDisplayRecords\":%d,\"aaData\":[",total,total));
		int i= 0;
		for(Role r:roleList)
		{
			if(i != 0) sb.append(",");
			addDataRow(sb,r);
			i++;
		}
		sb.append("]}");
		return sb.toString();
	}

	@Override
	public String getRoleDataRow(Integer id)
	{
		Role r = selectByPrimaryKey(id);
		StringBuilder sb = new StringBuilder();
		addDataRow(sb,r);
		return sb.toString();
	}

	private void addDataRow(StringBuilder sb,Role r)
	{
		sb.append("[");
		sb.append("\"<input type=\\\"checkbox\\\" name=\\\"id[]\\\" value=\\\"").append(r.getId()).append("\\\">\"");
		sb.append(",").append(r.getId());
		sb.append(",\"").append(r.getName()).append("\"");
		sb.append(",\"").append(r.getRemark()).append("\"");
		sb.append(",\"").append(r.getUpdatePerson()).append("\"");
		sb.append(",\"").append(StringUtil.formatDate(r.getUpdateDate(),"yyyy-MM-dd HH:mm:ss")).append("\"");
		sb.append(",\"")
				.append("<a href=\\\"javascript:Role.update_click('").append(r.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-edit\\\"></i> 修改</a>")
				.append("&nbsp;&nbsp;<a href=\\\"javascript:Role.remove('").append(r.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-times\\\"></i> 删除</a>")
				.append("&nbsp;&nbsp;<a href=\\\"javascript:Role.assign_click('").append(r.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-key\\\"></i> 分配权限</a>")
				.append("\"");
		sb.append("]");
	}

	@Override
	public String getRoleForOptions(Integer userId)
	{
		List<Role> assignRoles = getRoleListByUserId(userId);
		List<Role> allRoles = selectListByMap(null);

		Map<Integer,Role> hmAssignRoles = new HashMap<Integer,Role>();
		for(Role r:assignRoles)
		{
			hmAssignRoles.put(r.getId(),r);
		}
		StringBuilder sb = new StringBuilder();
		for(Role r:allRoles)
		{
			sb.append("<option value=\"").append(r.getId()).append("\"");
			if(hmAssignRoles.containsKey(r.getId()))
			{
				sb.append(" selected");
			}
			sb.append(">").append(r.getName()).append("</option>");
		}
		return sb.toString();
	}

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
